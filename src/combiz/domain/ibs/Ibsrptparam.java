package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsrptparam extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String autodata;
     private java.lang.String description;
     private java.lang.String lookupfile;
     private java.lang.String lookuplist;
     private java.lang.String lookuptype;
     private java.lang.String paramfield;
     private java.lang.String paramname;
     private java.lang.Long parampos;
     private java.lang.String paramvalue;
     private java.lang.String rptname;
     
    /** default constructor */
    public Ibsrptparam(){}
    
   
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
    * 是否自动带值
    * @return java.lang.String
    */
	public java.lang.String getAutodata() {
		if(this.autodata==null || this.autodata.length()<=0)
	  		return null;
	  	else
	  		return this.autodata;
	}
	
	/**
    * 是否自动带值
    * @return java.lang.String
    */
	public void setAutodata(java.lang.String autodata) {
	   this.autodata = autodata;
	}
	
	
    /**
    * 参数描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 参数描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * lookup文件
    * @return java.lang.String
    */
	public java.lang.String getLookupfile() {
		if(this.lookupfile==null || this.lookupfile.length()<=0)
	  		return null;
	  	else
	  		return this.lookupfile;
	}
	
	/**
    * lookup文件
    * @return java.lang.String
    */
	public void setLookupfile(java.lang.String lookupfile) {
	   this.lookupfile = lookupfile;
	}
	
	
    /**
    * 查找值列表
    * @return java.lang.String
    */
	public java.lang.String getLookuplist() {
		if(this.lookuplist==null || this.lookuplist.length()<=0)
	  		return null;
	  	else
	  		return this.lookuplist;
	}
	
	/**
    * 查找值列表
    * @return java.lang.String
    */
	public void setLookuplist(java.lang.String lookuplist) {
	   this.lookuplist = lookuplist;
	}
	
	
    /**
    * 查找类型
    * @return java.lang.String
    */
	public java.lang.String getLookuptype() {
		if(this.lookuptype==null || this.lookuptype.length()<=0)
	  		return null;
	  	else
	  		return this.lookuptype;
	}
	
	/**
    * 查找类型
    * @return java.lang.String
    */
	public void setLookuptype(java.lang.String lookuptype) {
	   this.lookuptype = lookuptype;
	}
	
	
    /**
    * 参数取值字段
    * @return java.lang.String
    */
	public java.lang.String getParamfield() {
		if(this.paramfield==null || this.paramfield.length()<=0)
	  		return null;
	  	else
	  		return this.paramfield;
	}
	
	/**
    * 参数取值字段
    * @return java.lang.String
    */
	public void setParamfield(java.lang.String paramfield) {
	   this.paramfield = paramfield;
	}
	
	
    /**
    * 参数名称
    * @return java.lang.String
    */
	public java.lang.String getParamname() {
		if(this.paramname==null || this.paramname.length()<=0)
	  		return null;
	  	else
	  		return this.paramname;
	}
	
	/**
    * 参数名称
    * @return java.lang.String
    */
	public void setParamname(java.lang.String paramname) {
	   this.paramname = paramname;
	}
	
	
    /**
    * 参数排序顺序
    * @return java.lang.Long
    */
	public java.lang.Long getParampos() {
	  		return this.parampos;
	}
	
	/**
    * 参数排序顺序
    * @return java.lang.Long
    */
	public void setParampos(java.lang.Long parampos) {
	   this.parampos = parampos;
	}
	
	
    /**
    * 默认参数值
    * @return java.lang.String
    */
	public java.lang.String getParamvalue() {
		if(this.paramvalue==null || this.paramvalue.length()<=0)
	  		return null;
	  	else
	  		return this.paramvalue;
	}
	
	/**
    * 默认参数值
    * @return java.lang.String
    */
	public void setParamvalue(java.lang.String paramvalue) {
	   this.paramvalue = paramvalue;
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
	
	
}