function checkForm() {
	var flag = false;
	var msg = '';
	
	// 用户名
	var userName = document.getElementById("loginName");
	if(userName.value ==""){
		msg += '请输入 用户名！\n';
		userName.focus();
		flag = true;	
	}
	
	// 密码
	var loginPwd = document.getElementById("loginPwd");
	if (loginPwd.value == "") {
		msg += '请输入  密码！\n';
		loginPwd.focus();
		flag = true;
	}

	if (flag == true) {
		alert(msg);
		return false;
	}
	return true;
}