package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Deptance extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String deptnum;
     private java.lang.String deptance;
     
    // Constructors
    /** default constructor */
    public Deptance(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getDeptnum() {
	   return this.deptnum;
	}	    
	public void setDeptnum(java.lang.String deptnum) {
	   this.deptnum = deptnum;
	}
	public java.lang.String getDeptance() {
	   return this.deptance;
	}	    
	public void setDeptance(java.lang.String deptance) {
	   this.deptance = deptance;
	}
}