package com.yhbc.base.common.view;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.KeyboardNewUtil;
import com.yhbc.base.common.utils.MyWindowManager;

import java.lang.reflect.Field;

/**
 * 作者：tianxiangmin on 2017/12/5 15:01
 * 邮箱：txm@blibao.com
 * 标题:
 */

public class KeyBardView extends LinearLayout {
    //记录悬浮窗的宽度
    public int viewWidth;
    //记录小悬浮窗的高度
    public int viewHeight;

    //记录系统状态栏的高度
    private int statusBarHeight;

    // 用于更新小悬浮窗的位置
    private WindowManager windowManager;

    //小悬浮窗的参数
    private WindowManager.LayoutParams mParams;

    // 记录当前手指位置在屏幕上的横坐标值
    private float xInScreen;

    // 记录当前手指位置在屏幕上的纵坐标值
    private float yInScreen;

    //记录手指按下时在屏幕上的横坐标的值
    private float xDownInScreen;

    // 记录手指按下时在屏幕上的纵坐标的值
    private float yDownInScreen;

    //记录手指按下时在小悬浮窗的View上的横坐标的值
    private float xInView;

    //记录手指按下时在小悬浮窗的View上的纵坐标的值
    private float yInView;

    private  Context key_context;
    private  EditText input_et;
    private KeyboardNewUtil keyboardNewUtil;
    public KeyBardView(final Context context, EditText et, final boolean full) {
        super(context);
        this.key_context = context;
        input_et = et;
        getStatusBarHeight();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        View content_view;
        content_view = LayoutInflater.from(context).inflate(R.layout.float_full_window, this);
        View view = findViewById(R.id.window_layout);
        LinearLayout ll_close = (LinearLayout) findViewById(R.id.ll_close);
        LinearLayout ll_full_small = (LinearLayout) findViewById(R.id.ll_full_small);
        final KeyboardView keyboard_view = (KeyboardView) findViewById(R.id.keyboard_view);

        ll_full_small.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWindowManager.getInstanse().removeInputWindowNotBoard();
                if(null != key_context) {
                    if (full) {
                        keyboard_view.setPadding(0, 0, 0, 0);
                        MyWindowManager.getInstanse().createInputWindow(key_context, input_et, false);
                    } else {
                        keyboard_view.setPadding(1, 1, 1, 1);
                        MyWindowManager.getInstanse().createInputWindow(key_context, input_et, true);
                    }
                }
            }
        });
        //改变这个
        keyboardNewUtil= KeyboardNewUtil.getInstanse().initKeyboard(context, input_et, content_view, full);
        ll_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWindowManager.getInstanse().removeInputWindow();
            }
        });
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                // 手指移动的时候更新小悬浮窗的位置
                if (Math.abs(xDownInScreen - xInScreen) > 1.5 || Math.abs(yDownInScreen - yInScreen) > 1.5) {
                    updateViewPosition();
                }
                break;
//            case MotionEvent.ACTION_UP:
//                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。
     *
     * @param params 小悬浮窗的参数
     */
    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }

    /**
     * 更新小悬浮窗在屏幕中的位置。
     */
    private void updateViewPosition() {

        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this, mParams);

    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {

        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    public void clean() {
        key_context = null;
        input_et = null;
    }
    public  void setInput_et(EditText input_et) {
        this.input_et = input_et;
        if (keyboardNewUtil!=null){
            keyboardNewUtil.setEd(input_et);
        }
    }
}
