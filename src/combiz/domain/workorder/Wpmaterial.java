package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wpmaterial extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String matreqnum;
     private java.lang.String wonum;
     private java.lang.String jpnum;
     private java.lang.String tasknum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String warehouse;
     private java.lang.Double itemqty;
     private java.lang.Double unitcost;
     private java.lang.Double linecost;
     private java.lang.String vendor;
     private java.util.Date requiredate;
     private java.lang.String requestby;
     private java.lang.String prnum;
     private java.lang.Long prlinenum;
     private java.lang.String issueto;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String orderunit;
     private java.lang.String classid;
     private java.lang.String remark;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Wpmaterial(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 物料编号
    * @return java.lang.String
    */
	public java.lang.String getMatreqnum() {
		if(this.matreqnum==null || this.matreqnum.length()<=0)
	  		return null;
	  	else
	  		return this.matreqnum;
	}
	
	/**
    * 物料编号
    * @return java.lang.String
    */
	public void setMatreqnum(java.lang.String matreqnum) {
	   this.matreqnum = matreqnum;
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
    * 物资编码
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 物资编码
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
    * 数量
    * @return java.lang.Double
    */
	public java.lang.Double getItemqty() {
	  		return this.itemqty;
	}
	
	/**
    * 数量
    * @return java.lang.Double
    */
	public void setItemqty(java.lang.Double itemqty) {
	   this.itemqty = itemqty;
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
    * 申请日期
    * @return java.util.Date
    */
	public java.util.Date getRequiredate() {
	  		return this.requiredate;
	}
	
	/**
    * 申请日期
    * @return java.util.Date
    */
	public void setRequiredate(java.util.Date requiredate) {
	   this.requiredate = requiredate;
	}
	
	
    /**
    * 申请人
    * @return java.lang.String
    */
	public java.lang.String getRequestby() {
		if(this.requestby==null || this.requestby.length()<=0)
	  		return null;
	  	else
	  		return this.requestby;
	}
	
	/**
    * 申请人
    * @return java.lang.String
    */
	public void setRequestby(java.lang.String requestby) {
	   this.requestby = requestby;
	}
	
	
    /**
    * 采购申请
    * @return java.lang.String
    */
	public java.lang.String getPrnum() {
		if(this.prnum==null || this.prnum.length()<=0)
	  		return null;
	  	else
	  		return this.prnum;
	}
	
	/**
    * 采购申请
    * @return java.lang.String
    */
	public void setPrnum(java.lang.String prnum) {
	   this.prnum = prnum;
	}
	
	
    /**
    * 采购申请行
    * @return java.lang.Long
    */
	public java.lang.Long getPrlinenum() {
	  		return this.prlinenum;
	}
	
	/**
    * 采购申请行
    * @return java.lang.Long
    */
	public void setPrlinenum(java.lang.Long prlinenum) {
	   this.prlinenum = prlinenum;
	}
	
	
    /**
    * 发放到
    * @return java.lang.String
    */
	public java.lang.String getIssueto() {
		if(this.issueto==null || this.issueto.length()<=0)
	  		return null;
	  	else
	  		return this.issueto;
	}
	
	/**
    * 发放到
    * @return java.lang.String
    */
	public void setIssueto(java.lang.String issueto) {
	   this.issueto = issueto;
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
    * 规格型号
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * 规格型号
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * 定购单位
    * @return java.lang.String
    */
	public java.lang.String getOrderunit() {
		if(this.orderunit==null || this.orderunit.length()<=0)
	  		return null;
	  	else
	  		return this.orderunit;
	}
	
	/**
    * 定购单位
    * @return java.lang.String
    */
	public void setOrderunit(java.lang.String orderunit) {
	   this.orderunit = orderunit;
	}
	
	
    /**
    * 分类
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 分类
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
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
    * 预算号
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算号
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
}