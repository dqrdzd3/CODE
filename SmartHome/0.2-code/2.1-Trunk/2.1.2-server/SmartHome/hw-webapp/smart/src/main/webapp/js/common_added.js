;(function($, W, undefined) {
	
	$.extend(W.$$ = W.$$ || {}, {
		
		// 获取tabs选项卡对象
		getTabsBar : function() {
			return $$.getFrame('rightFrame').Tabs;
		}
		
		
		
	});

})(jQuery, window);



