package com.yhbc.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author SXQ
 * Date 2018/11/2
 * Explain
 */
public abstract class BaseFragment<T> extends Fragment {

    public String TAG = "BaseActivity---->";
    protected Context mContext;
    protected View mRootView;
    protected T mPresenter;
//    Unbinder unbinder;
    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;

    /**
     * Fragment对用户可见的标记
     */
    private boolean isUIVisible;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            this.mContext = getActivity();
            mRootView = inflater.inflate(getLayoutView(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initPresenter();
        init();
        lazyLoad();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    protected void init() {
    }

    protected void loadData(){}

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * 初始化业务层的实例
     */
    public void initPresenter() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}
