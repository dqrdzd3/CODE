<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%request.getRequestDispatcher("weeklySales.do?method=search").forward(request, response); %>
<%-- <%RequestDispatcher rd = request.getRequestDispatcher("weeklySales.do?method=search");
try{
	rd.forward(request, response);
	return;
}catch(Exception e){}
%> --%>
<script type="text/javascript">
function check(){
var weeklySalesId = ${weeklySales.weeklySalesId};
if(weeklySalesId == null){
alert("此条记录已删除");
}else{
//request.getRequestDispatcher("weeklySales.do?method=searchAll").forward(request, response);
	alert("get-dispacher");
	request.getRequestDispatcher("weeklySales.do?method=search").forward(request, response);
}
}
window.onload=check(); 
</script>