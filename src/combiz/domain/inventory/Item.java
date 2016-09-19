package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Item extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String classid;
     private java.lang.Double curbal;
     private java.lang.String description;
     private java.lang.String hazardid;
     private java.lang.String inspectreq;
     private java.lang.String issueunit;
     private java.lang.String itemnum;
     private java.lang.Double lifeexp;
     private java.lang.String lottype;
     private java.lang.String modelnum;
     private java.lang.String orderunit;
     private java.lang.String outside;
     private java.lang.String rotating;
     private java.lang.String spareautoadd;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String taxcode;
     
    /** default constructor */
    public Item(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 库存分类
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * 库存分类
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
    /**
    * 库存余量
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * 库存余量
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
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
    * 危险标识
    * @return java.lang.String
    */
	public java.lang.String getHazardid() {
		if(this.hazardid==null || this.hazardid.length()<=0)
	  		return null;
	  	else
	  		return this.hazardid;
	}
	
	/**
    * 危险标识
    * @return java.lang.String
    */
	public void setHazardid(java.lang.String hazardid) {
	   this.hazardid = hazardid;
	}
	
	
    /**
    * 是否检验
    * @return java.lang.String
    */
	public java.lang.String getInspectreq() {
		if(this.inspectreq==null || this.inspectreq.length()<=0)
	  		return null;
	  	else
	  		return this.inspectreq;
	}
	
	/**
    * 是否检验
    * @return java.lang.String
    */
	public void setInspectreq(java.lang.String inspectreq) {
	   this.inspectreq = inspectreq;
	}
	
	
    /**
    * 订购单位
    * @return java.lang.String
    */
	public java.lang.String getIssueunit() {
		if(this.issueunit==null || this.issueunit.length()<=0)
	  		return null;
	  	else
	  		return this.issueunit;
	}
	
	/**
    * 订购单位
    * @return java.lang.String
    */
	public void setIssueunit(java.lang.String issueunit) {
	   this.issueunit = issueunit;
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
    * 有效期
    * @return java.lang.Double
    */
	public java.lang.Double getLifeexp() {
	  		return this.lifeexp;
	}
	
	/**
    * 有效期
    * @return java.lang.Double
    */
	public void setLifeexp(java.lang.Double lifeexp) {
	   this.lifeexp = lifeexp;
	}
	
	
    /**
    * 批次类型
    * @return java.lang.String
    */
	public java.lang.String getLottype() {
		if(this.lottype==null || this.lottype.length()<=0)
	  		return null;
	  	else
	  		return this.lottype;
	}
	
	/**
    * 批次类型
    * @return java.lang.String
    */
	public void setLottype(java.lang.String lottype) {
	   this.lottype = lottype;
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
    * 订购单位
    * @return java.lang.String
    */
	public java.lang.String getOrderunit() {
		if(this.orderunit==null || this.orderunit.length()<=0)
	  		return null;
	  	else
	  		return this.orderunit;
	}
	
	/**
    * 订购单位
    * @return java.lang.String
    */
	public void setOrderunit(java.lang.String orderunit) {
	   this.orderunit = orderunit;
	}
	
	
    /**
    * 外部物资?
    * @return java.lang.String
    */
	public java.lang.String getOutside() {
		if(this.outside==null || this.outside.length()<=0)
	  		return null;
	  	else
	  		return this.outside;
	}
	
	/**
    * 外部物资?
    * @return java.lang.String
    */
	public void setOutside(java.lang.String outside) {
	   this.outside = outside;
	}
	
	
    /**
    * 周转件?
    * @return java.lang.String
    */
	public java.lang.String getRotating() {
		if(this.rotating==null || this.rotating.length()<=0)
	  		return null;
	  	else
	  		return this.rotating;
	}
	
	/**
    * 周转件?
    * @return java.lang.String
    */
	public void setRotating(java.lang.String rotating) {
	   this.rotating = rotating;
	}
	
	
    /**
    * 是否添加为备件
    * @return java.lang.String
    */
	public java.lang.String getSpareautoadd() {
		if(this.spareautoadd==null || this.spareautoadd.length()<=0)
	  		return null;
	  	else
	  		return this.spareautoadd;
	}
	
	/**
    * 是否添加为备件
    * @return java.lang.String
    */
	public void setSpareautoadd(java.lang.String spareautoadd) {
	   this.spareautoadd = spareautoadd;
	}
	
	
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 状态日期
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 状态日期
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * 税代码
    * @return java.lang.String
    */
	public java.lang.String getTaxcode() {
		if(this.taxcode==null || this.taxcode.length()<=0)
	  		return null;
	  	else
	  		return this.taxcode;
	}
	
	/**
    * 税代码
    * @return java.lang.String
    */
	public void setTaxcode(java.lang.String taxcode) {
	   this.taxcode = taxcode;
	}
	
	
}