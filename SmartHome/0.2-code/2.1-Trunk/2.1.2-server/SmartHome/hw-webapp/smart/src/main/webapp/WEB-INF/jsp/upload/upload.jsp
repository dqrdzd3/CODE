<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>

<title>图片上传</title>
<script type="text/javascript">
	var MA002 = '${requestScope.MA002}';
	var ids = '';
	var picback = window.opener.picback;
	var from = window.opener.from;
	if(from == 'undefined' || from == undefined){
		from = '';
	}
	window.onunload = function(){
		var bottomWin = window.top.document.getElementById("bottom").contentWindow;
		var imgs = bottomWin.document.getElementById("xsqimg").getElementsByTagName('img');
		var srcs = '';
		var str = '';
		for(i=0;i<imgs.length;i++){
			//alert(imgs[i].id);
			var id = imgs[i].id;
			str += id + ',';

		}
		str = str.substring(0, str.length - 1);
		
		if(picback){
			window.opener.shuaxin();
		}
		
		window.opener.document.getElementById('c004ids').value = str;
	};
</script>

</head>
<frameset rows="62,*">
	<frame id="top" name="top" src="initTop.action" frameborder=0 border=0  marginwidth=0  marginheight=0  scrolling=0 />
	<frame id="bottom" name="bottom" src="initBottom.action?MA002=${requestScope.MA002}&from=${requestScope.from}" frameborder=0 border=0  marginwidth=0  marginheight=0  scrolling=0 />
</frameset>
</html>