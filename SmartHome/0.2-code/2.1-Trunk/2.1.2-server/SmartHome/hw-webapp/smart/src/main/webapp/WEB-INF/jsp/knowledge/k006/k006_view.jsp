<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function closeDiv(id){
		$("#" + id).dialog('close');
	}
</script>
<s:form action="k006" method="post" id="k006"> 
<div id="windows">
<div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain"><table width="100%" cellpadding="0" cellspacing="0"  class="windowdivmaintable">
<tr>
    <th>疾病名称</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA002" id="MA002" cssClass="required"></s:textfield></td>
    <th>疾病别名</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA003" id="MA003"></s:textfield></td>
    <th>英 文 名</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA004" id="MA004" cssClass="alnum"></s:textfield></td>
  </tr>
  <tr>
    <th>疾病代码</th>
    <td><s:textfield name="k006PO.MA005" id="MA005"></s:textfield></td>
    <th>所属类别</th>
    <td> <s:select list="sslbList" name="k006PO.MA006" id="sslb" listKey="value" listValue="name" headerKey="0" headerValue="-----" ></s:select>
   </td>
    <th>人体部位</th>
    <td><s:textfield name="k006PO.MA009" id="MA009"></s:textfield></td>
  </tr>
  <tr>
    <th>就诊科室</th>
    <td><s:textfield name="k006PO.MA010" id="MA010"></s:textfield></td>
    <th>相关系统</th>
    <td><s:textfield name="k006PO.MA011" id="MA011"></s:textfield></td>
    <th>患病症状</th>
    <td><s:textfield name="k006PO.MA012" id="MA012"></s:textfield></td>
  </tr>
  <tr>
    <th>相关疾病</th>
    <td colspan="3"><s:textfield name="k006PO.MA013" id="MA013"></s:textfield></td>
    <th>化验检查</th>
    <td><s:textfield name="k006PO.MA014" id="MA014"></s:textfield></td>
    </tr>
  <tr>
    <th>疾病概述</th>
    <td colspan="5"><s:textarea name="k006PO.MA015" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>症状体征</th>
    <td colspan="5"><s:textarea name="k006PO.MA016" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>诊断检查</th>
    <td colspan="5"><s:textarea name="k006PO.MA017" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>治疗方案</th>
    <td colspan="5"><s:textarea name="k006PO.MA018" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>预防及预后</th>
    <td colspan="5"><s:textarea name="k006PO.MA019" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>保健小贴士</th>
    <td colspan="5"><s:textarea name="k006PO.MA020" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th>备注</th>
    <td colspan="5"><s:textarea name="k006PO.MA021" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
</table>
 </div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
      <li class="back"><input type="button" value="" class="back"
							onclick="closeDiv('showcont')"></li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
