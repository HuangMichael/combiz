package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invstock extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String binnum;
     private java.lang.Double curbal;
     private java.lang.String itemnum;
     private java.lang.String lotnum;
     private java.lang.Double physcnt;
     private java.util.Date physcntdate;
     private java.lang.String reconciled;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Invstock(){}
    
   
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
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getCurbal() {
	  		return this.curbal;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setCurbal(java.lang.Double curbal) {
	   this.curbal = curbal;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ������
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
    * �̵�����
    * @return java.lang.Double
    */
	public java.lang.Double getPhyscnt() {
	  		return this.physcnt;
	}
	
	/**
    * �̵�����
    * @return java.lang.Double
    */
	public void setPhyscnt(java.lang.Double physcnt) {
	   this.physcnt = physcnt;
	}
	
	
    /**
    * �̵�����
    * @return java.util.Date
    */
	public java.util.Date getPhyscntdate() {
	  		return this.physcntdate;
	}
	
	/**
    * �̵�����
    * @return java.util.Date
    */
	public void setPhyscntdate(java.util.Date physcntdate) {
	   this.physcntdate = physcntdate;
	}
	
	
    /**
    * �Ƿ����
    * @return java.lang.String
    */
	public java.lang.String getReconciled() {
		if(this.reconciled==null || this.reconciled.length()<=0)
	  		return null;
	  	else
	  		return this.reconciled;
	}
	
	/**
    * �Ƿ����
    * @return java.lang.String
    */
	public void setReconciled(java.lang.String reconciled) {
	   this.reconciled = reconciled;
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
	
	
}