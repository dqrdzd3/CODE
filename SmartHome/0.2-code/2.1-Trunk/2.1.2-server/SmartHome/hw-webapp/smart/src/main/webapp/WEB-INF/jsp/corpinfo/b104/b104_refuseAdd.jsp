<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
	<div id="windowstop">
		<div id="windowstopleft">
			<ul>
				<li>审批不通过</li>
			</ul>
		</div>
	</div>
	<s:form action="" method="post" id="b104RefuseAdd">
	<input type="hidden" id="b104Id" value="${b104PO.ma001}" />
	<div id="windowsmain">
		<div id="windowdiv"></div>
		<div id="windowdiv">
			<div id="windowdivmain">
					<table class="zzsajj_table">
					    <tr>
					    	<th><span>*</span>不通过理由：</th>
					        <td>
					        	<textarea id="refuseMsg" name="refuseMsg" cols="45" rows="5" class="required textareamodify {maxlength:100,minlength:0}"></textarea>
					        </td>
					    </tr>
					    <tr>
					    	<td colspan="2"></td>
					    </tr>
					</table>
			</div>
		</div>
	</div>
	</s:form>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
		     <ul><a href="javascript:void(0)" onclick="closeDiv();"><li class="back" style="margin-right:15px;"></li></a>
		       <a onclick="refuseAdd()"><li class="save"></li></a>
		       <li>( 说明：<span>*</span>号位必填项)</li>
		      </ul>
			</div>
		</div>
	</div>
</div>
