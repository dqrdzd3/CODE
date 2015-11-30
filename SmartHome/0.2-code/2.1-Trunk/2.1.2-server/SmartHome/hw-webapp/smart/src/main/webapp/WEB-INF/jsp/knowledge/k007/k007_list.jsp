<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>"/>
<title><s:text name="预案知识库" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript" src='<s:url value="/js/util/links.js"></s:url>'></script>
<script type="text/javascript" src="<%=basePath%>res/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
var gridOption = {
		caption:'企业预案库',
		colModel:[
            {label:'', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
			{label:'预案名称', name:'MA004', index:'MA004',formatter:showcontent},
			{label:'预案类型', name:'yalxname', index:'yalxname'},
			{label:'事故类型', name:'MA006', index:'MA006'},
			{label:'事故类型子类', name:'MA007', index:'MA007'}
		],
		pk:'MA001',
		formId:'searchForm',
		winTitle:'预案库'
};
var actionOption = {
		 beforeAdd : function(form) {
	    	   $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002=")
			},
	         afterFillForm : function(id, jsonPO, form) {
	        	 var id=jsonPO.MA001;
				 $("#img",form).attr("src","<%=path%>/upload/fileupload!doAddupload?MA002="+id)
			},
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("请选择一条记录!",2,'信息');
	}else{
		$$.openDiv('showcont','预案库详情',$$.getContextPath()+'/knowledge/k007!doView',{'k007PO.MA001':id},function(data){
		$$.clearInput('#showcont');
		});
	}
};
function closeDiv(){
	$('#operating_input_form').dialog('close');
};
function getTemplate()
{
	var oFCKeditor = null;
    oFCKeditor = new FCKeditor("k007PO.MA009");
    oFCKeditor.BasePath = $$.getContextPath()+"res/fckeditor/";
    oFCKeditor.ToolbarSet="Document";
    oFCKeditor.Width="100%";
    oFCKeditor.Height="200";
    oFCKeditor.ReplaceTextarea();
    
	var yaType=	window.document.getElementById("k007PO.MA005").value;   
	document.getElementById("k007PO.MA009").value="";
	if(yaType=="zonghe_yjya"){
		var str="<p><FONT SIZE='5'><B>1 总则</B></FONT><br><FONT SIZE='4'><B>1.1 编制目的</B></FONT><br><br><br><br><FONT SIZE='4'><B>1.2 编制依据</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>1.3 适用范围</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>1.4 应急预案体系</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>1.5 应急工作原则</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>2 生产经营单位的危险性分析</B></FONT><br>";
		str+="<FONT SIZE='4'><B>2.1 生产经营单位概况</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>2.2 危险源与风险分析</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>3 组织机构及职责</B></FONT><br>";
		str+="<FONT SIZE='4'><B>3.1 应急组织体系</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>3.2 指挥机构及职责</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>4 预防与预警</B></FONT><br>";
		str+="<FONT SIZE='4'><B>4.1 危险源监控</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>4.2 预警行动</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>4.3 信息报告与处置</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>5 应急响应</B></FONT><br>";
		str+="<FONT SIZE='4'><B>5.1 响应分级</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>5.2 响应程序</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>5.3 应急结束</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>6 信息发布</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>7 后期处置</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>8 保障措施</B></FONT><br>";
		str+="<FONT SIZE='4'><B>8.1 通信与信息保障</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>8.2 应急队伍保障</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>8.3 应急物资装备保障</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>8.4 经费保障</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>8.5 其他保障</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>9 培训与演练</B></FONT><br>";
		str+="<FONT SIZE='4'><B>9.1 培训</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>9.2 演练</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>10 奖惩</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='5'><B>11 附则</B></FONT><br>";
		str+="<FONT SIZE='4'><B>11.1 术语和定义</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>11.2 应急预案备案</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>11.3 维护和更新</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>11.4 制定与解释</B></FONT><br><br><br><br>";
		str+="<FONT SIZE='4'><B>11.5 应急预案实施</B></FONT><br><br><br><br></p>";
		document.getElementById("k007PO.MA009").value =str;
			
	}
	if(yaType=="zhuanxiang_yjya"){
		var strContent="<p><FONT SIZE='5'><B>1 专项应急预案的主要内容</B></FONT><br><FONT SIZE='4'><B>1.1 事故类型和危害程度分析</B></FONT><br><br><br><br><FONT SIZE='4'><B>1.2 应急处置基本原则</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>2 组织机构及职责</B></FONT><br>";
		strContent+="<FONT SIZE='4'><B>2.1 应急组织体系</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='4'><B>2.2 指挥机构及职责</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>3 预防</B></FONT><br>";
		strContent+="<FONT SIZE='4'><B>3.1 危险源监控</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>4 预警</B></FONT><br>";
		strContent+="<FONT SIZE='4'><B>4.1 预警行动</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>5 信息报告程序</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>6 应急处置</B></FONT><br>";
		strContent+=+"<FONT SIZE='4'><B>6.1 响应分级</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='4'><B>6.2 响应程度</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='4'><B>6.3 处置措施</B></FONT><br><br><br><br>";
		strContent+="<FONT SIZE='5'><B>7 应急物资与装备保障</B></FONT><br><br><br><br></p>";
		document.getElementById("k007PO.MA009").value=strContent;
	}
	if(yaType=="xianchang_yjya"){
		var strCon="<p><FONT SIZE='5'><B>1 事故特征</B></FONT><br><br><br><br>";
		strCon+="<FONT SIZE='5'><B>2 应急组织与职责</B></FONT><br><br><br><br>";
		strCon+="<FONT SIZE='5'><B>3 应急处置</B></FONT><br><br><br><br>";
		strCon+="<FONT SIZE='5'><B>4 注意事项</B></FONT><br><br><br><br></p>";
		document.getElementById("k007PO.MA009").value=strCon;
	}
	
	
	FCKeditorAPI.GetInstance("k007PO.MA009").SetHTML(document.getElementById("k007PO.MA009").value);

}
</script>
</head>
<body> 
	<div>
	<div id="searcharea">
	  <div id="searcharealeft">
	    <div id="searcharearight">
			<s:form action="k007" method="post" cssClass="formmargin"  id="searchForm"> 	
				<table border="0" cellspacing="8" cellpadding="0">
		        <tr>
		          <td align="right" style="width:50px;" nowrap="nowrap"><s:text name="预案名称" /> </td>
		          <td style="width:200px;"> <s:textfield name="k007PO.MA004" cssClass="input2"/> </td>
		          
		          <td  align="right" style="width:50px;" nowrap="nowrap">预案类型</td>
		          <td style="width:200px;"><s:select list="yazlList" name="k007PO.MA005" listKey="value" listValue="name"  ength="25" headerKey="" headerValue="请选择" id="MA005"></s:select></td>
		         
		          <td width="98" rowspan="2"><s:submit value="" cssClass="search"/></td>
		        </tr>
		      </table>
			</s:form>
		    </div>
	  </div>
	</div>
	<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
	<div class="operating" id="operating"><hwsoft:operation code="${code}" param="add,edit,delete"/></div>
</div>
	<div id="showcont"></div>
</body>
</html>