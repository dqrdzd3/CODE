<%@ page contentType="text/html;charset=UTF-8" language="java"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<table id="userPm25RankMon" class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>发货日期</th>
              <th>联系手机</th>
              <th>联系人</th>
              <th>设备标识</th>
              <th>数据</th>
              <th>添加潜在客户</th>
            </tr>
          </thead>
          <tbody>
          
          	<c:forEach items="${rankUserPMM}" var="rankPmm">  
		        <tr>  
		            <td><script>dateFormat('${rankPmm.REG_DATE}','yyyy-MM-dd')</script></td>  
		            <td>${rankPmm.TEL}</td> 
		            <td>${rankPmm.LXR}</td>
		            <td>${rankPmm.DEV_ID}</td>
		            <td>${rankPmm.DATAVALUE}</td>
		            
		            
		            <td><script>if(${rankPmm.FOCUSOR} == 0) document.write("<button class='btn btn-danger btn-flat' style = 'font-weight:bold' onclick = 'onSureStatus(\"${rankPmm.ID}\");'>关注</button>");if(${rankPmm.FOCUSOR} == 1) document.write("<font>已关注</font>");</script><span style = "display: none;" >${rankPmm.ID} </span></td>
		            
		        </tr>  
		    </c:forEach> 
          	
		  </tbody>
        </table>
</body>
</html>