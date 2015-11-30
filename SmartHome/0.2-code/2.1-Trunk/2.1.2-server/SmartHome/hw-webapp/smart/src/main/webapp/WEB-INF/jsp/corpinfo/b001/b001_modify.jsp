<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title>企业基本信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,validatorJS,grid)" executeResult="true"/>
</head>
<body>
<s:form action="b001" method="post" > 
<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
      <li>企业概况表</li>
      </ul>
    </div>
  </div> 
<div id="windowsmain">
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>基本信息</div>
        </div>
      </div><div id="windowdivmain">
       <table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable" id="windowdivmaintable">
	        		<tbody>
	        		
	        		<s:hidden name="b001PO.MA001" />
	        		<s:hidden name="b001PO.MA028" />
	        		<s:hidden name="b001PO.MA038" />
	        		<s:hidden name="b001PO.MA039" />
	        	
	        		<tr>
			  			<th><span > *</span> 法人单位名称</th>
			  			<td>
			  				<s:textfield name="b001PO.MA004" id="MA004"></s:textfield>
			  				<s:fielderror cssStyle="color:red;">  
			  				<s:param>b001PO.MA004</s:param> 
                          </s:fielderror>  
						</td>
						<th><span > * </span>单位代码</th>
	  					<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA003" id="MA003"></s:textfield>
			  			</td>
	  				</tr>
  					<tr>
			  			<th>单位负责人姓名</th>
			  			<td>
			  				<s:textfield name="b001PO.MA026" id="MA026"></s:textfield>
						</td>
			  			<th>单位负责人电话</th>
			  			<td>
			  				<s:textfield name="b001PO.MA027" id="MA027"></s:textfield>
			  			</td>
  					</tr>
			  		<tr>
			  			<th>通讯地址</th>
			  			<td>
			  				<s:textfield name="b001PO.MA033" id="MA033"></s:textfield>
			  			</td>
			  			<th>邮政编码</th>
			  			<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA034" id="MA034"></s:textfield>
			  			</td>
			  		</tr>
			  		<tr>
			  			<th>地区</th>
			    		<td>
			    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
 <tr>
    <td style="margin:0; padding:0;border:none;"><s:select list="provinceList" id="pts" onchange="upsc()" listKey="code" listValue="desc" headerKey="" headerValue="请选择" name="b001PO.MA007"></s:select>
    <td style="margin:0; padding:0;border:none;"><s:select list="cityList" id="cityts" onchange="ucitysc()" listKey="code" listValue="desc" headerKey="" headerValue="请选择" name="b001PO.MA008"></s:select>
    <td style="margin:0; padding:0;border:none;"><s:select list="countyList" id="cts"  listKey="code" listValue="desc" headerKey="" headerValue="请选择" name="b001PO.MA009"></s:select>
  </tr>
</table>

			    		</td>
			  			<th>企业email地址</th>
			  			<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA020" id="MA020"></s:textfield>
			  			</td>
			  		</tr>
			  		<tr>
			  			<th><span > * </span>中央企业</th>
			  			<td>
	  					<s:select list="codeValueSF" name="b001PO.MA019" id="b001PO.MA019" listKey="value" listValue="name" headerKey="" headerValue="请选择" ength="25" ></s:select></td>
			  		
			  			<th><span > *</span> 经济类型</th>
			  			<td>
			  				<s:select list="codeValueJJLX" name="b001PO.MA010" id="b001PO.MA034" listKey="value" listValue="display" headerKey="" headerValue="请选择" ength="25" ></s:select></td>
			  		</tr>
                   
                    <tr>
                    <th><span > *</span> 所属行业</th>
			  			<td class="tb_inputtwo"><s:select list="codeValueSSHY" name="b001PO.MA011" id="b001PO.MA011" listKey="value" listValue="name" headerKey="" headerValue="请选择" ength="25" ></s:select></td>
			  			<th><span > *</span> 成立时间</th>
			  			<td>
			  				<s:textfield name="b001PO.MA012" id="MA012" cssClass="hwDatePicker Wdate"></s:textfield>
						</td>
			  			
			  		</tr>
                    <tr>
                    <th>占地面积(m<sup>2</sup>)</th>
			  			<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA025" id="MA025"></s:textfield>
			  			</td >
			  			<th>行业管理部门</th>
			  			<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA013" id="MA013"></s:textfield>
			  			</td>
			  			
			  		</tr>
                    <tr>
                    <th style="width:150px;">职工总数(人)</th>
			  			<td class="tb_inputtwo">
			  				<s:textfield name="b001PO.MA014" id="MA014"></s:textfield>
			  			</td>
			  			<th style="width:150px;">固定资产总值(万元)</th>
			  			<td class="tb_inputtwo"><s:textfield name="b001PO.MA015" id="MA015"></s:textfield></td>
			  			
			  		</tr>
                    <tr>
                    <th>年利润(万元)</th>
			  			<td class="tb_inputtwo"><s:textfield name="b001PO.MA017" id="MA017"></s:textfield></td>
			  		<th>年总收入(万元)</th>
			  			<td  ><s:textfield name="b001PO.MA016" id="MA016"></s:textfield></td>			
			  		</tr>
                    <tr>
			  			<th>主要产品</th>
			  			<td colspan="3" >
			  				<s:textarea name="b001PO.MA018" id="MA018" cols="45" rows="5" cssClass="textareamodify"></s:textarea>
			  			</td>
			  		</tr>
			  				</tbody></table>
      </div>
    </div>
    <div id="windowdiv">
      <div id="windowtitle">
        <div id="windowtitleleft">
          <div>GIS定位及备注信息  </div>
        </div>
      </div>
      <div id="windowdivmain"><table border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
			 	     <tbody><tr>
			 			<th style="width:150px;">经度</th>
			 			<td style="width:280px;">
			 				<s:textfield name="b001PO.MA035" id="MA035"></s:textfield>
			 				<!-- <input type="button" class="btn_locat"value="定位" onClick="showDitu('http://192.168.1.154:8080/ym/gis/clientgis.jsp');"/> -->
			 				
			 			</td>
			 			<th style="width:150px;">纬度</th>
			 			<td style="width:260px;">
			 				<s:textfield name="b001PO.MA036" id="MA036"></s:textfield>
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
       <li class="cancel" style="margin-right:15px;"><s:submit value="" method="doList" cssClass="back"></s:submit> </li>
      <li class="save"><s:submit value="" method="doSaveEdit" cssClass="submit"></s:submit></li>
      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>
</body>
</html>
