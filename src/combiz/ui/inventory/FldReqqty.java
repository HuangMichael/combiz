package combiz.ui.inventory;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;

public class FldReqqty extends FieldAdapter {

	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ� ���
	 */
	public void init(Component component) {

	}
	/* 
	 * ���ܣ�
	 * ���ߣ����
	 * ���ڣ�Oct 8, 200810:24:32 AM
	 */
	public void action(Component com) throws Exception {
		// �õ���������
		Double quantity = (Double) this.getValueByColname("quantity");
		if (quantity < 0 ) {
			this.setValueByColname("quantity", 0D);
			throw new Exception ("������������С��0 ��");
		}
		if (quantity == null ) {
			this.setValueByColname("quantity", 0D);
		}
		Double reqqty = (Double) this.getValueByColname("reqqty");
		if(reqqty==null)
		{
			reqqty = 0d;
		}
		if (reqqty < 0 ) {
			this.setValueByColname("reqqty", 0D);
			throw new Exception ("������������С��0 ��");
		}
		if (reqqty != null ) {
			if ((reqqty-quantity)<0) {
				throw new Exception("�����������ܴ����������������ʵ!");
			}
		}
		
		

	}

	/*
	 * ���ܣ� ���ߣ���� ���ڣ�Sep 25, 20086:22:54 PM
	 */
	public void validate(Component component) {

	}

}
