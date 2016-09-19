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
    * ×Ö¶ÎAPP
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * ×Ö¶ÎAPP
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * ×Ö¶ÎASSIGNCODE
    * @return java.lang.String
    */
	public java.lang.String getAssigncode() {
		if(this.assigncode==null || this.assigncode.length()<=0)
	  		return null;
	  	else
	  		return this.assigncode;
	}
	
	/**
    * ×Ö¶ÎASSIGNCODE
    * @return java.lang.String
    */
	public void setAssigncode(java.lang.String assigncode) {
	   this.assigncode = assigncode;
	}
	
	
    /**
    * ×Ö¶ÎASSIGNSTATUS
    * @return java.lang.String
    */
	public java.lang.String getAssignstatus() {
		if(this.assignstatus==null || this.assignstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assignstatus;
	}
	
	/**
    * ×Ö¶ÎASSIGNSTATUS
    * @return java.lang.String
    */
	public void setAssignstatus(java.lang.String assignstatus) {
	   this.assignstatus = assignstatus;
	}
	
	
    /**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ×Ö¶ÎDESCRIPTION
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ×Ö¶ÎEMAILNOTIFY
    * @return java.lang.String
    */
	public java.lang.String getEmailnotify() {
		if(this.emailnotify==null || this.emailnotify.length()<=0)
	  		return null;
	  	else
	  		return this.emailnotify;
	}
	
	/**
    * ×Ö¶ÎEMAILNOTIFY
    * @return java.lang.String
    */
	public void setEmailnotify(java.lang.String emailnotify) {
	   this.emailnotify = emailnotify;
	}
	
	
    /**
    * ×Ö¶ÎINITPERSON
    * @return java.lang.String
    */
	public java.lang.String getInitperson() {
		if(this.initperson==null || this.initperson.length()<=0)
	  		return null;
	  	else
	  		return this.initperson;
	}
	
	/**
    * ×Ö¶ÎINITPERSON
    * @return java.lang.String
    */
	public void setInitperson(java.lang.String initperson) {
	   this.initperson = initperson;
	}
	
	
    /**
    * ×Ö¶ÎLASTMEMO
    * @return java.lang.String
    */
	public java.lang.String getLastmemo() {
		if(this.lastmemo==null || this.lastmemo.length()<=0)
	  		return null;
	  	else
	  		return this.lastmemo;
	}
	
	/**
    * ×Ö¶ÎLASTMEMO
    * @return java.lang.String
    */
	public void setLastmemo(java.lang.String lastmemo) {
	   this.lastmemo = lastmemo;
	}
	
	
    /**
    * ËùÔÚ½Úµã±àºÅ
    * @return java.lang.Long
    */
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}
	
	/**
    * ËùÔÚ½Úµã±àºÅ
    * @return java.lang.Long
    */
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	
	
    /**
    * ×Ö¶ÎOWNERID
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * ×Ö¶ÎOWNERID
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * ×Ö¶ÎOWNERTABLE
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * ×Ö¶ÎOWNERTABLE
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * ×Ö¶ÎSTARTDATE
    * @return java.util.Date
    */
	public java.util.Date getStartdate() {
	  		return this.startdate;
	}
	
	/**
    * ×Ö¶ÎSTARTDATE
    * @return java.util.Date
    */
	public void setStartdate(java.util.Date startdate) {
	   this.startdate = startdate;
	}
	
	
    /**
    * ÈÎÎñ·ÖÅäID
    * @return java.lang.Long
    */
	public java.lang.Long getTaskid() {
	  		return this.taskid;
	}
	
	/**
    * ÈÎÎñ·ÖÅäID
    * @return java.lang.Long
    */
	public void setTaskid(java.lang.Long taskid) {
	   this.taskid = taskid;
	}
	
	
    /**
    * ×Ö¶ÎWFINSTID
    * @return java.lang.Long
    */
	public java.lang.Long getWfinstid() {
	  		return this.wfinstid;
	}
	
	/**
    * ×Ö¶ÎWFINSTID
    * @return java.lang.Long
    */
	public void setWfinstid(java.lang.Long wfinstid) {
	   this.wfinstid = wfinstid;
	}
	
	
    /**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * ×Ö¶ÎWFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * ×Ö¶ÎWFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
    /**
    * ×Ö¶ÎWFROLE
    * @return java.lang.String
    */
	public java.lang.String getWfrole() {
		if(this.wfrole==null || this.wfrole.length()<=0)
	  		return null;
	  	else
	  		return this.wfrole;
	}
	
	/**
    * ×Ö¶ÎWFROLE
    * @return java.lang.String
    */
	public void setWfrole(java.lang.String wfrole) {
	   this.wfrole = wfrole;
	}
	
	
}