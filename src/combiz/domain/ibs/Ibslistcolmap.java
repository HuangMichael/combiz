package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibslistcolmap extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String listname;
     private java.lang.String lookupcol;
     private java.lang.String nullfill;
     private java.lang.String tablename;
     private java.lang.String targetcol;
     
    // Constructors
    /** default constructor */
    public Ibslistcolmap(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getListname() {
	   return this.listname;
	}	    
	public void setListname(java.lang.String listname) {
	   this.listname = listname;
	}
	public java.lang.String getLookupcol() {
	   return this.lookupcol;
	}	    
	public void setLookupcol(java.lang.String lookupcol) {
	   this.lookupcol = lookupcol;
	}
	public java.lang.String getNullfill() {
	   return this.nullfill;
	}	    
	public void setNullfill(java.lang.String nullfill) {
	   this.nullfill = nullfill;
	}
	public java.lang.String getTablename() {
	   return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	public java.lang.String getTargetcol() {
	   return this.targetcol;
	}	    
	public void setTargetcol(java.lang.String targetcol) {
	   this.targetcol = targetcol;
	}
}