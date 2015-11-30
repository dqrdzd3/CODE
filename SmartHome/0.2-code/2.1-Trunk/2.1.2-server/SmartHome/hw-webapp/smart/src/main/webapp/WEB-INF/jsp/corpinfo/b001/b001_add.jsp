<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html> 
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title>企业基本信息-<s:text name="application.title" /></title>
<s:action name="include(validatorJS,uploadify)" executeResult="true"/>
</head>
<body>
<script type="text/javascript">
var imgType = ['.jpg','.png','.gif'];
$(function(){
	var options = window.uploadOptions || "";
	$('#upload').uploadify(jQuery.extend(options,{
		'auto' : false,//是否选择文件后自动上传
        'swf' : '<%=basePath%>res/uploadify-v3.0.0/uploadify.swf',
     	 //上传地址
        'uploader' : '<%=path%>/upload/fileupload!doUpLoad', 
      	//取消图片
        'cancelImage':'<%=path%>/res/uploadify-v3.0.0/uploadify-cancel.png',
		'buttonImage' :'<%=path%>/res/uploadify-v3.0.0/liulan.jpg',
		'fileTypeExts':'*.jpg;*.gif;*.png',//上传文件类型限制
		'multi':true,//是否允许多文件上传
		'uploadLimit':3,//允许上传文件个数
		'queueID':'queueDiv',//上传队列id
		'onUploadSuccess':function(file, data, response){
			var baseURL = '<%=path%>';
			onUploadSuccessFun(this,file, data, response, baseURL);
		},
		'onUploadStart':function(file){
			var baseURL = '<%=path%>';
			onUploadStartFun(this,file,baseURL);
		}
	}));
});
</script>
<s:form action="b001" method="post">

	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>企业信息</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<DIV id=con>
				<UL id=tags> 
					<LI class=selectTag><A onClick="selectTag('tagContent0',this)"
						href="javascript:void(0)" onfocus="this.blur()">基础信息</A></LI>
					<LI><A onClick="selectTag('tagContent1',this)"
						href="javascript:void(0)" onfocus="this.blur()">企业性质</A></LI>
					<LI><A onClick="selectTag('tagContent2',this)"
						href="javascript:void(0)" onfocus="this.blur()"> 人员联系方式</A></LI>
					<LI><A onClick="selectTag('tagContent3',this)"
						href="javascript:void(0)" onfocus="this.blur()">安全生产信息</A></LI>
					<LI><A onClick="selectTag('tagContent4',this)"
						href="javascript:void(0)" onfocus="this.blur()">地理信息</A></LI>
				</UL>
				<DIV id=tagContent>
					<!-- 基础信息 -->
					<DIV id=tagContent0 class="tagContent selectTag">
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">
							<tbody>
								<s:hidden name="b001PO.MA001" />
								<s:hidden name="b001PO.MA028" />
								<s:hidden name="b001PO.MA002" />
								<s:hidden name="b001PO.MA038" />
								<s:hidden name="b001PO.MA039" />

								<tr>
									<th><span> *</span> 企业名称</th>
									<td><s:textfield name="b001PO.MA004" id="MA004"
											cssClass="required"></s:textfield> <s:fielderror
											cssStyle="color:red;">
											<s:param>b001PO.MA004</s:param>
										</s:fielderror></td>
									<th>企业英文名称</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA070"
											id="MA070"></s:textfield></td>
								</tr>
								<tr>
									<th><span> *</span> 法定代表人</th>
									<td><s:textfield name="b001PO.MA047" id="MA047"
											cssClass="required {maxlength:10,minlength:0}" ></s:textfield> <s:fielderror
											cssStyle="color:red;">
										</s:fielderror></td>
									<th>法人单位注册地址</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA045"
											id="MA045" cssClass="{maxlength:100,minlength:0}"></s:textfield></td>
								</tr>
								<tr>
									<th><span> * </span>组织机构代码</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA003"
											id="MA003" cssClass="required"></s:textfield></td>
									<th><span> *</span> 成立时间</th>
									<td><s:textfield name="b001PO.MA012" id="MA012"
											cssClass="dateISO Wdate" dateformat="yyyy-MM-dd"
											onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"></s:textfield>
									</td>
								</tr>
								<tr>
									<th><span>*</span>所在地区</th>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td style="margin: 0; padding: 0; border: none;"><s:select
														list="provinceList" id="pts" onchange="psc()"
														listKey="code" listValue="desc" headerKey=""
														headerValue="请选择" name="b001PO.MA007" cssClass='required'></s:select>
												</td>
												<td style="margin: 0; padding: 0; border: none;"><s:select
														list="cityl" id="cityts" onchange="citysc()"
														listKey="code" listValue="desc" headerKey=""
														headerValue="请选择" name="b001PO.MA008" cssClass='required'></s:select></td>
												<td style="margin: 0; padding: 0; border: none;"><s:select
														list="areal" id="cts" listKey="code" listValue="desc"
														headerKey="" headerValue="请选择" name="b001PO.MA009"
														cssClass='required'></s:select></td>
											</tr>
										</table>
									</td>

									<th>所在化工园区</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA046"
											id="MA046"></s:textfield></td>
								</tr>

								<tr>
									<th>占地面积(m<sup>2</sup>)
									</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA025"
											id="MA025" cssClass=' number decimal'></s:textfield></td>
									<th>行业管理部门</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA013"
											id="MA013"></s:textfield></td>

								</tr>
								<tr>
									<th style="width: 150px;">职工总数(人)</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA014"
											id="MA014" cssClass='digits'></s:textfield></td>
									<th style="width: 150px;">固定资产总值(万元)</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA015"
											id="MA015" cssClass='decimal'></s:textfield></td>

								</tr>
								<tr>
									<th>年利润(万元)</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA017"
											id="MA017" cssClass="decimal"></s:textfield></td>
									<th>年总收入(万元)</th>
									<td><s:textfield name="b001PO.MA016" id="MA016"
											cssClass='decimal'></s:textfield></td>
								</tr>
								<tr>
									<th><span>(100字以内)</span>主要产品</th>
									<td colspan="3"><s:textarea name="b001PO.MA018" id="MA018"
											cols="45" rows="5"
											cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
									</td>
								</tr>
								<tr>
									<th><span>(100字以内)</span>注册地址</th>
									<td colspan="3"><s:textfield name="b001PO.MA045"
											id="MA045" cssClass="{maxlength:100,minlength:0}"></s:textfield>
									</td>
								</tr>
							</tbody>
						</table>
					</DIV>
					<!-- 基础信息end -->

					<!-- 企业性质 -->
					<DIV id=tagContent1 class=tagContent>
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">
							<tbody>
								<tr>

									<th><span> * </span>隶属关系</th>
									<td><s:select list="codeValueLSGX" name="b001PO.MA053"
											listKey="value" listValue="name" headerKey=""
											headerValue="请选择" ength="25" cssClass='required'></s:select></td>

									<th><span> *</span> 经济类型</th>
									<td><s:select list="codeValueJJLX" name="b001PO.MA010"
											listKey="value" listValue="display" headerKey=""
											headerValue="请选择" ength="25" cssClass='required'></s:select></td>
								</tr>
								<tr>
									<th><span> *</span> 企业类型</th>
									<td class="tb_inputtwo"><s:select list="codeValueQYLX"
											name="b001PO.MA051" listKey="value" listValue="name"
											headerKey="" headerValue="请选择" ength="25"
											cssClass='required'></s:select></td>
									<th><span> *</span> 所属行业</th>
									<td class="tb_inputtwo"><s:select list="codeValueSSHY"
											name="b001PO.MA011" listKey="value" listValue="name"
											headerKey="" headerValue="请选择" ength="25"
											cssClass='required'></s:select></td>
								</tr>
							</tbody>
						</table>
					</DIV>
					<!-- 企业性质 end -->

					<!-- 人员联系方式 -->
					<DIV class=tagContent id=tagContent2>
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">
							<tbody>
								<tr>
									<th>负责人姓名</th>
									<td><s:textfield name="b001PO.MA026" id="MA026"></s:textfield>
									</td>
									<th>负责人电话</th>
									<td><s:textfield name="b001PO.MA027" id="MA027"
											cssClass="isPhone"></s:textfield></td>
								</tr>
								<tr>
									<th>安全生产负责人</th>
									<td><s:textfield name="b001PO.MA048" id="MA048"></s:textfield>
									</td>
									<th>安全生产负责人联系电话</th>
									<td><s:textfield name="b001PO.MA049" id="MA049"
											cssClass="isPhone"></s:textfield></td>
								</tr>
								<tr>
									<th>企业Email地址</th>
									<td><s:textfield name="b001PO.MA020" id="MA020"
											cssClass="email"></s:textfield></td>
									<th>传真</th>
									<td><s:textfield name="b001PO.MA050" id="MA050"
											cssClass="isPhone"></s:textfield></td>
								</tr>

								<tr>
									<th>企业通讯地址</th>
									<td><s:textfield name="b001PO.MA033" id="MA033"></s:textfield>
									</td>
									<th>邮政编码</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA034"
											id="MA034"></s:textfield></td>
								</tr>
							</tbody>
						</table>
					</DIV>
					<!-- 人员联系方式end -->

					<!-- 安全生产信息 -->
					<DIV class=tagContent id=tagContent3>
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">
							<tbody>
								<tr>
									<th>营业执照注册号</th>
									<td><s:textfield name="b001PO.MA054" id="MA054"></s:textfield>
									</td>
									<th>安全生产许可证编号</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA055"
											id="MA055"></s:textfield></td>
								</tr>
								<tr>
									<th>危险化学品登记证编号</th>
									<td><s:textfield name="b001PO.MA056" id="MA056"></s:textfield>
									</td>
									<th>安全生产标准化证书编号</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA057"
											id="MA057"></s:textfield></td>
								</tr>
								<tr>
									<th>高新技术企业认定证书号</th>
									<td><s:textfield name="b001PO.MA058" id="MA058"></s:textfield>
									</td>
									<th>主要负责人人数</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA059"
											id="MA059" cssClass="digits"></s:textfield></td>
								</tr>
								<tr>
									<th>分管负责人和安全管理人员人数</th>
									<td><s:textfield name="b001PO.MA060" id="MA060"
											cssClass="digits"></s:textfield></td>
									<th>特种作业人数</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA061"
											id="MA061" cssClass="digits"></s:textfield></td>
								</tr>
								<tr>
									<th>其他从业人数</th>
									<td><s:textfield name="b001PO.MA062" id="MA062"
											cssClass="digits"></s:textfield></td>
									<th>农民工人数</th>
									<td class="tb_inputtwo"><s:textfield name="b001PO.MA063"
											id="MA063" cssClass="digits"></s:textfield></td>
								</tr>
								<tr>
									<th>是否存在重大危险源</th>
									<td><s:select list="codeValueSF"
										name="b001PO.MA052" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25" ></s:select></td>
									<th>是否存在重大事故隐患</th>
									<td><s:select list="codeValueSF"
										name="b001PO.MA064" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25" ></s:select></td>
								</tr>
								<tr>
									<th>是否有专兼职应急救援队伍</th>
									<td><s:select list="codeValueSF"
										name="b001PO.MA065" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25" ></s:select></td>
									<th></th>
									<td class="tb_inputtwo"></td>
								</tr>
							</tbody>
						</table>
					</DIV>
					<!-- 安全生产信息end -->

					<!-- 地理信息 -->
					<DIV class=tagContent id=tagContent4>
						<table border="0" cellspacing="0" cellpadding="0"
							class="windowdivmaintable">
							<tbody>

								<tr>
									<th colspan="1">厂区平面图</th>
									<td nowrap="nowrap" colspan="3"><s:file id="upload" name="upload" /> <img
										alt="上传" src="<%=path%>/res/uploadify-v3.0.0/shangchuan.jpg"
										onclick="javascript:$('#upload').uploadifyUpload()"> <s:hidden
											name="b001PO.MA066" id="filepath" /></td>
								</tr>
								<tr>
									<td colspan="1"></td>
									<td colspan="3" nowrap="nowrap"><div id="queueDiv"
											style="width: 200px; float: left; position: relative;"></div>
										<div id="imgpreDiv" style="width: 200px;"></div></td>
								</tr>
							</tbody>
						</table>
					</DIV>
					<!-- 地理信息end  -->
				</DIV>
				</DIV>
				<p></p>
				</div>
				<div id="windowsbottom">
					<div id="windowsbottomleft">
						<div id="windowsbottomright">
							<ul>
								<li class="save"><s:submit value="" cssClass="back"
										method="doList"></s:submit></li>
								<li class="cancel" style="margin-right: 15px;"><s:submit
										value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
								<li>( 说明：<span>*</span>号位必填项)
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		
</s:form>