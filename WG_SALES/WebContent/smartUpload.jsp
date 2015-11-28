<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<%@ page contentType="text/html; charset=utf-8" import="com.jspsmart.upload.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!--  <script src="performance/excel/sweetalert.min.js" type="text/javascript"></script>
 <script src="performance/js/jquery-1.11.3.js" type="text/javascript"></script> -->
</head>
<body>
<!-- <script type="text/javascript">
function check(){
	alert("文件上传成功!");
}
window.onload=check(); 
</script> -->
<!-- 读取桌面路径的包 -->
<%@page import="javax.swing.filechooser.FileSystemView" %>
<%   
SmartUpload smart=new SmartUpload();  
smart.initialize(pageContext);// 初始化上传操作  
//smart.setAllowedFilesList(".xls");
smart.upload();       //上传准备  
// int count = smart.save("upload");  //文件保存  

FileSystemView fsv = FileSystemView.getFileSystemView();
String deskPath = fsv.getHomeDirectory().toString(); 

com.jspsmart.upload.SmartFile file=smart.getFiles().getFile(0); 
//file.saveAs("upload/upload.xls",0); 
file.saveAs(deskPath+"/upload.xls",0);
// out.println(count+"个文件上传成功！<br>");  
request.getRequestDispatcher("/WEB-INF/sales/salesIndex.jsp").forward(request, response); 
//out.println("<script>alert('文件上传成功!')</script>");
%> 
</body>
</html>