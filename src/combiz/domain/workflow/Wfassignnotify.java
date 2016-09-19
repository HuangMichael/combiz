package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfassignnotify extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String assigncode;
     private java.lang.String assignstatus;
     private java.lang.String description;
     private java.lang.String emailnotify;
     private java.lang.String initperson;
     private java.lang.String lastmemo;
     private java.lang.Long nodeid;
     private java.lang.Long ownerid;
     private java.lang.String ownertable;
     private java.util.Date startdate;
     private java.lang.Long taskid;
     private java.lang.Long wfinstid;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     private java.lang.String wfrole;
     
    /** default constructor */
    public Wfassignnotify(){}
    
   
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
    * �ֶ�ASSIGNCODE
    * @return java.lang.String
    */
	public java.lang.String getAssigncode() {
		if(this.assigncode==null || this.assigncode.length()<=0)
	  		return null;
	  	else
	  		return this.assigncode;
	}
	
	/**
    * �ֶ�ASSIGNCODE
    * @return java.lang.String
    */
	public void setAssigncode(java.lang.String assigncode) {
	   this.assigncode = assigncode;
	}
	
	
    /**
    * �ֶ�ASSIGNSTATUS
    * @return java.lang.String
    */
	public java.lang.String getAssignstatus() {
		if(this.assignstatus==null || this.assignstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assignstatus;
	}
	
	/**
    * �ֶ�ASSIGNSTATUS
    * @return java.lang.String
    */
	public void setAssignstatus(java.lang.String assignstatus) {
	   this.assignstatus = assignstatus;
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
    * �ֶ�INITPERSON
    * @return java.lang.String
    */
	public java.lang.String getInitperson() {
		if(this.initperson==null || this.initperson.length()<=0)
	  		return null;
	  	else
	  		return this.initperson;
	}
	
	/**
    * �ֶ�INITPERSON
    * @return java.lang.String
    */
	public void setInitperson(java.lang.String initperson) {
	   this.initperson = initperson;
	}
	
	
    /**
    * �ֶ�LASTMEMO
    * @return java.lang.String
    */
	public java.lang.String getLastmemo() {
		if(this.lastmemo==null || this.lastmemo.length()<=0)
	  		return null;
	  	else
	  		return this.lastmemo;
	}
	
	/**
    * �ֶ�LASTMEMO
    * @return java.lang.String
    */
	public void setLastmemo(java.lang.String lastmemo) {
	   this.lastmemo = lastmemo;
	}
	
	
    /**
    * ���ڽڵ���
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * ���ڽڵ���
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * �ֶ�OWNERID
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * �ֶ�OWNERID
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * �ֶ�OWNERTABLE
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * �ֶ�OWNERTABLE
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * �ֶ�STARTDATE
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * �ֶ�STARTDATE
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
    * �ֶ�WFINSTID
    * @return java.lang.Long
    */
	public java.lang.Long getWfinstid() {
	  		return this.wfinstid;
	}
	
	/**
    * �ֶ�WFINSTID
    * @return java.lang.Long
    */
	public void setWfinstid(java.lang.Long wfinstid) {
	   this.wfinstid = wfinstid;
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