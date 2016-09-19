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
    * �ĵ����
    * @return java.lang.String
    */
	public java.lang.String getDocnum() {
		if(this.docnum==null || this.docnum.length()<=0)
	  		return null;
	  	else
	  		return this.docnum;
	}
	
	/**
    * �ĵ����
    * @return java.lang.String
    */
	public void setDocnum(java.lang.String docnum) {
	   this.docnum = docnum;
	}
	
	
    /**
    * �汾��
    * @return java.lang.String
    */
	public java.lang.String getVersion() {
		if(this.version==null || this.version.length()<=0)
	  		return null;
	  	else
	  		return this.version;
	}
	
	/**
    * �汾��
    * @return java.lang.String
    */
	public void setVersion(java.lang.String version) {
	   this.version = version;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getDescription() {
		if(this.description==null || this.description.length()<=0)
	  		return null;
	  	else
	  		return this.description;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setDescription(java.lang.String description) {
	   this.description = description;
	}
	
	
    /**
    * �ĵ�·������
    * @return java.lang.String
    */
	public java.lang.String getUrltype() {
		if(this.urltype==null || this.urltype.length()<=0)
	  		return null;
	  	else
	  		return this.urltype;
	}
	
	/**
    * �ĵ�·������
    * @return java.lang.String
    */
	public void setUrltype(java.lang.String urltype) {
	   this.urltype = urltype;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getCreator() {
		if(this.creator==null || this.creator.length()<=0)
	  		return null;
	  	else
	  		return this.creator;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setCreator(java.lang.String creator) {
	   this.creator = creator;
	}
	
	
    /**
    * ����ʱ��
    * @return java.util.Date
    */
	public java.util.Date getCreatedate() {
	  		return this.createdate;
	}
	
	/**
    * ����ʱ��
    * @return java.util.Date
    */
	public void setCreatedate(java.util.Date createdate) {
	   this.createdate = createdate;
	}
	
	
    /**
    * 	�ĵ�·��
    * @return java.lang.String
    */
	public java.lang.String getUrlpath() {
		if(this.urlpath==null || this.urlpath.length()<=0)
	  		return null;
	  	else
	  		return this.urlpath;
	}
	
	/**
    * 	�ĵ�·��
    * @return java.lang.String
    */
	public void setUrlpath(java.lang.String urlpath) {
	   this.urlpath = urlpath;
	}
	
	
    /**
    * ״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getOwnertable() {
		if(this.ownertable==null || this.ownertable.length()<=0)
	  		return null;
	  	else
	  		return this.ownertable;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setOwnertable(java.lang.String ownertable) {
	   this.ownertable = ownertable;
	}
	
	
    /**
    * �������¼ID
    * @return java.lang.Long
    */
	public java.lang.Long getOwnerid() {
	  		return this.ownerid;
	}
	
	/**
    * �������¼ID
    * @return java.lang.Long
    */
	public void setOwnerid(java.lang.Long ownerid) {
	   this.ownerid = ownerid;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getMemo() {
		if(this.memo==null || this.memo.length()<=0)
	  		return null;
	  	else
	  		return this.memo;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setMemo(java.lang.String memo) {
	   this.memo = memo;
	}
	
	
    /**
    * �޶�����
    * @return java.util.Date
    */
	public java.util.Date getRevdate() {
	  		return this.revdate;
	}
	
	/**
    * �޶�����
    * @return java.util.Date
    */
	public void setRevdate(java.util.Date revdate) {
	   this.revdate = revdate;
	}
	
	
    /**
    * �����ؼ���
    * @return java.lang.String
    */
	public java.lang.String getSearchkey() {
		if(this.searchkey==null || this.searchkey.length()<=0)
	  		return null;
	  	else
	  		return this.searchkey;
	}
	
	/**
    * �����ؼ���
    * @return java.lang.String
    */
	public void setSearchkey(java.lang.String searchkey) {
	   this.searchkey = searchkey;
	}
	
	
    /**
    * ����Ŀ¼���
    * @return java.lang.String
    */
	public java.lang.String getLibnum() {
		if(this.libnum==null || this.libnum.length()<=0)
	  		return null;
	  	else
	  		return this.libnum;
	}
	
	/**
    * ����Ŀ¼���
    * @return java.lang.String
    */
	public void setLibnum(java.lang.String libnum) {
	   this.libnum = libnum;
	}
	
	
    /**
    * �ļ���
    * @return java.lang.String
    */
	public java.lang.String getFilename() {
		if(this.filename==null || this.filename.length()<=0)
	  		return null;
	  	else
	  		return this.filename;
	}
	
	/**
    * �ļ���
    * @return java.lang.String
    */
	public void setFilename(java.lang.String filename) {
	   this.filename = filename;
	}
	
	
    /**
    * �ĵ�ý������
    * @return java.lang.String
    */
	public java.lang.String getContenttype() {
		if(this.contenttype==null || this.contenttype.length()<=0)
	  		return null;
	  	else
	  		return this.contenttype;
	}
	
	/**
    * �ĵ�ý������
    * @return java.lang.String
    */
	public void setContenttype(java.lang.String contenttype) {
	   this.contenttype = contenttype;
	}
	
	
}