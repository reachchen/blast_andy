package com.yhbc.base.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检查网络状态
 */
public class CheckHttpUtil {

	private static CheckHttpUtil checkHttpUtil;
	public static CheckHttpUtil getInstanse(){
		if (checkHttpUtil==null) {
			checkHttpUtil = new CheckHttpUtil();
		}
		return checkHttpUtil;
	}

	public boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED&& info[i].isAvailable()) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
