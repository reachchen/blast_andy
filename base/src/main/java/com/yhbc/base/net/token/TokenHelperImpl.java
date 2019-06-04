package com.yhbc.base.net.token;

import android.app.Application;

import com.alibaba.fastjson.JSONObject;
import com.yhbc.base.framework.rx.BaseObserver;
import com.yhbc.base.net.token.api.ITokenHelper;
import com.yhbc.base.net.token.api.LoginCallback;
import com.yhbc.base.net.token.bean.ResponseBean;
import com.yhbc.base.net.token.bean.TokenBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author xuhaijiang on 2018/9/25.
 */
public class TokenHelperImpl implements ITokenHelper {

    private LoginCallback callback;
    private Application app;

    public TokenHelperImpl(LoginCallback callback, Application app) {
        this.callback = callback;
        this.app = app;
    }

    @Override
    public Response refreshToken(Interceptor.Chain chain, Request request) {

        TokenBean bean = TokenUtils.getDataFromSp(app);
        ResponseBean responseBean = refreshToken(bean, chain, request);

        return responseBean.getResponse();
    }

    /**
     * 刷新token
     *
     * @param tokenBean token
     */
    private ResponseBean refreshToken(TokenBean tokenBean, final Interceptor.Chain chain, final Request request) {
        final ResponseBean responseBean = new ResponseBean();
        Map<String, Object> map = new HashMap<>(1);
        map.put("refresh_token", tokenBean.getRefreshoken());
        String json = JSONObject.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        Map<String, String> headMap = new HashMap<>(2);
        headMap.put("token", tokenBean.getToken());
        callback.refreshToken(requestBody, headMap)
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe(new BaseObserver<TokenBean>() {
                    @Override
                    public void onSuccess(TokenBean bean) {
                        TokenUtils.saveData(app, bean.getToken(), bean.getRefreshoken());
                        requestAgain(responseBean, bean.getToken(), chain, request);
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        callback.reLogin(errorMsg);
                    }
                });

        /*String jsonString = JSONObject.toJSONString(map);
        MediaType JSON = MediaType.parse("application/json;");
        RequestBody body = RequestBody.create(JSON, jsonString);
        //使用call.execute()执行同步网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request1 = new Request.Builder().url(UrlEnum.TOKEN.getUrl() + TokenApiService.URL_HEAD_SPEC)
                .addHeader("token", !TextUtils.isEmpty(TokenUtils.getDataFromSp(BaseAppication.baseApp).getToken()) ? TokenUtils.getDataFromSp(BaseAppication.baseApp).getToken() : "")
                .addHeader("Content-Type", "application/json")
                .addHeader("invoke_source", "2234")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request1);
        ResponseBody body1 = null;
        try {
            body1 = call.execute().body();
            String string = body1.string();
            CommonResult<TokenBean> beanBaseResponse = JSONObject.parseObject(string, new TypeReference<CommonResult<TokenBean>>() {
            });
            if ("10000".equals(beanBaseResponse.getCode())) {
                TokenUtils.saveData(app, beanBaseResponse.getData().getToken(), beanBaseResponse.getData().getRefreshoken());
                requestAgain(responseBean, beanBaseResponse.getData().getToken(), chain, request);
            } else {
                callback.reLogin(beanBaseResponse.getSubMsg());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return responseBean;
    }

    /**
     * 重新请求
     */
    private void requestAgain(ResponseBean responseBean, String token, Interceptor.Chain chain, Request request) {
        Request.Builder build = request.newBuilder().removeHeader("token").addHeader("token", token);
        try {
            Request newRequest = build.build();
            //重构幂等参数
            newRequest = TokenInterceptorImpl.idempotent(newRequest, token);
            Response response = chain.proceed(newRequest);
            responseBean.setResponse(response);
            responseBean.setSuccess(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerTokenOver() {

    }
}
