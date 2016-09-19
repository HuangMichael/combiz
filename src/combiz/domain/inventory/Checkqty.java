package combiz.domain.inventory;

import combiz.system.IBOBaseObject;

public class Checkqty extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
     private java.lang.String checkbinnum;
     private java.util.Date checkdate;
     private java.lang.String checkqtynum;
     private java.lang.String chkwarehouse;
     private java.lang.String classid;
     private java.lang.String informman;
     private java.lang.String isall;
     private java.lang.String itemnum;
     private java.lang.String memo;
     private java.lang.String operator;
     private java.lang.String status;
     private java.util.Date statusdate;
     
    /** default constructor */
    public Checkqty(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    /**
    * �̵����
    * @return java.lang.String
    */
	public java.lang.String getCheckbinnum() {
		if(this.checkbinnum==null || this.checkbinnum.length()<=0)
	  		return null;
	  	else
	  		return this.checkbinnum;
	}
	
	/**
    * �̵����
    * @return java.lang.String
    */
	public void setCheckbinnum(java.lang.String checkbinnum) {
	   this.checkbinnum = checkbinnum;
	}
	
	
    /**
    * �̵�����
    * @return java.util.Date
    */
	public java.util.Date getCheckdate() {
	  		return this.checkdate;
	}
	
	/**
    * �̵�����
    * @return java.util.Date
    */
	public void setCheckdate(java.util.Date checkdate) {
	   this.checkdate = checkdate;
	}
	
	
    /**
    * �̵㵥���
    * @return java.lang.String
    */
	public java.lang.String getCheckqtynum() {
		if(this.checkqtynum==null || this.checkqtynum.length()<=0)
	  		return null;
	  	else
	  		return this.checkqtynum;
	}
	
	/**
    * �̵㵥���
    * @return java.lang.String
    */
	public void setCheckqtynum(java.lang.String checkqtynum) {
	   this.checkqtynum = checkqtynum;
	}
	
	
    /**
    * �̵�ⷿ
    * @return java.lang.String
    */
	public java.lang.String getChkwarehouse() {
		if(this.chkwarehouse==null || this.chkwarehouse.length()<=0)
	  		return null;
	  	else
	  		return this.chkwarehouse;
	}
	
	/**
    * �̵�ⷿ
    * @return java.lang.String
    */
	public void setChkwarehouse(java.lang.String chkwarehouse) {
	   this.chkwarehouse = chkwarehouse;
	}
	
	
    /**
    * ������
    * @return java.lang.String
    */
	public java.lang.String getClassid() {
		if(this.classid==null || this.classid.length()<=0)
	  		return null;
	  	else
	  		return this.classid;
	}
	
	/**
    * ������
    * @return java.lang.String
    */
	public void setClassid(java.lang.String classid) {
	   this.classid = classid;
	}
	
	
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
    * �Ƿ��̵����пⷿ
    * @return java.lang.String
    */
	public java.lang.String getIsall() {
		if(this.isall==null || this.isall.length()<=0)
	  		return null;
	  	else
	  		return this.isall;
	}
	
	/**
    * �Ƿ��̵����пⷿ
    * @return java.lang.String
    */
	public void setIsall(java.lang.String isall) {
	   this.isall = isall;
	}
	
	
    /**
    * ���ʱ��
    * @return java.lang.String
    */
	public java.lang.String getItemnum() {
		if(this.itemnum==null || this.itemnum.length()<=0)
	  		return null;
	  	else
	  		return this.itemnum;
	}
	
	/**
    * ���ʱ��
    * @return java.lang.String
    */
	public void setItemnum(java.lang.String itemnum) {
	   this.itemnum = itemnum;
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
    * �̵�Ա
    * @return java.lang.String
    */
	public java.lang.String getOperator() {
		if(this.operator==null || this.operator.length()<=0)
	  		return null;
	  	else
	  		return this.operator;
	}
	
	/**
    * �̵�Ա
    * @return java.lang.String
    */
	public void setOperator(java.lang.String operator) {
	   this.operator = operator;
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
    * ״̬����
    * @return java.util.Date
    */
	public java.util.Date getStatusdate() {
	  		return this.statusdate;
	}
	
	/**
    * ״̬����
    * @return java.util.Date
    */
	public void setStatusdate(java.util.Date statusdate) {
	   this.statusdate = statusdate;
	}
	
	
}