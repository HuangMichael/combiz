package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wofailproblem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String failurecode;
     private java.lang.String failureproblem;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wofailproblem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 问题描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 问题描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 故障代码
    * @return java.lang.String
    */
	public java.lang.String getFailurecode() {
		if(this.failurecode==null || this.failurecode.length()<=0)
	  		return null;
	  	else
	  		return this.failurecode;
	}
	
	/**
    * 故障代码
    * @return java.lang.String
    */
	public void setFailurecode(java.lang.String failurecode) {
	   this.failurecode = failurecode;
	}
	
	
    /**
    * 故障问题
    * @return java.lang.String
    */
	public java.lang.String getFailureproblem() {
		if(this.failureproblem==null || this.failureproblem.length()<=0)
	  		return null;
	  	else
	  		return this.failureproblem;
	}
	
	/**
    * 故障问题
    * @return java.lang.String
    */
	public void setFailureproblem(java.lang.String failureproblem) {
	   this.failureproblem = failureproblem;
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