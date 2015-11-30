<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$.hwPage({
		pageId		:	'pageDiv',		
		pageNo		: 	'${page.curPage}',
		pageSize	:	'${page.number}',
		totalCount	:	'${page.totalNum}',
		listSize	:	[1,5,10,15],
		load		:	$.template.loadMasterTb
	});
	$.hwTb.clickFun('listTb',function(){
		$.template.loadList();
	});
</script>


<!-- 表格头部信息，显示表格的名称 -->
<div id="tbDiv">
<div  id="fram_b">
	<div id="fram_l">
		<div id="fram_r">
			<!-- 表格名称 -->
			<div class="tite">隐患列表</div>
			<div  class="framrighticon">
				<ul>
					<!-- 导出表格 -->
					<li class="daochu"  style="padding-right:5px;"><a alt="导出excel" href="javascript:$.hwTb.export_table()"></a></li>
					<!-- 刷新表格 -->
					<li class="shuaxin" style="padding-right:5px;"><a alt="刷新"  href="javascript:$.template.loadMasterTb();"></a></li>
				    <!-- 隐藏表格 -->
				    <li class="yincang" ><a alt="隐藏" href="javascript:$.hwTb.hidden_table('divb')"></a></li>
			  	</ul>
			</div>
		</div>
	</div>
</div>

<!-- 表格div -->
<div class="divb" id="divb">
	<!-- 表头div -->
	<div  class="tablethbj">
		<table border="0" cellspacing="0" cellpadding="0" class="headertable">
		    <tr>
		    	<!-- 表头信息 -->
			    <th width="26px;">&nbsp;</th>
			    <th width="26px;"><input type="checkbox" name="checkbox" value="checkbox" onclick="$.hwTb.selectAll(this)" /></th>
			    <th onclick="$.hwTb.sortTable(this,'name','queryFrom',$.template.loadMasterTb)">姓名</th>
				<th onclick="$.hwTb.sortTable(this,'sex','queryFrom',$.template.loadMasterTb)">性别</th>
				<th onclick="$.hwTb.sortTable(this,'age','queryFrom',$.template.loadMasterTb)">年龄</th>
				<th onclick="$.hwTb.sortTable(this,'birth','queryFrom',$.template.loadMasterTb)">生日</th>
		    </tr>
		</table>
	</div>
	
	<!-- 表体div -->
	<div class="p_r" id="divc">
		<table  border="0" cellspacing="0" cellpadding="0"  class="miantable"  id="listTb">
			<!-- 遍历数据 -->
			<s:iterator var="v" value="page.result" status="s">
				<tr>
					<th width="26px;">${s.index+1}</th>
	  				<td width="26px;" align="center">
						<input type="checkbox" name="checkbox2" value="${v.id}" />
	  				</td>
					<td><a href="javascript:void(0);" onclick="alert(2);">${v.name }</a></td>
					<td>${v.sexName }</td>
					<td>${v.age }</td>
					<td>${v.birthStr }</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	

	<div style="clear:both;"></div>
	<!-- 分页区域div -->
	<div id="pageDiv"></div>
</div>
</div>