<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.io.*"%>
<%@ page import="combiz.system.util.Util"%>
<HTML>
<HEAD>
<TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<style>
body,td,input,textarea {font-size:9pt}
</style>
<%
	String pagePath = new String(request.getParameter("pagepath").getBytes("iso8859_1"));

	//执行保存动作
	String submit = request.getParameter("submit");
	if (submit != null)
	{
		String submitContent = new String(request.getParameter("content1").getBytes("iso8859_1"));
		if(submitContent==null)
			submitContent="";
		if (submitContent.indexOf("<META http-equiv=Content-Type content=\"text/html; charset=GBK\"><style id=\"basecss\" type=\"text/css\">") < 0)
		{
			submitContent = "<META http-equiv=Content-Type content=\"text/html; charset=GBK\"><style id=\"basecss\" type=\"text/css\">P { 	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Tahoma}BODY {	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Tahoma}TD {	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Tahoma}A {	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Tahoma; TEXT-DECORATION: none}A:hover {	TEXT-DECORATION: underline}</style>"
				+ submitContent;
		}

		String path = config.getServletContext().getRealPath("/")
			+ File.separator + "system" + File.separator + "help"
			+ File.separator + "helpfiles" + File.separator + pagePath;
		File file = new File(path);
		if (!file.exists())
		{
			File parentFile = new File(file.getParent());
			if (!parentFile.exists())
				parentFile.mkdirs();
			file.createNewFile();
		}
		FileWriter filewriter = new FileWriter(file);
		filewriter.write(submitContent);
		filewriter.flush();
		filewriter.close();
		out.println("<script>alert('保存帮助文件成功！')</script>");
	}
	//读取文件
	String fileContent = "";
	if (pagePath != null)
	{
		String path = config.getServletContext().getRealPath("/")
			+ File.separator + "system" + File.separator + "help"
			+ File.separator + "helpfiles" + File.separator + pagePath;
		String readStr = "";
		File file = new File(path);
		if (file.exists())
		{
			BufferedReader bufread = new BufferedReader(new FileReader(file));
			while ((readStr = bufread.readLine()) != null)
			{
				fileContent = fileContent + readStr;
			}
			bufread.close();
		}
	}
%>
</HEAD>

<BODY topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" bgcolor="#EEEEDD">
<FORM method="POST" name="editform" action="index.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1" width="100%" height="100%">
<TR>
	<TD>
		<INPUT type="hidden" name="content1" value="<%=Util.HTMLEncode(fileContent)%>">
		<IFRAME ID="eWebEditor1" src="eWebEditor.jsp?id=content1&style=standard" frameborder="0" scrolling="no" width="100%" height="100%"></IFRAME>
	</TD>
</TR>
<TR>
	<TD colspan=2 align=right height="24">
	<p align="right">
	<input type="hidden" name="pagepath" value="<%=pagePath%>" >
	<INPUT type=submit name=submit value="保存编辑文件">
	</TD>
</TR>
</TABLE>
</FORM>

</BODY>
</HTML>
