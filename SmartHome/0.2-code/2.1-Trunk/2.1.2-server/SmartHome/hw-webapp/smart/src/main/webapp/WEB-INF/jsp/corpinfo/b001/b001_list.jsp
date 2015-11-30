<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>

<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<title>企业基本信息-<s:text name="application.title" /></title>
<s:action
	name="include(mainCSS,jquery,utilJS,validatorJS,grid,commonJS,my97)"
	executeResult="true" />
<script type="text/javascript" src="<%=path%>/js/suggest.js"></script>
<link href="<%=path %>/css/suggest.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var gridOption = {
		id : 'b001table',
		caption : '企业基础信息',
		formId : 'searchForm',
		colModel : [ {
			label : 'index',
			name : 'MA001',
			index : 'MA001',
			hidden : true,
			hidedlg : true
		}, {
			label : '单位代码',
			name : 'MA003',
			index : 'MA003'
		}, {
			label : '法人单位名称',
			name : 'MA004',
			index : 'MA004'
		}, {
			label : '单位负责人姓名',
			name : 'MA026',
			index : 'MA026'
		}, {
			label : '定位',
			name : 'islabel2',
			index : 'islabel',
			hidden : true,
			hidedlg : true
		}, {
			label : '定位',
			name : 'islabel',
			index : 'islabel',
			formatter : progress
		} ],
		pk : "MA001",
		winTitle : "企业基础信息"
	};
	//celv,列值；opts：colmod；obj表示行对象
	function progress(celv, opts, obj) {
		if (celv == 'Y')
			return '<div ><img onclick="openShowMap(\''
					+ obj[0]
					+ '\')" src="<s:url value="/"/>images/location.png" /></div>';
		else
			return '<div ><img onclick="openEditMapOPT(\''
					+ obj[0]
					+ ','
					+ obj[1]
					+ ','
					+ obj[2]
					+ '\')" src="<s:url value="/"/>images/nolocation.png" /></div>';

	}

	//需要修改传递参数：企业ID，企业Name，危险源1的数值，危险源3的数值。目前还没有传递参数。corpid,corpname,wxy1num,wxy2num,wxy3num
	function openShowMap(corpid) {
		$$.getFrame('rightFrame').Tabs.openTab("mapgisshowid", "地图展示页面",
				"map/map_right!mapShow.action?layertype='corpLayer'&codeid='"
						+ corpid + "'", "地图模式 ");

	}
	//需要修改传递参数：企业ID，企业Name，危险源1的数值，危险源3的数值。目前还没有传递参数。corpid,corpname,wxy1num,wxy2num,wxy3num
	function openEditMapOPT(corpid) {
		showMsg("请先进行标注，再定位！",4,"");
		// $$.getFrame('rightFrame').Tabs.openTab("mapgiseditid","地图标注页面","frame/map_right!mapEdit.action?corpid="+corpid,"地图模式 "); 
	}
	/**
	 * 省份改变，获取地市信息
	 */
	function psc() {
		jQuery('#cts').html('<option>请选择</option>');
		jQuery.post($$.getContextPath()+'corpinfo/b001!getcityList', {
			provinceId : jQuery('#pts').val()
		}, function(option) {
			jQuery('#cityts').html(option);
		});
	}

	/**
	 * 地市改变，获取区县信息
	 */
	function citysc() {
		jQuery.post($$.getContextPath()+'corpinfo/b001!getcountyList', {
			cityId : jQuery('#cityts').val()
		}, function(option) {
			jQuery('#cts').html(option);
		});
	}

	var actionOption = {
		init : true,
		gridId : 'b001table',
		actionUrl : 'corpinfo/b001',
		winTitle : '企业信息',
		beforeFillForm : function(id, jsonPO, form) {
			var proId = jsonPO.MA007;
			var cityId = jsonPO.MA008;
			var areaId = jsonPO.MA009;

			jQuery.post($$.getContextPath()+'corpinfo/b001!getcityList', {
				provinceId : proId
			}, function(option) {
				jQuery('#cityts', $(form)).html(option);
				form['b001PO.MA008'].value = cityId;
			});

			jQuery.post($$.getContextPath()+'corpinfo/b001!getcountyList', {
				cityId : cityId
			}, function(option) {
				jQuery('#cts', $(form)).html(option);
				form['b001PO.MA009'].value = areaId;
			});
		},
		buttonInited : function(form) {
			$$.addButton('标注', function() {
				openEditHWMap();

			}, 'lisj');
		}

	};

function openEditHWMap(){
		var corpid = getSelectedRowID('#b001table');
		var corpname = jQuery('#b001table').jqGrid('getCell',corpid,'MA004');
		var codetype="";
		var islabel = jQuery('#b001table').jqGrid('getCell',corpid,'islabel2');
		 var i = getSelectedRowID('#b001table').length;
		 if(i==0){
			 showMsg("请选择待标注的记录!",4,"");
			 return;
		 }else if(i>1){
			 showMsg("只能选择一条记录!",4,"");
			 return;
		 }

		//如果已经标注，则不可以再次标注						
		 if(islabel=='Y'){
			 showMsg("已经标注过，不可再次标注!",4,"");
			 return;
		 }
		$$.getFrame('rightFrame').Tabs.openTab("mapgiseditid", "地图标注页面",
				"map/map_right!mapEdit.action?layertype='corpLayer'&&codeid='"
						+ corpid + "'&&codename='"
						+ encodeURI(encodeURI(corpname)) + "'"
						+"&&codetype='"+codetype+"'", "地图模式 ");
	}
	
	function ajaxGetName(id,str,divid){
		var ma051=$("#ma051").val();
		if(str == null || str == '') {
			closeDiv('suggest_input');
		}else{
			getData($$.getContextPath()+'corpinfo/b001!ajaxGetName?ram='+Math.random(),{'b001PO.MA069':str,'b001PO.MA051':ma051},id,divid);
		}
	}
</script>
</head>
<body>
	<div>

		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form action="b001!query" method="post" cssClass="formmargin"
						id="searchForm">
						<s:hidden name="b001PO.MA051" id="ma051"></s:hidden>
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 80px;">企业名称</td>
								<td colspan="1" style="width: 200px;" >
								
								<s:textfield id="corpName"
										name="b001PO.MA004" cssClass="input2" onkeyup="ajaxGetName(this.id,this.value,'suggest_input');"/>
										<div id="suggest_input" class="suggestiDiv"></div>
										
								</td>
								
								<td align="right" style="width: 90px;">单位负责人姓名</td>
								<td colspan="1" style="width: 200px;"><s:textfield
										name="b001PO.MA026" cssClass="input2" /></td>
								
							</tr>
							<tr>
								<td align="right" style="width: 80px;">开始日期</td>
								<td colspan="1" style="width: 200px;"><s:textfield
										name="b001PO.MA031" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd" width="50px"
											onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/>
											至
											<s:textfield
										name="b001PO.MA032" cssClass="dateISO Wdate" dateformat="yyyy-MM-dd" width="50px"
											onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/>
											</td>
								<td width="98" rowspan="2"><s:submit value=""
										cssClass="search" /></td>
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
		<hwsoft:operation code="${code}" param="view,add,edit,delete,editmap"/>
	</div>
	</div>
</body>
</html>