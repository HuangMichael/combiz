package combiz.business.assetscard;

import combiz.domain.assetscard.Assetscardline;
import combiz.domain.assetscard.Carryover;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.List;

public class CarryoverImpl extends IBOBaseImpl
implements CarryoverSrv
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
	 * 
	 * @TODO ���carryover assetscardline�в�������
	 * @param list
	 * @throws Exception
	 * @��ΰ 2010-05-10 ����14:41
	 */
	public void copycarryover() throws Exception {

		List carryoverlist = this.getBaseDao().findWithQuery(Carryover.class, "status='δ��ת'");
		if(carryoverlist.size()>0)
		{
			Carryover carryover = (Carryover) carryoverlist.get(0);
			String mon = carryover.getMon();
			String year = carryover.getYear();
			String nmon = null;
			String nyear = null;
			if(Util.isNotNull(mon)&&Util.isNotNull(year))
			{
				int hmon = Integer.parseInt(mon);
				int hyear = Integer.parseInt(year);
				if(hmon-12==0)
				{
					nmon = "01";
					nyear = String.valueOf(hyear + 1);
				}
				else
				{
					nmon= String.valueOf(hmon+1);
					if(nmon.length()==1)
					{
						nmon="0"+nmon;

					}
					nyear = String.valueOf(hyear);
				}
				List assetcardlinelist = this.findWithQuery(Assetscardline.class,"status='δ��ת'");



				if(assetcardlinelist.size()>0 )
				{

					for(int i = 0;i<assetcardlinelist.size();i++)
					{
						Assetscardline assetscardline = (Assetscardline) assetcardlinelist.get(i);

						if(assetscardline.getDepreciationmonth()<=assetscardline.getExpectedmonth())
						{
							Assetscardline newassetline =new  Assetscardline();  
							newassetline.setStatus("δ��ת");
							newassetline.setDepreciationmonth(assetscardline.getDepreciationmonth()+1);
							newassetline.setAccdepreciation(assetscardline.getAccdepreciation()+assetscardline.getMthamount());
							newassetline.setAssetcode(assetscardline.getAssetcode());
							newassetline.setQuantity(assetscardline.getQuantity());
							newassetline.setResidualvalues(assetscardline.getResidualvalues());
							newassetline.setMon(nmon);
							newassetline.setYear(nyear);
							
							newassetline.setCost(assetscardline.getCost());
							newassetline.setExpectedmonth(assetscardline.getExpectedmonth());
							newassetline.setImpairment(assetscardline.getImpairment());
							newassetline.setManufacturers(assetscardline.getManufacturers());
							newassetline.setModel(assetscardline.getModel());
							newassetline.setMthamount(assetscardline.getMthamount());
							newassetline.setMthval(assetscardline.getMthval());
							newassetline.setResidual(assetscardline.getResidual());
							newassetline.setSuppliers(assetscardline.getSuppliers());
							newassetline.setNetworth(assetscardline.getCost()-assetscardline.getAccdepreciation());
							newassetline.setNet(assetscardline.getCost()-assetscardline.getAccdepreciation()-assetscardline.getImpairment());
							this.getBaseDao().saveObject(newassetline);

						}
						else
						{
							Assetscardline newassetline =new  Assetscardline();  
							newassetline.setStatus("δ��ת");
							newassetline.setDepreciationmonth(assetscardline.getExpectedmonth());
							newassetline.setDepreciationmonth(assetscardline.getDepreciationmonth());
							newassetline.setAccdepreciation(assetscardline.getAccdepreciation());
							newassetline.setAssetcode(assetscardline.getAssetcode());
							newassetline.setMon(nmon);
							newassetline.setYear(nyear);
							newassetline.setQuantity(assetscardline.getQuantity());
							newassetline.setResidualvalues(assetscardline.getResidualvalues());
						
							newassetline.setCost(assetscardline.getCost());
							newassetline.setExpectedmonth(assetscardline.getExpectedmonth());
							newassetline.setImpairment(assetscardline.getImpairment());
							newassetline.setManufacturers(assetscardline.getManufacturers());
							newassetline.setModel(assetscardline.getModel());
							newassetline.setMthamount(assetscardline.getMthamount());
							newassetline.setMthval(assetscardline.getMthval());
							newassetline.setResidual(assetscardline.getResidual());
							newassetline.setSuppliers(assetscardline.getSuppliers());
							newassetline.setNetworth(assetscardline.getNetworth());
							newassetline.setNet(assetscardline.getNet());
							this.getBaseDao().saveObject(newassetline);


						}						




						assetscardline.setStatus("�ѽ�ת");
						this.getBaseDao().updateObject(assetscardline);


					}

				}
				else
				{
					throw new Exception("�����쳣��");
				}

				Carryover ncarryover = new Carryover();
				ncarryover.setYear(nyear);
				ncarryover.setMon(nmon);
				ncarryover.setSourceid(carryover.getId());
				ncarryover.setStatus("δ��ת");
				this.getBaseDao().saveObject(ncarryover);

				carryover.setStatus("�ѽ�ת");
				this.getBaseDao().updateObject(carryover);

			}
		}

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
	 * 
	 * @TODO ���carryover assetscardline�в�������
	 * @param list
	 * @throws Exception
	 * @��ΰ 2010-05-14 ����12:00
	 */
	public void copyuncarryover(Object obj ) throws Exception {


	
		Carryover carryover = (Carryover) obj;

	
		long sourceid = carryover.getSourceid();

		if(Util.isNotNull(String.valueOf(sourceid)))
		{
			Carryover scarryover = (Carryover) this.findUniqueBy(Carryover.class, "id", sourceid);
			if(scarryover!=null)
			{
				//������ĩ��ת�еļ�¼Ϊ��δ��ת��
				scarryover.setStatus("δ��ת");
				this.getBaseDao().updateObject(scarryover);
				//���豸�۾���ϸ�ж�Ӧcarryover�����µļ�¼���޸�Ϊ��δ��ת��
				List assertlinelist = this.findWithQuery(Assetscardline.class,"mon='"+scarryover.getMon()+"' and year='"+scarryover.getYear()+"'");
				if(assertlinelist.size()>0)
				{
					for(int j=0;j<assertlinelist.size();j++)
					{
						Assetscardline assetscardline = (Assetscardline) assertlinelist.get(j);
						assetscardline.setStatus("δ��ת");
						
					}
				}
			}
		}

		//ɾ��assetcardlinelist�н�ת�в��������  
		List assetcardlinelist = this.findWithQuery(Assetscardline.class,"mon='"+carryover.getMon()+"' and year='"+carryover.getYear()+"'");
		
		this.getBaseDao().deleteBatch(assetcardlinelist);
		this.getBaseDao().deleteObject(carryover);
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