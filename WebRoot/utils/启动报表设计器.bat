set JDK_HOME=%JAVA_HOME%
cd ../
set RPT_HOME=%cd%
"%JDK_HOME%/bin/java" -Xmx256M -DREPORT_HOME=%RPT_HOME%/ -cp %RPT_HOME%/WEB-INF/lib/inbasis_rpt.jar;%RPT_HOME%/WEB-INF/lib/hibernate3.jar;%RPT_HOME%/WEB-INF/lib/oraclethin.jar;%RPT_HOME%/WEB-INF/lib/mssqlserver.jar;%RPT_HOME%/WEB-INF/lib/msbase.jar;%RPT_HOME%/WEB-INF/lib/msutil.jar;%RPT_HOME%/WEB-INF/lib/derbyclient.jar;%RPT_HOME%/WEB-INF/lib/derbyLocale_zh_CN.jar;%RPT_HOME%/WEB-INF/lib/derbynet.jar;%RPT_HOME%/WEB-INF/lib/derby.jar com.fr.start.Designer