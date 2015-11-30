/**
 * 文件名：SwitchModuleAction.java
 * 版本信息：
 * 日期：2012-12-14
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hwsensor.permission.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.StringUtil;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysRolePermPO;
import com.hwsensor.permission.pojo.SysUserRolePO;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysRolePermService;
import com.hwsensor.permission.service.ISysUserRoleService;
import com.hwsensor.permission.service.IUserService;

/**
 * 
 * 项目名称：permission
 * 类名称：SwitchModuleAction
 * 类描述：首页切换业务模块时判断是否拥有权限
 * 创建人：杜群星
 * 创建时间：2012-12-14 下午2:27:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 * 
 */
@SuppressWarnings("unchecked")
public class SwitchModuleAction extends BaseAction {
  
	  private static final String DEVELOPING = "此业务正在开发中，\r\n敬请期待！";
	//  private static final String ENT_NO_PURCHASE = "本企业暂未购买此业务，\r\n如需购买，请联系代理商！";
	//  private static final String GOV_NO_PURCHASE = "本政府暂未购买此业务，\r\n如需购买，请联系代理商！";
	  private static final String NO_PURCHASE = "暂未购买此业务，\r\n如需购买，请联系代理商！";
	  private static final String PERSON_NO_PERMI = "您暂无权使用此业务，\r\n请联系管理员！";
	
	@Autowired
	private ISysModuleService sysModuleService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISysRolePermService sysRolePermService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
  
public ISysModuleService getSysModuleService() {
	return sysModuleService;
}

public void setSysModuleService(ISysModuleService sysModuleService) {
	this.sysModuleService = sysModuleService;
}

public IUserService getUserService() {
	return userService;
}

public void setUserService(IUserService userService) {
	this.userService = userService;
}

public ISysRolePermService getSysRolePermService() {
	return sysRolePermService;
}

public void setSysRolePermService(ISysRolePermService sysRolePermService) {
	this.sysRolePermService = sysRolePermService;
}

public ISysUserRoleService getSysUserRoleService() {
	return sysUserRoleService;
}

public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
	this.sysUserRoleService = sysUserRoleService;
}

