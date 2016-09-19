package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Equipdep extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.Double depcost;
     private java.lang.String depcount;
     private java.lang.String depnum;
     private java.lang.Double deprate;
     private java.lang.String eqnum;
     private java.lang.Double ldepcost;
     private java.lang.Double ldeprate;
     private java.lang.Long linenum;
     private java.lang.Long lusedyears;
     private java.lang.Double nowcost;
     private java.lang.Long planyears;
     private java.lang.String status;
     private java.lang.Long usedyears;
     
    /** default constructor */
    public Equipdep(){}
    
   
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
    * �۾�����
    * @return java.lang.String
    */
	public java.lang.String getDepcount() {
		if(this.depcount==null || this.depcount.length()<=0)
	  		return null;
	  	else
	  		return this.depcount;
	}
	
	/**
    * �۾�����
    * @return java.lang.String
    */
	public void setDepcount(java.lang.String depcount) {
	   this.depcount = depcount;
	}
	
	
    /**
    * �۾ɵ�����
    * @return java.lang.String
    */
	public java.lang.String getDepnum() {
		if(this.depnum==null || this.depnum.length()<=0)
	  		return null;
	  	else
	  		return this.depnum;
	}
	
	/**
    * �۾ɵ�����
    * @return java.lang.String
    */
	public void setDepnum(java.lang.String depnum) {
	   this.depnum = depnum;
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
    * �豸���
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �豸���
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * �۾ɶ�(����)
    * @return java.lang.Double
    */
	public java.lang.Double getLdepcost() {
	  		return this.ldepcost;
	}
	
	/**
    * �۾ɶ�(����)
    * @return java.lang.Double
    */
	public void setLdepcost(java.lang.Double ldepcost) {
	   this.ldepcost = ldepcost;
	}
	
	
    /**
    * �۾���(����)
    * @return java.lang.Double
    */
	public java.lang.Double getLdeprate() {
	  		return this.ldeprate;
	}
	
	/**
    * �۾���(����)
    * @return java.lang.Double
    */
	public void setLdeprate(java.lang.Double ldeprate) {
	   this.ldeprate = ldeprate;
	}
	
	
    /**
    * �к�
    * @return java.lang.Long
    */
	public java.lang.Long getLinenum() {
	  		return this.linenum;
	}
	
	/**
    * �к�
    * @return java.lang.Long
    */
	public void setLinenum(java.lang.Long linenum) {
	   this.linenum = linenum;
	}
	
	
    /**
    * ��ʹ����(����)
    * @return java.lang.Long
    */
	public java.lang.Long getLusedyears() {
	  		return this.lusedyears;
	}
	
	/**
    * ��ʹ����(����)
    * @return java.lang.Long
    */
	public void setLusedyears(java.lang.Long lusedyears) {
	   this.lusedyears = lusedyears;
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
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ��ʹ������
    * @return java.lang.Long
    */
	public java.lang.Long getUsedyears() {
	  		return this.usedyears;
	}
	
	/**
    * ��ʹ������
    * @return java.lang.Long
    */
	public void setUsedyears(java.lang.Long usedyears) {
	   this.usedyears = usedyears;
	}
	
	
}