package combiz.ui.tool;

import combiz.business.classattr.ClassificationSrv;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.TopWindow;
import combiz.system.ui.common.MainTree;
import combiz.system.util.Util;

import java.util.List;

import com.inbasis.zk.ui.Page;
import com.inbasis.zul.Treecell;
import com.inbasis.zul.Treechildren;
import com.inbasis.zul.Treeitem;
import com.inbasis.zul.Treerow;

public class ToolTree extends MainTree
{
	ToolWindow mainWnd;
	ClassificationSrv classificationSrv;
	
	public ToolTree() 
	{
		super();
	}

	public void onCreate()
	{
		this.setVflex(true);
		
		Page topPage = this.getDesktop().getPage("topPage");
		TopWindow topWnd = (TopWindow) topPage.getFellow("topWnd");
		this.setHeight((topWnd.getDesktopHeight() - 130) +"px");
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
		
		mainWnd = (ToolWindow)this.getFellow("mainWnd");
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		//classificationSrv.setOrderBy("location");
		List list = classificationSrv.findWithQuery("classtype='����' and parent is null");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�з������ݣ�"));
			this.appendChild(tc);
			mainWnd.fetchData(null);
			return;
		}
		classificationSrv.setOrderBy("orderby");
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//��������ʾ������ַ���
		//tcols.appendChild(tcol);
		//this.appendChild(tcols);
		
		Treechildren tc = new Treechildren();
		Treeitem ti;
		for(int i=0;i<list.size();i++)
		{
			Classification classification = (Classification)list.get(i);
			String desc = classification.getDescription();
			if(desc==null) 
				desc = "";
			ti = new Treeitem(desc+":"+classification.getClassid());  //+":"+lochiery.getLocation()
			ti.setValue(classification);
			ti.setImage("/images/img_location.gif");
			ti.setOpen(true);
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
			
			Classification lochparent = (Classification)parentitem.getValue();
			List list = classificationSrv.findWithQuery("classtype in('����') and parent = '"+lochparent.getClassid()+"'");
			Treeitem ti;
			for(int i=0;i<list.size();i++)
			{
				Classification classification = (Classification)list.get(i);
				String locdesc = classification.getDescription();
				if(locdesc==null)
					locdesc = "";
				ti = new Treeitem(locdesc+":"+classification.getClassid()); // locdesc +":"+classification.getClassid()
				ti.setValue(classification);
				ti.setImage("/images/img_location.gif");
				ti.setOpen(false);
				//����Ҫ����β����ϣ������Զ������ͳ�������
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
		Classification classification = (Classification)mainWnd.getMainObject();
		item.setValue(classification);
		item.setLabel(classification.getClassid());  //classification.getDescription()+":"+
		
		//����ѡ��
		this.onSelect();
	}
	
}
