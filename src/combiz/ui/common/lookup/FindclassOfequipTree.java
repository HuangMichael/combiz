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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ�ljh ���ڣ�2008-03-10
	 */
	public void createRoot()
	throws Exception
	{
		//����������¹���
		this.getChildren().clear();
		
		classificationSrv = (ClassificationSrv)IBOSrvUtil.getSrv("classification");
		/****************************************************
		 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
		 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
		 */
		String whereStr = "classtype='�ʲ�' and parent is null";
		if(Util.isNotNull(this.getTreeQueryString("CLASSIFICATION")))
			whereStr = whereStr + " and " + this.getTreeQueryString("CLASSIFICATION");
		List list = classificationSrv.findWithQuery(whereStr,"orderby");
		if(list==null || list.isEmpty())
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("û�з������ݣ�"));
			this.appendChild(tc);
			return;
		}
		
		//Treecols tcols = new Treecols();
		//Treecol tcol = new Treecol();
		//tcol.setMaxlength(10);//��������ʾ������ַ���
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
			//����Ҫ����β����ϣ������Զ������ͳ�������
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
			
			Classification lochparent = (Classification)parentitem.getValue();
			String whereStr = "classtype ='�ʲ�' and parent = '"+lochparent.getClassid()+"'";
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
		Classification classification = (Classification) treeitem.getValue();
		String classid = classification.getClassid();
		ListWindow mainWnd = (ListWindow)this.getFellow("classificationofitem");
		mainWnd.setTitle(classid);
		/****************************************************
		 * ������м�����������Աѡ���ʱ����Ҫ�ڸô�������Ա�Ĺ�������this.getQueryString()
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
