package com.yhbc.base.common.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author wangqibin
 */
public class DoubleUtil {

    public static void main(String[] args) {
        System.out.println(1 / 5);
        System.out.println(DoubleUtil.div(1.0, 6.0, 2));
    }

    /**
     * 双精度加法运算
     */
    public static double add(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(Double.toString(num1));
        BigDecimal b2 = new BigDecimal(Double.toString(num2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 精准的加法计算
     */
    public static double add(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.add(b2).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 精准的减法计算
     */
    public static double sub(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.subtract(b2).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 双精度加法运算
     */
    public static double add(double num1, double num2, double num3) {
        return add(add(num1, num2), num3);
    }

    /**
     * 双精度减法运算
     */
    public static double sub(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(Double.toString(num1));
        BigDecimal b2 = new BigDecimal(Double.toString(num2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 进行乘法运算
     */
    public static double mul(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 进行除法运算
     */
    public static double div(double num1, double num2, int len) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 进行除法运算保留两位小数并四舍五入
     */
    public static double div(double num1, double num2) {
        if(0 == num2) return num1;
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 进行乘法运算保留两位
     */
    public static double mul2(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return maybe(b1.multiply(b2).doubleValue());
    }

    /**
     * 进行保留两位小数，四舍五入
     */
    public static double maybe(double num1) {
//        BigDecimal b1 = new BigDecimal(num1);
//        String s1 = new DecimalFormat("0.000").format(b1);
//        Double d1 = Double.parseDouble(s1);
//        return d1;
        /*
         * 如果不将num1转换为String,部分小数类似1.485,会出现new BigDecimal(1.485) = 1.48499999999的情况,导致四舍五入精度丢失
         * 具体情况可以在ExampleUnitTest中测试
         */
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String s1 = decimalFormat.format(b1);
        return Double.parseDouble(s1);
    }

    /**
     * 进行保留两位小数，四舍五入
     */
    public static double maybe3(double num1) {
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String s1 = decimalFormat.format(b1);
        return Double.parseDouble(s1);
    }

    /**
     * 四舍五入把double转化int整型，0.5进一，小于0.5不进一
     */
    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

    /**
     * 进行减法运算保留两位小数并四舍五入
     */
    public static double subMybe(Double num1, Double num2) {
        BigDecimal b1 = new BigDecimal(Double.toString(num1));
        BigDecimal b2 = new BigDecimal(Double.toString(num2));
        return b1.subtract(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * String强转Double
     *
     * @param sources
     * @return
     */
    public static double parseDouble(String sources) {
        if (TextUtils.isEmpty(sources)){ return 0;}
        double d = 0;
        try {
            d = Double.parseDouble(sources);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 四舍五入,保留两位小数
     */
    public static double maybeNew(double num1) {
        return (double) Math.round(num1 * 100) / 100;
    }

    /**
     * double 转 int
     *
     * @param num
     * @return
     */
    public static int ceilInt(double num){
        return (int) Math.ceil(num);
    }


    /**
     * 比对两个double是否相等
     * @param d1
     * @param d2
     * @returnDoubleUtil.equals(localTotalFee, model.getTotalFee())
     */
    public static boolean equals(double d1, double d2) {
        if (d1 - d2 < 0.000001 &&
                d1 - d2 > -0.000001) {
            return true;
        }
        return false;
    }

    /**
     * 进行保留一位小数
     */
    public static double retainDecimal(double num1) {
        DecimalFormat df = new DecimalFormat("#0.#");
        df.setRoundingMode(RoundingMode.FLOOR);
        String str = df.format(num1);
        Double d1 = Double.parseDouble(str);
        return d1;
    }
}

