<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title><s:text name="预案知识库" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>

<script type="text/javascript">
var gridOption = {
		caption:'预案知识库表',
		colModel:[
            {label:'', name:'objid', index:'objid',hidden:true,hidedlg:true},
			{label:'预案名称', name:'NAME', index:'NAME',formatter:showcontent},
			{label:'编制目的', name:'PURPOSE', index:'PURPOSE'},
			{label:'编制依据', name:'BASED', index:'BASED'},
			{label:'适用范围', name:'RANGE', index:'RANGE'}

		],
		pk:'objid',
		formId:'searchForm',
		winTitle:'政府端预案库'
};
var actionOption = {
		poName:'tcorpambPO',
		table:'tcorpamb'
	};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("请选择一条记录!",2,'信息');
	}else{
		$$.openDiv('showcont','预案详情',$$.getContextPath()+'/knowledge/tcorpamb!doView',{'tcorpambPO.objid':id},function(){
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
		<s:form action="tcorpamb" method="post" cssClass="formmargin"  id="searchForm"> 	
			<table border="0" cellspacing="8" cellpadding="0">
	        <tr>
	          <td align="right" style="width:50px;">预案名称 </td>
	          <td style="width:300px;"> <s:textfield name="tcorpambPO.name" cssClass="input2"/> </td>
	          
	          <td width="98"><s:submit value="" cssClass="search"/></td>
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