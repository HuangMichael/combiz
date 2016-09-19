package combiz.domain.kpi;

import combiz.system.IBOBaseObject;

public class Kpipicture extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String bgcolor;
     private java.lang.String description;
     private java.lang.Long fgalpha;
     private java.lang.Long height;
     private java.lang.String kptnum;
     private java.lang.String kpttype;
     private java.lang.String threed;
     private java.lang.Long width;
     
    // Constructors
    /** default constructor */
    public Kpipicture(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getBgcolor() {
	   return this.bgcolor;
	}	    
	public void setBgcolor(java.lang.String bgcolor) {
	   this.bgcolor = bgcolor;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.Long getFgalpha() {
	   return this.fgalpha;
	}	    
	public void setFgalpha(java.lang.Long fgalpha) {
	   this.fgalpha = fgalpha;
	}
	public java.lang.Long getHeight() {
	   return this.height;
	}	    
	public void setHeight(java.lang.Long height) {
	   this.height = height;
	}
	public java.lang.String getKptnum() {
	   return this.kptnum;
	}	    
	public void setKptnum(java.lang.String kptnum) {
	   this.kptnum = kptnum;
	}
	public java.lang.String getKpttype() {
	   return this.kpttype;
	}	    
	public void setKpttype(java.lang.String kpttype) {
	   this.kpttype = kpttype;
	}
	public java.lang.String getThreed() {
	   return this.threed;
	}	    
	public void setThreed(java.lang.String threed) {
	   this.threed = threed;
	}
	public java.lang.Long getWidth() {
	   return this.width;
	}	    
	public void setWidth(java.lang.Long width) {
	   this.width = width;
	}
}