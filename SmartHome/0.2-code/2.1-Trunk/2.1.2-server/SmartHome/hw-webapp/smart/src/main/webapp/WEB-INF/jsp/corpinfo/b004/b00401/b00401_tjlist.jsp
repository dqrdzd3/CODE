<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)"
	executeResult="true" />

<title>设备设施信息-<s:text name="application.title" /></title>
<link href="<%=path %>/css/suggest.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var gridOption = {
		caption : '设备设施信息',
		formId : 'searchForm',
		colModel : [ {
			label : '设备名称',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent1
		}, {
			label : 'PK',
			name : 'MA001',
			index : 'MA001',
			hidden:true
		},
		{
			label : '数量',
			name : 'MA021',
			index : 'MA021'
		},
		{
			label : '设备类型',
			name : 'MA006',
			index : 'MA006'
		}, {
			label : '启用日期',
			name : 'MA010',
			index : 'MA010',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		}, {
			label : '有效截至日期',
			name : 'MA011',
			index : 'MA011',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		} ],
		pk : "MA001",
		multiselect:false,
		winTitle : "设备设施信息"
	};
	function viewb00401(id){
		var url=$$.getContextPath()+'corpinfo/b00401!doEditB00401?b00401PO.MA001='+id;
		
		$$.openDiv('viewdiv','设备设施查看',url,'',function(data){
			$$.clearInput('#viewdiv');
			jQuery("#viewdiv .save").hide();
			jQuery("#viewdiv .cancel").click(function(){
				jQuery("#viewdiv").dialog('close');
			});
		});
	}
	function showcontent1(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="viewb00401(\''+obj[1]+'\')">'+celv+'</a>';
	}
	function ajaxGetName(id,str,divid){
		var ma004=$("#MA004").val();
		var ma006=$("#MA006").val();
		if(str == null || str == '') {
			closeDiv('suggest_input');
		}else{
			getData($$.getContextPath()+'corpinfo/b00401!ajaxGetName?ram='+Math.random(),{'b00401PO.MA004':ma004,'b00401PO.MA006':ma006},id,divid);
		}
	}
</script>
</head>
<body>
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight" style="display:none">
					<s:form action="b00401" method="post" cssClass="formmargin"
						id="searchForm">
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 100px;">设备类型</td>
								<td colspan="1" style="width: 300px;">
									<s:select name="b00401PO.MA006" list="codeValueSBLX" listKey="value" id="MA006"
										listValue="name" headerKey="" headerValue="请选择"
									></s:select>
								</td>
								<td width="98" rowspan="2"><s:submit value=""
										cssClass="search" /></td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div class="operating" id="operating">
			<hwsoft:operation code="${code}" param="view,add,edit,delete" />
		</div>
		<div id="viewdiv"></div>
	</div>
</body>
</html>