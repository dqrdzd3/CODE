<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
    // 解决多页面验证的问题
jQuery().ready(function(){
		jQuery.validator.setDefaults({ 
			ignore:""
		});
	});
  function closeDiv(id){
		$("#" + id).dialog('close');
	}
function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for( i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}
</SCRIPT>
<s:form action="whp" method="post" id="tmsds"> 		
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>危化品信息</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain"> <DIV id=con>
<UL id=tags>
  <LI class=selectTag><A onClick="selectTag('tagContent0',this)" 
  href="javascript:void(0)" onfocus="this.blur()">化学品</A> </LI>
  <LI><A onClick="selectTag('tagContent1',this)" 
  href="javascript:void(0)" onfocus="this.blur()">成分组成</A> </LI>
  <LI><A onClick="selectTag('tagContent2',this)" 
  href="javascript:void(0)" onfocus="this.blur()"> 危险性</A> </LI>
  <LI><A onClick="selectTag('tagContent3',this)" 
  href="javascript:void(0)" onfocus="this.blur()">急救部分</A> </LI>
  <LI><A onClick="selectTag('tagContent4',this)" 
  href="javascript:void(0)" onfocus="this.blur()">消防措施</A> </LI>
  <LI><A onClick="selectTag('tagContent5',this)" 
  href="javascript:void(0)" onfocus="this.blur()">应急处理</A> </LI>
  <LI><A onClick="selectTag('tagContent6',this)" 
  href="javascript:void(0)" onfocus="this.blur()">操作处置</A> </LI>
  <LI><A onClick="selectTag('tagContent7',this)" 
  href="javascript:void(0)" onfocus="this.blur()">接触控制</A> </LI>
  <LI><A onClick="selectTag('tagContent8',this)" 
  href="javascript:void(0)" onfocus="this.blur()">理化特性</A> </LI>
  <LI><A onClick="selectTag('tagContent9',this)" 
  href="javascript:void(0)" onfocus="this.blur()">稳定性和活性</A> </LI>
  <LI><A onClick="selectTag('tagContent10',this)" 
  href="javascript:void(0)" onfocus="this.blur()">毒理学资料</A> </LI>
  <LI><A onClick="selectTag('tagContent11',this)" 
  href="javascript:void(0)" onfocus="this.blur()"> 生态学资料</A> </LI>
  <LI><A onClick="selectTag('tagContent12',this)" 
  href="javascript:void(0)" onfocus="this.blur()">废弃处置</A> </LI>
  <LI><A onClick="selectTag('tagContent13',this)" 
  href="javascript:void(0)" onfocus="this.blur()">运输信息</A> </LI>
  <LI><A onClick="selectTag('tagContent14',this)" 
  href="javascript:void(0)" onfocus="this.blur()">其他信息</A> </LI>
  </UL>
<DIV id=tagContent>
<DIV id=tagContent0 class="tagContent selectTag"><table width="100%" cellpadding="0" cellspacing="0"  Class="windowdivmaintable">
  
	<s:hidden name="tmsdsPO.objid" id="objid"></s:hidden>
  
  <tr>
  
    <th>化学品中文名称</th>
    <td  class="tb_input">
    <s:textfield name="tmsdsPO.chnname" id="chnName" cssClass="required"></s:textfield></td>
      <th>技术说明书编码</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.suming"></s:textfield></td>
    <th>化学品英文名称</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.engname" cssClass="alnum"></s:textfield></td>
  </tr>
  <tr>
    <th>化学品俗名或商品名</th>
    <td><s:textfield name="tmsdsPO.tinfo"></s:textfield></td>
    <th>国家应急电话</th>
    <td><s:textfield name="tmsdsPO.countrytel" cssClass="isTel" ></s:textfield></td>
    <th>企业名称</th>
    <td><s:textfield name="tmsdsPO.corpname"></s:textfield></td>
  </tr>
  <tr>
    <th>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</th>
    <td ><s:textfield name="tmsdsPO.address" ></s:textfield></td>
    <th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编</th>
    <td><s:textfield name="tmsdsPO.postcode"  cssClass="isZipCode" ></s:textfield></td>
    <th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</th>
    <td><s:textfield name="tmsdsPO.email" ></s:textfield></td>
  </tr>
  <tr>
    <th>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</th>
    <td><s:textfield name="tmsdsPO.fax" cssClass="isTel" ></s:textfield></td>
    <th>企业应急电话 </th>
    <td><s:textfield name="tmsdsPO.corptel" cssClass="isPhone" ></s:textfield></td>
    <th>生效日期</th>
    <td><s:textfield value="tmsdsPO.availabilitydate"><s:param name="value"><s:date name="tmsdsPO.availabilitydate" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
  </tr>
  </table></DIV>
