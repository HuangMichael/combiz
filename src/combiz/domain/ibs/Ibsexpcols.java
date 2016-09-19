package combiz.domain.ibs;

import combiz.system.IBOBaseObject;

public class Ibsexpcols extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String colname;
     private java.lang.String expname;
     private java.lang.Long position;
     private java.lang.String tablename;
     private java.lang.String userid;
     
    /** default constructor */
    public Ibsexpcols(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �����ֶ�
    * @return java.lang.String
    */
	public java.lang.String getColname() {
		if(this.colname==null || this.colname.length()<=0)
	  		return null;
	  	else
	  		return this.colname;
	}
	
	/**
    * �����ֶ�
    * @return java.lang.String
    */
	public void setColname(java.lang.String colname) {
	   this.colname = colname;
	}
	
	
    /**
    * ����������
    * @return java.lang.String
    */
	public java.lang.String getExpname() {
		if(this.expname==null || this.expname.length()<=0)
	  		return null;
	  	else
	  		return this.expname;
	}
	
	/**
    * ����������
    * @return java.lang.String
    */
	public void setExpname(java.lang.String expname) {
	   this.expname = expname;
	}
	
	
    /**
    * ����λ��
    * @return java.lang.Long
    */
	public java.lang.Long getPosition() {
	  		return this.position;
	}
	
	/**
    * ����λ��
    * @return java.lang.Long
    */
	public void setPosition(java.lang.Long position) {
	   this.position = position;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getTablename() {
		if(this.tablename==null || this.tablename.length()<=0)
	  		return null;
	  	else
	  		return this.tablename;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setTablename(java.lang.String tablename) {
	   this.tablename = tablename;
	}
	
	
    /**
    * �û���
    * @return java.lang.String
    */
	public java.lang.String getUserid() {
		if(this.userid==null || this.userid.length()<=0)
	  		return null;
	  	else
	  		return this.userid;
	}
	
	/**
    * �û���
    * @return java.lang.String
    */
	public void setUserid(java.lang.String userid) {
	   this.userid = userid;
	}
	
	
}