package com.hw.hwsafe.serverpush.action;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.pushlet.service.IPushletMsgService;

public class PushletAction extends BaseAction {

	@Autowired
	private IPushletMsgService pushletMsgService;

	public String addAdmin() throws Exception{
		Random r = new Random();
		pushletMsgService.insertMsg("fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85", "admin的测试消息" + r.nextInt(100), "url:" + r.nextInt(99999));
		return "add";
	}
	
	public String addZfadmin() throws Exception{
		Random r = new Random();
		pushletMsgService.insertMsg("f3ae609f-2938-4133-9c00-e9d1fe64fe3a", "zfadmin的测试消息" + r.nextInt(100), "url:" + r.nextInt(99999));
		return "add";
	}
	
	// -------------------------------------------------------

	public void setPushletMsgService(IPushletMsgService pushletMsgService) {
		this.pushletMsgService = pushletMsgService;
	}

}
