<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统字典</title>     


</head>

<frameset cols="173,*" frameborder="no" border="0" framespacing="0">
  <frame src="c007!loadTree" name="dictLeftFrame"  noresize="noresize" id="dictLeftFrame" title="dictLeftFrame" />
  <frame src="c007!list?code=${code}" name="dictRightFrame" id="dictRightFrame" title="dictRightFrame" />
</frameset>
<noframes><body>
</body></noframes>
</html>