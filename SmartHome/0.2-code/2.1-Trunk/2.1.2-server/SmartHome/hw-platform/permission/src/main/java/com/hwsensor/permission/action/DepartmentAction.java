package com.hwsensor.permission.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.pojo.DepartmentPO;
import com.hwsensor.permission.service.IDepartmentService;
import com.hwsensor.permission.service.ISysRoleService;

/**
 * 
 * 项目名称：framework
 * 类名称：DepartmentAction
 * 类描述：权限系统的部门管理模块的增删改查等操作。
 * 创建人：孟繁波
 * 创建时间：2012-5-15 下午9:05:36
 * 修改人：孟繁波
 * 修改时间：2012-5-15 下午9:05:36
 * 修改备注：
 * @version 
 * 
 */
public class DepartmentAction extends BaseAction {
	@Autowired
	private IDepartmentService iDepartmentService;
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	private String pId;
	private DepartmentPO deptPO;
  private String[] roleArray;	
	/**
	 * childrenList:根据父ID查询出的所有子部门
	 */
	private List<DepartmentPO> childrenList;
	private String zTreeNodesStr;

	private List roleList = new ArrayList();
	
	/**
	 * 查询出所有部门
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public String doList() {
		try {
		   // 处理传参pId
	    String _pId = request.getParameter("pId");
	    String pId = (_pId == null || _pId.isEmpty())? "0" : _pId;  
	    String organ_uuid=SessionUtil.getOrgId();
	    
	    // 设置deptPO
	    if (pId.equals("0")) {
	      if (deptPO == null) {
	        deptPO = new DepartmentPO();
	      }
	      deptPO.setDepart_name("");
	    } else {
	      deptPO = iDepartmentService.retrieveDepartmentByDeptId(pId);
	    }
	    HashMap<String, String> map=new HashMap<String, String>();
	    map.put("pId", pId);
	    map.put("ogrgan_uuid", organ_uuid);
	    
	    // 查询子部门
	    childrenList = (pId.equals("0")) ? iDepartmentService.retrieveAllRootDept(organ_uuid) : iDepartmentService.retrieveDepartmentsByParentId(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String doLoadTree(){
		try {
			zTreeNodesStr = iDepartmentService.retrieveDeptTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "depttree";
	}
	
	public String queryData() throws Exception {
		// 分页信息
		pagerData =  initPagerData();
		pId = (request.getParameter("pId") == null || request.getParameter("pId").isEmpty() || request.getParameter("pId").equals("0")? "" : request.getParameter("pId"));
		if(deptPO == null){
			deptPO = new DepartmentPO();
		}
		deptPO.setParent_uuid(pId);
		// 参数Map
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("organ_uuid", SessionUtil.getOrgId());
		paramMap.put("po", deptPO); // 查询条件
		paramMap.put("pagerData", pagerData);
		
		
		// 根据条件查询分页数据
		pagerData.setRows(iDepartmentService.retrieveByPage(paramMap));
		
		// 根据条件查询总条数
		paramMap.put("selectCount", true);
		pagerData.setRecords(iDepartmentService.retrieveByPage(paramMap));
		
		return JSON_PAGER;
	}
	
	
	/**
	 * 添加部门
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public String doSaveAdd() throws Exception {
		try{
		    // 设置deptPO
		    String uuid = UUIDGenerater.getUUID();
		    deptPO.setUuid(uuid);
		    String pId = deptPO.getParent_uuid();
		    deptPO.setParent_uuid(StringUtils.isBlank(pId) ? uuid : pId);
		    if(StringUtils.isNotBlank(deptPO.getPrekey())){
		      deptPO.setDepart_code(deptPO.getPrekey()+deptPO.getDepart_code());
		    }
		    HashMap<String, String> map=new HashMap<String, String>();
		    map.put("name", deptPO.getDepart_name());
		    map.put("corpid", SessionUtil.getOrgId());
		    UserMessageData messageData =  iDepartmentService.addDepartment(map,deptPO);
		    if(messageData == null || messageData.getContent().isEmpty()){
		      setAddSuccessMsg();
		    }else{
		      message = messageData;
		    }
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	

	public String delBatch(){
		try{	
		    String ids = request.getParameter("ids");
		    String[] idsArr = ids.split(",");
		    HashMap<String, String[]> map=new HashMap<String, String[]>();
		    map.put("ids", idsArr);
		    UserMessageData messageData = iDepartmentService.delBatch(map);
		    if(messageData == null || messageData.getContent().isEmpty()){
		      setDelSuccessMsg();
		    }else{
          message = messageData;
        }
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
	return JSON_MSG;
	}
	
	/**
	 * 更新部门
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public String doSaveEdit(){
		try{
	    HashMap<String, String> map=new HashMap<String, String>();
	    map.put("name", deptPO.getDepart_name());
	    map.put("corpid", SessionUtil.getOrgId());
	    map.put("id", deptPO.getUuid());
			UserMessageData messageData = iDepartmentService.updateDepartment(map,deptPO);
			if(messageData == null || messageData.getContent().isEmpty()){
			  setUpdateSuccessMsg();
			}else{
        message = messageData;
      }
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 验证部门编码的唯一性
	 */
	public void checkOnlyKey(){
		try {
			String deptKey=request.getParameter("deptKey");
			Integer records=iDepartmentService.countDeptByKey(deptKey);
			if(records==0){
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(true);
				return;
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过部门编码查询一条部门信息
	 * @return
	 */
	public String doQueryDeptByKey(){
		try{
			deptPO = iDepartmentService.retrieveDepartmentByKey(deptPO.getDepart_code());
			setJsonPO(deptPO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 通过部门ID查询一条部门信息
	 * @return
	 */
	public String doQueryDeptById(){
	  try{
	    deptPO = iDepartmentService.retrieveDepartmentByDeptId(deptPO.getUuid());
	    setJsonPO(deptPO);
	  }catch (Exception e) {
	    e.printStackTrace();
	  }
	  return JSON_PO;
	}
	
	public String doAdd(){
	  
		return "add";
	}
	
	public String doEdit(){
		try {
			deptPO = iDepartmentService.retrieveDepartmentByDeptId(deptPO.getUuid());
			setJsonPO(deptPO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	public String doView(){
	   try {
	      deptPO = iDepartmentService.retrieveDepartmentByDeptId(deptPO.getUuid());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "view";
	}
	/**
	 * 跳转到部门授权页面
	 * @return
	 * @throws Exception
	 */
	public String doAssignDeptRole()throws Exception{
		String id=request.getParameter("id");
		System.out.println(id);
		deptPO = iDepartmentService.retrieveDepartmentByDeptId(id);
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("ORGAN_UUID", this.getSessionUserPO().getCorpid());
		roleList=sysRoleService.retrieveByCondition(map);
		// 查询部门已有的角色信息
		List deptroleList=iDepartmentService.retrieveDeptRole(deptPO.getUuid());
		if(deptroleList.size()>0){
			int i=0;
			roleArray= new String[deptroleList.size()];
			for (Object deptMap : deptroleList) {
				Map<String, String>demap = (Map<String, String>) deptMap;
				roleArray[i++] = demap.get("ROLE_UUID");
			}
		}
		return "deptrole";
	}
	
	/**
	 * 部门角色授权
	 * @return
	 * @throws Exception
	 */
	public String doSaveAssigndeptEmpower()throws Exception{
		try {
			String id=request.getParameter("deptid");
			String roleids=request.getParameter("roleids");
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("deptid", id);
			map.put("roleids", roleids);
			map.put("creator", this.getSessionUserPO().getId());
			UserMessageData messageData = iDepartmentService.deptEmpower(map);
			if(messageData == null || messageData.getContent().isEmpty()){
			  message.set(Constants.MSG_OK, "授权成功", "信息");
			}else{
        message = messageData;
      }
		} catch (Exception e) {
			e.printStackTrace();
			message.set(Constants.MSG_ERROR, "授权失败","错误");
		}
		return JSON_MSG;
	}
	
	/*
	 * Getters, Setters
	 */

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public DepartmentPO getDeptPO() {
		return deptPO;
	}

	public void setDeptPO(DepartmentPO deptPO) {
		this.deptPO = deptPO;
	}

	public List<DepartmentPO> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<DepartmentPO> childrenList) {
		this.childrenList = childrenList;
	}

	public String getZTreeNodesStr() {
		return zTreeNodesStr;
	}

	public void setZTreeNodesStr(String zTreeNodesStr) {
		this.zTreeNodesStr = zTreeNodesStr;
	}

	public String[] getRoleArray() {
		return roleArray;
	}

	public void setRoleArray(String[] roleArray) {
		this.roleArray = roleArray;
	}

	public IDepartmentService getiDepartmentService() {
		return iDepartmentService;
	}

	public void setiDepartmentService(IDepartmentService iDepartmentService) {
		this.iDepartmentService = iDepartmentService;
	}

	public ISysRoleService getSysRoleService() {

		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {

		this.sysRoleService = sysRoleService;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getRoleList() {
		return roleList;
	}
	
}
