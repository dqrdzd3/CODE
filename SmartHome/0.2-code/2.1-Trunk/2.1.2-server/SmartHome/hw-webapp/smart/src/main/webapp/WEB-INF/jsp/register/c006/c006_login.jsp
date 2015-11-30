<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
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
<base href="<%=basePath%>" />
<title><s:text name="企业注册修改页面" />-<s:text
		name="application.title" /></title>

<script type="text/javascript" src="res/jquery-1.7.2.min.js"></script>



<style type="text/css">
<!--
body,html {
	background: #E9E9E9;
}

img {
	border: none;
}

#top {
	height: 69px;
	background: #027B62;
}

#topmain {
	margin: 0 auto;
	padding: 0px;
	position: relative;
	height: 69px;
	background: url(images/register/topbannner.jpg) center top no-repeat;
}

#topcontent {
	width: 450px;
	margin: 0 auto;
	text-align: right;
	color: #05D3BA;
	line-height: 22px;
	padding-left: 553px;
}

#maincontent2 {
	background: url(images/register/maincontentbg.jpg) left top repeat-x
		#EAEAEA;
}

#main2 {
	width: 970px;
	margin: 0 auto;
	padding: 0px;
	position: relative;
	height: 567px;
	background: url(images/register/mainbg2.jpg) center top no-repeat;
	padding-top: 30px;
	font-size: 14px;
	color: #565656;
	padding-left: 33px;
}

#main2 th {
	width: 140px;
	font-weight: normal;
	text-align: right;
	height: 40px;
	line-height: 40px;
}

#main2  span {
	color: #F00;
}

#main2 td input {
	border: #CDCDCD solid 1px;
	border-right-color: #E8E8E8;
	border-bottom-color: #E8E8E8;
	width: 274px;
	height: 19px;
	padding-left: 6px;
	font-size: 12px;
	color: #666;
	margin-right: 6px;
}

#main2 td select {
	border: #CDCDCD solid 1px;
	border-right-color: #E8E8E8;
	border-bottom-color: #E8E8E8;
	width: 282px;
	height: 22px;
	padding-left: 6px;
	font-size: 12px;
	color: #666;
	margin-right: 6px;
}

#main2 td textarea {
	border: #CDCDCD solid 1px;
	border-right-color: #E8E8E8;
	border-bottom-color: #E8E8E8;
	width: 274px;
	height: 100px;
	padding-left: 6px;
	font-size: 12px;
	color: #666;
}

#mainbutton2 {
	position: absolute;
	width: 124px;
	height: 42px;
	top: 321px;
	right: 56px;
}

