package com.yhbc.base.common.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Alan on 2016/12/16.
 */
public class TakeOutOrderUtils {

    public TakeOutOrderUtils() {
        super();
    }

    public static TakeOutOrderUtils takeOutOrderUtils;

    public static TakeOutOrderUtils getInstanse() {
        if (takeOutOrderUtils == null) {
            takeOutOrderUtils = new TakeOutOrderUtils();
        }
        return takeOutOrderUtils;
    }

    /**
     * 动态设置ListView的高度
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
