package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Dephistory extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double depcost;
     private java.util.Date depdate;
     private java.lang.Double deprate;
     private java.lang.String eqnum;
     private java.lang.String labornum;
     private java.lang.Double nowcost;
     private java.lang.Long planyears;
     
    /** default constructor */
    public Dephistory(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �۾ɶ�
    * @return java.lang.Double
    */
	public java.lang.Double getDepcost() {
	  		return this.depcost;
	}
	
	/**
    * �۾ɶ�
    * @return java.lang.Double
    */
	public void setDepcost(java.lang.Double depcost) {
	   this.depcost = depcost;
	}
	
	
    /**
    * �۾�ʱ��
    * @return java.util.Date
    */
	public java.util.Date getDepdate() {
	  		return this.depdate;
	}
	
	/**
    * �۾�ʱ��
    * @return java.util.Date
    */
	public void setDepdate(java.util.Date depdate) {
	   this.depdate = depdate;
	}
	
	
    /**
    * �۾���
    * @return java.lang.Double
    */
	public java.lang.Double getDeprate() {
	  		return this.deprate;
	}
	
	/**
    * �۾���
    * @return java.lang.Double
    */
	public void setDeprate(java.lang.Double deprate) {
	   this.deprate = deprate;
	}
	
	
    /**
    * �豸����
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸����
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * �۾�Ա
    * @return java.lang.String
    */
	public java.lang.String getLabornum() {
		if(this.labornum==null || this.labornum.length()<=0)
	  		return null;
	  	else
	  		return this.labornum;
	}
	
	/**
    * �۾�Ա
    * @return java.lang.String
    */
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
	
	
    /**
    * ��ǰֵ
    * @return java.lang.Double
    */
	public java.lang.Double getNowcost() {
	  		return this.nowcost;
	}
	
	/**
    * ��ǰֵ
    * @return java.lang.Double
    */
	public void setNowcost(java.lang.Double nowcost) {
	   this.nowcost = nowcost;
	}
	
	
    /**
    * Ԥ��ʹ������
    * @return java.lang.Long
    */
	public java.lang.Long getPlanyears() {
	  		return this.planyears;
	}
	
	/**
    * Ԥ��ʹ������
    * @return java.lang.Long
    */
	public void setPlanyears(java.lang.Long planyears) {
	   this.planyears = planyears;
	}
	
	
}