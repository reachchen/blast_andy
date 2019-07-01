package com.yhbc.base.net.token;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.yhbc.base.BaseApplication;
import com.yhbc.base.net.token.bean.TokenBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * token 工具类
 *
 * @author xuhaijiang on 2018/9/25.
 */
public class TokenUtils {

    private static SharedPreferences sp;
    private static final String KEY_SETTINGS = "token";

    /**
     * 智能pos head静态值
     */
    private static String invokeSource = "2101";

    public static void setInvokeSource(String invokeSource) {
        TokenUtils.invokeSource = invokeSource;
    }

    public static String getInvokeSource() {
        return invokeSource;
    }

    /**
     * 保存token数据
     *
     * @param token        。
     * @param refreshToken 。
     */
    public static void saveData(Context context, String token, String refreshToken) {
        if (null == sp) {
            sp = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
        }
        if (null != token && refreshToken != null) {
            sp.edit().putString("yhbc_token", token)
                    .putString("yhbc_refresh_token", refreshToken)
                    .apply();
        }
    }

    /**
     * 保存api配置
     *
     * @param context context
     * @param map     上下文
     */
    public static void saveApiConfig(Context context, Map<String, String> map) {
        if (null == sp) {
            sp = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
        }
        if (null != map) {
            sp.edit().putString("api_config", JSONObject.toJSONString(map))
                    .apply();
        }
    }

    /**
     * 获取api配置
     *
     * @param context context
     * @return api配置键值对
     */
    public static Map<String, String> getApiConfig(Context context) {
        if (null == sp) {
            sp = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
        }
        String config = sp.getString("api_config", "");
        if ("".equals(config)) {
            return new HashMap<>(0);
        }
        @SuppressWarnings("unchecked")
        Map<String, String> map = JSONObject.parseObject(config, Map.class);
        return map;
    }


    /**
     * 转化为map为body
     *
     * @param map map
     * @return body
     */
    public static RequestBody parseMap2Body(Map<String, Object> map) {
        String json = JSONObject.toJSONString(map);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
    }

    /**
     * @return http head
     */
    public static Map<String, String> getHeaderMap() {
        Map<String, String> headMap = new HashMap<>(3);
        //版本
        headMap.put("client_version", "0.929");
        headMap.put("invoke_source", invokeSource);
        TokenBean bean = TokenUtils.getDataFromSp(BaseApplication.baseApp);
        if (!TextUtils.isEmpty(bean.getToken())) {
            headMap.put("token", bean.getToken());
        }
        return headMap;
    }

    /**
     * 从sp中获取数据
     *
     * @return token
     */
    public static TokenBean getDataFromSp(Context context) {
        if (null == sp) {
            sp = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
        }
        String token = sp.getString("yhbc_token", "");
        String refreshToken = sp.getString("yhbc_refresh_token", "");
        TokenBean bean = new TokenBean();
        bean.setToken(token);
        bean.setRefreshoken(refreshToken);
        return bean;
    }

}
