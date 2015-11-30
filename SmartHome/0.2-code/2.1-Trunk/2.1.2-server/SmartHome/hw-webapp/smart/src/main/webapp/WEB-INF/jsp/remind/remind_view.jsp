<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:action name="include(mainCSS,jquery)" executeResult="true" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	$(function(){
		var url = '${requestScope.po.MA004}';
		var param = '';
		$.post(url,param,function(result){
			$('#content').html(result);
		});
	});
	
	function closedia(){
		jQuery('#opendiv').dialog('close');
	}
</script>




<div id="windows">
  <div id="windowstop">
    <div id="windowstopleft">
      <ul><li>待办业务提醒内容</li>
      </ul>
    </div>
  </div>
  <div id="windowsmain">
    <div id="windowdiv"><div id="windowdivmain">
    
<form id="stateform">

  <div>

  	 
  </div>
  
  <br><br>
  <div>
  	<div id="content"></div>
  </div></form>
    </div>
    </div>
  </div>
 


  <div id="windowsbottom">
    <div id="windowsbottomleft">
      <div id="windowsbottomright"><input type="hidden" id="lsize" value="${lsize}"/>
      <ul><li ><input type="button" class="back" onclick="closedia()"/></li>
      <s:if test="planstate!=1">
       	<li  style="margin-right:15px;"><input type="button" class="save" onclick="substate()"/></li>
      </s:if>
      </ul>
      </div>
    </div>
  </div>
  
  
</div>
  
</body>
</html>