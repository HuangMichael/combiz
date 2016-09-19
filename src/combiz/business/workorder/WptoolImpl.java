package combiz.business.workorder;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wptool;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class WptoolImpl extends IBOBaseImpl implements WptoolSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Wptool))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
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
		// ɾ������
		super.delete(obj);
		
		// ɾ���������󣭸��෽��
		// this.deleteAllChild(obj, "");
	}
	/**
	 * ���㹤�߷���Ԥ��
	 * ������
	 * 2009-02-19
	 */
	@Override
	public void save(Object arg0) throws Exception {
		if (arg0 instanceof Wptool) {
			Wptool wptool = (Wptool) arg0;
			
			// ���жϸ������ڵ��������ǲ��ǹ���,��Ϊ���������õ�ʱ��Ҳ���õ��ñ��淽��.
			// �ڹ�������ӹ��߽��еĲ���
			if (this.getRecWnd() != null) {
				if (this.getRecWnd().getOwnerWnd() != null) {
					if (this.getRecWnd().getOwnerWnd().getMainObject() instanceof Workorder) {
						String wonum = wptool.getWonum();
						Double line_db = 0d;
						Workorder wo = null;
						// �ӽ����ϻ�ȡ�������������wo�����ǾͲ�Ҫ�����ݿ���ȡ�ˣ������ظ�����
						if (this.getRecWnd() != null) {
							if (this.getRecWnd().getOwnerWnd() != null) {
								wo = (Workorder) this.getRecWnd().getOwnerWnd()
										.getMainObject();
							}
						}
						// ������ǽ����ϵ��õı��淽������ô��ȡwo�϶�Ϊnull���Ǿʹ����ݿ���ֱ��ȡ��
						if (wo == null) {
							List wolist = this.getBaseDao().findWithQuery(
									Workorder.class, "wonum = '" + wonum + "'");
							if (wolist.size() > 0)
								wo = (Workorder) wolist.get(0);
						}
						if (wo != null) {
							if (wptool.getId() == null) // ���½���¼
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

							// ��ɾ����ǰ������
							wo.setEsttoolcost(linecost + line_db);

							this.getBaseDao().updateObject(wo);
						}

					}

				}
				// �����Լ�
				super.save(wptool);
			}
		}
	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////
}
