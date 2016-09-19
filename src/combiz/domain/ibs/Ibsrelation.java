package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsrelation extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String child;
     private java.lang.String defaultvalue;
     private java.lang.String listwhere;
     private java.lang.String parent;
     private java.lang.String relname;
     private java.lang.String reltype;
     private java.lang.String remark;
     private java.lang.String updelete;
     
    /** default constructor */
    public Ibsrelation(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 子表
    * @return java.lang.String
    */
	public java.lang.String getChild() {
		if(this.child==null || this.child.length()<=0)
	  		return null;
	  	else
	  		return this.child;
	}
	
	/**
    * 子表
    * @return java.lang.String
    */
	public void setChild(java.lang.String child) {
	   this.child = child;
	}
	
	
    /**
    * 从父级带入的默认值字段
    * @return java.lang.String
    */
	public java.lang.String getDefaultvalue() {
		if(this.defaultvalue==null || this.defaultvalue.length()<=0)
	  		return null;
	  	else
	  		return this.defaultvalue;
	}
	
	/**
    * 从父级带入的默认值字段
    * @return java.lang.String
    */
	public void setDefaultvalue(java.lang.String defaultvalue) {
	   this.defaultvalue = defaultvalue;
	}
	
	
    /**
    * 列表过滤条件
    * @return java.lang.String
    */
	public java.lang.String getListwhere() {
		if(this.listwhere==null || this.listwhere.length()<=0)
	  		return null;
	  	else
	  		return this.listwhere;
	}
	
	/**
    * 列表过滤条件
    * @return java.lang.String
    */
	public void setListwhere(java.lang.String listwhere) {
	   this.listwhere = listwhere;
	}
	
	
    /**
    * 父表
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父表
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 关系名
    * @return java.lang.String
    */
	public java.lang.String getRelname() {
		if(this.relname==null || this.relname.length()<=0)
	  		return null;
	  	else
	  		return this.relname;
	}
	
	/**
    * 关系名
    * @return java.lang.String
    */
	public void setRelname(java.lang.String relname) {
	   this.relname = relname;
	}
	
	
    /**
    * 关系类型：一对一、一对多
    * @return java.lang.String
    */
	public java.lang.String getReltype() {
		if(this.reltype==null || this.reltype.length()<=0)
	  		return null;
	  	else
	  		return this.reltype;
	}
	
	/**
    * 关系类型：一对一、一对多
    * @return java.lang.String
    */
	public void setReltype(java.lang.String reltype) {
	   this.reltype = reltype;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * 跟随父级删除
    * @return java.lang.String
    */
	public java.lang.String getUpdelete() {
		if(this.updelete==null || this.updelete.length()<=0)
	  		return null;
	  	else
	  		return this.updelete;
	}
	
	/**
    * 跟随父级删除
    * @return java.lang.String
    */
	public void setUpdelete(java.lang.String updelete) {
	   this.updelete = updelete;
	}
	
	
}