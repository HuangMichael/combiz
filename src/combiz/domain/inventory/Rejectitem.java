package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Rejectitem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String binnum;
     private java.lang.String custodian;
     private java.lang.String deptnum;
     private java.lang.String eqnum;
     private java.lang.Long invstockid;
     private java.lang.String isreject;
     private java.lang.String itemnum;
     private java.lang.String labornum;
     private java.lang.String location;
     private java.lang.String lotnum;
     private java.lang.String memo;
     private java.lang.String rejectnum;
     private java.lang.Double rejectqty;
     private java.lang.Long rejlinenum;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Rejectitem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 箱柜
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * 箱柜
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * 使用人
    * @return java.lang.String
    */
	public java.lang.String getCustodian() {
		if(this.custodian==null || this.custodian.length()<=0)
	  		return null;
	  	else
	  		return this.custodian;
	}
	
	/**
    * 使用人
    * @return java.lang.String
    */
	public void setCustodian(java.lang.String custodian) {
	   this.custodian = custodian;
	}
	
	
    /**
    * 资产目前所在部门
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * 资产目前所在部门
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * 设备编号
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * 设备编号
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * 拷贝的源记录ID
    * @return java.lang.Long
    */
	public java.lang.Long getInvstockid() {
	  		return this.invstockid;
	}
	
	/**
    * 拷贝的源记录ID
    * @return java.lang.Long
    */
	public void setInvstockid(java.lang.Long invstockid) {
	   this.invstockid = invstockid;
	}
	
	
    /**
    * 是否报废
    * @return java.lang.String
    */
	public java.lang.String getIsreject() {
		if(this.isreject==null || this.isreject.length()<=0)
	  		return null;
	  	else
	  		return this.isreject;
	}
	
	/**
    * 是否报废
    * @return java.lang.String
    */
	public void setIsreject(java.lang.String isreject) {
	   this.isreject = isreject;
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
    * 责任人
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * 责任人
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * 资产目前所在位置
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 资产目前所在位置
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
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
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * 主表报废单编号
    * @return java.lang.String
    */
	public java.lang.String getRejectnum() {
		if(this.rejectnum==null || this.rejectnum.length()<=0)
	  		return null;
	  	else
	  		return this.rejectnum;
	}
	
	/**
    * 主表报废单编号
    * @return java.lang.String
    */
	public void setRejectnum(java.lang.String rejectnum) {
	   this.rejectnum = rejectnum;
	}
	
	
    /**
    * 报废数量
    * @return java.lang.Double
    */
	public java.lang.Double getRejectqty() {
	  		return this.rejectqty;
	}
	
	/**
    * 报废数量
    * @return java.lang.Double
    */
	public void setRejectqty(java.lang.Double rejectqty) {
	   this.rejectqty = rejectqty;
	}
	
	
    /**
    * 报废单明细行号
    * @return java.lang.Long
    */
	public java.lang.Long getRejlinenum() {
	  		return this.rejlinenum;
	}
	
	/**
    * 报废单明细行号
    * @return java.lang.Long
    */
	public void setRejlinenum(java.lang.Long rejlinenum) {
	   this.rejlinenum = rejlinenum;
	}
	
	
    /**
    * 库房
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * 库房
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}