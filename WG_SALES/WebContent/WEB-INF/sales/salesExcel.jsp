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
   response.setHeader("Content-disposition","inline; filename=sales.xls");
   //以上这行设定传送到前端浏览器时的档名为test1.xls
   //就是靠这一行，让前端浏览器以为接收到一个excel档 
%>

<meta charset="UTF-8">
<title>报表数据统计系统</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

</head>

<body>
        
	
        	<table id="sales" border="1">
            <thead>
              <tr>
              	<th>销售主键</th>
                <th>客户名称</th>
                <th>收货人姓名</th>
                <th>收货人地址</th>
                <th>联系电话</th>
                <th>发货时间</th>
                <th>买家旺旺昵称</th>
                <th>产品名称</th>
                <th>产品编号</th>
                <th>型号</th>
                <th>颜色</th>
                <th>单价</th>
                <th>数量</th>
                <th>金额</th>
                <th>到款情况</th>
                <th>是否开票</th>
                <th>发票号</th>
                <th>销售平台</th>
                <th>快递公司</th>
                <th>快递单号</th>
                <th>签收时间</th>
                <th>快递费</th>
                <th>备注</th>
              </tr>
            </thead>
            <tbody>

            <c:forEach items="${list}" var="li">  
		        <tr>  
		        	<td>${li.salesId}</td>
		            <td>${li.customerName}</td>  
		            <td>${li.consigneeName}</td> 
		            <td>${li.receiverAddr}</td>
		            <td>${li.phoneNo}</td>
		            <td>
		            	<fmt:formatDate value="${li.deliveryTime}" pattern="MM/dd/yyyy" />
		            </td>
		            <td>${li.buyersNickname}</td>
		            <td>${li.productName}</td>
		            <td>${li.productId}</td>
		            <td>${li.modelType}</td>
		            <td>${li.color}</td>
		            <td>${li.unitPrice}</td>
		            <td>${li.quantity}</td>
		            <td>${li.totalPrice}</td>
		            <c:if test="${li.moneyStatus==0}">
		            	<td>全部</td>
		            </c:if>
		            <c:if test="${li.moneyStatus==1}">
		            	<td>已支付</td>
		            </c:if>
		            <c:if test="${li.moneyStatus==2}">
		            	<td>未支付</td>
		            </c:if>
		            <c:if test="${li.invoice==0}">
		            	<td>全部</td>
		            </c:if>
		            <c:if test="${li.invoice==1}">
		            	<td>是</td>
		            </c:if>
		            <c:if test="${li.invoice==2}">
		            	<td>否</td>
		            </c:if>
		            <td>${li.invoiceNo}</td>
		            <c:if test="${li.salesPlatform==0}">
		            	<td>空</td>
		            </c:if>
		            <c:if test="${li.salesPlatform==1}">
		            	<td>微店</td>
		            </c:if>
		            <c:if test="${li.salesPlatform==2}">
		            	<td>微信小店</td>
		            </c:if>
		            <c:if test="${li.salesPlatform==3}">
		            	<td>威果诚品</td>
		            </c:if>
		            <c:if test="${li.salesPlatform==4}">
		            	<td>企业淘宝</td>
		            </c:if>
		            <c:if test="${li.salesPlatform==5}">
		            	<td>线下</td>
		            </c:if>
		            <td>${li.courierCompany}</td>
		            <td>${li.courierNo}</td>
		            <td>
		            	<fmt:formatDate value="${li.signTime}" pattern="MM/dd/yyyy" />
		            </td>
		            <td>${li.courierCost}</td>
		            <td>${li.remark}</td>	            
		        </tr>  
		    </c:forEach> 
              
            </tbody>
          </table>
        	

</body>
</html>