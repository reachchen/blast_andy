package com.yhbc.base.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by zhh on 2018/1/17.
 * 获取设备信息：手机的厂商、型号、Android系统版本号、IMEI、当前系统语言等工具类
 */

public class EquipmentSystemUtil {

    private static final String[] DEVICE_BRAND = {"S1806", "Inxpos", "R30sdk"};

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 串口序列号
     *
     * @return
     */
    public static String getSERIAL() {
        return Build.SERIAL;
    }

    /**
     * 获取设备驱动名称
     *
     * @return
     */
    public static String getDEVICE() {
        return Build.DEVICE;
    }


    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }

    public static int getEquipmentModel() {
        String equipment=getSystemModel();
        int index = -1;
        for (int i = 0; i < DEVICE_BRAND.length; i++) {
            if (equipment.equalsIgnoreCase(DEVICE_BRAND[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    public final static List<String> resultEquipment(int type) {
        List<String> list = new ArrayList<>();
        switch (type) {
            case 0:
                list.add("ttyS1");
                list.add("ttyS3");
                break;
            case 1:
                list.add("ttyS2");
                list.add("ttyS4");
                break;
            case 2:
                list.add("ttyS1");
                list.add("ttyS0");
                break;
            default:
                return null;
        }
        return list;
    }

}
