package com.hw.hwsafe.smart.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.attachment.action.AppFileUpLoadAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.util.AndroidPush;
import com.hw.hwsafe.smart.util.SmsVerifyKit;
import com.hw.hwsafe.smart.util.base64;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.service.IPermissionFacadeService;

public class U001Action extends AppFileUpLoadAction {
	Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private IU001Service u001Service;

	@Autowired
	private IPermissionFacadeService permissionFacadeService;

	private U001PO u001PO;

	private static List<HashMap<String, Object>> codeList = new ArrayList<HashMap<String, Object>>(); // 记录短信验证码发送手机号和时间，防止1分钟内发送多次短信

	public String doIndex() {
		return SUCCESS;
	}

	public String doAdd() {

		return "add";
	}

	public String doSaveAdd() {
		return JSON_MSG;
	}

	public String doEdit() {
		return "edit";
	}

	public String doSaveEdit() {
		return JSON_MSG;
	}

	public String doDelete() {
		return JSON_MSG;
	}

	/********* 接口部分 ************/

	public String doCreateAccount() throws Exception {
		String userName = request.getParameter("USERNAME");// 昵称
		String password = request.getParameter("PASSWORD");
		String phone = request.getParameter("PHONE");//
		String email = request.getParameter("EMAIL");
		String lat = request.getParameter("LAT");
		String lng = request.getParameter("LNG");
		String loc = request.getParameter("LOC");
		String provice = request.getParameter("PROVICE");
		String city = request.getParameter("CITY");
		String area = request.getParameter("AREA");
		String path = "", filename = "";
		String iostoken = request.getParameter("IOSTOKEN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		/*
		 * if( file != null ){ filename = sdf.format(new
		 * Date())+fileFileName.substring
		 * (fileFileName.lastIndexOf(".")).toLowerCase(); path =
		 * AppFileUpLoad.uploadOne(file,filename); //
		 * System.out.println("File path is  "+ path); //
		 * System.out.println("FileName is " + filename); } else{
		 * System.out.println("上传的文件信息有误！！"); }
		 */
		byte[] fileByte = null;
		if (file != null) {
			fileByte = fileToStream(file);
		}

		if (StringUtils.isBlank(userName)) {
			throw new NullArgumentException("USERNAME");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			u001PO = new U001PO();
			u001PO.setMa006(phone);
			boolean flag = u001Service.checkAccount(u001PO);
			if (flag) {
				callbackDataPO = new CallbackDataPO("0", "账号重复", 0, null, null,
						"U001");
			} else {
				String finalMd5 = u001Service.GetMD5Code(phone, password);
				String finalPwd = PasswordUtil.createPassword(password);
				u001PO.setMa001(UUIDGenerater.getUUID());
				u001PO.setMa002(provice);
				u001PO.setMa003(city);
				u001PO.setMa004(area);
				u001PO.setMa005(email);
				u001PO.setMa006(phone);
				u001PO.setMa007(new Date());
				u001PO.setMa008(userName);
				u001PO.setMa009(finalPwd);
				u001PO.setMa010(finalMd5);
				u001PO.setMa012(path);
				u001PO.setMa013(filename);
				u001PO.setMa014(lng);
				u001PO.setMa015(lat);
				u001PO.setMa016(loc);
				u001PO.setMa017(fileByte);
				u001PO.setMa018(iostoken);
				u001Service.insertInstance(u001PO);
				AndroidPush.register(phone, "123456"); // 推送服务器增加用户 默认密码123456

				u001PO.setMa011(new SimpleDateFormat("yyyy-MM-dd HH:hh:ss")
						.format(u001PO.getMa007()));
				u001PO.setMa007(null);
				callbackDataPO = new CallbackDataPO("1", "用户注册成功", 1,
						new Gson().toJson(u001PO),
						JSONArray.fromObject(u001PO), "U001");
			}
		}
		return JSON_DATA;
	}

	/**
	 * 登录
	 */
	public String doLogin() throws Exception {
		String password = request.getParameter("PASSWORD");
		String phone = request.getParameter("PHONE");
		String iostoken = request.getParameter("IOSTOKEN");
		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			u001PO = new U001PO();
			u001PO.setMa006(phone);
			u001PO.setMa009(PasswordUtil.createPassword(password));
			List<U001PO> list = u001Service.retrieveInstanceByPO(u001PO);
			if (list != null && list.size() > 0) {
				u001PO = list.get(0);
				if (iostoken != null) {
					// 更新
					u001PO.setMa018(iostoken);
					u001PO.setMa019("ios");
					u001Service.updateInstance(u001PO);
				} else {
					u001PO.setMa019("android");
				}
				callbackDataPO = new CallbackDataPO("1", "登录成功", 1,
						new Gson().toJson(u001PO),
						JSONObject.fromObject(u001PO), "U001");
			} else {
				callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
						null, "U001");
			}
		}
		return JSON_DATA;
	}
	/**
	 * 获取图片头像
	 */
	public String getPic(){
		String userid = request.getParameter("USERID");
		String sessionid = request.getParameter("SESSIONID");
		if (StringUtils.isBlank(sessionid)) {
			throw new NullArgumentException("SESSIONID");
		} else if (StringUtils.isBlank(userid)) {
			throw new NullArgumentException("USERID");
		} else {
			u001PO = new U001PO();
			u001PO.setMa001(userid);
			u001PO.setMa010(sessionid);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "U001");
			}
			if (list != null && list.size() > 0) {
				U001PO u001 = list.get(0);
				
				try {
					
					callbackDataPO = new CallbackDataPO("1", "成功", 1,
							base64.toString(u001.getMa017()),
							null, "U001");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "失败", 0, null,
							null, "U001");
				}
			} else {
				callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
						null, "U001");
			}

		}
		return JSON_DATA;
	}
	/**
	 * 修改密码
	 */
	public String doEditPw() {
		String oldpass = request.getParameter("oldpass");
		String password = request.getParameter("MA009");
		String userid = request.getParameter("USERID");
		String sessionid = request.getParameter("SESSIONID");

		if (StringUtils.isBlank(sessionid)) {
			throw new NullArgumentException("SESSIONID");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			u001PO = new U001PO();
			u001PO.setMa001(userid);
			u001PO.setMa010(sessionid);
			u001PO.setMa009(PasswordUtil.createPassword(oldpass));
			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "U001");
			}
			if (list != null && list.size() > 0) {
				U001PO u001 = list.get(0);
				u001.setMa001(userid);
				u001.setMa009(PasswordUtil.createPassword(password));
				String finalMd5 = "";
				try {
					finalMd5 = u001Service
							.GetMD5Code(u001.getMa006(), password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					callbackDataPO = new CallbackDataPO("0", "更新失败", 0, null,
							null, "U001");
				}
				u001.setMa010(finalMd5);
				try {
					u001Service.updateInstance(u001);
					u001PO = u001Service.retrieveInstanceByPO(u001).get(0);
					AndroidPush.register(u001.getMa006(), password);
					callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
							new Gson().toJson(u001PO),
							JSONObject.fromObject(u001PO), "U001");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "修改失败", 0, null,
							null, "U001");
				}
			} else {
				callbackDataPO = new CallbackDataPO("0", "原密码录入错误", 0, null,
						null, "U001");
			}

		}
		return JSON_DATA;

	}

	/**
	 * 修改个人信息
	 */
	public String doEditInfo() {
		String userid = request.getParameter("USERID");
		String sessionid = request.getParameter("SESSIONID");
		String userName = request.getParameter("USERNAME");// 昵称

		String phone = request.getParameter("PHONE");//
		String email = request.getParameter("EMAIL");
		String path = "", filename = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		/*
		 * if( file != null ){ filename = sdf.format(new
		 * Date())+fileFileName.substring
		 * (fileFileName.lastIndexOf(".")).toLowerCase(); try { path =
		 * AppFileUpLoad.uploadOne(file,filename); } catch (Exception e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * System.out.println("File path is  "+ path);
		 * System.out.println("FileName is " + filename); } else{
		 * System.out.println("上传的文件信息有误！！"); }
		 */

		byte[] fileByte = null;
		if (file != null) {
			fileByte = fileToStream(file);
		}
		if (StringUtils.isBlank(sessionid)) {
			throw new NullArgumentException("SESSIONID");
		} else if (StringUtils.isBlank(userid)) {
			throw new NullArgumentException("USERID");
		} else {
			u001PO = new U001PO();
			u001PO.setMa001(userid);
			u001PO.setMa010(sessionid);
			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "U001");
			}

			if (list != null && list.size() > 0) {
				U001PO u001 = list.get(0);
				u001.setMa001(userid);
				u001.setMa005(email);
				u001.setMa006(phone);
				u001.setMa008(userName);
				u001.setMa012(path);
				u001.setMa013(filename);
				u001.setMa017(fileByte);
				try {
					u001Service.updateInstance(u001);
					u001PO = u001Service.retrieveInstanceByPO(u001).get(0);
					callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
							new Gson().toJson(u001),
							JSONObject.fromObject(u001), "U001");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "修改失败", 0, null,
							null, "U001");
				}
			} else {
				callbackDataPO = new CallbackDataPO("0", "该信息出错请重新登录", 0, null,
						null, "U001");
			}

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
					"U001");
			return JSON_DATA;

		}
		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else {

			U001PO tempU001po = new U001PO();
			tempU001po.setMa006(phone);
			try {
				List<U001PO> list = u001Service
						.retrieveInstanceByPO(tempU001po);
				if (list == null || list.size() == 0) {
					callbackDataPO = new CallbackDataPO("0",
							"此账号未注册，不能接收短信验证码", 0, null, null, "U001");
					return JSON_DATA;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "发送失败", 0, null, null,
						"U001");
				return JSON_DATA;
			}

			String yzm = x + "";
			try {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("phone", phone);
				map.put("yzm", yzm);
				u001Service.sendYZM(map);
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
				// System.out.println("智能家居验证码发送过程出错！");
			}

		}
		return JSON_DATA;

	}

	public String shareSDKYzm() {
		final String phone = request.getParameter("PHONE");
		String zone = request.getParameter("ZONE");
		final String password = request.getParameter("PASSWORD");
		final String code = request.getParameter("CODE");// 验证码
		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(code)) {
			throw new NullArgumentException("CODE");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			try {
				String resultString = new SmsVerifyKit("677c2c4b00e0", phone, zone, code).go();
								
								if (resultString.contains("200")) {
									log.info("验证码成功");
									u001PO = new U001PO();

									u001PO.setMa006(phone);
								

									List<U001PO> list = null;
									try {
										list = u001Service.retrieveInstanceByPO(u001PO);

										if (list != null && list.size() > 0) {
											U001PO u001 = list.get(0);
											u001.setMa009(PasswordUtil.createPassword(password));
											String finalMd5 = "";
											try {
												finalMd5 = u001Service.GetMD5Code(phone, password);
												u001.setMa010(finalMd5);
												try {
													u001Service.updateInstance(u001);
													u001PO = u001Service.retrieveInstanceByPO(u001PO)
															.get(0);
													callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
															new Gson().toJson(u001PO),
															JSONObject.fromObject(u001PO), "U001");
													u001Service.updateYZM(u001.getMa001());
													AndroidPush.register(phone, password);
												} catch (Exception e) {
													callbackDataPO = new CallbackDataPO("0", "修改失败", 0,
															null, null, "U001");
												}
											} catch (Exception e1) {
												// TODO Auto-generated catch block
												callbackDataPO = new CallbackDataPO("0", "更新失败", 0,
														null, null, "U001");
											}

										} else {
											callbackDataPO = new CallbackDataPO("0", "验证码错误", 0, null,
													null, "U001");
										}

									} catch (Exception e1) {
										// TODO Auto-generated catch block
										callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
												null, "U001");
									}
								} else {
									callbackDataPO = new CallbackDataPO("0", resultString, 0, null,
											null, "U001");
								}


			} catch (Exception e) {

				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "修改失败", 0,
						null, null, "U001");
				return JSON_DATA;
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

	/**
	 * 寻找密码
	 */
	public String doResetPw() {
		String phone = request.getParameter("PHONE");
		String password = request.getParameter("PASSWORD");
		String yzm = request.getParameter("YZM");// 验证码

		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(yzm)) {
			throw new NullArgumentException("YZM");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			u001PO = new U001PO();

			u001PO.setMa006(phone);
			u001PO.setMa011(yzm);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);

				if (list != null && list.size() > 0) {
					U001PO u001 = list.get(0);
					u001.setMa009(PasswordUtil.createPassword(password));
					String finalMd5 = "";
					try {
						finalMd5 = u001Service.GetMD5Code(phone, password);
						u001.setMa010(finalMd5);
						try {
							u001Service.updateInstance(u001);
							u001PO = u001Service.retrieveInstanceByPO(u001PO)
									.get(0);
							callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
									new Gson().toJson(u001PO),
									JSONObject.fromObject(u001PO), "U001");
							u001Service.updateYZM(u001.getMa001());
							AndroidPush.register(phone, password);
						} catch (Exception e) {
							callbackDataPO = new CallbackDataPO("0", "修改失败", 0,
									null, null, "U001");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						callbackDataPO = new CallbackDataPO("0", "更新失败", 0,
								null, null, "U001");
					}

				} else {
					callbackDataPO = new CallbackDataPO("0", "验证码错误", 0, null,
							null, "U001");
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "U001");
			}

		}
		return JSON_DATA;
	}

	/********* 接口部分 ************/

	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

	public U001PO getU001PO() {
		return u001PO;
	}

	public void setU001PO(U001PO u001po) {
		u001PO = u001po;
	}

	public IPermissionFacadeService getPermissionFacadeService() {
		return permissionFacadeService;
	}

	public void setPermissionFacadeService(
			IPermissionFacadeService permissionFacadeService) {
		this.permissionFacadeService = permissionFacadeService;
	}

	/**
	 * 
	 * 文件下载
	 * 
	 * @return
	 * @throws Exception
	 * @author 刘晓斌
	 * @create_time 2014年10月21日上午11:28:55
	 */
	public String doDownLoad() throws Exception {
		// File file = new File(downloadFilePath);
		// if(!file.exists()){
		// throw new
		// Exception("下载地址：『"+downloadFilePath+"』,文件:『"+downloadFileName+"』不存在！");
		// }
		return "down";
	}

	/**
	 * 
	 * 获取下载文件IO流
	 * 
	 * @return
	 * @throws Exception
	 * @author 刘晓斌
	 * @create_time 2014年10月21日上午11:29:37
	 */
	public InputStream getInputStream() throws Exception {
		InputStream inputStream = null;
		try {
			String ma001 = request.getParameter("ma001");
			byte[] b = u001Service.retrieveInstanceById(ma001).getMa017();
			// inputStream = FileUpload.getInputStream(downloadFilePath,
			// request);
			inputStream = new ByteArrayInputStream(b);
		} catch (Exception e) {
			throw new Exception("下载失败！");
		}
		return inputStream;
	}

	/**
	 * 
	 * 将文件对象转换成 byte数组
	 * 
	 * @param file
	 * @return
	 * @author 刘晓斌
	 * @create_time 2014年10月21日上午10:41:24
	 */
	private byte[] fileToStream(File file) {
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		byte[] b = new byte[4096];
		int len = -1;
		try {
			is = new FileInputStream(file);
			baos = new ByteArrayOutputStream(4096);
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}

	public static void main(String[] args) {
		try {
			String url = ConfConstants.HW_SMS_URL;
			Client client = new Client(new URL(url));
			client.invoke("sendMsg", new Object[] { "", "【汉威云】智能家居验证码" });
			System.out.println("向用户手机号发送短信，短信内容为报警信息");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("智能家居验证码已发送！");
	}

}
