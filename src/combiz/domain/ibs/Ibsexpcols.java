package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsexpcols extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String colname;
     private java.lang.String expname;
     private java.lang.Long position;
     private java.lang.String tablename;
     private java.lang.String userid;
     
    /** default constructor */
    public Ibsexpcols(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 导出字段
    * @return java.lang.String
    */
	public java.lang.String getColname() {
		if(this.colname==null || this.colname.length()<=0)
	  		return null;
	  	else
	  		return this.colname;
	}
	
	/**
    * 导出字段
    * @return java.lang.String
    */
	public void setColname(java.lang.String colname) {
	   this.colname = colname;
	}
	
	
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
    * 排序位置
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * 排序位置
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
    /**
    * 导出表
    * @return java.lang.String
    */
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}
	
	/**
    * 导出表
    * @return java.lang.String
    */
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
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