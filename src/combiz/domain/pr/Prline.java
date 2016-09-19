package combiz.domain.pr;

import combiz.system.IBOBaseObject;

public class Prline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String prnum;
     private java.lang.Long prlinenum;
     private java.lang.String itemnum;
     private java.lang.String description;
     private java.lang.String warehouse;
     private java.lang.Double orderqty;
     private java.lang.String orderunit;
     private java.lang.Double conversion;
     private java.lang.Double unitcost;
     private java.lang.Double linecost;
     private java.util.Date reqdeliverydate;
     private java.util.Date vendeliverydate;
     private java.util.Date enterdate;
     private java.lang.String enterby;
     private java.lang.String requestedby;
     private java.lang.String manufacturer;
     private java.lang.String modelnum;
     private java.lang.String ponum;
     private java.lang.Long polinenum;
     private java.lang.String stocktype;
     private java.lang.String remark;
     private java.lang.String isservice;
     private java.lang.Double loadedcost;
     private java.lang.String prorateservice;
     private java.lang.String rfqnum;
     private java.lang.Long rfqlinenum;
     private java.lang.String inspection;
     private java.lang.String location;
     private java.lang.String eqnum;
     private java.lang.String wonum;
     private java.lang.String tasknum;
     private java.lang.Long toprlinenum;
     private java.lang.String toprnum;
     private java.lang.String buditem;
     private java.lang.String budnum;
     
    /** default constructor */
    public Prline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ɹ������
    * @return java.lang.String
    */
	public java.lang.String getPrnum() {
		if(this.prnum==null || this.prnum.length()<=0)
	  		return null;
	  	else
	  		return this.prnum;
	}
	
	/**
    * �ɹ������
    * @return java.lang.String
    */
	public void setPrnum(java.lang.String prnum) {
	   this.prnum = prnum;
	}
	
	
    /**
    * �ɹ������к�
    * @return java.lang.Long
    */
	public java.lang.Long getPrlinenum() {
	  		return this.prlinenum;
	}
	
	/**
    * �ɹ������к�
    * @return java.lang.Long
    */
	public void setPrlinenum(java.lang.Long prlinenum) {
	   this.prlinenum = prlinenum;
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
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getUnitcost() {
	  		return this.unitcost;
	}
	
	/**
    * ����
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
    * Ҫ������
    * @return java.util.Date
    */
	public java.util.Date getReqdeliverydate() {
	  		return this.reqdeliverydate;
	}
	
	/**
    * Ҫ������
    * @return java.util.Date
    */
	public void setReqdeliverydate(java.util.Date reqdeliverydate) {
	   this.reqdeliverydate = reqdeliverydate;
	}
	
	
    /**
    * ��Ӧ�̽�������
    * @return java.util.Date
    */
	public java.util.Date getVendeliverydate() {
	  		return this.vendeliverydate;
	}
	
	/**
    * ��Ӧ�̽�������
    * @return java.util.Date
    */
	public void setVendeliverydate(java.util.Date vendeliverydate) {
	   this.vendeliverydate = vendeliverydate;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}
	
	/**
    * ��������
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
    * �ɹ���
    * @return java.lang.String
    */
	public java.lang.String getPonum() {
		if(this.ponum==null || this.ponum.length()<=0)
	  		return null;
	  	else
	  		return this.ponum;
	}
	
	/**
    * �ɹ���
    * @return java.lang.String
    */
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	
	
    /**
    * �ɹ����к�
    * @return java.lang.Long
    */
	public java.lang.Long getPolinenum() {
	  		return this.polinenum;
	}
	
	/**
    * �ɹ����к�
    * @return java.lang.Long
    */
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
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
    * ����
    * @return java.lang.String
    */
	public java.lang.String getIsservice() {
		if(this.isservice==null || this.isservice.length()<=0)
	  		return null;
	  	else
	  		return this.isservice;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setIsservice(java.lang.String isservice) {
	   this.isservice = isservice;
	}
	
	
    /**
    * �����гɱ�
    * @return java.lang.Double
    */
	public java.lang.Double getLoadedcost() {
	  		return this.loadedcost;
	}
	
	/**
    * �����гɱ�
    * @return java.lang.Double
    */
	public void setLoadedcost(java.lang.Double loadedcost) {
	   this.loadedcost = loadedcost;
	}
	
	
    /**
    * ��̯����
    * @return java.lang.String
    */
	public java.lang.String getProrateservice() {
		if(this.prorateservice==null || this.prorateservice.length()<=0)
	  		return null;
	  	else
	  		return this.prorateservice;
	}
	
	/**
    * ��̯����
    * @return java.lang.String
    */
	public void setProrateservice(java.lang.String prorateservice) {
	   this.prorateservice = prorateservice;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRfqnum() {
		if(this.rfqnum==null || this.rfqnum.length()<=0)
	  		return null;
	  	else
	  		return this.rfqnum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRfqnum(java.lang.String rfqnum) {
	   this.rfqnum = rfqnum;
	}
	
	
    /**
    * �������к�
    * @return java.lang.Long
    */
	public java.lang.Long getRfqlinenum() {
	  		return this.rfqlinenum;
	}
	
	/**
    * �������к�
    * @return java.lang.Long
    */
	public void setRfqlinenum(java.lang.Long rfqlinenum) {
	   this.rfqlinenum = rfqlinenum;
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
    * �豸
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getTasknum() {
		if(this.tasknum==null || this.tasknum.length()<=0)
	  		return null;
	  	else
	  		return this.tasknum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setTasknum(java.lang.String tasknum) {
	   this.tasknum = tasknum;
	}
	
	
    /**
    * ����������ƻ��ı��
    * @return java.lang.Long
    */
	public java.lang.Long getToprlinenum() {
	  		return this.toprlinenum;
	}
	
	/**
    * ����������ƻ��ı��
    * @return java.lang.Long
    */
	public void setToprlinenum(java.lang.Long toprlinenum) {
	   this.toprlinenum = toprlinenum;
	}
	
	
    /**
    * ����������ƻ��ı��
    * @return java.lang.String
    */
	public java.lang.String getToprnum() {
		if(this.toprnum==null || this.toprnum.length()<=0)
	  		return null;
	  	else
	  		return this.toprnum;
	}
	
	/**
    * ����������ƻ��ı��
    * @return java.lang.String
    */
	public void setToprnum(java.lang.String toprnum) {
	   this.toprnum = toprnum;
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
    * Ԥ����
    * @return java.lang.String
    */
	public java.lang.String getBudnum() {
		if(this.budnum==null || this.budnum.length()<=0)
	  		return null;
	  	else
	  		return this.budnum;
	}
	
	/**
    * Ԥ����
    * @return java.lang.String
    */
	public void setBudnum(java.lang.String budnum) {
	   this.budnum = budnum;
	}
	
	
}