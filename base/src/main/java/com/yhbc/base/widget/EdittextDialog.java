package com.yhbc.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/7/13.
 */

public class EdittextDialog extends Dialog {
    public EdittextDialog(@NonNull Context context) {
        super(context);
    }

    public EdittextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected EdittextDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setContentView(@NonNull View view) {
        if (Build.VERSION.SDK_INT < 19) {
            super.setContentView(view);
            return;
        }
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.setContentView(view);
    }
}
