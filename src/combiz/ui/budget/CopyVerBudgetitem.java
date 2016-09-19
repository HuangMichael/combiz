package combiz.ui.budget;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.business.budget.BudgetitemSrv;
import combiz.domain.budget.Budgetitem;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.MainWindow;
import combiz.system.ui.TMainWindow;

public class CopyVerBudgetitem extends CommonDialog
{
	public void copynew() throws Exception
	{
		Budgetitem toBd = (Budgetitem) this.getMainObject();
		String version = toBd.getVersion();
		int verCount = this.getMainSrv().getRowCountByWhere(toBd, "version='"+version+"'");
		if(verCount>0)
		{
			Messagebox.show("新版["+version+"]在数据库中已经存在，请重新输入版本编号！");
			return;
		}
		
		MainWindow tmWnd = (MainWindow) this.getOwnerWnd();
		//执行复制动作
		Budgetitem fromBd = (Budgetitem) tmWnd.getMainObject();
		((BudgetitemSrv)tmWnd.getMainSrv()).copyVersion(fromBd,toBd);
		
		//刷新主界面
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) tmWnd.getFellow("budgetItemVersion");
		Listitem listitem = new Listitem(version);
		listitem.setValue(version);
		budgetItemVersion.appendChild(listitem);
		budgetItemVersion.setSelectedItem(listitem);
		budgetItemVersion.onSelect();
		
		this.onClose();
	}
}
