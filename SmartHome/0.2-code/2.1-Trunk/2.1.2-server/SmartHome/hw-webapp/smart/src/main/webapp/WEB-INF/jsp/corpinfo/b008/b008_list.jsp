<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<title>	安全评价信息-<s:text name="application.title" /></title>

<script type="text/javascript">
var gridOption = {
		caption:'安全评价信息',
		formId:'searchForm',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'企业名称', name:'ma003', index:'ma003'},
			{label:'评价名称', name:'ma004', index:'ma004',formatter:showcontent},
			{label:'评价单位', name:'ma005', index:'ma005'},
			{label:'评价时间', name:'ma006', index:'ma006'},
			{label:'评价内容', name:'ma007', index:'ma007'}
		],
		pk:"ma001",
		winTitle:"安全评价信息"
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','安全评价详情',$$.getContextPath()+'/corpinfo/b008!view',{'b008PO.ma001':id},function(){
			$$.clearInput('#showcont');
			$('#b008addform *').attr('disabled',false);
		});
	}
};
function closediv(id){
	jQuery("#"+id).dialog('close');
}

</script>
</head>
<body>
<div>
<div id="searcharea">
  <div id="searcharealeft">
    <div id="searcharearight">
	<s:form action="b008" method="post" cssClass="formmargin" id="searchForm"> 	
		<s:hidden name="b008PO.ma002"/>
		<table border="0" cellspacing="8" cellpadding="0">
        <tr>
          <td align="right" style="width:50px;" nowrap="nowrap">评价名称</td>
          <td colspan="1" style="width:200px;"> <s:textfield name="b008PO.ma004" cssClass="input2"/> </td>
		  <td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
      </table>
	</s:form>
	    </div>
  </div>
</div>
<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
	<div id="showcont"></div>
</div>
</body>
</html>