<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title><s:text name="修改申请" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,my97,validatorJS,utilJS,grid,commonJS)" executeResult="true"/>
<style type="text/css">
<!--
body,html {overflow-x:hidden; overflow-y:auto;
}

#aaa input{
	width: 16px;

}
#aaa{
	line-height: 30px;
	text-align: center;
	height:30px;
}
</style>
<script type="text/javascript">
function petition(){
	var url = $$.getContextPath()+"corpinfo/b104!doSaveAdd",
		param =$("#b104Add").serialize(),
		callback = function(data){
		showMsg(data.content,data.type,{buttons:{
            '确定':function(){
            	$(this).dialog('close');
            	location.reload();	
            }
        }});};
	$.post(url,param,callback);
}

function clickme(obj){
	if($(obj).attr("checked")){
		$(obj).next().removeAttr("disabled");
	}else{
		$(obj).next().attr("disabled","disabled");
	}
}
</script>
</head>
<body>

<form  id="b104Add" action="b104" method="post">
<div style="padding: 20px;">
<table border="0" cellspacing="0" cellpadding="4" class="tablecss1"
				style="width: 97%;" align="center" >
    <tr>
    	<th style="width: 10%">修改项：</th>
        <td id="aaa">
      
        <input type="checkbox" name="b104PO.ma007" value="B001" onclick="clickme(this)" />基本信息
            <input type="hidden" name="b104PO.ma006" value="基本信息" disabled="disabled"/>
          
            <input type="checkbox" name="b104PO.ma007" value="B002" onclick="clickme(this)"/>安全组织机构
			<input type="hidden" name="b104PO.ma006"value="安全组织机构" disabled="disabled"/>
			
           <input type="checkbox" name="b104PO.ma007" value="B005" onclick="clickme(this)"/>企业证照
			<input type="hidden" name="b104PO.ma006"value="企业证照" disabled="disabled"/>
						
            <input type="checkbox" name="b104PO.ma007" value="Tregulation" onclick="clickme(this)"/>规章制度
            <input type="hidden" name="b104PO.ma006"value="规章制度" disabled="disabled"/>
       


       <input type="checkbox" name="b104PO.ma007" value="E001" onclick="clickme(this)"/>培训计划
			<input type="hidden" name="b104PO.ma006"value="培训计划" disabled="disabled"/>
           <input type="checkbox" name="b104PO.ma007" value="S121" onclick="clickme(this)"/>安全生产记录
			<input type="hidden" name="b104PO.ma006" value="安全生产记录" disabled="disabled"/>
            <input type="checkbox" name="b104PO.ma007" value="S108" onclick="clickme(this)"/>隐患排查
			<input type="hidden" name="b104PO.ma006" value="隐患排查" disabled="disabled"/> 
			</td>
       </tr>
  		<tr>
  		<th>申请理由：</th>
        <td ><textarea name="b104PO.ma004" cols="90" rows="10"></textarea></td>
        </tr>
    </tr>
  
</table>
<br/>
<div style="padding-top: 2px; text-align: center;">
<a href="javascript:void(0)" onclick="petition()"><img src="images/shenqing.png"></a></div>

		</div>
</form>
</body>
</html>