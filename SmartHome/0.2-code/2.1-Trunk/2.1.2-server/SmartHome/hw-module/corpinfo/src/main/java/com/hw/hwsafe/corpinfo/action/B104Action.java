package com.hw.hwsafe.corpinfo.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B104PO;
import com.hw.hwsafe.corpinfo.service.IB104Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.pushlet.service.IPushletMsgService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B104Action extends BaseAction {
	
	@Autowired
	private IB104Service b104Service;
	@Autowired
	private IPushletMsgService pushletMsgService;
	private B104PO b104PO;
	private String ids;
	private String refuseMsg;
	public String doAdd(){
		return "add";
	}
	/**
	 * 审核不通过后弹出该页面
	 * @author:王贺禧
	 * @throws Exception 
	 * @create_time:2013-10-17上午10:23:52
	 */
	public String doGetRefuseAdd() throws Exception{
		return "refuseAdd";
	}
	
	public String doGetRefuseSaveAdd(){
		try {
			b104PO = b104Service.retrieveInstanceById(b104PO.getMa001());
			if(b104PO != null){
				b104PO.setMa008("2");
				pushletMsgService.insertMsg(b104PO.getMa003(), refuseMsg);
				b104Service.updateRefuseStatus(b104PO);
				message.set(Constants.MSG_OK, "消息发送成功！");
			}else{
				message.set(Constants.MSG_OK, "消息发送失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.set(Constants.MSG_OK, "消息发送失败！");
		}
		return JSON_MSG;
	}
	
	public String doSaveAdd(){
		UserPO userPO = this.getSessionUserPO();
		b104PO.setMa001(UUIDGenerater.getUUID());
		b104PO.setMa002(userPO.getCorpid());
		b104PO.setMa003(userPO.getId());
		b104PO.setMa005(DateTimeUtils.getCurrentDate());
		b104PO.setMa008("0");
		try {
			b104Service.insertInstance(b104PO);
			message.set(Constants.MSG_OK, "申请成功");
		} catch (Exception e) {
			e.printStackTrace();
			message.set(Constants.MSG_ERROR, "申请失败");
		}
		return JSON_MSG;
	}
	
	public String doGetShow(){
		try {
			b104PO = b104Service.retrieveInstanceById(b104PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	
	public String doGetDelete(){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idArray", ids.split(","));
			b104Service.delBatchB104(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 审核不通过时推送消息通知用户
	 * @author:王贺禧
	 * @create_time:2013-10-17上午10:12:21
	 */
	public String doGetpushletMsg(){
		
		return "pushletMsg";
	}
	


	public IB104Service getB104Service() {
		return b104Service;
	}

	public void setB104Service(IB104Service b104Service) {
		this.b104Service = b104Service;
	}

	public B104PO getB104PO() {
		return b104PO;
	}

	public void setB104PO(B104PO b104po) {
		b104PO = b104po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IPushletMsgService getPushletMsgService() {
		return pushletMsgService;
	}

	public void setPushletMsgService(IPushletMsgService pushletMsgService) {
		this.pushletMsgService = pushletMsgService;
	}
	public String getRefuseMsg() {
		return refuseMsg;
	}
	public void setRefuseMsg(String refuseMsg) {
		this.refuseMsg = refuseMsg;
	}

}
