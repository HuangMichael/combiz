package combiz.ui.location;

import combiz.business.location.LocsystemSrv;
import combiz.domain.location.Locations;
import combiz.domain.location.Locstruct;
import combiz.domain.location.Locsystem;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.common.LookupTree;

import java.util.List;

import com.inbasis.zul.Listbox;
import com.inbasis.zul.Listitem;
import com.inbasis.zul.Treeitem;

public class LocsystemListbox extends Listbox
{
	private String defaultSystemid;
	private Treeitem curTreeitem;
	
	public LocsystemListbox()
	{
		super();
	}
	
	public void onCreate()
	{
		this.setId("locsystemlistbox");
		this.setMold("select");
		//从数据库获取LOCSYSTEM
		LocsystemSrv locsystemSrv = (LocsystemSrv)IBOSrvUtil.getSrv("locsystem");
		List systemlist = null;
		try {
			systemlist = locsystemSrv.findWithQuery(Locsystem.class,"","isdefault desc");
		} catch (Exception e) {
			e.printStackTrace();
		} //获取默认的系统
		for(int i=0;i<systemlist.size();i++)
		{
			Locsystem system = (Locsystem)systemlist.get(i);
			String systemid = system.getSystemid();
			Listitem li = new Listitem(system.getDescription());
			li.setValue(systemid);
			li.setTooltiptext(system.getDescription());
			this.appendChild(li);
			if(system.getIsdefault().equalsIgnoreCase("是"))
			{
				this.setSelectedItem(li);
				defaultSystemid = systemid;
			}
		}
	}
	
	public void onSelect() 
	throws Exception
	{
		LocationsTree loctree = (LocationsTree)this.getFellow("mainTree");
		if(loctree!=null)  //主窗口界面
		{
			Treeitem treeitem = loctree.getSelectedItem();
			if(treeitem!=null && treeitem.getValue()!=null)
				curTreeitem = treeitem;
			
			if(curTreeitem==null || curTreeitem.getValue()==null)
			{
				loctree.createRoot();
			}
			else
			{
				String systemid = (String) this.getSelectedItem().getValue();
				/*if(systemid.equals(this.defaultSystemid))  //如果是默认的设备系统
					loctree.createRoot();
				else
				{*/
					Locstruct locstruct = (Locstruct) curTreeitem.getAttribute("locstruct");
					loctree.createUpTree(locstruct, systemid, (Locations)curTreeitem.getValue());
				//}
			}
		}
		else //在lookup页面中使用
		{
			LookupTree fLocTree = (LookupTree) this.getFellow("lookupTree");
			if(fLocTree!=null)
				fLocTree.createRoot();
		}
	}
}
