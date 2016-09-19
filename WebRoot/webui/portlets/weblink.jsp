<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="combiz.system.IBSServer,combiz.domain.user.Ibsusers"%>
<jsp:directive.page import="combiz.domain.corp.Labor;"/>
<%
String url = IBSServer.getIBSServer().getURL();
Ibsusers ibsuser = (Ibsusers)session.getAttribute("userinfo");
Labor labor = (Labor)session.getAttribute("laborinfo");
String httpurl = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/" +request.getContextPath();
String userid = ibsuser.getUserid();
String deptnum = labor.getDeptnum();
%>
<html>
	<head>
	<meta http-equiv="Content-Language" content="zh-cn">
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
	<BODY text=#000000 scrollors=no SCROLL="no" top=0 left=0 leftMargin=0 topMargin=0 
	marginheight="0" marginwidth="0">
		
	<table border="1" width="100%" id="table1"  style="font-size: 12px">
		<tr>
			<td bgcolor="#3366FF">　</td>
			<td bgcolor="#3366FF">　</td>
		</tr>
		<tr>
			<td><a target="_blank" href="http://www.w3c.com/">英贝思软件科技有限公司</a></td>
			<td>　</td>
		</tr>
		<tr>
			<td><a target="_blank" href="http://www.eamworld.com/">企业资产管理世界</a></td>
			<td>　</td>
		</tr>
		<tr>
			<td>　</td>
			<td>　</td>
		</tr>
	</table>
		
	</body>
	</html>


