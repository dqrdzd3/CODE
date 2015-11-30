<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      	<li>移动设备列表</li>
      </ul>
    </div>
  </div>
	<div id="windowsmain">
		<div id="windowdiv">
			<div id="windowdivmain">
				<div style="margin-top: 10px;">
					<table width="100%" border="1" cellpadding="0" cellspacing="0"
						class="tablecss" id="addTestTb">
						<thead>
							<tr class="fixedHeaderTr">
								<th style="width: 26px;">序号</th>
								<th style="width: 120px;">设备名称</th>
								<th style="width: 120px;">设备ID</th>
								<th style="width: 120px;">客户端程序版本</th>
								<th style="width: 120px;">客户端系统平台</th>
								<th style="width: 120px;">客户端系统版本</th>
								<th style="width: 120px;">客户端型号</th>
<!-- 								<th style="width: 120px;">是否绑定</th> -->
<!-- 								<th style="width: 120px;">是否可用</th> -->
								<th style="width: 120px;">操作</th>
								<th style="width: 120px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="meList" var="map" status="s">
								<tr style="">
									<td align="center">
										${s.index+1}
									</td>
									<td align="center">${MA002}</td>
									<td align="center">${MA003}</td>
									<td align="center">${MA004}</td>
									<td align="center">${MA005}</td>
									<td align="center">${MA006}</td>
									<td align="center">${MA007}</td>
<!-- 									<td align="center"> -->
<%-- 										<s:if test="ISBOUND == 0"> --%>
<!-- 											未绑定 -->
<%-- 										</s:if> --%>
<%-- 										<s:elseif test="ISBOUND == 1"> --%>
<!-- 											已绑定 -->
<%-- 										</s:elseif> --%>
<!-- 									</td> -->
<!-- 									<td align="center"> -->
<%-- 										<s:if test="ISVALID == 0"> --%>
<!-- 											不可用 -->
<%-- 										</s:if> --%>
<%-- 										<s:elseif test="ISVALID == 1"> --%>
<!-- 											可用 -->
<%-- 										</s:elseif> --%>
<!-- 									</td> -->
									<td align="center" id="isValid">
										<s:if test="ISVALID == 0">
											<a href="javascript:;" value="${userId}|${MA001}" isValid="1" onclick="enableME(this);">启用</a>
										</s:if>
										<s:elseif test="ISVALID == 1">
											<a href="javascript:;"  value="${userId}|${MA001}" isValid="0" onclick="enableME(this);">禁用</a>
										</s:elseif>
									</td>
									<td align="center" id="isBound">
										<s:if test="ISBOUND == 0">
											<a href="javascript:;" value="${userId}|${MA001}" isBound="1" onclick="bindME(this);">绑定</a>
										</s:if>
										<s:elseif test="ISBOUND == 1">
											<a href="javascript:;" value="${userId}|${MA001}" isBound="0"  onclick="bindME(this);">解除</a>
										</s:elseif>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>