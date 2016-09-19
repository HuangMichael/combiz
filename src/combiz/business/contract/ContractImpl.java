package combiz.business.contract;

import combiz.domain.contract.Contline;
import combiz.domain.contract.Contract;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.system.IBOBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContractImpl extends IBOBaseImpl implements ContractSrv {

	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Contract))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		this.deleteAllChild(obj, "cntline");
		this.deleteAllChild(obj, "cntpay");
		this.deleteAllChild(obj, "cntchange");
		this.deleteAllChild(obj, "cntterms");
		super.delete(obj);

	}
	
	/**
	 * ������createpo
	 * ���ߣ����
	 * ���ܣ�ͨ����ͬ�����ɲɹ�����
	 * ���ڣ�Apr 13, 2009  2:40:11 PM
	 */
	public String createpo(Object obj) throws Exception {

		if (!(obj instanceof Contract)) {
			throw new Exception("���󴫵ݲ���ȷ��");
		}
		Contract contract = (Contract) obj;
        
        Double totalcost = (Double) this.getBaseDao().selectSumByHql("select sum(t.taxlinecost) from Contline t where t.cntnum='"+contract.getCntnum()+"'");
		if(totalcost == null)
		{
			totalcost = 0d;
		}
		

		String cntnum = contract.getCntnum(); //��ȡPR��prnum
		String ponum = this.genInskey("PONUM");//���Զ�����л�ȡRFQNUM
		
		ArrayList nopolist = new ArrayList();//����ArrayList
		
		List list = this.getBaseDao().findWithQuery(Contline.class, "cntnum='"+cntnum+"'");
		//ͨ��Prline.class�����ݿ�Prline���в�����ִ��prnum = PR.prnum
		
		if(list.size() == 0)
		{
			throw new Exception("����û����д��ͬ�ɹ�����ϸ�����ܴ����ɹ�����");
		}
		
		for(int i = 0;i<list.size();i++) //��Listִ�в�ѯ����prline��rfqnumΪ�յļ�¼�ŵ� norfqlist�У�
		{
			Contline contline =(Contline) list.get(i);
			if (contline.getPonum() == null)
				{
				nopolist.add(list.get(i));				
				contline.setPonum(ponum);//��prline���rfqnum���и�ֵ������prline��rfq���й�����
				}
				
		}
		
		if (nopolist.size() == 0)
		{
			throw new Exception("�òɹ������Ѿ�ȫ������Ϊ�ɹ�����ϣ����贴����");
		}
		//

		/**�ɹ���*/
        Po po = new Po();
		po.setPonum(ponum);
		//״̬������
		po.setStatus(contract.getStatus());
		po.setVendor(contract.getVendor());
		po.setStatusdate(contract.getStatusdate());
		//����
		po.setDescription("�ɺ�ͬ "+contract.getDescription() +" ���ɣ�");
		//��������,��������
		po.setOrderdate(new Date());
		//��Ӧ��,��ϵ��
		po.setContact(contract.getVendorcontact());
		//�ܼ�
		po.setTotalcost(totalcost);
		//�ջ��ˡ���ַ
		po.setShiptolabor("����д�ջ���");
		po.setShiptoaddr("����д�ջ���ַ");
		//��Ʊ�ˡ���ַ
		//po.setBilltolabor(pr.getBilltolabor());
		//po.setBilltoaddr(pr.getBilltoaddr());
		//�����ˡ�ʱ��
		//po.setChangeby(contract.getChangeby());
		//po.setChangedate(contract.getChangedate());
		//����,��������,˰�ܶ�
		//po.setExchangerate(contract.getExchangerate());
		//po.setExchangedate(contract.getExchangedate());
		//po.setTotalcost(contract.getTotalcost());
		//setTotaltax(contract.getTotaltax());
		//po.setCorpnum(contract.getCorpnum());
		//po.setSitenum(contract.getSitenum());

		//�������
		super.save(po);

		/***���ɲɹ�����**/

		//ȡ������������еĲɹ�������
		for (int i = 0; i < nopolist.size(); i++) {
			Contline pl = (Contline) nopolist.get(i);
			Poline ol = new Poline();
			pl.setLinenum(Long.parseLong(Integer.toString(i + 1)));
			//	int polinenum = this.getRowCountByWhere(ol, "ponum='"+po.getPonum()+"'")+1;
			ol.setPonum(ponum);
			ol.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			//				�����Ŀ���������ֿ�,�������
			ol.setItemnum(pl.getItemnum());
			ol.setDescription(pl.getDescription());
			ol.setWarehouse(pl.getWarehouse());
			ol.setStocktype(pl.getStocktype());
			//				����������������λ��ת��ϵ��
			Double ordqty = pl.getOrderqty();
			if (ordqty==null) {
				ordqty= 0d;
			}
			ol.setOrderqty(ordqty);
			ol.setOrderunit(pl.getOrderunit());
			if (pl.getConversion() == null) {
				ol.setConversion(0.00);
			} else {
				ol.setConversion(pl.getConversion());
			}

			//����˰���ۡ��ܼ�
			ol.setUnitcost(pl.getUnitcost());
			ol.setLinecost(pl.getLinecost());
			//��˰����,��˰�ܼ�,��������,�ѽ��յĵ���,���յ����ܼ�,˰��,˰���������
			ol.setTaxunitcost(0.00);
			ol.setTaxlinecost(0.00);
			ol.setReceivedqty(0.00);
			ol.setReceivedunitcost(0.00);
			ol.setReceivedtotalcost(0.00);
			ol.setTaxrate(0.00);
			ol.setTax(0.00);

			Double recqty = ol.getReceivedqty();
			if (recqty == null) {
				recqty = 0D;
			}
			if (ol.getOrderqty() != null ) {
				ol.setRejectedqty(ol.getOrderqty() - recqty);
			}
			
			//��Ӧ�̷�������,¼������,¼����,������
			ol.setVendeliverydate(pl.getVendeliverydate());
			ol.setEnterdate(pl.getEnterdate());
			ol.setEnterby(pl.getEnterby());
			ol.setRequestedby(pl.getRequestedby());
			//				�������� 
			ol.setReqdeliverydate(pl.getReqdeliverydate());
			//				�����̡���Ӧ���ͺ�
			ol.setManufacturer(pl.getManufacturer());
			ol.setModelnum(pl.getModelnum());
			//����,ע��
			ol.setService(pl.getService());
			ol.setRemark(pl.getRemark());
			//����λ��,��ɽ���,��Ҫ���,����ĳɱ�,�ѷ�̯
			ol.setLocation(pl.getLocation());
			ol.setReceiptscomplete("��");
			ol.setInspection(pl.getInspection());
			ol.setLoadedcost(pl.getLoadedcost());
			String prorated = pl.getProrated();
			if (prorated == null || prorated.equals("")) {
				prorated = "��";
			}
			ol.setProrated(prorated);

			//����,��������
			//ol.setWonum(pl.getWonum());
			//ol.setTasknum(pl.getTasknum());
			//ol.setCorpnum(pl.getCorpnum());
			//ol.setSitenum(pl.getSitenum());
			super.save(ol);
		}
		//return Integer.toString(ponum);
		return ponum;
	}
}
