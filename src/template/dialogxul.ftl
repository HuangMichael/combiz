<?xml version="1.0" encoding="GBK"?>
<editwindow xmlns:a="http://www.w3c.com/2005/zk/annotation">
 <zscript>
 String[] required = {}; 
 mainWnd.setRequired(required);
 String[] readonly = {}; 
 mainWnd.setReadonly(readonly);
 </zscript>
 <groupbox><caption label="${description}"><toolbarbutton label="�ر�" src="/images/close_edit_dialog.gif" onClick="mainWnd.closeWnd()"/></caption>
				<grid >
					<rows>
						<#list props as childprop>
						<row>
						${childprop.title}:<a:bind value="${tablename?lower_case}.${childprop.colname?lower_case}"/><#rt>
						<#switch childprop.dbtype>
						<#case "�ַ�">
							<#switch childprop.listtype>
							<#case "bandbox">
							<#lt><ibandbox id="${tablename?lower_case}.${childprop.colname?lower_case}" lookup="${childprop.listname?lower_case}"/><#break>
							<#case "combobox">
							<#lt><icombobox id="${tablename?lower_case}.${childprop.colname?lower_case}"/><#break>
							<#default>
							<#lt><itextbox id="${tablename?lower_case}.${childprop.colname?lower_case}"/>
							</#switch>
							<#break>
						<#case "��ֵ">
							<#lt><idoublebox id="${tablename?lower_case}.${childprop.colname?lower_case}"/><#break>
						<#case "����">
							<#lt><ilongbox id="${tablename?lower_case}.${childprop.colname?lower_case}"/><#break>
						<#case "����">
							<#lt><idatebox id="${tablename?lower_case}.${childprop.colname?lower_case}"/><#break>
						<#case "����ʱ��">
							<#lt><idatetimebox id="${tablename?lower_case}.${childprop.colname?lower_case}"/><#break>
						<#default>
							<#lt><itextbox id="${tablename?lower_case}.${childprop.colname?lower_case}"/>
						</#switch>
						
						</row>
						</#list>

					</rows>
				</grid>
 </groupbox>				
</editwindow>