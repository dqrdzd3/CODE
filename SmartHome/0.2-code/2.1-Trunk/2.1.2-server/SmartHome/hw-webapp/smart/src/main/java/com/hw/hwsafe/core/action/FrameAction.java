package com.hw.hwsafe.core.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.platform.userinfo.UserSession;
import com.hw.hwsafe.remind.outer.IRemindService;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.IUserService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：FrameAction
 * 类描述：处理系统的框架frame页面
 * 创建人：孟繁波
 * 创建时间：2012-8-11 
 * 修改人：杜群星
 * 修改时间：2012-12-12 
 * 修改备注：
 * @version 
 * 
 */

@SuppressWarnings("unchecked")
public class FrameAction extends BaseAction {
	
	@Autowired
	private ISysModuleService sysModuleService;
	@Autowired
	private IUserService sysUserService;
	@Autowired
	private ISysOperationService sysOperationService;
	
	private IRemindService remindService;
	
  @Autowired
  private ISysResourceService sysResourceService;

//  @Autowired
//  private IEhCacheManager cacheManager;
	
	private List<Map<String, String>> systemTreeStrList = new ArrayList<Map<String,String>>();
	
	private List<SysModulePO> userRootPerms = new ArrayList<SysModulePO>();
	
	private Map<String, String> moduleID = new HashMap<String,String>();
	
	//一般业务子系统模块
	private List<SysModulePO> busiModules = new ArrayList<SysModulePO>();
	//权限系统
	private List<SysModulePO> permiModules = new ArrayList<SysModulePO>();
	
