package com.yhbc.base.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * drabableRight相应点击
 * Created by xuhaijiang on 2018/3/26.
 */
public class DropDownTextView extends TextView {

    final int DRAWABLE_RIGHT = 2;

    public DropDownTextView(Context context) {
        super(context);
    }

    public DropDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DropDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnDropArrowClickListener {
        void onDropArrowClick();
    }

    /**
     * 设置图片变化已响应 右部点击事件
     * @param hintResId 默认图片
     * @param clickResId 点击图片
     */
    public void setDrawRightRes(final int hintResId, final int clickResId){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        "".equals(editable.toString()) ? hintResId : clickResId, 0);

            }
        });
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

                    if (onDropArrowClickListener != null && !"".equals(getText().toString())) {
                        onDropArrowClickListener.onDropArrowClick();
                        return true;
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
