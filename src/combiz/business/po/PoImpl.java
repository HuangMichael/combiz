package combiz.business.po;

import combiz.domain.classattr.Classspec;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.po.Conversion;
import combiz.domain.po.Invvendor;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.inventory.Item;
import combiz.domain.pr.Prline;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workflow.Wfassignment;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;
import combiz.domain.inventory.Invstock;
import combiz.domain.location.Locations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.inbasis.zk.ui.Sessions;
import com.inbasis.zul.Messagebox;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */

public class PoImpl extends IBOBaseImpl implements PoSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
		// ɾ���ӱ��¼--�ɹ�����

		this.deleteAllChild(obj, "poline");
	}
	
	
	
	@Override
	public void wfReassign(Object arg0, Wfassignment arg1, String arg2)
			throws Exception {
		// TODO Auto-generated method stub
		String a = arg2;
		Po po = (Po) arg0;
		if(po.getStatus().equals("�ȴ�����"))
		{
			po.setChangeby(a);
			po.setContact(a);
			this.update(po);
		}
		super.wfReassign(arg0, arg1, arg2);
	}



	/**
	 * �����̷��͵�ʱ���ж����вɹ��еĲֿ��Ƿ���д
	 * brianhong  2008-2-22
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 */
	@Override
	public boolean validData(Object arg0, Wfinstance arg1)
	throws Exception 
	{
		List polineList = this.getListByRelation(arg0, "poline");
		for(int i=0;i<polineList.size();i++)
		{
			Poline poline = (Poline) polineList.get(i);
			if(Util.isNull(poline.getWarehouse()))
			{
				Messagebox.show("�ɹ�������["+poline.getItemnum()+"]����Ŀ���ڲֿ�Ϊ�գ�����д��");
				return false;
			}
		}
		return true;
	}

	
	/**
	 * 
	 * @TODO �����ɹ��������С�����һ�����壬�û�����ѡ�����еĲ����������ݡ�
	 * @param list
	 * @throws Exception
	 * @����� 2007-8-24 ����05:53:29
	 */
	public void copyprline(List list, Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("���󴫵ݲ���ȷ!");
		Po po = (Po) obj;
		// Prline prline = new Prline();
		
		for (int i = 0; i < list.size(); i++) {
			Prline pl = (Prline) list.get(i);
			Poline p = new Poline();
			long maxID = this.getBaseDao().selectLongMaxByHql("select max(po.polinenum) from Poline po where po.ponum='" + po.getPonum() + "'") + 1;
			p.setPonum(po.getPonum());
			p.setPolinenum(maxID);

			p.setItemnum(pl.getItemnum());
			p.setDescription(pl.getDescription());
			p.setWarehouse(pl.getWarehouse());
			p.setOrderqty(pl.getOrderqty());
			p.setOrderunit(pl.getOrderunit());
			if (pl.getConversion() == null) {
				p.setConversion(0.0);
			} else {
				p.setConversion(pl.getConversion());
			}
			p.setUnitcost(pl.getUnitcost());
			p.setTaxunitcost(0.0);
			p.setTaxlinecost(0.0);
			p.setWarehouse(pl.getWarehouse());
			p.setLinecost(pl.getLinecost());
			p.setReceivedqty(0.0);
			p.setReceivedunitcost(0.0);
			p.setReceivedtotalcost(0.0);
			p.setTaxrate(0.00);
			p.setTax(0.00);
			p.setRejectedqty(0.0);
			p.setVendeliverydate(pl.getVendeliverydate());
			p.setRequestedby(pl.getEnterby());
			p.setEnterdate(pl.getEnterdate());
			p.setEnterby(this.getLaborInfo().getLabornum());
			p.setRequestedby(pl.getRequestedby());
			p.setReqdeliverydate(pl.getReqdeliverydate());
			p.setManufacturer(pl.getManufacturer());
			p.setModelnum(pl.getModelnum());
			p.setService(pl.getIsservice());
			p.setStocktype(pl.getStocktype());
			p.setRemark(pl.getRemark());
			p.setLocation(pl.getLocation());
			p.setReceiptscomplete("��");
			p.setInspection(pl.getInspection());
			p.setLoadedcost(pl.getLoadedcost());
			p.setProrated(pl.getProrateservice());
			p.setWonum(pl.getWonum());
			p.setTasknum(pl.getTasknum());
			//p.setCorpnum(po.getCorpnum());
			//p.setSitenum(po.getSitenum());
			super.save(p);

			// ��д����,���ɹ����źͲɹ����кŻ�д��ɹ���������

			pl.setPonum(po.getPonum());
			pl.setPolinenum(p.getPolinenum());
			super.save(pl);

		}

	}

	/**
	 * 
	 * @TODO �����ɹ��������С�����һ�����壬�û�����ѡ�����еĲ����������ݡ�
	 * @param list
	 * @throws Exception
	 * @����� 2007-8-24 ����05:53:29
	 */
	public void addpoline(List list, Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("���󴫵ݲ���ȷ!");
		Po po = (Po) obj;

		for (int i = 0; i < list.size(); i++) {

			Poline pl = (Poline) list.get(i);

			/*******************************************************************
			 *******************************************************************
			 1.����Invrectrans��д����   
			 ********************************************************************/

			List itemlist = this.getBaseDao().findWithQuery(Item.class, "itemnum='"+pl.getItemnum()+"'");
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);

				/**********************************************************************************************************
				 * *********************���ڶ�����λ�ͷ��ŵ�λת��ϵ���Ĵ���******************************************************
				 * *********************************************************************************************************
				 */
				Double realqty = pl.getRealqty();
				Double convrealqty = 0d;
				String issueunit = null;
				Double conversion = 0d;
				Double unitcost = 0d;

				Double hasqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + pl.getPonum() + "' and t.itemnum = '" + pl.getItemnum() + "'");
				if (hasqty == null)
					hasqty = 0d;


				List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, " itemnum='"+pl.getItemnum()+"' and warehouse ='"+pl.getWarehouse()+"'");
				if(inventorylist.size()>0)//����ÿ����Ŀ�ڲֿ��д��ڡ�
				{
					Inventory inventory = (Inventory) inventorylist.get(0);
					issueunit = inventory.getIssueunit();
				}
				else//����ÿ����Ŀ�ڲֿ��в����ڣ������ʱ��������
				{
					issueunit = item.getIssueunit();
				}
				if(pl.getOrderunit().equals(issueunit))
				{
					convrealqty = pl.getRealqty();
					conversion = 1d;
				}
				else
				{
					List convlist = this.getBaseDao().findWithQuery(Conversion.class, "orderunit = '"+pl.getOrderunit()+"' and issueunit = '"+issueunit+"'and itemnum = '"+pl.getItemnum()+"'");
					if(convlist.size()>0)
					{
						Conversion conv = (Conversion) convlist.get(0);
						conversion = conv.getConversion();
						convrealqty = pl.getRealqty()*conversion;
					}
					else
					{
						throw new Exception("�������ʱ���������ʱ��Ϊ'"+pl.getItemnum()+"'������,��Ӷ�����λΪ'"+pl.getOrderunit()+"',���ŵ�λΪ'"+issueunit+"'֮���ת��ϵ��!");
					}
				}

				/**********************************************************************************************************
				 * *********************�ж��Ƿ�����ת��******************************************************
				 * *********************************************************************************************************
				 */

				if(item.getRotating().equals("��"))//��ת��(item.getLottype().equals("���ι���"))
				{
					int lastrealqty = (int) (convrealqty+0);
					//�ó���ǰ����YYYYMMDDHHMMSS��Ϊ�������κ�
					Date date=new Date(); 
					SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
					String nowdate=sp.format(date); 
					for(int j=0;j<lastrealqty;j++)
					{
						Invrectrans irt = new Invrectrans();
						irt.setQuantity(conversion);
						irt.setModelnum(pl.getModelnum());
						irt.setUnitcost(pl.getUnitcost()/conversion);
						irt.setConversion(conversion);
						irt.setRecunit(issueunit);
						irt.setTolot(nowdate);
						String buditem = pl.getBuditem();
						String budnum = pl.getBudnum();
						if(Util.isNotNull(buditem)){
							irt.setBuditem(buditem);
						}
						else{
							irt.setBuditem("");
						}
						if(Util.isNotNull(budnum)){
							irt.setBudnum(budnum);
						}else{
							irt.setBudnum("");
						}
						List avglist = (List) this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + pl.getItemnum() + "' and warehouse = '" + pl.getWarehouse() +"'" );
						irt.setPonum(pl.getPonum());
						irt.setPolinenum(pl.getPolinenum());
						irt.setItemnum(pl.getItemnum());
						irt.setTowarehouse(pl.getWarehouse());
						irt.setTransdate(new Date());
						irt.setActualdate(new Date());
						irt.setLinecost(pl.getUnitcost()/conversion);
						irt.setActualcost(pl.getReceivedunitcost());
						irt.setRejectqty(0d);
						irt.setDescription(pl.getDescription());
						//irt.setSitenum(pl.getSitenum());
						//irt.setCorpnum(pl.getCorpnum());
						//����������������
						irt.setChangeby(pl.getEnterby());
						List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + pl.getItemnum() + "' and warehouse ='" + pl.getWarehouse() +"'");
						if(invstocklist.size()>0)
						{
							Invstock invstock = (Invstock) invstocklist.get(0);
							Double curbal = invstock.getCurbal();
							irt.setCurbal(curbal);
						}
						else
						{
							irt.setCurbal(0.0d);
						}


						irt.setRectype("����"); //����invtrans���е�����Ϊ����
						irt.setLoadedcost(pl.getLoadedcost());
						irt.setStatus("�����");
						if(avglist.size() > 0)
						{
							Inventory inventory = (Inventory) avglist.get(0);
							irt.setOldavgcost(inventory.getAvgcost());
						}
						else
						{
							irt.setOldavgcost(0d);
						}
						irt.setOldavgcost(0d);
						this.getBaseDao().saveObject(irt);
					}
				}
				else//����ת��
				{
					Invrectrans irt = new Invrectrans();
					List avglist = (List) this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + pl.getItemnum() + "' and warehouse = '" + pl.getWarehouse() +"'" );
					Double waitqty = pl.getOrderqty() - hasqty;
					irt.setPonum(pl.getPonum());
					irt.setPolinenum(pl.getPolinenum());
					irt.setItemnum(pl.getItemnum());
					irt.setTowarehouse(pl.getWarehouse());
					irt.setTransdate(new Date());
					irt.setActualdate(new Date());
					irt.setPonum(pl.getPonum());
					irt.setPolinenum(pl.getPolinenum());
					irt.setUnitcost(pl.getUnitcost());
					irt.setLinecost(pl.getLinecost());
					irt.setActualcost(pl.getReceivedunitcost());
					irt.setRejectqty(0d);
					irt.setDescription(pl.getDescription());
					String buditem = pl.getBuditem();
					String budnum = pl.getBudnum();
					if(Util.isNotNull(buditem)){
						irt.setBuditem(buditem);
					}
					else{
						irt.setBuditem("");
					}
					if(Util.isNotNull(budnum)){
						irt.setBudnum(budnum);
					}else{
						irt.setBudnum("");
					}	
					//irt.setSitenum(pl.getSitenum());
					//irt.setCorpnum(pl.getCorpnum());
					//����������������
					irt.setConversion(pl.getConversion());
					irt.setChangeby(pl.getEnterby());
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + pl.getItemnum() + "' and warehouse ='" + pl.getWarehouse() +"'");
					if(invstocklist.size()>0)
					{
						Invstock invstock = (Invstock) invstocklist.get(0);
						Double curbal = invstock.getCurbal();
						irt.setCurbal(curbal);
					}
					else
					{
						irt.setCurbal(0.0d);
					}

					/**********************************************************************************************************
					 * *********************�ж��Ƿ������ι���******************************************************
					 * *********************************************************************************************************
					 */

					if(item.getLottype().equals("���ι���"))
					{
						Date date=new Date(); 
						SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
						String nowdate=sp.format(date); 
						irt.setTolot(nowdate);

					}

					irt.setRecunit(issueunit);
					irt.setConversion(conversion);
					irt.setQuantity(convrealqty);
					irt.setUnitcost(pl.getUnitcost()/conversion);
					irt.setRectype("����"); //����invtrans���е�����Ϊ����
					irt.setLoadedcost(pl.getLoadedcost());
					irt.setStatus("������");
					if(avglist.size() > 0)
					{
						Inventory inventory = (Inventory) avglist.get(0);
						irt.setOldavgcost(inventory.getAvgcost());
					}
					else
						irt.setOldavgcost(0d);
					this.getBaseDao().saveObject(irt);
				}

			}

		}


	}
	/* 
	 * ���ܣ������ɹ���
	 * ���ߣ�����
	 * ���ڣ�Nov 2, 2008  1:11:20 PM
	 */
	public Po copypo(Object obj) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("���󴫵ݲ���ȷ!");
		Po po = (Po) obj;
		Po newpo = new Po();
		String sitenum = null;
		String corpnum=null;
		String labornum = this.getUserInfo().getLabornum();
		List laborlist = this.getBaseDao().findWithQuery(Labor.class, "labornum='"+labornum+"'");
		if(laborlist.size()>0)
		{
			Labor labor = (Labor) laborlist.get(0);
			sitenum = labor.getSitenum();
			corpnum = labor.getCorpnum();
		}
		
		String ponum = this.genInskey("PONUM");
		newpo.setPonum(ponum);
		newpo.setStatus("����δ����");
		newpo.setDescription(po.getDescription());
		newpo.setStatusdate(new Date());
		newpo.setChangeby(this.getLaborInfo().getLaborname());
		newpo.setVendor(po.getVendor());
		newpo.setTotalcost(po.getTotalcost());
		//newpo.setCorpnum(corpnum);
		//newpo.setSitenum(sitenum);
		
		this.save(newpo);
		
		List copypol = this.getBaseDao().findWithQuery(Poline.class, "ponum='" + po.getPonum() + "'");
		for (int i = 0; i < copypol.size(); i++) {
			Poline poline = new Poline();
			Poline pl = (Poline) copypol.get(i);
			Long maxID = this.getBaseDao().selectLongMaxByHql("select max(po.polinenum) from Poline po where po.ponum='" + newpo.getPonum() + "'") + 1;
			poline.setPonum(newpo.getPonum());
			poline.setPolinenum(maxID);
			poline.setItemnum(pl.getItemnum());
			poline.setDescription(pl.getDescription());
			poline.setWarehouse(pl.getWarehouse());
			poline.setStocktype(pl.getStocktype());
			poline.setTaxunitcost(0d);
			poline.setTaxlinecost(pl.getTaxlinecost());
			poline.setProrated("��");
			poline.setReceivedunitcost(0d);
			poline.setReceivedqty(0d);
			poline.setRejectedqty(0d);
			poline.setReceivedtotalcost(0d);
			poline.setReceiptscomplete("δ����");
			poline.setOrderqty(pl.getOrderqty());
			poline.setOrderunit(pl.getOrderunit());
			poline.setConversion(pl.getConversion());
			poline.setUnitcost(pl.getUnitcost());
			poline.setLinecost(pl.getLinecost());
			poline.setEnterby(this.getLaborInfo().getLaborname());
			poline.setEnterdate(new Date());
			poline.setService("��");
			poline.setInspection("��");
			poline.setLoadedcost(0d);
			//poline.setCorpnum(corpnum);
			//poline.setSitenum(sitenum);
			super.save(poline);
		
		}
		return newpo;


	}
	/* (non-Javadoc)
	 * @see combiz.business.po.PoSrv#verify(java.util.List, java.lang.Object)
	 */
	public void verify(List list, Object obj,List polinelist) throws Exception {
		if (!(obj instanceof Po))
			throw new Exception("���󴫵ݲ���ȷ!");
		Po po = (Po) obj;
		
		
	/*	for (int m = 0; m< list.size(); m++) 
		{
			Invrectrans invrectrans = (Invrectrans) list.get(m);
			String itemnum = invrectrans.getItemnum();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			if(item.getRotating().equals("��"))
			{
				if(Util.isNull(invrectrans.getEqnum()))
				{
					throw new Exception("���Ϊ:'"+itemnum+"'�Ŀ����Ŀ���ʲ���Ų���Ϊ�գ���ȷ�ϣ�");
				}
			}
			int count = this.getBaseDao().selectCountByHql("select count(*) from Invrectrans t where t.eqnum = '"+invrectrans.getEqnum()+"'");
			if(count>1)
			{
				throw new Exception("�����Ϊ:'"+itemnum+"'����ת����������ͬ���ʲ���ţ���ȷ�ϣ�");
			}
			
		}*/
		

		for (int i = 0; i < list.size(); i++) {
			Invrectrans invrectrans = (Invrectrans) list.get(i);
			String ponum = po.getPonum();
			String itemnum = invrectrans.getItemnum();
			String warehouse = invrectrans.getTowarehouse();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			String orderunit = item.getOrderunit();
			String issueunit = item.getIssueunit();
			Long polinenum = invrectrans.getPolinenum();
			
			
			/***********************************************************************
			 * ******************************************************************
			 * 3.�жϸÿ����Ŀ�Ƿ����ڿ�����У����û�з�������������Ϣ��ӵ�INVENTORY��
			 **********************************************************************/

			//�ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
			
			List unitcostlist = this.getBaseDao().findWithQuery(Poline.class, "ponum = '" + ponum +"' and polinenum = '" + polinenum + "'");
			Poline poline = (Poline) unitcostlist.get(0);
			Double totalrecqty = 0d;
			totalrecqty  = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invrectrans t where t.ponum = '" + ponum +"' and polinenum = '" + polinenum + "'" );
			if (totalrecqty == null)
				totalrecqty = 0d;
			poline.setReceivedqty(totalrecqty);
			//��ѯ�ڽ��ռ�¼�����вɹ������� ponum �Ľ��ռ�¼��
			if((poline.getOrderqty() - totalrecqty) > 0) 
				poline.setReceiptscomplete("���ֽ���");
			else
				poline.setReceiptscomplete("ȫ������");

			poline.setReceivedunitcost((poline.getReceivedunitcost()==null?0:invrectrans.getUnitcost()) );
			poline.setReceivedtotalcost((poline.getReceivedtotalcost()==null?0:poline.getReceivedtotalcost()) + invrectrans.getLinecost());

			super.update(poline);
			Double unitcost = poline.getUnitcost();
			String binnum = invrectrans.getTobin();
			List noitem = this.getBaseDao().findWithQuery(Inventory.class,"itemnum = '"+itemnum+"' and warehouse='"+ warehouse +"'");

			if(noitem.size() == 0){

				Inventory inventory = new Inventory();
				inventory.setItemnum(invrectrans.getItemnum());
				inventory.setItemdesc(invrectrans.getDescription());
				inventory.setWarehouse(invrectrans.getTowarehouse());
				inventory.setMinlevel(0d);
				inventory.setMaxlevel(0d);
				inventory.setStocktype("�ǳ������");
				inventory.setOrderqty(0d);
				inventory.setModelnum(invrectrans.getModelnum());
				inventory.setOrderunit(orderunit);
				inventory.setIssueunit(issueunit);
				inventory.setConversion(1d);
				inventory.setAvgcost(unitcost);// ��һ����ʱ�򣬿��ɱ���Ϊ���һ�ν��ճɱ���
				inventory.setLastcost(unitcost);
				inventory.setStdcost(unitcost);	
				inventory.setIssueytd(0d);
				//inventory.setSitenum(invrectrans.getSitenum());
				//inventory.setCorpnum(invrectrans.getCorpnum());
				super.save(inventory);

			}
			else
			{
				Inventory inventory = (Inventory) noitem.get(0);

				//List calavgcost = this.getMainSrv().getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'��') = '" + binnum);
//				List calavgcost = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"'");
				Double sumcurbal =  0d;

				sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '" + itemnum + "' and t.warehouse = '"+ warehouse +"'");
				if(sumcurbal == null)
					sumcurbal = 0d;
//				for (int j = 0;j < calavgcost.size();j++)
//				{
//				Invstock invstock = (Invstock) calavgcost.get(j);
//				sumcurbal = sumcurbal + invstock.getCurbal();

//				}
				Double avgcost = (sumcurbal * inventory.getAvgcost() + unitcost * invrectrans.getQuantity()) / (sumcurbal + invrectrans.getQuantity());
				inventory.setAvgcost(avgcost);
				super.update(inventory);

			}

			/***************************************************
			 * ******************************************************************
			 * 3.����Ƿ����ι����жϸÿ����Ŀ�Ƿ��ڿ��������������Ϣ�����û�з���������ֵ��ǰ����Ϊ0��
			 * ��������ι���Ļ�������INVSTOCK��INVLOT���в������ݡ� 
			 **************************************************/

			Double rqty = invrectrans.getQuantity();
			String rotating = item.getRotating();
			if(item.getLottype().equals("�����ι���"))//�����ι���
			{
				int size = 0;
				if(binnum == null)
					//List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and nvl(binnum,'��') = '" + binnum +"'");
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
					size = curbal.size();
					if (size == 0) {
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						// ��Ҫ�����������Ŀ����һ�����Ľ��մ���
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(rqty);
						invstock.setReconciled("��");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						super.save(invstock);


					} else {
						Invstock invstock = (Invstock) curbal.get(0);
						invrectrans.setCurbal(invstock.getCurbal());// ��Invrectrans�����curbal�ֶ�д�ص�ǰ�Ŀ��������
						Double nowcurbal = invstock.getCurbal() + rqty; // �����յĿ���������ӣ����¿��������
						invstock.setCurbal(nowcurbal);	
						super.getBaseDao().updateObject(invrectrans);
						super.save(invstock);// ע��˳��һ����д�ؿ����ձ��������Ϣ����Ϊ���ǽ���ǰ����������ٸ���invstock�Ŀ�������������������ݡ�
					}
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}
				else
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum = '" + binnum +"'");
					size = curbal.size();
					if (size == 0) {
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						// ��Ҫ�����������Ŀ����һ�����Ľ��մ���
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());

						invstock.setCurbal(rqty);
						invstock.setReconciled("��");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						super.save(invstock);
						invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
						Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
						String labornum = labor.getLabornum();
						invrectrans.setStatuschangeby(labornum);
						invrectrans.setStatusdate(new Date());
						super.getBaseDao().updateObject(invrectrans);

					} else {
						Invstock invstock = (Invstock) curbal.get(0);

						invrectrans.setCurbal(invstock.getCurbal());// ��Invrectrans�����curbal�ֶ�д�ص�ǰ�Ŀ��������
						Double nowcurbal = invstock.getCurbal() + rqty; // �����յĿ���������ӣ����¿��������
						invstock.setCurbal(nowcurbal);	
						super.save(invrectrans);
						super.save(invstock);// ע��˳��һ����д�ؿ����ձ��������Ϣ����Ϊ���ǽ���ǰ����������ٸ���invstock�Ŀ�������������������ݡ�
					}
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
					super.getBaseDao().updateObject(invrectrans);
				}

			}
			else//���ι�����豸
			{
				
				if(rotating.equals("��"))//��ת��
				{
					//���ʲ���equipment��������ݡ�
					int totallength = 10;//�ʲ���ŵ���λ����
					Equipment equip = new Equipment();
					equip.setInvcost(0.0D);
					equip.setBudgetcost(0.0D);
					equip.setPriority(0L);
					equip.setPurprice(0.0D);
					//equip.setReplacecost(0.0D);
					equip.setTotalcost(invrectrans.getUnitcost());
					equip.setYtdcost(0.0D);
					equip.setTotdowntime(0.0d);
					equip.setIsrunning("��");
					equip.setLotnum(invrectrans.getTolot());
					String eq = invrectrans.getEqnum();
					equip.setItemnum(invrectrans.getItemnum());
					equip.setLocation(invrectrans.getTowarehouse());
					equip.setDescription(item.getDescription());
					equip.setChangeby(this.getUserInfo().getLabornum());
					equip.setChangedate(new Date());
					equip.setInstalldate(new Date());
					//equip.setCorpnum(invrectrans.getCorpnum());
					//equip.setSitenum(invrectrans.getSitenum());
					equip.setClassid(item.getClassid());
					equip.setPlanyears(0L);
					equip.setModel(invrectrans.getModelnum());
					//equip.setPonum(invrectrans.getPonum());
					//equip.setPolinenum(invrectrans.getPolinenum());
					Double maxeq = 0d;
					long largeid = 0l;
					//���ʲ����б��
					//int count = this.getBaseDao().selectCountByHql("select count(*) from Equipment  t where t.classid = '"+item.getClassid()+"' and sitenum='"+invrectrans.getSitenum()+"' and corpnum='"+invrectrans.getCorpnum()+"'");
					int count = this.getBaseDao().selectCountByHql("select count(*) from Equipment  t where t.classid = '"+item.getClassid()+"'");
					List maxidlist = null;
					if(count>0)
					{
						if(IBSServer.getIBSServer().getDatabaseProductName().equals("SQLSERVER")){//�ж�ʹ�õ����ݿ���sqlserver����oracle���ݿ⡣
							/*	maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substring(t.eqnum,len(t.classid)+1,len(t.eqnum)-len(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "' and sitenum='"
											+ invrectrans.getSitenum()
											+ "' and corpnum='"
											+ invrectrans.getCorpnum()
											+ "'");*/
							maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substring(t.eqnum,len(t.classid)+1,len(t.eqnum)-len(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "'");
							if (maxidlist.size() > 0) {
								Map map = (Map) maxidlist.get(0);
								if (map.size() > 0) {
									Double maxvalue = (Double) map
											.get("LARGEID");
									maxeq = maxvalue.doubleValue() + 1;
									largeid = maxeq.longValue();
								}
							}
						}else{
							/*maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substr(t.eqnum,length(t.classid)+1,length(t.eqnum)-length(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "' and sitenum='"
											+ invrectrans.getSitenum()
											+ "' and corpnum='"
											+ invrectrans.getCorpnum()
											+ "'");*/
							maxidlist = this
							.getBaseDao()
							.selectListBySql(
									"select max(abs(substr(t.eqnum,length(t.classid)+1,length(t.eqnum)-length(t.classid)))) as largeid from Equipment  t where t.classid = '"
											+ item.getClassid()
											+ "'");
							if (maxidlist.size() > 0) {
								Map map = (Map) maxidlist.get(0);
								if (map.size() > 0) {
									BigDecimal maxvalue = (BigDecimal) map
											.get("LARGEID");
									maxeq = maxvalue.doubleValue() + 1;
									largeid = maxeq.longValue();
								}
							}
						}
						
					}
					
					String s = String.valueOf(largeid);
					String neweqnum = item.getClassid();
					int len1 = s.length();
					int len2 = item.getClassid().length();
					for(int j=0;j<totallength-len1-len2;j++)
					{
						neweqnum = neweqnum + "0";
					}
					neweqnum = neweqnum + s;
					
					equip.setEqnum(neweqnum);
					
//					equip.setEqnum(item.getClassid()+this.genInskey("EQNUM"));
					equip.setStatusdate(new Date());
					equip.setAssetnum(this.genInskey("CARDNUM"));
					equip.setStatus("δ����");
					this.getBaseDao().saveObject(equip);
					
					//�жϷ���ļ����������Ƿ���ڣ�������ڵĻ����Ͳ������ݡ�
					List classpeclist = this.getBaseDao().findWithQuery(Classspec.class, "classid='"+item.getClassid()+"'");
					if(classpeclist.size()>0)
					{
						for(int j=0;j<classpeclist.size();j++)
						{
							Classspec classpec = (Classspec) classpeclist.get(j);
							Eqspec eqspec = new Eqspec();
							eqspec.setEqnum(neweqnum);
							eqspec.setClassid(item.getClassid());
							eqspec.setClassattr(classpec.getClassattr());
							eqspec.setStrvalue(classpec.getDefaultstrvalue());
							eqspec.setUnitid(classpec.getUnitid());
							eqspec.setChangedate(new Date());
							eqspec.setChangeby(this.getUserInfo().getLabornum());
							eqspec.setIsmustbe(classpec.getIsmustbe());
							eqspec.setRemark(classpec.getRemark());
							this.getBaseDao().saveObject(eqspec);
						}
						
						
					}
					
					//�޸Ľ������еĿ������Ϊ��ǰ���������
					Double sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '"+itemnum+"' and t.warehouse = '"+ warehouse +"' ");
					if(sumcurbal==null)
					{
						sumcurbal=0d;
					}
					invrectrans.setCurbal(sumcurbal);
					invrectrans.setEqnum(equip.getEqnum());
					
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='"+itemnum+"' and warehouse ='"+warehouse+"' and lotnum = '"+invrectrans.getTolot()+"' ");
					if(invstocklist.size()>0)
					{
						Invstock invstock = (Invstock) invstocklist.get(0);
						invstock.setCurbal(invstock.getCurbal()+invrectrans.getQuantity());
						this.getBaseDao().updateObject(invstock);
					}
					else
					{
						Invstock invstock = new Invstock();
						invstock.setItemnum(invrectrans.getItemnum());
						invstock.setWarehouse(invrectrans.getTowarehouse());
						invstock.setBinnum(invrectrans.getTobin());
						invstock.setPhyscnt(invrectrans.getQuantity());
						invstock.setPhyscntdate(new Date());
						invstock.setCurbal(rqty);
						invstock.setReconciled("��");
						//invstock.setSitenum(invrectrans.getSitenum());
						//invstock.setCorpnum(invrectrans.getCorpnum());
						invstock.setLotnum(invrectrans.getTolot());
						this.getBaseDao().saveObject(invstock);
					}
					
					int invlotcount = this.getBaseDao().selectCountByHql("select count(*) from Invlot t where t.itemnum ='"+itemnum+"' and t.warehouse ='"+warehouse+"' and t.lotnum = '"+invrectrans.getTolot()+"'");
					if(invlotcount == 0)
					{
						Invlot invlot = new Invlot();
						invlot.setItemnum(invrectrans.getItemnum());
						//invlot.setCorpnum(invrectrans.getCorpnum());
						//invlot.setSitenum(invrectrans.getSitenum());
						invlot.setWarehouse(invrectrans.getTowarehouse());
						invlot.setLotnum(invrectrans.getTolot());
						invlot.setLotcost(invrectrans.getUnitcost());
						invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
						this.getBaseDao().saveObject(invlot);
					}
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
					
				}
				else//����ת��
				{
					Invstock invstock = new Invstock();
					invstock.setItemnum(invrectrans.getItemnum());
					invstock.setWarehouse(invrectrans.getTowarehouse());
					invstock.setBinnum(invrectrans.getTobin());
					// ��Ҫ�����������Ŀ����һ�����Ľ��մ���
					invstock.setPhyscnt(invrectrans.getQuantity());
					invstock.setPhyscntdate(new Date());
					invstock.setCurbal(rqty);
					invstock.setReconciled("��");
					//invstock.setSitenum(invrectrans.getSitenum());
					//invstock.setCorpnum(invrectrans.getCorpnum());
					invstock.setLotnum(invrectrans.getTolot());
					super.save(invstock);

					Invlot invlot = new Invlot();
					invlot.setItemnum(invrectrans.getItemnum());
					//invlot.setCorpnum(invrectrans.getCorpnum());
					//invlot.setSitenum(invrectrans.getSitenum());
					invlot.setWarehouse(invrectrans.getTowarehouse());
					invlot.setVendor(po.getVendor());
					invlot.setLotnum(invrectrans.getTolot());
					invlot.setLotcost(invrectrans.getUnitcost());
					invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
					super.getBaseDao().saveObject(invlot);
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
					Labor labor = (Labor) Sessions.getCurrent().getAttribute("laborinfo");
					String labornum = labor.getLabornum();
					invrectrans.setStatuschangeby(labornum);
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}
				
			}
			
		/*	//�ʲ���ţ�2008��11��10�ձ��ݣ�
			String lastitemnum = null;
			List invrectlist = this.getBaseDao().findWithQuery(Invrectrans.class, "eqnum is null and tolot is not null","itemnum");
			for(int m = 0; m < invrectlist.size(); m++)
			{
				Invrectrans invrec = (Invrectrans)invrectlist.get(m);
				if(invrec.getItemnum().equals(lastitemnum))//��������ͬ
				{
					
				}
				else//��Ų���ͬ
				{
					
				}
			}
*/

            /*************�޸�invvendor���������*************/
			
			//List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and sitenum='"+invrectrans.getSitenum()+"' and vendor='"+po.getVendor()+"'");
			List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and vendor='"+po.getVendor()+"'");
			if(invendorlist.size()>0)
			{
				Invvendor invendor = (Invvendor) invendorlist.get(0);
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setLastdate(invrectrans.getActualdate());
				this.getBaseDao().updateObject(invendor);
			}
			else
			{
				Invvendor invendor = new Invvendor();
				invendor.setItemnum(invrectrans.getItemnum());
				invendor.setVendor(po.getVendor());
				invendor.setPromtime(po.getRequireddate());
				invendor.setLastdate(invrectrans.getTransdate());
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setOrderunit(invrectrans.getRecunit());
				//invendor.setCorpnum(invrectrans.getCorpnum());
				//invendor.setSitenum(invrectrans.getSitenum());
				invendor.setIsdefault("��");
				this.getBaseDao().saveObject(invendor);
				
			}
			
			/*************�޸�invvendor���������(����)*************/

			List invstatus = this.getBaseDao().findWithQuery(Poline.class, "ponum = '" + ponum + "'");
			for(int k=0;k<invstatus.size();k++){
				Poline p = (Poline) invstatus.get(k);
				if(!p.getReceiptscomplete().equals("ȫ������"))
				{
					po.setReceipts("���ֽ���");
					this.getBaseDao().updateObject(po);
					break;
				}						
				else
				{
					po.setReceipts("�������");
					this.getBaseDao().updateObject(po);
				}
			}
		}
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************����ý����ж�Ӧ�������������У�֪ͨ��������*************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/
		/*************************************************************/


		if (polinelist != null && polinelist.size() > 0) 
		{
			String ponum = null;
			String polinenum = null;
			String body = null;
			String matreqnum = null;
			String requestby = null;
			for(int i=0;i<polinelist.size();i++)
			{
				Map polineobj = (Map) polinelist.get(i);
				if (polineobj.size() > 0) 
				{
					if (polineobj.get("PONUM") != null) 
					{
						ponum = (polineobj.get("PONUM")).toString();
					}
					if (polineobj.get("POLINENUM") != null) 
					{
						Object obj1 = polineobj.get("POLINENUM");
						polinenum = (polineobj.get("POLINENUM")).toString();
					}

					//1.�ɹ�������������ֱ�����ɡ�
					List wpmatList = this.getBaseDao().selectListBySql("select matreqnum,requestby from Wpmaterial   where prnum='"+ponum+"' and prlinenum ='"+polinenum+"'  group by matreqnum,requestby");
					if (wpmatList != null && wpmatList.size() > 0) 
					{
						for(int w=0;w<wpmatList.size();w++)
						{
							Map wpmatobj = (Map) wpmatList.get(w);
							if (wpmatobj.size() > 0) 
							{
								if (wpmatobj.get("MATREQNUM") != null) 
								{
									matreqnum = (wpmatobj.get("MATREQNUM")).toString();
								}
								if (wpmatobj.get("REQUESTBY") != null) 
								{
									requestby = (wpmatobj.get("REQUESTBY")).toString();
								}



								List sendwpmatList = this.getBaseDao().findWithQuery(Wpmaterial.class, "prnum = '"+ponum+"' and prlinenum ='"+polinenum+"' and matreqnum = '"+matreqnum+"' and requestby='"+requestby+"'");
								if(sendwpmatList.size()>0)
								{
									for(int n =0;n<sendwpmatList.size();n++)
									{
										Wpmaterial wpmat = (Wpmaterial) sendwpmatList.get(n);
										String matreqstr = wpmat.getMatreqnum();
										String itemnumstr = wpmat.getItemnum();
										String description = wpmat.getDescription();
										if(Util.isNull(body))
										{
											body = itemnumstr +"����Ϊ["+description+"]";;
										}
										else
										{
											body = body + ","+itemnumstr +"����Ϊ["+description+"]";;
										}
									}


								}

							}
						}



					}

					//2.�ɹ����ɲɹ��������ɡ�
					List prlinelist = this.getBaseDao().findWithQuery(Prline.class, "ponum='"+ponum+"' and polinenum ='"+polinenum+"'");
					if(prlinelist.size()>0)
					{
						for(int j=0;j<prlinelist.size();j++)
						{
							Prline prline  = (Prline) prlinelist.get(j);
							Long prlinenum = prline.getPrlinenum();
							String prnum = prline.getPrnum();
							List wpmatList2 = this.getBaseDao().selectListBySql("select matreqnum,requestby from Wpmaterial   where prnum='"+prnum+"' and prlinenum ='"+prlinenum+"' group by matreqnum,requestby");
							if (wpmatList2 != null && wpmatList2.size() > 0) 
							{
								for(int w=0;w<wpmatList2.size();w++)
								{
									Map wpobj = (Map) wpmatList2.get(w);
									if (wpobj.size() > 0) 
									{
										if (wpobj.get("MATREQNUM") != null) 
										{
											matreqnum = (wpobj.get("MATREQNUM")).toString();
										}
										if (wpobj.get("REQUESTBY") != null) 
										{
											requestby = (wpobj.get("REQUESTBY")).toString();
										}



										List sendwpmatList = this.getBaseDao().findWithQuery(Wpmaterial.class, "prnum = '"+prnum+"' and prlinenum ='"+prlinenum+"' and matreqnum = '"+matreqnum+"' and requestby='"+requestby+"'");
										if(sendwpmatList.size()>0)
										{
											for(int n =0;n<sendwpmatList.size();n++)
											{
												Wpmaterial wpmat = (Wpmaterial) sendwpmatList.get(n);
												String matreqstr = wpmat.getMatreqnum();
												String itemnumstr = wpmat.getItemnum();
												String description = wpmat.getDescription();
												if(Util.isNull(body))
												{
													body = itemnumstr +"����Ϊ["+description+"]";
												}
												else
												{
													body = body + ","+itemnumstr +"����Ϊ["+description+"]";
												}
											}

										}

									}

								}


							}
						}

					}



				}
			}

			if(Util.isNotNull(body))
			{
				Msgreceive msg = new Msgreceive();
				body = "��'"+matreqnum+"'���뵥�������"+body+"�Ѿ��������뾡��������ã�";
				String title = "�ʲ�������ʾ��";
				msg.setTitle(title);
				msg.setBody(body);
				msg.setSddate(new Date());
				msg.setSender("Admin");
				msg.setReceiver(requestby);
				msg.setSendtype("˽��");
				msg.setRectype("����");
				msg.setHasread("��");
				this.getBaseDao().saveObject(msg);
				Labor labor = (Labor) this.getBaseDao().findUniqueBy(Labor.class, "labornum", requestby);
				if(Util.isNotNull(labor.getEmail()))
				{
					Util.sendMail(labor.getEmail(), "������ʾ��", body);
				}

			}
		}


	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////
}
