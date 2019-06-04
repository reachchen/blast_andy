package com.yhbc.base.common.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yhbc.base.R;

/**
 * 数字键盘
 * Created by xhj on 2018/3/12.
 */
public class NumKeyboardView extends LinearLayout implements View.OnClickListener {

    private KeyboardListener mListener;

    public NumKeyboardView(Context context) {
        this(context, null);
    }

    public NumKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.include_num_keyboard_base, this, true);
        setBackgroundResource(R.drawable.bg_keyboard);
        setOrientation(VERTICAL);
        int[] resIds = new int[]{R.id.btn_key_1_base, R.id.btn_key_2_base, R.id.btn_key_3_base,
                R.id.btn_key_4_base, R.id.btn_key_5_base, R.id.btn_key_6_base,
                R.id.btn_key_7_base, R.id.btn_key_8_base, R.id.btn_key_9_base,
                R.id.btn_key_0_base, R.id.btn_key_point_base, R.id.btn_key_delete_base};
        for (int res : resIds) {
            findViewById(res).setOnClickListener(this);
        }
    }

//    private Unbinder unbinder;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        unbinder = ButterKnife.bind(this);

    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        unbinder.unbind();

    }

    /**
     * 设置监听
     *
     * @param listener {@link #mListener}
     */
    public void setListener(KeyboardListener listener) {
        mListener = listener;
    }

//    @OnClick({R.id.btn_key_1_base, R.id.btn_key_2_base, R.id.btn_key_3_base,
//            R.id.btn_key_4_base, R.id.btn_key_5_base, R.id.btn_key_6_base,
//            R.id.btn_key_7_base, R.id.btn_key_8_base, R.id.btn_key_9_base,
//            R.id.btn_key_0_base, R.id.btn_key_point_base, R.id.btn_key_delete_base})
    @Override
    public void onClick(View v) {
//        if (null == mListener) {
//            return;
//        }
        View rootview = ((Activity) getContext()).getWindow().getDecorView();
        //焦点控件
        View foucsView = rootview.findFocus();
        if (foucsView instanceof EditText) {
            EditText et = (EditText) foucsView;
            Editable editable = et.getText();
            int inputType = et.getInputType();
            int i = v.getId();
            if (i == R.id.btn_key_1_base) {
                editable.append("1");
//                    mListener.onNumClick(1);

            } else if (i == R.id.btn_key_2_base) {
                editable.append("2");
//                    mListener.onNumClick(2);

            } else if (i == R.id.btn_key_3_base) {
                editable.append("3");
//                    mListener.onNumClick(3);

            } else if (i == R.id.btn_key_4_base) {
                editable.append("4");
//                    mListener.onNumClick(4);

            } else if (i == R.id.btn_key_5_base) {
                editable.append("5");
//                    mListener.onNumClick(5);

            } else if (i == R.id.btn_key_6_base) {
                editable.append("6");
//                    mListener.onNumClick(6);

            } else if (i == R.id.btn_key_7_base) {
                editable.append("7");
//                    mListener.onNumClick(7);

            } else if (i == R.id.btn_key_8_base) {
                editable.append("8");
//                    mListener.onNumClick(8);

            } else if (i == R.id.btn_key_9_base) {
                editable.append("9");
//                    mListener.onNumClick(9);

            } else if (i == R.id.btn_key_0_base) {
                editable.append("0");
//                    mListener.onNumClick(0);

            } else if (i == R.id.btn_key_point_base) {
                if (inputType == EditorInfo.TYPE_CLASS_PHONE || inputType == EditorInfo.TYPE_NUMBER_FLAG_SIGNED) {
                    //约定 不显示小数点
                } else if (inputType == EditorInfo.TYPE_CLASS_NUMBER) {//约定 可以显示小数的数字
                    handlerNumPoint(editable);
                } else {//其他
                    editable.append(".");
                }
//                    mListener.onPointClick();

            } else if (i == R.id.btn_key_delete_base) {
                if (editable.length() > 0) {
                    editable.delete(editable.length() - 1, editable.length());
                }
//                    mListener.onDelete();

            }

        }
    }

    /**
     * 处理数字的小数点
     *
     * @param editable editable
     */
    private void handlerNumPoint(Editable editable) {
        if (editable.length() == 0) {
            editable.append("0.");
        } else if (!editable.toString().contains(".")) {
            editable.append(".");
        }
    }

    private Handler mHandler = new Handler();
    private OnTouchListener focusListener = new OnTouchListener() {
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            });

            return false;
        }

    };

    /**
     * 绑定处理相应的 EditText
     *
     * @param ets ets
     */
    public void attachEdittext(EditText... ets) {
        if (null != ets) {
            for (EditText editText : ets) {
                editText.setOnTouchListener(focusListener);
            }
        }
    }

    /**
     * 键盘监听
     */
    public interface KeyboardListener {
        /**
         * 数字键
         *
         * @param num num
         */
        void onNumClick(int num);

        /**
         * 小数点
         */
        void onPointClick();

        /**
         * 删除
         */
        void onDelete();
    }
}
