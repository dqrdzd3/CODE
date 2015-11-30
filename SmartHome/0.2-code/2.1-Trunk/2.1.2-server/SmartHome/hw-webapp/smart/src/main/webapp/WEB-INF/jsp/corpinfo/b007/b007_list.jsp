<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<title>	安全投入信息-<s:text name="application.title" /></title>

<script type="text/javascript">
var gridOption = {
		caption:'安全投入信息',
		formId:'searchForm',
		colModel:[
            {label:'', name:'ma001',hidden:true, index:'ma001',hidedlg:true},
			{label:'投入编号', name:'ma003', index:'ma003',formatter:showcontent},
			{label:'单位名称', name:'ma004', index:'ma004'},
			{label:'投入年度', name:'ma005', index:'ma005'},
			{label:'投入资金概算', name:'ma006', index:'ma006'},
			{label:'同期年总收入', name:'ma007', index:'ma007'}
		],
		pk:"ma001",
		winTitle:"安全投入信息"
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','安全投入详情',$$.getContextPath()+'/corpinfo/b007!view',{'b007PO.ma001':id},function(){
			$$.clearInput('#showcont');
			$('#b007addform *').attr('disabled',false);
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
	<s:form action="b007" method="post" cssClass="formmargin" id="searchForm"> 	
		<table border="0" cellspacing="8" cellpadding="0">
        <tr>
          <td align="right" style="width:50px;" nowrap="nowrap">投入编号</td>
          <td colspan="1" style="width:300px;"> <s:textfield name="b007PO.ma003" cssClass="input2"/> </td>
		   <td align="right" style="width:50px;" nowrap="nowrap">单位名称</td>
          <td colspan="1" style="width:300px;"> <s:textfield name="b007PO.ma004" cssClass="input2"/> </td>
		  <td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
      </table>
	</s:form>
	    </div>
  </div>
</div>
<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
</div>

<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
<div id="showcont"></div>
</body>
</html>