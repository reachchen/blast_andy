package com.yhbc.base.service;


/**
 * 组件生命周期管理类
 * Created by xuhaijiang on 2018/5/4.
 */
public interface IApplicationLike {

    void onCreate();

    void onStop();

    /**
     * @return 入口Activity
     */
//    Class<? extends Activity> getMainAct();
}
