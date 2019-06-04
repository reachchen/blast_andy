package com.yhbc.base.framework.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by wb on 2016/12/30.
 */

public abstract class BaseFragment extends Fragment {
    public final String TAG = getClass().getName();
    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Fragment fragment = getParentFragment();
        if (fragment != null) {
            fragment.startActivityForResult(intent, requestCode);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getChildFragmentManager().getFragments();
        if (fragmentList != null && fragmentList.size()>0) {
            for (Fragment fragment : fragmentList) {
                if(data != null){
                    if(isIntentAvailable(getActivity(),data)){
                        fragment.onActivityResult(requestCode, resultCode, data);
                    }
                }
            }
        }
    }


    /**
     * 检测 响应某个意图的Activity 是否存在
     * @param context
     * @param intent
     * @return
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        return list.size() > 0;
    }
}
