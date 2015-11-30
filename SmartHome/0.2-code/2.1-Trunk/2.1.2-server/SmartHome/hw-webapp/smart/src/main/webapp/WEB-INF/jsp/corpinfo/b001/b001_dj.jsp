<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>

<html>

<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<title>企业安全生产情况登记信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,utilJS,validatorJS,grid,commonJS,my97)"executeResult="true" />
</head>
<style type="text/css">
<!--  
.windowdivmaintable{border-collapse:collapse; border: solid 1px #4BCDB9; font-size:12px; color:#565656; padding:0px; margin:0 auto; width:928px;}
#windows{
    width:960px;
    margin:0 auto;
    background-color:#0B927D;
}
#windowstop{
	display:block !important;
	width:960px;
	height:41px;
	background:url(/hwsafe/images/windowstitlebg.jpg) left top repeat-x;
}
	#windowstopleft{
	background:url(/hwsafe/images/windowstitleleft.jpg) left top no-repeat;
	height:41px;
	padding-left:12px;
	font-weight: bold;
}
-->
</style>
<script type="text/javascript">
function saveinfo(){
	if($("#infoform").valid()){
		jQuery.post(
				$$.getContextPath()+'corpinfo/b001!saveAddReport',
				$("#infoform").serialize(),
				function(data){
					showMsg(data.content,data.type);
				}
		);
	}
};

jQuery(function(){
  if($('#aqzd0').attr("checked")=="checked") {
	  $('#aqzdsfws input').attr('disabled',true);
  }
  else {
	  $('#aqzdsfws input').addClass('required');
  }
  if($('#aqss0').attr("checked")=="checked") {
	  $('#aqsssfws input').attr('disabled',true);
  }else {
	  $('#aqsssfws input').addClass('required');
  }
  if($('#aqzr0').attr("checked")=="checked") {
	  $('#aqzrsfws input').removeClass('required');
	  $('#aqzrsfws input').attr('disabled',true);
  }
  else {
	  $('#aqzrsfws input').addClass('required');
  }
});

function sfws(){
	if($('#aqzr0').attr("checked")=="checked") {
		  $('#aqzrsfws input').removeAttr("checked");
		  $('#aqzrsfws label.error').remove();
		  $('#aqzrsfws input').removeClass('required');
		  $('#aqzrsfws input').attr('disabled',true);
	  }else {
		  $('#aqzrsfws input').addClass('required');
		  $('#aqzrsfws input').attr('disabled',false);
	  }
}

