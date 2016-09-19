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
	//绑定字段名
	private String band;
	
	
	public LocationStructbox()
	{
		super();
		this.setCols(50);
	}
	
	/**
	 * 在这里获取数据库中的listid，进行lookup的初始化
	 * @throws Exception
	 * 作者：洪小林 日期：2007-3-4
	 */
	public void onCreate()
	throws Exception
	{
		if(Util.isNull(this.getBand()))
		{
			Messagebox.show("必须指定band属性！");
			return;
		}
		
		//初始化右键菜单
		recWindow = (RecWindow)this.getFellow("mainWnd");
		this.setId(this.getBand() + ".structbox");
		
		//只读
		this.setReadonly(true);
		this.setStyle("background-color: #dddddd");
		
	}

	/**
	 * 
	 * 洪小林  Dec 21, 2009
	 * @throws Exception
	 */
	public void setStructValue()
	throws Exception
	{
		String textValue = "";
		String fldname = Util.fieldidToColname(this.getBand());  //绑定字段名
		int pos = this.getBand().indexOf(".");
		String fldtable = this.getBand().toUpperCase();  //绑定字段所在表
		if(pos>0)
			fldtable = this.getBand().substring(0,pos).toUpperCase();
		Component cmp = this.getFellow(this.getBand());
		if(cmp instanceof IBandbox)
		{
			IBandbox bandbox = (IBandbox)cmp;
			String fldvalue = bandbox.getValue();  //结构树节点值
			if(Util.isNotNull(fldvalue))  //没有值的话，不显示
			{
				Ibstablecols colinfo = IBSServer.getIBSServer().getIbstablecols(fldtable, fldname);
				if(colinfo!=null)
				{
					String listname = colinfo.getListname();
					ListInfo listinfo = (ListInfo) IBSServer.getIBSServer().getIbslistinfo().get(listname);
					Ibslisttable listtable = listinfo.getIbslisttable();
					if(listtable!=null)
					{
						//String trTableName = listtable.getTablename(); //结构树表
						//String trFldName = listtable.getTargetfield().toLowerCase();  //结构树关键字段
						//TableInfo tableInfo = IBSServer.getIBSServer().getTableInfo(trTableName);
						//String className = tableInfo.getIbstables().getClassname();
						//Class mainClazz = Class.forName(className);
						List objList = recWindow.getMainSrv().getBaseDao().findWithQuery(Locstruct.class, "location='"+fldvalue+"' and systemid=(select t.systemid from Locsystem t where t.isdefault='是')");
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
	 * 洪小林  Dec 21, 2009
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
			List parentList = recWindow.getMainSrv().getBaseDao().findWithQuery(Locstruct.class, "location='"+locs.getParent()+"' and systemid=(select t.systemid from Locsystem t where t.isdefault='是')");
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
