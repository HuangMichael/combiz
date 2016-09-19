package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invrectrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String statuschangeby;
     private java.lang.String wonum;
     private java.lang.String location;
     private java.lang.String eqnum;
     private java.lang.String tasknum;
     private java.lang.String rejectcode;
     private java.lang.Double rejectqty;
     private java.lang.Double conversion;
     private java.lang.String changeby;
     private java.lang.String issuetolabor;
     private java.lang.String packnum;
     private java.lang.String reqby;
     private java.lang.Double curbal;
     private java.lang.String tobin;
     private java.lang.Double exchangerate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String remark;
     private java.lang.String fromwarehouse;
     private java.lang.String frombin;
     private java.lang.String fromlot;
     private java.lang.String tolot;
     private java.lang.Double loadedcost;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String taxcode;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String invoicenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String towarehouse;
     private java.util.Date transdate;
     private java.util.Date actualdate;
     private java.lang.Double quantity;
     private java.lang.String recunit;
     private java.lang.String rectype;
     private java.lang.Double unitcost;
     private java.lang.Double linecost;
     private java.lang.Double actualcost;
     private java.lang.Double oldavgcost;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Invrectrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatuschangeby() {
		if(this.statuschangeby==null || this.statuschangeby.length()<=0)
	  		return null;
	  	else
	  		return this.statuschangeby;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatuschangeby(java.lang.String statuschangeby) {
	   this.statuschangeby = statuschangeby;
	}
	
	
    /**
    * 工单号
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * 工单号
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
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
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
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
    * 项目编号
    * @return java.lang.String
    */
	public java.lang.String getRejectcode() {
		if(this.rejectcode==null || this.rejectcode.length()<=0)
	  		return null;
	  	else
	  		return this.rejectcode;
	}
	
	/**
    * 项目编号
    * @return java.lang.String
    */
	public void setRejectcode(java.lang.String rejectcode) {
	   this.rejectcode = rejectcode;
	}
	
	
    /**
    * 拒收数量
    * @return java.lang.Double
    */
	public java.lang.Double getRejectqty() {
	  		return this.rejectqty;
	}
	
	/**
    * 拒收数量
    * @return java.lang.Double
    */
	public void setRejectqty(java.lang.Double rejectqty) {
	   this.rejectqty = rejectqty;
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
    * 修改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 修改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 发放人员
    * @return java.lang.String
    */
	public java.lang.String getIssuetolabor() {
		if(this.issuetolabor==null || this.issuetolabor.length()<=0)
	  		return null;
	  	else
	  		return this.issuetolabor;
	}
	
	/**
    * 发放人员
    * @return java.lang.String
    */
	public void setIssuetolabor(java.lang.String issuetolabor) {
	   this.issuetolabor = issuetolabor;
	}
	
	
    /**
    * 字段PACKNUM
    * @return java.lang.String
    */
	public java.lang.String getPacknum() {
		if(this.packnum==null || this.packnum.length()<=0)
	  		return null;
	  	else
	  		return this.packnum;
	}
	
	/**
    * 字段PACKNUM
    * @return java.lang.String
    */
	public void setPacknum(java.lang.String packnum) {
	   this.packnum = packnum;
	}
	
	
    /**
    * 接收人
    * @return java.lang.String
    */
	public java.lang.String getReqby() {
		if(this.reqby==null || this.reqby.length()<=0)
	  		return null;
	  	else
	  		return this.reqby;
	}
	
	/**
    * 接收人
    * @return java.lang.String
    */
	public void setReqby(java.lang.String reqby) {
	   this.reqby = reqby;
	}
	
	
    /**
    * 余量
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * 余量
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
	}
	
	
    /**
    * 箱柜
    * @return java.lang.String
    */
	public java.lang.String getTobin() {
		if(this.tobin==null || this.tobin.length()<=0)
	  		return null;
	  	else
	  		return this.tobin;
	}
	
	/**
    * 箱柜
    * @return java.lang.String
    */
	public void setTobin(java.lang.String tobin) {
	   this.tobin = tobin;
	}
	
	
    /**
    * 转换系数
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * 转换系数
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
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
    * 源仓库
    * @return java.lang.String
    */
	public java.lang.String getFromwarehouse() {
		if(this.fromwarehouse==null || this.fromwarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.fromwarehouse;
	}
	
	/**
    * 源仓库
    * @return java.lang.String
    */
	public void setFromwarehouse(java.lang.String fromwarehouse) {
	   this.fromwarehouse = fromwarehouse;
	}
	
	
    /**
    * 源箱柜
    * @return java.lang.String
    */
	public java.lang.String getFrombin() {
		if(this.frombin==null || this.frombin.length()<=0)
	  		return null;
	  	else
	  		return this.frombin;
	}
	
	/**
    * 源箱柜
    * @return java.lang.String
    */
	public void setFrombin(java.lang.String frombin) {
	   this.frombin = frombin;
	}
	
	
    /**
    * 源批次
    * @return java.lang.String
    */
	public java.lang.String getFromlot() {
		if(this.fromlot==null || this.fromlot.length()<=0)
	  		return null;
	  	else
	  		return this.fromlot;
	}
	
	/**
    * 源批次
    * @return java.lang.String
    */
	public void setFromlot(java.lang.String fromlot) {
	   this.fromlot = fromlot;
	}
	
	
    /**
    * 目的批次
    * @return java.lang.String
    */
	public java.lang.String getTolot() {
		if(this.tolot==null || this.tolot.length()<=0)
	  		return null;
	  	else
	  		return this.tolot;
	}
	
	/**
    * 目的批次
    * @return java.lang.String
    */
	public void setTolot(java.lang.String tolot) {
	   this.tolot = tolot;
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
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
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
    * 发票编码
    * @return java.lang.String
    */
	public java.lang.String getInvoicenum() {
		if(this.invoicenum==null || this.invoicenum.length()<=0)
	  		return null;
	  	else
	  		return this.invoicenum;
	}
	
	/**
    * 发票编码
    * @return java.lang.String
    */
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
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
    * 目的仓库
    * @return java.lang.String
    */
	public java.lang.String getTowarehouse() {
		if(this.towarehouse==null || this.towarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.towarehouse;
	}
	
	/**
    * 目的仓库
    * @return java.lang.String
    */
	public void setTowarehouse(java.lang.String towarehouse) {
	   this.towarehouse = towarehouse;
	}
	
	
    /**
    * 交易日期
    * @return java.util.Date
    */
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}
	
	/**
    * 交易日期
    * @return java.util.Date
    */
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	
	
    /**
    * 实际日期
    * @return java.util.Date
    */
	public java.util.Date getActualdate() {
	  		return this.actualdate;
	}
	
	/**
    * 实际日期
    * @return java.util.Date
    */
	public void setActualdate(java.util.Date actualdate) {
	   this.actualdate = actualdate;
	}
	
	
    /**
    * 数量
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * 数量
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
    /**
    * 接收单位
    * @return java.lang.String
    */
	public java.lang.String getRecunit() {
		if(this.recunit==null || this.recunit.length()<=0)
	  		return null;
	  	else
	  		return this.recunit;
	}
	
	/**
    * 接收单位
    * @return java.lang.String
    */
	public void setRecunit(java.lang.String recunit) {
	   this.recunit = recunit;
	}
	
	
    /**
    * 收据类型
    * @return java.lang.String
    */
	public java.lang.String getRectype() {
		if(this.rectype==null || this.rectype.length()<=0)
	  		return null;
	  	else
	  		return this.rectype;
	}
	
	/**
    * 收据类型
    * @return java.lang.String
    */
	public void setRectype(java.lang.String rectype) {
	   this.rectype = rectype;
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
    * 实际成本
    * @return java.lang.Double
    */
	public java.lang.Double getActualcost() {
	  		return this.actualcost;
	}
	
	/**
    * 实际成本
    * @return java.lang.Double
    */
	public void setActualcost(java.lang.Double actualcost) {
	   this.actualcost = actualcost;
	}
	
	
    /**
    * 旧平均成本
    * @return java.lang.Double
    */
	public java.lang.Double getOldavgcost() {
	  		return this.oldavgcost;
	}
	
	/**
    * 旧平均成本
    * @return java.lang.Double
    */
	public void setOldavgcost(java.lang.Double oldavgcost) {
	   this.oldavgcost = oldavgcost;
	}
	
	
    /**
    * 预算项目
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * 预算项目
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * 预算编号
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算编号
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
}