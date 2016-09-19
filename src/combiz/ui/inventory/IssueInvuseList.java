package combiz.ui.inventory;

import java.util.Date;

import com.inbasis.zul.Listitem;

import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Warehouse;
import combiz.system.ui.ListWindow;

public class IssueInvuseList extends ListWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public IssueInvuseList() {
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * 
	 * @throws Exception
	 *             作者：高群凯 日期：2007-8-20
	 */
	public boolean addNew() throws Exception {
		// 获取父级主窗体对象
		Warehouse parent = (Warehouse) ownerWnd.getMainObject();

		Invusetrans newobj = new Invusetrans();
		newobj.setWarehouse(parent.getWarehouse());
		newobj.setEnterby(this.getLaborInfo().getLabornum());
		newobj.setActualdate(new Date());
		newobj.setIssuetype("发放");
		newobj.setState("待确认");
		newobj.setTransdate(new Date());
		newobj.setConversion(1D);

		this.mainObject = newobj;
		return true;
	}

	/*
	 * 功能：判断和设置行记录为只读 作者：李建红 日期：Oct 8, 20083:53:43 PM
	 */
	@Override
	public void initRowData(Listitem listitem, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String labornum = this.getLaborInfo().getLabornum();
		ListWindow listwnd = (ListWindow) this.getOwnerWnd().getFellow(
				"invusetrans");
		boolean onlyread = false;
		if (listwnd != null) {
			Invusetrans invusetrans = (Invusetrans) obj;
			// 判断是否为编辑人本人
			if (invusetrans.getEnterby().equals(labornum)) {
				if (invusetrans.getState().equals("已完成")
						|| invusetrans.getState().equals("部分发放")
						|| invusetrans.getState().equals("已完成")) {
					onlyread = true;
				} else {
					onlyread = false;
				}

			} else {
				onlyread = true;
			}
			// 是否有权限编辑
			if (onlyread) {
				this.setReadonlyObject(listitem, true);// 设置行为不可编辑状态
			} else {
				this.setReadonlyObject(listitem, false);// 设置行为可编辑状态
			}
		}

	}
}
