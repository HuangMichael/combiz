package combiz.business.cal;

import combiz.domain.cal.Calendar;
import combiz.domain.cal.Calshift;
import combiz.domain.cal.Calshiftday;
import combiz.domain.cal.Calworkperiod;
import combiz.system.IBOBaseImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class CalshiftdayImpl extends IBOBaseImpl implements CalshiftdaySrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Calshiftday))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}

	/**
	 * ������ɹ����ų̱� brianhong 2007-10-29
	 * 
	 * @throws Exception
	 */
	public void calwp(Calendar cal, Calshift calshift) throws Exception {
		// ���������ų�����
		String weekday[] = { "����һ", "���ڶ�", "������", "������", "������", "������", "������" };
		int weekdayen[] = { java.util.Calendar.MONDAY,
				java.util.Calendar.TUESDAY, java.util.Calendar.WEDNESDAY,
				java.util.Calendar.THURSDAY, java.util.Calendar.FRIDAY,
				java.util.Calendar.SATURDAY, java.util.Calendar.SUNDAY };
		this.setOrderBy("dayseq");
		List shiftdaylist = this.findWithQuery("shiftnum='"
				+ calshift.getShiftnum() + "'");
		Calworkperiod calworkperiod;
		// ���������Ŀ�ʼʱ��ͽ���ʱ������JAVA����ʱ�����
		java.util.Calendar startdate = java.util.Calendar.getInstance();
		startdate.setTime(cal.getStartdate());
		java.util.Calendar enddate = java.util.Calendar.getInstance();
		enddate.setTime(cal.getEnddate());
		if (enddate.before(startdate))
			throw new Exception("���������ʱ���д��󣬿�ʼʱ�䲻���ڽ���ʱ���");

		// ��������һΪһ�����ڵĵ�һ��
		startdate.setFirstDayOfWeek(java.util.Calendar.MONDAY);
		enddate.setFirstDayOfWeek(java.util.Calendar.MONDAY);
		// ��ȡ��ʼ�����ڣ������������ʼ���Ӷ���������ʼ�������ȡ���µķ��Ͽ�ʼ���ڵ�ʱ�䣬������startdate
		int j;
		for (j = 0; j < weekday.length; j++) {
			if (weekday[j].equals(calshift.getStartday()))
				break;
		}
		// ���������µĿ�ʼ�������������죬����С��������ʼʱ�䣬Ҳ���ܴ���
		startdate.set(java.util.Calendar.DAY_OF_WEEK, weekdayen[j]);
		// System.out.println("���������ڣ�"+weekdayen[j]+" ����: "
		// +startdate.getTime());

		// ��ɾ�����������ų�
		this.getBaseDao().deleteBatch(
				this.getBaseDao().findWithQuery(
						Calworkperiod.class,
						"calnum='" + cal.getCalnum() + "' and shiftnum='"
								+ calshift.getShiftnum() + "'"));

		// ��ȡ�����ܵ�����
		long quot = 0;
		quot = cal.getEnddate().getTime() - cal.getStartdate().getTime();
		int days = (int) (quot / 1000 / 60 / 60 / 24);
		int k = 0;
		for (long i = 0; i < days; i++) {
			if (startdate.before(cal.getStartdate())) {
				// �ӿ�ʼ�ռ��㣬ѭ����һ��
				startdate.add(java.util.Calendar.DATE, 1);
				continue;
			}
			if (startdate.after(cal.getEnddate()))
				break;

			// ������ģʽ��ѭ��ȡ��calshiftday
			Calshiftday calshiftday = (Calshiftday) shiftdaylist.get(k);
			k = k + 1;
			if (k >= calshift.getDaymold())
				k = 0;

			// ������ʼ�ͽ���ʱ��ֻҪ��һ��Ϊ�գ��ǾͲ����ɼ�¼
			if (calshiftday.getStarttime() == null
					|| calshiftday.getEndtime() == null) {
				// �ӿ�ʼ�ռ��㣬ѭ����һ��
				startdate.add(java.util.Calendar.DATE, 1);
				continue;
			}

			// ��ȡĿǰ�Ĺ����գ��������յ����ڼ��뵽calshiftday���տ�ʼ�ͽ���ʱ���У��γɵ��յĿ�ʼ����ʱ��ͽ���ʱ��
			java.util.Calendar workstartdate = java.util.Calendar.getInstance();
			java.util.Calendar workenddate = java.util.Calendar.getInstance();
			// ��������ǰ�����ڣ�����ʱ�仹���ÿ�ʼ�ͽ���ʱ�����
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String todaystr = sdf.format(new Date());
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String starttimes = calshiftday.getStarttime();
			String endtimes = calshiftday.getEndtime();
			//ȡ����ģʽ�����ﶨ��Ŀ�ʼʱ��Сʱ����
			String[] s = null; 
			s=starttimes.split(":"); 
			String begstarttimes = s[0];
			//ȡ����ģʽ�����ﶨ��Ľ���ʱ��Сʱ����
			String[] s1 = null; 
			s1=endtimes.split(":");
			String begendtimes = s1[0];
			//����ʼʱ��ͽ���ʱ��Сʱ��ת��Ϊdouble�ͱȽϴ�С��
			Double starttimed = Double.parseDouble(begstarttimes);
			Double endtimed = Double.parseDouble(begendtimes);
			
			Date starttime = sdf.parse(todaystr + " "
					+ calshiftday.getStarttime());
			Date endtime = sdf.parse(todaystr + " " + calshiftday.getEndtime());
			
			
			// ���ÿ�ʼʱ��ͽ���ʱ�䣬������ѭ���������������ڴ����������õĵ�ǰ���ڣ�ʱ��ֵ���ֲ���
			workstartdate.setTime(starttime);
			workenddate.setTime(endtime);
			
			workstartdate.set(startdate.get(java.util.Calendar.YEAR), startdate
					.get(java.util.Calendar.MONTH), startdate
					.get(java.util.Calendar.DATE));
			//�жϽ���ʱ���Ƿ����ڿ�ʼʱ�䣬�������ʱ�����ڿ�ʼʱ�䣬˵���ǵڶ���Ŀ�ʼʱ�䡣
			if(endtimed<starttimed)
			{
				
				workenddate.set(startdate.get(java.util.Calendar.YEAR), startdate
						.get(java.util.Calendar.MONTH), startdate
						.get(java.util.Calendar.DATE)+1);
			}
			else
			{
				workenddate.set(startdate.get(java.util.Calendar.YEAR), startdate
						.get(java.util.Calendar.MONTH), startdate
						.get(java.util.Calendar.DATE));
			}
			
			// ���ö���ֵ
			calworkperiod = new Calworkperiod();
			calworkperiod.setCalnum(cal.getCalnum());
			calworkperiod.setShiftnum(calshift.getShiftnum());
			calworkperiod.setWorkdate(startdate.getTime());
			calworkperiod.setStarttime(workstartdate.getTime());
			calworkperiod.setEndtime(workenddate.getTime());
			calworkperiod.setWorkhours(calshiftday.getWorkhours());

			this.getBaseDao().saveObject(calworkperiod);

			// �ӿ�ʼ�ռ��㣬ѭ����һ��
			startdate.add(java.util.Calendar.DATE, 1);
		}
	}
}
