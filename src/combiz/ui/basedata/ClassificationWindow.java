package combiz.ui.basedata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.system.IBOSrvUtil;
import combiz.system.IBSServer;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.TMainWindow;
import combiz.system.util.Util;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class ClassificationWindow extends TMainWindow
implements InfWindow
{
	
	/////////////////////////////
	/////�����������Ϊ Classification
	////
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	/**
	 * 
	 */
	public ClassificationWindow()
	{
		super();
	}
	
	
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Classification newobj = new Classification();

		newobj.setHaschild("��");
		Treeitem ti = mainTree.getSelectedItem();
		String newclassid = "";
		String classtype = null;
		String parent = null;
		long largeid = 0l;
		int count = 0;
		Double maxbh = 0d;
		/*Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
			//���ñ��� ��ÿһ���01
			String classid = "";
			String format = "";//���ַ�����ʽ����2λ��ʽ
			String type = classification.getClasstype();//�õ�����
			List classlist = this.getMainSrv().getBaseDao().selectListByHql(" from Classification l where l.parent='"+classification.getClassid()+"' and l.classtype='"+type+"' order by l.classid desc");
			if (classlist.size()>0) {

				Classification classif = (Classification) classlist.get(0);
				String bigclassid = classif.getClassid();//�õ������
				String cutloc = bigclassid.substring(bigclassid.length()-2, bigclassid.length());
				String deloc = bigclassid.substring(0,bigclassid.length()-3);

				int lenth = cutloc.length();//�õ���ʼʱ��classid�ĳ���
				int setclassid = Integer.parseInt(cutloc)+1;//�õ�ת����int����+1�������

				String srvlenth = lenth +"";
				String srvsetclassid = setclassid +"";
				format = "%02d";
				if (cutloc.length()<srvlenth.length()) {//�ж�ת����ĳ����Ƿ�С��ԭʼ����
					//int addcount = srvsetclassid.length()+2;
					format = "%03d";
					classid = String.format(format, srvsetclassid);
				}
			}else{
				classid = classification.getClassid()+"01";
				//	newobj.setClassid(deloc+classid);
			}
			//newobj.setClassid(deloc+classid);
			newobj.setClasstype(classification.getClasstype());*/
		Classification classification = (Classification) ti.getValue();
		if(classification==null)//�ж��Ƿ�ȡ����������ĸ��ڵ�
		{
			String classlabel = ti.getLabel();
			if(classlabel.equals("���ʷ���"))
			{
				classtype = "����";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '����') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '����') and  t.ancestor is null");
				}


			}else if(classlabel.equals("�豸����"))
			{
				classtype = "�ʲ�";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '�ʲ�') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '�ʲ�') and  t.ancestor  is null");
				}

			}else
			{
				classtype = "����";
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.classid in (select c.classid from Classification c where c.classtype = '����') and (t.ancestor  is null or DATALENGTH(t.ancestor) = 0)");
				}
				else
				{
					count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance t where t.classid in (select c.classid from Classification c where c.classtype = '����') and  t.ancestor  is null");
				}



			}
			if(count == 0)
			{
				newclassid = "01";
			}
			else
			{
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(t.classid)) as largeid from classification  t where t.classtype = '"+classtype+"' and t.parent is null or DATALENGTH(t.parent) = 0");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
//							BigDecimal maxvalue = (BigDecimal) map
//							.get("LARGEID");
							maxbh = (Double)map.get("LARGEID") + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				else
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(t.classid)) as largeid from classification  t where t.classtype = '"+classtype+"'and t.parent is null");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
							BigDecimal maxvalue = (BigDecimal) map
							.get("LARGEID");
							maxbh = maxvalue.doubleValue() + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				String s = String.valueOf(largeid);
				int len1 = s.length();
				if(len1<2)
				{
					for(int j=0;j<2-len1;j++)
					{
						newclassid = newclassid + "0";
					}
					newclassid = newclassid + s;
				}


			}

		}
		else
		{
			String classid = classification.getClassid();
			parent = classid;
			int len = classid.length();
			classtype = classification.getClasstype();
			count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classance  t where t.ancestor = '"+classid+"' and t.classid <>'"+classid+"' and t.classtype='"+classtype+"'");
			if(count>0)
			{
				if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER"))
				{
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(substring(t.classid,"+len+"+1,len(t.classid)))) as largeid from classification  t where t.parent = '"
							+ classid+"' and t.classtype='"+classtype+"'");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
//							BigDecimal maxvalue = (BigDecimal) map
//							.get("LARGEID");
							maxbh = (Double)map.get("LARGEID") + 1;
							largeid = maxbh.longValue();
						}
					}
				}
				else
				{
					String as = 
						"select max(abs(substr(t.classid,"+len+"+1,length(t.classid)))) as largeid from classification  t where t.parent = '"
						+ classid+"' and t.classtype='"+classtype+"'";
					List maxidlist = this.getMainSrv()
					.getBaseDao()
					.selectListBySql(
							"select max(abs(substr(t.classid,"+len+"+1,length(t.classid)))) as largeid from classification  t where t.parent = '"
							+ classid+"' and t.classtype='"+classtype+"'");

					if (maxidlist.size() > 0) {
						Map map = (Map) maxidlist.get(0);
						if (map.size() > 0) {
							BigDecimal maxvalue = (BigDecimal) map
							.get("LARGEID");
							maxbh = maxvalue.doubleValue() + 1;
							largeid = maxbh.longValue();
						}
					}
				}

				String s = String.valueOf(largeid);
				newclassid = classid;
				int len1 = s.length();
				if(len1<2)
				{
					for(int j=0;j<2-len1;j++)
					{
						newclassid = newclassid + "0";
					}
				}
				newclassid = newclassid + s;
			}
			else
			{
				newclassid = classid +"01";
			}

		}



		newobj.setClassid(newclassid);
		newobj.setParent(parent);
		newobj.setClasstype(classtype);
		newobj.setOrderby(0L);





		mainObject = newobj;

		return true;
	}
	
	/**
	 * 
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-3-17
	 */
	public void delete() 
	throws Exception
	{
		Treeitem ti = mainTree.getSelectedItem();
		if (ti == null)
		{
			Messagebox.show("û��ѡ��Ҫɾ���ļ�¼��");
			return;
		}
		Classification classification = (Classification) ti.getValue();
		String classid = null;
		String getva = ti.getLabel();
		if (getva.equals("���ʷ���") || getva.equals("�豸����") || getva.equals("���߷���")) {
			Messagebox.show("�������಻��ɾ������ѡ������½ڵ������");
			return;
		}else{
			classid = classification.getClassid();
		}
		// �Ƿ�ɾ��������ײ�
		ClassificationSrv classificationSrv = (ClassificationSrv)this.getMainSrv();
		if (classificationSrv.hasChildren(classification))
		{
			Messagebox.show("���Ӽ����࣬��ѡ����ײ�������ɾ����");
			return;
		}

		//�жϷ���
		String sql = null;
		String type = classification.getClasstype();
		if ("����".equals(type)) {//���ʷ���ڵ��µĲ���
			sql = "select count(i.id) from Item i where i.classid ='"+classid+"'";
		}else if("�ʲ�".equals(type)) {//�豸����ڵ�Ĳ���
			sql = "select count(e.id) from Equipment e where e.classid ='"+classid+"'";
		}else{//���߷���ڵ��µĲ���
			sql = "select count(t.id) from Tool t where t.classid ='"+classid+"'";
		}
		
		System.out.println("sql = "+sql);
		int count = this.getMainSrv().getBaseDao().selectCountByHql(sql);
		if (count >0) {
			Messagebox.show("�÷����ѹ������ݣ�����ɾ���÷��࣡");
			return;
		}
		
		//����ҵ������ɾ������
		super.delete();
		// ���¹�����
		mainTree.afterDeleteItem();
	}
	
	
	/**
	 * 
	 * ���ܣ�ͬ��λ�ú��豸�����ļ�������
	 * ���ߣ���� ���ڣ�2009-9-9
	 */
	/**
	 * @throws Exception
	 */
	public void synchspec () throws Exception{
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ���ͬ��������");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("����λ����Ϣ�޸Ĳ���ǰ�����ȱ������ݣ�");
			return;
		}
		Treeitem ti = mainTree.getSelectedItem();
		if(ti==null)
		{
			throw new Exception("��ѡ��һ������ڵ㣡");
		}
		Classification classification = (Classification) ti.getValue();//�õ������ϵĶ���
		//�жϸ÷������Ƿ������ʣ��Ƿ�����˼�������
		int classapce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Classspec c where c.classid='"+classification.getClassid()+"'");
		if (classapce <= 0) {
			Messagebox.show("�÷�����û���κμ�������������Ҫͬ�������ʵ��");
			return;
		}
		if (classification.getClasstype().equals("����")) {
			int itempce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Item e where e.classid='"+classification.getClassid()+"'");
			if (itempce <= 0) {
				Messagebox.show("�÷�����û���κ����ʣ�����Ҫͬ�������ʵ��");
				return;
			}
		}
		if (classification.getClasstype().equals("����")) {
			int toolpce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Tool t where t.classid='"+classification.getClassid()+"'");
			if (toolpce <= 0) {
				Messagebox.show("�÷�����û���κι��ߣ�����Ҫͬ�������ʵ��");
				return;
			}
		}
		if (classification.getClasstype().equals("�ʲ�")) {
			int itempce = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment e where e.classid='"+classification.getClassid()+"'");
			if (itempce <= 0) {
				Messagebox.show("�÷�����û���κα���������Ҫͬ�������ʵ��");
				return;
			}
		}
		
		
		int rtn = Messagebox.show("�Ƿ�ȷ��ͬ���÷��������ʵļ���������", "ͬ��ȷ�ϣ�",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		if (rtn == Messagebox.NO)
			return;
		else if (rtn == Messagebox.YES) {
			((ClassificationSrv) (this.getMainSrv())).synchspec(classification);//����ͬ������
			Messagebox.show("��������ͬ�����!");
		}
		
	}
	
	//ά��������λ
	public void technicunit() throws Exception {

		if (this.getObjStatus() == this.ADDED
				|| this.getObjStatus() == this.MODIFIED) {
			Messagebox.show("��¼û�б��棬���ȱ�����ٽ��иò�����");
			return;
		}
		// ����ָ��ҳ�洰�ڣ������ƶ��������������û�й�������NULL
		this.popupDialog(this.getMainObject(), "/location/technicunit.xul", "");

	}
}
