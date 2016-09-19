package combiz.domain.workflow;

import combiz.system.IBOBaseObject;

public class Wftrans extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.Long actionid;
     private java.lang.String assigncode;
     private java.lang.Long assignid;
     private java.lang.String memo;
     private java.lang.Long nodeid;
     private java.lang.String nodetype;
     private java.lang.Long ownerid;
     private java.lang.String ownertable;
     private java.lang.Long priority;
     private java.util.Date transdate;
     private java.lang.String transtype;
     private java.lang.Long wfinstid;
     private java.lang.String wfname;
     private java.lang.Long wfrevision;
     
    // Constructors
    /** default constructor */
    public Wftrans(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.Long getActionid() {
	  		return this.actionid;
	}	    
	public void setActionid(java.lang.Long actionid) {
	   this.actionid = actionid;
	}
	public java.lang.String getAssigncode() {
		if(this.assigncode==null || this.assigncode.length()<=0)
	  		return null;
	  	else
	  		return this.assigncode;
	}	    
	public void setAssigncode(java.lang.String assigncode) {
	   this.assigncode = assigncode;
	}
	public java.lang.Long getAssignid() {
	  		return this.assignid;
	}	    
	public void setAssignid(java.lang.Long assignid) {
	   this.assignid = assignid;
	}
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}	    
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	public java.lang.Long getNodeid() {
	  		return this.nodeid;
	}	    
	public void setNodeid(java.lang.Long nodeid) {
	   this.nodeid = nodeid;
	}
	public java.lang.String getNodetype() {
		if(this.nodetype==null || this.nodetype.length()<=0)
	  		return null;
	  	else
	  		return this.nodetype;
	}	    
	public void setNodetype(java.lang.String nodetype) {
	   this.nodetype = nodetype;
	}
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}	    
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}	    
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	public java.lang.Long getPriority() {
	  		return this.priority;
	}	    
	public void setPriority(java.lang.Long priority) {
	   this.priority = priority;
	}
	public java.util.Date getTransdate() {
	  		return this.transdate;
	}	    
	public void setTransdate(java.util.Date transdate) {
	   this.transdate = transdate;
	}
	public java.lang.String getTranstype() {
		if(this.transtype==null || this.transtype.length()<=0)
	  		return null;
	  	else
	  		return this.transtype;
	}	    
	public void setTranstype(java.lang.String transtype) {
	   this.transtype = transtype;
	}
	public java.lang.Long getWfinstid() {
	  		return this.wfinstid;
	}	    
	public void setWfinstid(java.lang.Long wfinstid) {
	   this.wfinstid = wfinstid;
	}
	public java.lang.String getWfname() {
		if(this.wfname==null || this.wfname.length()<=0)
	  		return null;
	  	else
	  		return this.wfname;
	}	    
	public void setWfname(java.lang.String wfname) {
	   this.wfname = wfname;
	}
	public java.lang.Long getWfrevision() {
	  		return this.wfrevision;
	}	    
	public void setWfrevision(java.lang.Long wfrevision) {
	   this.wfrevision = wfrevision;
	}
}