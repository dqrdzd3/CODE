<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:action name="include(mainCSS,jquery,uploadify)" executeResult="true"/>
<meta http-equiv="content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=path %>/js/czd_common.js"></script>
<script type="text/javascript">
$(function(){
	var from = '${requestScope.from}';
	var limit = '${requestScope.limit}';
	if(limit == null || limit == ''){
		limit = 100;
	}else{
		limit = parseInt(limit);
	}
	$('#upload').uploadify({
		'auto' : false,
        'swf' : '<%=path%>/res/uploadify-v3.0.0/uploadify.swf',
        'uploader' : '<%=path%>/upload/upload?jsessionid=<%=session.getId()%>', 
        'cancelImage':'<%=path%>/res/uploadify-v3.0.0/uploadify-cancel.png',
		'buttonImage' : '<%=path%>/res/uploadify-v3.0.0/liulan.jpg',
		'fileObjName'   : 'uploadFile',
		'fileTypeExts' : '${requestScope.type}',
		'multi' : true,
		'removeCompleted':true,
		'uploadLimit' : limit,
		'hideButton' : false,
		'queueID' : 'queueDiv',
		'width' : 73, //按钮宽带
        'height': 28,//按钮高度
        'removeTimeout' : 1,
		onUploadSuccess : function(file, data, response){
			//alert(decodeURI(data));
			//处理数据，解析json字符串
			
			
			
			var obj = loadXML(data);
			var htmlvalue = $('#filedata').html();
			var ids = '';
			$(obj).find('file').each(function(i){
				var namename = $(this).children("name").text();
				var idid = $(this).children("id").text();
				var random = getRandom();
				htmlvalue += '<div id="div'+random+'"><a href="javascript:window.location.href = \'<%=path%>/upload/download?id='+idid+'\'">'+namename+'</a>';
				if(from != 'view'){
					htmlvalue += '　　<a style="" href="javascript:delfujian(\''+idid+'\',\''+random+'\')">删除</a></div>';
				}
				ids = ids + idid + ',';
			});
			$('#ids').val($('#ids').val() + ids);
			$('#c004ids', parent.document).val($('#ids').val());
			$('#filedata').html(htmlvalue);
		}
	});
});

function delfujian(id , num){
	var url = '<%=path%>/upload/doDelete.action?ids='+id;
	var param = '';
	$.post(url,param,function(result){
		$('#div'+num).remove();
		var ids = $('#ids').val();
    	ids = ids.replace(id+',' , '');
    	$('#ids').val(ids);
    	$('#c004ids', parent.document).val($('#ids').val());
		// alert('删除附件成功！');
		parent.showMsg('删除附件成功！', 1);
	});
}

function getRandom(){
	var str = Math.random()+'';
	if(str.indexOf('.') >= 0){
		str = str.split('.')[1];
	}
	
	return str;
}

function uploadFile(){
	var htmlStr = $('#queueDiv').html();
	if(htmlStr != ''){
		$('#upload').uploadifyUpload();
	}else{
		// alert('请选择要上传的文件！');
		parent.showMsg('请选择要上传的文件！', MSG.WARNING); //gxq bug: #3797
	}
	
}

$(function(){
	$('#c004ids', parent.document).val($('#ids').val());
});

function loadXML(xmlString){  
	    var xmlDoc;  
	    if (window.ActiveXObject)  
	    {  
	        xmlDoc = new ActiveXObject('Microsoft.XMLDOM');  
	        if(!xmlDoc) xmldoc = new ActiveXObject("MSXML2.DOMDocument.3.0");  
	        xmlDoc.async = false;  
	        xmlDoc.loadXML(xmlString);  
	    }else if (document.implementation && document.implementation.createDocument)  
	    {  
	        //xmlDoc = document.implementation.createDocument('', '', null);   
	        //xmlDoc.load(xmlFile);   
	        var domParser = new DOMParser();  
	        xmlDoc = domParser.parseFromString(xmlString, 'text/xml');  
	    }else  
	    {  
	        return null;  
	    }  
	    return xmlDoc;  
	}

</script>
<html>
<body>
<s:form action="" id="" method="post" enctype="multipart/form-data"> 
    <div> 
	    <table border="0" id="asdfghjkl" cellspacing="0" cellpadding="0" style="border-collapse:collapse; border: solid 1px #93c1db ; font-size:12px; color:#565656; padding:0px; margin:0 auto; width:100%;}" > 
		    <c:if test="${requestScope.from != 'view' }">
		    <tr>
	   			<td class="tb_input"  scope="col"> 
		    		<div style="position:relative;">
				     	<s:file id="upload" name="uploadFile"/>
				     	<img style="position:absolute; top:0; left:75px; _left:0px;"  alt="上传" src="<%=path %>/res/uploadify-v3.0.0/shangchuan.jpg" 
				      			onclick="javascript:uploadFile()">
				    </div>
	  			 </td>
	    	</tr>
	    	</c:if>
	    	<tr>
			    <td nowrap="nowrap">
				    <div id="queueDiv" float: left; position: relative;"></div>
				    <div id="filedata" style="width:300px;float: left; position: relative;">
				    	<c:forEach items="${requestScope.list }" var="v" varStatus="status">
				    		 <div id="div${status.count }">
						  		 <a href="javascript:window.location.href = '<%=path%>/upload/download?id=${v.MA001 }';">${v.MA003 }</a>
						  		 <c:if test="${requestScope.from != 'view' }">
						  			 　　<a style="" href="javascript:delfujian('${v.MA001 }','${status.count }')">删除</a>
						  		 </c:if>
						  	 </div>
						</c:forEach>
				    </div>
			    </td>
		    </tr>
		</table>
	</div>
	
	<input type="hidden" name="ids" id="ids" value="${requestScope.ids }" />
</s:form>
</body>
</html>

