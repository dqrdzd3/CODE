function checkForm() {
//	var e=window.event || event;
//	var target = e.srcElement;
//	alert(target.tagName+"sss");
//	if(target.tagName == 'input' || target.id == 'role__doList'){
//		alert(1);
//		return true;
//	}
	var flag = false;
	var msg = '';
	
	// 角色名称
	var roleName = document.getElementById("roleName");
	if(roleName.value.Trim() == ""){
		msg += '请输入角色名称！\n';
		roleName.focus();
		flag = true;	
	}

	if (flag == true) {
		alert(msg);
		return false;
	}
	return true;
}