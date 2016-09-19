package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Checkqtyitem extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double accountqty;
     private java.lang.Double actualqty;
     private java.lang.String binnum;
     private java.lang.String checkqtynum;
     private java.lang.String itemdesc;
     private java.lang.String itemnum;
     private java.lang.String lotnum;
     private java.lang.String memo;
     private java.lang.String status;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Checkqtyitem(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getAccountqty() {
	  		return this.accountqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setAccountqty(java.lang.Double accountqty) {
	   this.accountqty = accountqty;
	}
	
	
    /**
    * �̵�����
    * @return java.lang.Double
    */
	public java.lang.Double getActualqty() {
	  		return this.actualqty;
	}
	
	/**
    * �̵�����
    * @return java.lang.Double
    */
	public void setActualqty(java.lang.Double actualqty) {
	   this.actualqty = actualqty;
	}
	
	
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
    * �̵㵥���
    * @return java.lang.String
    */
	public java.lang.String getCheckqtynum() {
		if(this.checkqtynum==null || this.checkqtynum.length()<=0)
	  		return null;
	  	else
	  		return this.checkqtynum;
	}
	
	/**
    * �̵㵥���
    * @return java.lang.String
    */
	public void setCheckqtynum(java.lang.String checkqtynum) {
	   this.checkqtynum = checkqtynum;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getItemdesc() {
		if(this.itemdesc==null || this.itemdesc.length()<=0)
	  		return null;
	  	else
	  		return this.itemdesc;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setItemdesc(java.lang.String itemdesc) {
	   this.itemdesc = itemdesc;
	}
	
	
    /**
    * �����Ŀ���
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �����Ŀ���
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * �̵�״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * �̵�״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * �ⷿ����
    * @return java.lang.String
    */
	public java.lang.String getWarehouse() {
		if(this.warehouse==null || this.warehouse.length()<=0)
	  		return null;
	  	else
	  		return this.warehouse;
	}
	
	/**
    * �ⷿ����
    * @return java.lang.String
    */
	public void setWarehouse(java.lang.String warehouse) {
	   this.warehouse = warehouse;
	}
	
	
}