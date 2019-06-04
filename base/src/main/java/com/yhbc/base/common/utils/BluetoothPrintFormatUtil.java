package com.yhbc.base.common.utils;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**蓝牙打印,排版打印格式
 */
public class BluetoothPrintFormatUtil {

	private static BluetoothPrintFormatUtil bluetoothPrintFormatUtil;

	public static  BluetoothPrintFormatUtil getInstanse(){
		if (bluetoothPrintFormatUtil==null) {
			bluetoothPrintFormatUtil = new BluetoothPrintFormatUtil();
		}
		return bluetoothPrintFormatUtil;
	}

	/**
	 * 打印纸一行最大的字节
	 */
	private  final int LINE_BYTE_SIZE = 32;
	/**
	 * 分隔符
	 */
	private  final String SEPARATOR = "$";

	private  StringBuffer sb = new StringBuffer();

	/**
	 * 排版居中标题
	 * @param title
	 * @return
	 */
	public  String printTitle(String title) {
		sb.delete(0, sb.length());
		for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 2; i++) {
			sb.append(" ");
		}
		sb.append(title);
		return sb.toString();
	}

	public  String printTitle1(String title) {
		sb.delete(0, sb.length());
		for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 5; i++) {
			sb.append(" ");
		}
		sb.append(title);
		return sb.toString();
	}

	public  String printTitle33(String title) {
		sb.delete(0, sb.length());
		for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 6; i++) {
			sb.append(" ");
		}
		sb.append(title);
		return sb.toString();
	}

	public  String printTitle2(String title) {
		sb.delete(0, sb.length());
		for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 7; i++) {
			sb.append(" ");
		}
		sb.append(title);
		return sb.toString();
	}


	public  String printTitle44(String title) {
		sb.delete(0, sb.length());
		for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 4; i++) {
			sb.append(" ");
		}
		sb.append(title);
		return sb.toString();
	}
	/**
	 * 排版居中内容(以':'对齐)
	 * 
	 * 例：姓名：李白
	 * 	       病区：5A病区
	 * 	  住院号：11111
	 * 
	 * @param
	 * @return
	 */
	public  String printMiddleMsg(LinkedHashMap<String, String> middleMsgMap) {
		sb.delete(0, sb.length());
		String separated = ":";
		int leftLength = (LINE_BYTE_SIZE - getBytesLength(separated)) / 2;
		for (Entry<String, String> middleEntry : middleMsgMap.entrySet()) {
			for (int i = 0; i < (leftLength - getBytesLength(middleEntry.getKey())); i++) {
				sb.append(" ");
			}
			sb.append(middleEntry.getKey() + "：" + middleEntry.getValue());
		}
		return sb.toString();
	}

	/**
	 * 排版左右对称内容(以':'对齐)
	 * 
	 * 例：姓名：李白	住院号：111111
	 * 	       病区：5A病区	     科室：五官科
	 * 	       体重：130kg
	 * 
	 * 
	 * @param leftMsgMap	左边部分要打印内容	左边内容大小可大于右边内容大小  反之右边过大时会忽略内容
	 * @param rightMsgMap	右边部分要打印内容
	 * @return
	 */
	public  String printSymmetryMSG(LinkedHashMap<String, String> leftMsgMap, LinkedHashMap<String, String> rightMsgMap) {
		sb.delete(0, sb.length());
		int leftPrefixLength = getMaxLength(leftMsgMap.keySet().toArray());
		int rightValueLength = getMaxLength(rightMsgMap.values().toArray());
		Object rightMsgKeys[] = rightMsgMap.keySet().toArray();
		int position = 0;
		for (Entry<String, String> leftEntry : leftMsgMap.entrySet()) {
			String leftMsgPrefix = leftEntry.getKey();
			String leftMsgValue = leftEntry.getValue();
			for (int leftI = 0; leftI < (leftPrefixLength - getBytesLength(leftMsgPrefix)); leftI++) {
				sb.append(" ");
			}
			String leftMsg = leftMsgPrefix + "：" + leftMsgValue;
			sb.append(leftMsg);

			if (position > rightMsgKeys.length - 1)
				continue;
			int leftLength = leftPrefixLength + getBytesLength("：" + leftMsgValue);
			String rightMsgPrefix = rightMsgKeys[position] + "：";
			int rightLength = getBytesLength(rightMsgPrefix) + rightValueLength;
			String rightMsgValue = rightMsgMap.get(rightMsgKeys[position]);

			for (int middle = 0; middle < (LINE_BYTE_SIZE - leftLength - rightLength); middle++) {
				sb.append(" ");
			}
			sb.append(rightMsgPrefix + rightMsgValue);
			position++;
		}
		return sb.toString();
	}

	/**
	 * 打印订餐单 (左中右对称)
	 * 
	 * 例:   菜名                           数量            单价
	 *     早餐：
	 *       豆沙包                         1      3.0
	 *       蛋                                   1      1.5
	 *     午餐：
	 *       包子                              3      11.0
	 *     晚餐：
	 *     	 土豆                                2      4.1
	 * @param menuMsgMap 	Key为餐次 Value为 菜谱字符串数组    格式为：豆沙包$数量$单价
	 * @return
	 */
	public  String printMenuMSG(LinkedHashMap<String, LinkedList<String>> menuMsgMap) {
		sb.delete(0, sb.length());

		String menus[] = null;
		List<String> menuNames = new ArrayList<String>();
		List<String> menuPrices = new ArrayList<String>();
		for (List<String> strList : menuMsgMap.values()) {
			for (String str : strList) {
				if (str.contains(SEPARATOR)) {
					menus = str.split("[" + SEPARATOR + "]");
					if (menus != null && menus.length != 0) {
						menuNames.add(menus[0]);
						menuPrices.add(menus[2]);
					}
				}
			}
		}

		String menuNameTxt = "菜名";
		String numTxt = "数量";
		String priceTxt = "单价\n";

		int leftPrefixLength = getMaxLength(menuNames.toArray());
		int rightPrefixLength = getMaxLength(menuPrices.toArray());
		if (rightPrefixLength < getBytesLength(priceTxt))
			rightPrefixLength = getBytesLength(priceTxt);

		int leftMiddleNameLength = (leftPrefixLength - getBytesLength(menuNameTxt)) / 2;
		for (int i = 0; i < leftMiddleNameLength; i++) {
			sb.append(" ");
		}
		sb.append(menuNameTxt);
		int middleNumTxtLength = (LINE_BYTE_SIZE - leftPrefixLength - rightPrefixLength - getBytesLength(numTxt)) / 2;
		for (int i = 0; i < middleNumTxtLength + leftMiddleNameLength; i++) {
			sb.append(" ");
		}
		sb.append(numTxt);
		int middlePriceTxtLength = (rightPrefixLength - getBytesLength(priceTxt)) / 2;
		for (int i = 0; i < middleNumTxtLength + middlePriceTxtLength; i++) {
			sb.append(" ");
		}
		sb.append(priceTxt);

		for (Entry<String, LinkedList<String>> entry : menuMsgMap.entrySet()) {
			if (!"".equals(entry.getKey()))
				sb.append(entry.getKey() + "\n");
			for (String menu : entry.getValue()) {
				if (menu.contains(SEPARATOR)) {
					menus = menu.split("[" + SEPARATOR + "]");
					if (menus != null && menus.length != 0) {
						sb.append(menus[0]);
						for (int i = 0; i < (leftPrefixLength - getBytesLength(menus[0]) + middleNumTxtLength + getBytesLength(numTxt) / 2 - 1); i++) {
							sb.append(" ");
						}
						sb.append(menus[1]);
						for (int i = 0; i < (middleNumTxtLength + getBytesLength(numTxt) / 2 + 1 - getBytesLength(menus[1]) + middlePriceTxtLength); i++) {
							sb.append(" ");
						}
						sb.append(menus[2] + "\n");
					}
				} else { // 不包含分隔符 直接打印
					for (int i = 0; i < LINE_BYTE_SIZE / getBytesLength(menu) - getBytesLength("\n"); i++) {
						sb.append(menu);
					}
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 获取最大长度
	 * @param msgs
	 * @return
	 */
	private  int getMaxLength(Object[] msgs) {
		int max = 0;
		int tmp;
		for (Object oo : msgs) {
			tmp = getBytesLength(oo.toString());
			if (tmp > max) {
				max = tmp;
			}
		}
		return max;
	}

	/**
	 * 获取数据长度
	 * @param msg
	 * @return
	 */
	@SuppressLint("NewApi")
	private  int getBytesLength(String msg) {
		return msg.getBytes(Charset.forName("GB2312")).length;
	}

	/**
	 * 字符串长度补齐,方便打印时对齐,美化打印页面,不过中文计算好像有点问题
	 *
	 * @param strs 源字符
	 * @param len  指定字符长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String Fix_String_Lenth(int type, String strs, int len) {
		StringBuffer buffer = new StringBuffer();
		String strtemp = strs;
		buffer.append(strtemp);
		int len1 = 0;
		try {
			len1 = strs.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		switch (type) {
			case 0:
				len=4;
				while (len1 < len) {
					buffer.append(" ");;
					len1++;
				}
				strtemp = buffer.toString();
				break;

			case 1:
				if (len==25) {
					if (len1 < len) {
						while (++len1 < len) {
							buffer.append(" ");
						}
					} else {
						while (++len1 < len + 32) {
							buffer.append(" ");
						}
					}
				} else if (len==32) {
					if (len1 < len) {
						while (++len1 < len) {
							buffer.append(" ");
						}
					} else {
						while (++len1 < len + 48) {
							buffer.append(" ");
						}
					}
				}
				strtemp = buffer.toString();
				break;
			case 2://网络打印后厨补齐
				if (len==12) {
					if (len1 < len) {
						while (len1 < len) {
							buffer.append(" ");
							len1++;
						}
					} else {
						while (len1 < len + 16) {
							buffer.append(" ");
							len1++;
						}
					}
				}else if   (len==18){
					if (len1 < len) {
						while (len1 < len) {
							buffer.append(" ");
							len1++;
						}
					} else {
						while (len1 < len + 24) {
							buffer.append(" ");
							len1++;
						}
					}
				}
				strtemp = buffer.toString();
				break;
		}
		return strtemp;
	}
}
