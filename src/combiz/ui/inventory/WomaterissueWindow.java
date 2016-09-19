package combiz.ui.inventory;


import java.util.List;

import combiz.business.inventory.MatreqSrv;
import combiz.business.workorder.WorkorderSrv;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;

import combiz.domain.workorder.Workorder;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

public class WomaterissueWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public WomaterissueWindow() {
		super();
	}

	public void save() throws Exception {
		// TODO 自动生成方法存根
		Workorder wo = (Workorder) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("invusertrans");
		List invl = (List) listwnd.getModifiedObjectList();
		if (invl.size() > 0) {

			for (int j = 0; j < invl.size(); j++) {
				Invusetrans invusetrans = (Invusetrans) invl.get(j);
				Long id = invusetrans.getId();
				String itemnum = invusetrans.getItemnum();
				List itemlist = this.getMainSrv().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
				if(itemlist.size()>0)
				{
					Item item = (Item) itemlist.get(0);
					String lottype = item.getLottype();
					if(lottype.equals("批次管理") && Util.isNull(invusetrans.getLotnum()))
					{
						Messagebox.show("库存项目'"+itemnum+"'为批次管理的物资，请在发放行中选择箱柜确定发放的批次！");
						return;
					}
				}
				
				String issuetype = invusetrans.getIssuetype();
				Double quantity = invusetrans.getQuantity();
				Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.wonum = '"
								+ invusetrans.getWonum()
								+ "' and t.itemnum = '"
								+ invusetrans.getItemnum() + "'and t.id <> "
								+ id + "");
				if (num == null)
					num = 0d;
				
				Double issueqty = (Double) this.mainSrv.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where wonum = '"+wo.getWonum()+"'and t.issuetype = '发放' and t.state = '已完成'");
				   if(issueqty == null)
					   issueqty =0d;
				   
				List wpmaterlist = this.mainSrv.getBaseDao().findWithQuery(
						Wpmaterial.class,
						"wonum = '" + invusetrans.getWonum()
								+ "' and itemnum = '"
								+ invusetrans.getItemnum() + "'");
				
				if (wpmaterlist.size() > 0) {
					Wpmaterial wpmaterial = (Wpmaterial) wpmaterlist.get(0);
					Double orderqty = wpmaterial.getItemqty();
					if (issuetype.equals("发放")) {
						if (quantity <= 0 || (quantity + num) > orderqty) {
							Messagebox.show("编号为：" + itemnum
									+ "的库存项目发放数量大于该预留数\n量或者发放数量为零，请核实！");
							return;
						}
					}
					else
					{
						quantity = -quantity;
						if(quantity>=0 || (quantity+issueqty)<0) 
						{
							Messagebox.show("退库数量不为零，且不大于已发放数量，请核实！");
							return;
						}
						
					}
						
				}
			}
		}
		super.save();
	}

	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("工单进行发放操作前，请先保存记录！");
			return;
		}
		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		if (wo.getStatus().equals("已批准")) {
			

			int count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invreserve t where t.wonum = '"
							+ wo.getWonum() + "'");
			if (count > 0) {
				Window listWnd = (Window) this.popupDialog(
						this.getMainObject(),
						"/inventory/woinvreservepopup.xul", "wonum='"
								+ wo.getWonum() + "'");
			} else {
				Messagebox.show("工单'" + wo.getWonum()
						+ "'没有待发放的物资（设备），请确认！");
				return;
			}
		
			
			
			/*CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
					.getMainObject(), "/inventory/invreservepopup.xul",
					"wonum='" + wo.getWonum() + "'");
			if (listWnd == null)
				return;

			// 判断是否点击了确定按钮，还是取消按钮
			if (!listWnd.isConfirm())
				return;

			List retList = listWnd.getSelectObjects();
			System.out.println("retlist.size()="+retList.size());
			if (retList.size() < 1)
				throw new Exception("请选择一条记录！");
			((WorkorderSrv) this.getMainSrv()).geninvuse(retList, wo);
			this.refreshData();
			Messagebox.show("数据成功保存，确认发放数量无误后校验完成发放.如果不是全部发放，请在发放行里修改发放数量!");*/
			
			
		} else
			// Messagebox.show("该工单没有批准，不能进行发放操作");
			throw new Exception("该工单没有批准，不能进行发放操作");
	}
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：发放预留
	 * 日期：Oct 29, 2008 4:29:01 PM
	 *
	 */
	public void verify() throws Exception {

		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"wonum = '" + wo.getWonum() + "'and issuetype = '发放' and state ='待确认'");
		if (retList.size() == 0) {
			Messagebox.show("没有待确认的接收记录!");
		} else {
			((WorkorderSrv) this.getMainSrv()).verify(retList, wo);
			this.refreshData();
			Messagebox.show("完成发放!");

		}
	}

	public void returnissue() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行退库操作操作！");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("工单进行退库操作前，请先保存记录！");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusertrans");
		List retList = listWnd.getSelectObjects();
		int size = retList.size();
		if (retList.size() > 0) {
			
			int sum = 0;
			for(int i = 0;i <retList.size();i++)
			{
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				if(invusetrans.getState().equals("已完成") && invusetrans.getIssuetype().equals("发放"))
				{
					sum++;
				}
			}
			if(retList.size() - sum !=0)
			{
				throw new Exception("只能在发放行中选择已经发放且确认的记录进行退库操作");
			}
			
			

			Workorder wo = (Workorder) this.mainObject;
			if (wo.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.wonum ='"
							+ wo.getWonum() + "'and issuetype = '发放' and t.state = '已完成'");

			if (count > 0) {
				/*
				 * CommonListWindow listWnd = (CommonListWindow)
				 * this.popupDialog(this .getMainObject(),
				 * "/inventory/invreservepopup.xul", "wonum='" + wo.getWonum() +
				 * "'");
				 */
				((WorkorderSrv) this.getMainSrv()).returnissue(retList, wo);
				listWnd.mulitpleSelect();
				this.refreshData();
				Messagebox
						.show("数据成功保存，确认退库数量无误后，点击退库校验.如果不是全部退库，请在发放行里修改退库数量!");
			} else
				// Messagebox.show("该工单没有批准，不能进行发放操作");
				throw new Exception("该工单尚未进行发料，不能退库");
		} else
			throw new Exception("请选择退库记录");

	}
	
	public void verifyreturn() throws Exception {

		Workorder wo = (Workorder) this.mainObject;

		if (wo.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"wonum = '" + wo.getWonum() + "' and issuetype = '退回' and state ='待确认'");
		if (retList.size() == 0) {
			Messagebox.show("没有待确认的退库记录!");
		} else {
			((WorkorderSrv) this.getMainSrv()).verify(retList, wo);
			this.refreshData();
			Messagebox.show("完成退库确认!");

		}
	}
	

}
