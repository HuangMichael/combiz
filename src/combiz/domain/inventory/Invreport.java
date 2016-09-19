package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invreport extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classid;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String modelnum;
     private java.util.Date thisdate;
     private java.lang.Double invuseqty;
     private java.lang.Double invusecost;
     private java.lang.String warehouse;
     private java.lang.Double curbal;
     private java.lang.Double linecost;
     private java.lang.Double invrecqty;
     private java.lang.Double invrecvcost;
     private java.lang.Double lastmonqty;
     private java.lang.Double lastmoncost;
     private java.lang.Long orderdesc;
     private java.lang.Double avgcost;
     
    /** default constructor */
    public Invreport(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 物资编码类别
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 物资编码类别
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * 库存项目
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * 库存项目
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
    * 规格
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * 规格
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getThisdate() {
	  		return this.thisdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setThisdate(java.util.Date thisdate) {
	   this.thisdate = thisdate;
	}
	
	
    /**
    * 物料使用数量
    * @return java.lang.Double
    */
	public java.lang.Double getInvuseqty() {
	  		return this.invuseqty;
	}
	
	/**
    * 物料使用数量
    * @return java.lang.Double
    */
	public void setInvuseqty(java.lang.Double invuseqty) {
	   this.invuseqty = invuseqty;
	}
	
	
    /**
    * 物料使用成本
    * @return java.lang.Double
    */
	public java.lang.Double getInvusecost() {
	  		return this.invusecost;
	}
	
	/**
    * 物料使用成本
    * @return java.lang.Double
    */
	public void setInvusecost(java.lang.Double invusecost) {
	   this.invusecost = invusecost;
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
    * 总成本
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * 总成本
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * 本月收入数量
    * @return java.lang.Double
    */
	public java.lang.Double getInvrecqty() {
	  		return this.invrecqty;
	}
	
	/**
    * 本月收入数量
    * @return java.lang.Double
    */
	public void setInvrecqty(java.lang.Double invrecqty) {
	   this.invrecqty = invrecqty;
	}
	
	
    /**
    * 本月收入总金额
    * @return java.lang.Double
    */
	public java.lang.Double getInvrecvcost() {
	  		return this.invrecvcost;
	}
	
	/**
    * 本月收入总金额
    * @return java.lang.Double
    */
	public void setInvrecvcost(java.lang.Double invrecvcost) {
	   this.invrecvcost = invrecvcost;
	}
	
	
    /**
    * 上月余量
    * @return java.lang.Double
    */
	public java.lang.Double getLastmonqty() {
	  		return this.lastmonqty;
	}
	
	/**
    * 上月余量
    * @return java.lang.Double
    */
	public void setLastmonqty(java.lang.Double lastmonqty) {
	   this.lastmonqty = lastmonqty;
	}
	
	
    /**
    * 上月总金额
    * @return java.lang.Double
    */
	public java.lang.Double getLastmoncost() {
	  		return this.lastmoncost;
	}
	
	/**
    * 上月总金额
    * @return java.lang.Double
    */
	public void setLastmoncost(java.lang.Double lastmoncost) {
	   this.lastmoncost = lastmoncost;
	}
	
	
    /**
    * 排序号
    * @return java.lang.Long
    */
	public java.lang.Long getOrderdesc() {
	  		return this.orderdesc;
	}
	
	/**
    * 排序号
    * @return java.lang.Long
    */
	public void setOrderdesc(java.lang.Long orderdesc) {
	   this.orderdesc = orderdesc;
	}
	
	
    /**
    * 平均价格
    * @return java.lang.Double
    */
	public java.lang.Double getAvgcost() {
	  		return this.avgcost;
	}
	
	/**
    * 平均价格
    * @return java.lang.Double
    */
	public void setAvgcost(java.lang.Double avgcost) {
	   this.avgcost = avgcost;
	}
	
	
}