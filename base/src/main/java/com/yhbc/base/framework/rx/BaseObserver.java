package com.yhbc.base.framework.rx;

import com.alibaba.fastjson.JSON;
import com.yhbc.base.framework.exception.ApiException;
import com.yhbc.base.framework.exception.ExceptionEngine;
import com.yhbc.base.net.token.bean.CommonResult;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author SXQ
 * Date 2018/6/21
 * Explain 封装订阅者用于回调代替RxSubscriber(rx1.0到2.0的升级)
 */
public abstract class BaseObserver<T> implements Observer<CommonResult<T>> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull CommonResult<T> result) {
//        Type type=  new  TypeToken<CommonResult<T>>(){}.getType();
//        CommonResult<T> result = new Gson().fromJson(s.toString(),type);
        //请求成功
        if (result.getCode().equals("10000")) {
            if (null != result.getData()) {
                onSuccess(result.getData());
            } else {
                onSuccess((T) result.getSubMsg());
            }
        } else {
            String code = result.getCode();
            if (code.contains("41006")) {
                onFailed(JSON.toJSONString(result));
            } else {
                onError(new ApiException(result.getSubMsg()));
            }
        }
    }

    //自己封装失败的回调
    @Override
    public void onError(@NonNull Throwable e) {
        ApiException ex = ExceptionEngine.handleException(e);
        onFailed(ex.getMsg());
        onComplete();
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T result);

    public abstract void onFailed(String errorMsg);

}