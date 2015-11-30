<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="department" method="post" name="deptform" id="deptform"> 
			<s:hidden name="pId" id="parentId"></s:hidden>
			<s:hidden name="deptPO.parent_uuid" id="parent_uuid" />
			<s:hidden name="deptPO.prekey" id="prekey" />
			<s:hidden name="deptPO.uuid" id="uuid"/>
	<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>添加部门</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
   <div id="windowdiv"><div id="windowdivmain">
  <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable" >
 <tr>
	      <th><span class="red">*</span>部门名称 </th>
	      <td><s:textfield name="deptPO.depart_name"  cssClass="inputmodify {required:true,rangelength:[1,20]} stringCheck"></s:textfield></td>
	    </tr>
	    <tr>
	     <th><span class="red">*</span>角色 </th>
	      <td><s:checkboxlist name="roleArray" id="roleArray" list="roleList" listKey="UUID" listValue="ROLE_NAME" value="roleArray" ></s:checkboxlist></td>
	    </tr>
</table>
</div></div>
</div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
	      <li  ><input  type="button" onclick="closeDiv ('deptDiv','gridTable')" value="返回"></input></li>
	       <li  style="margin-right:8px;"><input type="button" value="授权"  onclick="empower()" /></li>
	      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
	</s:form>
	