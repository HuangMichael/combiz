package combiz.business.inventory;

import combiz.domain.ibs.Ibswhauth;
import combiz.domain.inventory.Inventory;
import combiz.domain.inventory.Invstock;
import combiz.domain.inventory.Invusetrans;
import combiz.domain.inventory.Matreq;
import combiz.domain.inventory.Warehouse;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.Date;
import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class WarehouseImpl extends IBOBaseImpl
implements WarehouseSrv {
	
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		Warehouse wh = (Warehouse)obj;
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		this.deleteAllChild(wh, "invbin");
		this.deleteAllChild(wh, "invusetrans");
		this.deleteAllChild(wh, "inventoryTable");
		
		this.getBaseDao().deleteBatch(this.getBaseDao().findWithQuery(Ibswhauth.class, "warehouse='"+wh.getWarehouse()+"'"));
	}
	/*********************���ż���***********************************/
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�����ѡ�����
	 * ���ڣ�Oct 7, 2008 2:30:39 PM
	 *
	 */
	public void verify(List list, Object obj) throws Exception {
		if (!(obj instanceof Warehouse))
			throw new Exception("���󴫵ݲ���ȷ!");
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			
			// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
			super.save(inv);
			Double issueqty = inv.getQuantity();
			Double reqqty = inv.getReqqty();
			if (reqqty == null) {
				reqqty = 0D;
			}
			if (issueqty == null) {
				issueqty = 0D;
			}
//			if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			if(inv.getBinnum() != null)
			{
				Integer length = inv.getItemnum().trim().length();
				if(inv.getItemnum().length() >0 && length == 0)
				{
					throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");
					
				}
				else
				{
					List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal = invstock.getCurbal();
					if(curbal - issueqty > 0)
					{
						invstock.setCurbal(curbal-issueqty);
						super.save(invstock);
						inv.setState("�����");
						super.save(inv);
					}
					else
					{
						if(curbal - issueqty == 0)
						{
							invstock.setCurbal(0d);
							super.save(invstock);
							super.delete(invstock);
							inv.setState("�����");
							super.save(inv);
						}
						else
//						Messagebox.show("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
							throw new Exception("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
					}
				}
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				if(invstocklist.size() >0)
				{
					Invstock invstock = (Invstock) invstocklist.get(0);
					Double curbal1 = invstock.getCurbal();
					if(curbal1 - issueqty > 0)
					{
						invstock.setCurbal(curbal1-issueqty);
						super.save(invstock);
						inv.setState("�����");
						super.save(inv);
						
					}
					else
					{
						if(curbal1 - issueqty == 0)
						{
							invstock.setCurbal(0d);
							super.save(invstock);
//							super.delete(invstock);
							inv.setState("�����");
							super.save(inv);
						}
						else
//						Messagebox.show("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
							throw new Exception("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������С�ڷ�������,���ܽ��з���");
					}
				}
				else
					throw new Exception("�������п����Ŀ���Ϊ��"+inv.getItemnum()+"�������Ŀ������Ϊ�㣬���ܷ��ţ��������������");
			}
			//����invusetrans��
			if ((reqqty-issueqty)>0) {
				inv.setState("���ַ���");
			}else{
				inv.setState("�����");
			}
			inv.setIssuetype("����");
			this.getBaseDao().updateObject(inv);
			
			//������ɣ�����Ϊֻ��
			
		}
		
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ������Ŀ���ź��Ҽ���������Խ����˿����
	 * ���ڣ�Oct 7, 2008 2:34:20 PM
	 *
	 */
	public void returnissue(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		String matreqnum = matreq.getMatreqnum();
		
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans invusetrans = (Invusetrans) list.get(i);
			List invuselist = this.getBaseDao().findWithQuery(Invusetrans.class, "matreqnum = '" + matreqnum + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype = '����' and state ='�����'");
			if(invuselist.size() > 0)
			{
		    Invusetrans newinvuse = new Invusetrans();
		    newinvuse.setItemnum(invusetrans.getItemnum());
		    newinvuse.setWarehouse(invusetrans.getWarehouse());
		    newinvuse.setMatreqnum(invusetrans.getMatreqnum());
		    newinvuse.setLocation(invusetrans.getLocation());
		    newinvuse.setIssuetype("�˻�");
		    newinvuse.setTransdate(new Date());
		    newinvuse.setActualdate(new Date());
		    String binnum = invusetrans.getBinnum();
		    newinvuse.setBinnum(binnum);
		    Double issueqty = 0d;
		    if(Util.isNotNull(binnum))
		    {
//		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and issuetype ='����' and state ='�����'");
		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + invusetrans.getMatreqnum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum = '"+ binnum + "'and state ='�����'");
		    	if(issueqty == null)
		    		issueqty = 0d;
		    	
		    }
		    else
		    {
//		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + invusetrans.getWonum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and issuetype ='����' and binnum is null and state ='�����'");
		    	issueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + invusetrans.getMatreqnum() + "' and itemnum ='" + invusetrans.getItemnum() + "'and binnum is null and state ='�����'");
		    	if(issueqty == null)
		    		issueqty = 0d;
		    }
		    newinvuse.setQuantity(-issueqty);
			List invstock =this.getBaseDao().findWithQuery(Invstock.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse() +"'");
			Invstock curbal = (Invstock) invstock.get(0);
			newinvuse.setCurbal(curbal.getCurbal());
			newinvuse.setPhyscnt(curbal.getPhyscnt());
			List avgcostlist =this.getBaseDao().findWithQuery(Inventory.class, "itemnum = '" + invusetrans.getItemnum() +"' and warehouse = '" + invusetrans.getWarehouse()+"'");
			Inventory avgcost = (Inventory)avgcostlist.get(0);
			newinvuse.setUnitcost(avgcost.getAvgcost());
			Double linecost = (-issueqty) * avgcost.getAvgcost();
			newinvuse.setLinecost(linecost);
			newinvuse.setActualcost(avgcost.getAvgcost());
			newinvuse.setConversion(1.0);
			newinvuse.setEnterby(this.getLaborInfo().getLabornum());
			newinvuse.setState("��ȷ��");
			super.save(newinvuse);
			}
			else
				throw new Exception("û�п����˿�ķ����У�");
			
		}
	}
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ��ڽ����˿�����󣬶��˿���˿��м�¼����У�顣
	 * ���ڣ�Oct 7, 2008 2:34:51 PM
	 *
	 */
	public void verifyreturn(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;
		

		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			/*******************************************************************
			 * ******************************************************************
			 * 
			 ******************************************************************/
			
			 // �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
			super.save(inv);
			Double returnqty = inv.getQuantity();
//			if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			Double hasissueqty = 0d;
			List inventorylist = this.getBaseDao().findWithQuery(Inventory.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse()+ "'");
			Inventory inventory = (Inventory) inventorylist.get(0);
			Double avgcost = 0d;
			if(inv.getBinnum() != null)
			{
				Integer length = inv.getItemnum().trim().length();
				if(length > 0)
				{
	        	List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum = '"+ inv.getBinnum() +"'");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal = invstock.getCurbal();
//				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.wonum ='" + inv.getWonum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='����' and state ='�����'");	
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + inv.getMatreqnum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and state ='�����'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal * inventory.getAvgcost())/(curbal-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("�����");
					super.save(inv);
					
				}
				else
				{
					throw new Exception("�����Ŀ���Ϊ��"+inv.getItemnum()+"������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");
				}
				}
				else
					throw new Exception("��������Ч�����ţ�������ȫ��Ϊ�ո�");
					
			}
			else
			{
				List invstocklist = this.getBaseDao().findWithQuery(Invstock.class, "itemnum ='" + inv.getItemnum() + "' and warehouse ='" + inv.getWarehouse() + "' and binnum is null");
				Invstock invstock = (Invstock) invstocklist.get(0);
				Double curbal1 = invstock.getCurbal();
				hasissueqty = (Double) this.getBaseDao().selectSumByHql("select sum(t.quantity) from Invusetrans t where t.matreqnum ='" + inv.getMatreqnum() + "' and itemnum ='" + inv.getItemnum() + "'and binnum = '"+ inv.getBinnum() + "'and issuetype ='����' and state ='�����'");
				if(hasissueqty + returnqty >= 0)
				{
					invstock.setCurbal(curbal1 - returnqty);
					avgcost = (- returnqty * inv.getUnitcost() + curbal1 * inventory.getAvgcost())/(curbal1-returnqty);
					inventory.setAvgcost(avgcost);
					super.save(inventory);
					super.save(invstock);
					inv.setState("�����");
					super.save(inv);
					
					
				}
				else
				{
					throw new Exception("�����Ŀ���Ϊ��"+inv.getItemnum()+"������Ϊ�˿�ķ��������˿��������ڷ��źϼ������������˿�");
						
				}
				
			}
		}
	}
	
	
	/**
	 * ����
	 * 
	 * ���ߣ����
	 * ���ܣ�������δУ�����˿���˿��м�¼���г����˿⡣
	 * ���ڣ�Sep 8, 2008 9:56:26 AM
	 *
	 */
	public void verifyreturnall(List list, Object obj) throws Exception {
		if (!(obj instanceof Matreq))
			throw new Exception("���󴫵ݲ���ȷ!");
		Matreq matreq = (Matreq) obj;

		if(list.size()<0) {
			throw new Exception("û���˻�δȷ�ϵļ�¼��");
		}
		
		for (int i = 0; i < list.size(); i++) {
			Invusetrans inv = (Invusetrans) list.get(i);
			
			// �ȸ���invrectrans���м�¼�� quantity ������ѽ������޸ĵĽ���������ʧ��
			super.save(inv);
			Double returnqty = inv.getQuantity();
			// if(inv.getBinnum().trim().length()>0 && inv.getBinnum() != null)
			if(inv.getState().equals("��ȷ��") && inv.getIssuetype().equals("�˻�")) {
				inv.setState("ȡ���˻�");
				this.getBaseDao().updateObject(inv);
			}else{
				throw new Exception("����ѡ������˻ش�ȷ�ϵļ�¼��");
			}
			
		}
	}
	
/////////////////////ҵ�񷽷�//////////////////////////////////
}
