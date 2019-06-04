package com.yhbc.base.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by chengwen on 2017-12-12.
 */
public class VersionUtils {

    /** 获取版本名 */
    public static String getVerName(Context context){

        String verName = "";
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);

            verName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 获取版本号
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            verCode = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verCode;
    }


}
