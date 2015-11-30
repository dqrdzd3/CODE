<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>企业资质证书信息-<s:text name="application.title" /></title>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<s:action name="include(mainCSS,jquery,validatorJS,grid)" executeResult="true"/>

</head>
<body>
<s:form action="b005" method="post" > 
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>企业执照 
</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>执照图片 - 图像显示等比例
            <input type="radio" onclick="setImageScale(0.2);" value="0.2" name="_imagescale">
20%
<input type="radio" checked="checked" onclick="setImageScale(0.4);" value="0.4" name="_imagescale">
40%
<input type="radio" onclick="setImageScale(0.6);" value="0.6" name="_imagescale">
60%
<input type="radio" onclick="setImageScale(0.8);" value="0.8" name="_imagescale">
80%
<input type="radio" onclick="setImageScale(1);" value="1" name="_imagescale">
100%  </div>
        </div>
      </div><div id="windowdivmain">
        <div class="imgstyle"></div>
      </div>
    </div>
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>企业执照表</div>
        </div>
      </div>
       <div id="windowdivmain">
      <table  cellspacing="0" cellpadding="0" class="windowdivmaintable">
	        		<tbody>
	        		
	        		<s:hidden name="b005PO.MA001"/>
	        		<s:hidden name="b005PO.MA003"/>
	        		<s:hidden name="b005PO.MA006"/>
	        		<s:hidden name="b005PO.MA008"/>
	        		<s:hidden name="b005PO.MA012"/>
	        		
	        		<tr>
			  			<th><span> *</span> 执照名称</th>
  			<td class="tb_inputtwo">
  					<s:textfield name="b005PO.MA004" id="MA004"></s:textfield>
			</td>  			
			<th><span> * </span>执照编号</th>
  			<td class="tb_inputtwo">
  					<s:textfield name="b005PO.MA002" id="MA002"></s:textfield>
			</td>
			
		</tr>
		<tr>
  			<th><span> *</span>发证单位</th>
  			<td class="tb_inputtwo">
  					<s:textfield name="b005PO.MA005" id="MA005"></s:textfield>
			</td>
  			<th><span> *</span> 有效期</th>
  			<td class="tb_inputtwo">
  					<s:textfield name="b005PO.MA007" id="MA007"></s:textfield>
  			</td></tr>
  			<tr>
  				<th style="width:140px;"><span> * </span>状态</th>
  			<td colspan="3">
            	<s:textfield name="b005PO.MA010" id="MA011"   readOnly="true"></s:textfield></td>
  			<th style="width:140px;"><span> * </span>图片文件</th>
  			<td class="tb_inputtwo">
            	<s:file name="upload" style="width:100%; vertical-align:top; margin-right:3px;border:#8BDED2 solid 1px;
	height:19px;"/>
            <s:hidden name="b005PO.MA011" cssclass="selectbutton"/>
  		</tr>
  		
  		<tr>
  			<th>备注</th>
  			<td colspan="3">
  					<s:textarea name="b005PO.MA009" id="MA009" cols="45" rows="5" cssClass="textareamodify"></s:textarea>
  			</td>
  		</tr>
				</tbody></table>
</div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
     <ul>
       <li class="save"><s:submit value="" cssClass="back" method="doList"></s:submit></li>
       <li class="cancel" style="margin-right:15px;"><s:submit value="" cssClass="submit"  method="doSaveEdit"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
</body>
</html>
