package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfassignment extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String assigncode;
     private java.lang.String assignstatus;
     private java.lang.Double dealtimeout;
     private java.lang.String dealtype;
     private java.lang.String description;
     private java.lang.String emailnotify;
     private java.util.Date enddate;
     private java.lang.String initperson;
     private java.lang.String lastmemo;
     private java.lang.String needpass;
     private java.lang.Long nodeid;
     private java.lang.Long ownerid;
     private java.lang.String ownertable;
     private java.lang.Long priority;
     private java.lang.String reassign;
     private java.util.Date startdate;
     private java.lang.Long taskid;
     private java.lang.String upwfrole;
     private java.lang.Long wfinstid;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String wfrole;
     
    /** default constructor */
    public Wfassignment(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ӧ�ó���
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * Ӧ�ó���
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getAssigncode() {
		if(this.assigncode==null || this.assigncode.length()<=0)
	  		return null;
	  	else
	  		return this.assigncode;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setAssigncode(java.lang.String assigncode) {
	   this.assigncode = assigncode;
	}
	
	
    /**
    * ����״̬
    * @return java.lang.String
    */
	public java.lang.String getAssignstatus() {
		if(this.assignstatus==null || this.assignstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assignstatus;
	}
	
	/**
    * ����״̬
    * @return java.lang.String
    */
	public void setAssignstatus(java.lang.String assignstatus) {
	   this.assignstatus = assignstatus;
	}
	
	
    /**
    * ����ʱ��Сʱ��
    * @return java.lang.Double
    */
	public java.lang.Double getDealtimeout() {
	  		return this.dealtimeout;
	}
	
	/**
    * ����ʱ��Сʱ��
    * @return java.lang.Double
    */
	public void setDealtimeout(java.lang.Double dealtimeout) {
	   this.dealtimeout = dealtimeout;
	}
	
	
    /**
    * ��ʱ�������ͣ��ϱ�/�Զ�����
    * @return java.lang.String
    */
	public java.lang.String getDealtype() {
		if(this.dealtype==null || this.dealtype.length()<=0)
	  		return null;
	  	else
	  		return this.dealtype;
	}
	
	/**
    * ��ʱ�������ͣ��ϱ�/�Զ�����
    * @return java.lang.String
    */
	public void setDealtype(java.lang.String dealtype) {
	   this.dealtype = dealtype;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * EMAIL֪ͨ
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * EMAIL֪ͨ
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getEnddate() {
	  		return this.enddate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setEnddate(java.util.Date enddate) {
	   this.enddate = enddate;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getInitperson() {
		if(this.initperson==null || this.initperson.length()<=0)
	  		return null;
	  	else
	  		return this.initperson;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setInitperson(java.lang.String initperson) {
	   this.initperson = initperson;
	}
	
	
    /**
    * ��һ���������
    * @return java.lang.String
    */
	public java.lang.String getLastmemo() {
		if(this.lastmemo==null || this.lastmemo.length()<=0)
	  		return null;
	  	else
	  		return this.lastmemo;
	}
	
	/**
    * ��һ���������
    * @return java.lang.String
    */
	public void setLastmemo(java.lang.String lastmemo) {
	   this.lastmemo = lastmemo;
	}
	
	
    /**
    * �Ƿ���Ҫ����������֤
    * @return java.lang.String
    */
	public java.lang.String getNeedpass() {
		if(this.needpass==null || this.needpass.length()<=0)
	  		return null;
	  	else
	  		return this.needpass;
	}
	
	/**
    * �Ƿ���Ҫ����������֤
    * @return java.lang.String
    */
	public void setNeedpass(java.lang.String needpass) {
	   this.needpass = needpass;
	}
	
	
    /**
    * �ڵ�ID
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * �ڵ�ID
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * ���̹�������
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * ���̹�������
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * ���ȼ�
    * @return java.lang.Long
    */
	public java.lang.Long getPriority() {
	  		return this.priority;
	}
	
	/**
    * ���ȼ�
    * @return java.lang.Long
    */
	public void setPriority(java.lang.Long priority) {
	   this.priority = priority;
	}
	
	
    /**
    * ���·����ˣ������ֶΣ�
    * @return java.lang.String
    */
	public java.lang.String getReassign() {
		if(this.reassign==null || this.reassign.length()<=0)
	  		return null;
	  	else
	  		return this.reassign;
	}
	
	/**
    * ���·����ˣ������ֶΣ�
    * @return java.lang.String
    */
	public void setReassign(java.lang.String reassign) {
	   this.reassign = reassign;
	}
	
	
    /**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * ��ʼʱ��
    * @return java.util.Date
    */
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
	}
	
	
    /**
    * �������ID
    * @return java.lang.Long
    */
	public java.lang.Long getTaskid() {
	  		return this.taskid;
	}
	
	/**
    * �������ID
    * @return java.lang.Long
    */
	public void setTaskid(java.lang.Long taskid) {
	   this.taskid = taskid;
	}
	
	
    /**
    * �ϱ���ɫ
    * @return java.lang.String
    */
	public java.lang.String getUpwfrole() {
		if(this.upwfrole==null || this.upwfrole.length()<=0)
	  		return null;
	  	else
	  		return this.upwfrole;
	}
	
	/**
    * �ϱ���ɫ
    * @return java.lang.String
    */
	public void setUpwfrole(java.lang.String upwfrole) {
	   this.upwfrole = upwfrole;
	}
	
	
    /**
    * WFINANCE��������
    * @return java.lang.Long
    */
	public java.lang.Long getWfinstid() {
	  		return this.wfinstid;
	}
	
	/**
    * WFINANCE��������
    * @return java.lang.Long
    */
	public void setWfinstid(java.lang.Long wfinstid) {
	   this.wfinstid = wfinstid;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * �汾
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * �汾
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
    /**
    * ��������ɫ
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * ��������ɫ
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
	
	
}