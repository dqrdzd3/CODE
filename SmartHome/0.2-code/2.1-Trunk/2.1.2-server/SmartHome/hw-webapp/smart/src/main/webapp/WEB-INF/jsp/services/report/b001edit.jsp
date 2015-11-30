<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript">
	$(function() {
		if (!$('#zl').val())
			jQuery('#zl').css("display", 'none');
		if (!$('#xl').val())
			jQuery('#xl').css("display", 'none');
	});
</script>
<script type="text/javascript">
	//更新企业信息（处于审核状态的不能修改）
	function subForm() {
		//获取iframe里的图片id，放如c004ids里
		//$('#c004ids').val($("#ids",document.frames('img').document).val());
		if (jQuery("#b001form").valid()) {

			jQuery.post('corpinfo/b001!doUpdate', jQuery("#b001form")
					.serialize(), function(msg) {
				showMsg(msg.content, msg.type);
			});
		}
	}
	/**
	 * 省份改变，获取地市信息
	 */
	function psc() {
		jQuery('#cts').html('<option>请选择</option>');
		jQuery.post('corpinfo/b001!getcityList', {
			provinceId : jQuery('#pts').val()
		}, function(option) {
			jQuery('#cityts').html(option);
		});
	}

	/**
	 * 地市改变，获取区县信息
	 */
	function citysc() {
		jQuery.post('corpinfo/b001!getcountyList', {
			cityId : jQuery('#cityts').val()
		}, function(option) {
			jQuery('#cts').html(option);
		});
	}
	/**
	 *获得行业类型
	 **/
	function mlsc() {
		jQuery('#dl').empty();
		jQuery('#zl').empty().append('<option value="">请选择行业中类</option>');
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post('corpinfo/b001!doGetHydlList', {
			'hymlID' : jQuery('#ml').val()
		}, function(option) {
			if (jQuery('#ml').val() == '') {
				document.getElementById('dl').style.display = 'none';
				document.getElementById('zl').style.display = 'none';
				document.getElementById('xl').style.display = 'none';
			} else {
				jQuery('#dl').html(option);
				document.getElementById('dl').style.display = 'inline';
			}
		});
	}
	function dlsc() {
		jQuery('#zl').empty().append('<option value="">请选择行业中类</option>');
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post('corpinfo/b001!doGetHyzlList', {
			'dlID' : jQuery('#dl').val()
		}, function(data) {
			//根据list列表的值，若没有子表则不显示
			var str = '<option value="">请选择行业中类</option>';
			var leng = data.length;
			if (leng > 0) {
				for ( var i = 0; i < data.length; i++) {
					str += '<option value="'+data[i].ID+'">' + data[i].DISPLAY
							+ '</option>';
				}
				jQuery('#zl').html(str);
				document.getElementById('zl').style.display = 'inline';
			} else {
				document.getElementById('zl').style.display = 'none';
				document.getElementById('xl').style.display = 'none';
			}
		});
	}
	function zlsc() {
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post('corpinfo/b001!doGetHyxlList', {
			'zlID' : jQuery('#zl').val()
		}, function(data) {
			//根据list列表的值，若没有子表则不显示
			var str = '<option value="">请选择行业小类</option>';
			var leng = data.length;
			if (leng > 0) {
				for ( var i = 0; i < data.length; i++) {
					str += '<option value="'+data[i].ID+'">' + data[i].DISPLAY
							+ '</option>';
				}
				jQuery('#xl').html(str);
				document.getElementById('xl').style.display = 'inline';
			} else {
				document.getElementById('xl').style.display = 'none';
			}
		});
	}
	$(function() {

		$('#MA004').bind('focus', function() {

			$(this).blur();
		});
		$('#MA003').bind('focus', function() {

			$(this).blur();
		});
	});

	//Gis标注
	function openEditCorpMap() {
		var corpid = jQuery('#ma001').val();//获得标注的企业ID
		var corpname = jQuery('#MA004').val();//获得标注的企业名称
		var codetype = "CORP";
		var codelevel = "";
		$$.getFrame('rightFrame').Tabs.openTab("mapgiseditid", "地图标注页面",
				"map/map_right!mapEdit.action?layertype='corpLayer'&&codeid='"
						+ corpid + "'&&codename='"
						+ encodeURI(encodeURI(corpname)) + "'" + "&&codetype='"
						+ codetype + "'&&codelevel='" + codelevel + "'",
				"地图模式 ");
	}
	function openShowCorpMap() {
		var corpid = jQuery('#ma001').val();//获得标注的企业ID
		$$.getFrame('rightFrame').Tabs.openTabMap("mapgisshowid", "地图展示页面",
				"map/map_right!mapDingwei.action?layertype='corpLayer'&codeid='"
						+ corpid + "'", null, true);

	}
</script>
<style type="text/css">
<!--
body,html {
	overflow-x: hidden;
	overflow-y: auto;
}

.tablecss1 {
	border-collapse: collapse;
	border: solid 1px #4BCDB9;
	font-size: 12px;
	color: #565656;
	width: 96%;
	margin: 0 auto;
	table-layout: fixed;
}

