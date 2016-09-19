package combiz.domain.contract;

import combiz.system.IBOBaseObject;

public class Contline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String cntnum;
     private java.lang.Long linenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String warehouse;
     private java.lang.Double orderqty;
     private java.lang.String orderunit;
     private java.lang.Double conversion;
     private java.lang.Double unitcost;
     private java.lang.Double taxunitcost;
     private java.lang.Double linecost;
     private java.lang.Double taxlinecost;
     private java.lang.Double receivedqty;
     private java.lang.Double receivedunitcost;
     private java.lang.Double receivedtotalcost;
     private java.lang.String taxcode;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.Double rejectedqty;
     private java.util.Date vendeliverydate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     private java.lang.String requestedby;
     private java.util.Date reqdeliverydate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String service;
     private java.lang.String stocktype;
     private java.lang.String remark;
     private java.lang.String location;
     private java.lang.String receiptscomplete;
     private java.lang.String inspection;
     private java.lang.Double loadedcost;
     private java.lang.String prorated;
     private java.lang.Double proratecost;
     private java.lang.String ponum;
     
    /** default constructor */
    public Contline(){}
    
   
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
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * 合同编号
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * 行号
    * @return java.lang.Long
    */
	public java.lang.Long getLinenum() {
	  		return this.linenum;
	}
	
	/**
    * 行号
    * @return java.lang.Long
    */
	public void setLinenum(java.lang.Long linenum) {
	   this.linenum = linenum;
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
    * 订购数量
    * @return java.lang.Double
    */
	public java.lang.Double getOrderqty() {
	  		return this.orderqty;
	}
	
	/**
    * 订购数量
    * @return java.lang.Double
    */
	public void setOrderqty(java.lang.Double orderqty) {
	   this.orderqty = orderqty;
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
    * 合同版本
    * @return java.lang.Double
    */
	public java.lang.Double getConversion() {
	  		return this.conversion;
	}
	
	/**
    * 合同版本
    * @return java.lang.Double
    */
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
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
    * 含税行成本
    * @return java.lang.Double
    */
	public java.lang.Double getTaxlinecost() {
	  		return this.taxlinecost;
	}
	
	/**
    * 含税行成本
    * @return java.lang.Double
    */
	public void setTaxlinecost(java.lang.Double taxlinecost) {
	   this.taxlinecost = taxlinecost;
	}
	
	
    /**
    * 接收数量
    * @return java.lang.Double
    */
	public java.lang.Double getReceivedqty() {
	  		return this.receivedqty;
	}
	
	/**
    * 接收数量
    * @return java.lang.Double
    */
	public void setReceivedqty(java.lang.Double receivedqty) {
	   this.receivedqty = receivedqty;
	}
	
	
    /**
    * 接收单位成本
    * @return java.lang.Double
    */
	public java.lang.Double getReceivedunitcost() {
	  		return this.receivedunitcost;
	}
	
	/**
    * 接收单位成本
    * @return java.lang.Double
    */
	public void setReceivedunitcost(java.lang.Double receivedunitcost) {
	   this.receivedunitcost = receivedunitcost;
	}
	
	
    /**
    * 接收总成本
    * @return java.lang.Double
    */
	public java.lang.Double getReceivedtotalcost() {
	  		return this.receivedtotalcost;
	}
	
	/**
    * 接收总成本
    * @return java.lang.Double
    */
	public void setReceivedtotalcost(java.lang.Double receivedtotalcost) {
	   this.receivedtotalcost = receivedtotalcost;
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
    * 拒收数量
    * @return java.lang.Double
    */
	public java.lang.Double getRejectedqty() {
	  		return this.rejectedqty;
	}
	
	/**
    * 拒收数量
    * @return java.lang.Double
    */
	public void setRejectedqty(java.lang.Double rejectedqty) {
	   this.rejectedqty = rejectedqty;
	}
	
	
    /**
    * 供应商发货日期
    * @return java.util.Date
    */
	public java.util.Date getVendeliverydate() {
	  		return this.vendeliverydate;
	}
	
	/**
    * 供应商发货日期
    * @return java.util.Date
    */
	public void setVendeliverydate(java.util.Date vendeliverydate) {
	   this.vendeliverydate = vendeliverydate;
	}
	
	
    /**
    * 录入日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 录入日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
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
    * 申请人
    * @return java.lang.String
    */
	public java.lang.String getRequestedby() {
		if(this.requestedby==null || this.requestedby.length()<=0)
	  		return null;
	  	else
	  		return this.requestedby;
	}
	
	/**
    * 申请人
    * @return java.lang.String
    */
	public void setRequestedby(java.lang.String requestedby) {
	   this.requestedby = requestedby;
	}
	
	
    /**
    * 要求的交付日期
    * @return java.util.Date
    */
	public java.util.Date getReqdeliverydate() {
	  		return this.reqdeliverydate;
	}
	
	/**
    * 要求的交付日期
    * @return java.util.Date
    */
	public void setReqdeliverydate(java.util.Date reqdeliverydate) {
	   this.reqdeliverydate = reqdeliverydate;
	}
	
	
    /**
    * 制造商
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * 制造商
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * 制造商型号
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * 制造商型号
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
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
    * 库存类型
    * @return java.lang.String
    */
	public java.lang.String getStocktype() {
		if(this.stocktype==null || this.stocktype.length()<=0)
	  		return null;
	  	else
	  		return this.stocktype;
	}
	
	/**
    * 库存类型
    * @return java.lang.String
    */
	public void setStocktype(java.lang.String stocktype) {
	   this.stocktype = stocktype;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * 位置
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 位置
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 接受完成
    * @return java.lang.String
    */
	public java.lang.String getReceiptscomplete() {
		if(this.receiptscomplete==null || this.receiptscomplete.length()<=0)
	  		return null;
	  	else
	  		return this.receiptscomplete;
	}
	
	/**
    * 接受完成
    * @return java.lang.String
    */
	public void setReceiptscomplete(java.lang.String receiptscomplete) {
	   this.receiptscomplete = receiptscomplete;
	}
	
	
    /**
    * 是否检验
    * @return java.lang.String
    */
	public java.lang.String getInspection() {
		if(this.inspection==null || this.inspection.length()<=0)
	  		return null;
	  	else
	  		return this.inspection;
	}
	
	/**
    * 是否检验
    * @return java.lang.String
    */
	public void setInspection(java.lang.String inspection) {
	   this.inspection = inspection;
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
    * 分摊服务成本
    * @return java.lang.Double
    */
	public java.lang.Double getProratecost() {
	  		return this.proratecost;
	}
	
	/**
    * 分摊服务成本
    * @return java.lang.Double
    */
	public void setProratecost(java.lang.Double proratecost) {
	   this.proratecost = proratecost;
	}
	
	
    /**
    * 采购单号
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * 采购单号
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
}