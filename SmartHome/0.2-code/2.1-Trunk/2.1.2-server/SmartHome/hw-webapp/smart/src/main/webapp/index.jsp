<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
window.setTimeout(function(){
window.opener=null;//如果后面需要用openner，这一行可以去掉 
window.open('','_top'); 
var newwindow = window.open('login.action','_blank','Width='+ (screen.width) + ',Height=' + (screen.height) + ',Left=0,Top=0,status=yes,menubar=no, location=no,scrollbars=no,resizable=yes');
if(newwindow !=null){
	window.top.close();
}else{
	$("#open").html("请调整浏览器设置，允许弹出临时窗口！或者直接点击<a href='#' onclick='opendiv();'>这里</a>进行跳转。");
}},1000);

function opendiv(){
	var newwindow = window.open('login.action','_blank','Width='+ (screen.width) + ',Height=' + (screen.height) + ',Left=0,Top=0,status=yes,menubar=no, location=no,scrollbars=no,resizable=yes');
	window.top.close();
} 
</script>
</head>
<body>
</body>
</html>