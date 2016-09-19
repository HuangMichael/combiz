package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsservice extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classname;
     private java.lang.String description;
     private java.lang.String enable;
     private java.lang.String srvname;
     
    /** default constructor */
    public Ibsservice(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 服务类名（类的全路径）
    * @return java.lang.String
    */
	public java.lang.String getClassname() {
		if(this.classname==null || this.classname.length()<=0)
	  		return null;
	  	else
	  		return this.classname;
	}
	
	/**
    * 服务类名（类的全路径）
    * @return java.lang.String
    */
	public void setClassname(java.lang.String classname) {
	   this.classname = classname;
	}
	
	
    /**
    * 服务描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 服务描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 是否启用？
    * @return java.lang.String
    */
	public java.lang.String getEnable() {
		if(this.enable==null || this.enable.length()<=0)
	  		return null;
	  	else
	  		return this.enable;
	}
	
	/**
    * 是否启用？
    * @return java.lang.String
    */
	public void setEnable(java.lang.String enable) {
	   this.enable = enable;
	}
	
	
    /**
    * 服务名称（可以是中文）
    * @return java.lang.String
    */
	public java.lang.String getSrvname() {
		if(this.srvname==null || this.srvname.length()<=0)
	  		return null;
	  	else
	  		return this.srvname;
	}
	
	/**
    * 服务名称（可以是中文）
    * @return java.lang.String
    */
	public void setSrvname(java.lang.String srvname) {
	   this.srvname = srvname;
	}
	
	
}