package com.hw.hwsafe.smart.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.bcel.generic.NEW;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.UserBasePO;
import com.hw.hwsafe.smart.pojo.MessagePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.pojo.UpdatePO;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.IUpdateService;
import com.hw.hwsafe.smart.service.IUserBaseService;
import com.hw.hwsafe.utils.UUIDGenerater;

public class UpdateAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUpdateService updateService;
	
	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IUserBaseService userService;
	
	
	public String getUpdateList(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");

		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

						UserBasePO po = userService.getBaseByUserId(u001PO.getMa001());
						if (po != null) {
							List<UpdatePO> messageList = updateService.retrieveUpdateByUserId(po.getId());
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
									null, JSONArray.fromObject(messageList), "UPDATE");
						}else {
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.QUERY_DATA_FAILURE, 0, null, "UPDATE");
						}
					
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "UPDATE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "UPDATE");
			}
			
		}
		
		return JSON_DATA;
	}
	
	public String addUpdate(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		
		String currentVersion = request.getParameter("CURRENT_VERSION");
		String lastVersion = request.getParameter("LAST_VERSION");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

						UserBasePO po = userService.getBaseByUserId(u001PO.getMa001());
						if (po != null) {
							UpdatePO updatePO = new UpdatePO();
							updatePO.setApp_base_id(po.getId());
							updatePO.setId(UUIDGenerater.get32UUID());
							updatePO.setCurrent_version(currentVersion);
							updatePO.setLast_version(lastVersion);
							updatePO.setUpdate_time(sdf.format(new Date()));
							List<UpdatePO> messageList = updateService.retrieveUpdateByUserId(po.getId());
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
									null, JSONObject.fromObject(updatePO), "UPDATE");
						}else {
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.QUERY_DATA_FAILURE, 0, null, "UPDATE");
						}
					
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "UPDATE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "UPDATE");
			}
			
		}
		
		
		
		return JSON_DATA;
	}

	public IUpdateService getUpdateService() {
		return updateService;
	}

	public void setUpdateService(IUpdateService updateService) {
		this.updateService = updateService;
	}

	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

	public IUserBaseService getUserService() {
		return userService;
	}

	public void setUserService(IUserBaseService userService) {
		this.userService = userService;
	}
	
	

}
