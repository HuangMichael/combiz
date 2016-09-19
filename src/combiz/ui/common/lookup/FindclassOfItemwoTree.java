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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ�ljh ���ڣ�2008-03-10
	 */
	public void createRoot()
	throws Exception
	{
//		����������¹���
		this.getChildren().clear();
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
		String whereStr = "classtype='����' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
		List list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_itemclass.appendChild(tc);
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
		whereStr = "classtype='�ʲ�' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
			list = classificationSrv.findWithQuery("classtype='�ʲ�' and parent is null","orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_eqclass.appendChild(tc);
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
		whereStr = "classtype='����' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and "+ this.getTreeQueryString("CLASSIFICATION");
		list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�����ݣ�"));
			treeitem_toolclass.appendChild(tc);
		}
		else
		{
			this.createChildRoot(list, treeitem_toolclass, "����");
		}
		
	}
	//��createroot��������
	private void createChildRoot(List list,Treeitem parentTi,String classtype)
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
	}
	
	
	/**
	 * չ�����ڵ�
	 * @param parentitem
	 * ���ߣ�ljh ���ڣ�2008-03-11
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
	 * 
	 * @throws Exception
	 * @ljh  2008-3-11  ����01:00:02
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
		 * ������м�����������Աѡ���ʱ����Ҫ�ڸô�������Ա�Ĺ�������this.getQueryString()
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
