package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Corpaddress extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String address;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String city;
     private java.lang.String corpnum;
     private java.lang.String country;
     private java.lang.String description;
     private java.lang.String postnum;
     private java.lang.String province;
     
    // Constructors
    /** default constructor */
    public Corpaddress(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getAddress() {
	   return this.address;
	}	    
	public void setAddress(java.lang.String address) {
	   this.address = address;
	}
	public java.lang.String getChangeby() {
	   return this.changeby;
	}	    
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	public java.util.Date getChangedate() {
	   return this.changedate;
	}	    
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	public java.lang.String getCity() {
	   return this.city;
	}	    
	public void setCity(java.lang.String city) {
	   this.city = city;
	}
	public java.lang.String getCorpnum() {
	   return this.corpnum;
	}	    
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	public java.lang.String getCountry() {
	   return this.country;
	}	    
	public void setCountry(java.lang.String country) {
	   this.country = country;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getPostnum() {
	   return this.postnum;
	}	    
	public void setPostnum(java.lang.String postnum) {
	   this.postnum = postnum;
	}
	public java.lang.String getProvince() {
	   return this.province;
	}	    
	public void setProvince(java.lang.String province) {
	   this.province = province;
	}
}