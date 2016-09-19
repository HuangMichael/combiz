package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invreserve extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double actualqty;
     private java.lang.String buditem;
     private java.lang.String budnum;
     private java.lang.String directreq;
     private java.lang.String eqnum;
     private java.lang.String issuedeptnum;
     private java.lang.String issuetolabor;
     private java.lang.String itemnum;
     private java.lang.String location;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.String reqby;
     private java.util.Date reqdate;
     private java.lang.String reqnum;
     private java.util.Date requireddate;
     private java.lang.Double reservedqty;
     private java.lang.String warehouse;
     private java.lang.String wonum;
     
    /** default constructor */
    public Invreserve(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 实际发放数量_虚拟字段
    * @return java.lang.Double
    */
	public java.lang.Double getActualqty() {
	  		return this.actualqty;
	}
	
	/**
    * 实际发放数量_虚拟字段
    * @return java.lang.Double
    */
	public void setActualqty(java.lang.Double actualqty) {
	   this.actualqty = actualqty;
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
	
	
    /**
    * 直接申请？
    * @return java.lang.String
    */
	public java.lang.String getDirectreq() {
		if(this.directreq==null || this.directreq.length()<=0)
	  		return null;
	  	else
	  		return this.directreq;
	}
	
	/**
    * 直接申请？
    * @return java.lang.String
    */
	public void setDirectreq(java.lang.String directreq) {
	   this.directreq = directreq;
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
    * 发放至部门
    * @return java.lang.String
    */
	public java.lang.String getIssuedeptnum() {
		if(this.issuedeptnum==null || this.issuedeptnum.length()<=0)
	  		return null;
	  	else
	  		return this.issuedeptnum;
	}
	
	/**
    * 发放至部门
    * @return java.lang.String
    */
	public void setIssuedeptnum(java.lang.String issuedeptnum) {
	   this.issuedeptnum = issuedeptnum;
	}
	
	
    /**
    * 发至员工
    * @return java.lang.String
    */
	public java.lang.String getIssuetolabor() {
		if(this.issuetolabor==null || this.issuetolabor.length()<=0)
	  		return null;
	  	else
	  		return this.issuetolabor;
	}
	
	/**
    * 发至员工
    * @return java.lang.String
    */
	public void setIssuetolabor(java.lang.String issuetolabor) {
	   this.issuetolabor = issuetolabor;
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
    * 预留位置
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 预留位置
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 预留的采购单行
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * 预留的采购单行
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * 预留的采购单
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * 预留的采购单
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * 申请人
    * @return java.lang.String
    */
	public java.lang.String getReqby() {
		if(this.reqby==null || this.reqby.length()<=0)
	  		return null;
	  	else
	  		return this.reqby;
	}
	
	/**
    * 申请人
    * @return java.lang.String
    */
	public void setReqby(java.lang.String reqby) {
	   this.reqby = reqby;
	}
	
	
    /**
    * 申请的时间
    * @return java.util.Date
    */
	public java.util.Date getReqdate() {
	  		return this.reqdate;
	}
	
	/**
    * 申请的时间
    * @return java.util.Date
    */
	public void setReqdate(java.util.Date reqdate) {
	   this.reqdate = reqdate;
	}
	
	
    /**
    * 申请编号
    * @return java.lang.String
    */
	public java.lang.String getReqnum() {
		if(this.reqnum==null || this.reqnum.length()<=0)
	  		return null;
	  	else
	  		return this.reqnum;
	}
	
	/**
    * 申请编号
    * @return java.lang.String
    */
	public void setReqnum(java.lang.String reqnum) {
	   this.reqnum = reqnum;
	}
	
	
    /**
    * 需求日期
    * @return java.util.Date
    */
	public java.util.Date getRequireddate() {
	  		return this.requireddate;
	}
	
	/**
    * 需求日期
    * @return java.util.Date
    */
	public void setRequireddate(java.util.Date requireddate) {
	   this.requireddate = requireddate;
	}
	
	
    /**
    * 预留数量
    * @return java.lang.Double
    */
	public java.lang.Double getReservedqty() {
	  		return this.reservedqty;
	}
	
	/**
    * 预留数量
    * @return java.lang.Double
    */
	public void setReservedqty(java.lang.Double reservedqty) {
	   this.reservedqty = reservedqty;
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
	
	
}