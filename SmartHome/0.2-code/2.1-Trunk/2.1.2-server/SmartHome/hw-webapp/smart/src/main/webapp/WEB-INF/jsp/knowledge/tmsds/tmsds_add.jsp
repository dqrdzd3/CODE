<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
    // 解决多页面验证的问题
	jQuery().ready(function(){
		jQuery.validator.setDefaults({ 
			ignore:""
		});
	});
</script>
<s:action name="include(tabTip)" executeResult="true" />
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
  
    <th><span Class="red">*</span> 化学品中文名称</th>
    <td  class="tb_input">
    <s:textfield name="tmsdsPO.chnname" id="chnName" cssClass="{required:true,maxlength:50}"></s:textfield></td>
      <th>技术说明书编码</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.suming" cssClass="{maxlength:25}"></s:textfield></td>
    <th>化学品英文名称</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.engname" cssClass="{alnum:true,maxlength:50}"></s:textfield></td>
  </tr>
  <tr>
    <th>化学品俗名或商品名</th>
    <td><s:textfield name="tmsdsPO.tinfo" cssClass="{maxlength:25}"></s:textfield></td>
    <th>国家应急电话</th>
    <td><s:textfield name="tmsdsPO.countrytel" cssClass="isTel" ></s:textfield></td>
    <th>企业名称</th>
    <td><s:textfield name="tmsdsPO.corpname" cssClass="{maxlength:25}"></s:textfield></td>
  </tr>
  <tr>
    <th>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</th>
    <td ><s:textfield name="tmsdsPO.address" cssClass="{maxlength:25}" ></s:textfield></td>
    <th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编</th>
    <td><s:textfield name="tmsdsPO.postcode"  cssClass="{isZipCode:true,maxlength:25}" ></s:textfield></td>
    <th>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</th>
    <td><s:textfield name="tmsdsPO.email"  cssClass="email"></s:textfield></td>
  </tr>
  <tr>
    <th>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</th>
    <td><s:textfield name="tmsdsPO.fax" cssClass="isTel" ></s:textfield></td>
    <th>企业应急电话 </th>
    <td><s:textfield name="tmsdsPO.corptel" cssClass="isPhone" ></s:textfield></td>
    <th>生效日期</th>
    <td><s:textfield name="tmsdsPO.availabilitydate" 

cssClass=" dataISO  Wdate" onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})" ></s:textfield></td>
  </tr>
  </table></DIV>
<DIV  id=tagContent1 class=tagContent ><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>纯品/混合物</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.type" cssClass="{maxlength:25}"></s:textfield></td>
    <th>化学品名称</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.hxpname" cssClass="{maxlength:25}"></s:textfield></td>
    <th>有害物成分</th>
    <td  class="tb_input"><s:textfield name="tmsdsPO.ingredient" cssClass="{maxlength:25}"></s:textfield></td>
  </tr>
  <tr>
    <th>含量</th>
    <td><s:textfield name="tmsdsPO.content" cssClass="{maxlength:25}"></s:textfield></td>
    <th>CAS No.</th>
    <td colspan="3" ><s:textfield name="tmsdsPO.casnum" cssClass="{maxlength:25}"></s:textfield></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent2><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>危险性类别</th>
    <td colspan="5"><s:textarea name="tmsdsPO.dangersort" cols="45" rows="5" maxlength="100" cssClass="{maxlength:100,minlength:0} textareamodify " ></s:textarea></td>
  </tr>
  <tr>
    <th>侵入途径</th>
    <td colspan="5"><s:textarea name="tmsdsPO.invade" cols="45" rows="5" maxlength="2000"  cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>健康危害</th>
    <td colspan="5"><s:textarea name="tmsdsPO.healthharm" cols="45" rows="5"  maxlength="2000"  cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>环境危害</th>
    <td colspan="5"><s:textarea name="tmsdsPO.environment" cols="45" rows="5"   maxlength="2000"  cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>燃爆危险</th>
    <td colspan="5"><s:textarea name="tmsdsPO.blast" cols="45" rows="5" maxlength="2000"   cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent3><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>皮肤接触</th>
    <td colspan="5"><s:textarea name="tmsdsPO.skincontact" cols="45" rows="5"   maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>眼睛接触</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eyecontact" cols="45" rows="5"   maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>吸 入</th>
    <td colspan="5"><s:textarea name="tmsdsPO.breathe" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>食 入</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eat" cols="45" rows="5"  maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent4><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>危险特性</th>
    <td colspan="5"><s:textarea name="tmsdsPO.dangercha" cols="45" rows="5"   maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>有害燃烧产物</th>
    <td colspan="5"><s:textarea name="tmsdsPO.decompose" cols="45" rows="5"  maxlength="100" cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>灭火方法及灭火剂</th>
    <td colspan="5"><s:textarea name="tmsdsPO.fireoutmethod" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>灭火注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.fireoutnotice" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent5><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>应急处理</th>
    <td colspan="5"><s:textarea name="tmsdsPO.contingencymethod" cols="45" rows="5"  maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>消除方法</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eliminatemethod" cols="45" rows="5"  maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent6><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>操作注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.operatenotice" cols="45" rows="5"  maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>储存注意事项</th>
    <td colspan="5"><s:textarea name="tmsdsPO.savenotice" cols="45" rows="5"  maxlength="2000"cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent7><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr>
    <th>最高容许浓度</th>
    <td colspan="5"><s:textarea name="tmsdsPO.maxallowableconcentration" cols="45" rows="5" cssClass="{maxlength:25,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>监测方法</th>
    <td colspan="5"><s:textarea name="tmsdsPO.monitoringmethod" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>工程控制</th>
    <td colspan="5"><s:textarea name="tmsdsPO.procontrol" cols="45" rows="5"maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>呼吸系统防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.breathedefend" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>眼睛防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.eyedefend" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>身体防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.suit" cols="45" rows="5" maxlength="2000"cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>手防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.handdefend" cols="45" rows="5"maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
  <tr>
    <th>其他防护</th>
    <td colspan="5"><s:textarea name="tmsdsPO.otherdefend" cols="45" rows="5"maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
  </tr>
