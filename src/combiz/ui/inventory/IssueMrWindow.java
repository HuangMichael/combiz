package combiz.ui.inventory;

import java.util.List;

import com.inbasis.zul.Messagebox;
import combiz.business.inventory.MatreqSrv;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

public class IssueMrWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IssueMrWindow() {
		super();
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
						throw new Exception("编号为：" + itemnum
								+ "的库存项目发放数量大于批准数量\n或者发放数量为零，请核实！");

					}
					if ((quantity >= 0)
							&& (invusetrans.getIssuetype().equals("退回"))) {
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

	// 发放
	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：从预留表里拷贝信息到发放行(invusetrans)里 日期：Nov 4, 2008 11:37:04 AM
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
				CommonListWindow listWnd = (CommonListWindow) this.popupDialog(
						this.getMainObject(),
						"/inventory/mrinvreservepopup.xul", "reqnum='"
								+ matreq.getMatreqnum() + "'");
				if (listWnd == null)
					return;

				// 判断是否点击了确定按钮，还是取消按钮
				if (!listWnd.isConfirm())
					return;

				List retList = listWnd.getSelectObjects();
				((MatreqSrv) this.getMainSrv()).geninvuse(retList, matreq);
				this.refreshData();
				Messagebox
						.show("数据成功保存，确认发放数量无误后校验完成发放.如果不是全部发放，请在发放行里修改发放数量!");
			} else
				Messagebox.show("没有该申请的预留库存项目，请先进行预留操作");
			return;

		} else
			Messagebox.show("该领用申请没有批准，不能进行发放操作");
		return;

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：确认发放 日期：Nov 4, 2008 11:37:41 AM
	 * 
	 */
	public void verify() throws Exception {

		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行确认操作！");
			return;
		}

		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("在进行确认操作前，请先保存记录！");
			return;
		}

		Matreq matreq = (Matreq) this.getMainObject();

		if (matreq.getId()==null) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL

		ListWindow listWnd = (ListWindow) this.getFellow("invusetrans");
		boolean ismultiple = listWnd.isMultiple();
		List retList = null;
		List selectList = null;
		Boolean toverify = null;
		if (ismultiple == true)// 用户是否点击选择
		{
			if (Messagebox.show("是否确认选中的发放行？", "请确认！", Messagebox.YES
					| Messagebox.NO, "") == Messagebox.YES)
				toverify = true;
			else
				toverify = false;
			if (toverify) {
				selectList = listWnd.getSelectObjects();
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
				retList = this.getMainSrv().getBaseDao().findWithQuery(
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

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：退库操作 日期：Nov 4, 2008 11:38:04 AM
	 * 
	 */
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
											+ "' and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.state = '已完成' and t.issuetype = '发放'");
					Double returnqty = (Double) this.getMainSrv().getBaseDao()
							.selectSumByHql(
									"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
											+ invusetrans.getWarehouse()
											+ "' and t.matreqnum ='"
											+ invusetrans.getMatreqnum()
											+ "'and t.itemnum = '"
											+ invusetrans.getItemnum()
											+ "' and t.issuetype = '退回'");
					if (issueqty == null) {
						issueqty = 0d;
					}
					if (returnqty == null) {
						returnqty = 0d;
					}
					if (issueqty + returnqty <= 0) {
						throw new Exception("不能生成退库行，您选中的库存编号为'"
								+ invusetrans.getItemnum()
								+ "'发放行，退库数量大于发放总数量！");
					}
				}
			}
			if (retList.size() - sum > 0) {
				throw new Exception("只能在发放行中选择已经发放且完成的发放记录进行退库操作");
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
				listWnd.mulitpleSelect();
				this.refreshData();
				Messagebox
						.show("数据成功保存，确认退库数量无误后，点击退库校验.如果不是全部退库，请在发放行里修改退库数量!");
			} else
				throw new Exception("该申请单尚未进行发料，不能退库");
		} else
			throw new Exception("请选择退库记录");

	}

	/**
	 * 方法
	 * 
	 * 作者：李阳 功能：退库校验 日期：Nov 4, 2008 11:38:14 AM
	 * 
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
										+ "' and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.state = '已完成' and t.issuetype = '发放'");
				Double returnqty = (Double) this.getMainSrv().getBaseDao()
						.selectSumByHql(
								"select sum(t.quantity) from Invusetrans t where t.warehouse = '"
										+ invusetrans.getWarehouse()
										+ "' and t.matreqnum ='"
										+ invusetrans.getMatreqnum()
										+ "'and t.itemnum = '"
										+ invusetrans.getItemnum()
										+ "' and t.issuetype = '退回'");
				if (issueqty == null) {
					issueqty = 0d;
				}
				if (returnqty == null) {
					returnqty = 0d;
				}
				if (issueqty + returnqty <= 0) {
					throw new Exception("不能完成退库，您选中的库存编号为'"
							+ invusetrans.getItemnum() + "'发放行，退库数量大于发放总数量！");
				}
			}
			((MatreqSrv) this.getMainSrv()).verify(retList, matreq);
			this.refreshData();
			Messagebox.show("完成退库校验!");

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
