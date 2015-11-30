<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title>	危化品信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>

<script type="text/javascript">
var gridOption = {
		id:'gridTable',
		caption:'危化品信息',
		formId:'searchForm',
		colModel:[
            {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'中文名称', name:'MA004', index:'MA004',formatter:showcontent},
			{label:'中文名称', name:'MA004Name', index:'MA004',hidden:true,hidedlg:true},
			{label:'别名', name:'MA005', index:'MA005'},
			{label:'货物编号', name:'MA006', index:'MA006'},
			{label:'数量', name:'MA007', index:'MA007'},
			{label:'产品类型', name:'MA008', index:'MA008'},
			{label:'定位2',name:'islabel2',index:'islabel',hidden:true,hidedlg:true},
			{label:'定位',name:'islabel',index:'islabel',formatter:progress}
		],
		pk:"MA001",
		winTitle:"危化品信息"
};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','企业安全机构详情',$$.getContextPath()+'/corpinfo/b012!view',{'b012PO.MA001':id},function(){
			$$.clearInput('#showcont');
			$('#b012addform *').attr('disabled',false);
		});
	}
};
function closediv(id){
	jQuery("#"+id).dialog('close');
}
//celv,列值；opts：colmod；obj表示行对象
function progress(celv, opts, obj) {
	if (celv == '1')
		return '<div ><img onclick="openShowMap(\''
				+ obj[0]
				+ '\')" src="<s:url value="/"/>images/location.png" /></div>';
	else
		return '<div ><img onclick="openEditMapOPT()" src="<s:url value="/"/>images/nolocation.png" /></div>';

}


//需要修改传递参数：企业ID，企业Name，危险源1的数值，危险源3的数值。目前还没有传递参数。corpid,corpname,wxy1num,wxy2num,wxy3num
function openShowMap(corpid) {
	$$.getFrame('rightFrame').Tabs.openTabMap("mapgisshowid", "地图展示页面",
			"map/map_right!mapDingwei.action?layertype='whpLayer'&codeid='"
						+ corpid + "'", null, true);

}
//需要修改传递参数：企业ID，企业Name，危险源1的数值，危险源3的数值。目前还没有传递参数。corpid,corpname,wxy1num,wxy2num,wxy3num
function openEditMapOPT() {
	 showMsg("请先进行标注，再定位！",4,"");
	// $$.getFrame('rightFrame').Tabs.openTab("mapgiseditid","地图标注页面","frame/map_right!mapEdit.action?corpid="+corpid,"地图模式 "); 
}

function openEditHWMap(){
		var corpid=jQuery('#gridTable').jqGrid("getGridParam", "selarrrow");
		var corpname = jQuery('#gridTable').jqGrid('getCell',corpid,'MA004Name');
		var codetype="WHP";
		var islabel = jQuery('#gridTable').jqGrid('getCell',corpid,'islabel2');
		var codelevel="";
		 var i=jQuery('#gridTable').jqGrid("getGridParam", "selarrrow").length;
		 if(i==0){
			 showMsg("请选择待标注的记录!",4,"");
			 return;
		 }else if(i>1){
			 showMsg("只能选择一条记录!",4,"");
			 return;
		 }

		//如果已经标注，则不可以再次标注						
		 if(islabel=='1'){
			 showMsg("已经标注过，不可再次标注!",4,"");
			 return;
		 }
		$$.getFrame('rightFrame').Tabs.openTab("mapgiseditid", "地图标注页面",
				"map/map_right!mapEdit.action?layertype='whpLayer'&&codeid='"
						+ corpid + "'&&codename='"
						+ encodeURI(encodeURI(corpname)) + "'"
						+"&&codetype='"+codetype+"'&&codelevel='"+codelevel+"'", "地图模式 ");
	}
	
	
</script>
</head>
<body>
<div>
<div id="searcharea">
  <div id="searcharealeft">
    <div id="searcharearight">
	<s:form action="b012!query" method="post" cssClass="formmargin" id="searchForm"> 	
		<table border="0" cellspacing="8" cellpadding="0">
        <tr>
          <s:hidden name="b012PO.MA003"/>
          <td align="right" style="width:50px;">中文名称 </td>
          <td colspan="1" style="width:200px;"> <s:textfield name="b012PO.MA004" cssClass="input2"/> </td>
          <td align="right" style="width:80px;">危险货物编号 </td>
          <td colspan="1" style="width:200px;"> <s:textfield name="b012PO.MA006" cssClass="input2"/> </td>
		  <td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
      </table>
	</s:form>
	    </div>
  </div>
</div>
<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete,editmap"/>
	</div>
	<div id="showcont"></div>
</div>
</body>
</html>