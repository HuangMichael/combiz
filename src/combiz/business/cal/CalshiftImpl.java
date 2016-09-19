package combiz.business.cal;

import combiz.domain.cal.Calshift;
import combiz.domain.cal.Calshiftday;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class CalshiftImpl extends IBOBaseImpl implements CalshiftSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Calshift))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		this.deleteAllChild(obj, "calshiftday");
		this.deleteAllChild(obj, "calworkperiod");
	}

	@Override
	public void save(Object obj) throws Exception {
		if (!(obj instanceof Calshift))
			throw new Exception("执行保存动作时，参数对象" + obj + "传递不正确！");

		String weekday[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };

		Calshift calshift = (Calshift) obj;
		if (calshift.getId() == null) {
			// 先删除所有数据
			this.deleteAllChild(calshift, "calshiftday");
			// 插入新默认数据
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
