package com.hw.hwsafe.smart.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;
import oracle.net.aso.e;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.CorpAttachmentPO;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpOnlinePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IBusinessService;
import com.hw.hwsafe.smart.service.ICorpAttachmentService;
import com.hw.hwsafe.smart.service.ICorpBusinessService;
import com.hw.hwsafe.smart.service.ICorpCrmService;
import com.hw.hwsafe.smart.service.ICorpMaterialService;
import com.hw.hwsafe.smart.service.ICorpOnlineService;
import com.hw.hwsafe.smart.service.ICorpRankService;
import com.hw.hwsafe.smart.util.base64;
import com.hw.hwsafe.utils.PasswordUtil;

public class CorpBusinessAction extends BaseAction {
	Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private ICorpBusinessService corpBusinessService;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private ICorpMaterialService corpMaterialService;
	
	@Autowired
	private ICorpCrmService corpCrmService;
	
	@Autowired
	private ICorpOnlineService corpOnlineService;
	
	@Autowired
	private ICorpRankService corpRankService;
	
	@Autowired
	private ICorpAttachmentService corpAttachmentService;
	
	private static List<HashMap<String, Object>> codeList = new ArrayList<HashMap<String, Object>>(); // 记录短信验证码发送手机号和时间，防止1分钟内发送多次短信
	
	private List<CorpMaterialDetailPO> materialDetailPOList = new ArrayList<CorpMaterialDetailPO>();   //耗材用户群
	
	private List<CorpCrmPO> corpCrmPOList = new ArrayList<CorpCrmPO>();      //潜在客户群
	
	private List<U001PO> corpOnlinePOList = new ArrayList<U001PO>();    //在线用户群
	
	private List<Map<String, Object>> rankUserVoc = new ArrayList<Map<String,Object>>();  //用户voc排名    日
	
	private List<Map<String, Object>> rankUserPM = new ArrayList<Map<String,Object>>();    //用户pm2.5排名   日
	
	private List<Map<String, Object>> rankUserCo2 = new ArrayList<Map<String,Object>>();    //用户CO2排名   日
	
	private List<Map<String, Object>> rankUserVocM = new ArrayList<Map<String,Object>>();  //用户voc排名    月
	
	private List<Map<String, Object>> rankUserPMM = new ArrayList<Map<String,Object>>();    //用户pm2.5排名   月
	
	private List<Map<String, Object>> rankUserCo2M = new ArrayList<Map<String,Object>>();    //用户CO2排名  月
	
