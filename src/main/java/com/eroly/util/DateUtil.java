package com.eroly.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期格式化
 * 
 */
public class DateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String DATE_FORMATTER = "yyyy-MM-dd";

	public static Date parseDate(String dateStr, String format) {
		if (StringUtil.isEmpty(dateStr))
			return null;
		Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			String dt = dateStr;// .replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (Date) df.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DATE_FORMATTER);
	}

	public static Date parseDate(String dateStr, SimpleDateFormat format) {
		if (StringUtil.isEmpty(dateStr))
			return null;
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (Exception e) {
			logger.error("dataStr:{} , e printStack:{}",dateStr,e);
		}
		return date;
	}

	public static String todayStr(String format) {
		return formatDateToStr(new Date(), format);
	}

	public static Date today(String format) {
		return parseDate(todayStr(format), format);
	}

	public static String todayStr() {
		return formatDateToStr(new Date(), DATE_FORMATTER);
	}

	/**
	 * 当前日期，精确到秒
	 * @return
	 */
	public static String todayfulldata() {
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String a1 = dateformat1.format(new Date());
		return a1;
	}
	
	/**
	 * 当前日期，精确到毫秒
	 * @return
	 */
	public static String todayPrecMilliSecond(){
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMddHHmmssS");
		String a1 = dateformat1.format(new Date());
		return a1;
	}
	
	/**
	 * 当前日期，精确到毫秒
	 * @return
	 */
	public static String todayHighPrecfullData(){
		Date dt = new Date();
		Long time = dt.getTime(); // 距离1970年1月1日0点0分0秒的毫秒数
		return String.valueOf(time);
	}
	
	public static String todayfullFormateDate(){
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a1 = dateformat1.format(new Date());
		return a1;
	}

	public static Date today() {
		return parseDate(todayStr(), DATE_FORMATTER);
	}

	/**
	 * @param date
	 *            需要格式化的日期對像
	 * @param formatter
	 *            格式化的字符串形式
	 * @return 按照formatter指定的格式的日期字符串
	 * @throws ParseException
	 *             無法解析的字符串格式時拋出
	 */
	public static String formatDateToStr(Date date, String formatter) {
		if (date == null)
			return "";
		try {
			return new java.text.SimpleDateFormat(formatter).format(date);
		} catch (Exception e) {
			logger.error("e printStack:{}",e);
		}
		return "";
	}

	/**
	 * 生成默认格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToStr(Date date) {
		return formatDateToStr(date, DATE_FORMATTER);
	}

	/**
	 * 將日期按照指定的模式格式化
	 * 
	 * @param date
	 *            {@link Date}
	 * @param format
	 *            如：yyyy/MM/dd
	 * @return 返回空表示格式化產生異常
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 将一种字符日期转化成另外一种日期格式
	 * 
	 * @param date
	 * @param fmtSrc
	 * @param fmtTag
	 * @return
	 */
	public static String format(String date, String fmtSrc, String fmtTag) {
		if (null == fmtSrc)
			return null;
		if (fmtSrc.equals(fmtTag)) {
			return date;
		}
		String year, month, daty;
		int Y, M, D;
		fmtSrc = fmtSrc.toUpperCase();
		Y = fmtSrc.indexOf("YYYY");
		M = fmtSrc.indexOf("MM");
		D = fmtSrc.indexOf("DD");
		if (Y < 0 || M < 0 || D < 0) {
			return date;
		}
		year = date.substring(Y, Y + 4);
		month = date.substring(M, M + 2);
		daty = date.substring(D, D + 2);
		fmtTag = fmtTag.toUpperCase();
		fmtTag = fmtTag.replaceAll("YYYY", year);
		fmtTag = fmtTag.replaceAll("MM", month);
		fmtTag = fmtTag.replaceAll("DD", daty);
		return fmtTag;
	}

	/**
	 * 計算指定年月的日期數
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int maxDayOfMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			boolean isRunYear = (year % 400 == 0)
					|| (year % 4 == 0 && year % 100 != 0);
			return isRunYear ? 29 : 28;
		default:
			return 31;
		}
	}

	/**
	 * 获取指定年月的日期數
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int maxDayOfMonth(String year, String month) {
		return maxDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastMonthDate(String year, String month) {
		return getLastMonthDate(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastMonthDate(int year, int month) {
		if (month <= 1) {
			year -= 1;
			month = 12;
		} else {
			month -= 1;
		}
		StringBuffer bfDate = new StringBuffer();
		bfDate.append(year);
		if (month < 10)
			bfDate.append("0");
		bfDate.append(month);
		bfDate.append(maxDayOfMonth(year, month));
		return bfDate.toString();
	}

	/**
	 * 提前N天的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date beforeDate(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -days);
		return c.getTime();

	}

	/**
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 一周前的日期
	 * 
	 * @return
	 */
	public static Date getLastWeek() {
		return getNextDay(-7);
	}

	/**
	 * 取相对天数，正数为向后，负数为向前
	 * 
	 * @param day
	 * @return
	 */
	public static Date getNextDay(int day) {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}

	public static int curHour(Calendar cal) {
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int curMinute(Calendar cal) {
		return cal.get(Calendar.MINUTE);
	}

	public static int curSecond(Calendar cal) {
		return cal.get(Calendar.SECOND);
	}

	public static String curTimeStr() {
		Calendar cal = new GregorianCalendar();
		// 分别取得当前日期的年、月、日
		StringBuffer bf = new StringBuffer(10);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10)
			bf.append("0");
		bf.append(hour);
		bf.append(":");
		int minite = cal.get(Calendar.MINUTE);
		if (minite < 10)
			bf.append("0");
		bf.append(minite);
		bf.append(":");
		int second = cal.get(Calendar.SECOND);
		if (second < 10)
			bf.append("0");
		bf.append(second);
		return bf.toString();
	}

	/***************************************************************************
	 * @功能 计算当前日期某年的第几周
	 * @return interger
	 * @throws ParseException
	 **************************************************************************/
	public static int getWeekNumOfYear() {
		Calendar calendar = new GregorianCalendar();
		int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		return iWeekNum;
	}

	/***************************************************************************
	 * @功能 计算指定日期某年的第几周
	 * @return interger
	 * @throws ParseException
	 **************************************************************************/
	public static int getWeekNumOfYearDay(String strDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(parseDate(strDate));
		int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		return iWeekNum;
	}

	/***************************************************************************
	 * @功能 计算某年某周的开始日期
	 * @return interger
	 * @throws ParseException
	 **************************************************************************/
	public static String getWeekFirstDay(int yearNum, int weekNum) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		return tempYear + "-" + tempMonth + "-" + tempDay;
	}

	public static String getWeekFirstDay(int weekNum) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 分别取得当前日期的年、月、日
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
	}

	/***************************************************************************
	 * @功能 计算某年某周的结束日期
	 * @return interger
	 * @throws ParseException
	 **************************************************************************/
	public static String getWeekEndDay(int yearNum, int weekNum) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 分别取得当前日期的年、月、日
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
	}

	public static String getWeekEndDay(int weekNum) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 分别取得当前日期的年、月、日
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
	}

	public static String getDatePreHours(int preHours) {
		// 当前日期
		Date date = new Date();
		// 格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER
				+ " HH:mm:ss");
		// 日历对象
		Calendar calendar = new GregorianCalendar();
		// 设置当前日期
		calendar.setTime(date);
		// 小时增减
		calendar.add(Calendar.HOUR_OF_DAY, preHours);

		return sdf.format(calendar.getTime());
	}

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 获取指定日期的下一周日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getNextWeekDay(String dateStr, int weekday) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateUtil.parseDate(dateStr, "yyyy-MM-dd"));
		int weekNum = cal.get(Calendar.WEEK_OF_YEAR);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
		return format(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取指定日期的当前一周日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getCurrWeekDay(String dateStr, int weekday) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateUtil.parseDate(dateStr, "yyyy-MM-dd"));
		cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
		return format(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取指定日期的下个月日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getCurrMonthDay(String dateStr, int day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateUtil.parseDate(dateStr, "yyyy-MM-dd"));
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int endDay = maxDayOfMonth(year, month);
		int valDay = day;
		if (day > endDay) {
			valDay = endDay;
		}
		cal.set(Calendar.DATE, valDay);
		return format(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取指定日期的下个月日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextMonthDay(String dateStr, int day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateUtil.parseDate(dateStr, "yyyy-MM-dd"));
		int month = cal.get(Calendar.MONTH) + 2;
		cal.set(Calendar.MONTH, month - 1);
		int year = cal.get(Calendar.YEAR);
		int endDay = maxDayOfMonth(year, month);
		int valDay = day;
		if (day > endDay) {
			valDay = endDay;
		}
		cal.set(Calendar.DATE, valDay);
		return format(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 前几个月日期
	 * 
	 * @param dateStr
	 * @param number
	 * @return
	 */
	public static String beforeMonthDate(String dateStr, int number) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(DateUtil.parseDate(dateStr, DATE_FORMATTER));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		int endDay = maxDayOfMonth(year, month);
		day = Math.min(endDay, day);
		cal.set(Calendar.MONTH, month - number);
		cal.set(Calendar.DATE, day);

		return format(cal.getTime(), DATE_FORMATTER);
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 获取指定日期的预约日期 weekday
	 * 
	 * @param date
	 * @return
	 */
	public static String getPlanWeekDay(String startDate, String endDate,
			int day) {
		String dt = getCurrWeekDay(startDate, day);
		if (dt.compareTo(startDate) < 0) {
			dt = getNextWeekDay(startDate, day);
		}
		if (dt.compareTo(endDate) > 0) {
			dt = "";
		}
		return dt;
	}

	/**
	 * 获取指定日期的预约日期day
	 * 
	 * @param date
	 * @return
	 */
	public static String getPlanMonthDay(String startDate, String endDate,
			int day) {
		String dt = getCurrMonthDay(startDate, day);
		if (dt.compareTo(startDate) < 0) {
			dt = getNextMonthDay(startDate, day);
		}
		if (dt.compareTo(endDate) > 0) {
			dt = "";
		}
		return dt;
	}

	/**
	 * 将字符串yyyyMMdd 格式成 字符串yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String fmtmat(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date newDate = df.parse(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(newDate);
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 将字符串yyyyMMddHHmmss 格式成 字符串yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String fmtmatfulldate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date newDate = df.parse(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(newDate);
		} catch (Exception ex) {
			return date;
		}
	}
	
	/**
	 * 当前日期 四位 mmdd
	 * 
	 * @param date
	 * @return
	 */
	public static String datemmdd() {
			SimpleDateFormat df = new SimpleDateFormat("MMdd");
			Date newDate = new Date();
			return df.format(newDate);
	}
	
	
	/**
	 * 当前日期 8 mmdd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateyymmddhms() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date newDate = new Date();
			return df.format(newDate);
	}
	
	/***
	 * 日期月份减一个月
	 * 
	 * @param datetime
	 *            日期(2014-11)
	 * @return 2014-10
	 */
	public static String dateFormat(String datetime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cl = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(datetime);
			cl.setTime(date);
			cl.add(Calendar.MONTH, -1);
			date = cl.getTime();
			return sdf.format(date);
		} catch (ParseException e) {
			logger.error("e printStack:{}",e);
		}
		return "";
	}

	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}

	/****
	 * 传入具体日期 ，返回具体日期减一个月。
	 * 
	 * @param date
	 *            日期(2014-04-20)
	 * @return 2014-03-20
	 * @throws ParseException
	 */
	public static String subMonth(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dt = sdf.parse(date);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);

		rightNow.add(Calendar.MONTH, -1);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);

		return reStr;
	}

	/****
	 * 获取月末最后一天
	 * 
	 * @param sDate
	 *            2014-11-24
	 * @return 30
	 */
	private static String getMonthMaxDay(String sDate) {
		SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf_full.parse(sDate + "-01");
			cal.setTime(date);
			int last = cal.getActualMaximum(Calendar.DATE);
			return String.valueOf(last);
		} catch (ParseException e) {
			logger.error("e printStack:{}",e);
		}
		return "";
	}

	// 判断是否是月末
	public static boolean isMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DATE) == cal
				.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		else
			return false;
	}

	/***
	 * 日期减一天、加一天
	 * 
	 * @param option
	 *            传入类型 pro：日期减一天，next：日期加一天
	 * @param _date
	 *            2014-11-24
	 * @return 减一天：2014-11-23或(加一天：2014-11-25)
	 */
	public static String checkOption(String option, String _date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		Date date = null;

		try {
			date = (Date) sdf.parse(_date);
			cl.setTime(date);
		} catch (ParseException e) {
			logger.error("e printStack:{}",e);
		}
		if ("pre".equals(option)) {
			// 时间减一天
			cl.add(Calendar.DAY_OF_MONTH, -1);

		} else if ("next".equals(option)) {
			// 时间加一天
			cl.add(Calendar.DAY_OF_YEAR, 1);
		} 
		date = cl.getTime();
		return sdf.format(date);
	}

	/***
	 * 判断日期是否为当前月， 是当前月返回当月最小日期和当月目前最大日期以及传入日期上月的最大日和最小日
	 * 不是当前月返回传入月份的最大日和最小日以及传入日期上月的最大日和最小日
	 * 
	 * @param date
	 *            日期 例如：2014-11
	 * @return String[] 开始日期，结束日期，上月开始日期，上月结束日期
	 * @throws ParseException
	 */
	public static String[] getNow_Pre_Date(String date) throws ParseException {

		String[] str_date = new String[4];
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
		String stMonth = sdf.format(now);
		String stdate = "";// 开始日期
		String endate = "";// 结束日期
		String preDate_start = "";// 上月开始日期
		String preDate_end = "";// 上月结束日期

		// 当前月
		if (date.equals(stMonth)) {
			stdate = stMonth + "-01"; // 2014-11-01
			endate = sdf_full.format(now);// 2014-11-24
			preDate_start = subMonth(stdate);// 2014-10-01
			preDate_end = subMonth(endate);// 2014-10-24
		} else {
			// 非当前月
			String monthMaxDay = getMonthMaxDay(date);
			stdate = date + "-01";// 2014-10-01
			endate = date + "-" + monthMaxDay;// 2014-10-31
			preDate_start = subMonth(stdate);// 2014-09-01
			preDate_end = subMonth(endate);// 2014-09-30
		}
		str_date[0] = stdate;
		str_date[1] = endate;
		str_date[2] = preDate_start;
		str_date[3] = preDate_end;

		return str_date;
	}
	
	/*
	 * 
	 * 当前时间的日期和传入日期比较
	 */
	public static boolean getbigDate(int date2){
		Calendar c = Calendar.getInstance();
		int datenum=c.get(Calendar.DATE);
		if(date2>datenum){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/*
	 * 月份加一
	 */
	public static String getNextMoth(String dateStr,String sdfStr){
		SimpleDateFormat sf = new SimpleDateFormat(sdfStr); 
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		
		try {
			ca.setTime(sf.parse(dateStr));
		} catch (ParseException e) {
			logger.error("e printStack:{}",e);
		}
		Date now = ca.getTime(); 
		ca.add(Calendar.MONTH, 1); //
		Date lastMonth = ca.getTime(); //结果 
		return sf.format(lastMonth);
	}
	
	/**
	 * 获取下一天
	 * 
	 * @param dateStr
	 * @param sdfStr
	 * @return
	 */
	public static String getNextDay(String dateStr,String sdfStr){
		SimpleDateFormat sf = new SimpleDateFormat(sdfStr); 
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		try {
			ca.setTime(sf.parse(dateStr));
		} catch (ParseException e) {
			logger.error("e printStack:{}",e);
		}
		ca.add(Calendar.DAY_OF_MONTH, 1);
		Date lastMonth = ca.getTime(); //结果 
		return sf.format(lastMonth);
	}
	
	/*
	 * 当前日期
	 * 
	 * yyyyMMdd
	 * 
	 * 
	 */
	public static String getNowDate(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
		return sf.format(new Date());
	}

	/*
	 * 当前日期
	 *
	 * HHmiSS
	 *
	 *
	 */
	public static String getNowTime(){
		SimpleDateFormat sf = new SimpleDateFormat("HHmmssSSS");
		return sf.format(new Date());
	}
	/**
	 * 当前时间，时分秒HHmmss
	 * @return
	 */
	public static String getNowTimeShort(){
		SimpleDateFormat sf = new SimpleDateFormat("HHmmss");
		return sf.format(new Date());
	}


	/*
	 * 当前年份月份
	 *
	 * yyyyMM
	 *
	 *
	 */
	public static String getNowYearMath(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		return sf.format(new Date());
	}
	
	/*
	 * 当前年份月份
	 *
	 * yyyyMM
	 *
	 *
	 */
	public static String getNowDay(){
		SimpleDateFormat sf = new SimpleDateFormat("dd");
		return sf.format(new Date());
	}

	/*
	 * 当前年份月份
	 *
	 * yyMM
	 *
	 *
	 */
	public static String getNowYearMath2(){
		SimpleDateFormat sf = new SimpleDateFormat("yyMM");
		return sf.format(new Date());
	}

	public static Integer getDays(Date smdate, Date bdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try{
			smdate  = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		}catch (Exception e){
			logger.error("e printStack:{}",e);
		}


		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1-time2)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}




	/**
	 * yyyMMdd
	 * 当前日期往前推算几个月
	 * @param mun
	 */
	public static String getDecMonth(String dateStr,int mun){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dt = null;
		try{
			dt = sdf.parse(dateStr);
		}catch (Exception e){
			return "";
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH,-mun);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}
	/**
	 * yyyMMdd
	 * 是否在有效的时间范围内：不在返回-1，在返回1
	 * @param y 有效年
	 * @param m 有效月
	 */
	public static int IsDateRange(String dateStr,int y,int m){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dt = null;
		try{
			dt = sdf.parse(dateStr);
		}catch (Exception e){
			return 2;
		}
		Calendar calendarNow = Calendar.getInstance();
		Calendar calendarDat = Calendar.getInstance();
		calendarNow.setTime(new Date());
		calendarDat.setTime(dt);
		calendarDat.add(Calendar.YEAR, y);
		calendarDat.add(Calendar.MONTH, m);
		return calendarDat.compareTo(calendarNow);
	}
	public static void main(String[] args) {
		System.out.println(IsDateRange("20161114",0,12));
	}

}
