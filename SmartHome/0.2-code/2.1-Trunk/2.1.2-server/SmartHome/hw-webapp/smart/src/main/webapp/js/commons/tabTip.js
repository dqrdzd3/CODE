
$(function(){
	// tab中添加*标
	$("#con #tagContent div").has("th:contains(*)").each(function(){
		$("a", getMatchTabLI($(this))).prepend("<span class=\"tabStar\">*</span> ");
	});
	
	// 点击“确定”按钮时触发验证
	$("#windowsbottom input.submit").click(function(){
		showErrorTab();
	});
	
	$("input, select, textarea", "#con #tagContent div th:contains(*) + td").each(function(){
		// TODO : 区分input类型：file,text等
		// ....
		$(this).blur(function(){
			checkTabTitle(this);
		});
	});
	
	
});

// var errLabelSltr = "label.error[style!='display: none;']";

// 验证通过后去除tab的提醒样式
function checkTabTitle(ele) {
	var $tabDiv = $(ele).parents("div.tagContent");
//	alert("len:" + $(errLabelSltr, $tabDiv).length);
	if (!hasErrorInput($tabDiv)) {
		getMatchTabLI($tabDiv).removeClass("errLI");
	}
}

// 获取对应的tab li标签
function getMatchTabLI($tabDiv) {
	return $("#tags li" ,$tabDiv.parents('#con')).eq($tabDiv.attr("id").replace("tagContent", ""));
}

// 是否仍有未通过验证的表单项
function hasErrorInput($tagDiv) {
	var labs = $("label.error", $tagDiv);
	for (var i = 0, len = labs.length; i < len; i++) {
		if (labs.get(i).style.display != 'none') {
			return true;
		}
	}
	return false;
}

// 将所有未通过验证的tab标签高亮显示
function showErrorTab() {
//	$("#con div.tagContent").has(errLabelSltr).each(function(){
	$("#con div.tagContent").each(function(){
			hasErrorInput($(this)) && getMatchTabLI($(this)).addClass("errLI");
		});
}