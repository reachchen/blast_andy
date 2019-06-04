package com.yhbc.base.common.view.tablayout.utils;


import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.yhbc.base.common.view.tablayout.widget.MsgView;


/**
 * 未读消息提示View,显示小红点或者带有数字的红点:
 * 数字一位,圆
 * 数字两位,圆角矩形,圆角是高度的一半
 * 数字超过两位,显示99+
 */
public class UnreadMsgUtils {
    public static void show(MsgView msgView, int num) {
        if (msgView == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        DisplayMetrics dm = msgView.getResources().getDisplayMetrics();
        msgView.setVisibility(View.VISIBLE);
        if (num <= -1) {//圆点,设置默认宽高
            msgView.setStrokeWidth(0);
            msgView.setText("");
            msgView.setBackgroundColor(Color.RED);
            lp.width = (int) (8 * dm.density);
            lp.height = (int) (8 * dm.density);
            msgView.setLayoutParams(lp);
        } else {
            lp.height = (int) (18 * dm.density);
            if (num >= 0 && num < 10) {//圆
                lp.width = (int) (18 * dm.density);
                msgView.setText(num + "");
            } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
                msgView.setText(num + "");
            } else {//数字超过两位,显示99+
                lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
                msgView.setText("99+");
            }
            msgView.setLayoutParams(lp);
        }
    }

    public  static  void show2(MsgView msgView, int num){
        if (msgView == null||num<=-1) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
        DisplayMetrics dm = msgView.getResources().getDisplayMetrics();
        msgView.setVisibility(View.VISIBLE);
        lp.height = (int) (18 * dm.density);
        lp.topMargin = (int) (15 * dm.density);
        if (num >= 0 && num < 10) {//圆
            lp.width = (int) (24 * dm.density);
//            msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
            msgView.setText(num + "");
        } else if (num > 9 && num < 100) {//圆角矩形,圆角是高度的一半,设置默认padding
            lp.width = (int) (36 * dm.density);
//            msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
            msgView.setText(num + "");
        }
        else {//数字超过两位,显示99+
            lp.width = (int) (48 * dm.density);
//            msgView.setPadding((int) (6 * dm.density), 0, (int) (6 * dm.density), 0);
            msgView.setText("99+");
        }
        msgView.setLayoutParams(lp);
    }

    public static void setSize(MsgView rtv, int size, int xOffset, int yOffset) {
        if (rtv == null) {
            return;
        }
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rtv.getLayoutParams();
        lp.width = lp.WRAP_CONTENT;
        lp.height = size;
        lp.topMargin = xOffset;
        lp.leftMargin = yOffset;
        rtv.setLayoutParams(lp);
    }
}
