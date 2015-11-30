<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<script type="text/javascript">
	function getChild(str,id){

		//清空
		if(id == 'MA003'){
			clearSelect('MA003');
			clearSelect('MA004');
			document.getElementById('MA003').options.add(new Option('------',''));
			document.getElementById('MA004').options.add(new Option('------',''));
		}
		if(id == 'MA004'){
			clearSelect('MA004');
			document.getElementById('MA004').options.add(new Option('------',''));
		}
		
		
		if(str != '000'){
			var url = $$.getContextPath()+'safety/s108!doFilterType?ran='+Math.random()+'&parentid='+str;
			var param = '';
			//ajax请求，联动
			$.post(url,param,function(result){
				//alert(result);
				var obj = eval('('+result+')');			
				if(obj.length > 0){
					//循环创建数据
					for(i=0;i<obj.length;i++){
						document.getElementById(id).options.add(new Option(obj[i].name,obj[i].id));
					}
				}
			});
		}
	}
	
	function clearSelect(id){
		$('#'+id).empty();
	}
</script>
</head>
<s:form action="" method="post" id="s108form"> 

<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul>
	      <li>隐患登记 
		  </li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv">
      <div id="windowdivmain">
      
      <input type="hidden" name="s108po.MA018" value="${requestScope.planid }" />
       <table  border="0" cellspacing="0" cellpadding="0" class="windowdivmaintable">
		    <tr>
			    <th scope="row"><span>*</span>隐患大类</th>
			    <td  colspan=""  class="tb_input">
			    	<select name="s108po.MA002" onchange="getChild(this.value,'MA003')" class="required">
			    		<option value="">-------</option>
			    		<c:forEach items="${requestScope.codeValueYHDL }" var="v">
			    			<option value="${v.id }">${v.display }</option>
			    		</c:forEach>
			    	</select>
			    </td>
			    <th scope="row"><span>*</span>隐患中类</th>
			    <td  colspan=""  class="tb_input">
			    	<select id="MA003" name="s108po.MA003" onchange="getChild(this.value,'MA004')" class="required">
			    		<option value="">-------</option>
			    		<c:forEach items="${requestScope.codeValueYhZL }" var="v">
			    			<option value="${v.id }">${v.display }</option>
			    		</c:forEach>
			    	</select>
			    </td>
			    <th scope="row"><span>*</span>隐患小类</th>
			    <td  colspan=""  class="tb_input">
			    	<select id="MA004" name="s108po.MA004"  class="required">
			    		<option value="">-------</option>
			    		<c:forEach items="${requestScope.codeValueYhXL }" var="v">
			    			<option value="${v.id }">${v.display }</option>
			    		</c:forEach>
			    	</select>
			    </td>
		    </tr>
		    
		    <tr>
			    <th scope="row"><span>*</span>被检单位</th>
			    <td  colspan=""  class="tb_input"><s:textfield name="s108po.MA005" cssClass="required {stringMaxLength:50}" ></s:textfield></td>
			    <th scope="row"><span>*</span>发现时间</th>
			    <td  colspan=""  class="tb_input"><input onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})" name="s108po.MA006" class="required" type="text" /></td>
			    <th scope="row"><span>*</span>发现地点</th>
			    <td  colspan=""  class="tb_input"><s:textfield name="s108po.MA007" cssClass="required {stringMaxLength:50}" ></s:textfield></td>
		    </tr>
		     
		     <tr>
			    <th scope="row"><span>*</span>隐患名称</th>
			    <td  colspan=""  class="tb_input"><s:textfield name="s108po.MA008" cssClass="required {stringMaxLength:50}" ></s:textfield></td>
			    <th scope="row"><span>*</span>隐患级别</th>
			    <td  colspan=""  class="tb_input">
		    			${requestScope.yhjb }
						<input type="hidden" name="s108po.MA009" value="${requestScope.yhjb }" />
			    </td>
			    <th scope="row"><span>*</span>隐患来源</th>
			    <td  colspan=""  class="tb_input">
			    	<select name="s108po.MA010">
			    		<option value="日常巡检">日常巡检</option>
			    		<option value="投诉举报">投诉举报</option>
			    		<option value="其他部门移交">其他部门移交</option>
			    	</select>
			    </td>
		    </tr>
		    
		     <tr>
			    <th scope="row"><span>*</span>可能损失（万元）</th>
			    <td  colspan=""  class="tb_input"><s:textfield name="s108po.MA021" cssClass="required {stringMaxLength:16,zFloat:true}" ></s:textfield></td>
			    <th scope="row" ><span>*</span>可能伤亡（人数）</th>
			    <td  colspan="3"  class="tb_input">
		    			<s:textfield name="s108po.MA022" cssClass="required {stringMaxLength:16,zNumber:true}" ></s:textfield>
			    </td>
		    </tr>
		    
		     <tr>
			    <th scope="row" ><span>*</span>检查人</th>
			    <td  colspan=""  class="tb_input"><s:textfield name="s108po.MA023" cssClass="required {stringMaxLength:50}" ></s:textfield></td>
			    <th scope="row" ><span>*</span>检查时间</th>
			    <td  colspan="3"  class="tb_input">
		    			<input onclick="hwDatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})" class="Wdate required" name="s108po.MA024" cssClass="required" />
			    </td>
		    </tr>

		    
		    <tr>
		    <th scope="row"><span>*</span>形成原因</th>
		    <td colspan="5"  class="tb_input">
				<textarea name="s108po.MA011" rows="10" cols="" maxlength="1000" class="required {stringMaxLength:1000}"></textarea>
			</td>
		    </tr>
		    
		    <tr>
		      <th scope="row"><span>*</span>危害影响</th>
		      <td  colspan="5"  class="tb_input"  >
				<textarea name="s108po.MA012" rows="10" cols="" maxlength="1000" class="required {stringMaxLength:1000}"></textarea>
			  </td>
		    </tr>
		</table>
      </div>
    </div>
  </div>
  
  
  
<div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright"><input type="hidden" id="lsize" value="${lsize}"/>
      <ul><li ><input type="button" class="back" onclick="closes108()"/></li>
       	<li  style="margin-right:15px;"><input type="button" class="save" onclick="subs108()"/></li>

      <li>( 说明：<span>*</span>号位必填项)</li>
      </ul>
      </div>
    </div>
  </div>
</div>
</s:form>


