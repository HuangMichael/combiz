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
	 * 初始化创建版本选择数据，由MainTree类的createroot方法调用。
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
	 * 切换不同版本
	 * brianhong  2008-11-24
	 * @throws Exception
	 */
	public void onSelect()
	throws Exception
	{
		Listitem listitem = this.getSelectedItem();
		String version = (String) listitem.getValue();
		BudgetItemTree maintree = (BudgetItemTree) this.getFellow("mainTree");
		if(maintree!=null) //在主界面的树
			maintree.createTree(version);
		else 
		{
			FindBudgetItemTree findBudgetItemTree = (FindBudgetItemTree) this.getFellow("lookupTree");
			if(findBudgetItemTree!=null) //在LOOKUP页面中
			{
				findBudgetItemTree.createTree();
				LookupWindow lookupWnd = (LookupWindow) this.getFellow("lookupWnd");
				if(lookupWnd!=null)
				{
					lookupWnd.setQueryString("enabled='是'");
					lookupWnd.getResultList(true);
				}
			}
			else  //在预算审批中的弹出窗口中
			{
				SelectBudgetItemTree selectBudgetItemTree = (SelectBudgetItemTree) this.getFellow("selectBudgetItemTree");
				selectBudgetItemTree.createTree();
			}
		}
		
			
	}
}
