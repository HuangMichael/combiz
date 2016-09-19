package combiz.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Util {
	/**
	 * 
	 * @TODO ������ؼ�IDת��Ϊ���ݿ��ֶ���
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
	 * @TODO ���ַ�ת��Ϊboolean����
	 * @param bstr
	 * @return
	 * @brianhong 2007-8-31
	 */
	public static boolean getBoolean(String bstr) {
		if (bstr == null || bstr.equals(""))
			return false;
		else if (bstr.equalsIgnoreCase("Y") || bstr.equalsIgnoreCase("1")
				|| bstr.equalsIgnoreCase("��"))
			return true;

		return false;
	}

	/**
	 * �����۾ɷ����۾ɶ� ����:������ ����:Apr 9, 2009
	 * 
	 * @param depfaction
	 *            �۾ɷ�
	 * @param scraprate
	 *            ��ֵ��
	 * @param planyears
	 *            Ԥ��ʹ������
	 * @param usedyear
	 *            ��ʹ������
	 * @param nowcost
	 *            ��ǰֵ
	 * @param totalcost
	 *            �ʲ�ԭֵ
	 * @return
	 */
	public static double countDepcost(String depfaction, Double scraprate,long planyears, long usedyear, Double nowcost, double totalcost) {
		double rate = 0.0;
		double depcost = 0.0;
		double nowCost = nowcost;
		if (planyears != 0 && depfaction != null) {
			if (depfaction.equals("ƽ�����޷�")) {
				rate = countDeprate(depfaction, scraprate, planyears, usedyear);
				depcost = rate * usedyear * totalcost;
			}
			if (depfaction.equals("˫�����ݼ���")) {
				rate = countDeprate(depfaction, scraprate, planyears, usedyear);
				for (int j = 0; j < usedyear; j++) {
					depcost += rate * nowCost;
					nowCost = totalcost - depcost;
				}
			}
			if (depfaction.equals("�����ܺͷ�")) {
				for (int i = 1; i < usedyear + 1; i++) {
					rate = countDeprate(depfaction, scraprate, planyears, i);
					depcost += totalcost * (1 - scraprate) * rate;
				}
			}
		} 
		return doubleTodouble(depcost);
	}

	/**
	 * �����۾��� ����:������ ����:Mar 31, 2009
	 * 
	 * @param depfaction
	 *            �۾ɷ�
	 * @param scraprate
	 *            ��ֵ��
	 * @param planyears
	 *            Ԥ��ʹ������
	 * @param usedyear
	 *            ��ʹ������
	 * @return
	 */
	public static double countDeprate(String depfaction, Double scraprate,
			long planyears, long usedyear) {
		double deprate = 0.0; // �۾���
		if (planyears != 0 && depfaction != null) {
			if (depfaction.equals("ƽ�����޷�")) {
				if (scraprate != null)
					deprate = (1 - scraprate) / planyears;
				else
					deprate = 1 / planyears;
			}
			if (depfaction.equals("�����ܺͷ�")) {
				double a = (planyears - usedyear) + 1;
				double b = planyears + 1;
				double c = (planyears * b)/2;
				deprate = a / c;
			}
			if (depfaction.equals("˫�����ݼ���")) {
				deprate = 2.0 / planyears;
			}
		}
		return doubleTodouble(deprate);
	}
	/**
	 * double����ת��
	 * ����:������
	 * ����:Apr 9, 2009
	 * @param number
	 * @return
	 */
	public static Double doubleTodouble(double number){
		NumberFormat format = new DecimalFormat("#0.0000");
		return new Double(format.format(number));
	}
}
