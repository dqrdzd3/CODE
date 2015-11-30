<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,jqueryImage,commonJS)"
	executeResult="true" />

<style type="text/css">
<!--
body,html {
	overflow-x: hidden;
}
-->
</style>
<script type="text/javascript">
	function getData(filePath){
		if (filePath.substring(filePath.lastIndexOf('\\')+1).length > 30) {
			if (parent.showMsg)
				parent.showMsg('文件名最长30个字符！',1);
			else {
				if (showMsg)
					showMsg('文件名最长30个字符！',1);
				else 
					alert('文件名最长30个字符！');
			}
			return false;
		}
		//将路径放进input
		document.getElementById('textfield').value = filePath;
		//文件名放进textarea
		var arr = ["jpg","jpeg","gif","png","3gp","JPG","JPEG","GIF","PNG","3GP"];
	    var  name = filePath.split('.').pop().toLowerCase();
	    if(jQuery.inArray(name,arr)<0){
	    	showMsg("请选择正确的图片格式！",2);
	    	return false;
	    }
		var index1 = filePath.lastIndexOf('\\');
		var index2 = filePath.lastIndexOf('.');
		
		var fileName = filePath.substring(index1+1,index2);
		//document.getElementById('introduce').value = fileName;
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
	function deletePic(){
		var ids = '';
		$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
	    	if(value.checked){
	    		ids += value.value + ',';
	    	}
		});
		if(ids == ''){
			showMsg('请至少选择一张图片!',2);
			return;
		}
		  showMsg("确定删除这些图片吗?",4,{
		        buttons:{
		            '取消':function(){
		                $(this).dialog('close');
		            },
		            '确认':function(){
		    			
		    				//ajax发送后台数据删除
		    				var url = '<%=path%>/upload/doDelete.action?ids='+ids;
		    				var param = '';
		    				$.post(url,param,function(result){
		    					$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
		    					    //if(value.name != null && value.name != ''){
		    					    	if(value.checked){
		    					    		//document.getElementById('dl'+value.value).style.display = 'none';
		    					    		$('#dl'+value.value).remove();
		    					    		//删除id
		    						    	var ids = $('#ids').val();
		    						    	ids = ids.replace(value.value+',' , '');
		    						    	$('#ids').val(ids);
		    						    	$('#c004ids', parent.document).val($('#ids').val());
		    					    	}
		    					    //}
		    					});
		    					showMsg('删除成功!',1);
		    				});
		    			
		            }
		        }
		    });
		
		
		
		
// 		if(window.confirm('确定删除这些图片吗?')){

// 			var ids = '';
// 			$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
// 		    	if(value.checked){
// 		    		ids += value.value + ',';
// 		    	}
// 			});
			
// 			if(ids == ''){
// 				alert('请至少选择一张图片!');
// 			}else{
// 				//ajax发送后台数据删除
<%-- 				var url = '<%=path%>/upload/doDelete.action?ids='+ids; --%>
// 				var param = '';
// 				$.post(url,param,function(result){
// 					$.each($('#xsqimg'+' input[type="checkbox"]'), function(key, value){
// 					    //if(value.name != null && value.name != ''){
// 					    	if(value.checked){
// 					    		//document.getElementById('dl'+value.value).style.display = 'none';
// 					    		$('#dl'+value.value).remove();
// 					    		//删除id
// 						    	var ids = $('#ids').val();
// 						    	ids = ids.replace(value.value+',' , '');
// 						    	$('#ids').val(ids);
// 						    	$('#c004ids', parent.document).val($('#ids').val());
// 					    	}
					    	
					    	
// 					    //}
// 					});
// 					alert('删除成功!');
// 				});
// 			}
// 		}
	}
	
	function yulan(){
		//获取选中的文档，有且只有一个，如果多个，提示单选
		var count = 0;
		var swfpath = null;
		var id = null;
		$.each($(':checkbox'), function(key, value){
			if(value.value != ''){
		    	if(value.checked){
		    		count++;
		    		id = value.value;
		    	}
			}
		});
		
		if(count == 0) showMsg('请选择一个图片进行预览！',2);
		else if(count > 1) showMsg('您选择了多个，请选择一个图片进行预览！',2);
		else if(count == 1){
			//window.open('<%=path%>/upload/doViewNew.action?id='+id);
			parent.$$.getFrame('rightFrame').Tabs.openTab("tpyl", "图片预览",'<%=path%>/upload/doViewNew.action?id='+id, "图片预览");
		}
		else{
			
		}
	}
	
	function checkForm(){
		var str = document.getElementById('textfield').value;
		
		if(str == '' || str == null){
			showMsg('请选择一个文件进行上传！',2);
			return false;
		}else{
			var arr = ["jpg","jpeg","gif","png","3gp","JPG","JPEG","GIF","PNG","3GP"];
		    var  name = str.split('.').pop();
		    if(jQuery.inArray(name,arr)<0){
		    	showMsg("请选择正确的图片格式！",2);
		    	return false;
		    }else{
				return true;
		    }
		}
		

		
	}
	
	$(function(){
		$('#c004ids', parent.document).val($('#ids').val());
	});
