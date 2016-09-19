package combiz.business.pr;

import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Pr;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
/**
 * @author ����
 *
 */
public class PrImpl extends IBOBaseImpl implements PrSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Pr))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//ɾ���ɹ�������
		this.deleteAllChild(obj, "prline");
	}

	/* save()
	 * ���淽��
	 */
	@Override
	public void save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if (!(obj instanceof Pr)){
			throw new Exception("���󴫵ݲ���ȷ��");
		}
		Pr pr = (Pr) obj;
		/*if (pr.getId()!=null) {
			pr.setChangeby(this.getLaborInfo().getLaborname());
			pr.setChangedate(new Date());
			this.getBaseDao().updateObject(obj);
		}*/
		super.save(obj);
	}

	/***
	 * 
	 * @TODO ����ѯ�۵���ѯ�۵��У�������ƻ��вɹ����뼰�ɹ������е���Ϣ�ֱ�ֵ��ѯ�۵���ѯ�۵���
	 * @param obj
	 * @����� 2007-8-14 ����03:52:56
	 */
	public String ceaterfq(Object obj) throws Exception {
		if (!(obj instanceof Pr))
			throw new Exception("���󴫵ݲ���ȷ��");
		//��òɹ��������
		Pr pr = (Pr) obj;
		String prnum = pr.getPrnum(); //��ȡPR��prnum
		String rfqnum = this.genInskey("RFQNUM");//���Զ�����л�ȡRFQNUM
		ArrayList norfqlist = new ArrayList();//����ArrayList
		List list = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"'");
		//ͨ��Prline.class�����ݿ�Prline���в�����ִ��prnum = PR.prnum
		if(list.size() == 0)
		{
			throw new Exception("�ɹ�������Ϊ�գ����ܴ���ѯ�۵�");
		}
		
		for(int i = 0;i<list.size();i++) //��Listִ�в�ѯ����prline��rfqnumΪ�յļ�¼�ŵ� norfqlist�У�
		{
			Prline prline =(Prline) list.get(i);
			if (prline.getRfqnum() == null)
				{
				norfqlist.add(prline);				
			    prline.setRfqnum(rfqnum);//��prline���rfqnum���и�ֵ������prline��rfq���й�����
			    this.getBaseDao().updateObject(prline);
				}
				
		}
		
		/***********����òɹ������еĲɹ������ж�������ѯ�۵��У���ʾ����Ҫ����*******/
		if (norfqlist.size() == 0)
		{
			throw new Exception("�òɹ������Ѿ�ȫ��������ϣ����贴����");
		}
		
	     /**��ѯ�۵�rfq�в�������¼**********************************************************/
		
		Rfq rfq = new Rfq();
		rfq.setRfqnum(rfqnum);
		/**����ѯ�۵�**/
		//���ɹ�������Ϣ��ֵ��ѯ�۵�
		//����
		rfq.setDescription(pr.getDescription());
		//״̬��Ϣ
		rfq.setStatus(pr.getStatus());
		rfq.setStatusdate(pr.getStatusdate());
		//¼���ˡ�¼��ʱ��
		rfq.setEnterby(this.getLaborInfo().getLaborname());
		rfq.setEnterdate(new Date());
		//�������� 
		rfq.setRequireddate(pr.getRequireddate());

		//�ջ��ˡ���ַ
		rfq.setShiptolabor(pr.getShiptoaddr());
		rfq.setShiptoaddr(pr.getShiptoaddr());
		//��Ʊ�ˡ���ַ
		rfq.setBilltolabor(pr.getBilltolabor());
		rfq.setBilltoaddr(pr.getBilltoaddr());
		//���������ʽ��֧������
		rfq.setFreightterms(pr.getFreightterms());
		rfq.setShipvia(pr.getShipvia());
		rfq.setPaymentterms(pr.getPaymentterms());
		//�����ˡ�ʱ��
		rfq.setChangeby(pr.getChangeby());
		rfq.setChangedate(pr.getChangedate());
		//rfq.setCorpnum(pr.getCorpnum());
		//rfq.setSitenum(pr.getSitenum());

		this.getBaseDao().saveObject(rfq);

		/**���ɹ�������ִ�е�û������ѯ�۵��Ĳɹ�����������ѯ�۵���**********************************************************/
		//��ȡ��Ҫ����ѯ�۵��Ĳɹ������м���norfqlist
		for (int i = 0; i < norfqlist.size(); i++) {
			Prline pl = (Prline) norfqlist.get(i);
			Rfqline rl = new Rfqline();
			rl.setRfqnum(rfq.getRfqnum());
			pl.setRfqlinenum(Long.parseLong(Integer.toString(i + 1)));//���ɹ����������ɵ�ѯ�۵���д�زɹ�������
	
			//��ȡѯ�۵���id��

			//int rlnum = this.getRowCountByWhere(rl, "rfqnum='"+rfq.getRfqnum()+"'")+1;
			rl.setRfqlinenum(Long.parseLong(Integer.toString(i + 1)));
			//�����Ŀ���������ֿ�
			rl.setItemnum(pl.getItemnum());
			rl.setDescription(pl.getDescription());
			rl.setWarehouse(pl.getWarehouse());
			//����������������λ��ת��ϵ��
			rl.setOrderqty(pl.getOrderqty());
			rl.setOrderunit(pl.getOrderunit());
			rl.setConversion(pl.getConversion());
			//�������� 
			rl.setReqdeliverydate(pl.getReqdeliverydate());
			//¼���ˡ�����
			rl.setEnterby(pl.getEnterby());
			rl.setEnterdate(pl.getEnterdate());
			//�ɹ������ɹ�����
			rl.setPonum(pl.getPonum());
			rl.setPolinenum(pl.getPolinenum());
			//���͡�ע��
			rl.setStocktype(pl.getStocktype());
			rl.setRemark(pl.getRemark());
			//������Ҫ���
			rl.setIsservice(pl.getIsservice());
			rl.setInspection(pl.getInspection());
			//�����̡���Ӧ���ͺ�
			rl.setManufacturer(pl.getManufacturer());
			rl.setModelnum(pl.getModelnum());
			//�гɱ�����λ�ɱ�
			rl.setLinecost(pl.getLinecost());
			rl.setUnitcost(pl.getUnitcost());
			//λ�á��豸
			rl.setLocation(pl.getLocation());
			rl.setEqnum(pl.getEqnum());
			//��������������
			rl.setWonum(pl.getWonum());
			rl.setTasknum(pl.getTasknum());
			//rl.setSitenum(pl.getSitenum());
			//rl.setCorpnum(pl.getCorpnum());

			//�������
			this.getBaseDao().saveObject(rl);
			System.out.println("����ѯ�۵��гɹ�.........");
		}

		return rfqnum;
	}

	/**
	 * 
	 * @TODO	�Զ����ɲɹ������ɹ����У���Ӧ������Դ�ڲɹ����롢�ɹ�������s
	 * @param obj
	 * @return String
	 * @throws Exception
	 * @����� 2007-8-15 ����12:19:50
	 */
	public String createpo(Object obj) throws Exception {

		if (!(obj instanceof Pr)) {
			throw new Exception("���󴫵ݲ���ȷ��");
		}
        Pr pr = (Pr) obj;
        
        Double totalcost = (Double) this.getBaseDao().selectSumByHql("select sum(t.linecost) from Prline t where t.prnum='"+pr.getPrnum()+"'");
		if(totalcost == null)
		{
			totalcost = 0d;
		}
		

		String prnum = pr.getPrnum(); //��ȡPR��prnum
		String ponum = this.genInskey("PONUM");//���Զ�����л�ȡRFQNUM
		
		
		List list = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"'");
		//ͨ��Prline.class�����ݿ�Prline���в�����ִ��prnum = PR.prnum
		
		if(list.size() == 0)
		{
			throw new Exception("����û����д����ϸ�����ܴ����ɹ���");
		}
		
		List nopolist = this.getBaseDao().findWithQuery(Prline.class, "prnum='"+prnum+"' and ponum is null");
		
		if (nopolist.size() == 0)
		{
			throw new Exception("�òɹ������Ѿ�ȫ������Ϊ�ɹ�����ϣ����贴����");
		}
		//

		/**�ɹ���*/
        Po po = new Po();
		//	po.setPonum(Integer.toString(ponum));
		po.setPonum(ponum);
		//״̬������
		//po.setStatus(pr.getStatus());
		po.setStatus("����δ����");
		po.setVendor(pr.getVendor());
		po.setStatusdate(pr.getStatusdate());
		//����
		po.setDescription(pr.getDescription());
		//��������,��������
		po.setOrderdate(pr.getRequestdate());
		po.setRequireddate(pr.getRequireddate());
		//��Ӧ��,��ϵ��
