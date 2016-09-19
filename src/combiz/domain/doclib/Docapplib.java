package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Docapplib extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String isrelapp;
     private java.lang.String libnum;
     private java.lang.String ownertable;
     private java.lang.String relquery;
     
    /** default constructor */
    public Docapplib(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 绑定应用程序
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * 绑定应用程序
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * 是关联应用程序？
    * @return java.lang.String
    */
	public java.lang.String getIsrelapp() {
		if(this.isrelapp==null || this.isrelapp.length()<=0)
	  		return null;
	  	else
	  		return this.isrelapp;
	}
	
	/**
    * 是关联应用程序？
    * @return java.lang.String
    */
	public void setIsrelapp(java.lang.String isrelapp) {
	   this.isrelapp = isrelapp;
	}
	
	
    /**
    * 文档库编号
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * 文档库编号
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * 附加文档所属表
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * 附加文档所属表
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * 关联表的文档列表查询条件
    * @return java.lang.String
    */
	public java.lang.String getRelquery() {
		if(this.relquery==null || this.relquery.length()<=0)
	  		return null;
	  	else
	  		return this.relquery;
	}
	
	/**
    * 关联表的文档列表查询条件
    * @return java.lang.String
    */
	public void setRelquery(java.lang.String relquery) {
	   this.relquery = relquery;
	}
	
	
}