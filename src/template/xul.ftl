<?xml version="1.0" encoding="GBK"?>
<mainwindow app="${appname}" title="${appdesc}" wfenabled="${wfenable}" resize="true" use="${mainWndClass}" 
xmlns:a="http://www.w3c.com/2005/zk/annotation">

<!-- ********************************** 菜单条和工具栏 ********************************** -->
<imenubar id="menubar"/>

<!-- ********************************** 消息提示框栏 ********************************** -->
<messagebar/>

<!-- ********************************** 标签页面 ********************************** -->
	<tabbox id="appTabbox" width="100%" onSelect="mainWnd.selectTab()">
		<tabs>
			<tab id="list" label="列表" width="70px"/>
			<tab id="main" label="${appdesc}"/>
			<#list childs as child>
			<tab id="${child.relname?lower_case}Tab" label="${child.description}"/>
			</#list>
		</tabs>
		
<!-- **********************************  列表结果集页面 ********************************** -->
		<tabpanels width="100%">
			<tabpanel>
			<zscript><![CDATA[
			//在此处加入应用程序默认过滤条件
			String queryString="";
			mainWnd.setQueryString(queryString);
			mainWnd.setOrderby("");
			]]></zscript>
			<!-- 查询过滤器 -->
			<hbox width="100%">
				<groupbox width="180">
				<caption label="高级查询" />
					<menubar use="combiz.system.ui.common.SearchMenuBar"/>
				</groupbox>
				<groupbox id="searchBox">
					<caption label="过滤器" />
						<!--过滤字段示例：  应用程序：<stextbox data="app" cols="10"/> -->
				</groupbox>
			</hbox>

			<!-- 结果集列表  -->
			<pagenavigator/>
				<resultlist pageSize="20">
					<listhead>
						<resultlistheader src="/images/img_listitem.gif"/>
						<#list props as prop>
						<resultlistheader data="${prop.colname?lower_case}" label="${prop.title}" sort="auto" width="10%"/>
						</#list>
					</listhead>
				</resultlist>
			</tabpanel>
			
<!-- ********************************** 主数据窗口 ********************************** -->
<!-- 初始化设置字段的必输和只读 -->
 <zscript><![CDATA[
 String[] required = {""};
 mainWnd.setRequired(required);
 String[] readonly = {""};
 mainWnd.setReadonly(readonly);
 ]]></zscript>
			<tabpanel>
				<grid>
					<rows>
						<!-- ******************************************************************** -->
						<!-- *************************在下面修改主界面字段**************************** -->
						<!-- ********************************************************************-->
						<#list props as prop>
						<row>
						${prop.title}:<a:bind value="${tablename?lower_case}.${prop.colname?lower_case}"/><#rt>
						<#switch prop.dbtype>
						<#case "字符">
							<#switch prop.listtype>
							<#case "bandbox">
							<#lt><ibandbox id="${tablename?lower_case}.${prop.colname?lower_case}" lookup="${prop.listname?lower_case}"/><#break>
							<#case "combobox">
							<#lt><icombobox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
							<#default>
							<#lt><itextbox id="${tablename?lower_case}.${prop.colname?lower_case}"/>
							</#switch>
							<#break>
						<#case "数值">
							<#lt><idoublebox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "整数">
							<#lt><ilongbox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "日期">
							<#lt><idatebox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "日期时间">
							<#lt><idatetimebox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#default>
							<#lt><itextbox id="${tablename?lower_case}.${prop.colname?lower_case}"/>
						</#switch>
						
						</row>
						</#list>
					</rows>
				</grid>
			</tabpanel>
			
<#list childs as child>
<!-- *****************************${child.description}**************************** -->
			<tabpanel>
				<listwindow id="${child.relname}" title="${child.description}" <#if child.listwindow?exists>use="${child.listwindow}"</#if> dialog="${child.dialog}">
				<defaultvalue band="${child.relname}" value=""/>
				<tablelist band="${child.relname}" pageSize="16">
					<listhead>
						<tablelistheader src="/images/img_listitem.gif"/>
						<#list child.props as childprop>
						<tablelistheader data="${childprop.colname?lower_case}" label="${childprop.title}" sort="auto" width="10%"/>
						</#list>
					</listhead>
					<tablelistfoot/>
				</tablelist>
				</listwindow>
			</tabpanel>
</#list>
		</tabpanels>
	</tabbox>
</mainwindow>
