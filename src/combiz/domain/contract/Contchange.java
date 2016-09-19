package combiz.domain.contract;

import combiz.system.IBOBaseObject;

public class Contchange extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String chgcause;
     private java.lang.String chgcontent;
     private java.lang.String cntnum;
     private java.lang.String cntversion;
     private java.lang.String remark;
     private java.lang.String status;
     private java.util.Date statusdate;
     
    /** default constructor */
    public Contchange(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 修改人
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * 修改人
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * 修改日期
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * 修改日期
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * 变更理由
    * @return java.lang.String
    */
	public java.lang.String getChgcause() {
		if(this.chgcause==null || this.chgcause.length()<=0)
	  		return null;
	  	else
	  		return this.chgcause;
	}
	
	/**
    * 变更理由
    * @return java.lang.String
    */
	public void setChgcause(java.lang.String chgcause) {
	   this.chgcause = chgcause;
	}
	
	
    /**
    * 变更内容
    * @return java.lang.String
    */
	public java.lang.String getChgcontent() {
		if(this.chgcontent==null || this.chgcontent.length()<=0)
	  		return null;
	  	else
	  		return this.chgcontent;
	}
	
	/**
    * 变更内容
    * @return java.lang.String
    */
	public void setChgcontent(java.lang.String chgcontent) {
	   this.chgcontent = chgcontent;
	}
	
	
    /**
    * 合同编号
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * 合同编号
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * 合同版本号
    * @return java.lang.String
    */
	public java.lang.String getCntversion() {
		if(this.cntversion==null || this.cntversion.length()<=0)
	  		return null;
	  	else
	  		return this.cntversion;
	}
	
	/**
    * 合同版本号
    * @return java.lang.String
    */
	public void setCntversion(java.lang.String cntversion) {
	   this.cntversion = cntversion;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
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
	
	
}