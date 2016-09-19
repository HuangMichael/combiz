package combiz.ui.doclib;

import combiz.business.doclib.DoclibarySrv;
import combiz.domain.doclib.Doclibary;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class DoclibaryTree extends MainTree
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DoclibaryWindow mainWnd;
	DoclibarySrv doclibarySrv;
	
	public DoclibaryTree() 
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
		
		mainWnd = (DoclibaryWindow)this.getFellow("mainWnd");
		doclibarySrv = (DoclibarySrv)IBOSrvUtil.getSrv("doclibary");
		//classificationSrv.setOrderBy("location");
		List list = doclibarySrv.findWithQuery("parent is null");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("没有分类数据！"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
			return;
		}
		doclibarySrv.setOrderBy("orderby");
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//设置列显示的最大字符数
		//tcols.appendChild(tcol);
		//this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Doclibary doclibary = (Doclibary)list.get(i);
			ti = new Treeitem(doclibary.getLibnum());  //+":"+lochiery.getLocation()
			ti.setValue(doclibary);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//很重要！这段不加上，树的自动滚动就出不来。
			Treerow treerow = (Treerow)ti.getChildren().get(0);
			Treecell treecell = (Treecell)treerow.getChildren().get(0);
			treecell.setStyle("white-space:nowrap;");
			//////////////////////////////////////////////
			List listchildren = doclibarySrv.findWithQuery("parent = '" + doclibary.getLibnum() + "'");
			if(listchildren!=null && !listchildren.isEmpty())
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
			mainWnd.fetchData(treeitem);
		}
		else
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
			
			Doclibary doclibparent = (Doclibary)parentitem.getValue();
			List list = doclibarySrv.findWithQuery("parent = '"+doclibparent.getLibnum()+"'");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Doclibary doclibary = (Doclibary)list.get(i);
				ti = new Treeitem(doclibary.getLibnum()); //
				ti.setValue(doclibary);
				ti.setImage("/images/img_location.gif");
				ti.setOpen(false);
				//很重要！这段不加上，树的自动滚动就出不来。
				Treerow treerow = (Treerow)ti.getChildren().get(0);
				Treecell treecell = (Treecell)treerow.getChildren().get(0);
				treecell.setStyle("white-space:nowrap;");
				//////////////////////////////////////////////
				List listchildren = doclibarySrv.findWithQuery("parent = '" + doclibary.getLibnum() + "'");
				if(listchildren!=null && !listchildren.isEmpty())
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
		Treeitem item = getSelectedItem();
		if(item==null)
			return;
		Doclibary Doclibary = (Doclibary)mainWnd.getMainObject();
		item.setValue(Doclibary);
		item.setLabel(Doclibary.getLibnum());
		this.onSelect();
	}
	
}
