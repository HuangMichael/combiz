package combiz.domain.smsg;

import combiz.system.IBOBaseObject;

public class Msgsender extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String title;
     private java.lang.String body;
     private java.util.Date sddate;
     private java.lang.String sendtype;
     private java.lang.String sender;
     private java.lang.String rectype;
     private java.lang.String recdept;
     private java.lang.String reclaborgrp;
     private java.lang.String reclabor;
     private java.lang.String issue;
     private java.lang.String reclist;
     private java.lang.String sendmail;
     
    // Constructors
    /** default constructor */
    public Msgsender(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getTitle() {
		if(this.title==null || this.title.length()<=0)
	  		return null;
	  	else
	  		return this.title;
	}	    
	public void setTitle(java.lang.String title) {
	   this.title = title;
	}
	public java.lang.String getBody() {
		if(this.body==null || this.body.length()<=0)
	  		return null;
	  	else
	  		return this.body;
	}	    
	public void setBody(java.lang.String body) {
	   this.body = body;
	}
	public java.util.Date getSddate() {
	  		return this.sddate;
	}	    
	public void setSddate(java.util.Date sddate) {
	   this.sddate = sddate;
	}
	public java.lang.String getSendtype() {
		if(this.sendtype==null || this.sendtype.length()<=0)
	  		return null;
	  	else
	  		return this.sendtype;
	}	    
	public void setSendtype(java.lang.String sendtype) {
	   this.sendtype = sendtype;
	}
	public java.lang.String getSender() {
		if(this.sender==null || this.sender.length()<=0)
	  		return null;
	  	else
	  		return this.sender;
	}	    
	public void setSender(java.lang.String sender) {
	   this.sender = sender;
	}
	public java.lang.String getRectype() {
		if(this.rectype==null || this.rectype.length()<=0)
	  		return null;
	  	else
	  		return this.rectype;
	}	    
	public void setRectype(java.lang.String rectype) {
	   this.rectype = rectype;
	}
	public java.lang.String getRecdept() {
		if(this.recdept==null || this.recdept.length()<=0)
	  		return null;
	  	else
	  		return this.recdept;
	}	    
	public void setRecdept(java.lang.String recdept) {
	   this.recdept = recdept;
	}
	public java.lang.String getReclaborgrp() {
		if(this.reclaborgrp==null || this.reclaborgrp.length()<=0)
	  		return null;
	  	else
	  		return this.reclaborgrp;
	}	    
	public void setReclaborgrp(java.lang.String reclaborgrp) {
	   this.reclaborgrp = reclaborgrp;
	}
	public java.lang.String getReclabor() {
		if(this.reclabor==null || this.reclabor.length()<=0)
	  		return null;
	  	else
	  		return this.reclabor;
	}	    
	public void setReclabor(java.lang.String reclabor) {
	   this.reclabor = reclabor;
	}
	public java.lang.String getIssue() {
		if(this.issue==null || this.issue.length()<=0)
	  		return null;
	  	else
	  		return this.issue;
	}	    
	public void setIssue(java.lang.String issue) {
	   this.issue = issue;
	}
	public java.lang.String getReclist() {
		if(this.reclist==null || this.reclist.length()<=0)
	  		return null;
	  	else
	  		return this.reclist;
	}	    
	public void setReclist(java.lang.String reclist) {
	   this.reclist = reclist;
	}
	public java.lang.String getSendmail() {
		if(this.sendmail==null || this.sendmail.length()<=0)
	  		return null;
	  	else
	  		return this.sendmail;
	}	    
	public void setSendmail(java.lang.String sendmail) {
	   this.sendmail = sendmail;
	}
}