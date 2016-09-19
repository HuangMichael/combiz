package combiz.ui.inventory;
 
import combiz.business.inventory.MatreqSrv;
import combiz.domain.corp.Department;
import combiz.domain.inventory.Invreserve;
import combiz.domain.inventory.Matreq;
import combiz.domain.pr.Pr;
import combiz.domain.tool.Toolreserve;
import combiz.domain.workorder.Wpmaterial;
import combiz.domain.workorder.Wptool;
import combiz.system.ui.InfWindow;
import combiz.system.ui.ListWindow;
import combiz.system.ui.MainWindow;
import combiz.system.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.inbasis.zul.Messagebox;

public class BorrowequWindow extends MainWindow
implements InfWindow
{
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public BorrowequWindow()
	{
		super();
	}
	
	public void save() throws Exception {
		Matreq mr = (Matreq)this.getMainObject();
		Date usedate = mr.getUsedate();//�黹����
		Date requireddate = mr.getRequireddate();//��������
		String idr1 = this.getIntoDate(usedate);
		String idr2 = this.getIntoDate(requireddate);
		if(requireddate != null && usedate !=null)
		{
			if(requireddate.after(usedate) && (!idr1.equals(idr2)))
			{
				Messagebox.show("�黹����Ӧ���ڽ�������,���ʵ��");				
				return;
			}
		}
		else
		{
			Messagebox.show("������������ں͹黹���ڣ�");				
			return;
		}
		super.save();
	}
	
	public String getIntoDate(Date date) throws InterruptedException 
	{
		SimpleDateFormat ntime = new SimpleDateFormat();
		ntime.applyPattern("yyyy-MM-dd");
		if(date != null){
		String date1=ntime.format(date.getTime());		
		return date1;
		}else{
			return null;
		}
		
	}
	/**
	 * ������¼
	 * @throws Exception
	 * ���ߣ���С�� ���ڣ�2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * ������ʼֵ
		 **********************************/
		Matreq newobj = new Matreq();
		String matreqnum = this.genAutokey("matreqnum");
		if(Util.isNotNull(matreqnum)){
			matreqnum = matreqnum.replace("MR", "BQ");
		}
		newobj.setMatreqnum(matreqnum);
		newobj.setRequestdate(new Date());
		newobj.setStatus("����δ����");
		newobj.setStatusdate(new Date());
		newobj.setReqtype("�豸��������");
		newobj.setReqdept(this.getLaborInfo().getDeptnum());
		newobj.setRequestby(this.getUserInfo().getLabornum());
		newobj.setRequireddate(new Date());		
		mainObject = newobj;
		return true;
	}
	/******************����Ԥ����Ϣ*************ljh***************/
	public void createinvr() throws Exception
	{
		if (this.selectedTabid.equals("list"))
		{
			Messagebox.show("������ѡ��һ����¼��");
			return;
		}
		if (this.objStatus == this.MODIFIED || this.getObjStatus() == this.ADDED)
		{
			Messagebox.show("��������Ԥ��ǰ�������ݣ�");
			return;
		}
		Matreq matreq = (Matreq) this.getMainObject();
		/**
		 *�ж��Ƿ����ظ�����Ԥ�����Ƿ���Ҫ����Ԥ��
		 * һ������ֻ������һ��Ԥ�����ж�״̬����Ϊ��׼״̬�Ĳ�������Ԥ��
		 */
		String matreqnum = matreq.getMatreqnum();
		String status = matreq.getStatus();
		if (!(status.equals("����׼"))) {
			throw new Exception ("�ý�������δ����׼����������Ԥ����");
		}
		List toolreservelist = this.getMainSrv().getBaseDao().findWithQuery(Toolreserve.class, "reqnum = '"+matreqnum+"'");
		if (toolreservelist.size()>0) {
			throw new Exception ("�����뵥�Ѿ�����Ԥ���������ظ����ɣ�");
		}
		
		List invreservelist = this.getMainSrv().getBaseDao().findWithQuery(Invreserve.class, "reqnum = '"+matreqnum+"'");
		if (invreservelist.size()>0) {
			throw new Exception ("�����뵥�Ѿ�����Ԥ���������ظ����ɣ�");
		}
		
		((MatreqSrv)this.getMainSrv()).createinvr(matreq);
		Messagebox.show("Ԥ��������ϣ�");
		this.refreshData();
	}

	/* 
	 * ���ܣ������Ӵ���ֻ��
	 * ���ߣ�����
	 * ���ڣ�2008-11-28����10:56:15
	 */
	@Override
	public void initData() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq)this.getMainObject();
		ListWindow listwnd =(ListWindow) this.getFellow("wpmaterial");
		if (matreq.getStatus()!=null && matreq.getStatus().equals("����׼")) 
		{
			listwnd.setReadonlyList(true);
		}
		else
		{
			listwnd.setReadonlyList(false);
		}
		super.initData();
	}


	@Override
	public void workflow() throws Exception {
		// TODO Auto-generated method stub
		Matreq matreq = (Matreq) this.getMainObject();
		String matreqnum = matreq.getMatreqnum();
		String exc = "Y";//��ʶ�������Ƿ����¼����ߣ���Ϊ��Y����ʱ�������ߣ����򣬷��ء�
		List wpmatlist = this.getMainSrv().getBaseDao().findWithQuery(Wpmaterial.class, "matreqnum = '"+matreqnum+"'");
		if(wpmatlist.size()>0)
		{
			if(matreq.getStatus().equals("����δ����"))
			{
				for(int i=0;i<wpmatlist.size();i++)
				{
					Wpmaterial wpmat = (Wpmaterial) wpmatlist.get(i);
					/****************�Ƿ����ɲɹ�����***********************/
					String sql = "select sum(inv.curbal) from Invstock inv where inv.itemnum = '"+wpmat.getItemnum()+"' and warehouse ='"+wpmat.getWarehouse()+"'";
					//���������
					Double sumcurbal =(Double) this.getMainSrv().getBaseDao().selectSumByHql(sql);
					//Ԥ������
					Double reserveqty = (Double) this.getMainSrv().getBaseDao().selectSumByHql("select sum(t.reservedqty) from Invreserve t where t.reqnum <> '"+wpmat.getMatreqnum()+"' and t.itemnum = '"+wpmat.getItemnum()+"' and t.warehouse = '"+wpmat.getWarehouse()+"' and t.ponum is null and t.polinenum is null");
					
					if(sumcurbal == null)
					{
						sumcurbal = 0d;
					}
					if(reserveqty == null)
					{
						reserveqty = 0d;
					}
					//��Ҫ�ɹ�����
					Double needorderqty = sumcurbal-reserveqty-wpmat.getItemqty();
					if (needorderqty<0) 
					{
						int rtn = Messagebox.show("�ɹ����Ϊ:'"+wpmat.getItemnum()+"'���ʲ�������������㣬ȷ�����͹�������","��ȷ���Ƿ��͹�������",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION);
						if(rtn == Messagebox.NO)
						{
							exc = "N";//���̾Ͳ����ͣ����ص��û��޸Ľ�����������
							break;
						}
							
					}
				}
				if(exc.equals("Y"))
				{
					super.workflow();
				}
				else
				{
					return;
				}
			}
			else
			{
				super.workflow();
			}
			
			
		}
		else
		{
			throw new Exception("����������Ϊ�գ����ܷ��͹���������ȷ�ϣ�");
		}
	}
	
	
	
}
