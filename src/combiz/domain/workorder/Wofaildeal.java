package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wofaildeal extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String failurecause;
     private java.lang.String failuredeal;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wofaildeal(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 措施描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 措施描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 原因代码
    * @return java.lang.String
    */
	public java.lang.String getFailurecause() {
		if(this.failurecause==null || this.failurecause.length()<=0)
	  		return null;
	  	else
	  		return this.failurecause;
	}
	
	/**
    * 原因代码
    * @return java.lang.String
    */
	public void setFailurecause(java.lang.String failurecause) {
	   this.failurecause = failurecause;
	}
	
	
    /**
    * 措施代码
    * @return java.lang.String
    */
	public java.lang.String getFailuredeal() {
		if(this.failuredeal==null || this.failuredeal.length()<=0)
	  		return null;
	  	else
	  		return this.failuredeal;
	}
	
	/**
    * 措施代码
    * @return java.lang.String
    */
	public void setFailuredeal(java.lang.String failuredeal) {
	   this.failuredeal = failuredeal;
	}
	
	
    /**
    * 工单编号
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * 工单编号
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}