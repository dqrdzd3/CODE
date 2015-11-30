<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="b007" method="post" id="b007addform"> 
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>添加安全经费情况  </li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
       <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
       <colgroup>
        <col width="140"></col>
        <col width="210"></col>
        <col width="140"></col>
        <col width="210"></col>
       </colgroup>
       <tr>	
       <s:hidden name="b007PO.ma001" id="ma001"></s:hidden>
	   <s:hidden name="b007PO.ma002" id="ma002"></s:hidden>
    <th>单位名称</th>
    <td colspan="3" class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma004" id="ma004" disabled="true"></s:textfield>
    </td>
    </tr>
  <tr>
    <th><span>* </span>投入编号</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma003" id="ma003" cssClass='{required:true,maxlength:30,stringCode:true}'></s:textfield></td>
    <th><span>* </span>投入年度</th>
    <td class="tb_inputtwo"  scope="col"><label for="select"></label>
     <s:select list="#{'2012':'2012','2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020'}" name="b007PO.ma005"   headerKey="" headerValue="请选择" ength="25" cssClass='required'></s:select>
      </td>
    </tr>
  <tr>
    <th><span>* </span>投入资金概算(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma006" id="ma006" cssClass="{required:true,maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    <th>同期年总收入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma007" id="ma007" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    </tr>
    <tr>
    <th>投入提取方式</th>
    <td colspan="3" class="tb_inputtwo"  scope="col"><s:textarea name="b007PO.ma008" id="ma008" cols="45" rows="5" cssClass="{maxlength:50,minlength:0} textarea1"></s:textarea></td>
    </tr>
    <tr>
    <th>评价取证投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma009" id="ma009" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    <th>劳保用品投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma010" id="ma010"  cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    </tr>
    <tr>
    <th>安全教育投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma011" id="ma011" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    <th>隐患整改投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma012" id="ma012" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    </tr>
    <tr>
    <th>安全科研投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma013" id="ma013" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}" /></td>
    <th>安全技改投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma014" id="ma014" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}" /></td>
    </tr>
    <tr>
    <th>其他安全投入(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma015" id="ma015" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    <th>年度安全投入总额(万元)</th>
    <td class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma016" id="ma016" cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    </tr>
    <tr>
    <th>安全投入占年总收入百分比</th>
    <td colspan="3" class="tb_inputtwo"  scope="col"><s:textfield name="b007PO.ma017" id="ma017"  cssClass="{maxlength:17, number:true, decimal:true,min:0,messages:{maxlength:'请输入少于17的有效数字'}}"/></td>
    </tr>
  <tr>
    <th>经费投入自我评价</th>
    <td colspan="3"><s:textarea name="b007PO.ma018" id="ma018" maxlength="100" cols="45" rows="5" cssClass="{maxlength:100,minlength:0} textarea1"/></td>
  </tr>
</table>
      </div>
    </div>
  </div>
  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright">
      <ul>
      <li class="save"><s:submit value="" cssClass="back" method="doList"></s:submit></li>
      <li class="cancel" style="margin-right:15px;"><s:submit value="" cssClass="submit" method="doSaveEdit"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>

</s:form>

