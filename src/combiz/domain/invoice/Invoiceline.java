package combiz.domain.invoice;

import combiz.system.IBOBaseObject;

public class Invoiceline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String state;
     private java.lang.String invoicenum;
     private java.lang.Long invoicelinenum;
     private java.lang.String vendor;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.Double invoiceqty;
     private java.lang.String invoiceunit;
     private java.lang.Double unitcost;
     private java.lang.Double taxunitcost;
     private java.lang.Double linecost;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.String enterby;
     private java.lang.String receiptreqd;
     private java.util.Date enterdate;
     private java.lang.String service;
     private java.lang.Double loadedcost;
     private java.lang.String proratetoinv;
     private java.lang.String prorated;
     private java.lang.Double proratecost;
     private java.lang.Double taxlinecost;
     private java.lang.Double conversion;
     private java.lang.String taxcode;
     private java.lang.String paytype;
     
    /** default constructor */
    public Invoiceline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 行状态
    * @return java.lang.String
    */
	public java.lang.String getState() {
		if(this.state==null || this.state.length()<=0)
	  		return null;
	  	else
	  		return this.state;
	}
	
	/**
    * 行状态
    * @return java.lang.String
    */
	public void setState(java.lang.String state) {
	   this.state = state;
	}
	
	
    /**
    * 发票编号
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * 发票编号
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
	}
	
	
    /**
    * 发票行号
    * @return java.lang.Long
    */
	public java.lang.Long getInvoicelinenum() {
	  		return this.invoicelinenum;
	}
	
	/**
    * 发票行号
    * @return java.lang.Long
    */
	public void setInvoicelinenum(java.lang.Long invoicelinenum) {
	   this.invoicelinenum = invoicelinenum;
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
    * 采购单编号
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * 采购单编号
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * 采购单行号
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * 采购单行号
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * 库存编号
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存编号
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * 发票数量
    * @return java.lang.Double
    */
	public java.lang.Double getInvoiceqty() {
	  		return this.invoiceqty;
	}
	
	/**
    * 发票数量
    * @return java.lang.Double
    */
	public void setInvoiceqty(java.lang.Double invoiceqty) {
	   this.invoiceqty = invoiceqty;
	}
	
	
    /**
    * 发票单位
    * @return java.lang.String
    */
	public java.lang.String getInvoiceunit() {
		if(this.invoiceunit==null || this.invoiceunit.length()<=0)
	  		return null;
	  	else
	  		return this.invoiceunit;
	}
	
	/**
    * 发票单位
    * @return java.lang.String
    */
	public void setInvoiceunit(java.lang.String invoiceunit) {
	   this.invoiceunit = invoiceunit;
	}
	
	
    /**
    * 单位成本
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * 单位成本
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
	}
	
	
    /**
    * 含税单价
    * @return java.lang.Double
    */
	public java.lang.Double getTaxunitcost() {
	  		return this.taxunitcost;
	}
	
	/**
    * 含税单价
    * @return java.lang.Double
    */
	public void setTaxunitcost(java.lang.Double taxunitcost) {
	   this.taxunitcost = taxunitcost;
	}
	
	
    /**
    * 行成本
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * 行成本
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * 税率
    * @return java.lang.Double
    */
	public java.lang.Double getTaxrate() {
	  		return this.taxrate;
	}
	
	/**
    * 税率
    * @return java.lang.Double
    */
	public void setTaxrate(java.lang.Double taxrate) {
	   this.taxrate = taxrate;
	}
	
	
    /**
    * 税
    * @return java.lang.Double
    */
	public java.lang.Double getTax() {
	  		return this.tax;
	}
	
	/**
    * 税
    * @return java.lang.Double
    */
	public void setTax(java.lang.Double tax) {
	   this.tax = tax;
	}
	
	
    /**
    * 录入人
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * 录入人
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
    /**
    * 匹配接收
    * @return java.lang.String
    */
	public java.lang.String getReceiptreqd() {
		if(this.receiptreqd==null || this.receiptreqd.length()<=0)
	  		return null;
	  	else
	  		return this.receiptreqd;
	}
	
	/**
    * 匹配接收
    * @return java.lang.String
    */
	public void setReceiptreqd(java.lang.String receiptreqd) {
	   this.receiptreqd = receiptreqd;
	}
	
	
    /**
    * 输入日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 输入日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 服务
    * @return java.lang.String
    */
	public java.lang.String getService() {
		if(this.service==null || this.service.length()<=0)
	  		return null;
	  	else
	  		return this.service;
	}
	
	/**
    * 服务
    * @return java.lang.String
    */
	public void setService(java.lang.String service) {
	   this.service = service;
	}
	
	
    /**
    * 载入成本
    * @return java.lang.Double
    */
	public java.lang.Double getLoadedcost() {
	  		return this.loadedcost;
	}
	
	/**
    * 载入成本
    * @return java.lang.Double
    */
	public void setLoadedcost(java.lang.Double loadedcost) {
	   this.loadedcost = loadedcost;
	}
	
	
    /**
    * 摊入物资成本
    * @return java.lang.String
    */
	public java.lang.String getProratetoinv() {
		if(this.proratetoinv==null || this.proratetoinv.length()<=0)
	  		return null;
	  	else
	  		return this.proratetoinv;
	}
	
	/**
    * 摊入物资成本
    * @return java.lang.String
    */
	public void setProratetoinv(java.lang.String proratetoinv) {
	   this.proratetoinv = proratetoinv;
	}
	
	
    /**
    * 已分摊
    * @return java.lang.String
    */
	public java.lang.String getProrated() {
		if(this.prorated==null || this.prorated.length()<=0)
	  		return null;
	  	else
	  		return this.prorated;
	}
	
	/**
    * 已分摊
    * @return java.lang.String
    */
	public void setProrated(java.lang.String prorated) {
	   this.prorated = prorated;
	}
	
	
    /**
    * 已分摊的服务成本
    * @return java.lang.Double
    */
	public java.lang.Double getProratecost() {
	  		return this.proratecost;
	}
	
	/**
    * 已分摊的服务成本
    * @return java.lang.Double
    */
	public void setProratecost(java.lang.Double proratecost) {
	   this.proratecost = proratecost;
	}
	
	
    /**
    * 含税成本
    * @return java.lang.Double
    */
	public java.lang.Double getTaxlinecost() {
	  		return this.taxlinecost;
	}
	
	/**
    * 含税成本
    * @return java.lang.Double
    */
	public void setTaxlinecost(java.lang.Double taxlinecost) {
	   this.taxlinecost = taxlinecost;
	}
	
	
    /**
    * 转换系数
    * @return java.lang.Double
    */
	public java.lang.Double getConversion() {
	  		return this.conversion;
	}
	
	/**
    * 转换系数
    * @return java.lang.Double
    */
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
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
    * 合同付款类型
    * @return java.lang.String
    */
	public java.lang.String getPaytype() {
		if(this.paytype==null || this.paytype.length()<=0)
	  		return null;
	  	else
	  		return this.paytype;
	}
	
	/**
    * 合同付款类型
    * @return java.lang.String
    */
	public void setPaytype(java.lang.String paytype) {
	   this.paytype = paytype;
	}
	
	
}