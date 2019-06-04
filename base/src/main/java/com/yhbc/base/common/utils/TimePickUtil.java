package com.yhbc.base.common.utils;

import android.content.Context;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Calendar;

/**
 * @author SXQ
 * Date 2018/11/28
 * Explain
 */
public class TimePickUtil {

    public static TimePickerView getTimePicker(Context context, LinearLayout view, OnTimeSelectListener listener) {
        Calendar endCalender = Calendar.getInstance();
        TimePickerView timePicker = new TimePickerBuilder(context, listener)
                .setTitleText("选择日期")
                .setType(new boolean[]{true, true, true, false, false, false})
                .setTitleColor(0xFF333333)
                .setCancelColor(0xFF333333)
                .setSubCalSize(14)
                .setTitleSize(12)
                .setDate(endCalender)
                .setLineSpacingMultiplier(2f)
                .setDecorView(view)
                .build();
        return timePicker;
    }

    public static TimePickerView getTimePicker(Context context, LinearLayout view, OnTimeSelectListener listener
            , boolean[] type) {
        Calendar endCalender = Calendar.getInstance();
        TimePickerView timePicker = new TimePickerBuilder(context, listener)
                .setTitleText("选择日期")
                .setType(type)
                .setTitleColor(0xFF333333)
                .setCancelColor(0xFF333333)
                .setSubCalSize(14)
                .setTitleSize(12)
                .setDate(endCalender)
                .setLineSpacingMultiplier(2f)
                .setDecorView(view)
                .build();
        return timePicker;
    }

}
