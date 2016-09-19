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
	
	
	///////////////////////////////////方法区////////////////////////////////////////////////
	/**
	 * 构造函数，初始化数据
	 * 
	 * 作者：洪小林 日期：2006-12-16
	 */
	public MsgsenderWindow()
	{
		super();
	}

	
	
	/**
	 * 新增记录
	 * @throws Exception
	 * 作者：洪小林 日期：2006-12-14
	 */
	public boolean addNew()
	throws Exception
	{
		/**********************************
		 * 创建初始值
		 **********************************/
		Msgsender newobj = new Msgsender();
		newobj.setIssue("否");
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
		if(msgsender.getIssue()!=null && msgsender.getIssue().equals("是"))
			attachment.setReadonly(true);
		else
			attachment.setReadonly(false);
	}
	

	@Override
	public void delete() 
	throws Exception 
	{
		Msgsender msgsender = (Msgsender)this.getMainObject();
		if(msgsender.getSendtype().equals("公告"))
			if(msgsender.getIssue().equals("是"))
			{
				Messagebox.show("请先取消公告发布再进行删除操作！");
				return;
			}
		
		super.delete();
	}



	/**
	 * 保存数据
	 * by 高群凯
	 */
	@Override
	public void save()
	throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		
		if(msgsender.getTitle()==null||msgsender.getTitle().equals(""))
		{
			Messagebox.show("请选择信息标题！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
			return;
		}
		else if(msgsender.getSendtype()==null||msgsender.getSendtype().equals(""))
		{
			Messagebox.show("请选择信息发送类型！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
			return;
		}
		else if(msgsender.getSendtype().equals("私有"))
		{
			if(msgsender.getRectype()==null||msgsender.getRectype().equals(""))
			{
				Messagebox.show("请选择信息信息接收类型！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
				return;
			}
			else if(msgsender.getRectype().equals("个人"))
			{
				if(msgsender.getReclabor()==null||msgsender.getReclabor().equals(""))
				{
					Messagebox.show("你选的信息接收类型为个人，请选择或输入接收人！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
				
			}else if(msgsender.getRectype().equals("部门")){
				if(msgsender.getRecdept()==null||msgsender.getRecdept().equals(""))
				{
					Messagebox.show("你选的信息接收类型为部门，请选择或输入接收部门！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
			}else if(msgsender.getRectype().equals("人员组"))
			{
				if(msgsender.getReclaborgrp()==null||msgsender.getReclaborgrp().equals(""))
				{
					Messagebox.show("你选的信息接收类型为人员组，请选择或输入人员组！","输入错误！",Messagebox.YES,Messagebox.INFORMATION);
					return;
				}
			}
		}
		
		super.save();
	}
	
	/**
	 * 发布公告
	 * by 高群凯
	 */
	public void pubboard() throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再选择发布公告！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("发布公告前，请先保存数据！");
			return;
		}
/*		if(msgsender.getSendtype().equals("私有")){
			Messagebox.show("该信息不是公告信息.", "提示", Messagebox.OK, Messagebox.ERROR);
			return;
		}*/
		if(msgsender.getIssue().equals("是")){
			Messagebox.show("该公告已经处于发布状态.", "提示", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		((MsgsenderSrv)this.getMainSrv()).pubboard(this.getMainObject());
		this.refreshData();
		Messagebox.show("发布公告成功.", "提示", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	/**
	 * 取消发布
	 * by 高群凯
	 */
	public void cancelpub() throws Exception
	{
		Msgsender msgsender = (Msgsender) this.getMainObject();
		if(this.selectedTabid.equals("list"))
		{
			Messagebox.show("请选择一条主记录，然后再选择取消发布公告！");
			return;
		}
		if(this.getObjStatus()==this.MODIFIED || this.getObjStatus()==this.ADDED)
		{
			Messagebox.show("取消发布公告前，请先保存数据！");
			return;
		}
		if(msgsender.getSendtype().equals("私有")){
			Messagebox.show("该信息不是私有信息，不能取消发布，您可以重新发布一条新的！.", "提示", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if(msgsender.getIssue().equals("否")){
			Messagebox.show("该公告已经处于取消发布状态.", "提示", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		((MsgsenderSrv)this.getMainSrv()).cancelpub(this.getMainObject());
		this.refreshData();
		Messagebox.show("取消发布公告成功.", "提示", Messagebox.OK, Messagebox.INFORMATION);
	}
}
