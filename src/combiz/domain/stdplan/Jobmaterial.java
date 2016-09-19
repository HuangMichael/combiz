package combiz.domain.stdplan;

import combiz.system.IBOBaseObject;

public class Jobmaterial extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String itemnum;
     private java.lang.String jpnum;
     private java.lang.Double qty;
     private java.lang.String tasknum;
     private java.lang.String vendor;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Jobmaterial(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ×Ö¶ÎITEMNUM
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ×Ö¶ÎITEMNUM
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * ×Ö¶ÎJPNUM
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * ×Ö¶ÎJPNUM
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * ×Ö¶ÎQTY
    * @return java.lang.Double
    */
	public java.lang.Double getQty() {
	  		return this.qty;
	}
	
	/**
    * ×Ö¶ÎQTY
    * @return java.lang.Double
    */
	public void setQty(java.lang.Double qty) {
	   this.qty = qty;
	}
	
	
    /**
    * ×Ö¶ÎTASKNUM
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * ×Ö¶ÎTASKNUM
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * ×Ö¶ÎVENDOR
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * ×Ö¶ÎVENDOR
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * ×Ö¶ÎWAREHOUSE
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * ×Ö¶ÎWAREHOUSE
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}