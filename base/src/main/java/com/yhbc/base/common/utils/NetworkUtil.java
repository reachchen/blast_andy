package com.yhbc.base.common.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @date 2018/10/09
 * @author yangxd
 * @desc 网络工具类
 */
public class NetworkUtil {

	/**
	 * 判断是否有网络
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			// 获取NetworkInfo对象
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					// 判断当前网络状态是否为连接状态
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取网络类型
	 *
	 * @param context
	 * @return
	 */
	public static String getNetworkState(Context context) {
		// 获取网络服务
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 为空则认为无网络
		if (null == connManager) {
			return "无网络";
		}
		// 获取网络类型，如果为空，返回无网络
		NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
		if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
			return "无网络";
		}
		// 判断是否为WIFI
		NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (null != wifiInfo) {
			NetworkInfo.State state = wifiInfo.getState();
			if (null != state) {
				if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
					return "WIFI";
				}
			}
		}
		// 若不是WIFI，则去判断是2G、3G、4G网
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		int networkType = telephonyManager.getNetworkType();
		switch (networkType) {
			// 2G网络
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_CDMA:
			case TelephonyManager.NETWORK_TYPE_EDGE:
			case TelephonyManager.NETWORK_TYPE_1xRTT:
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return "2G";
			// 3G网络
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_HSUPA:
			case TelephonyManager.NETWORK_TYPE_HSPA:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
			case TelephonyManager.NETWORK_TYPE_EHRPD:
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return "3G";
			// 4G网络
			case TelephonyManager.NETWORK_TYPE_LTE:
				return "4G";
			default:
				return "其他";
		}
	}

	/**
	 * 获取运营商名字
	 *
	 * @param context
	 * @return
	 */
	public static String getOperatorName(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// getSimOperatorName就可以直接获取到运营商的名字
		return telephonyManager.getSimOperatorName();
	}

	/**
	 * 获取局域网IP
	 *
	 * @return
	 */
	public static String getLanIP() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				Enumeration<InetAddress> enIp = ni.getInetAddresses();
				while (enIp.hasMoreElements()) {
					InetAddress inetAddress = enIp.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& (inetAddress instanceof Inet4Address)) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "";
	}

}
