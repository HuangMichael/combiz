package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assetscard extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetclasses;
     private java.lang.String assetcode;
     private java.lang.String assetname;
     private java.lang.String assetstatus;
     private java.lang.String assetsubject;
     private java.lang.String changemethod;
     private java.lang.Double cost;
     private java.util.Date daterecorded;
     private java.lang.String depreciationmethod;
     private java.lang.String depreciationsjt;
     private java.lang.String eqnum;
     private java.lang.String location;
     private java.lang.Double net_xn;
     private java.lang.String parent;
     private java.lang.String responsible;
     private java.lang.String status;
     private java.lang.String summary;
     private java.util.Date usedate;
     private java.lang.String usedepartment;
     private java.lang.String users;
     
    /** default constructor */
    public Assetscard(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 资产类别
    * @return java.lang.String
    */
	public java.lang.String getAssetclasses() {
		if(this.assetclasses==null || this.assetclasses.length()<=0)
	  		return null;
	  	else
	  		return this.assetclasses;
	}
	
	/**
    * 资产类别
    * @return java.lang.String
    */
	public void setAssetclasses(java.lang.String assetclasses) {
	   this.assetclasses = assetclasses;
	}
	
	
    /**
    * 资产编号
    * @return java.lang.String
    */
	public java.lang.String getAssetcode() {
		if(this.assetcode==null || this.assetcode.length()<=0)
	  		return null;
	  	else
	  		return this.assetcode;
	}
	
	/**
    * 资产编号
    * @return java.lang.String
    */
	public void setAssetcode(java.lang.String assetcode) {
	   this.assetcode = assetcode;
	}
	
	
    /**
    * 资产名称
    * @return java.lang.String
    */
	public java.lang.String getAssetname() {
		if(this.assetname==null || this.assetname.length()<=0)
	  		return null;
	  	else
	  		return this.assetname;
	}
	
	/**
    * 资产名称
    * @return java.lang.String
    */
	public void setAssetname(java.lang.String assetname) {
	   this.assetname = assetname;
	}
	
	
    /**
    * 资产状态
    * @return java.lang.String
    */
	public java.lang.String getAssetstatus() {
		if(this.assetstatus==null || this.assetstatus.length()<=0)
	  		return null;
	  	else
	  		return this.assetstatus;
	}
	
	/**
    * 资产状态
    * @return java.lang.String
    */
	public void setAssetstatus(java.lang.String assetstatus) {
	   this.assetstatus = assetstatus;
	}
	
	
    /**
    * 资产科目
    * @return java.lang.String
    */
	public java.lang.String getAssetsubject() {
		if(this.assetsubject==null || this.assetsubject.length()<=0)
	  		return null;
	  	else
	  		return this.assetsubject;
	}
	
	/**
    * 资产科目
    * @return java.lang.String
    */
	public void setAssetsubject(java.lang.String assetsubject) {
	   this.assetsubject = assetsubject;
	}
	
	
    /**
    * 增减方式
    * @return java.lang.String
    */
	public java.lang.String getChangemethod() {
		if(this.changemethod==null || this.changemethod.length()<=0)
	  		return null;
	  	else
	  		return this.changemethod;
	}
	
	/**
    * 增减方式
    * @return java.lang.String
    */
	public void setChangemethod(java.lang.String changemethod) {
	   this.changemethod = changemethod;
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
    * 入账日期
    * @return java.util.Date
    */
	public java.util.Date getDaterecorded() {
	  		return this.daterecorded;
	}
	
	/**
    * 入账日期
    * @return java.util.Date
    */
	public void setDaterecorded(java.util.Date daterecorded) {
	   this.daterecorded = daterecorded;
	}
	
	
    /**
    * 折旧方式
    * @return java.lang.String
    */
	public java.lang.String getDepreciationmethod() {
		if(this.depreciationmethod==null || this.depreciationmethod.length()<=0)
	  		return null;
	  	else
	  		return this.depreciationmethod;
	}
	
	/**
    * 折旧方式
    * @return java.lang.String
    */
	public void setDepreciationmethod(java.lang.String depreciationmethod) {
	   this.depreciationmethod = depreciationmethod;
	}
	
	
    /**
    * 折旧科目
    * @return java.lang.String
    */
	public java.lang.String getDepreciationsjt() {
		if(this.depreciationsjt==null || this.depreciationsjt.length()<=0)
	  		return null;
	  	else
	  		return this.depreciationsjt;
	}
	
	/**
    * 折旧科目
    * @return java.lang.String
    */
	public void setDepreciationsjt(java.lang.String depreciationsjt) {
	   this.depreciationsjt = depreciationsjt;
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
    * 存放地点
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * 存放地点
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * 虚拟净额
    * @return java.lang.Double
    */
	public java.lang.Double getNet_xn() {
	  		return this.net_xn;
	}
	
	/**
    * 虚拟净额
    * @return java.lang.Double
    */
	public void setNet_xn(java.lang.Double net_xn) {
	   this.net_xn = net_xn;
	}
	
	
    /**
    * 父级
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * 父级
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * 负责人
    * @return java.lang.String
    */
	public java.lang.String getResponsible() {
		if(this.responsible==null || this.responsible.length()<=0)
	  		return null;
	  	else
	  		return this.responsible;
	}
	
	/**
    * 负责人
    * @return java.lang.String
    */
	public void setResponsible(java.lang.String responsible) {
	   this.responsible = responsible;
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
    * 摘要
    * @return java.lang.String
    */
	public java.lang.String getSummary() {
		if(this.summary==null || this.summary.length()<=0)
	  		return null;
	  	else
	  		return this.summary;
	}
	
	/**
    * 摘要
    * @return java.lang.String
    */
	public void setSummary(java.lang.String summary) {
	   this.summary = summary;
	}
	
	
    /**
    * 使用日期
    * @return java.util.Date
    */
	public java.util.Date getUsedate() {
	  		return this.usedate;
	}
	
	/**
    * 使用日期
    * @return java.util.Date
    */
	public void setUsedate(java.util.Date usedate) {
	   this.usedate = usedate;
	}
	
	
    /**
    * 使用部门
    * @return java.lang.String
    */
	public java.lang.String getUsedepartment() {
		if(this.usedepartment==null || this.usedepartment.length()<=0)
	  		return null;
	  	else
	  		return this.usedepartment;
	}
	
	/**
    * 使用部门
    * @return java.lang.String
    */
	public void setUsedepartment(java.lang.String usedepartment) {
	   this.usedepartment = usedepartment;
	}
	
	
    /**
    * 使用人
    * @return java.lang.String
    */
	public java.lang.String getUsers() {
		if(this.users==null || this.users.length()<=0)
	  		return null;
	  	else
	  		return this.users;
	}
	
	/**
    * 使用人
    * @return java.lang.String
    */
	public void setUsers(java.lang.String users) {
	   this.users = users;
	}
	
	
}