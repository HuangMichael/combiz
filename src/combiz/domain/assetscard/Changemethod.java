package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Changemethod extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String categories;
     private java.lang.String details;
     private java.lang.String parent;
     
    /** default constructor */
    public Changemethod(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 大类
    * @return java.lang.String
    */
	public java.lang.String getCategories() {
		if(this.categories==null || this.categories.length()<=0)
	  		return null;
	  	else
	  		return this.categories;
	}
	
	/**
    * 大类
    * @return java.lang.String
    */
	public void setCategories(java.lang.String categories) {
	   this.categories = categories;
	}
	
	
    /**
    * 明细类别
    * @return java.lang.String
    */
	public java.lang.String getDetails() {
		if(this.details==null || this.details.length()<=0)
	  		return null;
	  	else
	  		return this.details;
	}
	
	/**
    * 明细类别
    * @return java.lang.String
    */
	public void setDetails(java.lang.String details) {
	   this.details = details;
	}
	
	
    /**
    * 编号
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 编号
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
}