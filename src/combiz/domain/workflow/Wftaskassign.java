package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wftaskassign extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String sitefilter;
     private java.lang.Long nodeid;
     private java.lang.String app;
     private java.lang.String wfrole;
     private java.lang.String description;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String emailnotify;
     private java.lang.String condition;
     private java.lang.String conditionclass;
     private java.lang.String upwfrole;
     private java.lang.String dealtype;
     private java.lang.Long priority;
     private java.lang.Double dealtimeout;
     private java.lang.String needpass;
     
    /** default constructor */
    public Wftaskassign(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ص����
    * @return java.lang.String
    */
	public java.lang.String getSitefilter() {
		if(this.sitefilter==null || this.sitefilter.length()<=0)
	  		return null;
	  	else
	  		return this.sitefilter;
	}
	
	/**
    * �ص����
    * @return java.lang.String
    */
	public void setSitefilter(java.lang.String sitefilter) {
	   this.sitefilter = sitefilter;
	}
	
	
    /**
    * �ڵ���
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * �ڵ���
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
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
    * ��ɫ
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * ��ɫ
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
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
    * �Ƿ�EMAIL֪ͨ
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * �Ƿ�EMAIL֪ͨ
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getCondition() {
		if(this.condition==null || this.condition.length()<=0)
	  		return null;
	  	else
	  		return this.condition;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getConditionclass() {
		if(this.conditionclass==null || this.conditionclass.length()<=0)
	  		return null;
	  	else
	  		return this.conditionclass;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setConditionclass(java.lang.String conditionclass) {
	   this.conditionclass = conditionclass;
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
	
	
}