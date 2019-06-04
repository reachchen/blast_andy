package com.yhbc.base.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * drawable 居中
 * Created by xuhaijiang on 16/4/28.
 */
public class DrawableCenterTextView extends TextView {

    /**
     * 方向
     */
    private int orientation = 0;

    public DrawableCenterTextView(Context context) {
        super(context);
        init(context, null);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        if (null != attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawableCenterTextView);
            orientation = a.getInteger(R.styleable.DrawableCenterTextView_orientation, 0);
            a.recycle();
        }
        setGravity(orientation == 0 ? (Gravity.START | Gravity.CENTER_VERTICAL) : (Gravity.CENTER_HORIZONTAL | Gravity.TOP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        //横向
        if (orientation == 0) {
            Drawable drawableHorizontal = drawables[0];
            if (drawableHorizontal == null) {
                drawableHorizontal = drawables[2];
            }
            if (drawableHorizontal != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth;
                drawableWidth = drawableHorizontal.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        } else {
            //纵向
            Drawable drawableVertical = drawables[1];
            if (drawableVertical == null) {
                drawableVertical = drawables[3];
            }
            if (null != drawableVertical) {
                float textHeight = getPaint().measureText(getText().toString(), 0, 1);
                int drawablePadding = getCompoundDrawablePadding();
                int drawableHeight = drawableVertical.getIntrinsicHeight();
                float bodyHeight = textHeight + drawableHeight + drawablePadding;
                canvas.translate(0,(getHeight() - bodyHeight) / 2);
            }
        }
        super.onDraw(canvas);
    }
}
