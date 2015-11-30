<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path ;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<s:action name="include(mainCSS,jquery,my97,grid,commonJS,validatorJS)"
	executeResult="true" />
<title>Insert title here</title>
<style type="text/css">
#windowdivmain {
	padding: 11px;
	padding-right: 10px;
	border-style: none;
	border-left: #B4EBE8 solid 1px;
	border-right: #B4EBE8 solid 1px;
	line-height: 24px;
}
td,th{
text-align:center !important;
}
.operation {
	cursor: hand;
	margin-left: 50px;
}
</style>
</head>
<script type="text/javascript">
	function deleteb005(id) {
		alert(id);
	}
	
	function updateB001(id){
	}
</script>
<body>
	<div class="branchright">
		<div class="declaretitle">企业信息上报</div>
		<div style="clear: both; padding-left: 20px;"></div>
		<div id="windowdiv">
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						企业信息 <span class="operation" onclick="updateB001('${requestScope.corpinfopo.b001PO.MA001}')"></span>
					</div>
				</div>
			</div>
			<div id="qyjbxx">
				<table border="0" cellspacing="0" cellpadding="0"
					class="windowdivmaintable">
					<tr>
						<th scope="row" >企业名称</th>
						<td colspan="" class="tb_input">
							${requestScope.corpinfopo.b001PO.MA004 }</td>
						<th scope="row">组织机构代码</th>
						<td colspan="" class="tb_input">
							${requestScope.corpinfopo.b001PO.MA003 }</td>
						<th scope="row">成立时间</th>
						<td colspan="" class="tb_input">
							<s:date name="#request.corpinfopo.b001PO.MA012" format="yyyy-MM-dd"></s:date>
						</td>
					</tr>
					<tr>
						<th scope="row">企业类型</th>
						<td colspan="" class="tb_input">${requestScope.corpinfopo.b001PO.MA051}</td>
						<th scope="row">法人代表</th>
						<td colspan="" class="tb_input">${requestScope.corpinfopo.b001PO.MA047}</td>
						<th scope="row">所在地区</th>
						<td colspan="" class="tb_input">${requestScope.corpinfopo.b001PO.MA024}</td>
					</tr>

					<tr>
						<th scope="row">职工总数</th>
						<td colspan="" class="tb_input">${requestScope.corpinfopo.b001PO.MA014}</td>
						<th scope="row">固定资产</th>
						<td colspan="" class="tb_input">
							${requestScope.corpinfopo.b001PO.MA015}</td>
						<th scope="row">注册地址</th>
						<td colspan="" class="tb_input">${requestScope.corpinfopo.b001PO.MA045}</td>
					</tr>

					<tr>
						<th scope="row">主要产品</th>
						<td colspan="5" class="tb_input " style="height: 50px;">
							${requestScope.corpinfopo.b001PO.MA018}</td>
					</tr>
				</table>
			</div>

			<!-- 企业证照信息 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						企业证照
						<span onclick="addHCXX()" class="operation"></span>
					</div>
				</div>
			</div>

			<c:if test="${requestScope.corpinfopo.b005List!= null}">
				<div id="yhhcxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
								<th scope="row">证件编号</th>
								<th scope="row">到期时间</th>
								<th scope="row">证件名称</th>
								<th scope="row">缩略图</th>
							</tr>
						<s:iterator value="#request.corpinfopo.b005List"  var="c" >
							<tr>
								<td colspan="" class="tb_input">${c.MA002}</td>
								<td colspan="" class="tb_input">
									<s:date name="#c.MA007" format="yyyy-MM-dd"></s:date>
								</td>
								<td colspan="" class="tb_input">${c.MA004}</td>
								<td colspan="" class="tb_input">
								<s:iterator value="#c.c004poList" var="f">
<%-- 							  <a  href="<%=basePath%>${f.MA006}">${f.MA003}</a> <br> --%>
							  <img src="<%=basePath%>${f.MA006}" >${f.MA003}<br>
								</s:iterator>
								</td>
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>

			<!-- 	   企业证照信息结束 -->

			<!--     规章制度信息 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						规章制度
						<span onclick="addgzzd()" class="operation"></span>
					</div>
				</div>
			</div>
			<c:if test="${requestScope.corpinfopo.tregulationList!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
								<th scope="row">制度编号</th>
								<th scope="row">编制时间</th>
								<th scope="row">制度名称</th>
								<th scope="row">制度类型</th>
								<th scope="row">附件</th>
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.tregulationList"
							var="c">
							<tr>
								<td colspan="" class="tb_input">${c.MA003}</td>
								<td colspan="" class="tb_input">
								<s:date name="#c.MA008" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">${c.MA004}</td>
								<td colspan="" class="tb_input">${c.MA007}</td>
								<td colspan="" class="tb_input">
                                <s:iterator value="#c.c004poList" var="f">
                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a>
                                </s:iterator>
                                </td>
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
			<!-- 	 规章制度信息结束 -->


			<!-- 	   培训计划信息开始 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						培训计划
						<span onclick="addgzzd()" class="operation"></span>
					</div>
				</div>
			</div>
			<c:if test="${requestScope.corpinfopo.e001List!= null}">
				<div id="yhysxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
								<th scope="row">培训主题</th>
								<th scope="row">培训时间</th>
								<th scope="row">培训对象</th>
								<th scope="row">培训人</th>
								<th scope="row">附件</th>
