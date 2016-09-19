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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		mainWnd = (TMainWindow) this.getFellow("mainWnd");
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		Treechildren topTc = new Treechildren();
		this.appendChild(topTc);
		//��������̧ͷ
		Treeitem treeitem_itemclass = new Treeitem();
		Treerow treerow = new Treerow();
		treerow.appendChild(new Treecell("���ʷ���"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_itemclass.appendChild(treerow);
		treeitem_itemclass.setImage("/images/btn_unitem.gif");
		topTc.appendChild(treeitem_itemclass);
		List list = classificationSrv.findWithQuery("classtype='����' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_itemclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_itemclass, "����");
		}
		
		
		//�豸����
		Treeitem treeitem_eqclass = new Treeitem();
		treerow = new Treerow();
		treerow.appendChild(new Treecell("�豸����"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_eqclass.appendChild(treerow);
		treeitem_eqclass.setImage("/images/img_location.gif");
		topTc.appendChild(treeitem_eqclass);
		list = classificationSrv.findWithQuery("classtype='�ʲ�' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_eqclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_eqclass, "�ʲ�");
		}
		
		
		//���߷���
		Treeitem treeitem_toolclass = new Treeitem();
		treerow = new Treerow();
		treerow.appendChild(new Treecell("���߷���"));
		treerow.setStyle("font-weight: bolder;color: #FF0000;");
		treeitem_toolclass.appendChild(treerow);
		treeitem_toolclass.setImage("/images/btn_uneqnum.gif");
		topTc.appendChild(treeitem_toolclass);
		list = classificationSrv.findWithQuery("classtype='����' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_toolclass.appendChild(tc);
			mainWnd.fetchData(null);
		}
		else
		{
			this.createChildRoot(list, treeitem_toolclass, "����");
		}
		
	}
	//��createroot��������
	private void createChildRoot(List list,Treeitem parentTi, String classtype)
	throws Exception
	{
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
			ti = new Treeitem(classification.getDescription()+":"+classification.getClassid()); 
			ti.setValue(classification);
			//ti.setImage("/images/img_location.gif");
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
		parentTi.appendChild(tc);
		
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
		Treeitem ti = getSelectedItem();
		if(ti==null)
			return;
		Classification classification = (Classification)mainWnd.getMainObject();
		ti.setValue(classification);
		ti.setLabel(classification.getDescription()+":"+classification.getClassid());
		//����ѡ��
		this.onSelect();
	}
	
	
}
