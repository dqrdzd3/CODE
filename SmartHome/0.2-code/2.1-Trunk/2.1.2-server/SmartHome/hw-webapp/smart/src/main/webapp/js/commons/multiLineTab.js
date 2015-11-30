/*
 * 20130328 mfb: form表单多tab时，重新改回自动换行样式，便于快速切换以及未通过验证时的提示。
 * html代码参考b0100101_add.jsp
 * 
 */ 					

$(function(){
	// 构建li标签
	var ul = $("<ul>").attr("id", "tags");
	$("#con #tt div").each(function(i){
		var contentID = "tagContent" + i;

		ul.append($("<li>").append($("<a>")
				.append($(this).attr("title"))
				.attr({
					"onclick" : "selectTag('" + contentID + "', this)",
					"href" : "javascript:void(0)",
					"onfocus" : "this.blur()"
				}))
			);
		
		$(this).attr({"id" : contentID, "class" : "tagContent" + (i == 0 ? " selectTag" : "")})
		.removeAttr("title")
		.removeAttr("closable")
		.removeAttr("style");
		
	});
	
	// 显示第一个tab的内容
	$(">li:eq(0)", ul).attr({"class":"selectTag"});
	
	// 插入文档
	ul.insertBefore($("#con #tt")
			.attr({"id" : "tagContent"})
			.removeAttr("class")
			.removeAttr("tools"));

});