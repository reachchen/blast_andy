package com.yhbc.base.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘
 * Created by xhj on 2018/3/20.
 */
public class SoftKeyboardUtils {

    /**
     * 手动隐藏软键盘
     *
     * @param context
     */
    public static void hideSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏
     *
     * @param view v
     */
    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示
     *
     * @param activity
     */
    public static void showSoftKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.showSoftInput(activity.getWindow().getDecorView(), InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 显示
     *
     * @param context
     * @param editText
     */
    public static void showSoftKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }
}
