function checkForm() {
	var flag = true;
	var msg = '';
	
	// 事故案例名称
	var name = document.getElementById("name");
	if(name.value ==""){
		msg += '请输入事故案例名称！\n';
		flag = false;	
	}

	// 伤亡人数
	var casesw = document.getElementById("casesw").value;
	if (!Validator.IsIntNum(casesw)) {
		msg += '伤亡人数，请输入数字！\n';
		flag = false;
	}
	
	// 重伤人数
	var casezs = document.getElementById("casezs").value;
	if (!Validator.IsIntNum(casezs)) {
		msg += '重伤人数，请输入数字！\n';
		flag = false;
	}
	
	// 轻伤人数
	var caseqs = document.getElementById("caseqs").value;
	if (!Validator.IsIntNum(caseqs)) {
		msg += '轻伤人数，请输入数字！\n';
		flag = false;
	}
	

	if (flag == false) {
		alert(msg);
		return false;
	}
	return true;
}