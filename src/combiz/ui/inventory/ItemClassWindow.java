package combiz.ui.inventory;

import java.util.List;

import combiz.business.classattr.ClassificationSrv;
import combiz.business.inventory.ItemSrv;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.TMainWindow;

import com.inbasis.zul.Messagebox;
import com.inbasis.zul.Treeitem;

public class ItemClassWindow extends TMainWindow
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
	public ItemClassWindow()
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
		newobj.setClasstype("����");
		newobj.setHaschild("��");
		Treeitem ti = mainTree.getSelectedItem();
		if(ti!=null && ti.getValue()!=null)
		{
			Classification classification = (Classification) ti.getValue();
			newobj.setParent(classification.getClassid());
		}
		
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
		//����ҵ������ɾ������
		super.delete();
		// ���¹�����
		mainTree.afterDeleteItem();
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
			ItemSrv itemsrv = (ItemSrv)IBOSrvUtil.getSrv("item");
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
				itemsrv.cretetree(classlist);
				this.refreshData();
				Messagebox.show("���ݴ������!");
			}
		setMainData();

		}
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ���� ���ܣ����޸�����Ϊ��ת����ʱ����֤������Ϊ��
	 * 
	 */
	
	/* (non-Javadoc)
	 * @see combiz.system.ui.TMainWindow#save()
	 */
	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
		if (this.selectedTabid.equals("list")) {
			Messagebox.show("��ѡ��һ������¼��Ȼ���ٽ��б��������");
			return;
		}
		ListWindow itemwnd = (ListWindow) this.getFellow("itemclass");//�õ������ϵĶ���
		Item item =(Item) itemwnd.getSelectObject();
		List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+item.getItemnum()+"'");
		//List itemlist = itemwnd.getSelectObjects();
		if (itemlist.size()>0) {
			Item olditem =(Item) itemlist.get(0);
			
			if (item.getId() != null) {//˵�������½���¼
				if (!(item.getRotating().equals(olditem.getRotating()))) {
					if ("��".equals(item.getRotating())) {
						int rtn = Messagebox.show("�Ƿ�ȷ���޸�Ϊ��ת����", "ȷ���޸ģ�", Messagebox.YES
								| Messagebox.NO, Messagebox.QUESTION);
						if (rtn == Messagebox.NO)
							return;
						else if (rtn == Messagebox.YES) {
							//�õ�����Ƿ����������У������޸�
							List invlist = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class, "itemnum='"+item.getItemnum()+"'");
							Double culbar = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(i.culbar) from Invstock i where i.itemnum='"+item.getItemnum()+"'");
							if (culbar!=null && culbar>0) {
								Messagebox.show("���ʿ��������Ϊ�㣬�����޸ĳ���ת����");
								return;
							}
							if (invlist.size()>0) {
								Messagebox.show("���ʿ��������Ϊ�㣬�����޸ĳ���ת����");
								return;
							}
							}
					}
				}
				
			}
		}
		
		
		super.save();
	}
	
}
