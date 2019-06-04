package com.yhbc.base.common.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/1/28.
 */
public class DecimalUtils {

    /**
     * 精准的加法计算
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return roundOff(b1.add(b2).doubleValue());
    }

    /**
     * 精准的加法计算
     */
    public static double add(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return roundOff(b1.add(b2).doubleValue());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 精准的减法计算
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return roundOff(b1.subtract(b2).doubleValue());
    }

    /**
     * 精准的减法计算
     */
    public static double sub(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return roundOff(b1.subtract(b2).doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 精准的乘法计算
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return roundOff(b1.multiply(b2).doubleValue());
    }

    /**
     * 精准的乘法计算
     */
    public static double multiply(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return roundOff(b1.multiply(b2).doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 精准的除法计算
     * 保留len位小数
     */
    public static double divide(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return roundOff(b1.divide(b2).doubleValue());
    }

    /**
     * 精准的除法计算
     * 保留len位小数
     */
    public static double divide(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return roundOff(b1.divide(b2).doubleValue());
    }


    /**
     * 四舍五入保留两位
     */
    public static double roundOff(double f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 价格展示
     * @param price
     * @return
     */
    public static String priceCovert(Double price){
        if(price != null){
            if(price==0d)
                return "￥0";
            String res = String.valueOf(roundOff(price));
            int i = res.indexOf(".");
            if(i<0){
                return "￥"+res;
            }
            String temp = res.substring(0,i);
            char ten = res.charAt(i+1);
            char millon;
            if(i<res.length()-2){
                millon = res.charAt(i+2);
            }else {
                millon = '0';
            }

            if(ten=='0'){
                if(millon=='0'){
                    return "￥"+temp;
                }else {
                    return "￥"+temp+".0"+millon;
                }
            }else {
                if(millon=='0'){
                    return "￥"+temp+'.'+ten;
                }else {
                    return "￥"+temp+'.'+ten+millon;
                }
            }
        }else{
            return "￥0";
        }
    }

    /**
     * 展示价格,不带符号位的
     * @param price
     * @return
     */
    public static String numConvert(Double price){
        if(price != null){
            if(price==0d)
                return "0";
            String res = String.valueOf(price);
            int i = res.indexOf(".");
            if(i<0){
                return res;
            }
            String temp = res.substring(0,i);
            char ten = res.charAt(i+1);
            char millon;
            if(i<res.length()-2){
                millon = res.charAt(i+2);
            }else {
                millon = '0';
            }

            if(ten=='0'){
                if(millon=='0'){
                    return temp;
                }else {
                    return temp+".0"+millon;
                }
            }else {
                if(millon=='0'){
                    return temp+'.'+ten;
                }else {
                    return temp+'.'+ten+millon;
                }
            }
        }else{
            return "0";
        }
    }

    /**
     * 数值标准化,不带符号位的
     * @param num
     * @return
     */
    public static String numConvert(String num){
        if(num != null&&!num.equals("")){
            int i = num.indexOf(".");
            if(i<0){
                return num;
            }
            String temp = num.substring(0,i);
            char ten;
            char millon;
            if(i<num.length()-1){
                ten = num.charAt(i+1);
            }else {
                ten = '0';
            }
            if(i<num.length()-2){
                millon = num.charAt(i+2);
            }else {
                millon = '0';
            }
            if(ten=='0'){
                if(millon=='0'){
                    return temp;
                }else {
                    return temp+".0"+millon;
                }
            }else {
                if(millon=='0'){
                    return temp+'.'+ten;
                }else {
                    return temp+'.'+ten+millon;
                }
            }
        }else{
            return "0";
        }
    }
    /**
     * 四舍五入保留两位
     */
    public static double roundOff(String f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static double round(double d, int len) {     // 进行四舍五入
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，
        //表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
