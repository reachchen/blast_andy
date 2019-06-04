package com.yhbc.base.common.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.KeyboardNewUtil;
import com.yhbc.base.common.utils.MyWindowManager;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/25.
 */

@SuppressLint("AppCompatCustomView")
public class CustomInputEditText extends EditText {
    Context context;
    private Drawable mClearDrawable;

    /**
     * 是否点击右边的图标弹出键盘
     */
    private boolean isTouchRight = true;
    /**
     * 是否显示默认图标
     */
    private boolean isShowIcon;

    private boolean isFull = false;

    public CustomInputEditText(final Context context) {
        super(context);
        initEditText(context, null);
    }

    public CustomInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditText(context, attrs);
    }

    public CustomInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditText(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomInputEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initEditText(context, attrs);

    }

    public void setFull(boolean full) {
        isFull = full;
    }

    /**
     * 设置点击右边图标弹出键盘
     *
     * @param touchRight 默认为true
     */
    public void setTouchRight(boolean touchRight) {
        isTouchRight = touchRight;
    }

    private void initEditText(Context context, AttributeSet attrs) {
        if (null != attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomInputEditText);
            isTouchRight = a.getBoolean(R.styleable.CustomInputEditText_isClickRight, true);
            isShowIcon = a.getBoolean(R.styleable.CustomInputEditText_isShowIcon, true);
            a.recycle();
        }
        this.context = context;
        this.setCursorVisible(true);
        try {//修改光标的颜色（反射）
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this, R.drawable.color_cusrsor);

        } catch (Exception ignored) {
            // TODO: handle exception
        }
        KeyboardNewUtil.getInstanse().etInputNull(this, context);

        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null && isShowIcon) {
            mClearDrawable = getResources().getDrawable(R.mipmap.keyboard_icon);
        }
        //设置图标的位置以及大小,getIntrinsicWidth()获取显示出来的大小而不是原图片的带小
        if (null != mClearDrawable) {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        }//        //默认设置隐藏图标
        setClearIconVisible(false);
    }

    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (!isTouchRight || getCompoundDrawables()[2] != null) {
                boolean touchable = !isTouchRight ||
                        (event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight()))));
                if (touchable && null != context) {
                    MyWindowManager.getInstanse().createInputWindow(context, this, isFull);
                }
            }
//
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setClearIconVisible(true);
            KeyBardView keyboardView = MyWindowManager.getInstanse().getInputWindow();
            if (keyboardView != null) {
                keyboardView.setInput_et(this);
            }
        } else {
            setClearIconVisible(false);
        }
    }
}
