package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Reject extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String informman;
     private java.lang.String memo;
     private java.lang.String reason;
     private java.util.Date rejectdate;
     private java.lang.String rejectnum;
     private java.lang.String rejecttype;
     private java.lang.String reqdept;
     private java.lang.String requestby;
     private java.lang.String status;
     private java.util.Date statusdate;
     private java.lang.String usedept;
     
    /** default constructor */
    public Reject(){}
    
   
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
	public java.lang.String getInformman() {
		if(this.informman==null || this.informman.length()<=0)
	  		return null;
	  	else
	  		return this.informman;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setInformman(java.lang.String informman) {
	   this.informman = informman;
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
    * ����ԭ��
    * @return java.lang.String
    */
	public java.lang.String getReason() {
		if(this.reason==null || this.reason.length()<=0)
	  		return null;
	  	else
	  		return this.reason;
	}
	
	/**
    * ����ԭ��
    * @return java.lang.String
    */
	public void setReason(java.lang.String reason) {
	   this.reason = reason;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getRejectdate() {
	  		return this.rejectdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setRejectdate(java.util.Date rejectdate) {
	   this.rejectdate = rejectdate;
	}
	
	
    /**
    * ���ϵ����
    * @return java.lang.String
    */
	public java.lang.String getRejectnum() {
		if(this.rejectnum==null || this.rejectnum.length()<=0)
	  		return null;
	  	else
	  		return this.rejectnum;
	}
	
	/**
    * ���ϵ����
    * @return java.lang.String
    */
	public void setRejectnum(java.lang.String rejectnum) {
	   this.rejectnum = rejectnum;
	}
	
	
    /**
    * �������ͣ���ʶ���ʲ�/���ı��ϣ�
    * @return java.lang.String
    */
	public java.lang.String getRejecttype() {
		if(this.rejecttype==null || this.rejecttype.length()<=0)
	  		return null;
	  	else
	  		return this.rejecttype;
	}
	
	/**
    * �������ͣ���ʶ���ʲ�/���ı��ϣ�
    * @return java.lang.String
    */
	public void setRejecttype(java.lang.String rejecttype) {
	   this.rejecttype = rejecttype;
	}
	
	
    /**
    * ���벿��
    * @return java.lang.String
    */
	public java.lang.String getReqdept() {
		if(this.reqdept==null || this.reqdept.length()<=0)
	  		return null;
	  	else
	  		return this.reqdept;
	}
	
	/**
    * ���벿��
    * @return java.lang.String
    */
	public void setReqdept(java.lang.String reqdept) {
	   this.reqdept = reqdept;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getRequestby() {
		if(this.requestby==null || this.requestby.length()<=0)
	  		return null;
	  	else
	  		return this.requestby;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setRequestby(java.lang.String requestby) {
	   this.requestby = requestby;
	}
	
	
    /**
    * ����״̬
    * @return java.lang.String
    */
	public java.lang.String getStatus() {
		if(this.status==null || this.status.length()<=0)
	  		return null;
	  	else
	  		return this.status;
	}
	
	/**
    * ����״̬
    * @return java.lang.String
    */
	public void setStatus(java.lang.String status) {
	   this.status = status;
	}
	
	
    /**
    * ��������
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ��������
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
    /**
    * ��ǩ����
    * @return java.lang.String
    */
	public java.lang.String getUsedept() {
		if(this.usedept==null || this.usedept.length()<=0)
	  		return null;
	  	else
	  		return this.usedept;
	}
	
	/**
    * ��ǩ����
    * @return java.lang.String
    */
	public void setUsedept(java.lang.String usedept) {
	   this.usedept = usedept;
	}
	
	
}