<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="windows">
	<div id="windowstop">
		<div id="windowstopleft">
			<ul>
				<li>审批数据</li>
			</ul>
		</div>
	</div>
	<div id="windowsmain">
		<div id="windowdiv"></div>
		<div id="windowdiv">
			<div id="windowdivmain">
					<table class="zzsajj_table">
					    <tr>
					    	<th>申请修改项：</th>
					        <td>${b104PO.ma006 }</td>
					    </tr>
					    <tr>
					    	<th>修改理由：</th>
					        <td>${b104PO.ma004 }</td>
					    </tr>
					</table>
			</div>
		</div>
	</div>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
				<ul>
					<li style="margin-right: 15px;"><a href="javascript:void(0)" onclick="refuse('${b104PO.ma001}')" ><img src="images/butongyi.jpg"></a></li>
					<li><a href="javascript:void(0)" onclick="passpetition('${b104PO.ma001}')" ><img src="images/tongyi.jpg"></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
