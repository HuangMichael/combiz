package combiz.ui.budget;

import java.util.HashMap;
import java.util.List;

import combiz.business.budget.BudgetitemSrv;
import combiz.domain.budget.Budgetitem;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class BudgetitemWindow extends TMainWindow
implements InfWindow
{
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public BudgetitemWindow()
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
		Budgetitem newobj = new Budgetitem();
		String version="";
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) this.getFellow("budgetItemVersion");
		if(budgetItemVersion.getSelectedItem()!=null)
			version = (String) budgetItemVersion.getSelectedItem().getValue();
		if(Util.isNull(version))
		{
			Messagebox.show("��ѡ��һ��Ԥ��汾��");
			return false;
		}
		//����������Ӷ���ĳ�ʼ��ֵ
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Budgetitem parent = (Budgetitem) ti.getValue();
			newobj.setParent(parent.getBuditem());
			
			Long maxorder = 1L;
			List sameList = this.getMainSrv().findWithQuery("parent='"+parent.getBuditem()+"' and version='"+parent.getVersion()+"'", "buditem desc");
			if(sameList.size()>0)
			{
				Budgetitem same = (Budgetitem) sameList.get(0);
				String bitem = same.getBuditem();
				String bitemend = bitem.substring(bitem.lastIndexOf(".") + 1);
				if(bitemend!=null)
					maxorder = Long.parseLong(bitemend) + 1;
			}
			newobj.setVersion(parent.getVersion());
			newobj.setBuditem(parent.getBuditem() + "." + maxorder);
			newobj.setOrderby(maxorder);
			newobj.setMeaunit(parent.getMeaunit());
			newobj.setBudtype(parent.getBudtype());
			newobj.setBudclass(parent.getBudclass());
			newobj.setChildclass(parent.getChildclass());
			newobj.setBudperiod(parent.getBudperiod());
		}
		else
		{
			Long maxorder = 1L;
			List sameList = this.getMainSrv().findWithQuery("parent is null and version='"+version+"'", "buditem desc");
			if(sameList.size()>0)
			{
				Budgetitem same = (Budgetitem) sameList.get(0);
				String bitem = same.getBuditem();
				String bitemend = bitem.substring(bitem.lastIndexOf(".") + 1);
				if(bitemend!=null)
					maxorder = Long.parseLong(bitemend) + 1;
			}

			newobj.setVersion(version);
			newobj.setBuditem(String.valueOf(maxorder));
			newobj.setOrderby(maxorder);
			newobj.setMeaunit("Ԫ");
			newobj.setBudtype("��˾����");
			newobj.setBudperiod("���");
			newobj.setChildclass("֧��");
		}

		
		newobj.setHaschild("��");
		newobj.setEnabled("��");
		
		mainObject = newobj;
		return true;
	}
	
	/**
	 * 
	 * brianhong  2008-11-24
	 * @throws Exception 
	 */
	public void deleteall() throws Exception
	{
		int rtn = Messagebox.show("ȷ��ɾ���ð汾�����м�¼��","ɾ��ȷ�ϣ�",Messagebox.YES | Messagebox.NO ,Messagebox.QUESTION);
		if(rtn==Messagebox.YES)
		{
			Budgetitem budgetitem = (Budgetitem) this.getMainObject();
			((BudgetitemSrv)this.getMainSrv()).deleteall(budgetitem);
			this.getMainTree().createRoot();
		}
	}
	
	/**
	 * ���Ƶ�ǰ�汾
	 * brianhong  2008-11-23
	 * @throws Exception
	 */
	public void copyitem()
	throws Exception
	{
		Budgetitem mainobj = (Budgetitem) mainObject;
		if(mainobj!=null && mainobj.getId()!=null)
		{
			Budgetitem newobj = new Budgetitem();
			newobj.setHaschild("��");
			newobj.setEnabled("��");
			this.popupDialog(newobj, "/budget/copyverpopup.xul");
		}
		else
			Messagebox.show("û��ѡ��Ԥ����Ŀ��");
		
	}
	
	/**
	 * �����°汾
	 * brianhong  2008-11-23
	 * @throws Exception 
	 */
	public void newver() 
	throws Exception
	{
		Budgetitem mainobj = (Budgetitem) mainObject;
		Budgetitem newobj = new Budgetitem();
		newobj.setOrderby(1L);
		newobj.setMeaunit("Ԫ");
		newobj.setBudtype("��˾����");
		newobj.setBudperiod("���");
		newobj.setChildclass("֧��");
		newobj.setHaschild("��");
		newobj.setEnabled("��");
		this.popupDialog(newobj, "/budget/newverpopup.xul");
	}
}
