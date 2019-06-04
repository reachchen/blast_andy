package com.yhbc.base.common.utils;

/**
 * Created by chengwen on 2016/12/12.
 */
public class TypeUtils {

    public static double getDouble(String data){
        double d = 0;
        try {
            d = Double.parseDouble(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }

    public static int getInt(String data){
        int d = 0;
        try {
            d = Integer.parseInt(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }

    public static long getLong(String data){
        long l = 0L;
        try {
            l = Long.parseLong(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return l;
    }

    public static String getString(String data){
        if(data == null){
            return "";
        }
        return data;
    }

    public static boolean getBoolean(String data){
        try {
            boolean b = Boolean.parseBoolean(data);
            return b;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
