package combiz.ui.pr;

import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class ReqplanWindow extends MainWindow implements InfWindow {

	public ReqplanWindow() {
		super();
	}

	/**
	 * 
	 * @TODO 新增一条记录
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午01:17:05
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Pr pr = new Pr();

		pr.setPrnum(this.genAutokey("prnum"));
		pr.setStatus("流程未启动");
		pr.setStatusdate(new Date());
		pr.setRequestdate(new Date());
		//pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setRequestedby(this.getLaborInfo().getLabornum());
		pr.setRequestdept(this.getLaborInfo().getDeptnum());
		pr.setExchangerate(0d);
		pr.setExchangedate(new Date());
		//pr.setChangedate(new Date());
		pr.setPrtype("需求计划");
		mainObject = pr;

		return true;
	}


	/* 
	 * 功能：设置之窗口是否为只读
	 * 作者：李建红
	 * 日期：2008-11-10下午04:34:13
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr) this.getMainObject();
		ListWindow prlineWnd = (ListWindow) this.getFellow("prline");
		if (pr.getStatus()!=null && pr.getStatus().equals("已批准")) {
			prlineWnd.setReadonlyList(true);
		}else{
			prlineWnd.setReadonlyList(false);
		}
		
		super.initData();
	}
	/**
	 * 陈明锐 根据设备选择备件 2009-02-23
	 * 
	 * @throws Exception
	 */
	public void findeqsparepart() throws Exception {
		//Pr pr = (Pr) this.getMainObject();
		//if (!pr.getStatus().equals("已批准")) {
			Workorder workorder = new Workorder();
			CommonDialog comdialog = (CommonDialog) this.popupDialog(workorder,
					"/common/prfindpartbyequip.xul");
			if (comdialog == null)
				return;
			if (comdialog.isConfirm())
				return;
			this.refreshData();
//		} else {
//			Messagebox.show("流程已批准无法添加采购单行");
//		}

	}
	/**
	 * 根据物资编码添加BOM备件信息
	 * 
	 * 作者:陈明锐 日期:Mar 2, 2009
	 * 
	 * @throws Exception
	 */
	public void findeqbom() throws Exception {
		// Workorder workorder = (Workorder) this.getOwnerWnd().getMainObject();
		// if (!workorder.getStatus().equals("已批准")) {
		Wpmaterial wpmaterial = new Wpmaterial();
		CommonDialog comdialog = (CommonDialog) this.popupDialog(wpmaterial,
				"/common/prfindbombyitem.xul");
		if (comdialog == null)
			return;
		if (comdialog.isConfirm())
			return;
		ListWindow listWnd = (ListWindow) this.getFellow("prline");
		listWnd.refreshData();// 刷新部分
		// }else{
		// Messagebox.show("流程已批准无法添加备件");
		// }
	}
	
	/* workflow
	 * 发送工作流的时候，校验仓库，数量，单价信息
	 * 李建红
	 */
	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr)this.getMainObject();
		String prnum = pr.getPrnum();
		boolean flasg = false;
		List polinelist = this.getMainSrv().getBaseDao().findWithQuery(Prline.class, "prnum = '"+prnum+"'");
		if (polinelist.size()>0) {
			for (int i=0;i<polinelist.size();i++) {
				Prline prline = (Prline) polinelist.get(i);
				String warehouse = prline.getWarehouse();
				String itemnum = prline.getItemnum();
				Double qty = prline.getOrderqty();
				if (warehouse == null || itemnum == null || qty == null || qty<=0 ){
					flasg = true;	
				}
			}
			if (flasg) {
				Messagebox.show("订单行物资编码，订购数量，或仓库信息不完善，不能发送工作流，请核实！");
				return;
			}
		}else{
			Messagebox.show("该订单未任何采购单行，不能发送工作流，请核实！");
			return;
		}
		super.workflow();
	}

}
