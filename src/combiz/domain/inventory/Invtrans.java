package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invtrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String itemnum;
     private java.lang.String warehouse;
     private java.util.Date transdate;
     private java.lang.String transtype;
     private java.lang.Double quantity;
     private java.lang.Double curbal;
     private java.lang.Double physcnt;
     private java.lang.Double oldcost;
     private java.lang.Double newcost;
     private java.lang.Double linecost;
     private java.lang.Double conversion;
     private java.lang.String enterby;
     private java.lang.String memo;
     private java.lang.String binnum;
     private java.lang.String lotnum;
     
    /** default constructor */
    public Invtrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * 交易类型
    * @return java.lang.String
    */
	public java.lang.String getTranstype() {
		if(this.transtype==null || this.transtype.length()<=0)
	  		return null;
	  	else
	  		return this.transtype;
	}
	
	/**
    * 交易类型
    * @return java.lang.String
    */
	public void setTranstype(java.lang.String transtype) {
	   this.transtype = transtype;
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
    * 实际数量
    * @return java.lang.Double
    */
	public java.lang.Double getPhyscnt() {
	  		return this.physcnt;
	}
	
	/**
    * 实际数量
    * @return java.lang.Double
    */
	public void setPhyscnt(java.lang.Double physcnt) {
	   this.physcnt = physcnt;
	}
	
	
    /**
    * 旧成本
    * @return java.lang.Double
    */
	public java.lang.Double getOldcost() {
	  		return this.oldcost;
	}
	
	/**
    * 旧成本
    * @return java.lang.Double
    */
	public void setOldcost(java.lang.Double oldcost) {
	   this.oldcost = oldcost;
	}
	
	
    /**
    * 新成本
    * @return java.lang.Double
    */
	public java.lang.Double getNewcost() {
	  		return this.newcost;
	}
	
	/**
    * 新成本
    * @return java.lang.Double
    */
	public void setNewcost(java.lang.Double newcost) {
	   this.newcost = newcost;
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
	
	
}