package com.yhbc.base.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author SXQ
 * Date 2018/11/20
 * Explain 尺寸转换工具类
 */
public class DensityUtil {
    //缩放比例值
    public static float RATIO = 0.95F;

    /**
     * px 转 dp【按照一定的比例】
     */
    public static int px2dipRatio(Context context, float pxValue) {
        float scale = getScreenDendity(context) * RATIO;
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * dp转px【按照一定的比例】
     */
    public static int dip2pxRatio(Context context, float dpValue) {
        float scale = getScreenDendity(context) * RATIO;
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * px 转 dp
     * 48px - 16dp
     * 50px - 17dp
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = getScreenDendity(context);
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = getScreenDendity(context);
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * 获取屏幕的宽度（像素）
     */
    public static int getScreenWidth(Context context) {
        //1080
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的宽度（dp）
     */
    public static int getScreenWidthDp(Context context) {
        float scale = getScreenDendity(context);
        //360
        return (int) (context.getResources().getDisplayMetrics().widthPixels / scale);
    }

    /**
     * 获取屏幕的高度（像素）
     */
    public static int getScreenHeight(Context context) {
        //1776
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的高度（像素）
     */
    public static int getScreenHeightDp(Context context) {
        float scale = getScreenDendity(context);
        //592
        return (int) (context.getResources().getDisplayMetrics().heightPixels / scale);
    }

    /**
     * 屏幕密度比例
     */
    public static float getScreenDendity(Context context) {
        //3
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取状态栏的高度 72px
     * http://www.2cto.com/kf/201501/374049.html
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            Object object = aClass.newInstance();
            int height = Integer.parseInt(aClass.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;

        //依赖于WMS(窗口管理服务的回调)【不建议使用】
        /*Rect outRect = new Rect();
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;*/
    }

    /**
     * 指定机型（displayMetrics.xdpi）下dp转px
     * 18dp - 50px
     */
    public static int dpToPx(Context context, int dp) {
        return Math.round(((float) dp * getPixelScaleFactor(context)));
    }

    /**
     * 指定机型（displayMetrics.xdpi）下px 转 dp
     * 50px - 18dp
     */
    public static int pxToDp(Context context, int px) {
        return Math.round(((float) px / getPixelScaleFactor(context)));
    }

    /**
     * 获取水平方向的dpi的密度比例值
     * 2.7653186
     */
    public static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / 160.0f);
    }

    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            return new Point(display.getWidth(), display.getHeight());
        }else{
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

}
