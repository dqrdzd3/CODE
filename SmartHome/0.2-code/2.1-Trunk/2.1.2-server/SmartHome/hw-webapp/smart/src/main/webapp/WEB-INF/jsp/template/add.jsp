<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">

</style>
<div id="windows">
  <div id="windowsmain">
    <div id="windowdiv">
    	<div id="windowdivmain">
    			<s:form id="addTemplate">
					<table border="1" cellspacing="0" cellpadding="0" id="windowdivmaintable"
						class="windowdivmaintable" style="width: 100%;">
						<tr>
							<th>姓名：</th>
							<td class="tb_input" ><s:textfield  name="testuiPO.name" /></td>
							<th>性别：</th>
							<td class="tb_input" ><s:radio name="testuiPO.sex" list="#{0:'男',1:'女',2:'保密'}" listKey="key" listValue="value" value="2"/></td>
							<th>年龄：</th>
							<td class="tb_input" ><s:textfield  name="testuiPO.age" /></td>
							<th>生日：</th>
							<td class="tb_input" ><s:textfield  name="testuiPO.birth" cssClass="Wdate"  onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"/></td>
							
						</tr>
					</table>
				</s:form>
    	</div>
	 </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
	      <ul>
	          <li  style="margin-right:15px;"><input id="backBtn" type="button" class="back"/></li>
		      <li  style="margin-right:15px;"><input id="saveBtn" type="button" class="save"/></li>
		      <li>( 说明：<span>*</span>号位必填项)</li>
	      </ul>
      </div>
    </div>
  </div>
</div>