</script>
<body>
<s:form id="infoform">
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>郑州高新技术产业开发区安全生产情况登记表</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowdivmain"><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>企业名称</th>
    <td colspan="7"><s:textfield name="b001PO.MA004" disabled="true"/>
    <s:hidden name="s099PO.ma001" />
    <s:hidden name="s099PO.ma002" id="corpID"/>
    <s:hidden name="b001PO.MA001" id="corpid"/>
    </td>
    </tr>
  <tr>
    <th >注册地址</th>
    <td colspan="7" ><s:textfield name="b001PO.MA045" disabled="true"/></td>
    </tr>
  <tr>
    <th>经营地址</th>
    <td colspan="7"><s:textfield name="s099PO.ma004"  cssClass="{maxlength:40}"/></td>
    </tr>
  <tr>
    <th>企业法人</th>
    <td ><s:textfield name="b001PO.MA047"  disabled="true"/></td>
    <th align="right">联系电话</th>
    <td ><s:textfield name="s099PO.ma006" cssClass="{isPhone:true}" /></td>
    <th align="right">固定电话</th>
    <td ><s:textfield name="s099PO.ma046" cssClass="{isPhone:true}"  /></td>
    <th align="right" style="width:110px;">是否参加过安全培训</th>
    <td><s:radio list="codeValueSF" listKey="value" listValue="name"  name="s099PO.ma007" cssClass="radiostyle " /></td>
    </tr>
    <tr>
    <th>安全生产副总</th>
    <td ><s:textfield name="s099PO.ma041" cssClass="{maxlength:20}" /></td>
    <th align="right">联系电话</th>
    <td ><s:textfield name="s099PO.ma042"  cssClass="{isPhone:true}" /></td>
    <th align="right">固定电话</th>
    <td ><s:textfield name="s099PO.ma043"  cssClass="{isPhone:true}" /></td>
    <th align="right" style="width:110px;">是否参加过安全培训</th>
    <td><s:radio list="codeValueSF" listKey="value" listValue="name"  name="s099PO.ma044" cssClass="radiostyle " /></td>
    </tr>
  <tr>
    <th>安全负责人</th>
    <td ><s:textfield name="b001PO.MA048"  disabled="true"/></td>
    <th align="right">联系电话</th>
    <td ><s:textfield name="b001PO.MA049"  disabled="true"/></td>
    <th align="right">固定电话</th>
    <td ><s:textfield name="s099PO.ma047"   /></td>
    <th align="right" style="width:110px;">是否参见过安全培训</th>
    <td><s:radio list="codeValueSF" listKey="value" listValue="name"   name="s099PO.ma010" cssClass="radiostyle " /></td>
    </tr>
  <tr>
    <th>注册资金</th>
    <td colspan="3"><s:textfield name="s099PO.ma011"  style="width:140px;" cssClass="{number:true,maxlength:17,decimal:true,min:0}"/>
      万元</td>
    <th colspan="2" align="right">员工人数</th>
    <td colspan="2"><s:textfield name="b001PO.MA014"  style="width:140px;" disabled="true"/>
      人</td>
    </tr>
  <tr>
    <th>安全机构</th>
    <td colspan="7"><s:radio list="codeValueYW"  disabled="true" listKey="value" listValue="name" name="s099PO.ma013" cssClass="radiostyle"/></td>
    </tr>
  <tr>
    <th>安全制度</th>
    <td colspan="3" ><s:radio list="codeValueYW"  disabled="true"  listKey="value" id="aqzd"    listValue="name" name="s099PO.ma014" cssClass="radiostyle "/></td>
    <td colspan="4" id="aqzdsfws"><s:radio list="codeValueWS" listKey="value"    listValue="display" name="s099PO.ma015" cssClass="radiostyle"/></td>
    </tr>
  <tr>
    <th>安全设施</th>
    <td colspan="3"><s:radio list="codeValueYW"  disabled="true"   listKey="value" id="aqss" listValue="name" name="s099PO.ma016" cssClass="radiostyle"/></td>
    <td colspan="4" id="aqsssfws"><s:radio list="codeValueWS" listKey="value"  listValue="display" name="s099PO.ma017" cssClass="radiostyle"/></td>
    </tr>
  <tr>
    <th>安全责任制</th>
    <td colspan="3"><s:radio list="codeValueYW" listKey="value" onchange="sfws()" id="aqzr" listValue="name" name="s099PO.ma018" cssClass="radiostyle required"/></td>
    <td colspan="4" id="aqzrsfws"><s:radio list="codeValueWS" listKey="value"  listValue="display" name="s099PO.ma019" cssClass="radiostyle"/></td>
    </tr>
	<tr>
    <th>经济性质</th>
    <td colspan="7"><s:select list="codeValueJJLX" name="b001PO.MA010"listKey="value" listValue="display" headerKey="" headerValue="请选择"ength="25" cssClass='required'></s:select></td>
    </tr>
  <tr>
    <th>所属行业</th>
    <td colspan="7"><s:select list="codeValueQYLX" name="b001PO.MA051" listKey="value" listValue="name" headerKey="" headerValue="请选择" ength="25" cssClass='required'></s:select></td>
    </tr>
  <tr>
  
    <th>主要产品</th>
    <td colspan="7"><s:textfield name="b001PO.MA018" ></s:textfield></td>
    </tr>
    <tr>
    <th>主要经营范围</th>
    <td colspan="7"><s:textfield name="s099PO.ma045" cssClass="{maxlength:100}" ></s:textfield></td>
    </tr>
	<tr>
    <th>主要原料</th>
    <td colspan="7"><s:textfield name="s099PO.ma023" cssClass="{maxlength:100}" ></s:textfield></td>
    </tr>
	<tr>
    <th>主要工艺</th>
    <td colspan="7"><s:textfield name="s099PO.ma024" cssClass="{maxlength:100}" ></s:textfield></td>
    </tr>
	<tr>
    <th>危险化学品</th>
    <td colspan="3"><s:textfield name="s099PO.ma026"  style="width:225px;" readonly="true"/>
      种</td>
    <th>数量</th>
    <td colspan="3"><s:textfield name="s099PO.ma027"  cssClass="{number:true,decimal:true,maxlength:2,min:0}"   style="width:222px;" />
      吨</td>
    </tr>
	<tr>
    <th>特种设备</th>
    <td><s:textfield name="s099PO.ma028" style="width:50px;" readonly="true"/> 
      台</td>
    <th>年检合格</th>
    <td><s:textfield name="s099PO.ma029"  style="width:50px;" readonly="true"/>
      台</td>
    <th>未年检</th>
    <td><s:textfield name="s099PO.ma030"  style="width:50px;" readonly="true"/>
台 </td>
    <th>不合格</th
    ><td><s:textfield name="s099PO.ma031"  style="width:50px;" readonly="true"/>
台 </td>
	</tr>
	<tr>
    <th>特种工</th>
    <td><s:textfield name="s099PO.ma032"  style="width:50px;" readonly="true"/>
    人</td>
    <th>持证</th>
    <td><s:textfield name="s099PO.ma033"  style="width:50px;" readonly="true"/>
人</td>
    <th>无证</th>
    <td colspan="3"><s:textfield name="s099PO.ma034"  style="width:50px;" readonly="true"/>
人</td>
    </tr>
	<tr>
    <th>警示标语</th>
   <td colspan="7"><s:radio list="codeValueYW" listKey="value" value="1" listValue="name" name="s099PO.ma035" cssClass="radiostyle required"/></td>
    </tr>
	<tr>
    <th>应急预案</th>
    <td colspan="7"><s:radio list="codeValueYW" listKey="value" listValue="name" name="s099PO.ma036" cssClass="radiostyle"/></td>
    </tr>
</table>
</div>
    </div>
    <div id="windowdiv"></div>
    </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
       <li class="save"><a href="javascript:saveinfo()">&nbsp;</a></li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
</body>
</html>
