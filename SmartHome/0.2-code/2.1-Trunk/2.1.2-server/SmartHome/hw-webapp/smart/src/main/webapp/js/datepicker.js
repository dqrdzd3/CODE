;(function(){ 
	window.$$ = window.$$ || {};
	
	var dpSltr = ".hwDatePicker",
	
	/* ****** 日期控件的配置选项  *******/
		dpOption = {
			dateFormat:'yy-mm-dd',
			currentText:'返回今天',
			showButtonPanel:true,
			showWeek:true,
		//	weekHeader : '周',
			changeMonth:true,
			changeYear:true,
		//	autoSize:true,
		//	gotoCurrent : true,
			numberOfMonths : 1, //[2,6],
			showOtherMonths : true,
			selectOtherMonths : true,
			monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
			stepMonths : 1,
			shortYearCutoff : 10,
			showMonthAfterYear : true,
			yearRange : 'c-50:c+50',
			yearSuffix : '',
			duration : 'normal',
			onSelect : function(txt, inst) {
					inst.dpDiv.find("a").attr("href","javascript:;");
			}
			
			
	};
	
	window.$$.datepicker = function(context, option, extend) {
		context = context || document,
		option = extend ? $.extend(dpOption, option) : (option || dpOption);
		$(dpSltr, context).datepicker(option);
	};
	
	// 格式化表单中日期
	window.$$.datepickerFormat = function (context, fmt) {
		context = context || document,
		$(dpSltr, context).datepicker("option", {dateFormat:fmt ? fmt : dpOption.dateFormat});
	};
	
	
	// ------------------------------
	$(function() {
		$$.datepicker();
	});
	
})();


