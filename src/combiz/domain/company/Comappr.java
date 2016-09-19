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
    * 用户评价
    * @return java.lang.String
    */
	public java.lang.String getAppraise() {
		if(this.appraise==null || this.appraise.length()<=0)
	  		return null;
	  	else
	  		return this.appraise;
	}
	
	/**
    * 用户评价
    * @return java.lang.String
    */
	public void setAppraise(java.lang.String appraise) {
	   this.appraise = appraise;
	}
	
	
    /**
    * 供应商资质
    * @return java.lang.String
    */
	public java.lang.String getAptitude() {
		if(this.aptitude==null || this.aptitude.length()<=0)
	  		return null;
	  	else
	  		return this.aptitude;
	}
	
	/**
    * 供应商资质
    * @return java.lang.String
    */
	public void setAptitude(java.lang.String aptitude) {
	   this.aptitude = aptitude;
	}
	
	
    /**
    * 供货及时性
    * @return java.lang.Long
    */
	public java.lang.Long getBttimes() {
	  		return this.bttimes;
	}
	
	/**
    * 供货及时性
    * @return java.lang.Long
    */
	public void setBttimes(java.lang.Long bttimes) {
	   this.bttimes = bttimes;
	}
	
	
    /**
    * 公司
    * @return java.lang.String
    */
	public java.lang.String getCompany() {
		if(this.company==null || this.company.length()<=0)
	  		return null;
	  	else
	  		return this.company;
	}
	
	/**
    * 公司
    * @return java.lang.String
    */
	public void setCompany(java.lang.String company) {
	   this.company = company;
	}
	
	
    /**
    * 产品合格率
    * @return java.lang.Double
    */
	public java.lang.Double getContentment() {
	  		return this.contentment;
	}
	
	/**
    * 产品合格率
    * @return java.lang.Double
    */
	public void setContentment(java.lang.Double contentment) {
	   this.contentment = contentment;
	}
	
	
    /**
    * 信誉等级
    * @return java.lang.String
    */
	public java.lang.String getGrade() {
		if(this.grade==null || this.grade.length()<=0)
	  		return null;
	  	else
	  		return this.grade;
	}
	
	/**
    * 信誉等级
    * @return java.lang.String
    */
	public void setGrade(java.lang.String grade) {
	   this.grade = grade;
	}
	
	
    /**
    * 产品质量
    * @return java.lang.Long
    */
	public java.lang.Long getQuality() {
	  		return this.quality;
	}
	
	/**
    * 产品质量
    * @return java.lang.Long
    */
	public void setQuality(java.lang.Long quality) {
	   this.quality = quality;
	}
	
	
    /**
    * 总评分
    * @return java.lang.Long
    */
	public java.lang.Long getTotalscore() {
	  		return this.totalscore;
	}
	
	/**
    * 总评分
    * @return java.lang.Long
    */
	public void setTotalscore(java.lang.Long totalscore) {
	   this.totalscore = totalscore;
	}
	
	
}