<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form  method="post" id="s001Add" enctype="multipart/form-data">
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>to B 权限分配</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
		<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
			<th scope="col"><span>*</span>公司名</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma002"
					cssClass="required {maxlength:32,minlength:0} " /></td>
			<th scope="col"><span>*</span>联系人</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma003" cssClass="required {maxlength:32,minlength:0}" /></td>
			<th scope="col"><span>*</span>联系方式</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma004" cssClass="required {maxlength:32,minlength:0}" /></td>		
					
		</tr>
		<tr>
			<th scope="col">通讯地址</th>
			<td colspan="6"><s:textfield
					name="businessPO.ma005"
					/></td>
	
		</tr>
		<tr>
		
			<th scope="col">法人</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma006"  /></td>
			<th scope="col">法人联系方式</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma007" /></td>	
			<th scope="col">邮件</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma014" /></td>				
					
		</tr>
		<tr>
		
			<th scope="col"><span>*</span>登录帐号</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma009"  cssClass="required {maxlength:32,minlength:0} " /></td>
			<th scope="col"><span>*</span>密码</th>
			<td class="tb_inputtwo" scope="col"><s:textfield
					name="businessPO.ma010" cssClass="required {maxlength:32,minlength:0} " /></td>	
				<th scope="col"></th>
			<td class="tb_inputtwo" scope="col"></td>				
					
		</tr>
<tr>
			<th scope="row">营业执照</th>
			<td colspan="6"> <img id="preImg_advertise1" width="300px" alt="图片预览" src="images/pic_add.png" width="150px"/><input type="file" id="file" name="file" value="上传" /><input type="button" value="上传" onclick="uploadImage('advertise1','file')" /><input type="hidden" id = "id_advertise1" name="businessPO.ma008" value="" /></td>
		</tr>
		<tr>
			<th scope="row">LOGO（64）</th>
			<td colspan="6"> <img id="preImg_advertise2" width="64px" alt="图片预览" src="images/pic_add.png" width="150px"/><input type="file" id="file1" name="file" value="上传" /><input type="button"  value="上传" onclick="uploadImage('advertise2','file1')" /><input type="hidden" id = "id_advertise2" name="businessPO.ma012" value="" /></td>
		</tr>
		<tr>
			<th scope="row">LOGO（512）</th>
			<td colspan="6"> <img id="preImg_advertise3" width="512px" alt="图片预览" src="images/pic_add.png" width="150px"/><input type="file" id="file2" name="file" value="上传" /><input type="button"  value="上传" onclick="uploadImage('advertise3','file2')" /><input type="hidden" id = "id_advertise3" name="businessPO.ma013" value="" /></td>
		</tr>
		<tr>
			<th scope="row">备注</th>
			<td colspan="6"><s:textarea name="businessPO.ma016"
				 /></td>
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
