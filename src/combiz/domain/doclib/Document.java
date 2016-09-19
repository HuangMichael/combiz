package combiz.domain.doclib;

import combiz.system.IBOBaseObject;

public class Document extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String docnum;
     private java.lang.String description;
     private java.lang.String doctype;
     private java.lang.String docextend;
     private java.lang.String creator;
     private java.util.Date createdate;
     private java.lang.String urltype;
     private java.lang.String urlpath;
     private java.lang.String ownertable;
     private java.lang.Long ownerid;
     private java.lang.String status;
     private java.util.Date changedate;
     private java.lang.String changeby;
     private java.lang.String author;
     private java.util.Date authordate;
     private java.lang.String ownerdept;
     private java.lang.String libnum;
     private java.lang.String attachname;
     private java.lang.String inlistshow;
     
    /** default constructor */
    public Document(){}
    
   
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
    * �ĵ�����
    * @return java.lang.String
    */
	public java.lang.String getDoctype() {
		if(this.doctype==null || this.doctype.length()<=0)
	  		return null;
	  	else
	  		return this.doctype;
	}
	
	/**
    * �ĵ�����
    * @return java.lang.String
    */
	public void setDoctype(java.lang.String doctype) {
	   this.doctype = doctype;
	}
	
	
    /**
    * �ĵ���չ��
    * @return java.lang.String
    */
	public java.lang.String getDocextend() {
		if(this.docextend==null || this.docextend.length()<=0)
	  		return null;
	  	else
	  		return this.docextend;
	}
	
	/**
    * �ĵ���չ��
    * @return java.lang.String
    */
	public void setDocextend(java.lang.String docextend) {
	   this.docextend = docextend;
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
    * �ĵ�·��
    * @return java.lang.String
    */
	public java.lang.String getUrlpath() {
		if(this.urlpath==null || this.urlpath.length()<=0)
	  		return null;
	  	else
	  		return this.urlpath;
	}
	
	/**
    * �ĵ�·��
    * @return java.lang.String
    */
	public void setUrlpath(java.lang.String urlpath) {
	   this.urlpath = urlpath;
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
    * �޸�����
    * @return java.util.Date
    */
	public java.util.Date getChangedate() {
	  		return this.changedate;
	}
	
	/**
    * �޸�����
    * @return java.util.Date
    */
	public void setChangedate(java.util.Date changedate) {
	   this.changedate = changedate;
	}
	
	
    /**
    * �޸���
    * @return java.lang.String
    */
	public java.lang.String getChangeby() {
		if(this.changeby==null || this.changeby.length()<=0)
	  		return null;
	  	else
	  		return this.changeby;
	}
	
	/**
    * �޸���
    * @return java.lang.String
    */
	public void setChangeby(java.lang.String changeby) {
	   this.changeby = changeby;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getAuthor() {
		if(this.author==null || this.author.length()<=0)
	  		return null;
	  	else
	  		return this.author;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setAuthor(java.lang.String author) {
	   this.author = author;
	}
	
	
    /**
    * д��ʱ��
    * @return java.util.Date
    */
	public java.util.Date getAuthordate() {
	  		return this.authordate;
	}
	
	/**
    * д��ʱ��
    * @return java.util.Date
    */
	public void setAuthordate(java.util.Date authordate) {
	   this.authordate = authordate;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getOwnerdept() {
		if(this.ownerdept==null || this.ownerdept.length()<=0)
	  		return null;
	  	else
	  		return this.ownerdept;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setOwnerdept(java.lang.String ownerdept) {
	   this.ownerdept = ownerdept;
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
    * �����ļ���
    * @return java.lang.String
    */
	public java.lang.String getAttachname() {
		if(this.attachname==null || this.attachname.length()<=0)
	  		return null;
	  	else
	  		return this.attachname;
	}
	
	/**
    * �����ļ���
    * @return java.lang.String
    */
	public void setAttachname(java.lang.String attachname) {
	   this.attachname = attachname;
	}
	
	
    /**
    * ���б���ʾ��
    * @return java.lang.String
    */
	public java.lang.String getInlistshow() {
		if(this.inlistshow==null || this.inlistshow.length()<=0)
	  		return null;
	  	else
	  		return this.inlistshow;
	}
	
	/**
    * ���б���ʾ��
    * @return java.lang.String
    */
	public void setInlistshow(java.lang.String inlistshow) {
	   this.inlistshow = inlistshow;
	}
	
	
}