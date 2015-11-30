<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>" />
<title>状态解决-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)"
	executeResult="true" />
<script type="text/javascript">
	var gridOption = {
		id : 'gridTable',
		caption : '设备状态及解决信息',
		colModel : [ {
			label : '',
			name : 'ma001',
			index : 'ma001',
			hidden : true,
			hidedlg : true
		}, {
			label : '气体类型',
			name : 'ma002',
			index : 'ma002',
			formatter : showcontent
		}, {
			label : '状态',
			name : 'ma003',
			index : 'ma003',
			formatter : showStatecontent
		}, {
			label : '标题',
			name : 'ma004',
			index : 'ma004'
		}, {
			label : '商城路径',
			name : 'ma008',
			index : 'ma008'
		}, {
			label : '环境类型',
			name : 'ma009',
			index : 'ma009',
			formatter : showEnv
		} ],
		formId : 'searchForm',
		winTitle : '设备状态及解决信息'
	};

	var actionOption = {
		init : true
	};

	$(function() {
		$$.bindButton('view', function() {
			var url = $$.getContextPath() + 'smart/s007!doShow';
			$$.getFrame('rightFrame').Tabs.openTab("titleid", "状态解决", url,
					"状态解决 ", true);
		}, 'operating');

		$$.bindButton('add', function() {
			var divId = 'showd', title = '状态解决', url = $$.getContextPath()
					+ 'smart/s007!doAdd', param = '', callback = function() {
			};
			$$.openDiv(divId, title, url, param, callback);
		});

		$$
				.bindButton(
						'edit',
						function() {
							var gridId = "#gridTable";
							if (!$$.checkSelRow(1))
								return;
							var ids = getSelectedRowID(gridId);
							var id = ids[0];
							var divId = 'showd', title = '状态解决', url = $$
									.getContextPath()
									+ 'smart/s007!doEdit', param = 's007po.ma001='
									+ id, callback = function() {
							};
							$$.openDiv(divId, title, url, param, callback);
						});
		$$
				.bindButton('delete',
						function() {
							var gridId = "gridTable";
							if ($$.checkSelRow(0))
								return;
							var ids = getSelectedRowID("#" + gridId);
							var url = $$.getContextPath()
									+ 'smart/s007!doDelete', param = 'ids='
									+ ids.join(), callback = function(data) {
								var buttons = {
									'确定' : function() {
										reloadGrid(gridId);
										$(this).dialog('close');
									}
								};
								showMsg(data.content, data.type, {
									title : data.title,
									buttons : buttons
								});
							};

							showMsg('确定要删除所选的记录吗？', MSG.INFO, {
								buttons : {
									'取消' : function() {
										$(this).dialog('close');
									},
									'确定' : function() {
										$.post(url, param, function(data) {
											callback(data);
										});

									}
								}
							});
						});
	});

	function closeshowd() {
		$("#showd").dialog('close');
	}

	function showcontent(celv, opts, obj) {

		 var str;
		switch (celv) {
		case 0:
			str = '温度';
			break;
		case 1:
			str = '湿度';
			break;
		case 2:
			str = 'CO2';
			break;
		case 3:
			str = 'PM2.5';
			break;
		case 4:
			str = 'VOC';
			break;
		case 5:
			str = 'c6h6';
			break;
		case 6:
			str = 'ch2o';
			break;
		case 7:
			str = '酒精';
			break;
		case 8:
			str = 'CO';
			break;
		case 9:
			str = 'CH4';
			break;
		}

 		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''
				+ obj[0] + '\')">' + str + '</a>'; 
	}
	function showEnv(celv, opts, obj) {

		 var str;
		/* switch (celv) {
		case 0:
			str = '其他环境';
			break;
		case 1:
			str = '新房';
			break;
		case 2:
			str = '新车';
			break;
		case 3:
			str = '新家具';
			break;
		case 4:
			str = '户外PM2.5';
			break;
		
		} */
		if (celv=='0'){
			str = '其他环境';
		}
		if (celv=='1'){
			str = '新房';
		}
		if (celv=='2'){
			str = '新车';		
				}
		if (celv=='3'){
			str = '新家具';
		}
		if (celv=='4'){
			str = '户外PM2.5';
		}

		return str; 
	}
	function showStatecontent(celv, opts, obj) {
		var str;
		switch (celv) {
		case 0:
			str = '低';
			break;
		case 1:
			str = '正常';
			break;
		case 2:
			str = '高';
			break;
		}
		return str;
	}

	function showContentById(id) {
		if (id == null || id == '') {
			showMsg("请选择一条记录!", 2);
		} else {
			$$.openDiv('showd', '状态解决信息查询', $$.getContextPath()
					+ 'smart/s007!doShow', {
				's007po.ma001' : id
			});
		}
	}

	function saveS001() {
		if ($('#s001Add').valid()) {
			var param = $('#s001Add').serialize();
			var url = $$.getContextPath() + 'smart/s007!doSaveAdd';
			$.post(url, param, function(data) {
				showMsg(data.content, data.type, {
					buttons : {
						'确定' : function() {
							closeshowd();
							$(this).dialog('close');
							reloadGrid("gridTable");
						}
					}
				});
			});
		}
		;
	}

	function modifyS001() {
		if ($('#s001Edit').valid()) {
			var param = $('#s001Edit').serialize();
			var url = $$.getContextPath() + 'smart/s007!doSaveEdit';
			$.post(url, param, function(data) {
				showMsg(data.content, data.type, {
					buttons : {
						'确定' : function() {
							closeshowd();
							$(this).dialog('close');
							reloadGrid("gridTable");
						}
					}
				});
			});
		}
		;
	}
</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
				<s:form action="s007" method="post" cssClass="formmargin"
					id="searchForm">
					<table border="0" cellspacing="8" cellpadding="0">
						<tr>
							<td align="right" style="width: 76px;">标题</td>
							<td style="width: 300px;"><s:textfield name="s007po.ma004"
									cssClass="input2" /></td>
							<td width="98"><s:submit value="" cssClass="search" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71"
			height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete" />
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>