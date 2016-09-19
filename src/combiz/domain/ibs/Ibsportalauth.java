package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsportalauth extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String portal;
     private java.lang.String grpname;
     private java.lang.Long position;
     
    /** default constructor */
    public Ibsportalauth(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �Ż�
    * @return java.lang.String
    */
	public java.lang.String getPortal() {
		if(this.portal==null || this.portal.length()<=0)
	  		return null;
	  	else
	  		return this.portal;
	}
	
	/**
    * �Ż�
    * @return java.lang.String
    */
	public void setPortal(java.lang.String portal) {
	   this.portal = portal;
	}
	
	
    /**
    * Ȩ����
    * @return java.lang.String
    */
	public java.lang.String getGrpname() {
		if(this.grpname==null || this.grpname.length()<=0)
	  		return null;
	  	else
	  		return this.grpname;
	}
	
	/**
    * Ȩ����
    * @return java.lang.String
    */
	public void setGrpname(java.lang.String grpname) {
	   this.grpname = grpname;
	}
	
	
    /**
    * ����
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * ����
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
}