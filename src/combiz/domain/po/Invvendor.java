package combiz.domain.po;

import combiz.system.IBOBaseObject;

public class Invvendor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date biddate;
     private java.lang.Double bidprice;
     private java.lang.String isdefault;
     private java.lang.String itemnum;
     private java.lang.Double lastcost;
     private java.util.Date lastdate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String orderunit;
     private java.util.Date promtime;
     private java.lang.String taxcode;
     private java.lang.String vendor;
     
    /** default constructor */
    public Invvendor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 投标日期
    * @return java.util.Date
    */
	public java.util.Date getBiddate() {
	  		return this.biddate;
	}
	
	/**
    * 投标日期
    * @return java.util.Date
    */
	public void setBiddate(java.util.Date biddate) {
	   this.biddate = biddate;
	}
	
	
    /**
    * 投标价格
    * @return java.lang.Double
    */
	public java.lang.Double getBidprice() {
	  		return this.bidprice;
	}
	
	/**
    * 投标价格
    * @return java.lang.Double
    */
	public void setBidprice(java.lang.Double bidprice) {
	   this.bidprice = bidprice;
	}
	
	
    /**
    * 是否默认供应商
    * @return java.lang.String
    */
	public java.lang.String getIsdefault() {
		if(this.isdefault==null || this.isdefault.length()<=0)
	  		return null;
	  	else
	  		return this.isdefault;
	}
	
	/**
    * 是否默认供应商
    * @return java.lang.String
    */
	public void setIsdefault(java.lang.String isdefault) {
	   this.isdefault = isdefault;
	}
	
	
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
    * 上次成本
    * @return java.lang.Double
    */
	public java.lang.Double getLastcost() {
	  		return this.lastcost;
	}
	
	/**
    * 上次成本
    * @return java.lang.Double
    */
	public void setLastcost(java.lang.Double lastcost) {
	   this.lastcost = lastcost;
	}
	
	
    /**
    * 最近一次供货日期
    * @return java.util.Date
    */
	public java.util.Date getLastdate() {
	  		return this.lastdate;
	}
	
	/**
    * 最近一次供货日期
    * @return java.util.Date
    */
	public void setLastdate(java.util.Date lastdate) {
	   this.lastdate = lastdate;
	}
	
	
    /**
    * 生成厂家
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * 生成厂家
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * 型号
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * 型号
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * 订购单位
    * @return java.lang.String
    */
	public java.lang.String getOrderunit() {
		if(this.orderunit==null || this.orderunit.length()<=0)
	  		return null;
	  	else
	  		return this.orderunit;
	}
	
	/**
    * 订购单位
    * @return java.lang.String
    */
	public void setOrderunit(java.lang.String orderunit) {
	   this.orderunit = orderunit;
	}
	
	
    /**
    * 交货时间
    * @return java.util.Date
    */
	public java.util.Date getPromtime() {
	  		return this.promtime;
	}
	
	/**
    * 交货时间
    * @return java.util.Date
    */
	public void setPromtime(java.util.Date promtime) {
	   this.promtime = promtime;
	}
	
	
    /**
    * 税代码
    * @return java.lang.String
    */
	public java.lang.String getTaxcode() {
		if(this.taxcode==null || this.taxcode.length()<=0)
	  		return null;
	  	else
	  		return this.taxcode;
	}
	
	/**
    * 税代码
    * @return java.lang.String
    */
	public void setTaxcode(java.lang.String taxcode) {
	   this.taxcode = taxcode;
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
	
	
}