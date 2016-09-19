package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Itemeqbom extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields 
     private java.lang.String itemnum;
     private java.lang.String parent;
     private java.lang.Double quanity;
     private java.lang.String remark;
     
    /** default constructor */
    public Itemeqbom(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 库存编码
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存编码
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * 数量
    * @return java.lang.Double
    */
	public java.lang.Double getQuanity() {
	  		return this.quanity;
	}
	
	/**
    * 数量
    * @return java.lang.Double
    */
	public void setQuanity(java.lang.Double quanity) {
	   this.quanity = quanity;
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
	
	
}