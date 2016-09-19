package combiz.business.tool;

import combiz.domain.tool.Tool;
import combiz.domain.tool.Toolissue;
import combiz.domain.tool.Toolreserve;
import combiz.domain.tool.Tooltrans;
import combiz.system.IBOBaseImpl;

import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;
/**
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class ToolImpl extends IBOBaseImpl
implements ToolSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Tool))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��		
		this.deleteAllChild(obj, "toolTable");
	}	
/////////////////////ҵ�񷽷�//////////////////////////////////	
	/**
	 * @author ljh
	 * @���ܣ��ڹ��߷���Ӧ�ó����е���"���Ź���"��ť�󣬴������¼�����tooltrans��������ݣ�
	 * @throws Exception
	 * @2008-03-18����02:12:39
	 */
	public void geninvuse(List list) throws Exception {
		Toolreserve toolreserve = null;
		for(int i = 0;i<list.size();i++){
		toolreserve =(Toolreserve)list.get(i);
			if (!(toolreserve instanceof Toolreserve))
				throw new Exception("���󴫵ݲ���ȷ1!");								
				/***************�жϹ����Ƿ��Ѿ�ȫ���������*******************************/
				String sql = "select sum(trans.toolqty) from Tooltrans trans where trans.sendnum = '"+toolreserve.getId()+"' and trans.transtype='����'";
				Long setcnt = (Long) this.getBaseDao().selectSumByHql(sql);
				if (setcnt == null)
				{
					setcnt = 0L;
				}
				System.out.println("=��������:="+toolreserve.getSendcount());
				if(toolreserve.getSendcount() <0)
				{
					throw new Exception("������������Ϊ��ֵ�����ʵ��");
				}
				if (toolreserve.getReservedqty() < (toolreserve.getSendcount()))
				{	
					throw new Exception("���߷����������ܴ���Ԥ�����������ʵ��");
				}					
				/***************�õ�ͳ�Ʊ��е�����******************/
				List toolissuelist = this.getBaseDao().findWithQuery(Toolissue.class, "toolnum = '"+toolreserve.getToolnum()+"' and reqlabor = '"+toolreserve.getReqlabor()+"'");
				Toolissue toolissue=null;
				if (toolissuelist.size()>0)
				{
					toolissue = (Toolissue) toolissuelist.get(0);
					toolissue.setToolqty(toolissue.getToolqty()+toolreserve.getSendcount());
					this.getBaseDao().updateObject(toolissue);
				}else {
					toolissue = new Toolissue();
					toolissue.setReqlabor(toolreserve.getReqlabor());
					toolissue.setToolnum(toolreserve.getToolnum());
					toolissue.setToolqty(toolreserve.getSendcount());
					this.getBaseDao().saveObject(toolissue);
				}				
				Tooltrans tooltrans = new Tooltrans();				
				List toollist = this.getBaseDao().findWithQuery(Tool.class, "toolnum = '"+toolreserve.getToolnum()+"'");
				Tool tool = (Tool) toollist.get(0);
				
				Long toolqty = tool.getQty();//��������
				Long qty = toolreserve.getReservedqty();
				Long count = toolreserve.getSendcount();//����Ĺ��ߴ���������
				
				tooltrans.setLinecost(toolreserve.getToolhrs());
				tooltrans.setToolhrs(toolreserve.getToolhrs());
				tooltrans.setUserdept(toolreserve.getUserdept());
				tooltrans.setEnterby(this.getLaborInfo().getLaborname());
				tooltrans.setEnterdate(new Date());
				tooltrans.setOutside(tool.getOutside());
				tooltrans.setReqlabor(toolreserve.getReqlabor());
				tooltrans.setToolnum(toolreserve.getToolnum());
				if (count == null || count == 0)
				{
					tooltrans.setToolqty(0L);
				}else {
					tooltrans.setToolqty(count);
				}
				tooltrans.setToolrate(tool.getToolrate());
				tooltrans.setTransdate(new Date());
				tooltrans.setTransid(toolreserve.getToolnum());//���ױ��
				tooltrans.setTranstype("����");
				tooltrans.setSendnum(toolreserve.getId());
				tooltrans.setWonum(toolreserve.getReqnum());
				
				super.save(tooltrans);
											
				/*******************���²ֿ�Ĺ�������******************************/
				if ((toolqty - count) >= 0) 
				{
					tool.setQty(toolqty - count);
					this.getBaseDao().updateObject(tool);
					
					/*****************���¹���Ԥ��������********************************************/
					if ((qty - count) > 0)
					{
						toolreserve.setReservedqty(qty - count);
						this.getBaseDao().updateObject(toolreserve);
					}else if ((qty - count)==0){
						this.getBaseDao().deleteObject(toolreserve);
					}else{
						throw new  Exception("���������������飡");
						//return;
					}
					
				}else {
					throw new  Exception("�ֿ��й����������㣬��ִ�вɹ���ȴ��黹���ٽ��!");
					//return;
				}		
		Messagebox.show("��ɷ���!");
		}
	}
	
