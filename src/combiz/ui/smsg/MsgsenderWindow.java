package combiz.ui.smsg;
 
import combiz.business.smsg.MsgsenderSrv;
import combiz.domain.smsg.Msgsender;
import combiz.system.ui.InfWindow;
import combiz.system.ui.MainWindow;
import combiz.system.ui.common.Attachment;

import java.util.Date;

import com.inbasis.zul.Messagebox;


public class MsgsenderWindow extends MainWindow
implements InfWindow
{
	
	
	///////////////////////////////////������////////////////////////////////////////////////
	/**
	 * ���캯������ʼ������
	 * 
	 * ���ߣ���С�� ���ڣ�2006-12-16
	 */
	public MsgsenderWindow()
	{
		super();
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
		Msgsender newobj = new Msgsender();
		newobj.setIssue("��");
		newobj.setSddate(new Date());
		newobj.setSender(this.getLaborInfo().getLabornum());
		mainObject = newobj;
		return true;
	}

	@Override
	public void initData() 
	throws Exception 
	{
		super.initData();
		
		Msgsender msgsender = (Msgsender)this.getMainObject();
		Attachment attachment = (Attachment) this.getFellow("attachment");
		if(msgsender.getIssue()!=null && msgsender.getIssue().equals("��"))
			attachment.setReadonly(true);
		else
			attachment.setReadonly(false);
	}
	

	@Override
	public void delete() 
	throws Exception 
	{
		Msgsender msgsender = (Msgsender)this.getMainObject();
		if(msgsender.getSendtype().equals("����"))
			if(msgsender.getIssue().equals("��"))
			{
				Messagebox.show("����ȡ�����淢���ٽ���ɾ��������");
				return;
			}
		
		super.delete();
	}



	/**
	 * ��������
	 * by ��Ⱥ��
	 */
	@Override
	public void save()
	throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		
		if(msgsender.getTitle()==null||msgsender.getTitle().equals(""))
		{
			Messagebox.show("��ѡ����Ϣ���⣡","�������",Messagebox.YES,Messagebox.INFORMATION);
			return;
		}
		else if(msgsender.getSendtype()==null||msgsender.getSendtype().equals(""))
		{
			Messagebox.show("��ѡ����Ϣ�������ͣ�","�������",Messagebox.YES,Messagebox.INFORMATION);
			return;
		}
		else if(msgsender.getSendtype().equals("˽��"))
		{
			if(msgsender.getRectype()==null||msgsender.getRectype().equals(""))
			{
				Messagebox.show("��ѡ����Ϣ��Ϣ�������ͣ�","�������",Messagebox.YES,Messagebox.INFORMATION);
				return;
			}
			else if(msgsender.getRectype().equals("����"))
			{
				if(msgsender.getReclabor()==null||msgsender.getReclabor().equals(""))
				{
					Messagebox.show("��ѡ����Ϣ��������Ϊ���ˣ���ѡ�����������ˣ�","�������",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
				
			}else if(msgsender.getRectype().equals("����")){
				if(msgsender.getRecdept()==null||msgsender.getRecdept().equals(""))
				{
					Messagebox.show("��ѡ����Ϣ��������Ϊ���ţ���ѡ���������ղ��ţ�","�������",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
			}else if(msgsender.getRectype().equals("��Ա��"))
			{
				if(msgsender.getReclaborgrp()==null||msgsender.getReclaborgrp().equals(""))
				{
					Messagebox.show("��ѡ����Ϣ��������Ϊ��Ա�飬��ѡ���������Ա�飡","�������",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
			}
		}
		
		super.save();
	}
	
	/**
	 * ��������
	 * by ��Ⱥ��
	 */
	public void pubboard() throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ����ѡ�񷢲����棡");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("��������ǰ�����ȱ������ݣ�");
			return;
		}
/*		if(msgsender.getSendtype().equals("˽��")){
			Messagebox.show("����Ϣ���ǹ�����Ϣ.", "��ʾ", Messagebox.OK, Messagebox.ERROR);
			return;
		}*/
		if(msgsender.getIssue().equals("��")){
			Messagebox.show("�ù����Ѿ����ڷ���״̬.", "��ʾ", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		((MsgsenderSrv)this.getMainSrv()).pubboard(this.getMainObject());
		this.refreshData();
		Messagebox.show("��������ɹ�.", "��ʾ", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	/**
	 * ȡ������
	 * by ��Ⱥ��
	 */
	public void cancelpub() throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("��ѡ��һ������¼��Ȼ����ѡ��ȡ���������棡");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("ȡ����������ǰ�����ȱ������ݣ�");
			return;
		}
		if(msgsender.getSendtype().equals("˽��")){
			Messagebox.show("����Ϣ����˽����Ϣ������ȡ�����������������·���һ���µģ�.", "��ʾ", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if(msgsender.getIssue().equals("��")){
			Messagebox.show("�ù����Ѿ�����ȡ������״̬.", "��ʾ", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		((MsgsenderSrv)this.getMainSrv()).cancelpub(this.getMainObject());
		this.refreshData();
		Messagebox.show("ȡ����������ɹ�.", "��ʾ", Messagebox.OK, Messagebox.INFORMATION);
	}
}
