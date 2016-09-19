package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Invlot extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String itemnum;
     private java.lang.Double lotcost;
     private java.lang.Double lotlinecost;
     private java.lang.String lotnum;
     private java.lang.String manufacturer;
     private java.lang.Double shelflife;
     private java.util.Date usebydate;
     private java.lang.String vendor;
     private java.lang.String warehouse;
     
    /** default constructor */
    public Invlot(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * ���γɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLotcost() {
	  		return this.lotcost;
	}
	
	/**
    * ���γɱ�
    * @return java.lang.Double
    */
	public void setLotcost(java.lang.Double lotcost) {
	   this.lotcost = lotcost;
	}
	
	
    /**
    * �����гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLotlinecost() {
	  		return this.lotlinecost;
	}
	
	/**
    * �����гɱ�
    * @return java.lang.Double
    */
	public void setLotlinecost(java.lang.Double lotlinecost) {
	   this.lotlinecost = lotlinecost;
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
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * ��Ч��
    * @return java.lang.Double
    */
	public java.lang.Double getShelflife() {
	  		return this.shelflife;
	}
	
	/**
    * ��Ч��
    * @return java.lang.Double
    */
	public void setShelflife(java.lang.Double shelflife) {
	   this.shelflife = shelflife;
	}
	
	
    /**
    * ʹ������
    * @return java.util.Date
    */
	public java.util.Date getUsebydate() {
	  		return this.usebydate;
	}
	
	/**
    * ʹ������
    * @return java.util.Date
    */
	public void setUsebydate(java.util.Date usebydate) {
	   this.usebydate = usebydate;
	}
	
	
    /**
    * ��Ӧ��
    * @return java.lang.String
    */
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}
	
	/**
    * ��Ӧ��
    * @return java.lang.String
    */
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
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