package combiz.domain.contract;

import combiz.system.IBOBaseObject;

public class Contterms extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String cntnum;
     private java.lang.String memo;
     private java.lang.String termnum;
     private java.lang.String terms;
     private java.lang.String termtype;
     
    /** default constructor */
    public Contterms(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * ��ͬ���
    * @return java.lang.String
    */
	public java.lang.String getCntnum() {
		if(this.cntnum==null || this.cntnum.length()<=0)
	  		return null;
	  	else
	  		return this.cntnum;
	}
	
	/**
    * ��ͬ���
    * @return java.lang.String
    */
	public void setCntnum(java.lang.String cntnum) {
	   this.cntnum = cntnum;
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
    * ������
    * @return java.lang.String
    */
	public java.lang.String getTermnum() {
		if(this.termnum==null || this.termnum.length()<=0)
	  		return null;
	  	else
	  		return this.termnum;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setTermnum(java.lang.String termnum) {
	   this.termnum = termnum;
	}
	
	
    /**
    * ����
    * @return java.lang.String
    */
	public java.lang.String getTerms() {
		if(this.terms==null || this.terms.length()<=0)
	  		return null;
	  	else
	  		return this.terms;
	}
	
	/**
    * ����
    * @return java.lang.String
    */
	public void setTerms(java.lang.String terms) {
	   this.terms = terms;
	}
	
	
    /**
    * ��������
    * @return java.lang.String
    */
	public java.lang.String getTermtype() {
		if(this.termtype==null || this.termtype.length()<=0)
	  		return null;
	  	else
	  		return this.termtype;
	}
	
	/**
    * ��������
    * @return java.lang.String
    */
	public void setTermtype(java.lang.String termtype) {
	   this.termtype = termtype;
	}
	
	
}