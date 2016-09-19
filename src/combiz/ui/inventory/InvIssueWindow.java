package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.MatreqSrv;
import combiz.business.inventory.WarehouseSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;


public class InvIssueWindow extends MainWindow
implements InfWindow
{		
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvIssueWindow()
	{
		super();
	}


	/**
	 * 
	 * brianhong  2007-11-13
	 */
	public void selectWMatLine()
	{

	}

	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：发放目前选中的物料ISSUSESEL
	 * 日期：Oct 6, 2008 2:35:42 PM
	 *
	 */
	public void issusesel() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("工单操作发放操作前，请先保存已经发放的记录！");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();
		Double qtycount = 0D;//所有选择的行交易数量

		if (retList.size() > 0) {
			Invusetrans invusetrans = null;
			int a = retList.size();
			int sum = 0;
			for(int i = 0;i < a;i++)
			{
				invusetrans = (Invusetrans) retList.get(i);
				if (invusetrans.getIssuetype().equals("未提交")) {
					qtycount = qtycount + invusetrans.getQuantity();
					sum++;
				}else{
					throw new Exception("只能选择未提交的申请行！");
				}

			}
			if( retList.size() - sum<0)
			{
				throw new Exception("只能在发放行中选择状态为未提交的行进行发放操作");
			}

			Warehouse warehouse = (Warehouse) this.mainObject;
			//查询出当前库存数量
			Integer curbal = this.getMainSrv().getBaseDao().selectCountByHql("select count(t.curbal) from Invstock t where t.warehouse='"+invusetrans.getWarehouse()+"' and t.itemnum='"+invusetrans.getItemnum()+"'");

			if ((curbal-qtycount)<0) {
				throw new Exception("库存余量不足，请先采购！");
			}
			//((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			((WarehouseSrv) this.getMainSrv()).verify(retList, warehouse);
			Messagebox.show("发放完成！");

		} else
			throw new Exception("请选择要发放的行！");

	}


	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：发放目前所有未提交行ISSUEALL
	 * 日期：Oct 6, 2008 2:36:45 PM
	 *
	 */
	public void issueall() throws Exception {
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("物料操作发放操作前，请先保存已经发放的记录！");
			return;
		}
		Warehouse warehouse = (Warehouse) this.mainObject;
		int rtn = Messagebox.show("是否确定发放目前所有未提交行？","发放！",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			if (warehouse.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			String labornum = this.getLaborInfo().getLabornum();
			Double curbal = 0D;//库存余量
			Double reqcount = 0D;//交易数量
			// 验证是否有未提交的行,发放只能发放自己添加的行,状态为未提交
			Integer selectall = this.getMainSrv().getBaseDao().selectCountByHql("select count(t.quantity) from Invusetrans t" +
					" where t.warehouse='"+warehouse.getWarehouse()+"' "
					+"and t.issuetype='发放' and t.state='待确认' "
					+"and t.enterby='"+labornum+"'");
			if (selectall <=0) {
				throw new Exception("没有需要发放的待确认记录，请核实！");
			}
			List retlist = null;
			retlist = this.getMainSrv().getBaseDao().findWithQuery(Invusetrans.class, " issuetype='发放' and state='待确认' and warehouse ='"+
					warehouse.getWarehouse()+"' and enterby='"+labornum+"'");
			if (retlist.size()>0) {
				for (int n=0;n<retlist.size();n++) {
					Invusetrans invusetrans = (Invusetrans) retlist.get(n);
					//得到物料所有发放需要的数量，并且判断库存余量是否满足需求，判断条件为申请同一仓库同一箱柜下物品数量
					String binnum = invusetrans.getBinnum();
					if(Util.isNotNull(binnum))
					{
						curbal = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum='"+invusetrans.getItemnum()+"' and t.warehouse='"+invusetrans.getWarehouse()+"' and binnum = '"+invusetrans.getBinnum()+"'");
					}
					else
					{
						curbal = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum='"+invusetrans.getItemnum()+"' and t.warehouse='"+invusetrans.getWarehouse()+"' and binnum is null");
					}

					if(curbal-invusetrans.getQuantity()<0)
					{
						throw new Exception("库存'"+invusetrans.getItemnum()+"'余量不足，请先提出采购，补充库存。");
					}

				}
			}else{
				throw new Exception("当前没有您可以发放的行，请核实！");
			}
			
			MatreqSrv matreqSrv = (MatreqSrv)IBOSrvUtil.getSrv("matreq");
			matreqSrv.verify(retlist, warehouse);
			this.refreshData();
			this.refreshChildData();
			Messagebox
			.show("完成发放!");}
	}

	/**
	 * 方法
	 * 
	 * 作者：李建红
	 * 功能：退库目前选中的物料RETURNSEL
	 * 日期：Oct 6, 2008 2:37:48 PM
	 *
	 */
	public void returnsel() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("物料操作发放操作前，请先保存已经发放的记录！");
			return;
		}
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();

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
			if( retList.size() - sum<0)
			{
				throw new Exception("只能在发放行中选择已经发放且已确认过的记录进行退库操作");
			}


			Matreq matreq = (Matreq) this.mainObject;
			if (matreq.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.matreqnum ='"
					+ matreq.getMatreqnum() + "'and t.issuetype = '发放' and t.state = '已完成'");

			if (count > 0) {
				/*
				 * CommonListWindow listWnd = (CommonListWindow)
				 * this.popupDialog(this .getMainObject(),
				 * "/inventory/invreservepopup.xul", "wonum='" + wo.getWonum() +
				 * "'");
				 */
				((MatreqSrv) this.getMainSrv()).returnissue(retList, matreq);
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


}
