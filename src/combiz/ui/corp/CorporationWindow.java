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
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
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
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
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
			if(ibstables.getAuthlevel().equals("��֯��"))
			{
				int count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from " + Util.toFirstUpcase(ibstables.getTablename()) + " where corpnum='"+corp.getCorpnum()+"'");
				if(count>0)
				{
					Messagebox.show("���ݱ�["+ibstables.getTablename()+"]�д��ڸ���֯���������ݣ�����ɾ������֯������");
					return;
				}
			}
		}
		super.delete();
	}
	
	
	
	/**
	 * �����tabҳ���ʱ��ִ��
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
	 * ��Ȩ�������в�������
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
				if(Messagebox.show("ȷ��Ҫ������֯�����ڵ����в������ݵķ���Ȩ�����貿��[" + department.getDeptnum() +"]�µ���������","Ȩ������ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					List laborList = this.getMainSrv().getBaseDao().findWithQuery(Labor.class,"deptnum='"+department.getDeptnum()+"'");
					for(int i=0;i<laborList.size();i++)
					{
						Labor labor = (Labor) laborList.get(i);
						labor.setAuthalldept("��");
						labor.setAuthdept(null);
						this.getMainSrv().getBaseDao().updateObject(labor);
					}
				}
			}
			else if(type.equals("labor"))
			{
				Labor labor = (Labor) treeitem.getValue();
				if(Messagebox.show("ȷ��Ҫ������֯�����ڵ����в������ݵķ���Ȩ������[" + labor.getLaborname()+"]��","Ȩ������ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					labor.setAuthalldept("��");
					labor.setAuthdept(null);
					this.getMainSrv().getBaseDao().updateObject(labor);
				}
				this.updateAuthList(labor);
			}
			listWnd.refreshData();
		}
	}
	
	/**
	 * ȡ����Ȩ������������
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
				if(Messagebox.show("ȷ��ȡ������[" + department.getDeptnum() +"]�����е��˷������в������ݵ�Ȩ����","Ȩ��ȡ��ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					List laborList = this.getMainSrv().getBaseDao().findWithQuery(Labor.class,"deptnum='"+department.getDeptnum()+"'");
					for(int i=0;i<laborList.size();i++)
					{
						Labor labor = (Labor) laborList.get(i);
						labor.setAuthalldept("��");
						this.getMainSrv().getBaseDao().updateObject(labor);
					}
				}
			}
			else if(type.equals("labor"))
			{
				Labor labor = (Labor) treeitem.getValue();
				if(Messagebox.show("ȷ��ȡ��[" + labor.getLaborname()+"]�������в������ݵ�Ȩ����","Ȩ��ȡ��ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
				{
					labor.setAuthalldept("��");
					this.getMainSrv().getBaseDao().updateObject(labor);
				}
				this.updateAuthList(labor);
			}
			listWnd.refreshData();
		}
	}

	/**
	 * �˵�����ʼ����֯����
	 * brianhong  2009-10-12
	 * @throws Exception
	 */
	public void initorg()
	throws Exception
	{
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٵ���ò˵���");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("ִ�иò˵�ǰ�����ȱ������ݣ�");
			return;
		}
		
		Corporation corp = (Corporation) this.getMainObject();
		if(Messagebox.show("ȷ��Ҫ���������ݵ���֯��������Ϊ["+corp.getCorpnum()+"]��","ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
		{
			HashMap ibstablesinfo = IBSServer.getIBSServer().getIbstablesinfo();
			Iterator itor = ibstablesinfo.values().iterator();
			while(itor.hasNext())
			{
				TableInfo tableinfo = (TableInfo) itor.next();
				Ibstables ibstables = tableinfo.getIbstables();
				if(ibstables.getAuthlevel().equals("��֯��"))
				{
					String updatesql = "update "+ibstables.getTablename().toLowerCase()+" set corpnum='"+corp.getCorpnum()+"'";
					//System.out.println(updatesql);
					this.getMainSrv().getBaseDao().executeSql(updatesql);
				}
			}
			Messagebox.show("��֯���������Ѿ����£�");
		}
	}
	
	
	
	/**
	 * ��ӵ���Ȩ�б�
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
				Messagebox.show("�������ڵ���ѡ��һ��Ա�����н�����Ȩ������");
				return;
			}
			
			DeptAuthCustTree custTree = (DeptAuthCustTree) this.getFellow("deptAuthCustTree_tree");
			if(custTree!=null)
			{
				if(custTree.getSelectedItems().isEmpty())
				{
					Messagebox.show("������ѡ��һ��������ӣ�");
					return;
				}
				Labor labor = (Labor) laboritem.getValue();
				Iterator iterator = custTree.getSelectedItems().iterator();
				if(iterator.hasNext())
				{
					if(Messagebox.show("ȷ����ѡ��Ĳ��ŷ���Ȩ������Ա��[" + labor.getLaborname()+"]��","ȷ��",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION) == Messagebox.NO)
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
					labor.setAuthalldept("��");
					labor.setAuthdept(authdept);
					this.getMainSrv().getBaseDao().updateObject(labor);
					
					//������Ա�б�
					ListWindow listWnd = (ListWindow) this.getFellow("deptlabortable");
					listWnd.refreshData();
					//������Ȩ�б�
					this.updateAuthList(labor);
				}
			}
		}
	}
	
	
	
	
	/**
	 * ����Ȩ�б���ɾ��
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
				Messagebox.show("�������ڵ���ѡ��һ��Ա�����н�����Ȩ������");
				return;
			}
			
			Labor labor = (Labor) laboritem.getValue();
			Listbox listbox = (Listbox) this.getFellow("authdeptlist_listbox");
			if(listbox.getSelectedCount()<=0)
			{
				Messagebox.show("�����������Ȩ���б���ѡ��һ����¼����ɾ����");
				return;
			}
			if(Util.getBoolean(labor.getAuthalldept()))
			{
				Messagebox.show("��Ա��Ŀǰ���������в��ŷ���Ȩ�ޣ����Ҫȡ�������ȡ������Ȩ�ް�ť��");
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
							//������Ȩ�б�
							this.updateAuthList(labor);
						}
						else if(pos>0)
						{
							String authdept2 = authdept.substring(0,pos-1) + authdept.substring(pos + deptnum.length() + 2);
							labor.setAuthdept(authdept2);
							this.getMainSrv().getBaseDao().updateObject(labor);
							//������Ȩ�б�
							this.updateAuthList(labor);
						}
					}
				}
			}
		}
		
	}
	
	
	/**
	 * ������Ȩ�б�
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
		if(Util.isNotNull(authall) && authall.equals("��"))
		{
			Listitem listitem = new Listitem("����Ȩ�������в��ţ�");
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
				Listitem listitem = new Listitem("��"+labor.getSitenum()+"�������µ������Ӳ��ţ�Ĭ����Ȩ��");
				listitem.setStyle("color:red");
				listbox.appendChild(listitem);
			}
		}
	}
	
	
	/**
	 * �޸�Ĭ�϶�����Ȩ
	 * ��С��  Jan 12, 2010
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
				Messagebox.show("�������ڵ���ѡ��һ��Ա�����н�����Ȩ������");
				return;
			}
			Labor labor = (Labor) laboritem.getValue();
			this.popupDialog(labor, "/corp/chgdefaultauth.xul");
		}
	}
	
	
	
}
