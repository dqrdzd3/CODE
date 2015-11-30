/**
 * 文件名：DepartmentServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-5-10
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.dao.IDepartmentDao;
import com.hwsensor.permission.pojo.DepartmentPO;
import com.hwsensor.permission.service.IDepartmentService;

/**
 * 
 * 项目名称：framework
 * 类名称：DepartmentServiceImpl
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-5-10 上午11:13:45
 * 修改人：孟繁波
 * 修改时间：2012-5-10 上午11:13:45
 * 修改备注：
 * @version 
 * 
 */
public class DepartmentServiceImpl extends BaseServiceImpl implements IDepartmentService  {
	private static Logger log = Logger.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	public IDepartmentDao iDepartmentDao;

	public void insertDepartment(DepartmentPO departmentPO) throws Exception {
		iDepartmentDao.insertDepartment(departmentPO);
	}

	public void updateDepartmentByPO(DepartmentPO departmentPO) throws Exception {
		iDepartmentDao.updateDepartment(departmentPO);
	}

	public DepartmentPO retrieveDepartmentByDeptId(String deptId) throws Exception {
		DepartmentPO deptPo=iDepartmentDao.retrieveDepartmentByDeptId(deptId);
		if(deptPo == null){
			return new DepartmentPO();
		}
			
		if(deptPo.getUuid().equals(deptPo.getParent_uuid())){
			deptPo.setPrekey("");
		}else{
			DepartmentPO parentDeptPo=iDepartmentDao.retrieveDepartmentByDeptId(deptPo.getParent_uuid());
			String parentKey=parentDeptPo.getDepart_code();
			String key=deptPo.getDepart_code();
			if(key.startsWith(parentKey)){
				String preKey=key.substring(0,parentKey.length()+1);
				key=key.substring(parentKey.length()+1);
				deptPo.setPrekey(preKey);
				deptPo.setDepart_code(key);
			}
		}
		return deptPo;
	}

	public List<DepartmentPO> retrieveAllDepartment() throws Exception {
		return iDepartmentDao.retrieveAllDepartment();
	}
	
	public List<DepartmentPO> retrieveAllDepartmentByCorpID(String id) throws Exception {
		return iDepartmentDao.retrieveAllDepartmentByCorpID(id);
	}

	public void deleteDepartmentByDeptId(String deptId) throws Exception {
		iDepartmentDao.deleteDepartmentByDeptId(deptId);
	}

	public List<DepartmentPO> retrieveDepartmentsByParentId(HashMap<String, String> map) throws Exception {
		return iDepartmentDao.retrieveDepartmentsByParentId(map);
	}

	public List<DepartmentPO> retrieveDepartmentsByParentId(String map) throws Exception {
		return iDepartmentDao.retrieveDepartmentsByParentId(map);
	}

	public List<DepartmentPO> retrieveAllRootDept(String organ_uuid) throws Exception {
		return iDepartmentDao.retrieveAllRootDept(organ_uuid);
	}

