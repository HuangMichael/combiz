package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Itemeqbom extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields 
     private java.lang.String itemnum;
     private java.lang.String parent;
     private java.lang.Double quanity;
     private java.lang.String remark;
     
    /** default constructor */
    public Itemeqbom(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getParent() {
		if(this.parent==null || this.parent.length()<=0)
	  		return null;
	  	else
	  		return this.parent;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setParent(java.lang.String parent) {
	   this.parent = parent;
	}
	
	
    /**
    * ����
    * @return java.lang.Double
    */
	public java.lang.Double getQuanity() {
	  		return this.quanity;
	}
	
	/**
    * ����
    * @return java.lang.Double
    */
	public void setQuanity(java.lang.Double quanity) {
	   this.quanity = quanity;
	}
	
	
    /**
    * ��ע
    * @return java.lang.String
    */
	public java.lang.String getRemark() {
		if(this.remark==null || this.remark.length()<=0)
	  		return null;
	  	else
	  		return this.remark;
	}
	
	/**
    * ��ע
    * @return java.lang.String
    */
	public void setRemark(java.lang.String remark) {
	   this.remark = remark;
	}
	
	
}