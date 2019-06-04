package com.yhbc.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.yhbc.base.common.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T> extends AppCompatActivity {

    public String TAG = "BaseActivity---->";
//    private Unbinder mUnBinder;

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initView();
    }

    private void initView() {
        setContentView(getLayoutView());
        ButterKnife.bind(this);
        setWhiteStatusBar();
        init();
    }

    /**
     * 供子类初始化一些配置
     */
    protected void init() {
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutView();

    /**
     * 初始化业务层的实例
     */
    public void initPresenter() {
    }

    /**
     * 初始化状态栏
     */
    public void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 50);
    }

    /**
     * 设置亮色状态栏背景时6.0已上的改变状态栏字体色值
     */
    public void setWhiteStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 设置状态栏底色颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getStatusBarColor());

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(getStatusBarColor())) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        } else {
            initStatusBar();
        }
    }

    /**
     * 设置亮色状态栏时，更改状态栏字体颜色为黑色
     *
     * @return
     */
    protected @ColorInt
    int getStatusBarColor() {
        return Color.WHITE;
    }


    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     */
    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, getPackageName().getClass().getName() + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, getPackageName().getClass().getName() + "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, getPackageName().getClass().getName() + "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, getPackageName().getClass().getName() + "onPause");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, getPackageName().getClass().getName() + "onDestroy");
//        if (mUnBinder != Unbinder.EMPTY) {
//            mUnBinder.unbind();
//        }
    }


}
