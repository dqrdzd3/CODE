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
<script src="resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	var _fm = document.fm;
	
	function add(){
		document.fm.action = "weeklySales.do?method=add";
		document.fm.submit();
	}
	
	function  subCheck(val) {  
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(val)){
				//var id = document.getElementById("unitPrice");				
				alert("请输入正确数字！");
			}  
	}
</script>
<body>
	 <form action="weeklySalesIndex.jsp" method="post" name="fm" >
    	<table  class="altrowstable" id="alternatecolor">
    		<tr>
				<td>产品名称:</td>
				<td><input type="text" name="productName" id="productName">
				</td>    		
    		</tr>
    		<tr>
				<td>规格型号:</td>
				<td><input type="text" name="specificationsModel" id="specificationsModel">
				</td>    		
    		</tr>
    		<tr>
				<td>单价:</td>
				<td><input type="text" name="unitPrice" id="unitPrice" onblur="subCheck(this.value);">
				</td>    		
    		</tr>
    		<tr>
				<td>数量:</td>
				<td><input type="text" name="quantity" id="quantity" onblur="subCheck(this.value);">
				</td>    		
    		</tr>
    		<tr>
				<td>金额:</td>
				<td><input type="text" name="totalPrice" id="totalPrice" onblur="subCheck(this.value);">
				</td>    		
    		</tr>
    		<tr>
				<td>周数:</td>
				<td><input type="text" class="number" name="week" id="week">
				</td>    		
    		</tr>
    		<tr>
				<td>年数:</td>
				<td><input type="text" name="year" id="year">
				</td>    		
    		</tr>
    		<tr>
				<td><input type="button" value="保存" onclick="add()"></td>
				<td><input type="reset" value="重置"></td>    		
    		</tr>
    	</table>
    </form>
</body>
</html>