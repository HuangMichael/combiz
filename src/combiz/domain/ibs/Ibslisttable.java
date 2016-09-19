package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibslisttable extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String errorbundle;
     private java.lang.String errorkey;
     private java.lang.String listname;
     private java.lang.String listwhere;
     private java.lang.String tablename;
     private java.lang.String targetfield;
     private java.lang.String validwhere;
     
    // Constructors
    /** default constructor */
    public Ibslisttable(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getErrorbundle() {
	   return this.errorbundle;
	}	    
	public void setErrorbundle(java.lang.String errorbundle) {
	   this.errorbundle = errorbundle;
	}
	public java.lang.String getErrorkey() {
	   return this.errorkey;
	}	    
	public void setErrorkey(java.lang.String errorkey) {
	   this.errorkey = errorkey;
	}
	public java.lang.String getListname() {
	   return this.listname;
	}	    
	public void setListname(java.lang.String listname) {
	   this.listname = listname;
	}
	public java.lang.String getListwhere() {
	   return this.listwhere;
	}	    
	public void setListwhere(java.lang.String listwhere) {
	   this.listwhere = listwhere;
	}
	public java.lang.String getTablename() {
	   return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	public java.lang.String getTargetfield() {
	   return this.targetfield;
	}	    
	public void setTargetfield(java.lang.String targetfield) {
	   this.targetfield = targetfield;
	}
	public java.lang.String getValidwhere() {
	   return this.validwhere;
	}	    
	public void setValidwhere(java.lang.String validwhere) {
	   this.validwhere = validwhere;
	}
}