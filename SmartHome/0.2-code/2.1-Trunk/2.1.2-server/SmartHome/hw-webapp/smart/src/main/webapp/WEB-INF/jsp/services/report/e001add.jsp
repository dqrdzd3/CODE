<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:form action="e001" method="post">
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>检查内容表</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
<div id="windowdiv"><div id="windowdivmain">
<table class="windowdivmaintable">
	<tr>
		<th><span>*</span>培训主题</th>
		<td>
			<input type="hidden" name="e001PO.MA001" value="${e001PO.MA001}"/>
			<input type="text" name="e001PO.MA002" class="textareamodify {required:true,maxlength:150}" > 
		</td>
		<th><span>*</span>主培人员</th>
		<td><input type="text" name="e001PO.MA010" class="{required:true}"> </td>
		<th><span>*</span>培训方式</th>
		<td>
			<s:select list="pxfzList" id="pts"  listKey="value" listValue="display" headerKey="" headerValue="请选择" name="e001PO.MA005" cssClass='{required:true}'></s:select>
		</td>
	</tr>
	<tr>
		<th><span>*</span>培训对象</th>
		<td>
			<input type="text" name="e001PO.MA006" class="{required:true}">
		</td>
		<th><span>*</span>培训学时</th>
		<td>
			<input type="text" name="e001PO.MA007" maxlength="5" class="{required:true,z31Float:true,messages:{required:'请输入培训学时'}}">
		</td>
		<th><span>*</span>限定人数</th>
		<td>
			<input type="text" name="e001PO.MA011" maxlength="5" class="{required:true,digits:true,messages:{required:'请输入限定人数'}}">
		</td>
	</tr>
	<tr>
		<th>预计培训地点</th>
		<td>
			<input type="text" name="e001PO.MA009">
		</td>
		<th>预计培训时间</th>
		 <td>
     		<input name="e001PO.MA008" id="MA005" class="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"></input>
		 </td>
		 <th><span>*</span>报名截止日期</th>
		<td>
     		<input name="e001PO.MA015" id="MA015" class="Wdate {required:true}" onclick="hwDatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"></input>
		</td>	
	</tr>
	<tr>
		<th>联系电话</th>
		<td>
			<input type="text" name="e001PO.MA013" class="{isPhone:true,messages:{isPhone:'电话号码或手机号码格式错误'}}">
		</td>
		<th>联系邮箱</th>
		<td>
			<input type="text" name="e001PO.MA014" class="{email:true,messages:{isPhone:'邮箱地址格式错误'}}">
		</td>
		<th>组织方联系人</th>
		<td>
			<input type="text" name="e001PO.MA012">
		</td>	
	</tr>
	<tr>
		<th>部门名称</th>
		<td>
			${e001PO.deptName}
			<input type="hidden" name="e001PO.MA019"/>
		</td>
		<th>创建人</th>
		<td>
			${e001PO.loginName}
		</td>
		<th>是否关闭报名</th>
		<td>
			<s:select list="#{'10':'否','20':'是'}" name="e001PO.MA016"></s:select>
		</td>
	</tr>
	<tr>
		<th><span>*</span>培训目的</th>
		<td colspan="5">
			<textarea class="textareamodify {required:true,maxlength:500}" rows="45" cols="5" name="e001PO.MA003"></textarea>
		</td>
	</tr>
	<tr>
		<th><span>*</span>培训内容</th>
		<td colspan="5">
			<textarea class="textareamodify {required:true,maxlength:1500}" rows="45" cols="5" name="e001PO.MA004" ></textarea>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 								<th>附件</th> -->
<!-- 								<td colspan="5" height="320"><iframe id="img" width="100%" -->
<!-- 										height="320" -->
<!-- 										src="" -->
<!-- 										frameborder=0 border=0 marginwidth=0 marginheight=0 -->
<!-- 										scrolling=0></iframe> <input type="hidden" id="c004ids" -->
<!-- 									name="c004ids" /></td> -->
<!-- 							</tr> -->
							
							<tr>
      <th scope="col" >上传文件</th>
      <td colspan="5" class="tb_input"  scope="col"> 
       <iframe id="img" width="100%" height="150" src="<%=path%>/upload/fileupload!doAddupload?MA002=" frameborder=0 border=0  marginwidth=0  marginheight=0  scrolling=0 ></iframe> 
            <input type="hidden" id="c004ids" name="c004ids" />
      </td>
   </tr>

</table>
</div></div></div>
<div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
     	<li class="save"><s:submit value="" cssClass="back"></s:submit></li>
        <li class="cancel" style="margin-right:15px;"><s:submit value="" cssClass="submit"  method="doSaveEdit"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
</div>
</div>
</s:form>
