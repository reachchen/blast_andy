package com.yhbc.base.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yhbc.base.R;


/**
 * 作者：tianxiangmin on 2018/1/16 09:41
 * 邮箱：txm@blibao.com
 * 标题:
 */

public class LoadingDialogUtil {

//    private static Dialog loadingDialog;
//    private static TextView tipTextView;
//    private static RelativeLayout rlClose;

    public static Dialog createLoadingDialog(Context context, String tip) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_base, null);
        // 提示文字
        TextView tipTextView = (TextView) view.findViewById(R.id.tip);
        tipTextView.setText(tip);// 设置加载信息
        Dialog loadingDialog = new Dialog(context, R.style.login_dialog_base);
        loadingDialog.setContentView(view);
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
//        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//        /**
//         *将显示Dialog的方法封装在这里面
//         */
        Window window = loadingDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
//        window.setAttributes(lp);
//        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }

    public static Dialog createLoadingDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_base, null);
        // 提示文字
//        TextView tipTextView = (TextView) view.findViewById(R.id.tip);
//        tipTextView.setText(tip);// 设置加载信息
        Dialog loadingDialog = new Dialog(context, R.style.login_dialog_base);
        loadingDialog.setContentView(view);
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
//        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//        /**
//         *将显示Dialog的方法封装在这里面
//         */
        Window window = loadingDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
//        window.setAttributes(lp);
//        window.setWindowAnimations(R.style.PopWindowAnimStyle);
//        loadingDialog.show();
        return loadingDialog;
    }


    /**
     * 关闭进度圈
     *
     * @param mDialogUtils
     */
    public static void closeLoadingDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }

    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }


    /**
     * 设置加载信息
     */
    public static void setText(Dialog dialog, String msg) {
        if (dialog != null && dialog.isShowing()) {
            TextView tipTextView = (TextView) dialog.findViewById(R.id.tip);
            tipTextView.setText(msg);
        }
    }
}
