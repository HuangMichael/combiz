package combiz.domain.tool;

import combiz.system.IBOBaseObject;

public class Tooltrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String enterby;
     private java.util.Date enterdate;
     private java.lang.Double linecost;
     private java.lang.String outside;
     private java.lang.String reqlabor;
     private java.lang.Long sendcount;
     private java.lang.Long sendnum;
     private java.lang.Double toolhrs;
     private java.lang.String toolnum;
     private java.lang.Long toolqty;
     private java.lang.Double toolrate;
     private java.util.Date transdate;
     private java.lang.String transid;
     private java.lang.String transtype;
     private java.lang.String userdept;
     private java.lang.Long waitqty;
     private java.lang.String wonum;
     
    // Constructors
    /** default constructor */
    public Tooltrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getEnterby() {
		if(this.enterby==null || this.enterby.length()<=0)
	  		return null;
	  	else
	  		return this.enterby;
	}	    
	public void setEnterby(java.lang.String enterby) {
	   this.enterby = enterby;
	}
	public java.util.Date getEnterdate() {
	  		return this.enterdate;
	}	    
	public void setEnterdate(java.util.Date enterdate) {
	   this.enterdate = enterdate;
	}
	public java.lang.Double getLinecost() {
	  		return this.linecost;
	}	    
	public void setLinecost(java.lang.Double linecost) {
	   this.linecost = linecost;
	}
	public java.lang.String getOutside() {
		if(this.outside==null || this.outside.length()<=0)
	  		return null;
	  	else
	  		return this.outside;
	}	    
	public void setOutside(java.lang.String outside) {
	   this.outside = outside;
	}
	public java.lang.String getReqlabor() {
		if(this.reqlabor==null || this.reqlabor.length()<=0)
	  		return null;
	  	else
	  		return this.reqlabor;
	}	    
	public void setReqlabor(java.lang.String reqlabor) {
	   this.reqlabor = reqlabor;
	}
	public java.lang.Long getSendcount() {
	  		return this.sendcount;
	}	    
	public void setSendcount(java.lang.Long sendcount) {
	   this.sendcount = sendcount;
	}
	public java.lang.Long getSendnum() {
	  		return this.sendnum;
	}	    
	public void setSendnum(java.lang.Long sendnum) {
	   this.sendnum = sendnum;
	}
	public java.lang.Double getToolhrs() {
	  		return this.toolhrs;
	}	    
	public void setToolhrs(java.lang.Double toolhrs) {
	   this.toolhrs = toolhrs;
	}
	public java.lang.String getToolnum() {
		if(this.toolnum==null || this.toolnum.length()<=0)
	  		return null;
	  	else
	  		return this.toolnum;
	}	    
	public void setToolnum(java.lang.String toolnum) {
	   this.toolnum = toolnum;
	}
	public java.lang.Long getToolqty() {
	  		return this.toolqty;
	}	    
	public void setToolqty(java.lang.Long toolqty) {
	   this.toolqty = toolqty;
	}
	public java.lang.Double getToolrate() {
	  		return this.toolrate;
	}	    
	public void setToolrate(java.lang.Double toolrate) {
	   this.toolrate = toolrate;
	}
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}	    
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	public java.lang.String getTransid() {
		if(this.transid==null || this.transid.length()<=0)
	  		return null;
	  	else
	  		return this.transid;
	}	    
	public void setTransid(java.lang.String transid) {
	   this.transid = transid;
	}
	public java.lang.String getTranstype() {
		if(this.transtype==null || this.transtype.length()<=0)
	  		return null;
	  	else
	  		return this.transtype;
	}	    
	public void setTranstype(java.lang.String transtype) {
	   this.transtype = transtype;
	}
	public java.lang.String getUserdept() {
		if(this.userdept==null || this.userdept.length()<=0)
	  		return null;
	  	else
	  		return this.userdept;
	}	    
	public void setUserdept(java.lang.String userdept) {
	   this.userdept = userdept;
	}
	public java.lang.Long getWaitqty() {
	  		return this.waitqty;
	}	    
	public void setWaitqty(java.lang.Long waitqty) {
	   this.waitqty = waitqty;
	}
	public java.lang.String getWonum() {
		if(this.wonum==null || this.wonum.length()<=0)
	  		return null;
	  	else
	  		return this.wonum;
	}	    
	public void setWonum(java.lang.String wonum) {
	   this.wonum = wonum;
	}
}