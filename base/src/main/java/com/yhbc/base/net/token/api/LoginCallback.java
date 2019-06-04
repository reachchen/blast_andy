package com.yhbc.base.net.token.api;

import com.yhbc.base.net.token.bean.CommonResult;
import com.yhbc.base.net.token.bean.TokenBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
/**
 * 重新登录，回调
 *
 * @author xuhaijiang on 2018/9/27.
 */
public interface LoginCallback {

    /**
     * 重新登录
     */
    void reLogin(String msg);

    /**
     * 刷新token
     *
     * @param body  body中的数据
     * @param heads header参数 已包含invoke_source和token，如果invoke_source和pos不同，请重写
     * @return 。
     */
    Observable<CommonResult<TokenBean>> refreshToken(@Body RequestBody body, @HeaderMap Map<String, String> heads);


}
