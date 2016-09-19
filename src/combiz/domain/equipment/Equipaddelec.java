package combiz.domain.equipment;

import combiz.system.IBOBaseObject;

public class Equipaddelec extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String problem;
     private java.lang.String requestnum;
     private java.util.Date runtime;
     private java.lang.String remark;
     
    // Constructors
    /** default constructor */
    public Equipaddelec(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getProblem() {
	   return this.problem;
	}	    
	public void setProblem(java.lang.String problem) {
	   this.problem = problem;
	}
	public java.lang.String getRequestnum() {
	   return this.requestnum;
	}	    
	public void setRequestnum(java.lang.String requestnum) {
	   this.requestnum = requestnum;
	}
	public java.util.Date getRuntime() {
	   return this.runtime;
	}	    
	public void setRuntime(java.util.Date runtime) {
	   this.runtime = runtime;
	}


	public java.lang.String getRemark() {
		return remark;
	}


	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


}