package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Doclibary extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.util.Date createdate;
     private java.lang.String creator;
     private java.lang.String description;
     private java.lang.String haschild;
     private java.lang.String libnum;
     private java.lang.String libpath;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.lang.String status;
     
    /** default constructor */
    public Doclibary(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 创建日期
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * 创建日期
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * 创建人
    * @return java.lang.String
    */
	public java.lang.String getCreator() {
		if(this.creator==null || this.creator.length()<=0)
	  		return null;
	  	else
	  		return this.creator;
	}
	
	/**
    * 创建人
    * @return java.lang.String
    */
	public void setCreator(java.lang.String creator) {
	   this.creator = creator;
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
    * 有子集
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * 有子集
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * 目录名
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * 目录名
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * 目录路径
    * @return java.lang.String
    */
	public java.lang.String getLibpath() {
		if(this.libpath==null || this.libpath.length()<=0)
	  		return null;
	  	else
	  		return this.libpath;
	}
	
	/**
    * 目录路径
    * @return java.lang.String
    */
	public void setLibpath(java.lang.String libpath) {
	   this.libpath = libpath;
	}
	
	
    /**
    * 序号
    * @return java.lang.Long
    */
	public java.lang.Long getOrderby() {
	  		return this.orderby;
	}
	
	/**
    * 序号
    * @return java.lang.Long
    */
	public void setOrderby(java.lang.Long orderby) {
	   this.orderby = orderby;
	}
	
	
    /**
    * 父目录
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父目录
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
}