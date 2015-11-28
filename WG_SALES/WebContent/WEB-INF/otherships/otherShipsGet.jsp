<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%request.getRequestDispatcher("otherShips.do?method=search").forward(request, response); %>
<script type="text/javascript">
function check(){
var otherShipsId = ${otherShips.otherShipsId};
if(otherShipsId == null){
alert("此条记录已删除");
}else{
//request.getRequestDispatcher("weeklySales.do?method=searchAll").forward(request, response);
	alert("get-dispacher");
	request.getRequestDispatcher("otherShips.do?method=search").forward(request, response);
}
}
window.onload=check(); 
</script>