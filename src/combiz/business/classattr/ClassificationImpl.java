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
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class ClassificationImpl extends IBOBaseImpl 
implements ClassificationSrv 
{

	private ClassspecSrv classspecSrv;

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception
	{
		Classification classification = (Classification) obj;

		// 修改父级分类
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
					parent.setHaschild("否");
					this.update(parent);
				}
			}
		}


		// 删除本身
		super.delete(obj);
		// 删除技术参数－参数：父对象，关系名
		this.deleteAllChild(classification, "assetclassspecTable");
		// 删除祖先表数据
		this.deleteAllChild(classification, "classance");
		
		//??????
		// 删除设备祖先表数据
		//this.deleteAllChild(classification, "eqclassTable");
		// 删除工具祖先表数据
		//this.deleteAllChild(classification, "toolclass");
		// 删除该分类对应的故障代码
		//this.deleteAllChild(classification, "failclass");
	}

	
	/**
	 * 子集的数量
	 * 洪小林  Nov 26, 2009
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
	 * 子集的数量
	 * 洪小林  Nov 26, 2009
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
	 * 是否有子集
	 * 洪小林  Nov 26, 2009
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
	 * 保存数据
	 */
	/* 
	 * 功能：
	 * 作者：李阳
	 * 日期：2008-3-20上午09:24:58
	 */
	@Override
	public void save(Object obj)
	throws Exception 
	{
		Classification classification = (Classification) obj;
		if (classification.getId() == null) // 新建
		{
			// 更新父级记录
			Classification parent = this.getParent(classification);
			if (parent != null) 
			{
				parent.setHaschild("是");
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

			// 插入classance祖先表数据
			// 产生祖先表数据
			Classance classance = new Classance();
			classance.setAncestor(classification.getClassid());
			classance.setClassid(classification.getClassid());
			classance.setClasstype(classification.getClasstype());
			super.save(classance);
			// 循环获取父级，产生祖先表数据
			Classification classc = this.getParent(classification);
			while (classc != null) {
				classance = new Classance();
				classance.setClassid(classification.getClassid());
				classance.setAncestor(classc.getClassid());
				classance.setClasstype(classification.getClasstype());
				super.save(classance);

				classc = this.getParent(classc);
			}

			// 保存自己
			super.save(obj);

			// 继承父设备分类技术参数
			ClassspecSrv classspec = (ClassspecSrv)IBOSrvUtil.getSrv("classspec");
			classspec.saveUpExtends(classification);
		} else {
			super.save(classification);
		}
		
		
	}

	/**
	 * 获取父级对象
	 * 
	 * @param classification
	 * @return 作者：洪小林 日期：2007-6-27
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
	 * 同步技术参数 
	 * @param classification
	 * @return 作者：李建红 日期：2009-9-9
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
			throw new Exception ("传递的对象不正确！");
		Classification classification = (Classification) obj;
		String classtype = classification.getClasstype();
		//得到该分类下的技术参数列表
		List classapcelist = this.getBaseDao().findWithQuery(Classspec.class, " classid='"+classification.getClassid()+"'");
		
		List equiplist = null;
		if ("资产".equals(classtype)) {
			equiplist = this.getBaseDao().findWithQuery(Equipment.class, "classid = '"+classification.getClassid()+"'");
		}
		List itemlist = null;
		if ("物资".equals(classtype)) {
			itemlist = this.getBaseDao().findWithQuery(Item.class, "classid = '"+classification.getClassid()+"'");
		}
		List toollist = null;
		if ("工具".equals(classtype)) {
			toollist = this.getBaseDao().findWithQuery(Tool.class, "classid = '"+classification.getClassid()+"'");
		}
		
		
		for (int t=0;t<classapcelist.size();t++) {//循环分类下的技术参数
			Classspec classspec =(Classspec) classapcelist.get(t);
			// 如果分类是资产分类，则执行下面的代码
			if ("资产".equals(classtype)) {
				//List equiplist = this.getBaseDao().findWithQuery(Equipment.class, "classid = '"+classification.getClassid()+"'");
				if (equiplist.size()>0) {
					for (int i=0;i<equiplist.size();i++) {//循环得到资产
						Equipment equipment =(Equipment) equiplist.get(i);
						List spceeqlist = this.getBaseDao().findWithQuery(Eqspec.class, "eqnum='"+equipment.getEqnum()+"'");//得到资产技术参数
						
						if (spceeqlist.size()<=0) {//如果没有技术参数，则添加
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
							this.getBaseDao().saveObject(eqspec); // 保存技术参数
						}else{//如果现存在技术参数，比较技术参数值和单位是否相同，不同，则update
							for (int a = 0;a<spceeqlist.size();a++) {//循环资产下的分类参数，与需要同步的技术参数比较单位和参数值，更新和添加技术参数
								Eqspec spec = (Eqspec) spceeqlist.get(a);
								String strvalue = spec.getStrvalue();//得到默认属性值
								String unitid = spec.getUnitid();//得到单位
								//判断属性值和单位是否相同，相同，则不需要更新，否则更新
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='资产' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
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
									this.getBaseDao().saveObject(eqspec); // 保存技术参数
								}else {//应该是更新了
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//更新对象
								}
							}
						}
						
					}
				}
				
			}
			
			// 如果分类是物资分类，则执行下面的代码
			if ("物资".equals(classtype)) {
				//List itemlist = this.getBaseDao().findWithQuery(Item.class, "classid = '"+classification.getClassid()+"'");
				if (itemlist.size()>0) {
					for (int m=0;m<itemlist.size();m++) {//循环物资 
						Item item = (Item) itemlist.get(m);
						List spceitemlist = this.getBaseDao().findWithQuery(Itemspec.class, "itemnum='"+item.getItemnum()+"'");//得到物资技术参数
						
						if (spceitemlist.size()<=0) {//如果没有技术参数，则添加
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
							this.getBaseDao().saveObject(itemspec); // 保存技术参数
						}else{//如果现存在技术参数，比较技术参数值和单位是否相同，不同，则update
							for (int a = 0;a<spceitemlist.size();a++) {//循环物资下的分类参数，与需要同步的技术参数比较单位和参数值，更新和添加技术参数
								Itemspec spec = (Itemspec) spceitemlist.get(a);
								String strvalue = spec.getStrvalue();//得到默认属性值
								String unitid = spec.getUnitid();//得到单位
								String classattr = spec.getClassattr();
								//判断属性值和单位是否相同，相同，则不需要更新，否则更新
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='物资' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
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
									this.getBaseDao().saveObject(itspec); // 保存技术参数
								}else {//应该是更新了
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//更新对象
								}
							}
						}
					}
				}
				}
				
			
			// 如果分类是工具分类，则执行下面的代码
			if ("工具".equals(classtype)) {
				//List toollist = this.getBaseDao().findWithQuery(Tool.class, "classid = '"+classification.getClassid()+"'");
				if (toollist.size()>0) {
					for (int n=0;n<toollist.size();n++) {
						Tool tool =(Tool) toollist.get(n);
						List tooleqlist = this.getBaseDao().findWithQuery(Toolspec.class, "toolnum='"+tool.getToolnum()+"'");//得到资产技术参数
						
						if (tooleqlist.size()<=0) {//如果没有技术参数，则添加
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
							this.getBaseDao().saveObject(toolspec); // 保存技术参数
						}else{//如果现存在技术参数，比较技术参数值和单位是否相同，不同，则update
							for (int a = 0;a<tooleqlist.size();a++) {//循环资产下的分类参数，与需要同步的技术参数比较单位和参数值，更新和添加技术参数
								Toolspec spec = (Toolspec) tooleqlist.get(a);
								String strvalue = spec.getStrvalue();//得到默认属性值
								String unitid = spec.getUnitid();//得到单位
								String classattr = spec.getClassattr();
								//判断属性值和单位是否相同，相同，则不需要更新，否则更新
//								int specin = this.getBaseDao().selectCountByHql("select * from Classspec c where c.classtype='工具' and unitid='"+unitid+"' and defaultstrvalue='"+strvalue+"' and classid='"+spec.getClassid()+"'");
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
									this.getBaseDao().saveObject(tospec); // 保存技术参数
								}else {//应该是更新了
									spec.setChangeby(this.getUserInfo().getLabornum());
									spec.setClassattr(classspec.getClassattr());
									spec.setChangedate(new Date());
									spec.setStrvalue(classspec.getDefaultstrvalue());
									spec.setUnitid(classspec.getUnitid());
									spec.setIsmustbe(classspec.getIsmustbe());
									spec.setRemark(classspec.getRemark());
									this.getBaseDao().updateObject(spec);//更新对象
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
