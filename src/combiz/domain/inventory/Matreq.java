package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Matreq extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Long bh;
     private java.lang.String buditem;
     private java.lang.String budnum;
     private java.lang.String craft;
     private java.lang.String description;
     private java.lang.String isgovprocurement;
     private java.util.Date issuedate;
     private java.lang.String matreqnum;
     private java.lang.String memo;
     private java.lang.String projnum;
     private java.lang.String reqdept;
     private java.lang.String reqtype;
     private java.lang.String requestby;
     private java.util.Date requestdate;
     private java.util.Date requireddate;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String supervisor;
     private java.util.Date usedate;
     private java.lang.String usedept;
     private java.lang.String wonum;
     
    /** default constructor */
    public Matreq(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 编号(用于签报中的年度第××号)
    * @return java.lang.Long
    */
	public java.lang.Long getBh() {
	  		return this.bh;
	}
	
	/**
    * 编号(用于签报中的年度第××号)
    * @return java.lang.Long
    */
	public void setBh(java.lang.Long bh) {
	   this.bh = bh;
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
    * 预算号
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * 预算号
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * 专业
    * @return java.lang.String
    */
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}
	
	/**
    * 专业
    * @return java.lang.String
    */
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
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
    * 是否政府采购
    * @return java.lang.String
    */
	public java.lang.String getIsgovprocurement() {
		if(this.isgovprocurement==null || this.isgovprocurement.length()<=0)
	  		return null;
	  	else
	  		return this.isgovprocurement;
	}
	
	/**
    * 是否政府采购
    * @return java.lang.String
    */
	public void setIsgovprocurement(java.lang.String isgovprocurement) {
	   this.isgovprocurement = isgovprocurement;
	}
	
	
    /**
    * 实际发放日期
    * @return java.util.Date
    */
	public java.util.Date getIssuedate() {
	  		return this.issuedate;
	}
	
	/**
    * 实际发放日期
    * @return java.util.Date
    */
	public void setIssuedate(java.util.Date issuedate) {
	   this.issuedate = issuedate;
	}
	
	
    /**
    * 申请编号
    * @return java.lang.String
    */
	public java.lang.String getMatreqnum() {
		if(this.matreqnum==null || this.matreqnum.length()<=0)
	  		return null;
	  	else
	  		return this.matreqnum;
	}
	
	/**
    * 申请编号
    * @return java.lang.String
    */
	public void setMatreqnum(java.lang.String matreqnum) {
	   this.matreqnum = matreqnum;
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
    * 项目编号
    * @return java.lang.String
    */
	public java.lang.String getProjnum() {
		if(this.projnum==null || this.projnum.length()<=0)
	  		return null;
	  	else
	  		return this.projnum;
	}
	
	/**
    * 项目编号
    * @return java.lang.String
    */
	public void setProjnum(java.lang.String projnum) {
	   this.projnum = projnum;
	}
	
	
    /**
    * 申请部门
    * @return java.lang.String
    */
	public java.lang.String getReqdept() {
		if(this.reqdept==null || this.reqdept.length()<=0)
	  		return null;
	  	else
	  		return this.reqdept;
	}
	
	/**
    * 申请部门
    * @return java.lang.String
    */
	public void setReqdept(java.lang.String reqdept) {
	   this.reqdept = reqdept;
	}
	
	
    /**
    * 申请类型
    * @return java.lang.String
    */
	public java.lang.String getReqtype() {
		if(this.reqtype==null || this.reqtype.length()<=0)
	  		return null;
	  	else
	  		return this.reqtype;
	}
	
	/**
    * 申请类型
    * @return java.lang.String
    */
	public void setReqtype(java.lang.String reqtype) {
	   this.reqtype = reqtype;
	}
	
	
    /**
    * 申请人
    * @return java.lang.String
    */
	public java.lang.String getRequestby() {
		if(this.requestby==null || this.requestby.length()<=0)
	  		return null;
	  	else
	  		return this.requestby;
	}
	
	/**
    * 申请人
    * @return java.lang.String
    */
	public void setRequestby(java.lang.String requestby) {
	   this.requestby = requestby;
	}
	
	
    /**
    * 申请时间
    * @return java.util.Date
    */
	public java.util.Date getRequestdate() {
	  		return this.requestdate;
	}
	
	/**
    * 申请时间
    * @return java.util.Date
    */
	public void setRequestdate(java.util.Date requestdate) {
	   this.requestdate = requestdate;
	}
	
	
    /**
    * 需用日期
    * @return java.util.Date
    */
	public java.util.Date getRequireddate() {
	  		return this.requireddate;
	}
	
	/**
    * 需用日期
    * @return java.util.Date
    */
	public void setRequireddate(java.util.Date requireddate) {
	   this.requireddate = requireddate;
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
	
	
    /**
    * 负责人
    * @return java.lang.String
    */
	public java.lang.String getSupervisor() {
		if(this.supervisor==null || this.supervisor.length()<=0)
	  		return null;
	  	else
	  		return this.supervisor;
	}
	
	/**
    * 负责人
    * @return java.lang.String
    */
	public void setSupervisor(java.lang.String supervisor) {
	   this.supervisor = supervisor;
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
	public java.lang.String getUsedept() {
		if(this.usedept==null || this.usedept.length()<=0)
	  		return null;
	  	else
	  		return this.usedept;
	}
	
	/**
    * 使用部门
    * @return java.lang.String
    */
	public void setUsedept(java.lang.String usedept) {
	   this.usedept = usedept;
	}
	
	
    /**
    * 工单编号
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * 工单编号
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}