	public void deleteDepartmentsByParentId(String parentId) {
	}
	
	
	/**
	 * buildeNodeStr 构建部门树字符串
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public String buildeNodeStr(List<DepartmentPO> list) {
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("[");
		for (DepartmentPO po : list) {
			String id = StringUtils.isBlank(po.getUuid()) ? "" : po.getUuid();
			String parentId = StringUtils.isBlank(po.getParent_uuid()) ? "" : po.getParent_uuid();
			String name = StringUtils.isBlank(po.getDepart_name()) ? "" : po.getDepart_name();
			String deptCode = StringUtils.isBlank(po.getDepart_code()) ? "" : po.getDepart_code(); 
			sbuff.append("{id:'" + id + "', pId:'" + parentId + "', name:'" + name + "', code:'" + deptCode + "', iconSkin:'pIcon'},");
		}
		if(sbuff.length()>1){
			sbuff.deleteCharAt(sbuff.length()-1);
		}
		return sbuff.append("]").toString();
	}
	
	public UserMessageData addDepartment(HashMap<String,String> map,DepartmentPO deptPO) throws Exception {
  
    UserMessageData messageData = new UserMessageData();
    int j = iDepartmentDao.checkDeptName(map);
    if (j >= 1) {
      messageData.set(Constants.MSG_INFO, "部门名称已使用！");
    } else {
      int i = iDepartmentDao.countDeptByKey(deptPO.getDepart_code());
      if (i > 0) {
        messageData.set(Constants.MSG_INFO, "部门编码已使用！");
      } else {
        deptPO.setCreator(SessionUtil.getUserId());
        deptPO.setOrgan_uuid(SessionUtil.getOrgId());
        insertDepartment(deptPO);
        Map deptMap = new HashMap();
        deptMap.put("deptPO", deptPO);
        messageData.set(Constants.MSG_OK, "添加成功！", deptMap);
      }
    }
    return messageData;
	}
	
	public UserMessageData updateDepartment(HashMap<String, String> map ,DepartmentPO deptPO) throws Exception {
    UserMessageData messageData = new UserMessageData();
    DepartmentPO oldDepartmentPO = iDepartmentDao.retrieveDepartmentByDeptId(map.get("id"));
    if (oldDepartmentPO == null) {
      messageData.set(Constants.MSG_INFO, "该记录不存在！");
    }
    int j = iDepartmentDao.checkDeptName(map);
    if (j >= 1) {
      messageData.set(Constants.MSG_INFO, "部门名称已使用");
    } else {
      updateDepartmentByPO(deptPO);
      Map deptMap = new HashMap();
      deptMap.put("deptPO", deptPO);
      messageData.set(Constants.MSG_OK, "修改成功！", deptMap);
    }
		return messageData;
	}	
	
	public List<Map<String,Object>> retrieveDepartments(Map map) throws Exception {
		return iDepartmentDao.retrieveDepartments(map);
	}
	
	public int retrieveCounts(Map map) throws Exception {
		return iDepartmentDao.retrieveCounts(map);
	}

	/**
	 * 查询构造部门树的数据
	 */
	public String retrieveDeptTree() throws Exception {
		
		// 查询所有部门
		String corpId = SessionUtil.getOrgId();
		Map map = new HashMap();
		map.put("corpId", corpId);
		String zTreeNodesStr = buildTree(map);
		return zTreeNodesStr;
	}
	
	public String buildTree(Map map) throws Exception{
		String nodeStr = "";
		String corpId = map.get("corpId") == null ? "" : map.get("corpId").toString() ;
		List<DepartmentPO> depts = retrieveAllDepartmentByCorpID(corpId);
		nodeStr = buildeNodeStr(depts);
		return nodeStr;
	}
	
	/**
	 * 通过部门编码查询唯一一条部门信息
	 */
	public DepartmentPO retrieveDepartmentByKey(String fullkey)throws Exception{
		DepartmentPO deptPo=iDepartmentDao.retrieveDepartmentByFullkey(fullkey);
		if(deptPo == null){
			return new DepartmentPO();
		}
			
		if(deptPo.getUuid().equals(deptPo.getParent_uuid())){
			deptPo.setPrekey("");
		}else{
			DepartmentPO parentDeptPo=iDepartmentDao.retrieveDepartmentByDeptId(deptPo.getParent_uuid());
			String parentKey=parentDeptPo.getDepart_code();
			String key=deptPo.getDepart_code();
			if(key.startsWith(parentKey)){
				String preKey=key.substring(0,parentKey.length()+1);
				key=key.substring(parentKey.length()+1);
				deptPo.setPrekey(preKey);
				deptPo.setDepart_code(key);
			}
		}
		return deptPo;
	}
	
	public int countDeptByKey(String key)throws Exception{
		return iDepartmentDao.countDeptByKey(key);
	};
	
	/**
	 * 批量删除部门信息
	 */
	public UserMessageData delBatch(HashMap<String, String[]> map)throws Exception{
//		部门下存在用户不允许删除部门	
		   UserMessageData message=new UserMessageData();
				
				Integer i=iDepartmentDao.retrieveUsernum(map);
				if(i>0){
					message.set(Constants.MSG_INFO, "选中的部门下存在用户，请确保部门下无用户！");
					
				}else{
					// 删除部门角色信息
					iDepartmentDao.delDepRole(map);
					// 删除部门数据
					iDepartmentDao.delBatch(map);
					message.set(Constants.MSG_OK, "删除成功！",map);
				}
			return message;
	}
	
