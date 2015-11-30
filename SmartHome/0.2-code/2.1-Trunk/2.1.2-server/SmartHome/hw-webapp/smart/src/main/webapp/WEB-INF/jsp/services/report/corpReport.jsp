<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt"  prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业信息查看</title>

<s:action name="include(mainCSS,jquery,commonJS)" executeResult="true" />

<style type="text/css">
.mr {font-size:14px; font-weight:normal;color:#003399;text-decoration:none;}
.mr:hover{text-decoration:underline;}
.nmCol {font-size:12px; font-weight:normal;color:#003399;text-decoration:none;}
.nmCol:hover{text-decoration:underline}
.zzsajj_table th {text-align:center !important;}
</style>

 <script type="text/javascript" language="javascript" >
  function display(){
document.getElementById("box").style.display="block"; 
  }
  function disappear(){
document.getElementById("box").style.display="none"; 
  }
 
 function gun(id) {
	  if (id == 'a1') 
		  $('#windowsmain1').animate({scrollTop: 0}, 'fast');
	  else   
	  	$('#windowsmain1').animate({scrollTop: '+=' + ($('#' + id).offset().top - 11)}, 'fast');
 }
 
 $(function(){
	 // 目录的显示与隐藏
// 	 $('.div_mod_ml').hover(function(){
// 	 	$(this).animate({'right':0}, 'fast');
// 	 }, function(){
// 	 	$(this).animate({'right':-82}, 'slow');
// 	 });
	 
	 $('#dirPic').toggle(
				function(){
		 			$('.div_mod_ml').animate({'right':-82}, 'slow');
		 		},
				function(){
		 			$('.div_mod_ml').animate({'right':0}, 'fast');
		 		}
			);
				
				
	 
 });
 
 // 全选
 function chkAll(obj) {
	 $('#chkListTD :checkbox').attr('checked', $(obj).attr('checked') ? true : false);
 }
 
 
//返回顶部
function goTop() {
	$('#windowsmain1').animate({scrollTop: 0}, 500);		
}

function viewPic(id) {
	$$.getFrame('rightFrame').Tabs.openTab("tpyl", "图片预览",$$.getContextPath()+'upload/doViewNew.action?id='+id, "图片预览");
}
	
var corpID = '${corpID}';
var tabID = 'tabid_qyxgxxck';
var govType = '<s:property value="#parameters.govType[0]"/>';
function moreZZJG() {
	$$.getTabsBar().openTab(tabID, '组织机构列表', $$.getContextPath() + 'corpinfo/b002?ORGAN_UUID=' + corpID + '&govType=' + govType, '', true);
}
function moreGZZD() {
	$$.getTabsBar().openTab(tabID, '规章制度列表', $$.getContextPath() + 'knowledge/tregulation?ORGAN_UUID=' + corpID + '&govType=' + govType, '', true);
}
function moreJYPX() {
	$$.getTabsBar().openTab(tabID, '教育培训列表', $$.getContextPath() + 'education/e001?ORGAN_UUID=' + corpID + '&govType=' + govType, '', true);
}	
function moreJCJL() {
	$$.getTabsBar().openTab(tabID, '检查记录列表', $$.getContextPath() + 'safety/s121!doList?ORGAN_UUID=' + corpID + '&govType=' + govType, '', true);
}	
function moreYHPC() {
	$$.getTabsBar().openTab(tabID, '隐患排查列表', $$.getContextPath() + 'safety/s108?ORGAN_UUID=' + corpID + '&govType=' + govType, '', true);
}	
	
// 名称链接列
var CTX = $$.getContextPath();
function procBackBtn(sltr) {
	$(':input.back', sltr).click(function(){
		$('#viewDiv').dialog('close');
	});
}
function viewQYZZ(id) {
	$$.openDiv('viewDiv', '查看执照详情', CTX + 'corpinfo/b005!doView?b005PO.MA001='+id, '', function(data){
		$$.clearInput('#b005Form');
		procBackBtn('#b005Form');
	});
}
function viewZZJG(id) {
	$$.openDiv('viewDiv', '查看机构详情', CTX + 'corpinfo/b002!doView?b002PO.MA001='+id, '', function(data){
		$$.clearInput('#b002Form');
		procBackBtn('#b002Form');
	});
}
function viewGZZD(id) {
	$$.openDiv('viewDiv', '查看制度详情', CTX + 'knowledge/tregulation!doView?tregulationPO.MA001='+id, '', function(data){
		$$.clearInput('#regulation');
		procBackBtn('#regulation');
	});
}
function viewJYPX(id) {
	$$.openDiv('viewDiv', '查看培训详情', CTX + 'education/e001!doView?e001PO.MA001='+id, '', function(data){
		$$.clearInput('#e001Form');
		procBackBtn('#e001Form');
	});
}
function viewJCJL(id) {
	$$.openDiv('viewDiv', '查看检查记录详情', CTX + 'safety/s121!doView?s121PO.ma001='+id, '', function(data){
		$$.clearInput('#s121Form');
		procBackBtn('#s121Form');
	});
}
function viewYHPC(id) {
	$$.getFrame('rightFrame').Tabs.openTab(id, "隐患治理流程",'safety/s108!doAddYH?id='+id, "隐患治理流程");
}

function repInfo() {
	if ($('#repForm :checkbox:checked').length == 0) {
		showMsg('请选择上报项！', MSG.INFO);
		return;
	}
	$.post(CTX + 'services/b10401!xianQuReport?corpID='+corpID, $('#repForm').serialize(), function(data){
		showMsg(data.content, data.type, {buttons:{
			'确定' : function() {
				location.reload();
			}
		}});
	});
}
 </script>

<script type="text/javascript">
	
		var Resize = function(options){
			this.defaults = {
					   //弹出层id
						id:'outerDiv'
				 };
			this.settings = {};//扩展后供最后使用属性
			
			$.extend(this.settings,this.defaults);

		};

		 Resize.prototype = {
			  resize:function(){

				  var _id = this.settings.id;	  			
				  var $divCont = $("#"+_id);
				  var divH = $divCont.height();
				  var divW = $divCont.width();
				  var topH = $divCont.find("#windowstop1").height() || 29;
				  var topW = $divCont.find("#windowstop1").width() || 762;
				  var bottomH = 4
				  var bottomW = $divCont.find("#windowsbottom1").width() || 762;
				  
				  var tableH=$divCont.find("#windowdiv1").height() || 400;

				  $divCont.find("#windowdiv11").height(divH-topH-bottomH-2);

				  if(divW < bottomW){
					  $divCont.width(bottomW);
				  }
				 
				  var tableH=$divCont.find("#windowsmain1").height() || 400;
				 
				  $divCont.find("#windowsmain1").height(divH-topH-bottomH-2);
				
				  if(divW < bottomW){
					  $divCont.width(bottomW);
				  }
			  }
		 };
		 
$(function(){
	
	var oResize = new Resize({id:'outerDiv'});
	oResize.resize();
			
	$(window).resize(function(){
		oResize.resize();
	});
});
         
</script>

</head>
<body>
<div id="outerDiv" class="waibuwindows">
<div id="windows1">
  <div id="windowstop1">
				<div id="windowstopleft1">
					<ul>
						<li>企业监管</li>

					</ul>
				</div>


			</div>
  <div id="windowsmain1" style="overflow:auto;">
    <div id="windowdiv1" >
    	<div id="windowdivmain1"  >
    	
    	
<!--     				<a id="a0"></a> -->
<!-- 					<table  cellspacing="0" cellpadding="0" id="zzsajj_table" -->
<!-- 						  class="zzsajj_table_mod" > -->
<!-- 						<tr> -->
<!-- 						<th class="title" style="width:30px;">序号</th> -->
<!-- 						<th  class="title" style="width:33%;">修改项</th> -->
<!-- 						<th  class="title" style="width:40%;">修改内容</th> -->
<!-- 						<th  class="title" style="width:25%;">上报时间</th> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 						<td rowspan="2" align="center" >1</td> -->
<!-- 						<td rowspan="2">企业信息-职工总数</td> -->
<!-- 						<td>改后：888</td> -->
<!-- 						<td>20123699</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 						  <td>改前：88888</td> -->
<!-- 					      <td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 					  <tr> -->
<!-- 						<td rowspan="2">2</td> -->
<!-- 						<td rowspan="2">企业信息-职工总数附件</td> -->
<!-- 						<td>改后：<a href="#">888</a></td> -->
<!-- 						<td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 						<tr> -->
<!-- 						  <td>改前：<a href="#">88888</a></td> -->
<!-- 					      <td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 					  <tr> -->
<!-- 						<td rowspan="2">3</td> -->
<!-- 						<td rowspan="2" class="shanchu">企业信息-职工总数附件</td> -->
<!-- 						<td class="shanchu">该信息被企业删除 </td> -->
<!-- 						<td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 						<tr> -->
<!-- 						  <td>改前：<a href="#">被删附件名称</a></td> -->
<!-- 					      <td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 					   <tr> -->
<!-- 						<td>4</td> -->
<!-- 						<td >日常检查记录</td> -->
<!-- 						<td >该信息为新增内容</br>检查名称：阿全减产</br>检查名称：阿全减产</br></td> -->
<!-- 						<td>20123699</td> -->
<!-- 					  </tr> -->
<!-- 						</table> -->




<!-- 基本信息 -->
<a id="a1"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;基本信息</th>
	</tr></table>	
	<div id="corpInfoDiv">
		<s:action name="b001!doViewCorp" namespace="/corpinfo" executeResult="true">
			<s:param name="b001PO.MA001" value="corpID"/>
			<s:param name="govType" value="#parameters.govType[0]"/>
		</s:action>
	</div>

				
<br/>


<!--  组织机构   -->	
<a id="a2"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;组织机构
  	　<s:if test="b002List.size >= 5">
  		<a href="javascript:;" onclick="moreZZJG()" class="mr">更多&gt;&gt;</a>
  	</s:if>
  </th>
	</tr></table>	
	
	<table border="0" cellspacing="0" cellpadding="0" class="zzsajj_table" style="width:97%; margin:0 auto;">
		<tr>
			<th>机构名称</th>
			<th>机构编号</th>
			<th>安全生产奖金投入</th>
			<th>从业人数</th>
			<th>参加培训人数</th>
			<th>缴保险人数</th>
			<th>安全管理人员数</th>
<!-- 			<th>是否已上报</th> -->
		</tr>				
		<s:iterator value="b002List" var="po">
			<tr>
				<td class="tb_input"><a href="javascript:;" onclick="viewZZJG('${po.MA001}')" class="nmCol">
						${po.MA004 }</a></td>
				<td class="tb_input">${po.MA002 }</td>
				<td class="tb_input">${po.MA005 }</td>
				<td class="tb_input">${po.MA006 }</td>
				<td class="tb_input">${po.MA007 }</td>
				<td class="tb_input">${po.MA008 }</td>
				<td class="tb_input">${po.MA018 }</td>
<%-- 				<td class="tb_input">${po.MA021 == 0 ? '否' : '是' }</td> --%>
			</tr>
		</s:iterator>				
						
	</table>
<br/>	
	
	
<!-- 规章制度   -->	
<a id="a3"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;规章制度
	<s:if test="#request.corpinfopo.tregulationList.size >= 5">  
  		　<a href="javascript:;" onclick="moreGZZD()" class="mr">更多&gt;&gt;</a>
  	</s:if>	
  </th>
	</tr></table>

			<c:if test="${requestScope.corpinfopo.tregulationList!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="zzsajj_table" style="width:97%; margin:0 auto;">
						<tr>
								<th scope="row">制度名称</th>
								<th scope="row">制度编号</th>
								<th scope="row">编制时间</th>
								<th scope="row">制度类型</th>
<!-- 								<th scope="row">附件</th> -->
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.tregulationList"
							var="c">
							<tr>
								<td colspan="" class="tb_input"><a href="javascript:;" onclick="viewGZZD('${c.MA001}')" class="nmCol">
									${c.MA004}</a></td>
								<td colspan="" class="tb_input">${c.MA003}</td>
								<td colspan="" class="tb_input">
								<s:date name="#c.MA008" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">${c.MA007}</td>
<!-- 								<td colspan="" class="tb_input"> -->
<%--                                 <s:iterator value="#c.c004poList" var="f"> --%>
<%--                                 <a href="<%=basePath%>${f.MA006}">${f.MA003}</a> --%>
<%--                                 </s:iterator> --%>
<!--                                 </td> -->
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>

<br/>
	
	
	
<!-- 教育培训   -->	
<a id="a4"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;教育培训
  	<s:if test="#request.corpinfopo.e001List.size >= 5">
  		　<a href="javascript:;" onclick="moreJYPX()" class="mr">更多&gt;&gt;</a>
  	</s:if>	
  </th>
	</tr></table>
	
			<c:if test="${requestScope.corpinfopo.e001List!= null}">
				<div id="yhysxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="zzsajj_table" style="width:97%; margin:0 auto;">
						<tr>
								<th scope="row">培训主题</th>
								<th scope="row">培训时间</th>
								<th scope="row">培训对象</th>
								<th scope="row">培训人</th>
<!-- 								<th scope="row">附件</th> -->
<!-- 								<th scope="row" >操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.e001List" var="c">
							<tr>
								<td colspan="" class="tb_input"><a href="javascript:;" onclick="viewJYPX('${c.MA001}')" class="nmCol">
									${c.MA002}</a></td>

								<td colspan="" class="tb_input">
								<s:date name="#c.MA008" format="yyyy-MM-dd"></s:date></td>

								<td colspan="" class="tb_input">${c.MA006}</td>
								<td colspan="" class="tb_input">${c.MA010}</td>
<!-- 								<td colspan="" class="tb_input"> -->
<%-- 	                                <s:iterator value="#c.c004poList" var="f"> --%>
<%-- 	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a> --%>
<%-- 	                                </s:iterator> --%>
<!--                                  </td> -->
<!-- 								<td colspan="" class="tb_input"><input type="button"  -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
<br/>			
			
			
				

<!-- 检查记录   -->	
<a id="a5"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;检查记录
  <s:if test="#request.corpinfopo.s121List.size >= 5">
  	　<a href="javascript:;" onclick="moreJCJL()" class="mr">更多&gt;&gt;</a>
  	</s:if>
  </th>
	</tr></table>	
	
			<c:if test="${requestScope.corpinfopo.s121List!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="zzsajj_table" style="width:97%; margin:0 auto;">
						<tr>
								<th scope="row">检查名称</th>
								<th scope="row">检查开始时间</th>
								<th scope="row">检查结束时间</th>
								<th scope="row">负责人</th>
<!-- 								<th scope="row">附件</th> -->
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.s121List" var="c">
							<tr>
								<td colspan="" class="tb_input"><a href="javascript:;" onclick="viewJCJL('${c.ma001}')" class="nmCol">
										${c.ma002}</a></td>
								<td colspan="" class="tb_input"><s:date name="#c.ma005" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input"><s:date name="#c.ma006" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">${c.ma008 }</td>
<!-- 								<td colspan="" class="tb_input"> -->
<%-- 								    <s:iterator value="#c.c004poList" var="f"> --%>
<%-- 	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a> --%>
<%-- 	                                </s:iterator> --%>
<!-- 								</td> -->
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.ma001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
<br/>			
			
					

<!-- 隐患排查  -->
<a id="a6"></a>
<table class="zzsajj_table_title"><tr>
  <th align="left" ><img src="images/view.png" width="16" height="16" style="float:left; line-height:29px; padding-top:5px;"/>&nbsp;隐患排查
  	<s:if test="#request.corpinfopo.s108List.size >= 5">
  		<a href="javascript:;" onclick="moreYHPC()" class="mr">更多&gt;&gt;</a>
  	 </s:if>
  </th>
	</tr></table>
	
			<c:if test="${requestScope.corpinfopo.s108List!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="zzsajj_table" style="width:97%; margin:0 auto;">
						<tr>
								<th scope="row">隐患名称</th>
								<th scope="row">发现时间</th>
								<th scope="row">隐患状态</th>
								<th scope="row">责任人</th>
<!-- 								<th scope="row">附件</th> -->
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.s108List" var="c">
							<tr>
								<td colspan="" class="tb_input"><a href="javascript:;" onclick="viewYHPC('${c.MA001}')" class="nmCol">
										${c.MA008}</a></td>
								<td colspan="" class="tb_input">
								<s:date name="#c.MA006" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">${c.MA015}</td>
								<td colspan="" class="tb_input">${c.MA023}</td>
<!-- 								<td colspan="" class="tb_input"> -->
<%-- 								   <s:iterator value="#c.c004poList" var="f"> --%>
<%-- 	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a> --%>
<%-- 	                                </s:iterator> --%>
<!-- 								</td> -->
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>	
	<br/>
		



		<s:if test="#parameters.govType == null || #parameters.govType[0] != 1">
				<form action="" id="repForm">	
					<table class="tjwz_table">
					<tr>
					  <td>(全选  <input type="checkbox" onclick="chkAll(this)" />) 上报项</td>
					    <td id="chkListTD">
							<input type="checkbox" name="b104PO.ma007" value="B001">企业基本信息
							<input type="checkbox" name="b104PO.ma007" value="B005">企业证照
							<input type="checkbox" name="b104PO.ma007" value="B002">安全生产组织机构
							<input type="checkbox" name="b104PO.ma007" value="Tregulation">企业规章制度
							<input type="checkbox" name="b104PO.ma007" value="E001">企业教育培训
							<input type="checkbox" name="b104PO.ma007" value="S121">日常检查记录
							<input type="checkbox" name="b104PO.ma007" value="S108">隐患排查
						</td>
				      <td><a href="javascript:;" onclick="repInfo()"><img src="images/sb2.png" width="82" height="23"></a></td>
					</tr></table>
				</form>	
		</s:if>

    	</div>
	 </div>
  </div>

  <div id="windowsbottom1">
    <div id="windowsbottomleft1">
      <div id="windowsbottomright1">
	      
      </div>
    </div>
  </div>
 
</div>
</div>
<div  class="div_mod_ml" style="2right:-82px;">
	<div ><img src="images/mulu.png" id="dirPic" width="31px" border="0" style="cursor:pointer; float:left;"/><img src="images/dingbu.jpg" onclick="goTop()" style="cursor:pointer;margin-top:90px; float:left; margin-left:-31px;"/>
		<div class="div_mod_menu"  style="display:block" >
		<ul >
<!-- 			<li><a href="javascript:gun('a0')">修改记录</a></li> -->
			<li><a href="javascript:gun('a1')">基本信息</a></li>
<!-- 			<li><a href="javascript:gun('a1')">企业证照</a></li> -->
			<li><a href="javascript:gun('a2')">组织机构</a></li>
			<li><a href="javascript:gun('a3')">规章制度</a></li>
			<li><a href="javascript:gun('a4')">教育培训</a></li>
			<li><a href="javascript:gun('a5')">检查记录</a></li>
			<li><a href="javascript:gun('a6')" >隐患排查</a></li>
		</ul>
		</div>
	</div>
	
</div>

<div id="viewDiv"></div>
</body>
</html>