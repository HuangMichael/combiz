package combiz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUnit {
	// 日历属性
	private static Calendar c = Calendar.getInstance();
	// 简单日期格式属性
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	public static long getYearValue(Date date){
		long year = 0;
		if(date!=null){
	    	Calendar   a   =   Calendar.getInstance()   ; 
		    long   x   =   (a.getTimeInMillis()-date.getTime())/1000000;
		    year = (x/31536)+1;
		}
	    return year;
    }
	/**
	 * 得到当前日期的简单格式：2009-03-31
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatsToday(Date date) {
		sdf.applyPattern("yyyy-MM-dd");
		String dateStr = sdf.format(date.getTime());
		return dateStr;
	}
	/**
	 * 得到当前日期的简单格式：2009-03-31
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatsToday() {
		sdf.applyPattern("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(c.getTime());
		return date;
	}
	/**
	 * 得到当前日期的简单格式：2005-11-01
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatToday() {
		sdf.applyPattern("yyyy-MM-dd");
		String date = sdf.format(c.getTime());
		return date;
	}

	/**
	 * 得到当前日期的简单格式：2005-11-01
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatYearMonth() {
		sdf.applyPattern("yyyy-MM");
		String date = sdf.format(c.getTime());
		return date;
	}

	/**
	 * 得到当前年的简单格式：2005
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatYear() {
		sdf.applyPattern("yyyy");
		String strYear = sdf.format(c.getTime());
		return strYear;
	}

	/**
	 * 得到当前月的简单格式：11
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatMonth() {
		sdf.applyPattern("M");
		String strMonth = sdf.format(c.getTime());
		return strMonth;
	}

	/**
	 * 得到当前日的简单格式：7
	 * 
	 * @return 字符串日期格式
	 */
	public static String getSimpleFormatDay() {
		sdf.applyPattern("d");
		String strDay = sdf.format(c.getTime());
		return strDay;
	}

	/**
	 * 得到当前日期之前(后)日期的简单格式
	 * 
	 * @param amount
	 *            相隔的单位:年
	 * @return 以年为单位的相隔日期
	 */
	public static String SimpleFormatDateComputeY(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.YEAR, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.YEAR, -amount);
		return date;
	}

	/**
	 * 得到当前日期之前(后)日期的简单格式
	 * 
	 * @param amount
	 *            相隔的单位：月
	 * @return 以年为单位的相隔日期
	 */
	public static String SimpleFormatDateComputeM(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.MONTH, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.MONTH, -amount);
		return date;

	}

	/**
	 * 得到当前日期之前(后)日期的简单格式
	 * 
	 * @param amount
	 *            相隔的单位：日
	 * @return 以年为单位的相隔日期
	 */

	public static String SimpleFormatDateComputeD(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.DATE, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.DATE, -amount);
		return date;
	}

	// public static void main(String[] args){
	// //得到系统时间的简单格式
	// System.out.println(DateUnit.getSimpleFormatToday());
	// //得到系统时间的年
	// System.out.println(DateUnit.getSimpleFormatYear());
	// //得到系统时间的月
	// System.out.println(DateUnit.getSimpleFormatMonth());
	// //得到系统时间的日
	// System.out.println(DateUnit.getSimpleFormatDay());
	// //得到系统时间的年－月格式
	// System.out.println(DateUnit.getSimpleFormatYearMonth());
	// }
	/**
	 * 当前时间转格式后返回日期型
	 * 
	 * @param format
	 *            :格式
	 * @return 当前时间日期型
	 */
	public static Date SimpleFormatDate(String format) {
		SimpleDateFormat format1 = new SimpleDateFormat(format);
		String date = format1.format(new Date());
		java.sql.Timestamp t = java.sql.Timestamp.valueOf(date);
		java.util.Date d = new java.util.Date(t.getTime());
		return d;
	}
	
	

}
