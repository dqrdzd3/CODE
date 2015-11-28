<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表数据统计系统</title>
</head>
<script type="text/javascript">
	var _fm=document.fm;
	function update(){
		document.fm.action="weeklySales.do?method=update";
		document.fm.submit();
	}
</script>
<body>
	<form action="update" method="post" name="fm">
    <input type="hidden" name="weeklySalesId" value="${weeklySales.weeklySalesId}">
    	<table  class="altrowstable" id="alternatecolor">
    		<tr>
				<td>产品名称:</td>
				<td><input type="text" name="productName" value="${weeklySales.productName}"></td>    		
    		</tr>
    		<tr>
				<td>规格型号:</td>
				<td><input type="text" name="specificationsModel" value="${weeklySales.specificationsModel}"></td>	
    		</tr>
    		<tr>
				<td>单价:</td>
				<td><input type="text" name="unitPrice" value="${weeklySales.unitPrice}"></td>    		
    		</tr>
    		<tr>
				<td>数量:</td>
				<td><input type="text" name="quantity" value="${weeklySales.quantity}"></td>    		
    		</tr>
    		<tr>
				<td>金额:</td>
				<td><input type="text" name="totalPrice" value="${weeklySales.totalPrice}"></td>    		
    		</tr>
    		<tr>
				<td>周数:</td>
				<td><input type="text" name="week" value="${weeklySales.week}"></td>    		
    		</tr>
    		<tr>
				<td>年数:</td>
				<td><input type="text" name="year" value="${weeklySales.year}"></td>    		
    		</tr>
    		<tr>
				<td><input type="button" value="保存" onclick="update()"></td>
				<td><input type="reset" value="重置"></td>    		
    		</tr>
    	</table>
    </form>
</body>
</html>