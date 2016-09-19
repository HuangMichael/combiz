package combiz.domain.workorder;

import combiz.system.IBOBaseObject;

public class Wptool extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String jpnum;
     private java.lang.Double linecost;
     private java.lang.Double rate;
     private java.lang.String tasknum;
     private java.lang.Double toolhrs;
     private java.lang.String toolnum;
     private java.lang.Long toolqty;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wptool(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ҵ�����
    * @return java.lang.String
    */
	public java.lang.String getJpnum() {
		if(this.jpnum==null || this.jpnum.length()<=0)
	  		return null;
	  	else
	  		return this.jpnum;
	}
	
	/**
    * ��ҵ�����
    * @return java.lang.String
    */
	public void setJpnum(java.lang.String jpnum) {
	   this.jpnum = jpnum;
	}
	
	
    /**
    * ���ܼ�
    * @return java.lang.Double
    */
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}
	
	/**
    * ���ܼ�
    * @return java.lang.Double
    */
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getRate() {
	  		return this.rate;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setRate(java.lang.Double rate) {
	   this.rate = rate;
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
    * ���߹�ʱ
    * @return java.lang.Double
    */
	public java.lang.Double getToolhrs() {
	  		return this.toolhrs;
	}
	
	/**
    * ���߹�ʱ
    * @return java.lang.Double
    */
	public void setToolhrs(java.lang.Double toolhrs) {
	   this.toolhrs = toolhrs;
	}
	
	
    /**
    * ���߱��
    * @return java.lang.String
    */
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}
	
	/**
    * ���߱��
    * @return java.lang.String
    */
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
	
	
    /**
    * ��������
    * @return java.lang.Long
    */
	public java.lang.Long getToolqty() {
	  		return this.toolqty;
	}
	
	/**
    * ��������
    * @return java.lang.Long
    */
	public void setToolqty(java.lang.Long toolqty) {
	   this.toolqty = toolqty;
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