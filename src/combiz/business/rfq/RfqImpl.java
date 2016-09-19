package combiz.business.rfq;

import combiz.domain.contract.Contline;
import combiz.domain.contract.Contract;
import combiz.domain.inventory.Item;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.rfq.Rfq;
import combiz.domain.rfq.Rfqline;
import combiz.domain.rfq.Rfqquoteline;
import combiz.domain.rfq.Rfqvendor;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zul.Messagebox;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */

public class RfqImpl extends IBOBaseImpl
implements RfqSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Rfq))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Rfq rfq = (Rfq)obj;
		//�õ����е�������¼��صı�����
		
		List rfqquoteline = this.getBaseDao().findWithQuery(Rfqquoteline.class, "rfqnum='"+rfq.getRfqnum()+"'");
		//ɾ����Щ��¼
		this.getBaseDao().deleteBatch(rfqquoteline);
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		
		//ѯ�۵���
		this.deleteAllChild(obj, "rfqline");
		//ѯ�۹�Ӧ��
		this.deleteAllChild(obj, "rfqvendor");
		//������
		this.deleteAllChild(obj,"");
	}
	
	/***
	 * 
	 * ͨ��ѯ�۵��к�ѯ�۹�Ӧ���Զ����ɱ����У���ʵ������Դ��ѯ�۵���ѯ�۹�Ӧ��
	 * @param obj
	 * @throws Exception
	 * @����� 2007-8-17 ����10:02:53
	 */
	public void createquot(Object obj) throws Exception{
		if(!(obj instanceof Rfq))
			throw new Exception("���񴫵ݲ���ȷ!");
		Rfq rfq = (Rfq)obj;
		
		List rlList = this.getBaseDao().findWithQuery(Rfqline.class, "rfqnum='"+rfq.getRfqnum()+"'");
		List rvList = this.getBaseDao().findWithQuery(Rfqvendor.class, "rfqnum='"+rfq.getRfqnum()+"'");
	
		for(int i = 0;i<rlList.size();i++){
			Rfqline rl = (Rfqline)rlList.get(i);
			for(int j = 0;j<rvList.size();j++){
				Rfqquoteline  rql = new Rfqquoteline();
				Rfqvendor rv = (Rfqvendor)rvList.get(j);
//				ѯ�۵�
				rql.setRfqnum(rfq.getRfqnum());
				//ѯ�۵���
				rql.setRfqlinenum(rl.getRfqlinenum());
//				�����Ŀ,����,
				rql.setItemnum(rl.getItemnum());
				rql.setDescription(rl.getDescription());
				//������,��Ӧ���ͺ�
				rql.setManufacturer(rl.getManufacturer());
				rql.setModelnum(rl.getModelnum());
				//��������,������λ,����,���ܼ�
				rql.setOrderqty(rl.getOrderqty());
				rql.setOrderunit(rl.getOrderunit());
				//rql.setUnitcost(rl.getUnitcost());
				//rql.setLinecost(rl.getOrderqty()*rl.getUnitcost());
				//ת��ϵ��,��������,¼����
				rql.setConversion(rl.getConversion());
				rql.setEnterdate(rl.getEnterdate());
				rql.setEnterby(rl.getEnterby());
				//����,˰��
				rql.setIsawarded("��");
				rql.setTax(0.00);
				//����,����
				rql.setIsservice(rl.getIsservice());
				rql.setRemark(rl.getRemark());
				//��Ӧ ��
				rql.setVendor(rv.getVendor());
				//rql.setSitenum(rl.getSitenum());
				//rql.setCorpnum(rl.getCorpnum());
				super.save(rql);
				
				
			}
		}
		
	}
	/* 
	 * ���ܣ�ͨ��ѯ�۹�Ӧ�̼���Ӧ�ı��������ɲɹ����Ͳɹ�����
	 * ���ߣ����
	 * ���ڣ�Sep 17, 20084:33:54 PM
	 */
	public void CreatePo(List rvlist,Rfq rfq) throws Exception {
		int count = 0;
		for (int a=0;a<rvlist.size();a++) {
			Rfqvendor rv =(Rfqvendor) rvlist.get(a);

			if(!(rv instanceof Rfqvendor))
				throw new Exception("���񴫵ݴ��󣺷Ƕ���ѯ�۹�Ӧ��!");	
		
	
		
		//ѭ��������
			List rl = this.getBaseDao().findWithQuery(Rfqquoteline.class, 
					"rfqnum='"+rv.getRfqnum()+"' and vendor='"+rv.getVendor()+"' and isawarded='��'");
			int vendorcount = 0;
			String ponum = null;
			Double totalcost = 0d;
			Po po = null;
			/*if (rl.size() <=0)
				throw new Exception("�޷����ɶ��������ȶԸù�Ӧ��'"+rv.getVendor()+"'�ı����н�����Ȩ������");*/

		
			for(int i=0;i<rl.size();i++)
			{
				Rfqquoteline rol = (Rfqquoteline)rl.get(i);
				List rfqlinelist = this.getBaseDao().findWithQuery(Rfqline.class, 
						"rfqnum='"+rol.getRfqnum()+"' and rfqlinenum="+rol.getRfqlinenum()+" and itemnum='"+rol.getItemnum()+"' and ponum is null");
				if(rfqlinelist.size()<=0)
				{
					continue;
				}
				Rfqline rfqline = (Rfqline) rfqlinelist.get(0);
				vendorcount++;
				if(vendorcount == 1)//�ù�Ӧ�̵�һ�����ɲɹ�����
				{
					po = new Po();
					count ++;
					ponum = this.genInskey("PONUM");
					po.setDescription(rfq.getDescription());
					po.setStatus("ѯ�۵�����");
					po.setReceipts("δ����");
					po.setStatusdate(new Date());
					po.setChangeby(this.getUserInfo().getLabornum());
					po.setChangedate(new Date());
					po.setPonum(ponum);
					po.setVendor(rv.getVendor());//��Ӧ��
					po.setContact(rv.getContact());//��ϵ��
					po.setFreightterms(rv.getFreightterms());//��������
					po.setPaymentterms(rv.getPaymentterms());//֧������
					po.setShipvia(rv.getShipvia());//���䷽ʽ
					po.setTotalcost(totalcost);
					//po.setSitenum(this.getLaborInfo().getSitenum());
					//po.setCorpnum(this.getLaborInfo().getCorpnum());
					this.getBaseDao().saveObject(po);
					
				}
				//�½��ɹ�����
				Poline pl = new Poline();
				Long polinenum = Long.parseLong(Integer.toString(i+1));
				pl.setPolinenum(polinenum);
				pl.setPonum(ponum);
				pl.setItemnum(rol.getItemnum());
				pl.setDescription(rol.getDescription());
				if(Util.isNull(rfqline.getWarehouse()))
					throw new Exception("ѯ�۵���["+rol.getItemnum()+"]�Ĳֿ��ֶ�Ϊ��,�޷����ɶ���!");
				pl.setWarehouse(rfqline.getWarehouse());
				pl.setOrderunit(rol.getOrderunit());
				if(rol.getConversion()==null)
				{
					pl.setConversion(0.00);
				}
				else
				{
					pl.setConversion(rol.getConversion());
				}
				//
				Double orderqty = (rol.getOrderqty()==null?0.0D:rol.getOrderqty());
				Double unitcost = (rol.getUnitcost()==null?0.0D:rol.getUnitcost());
				pl.setOrderqty(orderqty);
				pl.setUnitcost(unitcost);
				Double linecost = orderqty * unitcost;
				totalcost = totalcost + linecost;
				pl.setLinecost(linecost);
				pl.setTaxunitcost(unitcost);
				pl.setTaxlinecost(linecost);
				pl.setReceivedqty(0.0);
				pl.setReceivedunitcost(0.0);
				pl.setReceivedtotalcost(0.0);
				pl.setTaxcode(rol.getTaxcode());
				pl.setTaxrate(0.0);
				pl.setTax(rol.getTax());
				pl.setRejectedqty(0.0);
				pl.setEnterdate(rol.getEnterdate());
				pl.setEnterby(this.getUserInfo().getLabornum());
				pl.setManufacturer(rol.getManufacturer());
				pl.setModelnum(rol.getModelnum());
				pl.setService(rol.getIsservice());
				pl.setRemark(rol.getRemark());
				pl.setReceiptscomplete("��");
				pl.setInspection("��");
				pl.setLoadedcost(0.0);
				pl.setProrated("��");
				//pl.setCorpnum(this.getLaborInfo().getCorpnum());
				//pl.setSitenum(this.getLaborInfo().getSitenum());
				this.getBaseDao().saveObject(pl);
				
				//ͬʱ����ѯ�۵���
				rfqline.setPonum(ponum);
				rfqline.setPolinenum(polinenum);
				super.update(rfqline);
				
				
				//ͬʱ���²ɹ�������
				List prlines = this.getBaseDao().findWithQuery(Prline.class, 
						"rfqnum='"+rol.getRfqnum()+"' and rfqlinenum="+rol.getRfqlinenum()+" and itemnum='"+rol.getItemnum()+"'");
				for(int j=0;j<prlines.size();j++)
				{
					Prline prline = (Prline) prlines.get(j);
					prline.setPonum(ponum);
					prline.setPolinenum(polinenum);
					super.update(prline);
				}
				
				po.setTotalcost(totalcost);//�ܼ�
				this.getBaseDao().updateObject(po);
				


			}
		}
		if(count == 0)
		{
			throw new Exception("��ѡ�еĹ�Ӧ���Ѿ�ȫ�����ɹ��������˴β���û�������κζ�����");
		}
		
		
	}
	
	/* 
	 * ���ܣ����������ѯ�۵������ɺ�ͬ��
	 * ���ߣ�����
	 * ���ڣ�Oct 24, 2008  4:07:57 PM
	 */
	public void createcont(List rvlist,List vendorlist,Rfq rfq) throws Exception {
		
		if(vendorlist.size() >0)
		{
			//���չ�Ӧ�̽��з����ȡ����Ӧ�̵���Ϣ���ֱ����ɺ�ͬ��
			for(int j=0;j<vendorlist.size();j++)
			{
				Map obj = (Map) vendorlist.get(j);
				String vendor = null;
				//���ɺ�ͬ����
				if (obj.size() > 0)
				{
					if (obj.get("VENDOR") != null) 
					{
						vendor = (obj.get("VENDOR")).toString();
						System.out.println(vendor);
					}
					Contract cont = new Contract();
					String cntnum = this.genInskey("CONTRACTNUM");
					cont.setCntnum(cntnum);
					cont.setStatus("����δ����");
					cont.setStatusdate(new Date());
					cont.setVendor(vendor);
					cont.setCntversion("V1.0");
					//cont.setSitenum(rfq.getSitenum());
					//cont.setCorpnum(rfq.getCorpnum());
					this.getBaseDao().saveObject(cont);
					
					for (int a=0;a<rvlist.size();a++)
					{
						Rfqquoteline rfqline =(Rfqquoteline) rvlist.get(a);
						if(rfqline.getVendor().equals(vendor))
						{
							Contline contline = new Contline();
							contline.setCntnum(cntnum);
							long maxID = this.getBaseDao().selectLongMaxByHql("select  max(co.linenum) from Contline co where co.cntnum='"+cont.getCntnum()+"'")+1;
							contline.setLinenum(maxID);
							contline.setItemnum(rfqline.getItemnum());
							contline.setConversion(1d);
							//contline.setSitenum(rfq.getSitenum());
							//contline.setCorpnum(rfq.getCorpnum());
							List list = this.getBaseDao().findWithQuery(Item.class,"itemnum='" +rfqline.getItemnum()+ "'");
							if (list.size() >= 0) 
							{
								Item item = (Item) list.get(0);
								contline.setDescription(item.getDescription());
								contline.setOrderunit(item.getOrderunit());
								contline.setTaxcode(item.getTaxcode());
							}
							
							contline.setTaxunitcost(rfqline.getUnitcost());
							contline.setOrderqty(rfqline.getOrderqty());
							contline.setTaxlinecost(rfqline.getUnitcost() * rfqline.getOrderqty());
							contline.setLoadedcost(rfqline.getUnitcost() * rfqline.getOrderqty());
							contline.setRejectedqty(0d);
							contline.setEnterdate(new Date());
							contline.setEnterby(this.getUserInfo().getLabornum());
							contline.setRequestedby(rfqline.getEnterby());
							contline.setService("��");
							contline.setInspection("��");
							this.getBaseDao().saveObject(contline);
						}

					}
				}
			}
		}
		
		
	}
	/**
	 * 
	 * �����ɹ������У�����һ�����壬����Ϊ���еĲɹ������С��������û�ѡ�����е�һ��������
	 * @param obj
	 * @param list
	 * @throws Exception
	 * @����� 2007-8-24 ����06:00:05
	 */

	public void CopyPrline(Object obj, List list) throws Exception {
		if(!(obj instanceof Rfq)) 
			throw new Exception("���󴫵ݲ���ȷ!");
		Rfq rfq = (Rfq)obj;
		for(int i = 0;i<list.size();i++)
		{
			Prline pl = (Prline)list.get(i);
			Rfqline rl = new Rfqline();
			rl.setRfqnum(rfq.getRfqnum());
			long n = this.getBaseDao().selectLongMaxByHql("select max(rl.rfqlinenum) from Rfqline rl where rl.rfqnum='"+rfq.getRfqnum()+"'")+1;
			//System.out.println(n);
			rl.setRfqlinenum(n);
			rl.setItemnum(pl.getItemnum());rl.setWarehouse(pl.getWarehouse());
			rl.setDescription(pl.getDescription());
			rl.setOrderqty(pl.getOrderqty());rl.setOrderunit(pl.getOrderunit());
			rl.setConversion(pl.getConversion());
			rl.setReqdeliverydate(pl.getReqdeliverydate());
			rl.setEnterby(pl.getEnterby());rl.setEnterdate(pl.getEnterdate());
			rl.setPonum(pl.getPonum());rl.setPolinenum(pl.getPolinenum());
			rl.setStocktype(pl.getStocktype());rl.setRemark(pl.getRemark());
			rl.setIsservice(pl.getIsservice());
			rl.setManufacturer(pl.getManufacturer());rl.setModelnum(pl.getModelnum());
			rl.setUnitcost(pl.getUnitcost());
			rl.setLinecost(pl.getLinecost());
			rl.setInspection(pl.getInspection());
			rl.setEqnum(pl.getEqnum());rl.setLocation(pl.getLocation());
			rl.setWonum(pl.getWonum());rl.setTasknum(pl.getTasknum());
			//rl.setSitenum(pl.getSitenum());
			//rl.setCorpnum(pl.getCorpnum());
			super.save(rl);
			
			//��д���� ����rfqnum��rfqlinenumд�ؿ������Ĳɹ�����
			pl.setRfqnum(rfq.getRfqnum());
			pl.setRfqlinenum(rl.getRfqlinenum());
			super.save(pl);
			
		}
		
	}

	
	
}
