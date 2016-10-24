package com.jzpz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期操作函数
 * @author wQ
 *
 */
public class DateUtils {
	/**
	 * 日期格式化
	 * @param dateString
	 * @param dataFormat
	 * @return
	 */
	public static Date getDate4String(String dateString, String dataFormat) {
		if (null != dateString && dateString.trim().length() > 0) {
			DateFormat df = new SimpleDateFormat(dataFormat);
			try {
				return df.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String getNow2String (){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
}
