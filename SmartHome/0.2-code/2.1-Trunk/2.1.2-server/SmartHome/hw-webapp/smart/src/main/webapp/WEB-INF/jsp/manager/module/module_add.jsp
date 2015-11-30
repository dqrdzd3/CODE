<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src='<s:url value="/js/util/noclick.js"></s:url>'></script>
<s:form id="addModule">

	<div id="windows">
	  <div id="windowstop">
	    <div id="windowstopleft">
	      <ul><li>业务模块新增</li>
	      </ul>
	    </div>
	  </div>
	  <div id="windowsmain">
	    <div id="windowdiv">
	    	<div id="windowdivmain">
	     		 <div>
	     		 	 <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
	  
	 					 <tr>
	    					<th scope="col" ><span>*</span>模块名称</th>
	    					<td class="tb_input"  scope="col">
    							<s:hidden id="parentId" name="sysModulePO.PARENT_UUID"/>
    							<s:textfield name="sysModulePO.MENU_NAME" cssClass="{required:true,maxlength:20,messages:{required:'请输入模块名称'}} stringCheck"/>
    						</td>
   						    <th scope="col" ><span>*</span>编码</th>
						    <td class="tb_input"  scope="col">
						    	<s:textfield name="sysModulePO.MENU_CODE" 
						    		id="menuCode"
						    		cssClass="{required:true,alnum:true,maxlength:60,remote:{type:'post',async:false,url:'manager/sysmodule!checkOnlyModuleCode',data:{menuCode:function(){return $('#menuCode').val();},parentId:function(){return $('#parentId').val();}}},messages:{required:'请输入编号',remote:'编号已被使用'}}"
						    	/>
						    </td>
						    <th scope="col" ><span>*</span>排序 </th>
						    <td class="tb_input"  scope="col">
								<input name="sysModulePO.ORDERNUM" value="${orderNum}" class="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/>
						    </td>
					     </tr>
					     <tr>
						    <th scope="col" ><span>*</span>授权类别</th>
						    <td class="tb_input"  scope="col"><span class="tb_input">
						    	<s:if test='sysModulePO.USER_TYPE != null && sysModulePO.USER_TYPE !=""'>
							    	 <s:select list="userType" listKey="value" listValue="display" headerKey="" headerValue="请选择" value="sysModulePO.USER_TYPE" disabled="true"  cssClass="{required:true,messages:{required : '请选择授权人类别'}}"/>
							    	 <s:hidden name="sysModulePO.USER_TYPE" />
						    	</s:if>
						    	<s:else>
							    	 <s:select list="userType" listKey="value" listValue="display" headerKey="" headerValue="请选择" name="sysModulePO.USER_TYPE"  cssClass="{required:true,messages:{required : '请选择授权人类别'}}"/>
						    	</s:else>
						    </span></td>
						    <th scope="col" ><span>*</span>是否有效</th>
						    <td class="tb_input"  scope="col">
								<s:radio list="isValid" listKey="value" listValue="display" value="'10'"  name="sysModulePO.IS_VALID" cssClass="{required:true,messages:{required : '请选择是否有效'}}" onchange=""/>
					         </td>
						    
						    <th scope="col" ><span>*</span>模块类别 </th>
						    <td class="tb_input"  scope="col">
								<s:radio id="menuType" list="menuType" listKey="value" listValue="display" value="'20'"  name="sysModulePO.MENU_TYPE" onclick="doHideOperTb(this);" cssClass="{required:true,messages:{required : '请选择模块类别'}}"/>
						    </td>
						    
						  <tr>
						    
   						    <th scope="col" >URL链接</th>
						    <td colspan="3"  scope="col">
						    	<s:textfield name="sysModulePO.URL" cssClass="{stringMaxLength:200}"/>
						    </td>
						    
   						    <th scope="col" ><span>*</span>是否显示</th>
						    <td   scope="col">
						    	<s:radio id="isShow" list="isShow" listKey="value" listValue="display" name="sysModulePO.IS_SHOW" 
						    		cssClass="{required:true,messages:{required:'请选择是否显示'}}" value="'10'"
						    	/>
						    </td>
						  </tr>
						  <tr>
						    <th scope="col" ><p>备注</p></th>
						    <td colspan="5"   scope="col">
						      <s:textarea  cols="45" rows="5" name="sysModulePO.REMARK" cssClass="{stringCheck:true,stringMaxLength:100}"/>
					        </td>
					     </tr>
	  
	      </table>
	      </div>
		<div style="margin-top:10px;">
				<table width="100%" border="1" cellpadding="0" cellspacing="0" id="operTb" class="tablecss">
					<thead>
    	 				<tr class="fixedHeaderTr">
    	 					<th style="width:30px;">&nbsp;</th>
				    		<th style="width:80px;">业务操作</th>
				    		<th style="width:70px;">操作代码</th>
				    		<th style="width:70px;">样式名称</th>
				    		<th style="width:100px;">操作请求URL</th>
				    		<th style="width:90px;">绑定函数</th>
				    		<th style="width:110px;">是否有效</th>
				    		<th style="width:80px;">描述</th>
				    		<th style="width:50px;">排序</th>
				    		<th style="width:60px;">操作</th>
		   				</tr>
									    	
				    	<tr style="display: none;">
				    		<td align="center">0</td>
				    		<td align="center"><s:textfield name="action_name" value="" cssClass="{required:true,maxlength:10,messages:{required : '请输入操作名称'}}"/></td>
				    		<td align="center"><s:textfield name="action_code" value="" cssClass="{string:true,maxlength:10}"/></td>
				    		<td align="center"><s:textfield name="style_name" value="" cssClass="{required:true,string:true,maxlength:30,messages:{required : '请输入样式名称'}}"/></td>
				    		<td align="center"><s:textfield name="url" value="" cssClass="{string:true,maxlength:10}"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" cssClass="{maxlength:200}"/></td>
				    		<td align="center"><s:radio list="isValid" listKey="value" listValue="display" name="is_valid_0" value="'10'" cssClass="{required:true,messages:{required : '请选择是否有效'}}" /></td>
				    		<td align="center"><s:textfield name="remark" value="" cssClass="{stringCheck:true,stringMaxLength:100}"/></td>
				    		<td align="center"><s:textfield name="ordernum" value="" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/></td>
				    		<td align="center">
					    		<div class="branchoperate">
								    <ul>
								   	 <li class="branchdelete"><a href="javascript:;" onclick="doDeleteRow(this);">删除</a></li>
								    </ul>
							    </div>
						    </td>
			    		</tr>
					</thead>
    	 			<tbody>
				    	<tr>
				    		<td align="center">1</td>
				    		<td align="center"><s:textfield name="action_name" value="浏览" readonly="true"/></td>
				    		<td align="center"><s:textfield name="action_code" value="view" readonly="true"/></td>
				    		<td align="center"><s:textfield name="style_name" value="liview" readonly="true"/></td>
				    		<td align="center"><s:textfield name="url" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" readonly="true"/></td>
				    		<td align="center">
				    			<s:radio list="isValid" listKey="value" listValue="display" name="is_valid_1" value="'10'"  cssClass="{required:true,messages:{required : '请选择是否有效'}}"/>
				    		</td>
				    		<td align="center"><s:textfield name="remark" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="ordernum" value="1" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/></td>
				    		<td align="center">
					    		<div class="branchoperate">
								    <ul>
								   	 <li class="branchdelete"><a href="javascript:;"  onclick="doDeleteRow(this);">删除</a></li>
								    </ul>
							    </div>
						    </td>
			    		</tr>
							    		
					    <tr>
					    	<td align="center">2</td>
				    		<td align="center"><s:textfield name="action_name" value="新增" readonly="true"/></td>
				    		<td align="center"><s:textfield name="action_code" value="add" readonly="true"/></td>
				    		<td align="center"><s:textfield name="style_name" value="liadd" readonly="true"/></td>
				    		<td align="center"><s:textfield name="url" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" readonly="true"/></td>
				    		<td align="center"><s:radio list="isValid" listKey="value" listValue="display" name="is_valid_2" value="'10'"  cssClass="{required:true,messages:{required : '请选择是否有效'}}"/></td>
				    		<td align="center"><s:textfield name="remark" value="" readonly="true"/></td>
				    	  	<td align="center"><s:textfield name="ordernum" value="2" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}" /></td>
				    	  <td align="center">
				    	  	<div class="branchoperate">
					    	    <ul>
					    	      <li class="branchdelete"><a href="javascript:;"  onclick="doDeleteRow(this);">删除</a></li>
				    	        </ul>
				    	    </div>
				    	  </td>
			    		</tr>
			    		<tr>
			    			 <td align="center">3</td>
				    		<td align="center"><s:textfield name="action_name" value="修改" readonly="true"/></td>
				    		<td align="center"><s:textfield name="action_code" value="edit" readonly="true"/></td>
				    		<td align="center"><s:textfield name="style_name" value="liedit" readonly="true"/></td>
				    		<td align="center"><s:textfield name="url" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" readonly="true"/></td>
				    		<td align="center"><s:radio list="isValid" listKey="value" listValue="display" name="is_valid_3" value="'10'"  cssClass="{required:true,messages:{required : '请选择是否有效'}}"/></td>
				    		<td align="center"><s:textfield name="remark" value="" readonly="true"/></td>
			    	  		<td align="center"><s:textfield name="ordernum" value="3" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/></td>
			    	  <td align="center">
			    	  	<div class="branchoperate">
				    	    <ul>
				    	      <li class="branchdelete"><a href="javascript:;"  onclick="doDeleteRow(this);">删除</a></li>
			    	        </ul>
			    	    </div>
		    	      </td>
		    		</tr>
				    <tr>
				    		 <td align="center">4</td>
				    		<td align="center"><s:textfield name="action_name" value="删除" readonly="true"/></td>
				    		<td align="center"><s:textfield name="action_code" value="delete" readonly="true"/></td>
				    		<td align="center"><s:textfield name="style_name" value="lidelete" readonly="true"/></td>
				    		<td align="center"><s:textfield name="url" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" readonly="true"/></td>
				    		<td align="center"><s:radio list="isValid" listKey="value" listValue="display" name="is_valid_4" value="'10'"  cssClass="{required:true,messages:{required : '请选择是否有效'}}"/></td>
				    		<td align="center"><s:textfield name="remark" value="" readonly="true"/></td>
				    		<td align="center"><s:textfield name="ordernum" value="4" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/></td>
			    	  <td align="center">
			    	  	<div class="branchoperate">
				    	    <ul>
				    	      <li class="branchdelete"><a href="javascript:;"  onclick="doDeleteRow(this);">删除</a></li>
			    	        </ul>
			    	    </div>
			    	  </td>
		    		 </tr>
		       </tbody>
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
			       	<li class="save" style="margin-right:15px;">
					 	<input type="button" value="" class="save" onclick="doSubmitAddData();">
			       	</li>
		   		  	<li class="addbutton" style="margin-right:15px;">
					 	<input type="button" value="" class="addbutton" onclick="doAddOperation();">
		   		  	</li>
		        </ul>
	      </div>
	    </div>
	  </div>
	</div>
	
	
</s:form>