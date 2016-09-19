package combiz.ui.contract;

import java.util.Date;

import com.inbasis.zul.Messagebox;

import combiz.business.contract.ContractSrv;
import combiz.domain.contract.Contract;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

public class ContractWindow extends MainWindow implements InfWindow {
	public ContractWindow() {
		super();
	}

	public boolean addNew() throws Exception {
		Contract cont = new Contract();
		cont.setCntnum(this.genAutokey("cntnum"));
		cont.setCreateby(this.getLaborInfo().getLaborname());
		cont.setCreatedate(new Date());
		cont.setSigdate(new Date());
		cont.setStatus("����δ����");
		cont.setStatusdate(new Date());
		mainObject = cont;
		return true;
	}

	/**
	 * ������createpo ���ߣ���� ���ܣ�ͨ����ͬ��ֱ�����ɲɹ���ͬ ���ڣ�Apr 9, 2009 2:44:45 PM
	 * 
	 * @throws Exception
	 */
	public void createpo() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ������ɲɹ�����������");
			return;
		}
		Contract contranct = (Contract) this.getMainObject();
		String status = contranct.getStatus();
		if (!(status.equals("����׼"))) {
			Messagebox.show("�ú�ͬ��δ����׼������ִ�����ɺ�ͬ�ɹ���");
			return;
		}
		int count = this.getMainSrv().getBaseDao().selectCountByHql(
				"select count(*) from Contline c where c.cntnum='"
						+ contranct.getCntnum() + "'");
		if (count < 0) {
			Messagebox.show("�ú�ͬû�вɹ��У��������ɲɹ����������ʵ��");
			return;
		}
		String ponum = null;
		
		int rtn = Messagebox.show("�Ƿ�ȷҪͨ����ͬ���ɲɹ�������", "����ȷ�ϣ�",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			ponum = ((ContractSrv)this.getMainSrv()).createpo(contranct);
		}
		
		
		this.refreshData();
		Messagebox.show("�ɹ�����{"+ponum+"}�����ɹ�!");

	}
}