.loading{ filter:Alpha(Opacity=30);
/* IE */
-moz-opacity:0.4;
/* Moz + FF */
opacity: 0.4;position:absolute;  left:0px;  top:0px;  width:100%;  height:100%;	background:#f0f8ff;	display:none;  z-index:99998;} 


-->
</style>
<script type="text/javascript">
	$.ajaxSetup({
		async : false
	});
	function doSubmitAddData() {
		/**注册企业ＩＤ***/
		var corpID = $('#ma001').val();
		/***企业名称***/
		var corpName = $('#ma002').val();
		/***组织机构代码***/
		var corpCode = $('#ma003').val();
		/***安全负责人姓名***/
		var personName = $('#ma012').val();
		/***安全负责人电话***/
		var personTel = $('#ma013').val();
		/**安全负责人手机***/
		var personMobile = $('#ma014').val();
		/***安全负责人邮箱***/
		var personMail = $('#ma015').val();
		/***行业门类***/
		var hyml=$('#ml').val();
		/***经济类型***/
		var jjlx=$('#ma007').val();
		/***行业大类***/
		var hydl=$('#dl').val();
		/***隶属关系***/
		var lsgx=$('#lsgx').val();
		/***企业类型***/
		var qylx=$('#qylx').val();

		var valueArray = new Array();
		valueArray[0] = corpName;
		valueArray[1] = corpCode;
		valueArray[2] = personName;
		valueArray[3] = personTel;
		valueArray[4] = personMobile;
		valueArray[5] = personMail;
		valueArray[6] = hyml;
		valueArray[7] = jjlx;
		valueArray[8] = hydl;
		
		valueArray[9] = corpID;//这个变量是为了传递参数使用：企业ＩＤ
		
		valueArray[10] = lsgx;
		valueArray[11] = qylx;
		
		var idArray = new Array();
		idArray[0] = 'ma002';
		idArray[1] = 'ma003';
		idArray[2] = 'ma012';
		idArray[3] = 'ma013';
		idArray[4] = 'ma014';
		idArray[5] = 'ma015';
		idArray[6]='ml';
		idArray[7]='ma007';
		idArray[8]='dl';
		idArray[10]='lsgx';
		idArray[11]='qylx';

		var nameArray = new Array();
		nameArray[0] = '企业名称';
		nameArray[1] = '组织机构代码';
		nameArray[2] = '安全负责人姓名';
		nameArray[3] = '安全负责人电话';
		nameArray[4] = '安全负责人手机';
		nameArray[5] = '安全负责人邮箱';
		nameArray[6] = '行业类型中门类';
		nameArray[7] = '经济类型';
		nameArray[8] = '行业门类中大类';
		nameArray[10] = '隶属关系';
		nameArray[11] = '企业类型';

		var str_null = '';
		var str_value = '';
		//------------------------------------------------验证所有的必填项是不是非空
		for (i = 0; i < valueArray.length; i++) {
			//安全负责人电话不验证
			if (i == 3)
				continue;

			else {
				if (isEmpty(valueArray[i]))
					str_null = str_null + i + ',';
				else
					str_value = str_value + i + ',';
			}
		}

		//显示
		var nulls = str_null.split(',');
		var values = str_value.split(',');
		for (i = 0; i < nulls.length; i++) {
			var index = nulls[i];
			if (!isEmpty(index)) {
				$('#' + idArray[index] + 'span').html(
						nameArray[index] + '不能为空!');
				$('#' + idArray[index] + 'span').show();
			}
		}

		for (i = 0; i < values.length; i++) {
			var index = values[i];
			if (!isEmpty(index)) {
				$('#' + idArray[index] + 'span').html('');
				$('#' + idArray[index] + 'span').hide();
			}
		}

		//------------------------------------------------验证电话合法性
		var telIndex = 3;
		if (!isEmpty(valueArray[telIndex])) {
			if (!checkTel(valueArray[telIndex])) {
				$('#' + idArray[telIndex] + 'span').html('请输入正确的电话!');
				$('#' + idArray[telIndex] + 'span').show();
			} else {
				$('#' + idArray[telIndex] + 'span').html('');
				$('#' + idArray[telIndex] + 'span').hide();
			}
		}

		//------------------------------------------------验证手机格式，邮箱格式
		if (checkAll(valueArray)) {
			var mobileIndex = 4;
			if (!checkMobile(valueArray[mobileIndex])) {
				$('#' + idArray[mobileIndex] + 'span').html('请输入正确的手机号!');
				$('#' + idArray[mobileIndex] + 'span').show();
			} else {
				$('#' + idArray[mobileIndex] + 'span').html('');
				$('#' + idArray[mobileIndex] + 'span').hide();
			}

			var emailIndex = 5;
			if (!checkEmail(valueArray[emailIndex])) {
				$('#' + idArray[emailIndex] + 'span').html('请输入正确的邮箱!');
				$('#' + idArray[emailIndex] + 'span').show();
			} else {
				$('#' + idArray[emailIndex] + 'span').html('');
				$('#' + idArray[emailIndex] + 'span').hide();
			}

			//************************************ajax,后台手机和邮箱校验
			//验证手机
			if (checkMobile(valueArray[mobileIndex])) {
				var url = 'register/c006!checkOnlyMa014?ran=' + Math.random();
				var param = {
					'ma014' : valueArray[4],
					'ma001' : valueArray[9]
				};
				$.post(url, param, function(result) {
					if (result == 'true') {
						$('#' + idArray[4] + 'span').html('');
						$('#' + idArray[4] + 'span').hide();
					} else {
						$('#' + idArray[4] + 'span').html('手机号已存在!');
						$('#' + idArray[4] + 'span').show();
					}
				});
			}

			if (checkEmail(valueArray[emailIndex])) {
				//验证邮箱
				var url = 'register/c006!checkOnlyMa015?ran=' + Math.random();
				var param = {
					'ma015' : valueArray[5],
					'ma001' : valueArray[9]
				};
				$.post(url, param, function(result) {
					if (result == 'true') {
						$('#' + idArray[5] + 'span').html('');
						$('#' + idArray[5] + 'span').hide();
					} else {
						$('#' + idArray[5] + 'span').html('邮箱已存在!');
						$('#' + idArray[5] + 'span').show();
					}
				});
			}
			//************************************

			//------------------------------------------------ajax后台验证唯一性

			//验证企业名称
			var url = 'register/c006!checkOnlyMa002?ran=' + Math.random();
			var param = {
				'ma002' : valueArray[0],
				'ma001' : valueArray[9]
			};
			$.post(url, param, function(result) {
				if (result == 'true') {
					$('#' + idArray[0] + 'span').html('');
					$('#' + idArray[0] + 'span').hide();
				} else {
					$('#' + idArray[0] + 'span').html('企业名称已存在!');
					$('#' + idArray[0] + 'span').show();
				}
			});

			//验证组织机构代码
			var url = 'register/c006!checkOnlyMa003?ran=' + Math.random();
			var param = {
				'ma003' : valueArray[1],
				'ma001' : valueArray[9]
			};
			$.post(url, param, function(result) {
				if (result == 'true') {
					$('#' + idArray[1] + 'span').html('');
					$('#' + idArray[1] + 'span').hide();
				} else {
					$('#' + idArray[1] + 'span').html('组织机构已存在!');
					$('#' + idArray[1] + 'span').show();
				}
			});


			//如果所有的span都为空
			var spantag = true;
			for (i = 0; i < idArray.length; i++) {
				//alert(isEmpty($('#'+idArray[i]+'span').html()));
				if (!isEmpty($('#' + idArray[i] + 'span').html())) {
					spantag = false;
					break;
				}
			}

			//提交
			if (spantag) {
				var str = location.pathname.match("/[^/]*/?")[0];
				var url = str + 'register/c006!doSaveEdit';
				var param = $("#editfrom").serialize();
				showLoading();
				
				$.ajax({
					type : "POST",
					url : url,
					data : param,
					async : true,
					beforeSend : function() {
						showLoading();
					},
					success : function(msg) {
				    	if (msg.type == 1) {
							hideLoading();
							alert("修改成功");
							window.location.href = str + 'register.jsp';
						}else{
							if (msg.type == 5) {
								hideLoading();
								alert("修改失败");
							}else
								hideLoading();
						}
					}
				});

			}

		}
	}

	function checkMobile(str) {
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|18[0-9]{1})+\d{8})$/;
		return mobile.test(str);
	}

	function checkEmail(str) {
		var eamil = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
		return eamil.test(str);
	}

	function isEmpty(str) {
		if (str == null || str == '') {
			return true;
		} else
			return false;
	}

	function checkTel(str) {
		var tel = /^(((\d{3,4})-)?)(\d{7,9})$/;
		return tel.test(str);
	}

	function checkNumWord(str) {
		return /^[a-zA-Z0-9]+$/.test(str);
	}

	function checkMaxLength(str, num) {
		if (str.length > num) {
			return false;
		} else {
			return true;
		}
	}

	function checkMinLength(str, num) {
		if (str.length >= num) {
			return true;
		} else {
			return false;
		}
	}

	function checkPwd(str1, str2) {
		if (str1 == str2)
			return true;
		else
			return false;
	}

	function checkAll(valueArray) {
		var tag = true;
		for (i = 0; i < valueArray.length; i++) {
			if (i == 3)
				continue;
			if (isEmpty(valueArray[i])) {
				tag = false;
				break;
			}
		}
		return tag;
	}
	function showLoading() {

		$("#Loading").show();
		$("#LoadingImage").show();
	}

	function hideLoading() {
		$("#LoadingImage").hide();
		$("#Loading").hide();
	}

	function checkUnique(url, param) {
		var tag = null;
		$.post(url, param, function(result) {
			return result;
		});
	}

	function mlsc(){
		jQuery.post(
			'register/c006!doGetHydlList',
			{'hymlID':jQuery('#ml').val()},
			function(option){
				jQuery('#dl').html(option);
				document.getElementById('dl').style.display='block';
				document.getElementById('zl').style.display='none';
				jQuery('#zl').html('<option value="">请选择行业中类</option>');
				document.getElementById('xl').style.display='none';
				jQuery('#xl').html('<option value="">请选择行业小类</option>');
			}
		);
	}

	function dlsc(){
		jQuery.post(
			'register/c006!doGetHyzlList',
			{'dlID':jQuery('#dl').val()},
			function(data){
				//根据list列表的值，若没有子表则不显示
				var str='<option value="">请选择行业中类</option>';
				var leng=data.length;
				if(leng>0)
				{
					for(var i=0;i<data.length;i++){
						str+='<option value="'+data[i].ID+'">'+data[i].DISPLAY+'</option>';
					}
					jQuery('#zl').html(str);
					document.getElementById('zl').style.display='block';
					document.getElementById('xl').style.display='none';
					jQuery('#xl').html('<option value="">请选择行业小类</option>');
				}

			}
		);

	}	

	function zlsc(){
		jQuery.post(
			'register/c006!doGetHyxlList',
			{'zlID':jQuery('#zl').val()},
			function(data){
				//根据list列表的值，若没有子表则不显示
				var str='<option value="">请选择行业小类</option>';
				var leng=data.length;
				if(leng>0)
				{
					for(var i=0;i<data.length;i++){
						str+='<option value="'+data[i].ID+'">'+data[i].DISPLAY+'</option>';
					}
					jQuery('#xl').html(str);
					document.getElementById('xl').style.display='block';
					
				}
			}
		);

	}		
	//根据行业中类和小类是否有值，而显示对应的下拉列表框；默认的时候是none，当有值的时候，在form加载的时候再进行显示。
	function showHY()
	{
		if(jQuery('#zl').val()!=""){
			document.getElementById('zl').style.display='block';
		}
		if(jQuery('#xl').val()!=""){
			document.getElementById('xl').style.display='block';
		}
	}
