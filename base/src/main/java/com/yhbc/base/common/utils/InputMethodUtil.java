package com.yhbc.base.common.utils;

import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class InputMethodUtil {

	/** 弹出输入法 */
	public static void showKeyboard(EditText e) {

		try {
			final EditText editText = e;
			editText.setFocusableInTouchMode(true);
			editText.requestFocus();

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					InputMethodManager imm = (InputMethodManager) editText
							.getContext().getSystemService(
									Context.INPUT_METHOD_SERVICE);

					imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
				}

			}, 300);
		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	/** 隐藏输入法 */
	public static void hideKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

		}
	}

	/**
	 * 隐藏系统键盘
	 *
	 * @param editText
	 */
	public static void hideSystemSofeKeyboard(Context context, EditText editText) {
		int sdkInt = Build.VERSION.SDK_INT;
		if (sdkInt >= 11) {
			try {
				Class<EditText> cls = EditText.class;
				Method setShowSoftInputOnFocus;
				setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
				setShowSoftInputOnFocus.setAccessible(true);
				setShowSoftInputOnFocus.invoke(editText, false);

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			editText.setInputType(InputType.TYPE_NULL);
		}
		// 如果软键盘已经显示，则隐藏
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

}
