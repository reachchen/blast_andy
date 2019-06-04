package com.yhbc.base.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * 标题相对布局
 * Created by xuhaijiang on 2018/5/22.
 */
public class TitleLayout extends RelativeLayout implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 点击监听
     */
    private OnClickListener listener;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.base_title_layout, this, true);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_back_base).setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title_base);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        String title = a.getString(R.styleable.TitleLayout_title);
        if (null != title) {
            tvTitle.setText(title);
        }
        a.recycle();
    }

    /**
     * 设置点击返回监听
     *
     * @param listener 监听
     */
    public void setBackListener(OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        int _Id = v.getId();
        if (_Id == R.id.iv_back || _Id == R.id.tv_back_base) {
            if (null != listener) {
                listener.onClick(v);
                return;
            }
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).finish();
            }
        }
    }
}
