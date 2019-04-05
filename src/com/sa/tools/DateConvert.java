package com.sa.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
	public static String convertToBit(boolean date){
		return date?"1":"0";
	}
	public static String getDateStr(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static Date convertToDate(String dateString){
		Date data = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			data = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static Date convertToDate(String dateString, String pattern){
		Date data = null;
		DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			data = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	public static String convertToString (Date date){
		String dateString = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		dateString = sdf.format(date);
		return dateString;
	}
	public static String convertToString (Date date,String pattern){
		String dateString = null;
		DateFormat sdf = new SimpleDateFormat(pattern);
		dateString = sdf.format(date);
		return dateString;
	}
}
