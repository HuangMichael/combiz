package combiz.business.assetscard;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.domain.assetscard.Carryover;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AssetscardImpl extends IBOBaseImpl
implements AssetscardSrv
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
	 * 
	 * @TODO ���ʼ����carryover���г���һ�г�ʼ��������
	 * @param 
	 * @throws Exception
	 * @��ΰ 2010-05-07 ����22:01
	 */
	public void insert() throws Exception {
//		Assetscard assetscard= new Assetscard();
		Carryover carryover = new Carryover();
		//�������ڸ�Ϊ�ַ�����
		Date  today = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat month = new SimpleDateFormat("MM"); 
		String year = sdf.format(today); 
		String mon = month.format(today); 
//��carryover���û����ͬ�����²�������
		List carryoverlist = this.findWithQuery(Carryover.class,"year='"+year+"' and mon='"+mon+"'");
		if(carryoverlist.size()==0)
		{
			carryover.setStatus("δ��ת");
			carryover.setYear(year);
			carryover.setMon(mon);
			this.getBaseDao().saveObject(carryover);

		}
					
			//��assetscard�и�������
			List assetcardlist = this.getBaseDao().findWithQuery(Assetscard.class, "status='δ��ʼ��'");
			if(assetcardlist.size()>0)
			{
				for(int i=0;i<assetcardlist.size();i++)
				{
					Assetscard assetscard = (Assetscard) assetcardlist.get(i);
					assetscard.setStatus("�ѳ�ʼ��");
					this.getBaseDao().updateObject(assetscard);
				}
			
			//��assetscardline�в���"δ��ת"��status��
           List assetscardlinelist = this.getBaseDao().findWithQuery(Assetscardline.class," status=''");
               if(assetscardlinelist.size()>0)
               {
            	   for( int i=0; i<assetscardlinelist.size();i++)
            	   {
            		  Assetscardline assetscardline=(Assetscardline) assetscardlinelist.get(i);
            		  assetscardline.setStatus("δ��ת");
            		  this.getBaseDao().updateObject(assetscardline);
            	   }
               }
			

		}

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
	 * @TODO ���ʼ����carryover���г���һ�г�ʼ��������
	 * @param 
	 * @throws Exception
	 * @��ΰ 2010-05-07 ����22:01
	 */
	public void uninsert() throws Exception {
	
		Date nowdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat month = new SimpleDateFormat("MM"); 
		String year = sdf.format(nowdate); 
		String mon = month.format(nowdate);
		List carryoverlist = this.findWithQuery(Carryover.class,"year='"+year+"' and mon='"+mon+"'");
		if(carryoverlist.size()>0 )
		{

			Carryover carryover = (Carryover) carryoverlist.get(0);
			carryover.setStatus("δ��ת");
			super.update(carryover);

		}
		List assetscardlist=this.getBaseDao().findWithQuery(Assetscard.class, "status='�ѳ�ʼ��'");
		
		if(assetscardlist.size()>0)
		{
			for(int i=0;i<assetscardlist.size();i++)
				{Assetscard assetscard= (Assetscard) assetscardlist.get(i);
				assetscard.setStatus("δ��ʼ��");
				}
			
			
		}

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