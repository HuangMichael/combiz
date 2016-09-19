package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstabindex extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String changed;
     private java.lang.String description;
     private java.lang.String idxname;
     private java.lang.String isunique;
     private java.lang.String storage;
     private java.lang.String tablename;
     
    // Constructors
    /** default constructor */
    public Ibstabindex(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getChanged() {
	   return this.changed;
	}	    
	public void setChanged(java.lang.String changed) {
	   this.changed = changed;
	}
	public java.lang.String getDescription() {
	   return this.description;
	}	    
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	public java.lang.String getIdxname() {
	   return this.idxname;
	}	    
	public void setIdxname(java.lang.String idxname) {
	   this.idxname = idxname;
	}
	public java.lang.String getIsunique() {
	   return this.isunique;
	}	    
	public void setIsunique(java.lang.String isunique) {
	   this.isunique = isunique;
	}
	public java.lang.String getStorage() {
	   return this.storage;
	}	    
	public void setStorage(java.lang.String storage) {
	   this.storage = storage;
	}
	public java.lang.String getTablename() {
	   return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
}