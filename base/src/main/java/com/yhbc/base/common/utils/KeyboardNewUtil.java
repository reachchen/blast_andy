package com.yhbc.base.common.utils;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.yhbc.base.R;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 作者：tianxiangmin on 2017/12/5 16:05
 * 邮箱：txm@blibao.com
 * 标题:
 */

public class KeyboardNewUtil {
    private Context act;
    private KeyboardView keyboardView;
    private Keyboard k1;// 字母键盘
    private Keyboard k2;
    private boolean isnun = true;// 是否数据键盘
    private boolean isupper = false;// 是否大写
    private Instrumentation m_Instrumentation;
    private EditText ed;
    private static KeyboardNewUtil keyboardUtilUser;
    public static KeyboardNewUtil getInstanse(){
        if (null==keyboardUtilUser){
            keyboardUtilUser=new KeyboardNewUtil();
        }
        return  keyboardUtilUser;
    }

    private KeyboardNewUtil(){}

    public KeyboardNewUtil initKeyboard(Context act, EditText edit, View view, boolean full) {
        this.act = act;
        this.ed = edit;
        if (full) {
            k1 = new Keyboard(act, R.xml.qwerty_full);
        } else {
            k1 = new Keyboard(act, R.xml.symbols_num_abc);
        }
        keyboardView = (KeyboardView) view.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
        return keyboardUtilUser;
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            try {
                Editable editable = ed.getText();
                int start = ed.getSelectionStart();
                if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            if (m_Instrumentation == null) {
                                m_Instrumentation = new Instrumentation();
                            }
                            m_Instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
                        }
                    }.start();
                    MyWindowManager.getInstanse().removeInputWindow();
                } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                    if (editable != null && editable.length() > 0) {
                        if (start > 0) {
                            editable.delete(start - 1, start);
                        }
                    }
                } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
                    changeKey();
                    keyboardView.setKeyboard(k1);

                } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {// 数字键盘切换
//                ed.setText("");
                    if (isnun) {
                        isnun = false;
                        k1 = new Keyboard(act, R.xml.qwerty);
                        keyboardView.setKeyboard(k1);
                        keyboardView.setEnabled(true);
                        keyboardView.setPreviewEnabled(true);
                        keyboardView.setOnKeyboardActionListener(listener);

                    } else {
                        isnun = true;
                        k2 = new Keyboard(act, R.xml.symbols_num_abc);
                        keyboardView.setKeyboard(k2);
                        keyboardView.setEnabled(true);
                        keyboardView.setPreviewEnabled(true);
                        keyboardView.setOnKeyboardActionListener(listener);
                    }
                } else if (primaryCode == 803) { //100
                    String s = ed.getText().toString().trim() + "00";
                    if (s.length() > 0 && s.length() <= 7) {
                        ed.setText(s);
                        if (ed.getText().length() > 0) {
                            ed.setSelection(ed.getText().length());
                        }
                    }
                    return;
                } else if (primaryCode == 900) { // _
//                String s = ed.getText().toString().trim() + "_";
//                ed.setText(s);
//                if (ed.getText().length() > 0) {
//                    ed.setSelection(ed.getText().length());
//                }
                    editable.insert(start, "_");
                    return;
                } else if (primaryCode == 57419) { // go left
                    if (start > 0) {
                        ed.setSelection(start - 1);
                    }
                } else if (primaryCode == 57421) { // go right
                    if (start < ed.length()) {
                        ed.setSelection(start + 1);
                    }
                } else {
                    editable.insert(start, Character.toString((char) primaryCode));
                }
            }catch (Exception NullPointerException){
                NullPointerException.printStackTrace();
            }
        }
    };

    /**
     * 键盘大小写切换
     */
    private void changeKey() {
        List<Keyboard.Key> keylist = k1.getKeys();
        if (isupper) {//大写切换小写
            isupper = false;
            for (Keyboard.Key key : keylist) {
                if (key.label != null && isword(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                }
            }
        } else {//小写切换大写
            isupper = true;
            for (Keyboard.Key key : keylist) {
                if (key.label != null && isword(key.label.toString())) {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
    }

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }

    public void etInputNull(EditText et, Context context) {
        if (Build.VERSION.SDK_INT <= 10) {//4.0以下 danielinbiti
            et.setInputType(InputType.TYPE_NULL);
        } else {
            ((Activity)context).getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(et, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setEd(EditText ed) {
        this.ed = ed;
        if (null == ed) {
            act = null;
            MyWindowManager.getInstanse().removeInputWindow();
        }
    }
}
