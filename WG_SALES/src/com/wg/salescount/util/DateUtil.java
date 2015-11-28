package com.wg.salescount.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String date2StringForController(Date date){
		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(date != null){
			dateStr = sdf.format(date);
		}
		return dateStr;
		// Format date into output format
/*		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		String outputString = outputFormat.format(date);
		return outputString;*/
		//return null;
	}
	
	public static Date string2DateForController(String time){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if(time != null){
				date = (Date) sdf.parse(time);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date string2Date(String time){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		try {
			date = (Date) sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		// Convert input string into a date
		/*SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		Date date = null;
		try {
			date = inputFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;*/
		//return null;
	}
	
/*	public static String timestamp2String(){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tsStr = sdf.format(ts);
		return tsStr;		
	}*/
	
	public static String timestamp2String(Timestamp ts){
		String tsStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tsStr = sdf.format(ts);
		return tsStr;		
	}
	
	public static Timestamp string2Timestamp(String str){
		Timestamp ts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ts = (Timestamp) sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ts;
	}
}
