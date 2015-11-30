<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<s:form method="post" action="s009" id="s009" enctype="multipart/form-data">
		<div id="windows">
		  <div id="windowstop">
		    <div id="windowstopleft">
		      <ul>
		      <li>安全生产落实情况</li>
		      </ul>
		    </div>
		  </div>
		  <div id="windowsmain">
		    <div id="windowdiv">
		      <div id="windowdivmain">
			      <table id="addDutyTable" border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
			    	<tbody>
			    		<tr>     <s:hidden name="s121PO.ma001" ></s:hidden>
						    <th><span >*</span>记录名称</th>
							<td>
								<s:textfield  name="s121PO.ma002" cssClass="required"/>
							</td>
							
							 <td class="title">检查类型</td>
					      <td>
							<s:select list="checktype" name="s121PO.ma004" listKey="value" listValue="display" headerKey=""  headerValue="请选择" />
				     	   </td>
				     	   <th>检查责任人</th>
		  				   <td >
								<s:textfield name="s121PO.ma008" />
	              		   </td>
						</tr>	
				        <tr>
					     <th>记录生成时间</th>
			 				<td>
								<s:textfield  name="s121PO.ma003"  cssClass="Wdate" onclick="hwDatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
				     	   </td>
					      <td >检查开始时间</td>
					      <td >
									<s:textfield  name="s121PO.ma005"  id="MA005" cssClass="Wdate" onclick="hwDatePicker({maxDate:$dp.$('MA006').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
						  </td>
						  <th>检查结束时间</th>
		  				  <td >
		  							<s:textfield  name="s121PO.ma006"  id="MA006" cssClass="Wdate" onclick="hwDatePicker({minDate:$dp.$('MA005').value,dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:'true'})"/>
	              		</td>
					  </tr>
					<tr>
			  			<th>检查内容摘要</th>
			  			<td colspan="5">
			  				<s:textarea name="s121PO.ma007" maxLength="200" cssClass="{maxlength:200,minlength:0} textareamodify" ></s:textarea>
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
				       <li class="save">
				       		<s:submit value="" cssClass="back" method="doList" style="border:none;"></s:submit>
				       	</li>
			     	   <li class="cancel" style="margin-right:15px;">     
				           <s:submit value="" cssClass="submit" method="doSaveAdd" style="border:none;"></s:submit>
				       </li>
				      <li>( 说明：<span>*</span>号位必填项)</li>
			      </ul>
		      </div>
		    </div>
		  </div>
		</div>
	</s:form>
