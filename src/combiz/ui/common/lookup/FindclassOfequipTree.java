package combiz.ui.common.lookup;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.ListWindow;
import combiz.system.ui.common.LookupTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindclassOfequipTree extends LookupTree
{
	ClassificationSrv classificationSrv;
	
	public FindclassOfequipTree() 
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
		//清除树，重新构建
		this.getChildren().clear();
		
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		/****************************************************
		 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
		 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
		 */
		String whereStr = "classtype='资产' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and " + this.getTreeQueryString("CLASSIFICATION");
		List list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有分类数据！"));
			this.appendChild(tc);
			return;
		}
		
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//设置列显示的最大字符数
		//tcols.appendChild(tcol);
		//this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Classification classfication = (Classification)list.get(i);
			ti = new Treeitem(classfication.getDescription()+":"+classfication.getClassid());  //+":"+lochiery.getLocation()
			ti.setValue(classfication);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			if(Util.getBoolean(classfication.getHaschild()))
			{
				Treechildren nextChild = new Treechildren();
				ti.appendChild(nextChild);
				ti.addEventListener("onOpen", new openNode());
				this.expand(ti);
			}
			tc.appendChild(ti);
		}
		this.appendChild(tc);
		
		if(this.getItemCount()>0)
		{
			Treeitem treeitem = (Treeitem)this.getItems().iterator().next();
			this.selectItem(treeitem);
			this.onSelect();
		}
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
			
			Classification lochparent = (Classification)parentitem.getValue();
			String whereStr = "classtype ='资产' and parent = '"+lochparent.getClassid()+"'";
			if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
				whereStr = whereStr + " and " + this.getTreeQueryString("CLASSIFICATION");
			List list = classificationSrv.findWithQuery(whereStr,"orderby");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Classification classification = (Classification)list.get(i);
				String locdesc = classification.getDescription();
				if(locdesc==null)
					locdesc = "";
				ti = new Treeitem(locdesc+":"+classification.getClassid()); //
				ti.setValue(classification);
				ti.setImage("/images/img_location.gif");
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
		Classification classification = (Classification) treeitem.getValue();
		String classid = classification.getClassid();
		ListWindow mainWnd = (ListWindow)this.getFellow("classificationofitem");
		mainWnd.setTitle(classid);
		/****************************************************
		 * 如果是中间树，比如人员选择的时候，需要在该处加入人员的过滤条件this.getQueryString()
		 * 
		 */
		String whereStr = "classid='"+classid+"'";
		if(this.getQueryString()!=null)
			whereStr = whereStr + " and " + this.getQueryString();
		/******************************************************/
		mainWnd.setQueryString(whereStr);
		mainWnd.setOrderby("");
		mainWnd.refreshData();
	}
}
