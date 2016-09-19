package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wftasknotify extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String condition;
     private java.lang.String conditionclass;
     private java.lang.String description;
     private java.lang.String emailnotify;
     private java.lang.Long nodeid;
     private java.lang.String sitefilter;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String wfrole;
     
    /** default constructor */
    public Wftasknotify(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ֶ�APP
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * �ֶ�APP
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * �ֶ�CONDITION
    * @return java.lang.String
    */
	public java.lang.String getCondition() {
		if(this.condition==null || this.condition.length()<=0)
	  		return null;
	  	else
	  		return this.condition;
	}
	
	/**
    * �ֶ�CONDITION
    * @return java.lang.String
    */
	public void setCondition(java.lang.String condition) {
	   this.condition = condition;
	}
	
	
    /**
    * �ֶ�CONDITIONCLASS
    * @return java.lang.String
    */
	public java.lang.String getConditionclass() {
		if(this.conditionclass==null || this.conditionclass.length()<=0)
	  		return null;
	  	else
	  		return this.conditionclass;
	}
	
	/**
    * �ֶ�CONDITIONCLASS
    * @return java.lang.String
    */
	public void setConditionclass(java.lang.String conditionclass) {
	   this.conditionclass = conditionclass;
	}
	
	
    /**
    * �ֶ�DESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * �ֶ�DESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �ֶ�EMAILNOTIFY
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * �ֶ�EMAILNOTIFY
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
	}
	
	
    /**
    * �ֶ�NODEID
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * �ֶ�NODEID
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * �ֶ�SITEFILTER
    * @return java.lang.String
    */
	public java.lang.String getSitefilter() {
		if(this.sitefilter==null || this.sitefilter.length()<=0)
	  		return null;
	  	else
	  		return this.sitefilter;
	}
	
	/**
    * �ֶ�SITEFILTER
    * @return java.lang.String
    */
	public void setSitefilter(java.lang.String sitefilter) {
	   this.sitefilter = sitefilter;
	}
	
	
    /**
    * �ֶ�WFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * �ֶ�WFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * �ֶ�WFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * �ֶ�WFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
    /**
    * �ֶ�WFROLE
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * �ֶ�WFROLE
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
	
	
}