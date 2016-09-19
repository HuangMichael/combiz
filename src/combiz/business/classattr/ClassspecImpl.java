package combiz.business.classattr;

import combiz.domain.classattr.Classification;
import combiz.domain.classattr.Classspec;
import combiz.system.IBOBaseImpl;

import java.util.List;

/**
 * �ڸ�����дҵ������� ���ߣ���С�� ���ڣ�2006-12-17
 * 
 */
public class ClassspecImpl extends IBOBaseImpl implements ClassspecSrv {

	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ���� ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) throws Exception {
		if (!(obj instanceof Classspec))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");

		// ɾ������
		super.delete(obj);
		// ɾ���������󣭸��෽��
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
	 * @TODO �̳и��豸���༼�����������ӵ���
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 ����07:05:55
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
	 * @TODO ���¼̳и��豸���༼���������ɸ�����
	 * @param arg0
	 * @throws Exception
	 *             yuanjq 2007-8-14 ����07:05:55
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
			// �Ѿ����ڲ����ٲ���
			if (this.findWithQuery(
					"classid='" + classspec1.getClassid() + "' and classattr='"
							+ classspec1.getClassattr() + "'").size() > 0) {
				return;
			}

			super.save(classspec1);
			// �ݹ�
			saveDownExtends(classspec1);
		}
	}

	// ///////////////////ҵ�񷽷�//////////////////////////////////

}
