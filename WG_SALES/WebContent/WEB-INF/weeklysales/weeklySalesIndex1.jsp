<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<script src="resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	var _fm=document.fm;

	function search(){
		//document.fm.action="weeklySales.do?method=searchAll";
		document.fm.action="weeklySales.do?method=search";
		document.fm.method = "POST";
		document.fm.submit();
	}	
	
	function add(){
		
	}
	/**/
	function isDel(){
		if(confirm("确认要删除该条记录？")){
			return true;
		}else{
			return false;
		}
	}
	
	function  subCheck(val) {  
		if(event.keyCode==13){
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(val)){
				alert("请输入正确数字！");
			}else{
				//document.fm.action="weeklySales.do?method=searchAll";
				document.fm.action="weeklySales.do?method=search";
				if(validfor()){
					document.fm.submit();
				} 
			} 
		}  
	} 
	
	function toPost(method,currentPage,size,productName,specificationsModel,isPrecise){
		alert('aaa');
		document.fm.action="weeklySales.do?method="+method+"&currentPage="+currentPage+"&size="+size+"&productName="+productName+"&specificationsModel="+specificationsModel;
		document.fm.method="post";
		document.fm.submit();
	}		
</script>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-type" content="text/html;charset=utf-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.tb1{
		width:1200px;
		height: 10px;
		background-color:#FFFFFF;
	}
	#tab{
		 margin:0 auto;
	}
	table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	width:1200px;
}
table.hovertable th {
	background-color:#FFFFFF;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {
	background-color:#FFFFFF;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

#tablehead{
		 background-color:#66FF00;
	}
	
	#f{
	display:#FF0;
	}
	
	#m{
	width:200px;
	height:40px;
	backgroud:#F00;
	font-size:30px;	
	}
	
	</style>
  </head>
  
  <body> 
  <form action="search" method="post" name="fm">
  		<table>
  			<tr>
    			<td>产品名称：</td>
    			<td><input type="text" name="productName" value="${weeklySales.productName}"/></td>
    			<td></td>
    			<td>规格型号：</td>
    			<td><input type="text" name="specificationsModel" value="${weeklySales.specificationsModel}"/></td>
    			<td></td>
    			<td><input type="button" value="查询" onclick="search()"/></td>
    			<td><input type="button" value="添加记录" onclick="window.location.href='weeklySalesAdd.jsp'"/></td>
    		</tr>
    		<tr> 
    			<td colspan="10">&nbsp;</td>
    		</tr>
    	</table>
    	<table class="hovertable" id="tab" >
    		<tr  bgcolor="green" style="blue" >
    			<th id="tablehead"><font color="blue">选择</font></th>
    			<th id="tablehead"><font color="blue">产品名称</font></th>
    			<th id="tablehead"><font color="blue">规格型号</font></th>
    			<th id="tablehead"><font color="blue">单价</font></th>
    			<th id="tablehead"><font color="blue">数量</font></th>
    			<th id="tablehead"><font color="blue">金额</font></th>
    			<th id="tablehead"><font color="blue">操作</font></th>
    		</tr>
    		<c:forEach var="li" items="${list}">
    		<tr>
    			<td><input type="checkbox" name="isSelected"/></td>
    			<td>${li.productName}</td>
    			<td>${li.specificationsModel}</td>    			
    			<td>${li.unitPrice}</td>
    			<td>${li.quantity}</td>
    			<td>${li.totalPrice}</td>   		
    			<td><a href="weeklySalesSearchById.jsp?weeklySalesId=${li.weeklySalesId}">修改</a> <a href="weeklySalesDelete.jsp?weeklySalesId=${li.weeklySalesId}" onclick="return  isDel()">删除</a>
    				<a href="">详细信息</a>
    			</td>
    		</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="8" align="center">第<input type="text" name="currentPage" id="currentPage"  value="${page.currentPage}" onkeydown="subCheck(this.value);"/>页
    			 共${page.totalPage}页  ${page.total}条记录 &nbsp;&nbsp;
    			每页<input type="text" name="size" id="size" value="${page.size}" onkeydown="subCheck(this.value);"/>条
    			<a href="weeklySales.do?method=${method}&currentPage=1&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}">首     页</a>
    			<c:choose>
    				<c:when test="${page.currentPage==1&&page.totalPage==1}">
    					上一页
    					下一页
    				</c:when>
    				<c:when test="${page.currentPage==1&&page.currentPage<page.totalPage}">
    					上一页
    					<a href="weeklySales.do?method=${method}&currentPage=${page.currentPage+1}&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}">下一页</a>
    				</c:when>
    				
    				<c:when test="${page.currentPage>1&&page.currentPage<page.totalPage}">
    				
    					<a href="weeklySales.do?method=${method}&currentPage=${page.currentPage-1}&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}">上一页</a>
    					<a href="weeklySales.do?method=${method}&currentPage=${page.currentPage+1}&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}">下一页</a>
    				</c:when>
    				
    				<c:when test="${page.currentPage>1&&page.currentPage==page.totalPage}">
    					<a href="weeklySales.do?method=${method}&currentPage=${page.currentPage-1}&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}">上一页</a>
    					下一页
    				</c:when>				
    				<c:otherwise>
    					上一页
    					下一页
    				</c:otherwise>
    			</c:choose>
    		
    			<%--  <a href="weeklySales.do?method=${method}&currentPage=${page.totalPage}&size=${page.size}&productName=${weeklySales.productName}&specificationsModel=${weeklySales.specificationsModel}&isPrecise=${isPrecise}">尾     页</a>  --%>
    			 <a href="javascript:void(0)" onclick="toPost('${method}','${page.totalPage}','${page.size}','${weeklySales.productName}','${weeklySales.specificationsModel}')">尾     页</a>  
    			</td>
    		</tr>
    	</table>
    </form>  

  </body>
</html>
