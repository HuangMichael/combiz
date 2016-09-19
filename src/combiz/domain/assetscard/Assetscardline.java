package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assetscardline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetcode;
     private java.lang.Double quantity;
     private java.lang.String manufacturers;
     private java.lang.String suppliers;
     private java.lang.String model;
     private java.lang.Double cost;
     private java.lang.Long expectedmonth;
     private java.lang.Long depreciationmonth;
     private java.lang.Double accdepreciation;
     private java.lang.Double networth;
     private java.lang.Double impairment;
     private java.lang.Double net;
     private java.lang.Double residualvalues;
     private java.lang.Double residual;
     private java.lang.Double mthval;
     private java.lang.Double mthamount;
     private java.lang.String year;
     private java.lang.String mon;
     private java.lang.String status;
     
    /** default constructor */
    public Assetscardline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 设备编号
    * @return java.lang.String
    */
	public java.lang.String getAssetcode() {
		if(this.assetcode==null || this.assetcode.length()<=0)
	  		return null;
	  	else
	  		return this.assetcode;
	}
	
	/**
    * 设备编号
    * @return java.lang.String
    */
	public void setAssetcode(java.lang.String assetcode) {
	   this.assetcode = assetcode;
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
    * 制造商
    * @return java.lang.String
    */
	public java.lang.String getManufacturers() {
		if(this.manufacturers==null || this.manufacturers.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturers;
	}
	
	/**
    * 制造商
    * @return java.lang.String
    */
	public void setManufacturers(java.lang.String manufacturers) {
	   this.manufacturers = manufacturers;
	}
	
	
    /**
    * 供应商
    * @return java.lang.String
    */
	public java.lang.String getSuppliers() {
		if(this.suppliers==null || this.suppliers.length()<=0)
	  		return null;
	  	else
	  		return this.suppliers;
	}
	
	/**
    * 供应商
    * @return java.lang.String
    */
	public void setSuppliers(java.lang.String suppliers) {
	   this.suppliers = suppliers;
	}
	
	
    /**
    * 型号
    * @return java.lang.String
    */
	public java.lang.String getModel() {
		if(this.model==null || this.model.length()<=0)
	  		return null;
	  	else
	  		return this.model;
	}
	
	/**
    * 型号
    * @return java.lang.String
    */
	public void setModel(java.lang.String model) {
	   this.model = model;
	}
	
	
    /**
    * 原值
    * @return java.lang.Double
    */
	public java.lang.Double getCost() {
	  		return this.cost;
	}
	
	/**
    * 原值
    * @return java.lang.Double
    */
	public void setCost(java.lang.Double cost) {
	   this.cost = cost;
	}
	
	
    /**
    * 预计使用到月数
    * @return java.lang.Long
    */
	public java.lang.Long getExpectedmonth() {
	  		return this.expectedmonth;
	}
	
	/**
    * 预计使用到月数
    * @return java.lang.Long
    */
	public void setExpectedmonth(java.lang.Long expectedmonth) {
	   this.expectedmonth = expectedmonth;
	}
	
	
    /**
    * 已计提折旧月数
    * @return java.lang.Long
    */
	public java.lang.Long getDepreciationmonth() {
	  		return this.depreciationmonth;
	}
	
	/**
    * 已计提折旧月数
    * @return java.lang.Long
    */
	public void setDepreciationmonth(java.lang.Long depreciationmonth) {
	   this.depreciationmonth = depreciationmonth;
	}
	
	
    /**
    * 累计折旧
    * @return java.lang.Double
    */
	public java.lang.Double getAccdepreciation() {
	  		return this.accdepreciation;
	}
	
	/**
    * 累计折旧
    * @return java.lang.Double
    */
	public void setAccdepreciation(java.lang.Double accdepreciation) {
	   this.accdepreciation = accdepreciation;
	}
	
	
    /**
    * 净值
    * @return java.lang.Double
    */
	public java.lang.Double getNetworth() {
	  		return this.networth;
	}
	
	/**
    * 净值
    * @return java.lang.Double
    */
	public void setNetworth(java.lang.Double networth) {
	   this.networth = networth;
	}
	
	
    /**
    * 减值准备
    * @return java.lang.Double
    */
	public java.lang.Double getImpairment() {
	  		return this.impairment;
	}
	
	/**
    * 减值准备
    * @return java.lang.Double
    */
	public void setImpairment(java.lang.Double impairment) {
	   this.impairment = impairment;
	}
	
	
    /**
    * 净额
    * @return java.lang.Double
    */
	public java.lang.Double getNet() {
	  		return this.net;
	}
	
	/**
    * 净额
    * @return java.lang.Double
    */
	public void setNet(java.lang.Double net) {
	   this.net = net;
	}
	
	
    /**
    * 残值率
    * @return java.lang.Double
    */
	public java.lang.Double getResidualvalues() {
	  		return this.residualvalues;
	}
	
	/**
    * 残值率
    * @return java.lang.Double
    */
	public void setResidualvalues(java.lang.Double residualvalues) {
	   this.residualvalues = residualvalues;
	}
	
	
    /**
    * 残值
    * @return java.lang.Double
    */
	public java.lang.Double getResidual() {
	  		return this.residual;
	}
	
	/**
    * 残值
    * @return java.lang.Double
    */
	public void setResidual(java.lang.Double residual) {
	   this.residual = residual;
	}
	
	
    /**
    * 月折旧率
    * @return java.lang.Double
    */
	public java.lang.Double getMthval() {
	  		return this.mthval;
	}
	
	/**
    * 月折旧率
    * @return java.lang.Double
    */
	public void setMthval(java.lang.Double mthval) {
	   this.mthval = mthval;
	}
	
	
    /**
    * 月折旧额
    * @return java.lang.Double
    */
	public java.lang.Double getMthamount() {
	  		return this.mthamount;
	}
	
	/**
    * 月折旧额
    * @return java.lang.Double
    */
	public void setMthamount(java.lang.Double mthamount) {
	   this.mthamount = mthamount;
	}
	
	
    /**
    * 年
    * @return java.lang.String
    */
	public java.lang.String getYear() {
		if(this.year==null || this.year.length()<=0)
	  		return null;
	  	else
	  		return this.year;
	}
	
	/**
    * 年
    * @return java.lang.String
    */
	public void setYear(java.lang.String year) {
	   this.year = year;
	}
	
	
    /**
    * 月
    * @return java.lang.String
    */
	public java.lang.String getMon() {
		if(this.mon==null || this.mon.length()<=0)
	  		return null;
	  	else
	  		return this.mon;
	}
	
	/**
    * 月
    * @return java.lang.String
    */
	public void setMon(java.lang.String mon) {
	   this.mon = mon;
	}
	
	
    /**
    * 结转状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 结转状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
}