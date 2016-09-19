package combiz.ui.invoice;

import combiz.business.invoice.InvoiceSrv;
import combiz.business.po.PoSrv;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.invoice.Invoice;
import combiz.domain.invoice.Invoiceline;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.ui.CommonListWindow;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class InvoiceWindow extends MainWindow implements InfWindow {

	/**
	 * @TODO
	 * @����� 2007-8-21 ����03:18:38
	 */
	private static final long serialVersionUID = 1L;

	public InvoiceWindow() {
		super();
	}

	/**
	 * 
	 * @TODO ����һ����¼
	 * @throws Exception
	 * @����� 2007-8-7 ����01:17:05
	 */
	public boolean addNew() throws Exception {
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Invoice iv = new Invoice();

		iv.setInvoicenum(this.genAutokey("invoicenum"));
		mainObject = iv;
		iv.setStatus("����δ����");

		iv.setEnterby(this.getLaborInfo().getLabornum());
		//iv.setSitenum(this.getLaborInfo().getSitenum());
		//iv.setCorpnum(this.getLaborInfo().getCorpnum());
		iv.setEnterdate(new Date());
		iv.setStatusdate(new Date());
		iv.setChangeby(this.getLaborInfo().getLabornum());
		iv.setChangedate(new Date());
		iv.setTotalcost(0d);
		iv.setBasetotalcost(0d);
		iv.setTotaltax(0d);
		return true;
	}

	/**
	 * 
	 * @TODO �Զ����ɷ�Ʊ��---����һ������(������Դ�ڲɹ�����)�����û�ѡ�񡣽�ѡ����¼�뵽��Ʊ����
	 * 
	 * @throws Exception
	 * @����� 2007-8-21 ����05:03:57
	 */

	public void createinvoiceline() throws Exception {

		Invoice iv = (Invoice) this.getMainObject();
		if(Util.isNotNull(iv.getCntnum()))
		{
			Messagebox.show("���ŷ�Ʊ��Ϊ��ͬ����������ɷ�Ʊ�У����ڷ�Ʊ�����½���¼��");
			return;
		}
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("��������Ԥ��ǰ�������ݣ�");
			return;
		}
		
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
				.getMainObject(), "/invoice/invoicelineList.xul");
		if (listWnd == null)
			return;

		//�ж��Ƿ�����ȷ����ť������ȡ����ť
		if (!listWnd.isConfirm())
			return;
		//TableList tabblelist = (TableList) listWnd.getFellow(listWnd.getId()+"List");
		List retList = listWnd.getSelectObjects();
		((InvoiceSrv) this.getMainSrv()).CreateInvoiceline(retList, iv);
		Messagebox.show("�ɹ������Ѿ����Ƶ���Ʊ!");
		this.refreshData();
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ�У�鷢Ʊ��
	 * ���ڣ�Oct 23, 2008 5:10:12 PM
	 *
	 */
	public void verify() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("У�鷢Ʊǰ�����ȱ������ݣ�");
			return;
		}
		Invoice invoice = (Invoice)this.mainObject;
		List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '��У��'");
		if(retList.size() == 0){
			Messagebox.show("û����ҪУ��ķ�Ʊ��¼!");
		}
		else{
			((InvoiceSrv)this.getMainSrv()).verify(retList, invoice);
			this.refreshData();
			Messagebox.show("��ƱУ�����!");
			
		}
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���׼��Ʊ
	 * ���ڣ�Oct 23, 2008 5:09:59 PM
	 *
	 */
	public void approveinvoice() throws Exception{
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("��׼��Ʊǰ�����ȱ������ݣ�");
			return;
		}
		Invoice invoice = (Invoice)this.mainObject;
		if(invoice.getStatus().equals("У�����"))
		{
			List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '��У��'");
			if(retList.size() == 0){
				Messagebox.show("û����Ҫ��׼�ķ�Ʊ��¼!");
			}
			else{
				if(Util.isNull(invoice.getCntnum()))//�ɹ��ķ�Ʊ
				{
					((InvoiceSrv)this.getMainSrv()).approveinvoice(retList, invoice);
					this.refreshData();
					this.createinvtrans();//ͬʱ���ɷ�Ʊ�����¼
					Messagebox.show("��Ʊ����׼!");
				}
				else//��ͬ�ķ�Ʊ
				{
					((InvoiceSrv)this.getMainSrv()).apprcntinv(retList, invoice);
					this.refreshData();
					Messagebox.show("��Ʊ����׼!");
				}
			}
		}
		else
		{
			Messagebox.show("�÷�Ʊ�ﻹ��û�м���ķ�Ʊ�У����ܽ��з�Ʊ��׼\n�������߸÷�Ʊ����׼");
		}
	}

	@Override
	public void save() throws Exception {
		// TODO �Զ����ɷ������

		Invoice iv = (Invoice) this.getMainObject();
		iv.setChangeby(this.getLaborInfo().getLabornum());
		iv.setChangedate(new Date());
		super.save();
	}

	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		/*Invoice iv = (Invoice) this.getMainObject();
		ListWindow invoicelineWnd = (ListWindow)this.getFellow("invoiceline");
		if (iv.getStatus()!=null && iv.getStatus().equals("����׼")) {
			invoicelineWnd.setReadonlyList(true);
		}else{
			invoicelineWnd.setReadonlyList(false);
		}
		super.initData();*/
	}
	
	/**
	 * ����
	 * 
	 * ���� ���
	 * ���ܣ����ɷ�Ʊ������
	 * ���ڣ�8 08 , 2009 5:09:59 PM
	 *
	 */
	public void createinvtrans() throws Exception{
		/*if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����Ʊ��¼��");
			return;
		}
		if (this.getObjStatus() == this.MODIFIED
				|| this.getObjStatus() == this.ADDED) {
			Messagebox.show("���ɷ�Ʊ����ǰ�����ȱ������ݣ�");
			return;
		}
		//�õ���������
		
		if (invoice.getId() == null) {
			Messagebox.show("������ѡ��һ����Ʊ��¼��");
			return;
		}*/
		Invoice invoice = (Invoice)this.mainObject;
		//if (invoice.getStatus() != null && "����׼".equals(invoice.getStatus())) {
		
			//�����Ƿ��з�Ʊ�У��޷�Ʊ�У��߲���Ҫ���ɷ�Ʊ�����¼
			List retList =  this.getMainSrv().getBaseDao().findWithQuery(Invoiceline.class, "invoicenum = '" + invoice.getInvoicenum() +"' and state = '��У��'");
			if (retList.size()<0) {
				Messagebox.show("û����У��ķ�Ʊ�м�¼���������ɷ�Ʊ���죡");
				return;
			}
			
			//�õ�ѡ��ķ�Ʊ�ж���
			ListWindow invoicelineWnd = (ListWindow)this.getFellow("invoiceline");
			Invoiceline invline = null;
			List invlinelist = invoicelineWnd.getSelectObjects();
			if (invlinelist.size()>0) {
				for (int i=0;i<invlinelist.size();i++) {
					invline =(Invoiceline) invlinelist.get(i);
					if ("��У��".equals(invline.getState())) {
						//����ҵ���࣬���ɷ�Ʊ����
						((InvoiceSrv)(this.getMainSrv())).createinvtrans(invlinelist);
						//Messagebox.show("���ɷ�Ʊ��������ɣ�");
						//this.refreshData();
					}else{
						Messagebox.show("��Ʊ��δͨ��У�飬�������ɷ�Ʊ���죬���ʵ��");
						return;
					}
				}
				
			}else{
				Messagebox.show("�÷�Ʊ�޷�Ʊ�У��������ɷ�Ʊ�У�");
				return;
			}
			
			
		/*}else{
			Messagebox.show("�÷�Ʊδͨ��У�飬���߷�Ʊ��δУ�飬�������ɷ�Ʊ���죬���ʵ��");
			return;
		}*/
	}
	
	/**
	 * ����
	 * 
	 * ���� ���
	 * ���ܣ��鿴��Ʊ������
	 * ���ڣ�8 25 , 2009 5:09:59 PM
	 *
	 */

	public void selectinvtrans() throws Exception {

		Invoice iv = (Invoice) this.getMainObject();
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("��������Ԥ��ǰ�������ݣ�");
			return;
		}
		
		
		CommonListWindow listWnd = (CommonListWindow) this.popupDialog(this
				.getMainObject(), "/invoice/invoicetransList.xul");
		if (listWnd == null)
			return;

		//�ж��Ƿ�����ȷ����ť������ȡ����ť
		if (!listWnd.isConfirm())
			return;
		
	}
}
