package combiz.business.classattr;

import combiz.domain.classattr.Classance;
import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.domain.equipment.Eqspec;
import combiz.domain.equipment.Equipment;
import combiz.domain.inventory.Item;
import combiz.domain.inventory.Itemspec;
import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolspec;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;

import java.util.Date;
import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class ClassificationImpl extends IBOBaseImpl 
implements ClassificationSrv 
{

	private ClassspecSrv classspecSrv;

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception
	{
		Classification classification = (Classification) obj;

		// �޸ĸ�������
		if (classification.getParent() != null) 
		{
			List sameParentList = this.findWithQuery("parent = '"
					+ classification.getParent() + "' and classid<>'"
					+ classification.getClassid() + "'");
			if (sameParentList == null || sameParentList.size() <= 0) {
				List parentList = this.findWithQuery("classid = '"
						+ classification.getParent() + "'");
				if (parentList != null && parentList.size() > 0) {
					Classification parent = (Classification) parentList.get(0);
					parent.setHaschild("��");
					this.update(parent);
				}
			}
		}


		// ɾ������
		super.delete(obj);
		// ɾ�����������������������󣬹�ϵ��
		this.deleteAllChild(classification, "assetclassspecTable");
		// ɾ�����ȱ�����
		this.deleteAllChild(classification, "classance");
		
		//??????
		// ɾ���豸���ȱ�����
		//this.deleteAllChild(classification, "eqclassTable");
		// ɾ���������ȱ�����
		//this.deleteAllChild(classification, "toolclass");
		// ɾ���÷����Ӧ�Ĺ��ϴ���
		//this.deleteAllChild(classification, "failclass");
	}

	
	/**
	 * �Ӽ�������
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public List getChildrens(Classification classification)
	throws Exception
	{
		return this.findWithQuery("parent = '" + classification.getClassid()
				+ "' and classtype='"+classification.getClasstype()+"'");
	}
	
	/**
	 * �Ӽ�������
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public int getChildrenCount(Classification classification)
	throws Exception
	{
		return this.getBaseDao().selectCountByWhere(Classification.class, 
				"parent = '" + classification.getClassid() + 
				"' and classtype='"+classification.getClasstype()+"'");
	}
	
	/**
	 * �Ƿ����Ӽ�
	 * ��С��  Nov 26, 2009
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	public boolean hasChildren(Classification classification)
	throws Exception 
	{
		int count = this.getChildrenCount(classification);
		if (count > 0)
			return true;
		else
			return false;
	}
	

	/**
	 * ��������
	 */
	/* 
	 * ���ܣ�
	 * ���ߣ�����
	 * ���ڣ�2008-3-20����09:24:58
	 */
	@Override
	public void save(Object obj)
	throws Exception 
	{
		Classification classification = (Classification) obj;
		if (classification.getId() == null) // �½�
		{
			// ���¸�����¼
			Classification parent = this.getParent(classification);
			if (parent != null) 
			{
				parent.setHaschild("��");
				super.update(parent);
			}
			else
			{
				Classance classance = new Classance();
				classance.setAncestor(null);
				classance.setClassid(classification.getClassid());
				classance.setClasstype(classification.getClasstype());
				super.save(classance);
			}

			// ����classance���ȱ�����
			// �������ȱ�����
			Classance classance = new Classance();
			classance.setAncestor(classification.getClassid());
			classance.setClassid(classification.getClassid());
			classance.setClasstype(classification.getClasstype());
			super.save(classance);
			// ѭ����ȡ�������������ȱ�����
			Classification classc = this.getParent(classification);
			while (classc != null) {
				classance = new Classance();
				classance.setClassid(classification.getClassid());
				classance.setAncestor(classc.getClassid());
				classance.setClasstype(classification.getClasstype());
				super.save(classance);

				classc = this.getParent(classc);
			}

			// �����Լ�
			super.save(obj);

			// �̳и��豸���༼������
			ClassspecSrv classspec = (ClassspecSrv)IBOSrvUtil.getSrv("classspec");
			classspec.saveUpExtends(classification);
		} else {
			super.save(classification);
		}
		
		
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param classification
	 * @return ���ߣ���С�� ���ڣ�2007-6-27
	 */
	public Classification getParent(Classification classification) 
	throws Exception 
	{
		List parentList = this.findWithQuery("classid = '"
				+ classification.getParent() + "'");
		if (parentList.size() > 0)
			return (Classification) parentList.get(0);

		return null;

	}

	public ClassspecSrv getClassspecSrv() {
		return classspecSrv;
	}

	public void setClassspecSrv(ClassspecSrv classspecSrv) {
		this.classspecSrv = classspecSrv;
	}

	/**
	 * ͬ���������� 
	 * @param classification
	 * @return ���ߣ���� ���ڣ�2009-9-9
	 */
	/* (non-Javadoc)
	 * @see combiz.business.classattr.ClassificationSrv#synchspec(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see combiz.business.classattr.ClassificationSrv#synchspec(java.lang.Object)
	 */
	public void synchspec(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if (!(obj instanceof Classification))
			throw new Exception ("���ݵĶ�����ȷ��");
		Classification classification = (Classification) obj;
		String classtype = classification.getClasstype();
		//�õ��÷����µļ��������б�
		List classapcelist = this.getBaseDao().findWithQuery(Classspec.class, " classid='"+classification.getClassid()+"'");
		
		List equiplist = null;
		if ("�ʲ�".equals(classtype)) {
			equiplist = this.getBaseDao().findWithQuery(Equipment.class, "classid = '"+classification.getClassid()+"'");
		}
		List itemlist = null;
		if ("����".equals(classtype)) {
			itemlist = this.getBaseDao().findWithQuery(Item.class, "classid = '"+classification.getClassid()+"'");
		}
		List toollist = null;
		if ("����".equals(classtype)) {
			toollist = this.getBaseDao().findWithQuery(Tool.class, "classid = '"+classification.getClassid()+"'");
		}
		
		
		for (int t=0;t<classapcelist.size();t++) {//ѭ�������µļ�������
			Classspec classspec =(Classspec) classapcelist.get(t);
			// ����������ʲ����࣬��ִ������Ĵ���
			if ("�ʲ�".equals(classtype)) {
				//List equiplist = this.getBaseDao().findWithQuery(Equipment.class, "classid = '"+classification.getClassid()+"'");
				if (equiplist.size()>0) {
					for (int i=0;i<equiplist.size();i++) {//ѭ���õ��ʲ�
						Equipment equipment =(Equipment) equiplist.get(i);
						List spceeqlist = this.getBaseDao().findWithQuery(Eqspec.class, "eqnum='"+equipment.getEqnum()+"'");//�õ��ʲ���������
						
						if (spceeqlist.size()<=0) {//���û�м��������������
							Eqspec eqspec = new Eqspec();
							eqspec.setEqnum(equipment.getEqnum());
							eqspec.setClassattr(classspec.getClassattr());
							eqspec.setClassid(classification.getClassid());
							eqspec.setStrvalue(classspec.getDefaultstrvalue());
							eqspec.setUnitid(classspec.getUnitid());
							eqspec.setIsmustbe(classspec.getIsmustbe());
							eqspec.setRemark(classspec.getRemark());
							eqspec.setChangeby(this.getUserInfo().getLabornum());
							eqspec.setChangedate(new Date());
							this.getBaseDao().saveObject(eqspec); // ���漼������
						}else{//����ִ��ڼ����������Ƚϼ�������ֵ�͵�λ�Ƿ���ͬ����ͬ����update
							for (int a = 0;a<spceeqlist.size();a++) {//ѭ���ʲ��µķ������������Ҫͬ���ļ��������Ƚϵ�λ�Ͳ���ֵ�����º���Ӽ�������
								Eqspec spec = (Eqspec) spceeqlist.get(a);
								String strvalue = spec.getStrvalue();//�õ�Ĭ������ֵ
								String unitid = spec.getUnitid();//�õ���λ
								//�ж�����ֵ�͵�λ�Ƿ���ͬ����ͬ������Ҫ���£��������
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='�ʲ�' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
								int specin = this.getBaseDao().selectCountByHql("select count(*) from Classspec c where c.classattr='"+unitid+"' and c.unitid='"+unitid+"' and c.classid='"+spec.getClassid()+"'");
								if (specin <= 0) {
									Eqspec eqspec = new Eqspec();
									eqspec.setEqnum(equipment.getEqnum());
									eqspec.setClassattr(classspec.getClassattr());
									eqspec.setClassid(classification.getClassid());
									eqspec.setStrvalue(classspec.getDefaultstrvalue());
									eqspec.setUnitid(classspec.getUnitid());
									eqspec.setIsmustbe(classspec.getIsmustbe());
									eqspec.setRemark(classspec.getRemark());
									eqspec.setChangeby(this.getUserInfo().getLabornum());
									eqspec.setChangedate(new Date());
									this.getBaseDao().saveObject(eqspec); // ���漼������
								}else {//Ӧ���Ǹ�����
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//���¶���
								}
							}
						}
						
					}
				}
				
			}
			
			// ������������ʷ��࣬��ִ������Ĵ���
			if ("����".equals(classtype)) {
				//List itemlist = this.getBaseDao().findWithQuery(Item.class, "classid = '"+classification.getClassid()+"'");
				if (itemlist.size()>0) {
					for (int m=0;m<itemlist.size();m++) {//ѭ������ 
						Item item = (Item) itemlist.get(m);
						List spceitemlist = this.getBaseDao().findWithQuery(Itemspec.class, "itemnum='"+item.getItemnum()+"'");//�õ����ʼ�������
						
						if (spceitemlist.size()<=0) {//���û�м��������������
							Itemspec itemspec = new Itemspec();
							itemspec.setClassattr(classspec.getClassattr());
							itemspec.setItemnum(item.getItemnum());
							itemspec.setClassid(classification.getClassid());
							itemspec.setStrvalue(classspec.getDefaultstrvalue());
							itemspec.setUnitid(classspec.getUnitid());
							itemspec.setIsmustbe(classspec.getIsmustbe());
							itemspec.setRemark(classspec.getRemark());
							itemspec.setChangeby(this.getUserInfo().getLabornum());
							itemspec.setChangedate(new Date());
							this.getBaseDao().saveObject(itemspec); // ���漼������
						}else{//����ִ��ڼ����������Ƚϼ�������ֵ�͵�λ�Ƿ���ͬ����ͬ����update
							for (int a = 0;a<spceitemlist.size();a++) {//ѭ�������µķ������������Ҫͬ���ļ��������Ƚϵ�λ�Ͳ���ֵ�����º���Ӽ�������
								Itemspec spec = (Itemspec) spceitemlist.get(a);
								String strvalue = spec.getStrvalue();//�õ�Ĭ������ֵ
								String unitid = spec.getUnitid();//�õ���λ
								String classattr = spec.getClassattr();
								//�ж�����ֵ�͵�λ�Ƿ���ͬ����ͬ������Ҫ���£��������
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='����' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
								int specin = this.getBaseDao().selectCountByHql("select count(*) from Classspec c where c.unitid='"+unitid+"' and c.classattr='"+classattr+"' and c.classid='"+spec.getClassid()+"'");
								if (specin <= 0) {
									Itemspec itspec = new Itemspec();
									itspec.setClassattr(classspec.getClassattr());
									itspec.setItemnum(item.getItemnum());
									itspec.setClassid(classification.getClassid());
									itspec.setStrvalue(classspec.getDefaultstrvalue());
									itspec.setUnitid(classspec.getUnitid());
									itspec.setIsmustbe(classspec.getIsmustbe());
									itspec.setRemark(classspec.getRemark());
									itspec.setChangeby(this.getUserInfo().getLabornum());
									itspec.setChangedate(new Date());
									this.getBaseDao().saveObject(itspec); // ���漼������
								}else {//Ӧ���Ǹ�����
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//���¶���
								}
							}
						}
					}
				}
				}
				
			
			// ��������ǹ��߷��࣬��ִ������Ĵ���
			if ("����".equals(classtype)) {
				//List toollist = this.getBaseDao().findWithQuery(Tool.class, "classid = '"+classification.getClassid()+"'");
				if (toollist.size()>0) {
					for (int n=0;n<toollist.size();n++) {
						Tool tool =(Tool) toollist.get(n);
						List tooleqlist = this.getBaseDao().findWithQuery(Toolspec.class, "toolnum='"+tool.getToolnum()+"'");//�õ��ʲ���������
						
						if (tooleqlist.size()<=0) {//���û�м��������������
							Toolspec toolspec = new Toolspec();
							toolspec.setClassattr(classspec.getClassattr());
							toolspec.setToolnum(tool.getToolnum());
							toolspec.setClassid(classification.getClassid());
							toolspec.setStrvalue(classspec.getDefaultstrvalue());
							toolspec.setUnitid(classspec.getUnitid());
							toolspec.setIsmustbe(classspec.getIsmustbe());
							toolspec.setRemark(classspec.getRemark());
							toolspec.setChangeby(this.getUserInfo().getLabornum());
							toolspec.setChangedate(new Date());
							this.getBaseDao().saveObject(toolspec); // ���漼������
						}else{//����ִ��ڼ����������Ƚϼ�������ֵ�͵�λ�Ƿ���ͬ����ͬ����update
							for (int a = 0;a<tooleqlist.size();a++) {//ѭ���ʲ��µķ������������Ҫͬ���ļ��������Ƚϵ�λ�Ͳ���ֵ�����º���Ӽ�������
								Toolspec spec = (Toolspec) tooleqlist.get(a);
								String strvalue = spec.getStrvalue();//�õ�Ĭ������ֵ
								String unitid = spec.getUnitid();//�õ���λ
								String classattr = spec.getClassattr();
								//�ж�����ֵ�͵�λ�Ƿ���ͬ����ͬ������Ҫ���£��������
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='����' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
								int specin = this.getBaseDao().selectCountByHql("select count(*) from Classspec c where c.unitid='"+unitid+"' and c.classattr='"+classattr+"' and c.classid='"+spec.getClassid()+"'");
								if (specin <= 0) {
									Toolspec tospec = new Toolspec();
									tospec.setClassattr(classspec.getClassattr());
									tospec.setToolnum(tool.getToolnum());
									tospec.setClassid(classification.getClassid());
									tospec.setStrvalue(classspec.getDefaultstrvalue());
									tospec.setUnitid(classspec.getUnitid());
									tospec.setIsmustbe(classspec.getIsmustbe());
									tospec.setRemark(classspec.getRemark());
									tospec.setChangeby(this.getUserInfo().getLabornum());
									tospec.setChangedate(new Date());
									this.getBaseDao().saveObject(tospec); // ���漼������
								}else {//Ӧ���Ǹ�����
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//���¶���
								}
							}
						}
					}
				}
				}
				
			//
		}
		
	}

}
