package combiz.ui.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import combiz.domain.inventory.Invrectrans;
import combiz.domain.inventory.Item;
import combiz.system.FieldAdapter;
import combiz.system.ui.EditWindow;
import combiz.system.ui.ListWindow;
import combiz.system.util.Util;

import com.inbasis.zk.ui.Component;
import com.inbasis.zul.Messagebox;

/**
 * @author ���� E-mail:superyang4208731@yahoo.com.cn
 * 1:37:20 PM  Nov 2, 2008 
 * ���ܣ��ڽ�����ת����ʱ���ֶ�����Ϊ�����������֤������ʲ�����Ƿ����ʲ��д��ڣ�������ڣ���ʾ���������ʲ���š�
 * �����invrectrans
 * �����ֶΣ�eqnum
 */
public class FldReceqnum extends FieldAdapter
{	
	
	/**
	 * ��ʼ���ֶ����ݵ�ʱ����ø÷������÷��������ֶ�����ֵ�����ú󣨰󶨺󣩵��õġ�
	 * 
	 */
/*	public void init(Component component)
	throws Exception 
	{
		String itemnum = (String) this.getValueByColname("itemnum");
		if(Util.isNotNull(itemnum))
		{
			List itemlist = this.getMainSrv().getBaseDao().findWithQuery(Item.class, "itemnum='"+itemnum+"'");
			if(itemlist.size()>0)
			{
				Item item = (Item) itemlist.get(0);
				String rotating = item.getRotating();
				if(Util.isNotNull(rotating) && rotating.equals("��"))
				{
					this.setRequired(component);
					this.setNoReadonly(component);
				}
				else
				{
					this.setNoRequired(component);
					this.setReadonly(component);
				}
			}
			
		}
		


	}
*/
	
	/**
	 * �ֶ�������ƿ�����ø÷�����
	 * �������ø÷������������ݵĺϷ���Ч�顣
	 */
	public void validate(Component component) 
	throws Exception 
	{
		
	}

	/**
	 * �������ܼ�
	 */
	public void action(Component component)
	throws Exception 
	{
		String eqnum = (String) this.getValueByColname("eqnum");
		if(Util.isNotNull(eqnum))
		{
			Invrectrans invrec = (Invrectrans) this.getMainObject();
			int count =0;
			if(invrec.getId()== null)
			{
				count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment t where t.eqnum = '"+eqnum+"'");
			}
			else
			{
				count = this.getMainSrv().getBaseDao().selectCountByHql("select count(*) from Equipment t where t.eqnum = '"+eqnum+"' and id<>'"+invrec.getId()+"'");
			}
			
			if(count >0)
			{
				this.setValueByColname("eqnum" ,"");
				Messagebox.show("�Ѿ������ʲ����Ϊ��'"+eqnum+"'���ʲ�������ʲ����½��б�ţ�");
			}
		}
		
//		EditWindow editwind = (EditWindow) this.getEditWnd(component);
//		ListWindow listWnd = editwind.getListWnd();
//		List modfifylist = listWnd.getModifiedObjectList();
//		String str = null;
//		String[] s = null; 
//		Set set = new HashSet();
//
//		for(int j=0;j<modfifylist.size();j++)
//		{
//			Invrectrans invrec = (Invrectrans) modfifylist.get(j);
//			String neweqnum = invrec.getEqnum();
//			if(Util.isNull(str))
//			{
//				str = neweqnum;
//			}
//			else
//			{
//				str = str + "," + neweqnum;
//			}
//		}
//		if(Util.isNotNull(str))
//		{
//			s=str.split(","); 
//			for(int i = 0;i<s.length;i++){ 
//				set.add(s[i]); 
//			} 
//			if(set.size() < s.length) //���ظ�   //		if(set.size() == s.length) //���ظ� 
//			{
//				throw new Exception("���ظ��ı�ţ�");
//			}
//		}
		
	}

	@Override
	public void init(Component arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
