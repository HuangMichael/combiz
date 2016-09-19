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
 * �ڸ�����дҵ�������
 * ���ߣ���С�� ���ڣ�2006-12-17
 *
 */
public class MsgsenderImpl extends IBOBaseImpl
implements MsgsenderSrv {
	/**
	 * ɾ���û��飬ͬʱɾ���û�������û������Ӧ����Ȩ����
	 * ��д�����ɾ��������ɾ���û���ʱע��ɾ������
	 */
	@Override
	public void delete(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Msgsender))
			throw new Exception("Ҫɾ���Ķ��󴫵ݲ���ȷ��");
		
		//ɾ������
		super.delete(obj);
		//ɾ���������󣭸��෽��
		//this.deleteAllChild(obj, "");
	}

	
/////////////////////ҵ�񷽷�//////////////////////////////////
	/**
	 * ��������
	 * by ��Ⱥ��
	 */
	@Override
	public void save(Object obj) 
	throws Exception 
	{
		if(!(obj instanceof Msgsender))
			throw new Exception("Ҫ����Ķ��󴫵ݲ���ȷ��");
		
		Msgsender msgsender = (Msgsender)obj;
		msgsender.setSddate(new Date());
		msgsender.setSender(this.getLaborInfo().getLabornum());
		
		if(msgsender.getId() == null) //�½�
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
	 * @author:��Ⱥ��
	 * @description:���淢��
	 * @time:15:21 2007-7-27
	 */
	public void pubboard(Object obj) throws Exception
	{
		Msgsender msgsender = (Msgsender)obj;
		msgsender.setIssue("��");

		if(msgsender.getSendtype().equals("˽��"))
		{
			if(msgsender.getRectype()!=null)
			{
				if(msgsender.getRectype().equals("����"))
				{
					Msgreceive msgreceive = new Msgreceive();
					msgreceive.setTitle(msgsender.getTitle());
					msgreceive.setBody(msgsender.getBody());
					msgreceive.setSddate(msgsender.getSddate());
					msgreceive.setSendtype(msgsender.getSendtype());
					msgreceive.setSender(msgsender.getSender());
					msgreceive.setRectype(msgsender.getRectype());
					msgreceive.setReceiver(msgsender.getReclabor());
					msgreceive.setHasread("��");
					super.save(msgreceive);

					//��������
					this.copyAttachment(msgsender, msgreceive);

					msgsender.setRecdept("");	//���ּ�¼һ�£�Ҳ����˵���Ǹ�����Ϣʱ����ô���ղ����ֶκͽ�����Ա���ֶζ�Ӧ��Ϊ�գ��������������
					msgsender.setReclaborgrp("");
				}
				else if(msgsender.getRectype().equals("��Ա��"))
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
							msgreceive.setHasread("��");
							super.save(msgreceive);

							//��������
							this.copyAttachment(msgsender, msgreceive);
						}
					}

					msgsender.setRecdept("");
					msgsender.setReclabor("");

				}
				else if(msgsender.getRectype().equals("����"))
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
							msgreceive.setHasread("��");
							super.save(msgreceive);

							//��������
							this.copyAttachment(msgsender, msgreceive);
						}
					}

					msgsender.setReclabor("");
					msgsender.setReclaborgrp("");
				}
				else if(msgsender.getRectype().equals("ȫ��"))
				{
					//�Զ��ĳɹ��淢��
					msgsender.setSendtype("����");
					msgsender.setRecdept("");
					msgsender.setReclaborgrp("");
					msgsender.setReclabor("");
				}
			}
		}
		else if(msgsender.getSendtype().equals("����"))
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
	 * @author:��Ⱥ��
	 * @description:����ȡ������
	 * @time:15:21 2007-7-27
	 */
	public void cancelpub(Object obj)  throws Exception
	{
		Msgsender msgsender = (Msgsender)obj;
		if(msgsender.getSendtype()!=null && msgsender.getSendtype().equals("����"))
		{
			msgsender.setIssue("��");
			super.save(msgsender);
		}
		else
		{
			Messagebox.show("ֻ�й���ſ���ȡ��������");
		}
	}
	
	
	/**
	 * ������Ϣ
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
		String[] array = recliststr.split("��");
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
				msgreceive.setHasread("��");
				super.save(msgreceive);
	
				//��������
				this.copyAttachment(msgsender, msgreceive);
	
				//�����ʼ�
				if(sendmail)
				{
					String email = labor.getEmail();
					if(email!=null && email.indexOf("@")>=1)
						toList.add(email);
				}
				
				//�����ֻ�����
				MobileMessage mmsender = (MobileMessage)IBOSrvUtil.getBean("smssender");
				if(mmsender!=null)
					mmsender.executeSend(msgsender.getTitle()+"(���ԣ�" + this.getLaborInfo().getLaborname() + ")", 
							msgsender.getBody(), new Date(), null, labor, mmsender.MESSAGE, null);  
			}
		}

		//�����ʼ�
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
			
			Util.sendMail(toList, msgsender.getTitle()+"(���ԣ�" + this.getLaborInfo().getLaborname() + ")", msgsender.getBody(), attachments);

		}
		
		msgsender.setIssue("��");
		this.getBaseDao().updateObject(msgsender);
	}
	
	
}