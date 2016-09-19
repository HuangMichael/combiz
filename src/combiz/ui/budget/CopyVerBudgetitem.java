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
			Messagebox.show("�°�["+version+"]�����ݿ����Ѿ����ڣ�����������汾��ţ�");
			return;
		}
		
		MainWindow tmWnd = (MainWindow) this.getOwnerWnd();
		//ִ�и��ƶ���
		Budgetitem fromBd = (Budgetitem) tmWnd.getMainObject();
		((BudgetitemSrv)tmWnd.getMainSrv()).copyVersion(fromBd,toBd);
		
		//ˢ��������
		BudgetItemVersion budgetItemVersion = (BudgetItemVersion) tmWnd.getFellow("budgetItemVersion");
		Listitem listitem = new Listitem(version);
		listitem.setValue(version);
		budgetItemVersion.appendChild(listitem);
		budgetItemVersion.setSelectedItem(listitem);
		budgetItemVersion.onSelect();
		
		this.onClose();
	}
}
