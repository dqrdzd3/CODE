package com.hwsensor.permission.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hwsensor.permission.pojo.DepartmentPO;
import com.hwsensor.permission.service.IDepartmentService;
import com.hwsensor.permission.service.IUserService;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * 项目名称：framework 
 * 类名称：UserAction 
 * 类描述： 用户管理
 * 创建人：李玉梅 
 * 创建时间：2012-5-15
 */

public class UserAction extends BaseAction implements Preparable {

  @Autowired
  private IUserService sysUserService;
  
  @Autowired
  private IDictionaryService dictService;
  
  @Autowired
  private IDepartmentService iDepartmentService;
  
  private UserPO sysUserPO;
  
  private UserPO userinfoPO;
  
  private List<UserPO> sysUserList;
  
  private String deptTree;
  
  private String operTree;
  
  private String haveOpers;
  
  private List roleList = new ArrayList();
  
  private List<Map<String, String>> haveRoles = new ArrayList<Map<String,String>>();
  
  private List userType = new ArrayList();
  
  
  private List userStatus = new ArrayList();
  
  private List sex = new ArrayList();
  
  private List isHand = new ArrayList();
  
  private List isOuterNet = new ArrayList();
  
  private Map map = new HashMap();
  
  private DepartmentPO departmentPO;
  
  private List<String> haveRoleList = new ArrayList<String>();
  
  public void setHaveRoleList(List<String> haveRoleList) {
	this.haveRoleList = haveRoleList;
  }
  
  public List<String> getHaveRoleList() {
	return haveRoleList;
  }
  
  public void setIsHand(List isHand) {
  
    this.isHand = isHand;
  }
  
  
  public List getIsHand() {
  
    return isHand;
  }
  
  
  public void setIsOuterNet(List isOuterNet) {
  
    this.isOuterNet = isOuterNet;
  }
  
  public List getIsOuterNet() {
  
    return isOuterNet;
  }
  
  
  public void setDepartmentPO(DepartmentPO departmentPO) {
  
    this.departmentPO = departmentPO;
  }
  
  
  public DepartmentPO getDepartmentPO() {
  
    return departmentPO;
  }
  
  
  public UserPO getSysUserPO() {
  
    return sysUserPO;
  }

  
  public void setSysUserPO(UserPO sysUserPO) {
  
    this.sysUserPO = sysUserPO;
  }

  
  public List<UserPO> getSysUserList() {
  
    return sysUserList;
  }

  
  public void setSysUserList(List<UserPO> sysUserList) {
  
    this.sysUserList = sysUserList;
  }

  
  public String getDeptTree() {
  
    return deptTree;
  }

  
  public void setDeptTree(String deptTree) {
  
    this.deptTree = deptTree;
  }

  
  public String getOperTree() {
  
    return operTree;
  }

  
  public void setOperTree(String operTree) {
  
    this.operTree = operTree;
  }

  
  public String getHaveOpers() {
  
    return haveOpers;
  }

  
  public void setHaveOpers(String haveOpers) {
  
    this.haveOpers = haveOpers;
  }

  
  public List getRoleList() {
  
    return roleList;
  }

  
  public void setRoleList(List roleList) {
  
    this.roleList = roleList;
  }

  
  public List<Map<String, String>> getHaveRoles() {
  
    return haveRoles;
  }

  
  public void setHaveRoles(List<Map<String, String>> haveRoles) {
  
    this.haveRoles = haveRoles;
  }

  
  public List getUserType() {
  
    return userType;
  }

  
  public void setUserType(List userType) {
  
    this.userType = userType;
  }

  
  public List getUserStatus() {
  
    return userStatus;
  }

  
  public void setUserStatus(List userStatus) {
  
    this.userStatus = userStatus;
  }

  
  public List getSex() {
  
    return sex;
  }

  
  public void setSex(List sex) {
  
    this.sex = sex;
  }

  
  public Map getMap() {
  
    return map;
  }

  
  public void setMap(Map map) {
  
    this.map = map;
  }
  

  public UserPO getUserinfoPO() {
	return userinfoPO;
}


public void setUserinfoPO(UserPO userinfoPO) {
	this.userinfoPO = userinfoPO;
}


public void prepare() throws Exception {
    init();
  }
  
