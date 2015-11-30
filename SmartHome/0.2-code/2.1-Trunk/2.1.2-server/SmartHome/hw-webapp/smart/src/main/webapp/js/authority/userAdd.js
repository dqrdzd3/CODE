function checkForm() {
	var flag = true;
	var msg = '';
	
	// 登陆名称
	var name = document.getElementById("loginName");
	if(name.value ==""){
		msg += '请输入登陆名称！\n';
		flag = false;	
	}
	
	// 登陆密码
	var loginPwd = document.getElementById("loginPwd");
	if (loginPwd.value == "") {
		msg += '请添加 登陆密码！\n';
		flag = false;
	}
	// 用户名称
	var userName = document.getElementById("userName");
	if (userName.value == "") {
		msg += '请填写 用户名称！\n';
		flag = false;
	}	
	// 职务

	// 手机号码
	var userMobile = document.getElementById("userMobile").value;
	if (!Validator.IsMobile(userMobile)) {
		msg += '请正确填写 手机号码！\n';
		flag = false;
	}
	// 电子邮箱
	var userEmail = document.getElementById("userEmail").value;
	if (!Validator.IsEmail(userEmail)) {
		msg += '请正确填写 电子邮箱！\n';
		flag = false;
	}	

	if (flag == false) {
		alert(msg);
		return false;
	}
	return true;
}