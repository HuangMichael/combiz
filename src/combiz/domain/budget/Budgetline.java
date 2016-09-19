package combiz.domain.budget;

import combiz.system.IBOBaseObject;

public class Budgetline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String budclass;
     private java.lang.String buddept;
     private java.lang.Double budfeed;
     private java.lang.Double budget;
     private java.lang.String budgetnum;
     private java.lang.String buditem;
     private java.lang.String budnum;
     private java.lang.String budperiod;
     private java.lang.String budtype;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String childclass;
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String enabled;
     private java.lang.String haschild;
     private java.lang.String meaunit;
     private java.lang.Long orderby;
     private java.lang.String parent;
     private java.lang.String remark;
     private java.lang.String sitenum;
     private java.lang.String version;
     
    /** default constructor */
    public Budgetline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 预算类型
    * @return java.lang.String
    */
	public java.lang.String getBudclass() {
		if(this.budclass==null || this.budclass.length()<=0)
	  		return null;
	  	else
	  		return this.budclass;
	}
	
	/**
    * 预算类型
    * @return java.lang.String
    */
	public void setBudclass(java.lang.String budclass) {
	   this.budclass = budclass;
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
    * 实际费用
    * @return java.lang.Double
    */
	public java.lang.Double getBudfeed() {
	  		return this.budfeed;
	}
	
	/**
    * 实际费用
    * @return java.lang.Double
    */
	public void setBudfeed(java.lang.Double budfeed) {
	   this.budfeed = budfeed;
	}
	
	
    /**
    * 预算费用
    * @return java.lang.Double
    */
	public java.lang.Double getBudget() {
	  		return this.budget;
	}
	
	/**
    * 预算费用
    * @return java.lang.Double
    */
	public void setBudget(java.lang.Double budget) {
	   this.budget = budget;
	}
	
	
    /**
    * 预算号
    * @return java.lang.String
    */
	public java.lang.String getBudgetnum() {
		if(this.budgetnum==null || this.budgetnum.length()<=0)
	  		return null;
	  	else
	  		return this.budgetnum;
	}
	
	/**
    * 预算号
    * @return java.lang.String
    */
	public void setBudgetnum(java.lang.String budgetnum) {
	   this.budgetnum = budgetnum;
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
    * 预算编号
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算编号
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * 预算周期
    * @return java.lang.String
    */
	public java.lang.String getBudperiod() {
		if(this.budperiod==null || this.budperiod.length()<=0)
	  		return null;
	  	else
	  		return this.budperiod;
	}
	
	/**
    * 预算周期
    * @return java.lang.String
    */
	public void setBudperiod(java.lang.String budperiod) {
	   this.budperiod = budperiod;
	}
	
	
    /**
    * 核算类型
    * @return java.lang.String
    */
	public java.lang.String getBudtype() {
		if(this.budtype==null || this.budtype.length()<=0)
	  		return null;
	  	else
	  		return this.budtype;
	}
	
	/**
    * 核算类型
    * @return java.lang.String
    */
	public void setBudtype(java.lang.String budtype) {
	   this.budtype = budtype;
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
    * 预算子类型：收入、支出
    * @return java.lang.String
    */
	public java.lang.String getChildclass() {
		if(this.childclass==null || this.childclass.length()<=0)
	  		return null;
	  	else
	  		return this.childclass;
	}
	
	/**
    * 预算子类型：收入、支出
    * @return java.lang.String
    */
	public void setChildclass(java.lang.String childclass) {
	   this.childclass = childclass;
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
    * 预算项目名称
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 预算项目名称
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 是否启用？
    * @return java.lang.String
    */
	public java.lang.String getEnabled() {
		if(this.enabled==null || this.enabled.length()<=0)
	  		return null;
	  	else
	  		return this.enabled;
	}
	
	/**
    * 是否启用？
    * @return java.lang.String
    */
	public void setEnabled(java.lang.String enabled) {
	   this.enabled = enabled;
	}
	
	
    /**
    * 有子集？
    * @return java.lang.String
    */
	public java.lang.String getHaschild() {
		if(this.haschild==null || this.haschild.length()<=0)
	  		return null;
	  	else
	  		return this.haschild;
	}
	
	/**
    * 有子集？
    * @return java.lang.String
    */
	public void setHaschild(java.lang.String haschild) {
	   this.haschild = haschild;
	}
	
	
    /**
    * 计量单位
    * @return java.lang.String
    */
	public java.lang.String getMeaunit() {
		if(this.meaunit==null || this.meaunit.length()<=0)
	  		return null;
	  	else
	  		return this.meaunit;
	}
	
	/**
    * 计量单位
    * @return java.lang.String
    */
	public void setMeaunit(java.lang.String meaunit) {
	   this.meaunit = meaunit;
	}
	
	
    /**
    * 序号
    * @return java.lang.Long
    */
	public java.lang.Long getOrderby() {
	  		return this.orderby;
	}
	
	/**
    * 序号
    * @return java.lang.Long
    */
	public void setOrderby(java.lang.Long orderby) {
	   this.orderby = orderby;
	}
	
	
    /**
    * 预算项目父级
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 预算项目父级
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 预算说明
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 预算说明
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
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
    * 预算版本
    * @return java.lang.String
    */
	public java.lang.String getVersion() {
		if(this.version==null || this.version.length()<=0)
	  		return null;
	  	else
	  		return this.version;
	}
	
	/**
    * 预算版本
    * @return java.lang.String
    */
	public void setVersion(java.lang.String version) {
	   this.version = version;
	}
	
	
}