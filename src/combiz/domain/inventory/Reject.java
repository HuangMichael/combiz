package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Reject extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String informman;
     private java.lang.String memo;
     private java.lang.String reason;
     private java.util.Date rejectdate;
     private java.lang.String rejectnum;
     private java.lang.String rejecttype;
     private java.lang.String reqdept;
     private java.lang.String requestby;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String usedept;
     
    /** default constructor */
    public Reject(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 负责人
    * @return java.lang.String
    */
	public java.lang.String getInformman() {
		if(this.informman==null || this.informman.length()<=0)
	  		return null;
	  	else
	  		return this.informman;
	}
	
	/**
    * 负责人
    * @return java.lang.String
    */
	public void setInformman(java.lang.String informman) {
	   this.informman = informman;
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
    * 报废原因
    * @return java.lang.String
    */
	public java.lang.String getReason() {
		if(this.reason==null || this.reason.length()<=0)
	  		return null;
	  	else
	  		return this.reason;
	}
	
	/**
    * 报废原因
    * @return java.lang.String
    */
	public void setReason(java.lang.String reason) {
	   this.reason = reason;
	}
	
	
    /**
    * 报废日期
    * @return java.util.Date
    */
	public java.util.Date getRejectdate() {
	  		return this.rejectdate;
	}
	
	/**
    * 报废日期
    * @return java.util.Date
    */
	public void setRejectdate(java.util.Date rejectdate) {
	   this.rejectdate = rejectdate;
	}
	
	
    /**
    * 报废单编号
    * @return java.lang.String
    */
	public java.lang.String getRejectnum() {
		if(this.rejectnum==null || this.rejectnum.length()<=0)
	  		return null;
	  	else
	  		return this.rejectnum;
	}
	
	/**
    * 报废单编号
    * @return java.lang.String
    */
	public void setRejectnum(java.lang.String rejectnum) {
	   this.rejectnum = rejectnum;
	}
	
	
    /**
    * 报废类型（标识是资产/库存的报废）
    * @return java.lang.String
    */
	public java.lang.String getRejecttype() {
		if(this.rejecttype==null || this.rejecttype.length()<=0)
	  		return null;
	  	else
	  		return this.rejecttype;
	}
	
	/**
    * 报废类型（标识是资产/库存的报废）
    * @return java.lang.String
    */
	public void setRejecttype(java.lang.String rejecttype) {
	   this.rejecttype = rejecttype;
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
    * 流程状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 流程状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 流程日期
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * 流程日期
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * 会签部门
    * @return java.lang.String
    */
	public java.lang.String getUsedept() {
		if(this.usedept==null || this.usedept.length()<=0)
	  		return null;
	  	else
	  		return this.usedept;
	}
	
	/**
    * 会签部门
    * @return java.lang.String
    */
	public void setUsedept(java.lang.String usedept) {
	   this.usedept = usedept;
	}
	
	
}