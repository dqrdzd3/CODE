function checkForm() {
	var flag = false;
	var msg = '';
	
	// 功能名
	var name = document.getElementById("perName");
	if(name ==""){
		msg += '请输入功能名称！\n';
		flag = true;	
	}
	
	// 功能序号
	var orderNum = document.getElementById("perOrderNum");
	if (orderNum.value == "") {
		msg += '请填写 功能序号！\n';
		flag = true;
	}	

	if (flag == true) {
		alert(msg);
		return false;
	}
	return true;
	
}