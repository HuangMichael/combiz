package combiz.domain.kpi;

import combiz.system.IBOBaseObject;

public class Kpilink extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String kpinum;
     private java.lang.String kptnum;
     private java.lang.String series;
     private java.lang.String xpoint;
     private java.lang.Double ypoint;
     
    // Constructors
    /** default constructor */
    public Kpilink(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getKpinum() {
	   return this.kpinum;
	}	    
	public void setKpinum(java.lang.String kpinum) {
	   this.kpinum = kpinum;
	}
	public java.lang.String getKptnum() {
	   return this.kptnum;
	}	    
	public void setKptnum(java.lang.String kptnum) {
	   this.kptnum = kptnum;
	}
	public java.lang.String getSeries() {
	   return this.series;
	}	    
	public void setSeries(java.lang.String series) {
	   this.series = series;
	}
	public java.lang.String getXpoint() {
	   return this.xpoint;
	}	    
	public void setXpoint(java.lang.String xpoint) {
	   this.xpoint = xpoint;
	}
	public java.lang.Double getYpoint() {
	   return this.ypoint;
	}	    
	public void setYpoint(java.lang.Double ypoint) {
	   this.ypoint = ypoint;
	}
}