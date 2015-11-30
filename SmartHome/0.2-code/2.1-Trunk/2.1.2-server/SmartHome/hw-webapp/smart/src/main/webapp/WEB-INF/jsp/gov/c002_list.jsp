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
		<title><s:text name="政府机构列表" />-<s:text name="application.title" /></title>
		<s:action name="include(mainCSS,jquery,datepicker,grid,commonJS)" executeResult="true"/>
			
		<style type="text/css">
			<!--
			body,html {background:url(images/contentleftbg.jpg) left top repeat-y;
			 	overflow-x:hidden;
			}
			-->
		</style>
		
		<script type="text/javascript">
			var gridOption = {
					caption:'政府机构',
					colModel:[
						{label:'政府名称', name:'ma003', index:'ma003', formatter:viewLinkFormatter},
						{label:'政府代码', name:'ma002', index:'ma002'},
						{label:'联系电话', name:'ma005', index:'ma005'},
						{label:'是否有效', name:'ma008', index:'ma008', edittype:'select', formatter:'select', editoptions:{value:"10:是;00:否"}}
					],
					pk:'ma001',
					formId:'searchForm',
					winTitle:'政府机构'
			};
			
			var actionOption = {
					poName:'c002PO',
					table:'C002',
					beforeAdd : function(form) {
						var parentId=window.parent.frames['govLeftFrame'].document.getElementById("ma004").value;
						var parentDepar=window.parent.frames['govLeftFrame'].document.getElementById("parentDepar").value;
						if(parentId!=null){
							form["c002PO.ma004"].value=parentId;
							form["parentDepar"].value=parentDepar;
						}
					},
					beforeEdit:function(id,form){
						var parentDepar=window.parent.frames['govLeftFrame'].document.getElementById("parentDepar").value;
						form["parentDepar"].value=parentDepar;
					},
					beforeView:function(id,form){
						var parentDepar=window.parent.frames['govLeftFrame'].document.getElementById("parentDepar").value;
						form["parentDepar"].value=parentDepar;
					},
					success : function(form) {
						if(form["c002PO.ma001"].value==null || form["c002PO.ma001"].value==""){
							window.parent.frames['govLeftFrame'].addTreeNodes(form["c002PO.ma002"].value);
						}else{
							window.parent.frames['govLeftFrame'].updateTreeNode(form["c002PO.ma001"].value,form["c002PO.ma003"].value,form["c002PO.ma002"].value);
						}
					},
					buttonInited : function() {
						$$.bindButton('delete', function(){
							if ($$.checkSelRow(0,"gridTable")) return;
							var url = "gov/c002!doDeleteC002ByMa001s";
							var ids = getSelectedRowID("#gridTable");
						     showMsg('确定要删除所选的记录吗？',MSG.WARNING,{
						    	 buttons:{
							         '取消':function(){
							             $(this).dialog('close');
							         },
							         '确定':function(){
							        	 $.post(url,{table:"C002",pk:"ma001",ids:ids.join(),ran:Math.random()},function(msg){
												showMsg(msg.content,msg.type);
												reloadGrid("gridTable");
												if(msg.type==MSG.OK){
													window.parent.frames['govLeftFrame'].removeTreeNodes(ids);
												}
										 });
							         }
						     	}
						     });
							
						});
					}
				};
		</script>
	</head>
<body>
 	<div id="searcharea" style="margin-left:10px;">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form method="post" cssClass="formmargin" id="searchForm">
					<s:hidden name="c002PO.ma004" />
					<table border="0" cellspacing="8" cellpadding="0">
				      <tr>
				          <td align="right" style="width:50px;"  nowrap="nowrap">政府名称</td>
				          <td style="width:250px;" nowrap="nowrap">
							 <s:textfield name="c002PO.ma003" cssClass="input2"/>
					      </td>
					      <td align="right" style="width:50px;"  nowrap="nowrap">政府代码</td>
				          <td style="width:250px;" nowrap="nowrap">
							 <s:textfield name="c002PO.ma002" cssClass="input2"/>
					      </td>
				          <td  colspan="2" align="left" ><s:submit value="" cssClass="search"/></td>
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
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div id="moduleDiv" style="display: none;"></div>
</body>
</html>