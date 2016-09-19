package combiz.domain.rfq;

import combiz.system.IBOBaseObject;

public class Rfqline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String inspection;
     private java.lang.String isservice;
     private java.lang.String itemnum;
     private java.lang.Double linecost;
     private java.lang.String location;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.Double orderqty;
     private java.lang.String orderunit;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.String remark;
     private java.util.Date reqdeliverydate;
     private java.lang.Long rfqlinenum;
     private java.lang.String rfqnum;
     private java.lang.String stocktype;
     private java.lang.String tasknum;
     private java.util.Date awarddate;
     private java.lang.Double conversion;
     private java.lang.String description;
     private java.lang.String enterby;
     private java.util.Date enterdate;
     private java.lang.String eqnum;
     private java.lang.Double unitcost;
     private java.lang.String vendor;
     private java.lang.String warehouse;
     private java.lang.String wonum;
     
    /** default constructor */
    public Rfqline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 需要检查
    * @return java.lang.String
    */
	public java.lang.String getInspection() {
		if(this.inspection==null || this.inspection.length()<=0)
	  		return null;
	  	else
	  		return this.inspection;
	}
	
	/**
    * 需要检查
    * @return java.lang.String
    */
	public void setInspection(java.lang.String inspection) {
	   this.inspection = inspection;
	}
	
	
    /**
    * 服务
    * @return java.lang.String
    */
	public java.lang.String getIsservice() {
		if(this.isservice==null || this.isservice.length()<=0)
	  		return null;
	  	else
	  		return this.isservice;
	}
	
	/**
    * 服务
    * @return java.lang.String
    */
	public void setIsservice(java.lang.String isservice) {
	   this.isservice = isservice;
	}
	
	
    /**
    * 库存项目
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存项目
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * 供应商型号
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * 供应商型号
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
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
    * 订单行
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * 订单行
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * 采购单
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * 采购单
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
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
    * 到货日期
    * @return java.util.Date
    */
	public java.util.Date getReqdeliverydate() {
	  		return this.reqdeliverydate;
	}
	
	/**
    * 到货日期
    * @return java.util.Date
    */
	public void setReqdeliverydate(java.util.Date reqdeliverydate) {
	   this.reqdeliverydate = reqdeliverydate;
	}
	
	
    /**
    * 询价行
    * @return java.lang.Long
    */
	public java.lang.Long getRfqlinenum() {
	  		return this.rfqlinenum;
	}
	
	/**
    * 询价行
    * @return java.lang.Long
    */
	public void setRfqlinenum(java.lang.Long rfqlinenum) {
	   this.rfqlinenum = rfqlinenum;
	}
	
	
    /**
    * 询价单
    * @return java.lang.String
    */
	public java.lang.String getRfqnum() {
		if(this.rfqnum==null || this.rfqnum.length()<=0)
	  		return null;
	  	else
	  		return this.rfqnum;
	}
	
	/**
    * 询价单
    * @return java.lang.String
    */
	public void setRfqnum(java.lang.String rfqnum) {
	   this.rfqnum = rfqnum;
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
    * 授予日期
    * @return java.util.Date
    */
	public java.util.Date getAwarddate() {
	  		return this.awarddate;
	}
	
	/**
    * 授予日期
    * @return java.util.Date
    */
	public void setAwarddate(java.util.Date awarddate) {
	   this.awarddate = awarddate;
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
    * 录入时间
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 录入时间
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 设备
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 设备
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 单价
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * 单价
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
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
	
	
    /**
    * 工单
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * 工单
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}