</script>


</head>

<body onload="showHY();">
	<div id="top">
		<div id="topmain">
			<div id="topcontent">
				<marquee direction="left" height="22" width="450"
					onmouseout="this.start()" onmouseover="this.stop()"
					scrollamount="2" scrolldelay="1">最新消息：请于2013年1月1号至2013年1月31号注册企业管理员</marquee>
			</div>
		</div>
	</div>
	<div id="maincontent2">
		<div id="main2">
			<div id="loginDiv">
			   

			</div>
	
			<div id="editDiv" >
				<div class="editcontent"></div>
				<s:form id="editfrom" name="editfrom" >
					<s:hidden name="c006PO.ma001" id="ma001" />
					<s:hidden name="c006PO.ma020" id="ma020" />
					<s:hidden name="c006PO.ma021" id="ma021" />
					<s:hidden name="c006PO.ma010" id="ma010" />
					<s:hidden name="c006PO.ma011" id="ma011" />
					<s:hidden name="c006PO.ma009" id="ma009" />
					<s:hidden name="c006PO.oldMa003" id="oldMa003" />
					<table width="770" border="0" align="left" cellpadding="0"
						cellspacing="0">
						<tr>
							<th width="160" scope="row" nowrap="nowrap"><span>* </span>
								企业名称：</th>
							<td colspan="2"><s:textfield name="c006PO.ma002" id="ma002"
									cssClass="" maxLength="30"></s:textfield> <span id="ma002span"
								style="display: none;"></span></td>

						</tr>
						<tr>
							<th scope="row"><span>* </span> 组织机构代码：</th>
							<td colspan="2"><s:textfield name="c006PO.ma003" id="ma003"
									cssClass="" maxLength="10"></s:textfield> <span id="ma003span"
								style="display: none;"></span></td>
						</tr>

						<tr>
						<th scope="row"><span>* </span> 所在地区：</th>
						<td ><s:textfield value="河南省-郑州市-高新区" disabled="true"></s:textfield>
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 经济类型：</th>
						<td colspan="2">
								<s:select list="codeValueJJLX"
								listKey="value" listValue="display" headerKey="" headerValue="请选择经济类型" name="c006PO.ma007" id="ma007" ></s:select>
								<span id="ma007span" style="display: none;"></span>
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 隶属关系：</th>
						<td colspan="2">
								<s:select list="codeValueLSGX"
								listKey="value" listValue="name" headerKey="" headerValue="请选择隶属关系" name="c006PO.lsgx" id="lsgx" ></s:select>
								<span id="lsgxspan" style="display: none;"></span>
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 企业类型：</th>
						<td colspan="2">
								<s:select list="codeValueQYLX"
								listKey="value" listValue="name" headerKey="" headerValue="请选择企业类型" name="c006PO.qylx" id="qylx" ></s:select>
								<span id="qylxspan" style="display: none;"></span>
								</td>
					</tr>
						<tr>
							<th rowspan="4" scope="row"><span>* </span> 行业类型：</th>
							<td colspan="2">
								<s:select list="hymlList" id="ml" onchange="mlsc()" listKey="ID" listValue="DISPLAY" headerKey="" headerValue="请选择行业门类" name="c006PO.hymlID" />
								<span id="mlspan" style="display: none;"></span>
	 						</td>
	 					</tr>
						<tr>
					       <td colspan="2" valign="top">
	  						    <s:select list="dlList" id="dl" onchange="dlsc()" listKey="ID" listValue="DISPLAY" headerKey="" headerValue="请选择行业大类" name="c006PO.hydlID" />    
	   					  		<span id="dlspan" style="display: none;"></span>
	   					   </td>
	   					</tr>
						<tr>
					      <td colspan="2" valign="top">
					        <s:select list="zlList" id="zl" onchange="zlsc()" listKey="ID" listValue="DISPLAY" headerKey="" headerValue="请选择行业中类" name="c006PO.hyzlID" style="display: none;"/>
				          </td>
	   					</tr>
	   					<tr>
					      <td colspan="2" valign="top">
				            <s:select list="xlList" id="xl" listKey="ID" listValue="DISPLAY" headerKey="" headerValue="请选择行业小类" name="c006PO.hyxlID" style="display: none;"/></td>
	   					</tr>
						<tr>
							<th scope="row" nowrap="nowrap"><span>* </span> 安全负责人姓名：</th>
							<td colspan="2"><s:textfield name="c006PO.ma012" id="ma012"
									cssClass=""></s:textfield> <span id="ma012span"
								style="display: none;"></span></td>
						</tr>
						<tr>
							<th scope="row" nowrap="nowrap">安全负责人电话：</th>
							<td colspan="2"><s:textfield name="c006PO.ma013" id="ma013"
									cssClass=""></s:textfield> <span id="ma013span"
								style="display: none;"></span></td>
						</tr>
						<tr>
							<th scope="row" nowrap="nowrap"><span>* </span> 安全负责人手机号：</th>
							<td colspan="2"><s:textfield name="c006PO.ma014" id="ma014"
									cssClass=""></s:textfield> <span id="ma014span"
								style="display: none;"></span></td>
						</tr>
						<tr>
							<th scope="row" nowrap="nowrap"><span>* </span> 安全负责人邮箱：</th>
							<td colspan="2"><s:textfield name="c006PO.ma015" id="ma015"
									cssClass=""></s:textfield> <span id="ma015span"
								style="display: none;"></span></td>
						</tr>
						<!-- 
						<tr>
							<th scope="row"><span>* </span> 用户名：</th>
							<td colspan="2"><s:textfield name="c006PO.ma010" id="ma010"
									cssClass="" maxLength="20"></s:textfield> <span id="ma010span"
								style="display: none;"></span></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 用户密码：</th>
							<td colspan="2"><s:password name="c006PO.ma011" id="ma011"
									cssClass=""></s:password> <span id="ma011span"
								style="display: none;"></span></td>
						</tr>
						<tr>
							<th scope="row"><span>* </span> 确认密码：</th>
							<td colspan="2"><s:password name="reptLoginPwd"
									id="reptLoginPwd" cssClass=""></s:password> <span
								id="reptLoginPwdspan" style="display: none;"></span></td>
						</tr>
 						 -->
						<tr>
							<th scope="row">&nbsp;</th>
							<td width="280" height="50" align="center" valign="bottom"><img
								src="images/register/button3.png" width="153" height="30"
								onclick="doSubmitAddData()" /></td>
							<td width="351">&nbsp;</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>

	</div>
	</div>
	</div>
</body>
</html>
