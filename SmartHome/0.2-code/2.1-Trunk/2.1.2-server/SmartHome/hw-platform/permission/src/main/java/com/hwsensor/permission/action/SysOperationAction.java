/**
 * 文件名：SysOperationAction.java
 *
 * 版本信息：
 * 日期：2012-10-10
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.action;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.service.ISysOperationService;

/**
 * 项目名称：framework
 * 类名称：SysOperationAction
 * 类描述：系统操作action
 * 创建人：杜群星
 * 创建时间：2012-10-10 下午4:27:26
 */
public class SysOperationAction extends BaseAction {
  
  @Autowired
  private ISysOperationService sysOperationService;
  
  @Autowired
  private IDictionaryService dictionaryService;
  
  private SysOperationPO sysOperationPO;

  private List<SysOperationPO> operationList = new ArrayList<SysOperationPO>();

  private List isValid = new ArrayList();
  
  
  public void setIsValid(List isValid) {
  
    this.isValid = isValid;
  }
  
  public List getIsValid() {
  
    return isValid;
  }
  
  public SysOperationPO getSysOperationPO() {
  
    return sysOperationPO;
  }


  
  public void setSysOperationPO(SysOperationPO sysOperationPO) {
  
    this.sysOperationPO = sysOperationPO;
  }
  
  
  
  public List<SysOperationPO> getOperationList() {
  
    return operationList;
  }
  
  public void setOperationList(List<SysOperationPO> operationList) {
  
    this.operationList = operationList;
  }

  /**
   * 初始化用户类型，模块类型，是否有效数据列表
   * init
   * @param   name
   * @return String
   */
  public void init() throws Exception{
    try {
      isValid = dictionaryService.retrieveChildDictListByKey("isValid");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public String doList(){
    try {
      init();
      sysOperationService.retrieveSysOperationList(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "list";
  }
  
  public String doSaveAdd(){
    try {
      sysOperationService.insertSysOperationBP(this);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doDelete(){
    try {
      sysOperationService.deleteSysOperationBP(this);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }

public ISysOperationService getSysOperationService() {
	return sysOperationService;
}

public void setSysOperationService(ISysOperationService sysOperationService) {
	this.sysOperationService = sysOperationService;
}

public IDictionaryService getDictionaryService() {
	return dictionaryService;
}

public void setDictionaryService(IDictionaryService dictionaryService) {
	this.dictionaryService = dictionaryService;
}
  
}
