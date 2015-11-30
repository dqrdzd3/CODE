package com.hw.hwsafe.smart.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.service.IControlDeviceService;
import com.hw.hwsafe.smart.service.impl.ControlDeviceServiceImpl;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.hwsafe.smart.util.SmartCheckUser;
import com.hw.hwsafe.utils.UUIDGenerater;

public class ControlDeviceAction extends BaseAction {

	private ControlDevicePO controlDevicePO;

	@Autowired
	private IControlDeviceService controlDeviceService;

	private String ids;

	public String doAdd() {
		return "add";
	}

	public String doSaveAdd() {
		controlDevicePO.setMa001(UUIDGenerater.getUUID());
		controlDevicePO.setMa002(SessionUtil.getUserId());
		// controlDevicePO.setMa005(new Date());
		try {
			// d002Service.insertD002(controlDevicePO);
			// setAddSuccessMsg();
		} catch (Exception e) {
			setAddFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		try {
			// d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doSaveEdit() {
		try {
			// d002Service.updateD002(d002PO);
			// setUpdateSuccessMsg();
		} catch (Exception e) {
			setUpdateFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doDelete() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// map.put("idArray", ids.split(","));
			// d002Service.delBatchD002(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	public String doShow() {
		try {
			// d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	// 添加设备
	public String InsertDevice() {
		String userID = request.getParameter("USERID");
		String sessionID = request.getParameter("SESSIONID");
		controlDevicePO = new Gson().fromJson(request.getParameter("PO"),
				ControlDevicePO.class);

		String message = null;
		if (StringUtils.isBlank(sessionID)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(userID)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null,
					"Device_info");
		} else {
			try {
				List<ControlDevicePO> tempPo = controlDeviceService
						.retrieveInstanceByPO(controlDevicePO);
				if (tempPo != null) {
					callbackDataPO = new CallbackDataPO("0", "该设备已添加", 0, null,
							"Device_info");
				} else {
					controlDevicePO.setMa001(UUIDGenerater.getUUID());
					controlDeviceService.insertInstance(controlDevicePO);
					callbackDataPO = new CallbackDataPO("1", "操作成功", 0, null,
							"Device_info");
				}

			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0", "操作失败", 0, null,
						"Device_info");
				e.printStackTrace();
			}
		}
		return JSON_MSG;

	}

	// 删除设备
	public String deleteDevice() {
		String userID = request.getParameter("USERID");
		String sessionID = request.getParameter("SESSIONID");
		String ma001 = request.getParameter("MA001");

		String message = null;
		if (StringUtils.isBlank(sessionID)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(userID)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null,
					"Device_info");
		} else {
			Map<String, Object> vertify = new SmartCheckUser()
					.vertify(sessionID);
			callbackDataPO = (CallbackDataPO) vertify.get("callbackDataPO");
			if ((Boolean) vertify.get("isVertify")) {// 验证通过
				try {
					controlDeviceService.deleteInstanceById(ma001);
					callbackDataPO = new CallbackDataPO("1", "删除成功", 0, null,
							"Device_info");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "删除失败", 0, null,
							"Device_info");
					e.printStackTrace();
				}
			}
		}
		return JSON_MSG;
	}

	/**
	 * //控制设备
	 * 
	 * @return
	 * @throws Exception
	 */
	public String controlDevice() throws Exception {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String ctrlDetail = request.getParameter("CTRLORDER");
		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		} else if (StringUtils.isBlank(ctrlDetail)) {
			message = "没有指令";
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null,
					"ControlDevice");
		} else {
			SensorCtrlDetail sensorCtrlDetail = new Gson().fromJson(ctrlDetail,
					SensorCtrlDetail.class);
			ControlDevicePO retrieveDevicePO = new ControlDevicePO();
			retrieveDevicePO.setMa004(sensorCtrlDetail.getDeviceId());
			List<ControlDevicePO> controlDevicePOs = controlDeviceService
					.retrieveInstanceByPO(retrieveDevicePO);
			if (controlDevicePOs == null) {
				message = "没有该设备";
				callbackDataPO = new CallbackDataPO("0", message, 0, null,
						"ControlDevice");
			} else {
				// 发送控制
				if (controlDevicePOs.size() == 1) {
					((ControlDeviceServiceImpl) controlDeviceService)
							.ctrlAFN10F01(sensorCtrlDetail);
					message = "设备控制信号发送成功";
					callbackDataPO = new CallbackDataPO("0", message, 0, null,
							"ControlDevice");

				} else {
					message = "该设备信息错误";
					callbackDataPO = new CallbackDataPO("0", message, 0, null,
							"ControlDevice");
				}
			}
		}

		return JSON_DATA;
	}

	public ControlDevicePO getControlDevicePO() {
		return controlDevicePO;
	}

	public void setControlDevicePO(ControlDevicePO controlDevicePO) {
		this.controlDevicePO = controlDevicePO;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IControlDeviceService getControlDeviceService() {
		return controlDeviceService;
	}

	public void setControlDeviceService(IControlDeviceService controlDeviceService) {
		this.controlDeviceService = controlDeviceService;
	}
	
}
