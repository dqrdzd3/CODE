package com.hw.hwsafe.smart.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.attachment.action.AppFileUpLoadAction;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.dao.ICorpAttachmentDao;
import com.hw.hwsafe.smart.pojo.CorpAttachmentPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ICorpAttachmentService;
import com.hw.hwsafe.smart.service.IBusinessService;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.util.AndroidPush;
import com.hw.hwsafe.smart.util.base64;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

public class BusinessAction extends AppFileUpLoadAction {

	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private ICorpAttachmentService corpAttachmentService;
	
	private BusinessPO businessPO = new BusinessPO();
	

	
	private File file_logo_s,file_logo_b;
	
	private String ids;
	public String doIndex(){
		return "success";
	}
	public String doAdd() {
		return "add";
	}
	
	public String doSaveAdd() {
		businessPO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		businessPO.setMa011(PasswordUtil.createPassword(businessPO.getMa010()));
	
		businessPO.setMa015(new Date());
		businessPO.setMa010("");   //密码清空
		try {
			businessService.insertInstance(businessPO);
			setAddSuccessMsg();
		} catch (Exception e) {
			setAddFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		try {
			businessPO = businessService.retrieveInstanceById(businessPO.getMa001());
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
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doSaveEdit() {
		try {
			businessService.updateInstance(businessPO);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			setUpdateFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doDelete() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idArray", ids.split(","));
			businessService.delBatchInstance(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doShow() {
		try {
			businessPO = businessService.retrieveInstanceById(businessPO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	//修改信息管理
	public String changeBusinessInfo(){
		return JSON_DATA;
	}
	//修改密码
	public String changePWD(){
		String oldpass = request.getParameter("oldpass");
		String password = request.getParameter("newpass");
		String userid = request.getParameter("USERID");
		String sessionid = request.getParameter("SESSIONID");

		if (StringUtils.isBlank(sessionid)) {
			throw new NullArgumentException("SESSIONID");
		} else if (StringUtils.isBlank(password)) {
			throw new NullArgumentException("PASSWORD");
		} else {
			BusinessPO businessPO = new BusinessPO();
			businessPO.setMa001(userid);
			businessPO.setMa011(sessionid);
			businessPO.setMa009(PasswordUtil.createPassword(oldpass));
			List<BusinessPO> list = null;
			try {
				list = businessService.retrieveInstanceByPO(businessPO);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "U001");
			}
			if (list != null && list.size() > 0) {
				BusinessPO u001 = list.get(0);
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
					businessService.updateInstance(u001);
					BusinessPO u001PO = businessService.retrieveInstanceByPO(u001).get(0);
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
	//上传图片
	public String uploadPic() {

		byte[] fileByte = null;
		
	
			if (file != null) {
				fileByte = fileToStream(file);
				CorpAttachmentPO po = new CorpAttachmentPO();
				String ma001 = UUID.randomUUID().toString().trim().replaceAll("-", "");
				po.setMa001(ma001);
				po.setMa002(fileByte);
				//po.setMa003(base64.toString(fileByte));
				po.setMa005(1);
				try {
					corpAttachmentService.insertInstance(po);
					callbackDataPO = new CallbackDataPO("1", ma001, 1,
							base64.toString(fileByte),
							null, "Business");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0", "", 1,
							"",
							null, "Business");
					e.printStackTrace();
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
	public BusinessPO getBusinessPO() {
		return businessPO;
	}

	public void setBusinessPO(BusinessPO businessPO) {
		this.businessPO = businessPO;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}
	public IBusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	public File getFile_logo_s() {
		return file_logo_s;
	}
	public void setFile_logo_s(File file_logo_s) {
		this.file_logo_s = file_logo_s;
	}
	public File getFile_logo_b() {
		return file_logo_b;
	}
	public void setFile_logo_b(File file_logo_b) {
		this.file_logo_b = file_logo_b;
	}
	public ICorpAttachmentService getCorpAttachmentService() {
		return corpAttachmentService;
	}
	public void setCorpAttachmentService(
			ICorpAttachmentService corpAttachmentService) {
		this.corpAttachmentService = corpAttachmentService;
	}


}
