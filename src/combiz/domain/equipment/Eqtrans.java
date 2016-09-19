package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Eqtrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.util.Date readingdate;
     private java.lang.String eqnum;
     private java.util.Date datemoved;
     private java.lang.String fromparent;
     private java.lang.String toparent;
     private java.lang.String fromloc;
     private java.lang.String toloc;
     private java.util.Date transdate;
     private java.lang.String moveby;
     private java.lang.String memo;
     private java.lang.String wonum;
     private java.lang.String ponum;
     private java.lang.String fromcustodian;
     private java.lang.String tocustodian;
     private java.lang.String fromdeptnum;
     private java.lang.String todeptnum;
     private java.lang.String fromlabornum;
     private java.lang.String tolabornum;
     
    /** default constructor */
    public Eqtrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getReadingdate() {
	  		return this.readingdate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setReadingdate(java.util.Date readingdate) {
	   this.readingdate = readingdate;
	}
	
	
    /**
    * �ʲ����
    * @return java.lang.String
    */
	public java.lang.String getEqnum() {
		if(this.eqnum==null || this.eqnum.length()<=0)
	  		return null;
	  	else
	  		return this.eqnum;
	}
	
	/**
    * �ʲ����
    * @return java.lang.String
    */
	public void setEqnum(java.lang.String eqnum) {
	   this.eqnum = eqnum;
	}
	
	
    /**
    * �ƶ�����
    * @return java.util.Date
    */
	public java.util.Date getDatemoved() {
	  		return this.datemoved;
	}
	
	/**
    * �ƶ�����
    * @return java.util.Date
    */
	public void setDatemoved(java.util.Date datemoved) {
	   this.datemoved = datemoved;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getFromparent() {
		if(this.fromparent==null || this.fromparent.length()<=0)
	  		return null;
	  	else
	  		return this.fromparent;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setFromparent(java.lang.String fromparent) {
	   this.fromparent = fromparent;
	}
	
	
    /**
    * Ŀ��
    * @return java.lang.String
    */
	public java.lang.String getToparent() {
		if(this.toparent==null || this.toparent.length()<=0)
	  		return null;
	  	else
	  		return this.toparent;
	}
	
	/**
    * Ŀ��
    * @return java.lang.String
    */
	public void setToparent(java.lang.String toparent) {
	   this.toparent = toparent;
	}
	
	
    /**
    * �춯ǰλ��
    * @return java.lang.String
    */
	public java.lang.String getFromloc() {
		if(this.fromloc==null || this.fromloc.length()<=0)
	  		return null;
	  	else
	  		return this.fromloc;
	}
	
	/**
    * �춯ǰλ��
    * @return java.lang.String
    */
	public void setFromloc(java.lang.String fromloc) {
	   this.fromloc = fromloc;
	}
	
	
    /**
    * �춯��λ��
    * @return java.lang.String
    */
	public java.lang.String getToloc() {
		if(this.toloc==null || this.toloc.length()<=0)
	  		return null;
	  	else
	  		return this.toloc;
	}
	
	/**
    * �춯��λ��
    * @return java.lang.String
    */
	public void setToloc(java.lang.String toloc) {
	   this.toloc = toloc;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	
	
    /**
    * �ֶ�MOVEBY
    * @return java.lang.String
    */
	public java.lang.String getMoveby() {
		if(this.moveby==null || this.moveby.length()<=0)
	  		return null;
	  	else
	  		return this.moveby;
	}
	
	/**
    * �ֶ�MOVEBY
    * @return java.lang.String
    */
	public void setMoveby(java.lang.String moveby) {
	   this.moveby = moveby;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
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
    * ԭʹ����
    * @return java.lang.String
    */
	public java.lang.String getFromcustodian() {
		if(this.fromcustodian==null || this.fromcustodian.length()<=0)
	  		return null;
	  	else
	  		return this.fromcustodian;
	}
	
	/**
    * ԭʹ����
    * @return java.lang.String
    */
	public void setFromcustodian(java.lang.String fromcustodian) {
	   this.fromcustodian = fromcustodian;
	}
	
	
    /**
    * ��ʹ����
    * @return java.lang.String
    */
	public java.lang.String getTocustodian() {
		if(this.tocustodian==null || this.tocustodian.length()<=0)
	  		return null;
	  	else
	  		return this.tocustodian;
	}
	
	/**
    * ��ʹ����
    * @return java.lang.String
    */
	public void setTocustodian(java.lang.String tocustodian) {
	   this.tocustodian = tocustodian;
	}
	
	
    /**
    * ԭ���ڲ���
    * @return java.lang.String
    */
	public java.lang.String getFromdeptnum() {
		if(this.fromdeptnum==null || this.fromdeptnum.length()<=0)
	  		return null;
	  	else
	  		return this.fromdeptnum;
	}
	
	/**
    * ԭ���ڲ���
    * @return java.lang.String
    */
	public void setFromdeptnum(java.lang.String fromdeptnum) {
	   this.fromdeptnum = fromdeptnum;
	}
	
	
    /**
    * �����ڲ���
    * @return java.lang.String
    */
	public java.lang.String getTodeptnum() {
		if(this.todeptnum==null || this.todeptnum.length()<=0)
	  		return null;
	  	else
	  		return this.todeptnum;
	}
	
	/**
    * �����ڲ���
    * @return java.lang.String
    */
	public void setTodeptnum(java.lang.String todeptnum) {
	   this.todeptnum = todeptnum;
	}
	
	
    /**
    * ԭ������
    * @return java.lang.String
    */
	public java.lang.String getFromlabornum() {
		if(this.fromlabornum==null || this.fromlabornum.length()<=0)
	  		return null;
	  	else
	  		return this.fromlabornum;
	}
	
	/**
    * ԭ������
    * @return java.lang.String
    */
	public void setFromlabornum(java.lang.String fromlabornum) {
	   this.fromlabornum = fromlabornum;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getTolabornum() {
		if(this.tolabornum==null || this.tolabornum.length()<=0)
	  		return null;
	  	else
	  		return this.tolabornum;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setTolabornum(java.lang.String tolabornum) {
	   this.tolabornum = tolabornum;
	}
	
	
}