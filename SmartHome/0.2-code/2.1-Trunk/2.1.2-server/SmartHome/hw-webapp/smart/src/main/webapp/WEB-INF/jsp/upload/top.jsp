<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,commonJS)" executeResult="true"/>

<style type="text/css"> 
<!--
body,html{overflow-x:hidden; 
}
-->
</style>
 <script type="text/javascript">
 	
	function test_upload(){
		document.getElementById('uploadFile').click();
	}
	
	function getData(filePath){
		if (filePath.length > 30) {
			alert('文件名最长30个字符！');
			return false;
		}
		//将路径放进input
		//document.getElementById('textfield').value = filePath;
		//文件名放进textarea
		var arr = ["jpg","jpeg","gif","png","3gp"];
	    var  name = filePath.split('.').pop();
	    if(jQuery.inArray(name,arr)<0){
	    	alert("请选择正确的图片格式,否则可能不能正常展示");
	    	return false;
	    }
		var index1 = filePath.lastIndexOf('\\');
		var index2 = filePath.lastIndexOf('.');
		
		var fileName = filePath.substring(index1+1,index2);
		document.getElementById('introduce').value = fileName;
	}
	
	function checkfile(){
		var str = document.getElementById('textfield').value;
		if(str == null || str == '') {
			alert('请选择文件！');
			return false;
		}else{
			return true;
		}
	}
	
	window.onload = function(){
		var name = '${requestScope.c004.MA002}';
		document.getElementById("picback").value = parent.picback;
		document.getElementById("dangerid").value = parent.MA002;
		document.getElementById("from").value = parent.from;
		if(name == null || name == ''){}
		else{
			//刷新左边
			alert('上传图片成功！');
			top.ids = top.ids + '${requestScope.c004.MA001},';
			parent.bottom.location.href  = '<%=path %>/upload/initBottom.action?MA002='+top.MA002+'&ids='+top.ids+'&picback='+parent.picback+'&from='+parent.from;
		}
	};
	
	function closeWin(){
		//top.close();
		//alert(winodw.opener.document.getElementById('imageDiv'));
	}
</script>
</head>
 
<body>
 <div id="scq">
<form action="<%=path %>/upload/uploadFile.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="picback" id="picback"  />
<input type="hidden" name="dangerid" id="dangerid"  />
<input type="hidden" name="from" id="from"  />
<table width="100%" border="0" cellspacing="4" cellpadding="0" height="100%">
  <tr>
    <td  align="right" style="width:70px;" nowrap="nowrap">文件上传：</td>
    <td style="width:40%;">
    <!-- 
      <input type="text" name="textfield" id="textfield" /> -->
      <input type="file" id="uploadFile" name="uploadFile"  onchange="getData(this.value)" />
      </td>
    <td  align="center" style="width:80px;"><input style="background:url(res/uploadify-v3.0.0/shangchuan.jpg);width:68px;height:25px;border:0px;" type="submit" value="" /></td>
    <td  align="right"  style="width:70px;" nowrap="nowrap">文件说明：</td>
    <td ><textarea id="introduce" name="introduce" cols="" rows=""></textarea></td>
  </tr>
</table>
</form>
</body>
</html>

