package combiz.domain.budget;

import combiz.system.IBOBaseObject;

public class Budgetitemance extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ancestor;
     private java.lang.String buditem;
     private java.lang.String version;
     
    /** default constructor */
    public Budgetitemance(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ԥ����Ŀ����
    * @return java.lang.String
    */
	public java.lang.String getAncestor() {
		if(this.ancestor==null || this.ancestor.length()<=0)
	  		return null;
	  	else
	  		return this.ancestor;
	}
	
	/**
    * Ԥ����Ŀ����
    * @return java.lang.String
    */
	public void setAncestor(java.lang.String ancestor) {
	   this.ancestor = ancestor;
	}
	
	
    /**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * �汾
    * @return java.lang.String
    */
	public java.lang.String getVersion() {
		if(this.version==null || this.version.length()<=0)
	  		return null;
	  	else
	  		return this.version;
	}
	
	/**
    * �汾
    * @return java.lang.String
    */
	public void setVersion(java.lang.String version) {
	   this.version = version;
	}
	
	
}