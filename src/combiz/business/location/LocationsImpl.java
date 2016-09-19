package combiz.business.location;

import combiz.domain.classattr.Classspec;
import combiz.domain.location.Locations;
import combiz.domain.location.Locspec;
import combiz.domain.location.Locstruct;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.ui.RecWindow;

import java.util.Date;
import java.util.List;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class LocationsImpl extends IBOBaseImpl implements LocationsSrv {

	
	/**
	 * ���λ��
	 * 
	 * Ӣ��˼  Nov 24, 2009
	 * @param locstruct
	 * @param locations
	 * @throws Exception
	 */
	public void addLocations(Locstruct locstruct, Locations locations)
	throws Exception
	{
		LocstructSrv locstructSrv = (LocstructSrv) IBOSrvUtil.getSrv("locstruct");
		locstructSrv.save(locstruct);
		
		//����λ��
		this.save(locations);
	}
	
	
	
	@Override
	public boolean beforeInsert(Object obj) throws Exception
	{
		Locations locations = (Locations) obj;
		// ������������
		if (locations.getClassid() != null)
			this.genLocspec(locations);
		
		return true;
	}




	@Override
	public boolean beforeDelete(Object obj) throws Exception
	{
		Locations locations = (Locations) obj;
		List list = this.getBaseDao().findWithQuery(Locstruct.class,
				"location='"+locations.getLocation()+"'");
		if(list.size()>0) //������һ��ϵͳ�д��ڣ����ܴ����Ӽ��͸���
		{
			LocstructSrv locstructSrv = (LocstructSrv) IBOSrvUtil.getSrv("locstruct");
			if(list.size()==1)  //ֻ��һ��ϵͳ�д���
			{
				Locstruct locs = (Locstruct) list.get(0);
				if(locstructSrv.hasChildren(locs))
				{
					Messagebox.show("λ��["+locations.getDescription()+"]������λ�ã������ײ�λ�ÿ�ʼɾ����");
					return false;
				}
				else
				{
					locstructSrv.delete(locs);
				}
			}
			else //�ڶ��ϵͳ����
			{
				if(Messagebox.show("λ��["+locations.getDescription()+"]�ڶ��ϵͳ�д��ڣ���ɾ������ϵͳ�еĸ�λ�ã��Ƿ�ȷ��ɾ����","�Ƿ�ȷ��ɾ����",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION)==Messagebox.YES)
				{
					for(int i=0;i<list.size();i++)
					{
						Locstruct locs = (Locstruct) list.get(i);
						if(locstructSrv.hasChildren(locs))
						{
							Messagebox.show("λ��["+locations.getDescription()+"]��ϵͳ["+locs.getSystemid()+"]�д�����λ�ã������ײ�λ�ÿ�ʼɾ����");
							return false;
						}
						else
						{
							locstructSrv.delete(locs);
						}
					}
				}
			}
		}

		return true;
	}



	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception
	{
		// ɾ������
		super.delete(obj);
		// ɾ����������locance
		this.deleteAllChild(obj, "locspec");
	}


	/**
	 * 
	 * @return ���ߣ���С�� ���ڣ�2006-12-15
	 */
	@Override
	public void save(Object obj) 
	throws Exception
	{
		Locations locations = (Locations) obj;
		if (locations.getId() != null) // ���¼�¼
		{
			RecWindow recWnd = this.getRecWnd();
			Component cmp = recWnd.getFellow("locations.classid");
			if (cmp != null && cmp.isModified()
					&& locations.getClassid() != null) 
			{
				// ���¼�������
				int locspecCount = this.getCountByRelation(locations, "locspec");
				if (locspecCount<= 0)
					this.genLocspec(locations);
			}
		}
		
		// ����������
		super.save(locations);
	}
	

	
	/**
	 * ����λ�ü�������
	 * 
	 * ���ߣ����� ���ڣ�2007-7-8
	 * 
	 * @throws Exception
	 */
	public void genLocspec(Locations location)
	throws Exception 
	{
		List classspecList = this.getBaseDao().findWithQuery(Classspec.class,
				"classid='" + location.getClassid() + "'");
		if (classspecList.size() > 0) 
		{
			for (int i = 0; i < classspecList.size(); i++) {
				Classspec classpec = (Classspec) classspecList.get(i);
				Locspec locspec = new Locspec();
				locspec.setLocation(location.getLocation());
				locspec.setClassid(classpec.getClassid());
				locspec.setClassattr(classpec.getClassattr());
				locspec.setUnitid(classpec.getUnitid());
				locspec.setChangeby(this.getUserInfo().getLabornum());
				locspec.setChangedate(new Date());
				locspec.setIsmustbe(classpec.getIsmustbe());
				locspec.setRemark(classpec.getRemark());
				super.save(locspec);
			}
		}
	}
	
	
	
	
	public void updateLocCode(Locations loc) 
	throws Exception 
	{
		/**
		String parentStr = "";
		HashMap map = (HashMap) this.getBaseDao().selectListBySql(
				"select parent from locations where location='"
						+ loc.getLocation() + "'").get(0);
		Iterator it = map.keySet().iterator();
		if (it.hasNext()) {
			parentStr = (String) map.get(it.next());
		}
		String newLoc = this.getLocation(loc);
		super.update(loc);
		Locations loca = (Locations) this.getBaseDao().findWithQuery(
				Locations.class, "location ='" + loc.getParent() + "'").get(0);
		if (loca.getHaschild().equals("��")) {
			loca.setHaschild("��");
			super.update(loca);
		}

		int num = this.getBaseDao().selectCountByHql(
				"select count(*) from Locations l where l.parent='" + parentStr
						+ "'");
		Locations once = (Locations) this.getBaseDao().findWithQuery(
				Locations.class, "location ='" + parentStr + "'").get(0);
		if (num == 0) {
			once.setHaschild("��");
			super.update(once);
		}
		List moveLocCode = this.getBaseDao().selectListByHql(
				"from Locance l where l.location like '" + loc.getLocation()
						+ "%'");
		System.out.println("�ýڵ������ȱ�ļ���Ϊ��" + moveLocCode.size());
		this.getBaseDao().deleteBatch(moveLocCode);
		this.updateAllLocCode(newLoc, loc);*/
	}
	
	
	// ���ƶ��ڵ㼰�����ӽ�����ĸ���
	private void updateAllLocCode(String newLoc, Object obj)
	{
		/*Locations loc = (Locations) obj;
		String before = loc.getLocation();//������ѯ�豸�ĵ�ַ
		// ///////���±��ƶ��ڵ��location�Լ�parentΪ���ƶ��ڵ�
		List list = this.getBaseDao().selectListByHql(
				"from Locations l where l.location='" + loc.getLocation()
						+ "' or l.parent='" + loc.getLocation() + "'");
		// String newLoc = this.getLocation(loc);
		for (int i = 0; i < list.size(); i++) {
			Locations temp = (Locations) list.get(i);
			if (temp.getLocation().equals(loc.getLocation())) {
				temp.setLocation(newLoc);
			} else {
				temp.setParent(newLoc);
			}
		}
		try {
			this.getBaseDao().updateBatch(list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// /////�����ȱ��У�������Ӧ������
		Locance locance = new Locance();
		locance.setAncestor(newLoc);
		locance.setLocation(newLoc);
		locance.setSystemid(loc.getSystemid());
		try {
			this.getBaseDao().saveObject(locance);
			Locations parent = this.getParent(loc);
			while (null != parent) {
				locance = new Locance();
				locance.setLocation(newLoc);
				locance.setAncestor(parent.getLocation());
				locance.setSystemid(parent.getSystemid());
				super.save(locance);
				parent = this.getParent(parent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ///////�����뱻�ƶ��ڵ��йص��豸
		List equipment = this.getBaseDao().selectListByHql("from Equipment e where e.location='"+before+"'");
		System.out.println("��Ӧ�豸�ļ���Ϊ��======"+equipment.size());
		if (equipment.size() > 0) {
			for (int e = 0; e < equipment.size(); e++) {
				Equipment equip = (Equipment) equipment.get(e);
				equip.setLocation(newLoc);
				try {
					this.update(equip);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		/////�����뱻�ƶ��ڵ��йصļ�������
		List locspec = this.getBaseDao().selectListByHql("from Locspec l where l.location='"+before+"'");
		if(locspec.size()>0){
			for(int i=0;i<locspec.size();i++){
				Locspec temp = (Locspec)locspec.get(i);
				temp.setLocation(newLoc);
				try{
				this.update(temp);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
		}
		////////////////////////////////////////
		List child = this.getBaseDao().selectListByHql(
				"from Locations l where l.parent='" + newLoc + "'");
		if (child.size() > 0) {
			System.out.println("ִ�е���HERE+HERE+HERE+HERE");
			for (int i = 0; i < child.size(); i++) {
				Locations location = (Locations) child.get(i);
				String updateLoc = location.getParent();
				if (i < 10) {
					int num = i + 1;
					updateLoc += "0" + num;
				}
				System.out.println("�ӽڵ����=========" + updateLoc);
				this.updateAllLocCode(updateLoc, location);
			}
		}*/
	}

	// �ڵ㱻�ƶ��󣬻�ȡ���±���
	private String getLocation(Object obj)
	{
		/*Locations lo = (Locations) obj;
		String result = lo.getParent();
		Locations lac = null;
		List list = this.getBaseDao().findWithQuery(
				Locations.class,
				"location in (select max(l.location) from Locations l where l.parent='"
						+ result + "')");
		if (list.size() > 0) {
			lac = (Locations) list.get(0);
		}
		if (null == lac) {
			result += "01";
		} else {
			String max = lac.getLocation();
			String temp = max.substring(result.length(), max.length());
			int num = Integer.parseInt(temp) + 1;
			if (num < 10) {
				result += "0" + num;
			} else {
				result += "" + num;
			}
		}
		
		return result;*/
		
		return null;
	}

}