	private BusinessPO businessPO;
	
	
	public String doLogin(){
		
		return "login";
	}
	public String doIndex(){
		String password = request.getParameter("SESSIONID");
		String phone = request.getParameter("ACCOUNT");

		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ma009",phone);
			map.put("ma011",password);
			try {
				businessPO = corpBusinessService.doCorpLogin(map);
			} catch (Exception e) {
				
				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
						null, "CorpLogin");
				return "login";
			}
			if (businessPO != null ) {
				//耗材
				CorpMaterialDetailPO tempDetailPO = new CorpMaterialDetailPO();
				tempDetailPO.setMa007(businessPO.getMa001());

				try {
					materialDetailPOList = corpMaterialService.retrieveDetails(tempDetailPO);
				} catch (Exception e) {
					
					
					e.printStackTrace();
					return "login";
				}
				//在线
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("corpid", businessPO.getMa001());
				try {
					corpOnlinePOList = corpOnlineService.doQuery(map2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "login";
				}
				
				//潜在客户
				CorpCrmPO crmPO = new CorpCrmPO();
				crmPO.setMa003(businessPO.getMa001());
				crmPO.setMa011(1);
				try {
					corpCrmPOList = corpCrmService.retrieveInstanceByPO(crmPO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "login";
				}
				
				
				//用户排名
				
				Map<String, Object> conditionMap = new HashMap<String, Object>();
				
				conditionMap.put("corpid", businessPO.getMa001());
				conditionMap.put("dateType", "day");

				try {
					conditionMap.put("sensor", "D9");//气体类型 MEDIA_TYPE_CO = "04";MEDIA_TYPE_CO2 = "30";MEDIA_TYPE_CH4 = "01";MEDIA_TYPE_C6H6 = "1B";MEDIA_TYPE_CH2O = "17";MEDIA_TYPE_PM25 = "D8";MEDIA_TYPE_TEMPERATURE = "C9";MEDIA_TYPE_HUMIDITY = "CA";MEDIA_TYPE_VOC = "D9";
					rankUserVoc = corpRankService.rankByUser(conditionMap);

					conditionMap.put("sensor", "D8");
					rankUserPM = corpRankService.rankByUser(conditionMap);
					
					conditionMap.put("sensor", "30");
					rankUserCo2 = corpRankService.rankByUser(conditionMap);
					
					conditionMap.put("dateType", "month");    //月
					conditionMap.put("sensor", "D9");
					rankUserVocM = corpRankService.rankByUser(conditionMap);
					
					conditionMap.put("sensor", "D8");
					rankUserPMM = corpRankService.rankByUser(conditionMap);
					
					conditionMap.put("sensor", "30");
					rankUserCo2M = corpRankService.rankByUser(conditionMap);
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} else {
				callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
						null, "CorpLogin");
				return "login";
			}
		}
		
		return "main";
		//return "test";
	}
	
