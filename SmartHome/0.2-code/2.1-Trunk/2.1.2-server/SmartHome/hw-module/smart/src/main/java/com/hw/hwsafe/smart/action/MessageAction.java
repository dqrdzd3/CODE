package com.hw.hwsafe.smart.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import oracle.sql.CLOB;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.MessagePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IMessageService;
import com.hw.hwsafe.smart.service.IU001Service;

public class MessageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IMessageService messageService;
	
	@Autowired
	private IU001Service u001Service;

	public String getMessageList(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		String tempPage = request.getParameter("page");
		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					List<Map<String, Object>> messageList = null;
					if (tempPage!=null && tempPage.length()>0) {
						   Map<String, String> map = new HashMap<String, String>();
						   map.put("userid", u001PO.getMa001());
						   messageList =  messageService.retrieveByPage(map);
					}else {
						  messageList =  messageService.retrieveMessageByUserid(u001PO.getMa001());
					}
						
					 
						List<MessagePO> resultList = new LinkedList<MessagePO>();
						if (messageList != null || messageList.size() > 0) {
							for (int i = 0; i < messageList.size(); i++) {
							Map<String, Object> temp = messageList.get(i);
							MessagePO  messagePO = new MessagePO();
							
							//String clobString = com.hw.hwsafe.smart.util.ClobToString.clobToString(temp.get("CONTENT"));
							//Object clobObj = temp.get("CONTENT");
							CLOB clob = (CLOB) temp.get("CONTENT");
							String content = "";
							Object tempObj = new Object();
							
							Reader is = clob.getCharacterStream();
							BufferedReader br = new BufferedReader(is);
							String s;
							try {
								s = br.readLine();
								while (s != null) {
									// byte[] temp = s.getBytes("iso-8859-1");
									// s = new String(temp);
									content += s;
									s = br.readLine();
								}
								
								br.close();
								is.close();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							
							messagePO.setContent(content);
							
							tempObj = temp.get("CREATE_TIME");
							
							messagePO.setCreateTime(tempObj.toString());
							
							tempObj = temp.get("ID");
							
							messagePO.setId(tempObj.toString());
							
							
							tempObj = temp.get("IS_PUBLIC");
							
							messagePO.setIsPublic(Integer.parseInt(tempObj.toString()));
							tempObj = temp.get("READ_STATE");
							messagePO.setReadStates(Integer.parseInt(tempObj.toString()));
							tempObj = temp.get("TITLE");
							messagePO.setTitle(tempObj.toString());
							messagePO.setUserId(userId);
							
							resultList.add(messagePO);
						 //messagePO.setContent(messageList.get("CONTENT"));
							
							
							}
						 
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
								null, JSONArray.fromObject(resultList), "MESSAGE");
						}else{
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
									null, null, "MESSAGE");
						}
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "MESSAGE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "MESSAGE");
			}
			
		}
		
		return JSON_DATA;
	}
	
	public String getMessageDetail(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		String Id = request.getParameter("MESSAGEID");

		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

						
						
					List<Map<String, Object>> messageList = (List<Map<String, Object>>) messageService.retrieveMessageDetail(Id);
					List<MessagePO> resultList = new LinkedList<MessagePO>();
					if (messageList != null || messageList.size() > 0) {
						for (int i = 0; i < messageList.size(); i++) {
						Map<String, Object> temp = messageList.get(i);
						MessagePO  messagePO = new MessagePO();
						
						//String clobString = com.hw.hwsafe.smart.util.ClobToString.clobToString(temp.get("CONTENT"));
						//Object clobObj = temp.get("CONTENT");
						CLOB clob = (CLOB) temp.get("CONTENT");
						String content = "";
						Object tempObj = new Object();
						
						Reader is = clob.getCharacterStream();
						BufferedReader br = new BufferedReader(is);
						String s;
						try {
							s = br.readLine();
							while (s != null) {
								// byte[] temp = s.getBytes("iso-8859-1");
								// s = new String(temp);
								content += s;
								s = br.readLine();
							}
							
							br.close();
							is.close();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						messagePO.setContent(content);
						
						tempObj = temp.get("CREATE_TIME");
						
						messagePO.setCreateTime(tempObj.toString());
						
						tempObj = temp.get("ID");
						
						messagePO.setId(tempObj.toString());
						
						
						tempObj = temp.get("IS_PUBLIC");
						
						messagePO.setIsPublic(Integer.parseInt(tempObj.toString()));
						tempObj = temp.get("READ_STATE");
						messagePO.setReadStates(Integer.parseInt(tempObj.toString()));
						tempObj = temp.get("TITLE");
						messagePO.setTitle(tempObj.toString());
						messagePO.setUserId(userId);
						
						resultList.add(messagePO);
					 //messagePO.setContent(messageList.get("CONTENT"));
						
						
						}
					 
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
							null, JSONArray.fromObject(resultList), "MESSAGE");
					}else{
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
								null, null, "MESSAGE");
					}
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "MESSAGE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "MESSAGE");
			}
			
		}
		
		return JSON_DATA;
	}
	

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}



	public IU001Service getU001Service() {
		return u001Service;
	}



	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

}
