package combiz.business.equipment;

import combiz.domain.assetscard.Assetscard;
import combiz.domain.assetscard.Assetscardline;
import combiz.domain.assetscard.Carryover;
import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.domain.equipment.Eqance;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.system.IBOBaseImpl;
import combiz.system.ui.RecWindow;
import combiz.system.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inbasis.zk.ui.Component;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class EquipmentImpl extends IBOBaseImpl implements EquipmentSrv {
	private Carryover mainObject;

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Equipment))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		// ɾ������
		// ɾ���������󣭸��෽��
		this.deleteAllChild(obj, "eqspec");
		this.deleteAllChild(obj, "eqrelation");
		super.delete(obj);
	}

	@Override
	public void save(Object obj) throws Exception {
		if (!(obj instanceof Equipment))
			throw new Exception("Ҫ����Ķ��󴫵ݲ���ȷ��");

		Equipment equipment = (Equipment) obj;
		String eqnum = equipment.getEqnum();
		String parent = equipment.getParent();
		if (equipment.getId() == null)// �½�
		{
			// ���豸���������п���
			if (equipment.getClassid() != null)
				this.genEqspec(equipment);

			Eqance newobj = new Eqance();
			newobj.setEqnum(eqnum);
			newobj.setAncestor(eqnum);
			this.getBaseDao().saveObject(newobj);

			if(Util.isNotNull(parent))
			{
				List eqancelist = this.getBaseDao().findWithQuery(Eqance.class, "eqnum='"+parent+"'");
				if(eqancelist.size()>0)
				{
					for(int i=0;i<eqancelist.size();i++)
					{
						Eqance eqance =  (Eqance) eqancelist.get(i);
						Eqance newobj2 = new Eqance();
						newobj2.setEqnum(eqnum);
						newobj2.setAncestor(eqance.getAncestor());
						this.getBaseDao().saveObject(newobj2);
					}
				}
				else
				{
					Eqance newobj2 = new Eqance();
					newobj2.setEqnum(eqnum);
					newobj2.setAncestor(parent);
					this.getBaseDao().saveObject(newobj2);
					
				}

			}

			super.save(equipment);

		} 
		else//����
		{
			if (equipment.getAssetnum() != null
					&& !equipment.getAssetnum().equals("")) 
			{ // �жϹ̶��ʲ�����Ƿ�Ϊһ
				List list1 = this.getBaseDao().findWithQuery(Equipment.class,
						"assetnum='" + equipment.getAssetnum() + "' and eqnum <>'"+equipment.getEqnum()+"'");
				if (list1.size() > 0) {
					throw new Exception("���豸�̶��ʲ���Ų�Ψһ�������±�ţ�");
				}
			}

			RecWindow recWnd = this.getRecWnd();
			if(recWnd!=null)
			{
				Component cmp = recWnd.getFellow("equipment.classid");
				if (cmp != null && cmp.isModified()
						&& equipment.getClassid() != null) {
					// ���¼�������
					List locspecList = this.findByRelation(equipment, "locspec");
					if (locspecList.size() <= 0) {
						this.genEqspec(equipment);
					}
				}
			}
			super.save(equipment);
		}
	}


	/**
	 * ��ȡ��������
	 * @param classification
	 * @return
	 * ���ߣ���С�� ���ڣ�2007-6-27
	 */
	public Equipment getParent(Equipment equipment)
	throws Exception 
	{
		if(Util.isNotNull( equipment.getParent()))
		{
			List parentList = this.findWithQuery("eqnum = '" + equipment.getParent() + "'");
			if(parentList.size()>0)
				return (Equipment)parentList.get(0);
			return null;
		}
		else
		{
			return null;
		}

	}




	public void eqparentchg(Equipment equipment,String orgParent)
	throws Exception
	{

		if(Util.isNotNull(equipment.getParent()))
		{
			if(Util.isNull(orgParent))//ԭ���޸��豸�������и��豸
			{
				Eqance neqance = new Eqance();
				neqance.setAncestor(equipment.getParent());
				neqance.setEqnum(equipment.getEqnum());
				this.getBaseDao().saveObject(neqance);
				this.getBaseDao().updateObject(equipment);
			}
			//�����ǰ�ڵ�������ӽڵ�
			List chldList = this.getBaseDao().findWithQuery(Eqance.class,"ancestor='"+equipment.getEqnum()+"'");
			if(chldList.size()>0)
			{
				for(int i=0;i<chldList.size();i++)
				{
					Eqance eqance = (Eqance) chldList.get(i);
					String chldequip = eqance.getEqnum();
					//ɾ�����ȱ��¼
					List delList = this.getBaseDao().findWithQuery(Eqance.class, "eqnum='"+chldequip+"'");
					for(int j=0;j<delList.size();j++)
					{
						this.getBaseDao().deleteObject(delList.get(j));
					}
				}

				for(int i=0;i<chldList.size();i++)
				{
					Eqance eqance = (Eqance) chldList.get(i);
					String chldequip = eqance.getEqnum();

					//���¸�����¼
					Equipment chldEquipment;
					if(chldequip.equals(equipment.getEqnum()))
					{
						chldEquipment = equipment;
						//�����Լ�
						this.update(equipment);
					}
					else
					{
						chldEquipment = (Equipment) this.getBaseDao().findUniqueBy(Equipment.class, "eqnum", chldequip);
					}

//					Equipment parent = this.getParent(chldEquipment);

					//����classance���ȱ�����
					//�������ȱ�����
					int count = this.getBaseDao().selectCountByHql("select count(*) from Eqance t where t.ancestor='"+chldequip+"' and eqnum='"+chldequip+"'");

					if(count==0)
					{
						Eqance neqance = new Eqance();
						neqance.setAncestor(chldequip);
						neqance.setEqnum(chldequip);
						super.save(neqance);
					}

					//ѭ����ȡ�������������ȱ�����
					Equipment equip = this.getParent(chldEquipment);
					while(equip!=null)
					{
						Eqance neqance = new Eqance();
						neqance.setEqnum(chldequip);
						neqance.setAncestor(equip.getEqnum());
						super.save(neqance);
						equip = this.getParent(equip);
					}
				}

			}
			else
			{
				this.update(equipment);
				int count = this.getBaseDao().selectCountByHql("select count(*) from Eqance t where t.ancestor='"+equipment.getEqnum()+"' and eqnum='"+equipment.getEqnum()+"'");

				if(count==0)
				{
					Eqance newobj = new Eqance();
					newobj.setEqnum(equipment.getEqnum());
					newobj.setAncestor(equipment.getEqnum());
					this.getBaseDao().saveObject(newobj);
				}

				/*Eqance newobj3 = new Eqance();
				newobj3.setEqnum(equipment.getEqnum());
				newobj3.setAncestor(equipment.getParent());
				this.getBaseDao().saveObject(newobj3);*/
			}
		}
		else//��������Ϊ��
		{
			
//			List myselflist = this.getBaseDao().findWithQuery(Eqance.class, "eqnum='"+equipment.getEqnum()+"'");
			this.getBaseDao().updateObject(equipment);

			//�����ǰ�ڵ�������ӽڵ�
			List chldList = this.getBaseDao().findWithQuery(Eqance.class,"ancestor='"+equipment.getEqnum()+"' or eqnum='"+equipment.getEqnum()+"'");
			if(chldList.size()>0)//�ж��Ƿ��к��ӽڵ�
			{
				for(int i=0;i<chldList.size();i++)
				{
					Eqance eqance = (Eqance) chldList.get(i);
					String chldequip = eqance.getEqnum();
					//ɾ�����ȱ��¼
					List delList = this.getBaseDao().findWithQuery(Eqance.class, "eqnum='"+chldequip+"'");
					for(int j=0;j<delList.size();j++)
					{
						this.getBaseDao().deleteObject(delList.get(j));
					}
				}


				//���½������ӹ�ϵ
				for(int i=0;i<chldList.size();i++)
				{
					Eqance eqance = (Eqance) chldList.get(i);
					String chldequip = eqance.getEqnum();

					//���¸�����¼
					Equipment chldEquipment;
					if(chldequip.equals(equipment.getEqnum()))
					{
						chldEquipment = equipment;
						//�����Լ�
						this.update(equipment);
					}
					else
					{
						chldEquipment = (Equipment) this.getBaseDao().findUniqueBy(Equipment.class, "eqnum", chldequip);
					}

//					Equipment parent = this.getParent(chldEquipment);

					//����classance���ȱ�����
					//�������ȱ�����
					int count = this.getBaseDao().selectCountByHql("select count(*) from Eqance t where t.ancestor='"+chldequip+"' and eqnum='"+chldequip+"'");

					if(count==0)
					{
						Eqance neqance = new Eqance();
						neqance.setAncestor(chldequip);
						neqance.setEqnum(chldequip);
						super.save(neqance);
					}
					//ѭ����ȡ�������������ȱ�����
					Equipment equip = this.getParent(chldEquipment);
					while(equip!=null)
					{
						Eqance neqance = new Eqance();
						neqance.setEqnum(chldequip);
						neqance.setAncestor(equip.getEqnum());
						this.getBaseDao().saveObject(neqance);
						equip = this.getParent(equip);
					}
				}

			}
			else
			{
				this.update(equipment);
				Eqance newobj = new Eqance();
				newobj.setEqnum(equipment.getEqnum());
				newobj.setAncestor(equipment.getEqnum());
				this.getBaseDao().saveObject(newobj);
			}



		}
	}
	/**
	 * ���ݷ�������豸�ļ�������
	 * 
	 * @param equipment
	 * @throws Exception
	 * ���ߣ����� ���ڣ�2007-7-8
	 */
	public void genEqspec(Equipment equipment)
	throws Exception 
	{
		List classspecList = this.getBaseDao().findWithQuery(Classspec.class,
				"classid='" + equipment.getClassid() + "'");
		if (classspecList.size() > 0) {
			for (int i = 0; i < classspecList.size(); i++) {
				Classspec classpec = (Classspec) classspecList.get(i);
				Eqspec eqspec = new Eqspec();
				eqspec.setEqnum(equipment.getEqnum());
				eqspec.setClassid(classpec.getClassid());
				eqspec.setClassattr(classpec.getClassattr());
				eqspec.setUnitid(classpec.getUnitid());
				eqspec.setChangeby(this.getUserInfo().getLabornum());
				eqspec.setChangedate(new Date());
				eqspec.setIsmustbe(classpec.getIsmustbe());
				eqspec.setRemark(classpec.getRemark());
				super.save(eqspec);
			}
		}
	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////


	/* 
	 * ���ܣ�������
	 * ���ߣ����
	 * ���ڣ�Sep 8, 200812:15:12 PM
	 */
	public void cretetree(List classlist) throws Exception {
		// TODO Auto-generated method stub
		if(classlist.size()>0) {
			for (int i=0;i<classlist.size();i++) {
				Equipment classification = (Equipment) classlist.get(i);
				Long classid = classification.getId();
				if (classification.getId() != null) // �½�
				{
					this.getAnce(classification);
				}
			}
		}
	}


	public void getclass(List classlist) throws Exception {
		Equipment classification = null;
		Classance classance = new Classance();
		if (classlist != null && classlist.size()>0){
			for (int n=0;n<classlist.size();n++) {
				classification = (Equipment) classlist.get(n);
				classance = new Classance();
				classance.setClassid(classification.getParent());
				classance.setAncestor(classification.getClassid());
				this.getBaseDao().saveObject(classance);

				this.getAnce(classification);
			}

		}
	}

	public ArrayList getAnce(Equipment classification) 
	throws Exception 
	{
		List parentList = this.getBaseDao().findWithQuery(Classification.class,"parent = '"
				+ classification.getClassid() + "'");
		if (parentList.size() > 0){
			this.getclass(parentList);
			return (ArrayList) parentList;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see combiz.business.po.PoSrv#verify(java.util.List, java.lang.Object)
	 */
	public void copyfixed(List list) throws Exception {

		Date  today = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat month = new SimpleDateFormat("MM"); 
		String year = sdf.format(today); 
		String mon = month.format(today); 
		Double rel=0.03d;
		Long   m=48l;
		for (int i = 0; i < list.size(); i++) {
			Equipment equipment = (Equipment) list.get(i) ;

			equipment.setAssetnum(equipment.getEqnum());
			this.getBaseDao().updateObject(equipment);


			Assetscard newasset =new  Assetscard(); 
			newasset.setAssetcode(equipment.getEqnum());
			newasset.setAssetname(equipment.getDescription());
			newasset.setUsers(equipment.getChangeby());
			newasset.setUsedepartment(equipment.getDeptnum());
			newasset.setStatus(equipment.getStatus());
			newasset.setUsedate(equipment.getRundate());
			newasset.setParent(equipment.getParent());
			newasset.setAssetsubject("1501");
			newasset.setDepreciationsjt("1502");
			newasset.setEqnum(equipment.getEqnum());
			newasset.setLocation(equipment.getLocation());
			this.getBaseDao().saveObject(newasset);	


			Assetscardline newassetline =new  Assetscardline();  
			newassetline.setAssetcode(equipment.getAssetnum());
			newassetline.setCost(equipment.getPurprice());
			newassetline.setExpectedmonth(m);
			newassetline.setManufacturers(equipment.getManufacturer());
			newassetline.setModel(equipment.getModel());
			newassetline.setMthval((1-0.03)/48d);
			newassetline.setResidualvalues(rel);
			newassetline.setResidual(equipment.getPurprice()*rel);
			newassetline.setMthamount(equipment.getPurprice()*0.03*(1-0.03)/48d);
			newassetline.setStatus("δ��ת");
			newassetline.setNetworth(equipment.getPurprice());
			newassetline.setNet(equipment.getPurprice());
			newassetline.setYear(year);
			newassetline.setMon(mon);
			this.getBaseDao().saveObject(newassetline);




		}



	}

	private List getSelectObjects() {
		// TODO Auto-generated method stub
		return null;
	}
}
