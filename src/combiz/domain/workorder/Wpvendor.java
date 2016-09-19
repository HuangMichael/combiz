package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wpvendor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Long cntlinenum;
     private java.lang.String contract;
     private java.lang.String jpnum;
     private java.lang.Double rate;
     private java.lang.String tasknum;
     private java.lang.String vendor;
     private java.lang.Double vendorhrs;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wpvendor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ֶ�CNTLINENUM
    * @return java.lang.Long
    */
	public java.lang.Long getCntlinenum() {
	  		return this.cntlinenum;
	}
	
	/**
    * �ֶ�CNTLINENUM
    * @return java.lang.Long
    */
	public void setCntlinenum(java.lang.Long cntlinenum) {
	   this.cntlinenum = cntlinenum;
	}
	
	
    /**
    * �ֶ�CONTRACT
    * @return java.lang.String
    */
	public java.lang.String getContract() {
		if(this.contract==null || this.contract.length()<=0)
	  		return null;
	  	else
	  		return this.contract;
	}
	
	/**
    * �ֶ�CONTRACT
    * @return java.lang.String
    */
	public void setContract(java.lang.String contract) {
	   this.contract = contract;
	}
	
	
    /**
    * �ֶ�JPNUM
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * �ֶ�JPNUM
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * �ֶ�RATE
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * �ֶ�RATE
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
	}
	
	
    /**
    * �ֶ�TASKNUM
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * �ֶ�TASKNUM
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * �ֶ�VENDOR
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * �ֶ�VENDOR
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * �ֶ�VENDORHRS
    * @return java.lang.Double
    */
	public java.lang.Double getVendorhrs() {
	  		return this.vendorhrs;
	}
	
	/**
    * �ֶ�VENDORHRS
    * @return java.lang.Double
    */
	public void setVendorhrs(java.lang.Double vendorhrs) {
	   this.vendorhrs = vendorhrs;
	}
	
	
    /**
    * �ֶ�WONUM
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * �ֶ�WONUM
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}