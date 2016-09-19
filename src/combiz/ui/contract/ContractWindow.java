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
		cont.setStatus("流程未启动");
		cont.setStatusdate(new Date());
		mainObject = cont;
		return true;
	}

	/**
	 * 方法：createpo 作者：李建红 功能：通过合同，直接生成采购合同 日期：Apr 9, 2009 2:44:45 PM
	 * 
	 * @throws Exception
	 */
	public void createpo() throws Exception {
		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("记录没有保存，请先保存后再进行该操作！");
			return;
		}
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行生成采购订单操作！");
			return;
		}
		Contract contranct = (Contract) this.getMainObject();
		String status = contranct.getStatus();
		if (!(status.equals("已批准"))) {
			Messagebox.show("该合同还未被批准，不能执行生成合同采购！");
			return;
		}
		int count = this.getMainSrv().getBaseDao().selectCountByHql(
				"select count(*) from Contline c where c.cntnum='"
						+ contranct.getCntnum() + "'");
		if (count < 0) {
			Messagebox.show("该合同没有采购行，无需生成采购订单，请核实！");
			return;
		}
		String ponum = null;
		
		int rtn = Messagebox.show("是否确要通过合同生成采购订单吗？", "生成确认！",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			ponum = ((ContractSrv)this.getMainSrv()).createpo(contranct);
		}
		
		
		this.refreshData();
		Messagebox.show("采购订单{"+ponum+"}创建成功!");

	}
}
