package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Allocationline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String allocationnum;
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String eqnum;
     private java.lang.String fromwarehouse;
     private java.lang.String itemnum;
     private java.lang.Long linenum;
     private java.lang.String modelnum;
     private java.lang.Double quantity;
     private java.lang.String sitenum;
     private java.lang.Double totalcost;
     private java.lang.String towarehouse;
     
    /** default constructor */
    public Allocationline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 调拨单号
    * @return java.lang.String
    */
	public java.lang.String getAllocationnum() {
		if(this.allocationnum==null || this.allocationnum.length()<=0)
	  		return null;
	  	else
	  		return this.allocationnum;
	}
	
	/**
    * 调拨单号
    * @return java.lang.String
    */
	public void setAllocationnum(java.lang.String allocationnum) {
	   this.allocationnum = allocationnum;
	}
	
	
    /**
    * 组织机构
    * @return java.lang.String
    */
	public java.lang.String getCorpnum() {
		if(this.corpnum==null || this.corpnum.length()<=0)
	  		return null;
	  	else
	  		return this.corpnum;
	}
	
	/**
    * 组织机构
    * @return java.lang.String
    */
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
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
    * 行号
    * @return java.lang.Long
    */
	public java.lang.Long getLinenum() {
	  		return this.linenum;
	}
	
	/**
    * 行号
    * @return java.lang.Long
    */
	public void setLinenum(java.lang.Long linenum) {
	   this.linenum = linenum;
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
    * 地点
    * @return java.lang.String
    */
	public java.lang.String getSitenum() {
		if(this.sitenum==null || this.sitenum.length()<=0)
	  		return null;
	  	else
	  		return this.sitenum;
	}
	
	/**
    * 地点
    * @return java.lang.String
    */
	public void setSitenum(java.lang.String sitenum) {
	   this.sitenum = sitenum;
	}
	
	
    /**
    * 总成本
    * @return java.lang.Double
    */
	public java.lang.Double getTotalcost() {
	  		return this.totalcost;
	}
	
	/**
    * 总成本
    * @return java.lang.Double
    */
	public void setTotalcost(java.lang.Double totalcost) {
	   this.totalcost = totalcost;
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
	
	
}