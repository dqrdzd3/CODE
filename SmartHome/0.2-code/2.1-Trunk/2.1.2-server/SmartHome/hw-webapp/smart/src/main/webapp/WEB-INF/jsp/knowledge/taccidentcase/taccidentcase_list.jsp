<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:text name="事故案例知识库" />-<s:text name="application.title" /></title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>

<script type="text/javascript">
var gridOption = {
		caption:'事故案例知识库',
		colModel:[
            {label:'', name:'objid', index:'objid',hidden:true,hidedlg:true},
			{label:'事故名称', name:'name', index:'name',formatter:showcontent},
			{label:'事故发生时间', name:'createtime', index:'createtime'},
			{label:'事故发生地点', name:'address', index:'address'},
			{label:'事故类型', name:'type', index:'type'},
			{label:'事故行业', name:'casetrade', index:'casetrade'},
			{label:'事故模型', name:'module', index:'module'}

		],
		pk:'objid',
		
		formId:'searchForm',
		winTitle:'事故案例库'
};
var actionOption = {
		poName:'taccidentcasePO',
		table:'taccidentcase'
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("请选择一条记录!",2,'信息');
	}else{
		$$.openDiv('showcont','事故案例详情',$$.getContextPath()+'/knowledge/accident!doView',{'taccidentcasePO.objid':id},function(){
			$$.clearInput('#showcont');
		});
	}
};
</script>
</head>
<body>
	<div>
	<div id="searcharea">
	  <div id="searcharealeft">
	    <div id="searcharearight">
			<s:form action="accident" method="post" cssClass="formmargin" id="searchForm"> 	
				<table  border="0" cellspacing="8" cellpadding="0">
		        <tr>
		          <td align="right" style="width:50px;"><s:text name="事故名称" /> </td>
		          <td style="width:300px;"> <s:textfield name="taccidentcasePO.name" cssClass="input2"/> </td>
		                  
		          <td width="98" ><s:submit value="" cssClass="search"/></td>
		        </tr>
		      </table>
			</s:form>
		    </div>
	  </div>
	</div>
	<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
		<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
</div>
<div id="showcont"></div>
</body>
</html>