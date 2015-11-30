function checkForm() {
	var flag = false;
	var msg = '';
	
	// 部门名称
	var name = document.getElementById("deptName");
	if(name ==""){
		msg += '请输入 部门名称！\n';
		flag = true;	
	}
	
	// 部门描述
	var description = document.getElementById("deptDescription");
	if (description.value == "") {
		msg += '请添加 部门描述！\n';
		flag = true;
	}

	if (flag == true) {
		alert(msg);
		return false;
	}
	return true;
}