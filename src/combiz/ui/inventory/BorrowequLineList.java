package combiz.ui.inventory;

import combiz.domain.inventory.Matreq;
import combiz.domain.po.Po;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

import java.util.Date;

import com.inbasis.zul.Listitem;

public class BorrowequLineList extends ListWindow
{


	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public BorrowequLineList()
	{
		super();
	}

	/**
	 * 点击新建记录按钮的触发事件，添加默认字段值
	 * @throws Exception 
	 * 作者：洪小林 日期：2007-1-9
	 */
	public boolean addNew()
	throws Exception
	{
		//获取父级主窗体对象
		Matreq parent = (Matreq) ownerWnd.getMainObject();
		String defwarehouse = this.getLaborInfo().getDefaultstoreloc();
		Wpmaterial newobj = new Wpmaterial();
		newobj.setWarehouse(defwarehouse);
		newobj.setMatreqnum(parent.getMatreqnum());
		newobj.setWonum(parent.getWonum());
		newobj.setItemqty(0.0);
		newobj.setUnitcost(0.0);
		newobj.setLinecost(0.0);
		newobj.setRequestby(parent.getRequestby());
		newobj.setRequiredate(new Date());

		this.mainObject = newobj;
		return true;
	}	
	
	/* 
	 * 功能：如果行状态是"部门领导审批"的状态，设置为“只读”
	 * 作者：李阳
	 * 日期：Nov 28, 2008  11:13:25 AM
	 */
	@Override
	public void initRowData(Listitem listitem, Object obj) throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getOwnerWnd().getMainObject();
		ListWindow listwnd = (ListWindow) this.getOwnerWnd().getFellow(
				"wpmaterial");
		boolean onlyread = false;
		if (listwnd != null) {
			
			if (matreq.getStatus().equals("部门领导审批")||matreq.getStatus().equals("设备主管领导审批")||matreq.getStatus().equals("中心领导审批"))
			{
				onlyread = true;
			} 
			else
			{
				onlyread = false;
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
