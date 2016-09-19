<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业资产管理信息系统用户登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="images/xtree.css" type=text/css rel=stylesheet>
		<link href="images/User_login.css" type=text/css rel=stylesheet>
		<meta content="mshtml 6.00.6000.16674" name=generator>
	</head>
	<body id=userlogin_body>
		<div align="center">
			<h1 style="font: '微软雅黑'; font-size: 24pt;color: white;">企业资产管理信息系统</h1></div>

		<div id="user_login" align="center">
			<dl>
				<dd id=user_top>
					<ul>
						<li class=user_top_l></li>
						<li class=user_top_c></li>
						<li class=user_top_r></li>
					</ul>
					<dd id=user_main>
						<ul>
							<li class=user_main_l></li>
							<li class=user_main_c>
								<div class=user_main_box>
									<ul>
										<li class=user_main_text>账号:</li>
										<li class=user_main_input>
											<input class=txtusernamecssclass id=txtusername maxlength=20 name=txtusername> </li>
									</ul>
									<ul>
										<li class=user_main_text>密码:</li>
										<li class=user_main_input>
											<input class=txtpasswordcssclass id=txtpassword type=password name=txtpassword> </li>
									</ul>
								</div>
							</li>
							<li class=user_main_r>
								<input class=ibtnentercssclass id=ibtnenter style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px" onclick='javascript:webform_dopostbackwithoptions(new webform_postbackoptions("ibtnenter", "", true, "", "", false, false))' type=image src="images/user_botton.gif" name=ibtnenter> </li>
						</ul>
						<dd id=user_bottom>
							<ul>
								<li class=user_bottom_l></li>
								<li class=user_bottom_c></li>
								<li class=user_bottom_r></li>
							</ul>
						</dd>
			</dl>
		</div>
		<span id=valrusername style="display: none; color: red"></span>
		<span id=valrpassword style="display: none; color: red"></span>
		<span id=valrvalidatecode style="display: none; color: red"></span>
		<div id=validationsummary1 style="display: none; color: red"></div>
		<div></div>
		</form>
	</body>
</html>