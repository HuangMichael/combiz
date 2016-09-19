package combiz.domain.assetscard;

import combiz.system.IBOBaseObject;

public class Carryover extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String year;
     private java.lang.String mon;
     private java.lang.String status;
     private java.lang.Long sourceid;
     
    /** default constructor */
    public Carryover(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * Դ��¼id
    * @return java.lang.Long
    */
	public java.lang.Long getSourceid() {
	  		return this.sourceid;
	}
	
	/**
    * Դ��¼id
    * @return java.lang.Long
    */
	public void setSourceid(java.lang.Long sourceid) {
	   this.sourceid = sourceid;
	}
	
	
}