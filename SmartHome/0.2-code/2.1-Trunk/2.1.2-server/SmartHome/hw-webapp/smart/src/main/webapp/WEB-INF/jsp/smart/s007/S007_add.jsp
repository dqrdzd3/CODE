<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form  method="post" id="s001Add" >
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>状态解决</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col" ><span>*</span>气体类型</th>
							<td class="tb_inputtwo" scope="col"><s:select  name="s007po.ma002" 
								       headerKey="-1" headerValue="请选择"  
								       list="#{0:'温度', 1:'湿度', 2:'co2', 3:'pm2.5', 4:'voc', 5:'c6h6', 6:'ch2o', 7:'酒精', 8:'co', 9:'ch4'}"/></td>
						</tr>
						<tr>
							<th scope="col" ><span>*</span>环境类型</th>
							<td class="tb_inputtwo" scope="col"><s:select  name="s007po.ma009" 
								       headerKey="0" headerValue="其他环境"  
								       list="#{0:'其他环境', 1:'新房', 2:'新车', 3:'新家具', 4:'户外PM2.5'}"/></td>
						</tr>
						<tr>
							<th scope="col" ><span>*</span>状态</th>
							<td class="tb_inputtwo" scope="col">
							  <s:select  name="s007po.ma003" 
								       headerKey="-1" headerValue="请选择"  
								       list="#{0:'低', 1:'正常', 2:'高'}"/></td>
						</tr>
						<tr>
							<th scope="col" ><span>*</span>标题</th>
							<td class="tb_inputtwo" scope="col"><s:textfield
									name="s007po.ma004"
									cssClass="required {maxlength:30,minlength:0} ,isMobile" /></td>
						</tr>
						<tr>
							<th scope="row" ><span>* </span>建议方法</th>
							<td colspan="3"><s:textarea name="s007po.ma005"
									cssClass="textareamodify required {maxlength:2000,minlength:0}" /></td>
						</tr>
						<tr>
							<th scope="row" ><span>* </span>商城路径</th>
							<td colspan="3"><s:textarea name="s007po.ma008"
									cssClass="textareamodify required {maxlength:2000,minlength:0}" /></td>
						</tr>
					</table>
				</div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
	      <ul>
	       <li class="cancel" style="margin-right:15px;"><input type="button" class="back" style="border:none;" id="backBtn" onclick="closeshowd()" /></li>
	       <li class="save"><input type="button" class="save" style="border:none;" id="saveBtn" onclick="saveS001()" /></li>
	      <li>( 说明：<span>*</span>号为必填项)</li>
	      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
