<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="combiz.system.IBSServer,combiz.domain.user.Ibsusers;"%>
<%
String url = IBSServer.getIBSServer().getURL();
String DBProduct = IBSServer.getIBSServer().getDatabaseProductName();
String jarfile = "ibsdraw_ora.jar";
if(DBProduct.equals("ORACLE"))
	jarfile = "ibsdraw_ora.jar";
else
	jarfile = "ibsdraw_sql.jar";
String fileName = request.getParameter("fileName");
String editMold = request.getParameter("editMold");
if(editMold==null)
	editMold = "false";
if(fileName==null) 
	fileName = "";
else
	fileName = new String(fileName.getBytes("ISO8859-1"),"GBK");
Ibsusers ibsuser = (Ibsusers)session.getAttribute("userinfo");
String drawLibRoot = IBSServer.getIBSServer().getDrawLibRoot();
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
<script type="text/javascript">

function viewEq(type,numString)
{
	//type--- �ҽӵ���λ�û����豸�������λ�ã�location,������豸��equipment
	//numString--- ���ݹ��������ַ�����λ�û����豸�ı���  CLASSID="clsid:5852F5ED-8BF4-11D4-A245-0080C6F74284"
	ifme = document.getElementById("eqlist");
	if(type=='equipment')
		ifme.src= '../eqspeclist.jsp?eqnum= ' + numString;
	else if(type=='location')
		ifme.src= '../locspeclist.jsp?location= ' + numString;
}
</script>	
	</head>
	<BODY text=#000000 bgColor=#CCCCCC scrollors=no SCROLL="no" top=0 left=0 leftMargin=0 topMargin=0 
	marginheight="0" marginwidth="0">
	<TABLE cellSpacing=0 cellPadding=0 width="100%" height="100%" border=0 >
		<TR>
			<TD width="100%" height="100%"><!--<object codebase="<%=httpurl%>/system/jre-1_5_0-windows-i586.exe"> #Version=1,5,0,0 -->

			<applet code="com.inbasis.ibsdraw.applet.JFDrawApplet.class" width="100%" height="100%" 
				archive="<%=httpurl%>/eqdraw/<%=jarfile%>"/>
			<PARAM NAME = "jndiName" VALUE = "<%=url%>" >
			<PARAM NAME = "fileName" VALUE = "<%=fileName%>">
			<PARAM NAME="userId" VALUE="<%=ibsuser.getUserid()%>">
			<PARAM NAME="drawLibRoot" VALUE="<%=drawLibRoot%>">
			<PARAM NAME="editMold" VALUE="<%=editMold%>">
			<p>
		          ���������û�а�װJAVA���л���JRE1.5��<a href="<%=httpurl%>/system/jre-1_5_0-windows-i586.exe" target="blank_">����˴����ذ�װ�ļ�</a>
			</p>
			
			<applet/>
			<!-- /OBJECT> -->
			</TD>
		</TR>
	</TABLE>
	
	<!--  �����ǲ����� 
	<a href="javascript:viewEq('equipment','�豸1');">�豸����</a>
<iframe name="eqlist" height="200" width="100%" scrolling="auto" border="0" frameborder="0">
�������֧��Ƕ��ʽ��ܣ�������Ϊ����ʾǶ��ʽ��ܡ�</iframe>
	-->
	
	</body>
	</html>


