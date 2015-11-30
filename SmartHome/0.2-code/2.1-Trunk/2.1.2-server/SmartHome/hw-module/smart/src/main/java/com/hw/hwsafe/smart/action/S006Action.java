package com.hw.hwsafe.smart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.S006PO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IS006Service;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.util.IosPush;
import com.hw.hwsafe.smart.util.Push;
import com.hw.hwsafe.smart.util.SmartCheckUser;
import com.hw.hwsafe.utils.UUIDGenerater;
/**
 * 创建人：李羽皓
 * 信息反馈
 *
 */
public class S006Action extends BaseAction {
	@Autowired
	private IS006Service s006Service;
	@Autowired
	private IU001Service u001Service;
	private S006PO s006po;
	private String ids;
	public String doIndex(){
		return SUCCESS;
	}
	public String add()
	{
		return null;
	}
    /**
     * 
     * 选中回复人，弹出回复框
     * @return          
     * @author Liyuhao
     * @create_time 2014年7月19日下午2:52:48
     */
	public String doShow(){
		try {
			s006po = s006Service.retrieveInstanceById(s006po.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	/**
	 * 
	 * 点击用户名链接，弹出显示详细的div
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年8月5日上午11:38:10
	 */
	public String doShowDetail(){
		try {
			s006po = s006Service.retrieveInstanceById(s006po.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showDetail";
	}
	/**
	 * 
	 * 回复
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年7月19日下午3:41:59
	 */
    public String doReply() {
    	
    	//推送消息
    	try {
			Push.doWebservice(s006po.getMa008(), "回复", s006po.getMa005(),"00");
			U001PO u001po = u001Service.checkUserBySessionId(s006po.getMa004());
			if (u001po!=null && !StringUtils.isBlank(u001po.getMa018())) {
				IosPush.getPushSuccessed(u001po.getMa018(), s006po.getMa005(), 0, null);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	//更新数据库
    	try {
    		s006po.setMa006("1");
			s006Service.updateInstance(s006po);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
    	return JSON_MSG;
	}
	/**
	 * 
	 * 通过id批量删除反馈信息
	 * @return          
	 * @author Liyuhao
	 * @create_time 2014年7月18日下午3:45:51
	 */
	public String doDelete(){
 		try {
 			Map<String,Object> map = new HashMap<String,Object>();
 			map.put("idArray", ids.split(","));
 			s006Service.delBatchS006(map);
 			setDelSuccessMsg();
 		} catch (Exception e) {
 			e.printStackTrace();
 			setDelFailedMsg();
 		}
 		return JSON_MSG;
 	}
	//--------------------接口-----------------------------
	/**
	 * 查看反馈历史
	 * @param userId
	 * @return
	 */
	public String doListMessage()
	{
		//获取客户端传递到服务器的内容：
		String userId=request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
			if (userId!=null) {//输入是否为空
				List<S006PO> messageList = null;
				try {
					messageList = s006Service.getListMessage(userId);//调用service
					//服务器端传递到客户端的内容：
					callbackDataPO = new CallbackDataPO("1", "操作成功", messageList.size(),null ,JSONArray.fromObject(messageList), "S006");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "操作失败", 1,null ,null, "S006");
					e.printStackTrace();
				}
			}
			else {
				callbackDataPO = new CallbackDataPO("0", "操作失败", 1,null ,null, "S006");
			}
		}
		return JSON_DATA;
	}
	/**
	 * 发送反馈
	 * @param userId
	 * @param message
	 * @return
	 */
	public String doAddMessage()
	{
		String sessionId = request.getParameter("SESSIONID");
		//获取客户端传递到服务器的内容：
		String uId=request.getParameter("USERID");
		String uMessage=request.getParameter("MSG");
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
			s006po=new S006PO();
			if (uId!=null&&uMessage!=null) {//输入是否为空
				s006po.setMa001(UUIDGenerater.getUUID());
				s006po.setMa002(uMessage);
				s006po.setMa006("0");
				s006po.setMa007(null);
				//s006po.setMa003(DateTimeUtils.getCurFormatDate());
				s006po.setMa004(uId);
				//调用service，数据库操作：
				try {
					s006Service.addMessage(s006po);
					//服务器端传递到客户端的内容：
					callbackDataPO = new CallbackDataPO("1", "留言成功", 1,null ,null, "S006");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "留言失败", 1,null ,null, "S006");
					e.printStackTrace();
				}
			}
			else {
				callbackDataPO = new CallbackDataPO("0", "留言失败", 1,null ,null, "S006");
			}
		};
		return JSON_DATA;
	}
	
	public String doEditMessage(String userId, String 留言id,String message)
	{
		return null;
		
	}
	public String doDeleteMessage(String userId,String 留言id)
	{
		return null;
		
	}
	public S006PO getS006po() {
		return s006po;
	}
	public void setS006po(S006PO s006po) {
		this.s006po = s006po;
	}
	public IS006Service getS006Service() {
		return s006Service;
	}
	public void setS006Service(IS006Service s006Service) {
		this.s006Service = s006Service;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
