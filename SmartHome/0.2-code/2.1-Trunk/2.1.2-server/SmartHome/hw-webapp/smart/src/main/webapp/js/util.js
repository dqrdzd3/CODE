;(function($,w){
	w.$$ = w.$$ || {};
	
	$.extend(w.$$, {
		/* 递归指定window下的名称为name的frame,查找到第一个即返回  */
		getFrame : function(name,win) {
			win = win || w.top;
			for (var i = 0, fm, fms = win.frames, len = fms.length; i < len; i++)
				if ((fm = fms[i]).name == name || 
						(fm = this.getFrame(name, fm))) return fm;
			return null;
		}
	});
	
})(jQuery,window);