package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsassetdraw extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String addesc;
     private java.lang.String adnum;
     private java.lang.String drawtype;
     private java.lang.Long drawver;
     private java.lang.String filepath;
     private java.lang.String parent;
     private java.lang.String remark;
     
    /** default constructor */
    public Ibsassetdraw(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ͼ���ļ����
    * @return java.lang.String
    */
	public java.lang.String getAddesc() {
		if(this.addesc==null || this.addesc.length()<=0)
	  		return null;
	  	else
	  		return this.addesc;
	}
	
	/**
    * ͼ���ļ����
    * @return java.lang.String
    */
	public void setAddesc(java.lang.String addesc) {
	   this.addesc = addesc;
	}
	
	
    /**
    * ͼ���ļ���ʶ
    * @return java.lang.String
    */
	public java.lang.String getAdnum() {
		if(this.adnum==null || this.adnum.length()<=0)
	  		return null;
	  	else
	  		return this.adnum;
	}
	
	/**
    * ͼ���ļ���ʶ
    * @return java.lang.String
    */
	public void setAdnum(java.lang.String adnum) {
	   this.adnum = adnum;
	}
	
	
    /**
    * ͼ������豸ͼ��λ��ͼ���Զ���
    * @return java.lang.String
    */
	public java.lang.String getDrawtype() {
		if(this.drawtype==null || this.drawtype.length()<=0)
	  		return null;
	  	else
	  		return this.drawtype;
	}
	
	/**
    * ͼ������豸ͼ��λ��ͼ���Զ���
    * @return java.lang.String
    */
	public void setDrawtype(java.lang.String drawtype) {
	   this.drawtype = drawtype;
	}
	
	
    /**
    * ͼ���ļ��汾
    * @return java.lang.Long
    */
	public java.lang.Long getDrawver() {
	  		return this.drawver;
	}
	
	/**
    * ͼ���ļ��汾
    * @return java.lang.Long
    */
	public void setDrawver(java.lang.Long drawver) {
	   this.drawver = drawver;
	}
	
	
    /**
    * ͼ���ļ����·��
    * @return java.lang.String
    */
	public java.lang.String getFilepath() {
		if(this.filepath==null || this.filepath.length()<=0)
	  		return null;
	  	else
	  		return this.filepath;
	}
	
	/**
    * ͼ���ļ����·��
    * @return java.lang.String
    */
	public void setFilepath(java.lang.String filepath) {
	   this.filepath = filepath;
	}
	
	
    /**
    * ����ͼ���ļ�
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * ����ͼ���ļ�
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * ��ע˵��
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע˵��
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
}