package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Assetscardline extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String assetcode;
     private java.lang.Double quantity;
     private java.lang.String manufacturers;
     private java.lang.String suppliers;
     private java.lang.String model;
     private java.lang.Double cost;
     private java.lang.Long expectedmonth;
     private java.lang.Long depreciationmonth;
     private java.lang.Double accdepreciation;
     private java.lang.Double networth;
     private java.lang.Double impairment;
     private java.lang.Double net;
     private java.lang.Double residualvalues;
     private java.lang.Double residual;
     private java.lang.Double mthval;
     private java.lang.Double mthamount;
     private java.lang.String year;
     private java.lang.String mon;
     private java.lang.String status;
     
    /** default constructor */
    public Assetscardline(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �豸���
    * @return java.lang.String
    */
	public java.lang.String getAssetcode() {
		if(this.assetcode==null || this.assetcode.length()<=0)
	  		return null;
	  	else
	  		return this.assetcode;
	}
	
	/**
    * �豸���
    * @return java.lang.String
    */
	public void setAssetcode(java.lang.String assetcode) {
	   this.assetcode = assetcode;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getQuantity() {
	  		return this.quantity;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getManufacturers() {
		if(this.manufacturers==null || this.manufacturers.length()<=0)
	  		return null;
	  	else
	  		return this.manufacturers;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setManufacturers(java.lang.String manufacturers) {
	   this.manufacturers = manufacturers;
	}
	
	
    /**
    * ��Ӧ��
    * @return java.lang.String
    */
	public java.lang.String getSuppliers() {
		if(this.suppliers==null || this.suppliers.length()<=0)
	  		return null;
	  	else
	  		return this.suppliers;
	}
	
	/**
    * ��Ӧ��
    * @return java.lang.String
    */
	public void setSuppliers(java.lang.String suppliers) {
	   this.suppliers = suppliers;
	}
	
	
    /**
    * �ͺ�
    * @return java.lang.String
    */
	public java.lang.String getModel() {
		if(this.model==null || this.model.length()<=0)
	  		return null;
	  	else
	  		return this.model;
	}
	
	/**
    * �ͺ�
    * @return java.lang.String
    */
	public void setModel(java.lang.String model) {
	   this.model = model;
	}
	
	
    /**
    * ԭֵ
    * @return java.lang.Double
    */
	public java.lang.Double getCost() {
	  		return this.cost;
	}
	
	/**
    * ԭֵ
    * @return java.lang.Double
    */
	public void setCost(java.lang.Double cost) {
	   this.cost = cost;
	}
	
	
    /**
    * Ԥ��ʹ�õ�����
    * @return java.lang.Long
    */
	public java.lang.Long getExpectedmonth() {
	  		return this.expectedmonth;
	}
	
	/**
    * Ԥ��ʹ�õ�����
    * @return java.lang.Long
    */
	public void setExpectedmonth(java.lang.Long expectedmonth) {
	   this.expectedmonth = expectedmonth;
	}
	
	
    /**
    * �Ѽ����۾�����
    * @return java.lang.Long
    */
	public java.lang.Long getDepreciationmonth() {
	  		return this.depreciationmonth;
	}
	
	/**
    * �Ѽ����۾�����
    * @return java.lang.Long
    */
	public void setDepreciationmonth(java.lang.Long depreciationmonth) {
	   this.depreciationmonth = depreciationmonth;
	}
	
	
    /**
    * �ۼ��۾�
    * @return java.lang.Double
    */
	public java.lang.Double getAccdepreciation() {
	  		return this.accdepreciation;
	}
	
	/**
    * �ۼ��۾�
    * @return java.lang.Double
    */
	public void setAccdepreciation(java.lang.Double accdepreciation) {
	   this.accdepreciation = accdepreciation;
	}
	
	
    /**
    * ��ֵ
    * @return java.lang.Double
    */
	public java.lang.Double getNetworth() {
	  		return this.networth;
	}
	
	/**
    * ��ֵ
    * @return java.lang.Double
    */
	public void setNetworth(java.lang.Double networth) {
	   this.networth = networth;
	}
	
	
    /**
    * ��ֵ׼��
    * @return java.lang.Double
    */
	public java.lang.Double getImpairment() {
	  		return this.impairment;
	}
	
	/**
    * ��ֵ׼��
    * @return java.lang.Double
    */
	public void setImpairment(java.lang.Double impairment) {
	   this.impairment = impairment;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getNet() {
	  		return this.net;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setNet(java.lang.Double net) {
	   this.net = net;
	}
	
	
    /**
    * ��ֵ��
    * @return java.lang.Double
    */
	public java.lang.Double getResidualvalues() {
	  		return this.residualvalues;
	}
	
	/**
    * ��ֵ��
    * @return java.lang.Double
    */
	public void setResidualvalues(java.lang.Double residualvalues) {
	   this.residualvalues = residualvalues;
	}
	
	
    /**
    * ��ֵ
    * @return java.lang.Double
    */
	public java.lang.Double getResidual() {
	  		return this.residual;
	}
	
	/**
    * ��ֵ
    * @return java.lang.Double
    */
	public void setResidual(java.lang.Double residual) {
	   this.residual = residual;
	}
	
	
    /**
    * ���۾���
    * @return java.lang.Double
    */
	public java.lang.Double getMthval() {
	  		return this.mthval;
	}
	
	/**
    * ���۾���
    * @return java.lang.Double
    */
	public void setMthval(java.lang.Double mthval) {
	   this.mthval = mthval;
	}
	
	
    /**
    * ���۾ɶ�
    * @return java.lang.Double
    */
	public java.lang.Double getMthamount() {
	  		return this.mthamount;
	}
	
	/**
    * ���۾ɶ�
    * @return java.lang.Double
    */
	public void setMthamount(java.lang.Double mthamount) {
	   this.mthamount = mthamount;
	}
	
	
    /**
    * ��
    * @return java.lang.String
    */
	public java.lang.String getYear() {
		if(this.year==null || this.year.length()<=0)
	  		return null;
	  	else
	  		return this.year;
	}
	
	/**
    * ��
    * @return java.lang.String
    */
	public void setYear(java.lang.String year) {
	   this.year = year;
	}
	
	
    /**
    * ��
    * @return java.lang.String
    */
	public java.lang.String getMon() {
		if(this.mon==null || this.mon.length()<=0)
	  		return null;
	  	else
	  		return this.mon;
	}
	
	/**
    * ��
    * @return java.lang.String
    */
	public void setMon(java.lang.String mon) {
	   this.mon = mon;
	}
	
	
    /**
    * ��ת״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ��ת״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
}