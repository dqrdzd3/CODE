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

<s:form action="tcorpamb" method="post" id="tcorpamb"> 		
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>应急预案信息</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain"><DIV id=con>
<UL id=tags>
  <LI class=selectTag><A onClick="selectTag('tagContent0',this)" 
  href="javascript:void(0)" onfocus="this.blur()">名称信息</A> </LI>
  <LI ><A onClick="selectTag('tagContent1',this)" 
  href="javascript:void(0)" onfocus="this.blur()">总则</A> </LI>
  <LI><A onClick="selectTag('tagContent2',this)" 
  href="javascript:void(0)" onfocus="this.blur()"> 生产经营单位的危险性分析</A> </LI>
  <LI><A onClick="selectTag('tagContent3',this)" 
  href="javascript:void(0)" onfocus="this.blur()">组织机构及职责</A> </LI>
  <LI><A onClick="selectTag('tagContent4',this)" 
  href="javascript:void(0)" onfocus="this.blur()">预防与预警</A> </LI>
  <LI><A onClick="selectTag('tagContent5',this)" 
  href="javascript:void(0)" onfocus="this.blur()">应急响应</A> </LI>
  <LI><A onClick="selectTag('tagContent6',this)" 
  href="javascript:void(0)" onfocus="this.blur()">信息发布</A> </LI>
  <LI><A onClick="selectTag('tagContent7',this)" 
  href="javascript:void(0)" onfocus="this.blur()">后期处置</A> </LI>
  <LI><A onClick="selectTag('tagContent8',this)" 
  href="javascript:void(0)" onfocus="this.blur()">保障措施</A> </LI>
  <LI><A onClick="selectTag('tagContent9',this)" 
  href="javascript:void(0)" onfocus="this.blur()">培训与演练</A> </LI>
  <LI><A onClick="selectTag('tagContent10',this)" 
  href="javascript:void(0)" onfocus="this.blur()">奖惩</A> </LI>
  <LI><A onClick="selectTag('tagContent11',this)" 
  href="javascript:void(0)" onfocus="this.blur()"> 附则</A> </LI>
  <LI><A onClick="selectTag('tagContent12',this)" 
  href="javascript:void(0)" onfocus="this.blur()">备注</A> </LI>
  </UL>
<DIV id=tagContent>
<DIV  id=tagContent0 class="tagContent selectTag"><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr><s:hidden name="tcorpambPO.objid" id="objid"></s:hidden>
							<s:hidden name="tcorpambPO.corpid" id="corpid"></s:hidden>
								<th>
							    <span> * </span>预案编号</th>
								<td class="tb_input">
									<s:textfield name="tcorpambPO.code" id="code" cssClass="{required:true,maxlength:25}"></s:textfield>
								</td>
								<th>
							    <span> *</span> 预案名称</th>
								<td class="tb_input">
									<s:textfield name="tcorpambPO.name" id="name" cssClass="{required:true,maxlength:25}"></s:textfield>
								</td>
								<th>
									所在单位
								</th>
								<td class="tb_input">
									<s:textfield name="tcorpambPO.corpname" id="corpname" cssClass="{maxlength:50}"></s:textfield>
								</td>
							</tr>
		        </tbody></table></DIV>
                          <DIV  id=tagContent1 class=tagContent><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">							
							<tbody><tr>
								<th >
									<span>(200字以内)</span>编制目的
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.purpose" style=" height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>编制依据
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.based" style=" height: 55px;"maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
									
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>适用范围
								</th>
								<td colspan="5">
									<s:textarea name="tcorpambPO.range" style=" height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								</td>
							</tr>
							<tr>
								<th >
									<span>(200字以内)</span>应急预案体系
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.yansystem" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
						
								</td>
							</tr>
							<tr>
								<th >
									<span>(200字以内)</span>应急工作原则
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.principle" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent2><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>生产经营单位概况
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.general" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>危险源与风险分析
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.analysis" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent3><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>应急组织体系
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.yjsystem" style="height: 55px;"maxlength="200"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>指挥机构及职责
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.zhihuizhize" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent4><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>危险源监控
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.wxyjk" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>预警行动
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.yjaction" style="height: 55px;" maxlength="200"  cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>信息报告与处置
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.inforeport" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent5><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>响应分级
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.responsefj" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>响应程序
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.responseprogram" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>应急结束
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.yjend" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent6><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>信息发布
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.inforelease" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent7><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>后期处置
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.latedisposal" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent8><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>通信与信息保障
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.infosecurity" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>应急队伍保障
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.dwsecurity" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>应急物资装备保障
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.zhbsecurity" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>经费保障
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.jfsecurity" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>其他保障
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.othersecurity" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent9><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>培训
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.training" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>演练
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.drill" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent10><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>奖惩
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.rewardsandpunishments" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent11><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>术语和定义
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.define" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>应急预案备案
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.record" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
								<th>
									<span>(200字以内)</span>维护和更新
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.updates" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
							</tr><tr>
								<th>
									<span>(200字以内)</span>制定与解释
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.formulate" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
							<tr>
							</tr><tr>
								<th>
									<span>(200字以内)</span>应急预案实施
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.implement" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
		</tbody></table></DIV>
<DIV class=tagContent id=tagContent12><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
							<tbody><tr>
								<th>
									<span>(200字以内)</span>备注
								</th>
								<td colspan="5">
								<s:textarea name="tcorpambPO.remarks" style="height: 55px;" maxlength="200" cssClass="{maxlength:200,minlength:0} textareamodify"></s:textarea>
								
								</td>
							</tr>
						</tbody></table></DIV>
</DIV></DIV>
<p>
  
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

