package combiz.domain.invoice;

import java.util.Date;


/**
 * Invoicestatus generated by MyEclipse - Hibernate Tools
 */

public class Invoicestatus  implements java.io.Serializable {


    // Fields    

     private String id;
     private String invoicenum;
     private String status;
     private Date changedate;
     private String changeby;
     private String remark;


    // Constructors

    /** default constructor */
    public Invoicestatus() {
    }

	/** minimal constructor */
    public Invoicestatus(String invoicenum, String status, Date changedate, String changeby) {
        this.invoicenum = invoicenum;
        this.status = status;
        this.changedate = changedate;
        this.changeby = changeby;
    }
    
    /** full constructor */
    public Invoicestatus(String invoicenum, String status, Date changedate, String changeby, String remark) {
        this.invoicenum = invoicenum;
        this.status = status;
        this.changedate = changedate;
        this.changeby = changeby;
        this.remark = remark;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getInvoicenum() {
        return this.invoicenum;
    }
    
    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
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