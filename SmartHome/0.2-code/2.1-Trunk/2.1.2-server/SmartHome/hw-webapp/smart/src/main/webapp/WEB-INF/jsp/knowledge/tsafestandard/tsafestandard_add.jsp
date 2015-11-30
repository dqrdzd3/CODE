<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<link href="<%=basePath%>/css/main.css" rel="stylesheet" type="text/css" />
<title><s:text name="label.permission" />-<s:text name="application.title" /></title>

<!-- The JavaScript -->
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.scrollablecombo.js"></script>
<script type="text/javascript">
      $(function() {
          $('#type').scrollablecombo();
      });
	  $(function() {
          $('#casetrade').scrollablecombo();
      });
</script>
<style type="text/css">
<!--


.submit{
	background: url(images/okbutton.png) left top no-repeat;
	width:103px;
	height:31px;
	border: none;display:block;
}
.submit a:hover{ display:block; background: url(images/okbuttonover.png) left top no-repeat; width:103px; height:31px; }

.back{
	background: url(images/backabutton.png) left top no-repeat;
	width:103px;
	height:31px;
	border: none;display:block;
}
.back a:hover{ display:block; background: url(images/backabuttonover.png) left top no-repeat; width:103px; height:31px; }

-->
</style>

</head>
<body>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>法律法规标准库</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowdivmain"> 
      <s:form name="myfrom"  method="post" action="tsafestandard" enctype="multipart/form-data" cssClass="formmargin">
      <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable" id="windowdivmaintable">
   <tr>
    <th><span class="red">*</span> 标准名称</th>
    <td><s:textfield name="tsafelawPO.name" id="name"    /></td>
    <th><span class="red">*</span> 标准编号</th>
    <td><s:textfield name="tsafelawPO.code" id="code"    /></td>
     <th align="right"><span class="red">*</span> 标准类别</th>
    <td ><s:textfield name="tsafelawPO.type" id="type"    /></td>
  </tr>
  <tr>
   
    <th >颁布部门 </th>
    <td><s:textfield name="tsafelawPO.pubdept" id="pubdept"    /></td>
      <th>颁布地区 </th>
    <td><s:textfield name="tsafelawPO.area" id="area"     onfocus="WdatePicker({maxDate:'%y-%M-%d'})"/></td>
  <th align="right">状态</th>
    <td><s:radio name="tsafelawPO.status" list="#{'1':'有效','0':'作废'}" value="" cssClass="radiostyle" /></td>
  </tr>
  <tr>
    <th align="right">发布日期</th>
    <td>
      <s:textfield name="tsafelawPO.pubDate" id="pubDate"  cssClass="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/>
    </td>
    <th>实施日期</th>
    <td><s:textfield name="tsafelawPO.takeDate" id="takeDate"  cssClass="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/></td>
 <th align="right">失效日期</th>
    <td><s:textfield name="tsafelawPO.cancelDate" id="cancelDate"   cssClass="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/></td>
  </tr>
 
    <tr>
    <th align="right">法规内容</th>
    <td colspan="5"><s:textarea name="tsafelawPO.content" id="content"   cssClass="textarea1"  onfocus="WdatePicker({maxDate:'%y-%M-%d'})"/></td>
    </tr>
  
  <tr>
    <th align="right"><span class="red">*</span>上传文件 </th>
    <td colspan="5"><s:file name="upload"></s:file>
   
    </td>
    </tr>
</table>
</s:form>
</div>
    </div></div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul><li class="cancel" ><a href="#">&nbsp;</a></li>
       <li class="save" style="margin-right:15px;"><a href="#">&nbsp;</a></li>
      
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
</body>
</html>