	public String doCorpLogin(){
		
		String password = request.getParameter("PASSWORD");
		String phone = request.getParameter("ACCOUNT");

		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ma009",phone);
			map.put("ma011",PasswordUtil.createPassword(password));
			try {
				businessPO = corpBusinessService.doCorpLogin(map);
				if (businessPO != null ) {
					if (businessPO.getMa012()!= null ) {
						CorpAttachmentPO po = corpAttachmentService.retrieveInstanceById(businessPO.getMa012());
						if (po!=null) {
							businessPO.setMa019(base64.toString(po.getMa002()));
						}
					}
					callbackDataPO = new CallbackDataPO("1", "登录成功", 1,
							new Gson().toJson(businessPO),
							JSONObject.fromObject(businessPO), "CorpLogin");
				} else {
					callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
							null, "CorpLogin");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
						null, "CorpLogin");
			}
			
		}
		return JSON_DATA;
	}

	public String doEdit(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			//营业执照
			if (businessPO.getMa008()!= null ) {
				CorpAttachmentPO po = corpAttachmentService.retrieveInstanceById(businessPO.getMa008());
				if (po!=null) {
					businessPO.setMa018(base64.toString(po.getMa002()));
				}
			}
			//logo
			if (businessPO.getMa012()!= null ) {
				CorpAttachmentPO po = corpAttachmentService.retrieveInstanceById(businessPO.getMa012());
				if (po!=null) {
					businessPO.setMa019(base64.toString(po.getMa002()));
				}
			}
			if (businessPO.getMa013()!= null ) {
				CorpAttachmentPO po = corpAttachmentService.retrieveInstanceById(businessPO.getMa013());
				if (po!=null) {
					businessPO.setMa020(base64.toString(po.getMa002()));
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
		return "edit";
	}
	
	public String doSaveEdit(){
		
		try {
		    corpBusinessService.doManageAcount(businessPO);
			callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
		} catch (Exception e) {
		
			callbackDataPO = new CallbackDataPO("0", "修改失败", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
		}

		return JSON_DATA;
	}
	public String doChange(){
		return "change";
	}
	
	public String changePwd(){
		String oldPass = request.getParameter("oldpass");
		String newPass = request.getParameter("newpass");
		String account = request.getParameter("account");
		
		try {
			BusinessPO tempU001po = new BusinessPO();
			tempU001po.setMa009(account);
			tempU001po.setMa010(oldPass);
			List<BusinessPO> list = businessService
					.retrieveInstanceByPO(tempU001po);
			if (list == null || list.size() == 0) {
				callbackDataPO = new CallbackDataPO("0",
						"此账号未注册", 0, null, null, "Business");
				return JSON_DATA;
			}
			businessPO = list.get(0);
			businessPO.setMa010(newPass);
			businessPO.setMa011(PasswordUtil.createPassword(newPass));
			corpBusinessService.doManageAcount(businessPO);
			businessPO.setMa010("");
			callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
		} catch (Exception e) {
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "修改失败", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
		}

		return JSON_DATA;
	}
	
	/**
	 * 发送验证码
	 */
	public String sendCode() {
		String phone = request.getParameter("PHONE");

		Random random = new Random();
		int x = random.nextInt(899999) + 100000;
		int num = getNumFromList(phone);
		if (num == -2) {

			log.info("非法请求验证码");
			// return "";
			callbackDataPO = new CallbackDataPO("0", "非法请求验证码", 0, null, null,
					"Business");
			return JSON_DATA;

		}
		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else {

			BusinessPO tempU001po = new BusinessPO();
			tempU001po.setMa009(phone);
			try {
				List<BusinessPO> list = businessService
						.retrieveInstanceByPO(tempU001po);
				if (list == null || list.size() == 0) {
					callbackDataPO = new CallbackDataPO("0",
							"此账号未注册，不能接收短信验证码", 0, null, null, "Business");
					return JSON_DATA;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "发送失败", 0, null, null,
						"Business");
				return JSON_DATA;
			}

			String yzm = x + "";
			try {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("phone", phone);
				map.put("yzm", yzm);
				corpBusinessService.sendYZM(map);
				// SMSService smsService =new SMSService();
				// smsService.sendMsg(phone, "【汉威云】"+yzm+"（智能家居验证码）");
				// SMSPush.SmsSendContent(phone, "【汉威云】"+yzm+"（智能家居验证码）" );
				String url = ConfConstants.HW_SMS_URL;
				Client client = new Client(new URL(url));
				client.invoke("sendMsg", new Object[] { phone,
						"【汉威云】" + yzm + "（空气电台验证码）" });
				System.out.println("智能家居验证码已发送！");
				if (num > -1) {
					codeList.get(num).put("sendDate", new Date());
				} else {

					HashMap<String, Object> codeMap = new HashMap<String, Object>();
					codeMap.put("phone", phone);

					codeMap.put("sendDate", new Date());
					codeList.add(codeMap);
				}

			} catch (Exception e) {
				e.printStackTrace();
			
			}

		}
		return JSON_DATA;

	}
	// 获得缓存中是否保存手机号,且时间已超过一分钟
		private int getNumFromList(String phone) {
			int num = -1;
			for (int i = 0; i < codeList.size(); i++) {
				HashMap<String, Object> map = (HashMap<String, Object>) codeList
						.get(i);
				if (map.get("phone").equals(phone)) {
					Date date = (Date) map.get("sendDate");
					Date current = new Date();
					long d = current.getTime() - date.getTime();
					if (d / (1000 * 60) >= 1) {
						return i;
					} else {
						return -2;
					}

				}
			}
			return num;
		}
	public String doForget(){
		return "forget";
	}
	
	public String savePwd(){
		
		String newPass = request.getParameter("new");
		String phone = request.getParameter("phone");
		String yzm = request.getParameter("code");
		
		try {
			BusinessPO po = new BusinessPO();
			po.setMa009(phone);
			po.setMa017(yzm);
			po.setMa010(newPass);
			po.setMa011(PasswordUtil.createPassword(newPass));
			businessPO = corpBusinessService.resetPWD(po);
			callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
			corpBusinessService.updateYZM(businessPO.getMa001());
		} catch (Exception e) {
		
			callbackDataPO = new CallbackDataPO("1", "修改失败", 1,
					new Gson().toJson(businessPO),
					JSONObject.fromObject(businessPO), "CorpLogin");
		}

		return JSON_DATA;
	}
	public ICorpBusinessService getCorpBusinessService() {
		return corpBusinessService;
	}

	public void setCorpBusinessService(ICorpBusinessService corpBusinessService) {
		this.corpBusinessService = corpBusinessService;
	}

	public BusinessPO getBusinessPO() {
		return businessPO;
	}

	public void setBusinessPO(BusinessPO businessPO) {
		this.businessPO = businessPO;
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	public List<CorpMaterialDetailPO> getMaterialDetailPOList() {
		return materialDetailPOList;
	}
	public void setMaterialDetailPOList(
			List<CorpMaterialDetailPO> materialDetailPOList) {
		this.materialDetailPOList = materialDetailPOList;
	}
	public ICorpMaterialService getCorpMaterialService() {
		return corpMaterialService;
	}
	public void setCorpMaterialService(ICorpMaterialService corpMaterialService) {
		this.corpMaterialService = corpMaterialService;
	}
	public List<CorpCrmPO> getCorpCrmPOList() {
		return corpCrmPOList;
	}
	public void setCorpCrmPOList(List<CorpCrmPO> corpCrmPOList) {
		this.corpCrmPOList = corpCrmPOList;
	}
	public ICorpCrmService getCorpCrmService() {
		return corpCrmService;
	}
	public void setCorpCrmService(ICorpCrmService corpCrmService) {
		this.corpCrmService = corpCrmService;
	}
	public ICorpOnlineService getCorpOnlineService() {
		return corpOnlineService;
	}
	public void setCorpOnlineService(ICorpOnlineService corpOnlineService) {
		this.corpOnlineService = corpOnlineService;
	}
	public List<U001PO> getCorpOnlinePOList() {
		return corpOnlinePOList;
	}
	public void setCorpOnlinePOList(List<U001PO> corpOnlinePOList) {
		this.corpOnlinePOList = corpOnlinePOList;
	}
	public ICorpRankService getCorpRankService() {
		return corpRankService;
	}
	public void setCorpRankService(ICorpRankService corpRankService) {
		this.corpRankService = corpRankService;
	}
	public List<Map<String, Object>> getRankUserVoc() {
		return rankUserVoc;
	}
	public void setRankUserVoc(List<Map<String, Object>> rankUserVoc) {
		this.rankUserVoc = rankUserVoc;
	}
	public List<Map<String, Object>> getRankUserPM() {
		return rankUserPM;
	}
	public void setRankUserPM(List<Map<String, Object>> rankUserPM) {
		this.rankUserPM = rankUserPM;
	}
	public List<Map<String, Object>> getRankUserCo2() {
		return rankUserCo2;
	}
	public void setRankUserCo2(List<Map<String, Object>> rankUserCo2) {
		this.rankUserCo2 = rankUserCo2;
	}
	public ICorpAttachmentService getCorpAttachmentService() {
		return corpAttachmentService;
	}
	public void setCorpAttachmentService(
			ICorpAttachmentService corpAttachmentService) {
		this.corpAttachmentService = corpAttachmentService;
	}
	public List<Map<String, Object>> getRankUserVocM() {
		return rankUserVocM;
	}
	public void setRankUserVocM(List<Map<String, Object>> rankUserVocM) {
		this.rankUserVocM = rankUserVocM;
	}
	public List<Map<String, Object>> getRankUserPMM() {
		return rankUserPMM;
	}
	public void setRankUserPMM(List<Map<String, Object>> rankUserPMM) {
		this.rankUserPMM = rankUserPMM;
	}
	public List<Map<String, Object>> getRankUserCo2M() {
		return rankUserCo2M;
	}
	public void setRankUserCo2M(List<Map<String, Object>> rankUserCo2M) {
		this.rankUserCo2M = rankUserCo2M;
	}

	

}
