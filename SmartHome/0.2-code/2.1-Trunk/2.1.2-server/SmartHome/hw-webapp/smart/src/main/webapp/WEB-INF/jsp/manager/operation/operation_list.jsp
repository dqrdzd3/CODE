<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    
<s:form id="operList">
	<div id="windows">
	  <div id="windowstop">
	    <div id="windowstopleft">
	      <ul><li>业务操作修改</li>
	      </ul>
	    </div>
	  </div>
	  <div id="windowsmain">
	    <div id="windowdiv">
	    <div id="windowdivmain">
	      <div style="margin-top:0px;">
	      		<s:hidden id="menu_uuid" name="sysOperationPO.MENU_UUID"/>
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
			    			<td align="center">
				    			<s:hidden name="uuid" value=""/>
				    			<s:textfield name="action_name" value="" cssClass="{required:true,maxlength:10,messages:{required : '请输入操作名称'}}"/>
				    		</td>
				    		<td align="center"><s:textfield name="action_code" value="" cssClass="{string:true,maxlength:10}"/></td>
				    		<td align="center"><s:textfield name="style_name" value="" cssStyle="{required:true,string:true,maxlength:30,messages:{required : '请输入样式名称'}}"/></td>
				    		<td align="center"><s:textfield name="url" value="" cssClass="{maxlength:10}"/></td>
				    		<td align="center"><s:textfield name="bind_func" value="" cssClass="{maxlength:200}"/></td>
				    		<td align="center"><s:radio list="isValid" listKey="value" listValue="display" name="is_valid_0" value="'10'"  cssClass="{required:true,messages:{required : '请选择是否有效'}}"/></td>
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
    	 				<s:iterator var="operation" value="operationList" status="s">
	    	 				
					    	<tr>
					    		<td align="center">
					    			<s:property value="#s.index+1"/>
					    		</td>
					    		<td align="center">
					    			<s:hidden name="uuid" value="%{#operation.UUID}"/>
					    			<s:textfield name="action_name" value="%{#operation.ACTION_NAME}"  cssClass="{required:true,maxlength:10,messages:{required : '请输入操作名称'}}"/>
					    		</td>
					    		<td align="center"><s:textfield name="action_code" value="%{#operation.ACTION_CODE}" cssClass="{string:true,maxlength:10}"/></td>
					    		<td align="center"><s:textfield name="style_name" value="%{#operation.STYLE_NAME}" cssStyle="{required:true,string:true,maxlength:30,messages:{required : '请输入样式名称'}}"/></td>
					    		<td align="center"><s:textfield name="url" value="%{#operation.URL}" cssClass="{maxlength:10}"/></td>
					    		<td align="center"><s:textfield name="bind_func" value="%{#operation.BIND_FUNC}" cssClass="{maxlength:200}"/></td>
					    		<td align="center">
					    			<s:radio list="isValid" listKey="value" listValue="display" name="is_valid_%{#s.index+1}" value="%{#operation.IS_VALID}" cssClass="{required:true,messages:{required : '请选择是否有效'}}"/>
					    		</td>
					    		<td align="center"><s:textfield name="remark" value="%{#operation.REMARK}" cssClass="{stringCheck:true,stringMaxLength:100}"/></td>
					    		<td align="center"><s:textfield name="ordernum" value="%{#operation.ORDERNUM}" cssClass="{required:true,digits:true,messages:{required:'请输入排序号',digits:'只能输入整数'}}"/></td>
					    		<td align="center">
						    		<div class="branchoperate">
									    <ul>
									   	 <li class="branchdelete"><a href="javascript:;"  onclick="doDeleteRow(this);">删除</a></li>
									    </ul>
								    </div>
							    </td>
				    		</tr>
    	 				</s:iterator>
							    		
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
			 	<input type="button" value="" class="save" onclick="doSubmitOperData();">
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