package com.hw.hwsafe.smart.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.S004PO;
import com.hw.hwsafe.smart.service.IS004Service;
import com.hw.hwsafe.smart.service.IS005Service;
import com.hw.hwsafe.smart.util.SmartCheckUser;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 创建人：李羽皓
 * 讨论区
 *
 */
public class S004Action extends BaseAction {
	@Autowired
	private IS004Service s004Service;
	@Autowired
	private IS005Service s005Service;
	private S004PO s004po;
	private String ids;
	public String doIndex(){
		return SUCCESS;
	}
	public String doAdd(){
		return "add";
	}

	public String doSaveAdd(){
		try {
			s004po.setMa001(UUIDGenerater.getUUID());
			s004Service.insertInstance(s004po);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 
	 * 如果有人讨论过，则该主题不能删除
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年8月1日下午3:58:44
	 */
	public String doDelete(){
		try {
			String[] split = ids.split(",");
			for (String id : split) {
				if (s005Service.getCounts(id)<=0) {//没人讨论，删除
					s004Service.deleteInstanceById(id);
					setDelSuccessMsg();
				}
				else {//有人讨论，不删
					setDelFailedMsg();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	//------------------接口--------------------------
	/**
	 * 主题列表
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年7月10日上午8:36:40
	 */
	public String doView()
	{
		String sessionId = request.getParameter("SESSIONID");
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证是否通过
			List<S004PO> titleList = null;
			try {
				titleList = s004Service.getListTitle();//调用service
				//服务器端传递到客户端的内容：
				callbackDataPO = new CallbackDataPO("1", "操作成功", titleList.size(),null ,JSONArray.fromObject(titleList), "S004");
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0", "操作失败", 1,null ,null, "S004");
				e.printStackTrace();
			}
		}
		return JSON_DATA;
	}
	public IS004Service getS004Service() {
		return s004Service;
	}
	public void setS004Service(IS004Service s004Service) {
		this.s004Service = s004Service;
	}
	public S004PO getS004po() {
		return s004po;
	}
	public void setS004po(S004PO s004po) {
		this.s004po = s004po;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public IS005Service getS005Service() {
		return s005Service;
	}
	public void setS005Service(IS005Service s005Service) {
		this.s005Service = s005Service;
	}
}
