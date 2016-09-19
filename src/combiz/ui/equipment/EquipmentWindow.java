package combiz.ui.equipment;

import combiz.business.equipment.EquipmentSrv;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqdowntime;
import combiz.domain.equipment.Eqtrans;
import combiz.domain.equipment.Equipment;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class EquipmentWindow extends MainWindow implements InfWindow {

	// /////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public EquipmentWindow() {
		super();
	}

	/**
	 * 新增记录
	 * 
	 * @throws Exception
	 *             作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew() throws Exception {
		/***********************************************************************
		 * 创建初始值
		 **********************************************************************/
		Equipment newobj = new Equipment();
		newobj.setEqnum(this.genAutokey("eqnum"));
		newobj.setInvcost(0.0D);
		newobj.setBudgetcost(0.0D);
		newobj.setPriority(0L);
		newobj.setPurprice(0.0D);
		//newobj.setReplacecost(0.0D);
		newobj.setTotalcost(0.0D);
		newobj.setYtdcost(0.0D);
		newobj.setTotdowntime(0.0d);
		newobj.setIsrunning("是");

		newobj.setChangeby(this.getUserInfo().getLabornum());
		newobj.setChangedate(new Date());

		mainObject = newobj;
		return true;
	}
	
	
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		
		int a = this.ADDED;
		Equipment equipment = (Equipment) this.getMainObject();
		String args[]={"equipment.parent"};
		String argsnull[]={};
		if(this.getObjStatus() == a)
		{
			this.setReadonly(argsnull);
		}
		else
		{
			this.setReadonlyFields(args);
		}
		super.initData();
	}
	
	/**
	 * 方法
	 * 
	 * 作者：李阳 
	 * 功能：调整父级设备
	 * 日期：10:07:46 AM  Jun 10, 2010 
	 *
	 */
	public void equipchg()
	throws Exception
	{
		Equipment equipment = (Equipment) this.getMainObject();
		CommonDialog cp = (CommonDialog) this.popupDialog(equipment, "/equipment/equipchg.xul"); //
		if(cp.isConfirm())
		{
			this.clear();
			Messagebox.show("父级设备调整已经完成！");
		}
	}
	

	/**
	 * @throws Exception
	 * @author:高群凯
	 * @description:设备移动 @ 2007-8-7 上午11:41:49
	 */
	public void eqmove() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录，然后再进行设备移动操作！");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("设备移动操作前，请先保存数据！");
			return;
		}

		Equipment mainObject = (Equipment) this.getMainObject();

		Eqtrans eqtrans = new Eqtrans();
		eqtrans.setEqnum(mainObject.getEqnum());
		eqtrans.setFromloc(mainObject.getLocation());

		this.popupDialog((Object) eqtrans, "/equipment/eqmove.xul");

		// 重新刷新数据
		this.refreshData();
	}

	
	/**
	 * 设备停机
	 * 作者:陈明锐
	 * 日期:Apr 2, 2009
	 * @throws Exception
	 */
	public void eqdowntime() throws Exception {
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("请选择一条主记录!");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.objStatus == this.ADDED) {
			Messagebox.show("请先保存数据");
			return;
		}
		Eqdowntime eqdowntime = new Eqdowntime();
		this.popupDialog(eqdowntime, "/equipment/eqdowntimelist.xul");
	}

	/**
	 * @author 李阳 功能：对应接收应用程序下菜单中的接收检验；接收时，通过接收检验修改接收行信息。
	 * @throws Exception
	 *             2008-1-22下午01:11:24
	 */
	public void fixed() throws Exception {
	
		Equipment equipment = (Equipment) this.mainObject;
		
		List list = this.getSelectObjects();
	
		if (equipment.getId()==null ) {
			Messagebox.show("请您先选择一条记录！");
			return;
		}
		// 弹出指定页面窗口，并且制定过滤条件，如果没有过滤请用NULL
		if (equipment.getAssetnum()!=null ) {
			Messagebox.show("该数据已生成，请您先选择一条记录！");
			return;
		}
	
	
		else {
			((EquipmentSrv) this.getMainSrv()).copyfixed(list);
			this.clear();
			Messagebox.show("已经生成固定资产!");
		}
			
	
		}
	}

