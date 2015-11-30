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
<title>	危化品知识库-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS,my97)" executeResult="true"/>
<script type="text/javascript">
var gridOption = {
		caption:'危化品知识库表',
		colModel:[
		    {label:'', name:'objid', index:'objid',hidden:true,hidedlg:true},
			{label:'化学品中文名称', name:'CHNNAME', index:'CHNNAME',formatter:showcontent},
			{label:'CAS No.', name:'CASNUM', index:'CASNUM'},
			{label:'技术说明书编码', name:'SUMING', index:'SUMING'},
			{label:'化学品英文名称', name:'ENGNAME', index:'ENGNAME'},
			{label:'化学品俗名或商品名', name:'TINFO', index:'TINFO'},
			{label:'化学品名称', name:'HXPNAME', index:'HXPNAME'},
			{label:'有害物成分', name:'INGREDIENT', index:'INGREDIENT'}
		],
		pk:'objid',
		formId:'searchForm',
		winTitle:'危化品知识库'
};
var actionOption = {
		poName:'tmsdsPO',
		table:'tmsds'
	};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','危化品详情',$$.getContextPath()+'knowledge/whp!view',{'tmsdsPO.objid':id},function(){
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
	<s:form action="whp!query" method="post" cssClass="formmargin" id="searchForm"> 	
		<table width="100%" border="0" cellspacing="8" cellpadding="0">
        <tr>
          <td align="right" style="width:90px;">化学品中文名称 </td>
          <td colspan="3"> <s:textfield name="tmsdsPO.chnname" cssClass="input2"/> </td>
          <td width="10">&nbsp;</td>
          <td  align="right" style="width:60px;">CAS No</td>
          <td colspan="3"><s:textfield name="tmsdsPO.casnum" cssClass="input2"/></td>
          <td width="1" rowspan="2">&nbsp;</td>
          <td width="98" rowspan="2"><s:submit value="" cssClass="search"/></td>
        </tr>
        <tr>
          <td align="right">填表时间</td>
          <td>
          	<s:textfield id="fillBeginDate" name="tmsdsPO.inputdate" cssClass="input2 Wdate" 
          		onclick="hwDatePicker({maxDate:$dp.$('fillEndDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})" />
          </td>
          <td width="23" align="center">至</td>
          <td>
          	<s:textfield id="fillEndDate" name="tmsdsPO.inputdateEnd" cssClass=" input2 Wdate" 
          		onclick="hwDatePicker({minDate:$dp.$('fillBeginDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
          </td>
          <td width="10">&nbsp;</td>
          <td align="right">生效时间</td>
          <td >
          	<s:textfield id="effectBeginDate" name="tmsdsPO.availabilitydate" cssClass="input2 Wdate" 
          		onclick="hwDatePicker({maxDate:$dp.$('effectEndDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})" />
          </td>
          <td width="23" align="center">至</td>
          <td> 
          	<s:textfield id="effectEndDate" name="tmsdsPO.availabilitydateEnd" cssClass="input2 Wdate" 
          		onclick="hwDatePicker({minDate:$dp.$('effectBeginDate').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})" />
          </td>
          </tr>
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