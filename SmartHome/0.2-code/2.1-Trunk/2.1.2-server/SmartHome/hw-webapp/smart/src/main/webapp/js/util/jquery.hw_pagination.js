/**
 * 分页插件(适用于文章分页和列表分页)
 */

;(function($){
	$.extend({
		"load":function(pageOpt){
			var settings={
					'url': '',
					'pageparams': {'pageNO':1},//分页工具条所用参数，主要放pageNo
					'dataparams': {},//请求数据参数：如id,pageSize等。
					'type':'POST',
					'onSuccess': function(data){
//						alert(data);
					},
					'onError': function(data){
//						alert(data);
					},
					'onBeforeSend' : function(){
//						if(!$(this).url){
//							return false;
//						}
//						alert($(this).url);
					},
					'onComplete': function(){
//						alert('完成！');
					}
			};
			var options = $.extend(settings,pageOpt);
			$.ajax({
				   type: options.type,
				   url: options.url,
				   data: $.extend(options.pageparams,options.dataparams),
				   success:options.onSuccess,
				   error:options.onError,
				   beforeSend:options.onBeforeSend,
				   complete:options.onComplete
				});
			
		},
		'loadPage':function(pageOption){
			this.load(pageOption);
		},
		'pageNOCheck':function(pageNum){
			var val = $('#toPage','#pageDiv').val();
			var num = parseInt(val);
			var bool = isNaN(parseInt(num));
			var curPageNO = 0;
			if(!bool){
				curPageNO = num;
			}
			if(curPageNO<1){
				$('#toPage','#pageDiv').val(1);
			}else if(curPageNO > pageNum){
				$('#toPage','#pageDiv').val(pageNum);
			}
			this.loadPage($.extend(pageOption,{pageparams:{'pageNO':$('#toPage','#pageDiv').val()}}));
		}
	});
})(jQuery);
