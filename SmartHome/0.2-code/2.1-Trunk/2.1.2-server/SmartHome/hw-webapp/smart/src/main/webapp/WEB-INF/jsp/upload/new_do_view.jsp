<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<%=path %>/res/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript" src="<%=path %>/res/image/jquery.divbox.js"></script>
<script type="text/javascript" src="<%=path %>/res/image/jquery.rotate.1-1.js"></script>
<title>Insert title here</title>
<style>
		#divSCA
        {
            border: 0px solid #000;
            z-index: 10001;
            display: none;
			position:relative;
        }
       .divinput{ position:absolute; top:0px;left:0px;display:none;}
	   li{ list-style:none; padding:2px;float:left;}
	   a img{ border:0;}
	</style>
	
	
	<script type="text/javascript">
	    function suofang(){
	    	 var img = $("#Imgbox");
			 var oWidth, oHeight;
			 if (document.all) {
				oWidth=img[0].getAttribute('width'); 
			 	oHeight=img[0].getAttribute('height'); 
			 } else {
				oWidth=img.width(); 
				oHeight=img.height();
			 }

			 //如果宽大于高，且宽大于400，缩小
			 if(oWidth > 400 && oWidth > oHeight){
				 suoxiao(400 - oWidth);
			 }

			 if(oHeight > 300 && oHeight > oWidth){
				 suoxiao(300 - oHeight);
			 }
	    }
		function openDiv() {
		    
		    
  			$("#divSCA").OpenDiv();

  			suofang();
	
			resizeDiv();
		}
	
		function closeDiv() {
			//$("#divSCA").html('');
			//$("#divSCA").CloseDiv();
			//关闭tab页
			//$$.getFrame('rightFrame').Tabs.closeTab($$.getFrame('rightFrame').Tabs.getTabIndex($$.getFrame('rightFrame').Tabs.getTabById('tpyl')));
			count = 0;
		}

		
		var count = 0;

    //放大图片
    function fangda(size) {
	
	  if(count < 1){
			 var img = $("#Imgbox");
			var oWidth, oHeight;
			 if (document.all) {
				oWidth=img[0].getAttribute('width'); 
			 	oHeight=img[0].getAttribute('height'); 
			 } else {
				oWidth=img.width(); 
				oHeight=img.height();
			 }
			 if(oWidth >= oHeight){
				img.width(oWidth + size);
				img.height(oHeight + size/oWidth*oHeight);
			 }else{
				//alert(oWidth + size/oHeight*oWidth);
				img.width(oWidth + size/oHeight*oWidth);
				//alert(oHeight + size);
				img.height(oHeight + size);
			 }
			 
			 //alert(img.width());
			 //alert(img.height());
			 count++;
			 resizeDiv();
		}
    }

	function suoxiao(size){

			 var img = $("#Imgbox");
			 var oWidth, oHeight;
			 if (document.all) {
				oWidth=img[0].getAttribute('width'); 
			 	oHeight=img[0].getAttribute('height'); 
			 } else {
				oWidth=img.width(); 
				oHeight=img.height();
			 }
			 if(oWidth >= oHeight){
				img.width(oWidth + size);
				img.height(oHeight + size/oWidth*oHeight);
			 }else{
				img.width(oWidth + size/oHeight*oWidth);
				img.height(oHeight + size);
			 }

	
			 resizeDiv();

	}

    function zhuan(str){
		$('#Imgbox').rotateRight(str);
		suofang();
		//移动div的位置
		resizeDiv();
	
	}
	
	function resizeDiv(){
	 var MyDiv_w = $('#divSCA').width();
	 var MyDiv_h = $('#divSCA').height();
	 MyDiv_w = parseInt(MyDiv_w);
	 MyDiv_h = parseInt(MyDiv_h);


	 var width = $.PageSize().Width;
	 var height = $.PageSize().Height;

	 var Div_topposition = (height / 2) - (MyDiv_h / 2);
	 var Div_leftposition = (width / 2) - (MyDiv_w / 2);


	 $('#divSCA').css("left", Div_leftposition + "px");
     $('#divSCA').css("top", Div_topposition + "px");
	}

	function showButton(){
		$('#divinput').show();
	}

	function hideButton(){
		$('#divinput').hide();
	}
    
	function chakanyuantu(){
       window.open('<%=path%>/upload/doViewPic.action?path=${requestScope.po.MA006 }');
	}
	
	window.onload = function(){
		openDiv();
	};
	</script>
</head>
<body>
  <div id="divSCA">
  	 <div><img id="Imgbox" onmouseover="showButton()" onmouseout="hideButton()" src="<%=path %>${requestScope.po.MA006 }"  /></div>
	 <div id="divinput" onmouseover="showButton()" onmouseout="hideButton()" class="divinput">
		<li><a href="javascript:zhuan(90)" ><img width="24" height="24" src="<%=path %>/res/image/shunshizhen.png" alt="顺时针旋转"></a></li>
		<li><a href="javascript:zhuan(-90)" ><img width="24" height="24" src="<%=path %>/res/image/nishizhen.png" alt="逆时针旋转"></a></li>
		<li><a href="javascript:chakanyuantu()" ><img width="24" height="24" src="<%=path %>/res/image/chakanyuantu.png" alt="查看原图"></a></li>
		<!-- <li><a href="javascript:closeDiv()" ><img width="24" height="24" src="<%=path %>/res/image/close.png" alt="关闭"></a></li> -->
	</div>
  </div>
</body>
</html>