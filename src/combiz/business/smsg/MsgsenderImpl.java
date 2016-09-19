package combiz.business.smsg;

import combiz.domain.corp.Labor;
import combiz.domain.corp.Laborgrps;
import combiz.domain.doclib.Document;
import combiz.domain.smsg.Msgreceive;
import combiz.domain.smsg.Msgsender;
import combiz.system.IBOBaseImpl;
import combiz.system.IBOSrvUtil;
import combiz.system.Language;
import combiz.system.common.MobileMessage;
import combiz.system.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.inbasis.zul.Messagebox;
/**
 * 在该类中写业务处理过程
 * 作者：洪小林 日期：2006-12-17
 *
 */
public class MsgsenderImpl extends IBOBaseImpl
implements MsgsenderSrv {
	/**
	 * 删除用户组，同时删除用户组关联用户、组对应的授权数据
	 * 重写父类的删除方法，删除用户组时注意删除对象
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Msgsender))
			throw new Exception("要删除的对象传递不正确！");
		
		//删除本身
		super.delete(obj);
		//删除关联对象－父类方法
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////业务方法//////////////////////////////////
	/**
	 * 保存数据
	 * by 高群凯
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Msgsender))
			throw new Exception("要保存的对象传递不正确！");
		
		Msgsender msgsender = (Msgsender)obj;
		msgsender.setSddate(new Date());
		msgsender.setSender(this.getLaborInfo().getLabornum());
		
		if(msgsender.getId() == null) //新建
		{
			super.save(msgsender); 
		}
		else
		{
			super.update(msgsender);
		}
		}

	/**
	 * @param msgsender
	 * @return
	 * @author:高群凯
	 * @description:公告发布
	 * @time:15:21 2007-7-27
	 */
	public void pubboard(Object obj) throws Exception
	{
		Msgsender msgsender = (Msgsender)obj;
		msgsender.setIssue("是");

		if(msgsender.getSendtype().equals("私有"))
		{
			if(msgsender.getRectype()!=null)
			{
				if(msgsender.getRectype().equals("个人"))
				{
					Msgreceive msgreceive = new Msgreceive();
					msgreceive.setTitle(msgsender.getTitle());
					msgreceive.setBody(msgsender.getBody());
					msgreceive.setSddate(msgsender.getSddate());
					msgreceive.setSendtype(msgsender.getSendtype());
					msgreceive.setSender(msgsender.getSender());
					msgreceive.setRectype(msgsender.getRectype());
					msgreceive.setReceiver(msgsender.getReclabor());
					msgreceive.setHasread("否");
					super.save(msgreceive);

					//拷贝附件
					this.copyAttachment(msgsender, msgreceive);

					msgsender.setRecdept("");	//保持记录一致，也就是说当是个人信息时，那么接收部门字段和接收人员组字段都应该为空，以下有类似情况
					msgsender.setReclaborgrp("");
				}
				else if(msgsender.getRectype().equals("人员组"))
				{
					List laborgroupList = this.getBaseDao().findWithQuery(Laborgrps.class,"(groupname='"+msgsender.getReclaborgrp()+"') and (labornum!='"+this.getLaborInfo().getLabornum()+"')");
					if(laborgroupList.size()>0)
					{
						for(int i=0;i<laborgroupList.size();i++)
						{
							Laborgrps laborgrps = (Laborgrps)laborgroupList.get(i);
							Msgreceive msgreceive = new Msgreceive();
							msgreceive.setTitle(msgsender.getTitle());
							msgreceive.setBody(msgsender.getBody());
							msgreceive.setSddate(msgsender.getSddate());
							msgreceive.setSendtype(msgsender.getSendtype());
							msgreceive.setSender(msgsender.getSender());
							msgreceive.setRectype(msgsender.getRectype());
							msgreceive.setReceiver(laborgrps.getLabornum());
							msgreceive.setHasread("否");
							super.save(msgreceive);

							//拷贝附件
							this.copyAttachment(msgsender, msgreceive);
						}
					}

					msgsender.setRecdept("");
					msgsender.setReclabor("");

				}
				else if(msgsender.getRectype().equals("部门"))
				{
					List laborList = this.getBaseDao().findWithQuery(Labor.class,"(deptnum='"+msgsender.getRecdept()+"') and (labornum!='"+this.getLaborInfo().getLabornum()+"')");
					if(laborList.size()>0)
					{
						for(int i=0;i<laborList.size();i++)
						{
							Labor labor = (Labor)laborList.get(i);
							Msgreceive msgreceive = new Msgreceive();
							msgreceive.setTitle(msgsender.getTitle());
							msgreceive.setBody(msgsender.getBody());
							msgreceive.setSddate(msgsender.getSddate());
							msgreceive.setSendtype(msgsender.getSendtype());
							msgreceive.setSender(msgsender.getSender());
							msgreceive.setRectype(msgsender.getRectype());
							msgreceive.setReceiver(labor.getLabornum());
							msgreceive.setHasread("否");
							super.save(msgreceive);

							//拷贝附件
							this.copyAttachment(msgsender, msgreceive);
						}
					}

					msgsender.setReclabor("");
					msgsender.setReclaborgrp("");
				}
				else if(msgsender.getRectype().equals("全部"))
				{
					//自动改成公告发布
					msgsender.setSendtype("公告");
					msgsender.setRecdept("");
					msgsender.setReclaborgrp("");
					msgsender.setReclabor("");
				}
			}
		}
		else if(msgsender.getSendtype().equals("公告"))
		{
			msgsender.setRectype("");
			msgsender.setRecdept("");
			msgsender.setReclaborgrp("");
			msgsender.setReclabor("");
		}

		super.save(msgsender);
	}

	/**
	 * 
	 * brianhong  2007-11-11
	 * @param msgsender
	 * @param msgreceive
	 * @throws Exception 
	 */
	public void copyAttachment(Msgsender msgsender,Msgreceive msgreceive)
	throws Exception
	{
		String labornum = this.getUserInfo().getLabornum();
		String sitenum = this.getLaborInfo().getSitenum();

		List doclist = this.getBaseDao().findWithQuery(Document.class, "ownertable='"+
				msgsender.getClass().getSimpleName().toUpperCase()+"' and ownerid=" + msgsender.getId());
		if(doclist.size()>0)
		{
			for(int i=0;i<doclist.size();i++)
			{
				Document document = (Document) doclist.get(i);
				Document newdoc = new Document();
				newdoc.setAttachname(document.getAttachname());
				newdoc.setAuthor(document.getAuthor());
				newdoc.setAuthordate(document.getAuthordate());
				newdoc.setChangeby(document.getChangeby());
				newdoc.setChangedate(document.getChangedate());
				//newdoc.setCorpnum(document.getCorpnum());
				newdoc.setCreatedate(document.getCreatedate());
				newdoc.setCreator(document.getCreator());
				newdoc.setDescription(document.getDescription());
				newdoc.setDocextend(document.getDocextend());
				newdoc.setDoctype(document.getDoctype());
				newdoc.setLibnum(document.getLibnum());
				newdoc.setOwnerdept(document.getOwnerdept());
				newdoc.setStatus(document.getStatus());
				newdoc.setUrlpath(document.getUrlpath());
				newdoc.setUrltype(document.getUrltype());
//???
				newdoc.setDocnum(document.getDocnum()+"_msg_" + msgsender.getId() + "_" + msgreceive.getId());
				newdoc.setOwnerid(msgreceive.getId());
				newdoc.setOwnertable("MSGRECEIVE");
				//newdoc.setSitenum(sitenum);

				this.getBaseDao().saveObject(newdoc);
			}
		}
	}
	
	
	/**
	 * @param msgsender
	 * @return
	 * @author:高群凯
	 * @description:公告取消发布
	 * @time:15:21 2007-7-27
	 */
	public void cancelpub(Object obj)  throws Exception
	{
		Msgsender msgsender = (Msgsender)obj;
		if(msgsender.getSendtype()!=null && msgsender.getSendtype().equals("公告"))
		{
			msgsender.setIssue("否");
			super.save(msgsender);
		}
		else
		{
			Messagebox.show("只有公告才可以取消发布！");
		}
	}
	
	
	/**
	 * 发送消息
	 * brianhong  2008-6-4
	 * @param msgsend
	 * @param sendmail
	 * @throws Exception
	 */
	public void sendMessage(Object obj, boolean sendmail)
	throws Exception
	{
		Msgsender msgsender = (Msgsender)obj;
		String recliststr = msgsender.getReclist();
		String[] array = recliststr.split("、");
		List toList = new ArrayList();
		for(int j=0;j<array.length;j++)
		{
			List laborList = this.getBaseDao().findWithQuery(Labor.class,"labornum='" + array[j] + "'");
			if(laborList.size()>0)
			{
				Labor labor = (Labor)laborList.get(0);
				Msgreceive msgreceive = new Msgreceive();
				msgreceive.setTitle(msgsender.getTitle());
				msgreceive.setBody(msgsender.getBody());
				msgreceive.setSddate(msgsender.getSddate());
				msgreceive.setSendtype(msgsender.getSendtype());
				msgreceive.setSender(msgsender.getSender());
				msgreceive.setRectype(msgsender.getRectype());
				msgreceive.setReceiver(labor.getLabornum());
				msgreceive.setHasread("否");
				super.save(msgreceive);
	
				//拷贝附件
				this.copyAttachment(msgsender, msgreceive);
	
				//发送邮件
				if(sendmail)
				{
					String email = labor.getEmail();
					if(email!=null && email.indexOf("@")>=1)
						toList.add(email);
				}
				
				//发送手机短信
				MobileMessage mmsender = (MobileMessage)IBOSrvUtil.getBean("smssender");
				if(mmsender!=null)
					mmsender.executeSend(msgsender.getTitle()+"(来自：" + this.getLaborInfo().getLaborname() + ")", 
							msgsender.getBody(), new Date(), null, labor, mmsender.MESSAGE, null);  
			}
		}

		//发送邮件
		if(sendmail)
		{
			HashSet attachments = new HashSet();
			List doclist = this.getBaseDao().findWithQuery(Document.class, "ownertable='"+
					this.getMainTable() + "' and ownerid=" + msgsender.getId());
			for(int k=0;k<doclist.size();k++)
			{
				Document document = (Document) doclist.get(k);
				String doclibroot = combiz.system.IBSServer.getIBSServer().getDoclibroot();
				String filename = doclibroot + "/" + document.getUrlpath() + "/" + document.getAttachname();
				attachments.add(filename);
			}
			
			Util.sendMail(toList, msgsender.getTitle()+"(来自：" + this.getLaborInfo().getLaborname() + ")", msgsender.getBody(), attachments);

		}
		
		msgsender.setIssue("是");
		this.getBaseDao().updateObject(msgsender);
	}
	
	
}