package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsreport extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String description;
     private java.lang.Long position;
     private java.lang.String rptname;
     private java.lang.String rptpath;
     
    /** default constructor */
    public Ibsreport(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 报表描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 报表描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 排序
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * 排序
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
    /**
    * 报表名称
    * @return java.lang.String
    */
	public java.lang.String getRptname() {
		if(this.rptname==null || this.rptname.length()<=0)
	  		return null;
	  	else
	  		return this.rptname;
	}
	
	/**
    * 报表名称
    * @return java.lang.String
    */
	public void setRptname(java.lang.String rptname) {
	   this.rptname = rptname;
	}
	
	
    /**
    * 报表路径
    * @return java.lang.String
    */
	public java.lang.String getRptpath() {
		if(this.rptpath==null || this.rptpath.length()<=0)
	  		return null;
	  	else
	  		return this.rptpath;
	}
	
	/**
    * 报表路径
    * @return java.lang.String
    */
	public void setRptpath(java.lang.String rptpath) {
	   this.rptpath = rptpath;
	}
	
	
}