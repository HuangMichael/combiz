package combiz.ui.pr;

import combiz.business.pr.PrSrv;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class PrWindow extends MainWindow implements InfWindow {

	public PrWindow() {
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
		pr.setChangeby(this.getLaborInfo().getLabornum());
		pr.setRequestedby(this.getLaborInfo().getLabornum());
		pr.setRequestdept(this.getLaborInfo().getDeptnum());
		pr.setPrnumtype("手动填写");
		//pr.setBudnum();
		pr.setChangedate(new Date());
		pr.setPrtype("采购申请");
		mainObject = pr;

		return true;
	}

	/***************************************************************************
	 * 
	 * @TODO 自动生成询价单、询价单行。对应数据来源于采购申请、采购申请行
	 * @throws Exception
	 * @蒋祖兵 2007-8-15 上午11:28:05
	 */
	public void createrfq() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行创建询价单操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("在对采购申请操作前，请先保存数据！");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (pr.getStatus().equals("已批准")) {
			String rfqnum = ((PrSrv) this.getMainSrv()).ceaterfq(this
					.getMainObject());
			this.setMainData();
			Messagebox.show("成功生成询价单和询价单行-对应询价单:" + rfqnum);
		} else
			Messagebox.show("采购申请还没有批准，不能创建询价单");

	}

	/**
	 * 
	 * @throws Exception
	 * @TODO 自动生成采购单、采购单行，对应数据来源于采购申请、采购申请行s
	 * @蒋祖兵 2007-8-15 上午11:41:13
	 */
	public void createpo() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行创建采购单操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("由采购申请创建采购单操作前，请先保存数据！");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (pr.getStatus().equals("已批准")) {
			String ponum = ((PrSrv) this.getMainSrv()).createpo(this
					.getMainObject());
			this.setMainData();
			Messagebox.show("成功生成采购单和采购单行-对应采购单:" + ponum);
		} else
			Messagebox.show("采购申请还没有批准，不能创建采购单");

	}

	/**
	 * @author 李阳 功能：将采购申请复制后生成新的采购申请
	 * @throws Exception
	 *             2008-1-22上午11:12:56
	 */
	public void copypr() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行复制操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("采购申请复制操作前，请先保存数据！");
			return;
		}
		Pr pr = (Pr) this.getMainObject();
		if (pr.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Prline.class, "prnum = '" + pr.getPrnum() + "'");
		String prnum = ((PrSrv) this.getMainSrv()).copypr(retList, pr);
		this.setMainData();
		Messagebox.show("已成功将采购单:" + pr.getPrnum() + "复制到采购单" + prnum);

	}
	
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：将选中的需求计划行合并到采购申请行里
	 * 日期：Oct 22, 2008 3:06:56 PM
	 *
	 */
	public void unitepr() throws Exception{
		Pr pr = (Pr)this.mainObject;
		if (pr.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
//		弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行合并采购申请行操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("合并采购申请行操作前，请先保存数据！");
			return;
		}
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this.getMainObject(), "/pr/uniteprlinepopup.xul");
		if(listWnd==null)
			return;
		
		//判断是否点击了确定按钮，还是取消按钮
		if(!listWnd.isConfirm())
			return;
		}

	/* 
	 * 功能：设置子窗口是否应该为只读
	 * 作者：李建红
	 * 日期：2008-11-7下午03:52:13
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Pr pr = (Pr) this.mainObject;
		ListWindow prlineWnd = (ListWindow)this.getFellow("prline");
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
