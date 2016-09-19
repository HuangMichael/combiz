package combiz.business.workorder;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wptool;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class WptoolImpl extends IBOBaseImpl implements WptoolSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Wptool))
			throw new Exception("要删除的对象传递不正确！");
		if (this.getRecWnd() != null) {
			if (this.getRecWnd().getOwnerWnd() != null) {
				if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Workorder) {
					Wptool wptool =(Wptool)obj;
					Double line_db = 0d;
					Workorder wo = (Workorder) this.getRecWnd().getOwnerWnd().getMainObject();
					if (wo != null) {
							line_db =  wptool.getToolqty()*wptool.getToolhrs()*wptool.getRate();
							wo.setEsttoolcost((wo.getEsttoolcost()-line_db));
					}
						this.getBaseDao().updateObject(wo);
					}
				}
			}
		// 删除本身
		super.delete(obj);
		
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "");
	}
	/**
	 * 计算工具费用预算
	 * 陈明锐
	 * 2009-02-19
	 */
	@Override
	public void save(Object arg0) throws Exception {
		if (arg0 instanceof Wptool) {
			Wptool wptool = (Wptool) arg0;
			
			// 先判断父级窗口的主对象是不是工单,因为在物资领用的时候也会用到该保存方法.
			// 在工单中添加工具进行的操作
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Workorder) {
						String wonum = wptool.getWonum();
						Double line_db = 0d;
						Workorder wo = null;
						// 从界面上获取，如果界面上有wo对象，那就不要从数据库中取了，避免重复对象
						if (this.getRecWnd() != null) {
							if (this.getRecWnd().getOwnerWnd() != null) {
								wo = (Workorder) this.getRecWnd().getOwnerWnd()
										.getMainObject();
							}
						}
						// 如果不是界面上调用的保存方法，那么获取wo肯定为null，那就从数据库中直接取。
						if (wo == null) {
							List wolist = this.getBaseDao().findWithQuery(
									Workorder.class, "wonum = '" + wonum + "'");
							if (wolist.size() > 0)
								wo = (Workorder) wolist.get(0);
						}
						if (wo != null) {
							if (wptool.getId() == null) // 是新建记录
							{
								line_db = (Double) this.getBaseDao().selectSumByHql("select sum(t.linecost) from Wptool t where t.wonum = '"+wonum+"'");
							} else {
								line_db = (Double)this.getBaseDao().selectSumByHql("select sum(t.linecost) from Wptool t where t.wonum = '"+wonum+"' and t.id != "+wptool.getId());
							}
							if (line_db == null)
								line_db = 0d;
							Double linecost =  wptool.getLinecost();
							if (linecost == null)
								linecost = 0d;

							// 先删除以前的数据
							wo.setEsttoolcost(linecost + line_db);

							this.getBaseDao().updateObject(wo);
						}

					}

				}
				// 保存自己
				super.save(wptool);
			}
		}
	}

	// ///////////////////业务方法//////////////////////////////////
}
