/**
 * 文件名：B00402Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00402PO;
import com.hw.hwsafe.corpinfo.service.IB00402Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 项目名称：hwsafe 
 * 类名称：B00401Action 
 * 类描述： 设备设施类
 * 创建人：wy 
 * 创建时间：2012-6-11 下午6:00:49 
 * 修改人：
 * 修改时间：2012-6-11 下午6:00:49 
 * 修改备注：
 * @version
 */
public class B00402Action extends BaseAction  {
	private B00402PO b00402PO;
    @Autowired
	private IB00402Service b00402Service;

    @Autowired
	private CodeValueService codevalueService;// 代码表Service
    private List<CodeValuePO> codeValueSF;// 代码表：是否代码
	
   
    /**
	 * 跳转到添加页面
	 */
	public String doAdd() {
		codeValueSF = codevalueService.retrieveCodeByType("yesOrNo");// 是否
		return "addb00402";
	}
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			b00402PO.setMa001(UUIDGenerater.getUUID());
			b00402PO.setMa009(getSessionUserPO().getId());
			b00402PO.setMa010(DateTimeUtils.getCurrentDate());
			b00402PO.setMa011(getSessionUserPO().getCorpid());
			b00402Service.insertB00402(b00402PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace(); 
			setAddFailedMsg();
		}
		return JSON_MSG;
	}

	/**
	 * 修改操作
	 */
	public String doEdit() {
		try {
			b00402PO = b00402Service.retrieveB00402ByID(b00402PO.getMa001());
			if (b00402PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 查询设备设施记录
	 * @return
	 */
	public String doView() {
		try {
			b00402PO = b00402Service.retrieveB00402ByID(b00402PO.getMa001());
			if (b00402PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	/**
	 * 修改记录
	 */
	public String doEditB00402() {
		try {
			b00402PO = b00402Service.retrieveB00402ByID(b00402PO.getMa001());
			if (b00402PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryB00402";
	}
	
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit() {
		try {
			if(b00402Service.isExist(b00402PO.getMa001())){
				b00402Service.updateB00402(b00402PO);
				setUpdateSuccessMsg();
			}else {
			    setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}

	public B00402PO getB00402PO() {
		return b00402PO;
	}

	public void setB00402PO(B00402PO b00402po) {
		b00402PO = b00402po;
	}

	public IB00402Service getB00402Service() {
		return b00402Service;
	}

	public void setB00402Service(IB00402Service b00402Service) {
		this.b00402Service = b00402Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public List<CodeValuePO> getCodeValueSF() {
		return codeValueSF;
	}

	public void setCodeValueSF(List<CodeValuePO> codeValueSF) {
		this.codeValueSF = codeValueSF;
	}
	

}

