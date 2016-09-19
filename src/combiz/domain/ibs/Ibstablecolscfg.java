package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibstablecolscfg extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
    // Fields
     private java.lang.String changed;
     private java.lang.String classname;
     private java.lang.String colname;
     private java.lang.Long colno;
     private java.lang.String dbtype;
     private java.lang.String hbtype;
     private java.lang.String ibsdatatype;
     private java.lang.String inskey;
     private java.lang.String ispositive;
     private java.lang.String isvirtual;
     private java.lang.Long length;
     private java.lang.String listname;
     private java.lang.String notnull;
     private java.lang.Long primarykeyseq;
     private java.lang.String remarks;
     private java.lang.String sameascolumn;
     private java.lang.String sameastable;
     private java.lang.Long scale;
     private java.lang.String searchable;
     private java.lang.String tablename;
     private java.lang.String title;
     private java.lang.String userdefined;
     
    // Constructors
    /** default constructor */
    public Ibstablecolscfg(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    // Property accessors
	public java.lang.String getChanged() {
		if(this.changed==null || this.changed.length()<=0)
	  		return null;
	  	else
	  		return this.changed;
	}	    
	public void setChanged(java.lang.String changed) {
	   this.changed = changed;
	}
	public java.lang.String getClassname() {
		if(this.classname==null || this.classname.length()<=0)
	  		return null;
	  	else
	  		return this.classname;
	}	    
	public void setClassname(java.lang.String classname) {
	   this.classname = classname;
	}
	public java.lang.String getColname() {
		if(this.colname==null || this.colname.length()<=0)
	  		return null;
	  	else
	  		return this.colname;
	}	    
	public void setColname(java.lang.String colname) {
	   this.colname = colname;
	}
	public java.lang.Long getColno() {
	  		return this.colno;
	}	    
	public void setColno(java.lang.Long colno) {
	   this.colno = colno;
	}
	public java.lang.String getDbtype() {
		if(this.dbtype==null || this.dbtype.length()<=0)
	  		return null;
	  	else
	  		return this.dbtype;
	}	    
	public void setDbtype(java.lang.String dbtype) {
	   this.dbtype = dbtype;
	}
	public java.lang.String getHbtype() {
		if(this.hbtype==null || this.hbtype.length()<=0)
	  		return null;
	  	else
	  		return this.hbtype;
	}	    
	public void setHbtype(java.lang.String hbtype) {
	   this.hbtype = hbtype;
	}
	public java.lang.String getIbsdatatype() {
		if(this.ibsdatatype==null || this.ibsdatatype.length()<=0)
	  		return null;
	  	else
	  		return this.ibsdatatype;
	}	    
	public void setIbsdatatype(java.lang.String ibsdatatype) {
	   this.ibsdatatype = ibsdatatype;
	}
	public java.lang.String getInskey() {
		if(this.inskey==null || this.inskey.length()<=0)
	  		return null;
	  	else
	  		return this.inskey;
	}	    
	public void setInskey(java.lang.String inskey) {
	   this.inskey = inskey;
	}
	public java.lang.String getIspositive() {
		if(this.ispositive==null || this.ispositive.length()<=0)
	  		return null;
	  	else
	  		return this.ispositive;
	}	    
	public void setIspositive(java.lang.String ispositive) {
	   this.ispositive = ispositive;
	}
	public java.lang.String getIsvirtual() {
		if(this.isvirtual==null || this.isvirtual.length()<=0)
	  		return null;
	  	else
	  		return this.isvirtual;
	}	    
	public void setIsvirtual(java.lang.String isvirtual) {
	   this.isvirtual = isvirtual;
	}
	public java.lang.Long getLength() {
	  		return this.length;
	}	    
	public void setLength(java.lang.Long length) {
	   this.length = length;
	}
	public java.lang.String getListname() {
		if(this.listname==null || this.listname.length()<=0)
	  		return null;
	  	else
	  		return this.listname;
	}	    
	public void setListname(java.lang.String listname) {
	   this.listname = listname;
	}
	public java.lang.String getNotnull() {
		if(this.notnull==null || this.notnull.length()<=0)
	  		return null;
	  	else
	  		return this.notnull;
	}	    
	public void setNotnull(java.lang.String notnull) {
	   this.notnull = notnull;
	}
	public java.lang.Long getPrimarykeyseq() {
	  		return this.primarykeyseq;
	}	    
	public void setPrimarykeyseq(java.lang.Long primarykeyseq) {
	   this.primarykeyseq = primarykeyseq;
	}
	public java.lang.String getRemarks() {
		if(this.remarks==null || this.remarks.length()<=0)
	  		return null;
	  	else
	  		return this.remarks;
	}	    
	public void setRemarks(java.lang.String remarks) {
	   this.remarks = remarks;
	}
	public java.lang.String getSameascolumn() {
		if(this.sameascolumn==null || this.sameascolumn.length()<=0)
	  		return null;
	  	else
	  		return this.sameascolumn;
	}	    
	public void setSameascolumn(java.lang.String sameascolumn) {
	   this.sameascolumn = sameascolumn;
	}
	public java.lang.String getSameastable() {
		if(this.sameastable==null || this.sameastable.length()<=0)
	  		return null;
	  	else
	  		return this.sameastable;
	}	    
	public void setSameastable(java.lang.String sameastable) {
	   this.sameastable = sameastable;
	}
	public java.lang.Long getScale() {
	  		return this.scale;
	}	    
	public void setScale(java.lang.Long scale) {
	   this.scale = scale;
	}
	public java.lang.String getSearchable() {
		if(this.searchable==null || this.searchable.length()<=0)
	  		return null;
	  	else
	  		return this.searchable;
	}	    
	public void setSearchable(java.lang.String searchable) {
	   this.searchable = searchable;
	}
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}	    
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	public java.lang.String getTitle() {
		if(this.title==null || this.title.length()<=0)
	  		return null;
	  	else
	  		return this.title;
	}	    
	public void setTitle(java.lang.String title) {
	   this.title = title;
	}
	public java.lang.String getUserdefined() {
		if(this.userdefined==null || this.userdefined.length()<=0)
	  		return null;
	  	else
	  		return this.userdefined;
	}	    
	public void setUserdefined(java.lang.String userdefined) {
	   this.userdefined = userdefined;
	}
}