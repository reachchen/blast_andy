package com.yhbc.base.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yhbc.base.BaseApplication;
import com.yhbc.base.R;


/**
 * Created by Alan on 2017/3/15.
 */
public class ToastUtil {
    private static Toast mToast;
    private static TextView mTextView;
    // 隐藏toast信息框常量
    public static final int AIRPLAY_MESSAGE_HIDE_TOAST = 22;
    // 显示toast信息框时间
    public static final int AIRPLAY_TOAST_DISPLAY_TIME = 1000;
    private static Handler  m_Handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // TODO Auto-generated method stub
            switch (msg.what) {
                case AIRPLAY_MESSAGE_HIDE_TOAST:
                    cancelToast();
                    break;
            }
        }
    };
    /**
     * 显示toast
     * @param context
     * @param resId
     */
    public static void showToast(Context context, int resId) {
            showToast(BaseApplication.baseApp, BaseApplication.baseApp.getResources().getString(resId));
    }

    /**
     * 显示时间短的Toast
     */
    public static void showShort(String msg) {
        if (TextUtils.isEmpty(msg) && null != BaseApplication.baseApp) {
            return;
        }
        mToast = Toast.makeText(BaseApplication.baseApp, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 显示居中toast
     *
     * @param context
     * @param text
     */
    public static void showCenterToast(Context context, String text) {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = Toast.makeText(BaseApplication.baseApp, text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 显示toast
     * @param text
     */
    public static void show(Context context, String text) {
        if (null != mToast) {
            mToast.cancel();
        }
        mToast = Toast.makeText(BaseApplication.baseApp, text, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void  customToastShow(Context context, String msg) {
        LayoutInflater inflater = (LayoutInflater)BaseApplication.baseApp.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(R.layout.mn_status_dialog_layout, null);
        //自定义toast文本R.layout.mn_status_dialog_layout
        mTextView = (TextView)view.findViewById(R.id.tvShow);
        mTextView.setText(msg);
        if (mToast == null) {
            mToast = new Toast(BaseApplication.baseApp);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
        Message delayMsg = m_Handler.obtainMessage(AIRPLAY_MESSAGE_HIDE_TOAST);
        m_Handler.sendMessageDelayed(delayMsg, AIRPLAY_TOAST_DISPLAY_TIME);
    }

    private static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
    /**
     * 自定义居中显示toast
     */
    public void show() {
        mToast.show();
    }

    /**
     * 隐藏toast
     */
    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