.tablecss1 td {
	border-left: 1px solid #4BCDB9;
	border-bottom: 1px solid #4BCDB9;
	padding-right: 8px;
	word-wrap: break-word;
}

.tablecss1 span {
	color: #FF0000;
}

.tablecss1 th {
	border: #89CAC5 solid 1px;
	padding: 1px;
	line-height: 24px;
	background-color: #ECFFFF;
	text-align: center;
	color: #108772;
	font-weight: normal;
}

.tablecss1 input,textarea,select {
	border: solid 1px #8BDED2;
	height: 19px;
	margin: 0;
	padding: 0;
	font-size: 12px;
	font-family: Verdana, Geneva, sans-serif;
	line-height: 19px;
	color: #666;
	width: 100%;
}

.tablecss1 textarea {
	height: 60px;
}

.rightjianxi {
	margin-right: 15px;
}

.radiostyle {
	border: #BFF0ED solid 0px !important;
	border-left-color: #35C0AA;
	border-top-color: #35C0AA;
	border-style: none;
	width: 16px !important;
	height: auto;
}
-->
</style>
</head>
<body>

	<s:form action="b001" method="post" id="b001form">
		<!-- 基础信息 -->
		<div style="padding: 20px;">
			<table border="0" cellspacing="0" cellpadding="4" class="tablecss1"
				style="width: 97%" align="center">
				<tbody>
					<s:hidden type="hidden" name="b001PO.MA001" id="ma001" />
					<s:hidden name="b001PO.MA002" />
					<s:hidden name="b001PO.MA028" />
					<s:hidden name="b001PO.MA038" id="state" />
					<s:hidden name="b001PO.MA039" />
					<s:hidden name="b001PO.MA011" />
					<s:hidden name="b001PO.MA071" />

					<tr>
					<th style="width: 140px;"><span> *</span> 企业名称</th>
					<td><s:textfield name="b001PO.MA004" id="MA004"
							cssClass="{required:true,maxlength:100,minlength:0}"
							cssStyle="border:none" readonly="true"></s:textfield></td>
					<th><span> * </span>组织机构代码</th>
					<td><s:textfield name="b001PO.MA003" id="MA003"
							cssClass="required" cssStyle="border:none" readonly="true"></s:textfield></td>
					<th><span> *</span> 成立时间</th>
					<td><s:textfield name="b001PO.MA012" id="MA012"
							cssClass="dateISO Wdate" dateformat="yyyy-MM-dd"
							onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})">
							<s:param name="value">
								<s:date name="b001PO.MA012" format="yyyy-MM-dd" />
							</s:param>
						</s:textfield></td>
					</tr>
					<tr>
						<th><span> *</span> 企业类型</th>
						<td colspan="2"><s:select list="codeValueQYLX"
								name="b001PO.MA051" listKey="value" listValue="name"
								headerKey="" headerValue="请选择" ength="25" cssClass='required'></s:select></td>
						<th style="width: 140px;"><span> *</span> 法定代表人</th>
						<td><s:textfield name="b001PO.MA047" id="MA047"
								cssClass="required {maxlength:10,minlength:0}"></s:textfield>
						<th><span>*</span>所在地区</th>
						<td colspan="3"><s:select list="provinceList" id="pts"
								onchange="psc()" listKey="code" listValue="desc" headerKey=""
								headerValue="请选择" name="b001PO.MA007" cssClass='required'
								cssStyle="width:100px;"></s:select> <s:select list="cityl"
								id="cityts" onchange="citysc()" listKey="code" listValue="desc"
								headerKey="" headerValue="请选择" name="b001PO.MA008"
								cssClass='required' cssStyle="width:100px;"></s:select> <s:select
								list="areal" id="cts" listKey="code" listValue="desc"
								headerKey="" headerValue="请选择" name="b001PO.MA009"
								cssClass='required' cssStyle="width:100px;"></s:select></td>
					</tr>
					<tr>

						<th style="width: 150px;">职工总数(人)</th>
						<td><s:textfield name="b001PO.MA014" id="MA014"
								cssClass='{digits:true,maxlength:50}'></s:textfield></td>
						<th style="width: 150px;">固定资产总值(万元)</th>
						<td><s:textfield name="b001PO.MA015" id="MA015"
								cssClass='{number:true,maxlength:17,decimal:true,min:0}'></s:textfield></td>
						<th>单位注册地址</th>
						<td><s:textfield name="b001PO.MA045" id="MA045"
								cssClass="{maxlength:100,minlength:0}"></s:textfield></td>

					</tr>
					<tr>
						<th>主要产品<br> <span>(100字以内)</span></th>
						<td colspan="5"><s:textarea name="b001PO.MA018" id="MA018"
								cols="45" rows="5" maxlength="100"
								cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="padding-bottom: 10px; text-align: center;">
			<img onclick="subForm()" src="images/update.png" width="88"
				height="29" />
		</div>
	</s:form>
</body>
</html>
