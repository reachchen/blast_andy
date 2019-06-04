package com.yhbc.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

/**
 * DialogFragment的基类,去除系统自带的样式
 *
 * @author xuhaijiang on 2018/8/2.
 */
public class BaseDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (setDialogStyle() == 0) {
            //默认为背景半透明灰
            return new Dialog(getActivity(), R.style.base_dialog_payback);
        } else {
            return new Dialog(getActivity(), setDialogStyle());
        }
    }

    /**
     * 设置弹出框样式
     *
     * @return
     */
    protected int setDialogStyle() {
        return 0;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //防止重复添加导致IllegalStateException:Fragment already added
        if (!isAdded()) {
            super.show(manager, tag);
        }
    }

}
