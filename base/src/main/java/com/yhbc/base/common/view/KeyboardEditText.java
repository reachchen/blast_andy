package com.yhbc.base.common.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.KeyboardNewUtil;
import com.yhbc.base.common.utils.MyWindowManager;
import com.yhbc.base.common.utils.SoftKeyboardUtils;

import java.lang.reflect.Field;

/**
 * <br>
 *
 * @author xuhaijaing
 *         created at 2018/6/15 上午9:11
 */
public class KeyboardEditText extends EditText {
    Context context;

    public KeyboardEditText(final Context context) {
        super(context);
//        initEditText(context);
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initEditText(context);
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        initEditText(context);

    }


    public void initEditText(Context context) {
        //在dialogFragment中view的context不是Activity
        this.context = context;
        this.setCursorVisible(true);
        try {//修改光标的颜色（反射）
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this, R.drawable.color_cursor);

        } catch (Exception ignored) {
            // TODO: handle exception
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                KeyboardNewUtil.getInstanse().etInputNull(KeyboardEditText.this, context);
                SoftKeyboardUtils.hideSoftKeyboard(KeyboardEditText.this);
            }
        });

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        KeyboardNewUtil.getInstanse().setEd(null);
//        context = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            MyWindowManager.getInstanse().createInputWindow(context, this, false);
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            KeyBardView keyboardView = MyWindowManager.getInstanse().getInputWindow();
            if (keyboardView != null) {
                keyboardView.setInput_et(this);
            }
        }
    }

    /**
     * 显示键盘
     */
    public void showKeybaord() {
        MyWindowManager.getInstanse().createInputWindow(context, this, false);
    }
}
