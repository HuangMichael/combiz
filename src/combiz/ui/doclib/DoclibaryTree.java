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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		mainWnd = (DoclibaryWindow)this.getFellow("mainWnd");
		doclibarySrv = (DoclibarySrv)IBOSrvUtil.getSrv("doclibary");
		//classificationSrv.setOrderBy("location");
		List list = doclibarySrv.findWithQuery("parent is null");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�з������ݣ�"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
			return;
		}
		doclibarySrv.setOrderBy("orderby");
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//��������ʾ������ַ���
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
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
	 * չ�����ڵ�
	 * @param parentitem
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void expand(Treeitem parentitem)
	throws Exception
	{
		if(parentitem!=null && parentitem.getValue() != null) //���û�и���
		{
			//*********��һ�֣�ÿ�ζ�ɾ�������´����ݿ���ȡ��
			Treechildren tc = parentitem.getTreechildren();
			if(tc!=null && tc.getChildren().size()>0)
				tc.getChildren().clear();
			//*********�ڶ��֣���һ�ε���Ǵ����ݿ���ȡ���ݣ��Ժ�Ͳ�ɾ��Ҳ��ȡ��
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
				//����Ҫ����β����ϣ������Զ������ͳ�������
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
		else //����ˢ����
		{
			this.createRoot();
		}
	}

	
	/**
	 * ѡ���¼�
	 * 
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void onSelect() 
	throws Exception
	{
		Treeitem item = getSelectedItem();
		mainWnd.fetchData(item);
	}

	
	/**
	 * ���¹������ڵ�
	 * 
	 * ���ߣ���С�� ���ڣ�2007-4-25
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