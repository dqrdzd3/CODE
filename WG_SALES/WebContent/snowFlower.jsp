<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>js 圣诞节下雪花背景效果代码</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--把下面代码加到<head>与</head>之间-->
<style type="text/css">
body{background-color:#228B22;}
.drop{position:absolute;width:3;filter:flipV(),flipH();font-size:40;color:blue;}
</style>
<script language="javascript">
snow = true; 
snowsym = " * "
rainsym = " ' "
howmany = 25
if(snow){sym = snowsym; speed=1; angle=10; drops=howmany}
else{sym = rainsym; speed=50; drops=howmany; angle=6}
movex = -speed/angle; movey = speed; count = 0;
function moverain(){
for(move = 0; move < drops; move++){
xx[move]+=movex;  yy[move]+=mv[move];
hmm = Math.round(Math.random()*1);
if(xx[move] < 0){xx[move] = maxx+10;}
if(yy[move] > maxy){yy[move] = 10;}
drop[move].left = xx[move]
drop[move].top = yy[move]+document.body.scrollTop;
}setTimeout('moverain()','1')}
</script>
<script language="javascript">
if (document.all){
drop = new Array(); xx = new Array(); yy = new Array(); mv = new Array()
ly = "document.all['"; st = "'].style"
for(make = 0; make < drops; make++){
document.write('<div id="drop'+make+'" class=drop>'+sym+'</div>');
drop[make] = eval(ly+'drop'+make+st);
maxx = document.body.clientWidth-40
maxy = document.body.clientHeight-40
xx[make] = Math.random()*maxx;
yy[make] = -100-Math.random()*maxy;
drop[make].left = xx[make]
drop[make].top = yy[make]
mv[make] = (Math.random()*5)+speed/4;
drop[make].fontSize = (Math.random()*10)+20;
//*Change (col = 'white) to (col =YOUR COLOR)*//
if(snow){col = 'white'}else{col = 'blue'}
drop[make].color = col;
}
window.onload=moverain
}
</script>
</head>
<body>
看到了吗？
</body>
</html>
