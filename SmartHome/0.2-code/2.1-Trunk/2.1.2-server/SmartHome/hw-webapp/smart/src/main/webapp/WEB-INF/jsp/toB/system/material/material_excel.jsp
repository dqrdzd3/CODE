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
   response.setHeader("Content-disposition","inline; filename=material.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>

<meta charset="UTF-8">
<title>空气电台数据系统</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

</head>

<body>
        
	
        	<table id="crm" border="1">
            <thead>
              <tr>

                <th>耗材名称</th>
                <th>设备名称</th>
                <th>使用日期</th>
                <th>截止日期</th>
                <th>使用情况</th>
              </tr>
            </thead>
            <tbody>

            <c:forEach items="${materialDetailPOList }" var="detail">  
		        <tr>  
		            <td>${detail.ma008}</td>  
		            <td>${detail.ma003}</td> 
		            <td> <fmt:formatDate value="${detail.ma004}" pattern="yyyy-MM-dd" /></td>
		            <td> <fmt:formatDate value="${detail.ma005}" pattern="yyyy-MM-dd" /></td> 

				<c:if test="${detail.ma006==0}">
				       <td><span style = 'font-weight:bold;color:#F39C12;'>已更换</span></td>
				</c:if>
				<c:if test="${detail.ma006==1}">
				       <td><span style = 'font-weight:bold;color:green;'>使用中</span></td>
				</c:if>
				<c:if test="${detail.ma006==2}">
				       <td><span style = 'font-weight:bold;color:red;'>超期</span></td>
				</c:if>
		     
		            
		        </tr>  
		    </c:forEach> 
              
              
            </tbody>
          </table>
        	

</body>
</html>