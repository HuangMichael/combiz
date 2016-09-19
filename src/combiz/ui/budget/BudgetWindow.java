package combiz.ui.budget;

import combiz.business.budget.BudgetSrv;
import combiz.domain.budget.Budget;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;

import com.inbasis.zul.Messagebox;

public class BudgetWindow extends MainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public BudgetWindow()
	{
		super();
	}

	
	/**
	 * ��Ӽ�¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Budget newobj = new Budget();
		//����������Ӷ���ĳ�ʼ��ֵ
		newobj.setBudcycle("���");
		newobj.setEnabled("��");
		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setBuddept(this.getLaborInfo().getDeptnum());
		newobj.setChangedate(new Date());
		newobj.setStatus("����δ����");
		newobj.setStatusdate(new Date());
		
		mainObject = newobj;
		return true;
	}
	
	
	
	/**
	 * ��ʼ������
	 * 
	 * brianhong  2008-11-26
	 * @throws Exception
	 */
	@Override
	public void initData() throws Exception
	{
		Budget budget = (Budget) this.getMainObject();
		ListWindow listWnd = (ListWindow) this.getFellow("budgetline");
		if(Util.getBoolean(budget.getEnabled()))
		{
			listWnd.setReadonlyList(true);
			this.setReadonlyObject(true);
		}
		
		super.initData();
	}
	
	
	/**
	 * ���û��߽���Ԥ��
	 * brianhong  2008-11-26
	 * @throws Exception
	 */
	public void enablebd()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٵ���ò˵�������");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("ִ�иò���֮ǰ�����ȱ������ݣ�");
			return;
		}
		
		Budget budget = (Budget) this.getMainObject();
		if(Util.getBoolean(budget.getEnabled()))
		{
			int rtn = Messagebox.show("ȷ����������Ԥ������","ȷ�ϣ�",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
			if(rtn==Messagebox.YES)
			{
				((BudgetSrv)this.getMainSrv()).enablebd(budget, false);
			}
		}
		else
		{
			int rtn = Messagebox.show("ȷ����������Ԥ������","ȷ�ϣ�",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
			if(rtn==Messagebox.YES)
			{
				((BudgetSrv)this.getMainSrv()).enablebd(budget, true);
			}
		}
		
		this.refreshData();
	}


	/**
	 * ����Ԥ����Ŀ
	 * brianhong  2008-11-21
	 * @throws Exception
	 */
	public void copyitem()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٵ���ò˵�������");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("ִ�иò���֮ǰ�����ȱ������ݣ�");
			return;
		}
		this.popupDialog(mainObject, "/budget/selectbudgetitem.xul");
	}
	
	
	/**
	 * ɾ������Ԥ����
	 * brianhong  2008-11-24
	 */
	public void deleteall()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٵ���ò˵�������");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("ִ�иò���֮ǰ�����ȱ������ݣ�");
			return;
		}
		
		int rtn = Messagebox.show("ȷ��ɾ����Ԥ��������������Ԥ������","ɾ��ȷ�ϣ�",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
		if(rtn==Messagebox.YES)
		{
			((BudgetSrv)this.getMainSrv()).deleteAllLine(this.getMainObject());
		}
			
		this.refreshData();
	}


	@Override
	public void save() throws Exception
	{
		super.save();
	}
	
	
	
	
}
