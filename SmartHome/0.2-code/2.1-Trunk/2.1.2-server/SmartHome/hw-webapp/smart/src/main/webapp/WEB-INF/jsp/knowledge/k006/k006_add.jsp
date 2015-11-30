<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="k006" method="post" id="k006"> 
<div id="windows">
<div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain"><table width="100%" cellpadding="0" cellspacing="0"  class="windowdivmaintable">

       <s:hidden name="k006PO.MA001"/>
         <s:hidden name="k006PO.MA007"/>
           <s:hidden name="k006PO.MA008"/>
          <s:hidden name="k006PO.MA022"/>
            <s:hidden name="k006PO.MA023"/>
              <s:hidden name="k006PO.MA024"/>
 
<tr>
    <th><span> * </span>疾病名称</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA002" id="MA002" cssClass="{required:true,maxlength:18}"></s:textfield></td>
    <th>疾病别名</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA003" id="MA003" cssClass="{maxlength:18}"></s:textfield></td>
    <th>英 文 名</th>
    <td  class="tb_input"><s:textfield name="k006PO.MA004" id="MA004" cssClass="{maxlength:18,alnum:true}"></s:textfield></td>
  </tr>
  <tr>
    <th>疾病代码</th>
    <td><s:textfield name="k006PO.MA005" id="MA005" cssClass="{maxlength:18}"></s:textfield></td>
    <th>所属类别</th>
    <td> <s:select list="sslbList" name="k006PO.MA006" id="sslb" listKey="value" listValue="name" headerKey="0" headerValue="-----" ></s:select>
   </td>
    <th>人体部位</th>
    <td><s:textfield name="k006PO.MA009" id="MA009" cssClass="{maxlength:100}"></s:textfield></td>
  </tr>
  <tr>
    <th>就诊科室</th>
    <td><s:textfield name="k006PO.MA010" id="MA010" cssClass="{maxlength:2000}"></s:textfield></td>
    <th>相关系统</th>
    <td><s:textfield name="k006PO.MA011" id="MA011" cssClass="{maxlength:2000}"></s:textfield></td>
    <th>患病症状</th>
    <td><s:textfield name="k006PO.MA012" id="MA012" cssClass="{maxlength:2000}"></s:textfield></td>
  </tr>
  <tr>
    <th>相关疾病</th>
    <td colspan="3"><s:textfield name="k006PO.MA013" id="MA013" cssClass="{maxlength:2000}"></s:textfield></td>
    <th>化验检查</th>
    <td><s:textfield name="k006PO.MA014" id="MA014" cssClass="{maxlength:2000}"></s:textfield></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>疾病概述</th>
    <td colspan="5"><s:textarea name="k006PO.MA015" maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>症状体征</th>
    <td colspan="5"><s:textarea name="k006PO.MA016" maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>诊断检查</th>
    <td colspan="5"><s:textarea name="k006PO.MA017" maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>治疗方案</th>
    <td colspan="5"><s:textarea name="k006PO.MA018"  maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>预防及预后</th>
    <td colspan="5"><s:textarea name="k006PO.MA019" maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>保健小贴士</th>
    <td colspan="5"><s:textarea name="k006PO.MA020"  maxlength="2000" cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
  <tr>
    <th><span>(2000字以内)</span>备注</th>
    <td colspan="5"><s:textarea name="k006PO.MA021" maxlength="2000"  cols="45" rows="5" cssClass="textareamodify {maxlength:2000}"></s:textarea></td>
    </tr>
</table>
 </div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
       <li class="save"><s:submit value="" cssClass="back" method="doList" style="border:none;"></s:submit></li>
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