//	 ///////////////////ҵ�񷽷�////////�ڽ��ù��ߵ�ʱ����Ķ���//////////////////////////

	public void genreturn(List list) throws Exception {		
		System.out.println(list+"------------------"+list.size());
		Toolissue toolissue = null;
		Long getcount = 0L;
			for(int i = 0;i<list.size();i++){
				toolissue =(Toolissue)list.get(i);
			
			if (!(toolissue instanceof Toolissue))
				throw new  Exception("���󴫵ݲ���ȷ2!");	
			//�õ��û�ʵ�ʹ黹������
			getcount = toolissue.getSendcount();
			if (getcount==null)
			{
				getcount = 0L;
			}else if (getcount == 0){
				throw new  Exception("�黹��������Ϊ��!");
			}
			}
		
		//�õ���Ӧ�Ľ��ü�¼ͳ��
		Long setcnt = (Long) this.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='����'");
		Long setcount = (Long) this.getBaseDao().selectSumByHql("select sum(t.toolqty) from Tooltrans t where toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='�黹'");
		if (setcnt == null)
		{
			setcnt = 0L;
		}
//		�õ���Ӧ�Ĺ黹��¼ͳ��
		if (setcount == null)
		{
			setcount = 0L;
		}
		
		List translist = this.getBaseDao().findWithQuery(Tooltrans.class, " toolnum = '"+toolissue.getToolnum()+"' and reqlabor = '"+toolissue.getReqlabor()+"' and transtype='����'");
		Tooltrans tooltrans = null;
		if (translist.size()>0)
		{
			tooltrans = (Tooltrans) translist.get(0);
		}
		
		Long toolqty = toolissue.getToolqty();//�õ�ͳ�Ƶ���黹����
		System.out.print("�õ�ͳ�Ƶ���黹����"+toolqty);

		System.out.print("ʵ�ʹ黹����"+getcount);
		if (getcount == null)
		{
			getcount = 0L;
		}
		if (toolqty == null)
		{
			toolqty = 0L;
		}
		if (setcnt == setcount)
		{
			throw new  Exception("�ù��߼�¼�Ѿ��黹��ϣ�");
		}else if (setcnt < getcount || toolqty <getcount )
		{
			throw new  Exception("�黹�������ܴ��ڽ�����������ʵ��");
		}
		
		List toollist = this.getBaseDao().findWithQuery(Tool.class, "toolnum = '"+tooltrans.getToolnum()+"'");
		Tool tool = (Tool) toollist.get(0);
		
		Long count = tool.getQty();	
		Tooltrans trans = new Tooltrans();
			trans.setTransid(tooltrans.getToolnum());
			trans.setToolqty(getcount);
			trans.setEnterby(this.getLaborInfo().getLaborname());
			trans.setEnterdate(new Date());
			trans.setLinecost(tooltrans.getLinecost());
			trans.setOutside(tooltrans.getOutside());
			trans.setReqlabor(tooltrans.getReqlabor());
			//trans.setSendnum(tooltrans.getId());	//����������id
			trans.setToolhrs(tooltrans.getToolhrs());
			trans.setToolnum(tooltrans.getToolnum());
			trans.setToolrate(tooltrans.getToolrate());
			trans.setTransdate(new Date());
			trans.setTransid(tooltrans.getToolnum());
			trans.setTranstype("�黹");
			trans.setUserdept(tooltrans.getUserdept());
			trans.setWonum(tooltrans.getWonum());
			
			this.getBaseDao().saveObject(trans);
			
			//���¹�������
			tool.setQty(count + getcount);
			this.getBaseDao().updateObject(tool);
			
			//���¹���ͳ�Ʊ��¼
			if ((toolissue.getToolqty() - getcount)==0)
			{
				this.getBaseDao().deleteObject(toolissue);
			}else{
			toolissue.setToolqty(toolissue.getToolqty()- getcount);
			this.getBaseDao().updateObject(toolissue);
			}
	}	
}


