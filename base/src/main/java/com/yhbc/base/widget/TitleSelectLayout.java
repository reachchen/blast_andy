package com.yhbc.base.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * 项目名称：android_purchase_sale_storage
 * 包名称： com.yhbc.base.widget
 * 类描述： 标题栏选择框
 * 创建人： zhangzhihang
 * 创建时间：2018/12/8 13:59.
 */
public class TitleSelectLayout extends ConstraintLayout implements View.OnClickListener {

    public static final int SELECT_TYPE_LEFT = 0;
    public static final int SELECT_TYPE_RIGHT = SELECT_TYPE_LEFT + 1;

    public static final int CLICK_TYPE_BACK = 0;
    public static final int CLICK_TYPE_ADD = CLICK_TYPE_BACK + 1;
    public static final int CLICK_TYPE_SEARCH = CLICK_TYPE_ADD + 1;

    private Context mContext;
    private TextView tvLeft, tvRight, tvBack;
    private ImageView ivAdd, ivSearch;
    private ConstraintLayout layout;

    private TitleSelectCallBack callBack;

    public TitleSelectLayout(Context context) {
        this(context, null);
    }

    public TitleSelectLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.layout);
    }

    public TitleSelectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_title_select, this, true);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleSelectLayout, defStyleAttr, 0);
        initView(rootView, attributes);
        setListener();
    }

    private void initView(View rootView, TypedArray attributes) {
        String leftText = attributes.getString(R.styleable.TitleSelectLayout_leftTitleText);
        String rightText = attributes.getString(R.styleable.TitleSelectLayout_rightTitleText);
        String backText = attributes.getString(R.styleable.TitleSelectLayout_backText);
        int backTextSize = attributes.getInteger(R.styleable.TitleSelectLayout_backTextSize, 15);
        boolean showBack = attributes.getBoolean(R.styleable.TitleSelectLayout_showSelectBack, false);
        boolean showAdd = attributes.getBoolean(R.styleable.TitleSelectLayout_showAdd, false);
        boolean showSearch = attributes.getBoolean(R.styleable.TitleSelectLayout_showSearch, false);
        layout = rootView.findViewById(R.id.select_layout);
        tvLeft = rootView.findViewById(R.id.tv_title_left_select);
        tvRight = rootView.findViewById(R.id.tv_title_right_select);
        ivAdd = rootView.findViewById(R.id.iv_title_add);
        ivSearch = rootView.findViewById(R.id.iv_title_search);
        tvBack = rootView.findViewById(R.id.tv_title_back_text);
        if (showAdd) {
            Integer addIcon = attributes.getResourceId(R.styleable.TitleSelectLayout_addIcon, R.mipmap.tianjia);
            ivAdd.setImageResource(addIcon);
        }
        if(showSearch){
            Integer searchIcon = attributes.getResourceId(R.styleable.TitleSelectLayout_searchIcon, R.mipmap.ic_search);
            ivSearch.setImageResource(searchIcon);
        }
        int bgColor = attributes.getColor(R.styleable.TitleSelectLayout_backgroundColor, -1);
        showAdd(showAdd);
        showBack(showBack);
        showSearch(showSearch);
        setLeftText(leftText);
        setRightText(rightText);
        setBackText(backText, backTextSize);
        selectLeftOrRight(true);
        //非白色背景时,设置相应控件对应色
        if (bgColor != -1) {
            layout.setBackgroundColor(bgColor);
            tvBack.setTextColor(getResources().getColor(R.color.white));
            Drawable drawableLeft = getResources().getDrawable(
                    R.mipmap.back_white_icon);
            tvBack.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            tvLeft.setBackground(getResources().getDrawable(R.drawable.selector_titile_left_red));
            tvLeft.setTextColor(getResources().getColorStateList(R.color.selector_title_text_color_red));
            tvRight.setBackground(getResources().getDrawable(R.drawable.selector_titile_right_red));
            tvRight.setTextColor(getResources().getColorStateList(R.color.selector_title_text_color_red));
        }
    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    public void setCallBack(TitleSelectCallBack callBack) {
        this.callBack = callBack;
    }

    public void setLeftText(String text) {
        if (tvLeft != null && !TextUtils.isEmpty(text)) {
            tvLeft.setText(text);
        }
    }

    public void setRightText(String text) {
        if (tvRight != null && !TextUtils.isEmpty(text)) {
            tvRight.setText(text);
        }
    }

    public void setBackText(String text, int backTextSize) {
        if (tvBack != null && !TextUtils.isEmpty(text)) {
            tvBack.setText(text);
            tvBack.setTextSize(backTextSize);
            setVis(tvBack, true);
        }
    }

    public void showAdd(boolean show) {
        setVis(ivAdd, show);
    }

    public void showBack(boolean show) {
        setVis(tvBack, show);
    }

    public void showSearch(boolean show) {
        setVis(ivSearch, show);
    }

    private void setVis(View view, boolean show) {
        if (view != null) {
            view.setVisibility(show ? VISIBLE : GONE);
        }
    }

    /**
     * 选择切换
     *
     * @param left true-左边，false-右边
     */
    public void selectLeftOrRight(boolean left) {
        if (left) {
            tvLeft.setSelected(true);
            tvRight.setSelected(false);
        } else {
            tvLeft.setSelected(false);
            tvRight.setSelected(true);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_title_left_select) {
            if (callBack != null) {
                callBack.seletedPosition(SELECT_TYPE_LEFT);
            }
            selectLeftOrRight(true);
        } else if (i == R.id.tv_title_right_select) {
            if (callBack != null) {
                callBack.seletedPosition(SELECT_TYPE_RIGHT);
            }
            selectLeftOrRight(false);
        } else if (i == R.id.tv_title_back_text) {
            if (callBack != null) {
                callBack.clickType(CLICK_TYPE_BACK);
            }
            if (mContext != null && mContext instanceof Activity) {
                ((Activity) mContext).finish();
            }
        } else if (i == R.id.iv_title_add) {
            if (callBack != null) {
                callBack.clickType(CLICK_TYPE_ADD);
            }
        } else if (i == R.id.iv_title_search) {
            if (callBack != null) {
                callBack.clickType(CLICK_TYPE_SEARCH);
            }
        }
    }

    public interface TitleSelectCallBack {
        /**
         * 选择的位置
         *
         * @param position SELECT_TYPE_LEFT-左边 SELECT_TYPE_RIGHT-右边
         */
        void seletedPosition(int position);

        /**
         * 点击的控件类型
         *
         * @param position CLICK_TYPE_BACK-返回 CLICK_TYPE_ADD-添加 CLICK_TYPE_SEARCH-搜索
         */
        void clickType(int position);
    }
}
