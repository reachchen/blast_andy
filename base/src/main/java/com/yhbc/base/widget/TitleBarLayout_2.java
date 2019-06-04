package com.yhbc.base.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.KeyboardTool;
import com.yhbc.base.common.utils.ToastUtil;

/**
 * 带搜索动画的标题栏
 */
public class TitleBarLayout_2 extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    public RelativeLayout myBarLayout;
    public TextView tvTitle;
    public TextView tvCancel;
    public ImageView ivSearch;
    public ImageView ivBack;
    public ImageView ivPrint;
    public RelativeLayout rlSearchLayout;
    public EditText etSearch;
    private ImageView ivClearImg;

    TitleBarViewListener barListener;
    private boolean showPrint;

    public TitleBarLayout_2(Context context) {
        this(context, null);
    }

    public TitleBarLayout_2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.layout);

    }

    public TitleBarLayout_2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_bar_2, this, true);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBarLayout, defStyleAttr, 0);
        //初始化
        initStatusFormAttr(view, attributes);
        //设置按键监听
        setListener();
    }
    /**
     * 从标签读取属性设置title的样式
     * @param view
     * @param attributes
     */
    private void initStatusFormAttr(View view, TypedArray attributes) {
        myBarLayout = view.findViewById(R.id.title_bar_layout_2);
        ivBack = view.findViewById(R.id.img_bar_back);
        tvTitle = view.findViewById(R.id.tv_bar_title);
        ivSearch = view.findViewById(R.id.img_bar_search);
        ivPrint = view.findViewById(R.id.img_bar_print);
        tvCancel = view.findViewById(R.id.tv_bar_cancel);
        rlSearchLayout = view.findViewById(R.id.rl_search_layout);
        etSearch = view.findViewById(R.id.et_title_search);
        ivClearImg = view.findViewById(R.id.iv_clear_img);
        //构建标题
        boolean showTitle = attributes.getBoolean(R.styleable.TitleBarLayout_showTitle, true);
        setVisibility(tvTitle, showTitle);
        if (showTitle) {
            String title = attributes.getString(R.styleable.TitleBarLayout_titleText);
            tvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        }
        //构建右边搜索按钮
        boolean showMore = attributes.getBoolean(R.styleable.TitleBarLayout_showMore, false);
        setVisibility(ivSearch, showMore);
        if (showMore) {
            Integer moreIcon = attributes.getResourceId(R.styleable.TitleBarLayout_moreIcon, R.mipmap.icon_more);
            ivSearch.setImageResource(moreIcon);
        }
        //构建右边打印按钮
        showPrint = attributes.getBoolean(R.styleable.TitleBarLayout_showPrint, false);
        setVisibility(ivPrint, showPrint);
        if (showPrint) {
            Integer deriveIcon = attributes.getResourceId(R.styleable.TitleBarLayout_deriveIcon, R.mipmap.icon_derive);
            ivPrint.setImageResource(deriveIcon);
        }
        //构建右边TextView
        boolean showRight = attributes.getBoolean(R.styleable.TitleBarLayout_showRight, false);
        if (showRight) {
            tvCancel.setVisibility(VISIBLE);
            String rightText = attributes.getString(R.styleable.TitleBarLayout_rightText);
            tvCancel.setText(TextUtils.isEmpty(rightText) ? "please set right text" : rightText);
        }
        int bgColor = attributes.getColor(R.styleable.TitleBarLayout_bgColor, -1);
        if (bgColor != -1) {
            myBarLayout.setBackgroundColor(bgColor);
            tvTitle.setTextColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * 设置对标题栏按钮的监听
     * @param listener
     */
    public void setOnViewClickListener(TitleBarViewListener listener){
        barListener = listener;
    }

    /**
     * 设置点击事件
     */
    private void setListener() {
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivPrint.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        ivClearImg.setOnClickListener(this);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        barListener.searchEditContext(etSearch.getText().toString());
                        KeyboardTool.hideSoftInput(mContext, etSearch);
                    } else {
                        ToastUtil.show(mContext, "请输入物品名称");
                    }
                    return true;
                }
                return false;
            }
        });
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
        if (i== R.id.img_bar_back) {
            if (mContext != null && mContext instanceof Activity) {
                ((Activity) mContext).finish();
            }
            return;
        }
        //搜索键
        if(i==R.id.img_bar_search){
            if(barListener!=null){
                barListener.searchViewShow(rlSearchLayout,etSearch);
            }
            if(ivPrint.getVisibility()==View.VISIBLE){
                ivPrint.setVisibility(View.GONE);
            }
            rlSearchLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.slide_right_in));
            KeyboardTool.showSoftInput(mContext, etSearch);
            rlSearchLayout.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
            ivSearch.setVisibility(View.GONE);
            return;
        }
        //取消按钮
        if(i==R.id.tv_bar_cancel){
            rlSearchLayout.setVisibility(View.GONE);
            tvCancel.setVisibility(View.GONE);
            ivSearch.setVisibility(View.VISIBLE);
            etSearch.setText("");
            if(barListener!=null){
                barListener.searchViewDismiss(rlSearchLayout,etSearch);
            }
            rlSearchLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_right_out));
            KeyboardTool.hideSoftInput(mContext, etSearch);
            if(showPrint){
                ivPrint.setVisibility(View.VISIBLE);
            }
            return;
        }
        //清空et内容的按钮
        if(i==R.id.iv_clear_img){
            etSearch.setText("");
            return;
        }
        //打印按钮
        if(i==R.id.img_bar_print){
            if(barListener!=null){
                barListener.printAction();
            }
            return;
        }

    }

    public interface TitleBarViewListener{

        void searchViewShow(View view,EditText etView);

        void searchViewDismiss(View view ,EditText etView);

        void searchEditContext(String context);

        void printAction();
    }
}
