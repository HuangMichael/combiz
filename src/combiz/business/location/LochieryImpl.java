package combiz.business.location;

import combiz.domain.location.Locance;
import combiz.domain.location.Lochiery;
import combiz.system.IBOBaseImpl;

import java.util.List;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class LochieryImpl extends IBOBaseImpl
implements LochierySrv {
	
	private LocanceSrv locanceSrv;
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Lochiery))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
	/** 
	 * 
	 * @param lochiery
	 * @return
	 * ���ߣ���С�� ���ڣ�2006-12-15
	 */
	public void save(Lochiery lochiery)
	throws Exception
	{
		if(lochiery.getId() != null) //�޸�
		{
			this.getBaseDao().updateObject(lochiery);
		}
		else  
		{
			//�½�
			this.getBaseDao().saveObject(lochiery);
			//���¸�����¼
			List parentList = this.findWithQuery("location = '"+lochiery.getParent()+"' and systemid='"+lochiery.getSystemid()+"'");
			if(parentList.size()>0)
			{
				Lochiery loch = (Lochiery)parentList.get(0);
				loch.setHaschild("��");
				this.getBaseDao().updateObject(loch);
			}

			//�������ȱ�����
			Locance locance = new Locance();
			locance.setAncestor(lochiery.getLocation());
			locance.setLocation(lochiery.getLocation());
			locance.setSystemid(lochiery.getSystemid());
			this.getBaseDao().saveObject(locance);
			//ѭ����ȡ�������������ȱ�����
			Lochiery loc = this.getParent(lochiery);
			while(loc!=null)
			{
				locance = new Locance();
				locance.setAncestor(loc.getLocation());
				locance.setLocation(lochiery.getLocation());
				locance.setSystemid(loc.getSystemid());
				//this.locanceSrv.save(locance);
				this.getBaseDao().saveObject(locance);

				loc = this.getParent(loc);
			}

		}
	}
	
	/**
	 * ��ȡλ�ø���
	 * @param loc ��Ҫ��ȡ������λ�ò���
	 * @return
	 * ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public Lochiery getParent(Lochiery loc)
	throws Exception 
	{
		List parentList = this.findWithQuery("location = '" + loc.getParent() + "' and systemid='"+loc.getSystemid()+"'");
		if(parentList.size()>0)
		{
			Lochiery parent = (Lochiery)parentList.get(0);
			return parent;
		}
		
		return null;
	}


	/**
	 * ɾ����¼
	 * @param 
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2007-1-9
	 */
	public void delete(Lochiery lochiery)
	throws Exception
	{
		//ɾ��LOCANCE���ȱ�
		List locanceList = this.getLocanceSrv().findWithQuery("location='"+lochiery.getLocation()
				+"' and systemid='"+lochiery.getSystemid()+"'");
		for(int i=0;i<locanceList.size();i++)
		{
			Locance locance = (Locance) locanceList.get(i);
			this.getBaseDao().deleteObject(locance);
		}
		//���¸���
		Lochiery loch = this.getParent(lochiery);
		if(loch!=null)
		{
			List sameParent = this.findWithQuery("parent = '"+loch.getLocation()+"' and systemid='"+loch.getSystemid()+"'");
			if(sameParent.size()==0)
			{
				loch.setHaschild("��");
				this.update(loch);
			}
		}
		//ɾ���ṹ��LOCHIERY����
		this.delete(lochiery);
	}

	public LocanceSrv getLocanceSrv() {
		return locanceSrv;
	}

	public void setLocanceSrv(LocanceSrv locanceSrv) {
		this.locanceSrv = locanceSrv;
	}


}
