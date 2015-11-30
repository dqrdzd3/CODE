<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form id="editModule">
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>业务模块修改</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
      <div> 
      <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	  
	 					 <tr>
	    					<th scope="col" ><span>*</span>模块名称</th>
	    					<td class="tb_input"  scope="col">
    							<s:hidden name="sysModulePO.UUID"/>
    							<s:hidden name="sysModulePO.PARENT_UUID"/>
    							<s:textfield readonly="true" name="sysModulePO.MENU_NAME" cssClass=""/>
    						</td>
   						    <th scope="col" ><span>*</span>编码</th>
						    <td class="tb_input"  scope="col">
<%-- 						    	<s:textfield name="sysModulePO.MENU_CODE" readonly="true" cssClass=""/> --%>
						    	<s:textfield name="sysModulePO.subCode" readonly="true" cssClass=""/>
						    </td>
						    <th scope="col" ><span>*</span>排序 </th>
						    <td class="tb_input"  scope="col">
								<s:textfield name="sysModulePO.ORDERNUM" readonly="true" cssClass=""/>
						    </td>
					     </tr>
					     <tr>
						    <th scope="col" ><span>*</span>授权类别</th>
						    <td class="tb_input"  scope="col"><span class="tb_input">
						    	 <s:select list="userType" listKey="value"  listValue="display" headerKey="" headerValue="请选择" name="sysModulePO.USER_TYPE" disabled="true" cssClass=""/>
						    </span></td>
						    <th scope="col" ><span>*</span>是否有效</th>
						    <td class="tb_input"  scope="col">
								<s:radio list="isValid" listKey="value" listValue="display"  disabled="true"  name="sysModulePO.IS_VALID" cssClass=""/>
					         </td>
						    
						    <th scope="col" ><span>*</span>模块类别
						</th>
						    <td class="tb_input"  scope="col">
								<s:radio list="menuType" listKey="value" listValue="display" disabled="true" name="sysModulePO.MENU_TYPE" cssClass="noedit" />
						    </td>
						    </tr>
						  <tr>
   						    <th scope="col" >URL链接</th>
						    <td colspan="3"   scope="col">
						    	<s:textfield name="sysModulePO.URL" readonly="true" cssClass=""/>
						    </td>
						    <th scope="col" ><span>*</span>是否显示</th>
						    <td  scope="col">
						    	<s:radio list="isShow" listKey="value" listValue="display" name="sysModulePO.IS_SHOW" disabled="true"/>
						    </td>
					    </tr>
						  <tr>
						    <th scope="col" ><p>备注</p></th>
						    <td colspan="5"   scope="col">
						      <s:textarea  cols="45" rows="5" name="sysModulePO.REMARK" readonly="true"/>
					        </td>
					     </tr>
	  
	      </table>
      </div>
    </div>
    </div>
  </div> 
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
		<li class="back">
	 		<input type="button" value="" class="back" onclick="closeDiv('moduleDiv');">
		</li>
<!--      	<li class="save" style="margin-right:15px;"> -->
<!-- 	 		<input type="button" value="" class="save" onclick="doSubmitEditData();"> -->
<!--      	</li> -->
    
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>