</script>
</head>

<body style="overflow: auto;">
<c:if test="${param.from != 'view'}">
	<div id="scq">
		<c:if test="${requestScope.from != 'view' }">
			<form action="<%=path %>/upload/uploadnew.action" method="post"
				onsubmit="return checkForm();" enctype="multipart/form-data">
				<table width="100%" border="0" cellspacing="4" cellpadding="0"
					height="100%">
					<tr>
						<td align="right" style="width: 70px;" nowrap="nowrap">文件上传：</td>
						<td style="width: 40%;">
							<input type="hidden" name="textfield" id="textfield" /> 
							<input type="hidden" name="MA002" id="MA002" value="${requestScope.MA002 }" /> 
							<input type="hidden" name="ids" id="ids" value="${requestScope.ids }" /> 
							<input type="file" id="uploadFile" name="uploadFile" onchange="getData(this.value)" />
							<input type="hidden" name="from" id="from" value="${requestScope.from }" /> 
						</td>
						<td align="center" style="width: 80px;"><input
							style="background: url(res/uploadify-v3.0.0/shangchuan.jpg); width: 68px; height: 25px; border: 0px;"
							type="submit" value="" /></td>
						<td align="right" style="width: 70px;" nowrap="nowrap"></td>
						<td></td>
					</tr>
				</table>
			</form>
			
		</c:if>
	</div>
	</c:if>
	
	<c:if test="${requestScope.total != 0 }">

		<div id="xsq">
			<c:if test="${requestScope.from != 'view' }">
				<div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td style="width: 180px;">
								<div id="operating" class="operating">
									<ul>
										<li class="liview"><a href="javascript:yulan()">预览</a></li>
										<c:if test="${param.from != 'view'}">
											<li class="lidelete"><a href="javascript:deletePic()">删除</a></li>
										</c:if>
									</ul>
								</div>
							</td>
							<td style="width: 50px;">
								<div id="allselect">
									<c:if test="${param.from != 'view'}">
										<input name="sel" type="checkbox"
											onclick="selAll(this.checked)" value="" />全选
									</c:if>	
								</div>
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<div class="xsqimg" id="xsqimg">
				<c:forEach items="${requestScope.list }" var="v" varStatus="status">
					<dl id="dl${v.MA001 }">
						<dt>
							<c:if
								test="${v.MA007 == 'jpg' || v.MA007 == 'jpeg' || v.MA007 == 'bmp' || v.MA007 == 'gif' || v.MA007 == 'png' }">
								<img id="${v.MA001 }" src="<%=path %>${v.MA006 }" width="100%"
									height="100%" onclick="sel('${v.MA001 }')" />
							</c:if>
						</dt>
						<dd onclick="sel()">${v.MA003 }</dd>
						<dd>
							<input name="asdf" id="cc${v.MA001 }" type="checkbox"
								value="${v.MA001 }" />
						</dd>
					</dl>
				</c:forEach>

			</div>
		</div>
	</c:if>
</body>
</html>

