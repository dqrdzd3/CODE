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
	function getClientTestData(max){
		max=max||100;
		var getInt=function(min,max) {
				min = min || 0;
				max = max || 100;
				var _min= Math.min(min,max);
				max=Math.max(min,max);
				min=_min;
				var step= max - min +1;
				var s= (Math.random()+1)*max*10 % step;
				var num= min +  s ;
				return parseInt(num,10);
		}

		var data=[];
		var gl=['M','F','U'];
		for (var i=0;i<max;i++){
			var record={ no : 30+i , 
					name : 'abc'+i , 
					age :getInt(12,15) , 
					gender : gl[getInt(1,9)%3] , 
					english : 50+getInt(1,49), 
					math : 40+getInt(1,59)
				}
			data.push(record);
		}
		return data;	
	}

// 然后执行下面的语句, 客户端的数据就会有200条. (作为测试,我没有生成太多).
var data1 = getClientTestData( 200 );

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
		pageSize : 10,
		pageSizeList : [5,10,15,20],
		toolbarContent : 'nav | goto  | pagesize | state',
		container : 'grid1_container', 
		toolbarPosition : 'bottom'
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

