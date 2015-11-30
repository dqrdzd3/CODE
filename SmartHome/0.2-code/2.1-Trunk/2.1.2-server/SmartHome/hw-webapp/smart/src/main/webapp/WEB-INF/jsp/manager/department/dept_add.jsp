<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="department" method="post" name="deptform" id="addDeptForm"> 
			<s:hidden name="deptPO.parent_uuid" id="parent_uuid" />
			<s:hidden name="deptPO.prekey" id="prekey" />
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
	    <th><span class="red">*</span>部门编码 </th>
	      <td><s:textfield  name="deptPO.depart_code" id="deptKey" cssClass="{required:true,maxlength:20}" maxLength="20"></s:textfield></td>
	    </tr>
	    <tr>
	      <th>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</th>
	      <td>
	      <s:textarea name="deptPO.remark" id="roleDescription" cols="45" rows="5" style="width:99%; height:160px; " cssClass="textareamodify {maxlength:100}" ></s:textarea></td>
	    </tr>
	  

</table>
</div></div>
</div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
	      <li>
	     	 <input class="back" onclick="closeDiv('deptDiv','gridTable');" />
	      </li>
	       <li  style="margin-right:8px;">
	       	<input class="submit" onclick="doSubmitAddData()"/>
	       </li>
	      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
	</s:form>
	