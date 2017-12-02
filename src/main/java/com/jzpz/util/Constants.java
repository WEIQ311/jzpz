package com.jzpz.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 系统常量
 * 
 * @author linhongbao
 * 
 */
public class Constants {

	public static final int PAGE_SIZE = 10;
	public static final int PAGE_NUMBER = 10;
	public static final String DATE_FORMAT_STYLE = "yyyy-MM-dd";
	public static final String TIME_FORMAT_STYLE = "yyyy-MM-dd HH:mm:ss";
	public static final String MONEY_FORMAT_STYLE = "#,###.##";
	public static final int STRING_BUILDER_INIT_SIZE = 300;
	public static final String DB_SCHEAM = "pr";// Oracle数据库使用
	
	public static final String SCHOOLTERMCFG_KEY = "schoolTermCfg";// Oracle数据库使用

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		if (str == null) {
            return true;
        }
		if ("".equals(str)) {
            return true;
        }
		return false;
	}

	/**
	 * 保留2位小数
	 * 
	 * @param d
	 * @return
	 */
	public static Double to2decimal(Double d) {
		DecimalFormat df = new DecimalFormat(".##");
		d = Double.valueOf(df.format(d));
		return d;
	}

	/**
	 * 保留1位小数
	 * 
	 * @param d
	 * @return
	 */
	public static Double to1decimal(Double d) {
		DecimalFormat df = new DecimalFormat(".#");
		d = Double.valueOf(df.format(d));
		return d;
	}

	/**
	 * 获得日志的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得当前年
	 * 
	 * @param date
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获得两个日期之间相差的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDistDays(Date startDate, Date endDate) {
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
		return (int) totalDate;
	}

	/**
	 * 当前年度
	 * 
	 * @return
	 */
	public static Integer currentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STYLE);
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Object Obj) {
		if (Obj == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STYLE);
		return dateFormat.format(Obj);
	}

	/**
	 * 解析日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String dateStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STYLE);
		return dateFormat.parse(dateStr);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String formatStyle) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStyle);
		return dateFormat.format(date);
	}

	/**
	 * 判断double足够小
	 * 
	 * @param d
	 * @return
	 */
	public static boolean equalZero(double d) {
//		System.out.println(d - 0);
		return (d - 0) < 0.0000001;
	}

	/**
	 * 格式化金钱
	 * 
	 * @param money
	 * @return
	 */
	public static String formatMoney(Double money) {
		if (money == null) {
			return "";
		}
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(2);
		format.setMinimumIntegerDigits(1);
		format.setMaximumIntegerDigits(10);
		return format.format(money);
	}
}
