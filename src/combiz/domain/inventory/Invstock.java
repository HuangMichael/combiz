package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invstock extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String binnum;
     private java.lang.Double curbal;
     private java.lang.String itemnum;
     private java.lang.String lotnum;
     private java.lang.Double physcnt;
     private java.util.Date physcntdate;
     private java.lang.String reconciled;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Invstock(){}
    
   
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
    * 盘点数量
    * @return java.lang.Double
    */
	public java.lang.Double getPhyscnt() {
	  		return this.physcnt;
	}
	
	/**
    * 盘点数量
    * @return java.lang.Double
    */
	public void setPhyscnt(java.lang.Double physcnt) {
	   this.physcnt = physcnt;
	}
	
	
    /**
    * 盘点日期
    * @return java.util.Date
    */
	public java.util.Date getPhyscntdate() {
	  		return this.physcntdate;
	}
	
	/**
    * 盘点日期
    * @return java.util.Date
    */
	public void setPhyscntdate(java.util.Date physcntdate) {
	   this.physcntdate = physcntdate;
	}
	
	
    /**
    * 是否调整
    * @return java.lang.String
    */
	public java.lang.String getReconciled() {
		if(this.reconciled==null || this.reconciled.length()<=0)
	  		return null;
	  	else
	  		return this.reconciled;
	}
	
	/**
    * 是否调整
    * @return java.lang.String
    */
	public void setReconciled(java.lang.String reconciled) {
	   this.reconciled = reconciled;
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
	
	
}