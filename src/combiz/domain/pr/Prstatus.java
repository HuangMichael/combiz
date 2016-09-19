package combiz.domain.pr;

import combiz.system.IBOBaseObject;

public class Prstatus extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String prnum;
     private java.lang.String status;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.String remark;
     
    /** default constructor */
    public Prstatus(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 采购申请号
    * @return java.lang.String
    */
	public java.lang.String getPrnum() {
		if(this.prnum==null || this.prnum.length()<=0)
	  		return null;
	  	else
	  		return this.prnum;
	}
	
	/**
    * 采购申请号
    * @return java.lang.String
    */
	public void setPrnum(java.lang.String prnum) {
	   this.prnum = prnum;
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
	
	
}