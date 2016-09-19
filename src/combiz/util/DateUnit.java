package combiz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUnit {
	// ��������
	private static Calendar c = Calendar.getInstance();
	// �����ڸ�ʽ����
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
	 * �õ���ǰ���ڵļ򵥸�ʽ��2009-03-31
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatsToday(Date date) {
		sdf.applyPattern("yyyy-MM-dd");
		String dateStr = sdf.format(date.getTime());
		return dateStr;
	}
	/**
	 * �õ���ǰ���ڵļ򵥸�ʽ��2009-03-31
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatsToday() {
		sdf.applyPattern("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(c.getTime());
		return date;
	}
	/**
	 * �õ���ǰ���ڵļ򵥸�ʽ��2005-11-01
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatToday() {
		sdf.applyPattern("yyyy-MM-dd");
		String date = sdf.format(c.getTime());
		return date;
	}

	/**
	 * �õ���ǰ���ڵļ򵥸�ʽ��2005-11-01
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatYearMonth() {
		sdf.applyPattern("yyyy-MM");
		String date = sdf.format(c.getTime());
		return date;
	}

	/**
	 * �õ���ǰ��ļ򵥸�ʽ��2005
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatYear() {
		sdf.applyPattern("yyyy");
		String strYear = sdf.format(c.getTime());
		return strYear;
	}

	/**
	 * �õ���ǰ�µļ򵥸�ʽ��11
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatMonth() {
		sdf.applyPattern("M");
		String strMonth = sdf.format(c.getTime());
		return strMonth;
	}

	/**
	 * �õ���ǰ�յļ򵥸�ʽ��7
	 * 
	 * @return �ַ������ڸ�ʽ
	 */
	public static String getSimpleFormatDay() {
		sdf.applyPattern("d");
		String strDay = sdf.format(c.getTime());
		return strDay;
	}

	/**
	 * �õ���ǰ����֮ǰ(��)���ڵļ򵥸�ʽ
	 * 
	 * @param amount
	 *            ����ĵ�λ:��
	 * @return ����Ϊ��λ���������
	 */
	public static String SimpleFormatDateComputeY(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.YEAR, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.YEAR, -amount);
		return date;
	}

	/**
	 * �õ���ǰ����֮ǰ(��)���ڵļ򵥸�ʽ
	 * 
	 * @param amount
	 *            ����ĵ�λ����
	 * @return ����Ϊ��λ���������
	 */
	public static String SimpleFormatDateComputeM(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.MONTH, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.MONTH, -amount);
		return date;

	}

	/**
	 * �õ���ǰ����֮ǰ(��)���ڵļ򵥸�ʽ
	 * 
	 * @param amount
	 *            ����ĵ�λ����
	 * @return ����Ϊ��λ���������
	 */

	public static String SimpleFormatDateComputeD(int amount) {
		Calendar ctemp = c;
		ctemp.add(Calendar.DATE, amount);
		String date = sdf.format(ctemp.getTime());
		ctemp.add(Calendar.DATE, -amount);
		return date;
	}

	// public static void main(String[] args){
	// //�õ�ϵͳʱ��ļ򵥸�ʽ
	// System.out.println(DateUnit.getSimpleFormatToday());
	// //�õ�ϵͳʱ�����
	// System.out.println(DateUnit.getSimpleFormatYear());
	// //�õ�ϵͳʱ�����
	// System.out.println(DateUnit.getSimpleFormatMonth());
	// //�õ�ϵͳʱ�����
	// System.out.println(DateUnit.getSimpleFormatDay());
	// //�õ�ϵͳʱ����꣭�¸�ʽ
	// System.out.println(DateUnit.getSimpleFormatYearMonth());
	// }
	/**
	 * ��ǰʱ��ת��ʽ�󷵻�������
	 * 
	 * @param format
	 *            :��ʽ
	 * @return ��ǰʱ��������
	 */
	public static Date SimpleFormatDate(String format) {
		SimpleDateFormat format1 = new SimpleDateFormat(format);
		String date = format1.format(new Date());
		java.sql.Timestamp t = java.sql.Timestamp.valueOf(date);
		java.util.Date d = new java.util.Date(t.getTime());
		return d;
	}
	
	

}
