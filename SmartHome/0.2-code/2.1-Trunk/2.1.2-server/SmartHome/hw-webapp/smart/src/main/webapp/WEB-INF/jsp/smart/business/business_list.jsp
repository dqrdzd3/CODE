<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title>	业务授权-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
 <script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>  
<script type="text/javascript">
var gridOption = {
		id : 'gridTable',
		caption:'To B 业务',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'公司名', name:'ma002', index:'ma002',formatter:showcontent},
			{label:'联系人', name:'ma003', index:'ma003'},
			{label:'登录帐号', name:'ma009', index:'ma009'},
			{label:'邮箱', name:'ma014', index:'ma014'},
			{label:'创建时间', name:'ma015', index:'ma015'}
		],
		formId:'searchForm',
		winTitle:'业务授权'
};

var actionOption ={init:true };

$(function(){
    $$.bindButton('view',function() {
    	var url = $$.getContextPath()+'smart/business!doView';
    	$$.getFrame('rightFrame').Tabs.openTab("titleid", "业务授权浏览",url, "业务授权浏览 ", true);
    }, 'operating');
		
		
    $$.bindButton('add',function(){
        var divId = 'showd',
            title = '业务授权',
            url = $$.getContextPath()+'smart/business!doAdd',
            param = '',
            callback=function(){};
        $$.openDiv(divId, title, url, param, callback);
    });   
			    
    $$.bindButton('edit',function(){
        var gridId="#gridTable";
        if(!$$.checkSelRow(1)) return;
        var ids = getSelectedRowID(gridId);
        var id = ids[0];
	    var divId = 'showd',
	        title = '业务授权',
	        url = $$.getContextPath()+'smart/business!doEdit',
	        param = 'businessPO.ma001='+id,
	        callback=function(){};
	    $$.openDiv(divId, title, url, param, callback);
    });	
    $$.bindButton('delete',function(){
         var gridId="gridTable";
         if ($$.checkSelRow(0)) return;
         var ids = getSelectedRowID("#"+gridId);
	     var url = $$.getContextPath()+'smart/business!doDelete',
	         param = 'ids='+ids.join(),
	         callback = function(data){
                  var buttons = {
                    '确定':function(){
                        reloadGrid(gridId);
                        $(this).dialog('close');
                    }
                };
                showMsg(data.content, data.type, {title:data.title,buttons:buttons});
             };
	         
    	showMsg('确定要删除所选的记录吗？',MSG.INFO,{buttons:{
             '取消':function(){
                 $(this).dialog('close');
             },
             '确定':function(){
                 $.post(url,param,function(data){
                         callback(data);
                     });
                 
             }
         }});
    });	
  
});

function closeshowd(){
	$("#showd").dialog('close');
} 



function showcontent(celv, opts, obj) {
    return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+ obj[0] + '\')">' + celv + '</a>';
}

function showContentById(id) {
    if (id == null || id == '') {
        showMsg("请选择一条记录!", 2);
    } else {
        $$.openDiv('showd', '业务授权信息查询',$$.getContextPath()+'smart/business!doShow', {'businessPO.ma001' : id});
    }
}  
 
function saveS001(){
	if($('#s001Add').valid()){
		var param = $('#s001Add').serialize();
		var url = $$.getContextPath()+'smart/business!doSaveAdd';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  				reloadGrid("gridTable");
	  			}}
	   		});
		});
	};
}

function modifyS001(){
	if($('#s001Edit').valid()){
		var param = $('#s001Edit').serialize();
		var url = $$.getContextPath()+'smart/business!doSaveEdit';
		$.post(url,param,function(data){
			showMsg(data.content, data.type, {
	  		buttons:{
	  			'确定' : function(){
	  				closeshowd();
	  				$(this).dialog('close');
	  				reloadGrid("gridTable");
	  			}}
	   		});
		});
	};
}

function uploadImage(pic,fileid) {  
    //判断是否有选择上传文件  
      // var imgPath = $("#file").val();  
       var imgPath = $('#'+fileid).val();
        if (imgPath == "") {  
            alert("请选择上传图片！");  
            return;  
        } 
        //alert(imgPath);
        //判断上传文件的后缀名  
        var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1).toLowerCase();  
        if (strExtension != 'jpg' && strExtension != 'gif'  
        && strExtension != 'png' && strExtension != 'bmp') {  
            alert("请选择图片文件");  
            return;  
        }  
        
        /* 	$("#loading").ajaxStart(function() {
		$(this).show();
	}) //开始上传文件时显示一个图片
	.ajaxComplete(function() {
		$(this).hide();
	});//文件上传完成将图片隐藏起来
*/
	$.ajaxFileUpload( {
		url : $$.getContextPath()+'smart/business!uploadPic',//用于文件上传的服务器端请求地址
		secureuri : false,//一般设置为false
		fileElementId : fileid,//'file',//文件上传控件的id属性
		dataType : 'json',//返回值类型 一般设置为json
		success : function(data, status) //服务器成功响应处理函数
		{
			//alert(data);
			var obj = eval('(' + data + ')');
			//alert(obj.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
			 $("#preImg_"+pic).attr("src","data:image/png;base64,"+obj.data);  
			$("#id_"+pic).val(obj.message);
			
		},
		error : function(data, status, e)//服务器响应失败处理函数
		{
			alert(e);
		}
	})
	return false;
} 




</script>
</head>

<body>
	<div id="searcharea">
		<div id="searcharealeft">
			<div id="searcharearight">
			<s:form action="business" method="post" cssClass="formmargin" id="searchForm"> 
				<table border="0" cellspacing="8" cellpadding="0">
			        <tr>
			          <td align="right" style="width:76px;">公司名</td>
			          <td style="width:300px;"> <s:textfield name="businessPO.ma002" cssClass="input2"/> </td>
			          <td width="98"><s:submit value="" cssClass="search"/></td>
			        </tr>
			     </table>
			</s:form>
		</div>
		</div>
	</div>
	<div id="hiddlebutton">
		<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
	</div>
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div class="tablestyle">
		<table id="gridTable"></table>
	</div>
	<div id="showd"></div>
</body>
</html>