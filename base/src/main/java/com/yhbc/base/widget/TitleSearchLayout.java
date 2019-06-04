package com.yhbc.base.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.SoftKeyboardUtils;
import com.yhbc.base.common.utils.ToastUtil;

/**
 * @author SXQ
 * Date 2018/11/9
 * Explain 通用标题栏
 */
public class TitleSearchLayout extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    public RelativeLayout layout;
    public ImageView ivBack;
    public EditText etSearch;

    public TitleSearchLayout(Context context) {
        this(context, null);
    }

    public TitleSearchLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.layout);
    }

    public TitleSearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_search, this, true);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleSearchLayout, defStyleAttr, 0);
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
        layout = view.findViewById(R.id.title_search_layout);
        ivBack = view.findViewById(R.id.img_title_back);
        etSearch = view.findViewById(R.id.title_et_search);


        //构建返回
        boolean showBack = attributes.getBoolean(R.styleable.TitleSearchLayout_backSearch, true);
        if (showBack) {
            ivBack.setVisibility(VISIBLE);
        }
        Integer backIcon = attributes.getResourceId(R.styleable.TitleSearchLayout_backImg, R.mipmap.back_icon);
        ivBack.setImageResource(backIcon);
        int bgColor = attributes.getColor(R.styleable.TitleSearchLayout_bgSearchColor, getResources().getColor(R.color.white));
        layout.setBackgroundColor(bgColor);
        //构建返回
        boolean showEt= attributes.getBoolean(R.styleable.TitleSearchLayout_etSearchShow, true);
        if (showEt) {
            etSearch.setVisibility(VISIBLE);
        }
        //构建返回
        String hintText = attributes.getString(R.styleable.TitleSearchLayout_hintText);
        etSearch.setHint(""+hintText);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    SoftKeyboardUtils.hideSoftKeyboard(getContext());
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 设置点击事件
     */
    private void setListener() {
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
        if (i == R.id.img_title_back) {
            if (mContext != null && mContext instanceof Activity) {
                ((Activity) mContext).finish();
            }
        }
    }

}
