package combiz.domain.po;

import java.util.Date;


/**
 * Postatus generated by MyEclipse - Hibernate Tools
 */

public class Postatus  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String ponum;
     private String status;
     private Date changedate;
     private String changeby;
     private String remark;


    // Constructors

    /** default constructor */
    public Postatus() {
    }

	/** minimal constructor */
    public Postatus(String ponum, String status, Date changedate, String changeby) {
        this.ponum = ponum;
        this.status = status;
        this.changedate = changedate;
        this.changeby = changeby;
    }
    
    /** full constructor */
    public Postatus(String ponum, String status, Date changedate, String changeby, String remark) {
        this.ponum = ponum;
        this.status = status;
        this.changedate = changedate;
        this.changeby = changeby;
        this.remark = remark;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getPonum() {
        return this.ponum;
    }
    
    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getChangedate() {
        return this.changedate;
    }
    
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public String getChangeby() {
        return this.changeby;
    }
    
    public void setChangeby(String changeby) {
        this.changeby = changeby;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}