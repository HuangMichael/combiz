package combiz.domain.location;

import combiz.system.IBOBaseObject;

public class Locstruct extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String addchild;
     private java.lang.String classid;
     private java.lang.String craft;
     private java.lang.String deptnum;
     private java.lang.String description;
     private java.lang.String haschild;
     private java.lang.String location;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.lang.String systemid;
     private java.lang.String type;
     
    /** default constructor */
    public Locstruct(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 标示是否将子集也同时添加到所选系统（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getAddchild() {
		if(this.addchild==null || this.addchild.length()<=0)
	  		return null;
	  	else
	  		return this.addchild;
	}
	
	/**
    * 标示是否将子集也同时添加到所选系统（虚拟字段）
    * @return java.lang.String
    */
	public void setAddchild(java.lang.String addchild) {
	   this.addchild = addchild;
	}
	
	
    /**
    * 位置分类（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 位置分类（虚拟字段）
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * 专业（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * 专业（虚拟字段）
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	
	
    /**
    * 所属部门（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * 所属部门（虚拟字段）
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * 位置描述（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 位置描述（虚拟字段）
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
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
    * 位置编码
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 位置编码
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 排序
    * @return java.lang.Long
    */
	public java.lang.Long getOrderby() {
	  		return this.orderby;
	}
	
	/**
    * 排序
    * @return java.lang.Long
    */
	public void setOrderby(java.lang.Long orderby) {
	   this.orderby = orderby;
	}
	
	
    /**
    * 父级位置
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父级位置
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 所属系统ID
    * @return java.lang.String
    */
	public java.lang.String getSystemid() {
		if(this.systemid==null || this.systemid.length()<=0)
	  		return null;
	  	else
	  		return this.systemid;
	}
	
	/**
    * 所属系统ID
    * @return java.lang.String
    */
	public void setSystemid(java.lang.String systemid) {
	   this.systemid = systemid;
	}
	
	
    /**
    * 类型（虚拟字段）
    * @return java.lang.String
    */
	public java.lang.String getType() {
		if(this.type==null || this.type.length()<=0)
	  		return null;
	  	else
	  		return this.type;
	}
	
	/**
    * 类型（虚拟字段）
    * @return java.lang.String
    */
	public void setType(java.lang.String type) {
	   this.type = type;
	}
	
	
}