package com.yhbc.base.model;

import android.view.View;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/8/20.
 * 按钮过快点击防护
 */

public class RxViewModel implements Observable.OnSubscribe<View> {


    public RxViewModel(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSubscriber!=null)
                    mSubscriber.onNext(v);
            }
        });
    }

    private Subscriber mSubscriber;
    @Override
    public void call(Subscriber<? super View> subscriber) {
        mSubscriber = subscriber;
    }
}
