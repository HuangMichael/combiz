package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Checkqtyitem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double accountqty;
     private java.lang.Double actualqty;
     private java.lang.String binnum;
     private java.lang.String checkqtynum;
     private java.lang.String itemdesc;
     private java.lang.String itemnum;
     private java.lang.String lotnum;
     private java.lang.String memo;
     private java.lang.String status;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Checkqtyitem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 账上数量
    * @return java.lang.Double
    */
	public java.lang.Double getAccountqty() {
	  		return this.accountqty;
	}
	
	/**
    * 账上数量
    * @return java.lang.Double
    */
	public void setAccountqty(java.lang.Double accountqty) {
	   this.accountqty = accountqty;
	}
	
	
    /**
    * 盘点数量
    * @return java.lang.Double
    */
	public java.lang.Double getActualqty() {
	  		return this.actualqty;
	}
	
	/**
    * 盘点数量
    * @return java.lang.Double
    */
	public void setActualqty(java.lang.Double actualqty) {
	   this.actualqty = actualqty;
	}
	
	
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
    * 盘点单编号
    * @return java.lang.String
    */
	public java.lang.String getCheckqtynum() {
		if(this.checkqtynum==null || this.checkqtynum.length()<=0)
	  		return null;
	  	else
	  		return this.checkqtynum;
	}
	
	/**
    * 盘点单编号
    * @return java.lang.String
    */
	public void setCheckqtynum(java.lang.String checkqtynum) {
	   this.checkqtynum = checkqtynum;
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
    * 库存项目标号
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存项目标号
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
    * 盘点状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 盘点状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 库房名称
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * 库房名称
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}