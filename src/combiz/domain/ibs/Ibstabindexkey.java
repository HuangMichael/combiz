package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstabindexkey extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String changed;
     private java.lang.String colname;
     private java.lang.Long colseq;
     private java.lang.String idxname;
     private java.lang.String orderby;
     private java.lang.String tablename;
     
    // Constructors
    /** default constructor */
    public Ibstabindexkey(){}
    
   
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
	public java.lang.String getColname() {
	   return this.colname;
	}	    
	public void setColname(java.lang.String colname) {
	   this.colname = colname;
	}
	public java.lang.Long getColseq() {
	   return this.colseq;
	}	    
	public void setColseq(java.lang.Long colseq) {
	   this.colseq = colseq;
	}
	public java.lang.String getIdxname() {
	   return this.idxname;
	}	    
	public void setIdxname(java.lang.String idxname) {
	   this.idxname = idxname;
	}
	public java.lang.String getOrderby() {
	   return this.orderby;
	}	    
	public void setOrderby(java.lang.String orderby) {
	   this.orderby = orderby;
	}
	public java.lang.String getTablename() {
	   return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
}