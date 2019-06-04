package com.yhbc.base.common.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhbc.base.R;


/**
 * Created by chengwen on 2017/12/01.
 * 自定义选项卡
 */
public class SelectButton extends LinearLayout {

    private Context context;

    //Button的宽度
    private int width;
    //Button的高度
    private int height;


    //边框以及分割线宽度
    private int strokeWidth = dip2px(getContext(), 1);
    //圆角dp
    private int radius = dip2px(getContext(), 3);

    //背景颜色
    private int background_normal = Color.TRANSPARENT;
    //选中时候的背景颜色
    private int background_select = Color.RED;

    private int fengexian=Color.RED;

    //正常文字颜色
    private int textColor_normal = Color.RED;
    //选中文字的颜色
    private int textColor_select = Color.WHITE;
    //Button的文字大小
    private int textSize = dip2px(getContext(), 16);
    //SelectButton的按钮文本
    private String[] texts;

    //记录上一次选中的position
    private int selection = -1;

    public SelectButton(Context context) {
        this(context, null);
    }

    public SelectButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectButton);
        int n = a.getIndexCount();
        for(int i = 0; i < n; i++){
            int attr = a.getIndex(i);
            if (attr == R.styleable.SelectButton_width) {
                width = a.getDimensionPixelSize(attr, 0);

            } else if (attr == R.styleable.SelectButton_height) {
                height = a.getDimensionPixelSize(attr, 0);

            } else if (attr == R.styleable.SelectButton_strokeWidth) {
                strokeWidth = a.getDimensionPixelSize(attr, 1);

            } else if (attr == R.styleable.SelectButton_radius) {
                radius = a.getDimensionPixelSize(attr, dip2px(getContext(), 3));

            } else if (attr == R.styleable.SelectButton_background_normal) {
                background_normal = a.getColor(attr, Color.TRANSPARENT);

            } else if (attr == R.styleable.SelectButton_background_select) {
                background_select = a.getColor(attr, Color.RED);

            } else if (attr == R.styleable.SelectButton_fengexian){
                fengexian= a.getColor(attr, Color.RED);
            }
            else if (attr == R.styleable.SelectButton_textColor_normal) {
                textColor_normal = a.getColor(attr, Color.RED);

            } else if (attr == R.styleable.SelectButton_textColor_select) {
                textColor_select = a.getColor(attr, Color.WHITE);

            } else if (attr == R.styleable.SelectButton_textSize) {// 默认设置为16sp，TypeValue也可以把sp转化为px
                textSize = a.getDimensionPixelSize(attr, dip2px(getContext(), 16));

            } else if (attr == R.styleable.SelectButton_text) {
                String text = a.getString(attr);
                if (null != text) {
                    texts = text.split("~");
                }

            } else {
            }
        }
        a.recycle();
        //设置此布局方向
        this.setOrientation(HORIZONTAL);
        init();
    }

    /**
     * 重置文本控件
     *
     * @param texts texts
     */
    public void resetTexts(String[] texts) {
        if (null == texts || texts.length == 0) {
            return;
        }
        this.texts = texts;
        removeAllViews();
        init();
    }

    //初始化button的布局,绘制button
    private void init(){
        if (null==texts){
            return;
        }
        //根据传递过来的参数决定绘制的button的个数
        for (int i = 0; i < texts.length; i++) {
            final int position = i;

            final TextView tv = new TextView(getContext());
            LayoutParams layoutParams = new LayoutParams(width < 0 ? LayoutParams.WRAP_CONTENT : width, height);
            tv.setLayoutParams(layoutParams);

            addView(tv);
            if (width < 0) {
                tv.setPadding(textSize, 0, textSize, 0);
            }
            tv.setText(texts[i]);
            tv.setGravity(Gravity.CENTER);//内容居中
            tv.setTextSize(px2dip(context, textSize));
            tv.setTextColor(textColor_normal);

            //默认选中第一项
            if(i == 0){
                setViewBackground(tv, getLeftSelect());
                tv.setTextColor(textColor_select);
                selection = 0;
                addDivider();
            }else if (i == (texts.length - 1)){
//                addDivider();
                setViewBackground(tv, getRightNormal());
            }else{
                addDivider();
                setViewBackground(tv, getMiddleNormal());
            }

            //添加监听事件
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position == selection){
                        return;
                    }

                    resetView();
                    if (position == 0) {
                        setViewBackground(v, getLeftSelect());
                        ((TextView)v).setTextColor(textColor_select);
                    } else if (position == (texts.length - 1)) {
                        setViewBackground(v, getRightSelect());
                        ((TextView)v).setTextColor(textColor_select);
                    } else {
                        setViewBackground(v, getMiddleSelect());
                        ((TextView)v).setTextColor(textColor_select);
                    }
                    if (mOnItemButtonOnClickListener != null) {
                        mOnItemButtonOnClickListener.onItemClick(position);
                    }
                    selection = position;
                }
            });
        }
        setViewBackground(this, getStroke());
    }

    /** 将所有的Button复位 */
    private void resetView(){
        int count = this.getChildCount();
        for(int i=0; i<count; i++){
            //添加了分割线，需要判断view的类型
            View view = this.getChildAt(i);
            if(view instanceof TextView){
                TextView textView = (TextView) view;
                if(i == 0){//第一个肯定是TextView
                    setViewBackground(textView, getLeftNormal());
                    textView.setTextColor(textColor_normal);
                }else if (i == (count - 1)){//最后一个肯定是TextView
                    setViewBackground(textView, getRightNormal());
                    textView.setTextColor(textColor_normal);
                }else{
                    setViewBackground(textView, getMiddleNormal());
                    textView.setTextColor(textColor_normal);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void setViewBackground(final View view, final Drawable background) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(background);
        } else {
            ViewAccessorJB.setBackground(view, background);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    static class ViewAccessorJB {
        static void setBackground(final View view, final Drawable background) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) return;
            view.setBackground(background);
        }
    }

    private GradientDrawable stroke;
    //获取外层边框
    private GradientDrawable getStroke(){
        if(stroke == null){
            //内部透明、边框颜色
            stroke = new GradientDrawable();
            stroke.setColor(background_normal);
            stroke.setStroke(strokeWidth, background_select);
            stroke.setCornerRadius(radius);
        }
        return stroke;
    }

    /** 添加分割线 */
    private void addDivider(){
        View view = new View(getContext());
        LayoutParams lp = new LayoutParams(strokeWidth, height);
        view.setLayoutParams(lp);
        view.setBackgroundColor(fengexian);
        this.addView(view);
    }

    private GradientDrawable leftNormal;
    private GradientDrawable leftSelect;
    private GradientDrawable middleNormal;
    private GradientDrawable middleSelect;
    private GradientDrawable rightNormal;
    private GradientDrawable rightSelect;

    /** 左侧button未选中 */
    private GradientDrawable getLeftNormal(){
        if(leftNormal == null){
            leftNormal = new GradientDrawable();//创建drawable
            leftNormal.setColor(background_normal);//内部填充色
            leftNormal.setShape(GradientDrawable.RECTANGLE);
            //圆角设置以左上角为起点，顺时针,{x0,  y0, x1, y1, x2, y2, x3,y3}
            leftNormal.setCornerRadii(new float[]{radius, radius, 0, 0, 0, 0, radius, radius});
        }
        return leftNormal;
    }

    /** 左侧button选中 */
    private GradientDrawable getLeftSelect(){
        if(leftSelect == null){
            leftSelect = new GradientDrawable();//创建drawable
            leftSelect.setColor(background_select);//内部填充色
            leftSelect.setShape(GradientDrawable.RECTANGLE);
            leftSelect.setCornerRadii(new float[]{radius, radius, 0, 0, 0, 0, radius, radius});//圆角设置以左上角为起点，顺时针
        }
        return leftSelect;
    }

    /** 中间button未选中 */
    private GradientDrawable getMiddleNormal(){
        if(middleNormal == null){
            middleNormal = new GradientDrawable();//创建drawable
            middleNormal.setColor(background_normal);//内部填充色
            middleNormal.setShape(GradientDrawable.RECTANGLE);
        }
        return middleNormal;
    }

    /** 中间button未选中 */
    private GradientDrawable getMiddleSelect(){
        if(middleSelect == null){
            middleSelect = new GradientDrawable();//创建drawable
            middleSelect.setColor(background_select);//内部填充色
            middleSelect.setShape(GradientDrawable.RECTANGLE);
        }
        return middleSelect;
    }

    /** 右侧button未选中 */
    private GradientDrawable getRightNormal(){
        if(rightNormal == null){
            rightNormal = new GradientDrawable();//创建drawable
            rightNormal.setColor(background_normal);//内部填充色
            rightNormal.setShape(GradientDrawable.RECTANGLE);
            rightNormal.setCornerRadii(new float[]{0, 0, radius, radius, radius, radius, 0, 0});//圆角设置以左上角为起点，顺时针
        }
        return rightNormal;
    }

    /** 右侧button选中 */
    private GradientDrawable getRightSelect(){
        if(rightSelect == null){
            rightSelect = new GradientDrawable();//创建drawable
            rightSelect.setColor(background_select);//内部填充色
            rightSelect.setShape(GradientDrawable.RECTANGLE);
            rightSelect.setCornerRadii(new float[]{0, 0, radius, radius, radius, radius, 0, 0});//圆角设置以左上角为起点，顺时针
        }
        return rightSelect;
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    private OnItemButtonOnClickListener mOnItemButtonOnClickListener;

    /**
     * 点击事件回调
     */
    public interface OnItemButtonOnClickListener{
        void onItemClick(int position);
    }

    public void setOnItemButtonOnClickListener(OnItemButtonOnClickListener mOnItemButtonOnClickListener) {
        this.mOnItemButtonOnClickListener = mOnItemButtonOnClickListener;
    }
}
