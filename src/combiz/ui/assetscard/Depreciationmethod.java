package combiz.ui.assetscard;


import java.math.BigDecimal;
import java.text.DecimalFormat;

import combiz.system.FieldAdapter;

import com.inbasis.zk.ui.Component;
import com.inbasis.zk.ui.WrongValueException;
import com.inbasis.zul.Doublebox;
import com.inbasis.zul.Messagebox;

public class Depreciationmethod extends FieldAdapter {

	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * ���
	 */
	public void init(Component component) {

	}

	/**
	 * 
	 * @TODO �������뿪�����ı���ʱ���Զ������ʲ��۾���Ӧ��ֵ
	 * @param component
	 * @throws Exception
	 * @��ΰ 2010-05-06   10:28
	 * 	 */
	public void validate(Component component) 
	throws Exception 
	{	
		Double cost = (Double) this.getValueByColname("cost");//ȡ�������ϵ�ԭֵ�ֶ�cost;
		Long expectedmonth = (Long) this.getValueByColname("expectedmonth");//ȡ�������ϵ�Ԥ��ʹ�õ��·��ֶ�expectedmonth;
		Long depreciationmonth = (Long) this.getValueByColname("depreciationmonth");//ȡ�������ϵ��Ѽ�ʹ�õ��·��ֶ�depreciationmonth;
		
		Double residualvalues = (Double) this.getValueByColname("residualvalues");//ȡ�������ϵĲ�ֵ���ֶ�residualvalues;
		Double accdepreciation = (Double) this.getValueByColname("accdepreciation");//ȡ�������ϵ��ۼ��۾����ֶ�accdepreciation;
		Double impairment = (Double) this.getValueByColname("impairment");//ȡ�������ϵļ�ֵ׼���ֶ�impairment;

		Double num = 0d;
		Double sum = 0d;
	

		if(residualvalues == null || residualvalues<=0) {
			Messagebox.show("��ֵ�ʲ���Ϊ�գ������룡");
			this.setValueByColname("residualvalues", 0D);
			return;		
		}

		if((cost!=null || cost==null)&& (residualvalues!=null || accdepreciation==null))
		{
			if (residualvalues==null)
			{
				residualvalues=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
			num = cost * residualvalues;  //�����ֵ��
			this.setValueByColname("residual", num);//����ֵ�������ݿ��residual�С�
		}




		if((cost!=null ||cost==null) && (accdepreciation!=null || accdepreciation==null))
		{
			if (accdepreciation==null)
			{
				accdepreciation=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
			num = cost - accdepreciation;  //�����ǰ��ֵ��
			this.setValueByColname("networth", num);//���ϼƸ�ֵ�����ݿ⾻ֵ�ֶΡ�
		}

		if((cost!=null ||cost==null) && (accdepreciation!=null||accdepreciation==null) 
				&& (impairment!=null || impairment==null) 
			)
		{
			if (accdepreciation==null)
			{
				accdepreciation=0d;
			}
			if (impairment==null)
			{
				impairment=0d;
			}
			if (cost==null)
			{
				cost=0d;
			}
					
			num = cost - accdepreciation-impairment;  //�����ǰ���
			this.setValueByColname("net", num);//���ϼƸ�ֵ�����ݿ⾻���ֶΡ�
				}
		if((cost!=null ||cost==null) && (expectedmonth!=null || expectedmonth==null)
				&& (depreciationmonth!=null || depreciationmonth==null) && (residualvalues!=null || accdepreciation==null))
		{
			
			if (cost==null)
			{
				cost=0d;
			}
			if (depreciationmonth==null)
			{
				depreciationmonth=0l;
			}	
			if (expectedmonth==null)
			{
				expectedmonth=48l;
			}	
			
			if (residualvalues==null)
			{
				residualvalues=0d;
			}
			
			num = (1-residualvalues)/(expectedmonth - depreciationmonth);  //���۾��ʣ�
         
			BigDecimal bg = new BigDecimal(num); //����һ��BG����
	        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //bg����ȡ2λ����f1��������Ϊdouble��
			this.setValueByColname("mthval", f1);//���ϼƸ�ֵ�����ݿ����۾����ֶΡ�
			sum=num*cost;
			this.setValueByColname("mthamount", sum);//���ϼƸ�ֵ�����ݿ����۾����ֶΡ�
					
		}
		
		

	}




	/**
	 * @throws Exception 
	 * 
	 */
	public void action(Component component) throws Exception {

	}

}
