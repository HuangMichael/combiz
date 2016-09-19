package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsportal extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String description;
     private java.lang.String portal;
     private java.lang.Long portcols;
     private java.lang.Long portrows;
     private java.lang.String roworcol;
     
    /** default constructor */
    public Ibsportal(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
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
    * �Ż�����
    * @return java.lang.String
    */
	public java.lang.String getPortal() {
		if(this.portal==null || this.portal.length()<=0)
	  		return null;
	  	else
	  		return this.portal;
	}
	
	/**
    * �Ż�����
    * @return java.lang.String
    */
	public void setPortal(java.lang.String portal) {
	   this.portal = portal;
	}
	
	
    /**
    * �Ż���������
    * @return java.lang.Long
    */
	public java.lang.Long getPortcols() {
	  		return this.portcols;
	}
	
	/**
    * �Ż���������
    * @return java.lang.Long
    */
	public void setPortcols(java.lang.Long portcols) {
	   this.portcols = portcols;
	}
	
	
    /**
    * �Ż���������
    * @return java.lang.Long
    */
	public java.lang.Long getPortrows() {
	  		return this.portrows;
	}
	
	/**
    * �Ż���������
    * @return java.lang.Long
    */
	public void setPortrows(java.lang.Long portrows) {
	   this.portrows = portrows;
	}
	
	
    /**
    * ���й��������ꡢ������
    * @return java.lang.String
    */
	public java.lang.String getRoworcol() {
		if(this.roworcol==null || this.roworcol.length()<=0)
	  		return null;
	  	else
	  		return this.roworcol;
	}
	
	/**
    * ���й��������ꡢ������
    * @return java.lang.String
    */
	public void setRoworcol(java.lang.String roworcol) {
	   this.roworcol = roworcol;
	}
	
	
}