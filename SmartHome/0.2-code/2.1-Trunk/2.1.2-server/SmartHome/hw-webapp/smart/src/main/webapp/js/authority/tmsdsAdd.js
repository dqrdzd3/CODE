function checkForm() {
	var flag = true;
	var msg = '';
	
	// 登陆名称
	var name = document.getElementById("chnName");
	if(name.value ==""){
		msg += '请输入危化品名称！\n';
		flag = false;	
	}


	if (flag == false) {
		alert(msg);
		return false;
	}
	return true;
}