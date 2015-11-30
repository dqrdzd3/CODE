<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<title>企业基本信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,utilJS,validatorJS,grid,commonJS,my97)"executeResult="true" />
<script type="text/javascript">
function liulan(){
	var id=jQuery("#ma001").val();
	window.open('<%=path%>/upload/initfileupload?MA002='+id,'图片上传','height=500,width=800,top=250,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
};
$(function(){
	if(!$('#zl').val())
		jQuery('#zl').css("display",'none');
	if(!$('#xl').val())
		jQuery('#xl').css("display",'none');
});
$(function(){
		$('input:radio').each(function(){
			$(this).click();
		});
});
</script>
<script type="text/javascript">

function report() {
		var reportStat = $('#ma080').val();
		if (reportStat != '0') {
			showMsg("该企业信息已经上报，请勿重复操作！", 1);
			return;
		}
		showMsg("上报后将不能进行编辑！你确定要上报吗？", 1, {
			buttons : {
				"取消" : function() {
					jQuery(this).dialog("close");
				},
				"确定" : function() {
					$.post($$.getContextPath() + 'corpinfo/b001!report', {
						'corpid' : '' + $('#ma001').val()
					}, function(data) {
						showMsg(data.content,data.type);
						$('#ma080').val('1');
					});
				}

			}
		});

	}
//更新企业信息（处于审核状态的不能修改）
	function subForm() {
		//获取iframe里的图片id，放如c004ids里
		//$('#c004ids').val($("#ids",document.frames('img').document).val());
		if($('#ma080').val()!='0'){
			showMsg('信息已上报，不能更新！',1);
			return;
		}
		if (jQuery("#b001form").valid()) {

			jQuery.post('corpinfo/b001!doUpdate', jQuery("#b001form")
					.serialize(), function(msg) {
				showMsg(msg.content, msg.type);
			});
		}
	}

/**
	 * 省份改变，获取地市信息
	 */
	function psc() {
		jQuery('#cts').html('<option>请选择</option>');
		jQuery.post('corpinfo/b001!getcityList', {
			provinceId : jQuery('#pts').val()
		}, function(option) {
			jQuery('#cityts').html(option);
		});
	}

	/**
	 * 地市改变，获取区县信息
	 */
	function citysc() {
		jQuery.post('corpinfo/b001!getcountyList', {
			cityId : jQuery('#cityts').val()
		}, function(option) {
			jQuery('#cts').html(option);
		});
	}
	/**
	*获得行业类型
	**/
	function mlsc(){
		jQuery('#dl').empty();
		jQuery('#zl').empty().append('<option value="">请选择行业中类</option>');
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post(
				'corpinfo/b001!doGetHydlList',
			{'hymlID':jQuery('#ml').val()},
			function(option){
				if(jQuery('#ml').val()==''){
					document.getElementById('dl').style.display='none';
					document.getElementById('zl').style.display='none';
					document.getElementById('xl').style.display='none';
				}else {
					jQuery('#dl').html(option);
					document.getElementById('dl').style.display='inline';
				}
			}
		);
	}
	function dlsc(){
		jQuery('#zl').empty().append('<option value="">请选择行业中类</option>');
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post('corpinfo/b001!doGetHyzlList',
			{'dlID':jQuery('#dl').val()},
			function(data){
				//根据list列表的值，若没有子表则不显示
				var str='<option value="">请选择行业中类</option>';
				var leng=data.length;
				if(leng>0)
				{
					for(var i=0;i<data.length;i++){
						str+='<option value="'+data[i].ID+'">'+data[i].DISPLAY+'</option>';
					}
					jQuery('#zl').html(str);
					document.getElementById('zl').style.display='inline';
				}else {
					document.getElementById('zl').style.display='none';
					document.getElementById('xl').style.display='none';
				}
			}
		);
	}	
	function zlsc(){
		jQuery('#xl').empty().append('<option value="">请选择行业小类</option>');
		jQuery.post(
			'corpinfo/b001!doGetHyxlList',
			{'zlID':jQuery('#zl').val()},
			function(data){
				//根据list列表的值，若没有子表则不显示
				var str='<option value="">请选择行业小类</option>';
				var leng=data.length;
				if(leng>0)
				{
					for(var i=0;i<data.length;i++){
						str+='<option value="'+data[i].ID+'">'+data[i].DISPLAY+'</option>';
					}
					jQuery('#xl').html(str);
					document.getElementById('xl').style.display='inline';
				}else {
					document.getElementById('xl').style.display='none';
				}
			}
		);
	}	
	$(function(){
		
	$('#MA004').bind('focus',function(){
		
		$(this).blur();
	});
   $('#MA003').bind('focus',function(){
		
		$(this).blur();
	});
	});
	
//Gis标注
	function openEditCorpMap() {
		var corpid = jQuery('#ma001').val();//获得标注的企业ID
		var corpname = jQuery('#MA004').val();//获得标注的企业名称
		var codetype = "CORP";
		var codelevel = "";
		$$.getFrame('rightFrame').Tabs.openTab("mapgiseditid", "地图标注页面",
				"map/map_right!mapEdit.action?layertype='corpLayer'&&codeid='"
						+ corpid + "'&&codename='"
						+ encodeURI(encodeURI(corpname)) + "'" + "&&codetype='"
						+ codetype + "'&&codelevel='" + codelevel + "'",
				"地图模式 ");
	}
	function openShowCorpMap() {
		var corpid = jQuery('#ma001').val();//获得标注的企业ID
		$$.getFrame('rightFrame').Tabs.openTabMap("mapgisshowid", "地图展示页面",
				"map/map_right!mapDingwei.action?layertype='corpLayer'&codeid='"
						+ corpid + "'", null, true);

	}

	
</script>
<style type="text/css">
<!--
body,html {overflow-x:hidden; overflow-y:auto;
}
-->
.tablecss1 th{
	text-align: right;
}
</style>
</head>
<body>

	<s:form action="b001" method="post" id="b001form">
		<!-- 基础信息 -->
		<div style="padding: 20px;">
			<table border="0" cellspacing="0" cellpadding="4" class="tablecss1"
				style="width: 97%" align="center">
				<tbody>
					<s:hidden type="hidden" name="b001PO.MA001" id="ma001"  />
					<s:hidden name="b001PO.MA002" />
					<s:hidden name="b001PO.MA028" />
					<s:hidden name="b001PO.MA038" id="state" />
					<s:hidden name="b001PO.MA039" />
					<s:hidden name="b001PO.MA011" />
					<s:hidden name="b001PO.MA071" />
					<s:hidden name="b001PO.MA080" id="ma080" />
					<tr>
						<th style="width: 140px;"><span> *</span> 企业名称</th>
						<td><s:textfield name="b001PO.MA004" id="MA004"
								cssClass="{required:true,maxlength:100,minlength:0}"
								cssStyle="border:none" readonly="true"></s:textfield></td>
						<th style="width: 130px;">企业英文名称</th>
						<td><s:textfield name="b001PO.MA070" id="MA070"
								cssClass="{maxlength:50,stringCode:true}"></s:textfield></td>
						<th style="width: 140px;"><span> *</span> 法定代表人</th>
						<td><s:textfield name="b001PO.MA047" id="MA047"
								cssClass="required {maxlength:10,minlength:0}"></s:textfield>
					</tr>
					<tr>
						<th>单位注册地址</th>
						<td><s:textfield name="b001PO.MA045" id="MA045"
								cssClass="{maxlength:100,minlength:0}"></s:textfield></td>
						<th><span> * </span>组织机构代码</th>
						<td><s:textfield name="b001PO.MA003" id="MA003"
								cssClass="required" cssStyle="border:none" readonly="true"></s:textfield></td>
						<th><span> *</span> 成立时间</th>
						<td><s:textfield name="b001PO.MA012" id="MA012"
								cssClass=" Wdate required" dateformat="yyyy-MM-dd"
								onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})">
								<s:param name="value">
									<s:date name="b001PO.MA012" format="yyyy-MM-dd" />
								</s:param>
							</s:textfield></td>
					</tr>
					<tr>
						<th><span>*</span>所在地区</th>
						<td colspan="3"><s:select list="provinceList" id="pts"
								onchange="psc()" listKey="code" listValue="desc" headerKey=""
								headerValue="请选择" name="b001PO.MA007" cssClass='required'
								cssStyle="width:100px;"></s:select> <s:select list="cityl"
								id="cityts" onchange="citysc()" listKey="code" listValue="desc"
								headerKey="" headerValue="请选择" name="b001PO.MA008"
								cssClass='required' cssStyle="width:100px;"></s:select> <s:select
								list="areal" id="cts" listKey="code" listValue="desc"
								headerKey="" headerValue="请选择" name="b001PO.MA009"
								cssClass='required' cssStyle="width:100px;"></s:select></td>


						<th>占地面积(m<sup>2</sup>)
						</th>
						<td><s:textfield name="b001PO.MA025" id="MA025"
								cssClass=' {number:true,maxlength:17,decimal:true,min:0}'></s:textfield></td>
					</tr>
					<tr>
					    <th>上级主管部门</th><td><s:property value="b001PO.MA013"/></td>
						<th style="width: 150px;">职工总数(人)</th>
						<td><s:textfield name="b001PO.MA014" id="MA014"
								cssClass='{digits:true,maxlength:50}'></s:textfield></td>
						<th style="width: 150px;">固定资产总值(万元)</th>
						<td><s:textfield name="b001PO.MA015" id="MA015"
								cssClass='{number:true,maxlength:17,decimal:true,min:0}'></s:textfield></td>

					</tr>
					<tr>
						<th>年利润(万元)</th>
						<td><s:textfield name="b001PO.MA017" id="MA017"
								cssClass="{number:true,maxlength:17,decimal:true,min:0}"></s:textfield></td>
						<th>年总收入(万元)</th>
						<td><s:textfield name="b001PO.MA016" id="MA016"
								cssClass='{number:true,decimal:true,maxlength:17,min:0}'></s:textfield></td>
						<th><span> * </span>隶属关系</th>
						<td><s:select list="codeValueLSGX" name="b001PO.MA053"
								listKey="value" listValue="name" headerKey="" headerValue="请选择"
								ength="25" cssClass='required'></s:select></td>
					</tr>
					<tr>
						<th>主要产品<br>
						<span>(100字以内)</span></th>
						<td colspan="5"><s:textarea name="b001PO.MA018" id="MA018"
								cols="45" rows="5" maxlength="100"
								cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
						</td>
					</tr>
					<!-- 基础信息end -->
					<!-- 企业性质 -->
					<tr>
						<th><span> *</span> 经济类型</th>
						<td colspan="2"><s:select list="codeValueJJLX"
								name="b001PO.MA010" listKey="value" listValue="display"
								headerKey="" headerValue="请选择" ength="25" cssClass='required'></s:select></td>

						<th><span> *</span> 企业类型</th>
						<td colspan="2"><s:select list="codeValueQYLX"
								name="b001PO.MA051" listKey="value" listValue="name"
								headerKey="" headerValue="请选择" ength="25" cssClass='required'></s:select></td>
					</tr>
					<tr>


						<th scope="row"><span>* </span> 行业类型：</th>
						<td colspan="5" ><s:select list="hymlList"
								id="ml" onchange="mlsc()" listKey="ID" listValue="DISPLAY"
								headerKey="" headerValue="请选择行业门类" name="b001PO.hymlID"
								cssClass='required' cssStyle="width:200px;" /> <s:select
								list="dlList" id="dl" onchange="dlsc()" listKey="ID"
								listValue="DISPLAY" headerKey="" headerValue="请选择行业大类"
								name="b001PO.hydlID" cssClass='required' cssStyle="width:200px;" />

							<s:select list="zlList" id="zl" onchange="zlsc()" listKey="ID"
								listValue="DISPLAY" headerKey="" headerValue="请选择行业中类"
								name="b001PO.hyzlID" cssStyle="width:200px;" /> <s:select
								list="xlList" id="xl" listKey="ID" listValue="DISPLAY"
								headerKey="" headerValue="请选择行业小类" name="b001PO.hyxlID"
								cssStyle="width:200px;" /></td>
					</tr>

					<!-- 企业性质 end -->

					<!-- 人员联系方式 -->
					<tr>
						<th>单位负责人</th>
						<td><s:textfield name="b001PO.MA026" id="MA026"
								cssClass='{maxlength:10}'></s:textfield></td>
						<th>单位负责人电话</th>
						<td><s:textfield name="b001PO.MA027" id="MA027"
								cssClass="isPhone"></s:textfield></td>

						<th>安全生产负责人</th>
						<td><s:textfield name="b001PO.MA048" id="MA048"
								cssClass='{maxlength:10}'></s:textfield></td>
					</tr>
					<tr>
						<th>安全生产负责人电话</th>
						<td><s:textfield name="b001PO.MA049" id="MA049"
								cssClass="isPhone"></s:textfield></td>

						<th>企业Email地址</th>
						<td><s:textfield name="b001PO.MA020" id="MA020"
								cssClass="email"></s:textfield></td>
						<th>传真</th>
						<td><s:textfield name="b001PO.MA050" id="MA050"
								cssClass="{isPhone:true,messages:{isPhone:'请输入正确的传真号'}}"></s:textfield></td>
					</tr>

					<tr>
						<th>企业通讯地址</th>
						<td><s:textfield name="b001PO.MA033" id="MA033"
								cssClass='{maxlength:50}'></s:textfield></td>
						<th>邮政编码</th>
						<td><s:textfield name="b001PO.MA034" id="MA034"
								cssClass='{maxlength:10,isZipCode:true}'></s:textfield></td>

						<!-- 人员联系方式end -->

						<!-- 安全生产信息 -->

						<th>营业执照注册号</th>
						<td><s:textfield name="b001PO.MA054" id="MA054"
								cssClass='{maxlength:20,stringCode:true}'></s:textfield></td>
					</tr>
					<tr>
						<th>安全生产许可证编号</th>
						<td><s:textfield name="b001PO.MA055" id="MA055"
								cssClass='{maxlength:20,stringCode:true}'></s:textfield></td>
						<th>危险品登记证编号</th>
						<td><s:textfield name="b001PO.MA056" id="MA056"
								cssClass='{maxlength:20,stringCode:true}'></s:textfield></td>
						<th>安全生产标准化编号</th>
						<td><s:textfield name="b001PO.MA057" id="MA057"
								cssClass='{maxlength:20,stringCode:true}'></s:textfield></td>
					</tr>
					<tr>
						<th>企业认定证书号</th>
						<td><s:textfield name="b001PO.MA058" id="MA058"
								cssClass='{maxlength:20,stringCode:true}'></s:textfield></td>
						<th>主要负责人人数</th>
						<td><s:textfield name="b001PO.MA059" id="MA059"
								cssClass="{digits:true,maxlength:10}"></s:textfield></td>
						<th>安全管理人员数</th>
						<td><s:textfield name="b001PO.MA060" id="MA060"
								cssClass="{digits:true,maxlength:10}"></s:textfield></td>
					</tr>
					<tr>
						<th>厂区平面图</th>
						<td colspan="5" height="320"><iframe id="img" width="100%"
								height="320"
								src="<%=path%>/upload/initfileuploadnew?MA002=<s:property value="b001PO.MA001" />"
								frameborder=0 border=0 marginwidth=0 marginheight=0 scrolling=0></iframe>
							<input type="hidden" id="c004ids" name="c004ids" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="padding-bottom: 10px; text-align: center;">
			<img onclick="subForm()" src="images/update.png" width="88"
				height="29" />
				<img onclick="report()" src="images/report.png" width="88"
				height="29" />
<!-- 			<input  type ="button" value="上报" onclick="report()">	 -->
		</div>
	</s:form>
</body>
</html>
