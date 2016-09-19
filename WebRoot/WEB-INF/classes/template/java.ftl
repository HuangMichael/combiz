package ${package};

import combiz.system.IBOBaseObject;

public class ${classname} extends IBOBaseObject
implements java.io.Serializable 
{
 	private Long id;
 	
     //Fields
    <#list fields as field>    
     private ${field.type} ${field.name};
    </#list>
     
    /** default constructor */
    public ${classname}(){}
    
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    //Property accessors
    <#list fields as field>
    /**
    * ${field.title}
    * @return ${field.type}
    */
	public ${field.type} get${field.name?cap_first}() {
		<#if field.type == "java.lang.String">  
		if(this.${field.name}==null || this.${field.name}.length()<=0)
	  		return null;
	  	else
	  		return this.${field.name};
	  	<#else>
	  		return this.${field.name};
	  	</#if>
	}
	
	/**
    * ${field.title}
    * @return ${field.type}
    */
	public void set${field.name?cap_first}(${field.type} ${field.name}) {
	   this.${field.name} = ${field.name};
	}
	
	
    </#list>
}