<?xml version="1.0" encoding="GBK"?>
<mainwindow app="${appname}" title="${appdesc}" wfenabled="${wfenable}" resize="true" use="${mainWndClass}" 
xmlns:a="http://www.w3c.com/2005/zk/annotation">

<!-- ********************************** �˵����͹����� ********************************** -->
<imenubar id="menubar"/>

<!-- ********************************** ��Ϣ��ʾ���� ********************************** -->
<messagebar/>

<!-- ********************************** ��ǩҳ�� ********************************** -->
	<tabbox id="appTabbox" width="100%" onSelect="mainWnd.selectTab()">
		<tabs>
			<tab id="list" label="�б�" width="70px"/>
			<tab id="main" label="${appdesc}"/>
			<#list childs as child>
			<tab id="${child.relname?lower_case}Tab" label="${child.description}"/>
			</#list>
		</tabs>
		
<!-- **********************************  �б�����ҳ�� ********************************** -->
		<tabpanels width="100%">
			<tabpanel>
			<zscript><![CDATA[
			//�ڴ˴�����Ӧ�ó���Ĭ�Ϲ�������
			String queryString="";
			mainWnd.setQueryString(queryString);
			mainWnd.setOrderby("");
			]]></zscript>
			<!-- ��ѯ������ -->
			<hbox width="100%">
				<groupbox width="180">
				<caption label="�߼���ѯ" />
					<menubar use="combiz.system.ui.common.SearchMenuBar"/>
				</groupbox>
				<groupbox id="searchBox">
					<caption label="������" />
						<!--�����ֶ�ʾ����  Ӧ�ó���<stextbox data="app" cols="10"/> -->
				</groupbox>
			</hbox>

			<!-- ������б�  -->
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
			
<!-- ********************************** �����ݴ��� ********************************** -->
<!-- ��ʼ�������ֶεı����ֻ�� -->
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
						<!-- *************************�������޸��������ֶ�**************************** -->
						<!-- ********************************************************************-->
						<#list props as prop>
						<row>
						${prop.title}:<a:bind value="${tablename?lower_case}.${prop.colname?lower_case}"/><#rt>
						<#switch prop.dbtype>
						<#case "�ַ�">
							<#switch prop.listtype>
							<#case "bandbox">
							<#lt><ibandbox id="${tablename?lower_case}.${prop.colname?lower_case}" lookup="${prop.listname?lower_case}"/><#break>
							<#case "combobox">
							<#lt><icombobox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
							<#default>
							<#lt><itextbox id="${tablename?lower_case}.${prop.colname?lower_case}"/>
							</#switch>
							<#break>
						<#case "��ֵ">
							<#lt><idoublebox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "����">
							<#lt><ilongbox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "����">
							<#lt><idatebox id="${tablename?lower_case}.${prop.colname?lower_case}"/><#break>
						<#case "����ʱ��">
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
