package com.yhbc.base.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * @author SXQ
 * Date 2018/11/9
 * Explain 通用标题栏
 */
public class TitleBarLayout extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    public RelativeLayout layout;
    public TextView tvBack;
    public TextView tvTitle;
    public TextView tvRight;
    public ImageView ivMore;
    public ImageView ivBack;
    public ImageView ivPrint;

    public TitleBarLayout(Context context) {
        this(context, null);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.layout);
    }

    public TitleBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this, true);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBarLayout, defStyleAttr, 0);
        //初始化
        initStatusFormAttr(view, attributes);
        //设置按键监听
        setListener();
    }


    /**
     * 从标签读取属性设置title的样式
     *
     * @param view
     * @param attributes
     */
    private void initStatusFormAttr(View view, TypedArray attributes) {
        layout = view.findViewById(R.id.title_bar_layout);
        ivMore = view.findViewById(R.id.img_title_bar_more);
        ivBack = view.findViewById(R.id.img_title_bar_back);
        tvBack = view.findViewById(R.id.tv_title_bar_back);
        tvTitle = view.findViewById(R.id.tv_title_bar_title);
        tvRight = view.findViewById(R.id.tv_title_bar_right);
        ivPrint = view.findViewById(R.id.img_title_bar_print);
        //构建标题
        boolean showTitle = attributes.getBoolean(R.styleable.TitleBarLayout_showTitle, true);
        setVisibility(tvTitle, showTitle);
        if (showTitle) {
            String title = attributes.getString(R.styleable.TitleBarLayout_titleText);
            tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        }
        //构建右边icon更多
        boolean showMore = attributes.getBoolean(R.styleable.TitleBarLayout_showMore, false);
        setVisibility(ivMore, showMore);
        if (showMore) {
            Integer moreIcon = attributes.getResourceId(R.styleable.TitleBarLayout_moreIcon, R.mipmap.icon_more);
            ivMore.setImageResource(moreIcon);
        }
        //构建右边打印按钮
        boolean showPrint = attributes.getBoolean(R.styleable.TitleBarLayout_showPrint, false);
        setVisibility(ivPrint, showPrint);
        if (showPrint) {
            Integer deriveIcon = attributes.getResourceId(R.styleable.TitleBarLayout_deriveIcon, R.mipmap.icon_derive);
            ivPrint.setImageResource(deriveIcon);
        }
        //构建右边TextView
        boolean showRight = attributes.getBoolean(R.styleable.TitleBarLayout_showRight, false);
        if (showRight) {
            tvRight.setVisibility(VISIBLE);
            String rightText = attributes.getString(R.styleable.TitleBarLayout_rightText);
            tvRight.setText(TextUtils.isEmpty(rightText) ? "please set right text" : rightText);
        }
        //构建返回
        boolean showBack = attributes.getBoolean(R.styleable.TitleBarLayout_showBack, true);
        if (showBack) {
            boolean showBackByText = attributes.getBoolean(R.styleable.TitleBarLayout_showBackByText, false);
            if (showBackByText) {
                String leftText = attributes.getString(R.styleable.TitleBarLayout_leftText);
                tvBack.setText(TextUtils.isEmpty(leftText) ? getResources().getString(R.string.base_back) : leftText);
                tvBack.setVisibility(VISIBLE);
                ivBack.setVisibility(GONE);
            }
            Integer backIcon = attributes.getResourceId(R.styleable.TitleBarLayout_backIcon, R.mipmap.back_icon);
            ivBack.setImageResource(backIcon);
        } else {
            ivBack.setVisibility(GONE);
        }
        int bgColor = attributes.getColor(R.styleable.TitleBarLayout_bgColor, -1);
        if (bgColor != -1) {
            layout.setBackgroundColor(bgColor);
            tvTitle.setTextColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * 设置点击事件
     */
    private void setListener() {
        tvBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    /**
     * 设置view的可见性
     *
     * @param view 控件
     * @param show true-VISIBLE false-GONE
     */
    private void setVisibility(View view, boolean show) {
        if (view == null) {
            return;
        }
        view.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        //返回键
        if (i == R.id.tv_title_bar_back || i == R.id.img_title_bar_back) {
            if (mContext != null && mContext instanceof Activity) {
                ((Activity) mContext).finish();
            }
        }
    }

}
