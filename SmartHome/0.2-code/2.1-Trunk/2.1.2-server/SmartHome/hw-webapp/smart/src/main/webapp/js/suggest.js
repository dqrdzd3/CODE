function suggestDiv(input_id){
	var selId = null;
	var selName = null;
	
	this.showDiv = function(data,divid){
		//清空之前div的内容
		closeDiv(divid);
//		var obj=data;
//		alert(data.documentElement.textContent);
		//格式化json字符串
		var obj = eval('('+data+')');
		
		if(obj.length > 0){
			//创建ul
			var ulobj = document.createElement('ul');
			
			
			//循环创建数据
			for(i=0;i<obj.length;i++){
				var liobj = document.createElement('li');
				liobj.id = obj[i].id;
				liobj.name = obj[i].name;
				
				liobj.onmousedown = function(){
					document.getElementById(input_id).value = this.name;
					//guanbi div
					closeDiv(divid);
				};
				liobj.innerHTML = '<a href="javascript:void(0)">'+obj[i].name+'</a>';
				ulobj.appendChild(liobj);
				
			}
			
			document.getElementById(divid).appendChild(ulobj);
			//更改div的left top
			var ddobj = $('#'+divid);
			ddobj.css('left',getInputLeft(input_id));
			ddobj.css('top',getInputTop(input_id));
			ddobj.css('dispaly','block');
			ddobj.width($('#'+input_id).width() + 2);
			document.getElementById(divid).style.display = 'block';
			
			if(obj.length > 4){
				ddobj.height(90);
				ddobj.css('overflow','auto');
			}
//			alert(document.getElementById(divid).style.height);
			
		}else{
			closeDiv(divid);
		}
		
	};
}

//异步发送ajax请求
function getData(url,param,input_id,divid){
	var divObj = new suggestDiv(input_id);
	$.post(url,param,function(result){
		divObj.showDiv(result,divid);
		//divObj.showDiv('[{id:1,name:"hw"},{id:2,name:"hwdz"}]');
	});
}

document.onmousedown = function(){
	//判断鼠标点击的位置，如果是在div里面，不做处理
	var obj = document.getElementById('suggest_input');
	
	var top = obj.style.top;
	var left = obj.style.left;
	var width = obj.style.width;
	var height = obj.style.width;
	var x_index = pointerX();
	var y_index = pointerY();
	top = parseInt(top.replace('px'));
	left = parseInt(left.replace('px'));
	width = parseInt(width.replace('px'));
	height = parseInt(height.replace('px'));
	
	if(!(x_index > left && x_index < left+width && y_index > top && y_index < top + height)){
		closeDiv('suggest_input');
	}
	
};

//获取input框的位置，left
function getInputLeft(input_id){
	var element = $('#'+input_id);
	var offset = element.offset();
    return offset.left;
}

//获取input框的位置，top
function getInputTop(input_id){
	var element = $('#'+input_id);
	var offset = element.offset();
    return offset.top+22;
}

//关闭div
function closeDiv(div_id){
	var obj = $('#'+div_id);
	if(obj != null){
		obj.html('');
		obj.css('left','0');
		obj.css('top','0');
		obj.height(0);
		obj.css('dispaly','none');
		obj.css('overflow','');
	}
}

//获取鼠标点击的x位置
function pointerX(){   
     return event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft)); 
}
//获取鼠标点击的y位置    
function pointerY(){
	return event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop)); 
}

