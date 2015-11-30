<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		//alert(parent.document.getElementById('xsq'));
		var divobj = parent.document.getElementById('xsqimg');
		
		//上传文件的文件名
		var fileName = '${requestScope.fileName}';
		//上传文件的扩展名
		var extName = '${requestScope.extName}';
		//上传文件的路径
		var filePath = '${requestScope.filePath}';
		//上传文件的swf路径
		var swfPath = '${requestScope.swfPath}';
		
		var str = '';
		if(extName == 'pdf'){
			str += '<dl><dt><img src="images/pdf.png"  />';
		}
		else if(extName == 'doc' || extName == 'docx'){
			str += '<dl><dt><img src="images/word.png"  />';
		}
		else if(extName == 'txt'){
			str += '<dl><dt><img src="images/txt.png"  />';		
		}
		else if(extName == 'wav'){
			str += '<dl><dt><img src="images/wav.png"  />';
		}
		else{
			str += '<dl><dt><img src="'+filePath+'"  />';
		}
		str += '</dt><dd>'+fileName+'</dd><dd><input name="" type="checkbox" value="'+swfPath+'" /></dd></dl>';
		
		divobj.innerHTML = str + divobj.innerHTML;
	};
</script>
</head>
<body>
</body>
</html>