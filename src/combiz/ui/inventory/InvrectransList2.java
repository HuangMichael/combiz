package combiz.ui.inventory;

import combiz.domain.inventory.Invrectrans;
import combiz.system.ui.ListWindow;

import com.inbasis.zul.Listitem;

public class InvrectransList2 extends ListWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public InvrectransList2() {
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
//		PoWindow parentWnd = (PoWindow) ownerWnd;
//		Po po = (Po)parentWnd.getMainObject();
		//Inventory parent = (Inventory) parentWnd.getMainObject();
//		Listwindow invrectrans = this.getFellow("invrectrans");

		Invrectrans newobj = new Invrectrans();
//		newobj.setPonum()
//		newobj.setItemnum(parent.getItemnum());
//		newobj.setTowarehouse(parent.getWarehouse());
		this.mainObject = newobj;
		return true;
	}

	

	@Override
	public void save() throws Exception {
		// TODO 自动生成方法存根
		super.save();
	}

	@Override
//	根据接收行的接收记录的状态和箱柜字段来判断是否为可编辑状态。
	public void onRowSelected() {
		// TODO 自动生成方法存根
		
		ListWindow listWnd = (ListWindow) this.getFellow("invrectrans");
		Invrectrans invrectrans = (Invrectrans) this.getMainObject();
		if(invrectrans.getStatus().equals("已检验") ){
			listWnd.setReadonlyList(true);
		}
		else
		{
			listWnd.setReadonlyList(false);
		}
		super.onRowSelected();
	}

	@Override
	public void initRowData(Listitem arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		Invrectrans invrectrans = (Invrectrans) arg1;
		
		if (invrectrans.getStatus().equals("待检验") || invrectrans.getStatus().equals("待编号")) {
			this.setReadonlyObject(arg0, false);
		}else{
			this.setReadonlyObject(arg0, true);
		}
		super.initRowData(arg0, arg1);
	}
	

}
