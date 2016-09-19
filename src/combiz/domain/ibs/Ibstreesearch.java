package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstreesearch extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String app;
     private java.lang.String description;
     private java.lang.String isdepartment;
     private java.lang.String islabor;
     private java.lang.String listfield;
     private java.lang.String listtype;
     private java.lang.String searchbandfld;
     private java.lang.String searchlist;
     private java.lang.String treelabel;
     
    /** default constructor */
    public Ibstreesearch(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 应用程序名
    * @return java.lang.String
    */
	public java.lang.String getApp() {
		if(this.app==null || this.app.length()<=0)
	  		return null;
	  	else
	  		return this.app;
	}
	
	/**
    * 应用程序名
    * @return java.lang.String
    */
	public void setApp(java.lang.String app) {
	   this.app = app;
	}
	
	
    /**
    * 查询树的查询名称
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 查询树的查询名称
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 是部门树
    * @return java.lang.String
    */
	public java.lang.String getIsdepartment() {
		if(this.isdepartment==null || this.isdepartment.length()<=0)
	  		return null;
	  	else
	  		return this.isdepartment;
	}
	
	/**
    * 是部门树
    * @return java.lang.String
    */
	public void setIsdepartment(java.lang.String isdepartment) {
	   this.isdepartment = isdepartment;
	}
	
	
    /**
    * 是人员树
    * @return java.lang.String
    */
	public java.lang.String getIslabor() {
		if(this.islabor==null || this.islabor.length()<=0)
	  		return null;
	  	else
	  		return this.islabor;
	}
	
	/**
    * 是人员树
    * @return java.lang.String
    */
	public void setIslabor(java.lang.String islabor) {
	   this.islabor = islabor;
	}
	
	
    /**
    * 值列表存储值的字段(未使用)
    * @return java.lang.String
    */
	public java.lang.String getListfield() {
		if(this.listfield==null || this.listfield.length()<=0)
	  		return null;
	  	else
	  		return this.listfield;
	}
	
	/**
    * 值列表存储值的字段(未使用)
    * @return java.lang.String
    */
	public void setListfield(java.lang.String listfield) {
	   this.listfield = listfield;
	}
	
	
    /**
    * 值列表的类型
    * @return java.lang.String
    */
	public java.lang.String getListtype() {
		if(this.listtype==null || this.listtype.length()<=0)
	  		return null;
	  	else
	  		return this.listtype;
	}
	
	/**
    * 值列表的类型
    * @return java.lang.String
    */
	public void setListtype(java.lang.String listtype) {
	   this.listtype = listtype;
	}
	
	
    /**
    * 值列表绑定的字段
    * @return java.lang.String
    */
	public java.lang.String getSearchbandfld() {
		if(this.searchbandfld==null || this.searchbandfld.length()<=0)
	  		return null;
	  	else
	  		return this.searchbandfld;
	}
	
	/**
    * 值列表绑定的字段
    * @return java.lang.String
    */
	public void setSearchbandfld(java.lang.String searchbandfld) {
	   this.searchbandfld = searchbandfld;
	}
	
	
    /**
    * 搜索值列表
    * @return java.lang.String
    */
	public java.lang.String getSearchlist() {
		if(this.searchlist==null || this.searchlist.length()<=0)
	  		return null;
	  	else
	  		return this.searchlist;
	}
	
	/**
    * 搜索值列表
    * @return java.lang.String
    */
	public void setSearchlist(java.lang.String searchlist) {
	   this.searchlist = searchlist;
	}
	
	
    /**
    * 查询树上的显示标签（未使用）
    * @return java.lang.String
    */
	public java.lang.String getTreelabel() {
		if(this.treelabel==null || this.treelabel.length()<=0)
	  		return null;
	  	else
	  		return this.treelabel;
	}
	
	/**
    * 查询树上的显示标签（未使用）
    * @return java.lang.String
    */
	public void setTreelabel(java.lang.String treelabel) {
	   this.treelabel = treelabel;
	}
	
	
}