//		po.setVendor(pr.getVendor());
		po.setContact(pr.getContact());
		//		���������ʽ��֧������
		po.setFreightterms(pr.getFreightterms());
		po.setShipvia(pr.getShipvia());
		po.setPaymentterms(pr.getPaymentterms());
		//�ܼ�
		po.setTotalcost(totalcost);
		//�ջ��ˡ���ַ
		po.setShiptolabor(pr.getShiptoaddr());
		po.setShiptoaddr(pr.getShiptoaddr());
		//��Ʊ�ˡ���ַ
		po.setBilltolabor(pr.getBilltolabor());
		po.setBilltoaddr(pr.getBilltoaddr());
		//�����ˡ�ʱ��
		po.setChangeby(pr.getChangeby());
		po.setChangedate(pr.getChangedate());
		//����,��������,˰�ܶ�
		po.setExchangerate(pr.getExchangerate());
		po.setExchangedate(pr.getExchangedate());
		po.setTotaltax(pr.getTotaltax());
		//po.setCorpnum(pr.getCorpnum());
		//po.setSitenum(pr.getSitenum());

		//�������
		super.save(po);

		/***���ɲɹ�����**/

		//ȡ������������еĲɹ�������
		for (int i = 0; i < nopolist.size(); i++) {
			Prline pl = (Prline) nopolist.get(i);
			Poline ol = new Poline();
			pl.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			pl.setPonum(ponum);
			this.getBaseDao().updateObject(pl);
			//	int polinenum = this.getRowCountByWhere(ol, "ponum='"+po.getPonum()+"'")+1;
			ol.setPonum(ponum);
			String buditem = pl.getBuditem();
			String budnum = pl.getBudnum();
			if(Util.isNotNull(buditem)){
				ol.setBuditem(buditem);
			}
			else{
				ol.setBuditem("");
			}
			if(Util.isNotNull(budnum)){
				ol.setBudnum(budnum);
			}else{
				ol.setBudnum("");
			}
			ol.setPolinenum(Long.parseLong(Integer.toString(i + 1)));
			//				�����Ŀ���������ֿ�,�������
			ol.setItemnum(pl.getItemnum());
			ol.setDescription(pl.getDescription());
			ol.setWarehouse(pl.getWarehouse());
			ol.setStocktype(pl.getStocktype());
			//				����������������λ��ת��ϵ��
			ol.setOrderqty(pl.getOrderqty());
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

			ol.setRejectedqty(ol.getOrderqty() - ol.getReceivedqty());
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
			ol.setService(pl.getIsservice());
			ol.setRemark(pl.getRemark());
			//����λ��,��ɽ���,��Ҫ���,����ĳɱ�,�ѷ�̯
			ol.setLocation(pl.getLocation());
			ol.setReceiptscomplete("��");
			ol.setInspection(pl.getInspection());
			ol.setLoadedcost(pl.getLoadedcost());
			ol.setProrated(pl.getProrateservice());

			//����,��������
			ol.setWonum(pl.getWonum());
			ol.setTasknum(pl.getTasknum());
			//ol.setCorpnum(pl.getCorpnum());
			//ol.setSitenum(pl.getSitenum());
			super.save(ol);
		}
		//return Integer.toString(ponum);
		return ponum;
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ѡ�е�����ƻ��кϲ����ɹ���������
	 * ���ڣ�Oct 22, 2008 3:44:16 PM
	 *
	 */
	public void uniteprline(List list,Object obj) throws Exception{
		Pr pr = (Pr) obj;
		
		String prnum = pr.getPrnum();
		for(int i=0;i<list.size();i++)
		{
			Prline popuprline = (Prline) list.get(i); //����������ѡ�еļ�¼
			String itemnum = popuprline.getItemnum();
			List prlinelist = this.getBaseDao().findWithQuery(Prline.class, "prnum = '"+prnum+"' and itemnum = '"+itemnum+"'");
//			����ڵ�ǰ�ɹ������£�����ͬ�����Ŀ��ŵĲɹ������оͺϲ�������Ļ����´���һ����¼��
			if(prlinelist.size()>0)
			{
				Prline prl = (Prline) prlinelist.get(0);//�Ѿ����뵽�òɹ�������������µļ�¼��
				prl.setOrderqty(prl.getOrderqty() + popuprline.getOrderqty());
				
				this.getBaseDao().updateObject(prl);
				/****
				 *��������ƻ����ɲɹ�����󣬽������ɵĲɹ������ź��кŷ�д����Ӧ������ƻ�����
				 */
				popuprline.setToprlinenum(prl.getToprlinenum());
				popuprline.setToprnum(prl.getPrnum());
				this.getBaseDao().updateObject(popuprline);
				
			}
			else
			{
				Prline prline = new Prline();
				long maxID = this.getBaseDao().selectLongMaxByHql("select max(pr.prlinenum) from Prline pr where pr.prnum='" + pr.getPrnum() + "'") + 1;
				prline.setPrnum(pr.getPrnum());
				prline.setPrlinenum(maxID);
				prline.setItemnum(popuprline.getItemnum());
				prline.setDescription(popuprline.getDescription());
				prline.setWarehouse(popuprline.getWarehouse());
				prline.setOrderqty(popuprline.getOrderqty());
				prline.setUnitcost(popuprline.getUnitcost());
				prline.setOrderunit(popuprline.getOrderunit());
				prline.setConversion(popuprline.getConversion());
				prline.setUnitcost(popuprline.getUnitcost());
				prline.setLinecost(popuprline.getLinecost());
				prline.setEnterby(this.getLaborInfo().getLaborname());
				prline.setEnterdate(new Date());
				prline.setProrateservice("��");
				prline.setIsservice("��");
				prline.setLoadedcost(0d);
				prline.setInspection("��");
				//prline.setCorpnum(pr.getCorpnum());
				//prline.setSitenum(pr.getSitenum());
				this.getBaseDao().saveObject(prline);
				
				/****
				 *��������ƻ����ɲɹ�����󣬽������ɵĲɹ������ź��кŷ�д����Ӧ������ƻ�����
				 */
				popuprline.setToprlinenum(prline.getToprlinenum());
				popuprline.setToprnum(prline.getPrnum());
				this.getBaseDao().updateObject(popuprline);
				
			}
			
		}
		
	}
	
	
	
	 
		/**
		*@author ����
		*���ܣ�
		*@param list
		*@param obj
		*@throws Exception 
		*2008-1-22����10:14:34
		*/
		public String copypr(List list, Object obj) throws Exception {
			if (!(obj instanceof Pr))
				throw new Exception("���󴫵ݲ���ȷ!");
			Pr pr = (Pr) obj;
			Pr newpr = new Pr();
			String prnum = this.genInskey("PRNUM");
			newpr.setPrnum(prnum);
			newpr.setStatus("WAPPR");
			newpr.setDescription(pr.getDescription());
			newpr.setStatusdate(new Date());
			newpr.setChangeby(this.getLaborInfo().getLaborname());
			newpr.setRequestdate(new Date());
			newpr.setRequestedby(this.getLaborInfo().getLaborname());
			newpr.setRequestdept(this.getLaborInfo().getDeptnum());
			newpr.setChangedate(new Date());
			//newpr.setCorpnum(pr.getCorpnum());
			//newpr.setSitenum(pr.getSitenum());
			this.save(newpr);
			List copypl = this.getBaseDao().findWithQuery(Prline.class, "prnum='" + pr.getPrnum() + "'");
			for (int i = 0; i < copypl.size(); i++) {
				Prline prline = new Prline();
				Prline pl = (Prline) copypl.get(i);
//				pl.setId(this.get)
				long maxID = this.getBaseDao().selectLongMaxByHql("select max(pr.prlinenum) from Prline pr where pr.prnum='" + newpr.getPrnum() + "'") + 1;
				prline.setPrnum(newpr.getPrnum());
				prline.setPrlinenum(maxID);
				prline.setItemnum(pl.getItemnum());
				prline.setDescription(pl.getDescription());
				prline.setWarehouse(pl.getWarehouse());
				prline.setOrderqty(pl.getOrderqty());
				prline.setOrderunit(pl.getOrderunit());
				prline.setConversion(pl.getConversion());
				prline.setUnitcost(pl.getUnitcost());
				prline.setLinecost(pl.getLinecost());
				prline.setEnterby(this.getLaborInfo().getLaborname());
				prline.setEnterdate(new Date());
				prline.setProrateservice("��");
				prline.setIsservice("��");
				prline.setLoadedcost(0d);
				prline.setInspection("��");
				//prline.setCorpnum(pl.getCorpnum());
				//prline.setSitenum(pl.getSitenum());
			
				super.save(prline);
			
			}
			return prnum;


		}
	/////////////////////ҵ�񷽷�//////////////////////////////////

}
