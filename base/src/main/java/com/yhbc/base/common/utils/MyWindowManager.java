package com.yhbc.base.common.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;

import com.yhbc.base.common.view.KeyBardView;


/**
 * 作者：tianxiangmin on 2017/12/5 14:57
 * 邮箱：txm@blibao.com
 * 标题:
 */

public class MyWindowManager {
    private static MyWindowManager myWindowManager;
    //用于控制在屏幕上添加或移除悬浮窗
    private WindowManager windowManager;
    public  static   int screenWidth;
    public  static int screenHeight;
    //输入悬浮窗View的实例
    private KeyBardView inputWindow;
    //输入悬浮窗View的参数
    private WindowManager.LayoutParams inputWindowParams;
    private static EditText input_et;
    public static MyWindowManager getInstanse(){
        if (myWindowManager==null){
            myWindowManager=new MyWindowManager();
        }
        return myWindowManager;
    }

    /**
     * 创建输入浮窗。初始位置为屏幕的右部中间位置。
     */
    public  void createInputWindow(Context context, EditText et, boolean full) {

        windowManager = getWindowManager(context);

        screenWidth = windowManager.getDefaultDisplay().getWidth();

        screenHeight = windowManager.getDefaultDisplay().getHeight();

        if (inputWindow == null) {

            inputWindow = new KeyBardView(context,et,full);

            if (inputWindowParams == null) {

                inputWindowParams = new WindowManager.LayoutParams();

                inputWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;

                inputWindowParams.format = PixelFormat.RGBA_8888;

                inputWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                inputWindowParams.gravity = Gravity.LEFT | Gravity.TOP;

                if (full){
                    inputWindowParams.width = screenWidth;
                }else {
                    inputWindowParams.width =screenWidth/2;
                }

                inputWindowParams.height = inputWindow.viewHeight;

                inputWindowParams.x = screenWidth/4;

                inputWindowParams.y = screenHeight;

            }

            inputWindow.setParams(inputWindowParams);

            windowManager.addView(inputWindow, inputWindowParams);

        }
    }
    /**
     * 创建输入浮窗。初始位置为屏幕的中间位置
     */
    public  void createInputWindowtwo(Context context, EditText et, boolean full) {

        windowManager = getWindowManager(context);

        screenWidth = windowManager.getDefaultDisplay().getWidth();

        screenHeight = windowManager.getDefaultDisplay().getHeight();

        if (inputWindow == null) {

            inputWindow = new KeyBardView(context,et,full);

            if (inputWindowParams == null) {

                inputWindowParams = new WindowManager.LayoutParams();

                inputWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;

                inputWindowParams.format = PixelFormat.RGBA_8888;

                inputWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                inputWindowParams.gravity = Gravity.LEFT | Gravity.TOP;

                if (full){
                    inputWindowParams.width = screenWidth;
                }else {
                    inputWindowParams.width =screenWidth/2;
                }

                inputWindowParams.height = inputWindow.viewHeight;

                inputWindowParams.x = screenWidth/4;

                inputWindowParams.y = screenHeight/8;

            }

            inputWindow.setParams(inputWindowParams);

            windowManager.addView(inputWindow, inputWindowParams);

        }
    }

    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context
     *            必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    private WindowManager getWindowManager(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager;
    }

    /**
     * 将小悬浮窗从屏幕上移除。
     */
    public  void removeInputWindow() {
        if (inputWindow != null&&windowManager!=null) {
            inputWindow.clean();
//            if (inputWindow.isAttachedToWindow()) {
                windowManager.removeView(inputWindow);
//            }
            inputWindow = null;
            inputWindowParams=null;
        }
    }

    /**
     * 将小悬浮窗从屏幕上移除。
     */
    public  void removeInputWindowNotBoard() {
        if (inputWindow != null&&windowManager!=null) {
//            if (inputWindow.isAttachedToWindow()) {
                windowManager.removeView(inputWindow);
//            }
            inputWindow = null;
            inputWindowParams=null;
        }
    }

    public KeyBardView getInputWindow() {
        return inputWindow;
    }
}
