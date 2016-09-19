package combiz.business.location;

import combiz.domain.location.Locance;
import combiz.domain.location.Locstruct;
import combiz.domain.workflow.Wfaction;
import combiz.domain.workflow.Wfinstance;
import combiz.system.IBOBaseImpl;
import combiz.system.util.Util;

import java.util.List;

public class LocstructImpl extends IBOBaseImpl
implements LocstructSrv
{
	
	/**
	 * ��ӵ�ϵͳ
	 * locstruct ԭ����λ�ýṹ
	 * newobj  ��λ�ýṹ
	 * ��С��  Nov 24, 2009
	 * @param locstruct
	 */
	public void addToSystem(Locstruct locstruct, Locstruct newobj)
	throws Exception
	{
		this.save(newobj);
		//���λ�ü������Ӽ�λ�õ�ѡ�е�ϵͳ
		if(Util.getBoolean(newobj.getAddchild()))
		{
			String systemid = newobj.getSystemid();
			List childs = this.getChildren(locstruct,systemid);
			for(int i=0;i<childs.size();i++)
			{
				Locstruct childLoc = (Locstruct) childs.get(i);
				Locstruct newchild = new Locstruct();
				newchild.setLocation(childLoc.getLocation());
				newchild.setHaschild(childLoc.getHaschild());
				newchild.setOrderby(childLoc.getOrderby());
				newchild.setSystemid(systemid); //ϵͳ��ͬ
				newchild.setParent(childLoc.getParent());
				
				this.save(newchild);
				
				this.addChildToSystem(childLoc, systemid);
			}
		}
		else
			newobj.setHaschild("��");
	}
	
	/**
	 * ���ڵݹ������������Ӽ���ָ����ϵͳ
	 * ��С��  Nov 24, 2009
	 * @param parent
	 * @param systemid
	 * @throws Exception
	 */
	private void addChildToSystem(Locstruct parent, String systemid)
	throws Exception
	{
		List childs = this.getChildren(parent,systemid);  //ȡ���϶�����Ӽ�
		for(int i=0;i<childs.size();i++)
		{
			Locstruct childLoc = (Locstruct) childs.get(i);
			Locstruct newchild = new Locstruct();
			newchild.setLocation(childLoc.getLocation());
			newchild.setSystemid(systemid); //ϵͳ��ͬ
			newchild.setParent(childLoc.getParent());
			newchild.setHaschild(childLoc.getHaschild());
			newchild.setOrderby(childLoc.getOrderby());
			
			this.save(newchild);
			
			this.addChildToSystem(childLoc, systemid);
		}
	}
	
	/**
	 * ��ȡλ���Ӽ������˳�����ϵͳ�в����ڵ��Ӽ�
	 * 
	 * @param loc
	 *            ��Ҫ��ȡ������λ�ò���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	private List getChildren(Locstruct locstruct,String newsystemid)
	throws Exception
	{
		return this.getBaseDao().findWithQuery(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "' and location not in(select t.location from Locstruct t where t.systemid='"
				+ newsystemid + "' and t.parent='" + locstruct.getLocation() + "')");
	}
	
	
	/**
	 * �û��ӿ�
	 * �������֮ǰ���û��ӿ�
	 * ����false�򲻻ᱣ��ö���
	 * ��С��  Nov 14, 2009
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean beforeInsert(Object obj) 
	throws Exception
	{
		Locstruct locstruct = (Locstruct) obj;
		
		//���¸�����¼
		Locstruct parent = this.getParent(locstruct);
		if (parent != null) 
		{
			parent.setHaschild("��");
			this.getBaseDao().updateObject(parent);
		}
		
		//����������������
		this.genAncestor(locstruct);

		return true;
	}
	
	
	
	@Override
	public boolean beforeUpdate(Object obj) 
	throws Exception
	{
		Locstruct locstruct = (Locstruct) obj;
		//�ж��Ƿ��Ѿ����޸�
		int count = this.getBaseDao().selectCountByWhere(Locstruct.class,
				"location='"+locstruct.getLocation()+"' and systemid='"+locstruct.getSystemid()+
				"' and parent='"+locstruct.getParent()+"'");
		
		if(count<=0) //�趨�޸��˸����ֶ�parent��ʾ���Ա�����afterUpdate�����н��к����ĸ��²���
			changeParent = true;  
		
		
		return true;
	}
	
	private boolean changeParent;
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
		if(changeParent)  //����ĸ��²�������д��afterUpdate�����в���ȷ
		{
			Locstruct locstruct = (Locstruct) obj;
			//˵��locstruct�����Ѿ����޸��˸���parent�ֶ�
			//���¸�����¼
			Locstruct parent = this.getParent(locstruct);
			if (parent != null) 
			{
				parent.setHaschild("��");
				this.getBaseDao().updateObject(parent);
			}
			//ɾ�����ȱ�����
			this.getBaseDao().executeSql("delete from locance where location='"
					+locstruct.getLocation()+"' and systemid='"+locstruct.getSystemid()+"'");
			//����������������
			this.genAncestor(locstruct);
			
			//ѭ���Ӽ�����Ϊ�Ӽ�Ҳ��������ancestor
			List childs = this.getChildren(locstruct);
			if(childs.size()>0)
				this.updateChildsAncestor(childs);
		}
	}
	///����һ���������õݹ�
	private void updateChildsAncestor(List childs)
	throws Exception
	{
		for(int i=0;i<childs.size();i++)
		{
			Locstruct child = (Locstruct) childs.get(i);
			this.getBaseDao().executeSql("delete from locance where location='"
					+child.getLocation()+"' and systemid='"+child.getSystemid()+"'");
			//����������������
			this.genAncestor(child);
			
			List childs2 = this.getChildren(child);
			if(childs2.size()>0)
				this.updateChildsAncestor(childs2);
		}
	}
	
	
	/**
	 * �������ȱ�����
	 * ��С��  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void genAncestor(Locstruct locstruct)
	throws Exception
	{
		//�������ȱ�����
		Locance locance = new Locance();
		locance.setAncestor(locstruct.getLocation());
		locance.setLocation(locstruct.getLocation());
		locance.setSystemid(locstruct.getSystemid());
		this.getBaseDao().saveObject(locance);
		// ѭ����ȡ�������������ȱ�����
		Locstruct parent = this.getParent(locstruct);
		while (parent != null) 
		{
			locance = new Locance();
			locance.setLocation(locstruct.getLocation());
			locance.setAncestor(parent.getLocation());
			locance.setSystemid(parent.getSystemid());
			this.getBaseDao().saveObject(locance);

			parent = this.getParent(parent);
		}
	}
	
	

	@Override
	public void save(Object obj)
	throws Exception
	{
		super.save(obj);
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
		Locstruct locstruct = (Locstruct) obj;
		
		//���¸���
		this.updateParent(locstruct);
		
		//ɾ�������Ӽ�
		this.deleteAllChild(locstruct);
		
		//ɾ�����ȱ�
		this.getBaseDao().deleteBatch(this.findWithQuery(Locance.class, "location='"+locstruct.getLocation()+"'"));
		//���ɾ���Լ�
		this.getBaseDao().deleteObject(locstruct);
	}
	
	
	/**
	 * ɾ�����ڸ�ϵͳ�������Ӽ�
	 * ��С��  Nov 24, 2009
	 * @param parent
	 * @throws Exception
	 */
	public void deleteAllChild(Locstruct parent)
	throws Exception
	{
		List list = this.getChildren(parent);
		for(int i=0;i<list.size();i++)
		{
			Locstruct child = (Locstruct) list.get(i);
			//ɾ�����ȱ�
			this.getBaseDao().deleteBatch(this.findWithQuery(Locance.class, "location='"+child.getLocation()+"'"));
			//�Լ�
			this.getBaseDao().deleteObject(child);
			
			//�ݹ�
			this.deleteAllChild(child);
		}
	}
	
	
	/**
	 * ���¸���
	 * ��С��  Nov 24, 2009
	 * @param locstruct
	 * @throws Exception
	 */
	public void updateParent(Locstruct locstruct)
	throws Exception
	{
		Locstruct parentloc = this.getParent(locstruct);
		if (parentloc != null)
		{
			int sameParent = this.getBaseDao().selectCountByWhere(Locstruct.class,
					"parent = '" + locstruct.getParent() + "' and systemid='"
					+ locstruct.getSystemid() + "' and location<>'"
					+ locstruct.getLocation() + "'");
			if (sameParent == 0)
			{
				parentloc.setHaschild("��");
				this.getBaseDao().updateObject(parentloc);
			}
		}
	}
	
	
	
	/**
	 * ��ȡλ���Ӽ������˳�����ϵͳ�в����ڵ��Ӽ�
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public List getChildren(Locstruct locstruct)
	throws Exception
	{
		return this.getBaseDao().findWithQuery(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
	}
	
	
	/**
	 * �ж��Ƿ�λ�����Ӽ�
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public boolean hasChildren(Locstruct locstruct)
	throws Exception
	{
		int count = this.getChildrenCount(locstruct);
		if(count>0)
			return true;
		else
			return false;
	}
	
	/**
	 * ��ȡλ���Ӽ�������
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public int getChildrenCount(Locstruct locstruct)
	throws Exception
	{
		return this.getBaseDao().selectCountByWhere(Locstruct.class, "parent = '" + locstruct.getLocation()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
	}
	
	/**
	 * ��ȡλ�ø���
	 * @return ���ߣ���С�� ���ڣ�2007-5-4
	 */
	public Locstruct getParent(Locstruct locstruct)
	throws Exception
	{
		List parentList = this.getBaseDao().findWithQuery(Locstruct.class, "location = '" + locstruct.getParent()
				+ "' and systemid='" + locstruct.getSystemid() + "'");
		if (parentList.size() > 0)
		{
			Locstruct parent = (Locstruct) parentList.get(0);
			return parent;
		}

		return null;
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
	
	
}