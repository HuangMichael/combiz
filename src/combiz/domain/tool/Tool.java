package combiz.domain.tool;

import combiz.system.IBOBaseObject;

public class Tool extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String classid;
     private java.lang.String craft;
     private java.lang.String crewid;
     private java.lang.String description;
     private java.lang.String disabled;
     private java.lang.String glaccount;
     private java.lang.String outside;
     private java.lang.String owdept;
     private java.lang.Long qty;
     private java.lang.String toolnum;
     private java.lang.Double toolrate;
     private java.lang.String vendor;
     
    // Constructors
    /** default constructor */
    public Tool(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}	    
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	public java.lang.String getCraft() {
		if(this.craft==null || this.craft.length()<=0)
	  		return null;
	  	else
	  		return this.craft;
	}	    
	public void setCraft(java.lang.String craft) {
	   this.craft = craft;
	}
	public java.lang.String getCrewid() {
		if(this.crewid==null || this.crewid.length()<=0)
	  		return null;
	  	else
	  		return this.crewid;
	}	    
	public void setCrewid(java.lang.String crewid) {
	   this.crewid = crewid;
	}
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
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
	public java.lang.String getGlaccount() {
		if(this.glaccount==null || this.glaccount.length()<=0)
	  		return null;
	  	else
	  		return this.glaccount;
	}	    
	public void setGlaccount(java.lang.String glaccount) {
	   this.glaccount = glaccount;
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
	public java.lang.String getOwdept() {
		if(this.owdept==null || this.owdept.length()<=0)
	  		return null;
	  	else
	  		return this.owdept;
	}	    
	public void setOwdept(java.lang.String owdept) {
	   this.owdept = owdept;
	}
	public java.lang.Long getQty() {
	  		return this.qty;
	}	    
	public void setQty(java.lang.Long qty) {
	   this.qty = qty;
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
	public java.lang.Double getToolrate() {
	  		return this.toolrate;
	}	    
	public void setToolrate(java.lang.Double toolrate) {
	   this.toolrate = toolrate;
	}
	public java.lang.String getVendor() {
		if(this.vendor==null || this.vendor.length()<=0)
	  		return null;
	  	else
	  		return this.vendor;
	}	    
	public void setVendor(java.lang.String vendor) {
	   this.vendor = vendor;
	}
}