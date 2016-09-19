package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wplabor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String contact;
     private java.lang.String jpnum;
     private java.lang.Float laborhrs;
     private java.lang.String labornum;
     private java.lang.Long laborqty;
     private java.lang.Double linecost;
     private java.lang.Double rate;
     private java.lang.String tasknum;
     private java.lang.String vendor;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wplabor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 合同编号
    * @return java.lang.String
    */
	public java.lang.String getContact() {
		if(this.contact==null || this.contact.length()<=0)
	  		return null;
	  	else
	  		return this.contact;
	}
	
	/**
    * 合同编号
    * @return java.lang.String
    */
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
	
	
    /**
    * 作业包编号
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * 作业包编号
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * 人工小时
    * @return java.lang.Double
    */
	public java.lang.Float getLaborhrs() {
	  		return this.laborhrs;
	}
	
	/**
    * 人工小时
    * @return java.lang.Double
    */
	public void setLaborhrs(java.lang.Float laborhrs) {
	   this.laborhrs = laborhrs;
	}
	
	
    /**
    * 人工编号
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * 人工编号
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * 数量
    * @return java.lang.Long
    */
	public java.lang.Long getLaborqty() {
	  		return this.laborqty;
	}
	
	/**
    * 数量
    * @return java.lang.Long
    */
	public void setLaborqty(java.lang.Long laborqty) {
	   this.laborqty = laborqty;
	}
	
	
    /**
    * 行总价
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * 行总价
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * 费率
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * 费率
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
	}
	
	
    /**
    * 任务编号
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * 任务编号
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * 承包商
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * 承包商
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
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