package combiz.ui.location;
 
import combiz.business.location.LocationsSrv;
import combiz.business.location.LocstructSrv;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.domain.location.Locsystem;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.CommonDialog;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.inbasis.zul.Listbox;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;


public class LocationsWindow extends TMainWindow
implements InfWindow
{
	/////////////////////////////
	/////该类的主对象为 locations
	////
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public LocationsWindow()
	{
		super();
	}
	 
	/**
	 * 重写新建记录
	 * 
	 * 英贝思  Nov 24, 2009
	 * @throws Exception
	 */
	@Override
	public void add() throws Exception
	{
		if(this.objStatus==this.ADDED || this.objStatus == this.MODIFIED)
		{
			Messagebox.show("请先保存当前修改！");
			return;
		}
		
		Locstruct locstruct = new Locstruct();
		locstruct.setHaschild("否");
		locstruct.setOrderby(0L);
		//设置默认值
		locstruct.setDeptnum(this.getLaborInfo().getDeptnum());
		locstruct.setCraft(this.getLaborInfo().getCraft());
		locstruct.setType("运行位置");
		//根据父级生成编码
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Locations locations = (Locations) ti.getValue();
			locstruct.setParent(locations.getLocation());
			//自动插入位置的自动编号
			String getloc = "";
			List loclist = this.getMainSrv().getBaseDao().selectListByHql(" from Locstruct l where l.parent='"+locations.getLocation()+"' order by l.location desc");
			if (loclist.size()>0) {
				Locstruct bigloc = (Locstruct) loclist.get(0);
				String biglocation =  bigloc.getLocation();//得到最大的编号
				String cutloc = biglocation.substring(biglocation.length()-3, biglocation.length());
				String deloc = biglocation.substring(0,biglocation.length()-3);
				int setloc = Integer.parseInt(cutloc)+1;
				String format = "";
				getloc = Integer.toString(setloc);
				format = "%03d";
				getloc = deloc + String.format(format, setloc);//将转行成001或者019等三位数形式的String
			}else{
				String parterloc = locations.getLocation();
				getloc = parterloc + "001";
			}
			locstruct.setLocation(getloc);
		}
		
		//系统
		Listbox listbox = (Listbox) this.getFellow("locsystemlistbox");
		if(listbox!=null && listbox.getSelectedItem()!=null)
		{
			String systemid = (String)listbox.getSelectedItem().getValue();
			locstruct.setSystemid(systemid);
		}
		else
		{
			Messagebox.show("请先建立位置系统，然后再选择一个系统为其添加位置数据！");
			return;
		}

		//弹出新建记录窗口
		HashMap param = new HashMap();
		CommonDialog dialog = (CommonDialog) this.popupDialog(locstruct, "/location/newlocationpopup.xul", param);
		if(dialog.isConfirm())
		{
			Locations newobj = new Locations();
			newobj.setLocation(locstruct.getLocation());
			newobj.setDescription(locstruct.getDescription());
			newobj.setDeptnum(locstruct.getDeptnum());
			newobj.setClassid(locstruct.getClassid());
			newobj.setCraft(locstruct.getCraft());
			newobj.setLocpriority(0L);
			newobj.setType(locstruct.getType());
			newobj.setStatus("已启用");
			newobj.setStatusdate(new Date());
			newobj.setChangeby(this.getUserInfo().getLabornum());
			newobj.setChangedate(new Date());
			
			LocationsSrv locSrv = (LocationsSrv) this.getMainSrv();
			locSrv.addLocations(locstruct, newobj);
			
			//刷新树
			mainTree.expand(ti);
			if(ti!=null)
				ti.setOpen(true);
		}
	}


	/**
	 * 添加到系统
	 * 洪小林  Nov 24, 2009
	 * @throws Exception
	 */
	public void addtosystem()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED) 
		{
			Messagebox.show("进行位置信息修改操作前，请先保存数据！");
			return;
		}
		
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Locstruct locstruct = (Locstruct) ti.getAttribute("locstruct");
			Locstruct newobj = new Locstruct();
			newobj.setHaschild(locstruct.getHaschild());
			newobj.setOrderby(locstruct.getOrderby());
			newobj.setLocation(locstruct.getLocation());
			
			HashMap param = new HashMap();
			CommonDialog dialog = (CommonDialog) this.popupDialog(newobj, "/location/addtosystempopup.xul", param);
			if(dialog.isConfirm())
			{
				LocstructSrv locstructSrv = (LocstructSrv) IBOSrvUtil.getSrv("locstruct");
				locstructSrv.addToSystem(locstruct, newobj);
				
				Messagebox.show("已经将位置添加到了所选系统！");
			}
		}
		else
		{
			Messagebox.show("请至少选择一个位置！");
			return;
		}
		
	}



	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		return false;
	}
	

	/**
	 * 
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		/**
		ListWindow listWnd = (ListWindow)this.getFellow("childLoctionsTable");
		TableList tblist = (TableList)listWnd.getFellow(listWnd.mainList);
		if(tblist.getItemCount() > 0)
		{
			Messagebox.show("该位置存在子位置，请先删除所有子位置！");
			return;
		}
		**/
		Treeitem ti = mainTree.getSelectedItem();
		if(ti==null || ti.getValue()==null)
		{
			Messagebox.show("请在树节点选中要删除的记录！");
			return;
		}

		//Locations locations = (Locations) ti.getValue();
		//调用业务对象的删除方法
		super.delete();
		// 重新构建树
		mainTree.afterDeleteItem();
	}


	
	/**
	 * 配置系统
	 * brianhong  2009-8-26
	 * @throws Exception
	 */
	public void systemcfg()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED) {
			Messagebox.show("进行位置信息修改操作前，请先保存数据！");
			return;
		}
		
		this.popupDialog(this.getMainObject(), "/location/locsystemlist.xul");
	}
	
	/**
	 * 修改位置结构
	 * 洪小林  Nov 24, 2009
	 * @throws Exception
	 */
	public void chgstruct()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("进行位置信息修改操作前，请先保存数据！");
			return;
		}
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Locations locations = (Locations) this.getMainObject();
			this.popupDialog(this.getMainObject(), "/location/chglocstruct.xul", "location='"+locations.getLocation()+"'");
		}
		else
		{
			Messagebox.show("请至少选择一个位置！");
			return;
		}
	}
	
	
	

}
