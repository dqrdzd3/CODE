<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板index</title>
<s:action name="include(mainCSS,jquery,pageJS1.1,my97,tableJS,tabJS,popupDialog)"  executeResult="true"></s:action>
<script type="text/javascript"  src='<s:url value="/js/template/template.js"></s:url>'></script>
<script type="text/javascript">
// function switchTab(url,param,i,o){
// 	var url,param;
// 	url = url,param= param;
// 	$.post(url,param,function(data){
// 		$("div[id^=tabDiv]").hide().empty();
// 		$("#tabDiv"+i).html(data).show();
		
// 	});
// 	$.selectTag('tagContent'+i,o);
// }

$(function(){
    $.hwBindBtn('add',$.template.add,'operating');
    $.template.loadMasterTb();
	
});
</script>
</head>
<body>
<!-- 主表start -->
<!-- 搜索区域 -->
<div id="searcharea" >
	<div id="searcharealeft">
		<div id="searcharearight">
			<form action="" method="post" id="queryFrom">
				<table border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td style="width:80px;"align="right">姓名：</td>
						<td colspan="3" style="width:200px;">
                           <s:textfield name="testuiPO.name" class="input2" />
						</td>
						<td width="98" rowspan="2"><input type="button" onclick="$.template.loadMasterTb()" class="search"/></td>
					</tr>
				</table>
				<!-- 默认第一页，刷新，查询等时候用，必须有,id勿改，name勿改 -->
				<input type="hidden" name="curPage" id="curPage" value="1" />
				<!-- 排序字段，必须有,id勿改，name勿改 -->
				<input type="hidden" name="column" id="column" value="${requestScope.column }" />
				<!-- 排序方式，必须有,id勿改，name勿改 -->
				<input type="hidden" name="sort" id="sort" value="${requestScope.sort }" />
				<!-- 每页显示多少条，必须有 ,id勿改，name勿改-->
				<input type="hidden" name="pagesize" id="pagesize" value="${requestScope.page.number }" />
			</form>
		</div>
	</div>
</div>
<!-- 查询区域控制显示隐藏的箭头 -->
<div id="hiddlebutton">
	<a href="javascript:$.hwTb.showHidden()"><img src="images/searchhiddleicon.jpg" id="search_img" width="71" height="13" /></a>
</div>
<!-- 操作按钮 -->
<div id="operating" class="operating">
	<hwsoft:operation code="${code}" param="add,edit,delete"/>
</div>
<div id="masterTb">

</div>

<!-- 主表end -->

	<!-- 从表start -->
		<DIV id=con style="display: none;">
			<UL id=tags>
				<LI class=selectTag><A onClick="$.template.switchTab(0,this)"
					href="javascript:void(0)" onfocus="this.blur()">从表1</A></LI>
				<LI><A onClick="$.template.switchTab(1,this)"
					href="javascript:void(0)" onfocus="this.blur()">从表2</A></LI>
				<LI><A onClick="$.template.switchTab(2,this)"
					href="javascript:void(0)" onfocus="this.blur()">从表3</A></LI>
<!-- 				<LI><A onClick="switchTab('public/popup!list',{method:'list1',pageSize:10},0,this)" -->
<!-- 					href="javascript:void(0)" onfocus="this.blur()">从表1</A></LI> -->
<!-- 				<LI><A onClick="switchTab('public/popup!list',{method:'list2',pageSize:5},1,this)" -->
<!-- 					href="javascript:void(0)" onfocus="this.blur()">从表2</A></LI> -->
<!-- 				<LI><A onClick="switchTab('public/popup!list',{method:'list3',pageSize:15},2,this)" -->
<!-- 					href="javascript:void(0)" onfocus="this.blur()">从表3</A></LI> -->
			</UL>
			<DIV id=tagContent>
				<s:iterator var="menuCode" value="codes" status="s">
					<s:if test="#s.index == 0">
						<DIV id="tagContent${s.index}" class="tagContent selectTag">
					</s:if>
					<s:else>
						<DIV id="tagContent${s.index}" class="tagContent">
					</s:else>
				 
						<!-- 操作按钮 -->
						<div class="operating" id="operating${s.index}">
							<hwsoft:operation code="${menuCode}" param="add,edit,delete" />
						</div>
						<div id ="tabDiv${s.index}" style="display: none"></div>
				</DIV>
			</s:iterator>
		</DIV>
	</DIV>
		<!-- 从表end -->
<!--弹出层 start-->
<div id="templateDiv" style="display: none;"></div>
<!--弹出层 end-->
</body>
</html>