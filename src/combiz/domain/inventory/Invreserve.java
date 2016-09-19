package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invreserve extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double actualqty;
     private java.lang.String buditem;
     private java.lang.String budnum;
     private java.lang.String directreq;
     private java.lang.String eqnum;
     private java.lang.String issuedeptnum;
     private java.lang.String issuetolabor;
     private java.lang.String itemnum;
     private java.lang.String location;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.String reqby;
     private java.util.Date reqdate;
     private java.lang.String reqnum;
     private java.util.Date requireddate;
     private java.lang.Double reservedqty;
     private java.lang.String warehouse;
     private java.lang.String wonum;
     
    /** default constructor */
    public Invreserve(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ʵ�ʷ�������_�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getActualqty() {
	  		return this.actualqty;
	}
	
	/**
    * ʵ�ʷ�������_�����ֶ�
    * @return java.lang.Double
    */
	public void setActualqty(java.lang.Double actualqty) {
	   this.actualqty = actualqty;
	}
	
	
    /**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getBuditem() {
		if(this.buditem==null || this.buditem.length()<=0)
	  		return null;
	  	else
	  		return this.buditem;
	}
	
	/**
    * Ԥ����Ŀ
    * @return java.lang.String
    */
	public void setBuditem(java.lang.String buditem) {
	   this.buditem = buditem;
	}
	
	
    /**
    * Ԥ���
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * Ԥ���
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
    /**
    * ֱ�����룿
    * @return java.lang.String
    */
	public java.lang.String getDirectreq() {
		if(this.directreq==null || this.directreq.length()<=0)
	  		return null;
	  	else
	  		return this.directreq;
	}
	
	/**
    * ֱ�����룿
    * @return java.lang.String
    */
	public void setDirectreq(java.lang.String directreq) {
	   this.directreq = directreq;
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
    * ����������
    * @return java.lang.String
    */
	public java.lang.String getIssuedeptnum() {
		if(this.issuedeptnum==null || this.issuedeptnum.length()<=0)
	  		return null;
	  	else
	  		return this.issuedeptnum;
	}
	
	/**
    * ����������
    * @return java.lang.String
    */
	public void setIssuedeptnum(java.lang.String issuedeptnum) {
	   this.issuedeptnum = issuedeptnum;
	}
	
	
    /**
    * ����Ա��
    * @return java.lang.String
    */
	public java.lang.String getIssuetolabor() {
		if(this.issuetolabor==null || this.issuetolabor.length()<=0)
	  		return null;
	  	else
	  		return this.issuetolabor;
	}
	
	/**
    * ����Ա��
    * @return java.lang.String
    */
	public void setIssuetolabor(java.lang.String issuetolabor) {
	   this.issuetolabor = issuetolabor;
	}
	
	
    /**
    * ���ʱ���
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ���ʱ���
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * Ԥ��λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * Ԥ��λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * Ԥ���Ĳɹ�����
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * Ԥ���Ĳɹ�����
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * Ԥ���Ĳɹ���
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * Ԥ���Ĳɹ���
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getReqby() {
		if(this.reqby==null || this.reqby.length()<=0)
	  		return null;
	  	else
	  		return this.reqby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setReqby(java.lang.String reqby) {
	   this.reqby = reqby;
	}
	
	
    /**
    * �����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getReqdate() {
	  		return this.reqdate;
	}
	
	/**
    * �����ʱ��
    * @return java.util.Date
    */
	public void setReqdate(java.util.Date reqdate) {
	   this.reqdate = reqdate;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getReqnum() {
		if(this.reqnum==null || this.reqnum.length()<=0)
	  		return null;
	  	else
	  		return this.reqnum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setReqnum(java.lang.String reqnum) {
	   this.reqnum = reqnum;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRequireddate() {
	  		return this.requireddate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRequireddate(java.util.Date requireddate) {
	   this.requireddate = requireddate;
	}
	
	
    /**
    * Ԥ������
    * @return java.lang.Double
    */
	public java.lang.Double getReservedqty() {
	  		return this.reservedqty;
	}
	
	/**
    * Ԥ������
    * @return java.lang.Double
    */
	public void setReservedqty(java.lang.Double reservedqty) {
	   this.reservedqty = reservedqty;
	}
	
	
    /**
    * �ֿ�
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ֿ�
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}