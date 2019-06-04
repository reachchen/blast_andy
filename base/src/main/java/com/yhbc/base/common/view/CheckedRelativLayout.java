package com.yhbc.base.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * 可单选的相对布局
 * Created by xuhaijiang on 2018/6/21.
 */
public class CheckedRelativLayout extends RelativeLayout implements Checkable {

    /**
     * 是否选中
     */
    private boolean isChecked;

    private int[] checkedState = {android.R.attr.state_checked};
    private int[] pressedState = {android.R.attr.state_pressed};
    private int[] unState = {};

    public CheckedRelativLayout(Context context) {
        super(context);
    }

    public CheckedRelativLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckedRelativLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        refreshDrawableState();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = getBackground();
        if (null != drawable) {
            if (isChecked) {
                drawable.setState(checkedState);
            } else if (isPressed()) {
                drawable.setState(pressedState);
            } else {
                drawable.setState(unState);
            }

        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
        refreshDrawableState();
    }
}
