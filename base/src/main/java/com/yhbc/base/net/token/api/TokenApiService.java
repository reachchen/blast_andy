package com.yhbc.base.net.token.api;

import com.yhbc.base.net.UrlEnum;
import com.yhbc.base.net.YKey;
import com.yhbc.base.net.YUrl;
import com.yhbc.base.net.token.bean.CommonResult;
import com.yhbc.base.net.token.bean.TokenBean;
import com.yhbc.base.net.token.bean.TokenData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author xuhaijiang on 2018/9/25.
 */
@YUrl(UrlEnum.TOKEN)
public interface TokenApiService {
    /**
     * 特殊的请求，不做拦截
     */
    String URL_HEAD_SPEC = "common/refreshToken";

    /**
     * 刷新token
     *
     * @param body  参数
     * @param heads 添加的头部
     * @return TokenBean中只有expireTime和refreshoken字段有值
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(URL_HEAD_SPEC)
    Observable<CommonResult<TokenBean>> refreshToken(@Body RequestBody body, @HeaderMap Map<String, String> heads);


}
