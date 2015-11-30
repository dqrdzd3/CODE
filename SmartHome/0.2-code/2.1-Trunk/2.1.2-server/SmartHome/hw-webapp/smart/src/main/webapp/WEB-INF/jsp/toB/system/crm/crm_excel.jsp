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
   response.setHeader("Content-disposition","inline; filename=crm.xls");
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
                <th>设备标识</th>
                <th>手机号</th>
                <th>收货人</th>
                <th>省</th>
                <th>市</th>
                <th>区</th>
                <th>通讯地址</th>
                <th>关注状态</th>
                <th>设备注册日期</th>
              </tr>
            </thead>
            <tbody>

            <c:forEach items="${corpCrmPOList}" var="crm">  
		        <tr>  
		            <td>${crm.ma002}</td>  
		            <td>${crm.ma005}</td> 
		            <td>${crm.ma004}</td>
		            <td>${crm.ma007}</td>
		            <td>${crm.ma008}</td>
		            <td>${crm.ma009}</td>
		            <td>${crm.ma006}</td>
				<c:if test="${crm.ma011==0}">
				       <td>未关注</td>
				</c:if>
				<c:if test="${crm.ma011==1}">
				       <td><span style = 'font-weight:bold;color:green;'>关注</span></td>
				</c:if>
		            <td>
		            <fmt:formatDate value="${crm.ma012}" pattern="yyyy-MM-dd" />
		            </td>
		            
		        </tr>  
		    </c:forEach> 
              
              
            </tbody>
          </table>
        	

</body>
</html>