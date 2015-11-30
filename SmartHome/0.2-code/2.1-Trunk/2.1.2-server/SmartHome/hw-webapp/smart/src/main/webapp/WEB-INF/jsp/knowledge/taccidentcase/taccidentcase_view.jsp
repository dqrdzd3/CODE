<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function closeDiv(id){
		$("#" + id).dialog('close');
	}
</script>
<s:form action="accident" method="post" id="accident"> 	 
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>经典事故案例库</li>
      </ul>
    </div>
  </div>
 <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowdivmain"><table  border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
  <tr><s:hidden name="taccidentcasePO.objid" />
    <th>事故名称</th>
    <td colspan="3"><s:textfield name="taccidentcasePO.name" id="name" cssClass="inputmodify"></s:textfield>
    </td>
  </tr>
  <tr>
    <th style="width:120px;">事故类型</th>
    <td style="width:260px;">
     <s:select list="sglxList" name="taccidentcasePO.type" id="sgtype" listKey="value" listValue="name" headerKey="" headerValue="-----"  cssClass="required"></s:select>
   </td>
    <th style="width:120px;">事故发生地点</th>
    <td style="width:260px;"> <s:textfield name="taccidentcasePO.address"  cssClass="inputmodify"></s:textfield> 
    </td>
  </tr>
  <tr>
    <th>事故行业</th>
    <td>
     <s:select list="sghyList" name="taccidentcasePO.casetrade" id="sghy" listKey="value" listValue="name" headerKey="0" headerValue="-----" ></s:select>
	</td>
    <th>事故发生时间</th>
    <td><s:textfield value="taccidentcasePO.createtime"><s:param name="value"><s:date name="taccidentcasePO.createtime" format="yyyy-MM-dd"></s:date></s:param></s:textfield></td>
  </tr>
  <tr>
    <th align="right">事故模型</th>
    <td colspan="3"><s:textarea name="taccidentcasePO.module" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}" ></s:textarea></td>
  </tr>
  <tr>
    <th align="right">死亡原因</th>
    <td colspan="3"><s:textarea name="taccidentcasePO.cause" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}" ></s:textarea></td>
  </tr>
  <tr>
    <th align="right">解决办法</th>
    <td colspan="3"><s:textarea name="taccidentcasePO.way" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}" ></s:textarea></td>
  </tr>
  <tr>
    <th align="right">死亡人数(人)</th>
    <td><s:textfield name="taccidentcasePO.casesw" cssClass="inputmodify digits"></s:textfield></td>
    <th>重伤人数(人)</th>
    <td><s:textfield name="taccidentcasePO.casezs" cssClass="inputmodify digits"></s:textfield></td>
  </tr>
  <tr>
    <th align="right">轻伤人数(人)</th>
    <td><s:textfield name="taccidentcasePO.caseqs" cssClass="inputmodify digits"></s:textfield></td>
    <th>财产损失(万元)</th>
    <td><s:textfield name="taccidentcasePO.caseproperty" cssClass="inputmodify decimal"></s:textfield></td>
  </tr>
  <tr>
    <th align="right">备注</th>
    <td colspan="3"><s:textarea name="taccidentcasePO.remarks" cols="45" rows="5" cssClass="textareamodify {maxlength:200}" ></s:textarea></td>
  </tr>

</table>
</div>
    </div></div>
  
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
</script>

