package combiz.domain.company;

import combiz.system.IBOBaseObject;

public class Companies extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String description;
     private java.lang.String company;
     private java.lang.String parent;
     private java.lang.String type;
     private java.lang.String regfund;
     private java.lang.String comptype;
     private java.lang.String delegate;
     private java.lang.String regnum;
     private java.lang.String taxnum;
     private java.lang.String mainprod;
     private java.lang.String dealin;
     private java.lang.String address;
     private java.lang.String postnum;
     private java.lang.String province;
     private java.lang.String city;
     private java.lang.String contact;
     private java.lang.String phone;
     private java.lang.String fax;
     private java.lang.String contactmp;
     private java.lang.String contactmail;
     private java.lang.String fob;
     private java.lang.String freightterms;
     private java.lang.String shipvia;
     private java.lang.String paymentterms;
     private java.lang.String ebsnum;
     private java.lang.String ebsissync;
     private java.lang.String changeby;
     private java.util.Date changedate;
     private java.lang.String payvendor;
     private java.lang.String bankname;
     private java.lang.String bankaccount;
     private java.lang.String disabled;
     private java.lang.String remitaddr;
     private java.lang.String remitpostnum;
     private java.lang.String remitphone;
     private java.lang.String remitto;
     private java.lang.String homepage;
     private java.lang.String defwarehouse;
     
    // Constructors
    /** default constructor */
    public Companies(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getCompany() {
		if(this.company==null || this.company.length()<=0)
	  		return null;
	  	else
	  		return this.company;
	}	    
	public void setCompany(java.lang.String company) {
	   this.company = company;
	}
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}	    
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	public java.lang.String getType() {
		if(this.type==null || this.type.length()<=0)
	  		return null;
	  	else
	  		return this.type;
	}	    
	public void setType(java.lang.String type) {
	   this.type = type;
	}
	public java.lang.String getRegfund() {
		if(this.regfund==null || this.regfund.length()<=0)
	  		return null;
	  	else
	  		return this.regfund;
	}	    
	public void setRegfund(java.lang.String regfund) {
	   this.regfund = regfund;
	}
	public java.lang.String getComptype() {
		if(this.comptype==null || this.comptype.length()<=0)
	  		return null;
	  	else
	  		return this.comptype;
	}	    
	public void setComptype(java.lang.String comptype) {
	   this.comptype = comptype;
	}
	public java.lang.String getDelegate() {
		if(this.delegate==null || this.delegate.length()<=0)
	  		return null;
	  	else
	  		return this.delegate;
	}	    
	public void setDelegate(java.lang.String delegate) {
	   this.delegate = delegate;
	}
	public java.lang.String getRegnum() {
		if(this.regnum==null || this.regnum.length()<=0)
	  		return null;
	  	else
	  		return this.regnum;
	}	    
	public void setRegnum(java.lang.String regnum) {
	   this.regnum = regnum;
	}
	public java.lang.String getTaxnum() {
		if(this.taxnum==null || this.taxnum.length()<=0)
	  		return null;
	  	else
	  		return this.taxnum;
	}	    
	public void setTaxnum(java.lang.String taxnum) {
	   this.taxnum = taxnum;
	}
	public java.lang.String getMainprod() {
		if(this.mainprod==null || this.mainprod.length()<=0)
	  		return null;
	  	else
	  		return this.mainprod;
	}	    
	public void setMainprod(java.lang.String mainprod) {
	   this.mainprod = mainprod;
	}
	public java.lang.String getDealin() {
		if(this.dealin==null || this.dealin.length()<=0)
	  		return null;
	  	else
	  		return this.dealin;
	}	    
	public void setDealin(java.lang.String dealin) {
	   this.dealin = dealin;
	}
	public java.lang.String getAddress() {
		if(this.address==null || this.address.length()<=0)
	  		return null;
	  	else
	  		return this.address;
	}	    
	public void setAddress(java.lang.String address) {
	   this.address = address;
	}
	public java.lang.String getPostnum() {
		if(this.postnum==null || this.postnum.length()<=0)
	  		return null;
	  	else
	  		return this.postnum;
	}	    
	public void setPostnum(java.lang.String postnum) {
	   this.postnum = postnum;
	}
	public java.lang.String getProvince() {
		if(this.province==null || this.province.length()<=0)
	  		return null;
	  	else
	  		return this.province;
	}	    
	public void setProvince(java.lang.String province) {
	   this.province = province;
	}
	public java.lang.String getCity() {
		if(this.city==null || this.city.length()<=0)
	  		return null;
	  	else
	  		return this.city;
	}	    
	public void setCity(java.lang.String city) {
	   this.city = city;
	}
	public java.lang.String getContact() {
		if(this.contact==null || this.contact.length()<=0)
	  		return null;
	  	else
	  		return this.contact;
	}	    
	public void setContact(java.lang.String contact) {
	   this.contact = contact;
	}
	public java.lang.String getPhone() {
		if(this.phone==null || this.phone.length()<=0)
	  		return null;
	  	else
	  		return this.phone;
	}	    
	public void setPhone(java.lang.String phone) {
	   this.phone = phone;
	}
	public java.lang.String getFax() {
		if(this.fax==null || this.fax.length()<=0)
	  		return null;
	  	else
	  		return this.fax;
	}	    
	public void setFax(java.lang.String fax) {
	   this.fax = fax;
	}
	public java.lang.String getContactmp() {
		if(this.contactmp==null || this.contactmp.length()<=0)
	  		return null;
	  	else
	  		return this.contactmp;
	}	    
	public void setContactmp(java.lang.String contactmp) {
	   this.contactmp = contactmp;
	}
	public java.lang.String getContactmail() {
		if(this.contactmail==null || this.contactmail.length()<=0)
	  		return null;
	  	else
	  		return this.contactmail;
	}	    
	public void setContactmail(java.lang.String contactmail) {
	   this.contactmail = contactmail;
	}
	public java.lang.String getFob() {
		if(this.fob==null || this.fob.length()<=0)
	  		return null;
	  	else
	  		return this.fob;
	}	    
	public void setFob(java.lang.String fob) {
	   this.fob = fob;
	}
	public java.lang.String getFreightterms() {
		if(this.freightterms==null || this.freightterms.length()<=0)
	  		return null;
	  	else
	  		return this.freightterms;
	}	    
	public void setFreightterms(java.lang.String freightterms) {
	   this.freightterms = freightterms;
	}
	public java.lang.String getShipvia() {
		if(this.shipvia==null || this.shipvia.length()<=0)
	  		return null;
	  	else
	  		return this.shipvia;
	}	    
	public void setShipvia(java.lang.String shipvia) {
	   this.shipvia = shipvia;
	}
	public java.lang.String getPaymentterms() {
		if(this.paymentterms==null || this.paymentterms.length()<=0)
	  		return null;
	  	else
	  		return this.paymentterms;
	}	    
	public void setPaymentterms(java.lang.String paymentterms) {
	   this.paymentterms = paymentterms;
	}
	public java.lang.String getEbsnum() {
		if(this.ebsnum==null || this.ebsnum.length()<=0)
	  		return null;
	  	else
	  		return this.ebsnum;
	}	    
	public void setEbsnum(java.lang.String ebsnum) {
	   this.ebsnum = ebsnum;
	}
	public java.lang.String getEbsissync() {
		if(this.ebsissync==null || this.ebsissync.length()<=0)
	  		return null;
	  	else
	  		return this.ebsissync;
	}	    
	public void setEbsissync(java.lang.String ebsissync) {
	   this.ebsissync = ebsissync;
	}
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
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
	public java.lang.String getPayvendor() {
		if(this.payvendor==null || this.payvendor.length()<=0)
	  		return null;
	  	else
	  		return this.payvendor;
	}	    
	public void setPayvendor(java.lang.String payvendor) {
	   this.payvendor = payvendor;
	}
	public java.lang.String getBankname() {
		if(this.bankname==null || this.bankname.length()<=0)
	  		return null;
	  	else
	  		return this.bankname;
	}	    
	public void setBankname(java.lang.String bankname) {
	   this.bankname = bankname;
	}
	public java.lang.String getBankaccount() {
		if(this.bankaccount==null || this.bankaccount.length()<=0)
	  		return null;
	  	else
	  		return this.bankaccount;
	}	    
	public void setBankaccount(java.lang.String bankaccount) {
	   this.bankaccount = bankaccount;
	}
	public java.lang.String getDisabled() {
		if(this.disabled==null || this.disabled.length()<=0)
	  		return null;
	  	else
	  		return this.disabled;
	}	    
	public void setDisabled(java.lang.String disabled) {
	   this.disabled = disabled;
	}
	public java.lang.String getRemitaddr() {
		if(this.remitaddr==null || this.remitaddr.length()<=0)
	  		return null;
	  	else
	  		return this.remitaddr;
	}	    
	public void setRemitaddr(java.lang.String remitaddr) {
	   this.remitaddr = remitaddr;
	}
	public java.lang.String getRemitpostnum() {
		if(this.remitpostnum==null || this.remitpostnum.length()<=0)
	  		return null;
	  	else
	  		return this.remitpostnum;
	}	    
	public void setRemitpostnum(java.lang.String remitpostnum) {
	   this.remitpostnum = remitpostnum;
	}
	public java.lang.String getRemitphone() {
		if(this.remitphone==null || this.remitphone.length()<=0)
	  		return null;
	  	else
	  		return this.remitphone;
	}	    
	public void setRemitphone(java.lang.String remitphone) {
	   this.remitphone = remitphone;
	}
	public java.lang.String getRemitto() {
		if(this.remitto==null || this.remitto.length()<=0)
	  		return null;
	  	else
	  		return this.remitto;
	}	    
	public void setRemitto(java.lang.String remitto) {
	   this.remitto = remitto;
	}
	public java.lang.String getHomepage() {
		if(this.homepage==null || this.homepage.length()<=0)
	  		return null;
	  	else
	  		return this.homepage;
	}	    
	public void setHomepage(java.lang.String homepage) {
	   this.homepage = homepage;
	}
	public java.lang.String getDefwarehouse() {
		if(this.defwarehouse==null || this.defwarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.defwarehouse;
	}	    
	public void setDefwarehouse(java.lang.String defwarehouse) {
	   this.defwarehouse = defwarehouse;
	}
}