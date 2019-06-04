package com.yhbc.base.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 对象转sql
 */

public class ChangeToSqlUtil {

	public static  String changeToSql(String sql,Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer bufferName=new StringBuffer();
		StringBuffer bufferValue=new StringBuffer();
		bufferName.append(sql);
		bufferValue.append("values(");
		Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
		for(int j=0 ; j<field.length ; j++){     //遍历所有属性
			String name = field[j].getName();    //获取属性的名字
//			System.out.println("attribute name:"+name);
			String type = field[j].getGenericType().toString();    //获取属性的类型
			if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				String value = (String) m.invoke(model);    //调用getter方法获取属性值
				if(value != null){
					bufferValue.append("\""+value+"\"");
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append("null");
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Long")||type.equals("long")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Long value = (Long) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(0);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Integer")||type.equals("int")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Integer value = (Integer) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(0);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Short")||type.equals("short")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Short value = (Short) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(0);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Byte")||type.equals("byte")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Byte value = (Byte) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(0);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Double")||type.equals("double")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Double value = (Double) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(0);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.lang.Boolean")||type.equals("boolean")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Boolean value = (Boolean) m.invoke(model);
				if(value != null){
					bufferValue.append(value);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append(false);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
			if(type.equals("class java.util.Date")){
				bufferName.append("\""+name+"\"");
				name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				Method m = model.getClass().getMethod("get"+name);
				Date value = (Date) m.invoke(model);
				if(value != null){
					long date_time=value.getTime();
					bufferValue.append(date_time);
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}else {
					bufferValue.append("null");
					if (j!=(field.length-1)) {
						bufferName.append(",");
						bufferValue.append(",");
					}else {
						bufferName.append(")");
						bufferValue.append(")");
					}
				}
			}
		}
		return bufferName.toString()+bufferValue.toString()+";";
	}

}
