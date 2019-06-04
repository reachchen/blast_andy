package com.yhbc.base.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * 控制Drawable大小的Textview
 * Created by xuhaijiang on 2018/6/6.
 */
public class DrawableSizeTextView extends TextView {

    private int mDrawableSize;//drawable大小``

    public DrawableSizeTextView(Context context) {
        this(context, null);
    }

    public DrawableSizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Drawable drawableLeft = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;
        Drawable drawableTop = null;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawableSizeTextView);//获取自定义属性
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.DrawableSizeTextView_drawableSizes) {
                mDrawableSize = a.getDimensionPixelSize(R.styleable.DrawableSizeTextView_drawableSizes, 50);//获取drawable大小，没设置时默认为50dp

            } else if (attr == R.styleable.DrawableSizeTextView_drawableTop) {
                drawableTop = a.getDrawable(attr);//获取显示在上方的drawable

            } else if (attr == R.styleable.DrawableSizeTextView_drawableBottom) {
                drawableBottom = a.getDrawable(attr);//获取显示在下方的drawable

            } else if (attr == R.styleable.DrawableSizeTextView_drawableRight) {
                drawableRight = a.getDrawable(attr);//获取显示在右方的drawable

            } else if (attr == R.styleable.DrawableSizeTextView_drawableLeft) {
                drawableLeft = a.getDrawable(attr);//获取显示在左方的drawable
            }
        }
        a.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);//调用重写的方法进行设置drawable的大小.
    }
    /**
     *  重写TextView中的setCompoundDrawablesWithIntrinsicBounds的方法
     */
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);//drawable的bounds设置为了我们自定义大小了，第三个参数是设置宽度，第四个参数是设置高度，这里我就只设置了同一个也就是正方形了，如果想设置矩形的可以声明2个不同的自定义属性来设置。
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);//设置完了bounds就可以调用这个方法进行设置了 不用在代码中进行动态控制了。
    }

}
