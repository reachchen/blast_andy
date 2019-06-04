package com.yhbc.base.common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yhbc.base.R;
import com.yhbc.base.common.utils.TimeUtils;
import com.yhbc.base.common.view.wheelview.DividerGridItemDecoration;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期选择
 * Created by xhj on 2018/3/15.
 */
public class DatePicikDialog extends Dialog implements View.OnClickListener {
    private Calendar calendar;
    private DatePickAdapter adapter;
    private TextView tvDate;
    //初始日期
    private Date selectData;

    public DatePicikDialog(Context context) {
        super(context, R.style.base_dialog_payback);
    }

    /**
     * 设置初始值
     *
     * @param time   time
     * @param format 格式
     */
    public void setDate(String time, String format) {
        Date date = TimeUtils.string2Unix(time, format);
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }
        if (null != date) {
            calendar.setTime(date);
        }

        selectData = calendar.getTime();
        ininDatas();


    }

    @SuppressWarnings("RedundantCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_pick);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gv_data);
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }

        tvDate = (TextView) findViewById(R.id.tv_title);
        adapter = new DatePickAdapter(null, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),7));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));
        adapter.setOnItemClickLitener(new DatePickAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                String data = adapter.getItem(position);
                if (!TextUtils.isEmpty(data)) {
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(data));
                    onCallback(calendar.getTime());
                    dismiss();
                }
            }

            @Override
            public void onItemLongClick(View view, int position)
            {

            }
        });
        recyclerView.setBackgroundResource(R.drawable.bg_gridview_date);
        selectData = calendar.getTime();
        ininDatas();

        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.iv_right).setOnClickListener(this);
        findViewById(R.id.iv_right_double).setOnClickListener(this);
        findViewById(R.id.iv_left_double).setOnClickListener(this);

//        new ViewSizeHelper(gridView) {
//
//            @Override
//            protected void onLayouted(int[] size) {
//                int colum = gridView.getNumColumns();
//                int divider = getContext().getResources().getDimensionPixelOffset(R.dimen.x2) * (colum + 1);
//                int space = size[1] - divider;
//                int yu = space % colum;
//                //重绘边界值
//                LayerDrawable layerDrawable = (LayerDrawable) gridView.getBackground();
//                layerDrawable.setLayerInset(1, 0, 0, yu, 0);
//                layerDrawable.invalidateSelf();
//            }
//        };
    }

    /**
     * 回调
     *
     * @param time time
     */
    protected void onCallback(Date time) {

    }


    /**
     * 初始化
     */
    private void ininDatas() {
        int days = calendar.getActualMaximum(Calendar.DATE);//总天数
        int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);//总周数
        if (null != tvDate) {
            tvDate.setText(TimeUtils.unix2String(calendar.getTime(), "yyyy-MM"));
        }
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置1号
        int num = calendar.get(Calendar.DAY_OF_WEEK);//1号星期几
        String[] datas = new String[7 * weeks];
        for (int i = 0; i < days; i++) {
            datas[num + i - 1] = (i + 1) + "";
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int selectNum = -1;
        //noinspection deprecation
        if (year == (selectData.getYear() + 1900) && month == selectData.getMonth()) {
            //noinspection deprecation
            selectNum = selectData.getDate();
        }
        if (null != adapter) {
            adapter.setSelectNum(selectNum);
            adapter.refresh(datas);
        }
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);//重置日历

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            calendar.add(Calendar.MONTH, -1);

        } else if (i == R.id.iv_right) {
            calendar.add(Calendar.MONTH, 1);

        } else if (i == R.id.iv_left_double) {
            calendar.add(Calendar.YEAR, -1);

        } else if (i == R.id.iv_right_double) {
            calendar.add(Calendar.YEAR, 1);

        }
        ininDatas();
    }
}
