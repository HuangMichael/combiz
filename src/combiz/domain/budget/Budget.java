package combiz.domain.budget;

import combiz.system.IBOBaseObject;

public class Budget extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String budcycle;
     private java.lang.String buddept;
     private java.lang.Double budget;
     private java.lang.String budnum;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.lang.String sitenum;
     private java.lang.String status;
     private java.util.Date statusdate;
     
    /** default constructor */
    public Budget(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 预算年度2008，也可以是季度2008-1等
    * @return java.lang.String
    */
	public java.lang.String getBudcycle() {
		if(this.budcycle==null || this.budcycle.length()<=0)
	  		return null;
	  	else
	  		return this.budcycle;
	}
	
	/**
    * 预算年度2008，也可以是季度2008-1等
    * @return java.lang.String
    */
	public void setBudcycle(java.lang.String budcycle) {
	   this.budcycle = budcycle;
	}
	
	
    /**
    * 预算部门
    * @return java.lang.String
    */
	public java.lang.String getBuddept() {
		if(this.buddept==null || this.buddept.length()<=0)
	  		return null;
	  	else
	  		return this.buddept;
	}
	
	/**
    * 预算部门
    * @return java.lang.String
    */
	public void setBuddept(java.lang.String buddept) {
	   this.buddept = buddept;
	}
	
	
    /**
    * 预算费用合计
    * @return java.lang.Double
    */
	public java.lang.Double getBudget() {
	  		return this.budget;
	}
	
	/**
    * 预算费用合计
    * @return java.lang.Double
    */
	public void setBudget(java.lang.Double budget) {
	   this.budget = budget;
	}
	
	
    /**
    * 预算编号，主表关键字段
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算编号，主表关键字段
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * 编制人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 编制人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 编制时间
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 编制时间
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
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
    * 预算说明
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 预算说明
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 是否启用
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * 是否启用
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
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
    * 状态时间
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 状态时间
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
}