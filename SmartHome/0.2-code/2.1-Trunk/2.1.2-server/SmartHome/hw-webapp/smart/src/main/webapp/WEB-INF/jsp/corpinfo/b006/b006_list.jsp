<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid,commonJS)" executeResult="true"/>
<title>	企业周边环境信息-<s:text name="application.title" /></title>

<script type="text/javascript">

var gridOption = {
		id:'gridTable',
		caption:'企业周边环境信息',
		formId:'searchForm',
		url : 'corpinfo/b006!queryData.action',
		colModel:[
           {label:'', name:'ma001', index:'ma001',hidden:true,hidedlg:true},
			{label:'企业名称', name:'ma003', index:'ma003'},
			{label:'方位', name:'ma004', index:'ma004',formatter:showcontent},
			{label:'单位名称', name:'ma006', index:'ma006'},
			{label:'离企业距离', name:'ma007', index:'ma007'},
			{label:'人员数量', name:'ma011', index:'ma011'},
			{label:'定位2',name:'islabel2',index:'islabel',hidden:true,hidedlg:true},
			{label:'定位',name:'islabel',index:'islabel',formatter:progress}
		],
		pk:"ma001",
		winTitle:"企业周边环境信息"
};
var actionOption={
	       beforeAdd : function(form) {
	    	   $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002=")
			},
	         afterFillForm : function(id, jsonPO, form) {
	        	 var id=jsonPO.ma001;
				 $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002="+id)
			}
	};
function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
};
function showContentById(id){
	if(id == null || id == ''){
		showMsg("至少应该选择一条记录!",5,'错误');
	}else{
		$$.openDiv('showcont','企业周边环境详情',$$.getContextPath()+'/corpinfo/b006!view',{'b006PO.ma001':id},function(){
			$$.clearInput('#showcont');
			$('#b006addform *').attr('disabled',false);
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

//以下是GIS标注的函数
function openShowMap(corpid) {
	$$.getFrame('rightFrame').Tabs.openTabMap("mapgisshowid", "地图展示页面",
			"map/map_right!mapDingwei.action?layertype='envLayer'&codeid='"
						+ corpid + "'", null, true);
	
}
function openEditMapOPT() {
	 showMsg("请先进行标注，再定位！",2,"");
}
//GIS标注函数
function openEditHWMap(){
		var corpid=jQuery('#gridTable').jqGrid("getGridParam", "selarrrow");
		var corpname = jQuery('#gridTable').jqGrid('getCell',corpid,'ma006');
		var codetype="ENV";
		var islabel = jQuery('#gridTable').jqGrid('getCell',corpid,'islabel2');
		var codelevel="";
		 var i=jQuery('#gridTable').jqGrid("getGridParam", "selarrrow").length;
		 if(i==0){
			 showMsg("请选择待标注的记录!",2,"");
			 return;
		 }else if(i>1){
			 showMsg("只能选择一条记录!",2,"");
			 return;
		 }

		//如果已经标注，则不可以再次标注						
		 if(islabel=='1'){
			 showMsg("已经标注过，不可再次标注!",2,"");
			 return;
		 }
		$$.getFrame('rightFrame').Tabs.openTab("mapgiseditid", "地图标注页面",
				"map/map_right!mapEdit.action?layertype='envLayer'&&codeid='"
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
	<s:form action="b006" method="post" cssClass="formmargin" id="searchForm"> 	
		<s:hidden name="b006PO.ma002"></s:hidden>
		<table border="0" cellspacing="8" cellpadding="0">
        <tr>
          <td align="right" style="width:35px;" nowrap="nowrap">方位</td>
          <td colspan="1" style="width:300px;"> 
		       <s:select list="#{'东':'东','南':'南','西':'西','北':'北','东南':'东南','东北':'东北','西南':'西南','西北':'西北'}" name="b006PO.ma004"   headerKey="" headerValue="请选择" ength="25" ></s:select>
          </td>
		  <td width="98" rowspan="2"><s:submit value="" cssClass="search" /></td>
      </table>
	</s:form>
	    </div>
  </div>
</div>
<div id="hiddlebutton"><a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a></div>
</div>
<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete,editmap"/>
	</div>
	<div id="showcont"></div>
</body>
</html>