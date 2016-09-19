package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Allocation extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String allocationnum;
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String enterby;
     private java.util.Date enterdate;
     private java.util.Date fromdate;
     private java.lang.String fromperson;
     private java.lang.String fromsite;
     private java.lang.String sitenum;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.util.Date todate;
     private java.lang.String toperson;
     private java.lang.String tosite;
     
    /** default constructor */
    public Allocation(){}
    
   
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
    * 录入日期
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * 录入日期
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * 调出日期
    * @return java.util.Date
    */
	public java.util.Date getFromdate() {
	  		return this.fromdate;
	}
	
	/**
    * 调出日期
    * @return java.util.Date
    */
	public void setFromdate(java.util.Date fromdate) {
	   this.fromdate = fromdate;
	}
	
	
    /**
    * 调出部门经办人
    * @return java.lang.String
    */
	public java.lang.String getFromperson() {
		if(this.fromperson==null || this.fromperson.length()<=0)
	  		return null;
	  	else
	  		return this.fromperson;
	}
	
	/**
    * 调出部门经办人
    * @return java.lang.String
    */
	public void setFromperson(java.lang.String fromperson) {
	   this.fromperson = fromperson;
	}
	
	
    /**
    * 调出地点
    * @return java.lang.String
    */
	public java.lang.String getFromsite() {
		if(this.fromsite==null || this.fromsite.length()<=0)
	  		return null;
	  	else
	  		return this.fromsite;
	}
	
	/**
    * 调出地点
    * @return java.lang.String
    */
	public void setFromsite(java.lang.String fromsite) {
	   this.fromsite = fromsite;
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
    * 接收日期
    * @return java.util.Date
    */
	public java.util.Date getTodate() {
	  		return this.todate;
	}
	
	/**
    * 接收日期
    * @return java.util.Date
    */
	public void setTodate(java.util.Date todate) {
	   this.todate = todate;
	}
	
	
    /**
    * 接收方经办人
    * @return java.lang.String
    */
	public java.lang.String getToperson() {
		if(this.toperson==null || this.toperson.length()<=0)
	  		return null;
	  	else
	  		return this.toperson;
	}
	
	/**
    * 接收方经办人
    * @return java.lang.String
    */
	public void setToperson(java.lang.String toperson) {
	   this.toperson = toperson;
	}
	
	
    /**
    * 调入地点
    * @return java.lang.String
    */
	public java.lang.String getTosite() {
		if(this.tosite==null || this.tosite.length()<=0)
	  		return null;
	  	else
	  		return this.tosite;
	}
	
	/**
    * 调入地点
    * @return java.lang.String
    */
	public void setTosite(java.lang.String tosite) {
	   this.tosite = tosite;
	}
	
	
}