package com.yhbc.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by dzy on 2018/7/26.
 */

public class CustomDialog extends Dialog {

    private CustomDialogListener mListener;
    private int layoutId;
    private View mContentView;
    private boolean isFocus;

    private CustomDialog(@NonNull Context context, int themeResId, @NonNull Builder builder) {
        super(context, themeResId);

        this.mContentView = builder.contentView;
        this.isFocus = builder.isFocus;
        this.layoutId = builder.layoutId;
        this.mListener = builder.listener;
        initLayout();
    }

    private void initLayout() {
        if (mListener != null)
            mListener.initView(mContentView);
        setCanceledOnTouchOutside(isFocus);
        if (mContentView != null) {
            setContentView(mContentView);
        } else {
            setContentView(layoutId);
        }
    }


    /**
     * 构造者模式
     *
     * @param context
     * @return
     */
    public static Builder builder(Context context) {
        return new Builder(context);
    }

    public static class Builder {

        private Context context;
        private View contentView;
        private int themeResId = 0;
        private int layoutId = 0;
        private boolean isFocus = true;
        private CustomDialogListener listener;

        private Builder(Context context) {
            this.context = context;
        }

        /**
         * 传自己需要的dialog样式
         *
         * @param themeResId
         * @return
         */
        public Builder setTheme(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        /**
         * 传自己需要的dialog布局
         *
         * @param layoutId
         * @return
         */
        public Builder setContentView(int layoutId) {
            this.layoutId = layoutId;
            this.contentView = inflateView((ContextThemeWrapper) context, layoutId);
            return this;
        }

        /**
         * 传自己需要的dialog布局
         *
         * @param contentView
         * @return
         */
        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        /**
         * 设置点击dialog外部
         *
         * @param isFocus
         * @return
         */
        public Builder isFocus(boolean isFocus) {
            this.isFocus = isFocus;
            return this;
        }

        /**
         * 把布局返回,供外面修改
         *
         * @param listener
         * @return
         */
        public Builder getCustomView(CustomDialogListener listener) {
            this.listener = listener;
            return this;
        }

        public CustomDialog build() {
            if (contentView == null)
                throw new IllegalStateException("ContentView is required");
            if (themeResId == 0)
                throw new IllegalStateException("ThemeStyle is required");

            return new CustomDialog(context, themeResId, this);
        }

        private View inflateView(ContextThemeWrapper context, int layoutId) {
            return LayoutInflater.from(context)
                    .inflate(layoutId, null);
        }
    }

    public interface CustomDialogListener {
        void initView(View contentView);
    }
}