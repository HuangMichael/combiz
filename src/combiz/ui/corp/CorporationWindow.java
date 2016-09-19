package combiz.ui.corp;
 
import combiz.domain.corp.Corporation;
import combiz.domain.corp.Department;
import combiz.domain.corp.Labor;
import combiz.domain.ibs.Ibstables;
import combiz.system.IBSServer;
import combiz.system.TableInfo;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.inbasis.zul.Listbox;
import com.inbasis.zul.Listitem;
import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;


public class CorporationWindow extends MainWindow
implements InfWindow
{
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public CorporationWindow()
	{
		super();
	}


	@Override
	public void initData() throws Exception
	{
		super.initData();
	}



	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Corporation newobj = new Corporation();
		newobj.setCurrency("RMB");
		mainObject = newobj;
		return true;
	}



	@Override
	public void delete() throws Exception
	{
		Corporation corp = (Corporation) this.getMainObject();
		HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
		Iterator itor = ibstablesinfo.values().iterator();
		while(itor.hasNext())
		{
			TableInfo tableinfo = (TableInfo) itor.next();
			Ibstables ibstables = tableinfo.getIbstables();
			if(ibstables.getAuthlevel().equals("组织级"))
			{
				int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from " + Util.toFirstUpcase(ibstables.getTablename()) + " where corpnum='"+corp.getCorpnum()+"'");
				if(count>0)
				{
					Messagebox.show("数据表["+ibstables.getTablename()+"]中存在该组织机构的数据，不能删除该组织机构！");
					return;
				}
			}
		}
		super.delete();
	}
	
	
	
	/**
	 * 当点击tab页面的时候执行
	 * 
	 * brianhong  2009-10-12
	 * @param selectedTabid
	 * @throws Exception
	 */
	@Override
	public void onSelectedTab(String selectedTabid) throws Exception
	{
		super.onSelectedTab(selectedTabid);
		if(this.selectedTabid!=null && this.selectedTabid.equals("deptauthtab"))
		{
			DeptAuthTree deptAuthTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
			if(deptAuthTree!=null)
			{
				ListWindow listWnd = (ListWindow) this.getFellow("deptlabortable");
				listWnd.getResultList("1=2");
				deptAuthTree.createNavTree();
			}
			DeptAuthCustTree deptAuthCustTree = (DeptAuthCustTree) this.getFellow("deptAuthCustTree_tree");
			if(deptAuthTree!=null)
			{
				deptAuthCustTree.createNavTree();
				
			}
		}
	}
	
	@Override
	protected void fetchData() 
	throws Exception 
	{
		super.fetchData();
		
	}

	/**
	 * 授权访问所有部门数据
	 * brianhong  2009-10-12
	 * @throws Exception 
	 */
	public void authall() 
	throws Exception
	{
		
		ListWindow listWnd = (ListWindow) this.getFellow("deptlabortable");
		DeptAuthTree authTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
		Treeitem treeitem = authTree.getSelectedItem();
		if(treeitem==null)
			return;
		String type = (String) treeitem.getAttribute("type");
		if(type!=null)
		{
			if(type.equals("department"))
			{
				Department department = (Department) treeitem.getValue();
				if(Messagebox.show("确定要将该组织机构内的所有部门数据的访问权限授予部门[" + department.getDeptnum() +"]下的所有人吗？","权限授予确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					List laborList = this.getMainSrv().getBaseDao().findWithQuery(Labor.class,"deptnum='"+department.getDeptnum()+"'");
					for(int i=0;i<laborList.size();i++)
					{
						Labor labor = (Labor) laborList.get(i);
						labor.setAuthalldept("是");
						labor.setAuthdept(null);
						this.getMainSrv().getBaseDao().updateObject(labor);
					}
				}
			}
			else if(type.equals("labor"))
			{
				Labor labor = (Labor) treeitem.getValue();
				if(Messagebox.show("确定要将该组织机构内的所有部门数据的访问权限授予[" + labor.getLaborname()+"]吗？","权限授予确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					labor.setAuthalldept("是");
					labor.setAuthdept(null);
					this.getMainSrv().getBaseDao().updateObject(labor);
				}
				this.updateAuthList(labor);
			}
			listWnd.refreshData();
		}
	}
	
	/**
	 * 取消授权访问所有数据
	 * brianhong  2009-10-12
	 */
	public void cancelauthall()
	throws Exception
	{
		ListWindow listWnd = (ListWindow) this.getFellow("deptlabortable");
		DeptAuthTree authTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
		Treeitem treeitem = authTree.getSelectedItem();
		if(treeitem==null)
			return;
		String type = (String) treeitem.getAttribute("type");
		if(type!=null)
		{
			if(type.equals("department"))
			{
				Department department = (Department) treeitem.getValue();
				if(Messagebox.show("确定取消部门[" + department.getDeptnum() +"]下所有的人访问所有部门数据的权限吗？","权限取消确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					List laborList = this.getMainSrv().getBaseDao().findWithQuery(Labor.class,"deptnum='"+department.getDeptnum()+"'");
					for(int i=0;i<laborList.size();i++)
					{
						Labor labor = (Labor) laborList.get(i);
						labor.setAuthalldept("否");
						this.getMainSrv().getBaseDao().updateObject(labor);
					}
				}
			}
			else if(type.equals("labor"))
			{
				Labor labor = (Labor) treeitem.getValue();
				if(Messagebox.show("确定取消[" + labor.getLaborname()+"]访问所有部门数据的权限吗？","权限取消确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					labor.setAuthalldept("否");
					this.getMainSrv().getBaseDao().updateObject(labor);
				}
				this.updateAuthList(labor);
			}
			listWnd.refreshData();
		}
	}

	/**
	 * 菜单：初始化组织机构
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void initorg()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再点击该菜单！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("执行该菜单前，请先保存数据！");
			return;
		}
		
		Corporation corp = (Corporation) this.getMainObject();
		if(Messagebox.show("确定要将所有数据的组织机构更新为["+corp.getCorpnum()+"]？","确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
		{
			HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
			Iterator itor = ibstablesinfo.values().iterator();
			while(itor.hasNext())
			{
				TableInfo tableinfo = (TableInfo) itor.next();
				Ibstables ibstables = tableinfo.getIbstables();
				if(ibstables.getAuthlevel().equals("组织级"))
				{
					String updatesql = "update "+ibstables.getTablename().toLowerCase()+" set corpnum='"+corp.getCorpnum()+"'";
					//System.out.println(updatesql);
					this.getMainSrv().getBaseDao().executeSql(updatesql);
				}
			}
			Messagebox.show("组织机构数据已经更新！");
		}
	}
	
	
	
	/**
	 * 添加到授权列表
	 * brianhong  2009-10-19
	 * @throws Exception
	 */
	public void addAuthDept()
	throws Exception
	{
		DeptAuthTree authTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
		if(authTree!=null)
		{
			Treeitem laboritem = authTree.getSelectedItem();
			String type = (String) laboritem.getAttribute("type");
			if(type==null || !type.equals("labor"))
			{
				Messagebox.show("请在树节点上选择一个员工进行进行授权操作！");
				return;
			}
			
			DeptAuthCustTree custTree = (DeptAuthCustTree) this.getFellow("deptAuthCustTree_tree");
			if(custTree!=null)
			{
				if(custTree.getSelectedItems().isEmpty())
				{
					Messagebox.show("请至少选择一个部门添加！");
					return;
				}
				Labor labor = (Labor) laboritem.getValue();
				Iterator iterator = custTree.getSelectedItems().iterator();
				if(iterator.hasNext())
				{
					if(Messagebox.show("确定将选择的部门访问权限授予员工[" + labor.getLaborname()+"]吗？","确认",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.NO)
						return;
					String authdept = null;
					while(iterator.hasNext())
					{
						Treeitem treeitem = (Treeitem) iterator.next();
						if(treeitem.getValue()!=null)
						{
							Department dept = (Department) treeitem.getValue();
							if(Util.isNull(labor.getAuthdept()) || 
									labor.getAuthdept().indexOf("'"+dept.getDeptnum()+"'")<0)
							{
								if(authdept==null)
									authdept = "'" + dept.getDeptnum() + "'";
								else
									authdept = authdept + ",'" +  dept.getDeptnum() + "'";
							}
						}
					}
					if(Util.isNotNull(labor.getAuthdept()))
					{
						if(authdept==null)
							authdept = labor.getAuthdept();
						else
							authdept = authdept + "," + labor.getAuthdept();
					}
					labor.setAuthalldept("否");
					labor.setAuthdept(authdept);
					this.getMainSrv().getBaseDao().updateObject(labor);
					
					//更新人员列表
					ListWindow listWnd = (ListWindow) this.getFellow("deptlabortable");
					listWnd.refreshData();
					//更新授权列表
					this.updateAuthList(labor);
				}
			}
		}
	}
	
	
	
	
	/**
	 * 从授权列表中删除
	 * brianhong  2009-10-19
	 * @throws Exception
	 */
	public void delAuthDept()
	throws Exception
	{
		DeptAuthTree authTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
		if(authTree!=null)
		{
			Treeitem laboritem = authTree.getSelectedItem();
			String type = (String) laboritem.getAttribute("type");
			if(type==null || !type.equals("labor"))
			{
				Messagebox.show("请在树节点上选择一个员工进行进行授权操作！");
				return;
			}
			
			Labor labor = (Labor) laboritem.getValue();
			Listbox listbox = (Listbox) this.getFellow("authdeptlist_listbox");
			if(listbox.getSelectedCount()<=0)
			{
				Messagebox.show("请在已授予的权限列表中选择一条记录后再删除！");
				return;
			}
			if(Util.getBoolean(labor.getAuthalldept()))
			{
				Messagebox.show("该员工目前已授予所有部门访问权限，如果要取消，请点取消所有权限按钮！");
				return;
			}
			Listitem listitem = listbox.getSelectedItem();
			if(listitem!=null)
			{
				String deptnum = (String) listitem.getValue();
				if(deptnum!=null)
				{
					String authdept = labor.getAuthdept();
					if(Util.isNotNull(authdept))
					{
						int pos = authdept.indexOf("'" + deptnum +"'");
						if(pos==0)
						{
							if(("'" + deptnum +"'").equals(authdept))
								authdept = null;
							else
								authdept = authdept.substring(pos + deptnum.length() + 3);
							labor.setAuthdept(authdept);
							this.getMainSrv().getBaseDao().updateObject(labor);
							//更新授权列表
							this.updateAuthList(labor);
						}
						else if(pos>0)
						{
							String authdept2 = authdept.substring(0,pos-1) + authdept.substring(pos + deptnum.length() + 2);
							labor.setAuthdept(authdept2);
							this.getMainSrv().getBaseDao().updateObject(labor);
							//更新授权列表
							this.updateAuthList(labor);
						}
					}
				}
			}
		}
		
	}
	
	
	/**
	 * 更新授权列表
	 * brianhong  2009-10-19
	 * @param labor
	 * @throws Exception
	 */
	public void updateAuthList(Labor labor)
	throws Exception
	{
		Listbox listbox = (Listbox) this.getFellow("authdeptlist_listbox");
		listbox.getChildren().clear();
		
		String authall = labor.getAuthalldept();
		String authdept = labor.getAuthdept();
		if(Util.isNotNull(authall) && authall.equals("是"))
		{
			Listitem listitem = new Listitem("（授权访问所有部门）");
			listitem.setStyle("color:red");
			listbox.appendChild(listitem);
		}
		else
		{
			if(Util.isNotNull(authdept))
			{
				//authdept = authdept.substring(1, authdept.length() - 1);
				String[] authdepts = authdept.split(",");
				for(int i=0;i<authdepts.length;i++)
				{
					String deptnum = authdepts[i].substring(1, authdepts[i].length() - 1);
					if(Util.isNotNull(deptnum))
					{
						Listitem listitem = new Listitem(deptnum);
						listitem.setValue(deptnum);
						listitem.setStyle("color:red");
						listbox.appendChild(listitem);
					}
				}
			}
			else
			{
				Listitem listitem = new Listitem("【"+labor.getSitenum()+"】及以下的所有子部门（默认授权）");
				listitem.setStyle("color:red");
				listbox.appendChild(listitem);
			}
		}
	}
	
	
	/**
	 * 修改默认顶级授权
	 * 洪小林  Jan 12, 2010
	 * @throws Exception
	 */
	public void chgDefaultAuth()
	throws Exception
	{
		DeptAuthTree authTree = (DeptAuthTree) this.getFellow("deptAuthTree_tree");
		if(authTree!=null)
		{
			Treeitem laboritem = authTree.getSelectedItem();
			String type = (String) laboritem.getAttribute("type");
			if(type==null || !type.equals("labor"))
			{
				Messagebox.show("请在树节点上选择一个员工进行进行授权操作！");
				return;
			}
			Labor labor = (Labor) laboritem.getValue();
			this.popupDialog(labor, "/corp/chgdefaultauth.xul");
		}
	}
	
	
	
}
