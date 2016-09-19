package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Corporation extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String corpnum;
     private java.lang.String description;
     private java.lang.String parent;
     private java.lang.String currency;
     private java.lang.String shiptolabor;
     private java.lang.String shiptoaddress;
     private java.lang.String billtolabor;
     private java.lang.String billtoaddress;
     private java.lang.String contact;
     
    // Constructors
    /** default constructor */
    public Corporation(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getCorpnum() {
	   return this.corpnum;
	}	    
	public void setCorpnum(java.lang.String corpnum) {
	   this.corpnum = corpnum;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getParent() {
	   return this.parent;
	}	    
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	public java.lang.String getCurrency() {
	   return this.currency;
	}	    
	public void setCurrency(java.lang.String currency) {
	   this.currency = currency;
	}
	public java.lang.String getShiptolabor() {
	   return this.shiptolabor;
	}	    
	public void setShiptolabor(java.lang.String shiptolabor) {
	   this.shiptolabor = shiptolabor;
	}
	public java.lang.String getShiptoaddress() {
	   return this.shiptoaddress;
	}	    
	public void setShiptoaddress(java.lang.String shiptoaddress) {
	   this.shiptoaddress = shiptoaddress;
	}
	public java.lang.String getBilltolabor() {
	   return this.billtolabor;
	}	    
	public void setBilltolabor(java.lang.String billtolabor) {
	   this.billtolabor = billtolabor;
	}
	public java.lang.String getBilltoaddress() {
	   return this.billtoaddress;
	}	    
	public void setBilltoaddress(java.lang.String billtoaddress) {
	   this.billtoaddress = billtoaddress;
	}
	public java.lang.String getContact() {
	   return this.contact;
	}	    
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
}