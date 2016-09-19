package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wofailcause extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String failurecause;
     private java.lang.String failureproblem;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wofailcause(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ԭ������
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ԭ������
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getFailurecause() {
		if(this.failurecause==null || this.failurecause.length()<=0)
	  		return null;
	  	else
	  		return this.failurecause;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setFailurecause(java.lang.String failurecause) {
	   this.failurecause = failurecause;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getFailureproblem() {
		if(this.failureproblem==null || this.failureproblem.length()<=0)
	  		return null;
	  	else
	  		return this.failureproblem;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setFailureproblem(java.lang.String failureproblem) {
	   this.failureproblem = failureproblem;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}