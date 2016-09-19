package combiz.domain.company;

import combiz.system.IBOBaseObject;

public class Comappr extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String appraise;
     private java.lang.String aptitude;
     private java.lang.Long bttimes;
     private java.lang.String company;
     private java.lang.Double contentment;
     private java.lang.String grade;
     private java.lang.Long quality;
     private java.lang.Long totalscore;
     
    /** default constructor */
    public Comappr(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �û�����
    * @return java.lang.String
    */
	public java.lang.String getAppraise() {
		if(this.appraise==null || this.appraise.length()<=0)
	  		return null;
	  	else
	  		return this.appraise;
	}
	
	/**
    * �û�����
    * @return java.lang.String
    */
	public void setAppraise(java.lang.String appraise) {
	   this.appraise = appraise;
	}
	
	
    /**
    * ��Ӧ������
    * @return java.lang.String
    */
	public java.lang.String getAptitude() {
		if(this.aptitude==null || this.aptitude.length()<=0)
	  		return null;
	  	else
	  		return this.aptitude;
	}
	
	/**
    * ��Ӧ������
    * @return java.lang.String
    */
	public void setAptitude(java.lang.String aptitude) {
	   this.aptitude = aptitude;
	}
	
	
    /**
    * ������ʱ��
    * @return java.lang.Long
    */
	public java.lang.Long getBttimes() {
	  		return this.bttimes;
	}
	
	/**
    * ������ʱ��
    * @return java.lang.Long
    */
	public void setBttimes(java.lang.Long bttimes) {
	   this.bttimes = bttimes;
	}
	
	
    /**
    * ��˾
    * @return java.lang.String
    */
	public java.lang.String getCompany() {
		if(this.company==null || this.company.length()<=0)
	  		return null;
	  	else
	  		return this.company;
	}
	
	/**
    * ��˾
    * @return java.lang.String
    */
	public void setCompany(java.lang.String company) {
	   this.company = company;
	}
	
	
    /**
    * ��Ʒ�ϸ���
    * @return java.lang.Double
    */
	public java.lang.Double getContentment() {
	  		return this.contentment;
	}
	
	/**
    * ��Ʒ�ϸ���
    * @return java.lang.Double
    */
	public void setContentment(java.lang.Double contentment) {
	   this.contentment = contentment;
	}
	
	
    /**
    * �����ȼ�
    * @return java.lang.String
    */
	public java.lang.String getGrade() {
		if(this.grade==null || this.grade.length()<=0)
	  		return null;
	  	else
	  		return this.grade;
	}
	
	/**
    * �����ȼ�
    * @return java.lang.String
    */
	public void setGrade(java.lang.String grade) {
	   this.grade = grade;
	}
	
	
    /**
    * ��Ʒ����
    * @return java.lang.Long
    */
	public java.lang.Long getQuality() {
	  		return this.quality;
	}
	
	/**
    * ��Ʒ����
    * @return java.lang.Long
    */
	public void setQuality(java.lang.Long quality) {
	   this.quality = quality;
	}
	
	
    /**
    * ������
    * @return java.lang.Long
    */
	public java.lang.Long getTotalscore() {
	  		return this.totalscore;
	}
	
	/**
    * ������
    * @return java.lang.Long
    */
	public void setTotalscore(java.lang.Long totalscore) {
	   this.totalscore = totalscore;
	}
	
	
}