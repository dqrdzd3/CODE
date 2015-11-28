<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("basePath="+basePath);
%>
<base href="<%=basePath%>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"> 
function loadXML(){ 
  var xmlDoc; 
  try{ 
    //IE 
    xmlDoc=new ActiveXObject("Microsoft.XMLDOM"); 
  }
  catch(e){ 
    try{ 
      xmlDoc = document.implementation.createDocument("","",null); 
    }
    catch(e){ 
      alert(e.message); 
      return; 
    } 
  } 
  xmlDoc.async=false; 
  xmlDoc.load("<%=basePath%>resource/company_name.xml"); 
  return xmlDoc; 
} 
//网页加载完在加载 完成省份加载 
onload=function(){ 
  var xmlDocument = loadXML(); 
  var typeArr =xmlDocument.getElementsByTagName("type"); 
  var proSize = typeArr.length; 
  for(var i=0;i<proSize;i++){ 
    //创建option节点 
    var optionElement = document.createElement("option"); 
    var typeName = typeArr[i].getAttribute("name"); 
    //创建文本节点 
    var textElement =document.createTextNode(typeName); 
    optionElement.appendChild(textElement); 
    optionElement.setAttribute("value", typeName); 
    var node = document.getElementById("type"); 
    node.appendChild(optionElement); 
  } 
} 
//省份改变事件 
function changetype(node){ 
  //获取选择的角标 
  var index = node.selectedIndex; 
  //获取对应的省份名 
  var typeName = node.options[index].value; 
  loadcompanys(typeName); 
} 
 
//根据省份编号加载城市信息 
function loadcompanys(proName){ 
  var xmlDocument = loadXML(); 
  var typeArr =xmlDocument.getElementsByTagName("type"); 
  //获取城市的元素 
  var companySelectEle = document.getElementById("companys"); 
  var size = companySelectEle.options.length; 
  for(var i=size;i>0;i--){ 
    companySelectEle.remove(i); 
  } 
 
  //获取省份的个数 
  var proSize = typeArr.length; 
  var proElement; 
  //获取对应的省份元素 
  for(var i=0;i<proSize;i++){ 
    if(typeArr[i].getAttribute("name")==proName){ 
      proElement = typeArr[i]; 
      break; 
    } 
  } 
  //获取省份的城市信息 
  var companysArr = proElement.getElementsByTagName("company"); 
  var len = companysArr.length; 
  for(var i=0;i<len;i++){ 
    //创建option节点 
    var optionElement = document.createElement("option"); 
    //获取城市名 
    var companyName = companysArr[i].firstChild.nodeValue; 
    //创建文本节点 
    var textElement =document.createTextNode(companyName); 
    optionElement.appendChild(textElement); 
    optionElement.setAttribute("value", companyName); 
    companySelectEle.appendChild(optionElement); 
  } 
} 
function getValue(){ 
  var pro = document.getElementById("type").value; 
  var company = document.getElementById("companys").value; 
  alert(pro+":"+company); 
} 
</script> 
</head> 
 
<body> 
<select id="type" onchange="changetype(this)"> 
<option value="" selected="selected">--分销商类型--</option> 
</select> 
<select id="companys"> 
<option value="" selected="selected">--公司--</option> 
</select> 
<input type="button" value="弹出" onclick="getValue()"/> 
</body> 
</html>