</table></DIV>
<DIV class=tagContent id=tagContent8><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			  <tr>
						    <th>外观与性状</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.aspect" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						 <tr>
						    <th>PH值 </th>
						     <td   class="tb_input"><s:textfield name="tmsdsPO.ph" cssClass="{decimal:true,range:[0,14]}"></s:textfield></td>
						  
						    <th>熔点（℃）</th>
						     <td  class="tb_input"><s:textfield name="tmsdsPO.melpoint" cssClass="{decimal:true,min:-272.20}"></s:textfield></td>
						    
							  <th style="width:120px;">相对密度（水=1） </th>
							   <td  class="tb_input"><s:textfield name="tmsdsPO.densityw" cssClass="{decimal:true,min:0,maxlength:25}"></s:textfield></td>
						   
						  </tr>
						   <tr>
						    <th>沸点（℃） </th>
						     <td  ><s:textfield name="tmsdsPO.boipoint" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						   
						    <th style="width:140px;">相对蒸气密度（空气=1）</th>
						     <td><s:textfield name="tmsdsPO.densitya" cssClass="{decimal:true,min:0,maxlength:25}"></s:textfield></td>
						   
							  <th style="width:120px;">饱和蒸气压（kPa） </th>
							   <td><s:textfield name="tmsdsPO.streampress" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						   
						  </tr>
						   <tr>
						    <th>燃烧热（kJ/mol） </th>
						     <td><s:textfield name="tmsdsPO.burningheat" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						   
						    <th>临界温度（℃）</th>
						     <td><s:textfield name="tmsdsPO.criticialtem" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						    
							  <th>临界压力（MPa） </th>
							   <td><s:textfield name="tmsdsPO.criticialpre"cssClass="{decimal:true,maxlength:25}" ></s:textfield></td>
						    
						  </tr>
						   <tr>
						    <th style="width:140px;">辛醇/水分配系数的对数值 </th>
						     <td ><s:textfield name="tmsdsPO.wdatavalues" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						    
						    <th>闪点（℃）</th>
						     <td ><s:textfield name="tmsdsPO.flapoint" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						    
							  <th style="width:120px;">爆炸上限%（V/V） </th>
							   <td ><s:textfield name="tmsdsPO.highexplode" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						   
						  </tr>
						  <tr>
						    <th>引燃温度（℃） </th>
						     <td ><s:textfield name="tmsdsPO.selfignitetem"cssClass="{decimal:true,maxlength:25}" ></s:textfield></td>
						    
						    <th>爆炸下限%（V/V）</th>
						     <td colspan="3"><s:textfield name="tmsdsPO.lowexplode" cssClass="{decimal:true,maxlength:25}"></s:textfield></td>
						    
						  </tr>
						  <tr>
						    <th>溶解性</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.resolvable" cols="45" rows="5" maxlength="100" cssClass="{maxlength:100,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th>主要用途</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.purpose" cols="45" rows="5"  maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th>其他理化性质</th>
						     <td colspan="5"><s:textarea name="tmsdsPO.physicochemicalproperty" cols="45" maxlength="2000" rows="5"  cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
					</table></DIV>
               <DIV class=tagContent id=tagContent9><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th>稳定性</th>
						    <td ><s:textarea name="tmsdsPO.stability" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th>禁配物</th>
						      <td ><s:textarea name="tmsdsPO.taboo" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>避免接触的条件</th>
						    <td ><s:textarea name="tmsdsPO.avertcontact" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
		
						</tr>
						<tr>
						    <th>聚合危害</th>
						    <td ><s:textarea name="tmsdsPO.polymerize" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>分解产物</th>
						    <td ><s:textarea name="tmsdsPO.cleavageproduct" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent10><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th>急性毒性</th>
						    <td ><s:textarea name="tmsdsPO.acutetoxicity" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>亚急性和慢性毒性</th>
						    <td ><s:textarea name="tmsdsPO.subac" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>刺激性</th>
						    <td ><s:textarea name="tmsdsPO.thrill" cols="45" rows="5"  maxlength="250"cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>致敏性</th>
						    <td ><s:textarea name="tmsdsPO.sensitization" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>致突变性</th>
						    <td ><s:textarea name="tmsdsPO.mutagenicity" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>致畸性</th>
						    <td ><s:textarea name="tmsdsPO.teratogenicity" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>致癌性</th>
						    <td ><s:textarea name="tmsdsPO.carcinogenicity" cols="45" rows="5"  maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
						<tr>
						    <th>其 他</th>
						    <td ><s:textarea name="tmsdsPO.otherpathopoiesia" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>

						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent11><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th>生态毒性</th>
						    <td ><s:textarea name="tmsdsPO.ecotoxicity" cols="45" rows="5" maxlength="250"cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th>生物降解性</th>
						    <td ><s:textarea name="tmsdsPO.biodegradability" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th>非生物降解性</th>
						    <td ><s:textarea name="tmsdsPO.naturalabioticdegradation" cols="45" maxlength="250" rows="5" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th style="width:140px;">生物富集或生物积累性</th>
						    <td ><s:textarea name="tmsdsPO.bioconcentration" cols="45" rows="5" maxlength="250" cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th>其他有害作用</th>
						    <td ><s:textarea name="tmsdsPO.otherharmful" cols="45" rows="5" maxlength="250"cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent12><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        			<tr>
						    <th>是否废物危害</th>
						    <td ><s:textarea name="tmsdsPO.scrapcharacter" cols="45" rows="5" maxlength="500"cssClass="{maxlength:500,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th>废弃处置方法</th>
						    <td ><s:textarea name="tmsdsPO.scrapmethod" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
						<tr>
						    <th>废弃注意事项</th>
						    <td ><s:textarea name="tmsdsPO.scrapnotice" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent13><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
						  <tr>
						    <th>危险货物编号 </th>
						    <td  ><s:textfield name="tmsdsPO.wxhwnum" cssClass="{maxlength:25}"></s:textfield></td>
						 
						    <th>UN编号</th>
						    <td  ><s:textfield name="tmsdsPO.unnum" cssClass="{maxlength:25}"></s:textfield></td>
						    
							<th>包装标志</th>
							  <td  ><s:textfield name="tmsdsPO.packtitle" cssClass="{maxlength:250}"></s:textfield></td>
						  </tr>
	        			<tr>
						    <th>包装类别</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.packsort" cols="45" rows="5" maxlength="250"  cssClass="{maxlength:250,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th>包装方法</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.packmethod" cols="45" rows="5" maxlength="500" cssClass="{maxlength:500,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th>运输注意事项</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.trafficnotice" cols="45" rows="5" maxlength="500" cssClass="{maxlength:500,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>
					</table></DIV>
<DIV class=tagContent id=tagContent14><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        				<tr>	
							<th>填表日期</th>
						    <td class="tb_input"><s:textfield name="tmsdsPO.inputdate" cssClass="  Wdate" onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})"  ></s:textfield></td>
						   
						    <th>填表部门</th>
						    <td class="tb_input"><s:textfield name="tmsdsPO.inputdept" cssClass="maxlength:25"></s:textfield></td>
						   
							  <th>数据审核单位</th>
							  <td class="tb_input"><s:textfield name="tmsdsPO.applydept" cssClass="maxlength:25"></s:textfield></td>
						   
						</tr>
	        			<tr>
						    <th>法规信息</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.law" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						    
						</tr>
						<tr>
						    <th>参考文献</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.referencedocumentation" cols="45"maxlength="2000" rows="5" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						   
						</tr>			
						<tr>
						    <th>修改说明</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.modifyremark" cols="45" rows="5" maxlength="2000"cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
						  
						</tr>
						<tr>
						    <th>其他信息</th>
						    <td colspan="5"><s:textarea name="tmsdsPO.other" cols="45" rows="5" maxlength="2000" cssClass="{maxlength:2000,minlength:0} textareamodify"></s:textarea></td>
					   
						</tr>
					
						
					</table></DIV>
</DIV></DIV>
<p>
  <SCRIPT type=text/javascript>
function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
	
	
}
</SCRIPT>
</p></div>
<div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul><li><s:submit value="" method="doList" cssClass="back"></s:submit></li>
       <li class="save" style="margin-right:15px;"><s:submit value="" method="doSaveAdd"  cssClass="submit"></s:submit></li>
      
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>

	</s:form>
