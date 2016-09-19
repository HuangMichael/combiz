<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="combiz.system.IBSServer,combiz.domain.user.Ibsusers;"%>
<%
String url = IBSServer.getIBSServer().getURL();
String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
String jarfile = "ibsdraw.jar";
String dbtype = "0";
if(DBProduct.equals("ORACLE"))
	dbtype = "1";
	//jarfile = "ibsdraw_ora.jar";
//else
	//jarfile = "ibsdraw_sql.jar";
String fileName = request.getParameter("fileName");
//String editMold = request.getParameter("editMold");
//if(fileName==null) 
//	fileName = "";
//else
//	fileName = new String(fileName.getBytes("ISO8859-1"),"GBK");
Ibsusers ibsuser = (Ibsusers)session.getAttribute("userinfo");
//String drawLibRoot = IBSServer.getIBSServer().getDrawLibRoot();
String httpurl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" +request.getContextPath();
%>
<html>
	<head>
	<title></title>
<script language="JavaScript">
<!--
if (window.Event) 
  document.captureEvents(Event.MOUSEUP); 
function nocontextmenu() 
{
 event.cancelBubble = true
 event.returnValue = false;
 return false;
}
function norightclick(e) 
{
 if (window.Event) 
 {
  if (e.which == 2 || e.which == 3)
   return false;
 }
 else
  if (event.button == 2 || event.button == 3)
  {
   event.cancelBubble = true
   event.returnValue = false;
   return false;
  }
}
document.oncontextmenu = nocontextmenu;  // for IE5+
document.onmousedown = norightclick;  // for all others
//-->
</script>
	</head>
	<BODY text=#000000 bgColor=#CCCCCC scrollors=no SCROLL="no" top=0 left=0 leftMargin=0 topMargin=0 
	marginheight="0" marginwidth="0">
	<TABLE cellSpacing=0 cellPadding=0 width="100%" height="100%" border=0 >
		<TR>
			<TD width="100%" height="100%">

			<applet name="com.inbasis.ibsdraw.applet.ViewApplet" code="com.inbasis.ibsdraw.applet.ViewApplet.class" width="100%" height="100%" 
				archive="<%=httpurl%>/eqdraw/<%=jarfile%>">
			<PARAM NAME = "jndiName" VALUE = "<%=url%>" >
			<PARAM NAME = "fileName" VALUE = "C:\doclib\文档库\图形设备\总图\v1\总图_v1.ibx">
			<PARAM NAME="userid" VALUE="<%=ibsuser.getUserid()%>">
			<PARAM NAME="databaseType" VALUE="<%=dbtype%>">
			<PARAM NAME="httpURL" VALUE="<%=httpurl%>">
			<p>
		          您的浏览器没有安装JAVA运行环境JRE1.5！<a href="<%=httpurl%>/system/jre-1_5_0-windows-i586.exe" target="blank_">点击此处下载安装文件</a>
			</p>
			</applet>
			
			</TD>
		</TR>
	</TABLE>
	
	</body>
	</html>


