package combiz.domain.cal;

import java.util.Date;


/**
 * Calworkperiod generated by MyEclipse - Hibernate Tools
 */

public class Calworkperiod  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String calnum;
     private Date workdate;
     private String shiftnum;
     private Date starttime;
     private Date endtime;
     private Float workhours;


    // Constructors

    /** default constructor */
    public Calworkperiod() {
    }

    
    /** full constructor */
    public Calworkperiod(String calnum, Date workdate, String shiftnum, Date starttime, Date endtime, Float workhours) {
        this.calnum = calnum;
        this.workdate = workdate;
        this.shiftnum = shiftnum;
        this.starttime = starttime;
        this.endtime = endtime;
        this.workhours = workhours;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCalnum() {
        return this.calnum;
    }
    
    public void setCalnum(String calnum) {
        this.calnum = calnum;
    }

    public Date getWorkdate() {
        return this.workdate;
    }
    
    public void setWorkdate(Date workdate) {
        this.workdate = workdate;
    }

    public String getShiftnum() {
        return this.shiftnum;
    }
    
    public void setShiftnum(String shiftnum) {
        this.shiftnum = shiftnum;
    }

    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Float getWorkhours() {
        return this.workhours;
    }
    
    public void setWorkhours(Float workhours) {
        this.workhours = workhours;
    }
   








}