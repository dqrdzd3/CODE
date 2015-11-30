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
})
</script>
<script type="text/javascript">
//更新企业信息（处于审核状态的不能修改）
function subForm(){
	    //获取iframe里的图片id，放如c004ids里
	    //$('#c004ids').val($("#ids",document.frames('img').document).val());
		if(jQuery("#b001form").valid()){
			
			jQuery.post(
					'corpinfo/b001!doUpdate', 
					jQuery("#b001form").serialize(),
					function(msg) {
				showMsg(msg.content,msg.type);
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
</style>
</head>
<body>

	<s:form action="b001" method="post" id="b001form">
		<!-- 基础信息 -->
		<div style="">
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

					<tr>
						<th style="width: 140px;"><span> *</span> 企业名称</th>
						<td>${b001PO.MA004 }</td>
						<th style="width: 130px;">企业英文名称</th>
						<td>${b001PO.MA070 }</td>
						<th style="width: 140px;"><span> *</span> 法定代表人</th>
						<td>${b001PO.MA047 }</td>
					</tr>
					<tr>
						<th>单位注册地址</th>
						<td>${b001PO.MA045 }</td>
						<th><span> * </span>组织机构代码</th>
						<td>${b001PO.MA003 }</td>
						<th><span> *</span> 成立时间</th>
						<td>
									<s:date name="b001PO.MA012" format="yyyy-MM-dd" />
							</td>
					</tr>
					<tr>
						<th><span>*</span>所在地区</th>
						<td colspan="3">
							<s:property value="#v=b001PO.MA007, provinceList.{?#this.code == #v}[0].desc"/>	
							<s:property value="#v=b001PO.MA008, cityl.{?#this.code == #v}[0].desc"/>	
							<s:property value="#v=b001PO.MA009, areal.{?#this.code == #v}[0].desc"/>	
								</td>


						<th>占地面积(m<sup>2</sup>)
						</th>
						<td>${b001PO.MA025 }</td>
					</tr>
					<tr>
						<th>行业管理部门</th>
						<td>${b001PO.MA013 }</td>
						<th style="width: 150px;">职工总数(人)</th>
						<td>${b001PO.MA014 }</td>
						<th style="width: 150px;">固定资产总值(万元)</th>
						<td>${b001PO.MA015 }</td>

					</tr>
					<tr>
						<th>年利润(万元)</th>
						<td>${b001PO.MA017 }</td>
						<th>年总收入(万元)</th>
						<td>${b001PO.MA016 }</td>
						<th><span> * </span>隶属关系</th>
						<td>
							<s:property value="#v=b001PO.MA053, codeValueLSGX.{?#this.value == #v}[0].name"/>	
								</td>
					</tr>
					<tr>
						<th>主要产品<br>
						<span>(100字以内)</span></th>
						<td colspan="5">${b001PO.MA018 }
						</td>
					</tr>
					<!-- 基础信息end -->
					<!-- 企业性质 -->
					<tr>
						<th><span> *</span> 经济类型</th>
						<td colspan="2">
							<s:property value="#v=b001PO.MA010, codeValueJJLX.{?#this.value == #v}[0].display"/>	
								</td>

						<th><span> *</span> 企业类型</th>
						<td colspan="2">
									<s:property value="#v=b001PO.MA051, codeValueQYLX.{?#this.value == #v}[0].name"/>	
								</td>
					</tr>
					<tr>
						<th scope="row"><span>* </span> 行业类型：</th>
						<td colspan="5" width="500px;">
								<s:property value="#v=b001PO.hymlID, hymlList.{?#this.ID == #v}[0].DISPLAY"/>
								<s:property value="#v=b001PO.hydlID, dlList.{?#this.ID == #v}[0].DISPLAY"/>
								<s:property value="#v=b001PO.hyzlID, zlList.{?#this.ID == #v}[0].DISPLAY"/>
							</td>
					</tr>

					<!-- 企业性质 end -->

					<!-- 人员联系方式 -->
					<tr>
						<th>单位负责人</th>
						<td>${b001PO.MA026 }</td>
						<th>单位负责人电话</th>
						<td>${b001PO.MA027 }</td>

						<th>安全生产负责人</th>
						<td>${b001PO.MA048 }</td>
					</tr>
					<tr>
						<th>安全生产负责人电话</th>
						<td>${b001PO.MA049 }</td>

						<th>企业Email地址</th>
						<td>${b001PO.MA020 }</td>
						<th>传真</th>
						<td>${b001PO.MA050 }</td>
					</tr>

					<tr>
						<th>企业通讯地址</th>
						<td>${b001PO.MA033 }</td>
						<th>邮政编码</th>
						<td>${b001PO.MA034 }</td>

						<!-- 人员联系方式end -->

						<!-- 安全生产信息 -->

						<th>营业执照注册号</th>
						<td>${b001PO.MA054 }</td>
					</tr>
					<tr>
						<th>安全生产许可证编号</th>
						<td>${b001PO.MA055 }</td>
						<th>危险品登记证编号</th>
						<td>${b001PO.MA056 }</td>
						<th>安全生产标准化编号</th>
						<td>${b001PO.MA057 }</td>
					</tr>
					<tr>
						<th>企业认定证书号</th>
						<td>${b001PO.MA058 }</td>
						<th>主要负责人人数</th>
						<td>${b001PO.MA059 }</td>
						<th>安全管理人员数</th>
						<td>${b001PO.MA060 }</td>
					</tr>
					<!-- 安全生产信息end -->

					<!-- 地理信息 -->

                   <tr>
					<th>企业执照</th>
					<td colspan="5">
					
<%-- 					<s:if test="labelFlag==1"> --%>
<!-- 							<img style="cursor: hand;" src="images/location.png" -->
<!-- 								onclick="javascript:openShowCorpMap()" /> -->
<%-- 						</s:if> <s:elseif test="labelFlag==0"> --%>
<!-- 							<img style="cursor: hand;" src="images/nolocation.png" -->
<!-- 								onclick="javascript:openEditCorpMap()" /> -->
<%-- 						</s:elseif> --%>
						
						<table class="zzsajj_table">
								<tr>
									<th>执照名称</th>
									<th>执照编号</th>
									<th>发证单位</th>
									<th>有效期</th>
									<th>执照图片</th>
								</tr>	
								<s:iterator value="b005List" var="po">
									<tr>
										<td><a href="javascript:;" onclick="viewQYZZ('${po.MA001}')" class="nmCol">${po.MA004 }</a></td>
										<td>${po.MA002 }</td>
										<td>${po.MA005 }</td>
										<td><s:date name="#po.MA007" format="yyyy-MM-dd"/></td>
<%-- 										<td>${po.MA010 == 1 ? '是' : '否' }</td> --%>
										<td>
											<s:iterator value="#po.c004poList" var="f">
										  		<a  href="javascript:;" onclick="viewPic('${f.MA001}')">${f.MA003}</a> <br>
<%-- 										  		<a  href="<%=basePath%>${f.MA006}" target="_blank">${f.MA003}</a> <br> --%>
<%-- 										  		<img src="<%=basePath%>${f.MA006}" >${f.MA003}<br> --%>
											</s:iterator>
										</td>
									</tr>
								</s:iterator>
							</table>
						
						
						
						</td>
				</tr>
					<tr>
						<th>企业相关图片</th>
						<td colspan="5" height="320"><iframe id="img" width="100%"
								height="320"
								src="<%=path%>/upload/initfileuploadnew?MA002=<s:property value="b001PO.MA001" />&from=view"
								frameborder=0 border=0 marginwidth=0 marginheight=0 scrolling=0></iframe>
							<input type="hidden" id="c004ids" name="c004ids" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="padding-bottom: 10px; text-align: center;">
<!-- 			<img onclick="subForm()" src="images/update.png" width="88" -->
<!-- 				height="29" /> -->
		</div>
	</s:form>
</body>
</html>
