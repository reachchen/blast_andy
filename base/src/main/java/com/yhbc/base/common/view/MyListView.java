package com.yhbc.base.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * 创建时间： 2018/5/23
 * 作者：yangxd
 * 描述：不滚动listview
 **/
public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
