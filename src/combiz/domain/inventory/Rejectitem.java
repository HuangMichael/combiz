package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Rejectitem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String binnum;
     private java.lang.String custodian;
     private java.lang.String deptnum;
     private java.lang.String eqnum;
     private java.lang.Long invstockid;
     private java.lang.String isreject;
     private java.lang.String itemnum;
     private java.lang.String labornum;
     private java.lang.String location;
     private java.lang.String lotnum;
     private java.lang.String memo;
     private java.lang.String rejectnum;
     private java.lang.Double rejectqty;
     private java.lang.Long rejlinenum;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Rejectitem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ���
    * @return java.lang.String
    */
	public java.lang.String getBinnum() {
		if(this.binnum==null || this.binnum.length()<=0)
	  		return null;
	  	else
	  		return this.binnum;
	}
	
	/**
    * ���
    * @return java.lang.String
    */
	public void setBinnum(java.lang.String binnum) {
	   this.binnum = binnum;
	}
	
	
    /**
    * ʹ����
    * @return java.lang.String
    */
	public java.lang.String getCustodian() {
		if(this.custodian==null || this.custodian.length()<=0)
	  		return null;
	  	else
	  		return this.custodian;
	}
	
	/**
    * ʹ����
    * @return java.lang.String
    */
	public void setCustodian(java.lang.String custodian) {
	   this.custodian = custodian;
	}
	
	
    /**
    * �ʲ�Ŀǰ���ڲ���
    * @return java.lang.String
    */
	public java.lang.String getDeptnum() {
		if(this.deptnum==null || this.deptnum.length()<=0)
	  		return null;
	  	else
	  		return this.deptnum;
	}
	
	/**
    * �ʲ�Ŀǰ���ڲ���
    * @return java.lang.String
    */
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	
	
    /**
    * �豸���
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸���
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * ������Դ��¼ID
    * @return java.lang.Long
    */
	public java.lang.Long getInvstockid() {
	  		return this.invstockid;
	}
	
	/**
    * ������Դ��¼ID
    * @return java.lang.Long
    */
	public void setInvstockid(java.lang.Long invstockid) {
	   this.invstockid = invstockid;
	}
	
	
    /**
    * �Ƿ񱨷�
    * @return java.lang.String
    */
	public java.lang.String getIsreject() {
		if(this.isreject==null || this.isreject.length()<=0)
	  		return null;
	  	else
	  		return this.isreject;
	}
	
	/**
    * �Ƿ񱨷�
    * @return java.lang.String
    */
	public void setIsreject(java.lang.String isreject) {
	   this.isreject = isreject;
	}
	
	
    /**
    * �����
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �����
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * �ʲ�Ŀǰ����λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * �ʲ�Ŀǰ����λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ���κ�
    * @return java.lang.String
    */
	public java.lang.String getLotnum() {
		if(this.lotnum==null || this.lotnum.length()<=0)
	  		return null;
	  	else
	  		return this.lotnum;
	}
	
	/**
    * ���κ�
    * @return java.lang.String
    */
	public void setLotnum(java.lang.String lotnum) {
	   this.lotnum = lotnum;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * �����ϵ����
    * @return java.lang.String
    */
	public java.lang.String getRejectnum() {
		if(this.rejectnum==null || this.rejectnum.length()<=0)
	  		return null;
	  	else
	  		return this.rejectnum;
	}
	
	/**
    * �����ϵ����
    * @return java.lang.String
    */
	public void setRejectnum(java.lang.String rejectnum) {
	   this.rejectnum = rejectnum;
	}
	
	
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getRejectqty() {
	  		return this.rejectqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setRejectqty(java.lang.Double rejectqty) {
	   this.rejectqty = rejectqty;
	}
	
	
    /**
    * ���ϵ���ϸ�к�
    * @return java.lang.Long
    */
	public java.lang.Long getRejlinenum() {
	  		return this.rejlinenum;
	}
	
	/**
    * ���ϵ���ϸ�к�
    * @return java.lang.Long
    */
	public void setRejlinenum(java.lang.Long rejlinenum) {
	   this.rejlinenum = rejlinenum;
	}
	
	
    /**
    * �ⷿ
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ⷿ
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}