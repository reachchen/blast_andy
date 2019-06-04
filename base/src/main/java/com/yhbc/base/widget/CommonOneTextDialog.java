package com.yhbc.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhbc.base.R;

/**
 * 描 述：只有一个textview的确认取消弹窗
 * 作 者：wangwei
 * 时 间：2018/6/27
 */
public class CommonOneTextDialog extends Dialog {
    private TextView tv_name, tv_cancel, tv_confirm, tv_describtion;
    private RelativeLayout rl_close;
    private TvCancelOnClickListener mTvCancelOnClickListener;
    private TvConfirmOnClickListener mTvConfirmOnClickListener;
    private TvCloseOnClickListener mTvCloseOnClickListener;

    public CommonOneTextDialog(@NonNull Context context) {
        this(context, R.style.common_one_text_dialog);
    }

    public CommonOneTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.layout_common_one_text_dialog);
        tv_name = findViewById(R.id.tv_name);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_describtion = findViewById(R.id.tv_describtion);
        rl_close = findViewById(R.id.rl_close);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvCancelOnClickListener != null)
                    mTvCancelOnClickListener.onCancelClick();
                dismiss();
            }
        });
        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvCloseOnClickListener != null)
                    mTvCloseOnClickListener.onCloseClick();
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvConfirmOnClickListener != null)
                    mTvConfirmOnClickListener.onConfirmClick();
            }
        });
    }
//取消确定可能换位置
    public TextView getTv_cancel() {
        return tv_cancel;
    }

    public void setTv_cancel(TextView tv_cancel) {
        this.tv_cancel = tv_cancel;
    }

    public TextView getTv_confirm() {
        return tv_confirm;
    }

    public void setTv_confirm(TextView tv_confirm) {
        this.tv_confirm = tv_confirm;
    }

    public void setName(String str) {
        if (!TextUtils.isEmpty(str))
            tv_name.setText(str);
    }

    public void setDescribtion(String str) {
        if (!TextUtils.isEmpty(str))
            tv_describtion.setText(str);
    }

    public TextView getDescribtionTextView() {
        return tv_describtion;
    }

    public interface TvCancelOnClickListener {
        void onCancelClick();

    }

    public interface TvConfirmOnClickListener {
        void onConfirmClick();

    }

    public interface TvCloseOnClickListener {
        void onCloseClick();

    }

    public void setmTvCloseOnClickListener(TvCloseOnClickListener mTvCloseOnClickListener) {
        this.mTvCloseOnClickListener = mTvCloseOnClickListener;
    }

    public void setmTvCancelOnClickListener(TvCancelOnClickListener mTvCancelOnClickListener) {
        this.mTvCancelOnClickListener = mTvCancelOnClickListener;
    }

    public void setmTvConfirmOnClickListener(TvConfirmOnClickListener mTvConfirmOnClickListener) {
        this.mTvConfirmOnClickListener = mTvConfirmOnClickListener;
    }

    public void setDescribtion(CharSequence str) {
        if (!TextUtils.isEmpty(str))
            tv_describtion.setText(str);
    }
}
