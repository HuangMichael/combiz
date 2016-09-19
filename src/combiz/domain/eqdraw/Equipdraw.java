package combiz.domain.eqdraw;

import combiz.system.IBOBaseObject;

public class Equipdraw extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String drawnum;
     private java.lang.String description;
     private java.lang.String parent;
     private java.lang.String haschild;
     private java.lang.String drawpath;
     private java.lang.String createby;
     private java.util.Date createdate;
     private java.lang.Long position;
     
    /** default constructor */
    public Equipdraw(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 图形编号
    * @return java.lang.String
    */
	public java.lang.String getDrawnum() {
		if(this.drawnum==null || this.drawnum.length()<=0)
	  		return null;
	  	else
	  		return this.drawnum;
	}
	
	/**
    * 图形编号
    * @return java.lang.String
    */
	public void setDrawnum(java.lang.String drawnum) {
	   this.drawnum = drawnum;
	}
	
	
    /**
    * 图形文件描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 图形文件描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 父级
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父级
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 是否有子集
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * 是否有子集
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * 图形文件路径
    * @return java.lang.String
    */
	public java.lang.String getDrawpath() {
		if(this.drawpath==null || this.drawpath.length()<=0)
	  		return null;
	  	else
	  		return this.drawpath;
	}
	
	/**
    * 图形文件路径
    * @return java.lang.String
    */
	public void setDrawpath(java.lang.String drawpath) {
	   this.drawpath = drawpath;
	}
	
	
    /**
    * 创建人
    * @return java.lang.String
    */
	public java.lang.String getCreateby() {
		if(this.createby==null || this.createby.length()<=0)
	  		return null;
	  	else
	  		return this.createby;
	}
	
	/**
    * 创建人
    * @return java.lang.String
    */
	public void setCreateby(java.lang.String createby) {
	   this.createby = createby;
	}
	
	
    /**
    * 创建时间
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * 创建时间
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
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
	
	
}