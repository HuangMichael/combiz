package combiz.domain.invoice;

import combiz.system.IBOBaseObject;

public class Invoicematch extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.Double conversion;
     private java.lang.Long invoicelinenum;
     private java.lang.String invoicenum;
     private java.lang.Long invrectransid;
     private java.lang.Double linecost;
     private java.lang.Long polinenum;
     private java.lang.String ponum;
     private java.lang.Double quantity;
     private java.lang.Long servrectransid;
     private java.util.Date transdate;
     private java.lang.String transtype;
     private java.lang.String vendor;
     
    // Constructors
    /** default constructor */
    public Invoicematch(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.Double getConversion() {
	   return this.conversion;
	}	    
	public void setConversion(java.lang.Double conversion) {
	   this.conversion = conversion;
	}
	public java.lang.Long getInvoicelinenum() {
	   return this.invoicelinenum;
	}	    
	public void setInvoicelinenum(java.lang.Long invoicelinenum) {
	   this.invoicelinenum = invoicelinenum;
	}
	public java.lang.String getInvoicenum() {
	   return this.invoicenum;
	}	    
	public void setInvoicenum(java.lang.String invoicenum) {
	   this.invoicenum = invoicenum;
	}
	public java.lang.Long getInvrectransid() {
	   return this.invrectransid;
	}	    
	public void setInvrectransid(java.lang.Long invrectransid) {
	   this.invrectransid = invrectransid;
	}
	public java.lang.Double getLinecost() {
	   return this.linecost;
	}	    
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	public java.lang.Long getPolinenum() {
	   return this.polinenum;
	}	    
	public void setPolinenum(java.lang.Long polinenum) {
	   this.polinenum = polinenum;
	}
	public java.lang.String getPonum() {
	   return this.ponum;
	}	    
	public void setPonum(java.lang.String ponum) {
	   this.ponum = ponum;
	}
	public java.lang.Double getQuantity() {
	   return this.quantity;
	}	    
	public void setQuantity(java.lang.Double quantity) {
	   this.quantity = quantity;
	}
	public java.lang.Long getServrectransid() {
	   return this.servrectransid;
	}	    
	public void setServrectransid(java.lang.Long servrectransid) {
	   this.servrectransid = servrectransid;
	}
	public java.util.Date getTransdate() {
	   return this.transdate;
	}	    
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	public java.lang.String getTranstype() {
	   return this.transtype;
	}	    
	public void setTranstype(java.lang.String transtype) {
	   this.transtype = transtype;
	}
	public java.lang.String getVendor() {
	   return this.vendor;
	}	    
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
}