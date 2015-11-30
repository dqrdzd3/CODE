/**
 * 文件名：SysRoleAction.java
 *
 * 版本信息：
 * 日期：2012-10-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
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
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hwsensor.permission.pojo.SysRolePO;
import com.hwsensor.permission.service.ISysRoleService;

/**
 * 项目名称：framework
 * 类名称：SysRoleAction
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-12 下午1:31:08
 */
public class SysRoleAction extends BaseAction {

  @Autowired
  private ISysRoleService sysRoleService;
  
  @Autowired
  private IDictionaryService dictService;
  
  private SysRolePO sysRolePO;
  
  private List<SysRolePO> sysRoleList;
  
  private String operTree;
  
  private String haveOpers;
  
  private List userType = new ArrayList();
  
  private Map msgMap = new HashMap();
  
  public Map getMsgMap() {
  
    return msgMap;
  }
  
  public void setMsgMap(Map msgMap) {
  
    this.msgMap = msgMap;
  }


  public void setHaveOpers(String haveOpers) {
  
    this.haveOpers = haveOpers;
  }
  
  
  public String getHaveOpers() {
  
    return haveOpers;
  }
  
  public void setUserType(List userType) {
  
    this.userType = userType;
  }
  
  
  public List getUserType() {
  
    return userType;
  }
  
  public String getOperTree() {
  
    return operTree;
  }
  
  public void setOperTree(String operTree) {
  
    this.operTree = operTree;
  }


  public SysRolePO getSysRolePO() {
  
    return sysRolePO;
  }

  
  public void setSysRolePO(SysRolePO sysRolePO) {
  
    this.sysRolePO = sysRolePO;
  }

  
  public List<SysRolePO> getSysRoleList() {
  
    return sysRoleList;
  }

  
  public void setSysRoleList(List<SysRolePO> sysRoleList) {
  
    this.sysRoleList = sysRoleList;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public String doAdd(){
    try {
      init();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "add";
  }
  
  public String doSaveAdd(){
    
    try {
      sysRoleService.insertSysRoleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doEdit(){
    
    try {
      init();
      
      sysRolePO = sysRoleService.retrieveSysRoleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "edit";
  }
  public String doView(){
    
    try {
      init();
      
      sysRolePO = sysRoleService.retrieveSysRoleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "view";
  }
  
  public String doSaveEdit(){
    try {
      sysRoleService.updateSysRoleBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doDelete(){
    try {
      String ids = request.getParameter("ids");
          
      if(ids == null || ids.isEmpty()){
        message.set(Constants.MSG_INFO, "请选择要删除的角色！", Constants.SERVER_MSG);
        return Constants.JSON_MSG;
      }
      
      Map<String, String> map = new HashMap<String, String>();
      
      map.put("ids", ids);
      
      msgMap = sysRoleService.deleteSysRoleBP(map);
      
      if(msgMap.get(Constants.RETURN_MSG) != null && !msgMap.get(Constants.RETURN_MSG).toString().isEmpty()){
        message.set(Constants.MSG_ERROR, msgMap.get(Constants.RETURN_MSG).toString(), Constants.SERVER_MSG);
      }else{
        setDelSuccessMsg();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      setDelFailedMsg();
    }
    return Constants.JSON_MSG;
  }
  
  public String doAssignPermi(){
    try {
      sysRoleService.buildTree(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "assignpermi";
  }
  
  public String doSaveAssignPermi(){
    try {
      sysRoleService.roleAuthorzationBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return Constants.JSON_MSG;
  }
  
  public String doAssignUser(){
    String roleId = request.getParameter("roleId");
    
    Map<String, String> map = new HashMap<String, String>();
    map.put("UUID", roleId);
    
    sysRolePO = sysRoleService.retrieveSysRoleByMap(map);
    
    
    return "assignuser";
  }
  public String doSaveAssignUser(){
    try {
      
      String roleId = request.getParameter("roleId");
      String ids = request.getParameter("ids");
      ids = ids == null ? "" :ids;
      String[] idArr = ids.split(",");
      
      sysRoleService.assignUser(roleId,idArr);
    } catch (Exception e) {
      e.printStackTrace();
      message.set(Constants.MSG_ERROR, "角色分配失败！", Constants.SERVER_MSG);
    }
    message.set(Constants.MSG_OK, "角色分配成功！", Constants.SERVER_MSG);
    
    return Constants.JSON_MSG;
  }
  
  
  /**
   * 检查角色名称是否唯一
   * checkOnlyRoleName
   * @param   
   * @return    void
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   */
  public void checkOnlyRoleName(){
    
    String roleName = request.getParameter("roleName");
    String roleId = request.getParameter("roleId");
    roleName = roleName == null ? "" : roleName.trim();
    roleId = roleId == null ? "" : roleId.trim();
    
    String orgId = SessionUtil.getOrgId();
    
    Map<String, String> onlyMap = new HashMap<String, String>();
    onlyMap.put("ORGAN_UUID", orgId);
    onlyMap.put("ROLE_NAME", roleName);
    
    
    List<SysRolePO> roles = sysRoleService.retrieveByCondition(onlyMap);
    
    boolean flag = false;
    if(roles != null && roles.size() == 1){
      SysRolePO rolePO = roles.get(0);
      
      if(rolePO.getUUID() != null && rolePO.getUUID().equals(roleId)){
        flag = true;
      }else{
        flag = false;
      }
      
    }
    if(roles == null || roles.size() == 0){
      flag = true;
    }
    
      try {
        if (flag) {
          response.getWriter().write("true");
        } else {
          response.getWriter().write("false");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    
    
  }

public ISysRoleService getSysRoleService() {
	return sysRoleService;
}

public void setSysRoleService(ISysRoleService sysRoleService) {
	this.sysRoleService = sysRoleService;
}

public IDictionaryService getDictService() {
	return dictService;
}

public void setDictService(IDictionaryService dictService) {
	this.dictService = dictService;
}
  
}
