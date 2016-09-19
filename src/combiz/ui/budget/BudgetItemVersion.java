package combiz.ui.budget;

import combiz.system.IBOBaseDao;
import combiz.system.IBSServer;
import combiz.system.ui.LookupWindow;
import combiz.system.ui.RecWindow;
import combiz.ui.common.lookup.FindBudgetItemTree;

import java.rmi.RemoteException;
import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Listbox;
import com.inbasis.zul.Listitem;


public class BudgetItemVersion extends Listbox
{
	public void onCreate() 
	throws Exception
	{
		this.setId("budgetItemVersion");
		this.setMold("select");
	}
	
	/**
	 * ��ʼ�������汾ѡ�����ݣ���MainTree���createroot�������á�
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void createList() throws Exception
	{
		this.getChildren().clear();
		
		IBOBaseDao basedao = IBSServer.getIBSServer().getBaseDao();
		List verList = basedao.selectListByHql("select distinct t.version from Budgetitem t order by t.version desc");
		for(int i=0;i<verList.size();i++)
		{
			String version = (String) verList.get(i);
			Listitem listitem = new Listitem(version);
			listitem.setValue(version);
			this.appendChild(listitem);
		}
	}
	
	/**
	 * �л���ͬ�汾
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void onSelect()
	throws Exception
	{
		Listitem listitem = this.getSelectedItem();
		String version = (String) listitem.getValue();
		BudgetItemTree maintree = (BudgetItemTree) this.getFellow("mainTree");
		if(maintree!=null) //�����������
			maintree.createTree(version);
		else 
		{
			FindBudgetItemTree findBudgetItemTree = (FindBudgetItemTree) this.getFellow("lookupTree");
			if(findBudgetItemTree!=null) //��LOOKUPҳ����
			{
				findBudgetItemTree.createTree();
				LookupWindow lookupWnd = (LookupWindow) this.getFellow("lookupWnd");
				if(lookupWnd!=null)
				{
					lookupWnd.setQueryString("enabled='��'");
					lookupWnd.getResultList(true);
				}
			}
			else  //��Ԥ�������еĵ���������
			{
				SelectBudgetItemTree selectBudgetItemTree = (SelectBudgetItemTree) this.getFellow("selectBudgetItemTree");
				selectBudgetItemTree.createTree();
			}
		}
		
			
	}
}
