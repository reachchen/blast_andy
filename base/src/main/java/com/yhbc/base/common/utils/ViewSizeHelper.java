package com.yhbc.base.common.utils;

import android.view.View;
import android.view.ViewTreeObserver;

/**
 * size监听变化，获取宽高等数据
 * Created by xhj on 2018/3/22.
 */
public abstract class ViewSizeHelper implements ViewTreeObserver.OnGlobalLayoutListener {

    private View view;

    public ViewSizeHelper(View view) {
        this.view = view;
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        int[] size = new int[2];
        size[0] = view.getHeight();
        size[1] = view.getWidth();
        onLayouted(size);
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    /**
     * 布局已完成
     *
     * @param size 0:高；1：宽
     */
    protected abstract void onLayouted(int[] size);
}
