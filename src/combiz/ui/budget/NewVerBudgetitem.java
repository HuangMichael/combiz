package combiz.ui.budget;

import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;

import combiz.domain.budget.Budgetitem;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.TMainWindow;

public class NewVerBudgetitem extends CommonDialog
{
	public void savenew() throws Exception
	{
		Budgetitem budgetitem = (Budgetitem) this.getMainObject();
		String version = budgetitem.getVersion();
		int verCount = this.getMainSrv().getRowCountByWhere(budgetitem, "version='"+version+"'");
		if(verCount>0)
		{
			Messagebox.show("�°�["+version+"]�����ݿ����Ѿ����ڣ�����������汾��ţ�");
			return;
		}
		
		this.save();
		
		TMainWindow tmWnd = (TMainWindow) this.getOwnerWnd();
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) tmWnd.getFellow("budgetItemVersion");
		Listitem listitem = new Listitem(version);
		listitem.setValue(version);
		budgetItemVersion.appendChild(listitem);
		budgetItemVersion.setSelectedItem(listitem);
		budgetItemVersion.onSelect();
		
		this.onClose();
	}
}
