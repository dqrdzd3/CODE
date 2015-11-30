<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
// $(function(){
// 	var corpId = $("#corpId").val();
// 	if(corpId != null && corpId != "" ){
// 		var url = $$.getContextPath() + 'corpinfo/b001!doGetDangerByCorpId.action';
// 		var param = {'b001PO.MA001':corpId} ;
// 		$.post(url,param,function(data){
// 			var str = '<table class="windowdivmaintable" style="text-align:left;" ><tbody><tr><th align="left">危险源名称</th><th align="left">危险源类型</th><th align="left">危险源级别</th><th align="left">危险源申报状态</th></tr>';
// 			if(data != null && data !='' && data.length > 0){
// 				for (var i = 0 ; i < data.length ; i++){
// 					str +='<tr><td class="tb_inputtwo"><a href="javascript:void(0)"  onclick="fquery(\''+data[i].DANGERID+'\',\''+data[i].TYPECODE+'\',\''+data[i].DNAME+'\')">'+data[i].DNAME+'</a></td><td class="tb_inputtwo" >'+data[i].TYPEDESC+'</td>';
// 					str +='<td>'+data[i].STATE+'</td><td>'+data[i].WLEVEL+'</td></tr>';
// 				}
// 			}
// 			str +='</tbody></table>';
// 			$("#windowdivmain2").html(str);
// 		});
// 	}
// });
</script>
<script type="text/javascript">
$(function(){
	initGrid({
		url:$$.getContextPath()+'corpinfo/b001!doGetDangerByCorpId.action?b001PO.MA001='+$("#corpId").val(),
		id:'gridTable11',
		caption:'危险源信息',
		 formId:'SearchForm',
		colModel:[
            {label:'危险源ID', name:'dangerid', index:'dangerid',hidden:true,hidedlg:true},
			{label:'危险源名称', name:'dname', index:'dname',formatter:showcontent2},
			{label:'危险源类型', name:'typedesc', index:'typedesc'},
			{label:'危险源类型', name:'typecode', index:'typecode',hidden:true,hidedlg:true},
			{label:'危险源级别', name:'wlevel', index:'wlevel'},
			{label:'危险源申报状态', name:'state', index:'state'}
		],
		pk:"dangerid",
		winTitle:"企业危险源信息",
		multiselect:false,
		autowidth:false,
		width:748
	});
});
function closediv(){
	$('#showcontent').dialog('close');
	$("#coprinfodiv").dialog('close');
}
function hideorshow(){
	$('#windowdivmain2').toggle();
};
function showcontent2(celv,opts,obj){
	
	return '<a href="javascript:;" style="text-decoration: none;" onclick="fquery(\''+obj[0]+'\',\''+obj[3]+'\',\''+obj[1]+'\')">'+celv+'</a>';
}
</script>
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>企业概况表</li>
      </ul>
    </div>
  </div> 
<div id="windowsmain">
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>基本信息</div>
        </div>
      </div><div id="windowdivmain">
       <table  class="windowdivmaintable" id="windowdivmaintable">
						<tbody>

							<s:hidden name="b001PO.MA001" id = "corpId"/>
							<s:hidden name="b001PO.MA028" />
							<s:hidden name="b001PO.MA002" />
							<s:hidden name="b001PO.MA038" />
							<s:hidden name="b001PO.MA039" />

							<tr>
								<th>单位名称</th>
								<td><s:property value="b001PO.MA004" /></td>
								<th>组织机制代码</th>
								<td class="tb_inputtwo" ><s:property value="b001PO.MA003" />
								</td>
							</tr>
							<tr>
								<th>法定代表人</th>
								<td><s:property value="b001PO.MA047" /></td>
								<th>联系电话</th>
								<td><s:property value="b001PO.MA027" /></td>
							</tr>
							<tr>
								<th>通讯地址</th>
								<td><s:property value="b001PO.MA033" /></td>
								<th>邮政编码</th>
								<td class="tb_inputtwo"><s:property value="b001PO.MA034" />
								</td>
							</tr>

							<tr>
								<th>是否中央企业</th>
								<td><s:if test="b001PO.MA019 == 0">否</s:if>
									<s:else>是</s:else></td>

								<th>经济类型</th>
								<td><s:select list="codeValueJJLX" name="b001PO.MA010"
										listKey="value" listValue="display" headerKey=""
										headerValue="请选择" ength="25" cssClass='required' disabled="true"></s:select>
								</td>
							</tr>

							<tr>
								<th>成立时间</th>
								<td><s:date name="b001PO.MA012" format="yyyy-MM-dd"/></td>
								<th style="width: 150px;">职工总数(人)</th>
								<td class="tb_inputtwo"><s:property value="b001PO.MA014" /></td>
							</tr>
							<tr>
								<th>占地面积(m<sup>2</sup>)
								</th>
								<td class="tb_inputtwo"><s:property value="b001PO.MA025" />
								</td>
								<th>行政管理部门</th>
								<td class="tb_inputtwo"><s:property value="b001PO.MA013" />
								</td>

							</tr>
							<tr>

							</tr>


							<tr>
								<th>注册地址</th>
								<td colspan="3"><s:property value="b001PO.MA045" /></td>
							</tr>

							<tr>
								<th>申报日期</th>
								<td><s:property value="b001PO.MA037" /></td>
								<th>审核单位</th>
								<td class="tb_inputtwo"><s:property value="" /></td>
							</tr>

							<tr>
								<th>审核人</th>
								<td><s:property value="b001PO.MA040" /></td>
								<th>审核部门</th>
								<td class="tb_inputtwo"><s:property value="" /></td>
							</tr>
						</tbody>
					</table>
      </div>
    </div>
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>企业危险源信息 </div>
           <span><a href="javascript:" onclick="hideorshow()">&nbsp;</a></span>  
        </div>
      </div>
      <div id="windowdivmain2"  style="width:748px;display:none">
      <table id="gridTable11" style="width:748px;"></table>
      </div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright1">
      <ul>
       <li class="back" style="float: right; margin-top: 10px;"><a href="javascript:void(0)" onclick="closediv();"></a></li>
      </ul> 
      </div>
    </div>
  </div>
 <!--  <div id="supershow" style="display: none;"></div> -->
</div>


