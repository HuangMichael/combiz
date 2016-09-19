package combiz.domain.rfq;

import combiz.system.IBOBaseObject;

public class Rfqquoteline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Long polinenum;
     private java.util.Date quoteenddate;
     private java.lang.String modelnum;
     private java.lang.Double orderqty;
     private java.lang.String orderunit;
     private java.lang.Double unitcost;
     private java.lang.Double linecost;
     private java.lang.Double conversion;
     private java.util.Date deliverydate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     private java.lang.String isawarded;
     private java.lang.String taxcode;
     private java.lang.Double tax;
     private java.lang.String isservice;
     private java.lang.String remark;
     private java.util.Date quotestartdate;
     private java.lang.String rfqnum;
     private java.lang.Long rfqlinenum;
     private java.lang.String vendor;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String manufacturer;
     private java.lang.String ponum;
     
    /** default constructor */
    public Rfqquoteline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ���ɵĲɹ����к�
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * ���ɵĲɹ����к�
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * ������ֹ��
    * @return java.util.Date
    */
	public java.util.Date getQuoteenddate() {
	  		return this.quoteenddate;
	}
	
	/**
    * ������ֹ��
    * @return java.util.Date
    */
	public void setQuoteenddate(java.util.Date quoteenddate) {
	   this.quoteenddate = quoteenddate;
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
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getOrderqty() {
	  		return this.orderqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setOrderqty(java.lang.Double orderqty) {
	   this.orderqty = orderqty;
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
    * ��λ�ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * ��λ�ɱ�
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
	}
	
	
    /**
    * �гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * �гɱ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public java.lang.Double getConversion() {
	  		return this.conversion;
	}
	
	/**
    * ת��ϵ��
    * @return java.lang.Double
    */
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getDeliverydate() {
	  		return this.deliverydate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setDeliverydate(java.util.Date deliverydate) {
	   this.deliverydate = deliverydate;
	}
	
	
    /**
    * ¼������
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * ¼������
    * @return java.util.Date
    */
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	
	
    /**
    * ¼����
    * @return java.lang.String
    */
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}
	
	/**
    * ¼����
    * @return java.lang.String
    */
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	
	
    /**
    * �Ƿ�����
    * @return java.lang.String
    */
	public java.lang.String getIsawarded() {
		if(this.isawarded==null || this.isawarded.length()<=0)
	  		return null;
	  	else
	  		return this.isawarded;
	}
	
	/**
    * �Ƿ�����
    * @return java.lang.String
    */
	public void setIsawarded(java.lang.String isawarded) {
	   this.isawarded = isawarded;
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
    * ˰��
    * @return java.lang.Double
    */
	public java.lang.Double getTax() {
	  		return this.tax;
	}
	
	/**
    * ˰��
    * @return java.lang.Double
    */
	public void setTax(java.lang.Double tax) {
	   this.tax = tax;
	}
	
	
    /**
    * �Ƿ����
    * @return java.lang.String
    */
	public java.lang.String getIsservice() {
		if(this.isservice==null || this.isservice.length()<=0)
	  		return null;
	  	else
	  		return this.isservice;
	}
	
	/**
    * �Ƿ����
    * @return java.lang.String
    */
	public void setIsservice(java.lang.String isservice) {
	   this.isservice = isservice;
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
    * ������ʼ��
    * @return java.util.Date
    */
	public java.util.Date getQuotestartdate() {
	  		return this.quotestartdate;
	}
	
	/**
    * ������ʼ��
    * @return java.util.Date
    */
	public void setQuotestartdate(java.util.Date quotestartdate) {
	   this.quotestartdate = quotestartdate;
	}
	
	
    /**
    * ѯ�۵���
    * @return java.lang.String
    */
	public java.lang.String getRfqnum() {
		if(this.rfqnum==null || this.rfqnum.length()<=0)
	  		return null;
	  	else
	  		return this.rfqnum;
	}
	
	/**
    * ѯ�۵���
    * @return java.lang.String
    */
	public void setRfqnum(java.lang.String rfqnum) {
	   this.rfqnum = rfqnum;
	}
	
	
    /**
    * ѯ�۵��к�
    * @return java.lang.Long
    */
	public java.lang.Long getRfqlinenum() {
	  		return this.rfqlinenum;
	}
	
	/**
    * ѯ�۵��к�
    * @return java.lang.Long
    */
	public void setRfqlinenum(java.lang.Long rfqlinenum) {
	   this.rfqlinenum = rfqlinenum;
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
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getManufacturer() {
		if(this.manufacturer==null || this.manufacturer.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturer;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setManufacturer(java.lang.String manufacturer) {
	   this.manufacturer = manufacturer;
	}
	
	
    /**
    * ���ɵĲɹ�����
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * ���ɵĲɹ�����
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
}