package combiz.business.assetscard;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inbasis.zk.ui.Sessions;

import combiz.domain.assetscard.*;
import combiz.domain.classattr.Classspec;
import combiz.domain.corp.Labor;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invlot;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Rejectitem;
import combiz.domain.po.Invvendor;
import combiz.domain.po.Po;
import combiz.domain.po.Poline;
import combiz.domain.pr.Prline;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.domain.workorder.Wpmaterial;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;

public class AllocationImpl extends IBOBaseImpl
implements AllocationSrv
{

	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * ��С��  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeSave(Object obj) 
	throws Exception
	{
		return true;
	}

	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * ��С��  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		return true;
	}

	/* 
	 * ���ܣ����ʲ���ϸ���������ݵ�������ϸ��
	 * ���ߣ�����
	 * ���ڣ�May 13, 2010  4:25:59 PM
	 */
	public void insertline(List list,Object obj) throws Exception
	{
		Allocation allocation = (Allocation) obj;
		for(int i=0;i<list.size();i++)
		{
			Equipment equip = (Equipment) list.get(i);
			Allocationline newobj = new Allocationline();
			Long linenum = (Long) this.getBaseDao().selectMaxByHql("select max(t.linenum) from Allocationline t where t.allocationnum='"+allocation.getAllocationnum()+"'");
			if(linenum == null)
			{
				linenum = 0l;
			}
			newobj.setAllocationnum(allocation.getAllocationnum());
			newobj.setLinenum(linenum+1);
			newobj.setEqnum(equip.getEqnum());
			newobj.setItemnum(equip.getItemnum());
			newobj.setFromwarehouse(equip.getLocation());
			newobj.setModelnum(equip.getModel());
			newobj.setDescription(equip.getDescription());
			newobj.setQuantity(1d);
			newobj.setTotalcost(equip.getTotalcost());	
			newobj.setSitenum(allocation.getSitenum());
			newobj.setCorpnum(allocation.getCorpnum());
			this.getBaseDao().saveObject(newobj);

		}

	}

	/* 
	 * ���ܣ����������������������
	 * ���ߣ�����
	 * ���ڣ�May 18, 2010  11:24:38 AM
	 */
	public void addallocline(List list,Object obj) throws Exception
	{
		Allocation allocation = (Allocation) obj;
		for(int i=0;i<list.size();i++)
		{
			Allocationline allocationline = (Allocationline) list.get(i);
			Invrectrans irt = new Invrectrans();
			Date date=new Date(); 
			SimpleDateFormat sp=new SimpleDateFormat("yyyyMMddhhmmss"); 
			String nowdate=sp.format(date); 
			irt.setTolot(nowdate);
			irt.setQuantity(1d);
			irt.setEqnum(allocationline.getEqnum());
			irt.setModelnum(allocationline.getModelnum());
			irt.setUnitcost(allocationline.getTotalcost());
			irt.setConversion(1d);
			Equipment equip = (Equipment) this.getBaseDao().findUniqueBy(Equipment.class, "eqnum",allocationline.getEqnum());
			if(equip!=null)
			{
				irt.setFromlot(equip.getLotnum());
				irt.setItemnum(equip.getItemnum());
				irt.setManufacturer(equip.getManufacturer());

				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + equip.getItemnum() + "' and warehouse ='" + allocationline.getTowarehouse() +"'");
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
			}
			Item item = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum", equip.getItemnum());
			if(item!=null)
			{
				irt.setRecunit(item.getOrderunit());
			}

			irt.setPonum(allocationline.getAllocationnum());
			irt.setPolinenum(allocationline.getLinenum());
			irt.setChangeby(this.getUserInfo().getLabornum());
			irt.setTransdate(new Date());
			irt.setActualdate(new Date());
			irt.setLinecost(allocationline.getTotalcost());
			irt.setActualcost(allocationline.getTotalcost());
			irt.setRejectqty(0d);
			irt.setDescription(allocationline.getDescription());
			//irt.setSitenum(pl.getSitenum());
			//irt.setCorpnum(pl.getCorpnum());
			//����������������
			irt.setFromwarehouse(allocationline.getFromwarehouse());
			irt.setTowarehouse(allocationline.getTowarehouse());


			irt.setRectype("����"); //����invtrans���е�����Ϊ����
			irt.setLoadedcost(allocationline.getTotalcost());
			irt.setStatus("������");
			irt.setOldavgcost(0d);
			this.getBaseDao().saveObject(irt);


		}

	}



	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ�У�������ϸ��������ݣ��޸Ľ��ղֿ�Ŀ�档
	 * ���ڣ�1:43:08 PM  May 18, 2010 
	 *
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Allocation))
			throw new Exception("���󴫵ݲ���ȷ!");
		Allocation allocation = (Allocation) obj;
		for (int i = 0; i < list.size(); i++)
		{
			Invrectrans invrectrans = (Invrectrans) list.get(i);
			String ponum = invrectrans.getPonum();
			String itemnum = invrectrans.getItemnum();
			String warehouse = invrectrans.getTowarehouse();
			String fromwarehouse = invrectrans.getFromwarehouse();
			Double unitcost = invrectrans.getUnitcost();
			List itemstocktype = this.getBaseDao().findWithQuery(Item.class,"itemnum='" + itemnum + "'");
			Item item = (Item) itemstocktype.get(0);
			String orderunit = item.getOrderunit();
			String issueunit = item.getIssueunit();
			Long linenum = invrectrans.getPolinenum();


			/***********************************************************************
			 * **********************************************************************
			 * ************************�޸�Դ�ֿ�������*******************************
			 **********************************************************************/
			String frombinnum = invrectrans.getFrombin();
			Double rqty = invrectrans.getQuantity();
			String rotating = item.getRotating();
			if(item.getLottype().equals("������"))//�����ι���
			{
				int size = 0;
				if(Util.isNull(frombinnum))
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ fromwarehouse +"' and frombinnum is null");
					if (curbal.size() > 0) 
					{
						Invstock invstock = (Invstock) curbal.get(0);
						Double nowcurbal = invstock.getCurbal() - rqty; // �����յĿ���������ӣ����¿��������
						invstock.setCurbal(nowcurbal);	
						this.getBaseDao().saveObject(invstock);// ע��˳��һ����д�ؿ����ձ��������Ϣ����Ϊ���ǽ���ǰ����������ٸ���invstock�Ŀ�������������������ݡ�
					}

					super.getBaseDao().updateObject(invrectrans);
				}
				else
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ fromwarehouse +"' and binnum = '" + frombinnum +"'");
					if (curbal.size()>0) {
						Invstock invstock = (Invstock) curbal.get(0);
						Double nowcurbal = invstock.getCurbal() - rqty; 
						invstock.setCurbal(nowcurbal);	
						this.getBaseDao().saveObject(invstock);
					}
				}

			}
			else//���ι�����豸
			{


				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='"+itemnum+"' and warehouse ='"+fromwarehouse+"' and lotnum = '"+invrectrans.getFromlot()+"' ");
				if(invstocklist.size()>0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					invstock.setCurbal(invstock.getCurbal()-invrectrans.getQuantity());
					this.getBaseDao().updateObject(invstock);
				}

			}


			/***********************************************************************
			 * ******************************************************************
			 * �жϸÿ����Ŀ�Ƿ����ڿ�����У����û�з�������������Ϣ��ӵ�INVENTORY��
			 **********************************************************************/

			//�ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��

			String binnum = invrectrans.getTobin();
			List noitem = this.getBaseDao().findWithQuery(Inventory.class,"itemnum = '"+itemnum+"' and warehouse='"+ warehouse +"'");

			if(noitem.size() == 0)
			{

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

				Double sumcurbal =  0d;
				sumcurbal = (Double) this.getBaseDao().selectSumByHql("select sum(t.curbal) from Invstock t where t.itemnum = '" + itemnum + "' and t.warehouse = '"+ warehouse +"'");
				if(sumcurbal == null)
					sumcurbal = 0d;
				Double avgcost = (sumcurbal * inventory.getAvgcost() + unitcost * invrectrans.getQuantity()) / (sumcurbal + invrectrans.getQuantity());
				inventory.setAvgcost(avgcost);
				super.update(inventory);

			}

			/***************************************************
			 * ******************************************************************
			 * 3.����Ƿ����ι����жϸÿ����Ŀ�Ƿ��ڿ��������������Ϣ�����û�з���������ֵ��ǰ����Ϊ0��
			 * ��������ι���Ļ�������INVSTOCK��INVLOT���в������ݡ� 
			 **************************************************/

			if(item.getLottype().equals("������"))//�����ι���
			{
				int size = 0;
				if(Util.isNull(binnum))
				{
					List curbal = this.getBaseDao().findWithQuery(Invstock.class,"itemnum = '"+itemnum+"' and warehouse = '"+ warehouse +"' and binnum is null");
					size = curbal.size();
					if (size == 0) 
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
						super.save(invstock);


					} 
					else 
					{
						Invstock invstock = (Invstock) curbal.get(0);
						invrectrans.setCurbal(invstock.getCurbal());// ��Invrectrans�����curbal�ֶ�д�ص�ǰ�Ŀ��������
						Double nowcurbal = invstock.getCurbal() + rqty; // �����յĿ���������ӣ����¿��������
						invstock.setCurbal(nowcurbal);	
						super.getBaseDao().updateObject(invrectrans);
						super.save(invstock);// ע��˳��һ����д�ؿ����ձ��������Ϣ����Ϊ���ǽ���ǰ����������ٸ���invstock�Ŀ�������������������ݡ�
					}
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬

					invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
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
						super.save(invstock);
						invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
						invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
						invrectrans.setStatusdate(new Date());
						super.getBaseDao().updateObject(invrectrans);

					} else 
					{
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
					//�޸��ʲ���equipment������ݡ�
					Equipment equip = (Equipment) this.findUniqueBy(Equipment.class, "eqnum",invrectrans.getEqnum());
					if(equip!=null)
					{
						equip.setLocation(invrectrans.getTowarehouse());
						equip.setStatus("δ����");
						equip.setDeptnum("");
						equip.setLabornum("");
						equip.setChangeby(this.getUserInfo().getLabornum());
						equip.setChangedate(new Date());
						equip.setLotnum(invrectrans.getTolot());
						this.getBaseDao().updateObject(equip);
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
						invstock.setLotnum(invrectrans.getTolot());
						this.getBaseDao().saveObject(invstock);
					}

					int invlotcount = this.getBaseDao().selectCountByHql("select count(*) from Invlot t where t.itemnum ='"+itemnum+"' and t.warehouse ='"+warehouse+"' and t.lotnum = '"+invrectrans.getTolot()+"'");
					if(invlotcount == 0)
					{
						Invlot invlot = new Invlot();
						invlot.setItemnum(invrectrans.getItemnum());
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
					invstock.setLotnum(invrectrans.getTolot());
					super.save(invstock);

					Invlot invlot = new Invlot();
					invlot.setItemnum(invrectrans.getItemnum());
					invlot.setWarehouse(invrectrans.getTowarehouse());
					invlot.setVendor(invrectrans.getManufacturer());
					invlot.setLotnum(invrectrans.getTolot());
					invlot.setLotcost(invrectrans.getUnitcost());
					invlot.setLotlinecost(invrectrans.getUnitcost()*invrectrans.getQuantity());
					super.getBaseDao().saveObject(invlot);
					invrectrans.setStatus("�Ѽ���");//������ɣ����ɱ༭״̬
					invrectrans.setStatuschangeby(this.getUserInfo().getLabornum());
					invrectrans.setStatusdate(new Date());
					super.getBaseDao().updateObject(invrectrans);
				}

			}


			/*************�޸�invvendor���������*************/
			if(Util.isNotNull(invrectrans.getManufacturer()))
			{List invendorlist = this.getBaseDao().findWithQuery(Invvendor.class, "itemnum='"+invrectrans.getItemnum()+"' and vendor='"+invrectrans.getManufacturer()+"'");
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
				invendor.setVendor(invrectrans.getManufacturer());
				invendor.setPromtime(allocation.getFromdate());
				invendor.setLastdate(invrectrans.getTransdate());
				invendor.setLastcost(invrectrans.getUnitcost());
				invendor.setOrderunit(invrectrans.getRecunit());
				invendor.setIsdefault("��");
				this.getBaseDao().saveObject(invendor);

			}
			}
		}


		/*************�޸�invvendor���������(����)*************/


	}


	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * ��С��  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		return true;
	}



	/**
	 * �û��ӿ�
	 * �����½��������û��ӿڷ���
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterInsert(Object obj) 
	throws Exception
	{
	}

	/**
	 * �û��ӿ�
	 * ������¶������û��ӿڷ���
	 * brianhong  2009-10-22
	 * @param obj
	 * @throws Exception
	 */
	public void afterUpdate(Object obj) 
	throws Exception
	{
	}

	/**
	 * ɾ������֮ǰ���û��ӿ�
	 * ����false�Ļ�����ɾ���ö���
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public boolean beforeDelete(Object obj) throws Exception
	{
		return true;
	}

	/**
	 * ϵͳɾ������ķ���
	 * �����ڸ÷������ֹ�ָ��ɾ����Щ�ӱ�������ͨ����ϵ����
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @param obj ������
	 * @throws Exception
	 */
	public void delete(Object obj) 
	throws Exception 
	{
		//this.deleteAllChild(obj, "relationship");  //ͨ����ϵ��ɾ���ֱ�����
		super.delete(obj);
	}


	/**
	 * ɾ���������û��ӿ�
	 * 
	 * Ӣ��˼  Nov 14, 2009
	 * @param obj
	 * @throws Exception
	 */
	public void afterDelete(Object obj) throws Exception
	{

	}


	/**
	 * ������������ͨ���÷��������������״̬
	 * Ӣ��˼ Nov 14, 2009
	 * @param obj ������
	 * @param toStatus ״̬
	 * @throws Exception
	 */
	public void changeStatus(Object obj, String toStatus) throws Exception
	{
		super.changeStatus(obj, toStatus);
	}

	/**
	 * ����������ʱ�����ô˽ӿڡ�
	 * �ڷ��͹�����֮ǰ�ж����ݵ������ԡ�
	 * �ڷ�������֮ǰ���ø÷���,Ч�鹤������Ӧ�����������Ƿ���Ч
	 * ����ͨ���������ȡ�ӱ��¼������Ч��:List childs = this.getListByRelation(parentObj, relation)
	 * ����ǵ�һ������������,��ô����wfinstance==null
	 * ����д���,���Ե���Messagebox��Ϣ,�����׳��쳣 throw new Exception("�쳣��Ϣ");
	 * ����true�������������ߣ��������false�������ж�
	 * Ӣ��˼  Nov 14, 2009
	 * @param mainObject ������
	 * @param wfinstance ��������ʵ��
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance) throws Exception
	{
		return true;
	}


	/**
	 * ����������ʱ��������һ������ѡ�񴰿ڣ�ѡ��������ߺ󣬵��ô˽ӿڡ�
	 * ���Ը���ѡ�����һ���������ж����ݵ������ԣ������Ƿ�ִ����һ����������
	 * �ڷ�������ѡ���������ø÷���,Ч�鹤������Ӧ�����������Ƿ���Ч
	 * ����ͨ���������ȡ�ӱ��¼������Ч��:List childs = this.getListByRelation(parentObj, relation)
	 * ����ǵ�һ������������,��ô����wfinstance==null
	 * ����д���,���Ե���Messagebox��Ϣ,�����׳��쳣 throw new Exception("�쳣��Ϣ");
	 * ����true�������������ߣ��������false�������ж�
	 * Ӣ��˼  Nov 14, 2009
	 * @param mainObject ������
	 * @param wfinstance ��������ʵ��
	 * @param wfaction  ��һ���Ĳ���
	 * @return
	 * @throws Exception
	 */
	public boolean validData(Object mainObject, Wfinstance wfinstance, Wfaction wfaction) throws Exception
	{
		return true;
	}


	/**
	 * �����������û����·���
	 * �û��ӿ�
	 * ��С��  Dec 21, 2009
	 */
	public void wfReassign()
	throws Exception
	{
		//�û��Զ���ӿڷ���
	}

}