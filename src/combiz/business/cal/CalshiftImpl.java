package combiz.business.cal;

import combiz.domain.cal.Calshift;
import combiz.domain.cal.Calshiftday;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class CalshiftImpl extends IBOBaseImpl implements CalshiftSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Calshift))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "calshiftday");
		this.deleteAllChild(obj, "calworkperiod");
	}

	@Override
	public void save(Object obj) throws Exception {
		if (!(obj instanceof Calshift))
			throw new Exception("ִ�б��涯��ʱ����������" + obj + "���ݲ���ȷ��");

		String weekday[] = { "����һ", "���ڶ�", "������", "������", "������", "������", "������" };

		Calshift calshift = (Calshift) obj;
		if (calshift.getId() == null) {
			// ��ɾ����������
			this.deleteAllChild(calshift, "calshiftday");
			// ������Ĭ������
			int j;
			for (j = 0; j < weekday.length; j++) {
				if (weekday[j].equals(calshift.getStartday()))
					break;
			}

			Calshiftday calshiftday;
			for (int i = 0; i < calshift.getDaymold(); i++) {
				calshiftday = new Calshiftday();
				calshiftday.setShiftnum(calshift.getShiftnum());
				calshiftday.setDayseq(new Long(i + 1));
				calshiftday.setWorkday(weekday[j]);
				this.getBaseDao().saveObject(calshiftday);

				if (j >= 6)
					j = 0;
				else
					j = j + 1;
			}
		}

		super.save(calshift);
	}

}
