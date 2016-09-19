package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wfprocess extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String active;
     private java.lang.String app;
     private java.lang.String autoinit;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.lang.String tablename;
     private java.lang.String wfinstdesc;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     
    /** default constructor */
    public Wfprocess(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 字段ACTIVE
    * @return java.lang.String
    */
	public java.lang.String getActive() {
		if(this.active==null || this.active.length()<=0)
	  		return null;
	  	else
	  		return this.active;
	}
	
	/**
    * 字段ACTIVE
    * @return java.lang.String
    */
	public void setActive(java.lang.String active) {
	   this.active = active;
	}
	
	
    /**
    * 应用程序
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * 应用程序
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * 字段AUTOINIT
    * @return java.lang.String
    */
	public java.lang.String getAutoinit() {
		if(this.autoinit==null || this.autoinit.length()<=0)
	  		return null;
	  	else
	  		return this.autoinit;
	}
	
	/**
    * 字段AUTOINIT
    * @return java.lang.String
    */
	public void setAutoinit(java.lang.String autoinit) {
	   this.autoinit = autoinit;
	}
	
	
    /**
    * 修改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 修改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 修改日期
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 修改日期
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * 描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 字段ENABLED
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * 字段ENABLED
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * 字段TABLENAME
    * @return java.lang.String
    */
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}
	
	/**
    * 字段TABLENAME
    * @return java.lang.String
    */
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	
	
    /**
    * 流程实例描述-可以单据参数
    * @return java.lang.String
    */
	public java.lang.String getWfinstdesc() {
		if(this.wfinstdesc==null || this.wfinstdesc.length()<=0)
	  		return null;
	  	else
	  		return this.wfinstdesc;
	}
	
	/**
    * 流程实例描述-可以单据参数
    * @return java.lang.String
    */
	public void setWfinstdesc(java.lang.String wfinstdesc) {
	   this.wfinstdesc = wfinstdesc;
	}
	
	
    /**
    * 字段WFNAME
    * @return java.lang.String
    */
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}
	
	/**
    * 字段WFNAME
    * @return java.lang.String
    */
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	
	
    /**
    * 字段WFREVISION
    * @return java.lang.Long
    */
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}
	
	/**
    * 字段WFREVISION
    * @return java.lang.Long
    */
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
	
	
}