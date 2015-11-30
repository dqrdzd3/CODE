<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form id="roleView">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>查看操作日志</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th>日志ID</th>
							<td><s:textfield name="operLogPO.id" readonly="true" /></td>
						</tr>
						<tr>
							<th>日志编号</th>
							<td><s:textfield name="operLogPO.code" readonly="true" /></td>
						</tr>
						<tr>
							<th>操作人ID</th>
							<td><s:textfield name="operLogPO.operatorId" readonly="true" /></td>
						</tr>
						<tr>
							<th>操作人姓名</th>
							<td><s:textfield name="operLogPO.operatorName" readonly="true" /></td>
						</tr>
						<tr>
							<th>记录时间</th>
							<td>
								<s:textfield readonly="true">
									<s:param name="value"><s:date name="operLogPO.recordingTime" format="yyyy-MM-dd HH:mm:ss"/></s:param>
								</s:textfield>
							</td>
						</tr>
						<tr>
							<th>类名</th>
							<td><s:textfield name="operLogPO.className" readonly="true" /></td>
						</tr>
						<tr>
							<th>方法名</th>
							<td><s:textfield name="operLogPO.methodName" readonly="true" /></td>
						</tr>
						<tr>
							<th>内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;荣</th>
							<td><s:textarea name="operLogPO.content" cols="45" rows="5" readonly="true"
									style="width:99%; height:160px; "></s:textarea></td>
						</tr>
						<tr>
							<th>IP</th>
							<td><s:textfield name="operLogPO.ip" readonly="true" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
		      			<li class="back">
						 	<input type="button" value="" class="back" onclick="closeDiv('logDiv','gridTable');">
		      			</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</s:form>
