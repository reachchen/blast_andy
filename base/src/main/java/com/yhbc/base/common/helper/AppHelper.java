package com.yhbc.base.common.helper;

import com.yhbc.base.common.utils.SpUtil;

/**
 * @date ： 2018/7/16
 * @author ：yangxd
 * @desc：存储app相关信息
 **/
public class AppHelper {

    /**
     * set 版本号
     *
     * @param code
     */
    public static void setVersionCode(int code) {
        SpUtil.putInt("version_code", code);
    }

    /**
     * 获取版本号
     *
     */
    public static int getVersionCode() {
        return SpUtil.getInt("version_code");
    }

    /**
     * 设置是否第一次使用
     *
     * @param isFrist
     */
    public static void setNotFristUse(boolean isFrist) {
        SpUtil.putBoolean("frist_use", isFrist);
    }

    /**
     * 获取是否第一次使用
     *
     * @return
     */
    public static boolean getNotFristUse() {
        return SpUtil.getBoolean("frist_use");
    }



    /**
     * 设置是否正在更新中
     *
     * @param isUpdating
     */
    public static void setIsUpdating(boolean isUpdating) {
        SpUtil.putBoolean("is_updating", isUpdating);
    }


    /**
     * 获取是否正在更新中
     *
     * @return
     */
    public static boolean getIsUpdating() {
        return SpUtil.getBoolean("is_updating");
    }
}
