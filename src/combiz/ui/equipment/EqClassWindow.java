package combiz.ui.equipment;
 

import java.util.List;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.equipment.EquipmentSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class EqClassWindow extends TMainWindow
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
	public EqClassWindow()
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
		newobj.setClasstype("�ʲ�");
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		newobj.setHaschild("��");
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
		if (classification != null) 
		{
			if ("����".equals(classification.getClasstype())) {
				if ("���ʷ���".equals(classification.getClassid())){
					Messagebox.show("�������࣬����ɾ����");
					return;
				}
			}
			if ("�ʲ�".equals(classification.getClasstype())) {
				if ("�ʲ�����".equals(classification.getClassid())){
					Messagebox.show("�������࣬����ɾ����");
					return;
				}
			}
			if ("����".equals(classification.getClasstype())) {
				if ("����".equals(classification.getClassid())){
					Messagebox.show("�������࣬����ɾ����");
					return;
				}
			}
			//����ҵ������ɾ������
			super.delete();
			// ���¹�����
			mainTree.afterDeleteItem();
		}
	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�classance�в��������ϵ
	 * ���ڣ�Sep 1, 2008 3:36:07 PM
	 *
	 */
	public void cretetree() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ɸ������ǰ�����ȱ��浱ǰ�ļ�¼��");
			return;
		}
		int rtn = Messagebox.show("�Ƿ�ȷ�����´���������","ȷ�ϴ�����",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
		if(rtn == Messagebox.NO)
			return ;
		else if(rtn == Messagebox.YES)
		{
			EquipmentSrv equipmentsrv = (EquipmentSrv)IBOSrvUtil.getSrv("equipment");
			List classlist = this.getMainSrv().getBaseDao().findWithQuery(Classification.class, "classtype = '����' and  parent is null");
			
			if (classlist.size()>0) {
				List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, " classid='"+((Classification)classlist.get(0)).getClassid()+"'");
				if (listance.size()>0) {
					Messagebox.show("�����Ѿ����ڣ������ٴδ���!");
					return;
				}
			}
			//List listance = this.getMainSrv().getBaseDao().findWithQuery(Classance.class, "");
			//this.getMainSrv().getBaseDao().executeSql("delete Classance where ");
			
			for (int i=0;i<classlist.size();i++) {
				Classification classification = (Classification) classlist.get(i);
				equipmentsrv.cretetree(classlist);
				this.refreshData();
				Messagebox.show("���ݴ������!");
			}
		setMainData();

		}
	}
}
