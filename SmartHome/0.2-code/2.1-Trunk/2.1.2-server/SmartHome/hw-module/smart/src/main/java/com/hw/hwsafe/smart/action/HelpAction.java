package com.hw.hwsafe.smart.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.smart.service.IS001Service;

public class HelpAction extends BaseAction {
	@Autowired
	private IS001Service s001Service;
	public String doIndex() {
		List<Map<String, Object>> qaList = null;
		try {
			qaList=s001Service.getQAList();//调用service,查询数据库
			request.setAttribute("qaList", qaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public IS001Service getS001Service() {
		return s001Service;
	}
	public void setS001Service(IS001Service s001Service) {
		this.s001Service = s001Service;
	}
}
