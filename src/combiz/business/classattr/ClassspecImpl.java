package combiz.business.classattr;

import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * 在该类中写业务处理过程 作者：洪小林 日期：2006-12-17
 * 
 */
public class ClassspecImpl extends IBOBaseImpl implements ClassspecSrv {

	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Classspec))
			throw new Exception("要删除的对象传递不正确！");

		// 删除本身
		super.delete(obj);
		// 删除关联对象－父类方法
		// this.deleteAllChild(obj, "");
	}

	@Override
	public void save(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		Classspec classspec = (Classspec) arg0;
		if (classspec.getId() == null || classspec.getId().equals("")) {
			saveDownExtends(classspec);
		}
		super.save(arg0);
	}

	/**
	 * @TODO 继承父设备分类技术参数，由子调用
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 下午07:05:55
	 */
	public void saveUpExtends(Object arg0) throws Exception {
		Classification classification = (Classification) arg0;
		List classspecs = this.getBaseDao().findWithQuery(Classspec.class,
				"classid='" + classification.getParent() + "'");
		for (int i = 0; i < classspecs.size(); i++) {
			Classspec classspec = (Classspec) classspecs.get(i);

			Classspec classspec1 = new Classspec();
			classspec1.setClassattr(classspec.getClassattr());
			classspec1.setClassid(classification.getClassid());
			classspec1.setDefaultnumvalue(classspec.getDefaultnumvalue());
			classspec1.setDefaultstrvalue(classspec.getDefaultstrvalue());
			classspec1.setIsmustbe(classspec.getIsmustbe());
			classspec1.setRemark(classspec.getRemark());
			classspec1.setUnitid(classspec.getUnitid());

			super.save(classspec1);
		}
	}

	/**
	 * @TODO 向下继承父设备分类技术参数，由父调用
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 下午07:05:55
	 */
	public void saveDownExtends(Object arg0) throws Exception {
		Classspec classspec = (Classspec) arg0;
		List classifications = this.getBaseDao()
				.findWithQuery(Classification.class,
						"parent='" + classspec.getClassid() + "'");
		for (int i = 0; i < classifications.size(); i++) {
			Classification classification = (Classification) classifications
					.get(i);

			Classspec classspec1 = new Classspec();
			classspec1.setClassattr(classspec.getClassattr());
			classspec1.setClassid(classification.getClassid());
			classspec1.setDefaultnumvalue(classspec.getDefaultnumvalue());
			classspec1.setDefaultstrvalue(classspec.getDefaultstrvalue());
			classspec1.setIsmustbe(classspec.getIsmustbe());
			classspec1.setRemark(classspec.getRemark());
			classspec1.setUnitid(classspec.getUnitid());
			// 已经存在不用再插入
			if (this.findWithQuery(
					"classid='" + classspec1.getClassid() + "' and classattr='"
							+ classspec1.getClassattr() + "'").size() > 0) {
				return;
			}

			super.save(classspec1);
			// 递归
			saveDownExtends(classspec1);
		}
	}

	// ///////////////////业务方法//////////////////////////////////

}
