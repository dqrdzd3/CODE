<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function(){
		$.hwPage({
			pageId		:	'pageDiv3',		
			pageNo		: 	'${page.curPage}',
			pageSize	:	'${page.number}',
			totalCount	:	'${page.totalNum}',
			listSize	:	[1,5,10,15],
			load		:	$.template.loadData3
			
		});
	    $.hwBindBtn('add',$.template.add,'operating2');
	});

</script>
<form action="" method="post" id="queryFrom3" style="display: none;">
		<table border="0" cellspacing="8" cellpadding="0">
			<tr>
				<td style="width:80px;"align="right">姓名：</td>
			<td colspan="3" style="width:200px;">
                        <s:textfield name="testuiPO.name" class="input2" />
			</td>
			<td width="98" rowspan="2"><input type="button" class="search"/></td>
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
	<!-- 表格头部信息，显示表格的名称 -->
<div id="tbDiv3">
<div id="fram_b">
	<div id="fram_l">
		<div id="fram_r">
			<!-- 表格名称 -->
		<div class="tite">隐患列表</div>
		<div class="framrighticon">
			<ul>
				<!-- 导出表格 -->
				<li class="daochu" style="padding-right: 5px;"><a
					alt="导出excel" href="javascript:$.hwTb.export_table()"></a></li>
				<!-- 刷新表格 -->
				<li class="shuaxin" style="padding-right: 5px;"><a
					alt="刷新" href="javascript:$.template.loadData3()"></a></li>
				<!-- 隐藏表格 -->
					<li class="yincang"><a alt="隐藏"
						href="javascript:$.hwTb.hidden_table('divb3')"></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- 表格div -->
<div class="divb" id="divb3">
	<!-- 表头div -->
<div class="tablethbj">
	<table border="0" cellspacing="0" cellpadding="0"
		class="headertable">
		<tr>
			<!-- 表头信息 -->
			<th width="26px;">&nbsp;</th>
			<th width="26px;"><input type="checkbox" name="checkbox"
				value="checkbox" onclick="$.hwTb.selectAll(this)" /></th>
			<th onclick="$.hwTb.sortTable(this,'name','queryFrom3',$.template.loadData3)">姓名</th>
			<th onclick="$.hwTb.sortTable(this,'sex','queryFrom3',$.template.loadData3)">性别</th>
			<th onclick="$.hwTb.sortTable(this,'age','queryFrom3',$.template.loadData3)">年龄</th>
			<th onclick="$.hwTb.sortTable(this,'birth','queryFrom3',$.template.loadData3)">生日</th>
		</tr>
	</table>
</div>

<!-- 表体div -->
<div class="p_r" id="divc">
	<table border="0" cellspacing="0" cellpadding="0"
		class="miantable">
		<!-- 遍历数据 -->
		<s:iterator var="v" value="page.result" status="s">
			<tr onclick="$.hwTb.trClick(this);" onmousemove="$(this).addClass('td_hover')" onmouseout="$(this).removeClass('td_hover')">
				<th width="26px;">${s.index+1}</th>
				<td width="26px;" align="center"><input type="checkbox"
					name="checkbox2" value="${v.id}" /></td>
				<td><a href="javascript:void(0)">${v.name }</a></td>
				<td>${v.sexName }</td>
				<td>${v.age }</td>
				<td>${v.birthStr }</td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div style="clear: both;"></div>
	<!-- 分页区域div -->
	<div id="pageDiv3"></div>
</div>

</div>

	
		