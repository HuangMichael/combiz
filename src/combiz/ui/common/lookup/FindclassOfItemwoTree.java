package combiz.ui.common.lookup;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.system.ui.common.LookupTree;
import combiz.system.ui.common.MainTree.openNode;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindclassOfItemwoTree extends LookupTree
{
	ClassificationSrv classificationSrv;
	
	public FindclassOfItemwoTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("300px");
		this.setVflex(true);
	}
	
	
	/**
	 * 创建初始树
	 * @throws Exception
	 * 作者：ljh 日期：2008-03-10
	 */
	public void createRoot()
	throws Exception
	{
//		清除树，重新构建
		this.getChildren().clear();
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
		String whereStr = "classtype='物资' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
		List list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_itemclass.appendChild(tc);
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
		whereStr = "classtype='资产' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
			list = classificationSrv.findWithQuery("classtype='资产' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_eqclass.appendChild(tc);
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
		whereStr = "classtype='工具' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
		list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有数据！"));
			treeitem_toolclass.appendChild(tc);
		}
		else
		{
			this.createChildRoot(list, treeitem_toolclass, "工具");
		}
		
	}
	//由createroot方法调用
	private void createChildRoot(List list,Treeitem parentTi,String classtype)
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
	}
	
	
	/**
	 * 展开树节点
	 * @param parentitem
	 * 作者：ljh 日期：2008-03-11
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
			
			Classification parentClass = (Classification)parentitem.getValue();
			String whereStr = "classtype='"+parentClass.getClasstype()+"' and parent = '"+parentClass.getClassid()+"'";
			if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
				whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
			List list = classificationSrv.findWithQuery(whereStr,"orderby");
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
	 * 
	 * @throws Exception
	 * @ljh  2008-3-11  下午01:00:02
	 */
	public void onSelect() throws Exception
	{
		Treeitem treeitem = this.getSelectedItem();
		if(treeitem==null || treeitem.getValue()==null)
			return;
		Classification classif = (Classification) treeitem.getValue();
		ListWindow mainWnd = (ListWindow)this.getFellow("classificationofitem");
		mainWnd.setTitle(classif.getDescription()+":"+classif.getClassid());
		/****************************************************
		 * 如果是中间树，比如人员选择的时候，需要在该处加入人员的过滤条件this.getQueryString()
		 */
		String whereStr = "classid='"+classif.getClassid()+"'";
		if(Util.isNotNull(this.getQueryString()))
			whereStr = whereStr + " and " + this.getQueryString();
		/******************************************************/
		mainWnd.setQueryString(whereStr);
		mainWnd.setOrderby("");
		mainWnd.refreshData();
	}
}
