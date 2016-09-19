package combiz.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Util {
	/**
	 * 
	 * @TODO 将界面控件ID转换为数据库字段名
	 * @param fieldid
	 * @return
	 * @brianhong 2007-8-31
	 */
	public static String fieldidToColname(String fieldid) {
		if (fieldid == null)
			return null;

		String colName = fieldid;
		if (fieldid.indexOf(".") > 0)
			colName = fieldid.substring(fieldid.indexOf(".") + 1);
		colName = colName.toUpperCase();

		return colName;
	}

	/**
	 * 
	 * @TODO 将字符转换为boolean类型
	 * @param bstr
	 * @return
	 * @brianhong 2007-8-31
	 */
	public static boolean getBoolean(String bstr) {
		if (bstr == null || bstr.equals(""))
			return false;
		else if (bstr.equalsIgnoreCase("Y") || bstr.equalsIgnoreCase("1")
				|| bstr.equalsIgnoreCase("是"))
			return true;

		return false;
	}

	/**
	 * 根据折旧法算折旧额 作者:陈明锐 日期:Apr 9, 2009
	 * 
	 * @param depfaction
	 *            折旧法
	 * @param scraprate
	 *            残值率
	 * @param planyears
	 *            预计使用年限
	 * @param usedyear
	 *            已使用年限
	 * @param nowcost
	 *            当前值
	 * @param totalcost
	 *            资产原值
	 * @return
	 */
	public static double countDepcost(String depfaction, Double scraprate,long planyears, long usedyear, Double nowcost, double totalcost) {
		double rate = 0.0;
		double depcost = 0.0;
		double nowCost = nowcost;
		if (planyears != 0 && depfaction != null) {
			if (depfaction.equals("平均年限法")) {
				rate = countDeprate(depfaction, scraprate, planyears, usedyear);
				depcost = rate * usedyear * totalcost;
			}
			if (depfaction.equals("双倍余额递减法")) {
				rate = countDeprate(depfaction, scraprate, planyears, usedyear);
				for (int j = 0; j < usedyear; j++) {
					depcost += rate * nowCost;
					nowCost = totalcost - depcost;
				}
			}
			if (depfaction.equals("年数总和法")) {
				for (int i = 1; i < usedyear + 1; i++) {
					rate = countDeprate(depfaction, scraprate, planyears, i);
					depcost += totalcost * (1 - scraprate) * rate;
				}
			}
		} 
		return doubleTodouble(depcost);
	}

	/**
	 * 计算折旧率 作者:陈明锐 日期:Mar 31, 2009
	 * 
	 * @param depfaction
	 *            折旧法
	 * @param scraprate
	 *            残值率
	 * @param planyears
	 *            预计使用年限
	 * @param usedyear
	 *            已使用年限
	 * @return
	 */
	public static double countDeprate(String depfaction, Double scraprate,
			long planyears, long usedyear) {
		double deprate = 0.0; // 折旧率
		if (planyears != 0 && depfaction != null) {
			if (depfaction.equals("平均年限法")) {
				if (scraprate != null)
					deprate = (1 - scraprate) / planyears;
				else
					deprate = 1 / planyears;
			}
			if (depfaction.equals("年数总和法")) {
				double a = (planyears - usedyear) + 1;
				double b = planyears + 1;
				double c = (planyears * b)/2;
				deprate = a / c;
			}
			if (depfaction.equals("双倍余额递减法")) {
				deprate = 2.0 / planyears;
			}
		}
		return doubleTodouble(deprate);
	}
	/**
	 * double类型转换
	 * 作者:陈明锐
	 * 日期:Apr 9, 2009
	 * @param number
	 * @return
	 */
	public static Double doubleTodouble(double number){
		NumberFormat format = new DecimalFormat("#0.0000");
		return new Double(format.format(number));
	}
}
