package com.hwsensor.permission.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.service.ISysModuleService;

@SuppressWarnings("unchecked")
public class SysModuleAction extends BaseAction {
  
  @Autowired
  private IDictionaryService dictService;
  
  @Autowired
  private ISysModuleService sysModuleService;
  
  private SysModulePO sysModulePO;
  
  private List isValid = new ArrayList();
  private List userType = new ArrayList();
  private List menuType = new ArrayList();
  private List isShow = new ArrayList();
  
  private String moduleTree;
  
  private int orderNum;
  
  public SysModulePO getSysModulePO() {
  
    return sysModulePO;
  }

  
  public void setSysModulePO(SysModulePO sysModulePO) {
  
    this.sysModulePO = sysModulePO;
  }

  
  public List getIsValid() {
  
    return isValid;
  }

  
  public void setIsValid(List isValid) {
  
    this.isValid = isValid;
  }
  
  
  public void setModuleTree(String moduleTree) {
  
    this.moduleTree = moduleTree;
  }
  
  
  public String getModuleTree() {
  
    return moduleTree;
  }

  
  
  public List getUserType() {
  
    return userType;
  }


  
  public void setUserType(List userType) {
  
    this.userType = userType;
  }


  
  public List getMenuType() {
  
    return menuType;
  }


  
  public void setMenuType(List menuType) {
  
    this.menuType = menuType;
  }

  
  public void setIsShow(List isShow) {
  
    this.isShow = isShow;
  }
  
  public List getIsShow() {
  
    return isShow;
  }
  
  
  public void setOrderNum(int orderNum) {
  
    this.orderNum = orderNum;
  }
  
  public int getOrderNum() {
  
    return orderNum;
  }

  /**
   * 初始化用户类型，模块类型，是否有效数据列表
   * init
   * @param   name
   * @return String
   */
  public void init() throws Exception{
    try {
      userType = dictService.retrieveChildDictListByKey("usertype");
      menuType = dictService.retrieveChildDictListByKey("menutype");
      isValid = dictService.retrieveChildDictListByKey("isValid");
      isShow = dictService.retrieveChildDictListByKey("isShow");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  public String doList(){
    
    try {
      init();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "list";
  }
  
  /**
   * 加载模块树
   * doLoadTree
   * @param   name
   * @return String
   */
  public String doLoadTree(){
    try {
      sysModuleService.bulidSysMoudleTree(this);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "loadtree";
  }
  
  public String doAdd(){
    try {
      init();
      
      orderNum = sysModuleService.queryModuleOrderNum();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "add";
  }
  
  public String doSaveAdd(){
    try {
        sysModuleService.insertSysModuleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doEdit(){
    try {
      init();
      
      sysModulePO = sysModuleService.retrieveSysModuleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "edit";
  }
  
  public String doSaveEdit(){
    try {
      sysModuleService.updateSysModuleBP(this);
      
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return Constants.JSON_MSG;
  }
  
  public String doDelete(){
    try {
      
      sysModuleService.deleteSysModuleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doView(){
    try {
      init();
      sysModulePO = sysModuleService.retrieveSysModuleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "view";
  }
  
  /**
   * 验证模块编码的唯一性
   * checkOnlyModuleCode
   * @param   name
   * @return String
   */
  public void checkOnlyModuleCode(){
    try {
    
      String menuCode = request.getParameter("menuCode");
      String parentId = request.getParameter("parentId");
      String id = request.getParameter("id");
      
      menuCode = (menuCode == null || menuCode.equals("null")) ? "" : menuCode;
      parentId = (parentId == null || parentId.equals("null")) ? "" : parentId;
      id = (id == null || id.equals("null")) ? "" : id;
      
      Map map = new HashMap();
      
      SysModulePO sysModulePO = null;
      String pCode = "";
      if(!parentId.isEmpty()){
        map.put("UUID", parentId);
        sysModulePO = sysModuleService.retrieveSysModuleByMap(map);
        
        if(sysModulePO != null){
          pCode = sysModulePO.getMENU_CODE();
        }
      }
      
      if(!pCode.isEmpty()){
        menuCode = pCode+"_"+menuCode;
      }
      
      map.clear();
      
      map.put("MENU_CODE", menuCode);
      
      List<SysModulePO> modulePOs = sysModuleService.retrieveByCondition(map);
      
      String flag = "true";
      
      if(modulePOs != null && modulePOs.size()>0){
        if(modulePOs.get(0).getUUID().equals(id)){
          flag = "true";
        }else{
          flag = "false";
        }
      }
      
      response.getWriter().print(flag);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



public void setDictService(IDictionaryService dictService) {

  this.dictService = dictService;
}

public IDictionaryService getDictService() {

  return dictService;
}


public ISysModuleService getSysModuleService() {
	return sysModuleService;
}


public void setSysModuleService(ISysModuleService sysModuleService) {
	this.sysModuleService = sysModuleService;
}
  
  
}
