/**
 * 将json对象填充到form表单
 */
;(function ($) {
$.fn.json2form = function(data) {
		if (!data) return;
		
		//input text password hidden button reset submit checkbox radio select textarea
		$("input,select,textarea", this).each(function(){
			var elemType=$(this).attr("type")==undefined?this.type:$(this).attr("type");
			var elemName=$(this).attr("name")==undefined?this.name:$(this).attr("name");
			// 去除name中的po前缀
			elemName = elemName.replace(/[^.]*\./, '');
			
			var elemData=data[elemName];
			if(elemData||elemData==0){
				switch(elemType){
					case undefined:
					case "text":
					case "password":
					case "hidden":
					case "button":
					case "reset":
					case "textarea":
					case "submit":{
						if(typeof(elemData)=="string"){
							$(this).val(elemData.toUpperCase()=="NULL"?"":elemData);
						}else{
							$(this).val(elemData+"");
						}
						break;
					}
					case "checkbox":
					case "radio":{
						$(this).attr("checked",false);
						if(elemData.constructor==Array){//checkbox multiple value is Array
							for (var elem in elemData){
								if(elemData[elem]==$(this).val()){
									$(this).attr("checked",true);
								}
							}
						}else{//radio or checkbox is a string single value
							if(elemData==$(this).val()){
								$(this).attr("checked",true);
							}
						}
						break;
					}
					case "select":
					case "select-one":
					case "select-multiple":{
					//	$(this).find("option:selected").attr("selected",false);
						if(elemData.constructor==Array){
							for (var elem in elemData){
								$(this).find("option[value='"+elemData[elem]+"']").attr("selected",true);
							}
						}else{
							$(this).find("option[value='"+elemData+"']").attr("selected",true);
							// ------------------
							$(this).val(elemData);
						}
						break;
					}
				}
			}
		});
};
})(jQuery);