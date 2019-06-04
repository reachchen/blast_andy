package com.yhbc.base.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * drabableRight相应点击
 * Created by xuhaijiang on 2018/3/26.
 */
public class DropDownEditText extends EditText{

    final int DRAWABLE_RIGHT = 2;

    public DropDownEditText(Context context) {
        super(context);
    }

    public DropDownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DropDownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public interface OnDropArrowClickListener {
        void onDropArrowClick();
    }

    private OnDropArrowClickListener onDropArrowClickListener;

    public void setOnDropArrowClickListener(OnDropArrowClickListener onDropArrowClickListener) {
        this.onDropArrowClickListener = onDropArrowClickListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
            if (drawableRight != null) {
                //本次点击事件的x轴坐标，如果>当前控件宽度-控件右间距-drawable实际展示大小
                if (event.getX() >= (getWidth() - getPaddingRight() - drawableRight.getIntrinsicWidth())) {
                    //设置点击EditText右侧图标EditText失去焦点，
                    // 防止点击EditText右侧图标EditText获得焦点，软键盘弹出
                    setFocusableInTouchMode(false);
                    setFocusable(false);
                    if (onDropArrowClickListener != null) {
                        onDropArrowClickListener.onDropArrowClick();
                    }
                } else {
                    setFocusableInTouchMode(true);
                    setFocusable(true);
                }
            }else {
                setFocusableInTouchMode(true);
                setFocusable(true);
            }
        }
        return super.onTouchEvent(event);
    }
}
