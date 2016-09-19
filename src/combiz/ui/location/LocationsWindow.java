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
	/////�����������Ϊ locations
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public LocationsWindow()
	{
		super();
	}
	 
	/**
	 * ��д�½���¼
	 * 
	 * Ӣ��˼  Nov 24, 2009
	 * @throws Exception
	 */
	@Override
	public void add() throws Exception
	{
		if(this.objStatus==this.ADDED || this.objStatus == this.MODIFIED)
		{
			Messagebox.show("���ȱ��浱ǰ�޸ģ�");
			return;
		}
		
		Locstruct locstruct = new Locstruct();
		locstruct.setHaschild("��");
		locstruct.setOrderby(0L);
		//����Ĭ��ֵ
		locstruct.setDeptnum(this.getLaborInfo().getDeptnum());
		locstruct.setCraft(this.getLaborInfo().getCraft());
		locstruct.setType("����λ��");
		//���ݸ������ɱ���
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Locations locations = (Locations) ti.getValue();
			locstruct.setParent(locations.getLocation());
			//�Զ�����λ�õ��Զ����
			String getloc = "";
			List loclist = this.getMainSrv().getBaseDao().selectListByHql(" from Locstruct l where l.parent='"+locations.getLocation()+"' order by l.location desc");
			if (loclist.size()>0) {
				Locstruct bigloc = (Locstruct) loclist.get(0);
				String biglocation =  bigloc.getLocation();//�õ����ı��
				String cutloc = biglocation.substring(biglocation.length()-3, biglocation.length());
				String deloc = biglocation.substring(0,biglocation.length()-3);
				int setloc = Integer.parseInt(cutloc)+1;
				String format = "";
				getloc = Integer.toString(setloc);
				format = "%03d";
				getloc = deloc + String.format(format, setloc);//��ת�г�001����019����λ����ʽ��String
			}else{
				String parterloc = locations.getLocation();
				getloc = parterloc + "001";
			}
			locstruct.setLocation(getloc);
		}
		
		//ϵͳ
		Listbox listbox = (Listbox) this.getFellow("locsystemlistbox");
		if(listbox!=null && listbox.getSelectedItem()!=null)
		{
			String systemid = (String)listbox.getSelectedItem().getValue();
			locstruct.setSystemid(systemid);
		}
		else
		{
			Messagebox.show("���Ƚ���λ��ϵͳ��Ȼ����ѡ��һ��ϵͳΪ�����λ�����ݣ�");
			return;
		}

		//�����½���¼����
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
			newobj.setStatus("������");
			newobj.setStatusdate(new Date());
			newobj.setChangeby(this.getUserInfo().getLabornum());
			newobj.setChangedate(new Date());
			
			LocationsSrv locSrv = (LocationsSrv) this.getMainSrv();
			locSrv.addLocations(locstruct, newobj);
			
			//ˢ����
			mainTree.expand(ti);
			if(ti!=null)
				ti.setOpen(true);
		}
	}


	/**
	 * ��ӵ�ϵͳ
	 * ��С��  Nov 24, 2009
	 * @throws Exception
	 */
	public void addtosystem()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED) 
		{
			Messagebox.show("����λ����Ϣ�޸Ĳ���ǰ�����ȱ������ݣ�");
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
				
				Messagebox.show("�Ѿ���λ����ӵ�����ѡϵͳ��");
			}
		}
		else
		{
			Messagebox.show("������ѡ��һ��λ�ã�");
			return;
		}
		
	}



	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		return false;
	}
	

	/**
	 * 
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		/**
		ListWindow listWnd = (ListWindow)this.getFellow("childLoctionsTable");
		TableList tblist = (TableList)listWnd.getFellow(listWnd.mainList);
		if(tblist.getItemCount() > 0)
		{
			Messagebox.show("��λ�ô�����λ�ã�����ɾ��������λ�ã�");
			return;
		}
		**/
		Treeitem ti = mainTree.getSelectedItem();
		if(ti==null || ti.getValue()==null)
		{
			Messagebox.show("�������ڵ�ѡ��Ҫɾ���ļ�¼��");
			return;
		}

		//Locations locations = (Locations) ti.getValue();
		//����ҵ������ɾ������
		super.delete();
		// ���¹�����
		mainTree.afterDeleteItem();
	}


	
	/**
	 * ����ϵͳ
	 * brianhong  2009-8-26
	 * @throws Exception
	 */
	public void systemcfg()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED) {
			Messagebox.show("����λ����Ϣ�޸Ĳ���ǰ�����ȱ������ݣ�");
			return;
		}
		
		this.popupDialog(this.getMainObject(), "/location/locsystemlist.xul");
	}
	
	/**
	 * �޸�λ�ýṹ
	 * ��С��  Nov 24, 2009
	 * @throws Exception
	 */
	public void chgstruct()
	throws Exception
	{
		if (this.getObjStatus() == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("����λ����Ϣ�޸Ĳ���ǰ�����ȱ������ݣ�");
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
			Messagebox.show("������ѡ��һ��λ�ã�");
			return;
		}
	}
	
	
	

}
