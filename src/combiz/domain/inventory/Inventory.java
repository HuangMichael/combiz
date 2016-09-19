package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Inventory extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double sumreadyqty;
     private java.lang.String vendor;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.Double minlevel;
     private java.lang.Double maxlevel;
     private java.lang.String stocktype;
     private java.lang.String orderunit;
     private java.lang.String issueunit;
     private java.lang.Double conversion;
     private java.lang.Double orderqty;
     private java.lang.Double stdcost;
     private java.lang.Double avgcost;
     private java.lang.Double lastcost;
     private java.util.Date lastissuedate;
     private java.lang.Double issueytd;
     private java.lang.String abctype;
     private java.lang.Double sstock;
     private java.lang.Double sumresqty;
     private java.lang.String warehouse;
     private java.lang.String binnum;
     private java.lang.Double sumpoorderqty;
     private java.lang.String itemdesc;
     private java.lang.Double invcurbal;
     private java.lang.Double invphycnt;
     private java.util.Date invphydate;
     private java.lang.String itemnum;
     private java.lang.Double sumcurbal;
     
    /** default constructor */
    public Inventory(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 可用余量_虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getSumreadyqty() {
	  		return this.sumreadyqty;
	}
	
	/**
    * 可用余量_虚拟字段
    * @return java.lang.Double
    */
	public void setSumreadyqty(java.lang.Double sumreadyqty) {
	   this.sumreadyqty = sumreadyqty;
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
    * 重订购点
    * @return java.lang.Double
    */
	public java.lang.Double getMinlevel() {
	  		return this.minlevel;
	}
	
	/**
    * 重订购点
    * @return java.lang.Double
    */
	public void setMinlevel(java.lang.Double minlevel) {
	   this.minlevel = minlevel;
	}
	
	
    /**
    * 最大库存
    * @return java.lang.Double
    */
	public java.lang.Double getMaxlevel() {
	  		return this.maxlevel;
	}
	
	/**
    * 最大库存
    * @return java.lang.Double
    */
	public void setMaxlevel(java.lang.Double maxlevel) {
	   this.maxlevel = maxlevel;
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
    * 发放单位
    * @return java.lang.String
    */
	public java.lang.String getIssueunit() {
		if(this.issueunit==null || this.issueunit.length()<=0)
	  		return null;
	  	else
	  		return this.issueunit;
	}
	
	/**
    * 发放单位
    * @return java.lang.String
    */
	public void setIssueunit(java.lang.String issueunit) {
	   this.issueunit = issueunit;
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
    * 标准成本
    * @return java.lang.Double
    */
	public java.lang.Double getStdcost() {
	  		return this.stdcost;
	}
	
	/**
    * 标准成本
    * @return java.lang.Double
    */
	public void setStdcost(java.lang.Double stdcost) {
	   this.stdcost = stdcost;
	}
	
	
    /**
    * 平均成本
    * @return java.lang.Double
    */
	public java.lang.Double getAvgcost() {
	  		return this.avgcost;
	}
	
	/**
    * 平均成本
    * @return java.lang.Double
    */
	public void setAvgcost(java.lang.Double avgcost) {
	   this.avgcost = avgcost;
	}
	
	
    /**
    * 上次接收成本
    * @return java.lang.Double
    */
	public java.lang.Double getLastcost() {
	  		return this.lastcost;
	}
	
	/**
    * 上次接收成本
    * @return java.lang.Double
    */
	public void setLastcost(java.lang.Double lastcost) {
	   this.lastcost = lastcost;
	}
	
	
    /**
    * 最近一次发放日期
    * @return java.util.Date
    */
	public java.util.Date getLastissuedate() {
	  		return this.lastissuedate;
	}
	
	/**
    * 最近一次发放日期
    * @return java.util.Date
    */
	public void setLastissuedate(java.util.Date lastissuedate) {
	   this.lastissuedate = lastissuedate;
	}
	
	
    /**
    * 年累计发放数量
    * @return java.lang.Double
    */
	public java.lang.Double getIssueytd() {
	  		return this.issueytd;
	}
	
	/**
    * 年累计发放数量
    * @return java.lang.Double
    */
	public void setIssueytd(java.lang.Double issueytd) {
	   this.issueytd = issueytd;
	}
	
	
    /**
    * ABC分类
    * @return java.lang.String
    */
	public java.lang.String getAbctype() {
		if(this.abctype==null || this.abctype.length()<=0)
	  		return null;
	  	else
	  		return this.abctype;
	}
	
	/**
    * ABC分类
    * @return java.lang.String
    */
	public void setAbctype(java.lang.String abctype) {
	   this.abctype = abctype;
	}
	
	
    /**
    * 库存类型
    * @return java.lang.Double
    */
	public java.lang.Double getSstock() {
	  		return this.sstock;
	}
	
	/**
    * 库存类型
    * @return java.lang.Double
    */
	public void setSstock(java.lang.Double sstock) {
	   this.sstock = sstock;
	}
	
	
    /**
    * 预留数量_虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getSumresqty() {
	  		return this.sumresqty;
	}
	
	/**
    * 预留数量_虚拟字段
    * @return java.lang.Double
    */
	public void setSumresqty(java.lang.Double sumresqty) {
	   this.sumresqty = sumresqty;
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
    * 默认箱柜
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * 默认箱柜
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * 正采购数量_虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getSumpoorderqty() {
	  		return this.sumpoorderqty;
	}
	
	/**
    * 正采购数量_虚拟字段
    * @return java.lang.Double
    */
	public void setSumpoorderqty(java.lang.Double sumpoorderqty) {
	   this.sumpoorderqty = sumpoorderqty;
	}
	
	
    /**
    * 库存描述
    * @return java.lang.String
    */
	public java.lang.String getItemdesc() {
		if(this.itemdesc==null || this.itemdesc.length()<=0)
	  		return null;
	  	else
	  		return this.itemdesc;
	}
	
	/**
    * 库存描述
    * @return java.lang.String
    */
	public void setItemdesc(java.lang.String itemdesc) {
	   this.itemdesc = itemdesc;
	}
	
	
    /**
    * 余量虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getInvcurbal() {
	  		return this.invcurbal;
	}
	
	/**
    * 余量虚拟字段
    * @return java.lang.Double
    */
	public void setInvcurbal(java.lang.Double invcurbal) {
	   this.invcurbal = invcurbal;
	}
	
	
    /**
    * 物理盘点虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getInvphycnt() {
	  		return this.invphycnt;
	}
	
	/**
    * 物理盘点虚拟字段
    * @return java.lang.Double
    */
	public void setInvphycnt(java.lang.Double invphycnt) {
	   this.invphycnt = invphycnt;
	}
	
	
    /**
    * 盘点时间虚拟字段
    * @return java.util.Date
    */
	public java.util.Date getInvphydate() {
	  		return this.invphydate;
	}
	
	/**
    * 盘点时间虚拟字段
    * @return java.util.Date
    */
	public void setInvphydate(java.util.Date invphydate) {
	   this.invphydate = invphydate;
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
    * 库存余量_虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getSumcurbal() {
	  		return this.sumcurbal;
	}
	
	/**
    * 库存余量_虚拟字段
    * @return java.lang.Double
    */
	public void setSumcurbal(java.lang.Double sumcurbal) {
	   this.sumcurbal = sumcurbal;
	}
	
	
}