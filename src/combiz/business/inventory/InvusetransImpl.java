package combiz.business.inventory;

import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOBaseImpl;
import combiz.system.IBSServer;
import combiz.system.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class InvusetransImpl extends IBOBaseImpl
implements InvusetransSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Invusetrans))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		Invusetrans invu = (Invusetrans) obj;
		if(invu.getState().equals("�����"))
		{
			throw new Exception("����ɾ����ʷ���ݣ����ʵ��");
		}
		else
		{
			//ɾ������
			super.delete(obj);
		}
			
			
		
		
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����� 
	 * ���ܣ���ѡ�еĽ��ü�¼���й黹��
	 * ���ڣ�1:50:21 PM  Feb 18, 2009 
	 *
	 */
	public void returneq(List list) throws Exception
	{
		if ((list.size()<=0)) {
			throw new Exception("��ѡ��һ����¼��");
		}
		for(int i=0;i<list.size();i++)
		{
			
			Invusetrans invusetrans = (Invusetrans) list.get(i);
			//String matreqnum = invusetrans.getMatreqnum();
			Double curbal =0d;
			Double physcnt= 0d;
			Invusetrans newinvuse = new Invusetrans();
			newinvuse.setItemnum(invusetrans.getItemnum());
			newinvuse.setWarehouse(invusetrans.getWarehouse());
			newinvuse.setMatreqnum(invusetrans.getMatreqnum());
			newinvuse.setLocation(invusetrans.getLocation());
			newinvuse.setIssuetype("�黹");
			newinvuse.setTransdate(new Date());
			newinvuse.setActualdate(new Date());
			newinvuse.setLotnum(invusetrans.getLotnum());
			String binnum = invusetrans.getBinnum();
			newinvuse.setBinnum(binnum);
			newinvuse.setDescription(invusetrans.getDescription());
			Double issueqty = 0d;
			issueqty = (Double) this.getBaseDao().selectSumByHql(
					"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
					+ invusetrans.getMatreqnum()
					+ "' and itemnum ='"
					+ invusetrans.getItemnum()
					+ "'and eqnum = '" + invusetrans.getEqnum()
					+ "' and lotnum ='"+invusetrans.getLotnum()+"'and state ='�����'");
			if (issueqty == null)
				issueqty = 0d;

			newinvuse.setQuantity(-issueqty);
			List invstock = this.getBaseDao().findWithQuery(
					Invstock.class,
					"itemnum = '" + invusetrans.getItemnum()
					+ "' and warehouse = '"
					+ invusetrans.getWarehouse() + "' and  lotnum='"+invusetrans.getLotnum()+"'");
			if (invstock.size() > 0) {
				Invstock stock = (Invstock) invstock.get(0);
				physcnt = stock.getPhyscnt();
				curbal = stock.getCurbal();
			}
			newinvuse.setCurbal(curbal);
			newinvuse.setPhyscnt(physcnt);
			newinvuse.setUnitcost(invusetrans.getUnitcost());
			Double linecost = (-issueqty) * invusetrans.getUnitcost();
			newinvuse.setLinecost(linecost);
			newinvuse.setActualcost(invusetrans.getUnitcost());
			newinvuse.setConversion(1.0);
			newinvuse.setEnterby(this.getLaborInfo().getLabornum());
			newinvuse.setEqnum(invusetrans.getEqnum());
			newinvuse.setState("�����");
			//newinvuse.setSitenum(invusetrans.getSitenum());
			//newinvuse.setCorpnum(invusetrans.getCorpnum());
			this.getBaseDao().saveObject(newinvuse);
			
			invusetrans.setIssueid(newinvuse.getId());
			this.getBaseDao().updateObject(invusetrans);
			
			
//			------------------------------
			
			Double returnqty = newinvuse.getQuantity();
			Double hasissueqty = 0d;
			List inventorylist = this.getBaseDao().findWithQuery(
					Inventory.class,
					"itemnum ='" + invusetrans.getItemnum() + "' and warehouse ='"
					+ invusetrans.getWarehouse() + "'");
			Inventory inventory = (Inventory) inventorylist.get(0);
			Double avgcost = 0d;
			List invstocklist = null;
			String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
	    	if(DBProduct.equals("SQLSERVER"))
			{
	    		invstocklist = this.getBaseDao().findWithQuery(
						Invstock.class,
						"itemnum ='" + invusetrans.getItemnum() + "' and warehouse ='"
						+ invusetrans.getWarehouse() + "' and lotnum='"+invusetrans.getLotnum()+"' and (binnum is null or DATALENGTH(binnum) = 0)");
	    		
			}
	    	else
	    	{
	    		invstocklist = this.getBaseDao().findWithQuery(
						Invstock.class,
						"itemnum ='" + invusetrans.getItemnum() + "' and warehouse ='"
						+ invusetrans.getWarehouse() + "' and lotnum='"+invusetrans.getLotnum()+"' and binnum is null");
	    		
	    	}
	    	


			
			if (invstocklist.size() > 0)// ��������µ��˿⡣
			{
				Invstock invst = (Invstock) invstocklist.get(0);
				Double curbal1 = invst.getCurbal();
				
				if(DBProduct.equals("SQLSERVER"))
				{
					hasissueqty = (Double) this.getBaseDao().selectSumByHql(
							"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
							+ invusetrans.getMatreqnum() + "' and itemnum ='"
							+ invusetrans.getItemnum() + "'and (binnum is null or DATALENGTH(binnum) = 0) and issuetype ='����' and state ='�����'");
				}
				else
				{
					hasissueqty = (Double) this.getBaseDao().selectSumByHql(
							"select sum(t.quantity) from Invusetrans t where t.matreqnum ='"
							+ invusetrans.getMatreqnum() + "' and itemnum ='"
							+ invusetrans.getItemnum() + "'and binnum  is null and issuetype ='����' and state ='�����'");
				}
				
				
				if(hasissueqty==null)
				{
					hasissueqty=0d;
				}
				if (hasissueqty - returnqty >= 0) {
					Double cur = curbal1 - returnqty;
					invst.setCurbal(cur);
					if(cur!=0)
					{
						avgcost = (-returnqty * invusetrans.getUnitcost() + curbal1
								* inventory.getAvgcost())
								/ cur;
					}
					else
					{
						avgcost = 0d;
					}
					
					inventory.setAvgcost(avgcost);
					this.getBaseDao().updateObject(inventory);
					this.getBaseDao().updateObject(invst);
					

				} else {
					throw new Exception("�����Ŀ���Ϊ��" + invusetrans.getItemnum()
							+ "������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");

				}

			} else// �쳣�����invstock���е����ݶ�ʧ
			{
				Invstock invst = new Invstock();
				invst.setItemnum(invusetrans.getItemnum());
				invst.setWarehouse(invusetrans.getWarehouse());
				invst.setBinnum(invusetrans.getBinnum());
				// ��Ҫ�����������Ŀ����һ�����Ľ��մ���
				invst.setPhyscnt(invusetrans.getQuantity());
				invst.setLotnum(invusetrans.getLotnum());
				invst.setPhyscntdate(new Date());
				invst.setCurbal(invusetrans.getQuantity());
				invst.setReconciled("��");
				//invst.setSitenum(invusetrans.getSitenum());
				//invst.setCorpnum(invusetrans.getCorpnum());
				this.getBaseDao().saveObject(invst);
			}
			
			//�޸��ʲ����豸��λ��Ϊ0139�ֿ�λ�á�
			List equiplist = this.getBaseDao().findWithQuery(Equipment.class, "eqnum='"+newinvuse.getEqnum()+"' and lotnum='"+newinvuse.getLotnum()+"' and itemnum='"+newinvuse.getItemnum()+"'");
			if(equiplist.size()>0)
			{
				Equipment equip = (Equipment) equiplist.get(0);
				equip.setLocation(invusetrans.getWarehouse());
				equip.setStatus("δ����");
				equip.setLabornum("");
				equip.setDeptnum("");
				this.getBaseDao().updateObject(equip);
				
			}
			
			
			
			Invrectrans irt = new Invrectrans();
			List avglist = (List) this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + invusetrans.getItemnum() + "' and warehouse = '" + invusetrans.getWarehouse() +"'" );
			irt.setItemnum(invusetrans.getItemnum());
			irt.setTowarehouse(invusetrans.getWarehouse());
			irt.setTransdate(new Date());
			irt.setActualdate(new Date());
			irt.setPonum(invusetrans.getPonum());
			irt.setPolinenum(invusetrans.getPolinenum());
			irt.setUnitcost(invusetrans.getUnitcost());
			irt.setLinecost(invusetrans.getLinecost());
			irt.setActualcost(invusetrans.getActualcost());
			irt.setRejectqty(0d);
			irt.setDescription(invusetrans.getDescription());
			//irt.setSitenum(pl.getSitenum());
			//irt.setCorpnum(pl.getCorpnum());
			//����������������
			irt.setConversion(invusetrans.getConversion());
			irt.setChangeby(this.getUserInfo().getLabornum());
			List invstocklist2 = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + invusetrans.getItemnum() + "' and warehouse ='" + invusetrans.getWarehouse() +"'");
			irt.setTolot(invusetrans.getLotnum());
			if(invstocklist.size()>0)
			{
				Invstock invstock2 = (Invstock) invstocklist.get(0);
				Double curbal2 = invstock2.getCurbal();
				irt.setCurbal(curbal);
			}
			else
			{
				irt.setCurbal(0.0d);
			}

			irt.setQuantity(1d);
			irt.setRectype("���ù黹"); 
			irt.setLoadedcost(invusetrans.getLinecost());
			irt.setStatus("�Ѽ���");
			irt.setOldavgcost(inventory.getAvgcost());
			irt.setTransdate(new Date());
			irt.setEqnum(invusetrans.getEqnum());
			Item item = (Item) this.getBaseDao().findUniqueBy(Item.class, "itemnum", invusetrans.getItemnum());
			if(item != null)
			{
				irt.setModelnum(item.getModelnum());
				irt.setRecunit(item.getOrderunit());
			}
			irt.setStatusdate(new Date());
			
			this.getBaseDao().saveObject(irt);

		



		}
	}
	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