	/**
	 *部门角色授权
	 * @param map
	 * @throws Exception
	 */
	public UserMessageData deptEmpower(HashMap<String, String> map)throws Exception{
	  UserMessageData messageData = new UserMessageData();
		// 首先删除该部门的所有角色授权
		String depart_uuid=map.get("deptid");
		
		String[] roleIdArray= map.get("roleids").isEmpty()?null:map.get("roleids").split("\\|");
		iDepartmentDao.delDepRoleEmpower(depart_uuid);
		// 然后重新添加角色授权
		HashMap<String, String> parammap =new HashMap<String, String>();
		parammap.put("deptid",depart_uuid);
		parammap.put("creator", map.get("creator"));
		// 添加角色授权
		if(roleIdArray!=null){
			for(String id:roleIdArray){
				parammap.put("uuid",UUIDGenerater.getUUID());
				parammap.put("roleid", id);
				iDepartmentDao.deptEmpower(parammap);
			}
		}
		return messageData;
	}
	
	public List retrieveDeptRole(String uuid) throws Exception {
		return iDepartmentDao.retrieveDeptRole(uuid);
	}

	@Override
	public String insertDept(DepartmentPO departmentPO) throws Exception {
	    String uuid = UUIDGenerater.getUUID();
	    departmentPO.setUuid(uuid);
	    String pId = departmentPO.getParent_uuid();
	    departmentPO.setParent_uuid(StringUtils.isBlank(pId) ? uuid : pId);
	    if(StringUtils.isNotBlank(departmentPO.getPrekey())){
	    	departmentPO.setDepart_code(departmentPO.getPrekey()+departmentPO.getDepart_code());
	    }
	    HashMap<String, String> map=new HashMap<String, String>();
	    map.put("name", departmentPO.getDepart_name());
	    map.put("corpid", SessionUtil.getOrgId());
	    
	    int j = iDepartmentDao.checkDeptName(map);
	    if (j >= 1) {
	      log.warn("部门名称["+departmentPO.getDepart_name()+"]使用！");
	      return null;
	    } else {
	      int i = iDepartmentDao.countDeptByKey(departmentPO.getDepart_code());
	      if (i > 0) {
	          log.warn("部门编码["+departmentPO.getDepart_code()+"]已使用！");
	          return null;
	      } else {
	        departmentPO.setCreator(SessionUtil.getUserId());
	        departmentPO.setOrgan_uuid(SessionUtil.getOrgId());
	        insertDepartment(departmentPO);
	        return uuid;
	      }
	    }
		
	}

	@Override
	public boolean updateDept(DepartmentPO departmentPO) throws Exception {
	    HashMap<String, String> map=new HashMap<String, String>();
	    map.put("name", departmentPO.getDepart_name());
	    map.put("corpid", SessionUtil.getOrgId());
	    map.put("id", departmentPO.getUuid());
		
	    DepartmentPO oldDepartmentPO = iDepartmentDao.retrieveDepartmentByDeptId(map.get("id"));
	    if (oldDepartmentPO == null) {
	    	log.warn("部门已不存在！");
	    	return false;
	    }
	    int j = iDepartmentDao.checkDeptName(map);
	    if (j >= 1) {
	    	log.warn("部门名称已被使用！");
	    	return false;
	    } else {
	      updateDepartmentByPO(departmentPO);
	      Map deptMap = new HashMap();
	      deptMap.put("deptPO", departmentPO);
	      return true;
	    }
	}

	@Override
	public boolean deleteDepts(String[] ids) throws Exception {
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		map.put("ids", ids);
		Integer i=iDepartmentDao.retrieveUsernum(map);
		if(i>0){
//			message.set(Constants.MSG_INFO, "选中的部门下存在用户，请确保部门下无用户！");
			log.warn("选中的部门下存在用户，请确保部门下无用户！");
			return false;
		}else{
			// 删除部门角色信息
			iDepartmentDao.delDepRole(map);
			// 删除部门数据
			iDepartmentDao.delBatch(map);
//			message.set(Constants.MSG_OK, "删除成功！",map);
			return true;
		}
	}

}
