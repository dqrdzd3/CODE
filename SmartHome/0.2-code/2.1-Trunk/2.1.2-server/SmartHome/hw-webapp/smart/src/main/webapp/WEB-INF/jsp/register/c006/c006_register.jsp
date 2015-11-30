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
<base href="<%=basePath%>" />
<title><s:text name="企业注册页面" />-<s:text
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
	padding-left: 673px;
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

#main2 td .zgdwtd {
	border: #CDCDCD solid 1px;
	border-right-color: #E8E8E8;
	border-bottom-color: #E8E8E8;
	width: 132px;
	height: 22px;
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
	 function isValidEntpCode(code, has){
		 var ws = [3, 7, 9, 10, 5, 8, 4, 2];
		 var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		 var reg = /^([0-9A-Z]){8}[0-9|X]$/;// /^[A-Za-z0-9]{8}-[A-Za-z0-9]{1}$/
		 if (!reg.test(code)) {
		 return false;
		 }
		 var sum = 0;
		 for (var i = 0; i < 8; i++) {
		    sum += str.indexOf(code.charAt(i)) * ws[i];
		 }
		 var c9 = 11 - (sum % 11);
		 c9 = c9 == 10 ? 'X' : c9
		 return c9 == code.charAt(8);
		 }
	 
	function doSubmitAddData() {
		
		
		/***企业名称***/
		var corpName = $('#ma002').val();
		/***组织机构代码***/
		var corpCode= $('#ma003').val();
		/***安全负责人姓名***/
		var personName = $('#ma012').val();
		/***安全负责人电话***/
		var personTel = $('#ma013').val();
		/**安全负责人手机***/
		var personMobile = $('#ma014').val();
		/***安全负责人邮箱***/
		var personMail = $('#ma015').val();
		/***用户名***/
		var userName = $('#ma010').val();
		/***密码***/
		var userPwd = $('#ma011').val();
		/***行业门类***/
		var hyml=$('#ml').val();
		/***经济类型***/
		var jjlx=$('#ma007').val();
		/***行业大类***/
		var hydl=$('#dl').val();
		/***隶属关系***/
		var lsgx=$('#lsgx').val();
		/***业务类型***/
		var qylx=$('#qylx').val();
		/***密码确定***/
		var userPwdConfirm = $('#reptLoginPwd').val();
		var zgdw=$('#zgdwid').val();
		var valueArray = new Array();
		valueArray[0] = corpName;
		valueArray[1] = corpCode;
		valueArray[2] = personName;
		valueArray[3] = personTel;
		valueArray[4] = personMobile;
		valueArray[5] = personMail;
		valueArray[6] = userName;
		valueArray[7] = userPwd;
		valueArray[8] = userPwdConfirm;
		valueArray[9] = hyml;
		valueArray[10] = jjlx;
		valueArray[11] = hydl;
		valueArray[12] = lsgx;
		valueArray[13] = qylx;
		valueArray[14] = zgdw;
		
		
		var idArray = new Array();
		idArray[0] = 'ma002';
		idArray[1] = 'ma003';
		idArray[2] = 'ma012';
		idArray[3] = 'ma013';
		idArray[4] = 'ma014';
		idArray[5] = 'ma015';
		idArray[6] = 'ma010';
		idArray[7] = 'ma011';
		idArray[8] = 'reptLoginPwd';
		idArray[9]='ml';
		idArray[10]='ma007';
		idArray[11]='dl';
		idArray[12]='lsgx';
		idArray[13]='qylx';
		idArray[14]='zgdw';
		
		var nameArray = new Array();
		nameArray[0] = '企业名称';
		nameArray[1] = '组织机构代码';
		nameArray[2] = '安全负责人姓名';
		nameArray[3] = '安全负责人电话';
		nameArray[4] = '安全负责人手机';
		nameArray[5] = '安全负责人邮箱';
		nameArray[6] = '用户名';
		nameArray[7] = '用户密码';
		nameArray[8] = '密码确定';
		nameArray[9] = '行业类型';
		nameArray[10] = '经济类型';
		nameArray[11] = '行业门类中大类';
		nameArray[12] = '隶属关系';
		nameArray[13] = '业务类型';
		nameArray[14] = '主管单位';
		
		var str_null = '';
		var str_value = '';
		//------------------------------------------------验证所有的必填项是不是非空
		for(i=0;i<valueArray.length;i++){
			//安全负责人电话不验证
			if(i == 3 || i==9 || i==10 || i == 5 || i == 11 || i == 14) continue;
			
			else{
				if(i==0){
					if(valueArray[i]=='限25个汉字以内')
						str_null = str_null + i + ',';
					else{
						if(isEmpty(valueArray[i])) str_null = str_null + i + ',';
						else str_value = str_value + i + ',';
					}
				}	
				else{
					if(isEmpty(valueArray[i])) str_null = str_null + i + ',';
					else str_value = str_value + i + ',';
				}
			}
		}

		//显示
		var nulls = str_null.split(',');
		var values = str_value.split(',');
		for(i=0;i<nulls.length;i++){
			var index = nulls[i];
			if(!isEmpty(index)){
				$('#'+idArray[index]+'span').html(nameArray[index]+'不能为空!');
				$('#'+idArray[index]+'span').show();
			}
		}
		
		for(i=0;i<values.length;i++){
			var index = values[i];
			if(!isEmpty(index)){
				$('#'+idArray[index]+'span').html('');
				$('#'+idArray[index]+'span').hide();
			}
		}

		//------------------------------------------------验证电话合法性
		var telIndex = 3;
		if(!isEmpty(valueArray[telIndex])){
			if(!checkTel(valueArray[telIndex])){
				$('#'+idArray[telIndex]+'span').html('请输入正确的电话!');
				$('#'+idArray[telIndex]+'span').show();
			}else{
				$('#'+idArray[telIndex]+'span').html('');
				$('#'+idArray[telIndex]+'span').hide();
			}
		}
		
		//------------------------------------------------验证手机格式，邮箱格式
		if(checkAll(valueArray)){
			var mobileIndex = 4;
			if(!checkMobile(valueArray[mobileIndex])){
				$('#'+idArray[mobileIndex]+'span').html('请输入正确的手机号!');
				$('#'+idArray[mobileIndex]+'span').show();
			}else{
				$('#'+idArray[mobileIndex]+'span').html('');
				$('#'+idArray[mobileIndex]+'span').hide();
			}
			
			var emailIndex = 5;
			if(!checkEmail(valueArray[emailIndex])){
				$('#'+idArray[emailIndex]+'span').html('请输入正确的邮箱!');
				$('#'+idArray[emailIndex]+'span').show();
			}else{
				$('#'+idArray[emailIndex]+'span').html('');
				$('#'+idArray[emailIndex]+'span').hide();
			}
			var usernameIndex = 6;
			if(!checkUsername(valueArray[usernameIndex])){
				$('#'+idArray[6]+'span').html('只能包括英文字母,数字和下划线!');
				$('#'+idArray[6]+'span').show();
			}else{
				$('#'+idArray[6]+'span').html('');
				$('#'+idArray[6]+'span').hide();
			}
			
			//************************************ajax,后台手机和邮箱校验
			//验证手机
			if(checkMobile(valueArray[mobileIndex])){
				var url = 'register/c006!checkOnlyMa014?ran='+Math.random();
				var param = {'ma014':valueArray[4]};
				$.post(url,param,function(result){
					if(result == 'true'){
						$('#'+idArray[4]+'span').html('');
						$('#'+idArray[4]+'span').hide();
					}else{
						$('#'+idArray[4]+'span').html('手机号已存在!');
						$('#'+idArray[4]+'span').show();
					}
				});
			}
			
			if(checkEmail(valueArray[emailIndex])){
				//验证邮箱
				var url = 'register/c006!checkOnlyMa015?ran='+Math.random();
				var param = {'ma015':valueArray[5]};
				$.post(url,param,function(result){
					if(result == 'true'){
						$('#'+idArray[5]+'span').html('');
						$('#'+idArray[5]+'span').hide();
					}else{
						$('#'+idArray[5]+'span').html('邮箱已存在!');
						$('#'+idArray[5]+'span').show();
					}
				});
			}
			//************************************

			//------------------------------------------------验证密码长度
			var pwdtag = false;
			var pwdconfirmtag = false;
			if(checkMinLength(valueArray[7],6) && checkMaxLength(valueArray[7],18)){
				$('#'+idArray[7]+'span').html('');
				$('#'+idArray[7]+'span').hide();
				pwdtag = true;
			}else{
				$('#'+idArray[7]+'span').html('密码的长度为6-18位!');
				$('#'+idArray[7]+'span').show();
				pwdtag = false;
			}
			
			if(checkMinLength(valueArray[8],6) && checkMaxLength(valueArray[8],18)){
				$('#'+idArray[8]+'span').html('');
				$('#'+idArray[8]+'span').hide();
				pwdconfirmtag = true;
			}else{
				$('#'+idArray[8]+'span').html('密码的长度为6-18位!');
				$('#'+idArray[8]+'span').show();
				pwdconfirmtag = false;
			}
			
			if(pwdtag && pwdconfirmtag){
				//------------------------------------------------验证密码是否一致
				if(!checkPwd(valueArray[7],valueArray[8])){
					$('#'+idArray[7]+'span').html('');
					$('#'+idArray[8]+'span').html('2次输入的密码不一致!');
					$('#'+idArray[8]+'span').show();
				}else{
					$('#'+idArray[7]+'span').html('');
					$('#'+idArray[8]+'span').html('');
					$('#'+idArray[8]+'span').hide();
				}
			}
			
			//------------------------------------------------ajax后台验证唯一性
		
			//验证企业名称
			var url = 'register/c006!checkOnlyMa002?ran='+Math.random();
			var param = {'ma002':valueArray[0]};
			$.post(url,param,function(result){
				if(result == 'true'){
					$('#'+idArray[0]+'span').html('');
					$('#'+idArray[0]+'span').hide();
				}else{
					$('#'+idArray[0]+'span').html('企业名称已存在!');
					$('#'+idArray[0]+'span').show();
				}
			});
// 			//验证组织机构代码是否符合格式
// 			 if(isValidEntpCode($("#ma003").val())) {
// 				 $('#'+idArray[1]+'span').html('');
// 					$('#'+idArray[1]+'span').hide();
// 			 }else{
// 					$('#'+idArray[1]+'span').html('组织机构代码不正确！组织机构代码由八位数字（或大写拉丁字母）本体代码和一位数字（或大写拉丁字母）校验码组成！例如：706785831');
// 					$('#'+idArray[1]+'span').show();
// 					return ;
// 				}
			 
			//验证组织机构代码是否重复
			var url = 'register/c006!checkOnlyMa003?ran='+Math.random();
			var param = {'ma003':valueArray[1]};
			$.post(url,param,function(result){
				if(result == 'true'){
					$('#'+idArray[1]+'span').html('');
					$('#'+idArray[1]+'span').hide();
				}else{
					$('#'+idArray[1]+'span').html('组织机构已存在!');
					$('#'+idArray[1]+'span').show();
				}
			});
			
			//如果所有的span都为空
			var spantag = true;
			for(i=0;i<idArray.length;i++){
				//alert(isEmpty($('#'+idArray[i]+'span').html()));
				if(!isEmpty($('#'+idArray[i]+'span').html())){
					spantag = false;
					break;
				}
			}
			
			//提交
			if(spantag){
				//验证用户名
				var url = 'register/c006!checkOnlyMa010?ran='+Math.random();
				var param = {'ma010':valueArray[6]};
				var _flag = false;
				$.post(url,param,function(result){
					if(result == 'true'){
						$('#'+idArray[6]+'span').html('');
						$('#'+idArray[6]+'span').hide();
						_flag = true;
					}else{
						$('#'+idArray[6]+'span').html('用户名已存在!');
						$('#'+idArray[6]+'span').show();
						_flag = false;
					}
				});
				
				if(!_flag){
					return;
				}
				
				var zhanghao = $('#ma010').val();
				var str = location.pathname.match("/[^/]*/?")[0];
				var url = str + 'register/c006!doSaveAdd';
				var param = $("#addfrom").serialize();
				showLoading();
				//如果插入成功，则再调用短信发送功能
				/*
				$.post(url, param, function(msg) {
					if (msg.type == 1) {
						hideLoading();
						alert("注册成功");
						window.location.href = str + 'register.html';
						$.post(url2,param,function(result){
							if(result=='true')
								alert("短信发送成功！");
						});

					}else{
						hideLoading();
					}
				});
				*/
				$.ajax({
				    type: "POST",
				    url: url,
				    data: param,
				    async : true,
				    beforeSend: function(){
				    	//showLoading();
				    },
				    success: function(msg){
				    	if (msg.type == 1) {
							//hideLoading();
							//alert("注册成功");
							window.location.href = '<%=basePath%>/register/c006!doSuccess?zhanghao='+zhanghao;
						}else{
							if (msg.type == 5) {
								//hideLoading();
								alert("注册失败");
							}else{}
								//hideLoading();
						}
				    }
				});
			
			}
		
		}
	}
	
	function checkMobile(str){
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|18[0-9]{1})+\d{8})$/;
		return mobile.test(str);
	}

	function checkEmail(str){
		var eamil = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
		return eamil.test(str);
	}
	function checkUsername(str){
		var username= /^[a-zA-Z0-9_]+$/;
		return username.test(str);
	}
	function isEmpty(str){
		if(str == null || str == ''){
			return true;
		}else
			return false;
	}
	
	function checkTel(str){
		var tel = /^(((\d{3,4})-)?)(\d{7,9})$/;
		return tel.test(str);
	}
	
	function checkNumWord(str){
		return /^[a-zA-Z0-9]+$/.test(str);
	}
	
	function checkMaxLength(str,num){
		if(str.length > num){
			return false;
		}else{
			return true;
		}
	}
	
	function checkMinLength(str,num){
		if(str.length >= num){
			return true;
		}else{
			return false;
		}
	}
	
	function checkPwd(str1,str2){
		if(str1 == str2) return true;
		else return false;
	}
	
	function checkAll(valueArray){
		var tag = true;
		for(i=0;i<valueArray.length;i++){
			if(i == 3 || i == 9 || i == 10 || i == 11 || i == 5 || i == 14) continue;
			if(isEmpty(valueArray[i])){
				tag = false;
				break;
			}
		}
		return tag;
	}
	function showLoading(){
		
		$("#Loading").show();
		$("#LoadingImage").show();
	}
	
	function hideLoading(){
		$("#LoadingImage").hide();
		$("#Loading").hide();
	}
	
	function checkUnique(url,param){
		var tag = null;
		$.post(url,param,function(result){
			return result;
		});
	}
	

	function mlsc(){
		jQuery.post(
				'register/c006!doGetHydlList',
			{'hymlID':jQuery('#ml').val()},
			function(option){
				jQuery('#dl').html(option);
			}
		);
		document.getElementById('dl').style.display='block';
		document.getElementById('zl').style.display='none';
		document.getElementById('xl').style.display='none';
	}


	function dlsc(){
		jQuery.post('register/c006!doGetHyzlList',
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
	
	/**
	 * 输入框获取焦点时清空默认内容，失去焦点时判断，若为空，则还原为默认内容
	 * */
	function clearInput(event,obj){
		var e = event; 
		var defVal = obj.defaultValue;
		
		if(e.type=='focus'){
			if($(obj).val()==defVal){
				$(obj).val("");
				$(obj).css('color','#666000');
				
			}
		}
		if(e.type=='blur'){
			if($(obj).val() == ''){
				$(obj).val(defVal);
				$(obj).css('color','#cacaca');
			}
		}

	}
	/**
	* 主管单位改变时动态获取该组织机构下的子组织
	**/
	function zgdw(obj){
		var value=obj.value;
		$(obj).nextAll('.zgdwtd').remove();
		var url= 'register/c006!getZgdw';
		var param={'pId':value};
		jQuery.post(url,param,function(data){
			var leng=data.length;
			if(leng>0)
			{
				var str='<select theme="simple" class="zgdwtd" onchange="zgdw(this)">' ;
				
				str+='<option value="">请选择主管单位</option>';
				
				for(var i=0;i<data.length;i++){
					
					 str+='<option value="'+data[i].ma001+'">'+data[i].ma003+'</option>';
				}
                 str+='</select>';
				jQuery('.zgdwtd:last').after(str);
				
			}
			
		});
		var id=jQuery('select.zgdwtd:last').val();
		if(id==""){
			id=jQuery('select.zgdwtd:last').prev().val();
		}
		$('#zgdwid').val(id);
	}
	
</script>


</head>

<body>
	<s:form id="addfrom" name="addfrom" action="c006!doSaveAdd"
		method="post">
		<s:hidden name="c006PO.ma001" id="ma001" />
		<s:hidden name="c006PO.ma021" id="ma021" />	
		<div id="top">

			<div id="topmain">
				<div id="topcontent">
					<marquee direction="left" height="22" width="450"
						onmouseout="this.start()" onmouseover="this.stop()"
						scrollamount="2" scrolldelay="1"></marquee>
				</div>
			</div>

		</div>
		<div id="maincontent2">
			<div id="main2">
            <table width="770" border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<th width="160" scope="row" nowrap="nowrap"><span>* </span>
							企业名称：</th>
						<td colspan="2"><s:textfield name="c006PO.ma002" id="ma002"
								cssStyle="color:#cacaca"
								maxLength="25" onfocus="clearInput(event,this)" onblur="clearInput(event,this)" value="限25个汉字以内" ></s:textfield>
								<span id="ma002span" style="display: none;"></span>
								</td>

					</tr>
					<tr>
						<th scope="row"><span>* </span> 组织机构代码：</th>
						<td colspan="2"><s:textfield name="c006PO.ma003" id="ma003"
								cssClass=""
								maxLength="10"></s:textfield>
								<span id="ma003span" style="display: none;"></span>
								</td>
					</tr>
			

					
					

					<tr>
						<th rowspan="1" scope="row"><span>* </span> 行业类型：</th>
						<td colspan="2">
							<s:select list="hymlList" id="ml"  listKey="ID" listValue="DISPLAY" headerKey="" headerValue="请选择行业类型" name="c006PO.hymlID" />
 						    <span id="mlspan" style="display: none;"></span>
 						</td>
 					</tr>
 					
 					<tr >
						<th scope="row"><span>* </span> 企业类型：</th>
						<td colspan="2">
								<s:select list="codeValueQYLX"
								listKey="value" listValue="name" headerKey="" headerValue="请选择企业类型" name="c006PO.qylx" id="qylx" ></s:select>
								<span id="qylxspan" style="display: none;"></span>
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
					<tr >
					<th  scope="row"><span>* </span> 上级主管单位：</th>
						<td  colspan="2">
							<s:select  list="zgdwList" cssClass="zgdwtd" id="sjzgdw" listKey="ma001" listValue="ma003" name="c006PO.zgdwid" />
 						</td>
					</tr>
					<tr>
						<th scope="row" nowrap="nowrap"><span>* </span> 安全负责人姓名：</th>
						<td colspan="2"><s:textfield name="c006PO.ma012" id="ma012"
								cssClass="" maxLength="10"></s:textfield>
								<span id="ma012span" style="display: none;"></span>
								</td>
					</tr>

					<tr>
						<th scope="row" nowrap="nowrap"><span>* </span> 安全负责人手机号：</th>
						<td colspan="2"><s:textfield name="c006PO.ma014" id="ma014" 
								cssClass="" maxLength="11"></s:textfield>
							<span id="ma014span" style="display: none;"></span></td>
					</tr>

					<tr>
						<th scope="row"><span>* </span> 用户名：</th>
						<td colspan="2"><s:textfield name="c006PO.ma010" id="ma010"
								cssClass=""
								maxLength="10"></s:textfield>
								<span id="ma010span" style="display: none;"></span>
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 用户密码：</th>
						<td colspan="2"><s:password name="c006PO.ma011" id="ma011"
								cssClass=""></s:password>
								
								<span id="ma011span" style="display: none;"></span>
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 确认密码：</th>
						<td colspan="2"><s:password name="reptLoginPwd"
								id="reptLoginPwd"
								cssClass=""></s:password>
								<span id="reptLoginPwdspan" style="display: none;"></span>
								</td>
					</tr>

					<tr>
						<th scope="row">&nbsp;</th>
						<td width="280" height="50" align="center" valign="bottom"><img
								src="images/register/button2.png" width="153" height="30" onclick="doSubmitAddData()" /></td>
						<td width="351">&nbsp;</td>
					</tr>
				</table>


			</div>
		</div>

	</s:form>

<div id="Loading" class="loading" >
	<iframe id="LoadingFrame" frameborder="0" width="100%" height="100%" style="position: absolute; "></iframe>
</div>
<div id="LoadingImage" class="" 
  style="position:absolute;  left:0px;  top:45%;  width:100%;  text-align:center;  display:none;  z-index:99999; ">
  <img alt="短信发送中..." style="" src='<s:url value="/images/register/pageloading.gif"></s:url>'>请等待...
</div>

</body>
</html>
