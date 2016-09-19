package combiz.ui.common.lookup;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.common.LookupTree;
import combiz.system.ui.common.LookupTree.openNode;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class FindClassificationLocTree extends LookupTree
{   
	private String type;
	ClassificationSrv classificationSrv;
	
	public FindClassificationLocTree() 
	{
		super();
	}

	public void onCreate() throws Exception
	{
		this.setHeight("300px");
		this.setVflex(true);
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
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
		
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		/****************************************************
		 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
		 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
		 */
		String whereStr = "parent is null and classtype='资产'";
		if(this.getQueryString()!=null)
			whereStr = whereStr + " and " +  this.getQueryString();
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
			Classification classification = (Classification)list.get(i);
			ti = new Treeitem(classification.getDescription()+":"+classification.getClassid());
			ti.setValue(classification);
			ti.setImage("/images/img_location.gif");
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
		this.appendChild(tc);
		
		if(this.getItemCount()>0)
		{
			Treeitem treeitem = (Treeitem)this.getItems().iterator().next();
			this.selectItem(treeitem);
		}
	}
	
	
	/**
	 * 创建带有原始值的树
	 * @throws Exception
	 * 作者：洪小林 日期：2007-4-25
	 */
	public void createRoot(String value)
	throws Exception
	{
		this.getChildren().clear();
		List list = classificationSrv.findWithQuery("classid='"+value+"' and classtype='资产'");
		if(list.size()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("不存在分类["+value+"]！"));
			this.appendChild(tc);
			return;
		}
		Classification classification = (Classification) list.get(0);
		Treeitem ti = new Treeitem(classification.getDescription()+"["+classification.getClassid()+"]");
		ti.setValue(classification);
		ti.setImage("/images/img_location.gif");
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
		//最底层的tc
		Treechildren bottomTc = new Treechildren();
		bottomTc.appendChild(ti);

		//递归调用创建父级
		Treechildren topTc = null;
		Classification parent = this.getParent(classification);
		while(parent!=null)
		{
			topTc = this.createUpItem(parent, bottomTc);
			parent = this.getParent(parent);
			bottomTc = topTc;
		}

		//最顶层的tc
		if(topTc==null)
			topTc = bottomTc;
		this.appendChild(topTc);
		this.selectItem(ti);
	}
	
	/**
	 * 获取位置父级
	 * @return 作者：洪小林 日期：2007-5-4
	 */
	private Classification getParent(Classification classification)
	throws Exception
	{
		List parentList = classificationSrv.findWithQuery("classid = '" + classification.getParent() + "' and classtype='资产'");
		if (parentList.size() > 0)
		{
			Classification parent = (Classification) parentList.get(0);
			return parent;
		}
		return null;
	}
	
	//由createRoot方法调用递归
	private Treechildren createUpItem(Classification classification, Treechildren childTc)
	throws Exception
	{
		Treechildren tc = new Treechildren();
		Treeitem ti = new Treeitem(classification.getDescription()+"["+classification.getClassid()+"]");
		ti.setValue(classification);
		ti.setImage("/images/img_location.gif");
		ti.setOpen(true);
		//很重要！这段不加上，树的自动滚动就出不来。
		Treerow treerow = (Treerow)ti.getChildren().get(0);
		Treecell treecell = (Treecell)treerow.getChildren().get(0);
		treecell.setStyle("white-space:nowrap;");
		//////////////////////////////////////////////
		ti.addEventListener("onOpen", new openNode());
		ti.appendChild(childTc);
		tc.appendChild(ti);
		
		return tc;
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
			
			Classification parentClass = (Classification)parentitem.getValue();
			/****************************************************
			 * 如果是选择树，如部门选择树，那么在过滤条件上必须加上this.getQueryString()
			 * 如果是中间树，比如人员选择的时候的部门树，那么在过滤条件上必须加上this.getTreeQueryString("树的主表名-大写")
			 */
			String whereStr = "parent = '"+parentClass.getClassid()+"' and classtype='资产'";
			if(this.getQueryString()!=null)
				whereStr = whereStr + " and " + this.getQueryString();
			List list = classificationSrv.findWithQuery(whereStr,"orderby");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Classification classification = (Classification)list.get(i);
				ti = new Treeitem(classification.getDescription()+":"+classification.getClassid());
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
}
