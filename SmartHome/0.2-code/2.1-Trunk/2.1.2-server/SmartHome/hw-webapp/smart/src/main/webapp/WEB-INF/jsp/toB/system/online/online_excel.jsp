<%@ page contentType="application/msexcel;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
   response.setHeader("Content-disposition","inline; filename=online.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>

<meta charset="UTF-8">
<title>空气电台数据系统</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

</head>

<body>
        
	
        	<table id="crm" border="1">
            <thead>
              <tr>
                <th>设备标识</th>
                <th>手机号</th>
                <th>设备注册地址</th>
                <th>邮箱</th>
                <th>手机类型</th>
                <th>用户注册日期</th>
              </tr>
            </thead>
            <tbody>

            <c:forEach items="${u001POList}" var="u001">  
		        <tr>  
		           
					<td>${u001.ma018}</td>  
		            <td>${u001.ma006}</td> 
		            <td>${u001.ma016}</td>
		            <td>${u001.ma005}</td>
		            <td>${u001.ma019}</td>


		            
		            <td>
		            <fmt:formatDate value="${u001.ma007}" pattern="yyyy-MM-dd" />
		            </td>
		            
		        </tr>  
		    </c:forEach> 
              
              
            </tbody>
          </table>
        	

</body>
</html>