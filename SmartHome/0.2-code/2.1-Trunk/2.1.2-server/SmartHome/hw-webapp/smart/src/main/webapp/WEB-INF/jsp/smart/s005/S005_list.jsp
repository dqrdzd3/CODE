<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="zh">
<head>
<title>讨论区</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<s:action name="include(mainCSS,commonJS,jqueryJS,bootstrap)"
	executeResult="true" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	//删除
	function delFun(e) {
		$.post("s005!doDel?s005po.ma001=" + e.id, function(data, status) {
		   window.location.reload();
		});
	}
</script>
</head>
<body>
	<!-- 讨论主题 -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table>
					<tr>
						<!-- 标题 -->
						<td class="span2"><h2>标题：${s004po.ma002}</h2></td>
					</tr>
					<tr>
						<!-- 内容 -->
						<td style="width: 400px"><p>内容：${s004po.ma003}</p></td>
					</tr>
					<tr>
						<!-- 时间 -->
						<td class="span4"><p>创建时间：${s004po.ma004}</p></td>
					</tr>

				</table>
			</div>
		</div>
		<br> <br>
		<!-- 讨论列表	 -->
		<s:if test="#request.replyList!=null">
			<s:if test="#request.replyList.size!=0">
				<s:iterator value="#request.replyList" id="column">
					<div class="row-fluid">
						<table>
							<tr>
								<!-- 头像 -->
								<td style="width: 60px;"> 
								    <!--用户注册时没有上传头像，给出一个默认  -->
								    <s:if test="#column.ma009==null">
									     <img style="width: 40px; height: 50px" src="<%=basePath%>images/default_head.jpg">
								    </s:if>
								    <s:else>
								      <img style="width: 40px; height: 50px"
									   src="<%=basePath%>hwmobile/smart/u001!doDownLoad?ma001=<s:property value='#column.ma009' />">
								    </s:else>
								    
								</td>
								<!-- 内容 -->
								<td	style="width: 400px">
									<div>
										<p>
											<s:property value="#column.ma003" />
										</p>
									</div></td>
							</tr>
							<tr>
								<td></td>
								<!-- 回复人 -->
								<td style="width: 500px;"><div>
										<p>
										
										<s:property value="#column.ma008"/> <strong>回复于</strong><s:property value="#column.ma004" />
										</p>
									</div></td>
								<!-- 删除-->
								<td class="span4"><div>
										<button class="btn" type="button" value="删除"
											id="<s:property value='#column.ma001'/>"
											onclick="delFun(this)">删除</button>
									</div></td>
							</tr>
						</table>
					</div>
				</s:iterator>
				${toolBar }
			</s:if>
			<s:else>
				<div>该主题还没有人讨论呢</div>
			</s:else>
		</s:if>
		
	</div>
</body>
</html>
