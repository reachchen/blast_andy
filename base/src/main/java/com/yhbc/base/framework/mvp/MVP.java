package com.yhbc.base.framework.mvp;

/**
 * Created by chengwen on 2017-11-22.
 */
public interface MVP {


    interface Presenter<V> {

        void attachView(V view);

        void detachView();
    }
}
