package com.hw.hwsafe.smart.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IA001Dao;
import com.hw.hwsafe.smart.dao.ID002Dao;
import com.hw.hwsafe.smart.dao.IU001Dao;
import com.hw.hwsafe.smart.pojo.A001PO;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IA001Service;
import com.hw.hwsafe.smart.util.AndroidPush;
import com.hw.hwsafe.smart.util.IosPush;
import com.hw.hwsafe.sms.socket.ServerSender;
import com.hw.hwsafe.sms.socket.po.SMSMessage;
import com.ibm.icu.util.Calendar;

public class A001ServiceImpl extends BaseServiceImpl implements IA001Service {
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private IA001Dao a001Dao;

	@Autowired
	private ID002Dao d002Dao;

	@Autowired
	private IU001Dao u001Dao;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return a001Dao.retrieveByPage(map);
	}

	@Override
	public A001PO retrieveInstanceById(String ma001) throws Exception {
		return a001Dao.retrieveInstanceById(ma001);
	}

	@Override
	public List<A001PO> retrieveInstanceByPO(A001PO a001po) throws Exception {
		return a001Dao.retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(A001PO a001po) throws Exception {
		a001Dao.insertInstance(a001po);
	}

	@Override
	public void updateInstance(A001PO a001po) throws Exception {
		a001Dao.updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		a001Dao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		return a001Dao.delBatchInstance(map);
	}

	@Override
	public List<HashMap<String, Object>> getHisAlarmList(Map<String, Object> map)
			throws Exception {
		return a001Dao.getHisAlarmList(map);
	}

	@Override
	public List<Map<String, Object>> getNoAlarmList(Map<String, Object> map)
			throws Exception {
		return a001Dao.getNoAlarmList(map);
	}

	@Override
	public void updatePushState(Map<String, Object> map) throws Exception {
		a001Dao.updatePushState(map);
	}

	public void sendAlarm() throws Exception {
		//节日祝福
	//	GoodWishToEveryBody();
		
		log.info("*******定时报警服务启动******");
		A001PO a001PO = new A001PO();
		a001PO.setMa007("00");
		List<A001PO> a001POList = retrieveInstanceByPO(a001PO);
		if (a001POList != null && a001POList.size() > 0) {
			for (A001PO a001 : a001POList) {
				List<D002PO> d002poList = d002Dao.retrieveInstanceByDevId(a001
						.getMa006()); // 1：根据设备ID，查询表D002，得到用户ID，
				if (d002poList != null && d002poList.size() > 0) {
					for (int i = 0; i < d002poList.size(); i++) {
						D002PO d002po = d002poList.get(i);
						String userID = d002po.getMa002();
						U001PO u001po = u001Dao.retrieveInstanceById(userID); // 2：根据用户ID，查询得到用户的手机号等信息。
						if (null == u001po) {
							log.warn("用户id为[" + userID + "]的用户不存在,跳过报警短信发送");
							continue;
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("ma007", "10");
						map.put("ma001", a001.getMa001());
						a001Dao.updatePushState(map);
						log.info("*********报警数据更新状态成功！***********");

						String phone = u001po.getMa006();
						StringBuilder contentSb = new StringBuilder(
								"【空气电台报警】：编号为").append(a001.getMa006()).append(
								"的");
						contentSb.append(driverType(a001.getMa004()));
						contentSb.append("设备于").append(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(a001.getMa005()));
						contentSb.append("的状态为")
								.append(curType(a001.getMa003()))
								.append("，当前值为").append(a001.getMa002())
								.append("。");
						contentSb.append(alertType(a001.getMa003()));
						if (ConfConstants.ANDROID_PUSH_SWITCH) {
							// String content
							// ="【空气电台报警】：编号为"+a001.getMa006()+"的"+driverType(a001.getMa004())+"设备于"+
							// new
							// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(a001.getMa005())+"的状态为"+curType(a001.getMa003())+
							// "，当前值为"+a001.getMa002()+"。"+alertType(a001.getMa003());
							AndroidPush.pushContent(phone, "空气电台报警",
									contentSb.toString(), "");

						}
						// 【空气电台报警】设备名设备[设备编号]于yyyy-mm-dd 24hh:mm燃气报警，请注意检修！
						// 【空气电台报警】设备名设备[设备编号]于yyyy-mm-dd 24hh:mm一氧化碳报警，请注意通风！
						if (a001.getMa004().equals("01")) {
							pushContent(
									phone,
									"【空气电台报警】："
											+ d002po.getMa008()
											+ "设备["
											+ d002po.getMa004()
											+ "]于"
											+ new SimpleDateFormat(
													"yyyy-MM-dd HH:mm")
													.format(a001.getMa005())
											+ driverType(a001.getMa004())
											+ "报警，请注意检修！");
						}
						if (a001.getMa004().equals("04")) {
							pushContent(
									phone,
									"【空气电台报警】："
											+ d002po.getMa008()
											+ "设备["
											+ d002po.getMa004()
											+ "]于"
											+ new SimpleDateFormat(
													"yyyy-MM-dd HH:mm")
													.format(a001.getMa005())
											+ driverType(a001.getMa004())
											+ "报警，请注意通风！");
						}
						if (!StringUtils.isBlank(u001po.getMa018())) {
							IosPush.getPushSuccessed(u001po.getMa018(),
									contentSb.toString(), 0, null);
						}

						log.info("向用户[" + phone + "]终端推送消息");

					}
				} else {
					log.info("设备表中未查到该条记录！");
				}
			}
			log.info("该时间段定时报警服务完成…………");
		} else {
			log.info("该时间段没有新的报警信息…………");
		}
	}

	private void pushContent(String phone, String content) {
		try {
			log.info("***手机报警短信推送开始***");
			
			SMSMessage msg = new SMSMessage();
			msg.setContent(content);
			msg.setPhoneNo(phone);
			ServerSender.getInstance().sendMessage(msg);
			
//			String url = ConfConstants.HW_SMS_URL;
//			Client client = new Client(new URL(url));
//			client.invoke("sendMsg", new Object[] { phone, content });
			log.info("***手机报警短信推送结束***");
			log.info("***消息推送服务开始***");
//			Push.doWebservice(phone, "空气电台报警通知", content, "10");
			log.info("***消息推送服务结束***");
		} catch (Exception e) {
			log.error("服务器调用报警方法异常",e);
		}
		log.info("智能家居设备报警信息"+content+"已发送给"+phone);
	}

	private String driverType(String driverType) {
		if ("04".equals(driverType)) {
			return "一氧化碳";
		} else if ("01".equals(driverType)) {
			return "燃气";
		} else if ("30".equals(driverType)) {
			return "二氧化碳";
		} else if ("D8".equals(driverType)) {
			return "PM2.5";
		} else if ("CA".equals(driverType)) {
			return "湿度";
		} else if ("C9".equals(driverType)) {
			return "温度";
		} else if ("17".equals(driverType)) {
			return "甲醛";
		} else if ("1B".equals(driverType)) {
			return "苯";
		} else {
			return "其他";
		}
	}

	private String alertType(String alertType) {
		if ("00".equals(alertType)) {
			return ConfConstants.ALERTTYPE00;
		} else if ("01".equals(alertType)) {
			return ConfConstants.ALERTTYPE01;
		} else if ("02".equals(alertType)) {
			return ConfConstants.ALERTTYPE02;
		} else if ("03".equals(alertType)) {
			return ConfConstants.ALERTTYPE03;
		} else if ("04".equals(alertType)) {
			return ConfConstants.ALERTTYPE04;
		} else if ("05".equals(alertType)) {
			return ConfConstants.ALERTTYPE05;
		} else if ("06".equals(alertType)) {
			return ConfConstants.ALERTTYPE06;
		} else {
			return null;
		}
	}

	private String curType(String curType) {
		if ("00".equals(curType)) {
			return "预热";
		} else if ("01".equals(curType)) {
			return "正常";
		} else if ("02".equals(curType)) {
			return "低报";
		} else if ("03".equals(curType)) {
			return "高报";
		} else if ("04".equals(curType)) {
			return "传感器故障";
		} else if ("05".equals(curType)) {
			return "通讯故障";
		} else if ("06".equals(curType)) {
			return "求救信号";
		} else {
			return "其他";
		}
	}

	private void GoodWishToEveryBody() {
		String wishPatten = "尊敬的%s，三羊开泰迎新春，空气电台送平安。愿您和您的家人拥有清新健康新生活，快乐幸福每一天！";
		Calendar calendar = Calendar.getInstance();
		int MONTH = calendar.get(Calendar.MONTH);    //月份从 0 开始
		int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);
		int HOUR = calendar.get(Calendar.HOUR_OF_DAY);
		int MINUTE = calendar.get(Calendar.MINUTE);
		int SECOND = calendar.get(Calendar.SECOND);
		if (MONTH == 1 && DAY_OF_MONTH == 14 && HOUR == 8 && MINUTE == 50
				&& SECOND < 40) {
			try {
//				List<U001PO> userList = u001Dao
//						.retrieveInstanceByPO(new U001PO());
//				for (U001PO u001po : userList) {
//					pushContent(u001po.getMa006(),
//							String.format(wishPatten, u001po.getMa008()));
//					AndroidPush.pushContent(u001po.getMa006(), "空气电台祝福到",
//							String.format(wishPatten, u001po.getMa008()), "");
//				}
					pushContent("13592605164",
							String.format(wishPatten, "闫威"));
					AndroidPush.pushContent("13592605163", "空气电台祝福到",
							String.format(wishPatten, "闫威"), "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
