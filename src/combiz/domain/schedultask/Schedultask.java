package combiz.domain.schedultask;



/**
 * Schedultask generated by MyEclipse - Hibernate Tools
 */

public class Schedultask  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String description;
     private String classname;
     private String methodname;
     private String groupname;
     private String triggername;
     private String cronexp;
     private String repeatcount;
     private String enddatetime;
     private String startdatetime;
     private String isruning;


    // Constructors

    /** default constructor */
    public Schedultask() {
    }

	/** minimal constructor */
    public Schedultask(String classname, String cronexp) {
        this.classname = classname;
        this.cronexp = cronexp;
    }
    
    /** full constructor */
    public Schedultask(String description, String classname, String methodname, String groupname, String triggername, String cronexp, String repeatcount, String enddatetime, String startdatetime, String isruning) {
        this.description = description;
        this.classname = classname;
        this.methodname = methodname;
        this.groupname = groupname;
        this.triggername = triggername;
        this.cronexp = cronexp;
        this.repeatcount = repeatcount;
        this.enddatetime = enddatetime;
        this.startdatetime = startdatetime;
        this.isruning = isruning;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethodname() {
        return this.methodname;
    }
    
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getTriggername() {
        return this.triggername;
    }
    
    public void setTriggername(String triggername) {
        this.triggername = triggername;
    }

    public String getCronexp() {
        return this.cronexp;
    }
    
    public void setCronexp(String cronexp) {
        this.cronexp = cronexp;
    }

    public String getRepeatcount() {
        return this.repeatcount;
    }
    
    public void setRepeatcount(String repeatcount) {
        this.repeatcount = repeatcount;
    }

    public String getEnddatetime() {
        return this.enddatetime;
    }
    
    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getStartdatetime() {
        return this.startdatetime;
    }
    
    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    public String getIsruning() {
        return this.isruning;
    }
    
    public void setIsruning(String isruning) {
        this.isruning = isruning;
    }
   








}