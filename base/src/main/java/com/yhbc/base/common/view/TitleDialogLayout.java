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
 * 弹框标题相对布局 <br>
 * @author xuhaijaing
 * created at 2018/6/14 上午11:30
 */
public class TitleDialogLayout extends RelativeLayout implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 点击监听
     */
    private OnClickListener listener;

    public TitleDialogLayout(Context context) {
        this(context, null);
    }

    public TitleDialogLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleDialogLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.base_title_dialog_layout, this, true);
        findViewById(R.id.iv_back).setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title_base);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleDialogLayout);
        String title = a.getString(R.styleable.TitleDialogLayout_dialog_title);
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
        int vId = v.getId();
        if (vId == R.id.iv_back) {
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
