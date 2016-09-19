package combiz.ui.invoice;

import combiz.business.invoice.InvoiceSrv;
import combiz.business.po.PoSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class InvoiceWindow extends MainWindow implements InfWindow {

	/**
	 * @TODO
	 * @蒋祖兵 2007-8-21 下午03:18:38
	 */
	private static final long serialVersionUID = 1L;

	public InvoiceWindow() {
		super();
	}

	/**
	 * 
	 * @TODO 新增一条记录
	 * @throws Exception
	 * @蒋祖兵 2007-8-7 下午01:17:05
	 */
	public boolean addNew() throws Exception {
		/**********************************
		 * 创建初始值
		 **********************************/
		Invoice iv = new Invoice();

		iv.setInvoicenum(this.genAutokey("invoicenum"));
		mainObject = iv;
		iv.setStatus("流程未启动");

		iv.setEnterby(this.getLaborInfo().getLabornum());
		//iv.setSitenum(this.getLaborInfo().getSitenum());
		//iv.setCorpnum(this.getLaborInfo().getCorpnum());
		iv.setEnterdate(new Date());
		iv.setStatusdate(new Date());
		iv.setChangeby(this.getLaborInfo().getLabornum());
		iv.setChangedate(new Date());
		iv.setTotalcost(0d);
		iv.setBasetotalcost(0d);
		iv.setTotaltax(0d);
		return true;
	}

	/**
	 * 
	 * @TODO 自动生成发票行---弹出一个窗口(数据来源于采购单行)。由用户选择。将选择结果录入到发票行中
	 * 
	 * @throws Exception
	 * @蒋祖兵 2007-8-21 下午05:03:57
	 */

	public void createinvoiceline() throws Exception {

		Invoice iv = (Invoice) this.getMainObject();
		if(Util.isNotNull(iv.getCntnum()))
		{
			Messagebox.show("此张发票是为合同付款，不能生成发票行，请在发票行里新建记录！");
			return;
		}
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("请在生成预留前保存数据！");
			return;
		}
		
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
				.getMainObject(), "/invoice/invoicelineList.xul");
		if (listWnd == null)
			return;

		//判断是否点击了确定按钮，还是取消按钮
		if (!listWnd.isConfirm())
			return;
		//TableList tabblelist = (TableList) listWnd.getFellow(listWnd.getId()+"List");
		List retList = listWnd.getSelectObjects();
		((InvoiceSrv) this.getMainSrv()).CreateInvoiceline(retList, iv);
		Messagebox.show("采购单行已经复制到发票!");
		this.refreshData();
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：校验发票行
	 * 日期：Oct 23, 2008 5:10:12 PM
	 *
	 */
	public void verify() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("校验发票前，请先保存数据！");
			return;
		}
		Invoice invoice = (Invoice)this.mainObject;
		List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '待校验'");
		if(retList.size() == 0){
			Messagebox.show("没有需要校验的发票记录!");
		}
		else{
			((InvoiceSrv)this.getMainSrv()).verify(retList, invoice);
			this.refreshData();
			Messagebox.show("发票校验完毕!");
			
		}
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：批准发票
	 * 日期：Oct 23, 2008 5:09:59 PM
	 *
	 */
	public void approveinvoice() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("批准发票前，请先保存数据！");
			return;
		}
		Invoice invoice = (Invoice)this.mainObject;
		if(invoice.getStatus().equals("校验完成"))
		{
			List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '已校验'");
			if(retList.size() == 0){
				Messagebox.show("没有需要批准的发票记录!");
			}
			else{
				if(Util.isNull(invoice.getCntnum()))//采购的发票
				{
					((InvoiceSrv)this.getMainSrv()).approveinvoice(retList, invoice);
					this.refreshData();
					this.createinvtrans();//同时生成发票差异记录
					Messagebox.show("发票已批准!");
				}
				else//合同的发票
				{
					((InvoiceSrv)this.getMainSrv()).apprcntinv(retList, invoice);
					this.refreshData();
					Messagebox.show("发票已批准!");
				}
			}
		}
		else
		{
			Messagebox.show("该发票里还有没有检验的发票行，不能进行发票批准\n操作或者该发票已批准");
		}
	}

	@Override
	public void save() throws Exception {
		// TODO 自动生成方法存根

		Invoice iv = (Invoice) this.getMainObject();
		iv.setChangeby(this.getLaborInfo().getLabornum());
		iv.setChangedate(new Date());
		super.save();
	}

	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		/*Invoice iv = (Invoice) this.getMainObject();
		ListWindow invoicelineWnd = (ListWindow)this.getFellow("invoiceline");
		if (iv.getStatus()!=null && iv.getStatus().equals("已批准")) {
			invoicelineWnd.setReadonlyList(true);
		}else{
			invoicelineWnd.setReadonlyList(false);
		}
		super.initData();*/
	}
	
	/**
	 * 方法
	 * 
	 * 作者 李建红
	 * 功能：生成发票差异行
	 * 日期：8 08 , 2009 5:09:59 PM
	 *
	 */
	public void createinvtrans() throws Exception{
		/*if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条发票记录！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("生成发票差异前，请先保存数据！");
			return;
		}
		//得到主界面类
		
		if (invoice.getId() == null) {
			Messagebox.show("请您先选择一条发票记录！");
			return;
		}*/
		Invoice invoice = (Invoice)this.mainObject;
		//if (invoice.getStatus() != null && "已批准".equals(invoice.getStatus())) {
		
			//检验是否有发票行，无发票行，者不需要生成发票差异记录
			List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '已校验'");
			if (retList.size()<0) {
				Messagebox.show("没有已校验的发票行记录，不能生成发票差异！");
				return;
			}
			
			//得到选择的发票行对象
			ListWindow invoicelineWnd = (ListWindow)this.getFellow("invoiceline");
			Invoiceline invline = null;
			List invlinelist = invoicelineWnd.getSelectObjects();
			if (invlinelist.size()>0) {
				for (int i=0;i<invlinelist.size();i++) {
					invline =(Invoiceline) invlinelist.get(i);
					if ("已校验".equals(invline.getState())) {
						//调用业务类，生成发票差异
						((InvoiceSrv)(this.getMainSrv())).createinvtrans(invlinelist);
						//Messagebox.show("生成发票差异行完成！");
						//this.refreshData();
					}else{
						Messagebox.show("发票行未通过校验，不能生成发票差异，请核实！");
						return;
					}
				}
				
			}else{
				Messagebox.show("该发票无发票行，请先生成发票行！");
				return;
			}
			
			
		/*}else{
			Messagebox.show("该发票未通过校验，或者发票行未校验，不能生成发票差异，请核实！");
			return;
		}*/
	}
	
	/**
	 * 方法
	 * 
	 * 作者 李建红
	 * 功能：查看发票差异行
	 * 日期：8 25 , 2009 5:09:59 PM
	 *
	 */

	public void selectinvtrans() throws Exception {

		Invoice iv = (Invoice) this.getMainObject();
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("请在生成预留前保存数据！");
			return;
		}
		
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
				.getMainObject(), "/invoice/invoicetransList.xul");
		if (listWnd == null)
			return;

		//判断是否点击了确定按钮，还是取消按钮
		if (!listWnd.isConfirm())
			return;
		
	}
}