<DIV  id=tagContent1 class=tagContent ><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>纯品/混合物</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.type" ></s:textfield></td>
    <th>化学品名称</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.hxpname" ></s:textfield></td>
    <th>有害物成分</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.ingredient" ></s:textfield></td>
  </tr>
  <tr>
    <th>含量</th>
    <td><s:textfield name="tmsdsPO.content" ></s:textfield></td>
    <th>CAS No.</th>
    <td colspan="3" ><s:textfield name="tmsdsPO.casnum"></s:textfield></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent2><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(100字以内)</span>危险性类别</th>
    <td colspan="5"><s:textarea name="tmsdsPO.dangersort" cols="45" rows="5" cssClass="{maxlength:100,minlength:0} textareamodify " ></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>侵入途径</th>
    <td colspan="5"><s:textarea name="tmsdsPO.invade" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>健康危害</th>
    <td colspan="5"><s:textarea name="tmsdsPO.healthharm" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>环境危害</th>
    <td colspan="5"><s:textarea name="tmsdsPO.environment" cols="45" rows="5"   cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>燃爆危险</th>
    <td colspan="5"><s:textarea name="tmsdsPO.blast" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent3><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(200字以内)</span>皮肤接触</th>
    <td colspan="5"><s:textarea name="tmsdsPO.skincontact" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>眼睛接触</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eyecontact" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>吸 入</th>
    <td colspan="5"><s:textarea name="tmsdsPO.breathe" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>食 入</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eat" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent4><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(200字以内)</span>危险特性</th>
    <td colspan="5"><s:textarea name="tmsdsPO.dangercha" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>有害燃烧产物</th>
    <td colspan="5"><s:textarea name="tmsdsPO.decompose" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>灭火方法及灭火剂</th>
    <td colspan="5"><s:textarea name="tmsdsPO.fireoutmethod" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>灭火注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.fireoutnotice" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent5><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(200字以内)</span>应急处理</th>
    <td colspan="5"><s:textarea name="tmsdsPO.contingencymethod" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>消除方法</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eliminatemethod" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent6><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(200字以内)</span>操作注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.operatenotice" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>储存注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.savenotice" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent7><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th><span>(200字以内)</span>最高容许浓度</th>
    <td colspan="5"><s:textarea name="tmsdsPO.maxallowableconcentration" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>监测方法</th>
    <td colspan="5"><s:textarea name="tmsdsPO.monitoringmethod" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>工程控制</th>
    <td colspan="5"><s:textarea name="tmsdsPO.procontrol" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>呼吸系统防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.breathedefend" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>眼睛防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eyedefend" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>身体防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.suit" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>手防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.handdefend" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th><span>(200字以内)</span>其他防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.otherdefend" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent8><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			  <tr>
						    <th><span>(200字以内)</span>外观与性状</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.aspect" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						 <tr>
						    <th>PH值 </th>
						     <td   class="tb_input"><s:textfield name="tmsdsPO.ph" cssClass="{decimal:true,range:[0,99]}"></s:textfield></td>
						  
						    <th>熔点（℃）</th>
						     <td  class="tb_input"><s:textfield name="tmsdsPO.melpoint" cssClass="{decimal:true,range:[0,99999]}"></s:textfield></td>
						    
							  <th style="width:120px;">相对密度（水=1） </th>
							   <td  class="tb_input"><s:textfield name="tmsdsPO.densityw" ></s:textfield></td>
						   
						  </tr>
						   <tr>
						    <th>沸点（℃） </th>
						     <td  ><s:textfield name="tmsdsPO.boipoint" ></s:textfield></td>
						   
						    <th style="width:140px;">相对蒸气密度（空气=1）</th>
						     <td><s:textfield name="tmsdsPO.densitya" ></s:textfield></td>
						   
							  <th style="width:120px;">饱和蒸气压（kPa） </th>
							   <td><s:textfield name="tmsdsPO.streampress" ></s:textfield></td>
						   
						  </tr>
						   <tr>
						    <th>燃烧热（kJ/mol） </th>
						     <td><s:textfield name="tmsdsPO.burningheat" ></s:textfield></td>
						   
						    <th>临界温度（℃）</th>
						     <td><s:textfield name="tmsdsPO.criticialtem" ></s:textfield></td>
						    
							  <th>临界压力（MPa） </th>
							   <td><s:textfield name="tmsdsPO.criticialpre" ></s:textfield></td>
						    
						  </tr>
						   <tr>
						    <th style="width:140px;">辛醇/水分配系数的对数值 </th>
						     <td ><s:textfield name="tmsdsPO.wdatavalues" ></s:textfield></td>
						    
						    <th>闪点（℃）</th>
						     <td ><s:textfield name="tmsdsPO.flapoint" ></s:textfield></td>
						    
							  <th style="width:120px;">爆炸上限%（V/V） </th>
							   <td ><s:textfield name="tmsdsPO.highexplode" ></s:textfield></td>
						   
						  </tr>
						  <tr>
						    <th>引燃温度（℃） </th>
						     <td ><s:textfield name="tmsdsPO.selfignitetem" ></s:textfield></td>
						    
						    <th>爆炸下限%（V/V）</th>
						     <td colspan="3"><s:textfield name="tmsdsPO.lowexplode" ></s:textfield></td>
						    
						  </tr>
						  <tr>
						    <th><span>(200字以内)</span>溶解性</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.resolvable" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th><span>(200字以内)</span>主要用途</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.purpose" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th><span>(200字以内)</span>其他理化性质</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.physicochemicalproperty" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent9><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th><span>(200字以内)</span>稳定性</th>
						    <td ><s:textarea name="tmsdsPO.stability" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th><span>(200字以内)</span>禁配物</th>
						      <td ><s:textarea name="tmsdsPO.taboo" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>避免接触的条件</th>
						    <td ><s:textarea name="tmsdsPO.avertcontact" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
		
						</tr>
						<tr>
						    <th><span>(200字以内)</span>聚合危害</th>
						    <td ><s:textarea name="tmsdsPO.polymerize" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>分解产物</th>
						    <td ><s:textarea name="tmsdsPO.cleavageproduct" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent10><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th><span>(200字以内)</span>急性毒性</th>
						    <td ><s:textarea name="tmsdsPO.acutetoxicity" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>亚急性和慢性毒性</th>
						    <td ><s:textarea name="tmsdsPO.subac" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>刺激性</th>
						    <td ><s:textarea name="tmsdsPO.thrill" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>致敏性</th>
						    <td ><s:textarea name="tmsdsPO.sensitization" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>致突变性</th>
						    <td ><s:textarea name="tmsdsPO.mutagenicity" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>致畸性</th>
						    <td ><s:textarea name="tmsdsPO.teratogenicity" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>致癌性</th>
						    <td ><s:textarea name="tmsdsPO.carcinogenicity" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th><span>(200字以内)</span>其 他</th>
						    <td ><s:textarea name="tmsdsPO.otherpathopoiesia" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>

						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent11><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th><span>(200字以内)</span>生态毒性</th>
						    <td ><s:textarea name="tmsdsPO.ecotoxicity" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th><span>(200字以内)</span>生物降解性</th>
						    <td ><s:textarea name="tmsdsPO.biodegradability" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th><span>(200字以内)</span>非生物降解性</th>
						    <td ><s:textarea name="tmsdsPO.naturalabioticdegradation" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th style="width:140px;"><span>(200字以内)</span>生物富集或生物积累性</th>
						    <td ><s:textarea name="tmsdsPO.bioconcentration" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th><span>(200字以内)</span>其他有害作用</th>
						    <td ><s:textarea name="tmsdsPO.otherharmful" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent12><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th><span>(200字以内)</span>是否废物危害</th>
						    <td ><s:textarea name="tmsdsPO.scrapcharacter" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th><span>(200字以内)</span>废弃处置方法</th>
						    <td ><s:textarea name="tmsdsPO.scrapmethod" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th><span>(200字以内)</span>废弃注意事项</th>
						    <td ><s:textarea name="tmsdsPO.scrapnotice" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent13><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
						  <tr>
						    <th>危险货物编号 </th>
						    <td  ><s:textfield name="tmsdsPO.wxhwnum" ></s:textfield></td>
						 
						    <th>UN编号</th>
						    <td  ><s:textfield name="tmsdsPO.unnum" ></s:textfield></td>
						    
							<th>包装标志</th>
							  <td  ><s:textfield name="tmsdsPO.packtitle" ></s:textfield></td>
						   
						  </tr>
	        			<tr>
						    <th><span>(200字以内)</span>包装类别</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.packsort" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th><span>(200字以内)</span>包装方法</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.packmethod" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th><span>(200字以内)</span>运输注意事项</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.trafficnotice" cols="45" rows="5"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent14><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        				<tr>	
							<th>填表日期</th>
						    <td class="tb_input">
						     <s:textfield value="tmsdsPO.inputdate"><s:param name="value"><s:date name="tmsdsPO.inputdate" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
						    <th>填表部门</th>
						    <td class="tb_input"><s:textfield name="tmsdsPO.inputdept" ></s:textfield></td>
						   
							  <th>数据审核单位</th>
							  <td class="tb_input"><s:textfield name="tmsdsPO.applydept" ></s:textfield></td>
						   
						</tr>
	        			<tr>
						    <th><span>(200字以内)</span>法规信息</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.law" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th><span>(200字以内)</span>参考文献</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.referencedocumentation" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>			
						<tr>
						    <th><span>(200字以内)</span>修改说明</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.modifyremark" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th><span>(200字以内)</span>其他信息</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.other" cols="45" rows="5" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea></td>
					   
						</tr>
					
						
					</table></DIV>
</DIV></DIV>
</div>
<div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul><li class="back"><input type="button" value="" class="back"	onclick="closeDiv('showcont')"></li>
      </ul>
      </div>
    </div>
  </div>
</div>

	</s:form>
