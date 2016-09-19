<?xml version="1.0" encoding="GBK"?>
<mainwindow app="${appname}" title="${appdesc}" resize="true" wfenabled="${wfenable}" use="${mainWndClass}" 
xmlns:a="http://www.w3c.com/2005/zk/annotation">

<!-- ********************************** 菜单条和工具栏 ********************************** -->
<imenubar id="menubar"/>

<!-- ********************************** 消息提示框栏 ********************************** -->
<messagebar/>
<hbox spacing="0">
<!-- **********************************  结果树页面 ********************************** -->
<groupbox><caption label="${appdesc}"/>
<tree use="combiz.system.ui.common.MainTree"
topwhere="${topwhere}" expwhere="${expwhere}" parentvalue="${parentvalue}"
parentimg="${parentimg}" childimg="${childimg}"
label="${label}" openchild="${openchild}" width="180"/>
</groupbox>
<splitter collapse="before"/>
<!-- ********************************** 标签页面 ********************************** -->
	<tabbox id="appTabbox" width="100%" onSelect="mainWnd.selectTab()">
		<tabs>
			<tab id="main" label="${appdesc}" width="80px"/>
			<#list childs as child>
			<tab id="${child.relname?lower_case}Tab" label="${child.description}" width="80px"/>
			</#list>
		</tabs>
		
		<tabpanels width="100%">			
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
</hbox>
</mainwindow>
