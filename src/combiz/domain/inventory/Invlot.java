package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invlot extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String itemnum;
     private java.lang.Double lotcost;
     private java.lang.Double lotlinecost;
     private java.lang.String lotnum;
     private java.lang.String manufacturer;
     private java.lang.Double shelflife;
     private java.util.Date usebydate;
     private java.lang.String vendor;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Invlot(){}
    
   
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
    * 批次成本
    * @return java.lang.Double
    */
	public java.lang.Double getLotcost() {
	  		return this.lotcost;
	}
	
	/**
    * 批次成本
    * @return java.lang.Double
    */
	public void setLotcost(java.lang.Double lotcost) {
	   this.lotcost = lotcost;
	}
	
	
    /**
    * 批次行成本
    * @return java.lang.Double
    */
	public java.lang.Double getLotlinecost() {
	  		return this.lotlinecost;
	}
	
	/**
    * 批次行成本
    * @return java.lang.Double
    */
	public void setLotlinecost(java.lang.Double lotlinecost) {
	   this.lotlinecost = lotlinecost;
	}
	
	
    /**
    * 批次号
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * 批次号
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
	}
	
	
    /**
    * 生产厂家
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * 生产厂家
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * 有效期
    * @return java.lang.Double
    */
	public java.lang.Double getShelflife() {
	  		return this.shelflife;
	}
	
	/**
    * 有效期
    * @return java.lang.Double
    */
	public void setShelflife(java.lang.Double shelflife) {
	   this.shelflife = shelflife;
	}
	
	
    /**
    * 使用日期
    * @return java.util.Date
    */
	public java.util.Date getUsebydate() {
	  		return this.usebydate;
	}
	
	/**
    * 使用日期
    * @return java.util.Date
    */
	public void setUsebydate(java.util.Date usebydate) {
	   this.usebydate = usebydate;
	}
	
	
    /**
    * 供应商
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * 供应商
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
	
	
    /**
    * 仓库
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * 仓库
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}