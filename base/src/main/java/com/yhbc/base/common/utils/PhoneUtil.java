package com.yhbc.base.common.utils;

import android.text.TextUtils;

/**
 * @createTime： 2018/9/20
 * @author ：yangxd
 * @desc：手机号码类
 **/
public class PhoneUtil {

    /**
     * 打印卡号规则
     * 11位手机号 133****3333 六位以下显示末两位 **11  六位以上显示末四位 ****1234
     *
     * @param cardId 卡号
     */
    public static String getCardIdPrint(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return "";
        }
        int strLength = cardId.length();
        if (MobilUtils.isMobileNO(cardId)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                char c = cardId.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else if (strLength >= 3 && strLength <= 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                char c = cardId.charAt(i);
                if (i >= 0 && i <= strLength - 3) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else if (strLength >= 7) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                char c = cardId.charAt(i);
                if (i >= 0 && i <= strLength - 5) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else if (strLength <= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                char c = cardId.charAt(i);
                if (i == 0) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }

}