  /**
   * 初始化用户类型，模块类型，是否有效数据列表
   * init
   * @param   name
   * @return String
   */
  private void init() throws Exception{
    try {
      userType = dictService.retrieveChildDictListByKey("usertype");
      userStatus = dictService.retrieveChildDictListByKey("userstatus");
      sex = dictService.retrieveChildDictListByKey("sex");
      isHand = dictService.retrieveChildDictListByKey("isHand");
      isOuterNet = dictService.retrieveChildDictListByKey("isOuterNet");
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
     UserMessageData messageData =  sysUserService.insertSysUserBP(this);
     if(messageData == null || messageData.getContent().isEmpty()){
       message.set(Constants.MSG_ERROR, "请联系管理员", Constants.SERVER_MSG);
     }else{
       message = messageData;
     }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  public String doEdit(){
    
    try {
      init();
      
      sysUserPO = sysUserService.retrieveSysUserBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "edit";
  }
  public String doView(){
    
    try {
      init();
      sysUserPO = sysUserService.retrieveSysUserBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "view";
  }
  
  public String doSaveEdit(){
    try {
      UserMessageData messageData = sysUserService.updateSysUserBP(this);
      
      if(messageData == null || messageData.getContent().isEmpty()){
        messageData.set(Constants.MSG_ERROR, "请联系管理员", Constants.SERVER_MSG);
      }else{
        message = messageData;
      }
    } catch (Exception e) {
      setUpdateFailedMsg();
      e.printStackTrace();
    }
    return Constants.JSON_MSG;
  }
  
  
  
  
  public String doDelete(){
    try {
      String ids = request.getParameter("ids");
      
      if(ids == null || ids.isEmpty()){
        message.set(Constants.MSG_INFO, "请选择要删除的用户！", Constants.SERVER_MSG);
        return Constants.JSON_MSG;
      }
      
      
      map.put("ids", ids);
      
      Map msgMap = new HashMap();
      
      msgMap =  sysUserService.deleteSysUserBP(map);
      
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
  
  
  /**
   * 分配角色页面
   * doAssignRole
   * @param   name
   * @return String
   */
  public  String doAssignRole(){
    try {
      sysUserService.doAssignRole(this);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "userrole";
  }
  
  /**
   * 角色分配保存
   * doAssingRoleSave
   * @param   name
   * @return String
   */
  public  String doSaveAssignRole(){
    
    sysUserService.saveAssignRole(this);
    
    return Constants.JSON_MSG;
  }
  

  /**
   * 控制跳转到密码修改页面
   * @return
   */
	public String userPassWord(){
		return "editpassword";
	}
	/**
	 * 修改用户密码信息
	 * @return
	 */
	public String editUserPassWord(){
		try {
			String loginname=this.getSessionUserPO().getLoginName();
			String UUID=this.getSessionUserPO().getUUID();
			userinfoPO.setUUID(UUID);
			userinfoPO.setLOGIN_NAME(loginname);
			message=sysUserService.updateSysUserPassWord(userinfoPO);
		} catch (Exception e) {
			e.printStackTrace();
		setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 查询当前登录用户的基本信息
	 */
	public  String getUserInfo() throws Exception{
	
		try{
			userinfoPO = sysUserService.retrieveUserInfoByUserID(this.getSessionUserPO().getId());
			if(userinfoPO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userinfo";
	}
	/**
	 * 修改登录用户的基本信息（操作新用户表）
	 */
	public String doSaveEditUserInfo() {
		try {
			//获得从前台得到的参数，赋予部门字段
			message=sysUserService.updateSysUserInfo(userinfoPO);
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
  /**
   * 根据用户角色Id,查询此角色下拥有的操作
   * doQueryHaveOpers
   * @param   name
   * @return String
   */
  public String doQueryHaveOpers(){
    
    String ids = request.getParameter("roleIds");
    ids = ids == null ? "": ids;
    List idList = new ArrayList();
    idList = Arrays.asList(ids.split(","));
    Map map = new HashMap();
    map.put("haveRoles", idList);
    haveOpers = sysUserService.queryHaveOpers(map);
   
    return "haveopers";
  }
  
  /**
   * 密码重置
   * doReSetPassWord
   * @param   name
   * @return String
   */
  public String doReSetPassWord(){
    try {
        
      String ids = request.getParameter("ids");
      ids = ids == null ? "" : ids;
      
      if(ids.isEmpty()){
        getMessage().set(Constants.MSG_ERROR, "请选择要重置密码的账号！", Constants.SERVER_MSG);
        return Constants.JSON_MSG;
      }
      
      String[] idArr = ids.split(",");
      map.put("idArr", idArr);
      
      map = sysUserService.updatePassWord(map);
      
      if(map == null){
        getMessage().set(Constants.MSG_OK, "密码重置成功,\r\n 请注意查收邮件！", Constants.SERVER_MSG);
      }
      if(map != null && !map.get(Constants.RETURN_MSG).equals("")){
        getMessage().set(Constants.MSG_ERROR, "密码重置失败！", Constants.SERVER_MSG);
      }
    
    } catch (Exception e) {
      e.printStackTrace();
      getMessage().set(Constants.MSG_ERROR, "密码重置失败！", Constants.SERVER_MSG);
    }
    return Constants.JSON_MSG;
  }
  
  /**
   * 政府/企业index页面
   */
  public String doIndex(){
    
    return "index";
    
  }
  /**
   * 政府/企业中部门树
   */
  public String doLoadTree(){
    UserPO userPO = getSessionUserPO();
    String corpId = userPO.getCorpid();
    map.put("corpId", corpId);
    deptTree = sysUserService.buildDeptTree(map);
    return "loadtree";
  }
  
  /**
   * 政府企业中非管理员用户列表
   * doList
   * @param   name
   * @return String
   */
  public String doList(){
    
    return "list";
  }
  
  public String doAddOrg(){
    try {
      init();
      String ID = request.getParameter("deptID");
      departmentPO = queryDeptByID(ID);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "add";
  }
  
  public String doEditOrg(){
    
    try {
      init();
      String ID = request.getParameter("deptID");
      departmentPO = queryDeptByID(ID);
      
      sysUserPO = sysUserService.retrieveSysUserBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "edit";
  }
  
  
  public String doViewOrg(){
    
    try {
      init();
      String ID = request.getParameter("deptID");
      departmentPO = queryDeptByID(ID);
      sysUserPO = sysUserService.retrieveSysUserBP(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "view";
  }
  
  
  /**
   * 查询部门信息
   * queryDeptByID
   * @param   name
   * @return String
   */
  private DepartmentPO queryDeptByID(String ID) throws Exception{
    DepartmentPO departmentPO = null;
    
    departmentPO = iDepartmentService.retrieveDepartmentByDeptId(ID);
    
    return departmentPO;
    
  }
  
 public String queryUserList() throws Exception{
   try {
    
     String roleType = request.getParameter("roleType");
            roleType = roleType == null ? "" : roleType;
     String roleOrgId = request.getParameter("roleOrgId");
            roleOrgId = roleOrgId == null ? "" : roleOrgId; 
     pagerData = initPagerData();
     
     //放置页面参数
     map.put("pagerData", pagerData);
     
     if(sysUserPO == null){
       sysUserPO = new UserPO();
     }
     String loginName = sysUserPO.getLOGIN_NAME();
     String realName = sysUserPO.getREAL_NAME();
     
     map.put("LOGINNAME", loginName);
     map.put("REALNAME", realName);
     
     
     UserPO userPO = getSessionUserPO();
     
     String userType = userPO.getUSER_TYPE();
//     String curOrgId = userPO.getORGAN_UUID();
     
     //判断当前用户是否为系统用户
     //是：查询用户为，系统非管理员和企业、政府的管理员
     //否：查询用户为，企业，政府的非管理员
     if(userType.equals("SYS")){
       
       map.put("USER_TYPE", roleType);
//       map.put("ORGID", curOrgId);
       
       if(roleType.equals("SYS")){
         map.put("IS_ADMIN", "0");
       }else{
         map.put("IS_ADMIN", "1");
       }
       
     }else{
       map.put("USER_TYPE", userType);
       map.put("IS_ADMIN", "0");
       map.put("ORGID", roleOrgId);
     }
     
     pagerData.setRows(sysUserService.queryUsersList(map));
     
     // 根据条件查询总条数
     map.put("selectCount", true);
     
     pagerData.setRecords(sysUserService.queryUsersList(map));
   
   } catch (Exception e) {
     e.printStackTrace();
   }
   return JSON_PAGER;
 }
 
 /**
  * 检查登录账号是否唯一
  */
 public void checkOnlyLoginName(){
   try {
       String loginName = request.getParameter("loginName");
       String orgId = request.getParameter("orgId");
       loginName = loginName == null ? "" : loginName;
       orgId = (orgId == null || orgId.equals("null")) ? "" : orgId;
       
       map.put("LOGIN_NAME", loginName);
//       map.put("ORGAN_UUID", orgId);
      
       sysUserList = sysUserService.retrieveByCondition(map);
       
       String flag = "true";
       if(sysUserList != null && sysUserList.size()>0){
         flag = "false";
       }
   
       response.getWriter().print(flag);
    } catch (Exception e) {
      e.printStackTrace();
      try {
        response.getWriter().print("false");
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
   
 }
 /**
  * 检查员工编号是否唯一
  */
 public void checkOnlyEmployeeId(){
   try {
     String employeeId = request.getParameter("employeeId");
     String uuid = request.getParameter("uuid");
     String orgId = request.getParameter("orgId");
     employeeId = employeeId == null ? "" : employeeId;
     orgId = (orgId == null || orgId.equals("null")) ? "" : orgId;
     uuid = (uuid == null || uuid.equals("null")) ? "" : uuid;
     
     map.put("EMPLOYEE_NUM", employeeId);
     map.put("uniqueId", uuid);
     map.put("ORGAN_UUID", orgId);
     
     sysUserList = sysUserService.retrieveByCondition(map);
     
     String flag = "true";
     if(sysUserList != null && sysUserList.size()>0){
       flag = "false";
     }
     
     response.getWriter().print(flag);
   } catch (Exception e) {
     e.printStackTrace();
     try {
       response.getWriter().print("false");
     } catch (IOException ioe) {
       ioe.printStackTrace();
     }
   }
   
 }

public IUserService getSysUserService() {
	return sysUserService;
}

public void setSysUserService(IUserService sysUserService) {
	this.sysUserService = sysUserService;
}



public IDictionaryService getDictService() {
	return dictService;
}

public void setDictService(IDictionaryService dictService) {
	this.dictService = dictService;
}


public IDepartmentService getiDepartmentService() {

  return iDepartmentService;
}


public void setiDepartmentService(IDepartmentService iDepartmentService) {

  this.iDepartmentService = iDepartmentService;
}


 
 
}
