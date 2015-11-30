<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="updform">
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>安全管理</li>
				</ul>
			</div>
		</div>
		<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable" id="windowdivmaintable">
						<tbody>

							<s:hidden name="b003PO.MA001" id="MA001"></s:hidden>
							<s:hidden name="b003PO.MA003" id="MA003"></s:hidden>
							<s:hidden name="b003PO.MA013" id="MA013"></s:hidden>

							<tr>
								<th><span> * </span>人员编号</th>
								<td class="tb_input"><s:textfield name="b003PO.MA002"
										id="MA002" cssClass='{required:true,maxlength:25}'></s:textfield></td>
								<th><span> *</span> 姓名</th>
								<td class="tb_input"><s:textfield name="b003PO.MA004"
										id="MA004" cssClass='{required:true,maxlength:10}'></s:textfield></td>
								<th><span>*</span>联系电话</th>
								<td class="tb_input"><s:textfield name="b003PO.MA009"
										id="MA009" cssClass='{isPhone:true,required:true}'></s:textfield></td>
							</tr>
							<tr>
								<th>性别</th>
								<td><s:radio list="sex" name="b003PO.MA016" listKey="value" listValue="name" cssClass="radiostyle"></s:radio></td>
								<th>民族</th>
								<td><s:select list="codeValueMZ" id="mz"
										name="b003PO.MA017" listKey="value" listValue="name"
										headerKey="" headerValue="请选择" ength="25"></s:select></td>

								<th>出生日期</th>
								<td><s:textfield name="b003PO.MA018"
										cssClass="Wdate"
										onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"
										><s:param name="value"><s:date name="b003PO.MA018" format="yyyy-MM-dd"/></s:param></s:textfield></td>
							</tr>
							<tr>
								<th>学历</th>
								<td><s:select list="codeValueXL" name="b003PO.MA019"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th>技术职称</th>
								<td><s:select list="codeValueJSZC" name="b003PO.MA020"
										listKey="value" listValue="name" headerKey=""
										headerValue="请选择" ength="25"></s:select></td>

								<th><span> *</span> 职务</th>
								<td><s:textfield name="b003PO.MA005" id="MA005"
										cssClass='{required:true,maxlength:10}'></s:textfield></td>
							</tr>
							<tr>
								<th>传真</th>
								<td><s:textfield name="b003PO.MA010" id="MA010" cssClass="{isPhone:true,messages:{isPhone:'请输入正确的传真号码'}}"></s:textfield>
								</td>
								<th><span> *</span> 人员类别</th>
								<td><s:select list="codeValueAQRYLB"
										onchange="roletochange1()" id="ma006" name="b003PO.MA006"
										listKey="value" listValue="display" headerKey=""
										headerValue="请选择" ength="25" cssClass='required'></s:select>
								</td>
	                            <th id="tsgzbt">特殊工种</th>
									<td><s:select list="codeValueTSGZ" id="updatetsgz"
											 name="b003PO.MA023" listKey="value"
											listValue="display" headerKey="" headerValue="请选择" ength="25"></s:select></td>
							</tr>
							<tr style="display: none;" id="_aptitude">
								<th>主要负责人资质证书</th>
								<td colspan="5"><s:textfield name="b003PO.MA007" cssClass='{maxlength:2}'></s:textfield>
								</td>
							</tr>
							<tr style="display: none;" id="_grade">
								<th>级别</th>
								<td colspan="5"><s:textfield name="b003PO.MA011" id="MA011"  cssClass='{maxlength:2}'></s:textfield>
								</td>
							</tr>
							<tr style="display: none;" id="_complexion">
								<th>注册情况</th>
								<td colspan="5"><s:textfield name="b003PO.MA008" id="MA008" cssClass='{maxlength:2}'></s:textfield>
								</td>
							</tr>
							<tr>
								<th><span>(100字以内)</span>备注</th>
								<td colspan="5"><s:textarea name="b003PO.MA015" id="b003MA015"
										cols="45" rows="5" maxlength="100"
										cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<div id="windowsbottom">
			<div id="windowsbottomleft">
				<div id="windowsbottomright">
					<ul>
						<li class="cancel"><input type="button" class="back"
							onclick="closedia()"></li>
						<li class="save" style="margin-right: 15px;"><input
							type="button" class="submit" onclick="doupd()"></li>
						<li>( 说明：<span>*</span>号位必填项)
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
// 	function uptochange() {
// 		if (jQuery('#updform #ma006').val() == '其它') {
// 			jQuery('#updform #ptypedesc').text('工种');
// 			jQuery('#updform #ptypetd')
// 					.html(
// 							'<select name="b003PO.MA021"><option value="1">农民工</option></select>');
// 		} else {
// 			if (jQuery('#updform #othertype')) {
// 				jQuery('#updform #ptypedesc').text('');
// 				jQuery('#updform #ptypetd').html(
// 						'<input type="hidden" name="b003PO.MA021" value="0"/>');
// 			}
// 		}
// 	};
	$(function(){
		var tsgz=$("#updatetsgz").val();
		if(tsgz==''||tsgz==null){
			jQuery('#updform #updatetsgz').hide();
			jQuery('#updform #tsgzbt').hide();
		}else 
			{
			jQuery('#updform #updatetsgz').show();
			jQuery('#updform #tsgzbt').show();
			}
	});
	function roletochange1() {
		if (jQuery('#updform #ma006').val() == '3') {
			jQuery('#updform #updatetsgz').show();
			jQuery('#updform #tsgzbt').show();

		} else {
			jQuery('#updform #updatetsgz').val("");
			jQuery('#updform #updatetsgz').hide();
			jQuery('#updform #tsgzbt').hide();
		}
	}
	
</script>
