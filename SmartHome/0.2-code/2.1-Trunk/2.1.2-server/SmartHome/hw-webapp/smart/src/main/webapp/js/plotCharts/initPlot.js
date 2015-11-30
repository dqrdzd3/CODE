/**
 * initPlot=function(id,url,param,type,options)
 * 
 *    id:  DIV的ID     
 *   url:  数据请求资源   
 * param:  请求数据时所传的参数 
 *  type:  图表类型  pie 饼图   line  线图    pillar
 *    
 * 饼图简单几个属性支持
 * options={
 * 		width:'宽',
 * 		height:'高',
 * 		styleType:'3d'||'其它字符串'   3d  3D.swf     其它:2D.swf,
 * 		font:'字体',
 * 		fontSize:'字体大小'
 * }
 *  
 * 线图柱图可进行全属性扩展
 * 这个options 跟上边的属性完全不一样
 * options={
 *      自己看API 
 * }
 */

;(function($,w){
//	线图 柱图属性
	w.initPlot=function(id,url,param,reportType,styleType,options){
//		if(id==tempId)return;
		var base=location.pathname.match("/[^/]*/?")[0];
		var width,height,style,font,fontSize,jsonData;
		var opt=options||{};
		if(typeof reportType==undefined){
			alert("error:reportType");return;
		}
		if(reportType!='pie'&&reportType!='line'&&reportType!='pillar'){
			alert('error:报表类型 :  pie 饼图   line  线图    pillar ');
			return;
		}
		if(reportType=='pie'){
			width=opt.width||'500',
			height=opt.height||'350',
			style=(styleType=='3d')?'Pie3D.swf':'Pie2D.swf',
			font=opt.font||'宋体',
			fontSize=opt.fontSize||'18',
			title=opt.title||'';
			$.post(
				url,
				param,
				function(jstr){
					if(jstr.data==''){
						$('#'+id).html('<font size="20" color="red">没有统计数据!</font>');
						return;
					}
					jsonData='{"chart":{"palette":"'+jstr.conum+'","basefont":"'+font+'","basefontsize":"'+fontSize+'"},"data":'+jstr.data+'}';
					$('#'+id).insertFusionCharts({
						swfUrl:base+"res/plotCharts/"+ style,
						width:width,
						height:height,
						id:id+Math.random(),
						dataFormat:'json',
						dataSource:jsonData
					});
				}
			);
		}else if(reportType=='line'||reportType=='pillar'){
			style=styleType||"MSColumn3D.swf";
			var lpattr={
					swfUrl : base+"res/plotCharts/"+ style,
					width : "800",
					height : "400",
					id : id+Math.random(),
					dataFormat : "json",
					dataSource : {}
				};
			$.post(url,param,function(jstr){
				var opt={
					"categories" :eval(jstr.category),
					"dataset" : eval(jstr.dataset)
				};
				$('#'+id).insertFusionCharts($.extend(lpattr,options||{},{dataSource:$.extend(lpattr.dataSource,options.dataSource,opt)}));
			});
		}
	};
})(jQuery,window);