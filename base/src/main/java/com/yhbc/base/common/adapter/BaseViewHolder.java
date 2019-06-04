package com.yhbc.base.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Method;

import butterknife.ButterKnife;


/**
 * M为这个itemView对应的model。
 * 使用RecyclerArrayAdapter就一定要用这个ViewHolder。
 * 这个ViewHolder将ItemView与Adapter解耦。
 * 推荐子类继承第二个构造函数。并将子类的构造函数设为一个ViewGroup parent。
 * 然后这个ViewHolder就完全独立。adapter在new的时候只需将parentView传进来。View的生成与管理由ViewHolder执行。
 * 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
 * <p>
 * 在一些特殊情况下，只能在setData里设置监听。
 *
 *
 */
abstract public class BaseViewHolder<M> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {

    protected BaseViewHolder<M> holder;

    private int mLayoutId;
    protected Context mContext;
    private View mConvertView;

    private SparseArray<View> mViews = new SparseArray<>();
    protected OnViewClickListener mOnViewClickListener;
    protected OnFocusChangeListener mOnFocusChangeListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        holder = this;
        mConvertView = itemView;
        mContext = mConvertView.getContext();
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        holder = this;
        mConvertView = itemView;
        mLayoutId = res;
        mContext = mConvertView.getContext();
        ButterKnife.bind(this,itemView);
    }

    public void setData(M item,int position) {

    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    protected Context getContext() {
        return mContext == null ? (mContext = itemView.getContext()) : mContext;
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 获取item布局
     *
     * @return
     */
    public View getItemView() {
        return mConvertView;
    }

    public BaseViewHolder setOnItemViewClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public BaseViewHolder setEable(int viewId, boolean isEnable) {
        EditText view = getView(viewId);
        view.setEnabled(isEnable);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    public BaseViewHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundColor(int color) {
        itemView.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackground(int viewId,Drawable drable) {
        View view = getView(viewId);
        view.setBackground(drable);
        return this;
    }

    public BaseViewHolder setMatch() {
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        itemView.setLayoutParams(params);

        return this;
    }

    public BaseViewHolder setBackgroundColorRes(int viewId, int colorRes) {
        View view = getView(viewId);
        view.setBackgroundResource(colorRes);
        return this;
    }

    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setImageDrawableRes(int viewId, int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableRes);
        return setImageDrawable(viewId, drawable);
    }

    public BaseViewHolder setImageUrl(int viewId, String imgUrl) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imgUrl).into(view);
        return this;
    }


    public BaseViewHolder setImageBitmap(int viewId, Bitmap imgBitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(imgBitmap);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public BaseViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnFocusChangeListener(int viewId){
        View view = getView(viewId);
        view.setOnFocusChangeListener(this);
        return this;
    }

    public BaseViewHolder setInputNone(int viewId) {
        final EditText editText = (EditText) getView(viewId);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
                editText.requestFocus();
                return false;
            }
        });
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {//TODO: handle exception
            }
            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {//TODO: handle exception
            }

            try {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    editText.setSelection(content.length());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getAdapterPosition());
        }
    }

    @Override
    public void onFocusChange(View v,boolean hasFocus) {
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChangeListener(v,this.getAdapterPosition(), hasFocus);
        }
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public interface OnFocusChangeListener {
        void onFocusChangeListener(View view, int position,boolean focuse);
    }

    public void setOnItemClickListener(BaseViewHolder.OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }


    public void setmOnFocusChangeListener(BaseViewHolder.OnFocusChangeListener listener) {
        this.mOnFocusChangeListener = listener;
    }

}