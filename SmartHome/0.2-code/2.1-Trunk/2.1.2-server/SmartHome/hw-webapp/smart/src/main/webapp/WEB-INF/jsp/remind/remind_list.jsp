<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,grid,commonJS)" executeResult="true" />
<title>Insert title here</title>
<script type="text/javascript">
	function seecontent(remindId){
		$$.openDiv('opendiv','待办业务提醒内容','remind/remind!doView?id='+remindId,'',function(){
			//删除tr
			$('#tr'+remindId).remove();
			//刷新头部页面的数量
			//var frames = window.parent.parent.parent.parent.document.getElementById("topFrame"); 
			//alert(frames.innerHTML);
			//frames.refresh();
		});
	}
</script>
</head>
<body>
<div class="branchright" id="div">
<div id="windowtitle" style="border-right:#B4EBE8 solid 1px;">
        <div id="windowtitleleft">
          <div>待办业务消息
</div>
        </div>
      </div>
  <div class="qdjcnrdiv">
  	<table width="100%" border="1" cellpadding="0" cellspacing="0" class=" tablecss">
		<thead>
            <tr class="fixedHeaderTr">
	            <th style="width:300px;">标题</th>
	            <th style="width:100px;">类型</th>
	    	    <th style="width:200px;">发送人</th>
	    		<th >发送时间</th>
	    		
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.remindList }" var="v" varStatus="status">
				<tr id="tr${v.MA001 }">
		    		<td align="center"><a href="javascript:seecontent('${v.MA001 }')">${v.MA002 }</a></td>
		    		<td align="center">
	    				<c:if test="${v.MA008 == '1' }">
	    					短信
	    				</c:if>
	    				<c:if test="${v.MA008 == '2' }">
	    					邮件
	    				</c:if>
	    				<c:if test="${v.MA008 == '3' }">
	    					其他
	    				</c:if>
	    			</td>
		    	 	<td align="center">${v.MA007 }</td>
	    		 	<td align="center">${v.MA006d }</td>
	    			
	    		</tr>
			</c:forEach>
		</tbody>
  </table>
  </div>
</div>

<div id="opendiv"></div>
</body>
</html>