	private List<SysModulePO> showModules = new ArrayList<SysModulePO>();
	
	
	/**
	 * mainLeft(查询功能菜单)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public String mainLeft() throws Exception {
	  try {
    
    		// 查询用户所有权限
    		UserPO userPO = ((UserSession) request.getSession().getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
    		String userId = userPO.getId();
    		
    		UserPO newUserPO = sysUserService.retrieveSysUserByUserID(userId);
    		String isAdmin = newUserPO.getIS_ADMIN();
    		String userType = newUserPO.getUSER_TYPE();
    		String orgId = newUserPO.getORGAN_UUID();
    		String deptId = newUserPO.getDEPART_UUID();
    		String isValid = "10";
    		
    		List<SysModulePO> allUserPerm = null;
    		List<SysModulePO> rootPerm = null;
    		List<SysOperationPO> haveOpers = null ;
    		
    		Map moduleMap = new HashMap();
    		moduleMap.put("USER_TYPE", userType);
    		moduleMap.put("IS_VALID", isValid);
    		
    		String adminOrgId = sysUserService.getSysAdminOrgId();
    		
    		if(userType.equals("SYS") && isAdmin.equals("1")){
    	    allUserPerm = sysModuleService.retrieveByCondition(moduleMap);
    	    rootPerm = sysModuleService.queryRootPer(moduleMap);
    	    
    	    haveOpers = sysOperationService.retrieveAllOperations();
    	    
    		}
    		if((userType.equals("SYS") && !isAdmin.equals("1")) || 
    		    (!userType.equals("SYS") && isAdmin.equals("1"))){
    		  
    		  moduleMap.put("UUID", userId);
    		  moduleMap.put("ORGAN_UUID", adminOrgId);
    		  allUserPerm = sysModuleService.retrieveAllUserPermByMap(moduleMap);
    		  moduleMap.put("rootperm", "1");
    		  rootPerm = sysModuleService.retrieveAllUserPermByMap(moduleMap);
    		  
    		  Map operMap = new HashMap();
    		  operMap.put("USER_UUID", userId);
    		  operMap.put("ORGAN_UUID", adminOrgId);
    		  operMap.put("IS_VALID", isValid);
    		  
    		  haveOpers = sysOperationService.retrieveAllOperationsByMap(operMap);
    		  
    		}
    		if(!userType.equals("SYS") && !isAdmin.equals("1")){
    		  moduleMap.put("UUID", userId);
    		  moduleMap.put("ORGAN_UUID", orgId);
    		  moduleMap.put("DEPART_UUID", deptId);
    		  allUserPerm = sysModuleService.retrieveOrgUserPermByMap(moduleMap);
    		  moduleMap.put("rootperm", "1");
    		  rootPerm = sysModuleService.retrieveOrgUserPermByMap(moduleMap);
    		  
          Map operMap = new HashMap();
          operMap.put("USER_UUID", userId);
          operMap.put("ORGAN_UUID", orgId);
          operMap.put("DEPART_UUID", deptId);
          operMap.put("IS_VALID", isValid);
          
          haveOpers = sysOperationService.retrieveOperationsByMap(operMap);
    		}
    		
    		
    	    
    		//过滤掉重复的权限模块
    		allUserPerm = filterModule(allUserPerm);
    		rootPerm = filterModule(rootPerm);
    		haveOpers = filterOperations(haveOpers);
    		
    		//创建session
        HttpSession session = getSession();
        
    		// 放入session
        session.setAttribute(Constants.ALL_USER_PERM, allUserPerm);
        session.setAttribute(Constants.ALL_USER_OPER, haveOpers);
        
//        setPermUrlsToSession(newUserPO);
    		
//        cacheManager.addEhCache("permissionCache",userId, allUserPerm);
//        cacheManager.addEhCache("operationCache",userId, haveOpers);
        
    		//过滤掉不显示的模块
    		allUserPerm = filterModuleByIsShow(allUserPerm);
    		rootPerm = filterModuleByIsShow(rootPerm);
    		
    	  //查询当前用户拥有的根权限
    		queryRootPermByUser(allUserPerm,rootPerm);
    		
    		bulidSystemTree(allUserPerm);
    		//拆分一般业务模块和系统模块
    	  splitModules(userRootPerms);
    		
    	  if(userType.equals("SYS")){
    	    showModules = permiModules.addAll(busiModules) ? permiModules : busiModules;
    	  }else{
    	    showModules = busiModules;
    	  }
    	  
    } catch (Exception e) {
      e.printStackTrace();
    }
		return SUCCESS;
	}
	
	
	
	/**
	 * 拆分一般业务模块和系统模块
	 * splitModules
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private void splitModules(List<SysModulePO> userRootPerms) {
	  for (SysModulePO sysModulePO : userRootPerms) {
//	    String menuCode = StringUtil.String2DefVal(sysModulePO.getMENU_CODE());
	    
	    
      if(sysModulePO.getMENU_CODE().endsWith("_QXGLXT")){
        permiModules.add(sysModulePO);
      }else{
        busiModules.add(sysModulePO);
      }
    }
    
  }

  private List<SysOperationPO> filterOperations(List<SysOperationPO> haveOpers) {
	  
    List<SysOperationPO> operList = new ArrayList<SysOperationPO>();
    
    Map allOperMap = new LinkedHashMap();
    
    for (SysOperationPO operationPO : haveOpers) {

      String id = operationPO.getUUID();
      
      if(!allOperMap.containsKey(id)){
        allOperMap.put(id, operationPO);
        
      }
      
    }
    
    operList.addAll(allOperMap.values());
    
    return operList;
  }

  private List<SysModulePO> filterModuleByIsShow(List<SysModulePO> modules) {
	  List<SysModulePO> moduleList = new ArrayList<SysModulePO>();
	  
	  for (SysModulePO sysModulePO : modules) {
	    if(sysModulePO.getIS_SHOW() != null && sysModulePO.getIS_SHOW().equals("10")){
	        moduleList.add(sysModulePO);
	    }
      
    }
    return moduleList;
  }

  private List<SysModulePO> filterModule(List<SysModulePO> modules){
	  List<SysModulePO> retList = new ArrayList<SysModulePO>();
	  Map allMap = new LinkedHashMap();
	  
	  for (int i = 0; i < modules.size(); i++) {
      SysModulePO modulePO = modules.get(i);
      String moduleId= modulePO.getUUID();
      
      if(!allMap.containsKey(moduleId)){
        allMap.put(moduleId, modulePO);
      }
	    
    }
	  
	  retList.addAll(allMap.values());
    return retList;
	  
	}
	
	private void bulidSystemTree(List<SysModulePO> sysModulePOs) {
	  for (SysModulePO userRootPerm : userRootPerms) {
	    JSONArray userSystemPerm = new JSONArray();
	    userSystemPerm = buildTree(userRootPerm,sysModulePOs,userSystemPerm);
	    
	    
	    Map<String,String> map = new HashMap<String, String>();
	    String rootId = userRootPerm.getUUID();
	    map.put("id", rootId);
	    map.put("systemTree", userSystemPerm.toString());
	    
	    systemTreeStrList.add(map);
    }
    
  }


  private JSONArray buildTree(SysModulePO sysModulePO,List<SysModulePO> haveModulePOs ,JSONArray nodes) {
    
    String rootId = sysModulePO.getUUID();
    for (SysModulePO haveModulePO : haveModulePOs) {
      
      String pId  = haveModulePO.getPARENT_UUID();
      String id = haveModulePO.getUUID();
      String name = haveModulePO.getMENU_NAME();
      String url = haveModulePO.getURL();
      url = (url == null || url.equals("#")) ? "" : url;
      String type = haveModulePO.getMENU_TYPE();
      type = type == null ? "" : type;
      String target = "rightFrame";
      
      if(!pId.equals(id) && pId.equals(rootId)){
        JSONObject node = new JSONObject();
        node.put("id", id);
        node.put("pId", pId);
        node.put("name", name);
        
        node.put("href", url);
        node.put("target", target);
        
        
        if(type.equals("10")){
          node.put("iconSkin", "pIcon");
        }else{
          node.put("iconSkin", "cIcon");
        }
        
        nodes.add(node);
        
        buildTree(haveModulePO,haveModulePOs,nodes);
      }
      
    }
    
    return nodes;
    
  }


  private void queryRootPermByUser(List<SysModulePO> modules,List<SysModulePO> rootModules) throws Exception{
	  
	  for (int i = 0; i < rootModules.size(); i++) {
	    SysModulePO rootModule = rootModules.get(i);
	    
	    String rootModuleId = rootModule.getUUID();
	    
	    String rootModuleCode = rootModule.getMENU_CODE();
	    
	    for (int j = 0; j < modules.size(); j++) {
	      SysModulePO modulePO = modules.get(j);
	      String id = modulePO.getUUID();
	      String pId = modulePO.getPARENT_UUID();
//	      String menuCode = modulePO.getMENU_CODE();
	      
//	      Integer ordernum = modulePO.getORDERNUM();
//	      int index = (ordernum == null || ordernum.equals("")) ? 0 : ordernum.intValue();
	      
	      if(rootModuleCode.equals("XT") || rootModuleCode.equals("QY") || rootModuleCode.equals("ZF")){
	        if(pId.equals(rootModuleId) && !id.equals(pId)){
	          userRootPerms.add(modulePO);
	          continue;
	        }
	      }else{
	        
	        if(!id.equals(pId)){
	          continue;
	        }
	        if(id.equals(rootModuleId)){
//          userRootPerms.add(index,modulePO);
	          userRootPerms.add(modulePO);
	        }
	        continue;
	      }
	      
	      
	    }
	  }
	  
	  
	}
  

	
	public String getModulesID() throws Exception {
	  for (SysModulePO p : userRootPerms) {
	    moduleID.put(p.getMENU_NAME(), p.getUUID());
	  }
	  int total = remindService.getRemindTotal(SessionUtil.getUserId());
	  getRequest().setAttribute("total", total);
	  return SUCCESS;
	}
	
	// main right home page
	public String mainRightHome() throws Exception {
//		getModulesID();
//		return Boolean.parseBoolean(getConfig("fourIcon")) ? "fourIcon" : SUCCESS;
	  String resultType="";
	  
	  String userType = SessionUtil.getUserType();
	  
	  if(Constants.SYS.equals(userType)){
	    resultType = "sys";
	  }else if(Constants.ENT.equals(userType)){
	    resultType = "ent";
	  }if(Constants.GOV.equals(userType)){
	    resultType = "gov";
	  }
	  
	  return resultType;
	}
	
	// main right top buttons page
	public String mainRightTopBtns() throws Exception {
		return Boolean.parseBoolean(getConfig("tabs")) ? "tabs" : SUCCESS;
	}
	
	
	
	
	// get config param
	private String getConfig(String name) {
		Map<String,String> map = (Map) ServletActionContext.getServletContext().getAttribute("config");
		return map.get(name);
	}
	

	
  public List<Map<String, String>> getSystemTreeStrList() {
  
    return systemTreeStrList;
  }
  
  public void setSystemTreeStrList(List<Map<String, String>> systemTreeStrList) {
  
    this.systemTreeStrList = systemTreeStrList;
  }
	

	public Map<String, String> getModuleID() {
		return moduleID;
	}

	public void setModuleID(Map<String, String> moduleID) {
		this.moduleID = moduleID;
	}

  
	
  public void setUserRootPerms(List<SysModulePO> userRootPerms) {
  
    this.userRootPerms = userRootPerms;
  }
  
  
  public List<SysModulePO> getUserRootPerms() {
  
    return userRootPerms;
  }
  
  
  public List<SysModulePO> getBusiModules() {
    
    return busiModules;
  }
  
  
  public void setBusiModules(List<SysModulePO> busiModules) {
    
    this.busiModules = busiModules;
  }
  
  
  public List<SysModulePO> getPermiModules() {
    
    return permiModules;
  }
  
  
  public void setPermiModules(List<SysModulePO> permiModules) {
    
    this.permiModules = permiModules;
  }
  
  
  public List<SysModulePO> getShowModules() {
    
    return showModules;
  }
  
  
  public void setShowModules(List<SysModulePO> showModules) {
    
    this.showModules = showModules;
  }
  
  // ----------------- private methods --------------------
  

//  private void setPermUrlsToSession(UserPO userPO) {
//		List<String> permUrls;
//		if (("SYS".equals(userPO.getUSER_TYPE())) && ("1".equals(userPO.getIS_ADMIN())))
//			permUrls = getSysAdminPermUrls();
//		else {
//			permUrls = getOrdinaryPermUrls(userPO.getUUID());
//		}
//		List permModuleUrls = treatPermUrls(permUrls);
//		getSession().setAttribute("permModuleUrl", permModuleUrls);
//	}
	  
//	private List<String> getSysAdminPermUrls() {
//		return sysResourceService.retrieveAllUrl();
//	}
	  
//	private List<String> getOrdinaryPermUrls(String userId) {
//		return sysResourceService.retrieveUrlsByUserId(userId);
//	}
	  
//	private List<String> treatPermUrls(List<String> permUrls) {
//	    List<String> result = new ArrayList<String>();
//	    for (String permUrl : permUrls) {
//	    	permUrl = UrlUtil.delUnnecessaryUrlSuffix(permUrl);
//	    	if (permUrl.contains("?")){
//	    		result.add(permUrl.substring(0, permUrl.indexOf("?")));
//	    	} else {
//	    		result.add(permUrl);
//	    	}
//	    }
//	    return result;
//	}



	public ISysModuleService getSysModuleService() {
		return sysModuleService;
	}



	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}



	public IUserService getSysUserService() {
		return sysUserService;
	}



	public void setSysUserService(IUserService sysUserService) {
		this.sysUserService = sysUserService;
	}



	public ISysOperationService getSysOperationService() {
		return sysOperationService;
	}



	public void setSysOperationService(ISysOperationService sysOperationService) {
		this.sysOperationService = sysOperationService;
	}



	public ISysResourceService getSysResourceService() {
		return sysResourceService;
	}



	public void setSysResourceService(ISysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}



	public IRemindService getRemindService() {
		return remindService;
	}



	public void setRemindService(IRemindService remindService) {
		this.remindService = remindService;
	}
	
	
}
