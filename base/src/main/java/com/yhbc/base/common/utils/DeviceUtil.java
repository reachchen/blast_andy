package com.yhbc.base.common.utils;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @createTime： 2018/10/9
 * @author：yangxd
 * @desc：设备信息
 **/
public class DeviceUtil {

    /**
     * 返回序列号
     * <Android 2.3以上可以使用此方法>
     * @return
     */
    public static String getSerialNumber(){
        String serialNumber = android.os.Build.SERIAL;
        return TextUtils.isEmpty(serialNumber) ? "" : serialNumber;
    }

    /**
     * 获取设备型号
     * @return
     */
    public static String getModel() {
        String model = android.os.Build.MODEL;
        return TextUtils.isEmpty(model) ? "" : model;
    }

    /**
     * 获取手机系统版本号
     *
     * @return
     */
    public static String getSystemVersion() {
        String version = android.os.Build.VERSION.RELEASE;
        return TextUtils.isEmpty(version) ? "" : version;
    }

    /**
     * 获取手机制造商
     *
     * @return
     */
    public static String getManufacturer() {
        String carrier= android.os.Build.MANUFACTURER;
        return TextUtils.isEmpty(carrier) ? "" : carrier;
    }

    /**
     * 通过命令获取mac
     *
     * @return
     */
    public static String getSystemProperties(String key) {
        String value = "";
        Class<?> cls;
        try {
            cls = Class.forName("android.os.SystemProperties");
            Method hideMethod = cls.getMethod("get", String.class);
            Object object = cls.newInstance();
            value = (String) hideMethod.invoke(object, key);
        } catch (ClassNotFoundException e) {
            Log.e("ClassNotFoundException", "get error() ", e);
        } catch (NoSuchMethodException e) {
            Log.e("NoSuchMethodException", "get error() ", e);
        } catch (InstantiationException e) {
            Log.e("NoSuchMethodException", "get error() ", e);
        } catch (IllegalAccessException e) {
            Log.e("NoSuchMethodException", "get error() ", e);
        } catch (IllegalArgumentException e) {
            Log.e("NoSuchMethodException", "get error() ", e);
        } catch (InvocationTargetException e) {
            Log.e("NoSuchMethodException", "get error() ", e);
        }
        return value;
    }
}
