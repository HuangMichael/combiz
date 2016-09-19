package combiz.domain.test;

import combiz.system.IBOBaseObject;

public class Wosum extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String location;
     private java.lang.String wodesc;
     private java.lang.String wonum;
     
    /** default constructor */
    public Wosum(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �ֶ�LOCATION������
    * @return java.lang.String
    */
	public java.lang.String getLocation() {
		if(this.location==null || this.location.length()<=0)
	  		return null;
	  	else
	  		return this.location;
	}
	
	/**
    * �ֶ�LOCATION������
    * @return java.lang.String
    */
	public void setLocation(java.lang.String location) {
	   this.location = location;
	}
	
	
    /**
    * �ֶ�WODESC������
    * @return java.lang.String
    */
	public java.lang.String getWodesc() {
		if(this.wodesc==null || this.wodesc.length()<=0)
	  		return null;
	  	else
	  		return this.wodesc;
	}
	
	/**
    * �ֶ�WODESC������
    * @return java.lang.String
    */
	public void setWodesc(java.lang.String wodesc) {
	   this.wodesc = wodesc;
	}
	
	
    /**
    * �ֶ�WONUM������
    * @return java.lang.String
    */
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}
	
	/**
    * �ֶ�WONUM������
    * @return java.lang.String
    */
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
	
	
}