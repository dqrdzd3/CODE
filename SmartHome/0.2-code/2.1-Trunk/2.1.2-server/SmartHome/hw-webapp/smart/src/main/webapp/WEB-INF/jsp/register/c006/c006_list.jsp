<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>" />
<title><s:text name="企业注册" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,grid,commonJS)" executeResult="true"/>


<script type="text/javascript">
var gridOption = {
           			id:'gridTable',
					caption:'核查企业注册信息',
					colModel:[
						{label:'企业名称', name:'ma002', index:'ma002'},
						{label:'企业编号', name:'ma009', index:'ma009'},
						{label:'组织机构代码', name:'ma003', index:'ma003'},
						{label:'经济类型', name:'ma007Name', index:'ma007Name'},
						{label:'行业类型', name:'hymlname', index:'hymlname'},
						{label:'用户名', name:'ma010', index:'ma010'},
						{label:'安全负责人姓名', name:'ma012', index:'ma012'},
						{label:'安全负责人手机', name:'ma014', index:'ma014'},
						{label:'状态', name:'ma020Name', index:'ma020Name'}
					],
					pk:'ma001',
					formId:'searchForm',
					winTitle:'企业注册信息'
           		};
           

           var actionOption = {
                   		init:true,
                    	id:'operating',
                    	gridId:'gridTable'
                   };  	
          

           function hcFun(){
        		 var i = getSelectedRowID('#gridTable').length;
        		 if(i==0){
        			 showMsg("请选择待核查的注册记录!",4,"");
        			 return;
        		 }else if(i>1){
        			 showMsg("只能选择一条记录!",4,"");
        			 return;
        		 }
    			 if(jQuery('#gridTable').jqGrid("getCell",''+getSelectedRowID('#gridTable'),"ma020Name")!='未审核'){
    				 showMsg("该注册信息已经核查完成!",4,"");
    				 return;
    			 }
        		 
        		 $$.openDiv(
        			 'showd',
        			 '企业用户核查',
        			 $$.getContextPath()+'register/c006!doEditHC',
        			 {
        				 'c006PO.ma001':getSelectedRowID('#gridTable')+''
        			 }
        		 );
        	}
</script>
</head>
<body> 

<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form  method="post" cssClass="formmargin" id="searchForm"> 
			<table  border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td style="width:50px;"align="right" nowrap="nowrap">企业名称</td>
									<td  style="width:300px;"><s:textfield name="c006PO.ma002" cssClass="input2" /></td>
									
									<td style="width:80px;" align="right" nowrap="nowrap">组织机构代码</td>
									<td style="width:300px;"><s:textfield name="c006PO.ma003" cssClass="input2"/></td>
									
									<td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
								</tr>
							</table>
			</s:form>

	</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="hecha"/>
	</div>
		<div class="tablestyle">
		<div>
			<table id="gridTable"></table>
		</div>
	</div>
	<div id="showd"></div>
</body>
</html>