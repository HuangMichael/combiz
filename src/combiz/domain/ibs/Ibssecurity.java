package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibssecurity extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String ipseg1;
     private java.lang.String ipseg2;
     private java.lang.String ipseg21;
     private java.lang.String ipseg22;
     private java.lang.String ipseg23;
     private java.lang.String ipseg24;
     private java.lang.String ipseg3;
     private java.lang.String ipseg4;
     private java.lang.String remark;
     private java.lang.String sectype;
     
    /** default constructor */
    public Ibssecurity(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ʼIP��1
    * @return java.lang.String
    */
	public java.lang.String getIpseg1() {
		if(this.ipseg1==null || this.ipseg1.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg1;
	}
	
	/**
    * ��ʼIP��1
    * @return java.lang.String
    */
	public void setIpseg1(java.lang.String ipseg1) {
	   this.ipseg1 = ipseg1;
	}
	
	
    /**
    * ��ʼIP��2
    * @return java.lang.String
    */
	public java.lang.String getIpseg2() {
		if(this.ipseg2==null || this.ipseg2.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg2;
	}
	
	/**
    * ��ʼIP��2
    * @return java.lang.String
    */
	public void setIpseg2(java.lang.String ipseg2) {
	   this.ipseg2 = ipseg2;
	}
	
	
    /**
    * ����IP��1
    * @return java.lang.String
    */
	public java.lang.String getIpseg21() {
		if(this.ipseg21==null || this.ipseg21.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg21;
	}
	
	/**
    * ����IP��1
    * @return java.lang.String
    */
	public void setIpseg21(java.lang.String ipseg21) {
	   this.ipseg21 = ipseg21;
	}
	
	
    /**
    * ����IP��2
    * @return java.lang.String
    */
	public java.lang.String getIpseg22() {
		if(this.ipseg22==null || this.ipseg22.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg22;
	}
	
	/**
    * ����IP��2
    * @return java.lang.String
    */
	public void setIpseg22(java.lang.String ipseg22) {
	   this.ipseg22 = ipseg22;
	}
	
	
    /**
    * ����IP��3
    * @return java.lang.String
    */
	public java.lang.String getIpseg23() {
		if(this.ipseg23==null || this.ipseg23.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg23;
	}
	
	/**
    * ����IP��3
    * @return java.lang.String
    */
	public void setIpseg23(java.lang.String ipseg23) {
	   this.ipseg23 = ipseg23;
	}
	
	
    /**
    * ����IP��4
    * @return java.lang.String
    */
	public java.lang.String getIpseg24() {
		if(this.ipseg24==null || this.ipseg24.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg24;
	}
	
	/**
    * ����IP��4
    * @return java.lang.String
    */
	public void setIpseg24(java.lang.String ipseg24) {
	   this.ipseg24 = ipseg24;
	}
	
	
    /**
    * ��ʼIP��3
    * @return java.lang.String
    */
	public java.lang.String getIpseg3() {
		if(this.ipseg3==null || this.ipseg3.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg3;
	}
	
	/**
    * ��ʼIP��3
    * @return java.lang.String
    */
	public void setIpseg3(java.lang.String ipseg3) {
	   this.ipseg3 = ipseg3;
	}
	
	
    /**
    * ��ʼIP��4
    * @return java.lang.String
    */
	public java.lang.String getIpseg4() {
		if(this.ipseg4==null || this.ipseg4.length()<=0)
	  		return null;
	  	else
	  		return this.ipseg4;
	}
	
	/**
    * ��ʼIP��4
    * @return java.lang.String
    */
	public void setIpseg4(java.lang.String ipseg4) {
	   this.ipseg4 = ipseg4;
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
    * �������ͣ��ܾ�������
    * @return java.lang.String
    */
	public java.lang.String getSectype() {
		if(this.sectype==null || this.sectype.length()<=0)
	  		return null;
	  	else
	  		return this.sectype;
	}
	
	/**
    * �������ͣ��ܾ�������
    * @return java.lang.String
    */
	public void setSectype(java.lang.String sectype) {
	   this.sectype = sectype;
	}
	
	
}