package combiz.ui.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Executions;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Window;

import combiz.business.inventory.MatreqSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueEqWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IssueEqWindow() {
		super();
	}

	/**
	 * 
	 * brianhong 2007-11-13
	 */
	public void selectMRLine() {

	}

	public void save() throws Exception {
		// TODO 自动生成方法存根
		Matreq matreq = (Matreq) this.getMainObject();
		ListWindow listwnd = (ListWindow) this.getFellow("invusetrans");
		List invl = (List) listwnd.getModifiedObjectList();
		if (invl.size() > 0) {

			for (int j = 0; j < invl.size(); j++) {
				Invusetrans invusetrans = (Invusetrans) invl.get(j);
				Long id = invusetrans.getId();
				String itemnum = invusetrans.getItemnum();
				Double quantity = invusetrans.getQuantity();
				Double num = (Double) this.mainSrv.getBaseDao().selectSumByHql(
						"select sum(t.quantity) from Invusetrans t where t.matreqnum = '"
								+ invusetrans.getMatreqnum()
								+ "' and t.itemnum = '"
								+ invusetrans.getItemnum() + "' and t.id <> "
								+ id + "");
				if (num == null)
					num = 0d;
				List wpmaterlist = this.mainSrv.getBaseDao().findWithQuery(
						Wpmaterial.class,
						"matreqnum = '" + invusetrans.getMatreqnum()
								+ "' and itemnum = '"
								+ invusetrans.getItemnum() + "'");
				if (wpmaterlist.size() > 0) {
					Wpmaterial wpmaterial = (Wpmaterial) wpmaterlist.get(0);
					Double orderqty = wpmaterial.getItemqty();
					if ((quantity <= 0 || (quantity + num) > orderqty)
							&& (invusetrans.getIssuetype().equals("发放"))) {
						// throw new Exception("接收数量应不为0，且不大于订购数量，请核实！");
						// Messagebox.show("编号为：" + itemnum +
						// "的库存项目发放数量大于批准数量\n或者发放数量为零，请核实！");
						// return;
						throw new Exception("编号为：" + itemnum
								+ "的库存项目发放数量大于批准数量\n或者发放数量为零，请核实！");

					}
					if ((quantity >= 0)
							&& (invusetrans.getIssuetype().equals("退回"))) {
						// Messagebox.show("编号为：" + itemnum +
						// "的库存项目退库数量应为负值，请核实！");
						// return;
						throw new Exception("编号为：" + itemnum
								+ "的库存项目退库数量应为负值，请核实！");
					}
					if ((invusetrans.getIssuetype().equals("退回"))
							&& (quantity + num) < 0) {
						throw new Exception("编号为：" + itemnum
								+ "的库存项目退库数量不应大于发放数量，请核实！");
					}
				}
			}
		}
		/*
		 * else throw new Exception("没有修改，不需要保存");
		 */
		super.save();
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：生成发放行 日期：Nov 4, 2008 10:38:45 AM
	 * 
	 */
	public void geninvuse() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行发放操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("工单操作发放操作前，请先保存已经发放的记录！");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		String status = matreq.getStatus();
		if (Util.isNotNull(status) && status.equals("已批准")) {
			int count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invreserve t where t.reqnum = '"
							+ matreq.getMatreqnum() + "'");
			if (count > 0) {
				Window listWnd = (Window) this.popupDialog(
						this.getMainObject(),
						"/inventory/eqinvreservepopup.xul", "reqnum='"
								+ matreq.getMatreqnum() + "'");
			} else {
				Messagebox.show("领用申请'" + matreq.getMatreqnum()
						+ "'没有待发放的设备，请确认！");
				return;
			}
		}

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：物资和周转件的确认发放 日期：Nov 4, 2008 10:38:59 AM
	 * 
	 */
	public void verify() throws Exception {

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		ArrayList retList = new ArrayList();
		ArrayList selectList = new ArrayList();
		Boolean toverify = null;
		if (ismultiple == true)// 用户是否点击选择
		{
			if (Messagebox.show("是否校验选中的发放行？", "请确认！", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				selectList = (ArrayList) listWnd.getSelectObjects();
				for (int i = 0; i < selectList.size(); i++) {
					Invusetrans invu = (Invusetrans) selectList.get(i);
					if (invu.getState().equals("待确认")) {
						retList.add(invu);
					}

				}
				if (retList == null)
					;
				{
					throw new Exception("没有待确认的接收记录!");
				}
			} else {
				return;
			}

		} else {
			if (Messagebox.show("是否确认该领用申请下所有未确认的发放行？", "请确认！", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				retList = (ArrayList) this.getMainSrv().getBaseDao()
						.findWithQuery(
								Invusetrans.class,
								"matreqnum = '" + matreq.getMatreqnum()
										+ "' and state ='待确认'");
			} else {
				return;
			}

		}
		if (retList.size() == 0) {
			Messagebox.show("没有待确认的接收记录!");
		} else {
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("完成发放!");
		}
	}

	// 退库
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
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		List retList = listWnd.getSelectObjects();

		if (retList.size() > 0) {

			int sum = 0;
			for (int i = 0; i < retList.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				if (invusetrans.getState().equals("已完成")
						&& invusetrans.getIssuetype().equals("发放")) {
					sum++;

					Double issueqty = (Double) this
							.getMainSrv()
							.getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "' and t.eqnum = '"
											+ invusetrans.getEqnum()
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.state = '已完成' and t.issuetype = '发放'");
					Double returnqty = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "' and t.eqnum = '"
											+ invusetrans.getEqnum()
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.issuetype = '退回'");
					if (issueqty == null) {
						issueqty = 0d;
					}
					if (returnqty == null) {
						returnqty = 0d;
					}
					if (issueqty + returnqty <= 0) {
						Messagebox.show("不能生成退库行，您选中的资产编号为'"
								+ invusetrans.getEqnum() + "'发放行，退库数量大于发放总数量！");
						break;
					}
				}
			}
			if (retList.size() - sum != 0) {
				throw new Exception("只能在发放行中选择已经发放且确认过的记录进行退库操作");
			}

			Matreq matreq = (Matreq) this.mainObject;
			if (matreq.getId()==null) {
				Messagebox.show("请您先选择一条记录！");
				return;
			}
			Integer count = this.getMainSrv().getBaseDao().selectCountByHql(
					"select count(*) from Invusetrans t where t.matreqnum ='"
							+ matreq.getMatreqnum()
							+ "'and issuetype = '发放' and t.state = '已完成'");

			if (count > 0) {
				((MatreqSrv) this.getMainSrv()).returnissue(retList, matreq);
				this.refreshData();
				Messagebox.show("数据成功保存，确认退库数量无误后，点击退库确认!");
			} else
				throw new Exception("该领用申请尚未进行发料，不能退库");
		} else
			throw new Exception("请选择退库记录");

	}

	// 退库校验
	/**
	 * 方法：verifyreturn 作者：李建红 功能： 日期：Apr 16, 2009 6:26:36 PM
	 */
	public void verifyreturn() throws Exception {

		Matreq matreq = (Matreq) this.mainObject;

		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		List retList = this.getMainSrv().getBaseDao().findWithQuery(
				Invusetrans.class,
				"matreqnum = '" + matreq.getMatreqnum()
						+ "' and issuetype = '退回' and state ='待确认'");
		if (retList.size() == 0) {
			Messagebox.show("没有待确认的退库记录!");
		} else {
			for (int i = 0; i < retList.size(); i++) {
				Invusetrans invusetrans = (Invusetrans) retList.get(i);
				Double issueqty = (Double) this
						.getMainSrv()
						.getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "' and t.eqnum = '"
										+ invusetrans.getEqnum()
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.state = '已完成' and t.issuetype = '发放'");
				Double returnqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "' and t.eqnum = '"
										+ invusetrans.getEqnum()
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.issuetype = '退回'");
				if (issueqty == null) {
					issueqty = 0d;
				}
				if (returnqty == null) {
					returnqty = 0d;
				}
				if (issueqty + returnqty < 0) {
					Messagebox.show("不能完成退库，资产编号为'"
							+ invusetrans.getEqnum() + "'退库行，退库数量大于发放总数量！");
					break;
				}
			}

			((MatreqSrv) this.getMainSrv()).verifyreturn(retList, matreq);
			this.refreshData();
			Messagebox.show("完成退库确认!");

		}
	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：打印资产卡片 日期：11:49:14 AM Mar 11, 2009
	 * 
	 */
	public void printEuipcard() throws Exception {

		String isrotating = null;
		Matreq mat = (Matreq) this.getMainObject();
		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		boolean toprint = false;
		if (!ismultiple) {
			if (Messagebox.show("是否打印该发放单下所有的设备标签？", "提示！！！", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toprint = true;
			else
				toprint = false;
		} else {
			if (Messagebox.show("是否打印选中的发放记录中的设备标签？", "提示！！！", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toprint = true;
			else
				toprint = false;

		}

		if (toprint) {
			List invuselist = listWnd.getSelectObjects();
			if (invuselist.size() == 0) {
				throw new Exception("请选择一条发放记录再打印！");
			} else {
				Map param = new HashMap();
				for (int j = 0; j < invuselist.size(); j++) {
					Invusetrans invu = (Invusetrans) invuselist.get(j);
					String itemnum = invu.getItemnum();
					Item item = (Item) this.getMainSrv().getBaseDao()
							.findUniqueBy(Item.class, "itemnum", itemnum);
					isrotating = item.getRotating();

					param.put("printType", "equip");
					List objList = new ArrayList();
					objList.add(invu);
					param.put("objList", objList);
				}

				if (Util.getBoolean(isrotating))// 判断是否周转件？
				{

					Window equipcardWnd = (Window) Executions.createComponents(
							"/common/printeqcard.xul", null, param);
					equipcardWnd.doModal();

				}
			}
		} else {
			return;
		}

	}

	/*
	 * @Override @设置完成操作的行只读 ljh
	 */

	/*
	 * public void initData() throws Exception { // TODO Auto-generated method
	 * stub Matreq matreq = (Matreq) this.mainObject;
	 * 
	 * ListWindow issuemrWnd = (ListWindow) this.getFellow("invusetrans"); if
	 * (matreq.getStatus()!=null && matreq.getStatus().equals("已批准")) {
	 * issuemrWnd.setReadonlyList(true); } super.initData(); }
	 */

}
