package combiz.ui.basedata;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TMainWindow;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class ClassTree extends MainTree
{
	TMainWindow mainWnd;
	ClassificationSrv classificationSrv;
	
	public ClassTree() 
	{
		super();
	}

	public void onCreate()
	{
		this.setVflex(true);
		
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 120) +"px");
	}
	
	
	/**
	 * 创建初始树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//清除树，重新构建
		this.getChildren().clear();
		
		mainWnd = (TMainWindow) this.getFellow("mainWnd");
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		Treechildren topTc = new Treechildren();
		this.appendChild(topTc);
		//创建分类抬头
		Treeitem treeitem_itemclass = new Treeitem();
		Treerow treerow = new Treerow();
		treerow.appendChild(new Treecell("物资分类"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_itemclass.appendChild(treerow);
		treeitem_itemclass.setImage("/images/btn_unitem.gif");
		topTc.appendChild(treeitem_itemclass);
		List list = classificationSrv.findWithQuery("classtype='物资' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_itemclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_itemclass, "物资");
		}
		
		
		//设备分类
		Treeitem treeitem_eqclass = new Treeitem();
		treerow = new Treerow();
		treerow.appendChild(new Treecell("设备分类"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_eqclass.appendChild(treerow);
		treeitem_eqclass.setImage("/images/img_location.gif");
		topTc.appendChild(treeitem_eqclass);
		list = classificationSrv.findWithQuery("classtype='资产' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_eqclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_eqclass, "资产");
		}
		
		
		//工具分类
		Treeitem treeitem_toolclass = new Treeitem();
		treerow = new Treerow();
		treerow.appendChild(new Treecell("工具分类"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_toolclass.appendChild(treerow);
		treeitem_toolclass.setImage("/images/btn_uneqnum.gif");
		topTc.appendChild(treeitem_toolclass);
		list = classificationSrv.findWithQuery("classtype='工具' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_toolclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_toolclass, "工具");
		}
		
	}
	//由createroot方法调用
	private void createChildRoot(List list,Treeitem parentTi, String classtype)
	throws Exception
	{
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//设置列显示的最大字符数
		//tcols.appendChild(tcol);
		//this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Classification classification = (Classification)list.get(i);
			ti = new Treeitem(classification.getDescription()+":"+classification.getClassid()); 
			ti.setValue(classification);
			//ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(classification.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			tc.appendChild(ti);
		}
		parentTi.appendChild(tc);
		
		mainWnd.fetchData(null);
	}
	
	/**
	 * 展开树节点
	 * @param parentitem
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		if(parentitem!=null && parentitem.getValue() != null) //如果没有父级
		{
			//*********第一种：每次都删除后重新从数据库中取数
			Treechildren tc = parentitem.getTreechildren();
			if(tc!=null && tc.getChildren().size()>0)
				tc.getChildren().clear();
			//*********第二种：第一次点击是从数据库中取数据，以后就不删除也不取了
			//if(tc.getChildren().size()>0)
			//	return;
			
			Classification classparent = (Classification)parentitem.getValue();
			List list = classificationSrv.findWithQuery("classtype='"+classparent.getClasstype()+"' and parent = '"+classparent.getClassid()+"'","orderby");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Classification classification = (Classification)list.get(i);
				ti = new Treeitem(classification.getDescription()+":"+classification.getClassid()); 
				ti.setValue(classification);
				//ti.setImage("/images/img_location.gif");
				ti.setOpen(false);
				//很重要！这段不加上，树的自动滚动就出不来。
				Treerow treerow = (Treerow)ti.getChildren().get(0);
				Treecell treecell = (Treecell)treerow.getChildren().get(0);
				treecell.setStyle("white-space:nowrap;");
				//////////////////////////////////////////////
				if(Util.getBoolean(classification.getHaschild()))
				{
					Treechildren nextChild = new Treechildren();
					ti.appendChild(nextChild);
					ti.addEventListener("onOpen", new openNode());
				}
				tc.appendChild(ti);
			}
		}
		else //重新刷新树
		{
			this.createRoot();
		}
	}

	
	/**
	 * 选择事件
	 * 
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void onSelect() 
	throws Exception
	{
		Treeitem item = getSelectedItem();
		mainWnd.fetchData(item);
	}

	
	/**
	 * 重新构建树节点
	 * 
	 * 作者：洪小林 日期：2007-4-25
	 * @throws Exception 
	 */
	public void afterModifyItem()
	throws Exception
	{
		Treeitem ti = getSelectedItem();
		if(ti==null)
			return;
		Classification classification = (Classification)mainWnd.getMainObject();
		ti.setValue(classification);
		ti.setLabel(classification.getDescription()+":"+classification.getClassid());
		//重新选中
		this.onSelect();
	}
	
	
}
