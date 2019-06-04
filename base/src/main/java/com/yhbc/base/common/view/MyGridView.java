package com.yhbc.base.common.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * 创建时间： 2018/5/23
 * 作者：yangxd
 * 描述：不滚动gridview
 **/
public class MyGridView extends GridView {

	public MyGridView(Context context) {
		super(context);
	}
	
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
