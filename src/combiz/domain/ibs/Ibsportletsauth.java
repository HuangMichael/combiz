package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsportletsauth extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String grpname;
     private java.lang.String portlet;
     
    /** default constructor */
    public Ibsportletsauth(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 权限组
    * @return java.lang.String
    */
	public java.lang.String getGrpname() {
		if(this.grpname==null || this.grpname.length()<=0)
	  		return null;
	  	else
	  		return this.grpname;
	}
	
	/**
    * 权限组
    * @return java.lang.String
    */
	public void setGrpname(java.lang.String grpname) {
	   this.grpname = grpname;
	}
	
	
    /**
    * 门户窗口编号
    * @return java.lang.String
    */
	public java.lang.String getPortlet() {
		if(this.portlet==null || this.portlet.length()<=0)
	  		return null;
	  	else
	  		return this.portlet;
	}
	
	/**
    * 门户窗口编号
    * @return java.lang.String
    */
	public void setPortlet(java.lang.String portlet) {
	   this.portlet = portlet;
	}
	
	
}