package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Warehouse extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String shiptoaddr;
     private java.lang.String shiptoinvlabor;
     private java.lang.String shiptolabor;
     private java.lang.String sitenum;
     private java.lang.String warehouse;
     private java.lang.String warehouseadmin;
     
    /** default constructor */
    public Warehouse(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 组织机构
    * @return java.lang.String
    */
	public java.lang.String getCorpnum() {
		if(this.corpnum==null || this.corpnum.length()<=0)
	  		return null;
	  	else
	  		return this.corpnum;
	}
	
	/**
    * 组织机构
    * @return java.lang.String
    */
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	
	
    /**
    * 描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 收货地址
    * @return java.lang.String
    */
	public java.lang.String getShiptoaddr() {
		if(this.shiptoaddr==null || this.shiptoaddr.length()<=0)
	  		return null;
	  	else
	  		return this.shiptoaddr;
	}
	
	/**
    * 收货地址
    * @return java.lang.String
    */
	public void setShiptoaddr(java.lang.String shiptoaddr) {
	   this.shiptoaddr = shiptoaddr;
	}
	
	
    /**
    * 收货人
    * @return java.lang.String
    */
	public java.lang.String getShiptoinvlabor() {
		if(this.shiptoinvlabor==null || this.shiptoinvlabor.length()<=0)
	  		return null;
	  	else
	  		return this.shiptoinvlabor;
	}
	
	/**
    * 收货人
    * @return java.lang.String
    */
	public void setShiptoinvlabor(java.lang.String shiptoinvlabor) {
	   this.shiptoinvlabor = shiptoinvlabor;
	}
	
	
    /**
    * 收货人
    * @return java.lang.String
    */
	public java.lang.String getShiptolabor() {
		if(this.shiptolabor==null || this.shiptolabor.length()<=0)
	  		return null;
	  	else
	  		return this.shiptolabor;
	}
	
	/**
    * 收货人
    * @return java.lang.String
    */
	public void setShiptolabor(java.lang.String shiptolabor) {
	   this.shiptolabor = shiptolabor;
	}
	
	
    /**
    * 地点
    * @return java.lang.String
    */
	public java.lang.String getSitenum() {
		if(this.sitenum==null || this.sitenum.length()<=0)
	  		return null;
	  	else
	  		return this.sitenum;
	}
	
	/**
    * 地点
    * @return java.lang.String
    */
	public void setSitenum(java.lang.String sitenum) {
	   this.sitenum = sitenum;
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
	
	
    /**
    * 仓库管理员
    * @return java.lang.String
    */
	public java.lang.String getWarehouseadmin() {
		if(this.warehouseadmin==null || this.warehouseadmin.length()<=0)
	  		return null;
	  	else
	  		return this.warehouseadmin;
	}
	
	/**
    * 仓库管理员
    * @return java.lang.String
    */
	public void setWarehouseadmin(java.lang.String warehouseadmin) {
	   this.warehouseadmin = warehouseadmin;
	}
	
	
}