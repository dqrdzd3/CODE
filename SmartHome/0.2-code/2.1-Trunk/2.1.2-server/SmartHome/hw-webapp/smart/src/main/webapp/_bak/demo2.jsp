<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title><s:text name="label.role" />-<s:text name="application.title" /></title>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/gt_grid.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/china/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/vista/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plugin/gt-grid/skin/mac/skinstyle.css" />

<script type="text/javascript" src="<%=contextPath%>/plugin/gt-grid/gt_msg_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plugin/gt-grid/gt_grid_all.js"></script>

<script type="text/javascript">
//教程中的代码就写在这个区域里.
var data1 = [
   { no : 1 , name :'aaa', age : 12, gender : 'M' , english : 76.5 , math :92 },
   { no : 2 , name :'bbb', age : 11, gender : 'F' , english : 89   , math :87 },
   { no : 3 , name :'ccc', age : 13, gender : 'F' , english : 53   , math :62 }
];

var dsConfig= {
	data : data1,
	fields :[
		{name : 'no'     ,  type: 'int'     , index : 'no'      },
		{name : 'name'   ,  type: 'string'  , index : 'name'    },
		{name : 'age'    ,  type: 'int'     , index : 'age'     },
		{name : 'gender' ,  type: 'string'  , index : 'gender'  },
		{name : 'english',  type: 'float'   , index : 'english' },
		{name : 'math'   ,  type: 'float'   , index : 'math'    },
		
		{name : 'total'   , type: 'float',
			initValue : function(record){
				return record['english'] + record['math'];
			}
		}
	]
};

var colsConfig = [
	{ id : 'no'      ,header : "学号" , fieldName : 'no', align : 'center'},
    { id : 'name'    ,header : "姓名" , fieldName : 'name'},
    { id : 'age'     ,header : "年龄" , fieldName : 'age'},
    { id : 'gender'  ,header : "性别" , fieldName : 'gender', align : 'center',
    	renderer : GT.Grid.mappingRenderer(  {'U': '未知' , 'M':'男', 'F':'女'} , '未知' )
    },
    { id : 'english' ,header : "英语" , fieldName : 'english', align :'right'},
    { id : 'math'    ,header : "数学" , fieldName : 'math', align :'right'},
    
    { id : 'total'   , header : "总成绩" , width : 70 , align :'right',
		renderer : function(value ,record,columnObj,grid,colNo,rowNo){
			   var total = record['total'];
			   if (total>170) {
					total =  '<span style="color:red" >'+ total +'</span>';
			   }else if (total<120) {
					total = '<span style="color:blue" >'+ total +'</span>';
			   }
			   return total;
		}
	}, 
    
    { id : 'detail'   , header : "详细信息" , width : 120,
		renderer : function(value ,record,columnObj,grid,colNo,rowNo){
			return '<a href=".?no='+record['no']+'" >'+ record['name'] +' 的详细信息</a>';
		}
	}
];


var mygrid1 = new GT.Grid( { /*...一些配置信息...*/ }  );

var gridConfig={
		id : "grid1",
		dataset : dsConfig ,
		columns : colsConfig ,
		container : 'grid1_container', 
		toolbarPosition : 'bottom',
		toolbarContent : 'state'
};

var mygrid=new GT.Grid( gridConfig );
GT.Utils.onLoad( GT.Grid.render(mygrid) );   //mygrid为之前创建的对象,此处也可以传入grid的id.



</script>
</head>

<body>
	<!-- grid的容器. -->
	<div id="grid1_container" style="width:100%; height: 300px"></div>

</body>
</html>

