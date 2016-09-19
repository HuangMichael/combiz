package combiz.domain.po;

import combiz.system.IBOBaseObject;

public class Poline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double receivedqty;
     private java.lang.Double taxrate;
     private java.lang.Double tax;
     private java.lang.Double waitinvqty;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String warehouse;
     private java.lang.Double orderqty;
     private java.lang.String orderunit;
     private java.lang.Double conversion;
     private java.lang.Double unitcost;
     private java.lang.Double taxunitcost;
     private java.lang.Double linecost;
     private java.lang.Double taxlinecost;
     private java.lang.Double waitqty;
     private java.lang.Double receivedunitcost;
     private java.lang.Double receivedtotalcost;
     private java.lang.String taxcode;
     private java.lang.Double rejectedqty;
     private java.lang.String tasknum;
     private java.util.Date vendeliverydate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     private java.lang.String requestedby;
     private java.util.Date reqdeliverydate;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String service;
     private java.lang.String stocktype;
     private java.lang.String remark;
     private java.lang.String location;
     private java.lang.String receiptscomplete;
     private java.lang.String inspection;
     private java.lang.Double loadedcost;
     private java.lang.String prorated;
     private java.lang.Double proratecost;
     private java.lang.String wonum;
     private java.lang.Double realqty;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Poline(){}
    
   
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
	public java.lang.Double getReceivedqty() {
	  		return this.receivedqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setReceivedqty(java.lang.Double receivedqty) {
	   this.receivedqty = receivedqty;
	}
	
	
    /**
    * ˰��
    * @return java.lang.Double
    */
	public java.lang.Double getTaxrate() {
	  		return this.taxrate;
	}
	
	/**
    * ˰��
    * @return java.lang.Double
    */
	public void setTaxrate(java.lang.Double taxrate) {
	   this.taxrate = taxrate;
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
    * δ��������-�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getWaitinvqty() {
	  		return this.waitinvqty;
	}
	
	/**
    * δ��������-�����ֶ�
    * @return java.lang.Double
    */
	public void setWaitinvqty(java.lang.Double waitinvqty) {
	   this.waitinvqty = waitinvqty;
	}
	
	
    /**
    * �ɹ�����
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * �ɹ�����
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * ��
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * ��
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	
	
    /**
    * �����Ŀ
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * �����Ŀ
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
    * ����˰����
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * ����˰����
    * @return java.lang.Double
    */
	public void setUnitcost(java.lang.Double unitcost) {
	   this.unitcost = unitcost;
	}
	
	
    /**
    * ��˰����
    * @return java.lang.Double
    */
	public java.lang.Double getTaxunitcost() {
	  		return this.taxunitcost;
	}
	
	/**
    * ��˰����
    * @return java.lang.Double
    */
	public void setTaxunitcost(java.lang.Double taxunitcost) {
	   this.taxunitcost = taxunitcost;
	}
	
	
    /**
    * ��˰�гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * ��˰�гɱ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ����˰�гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getTaxlinecost() {
	  		return this.taxlinecost;
	}
	
	/**
    * ����˰�гɱ�
    * @return java.lang.Double
    */
	public void setTaxlinecost(java.lang.Double taxlinecost) {
	   this.taxlinecost = taxlinecost;
	}
	
	
    /**
    * Ӧ��������-�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getWaitqty() {
	  		return this.waitqty;
	}
	
	/**
    * Ӧ��������-�����ֶ�
    * @return java.lang.Double
    */
	public void setWaitqty(java.lang.Double waitqty) {
	   this.waitqty = waitqty;
	}
	
	
    /**
    * ���յ���
    * @return java.lang.Double
    */
	public java.lang.Double getReceivedunitcost() {
	  		return this.receivedunitcost;
	}
	
	/**
    * ���յ���
    * @return java.lang.Double
    */
	public void setReceivedunitcost(java.lang.Double receivedunitcost) {
	   this.receivedunitcost = receivedunitcost;
	}
	
	
    /**
    * ���յ����ܳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getReceivedtotalcost() {
	  		return this.receivedtotalcost;
	}
	
	/**
    * ���յ����ܳɱ�
    * @return java.lang.Double
    */
	public void setReceivedtotalcost(java.lang.Double receivedtotalcost) {
	   this.receivedtotalcost = receivedtotalcost;
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
    * ��������
    * @return java.lang.Double
    */
	public java.lang.Double getRejectedqty() {
	  		return this.rejectedqty;
	}
	
	/**
    * ��������
    * @return java.lang.Double
    */
	public void setRejectedqty(java.lang.Double rejectedqty) {
	   this.rejectedqty = rejectedqty;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * ��Ӧ�̷�������
    * @return java.util.Date
    */
	public java.util.Date getVendeliverydate() {
	  		return this.vendeliverydate;
	}
	
	/**
    * ��Ӧ�̷�������
    * @return java.util.Date
    */
	public void setVendeliverydate(java.util.Date vendeliverydate) {
	   this.vendeliverydate = vendeliverydate;
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
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRequestedby() {
		if(this.requestedby==null || this.requestedby.length()<=0)
	  		return null;
	  	else
	  		return this.requestedby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRequestedby(java.lang.String requestedby) {
	   this.requestedby = requestedby;
	}
	
	
    /**
    * Ҫ��Ľ�������
    * @return java.util.Date
    */
	public java.util.Date getReqdeliverydate() {
	  		return this.reqdeliverydate;
	}
	
	/**
    * Ҫ��Ľ�������
    * @return java.util.Date
    */
	public void setReqdeliverydate(java.util.Date reqdeliverydate) {
	   this.reqdeliverydate = reqdeliverydate;
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
    * ��Ʒ�ͺ�
    * @return java.lang.String
    */
	public java.lang.String getModelnum() {
		if(this.modelnum==null || this.modelnum.length()<=0)
	  		return null;
	  	else
	  		return this.modelnum;
	}
	
	/**
    * ��Ʒ�ͺ�
    * @return java.lang.String
    */
	public void setModelnum(java.lang.String modelnum) {
	   this.modelnum = modelnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getService() {
		if(this.service==null || this.service.length()<=0)
	  		return null;
	  	else
	  		return this.service;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setService(java.lang.String service) {
	   this.service = service;
	}
	
	
    /**
    * �������
    * @return java.lang.String
    */
	public java.lang.String getStocktype() {
		if(this.stocktype==null || this.stocktype.length()<=0)
	  		return null;
	  	else
	  		return this.stocktype;
	}
	
	/**
    * �������
    * @return java.lang.String
    */
	public void setStocktype(java.lang.String stocktype) {
	   this.stocktype = stocktype;
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
    * ����λ��
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * ����λ��
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * ��ɽ���
    * @return java.lang.String
    */
	public java.lang.String getReceiptscomplete() {
		if(this.receiptscomplete==null || this.receiptscomplete.length()<=0)
	  		return null;
	  	else
	  		return this.receiptscomplete;
	}
	
	/**
    * ��ɽ���
    * @return java.lang.String
    */
	public void setReceiptscomplete(java.lang.String receiptscomplete) {
	   this.receiptscomplete = receiptscomplete;
	}
	
	
    /**
    * ��Ҫ���
    * @return java.lang.String
    */
	public java.lang.String getInspection() {
		if(this.inspection==null || this.inspection.length()<=0)
	  		return null;
	  	else
	  		return this.inspection;
	}
	
	/**
    * ��Ҫ���
    * @return java.lang.String
    */
	public void setInspection(java.lang.String inspection) {
	   this.inspection = inspection;
	}
	
	
    /**
    * ����ĳɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLoadedcost() {
	  		return this.loadedcost;
	}
	
	/**
    * ����ĳɱ�
    * @return java.lang.Double
    */
	public void setLoadedcost(java.lang.Double loadedcost) {
	   this.loadedcost = loadedcost;
	}
	
	
    /**
    * �ѷ�̯
    * @return java.lang.String
    */
	public java.lang.String getProrated() {
		if(this.prorated==null || this.prorated.length()<=0)
	  		return null;
	  	else
	  		return this.prorated;
	}
	
	/**
    * �ѷ�̯
    * @return java.lang.String
    */
	public void setProrated(java.lang.String prorated) {
	   this.prorated = prorated;
	}
	
	
    /**
    * ��̯����ɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getProratecost() {
	  		return this.proratecost;
	}
	
	/**
    * ��̯����ɱ�
    * @return java.lang.Double
    */
	public void setProratecost(java.lang.Double proratecost) {
	   this.proratecost = proratecost;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
    /**
    * ʵ�ʽ�������-�����ֶ�
    * @return java.lang.Double
    */
	public java.lang.Double getRealqty() {
	  		return this.realqty;
	}
	
	/**
    * ʵ�ʽ�������-�����ֶ�
    * @return java.lang.Double
    */
	public void setRealqty(java.lang.Double realqty) {
	   this.realqty = realqty;
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
	
	
}