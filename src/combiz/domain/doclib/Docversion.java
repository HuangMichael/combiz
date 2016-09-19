package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Docversion extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String docnum;
     private java.lang.String version;
     private java.lang.String description;
     private java.lang.String urltype;
     private java.lang.String creator;
     private java.util.Date createdate;
     private java.lang.String urlpath;
     private java.lang.String status;
     private java.lang.String ownertable;
     private java.lang.Long ownerid;
     private java.lang.String memo;
     private java.util.Date revdate;
     private java.lang.String searchkey;
     private java.lang.String libnum;
     private java.lang.String filename;
     private java.lang.String contenttype;
     
    /** default constructor */
    public Docversion(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * 文档编号
    * @return java.lang.String
    */
	public java.lang.String getDocnum() {
		if(this.docnum==null || this.docnum.length()<=0)
	  		return null;
	  	else
	  		return this.docnum;
	}
	
	/**
    * 文档编号
    * @return java.lang.String
    */
	public void setDocnum(java.lang.String docnum) {
	   this.docnum = docnum;
	}
	
	
    /**
    * 版本号
    * @return java.lang.String
    */
	public java.lang.String getVersion() {
		if(this.version==null || this.version.length()<=0)
	  		return null;
	  	else
	  		return this.version;
	}
	
	/**
    * 版本号
    * @return java.lang.String
    */
	public void setVersion(java.lang.String version) {
	   this.version = version;
	}
	
	
    /**
    * 描述
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * 描述
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * 文档路径类型
    * @return java.lang.String
    */
	public java.lang.String getUrltype() {
		if(this.urltype==null || this.urltype.length()<=0)
	  		return null;
	  	else
	  		return this.urltype;
	}
	
	/**
    * 文档路径类型
    * @return java.lang.String
    */
	public void setUrltype(java.lang.String urltype) {
	   this.urltype = urltype;
	}
	
	
    /**
    * 创建人
    * @return java.lang.String
    */
	public java.lang.String getCreator() {
		if(this.creator==null || this.creator.length()<=0)
	  		return null;
	  	else
	  		return this.creator;
	}
	
	/**
    * 创建人
    * @return java.lang.String
    */
	public void setCreator(java.lang.String creator) {
	   this.creator = creator;
	}
	
	
    /**
    * 创建时间
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * 创建时间
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * 	文档路径
    * @return java.lang.String
    */
	public java.lang.String getUrlpath() {
		if(this.urlpath==null || this.urlpath.length()<=0)
	  		return null;
	  	else
	  		return this.urlpath;
	}
	
	/**
    * 	文档路径
    * @return java.lang.String
    */
	public void setUrlpath(java.lang.String urlpath) {
	   this.urlpath = urlpath;
	}
	
	
    /**
    * 状态
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * 状态
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * 关联表
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * 关联表
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * 关联表记录ID
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * 关联表记录ID
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * 备注
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * 备注
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * 修订日期
    * @return java.util.Date
    */
	public java.util.Date getRevdate() {
	  		return this.revdate;
	}
	
	/**
    * 修订日期
    * @return java.util.Date
    */
	public void setRevdate(java.util.Date revdate) {
	   this.revdate = revdate;
	}
	
	
    /**
    * 搜索关键词
    * @return java.lang.String
    */
	public java.lang.String getSearchkey() {
		if(this.searchkey==null || this.searchkey.length()<=0)
	  		return null;
	  	else
	  		return this.searchkey;
	}
	
	/**
    * 搜索关键词
    * @return java.lang.String
    */
	public void setSearchkey(java.lang.String searchkey) {
	   this.searchkey = searchkey;
	}
	
	
    /**
    * 所属目录编号
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * 所属目录编号
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * 文件名
    * @return java.lang.String
    */
	public java.lang.String getFilename() {
		if(this.filename==null || this.filename.length()<=0)
	  		return null;
	  	else
	  		return this.filename;
	}
	
	/**
    * 文件名
    * @return java.lang.String
    */
	public void setFilename(java.lang.String filename) {
	   this.filename = filename;
	}
	
	
    /**
    * 文档媒体类型
    * @return java.lang.String
    */
	public java.lang.String getContenttype() {
		if(this.contenttype==null || this.contenttype.length()<=0)
	  		return null;
	  	else
	  		return this.contenttype;
	}
	
	/**
    * 文档媒体类型
    * @return java.lang.String
    */
	public void setContenttype(java.lang.String contenttype) {
	   this.contenttype = contenttype;
	}
	
	
}