<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>健康知识</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
    <div id="windowdivmain">
	<table border="0" cellspacing="0" cellpadding="0"
		class="windowdivmaintable">
		<tr>
			<th scope="col"><span>*</span>题目</th>
			<td class="tb_inputtwo" scope="col">${k001PO.ma002 }</td>
			<th scope="col"><span>*</span>关键字</th>
			<td class="tb_inputtwo" scope="col">${k001PO.ma004 }</td>
		</tr>
		<tr>
			<th scope="row"><span>* </span>内容</th>
			<td colspan="3">${k001PO.ma003 }</td>
		</tr>
	</table>
	</div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
	      <ul>
	       <li class="cancel" style="margin-right:15px;"><input type="button" class="back" style="border:none;" id="backBtn" onclick="closeshowd()" /></li>
	      </ul>
      </div>
    </div>
  </div>
</div>

