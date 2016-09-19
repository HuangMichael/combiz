package combiz.domain.po;

import combiz.system.IBOBaseObject;

public class Invvendor extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date biddate;
     private java.lang.Double bidprice;
     private java.lang.String isdefault;
     private java.lang.String itemnum;
     private java.lang.Double lastcost;
     private java.util.Date lastdate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String orderunit;
     private java.util.Date promtime;
     private java.lang.String taxcode;
     private java.lang.String vendor;
     
    /** default constructor */
    public Invvendor(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * Ͷ������
    * @return java.util.Date
    */
	public java.util.Date getBiddate() {
	  		return this.biddate;
	}
	
	/**
    * Ͷ������
    * @return java.util.Date
    */
	public void setBiddate(java.util.Date biddate) {
	   this.biddate = biddate;
	}
	
	
    /**
    * Ͷ��۸�
    * @return java.lang.Double
    */
	public java.lang.Double getBidprice() {
	  		return this.bidprice;
	}
	
	/**
    * Ͷ��۸�
    * @return java.lang.Double
    */
	public void setBidprice(java.lang.Double bidprice) {
	   this.bidprice = bidprice;
	}
	
	
    /**
    * �Ƿ�Ĭ�Ϲ�Ӧ��
    * @return java.lang.String
    */
	public java.lang.String getIsdefault() {
		if(this.isdefault==null || this.isdefault.length()<=0)
	  		return null;
	  	else
	  		return this.isdefault;
	}
	
	/**
    * �Ƿ�Ĭ�Ϲ�Ӧ��
    * @return java.lang.String
    */
	public void setIsdefault(java.lang.String isdefault) {
	   this.isdefault = isdefault;
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
    * �ϴγɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLastcost() {
	  		return this.lastcost;
	}
	
	/**
    * �ϴγɱ�
    * @return java.lang.Double
    */
	public void setLastcost(java.lang.Double lastcost) {
	   this.lastcost = lastcost;
	}
	
	
    /**
    * ���һ�ι�������
    * @return java.util.Date
    */
	public java.util.Date getLastdate() {
	  		return this.lastdate;
	}
	
	/**
    * ���һ�ι�������
    * @return java.util.Date
    */
	public void setLastdate(java.util.Date lastdate) {
	   this.lastdate = lastdate;
	}
	
	
    /**
    * ���ɳ���
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ���ɳ���
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * �ͺ�
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * �ͺ�
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * ������λ
    * @return java.lang.String
    */
	public java.lang.String getOrderunit() {
		if(this.orderunit==null || this.orderunit.length()<=0)
	  		return null;
	  	else
	  		return this.orderunit;
	}
	
	/**
    * ������λ
    * @return java.lang.String
    */
	public void setOrderunit(java.lang.String orderunit) {
	   this.orderunit = orderunit;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getPromtime() {
	  		return this.promtime;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setPromtime(java.util.Date promtime) {
	   this.promtime = promtime;
	}
	
	
    /**
    * ˰����
    * @return java.lang.String
    */
	public java.lang.String getTaxcode() {
		if(this.taxcode==null || this.taxcode.length()<=0)
	  		return null;
	  	else
	  		return this.taxcode;
	}
	
	/**
    * ˰����
    * @return java.lang.String
    */
	public void setTaxcode(java.lang.String taxcode) {
	   this.taxcode = taxcode;
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
	
	
}