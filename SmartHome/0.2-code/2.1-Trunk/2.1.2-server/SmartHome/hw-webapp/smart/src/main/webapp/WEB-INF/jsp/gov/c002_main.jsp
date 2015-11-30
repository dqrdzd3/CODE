<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>政府机构</title>     
		
	</head>
	
	<frameset cols="173,*" frameborder="no" border="0" framespacing="0">
	  <frame src="c002!loadTree?code=${code}" name="govLeftFrame"  noresize="noresize" id="govLeftFrame" title="govLeftFrame" />
	  <frame src="c002!list?code=${code}" name="govRightFrame" id="govRightFrame" title="govRightFrame" />
	</frameset>
	
	<noframes>
		<body></body>
	</noframes>
</html>