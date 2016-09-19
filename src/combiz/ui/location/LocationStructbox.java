package combiz.ui.location;

import combiz.domain.ibs.Ibslisttable;
import combiz.domain.ibs.Ibstablecols;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.system.IBSServer;
import combiz.system.ListInfo;
import combiz.system.ui.RecWindow;
import combiz.system.ui.common.IBandbox;
import combiz.system.ui.common.IStructbox;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

public class LocationStructbox extends IStructbox
{
	private RecWindow recWindow;
	//���ֶ���
	private String band;
	
	
	public LocationStructbox()
	{
		super();
		this.setCols(50);
	}
	
	/**
	 * �������ȡ���ݿ��е�listid������lookup�ĳ�ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-3-4
	 */
	public void onCreate()
	throws Exception
	{
		if(Util.isNull(this.getBand()))
		{
			Messagebox.show("����ָ��band���ԣ�");
			return;
		}
		
		//��ʼ���Ҽ��˵�
		recWindow = (RecWindow)this.getFellow("mainWnd");
		this.setId(this.getBand() + ".structbox");
		
		//ֻ��
		this.setReadonly(true);
		this.setStyle("background-color: #dddddd");
		
	}

	/**
	 * 
	 * ��С��  Dec 21, 2009
	 * @throws Exception
	 */
	public void setStructValue()
	throws Exception
	{
		String textValue = "";
		String fldname = Util.fieldidToColname(this.getBand());  //���ֶ���
		int pos = this.getBand().indexOf(".");
		String fldtable = this.getBand().toUpperCase();  //���ֶ����ڱ�
		if(pos>0)
			fldtable = this.getBand().substring(0,pos).toUpperCase();
		Component cmp = this.getFellow(this.getBand());
		if(cmp instanceof IBandbox)
		{
			IBandbox bandbox = (IBandbox)cmp;
			String fldvalue = bandbox.getValue();  //�ṹ���ڵ�ֵ
			if(Util.isNotNull(fldvalue))  //û��ֵ�Ļ�������ʾ
			{
				Ibstablecols colinfo = IBSServer.getIBSServer().getIbstablecols(fldtable, fldname);
				if(colinfo!=null)
				{
					String listname = colinfo.getListname();
					ListInfo listinfo = (ListInfo) IBSServer.getIBSServer().getIbslistinfo().get(listname);
					Ibslisttable listtable = listinfo.getIbslisttable();
					if(listtable!=null)
					{
						//String trTableName = listtable.getTablename(); //�ṹ����
						//String trFldName = listtable.getTargetfield().toLowerCase();  //�ṹ���ؼ��ֶ�
						//TableInfo tableInfo = IBSServer.getIBSServer().getTableInfo(trTableName);
						//String className = tableInfo.getIbstables().getClassname();
						//Class mainClazz = Class.forName(className);
						List objList = recWindow.getMainSrv().getBaseDao().findWithQuery(Locstruct.class, "location='"+fldvalue+"' and systemid=(select t.systemid from Locsystem t where t.isdefault='��')");
						if(objList.size()>0)
						{
							Locstruct locs = (Locstruct) objList.get(0);
							Locations loc = (Locations) recWindow.getMainSrv().getBaseDao().findUniqueBy(Locations.class, "location", locs.getLocation());
							if(loc!=null)
							{
								textValue = loc.getDescription();
								this.setValue(this.attachParentString(locs, textValue));
								return;
							}
						}
					}
				}
			}
		}
		this.setValue(null);
	}
	
	/**
	 * 
	 * ��С��  Dec 21, 2009
	 * @param mainObj
	 * @param trFldName
	 * @param textValue
	 * @return
	 * @throws Exception
	 */
	private String attachParentString(Locstruct locs, String textValue)
	throws Exception
	{
		String parentValue = (String) Util.getValueFromObject(locs, "parent");
		if(Util.isNotNull(parentValue))
		{
			List parentList = recWindow.getMainSrv().getBaseDao().findWithQuery(Locstruct.class, "location='"+locs.getParent()+"' and systemid=(select t.systemid from Locsystem t where t.isdefault='��')");
			if(parentList.size()>0)
			{
				Locstruct parent = (Locstruct) parentList.get(0);
				Locations loc = (Locations) recWindow.getMainSrv().getBaseDao().findUniqueBy(Locations.class, "location", parent.getLocation());
				if(loc!=null)
				{
					String value = loc.getDescription();
				    textValue = value + "\\" + textValue;
					
					return this.attachParentString(parent, textValue);
				}
			}
			else
			{
				textValue = "\\\\" + textValue;
				return textValue;
			}
		}
		return "\\\\" + textValue;
	}
	

	public String getBand()
	{
		return band;
	}

	public void setBand(String band)
	{
		this.band = band;
	}
}