public void checkPermi(){
    
    String retMsg = "true";
    
    String id = request.getParameter("id");
    
    try {
        
        
        String menuCode = StringUtil.string2DefVal(id);
        
        UserPO curUserPO = SessionUtil.getUserPO();
        String curUserId = curUserPO.getUUID();
        String userType = curUserPO.getUSER_TYPE();
        String isAdmin = curUserPO.getIS_ADMIN();
     
        boolean isPurchase = false;
        boolean isHavePermi = false;
        
        logger.info("判断【"+menuCode+"】业务是否正在开发中");
        //1.判断此业务是否正在开发中
        SysModulePO modulePO = checkIsDeveloping(menuCode);
        if (modulePO != null) {
          logger.info("【"+menuCode+"】业务已在开发");
          if (Constants.SYS.equals(userType) && Integer.parseInt(StringUtil.string2DefVal(isAdmin, "0")) == 0) {
            logger.info("判断用户为系统非管理员是否有权限");
            isHavePermi = checkIsHavePermi(curUserPO, modulePO);
            if (!isHavePermi) {
              logger.info("用户为系统非管理员无权限");
              retMsg = PERSON_NO_PERMI;
            }
            logger.info("用户为系统非管理员有权限");
          }
          
          if (!Constants.SYS.equals(userType)) {
            // 2.判断企业、政府是否已购买此业务
            logger.info("判断企业、政府是存在管理员");
            UserPO adminUser = checkIsExitOrgAdmin();
            if (adminUser != null) {
              logger.info("企业,政府存在管理员，判断是否已经购买此业务");
              String orgAdminId = adminUser.getUUID();
              isPurchase = checkIsPurchase(adminUser, modulePO);
              if (isPurchase) {
                // checkIsHavePermi(userPO, modulePO);
                // 判断是否是管理员，不是的话，判断普通用户是否拥有权限
                logger.info("判断登录用户是否为管理员");
                if (!curUserId.equals(orgAdminId)) {
                  logger.info("登录用户不是管理员，判断是否拥有此业务访问权限");
                  isHavePermi = checkIsHavePermi(curUserPO, modulePO);
                  if (!isHavePermi) {
                    logger.info("登录用户不是管理员，不拥有此业务访问权限");
                    retMsg = PERSON_NO_PERMI;
                  }
                  logger.info("登录用户不是管理员，拥有此业务访问权限");
                }
                logger.info("登录用户是为管理员");
              } else {
                logger.info("企业,政府存在管理员，未购买此业务");
                retMsg = NO_PURCHASE;
              }
            } else {
              logger.info("企业、政府不存在管理员，即未购买此业务");
              retMsg = NO_PURCHASE;
            }
          }
        } else {
          retMsg = DEVELOPING;
        }
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(retMsg);
    } catch (Exception e) {
      e.printStackTrace();
      retMsg = "请联系管理员";
      response.setContentType("text/html; charset=UTF-8");
      try {
        response.getWriter().print(retMsg);
      } catch (IOException ioex) {
        ioex.printStackTrace();
      }
    }
    
  }
  
  /**
   * 判断此业务是否正在开发中
   * isDeveloping
   * @param   
   * @return    boolean
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   */
  private SysModulePO checkIsDeveloping(String menuCode){
    SysModulePO modulePO = null;
    
    Map moduleMap = new HashMap();
    moduleMap.put("MENU_CODE", menuCode);
    moduleMap.put("menu_type", "10");
    
    modulePO = sysModuleService.retrieveSysModuleByMap(moduleMap);
    
    
    return modulePO;
  }
  
  /**
   * 判断企业、政府是否已购买此业务
   * isPurchase
   * @param   
   * @return    boolean
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   */
  private boolean checkIsPurchase(UserPO userPO,SysModulePO modulePO){
    
    boolean flag = false;
    
      String userId = userPO.getUUID();
      
      Map urMap = new HashMap();
      urMap.put("USER_UUID", userId);
      
      List<SysUserRolePO> roles =  sysUserRoleService.retrieveByCondition(urMap);
      
      
      for (SysUserRolePO userRolePO : roles) {
        String roleId = userRolePO.getROLE_UUID();
        String moduleId = modulePO.getUUID();
        
        Map rpMap = new HashMap();
        rpMap.put("ROLE_UUID", roleId);
        rpMap.put("MODULE_UUID", moduleId);
        
        SysRolePermPO sysRolePermPO = sysRolePermService.retrieveSysRolePermByMap(rpMap);
        
        if(sysRolePermPO != null){
          flag = true;
          break;
        }
      }
    
    
    return flag;
  }
  
  /**
   * 检查企业或政府是否存在管理员
   * checkIsExitOrgAdmin
   * @param   
   * @return    UserPO
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   */
  private UserPO checkIsExitOrgAdmin(){
    String orgId = SessionUtil.getOrgId();
    Map userMap = new HashMap();
    userMap.put("ORGAN_UUID", orgId);
    userMap.put("IS_ADMIN", "1");
    //查询企业，政府管理员
    return userService.retrieveSysUserByMap(userMap);
  }
  
  /**
   * 判断用户是否拥有权限
   * checkIsHavePermi
   * @param   
   * @return    boolean
   * @Exception 异常对象
   * @since  CodingExample　Ver(编码范例查看) 1.1
   */
   private boolean checkIsHavePermi(UserPO userPO,SysModulePO modulePO){
     boolean flag = false;
     
//     String orgId = SessionUtil.getOrgId();
     
     String userId = userPO.getUUID();
     String orgId = userPO.getORGAN_UUID();
     
     Map curUrMap = new HashMap();
     curUrMap.put("USER_UUID", userId);
     
     List<SysUserRolePO> curUserRoles =  sysUserRoleService.retrieveByCondition(curUrMap);
     
     
     for (SysUserRolePO curUserRolePO : curUserRoles) {
       String curRoleId = curUserRolePO.getROLE_UUID();
       String curModuleId = modulePO.getUUID();
       
       Map rpMap = new HashMap();
       rpMap.put("ROLE_UUID", curRoleId);
       rpMap.put("MODULE_UUID", curModuleId);
       rpMap.put("ORGAN_UUID", orgId);
       
       SysRolePermPO sysRolePermPO = sysRolePermService.retrieveSysRolePermByMap(rpMap);
       
       if(sysRolePermPO != null){
         flag = true;
         break;
       }
     }
     
     return flag;
  }
}