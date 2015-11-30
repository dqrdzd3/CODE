package com.hw.hwsafe.smart.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.attachment.action.AppFileUpLoadAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.AppUserPO;
import com.hw.hwsafe.smart.pojo.UserBasePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.IUserBaseService;
import com.hw.hwsafe.smart.util.AndroidPush;
import com.hw.hwsafe.smart.util.base64;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

public class UserBaseAction extends AppFileUpLoadAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private U001PO u001PO;
	
	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IUserBaseService userService;
	
	public String loginAriRadio() throws Exception{
		String phone = request.getParameter("PHONENO");
		String password = request.getParameter("PASSWORD");
		String mobileType = request.getParameter("MOBILETYPE");
		String os = request.getParameter("OS");
		String iostoken = request.getParameter("IOSTOKEN");
		if (StringUtils.isBlank(phone)) {
			throw new NullArgumentException("PHONE");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		}else {
			
			u001PO = new U001PO();
			u001PO.setMa006(phone);
			u001PO.setMa009(PasswordUtil.createPassword(password));
			List<U001PO> list = u001Service.retrieveInstanceByPO(u001PO);
			if (list != null && list.size() > 0) {
				u001PO = list.get(0);
				if (iostoken != null) {
					// 更新
					u001PO.setMa018(iostoken);
			
				
				} 
				u001PO.setMa019(mobileType);
				u001Service.updateInstance(u001PO);
				AppUserPO userPO = new AppUserPO();
				userPO.setUserID(u001PO.getMa001());
				userPO.setName(u001PO.getMa008());
				userPO.setCountryCode(u001PO.getMa020());
				userPO.setEmail(u001PO.getMa005());
				userPO.setPhoneNo(u001PO.getMa006());
				userPO.setToken(u001PO.getMa010());
				userPO.setCountryCode(u001PO.getMa020());
				if (u001PO.getMa017()!=null) {
					//userPO.setPicbs64(base64.toString(u001PO.getMa017()));
					userPO.setPic(u001PO.getMa017());
				}
			
		
				UserBasePO basePO = userService.getBaseByUserId(u001PO.getMa001());
				if (basePO == null) {
					basePO = new UserBasePO();
					
					basePO.setCity(u001PO.getMa003());
					basePO.setLat(u001PO.getMa014());
					basePO.setLon(u001PO.getMa015());
					basePO.setProvince(u001PO.getMa002());
					basePO.setSection(u001PO.getMa004());
				
				}
				userPO.setBaseInfo(basePO);
				callbackDataPO = new CallbackDataPO("1", "登录成功", 1,
						new Gson().toJson(userPO),
						JSONObject.fromObject(userPO), "U001");
			} else {
				callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误", 0, null,
						null, "U001");
			}
			
			
		}
		
		
		
		return JSON_DATA;
	}

	
	//注册
	public String register() {
		String userName = request.getParameter("USERNAME");// 昵称
		String password = request.getParameter("PASSWORD");
		String phone = request.getParameter("PHONE");//
		String email = request.getParameter("EMAIL");
		String lat = request.getParameter("LAT");
		String lng = request.getParameter("LNG");
		String loc = request.getParameter("LOC");
		String provice = request.getParameter("PROVINCE");
		String city = request.getParameter("CITY");
		String area = request.getParameter("SECTION");
		String path = "", filename = "";
		String iostoken = request.getParameter("IOSTOKEN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String app = request.getParameter("APP");
		String app_version = request.getParameter("APPVERSION");
		String manufacturer = request.getParameter("MANUFACTURER");
		String  os = request.getParameter("OS");
		String equip = request.getParameter("EQUIP_INFO");
		String addr = request.getParameter("ADDR");
		String countryCode = request.getParameter("COUNTRYCODE");

		byte[] fileByte = null;
		if (file != null) {
			fileByte = fileToStream(file);
		}
		UserBasePO userBasePO = new UserBasePO();
		userBasePO.setId(UUIDGenerater.get32UUID());
		userBasePO.setApp(app);
		userBasePO.setApp_version(app_version);
		userBasePO.setManufacturer(manufacturer);
		userBasePO.setOs(os);
		userBasePO.setEquip_info(equip);
		userBasePO.setProvince(provice);
		userBasePO.setCity(city);
		userBasePO.setSection(area);
		userBasePO.setLat(lat);
		userBasePO.setLon(lng);
		userBasePO.setAddr(addr);

		
		if (StringUtils.isBlank(userName)) {
			throw new NullArgumentException("USERNAME");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			try {
				u001PO = new U001PO();
				u001PO.setMa006(phone);
				boolean flag = u001Service.checkAccount(u001PO);
				if (flag) {
					callbackDataPO = new CallbackDataPO("0", "账号重复", 0, null, null,
							"U001");
				} else {
					String finalMd5 = u001Service.GetMD5Code(phone, password);
					String finalPwd = PasswordUtil.createPassword(password);
					String id = UUIDGenerater.get32UUID();
					u001PO.setMa001(id);
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
					u001PO.setMa020(countryCode);
					u001Service.insertInstance(u001PO);
					userBasePO.setUserId(id);
				    userService.insertAppBase(userBasePO);
				//	AndroidPush.register(phone, "123456"); // 推送服务器增加用户 默认密码123456''
				    
				    AppUserPO userPO = new AppUserPO();
					userPO.setUserID(u001PO.getMa001());
					userPO.setName(u001PO.getMa008());
					userPO.setCountryCode(u001PO.getMa020());
					userPO.setEmail(u001PO.getMa005());
					userPO.setPhoneNo(u001PO.getMa006());
					userPO.setToken(u001PO.getMa010());
					userPO.setCountryCode(u001PO.getMa020());
					if (u001PO.getMa017()!=null) {
						//userPO.setPicbs64(base64.toString(u001PO.getMa017()));
						userPO.setPic(u001PO.getMa017());
					}
					userPO.setBaseInfo(userBasePO);
					
					u001PO.setMa011(new SimpleDateFormat("yyyy-MM-dd HH:hh:ss")
							.format(u001PO.getMa007()));
					u001PO.setMa007(null);
					callbackDataPO = new CallbackDataPO("1", "用户注册成功", 1,
							new Gson().toJson(userPO),
							JSONArray.fromObject(userPO), "U001");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0", "用户注册失败", 0,
						null,
						null, "U001");
			}
			
		}
		return JSON_DATA;
	}
	/**
	 * 寻找密码
	 */
	public String doResetPw() {
		String phone = request.getParameter("PHONE");
		String password = request.getParameter("NEW");
		String yzm = request.getParameter("VALIDATECODE");// 验证码

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
							AppUserPO userPO = new AppUserPO();
							userPO.setUserID(u001PO.getMa001());
							userPO.setName(u001PO.getMa008());
							userPO.setCountryCode(u001PO.getMa020());
							userPO.setEmail(u001PO.getMa005());
							userPO.setPhoneNo(u001PO.getMa006());
							userPO.setToken(u001PO.getMa010());
							
							callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
									new Gson().toJson(userPO),
									JSONObject.fromObject(userPO), "U001");
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
	
	/**
	 * 修改密码
	 */
	public String doEditPw() {
		String oldpass = request.getParameter("OLD");
		String password = request.getParameter("NEW");
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

					callbackDataPO = new CallbackDataPO("0", "更新失败", 0, null,
							null, "U001");
				}
				u001.setMa010(finalMd5);
				try {
					u001Service.updateInstance(u001);
					u001PO = u001Service.retrieveInstanceByPO(u001).get(0);
					AppUserPO userPO = new AppUserPO();
					userPO.setUserID(u001PO.getMa001());
					userPO.setName(u001PO.getMa008());
					userPO.setCountryCode(u001PO.getMa020());
					userPO.setEmail(u001PO.getMa005());
					userPO.setPhoneNo(u001PO.getMa006());
					userPO.setToken(u001PO.getMa010());
					
					
					AndroidPush.register(u001.getMa006(), password);
					callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
							new Gson().toJson(userPO),
							JSONObject.fromObject(userPO), "U001");
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
	public String change() {
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
					
					AppUserPO userPO = new AppUserPO();
					userPO.setUserID(u001PO.getMa001());
					userPO.setName(u001PO.getMa008());
					userPO.setCountryCode(u001PO.getMa020());
					userPO.setEmail(u001PO.getMa005());
					userPO.setPhoneNo(u001PO.getMa006());
					userPO.setToken(u001PO.getMa010());
					
					if (u001PO.getMa017()!=null) {
						
						userPO.setPic(u001PO.getMa017());
					}
				
			
					UserBasePO basePO = userService.getBaseByUserId(u001PO.getMa001());
					if (basePO == null) {
						basePO = new UserBasePO();
						
						basePO.setCity(u001PO.getMa003());
						basePO.setLat(u001PO.getMa014());
						basePO.setLon(u001PO.getMa015());
						basePO.setProvince(u001PO.getMa002());
						basePO.setSection(u001PO.getMa004());
					}
					userPO.setBaseInfo(basePO);
					
					
					callbackDataPO = new CallbackDataPO("1", "修改成功", 1,
							new Gson().toJson(userPO),
							JSONObject.fromObject(userPO), "U001");
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
	public U001PO getU001PO() {
		return u001PO;
	}


	public void setU001PO(U001PO u001po) {
		u001PO = u001po;
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
