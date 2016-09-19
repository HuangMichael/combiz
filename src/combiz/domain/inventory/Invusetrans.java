package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invusetrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double actualcost;
     private java.util.Date actualdate;
     private java.lang.String binnum;
     private java.lang.Double conversion;
     private java.lang.Double curbal;
     private java.lang.String description;
     private java.lang.String enterby;
     private java.lang.String eqnum;
     private java.lang.Double exchangerate;
     private java.lang.Long invrectransid;
     private java.lang.Long issueid;
     private java.lang.String issuetolabor;
     private java.lang.String issuetype;
     private java.lang.String itemnum;
     private java.lang.Double linecost;
     private java.lang.String location;
     private java.lang.String lotnum;
     private java.lang.String matreqnum;
     private java.lang.String memo;
     private java.lang.String packnum;
     private java.lang.Double physcnt;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.Double quantity;
     private java.lang.Double reqqty;
     private java.lang.String roteqnum;
     private java.util.Date transdate;
     private java.lang.Double unitcost;
     private java.lang.String warehouse;
     private java.lang.String wonum;
     private java.lang.String issuedeptnum;
     private java.lang.String state;
     private java.util.Date requestdate;
     private java.util.Date usedate;
     private java.lang.String isprint;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Invusetrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 实际单价
    * @return java.lang.Double
    */
	public java.lang.Double getActualcost() {
	  		return this.actualcost;
	}
	
	/**
    * 实际单价
    * @return java.lang.Double
    */
	public void setActualcost(java.lang.Double actualcost) {
	   this.actualcost = actualcost;
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
    * 箱柜编号
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * 箱柜编号
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
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
    * 当前余量
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * 当前余量
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
	}
	
	
    /**
    * 库存项目描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 库存项目描述
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
    * 汇率
    * @return java.lang.Double
    */
	public java.lang.Double getExchangerate() {
	  		return this.exchangerate;
	}
	
	/**
    * 汇率
    * @return java.lang.Double
    */
	public void setExchangerate(java.lang.Double exchangerate) {
	   this.exchangerate = exchangerate;
	}
	
	
    /**
    * 接收行
    * @return java.lang.Long
    */
	public java.lang.Long getInvrectransid() {
	  		return this.invrectransid;
	}
	
	/**
    * 接收行
    * @return java.lang.Long
    */
	public void setInvrectransid(java.lang.Long invrectransid) {
	   this.invrectransid = invrectransid;
	}
	
	
    /**
    * 退料情况
    * @return java.lang.Long
    */
	public java.lang.Long getIssueid() {
	  		return this.issueid;
	}
	
	/**
    * 退料情况
    * @return java.lang.Long
    */
	public void setIssueid(java.lang.Long issueid) {
	   this.issueid = issueid;
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
    * 发放类型
    * @return java.lang.String
    */
	public java.lang.String getIssuetype() {
		if(this.issuetype==null || this.issuetype.length()<=0)
	  		return null;
	  	else
	  		return this.issuetype;
	}
	
	/**
    * 发放类型
    * @return java.lang.String
    */
	public void setIssuetype(java.lang.String issuetype) {
	   this.issuetype = issuetype;
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
    * 发放总价
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * 发放总价
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
    * 批次编号
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * 批次编号
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
	}
	
	
    /**
    * 领用申请单编号
    * @return java.lang.String
    */
	public java.lang.String getMatreqnum() {
		if(this.matreqnum==null || this.matreqnum.length()<=0)
	  		return null;
	  	else
	  		return this.matreqnum;
	}
	
	/**
    * 领用申请单编号
    * @return java.lang.String
    */
	public void setMatreqnum(java.lang.String matreqnum) {
	   this.matreqnum = matreqnum;
	}
	
	
    /**
    * 备忘
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * 备忘
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * 装箱单编号
    * @return java.lang.String
    */
	public java.lang.String getPacknum() {
		if(this.packnum==null || this.packnum.length()<=0)
	  		return null;
	  	else
	  		return this.packnum;
	}
	
	/**
    * 装箱单编号
    * @return java.lang.String
    */
	public void setPacknum(java.lang.String packnum) {
	   this.packnum = packnum;
	}
	
	
    /**
    * 实际盘点数量
    * @return java.lang.Double
    */
	public java.lang.Double getPhyscnt() {
	  		return this.physcnt;
	}
	
	/**
    * 实际盘点数量
    * @return java.lang.Double
    */
	public void setPhyscnt(java.lang.Double physcnt) {
	   this.physcnt = physcnt;
	}
	
	
    /**
    * 采购单行
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * 采购单行
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
    * 交易数量
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * 交易数量
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
    /**
    * 申请数量
    * @return java.lang.Double
    */
	public java.lang.Double getReqqty() {
	  		return this.reqqty;
	}
	
	/**
    * 申请数量
    * @return java.lang.Double
    */
	public void setReqqty(java.lang.Double reqqty) {
	   this.reqqty = reqqty;
	}
	
	
    /**
    * 周转设备编号
    * @return java.lang.String
    */
	public java.lang.String getRoteqnum() {
		if(this.roteqnum==null || this.roteqnum.length()<=0)
	  		return null;
	  	else
	  		return this.roteqnum;
	}
	
	/**
    * 周转设备编号
    * @return java.lang.String
    */
	public void setRoteqnum(java.lang.String roteqnum) {
	   this.roteqnum = roteqnum;
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
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getState() {
		if(this.state==null || this.state.length()<=0)
	  		return null;
	  	else
	  		return this.state;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setState(java.lang.String state) {
	   this.state = state;
	}
	
	
    /**
    * 借用日期
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * 借用日期
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
    /**
    * 归还日期
    * @return java.util.Date
    */
	public java.util.Date getUsedate() {
	  		return this.usedate;
	}
	
	/**
    * 归还日期
    * @return java.util.Date
    */
	public void setUsedate(java.util.Date usedate) {
	   this.usedate = usedate;
	}
	
	
    /**
    * 是否已经打印
    * @return java.lang.String
    */
	public java.lang.String getIsprint() {
		if(this.isprint==null || this.isprint.length()<=0)
	  		return null;
	  	else
	  		return this.isprint;
	}
	
	/**
    * 是否已经打印
    * @return java.lang.String
    */
	public void setIsprint(java.lang.String isprint) {
	   this.isprint = isprint;
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