package combiz.domain.corp;

import combiz.system.IBOBaseObject;

public class Laborgrps extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String groupname;
     private java.lang.String labornum;
     
    // Constructors
    /** default constructor */
    public Laborgrps(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getGroupname() {
	   return this.groupname;
	}	    
	public void setGroupname(java.lang.String groupname) {
	   this.groupname = groupname;
	}
	public java.lang.String getLabornum() {
	   return this.labornum;
	}	    
	public void setLabornum(java.lang.String labornum) {
	   this.labornum = labornum;
	}
}