<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery)" executeResult="true"/>
<style type="text/css"> 
<!--
body,html{overflow-x:hidden; 
}
-->
</style>
 <script type="text/javascript">
	function yulan(){
		//获取选中的文档，有且只有一个，如果多个，提示单选
		var count = 0;
		var swfpath = null;
		var id = null;
		$.each($(':checkbox'), function(key, value){
		    //if(value.name != null && value.name != ''){
		    	if(value.checked){
		    		count++;
		    		id = value.id;
		    		swfpath = value.value;
		    	}
		    //}
		});
		
		if(count == 0) alert('请选择一个文档进行预览！');
		else if(count > 1) alert('您选择了多个，请选择一个文档进行预览！');
		else if(count == 1){
			window.open('doView.action?swfpath='+swfpath+'&id='+id,'flexpaper','height=500,width=800,top=50,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
		}
		else{
			
		}
	}
	
	function deletePic(){
		if(window.confirm('确定删除这些图片吗?')){
			
			//危险源备案资料管理
			//if(parent.picback){
				var ids = '';
				$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
			    	if(value.checked){
			    		ids += value.value + ',';
			    	}
				});
				//ajax发送后台数据删除
				var url = '<%=path%>/upload/doDelete.action?ids='+ids;
				var param = '';
				$.post(url,param,function(result){
					$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
					    //if(value.name != null && value.name != ''){
					    	if(value.checked){
					    		//document.getElementById('dl'+value.value).style.display = 'none';
					    		$('#dl'+value.value).remove();
					    	}
					    //}
					});
					alert('删除成功!');

					
					var imgs = document.getElementById("xsqimg").getElementsByTagName('img');
					var mnb = '';
					for(i=0;i<imgs.length;i++){
						//alert(imgs[i].id);
						var id = imgs[i].id;
						mnb += id + ',';

					}
					mnb = mnb.substring(0, mnb.length - 1);
					top.ids = mnb;
					
				});
			//}
			
			//正常
			/*
			else{
				$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
				    //if(value.name != null && value.name != ''){
				    	if(value.checked){
				    		//document.getElementById('dl'+value.value).style.display = 'none';
				    		$('#dl'+value.value).remove();
				    	}
				    //}
				});
			}
			*/
		}
		
		
	}
	function sel(id){
		document.getElementById('cc'+id).checked = !document.getElementById('cc'+id).checked;
	}
	function selAll(tag){
		$.each($('input[type="checkbox"]'), function(key, value){
		    if(value.name == 'asdf'){
		    	value.checked = tag;
		    }
		});
	}
	
</script>
</head>
 
<body>
<c:if test="${requestScope.total != 0 }">
	<div id="xsq">
	  		<div>
	  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					  <td style="width:180px;">
					  <div id="operating" class="operating">
					    <ul>
					      <!-- <li class="liview"><a href="javascript:yulan()">预览</a></li> -->
					      <li class="lidelete"><a href="javascript:deletePic()">删除</a></li>
					    </ul>
					  </div>
					  </td>
					  <!-- 
					  <td align="center">
					  	<div class="digg">
					  		<span class="disabled">总记录数${requestScope.total }</span>
					  		<span class="disabled">当前页${requestScope.curPage }/共${requestScope.totalPage }页</span>
					  		<a href="#"> 上一页 </a>
					  		<c:forEach var="i" begin="1" end="${requestScope.totalPage }" step="1">
					  			<c:choose>
					  				<c:when test="${i == requestScope.curPage }">
					  					<span class="current">${i }</span>
					  				</c:when>
					  				<c:otherwise>
					  					<a href="#">${i }</a>
					  				</c:otherwise>
					  			</c:choose>
					  			
					  		</c:forEach>
					  		<a href="#"> 下一页 </a>
					  		<span  class="disabled">&nbsp;&nbsp;转到第<input name="" type="text" />页</span>
					  		<span  class="disabled"><input name="" type="button" value="确定" /></span>
					  	</div>
					  </td>
					   -->
					  <td style="width:50px;">
					  	<div id="allselect">
					  		<input name="sel" type="checkbox" onclick="selAll(this.checked)" value="" />全选
					  	</div>
					  </td>
				  </tr>
				</table>
		</div>
	<div class="xsqimg" id="xsqimg">
	        <c:forEach items="${requestScope.list }" var="v" varStatus="status">
		  	 <dl id="dl${v.MA001 }">
				<dt>
			  	 <c:if test="${v.MA007 == 'jpg' || v.MA007 == 'jpeg' || v.MA007 == 'bmp' || v.MA007 == 'gif' || v.MA007 == 'png' }">
			  	 	<img id="${v.MA001 }" src="<%=path %>${v.MA006 }" width="100%" height="100%" onclick="sel('${v.MA001 }')" />
			  	 </c:if>
				</dt>
				<dd onclick="sel()">${v.MA003 }</dd>
                <dd><input name="asdf" id="cc${v.MA001 }" type="checkbox" value="${v.MA001 }" /></dd>                 
			 </dl>
		  </c:forEach>      
	</div>
	</div>
</c:if>
</body>
</html>