<!-- 								<th scope="row" >操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.e001List" var="c">
							<tr>
								<td colspan="" class="tb_input">${c.MA002}</td>

								<td colspan="" class="tb_input">
								<s:date name="#c.MA008" format="yyyy-MM-dd"></s:date></td>

								<td colspan="" class="tb_input">${c.MA006}</td>
								<td colspan="" class="tb_input">${c.MA010}</td>
								<td colspan="" class="tb_input">
	                                <s:iterator value="#c.c004poList" var="f">
	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a>
	                                </s:iterator>
                                 </td>
<!-- 								<td colspan="" class="tb_input"><input type="button"  -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
			<!-- 	 培训计划信息结束 -->
            <!-- 	 危险源信息结束 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						危险源
						<span onclick="addgzzd()" class="operation"></span>
					</div>
				</div>
			</div>


			<div id="yhysxx">
				<table border="0" cellspacing="0" cellpadding="0"
					class="windowdivmaintable">
					<tr>
						<td colspan="" class="tb_input"></td>

						<td colspan="" class="tb_input"></td>

						<td colspan="" class="tb_input"></td>
						<td colspan="" class="tb_input"></td>
						<td colspan="" class="tb_input"></td>
<!-- 						<td colspan="" class="tb_input"><input type="button" -->
<!-- 							onclick="deleteb005()" value="删除"></td> -->
					</tr>
				</table>
			</div>
			<!-- 危险源信息结束 -->
			<!-- 日常记录开始 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						日常记录
						<span onclick="addgzzd()" class="operation"></span>
					</div>
				</div>
			</div>
			<c:if test="${requestScope.corpinfopo.s121List!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
								<th scope="row">检查名称</th>
								<th scope="row">检查时间</th>
								<th scope="row">附件</th>
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.s121List" var="c">
							<tr>
								<td colspan="" class="tb_input">${c.ma002}</td>
								<td colspan="" class="tb_input"><s:date name="#c.ma005" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">
								    <s:iterator value="#c.c004poList" var="f">
	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a>
	                                </s:iterator>
								</td>
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.ma001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
			<!-- 日常记录开始 -->
			<!-- 隐患排查开始 -->
			<div id="windowtitle" style="border-right: #B4EBE8 solid 1px;">
				<div id="windowtitleleft">
					<div>
						隐患排查 <span onclick="addyhpc()" class="operation"></span>
					</div>
				</div>
			</div>

			<c:if test="${requestScope.corpinfopo.s108List!= null}">
				<div id="yhzgxx">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
								<th scope="row">隐患名称</th>
								<th scope="row">发现时间</th>
								<th scope="row">隐患状态</th>
								<th scope="row">责任人</th>
								<th scope="row">附件</th>
<!-- 								<th scope="row">操作</th> -->
							</tr>
						<s:iterator value="#request.corpinfopo.s108List" var="c">
							<tr>
								<td colspan="" class="tb_input">${c.MA008}</td>
								<td colspan="" class="tb_input">
								<s:date name="#c.MA006" format="yyyy-MM-dd"></s:date></td>
								<td colspan="" class="tb_input">${c.MA015}</td>
								<td colspan="" class="tb_input">${c.MA023}</td>
								<td colspan="" class="tb_input">
								   <s:iterator value="#c.c004poList" var="f">
	                                <a href="<%=basePath%>${f.MA006}">${f.MA003}</a>
	                                </s:iterator>
								</td>
<!-- 								<td colspan="" class="tb_input"><input type="button" -->
<%-- 									onclick="deleteb005('${c.MA001}')" value="删除"></td> --%>
							</tr>
						</s:iterator>
					</table>
				</div>
			</c:if>
			<!-- 隐患排查结束 -->
		</div>
		<div align="center">
		<input type="button" value="上报">
			<input type="button" value="修改申请">
		</div>
	</div>
	<div id="opendiv"></div>
</body>
</html>