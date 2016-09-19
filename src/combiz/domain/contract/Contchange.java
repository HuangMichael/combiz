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
    * �޸���
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * �޸���
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * �޸�����
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * �޸�����
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getChgcause() {
		if(this.chgcause==null || this.chgcause.length()<=0)
	  		return null;
	  	else
	  		return this.chgcause;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setChgcause(java.lang.String chgcause) {
	   this.chgcause = chgcause;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getChgcontent() {
		if(this.chgcontent==null || this.chgcontent.length()<=0)
	  		return null;
	  	else
	  		return this.chgcontent;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setChgcontent(java.lang.String chgcontent) {
	   this.chgcontent = chgcontent;
	}
	
	
    /**
    * ��ͬ���
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * ��ͬ���
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
	}
	
	
    /**
    * ��ͬ�汾��
    * @return java.lang.String
    */
	public java.lang.String getCntversion() {
		if(this.cntversion==null || this.cntversion.length()<=0)
	  		return null;
	  	else
	  		return this.cntversion;
	}
	
	/**
    * ��ͬ�汾��
    * @return java.lang.String
    */
	public void setCntversion(java.lang.String cntversion) {
	   this.cntversion = cntversion;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ״̬����
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ״̬����
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
}