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
	 * ������ʼ��
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
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
		String whereStr = "parent is null and classtype='�ʲ�'";
		if(this.getQueryString()!=null)
			whereStr = whereStr + " and " +  this.getQueryString();
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
			Classification classification = (Classification)list.get(i);
			ti = new Treeitem(classification.getDescription()+":"+classification.getClassid());
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
		}
	}
	
	
	/**
	 * ��������ԭʼֵ����
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-4-25
	 */
	public void createRoot(String value)
	throws Exception
	{
		this.getChildren().clear();
		List list = classificationSrv.findWithQuery("classid='"+value+"' and classtype='�ʲ�'");
		if(list.size()<=0)
		{
			Treechildren tc = new Treechildren();
			tc.appendChild(new Treeitem("�����ڷ���["+value+"]��"));
			this.appendChild(tc);
			return;
		}
		Classification classification = (Classification) list.get(0);
		Treeitem ti = new Treeitem(classification.getDescription()+"["+classification.getClassid()+"]");
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
		//��ײ��tc
		Treechildren bottomTc = new Treechildren();
		bottomTc.appendChild(ti);

		//�ݹ���ô�������
		Treechildren topTc = null;
		Classification parent = this.getParent(classification);
		while(parent!=null)
		{
			topTc = this.createUpItem(parent, bottomTc);
			parent = this.getParent(parent);
			bottomTc = topTc;
		}

		//����tc
		if(topTc==null)
			topTc = bottomTc;
		this.appendChild(topTc);
		this.selectItem(ti);
	}
	
	/**
	 * ��ȡλ�ø���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	private Classification getParent(Classification classification)
	throws Exception
	{
		List parentList = classificationSrv.findWithQuery("classid = '" + classification.getParent() + "' and classtype='�ʲ�'");
		if (parentList.size() > 0)
		{
			Classification parent = (Classification) parentList.get(0);
			return parent;
		}
		return null;
	}
	
	//��createRoot�������õݹ�
	private Treechildren createUpItem(Classification classification, Treechildren childTc)
	throws Exception
	{
		Treechildren tc = new Treechildren();
		Treeitem ti = new Treeitem(classification.getDescription()+"["+classification.getClassid()+"]");
		ti.setValue(classification);
		ti.setImage("/images/img_location.gif");
		ti.setOpen(true);
		//����Ҫ����β����ϣ������Զ������ͳ�������
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
			
			Classification parentClass = (Classification)parentitem.getValue();
			/****************************************************
			 * �����ѡ�������粿��ѡ��������ô�ڹ��������ϱ������this.getQueryString()
			 * ������м�����������Աѡ���ʱ��Ĳ���������ô�ڹ��������ϱ������this.getTreeQueryString("����������-��д")
			 */
			String whereStr = "parent = '"+parentClass.getClassid()+"' and classtype='�ʲ�'";
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
}
