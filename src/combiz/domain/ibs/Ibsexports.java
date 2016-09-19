package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsexports extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String expname;
     private java.lang.String isdefault;
     private java.lang.String isshare;
     private java.lang.String ownertable;
     private java.lang.String userid;
     
    /** default constructor */
    public Ibsexports(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 导出配置名
    * @return java.lang.String
    */
	public java.lang.String getExpname() {
		if(this.expname==null || this.expname.length()<=0)
	  		return null;
	  	else
	  		return this.expname;
	}
	
	/**
    * 导出配置名
    * @return java.lang.String
    */
	public void setExpname(java.lang.String expname) {
	   this.expname = expname;
	}
	
	
    /**
    * 是否默认
    * @return java.lang.String
    */
	public java.lang.String getIsdefault() {
		if(this.isdefault==null || this.isdefault.length()<=0)
	  		return null;
	  	else
	  		return this.isdefault;
	}
	
	/**
    * 是否默认
    * @return java.lang.String
    */
	public void setIsdefault(java.lang.String isdefault) {
	   this.isdefault = isdefault;
	}
	
	
    /**
    * 是否共享？
    * @return java.lang.String
    */
	public java.lang.String getIsshare() {
		if(this.isshare==null || this.isshare.length()<=0)
	  		return null;
	  	else
	  		return this.isshare;
	}
	
	/**
    * 是否共享？
    * @return java.lang.String
    */
	public void setIsshare(java.lang.String isshare) {
	   this.isshare = isshare;
	}
	
	
    /**
    * 导出设置绑定的表
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * 导出设置绑定的表
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * 用户名
    * @return java.lang.String
    */
	public java.lang.String getUserid() {
		if(this.userid==null || this.userid.length()<=0)
	  		return null;
	  	else
	  		return this.userid;
	}
	
	/**
    * 用户名
    * @return java.lang.String
    */
	public void setUserid(java.lang.String userid) {
	   this.userid = userid;
	}
	
	
}