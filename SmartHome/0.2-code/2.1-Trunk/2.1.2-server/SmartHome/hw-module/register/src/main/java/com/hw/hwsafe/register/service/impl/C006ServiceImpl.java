/**
 * 文件名：C006ServiceImpl.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.dao.IB001Dao;
import com.hw.hwsafe.corpinfo.dao.IC007Dao;
import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.cpnyreg.dao.IC001Dao;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.ICodeSequenceService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.register.constants.RegisterConstants;
import com.hw.hwsafe.register.dao.IC006Dao;
import com.hw.hwsafe.register.pojo.C006PO;
import com.hw.hwsafe.register.service.IC006Service;
import com.hw.hwsafe.sms.czd.outer.ISendMessage;
import com.hw.hwsafe.sms.czd.outer.impl.SendMessageImpl;
import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.utils.*;
import com.hwsensor.permission.dao.*;
import com.hwsensor.permission.pojo.*;

/**
 * 
 * 
 * 项目名称：hw-register 类名称：C006ServiceImpl 类描述： 创建人：李玉梅 创建时间：2012-12-21 下午5:07:39
 * 修改人：李玉梅 修改时间：2012-12-21 下午5:07:39 修改备注： wwwwwwwwwewerrrr
 * 
 * @version
 * 
 */
public class C006ServiceImpl extends BaseServiceImpl implements IC006Service {

	@Autowired
	private IC006Dao c006Dao;
	@Autowired
	private IC001Dao c001Dao;
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IB001Dao b001Dao;

	@Autowired
	private ISysRoleDao roleDao;
	@Autowired
	private ISysUserRoleDao userRoleDao;

	@Autowired
	private ISysRolePermDao rolePermDao;
	@Autowired
	private ISysModuleDao moduleDao;
	@Autowired
	private ISysOperationDao operationDao;

	@Autowired
	private ICodeSequenceService codeService;

	@Autowired
	private IC007Dao c007Dao;

	@Override
	public List<C006PO> retrieveAllC006() throws Exception {
		return c006Dao.retrieveAllC006();
	}

	@Override
	public C006PO retrieveC006ByID(String c006Id) throws Exception {
		return c006Dao.retrieveC006ByID(c006Id);
	}

	@Override
	public List<C006PO> retrieveC006ByPO(C006PO c006po) throws Exception {
		return c006Dao.retrieveC006ByPO(c006po);
	}

	/**
	 * 注册的时候，新增C006一条记录，同时新增s_user，B001一条记录
	 * 
	 * 李玉梅修改于2013年9月13日：实现注册成功之后，直接登录系统。不再往C001表中新增记录
	 */
	@Override
	public UserMessageData insertC006(C006PO c006po) throws Exception {

		UserMessageData message = new UserMessageData();
		c006po.setMa001(UUIDGenerater.getUUID());
		c006po.setMa016(DateTimeUtils.getCurrentDate());
		c006po.setMa020(C006PO.STATUS_REG);// 新增是注册状态
		c006po.setMa021(C006PO.MA021_VALID);// 新增是有效状态
		String strCode = C006PO.CORPCODE_PRE;// 编码规则为ZK+行业类型编号+
		C007PO c007po = new C007PO();
		String strCodeStr = "";// 中间变量：行业编码
		String hyStr = "";
		if (c006po.getHymlID() != "" && !c006po.getHymlID().isEmpty()) {// 如果行业门类不为空，则添加行业门类，根据ＩＤ，获得对应的值
			c007po = c007Dao.retrieveC007ById(c006po.getHymlID());
			strCode = strCode + c007po.getValue();
			hyStr += c007po.getValue();
			strCodeStr=hyStr;
		} else {
			strCodeStr = strCode;
		}
		
		/**
		// 拼接行业类型字符串以“C_15_151_153”的形式
		if (c006po.getHymlID() != "" && !c006po.getHymlID().isEmpty()) {// 如果行业门类不为空，则添加行业门类，根据ＩＤ，获得对应的值
			c007po = c007Dao.retrieveC007ById(c006po.getHymlID());
			strCode = strCode + c007po.getValue();
			hyStr += c007po.getValue();
		} else {
			strCodeStr = strCode;
		}

		if (c006po.getHydlID() != null && !c006po.getHydlID().isEmpty()) {// 如果行业大类不为空，则添加行业大类
			c007po = c007Dao.retrieveC007ById(c006po.getHydlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}

		if (c006po.getHyzlID() != null && !c006po.getHyzlID().isEmpty()) {// 如果行业中类不为空，则添加行业中类
			c007po = c007Dao.retrieveC007ById(c006po.getHyzlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}

		if (c006po.getHyxlID() != "" && !c006po.getHyxlID().isEmpty()) {// 如果行业小类不为空，则添加行业小类
			c007po = c007Dao.retrieveC007ById(c006po.getHyxlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}
		**/
		
		// 对企业编号进行编码
		String corpCode = strCodeStr
				+ codeService.retrieveSequence(strCodeStr, 9);

		c006po.setMa009(corpCode);
		String oldPassword = c006po.getMa011();
		c006po.setOldPassword(oldPassword);
		String md5Pwd = PasswordUtil.createPassword(oldPassword);// 对密码进行M5加密
		c006po.setMa011(md5Pwd);

		int optRecNum = c006Dao.insertC006(c006po);

		// 注册成功后，往s_user，b001表中添加记录；
		if (optRecNum == 1) {
			String corpID = UUIDGenerater.getUUID();
			
			//往企业注册表C001表中添加一条记录
			C001PO c001po = new C001PO();
			c001po.setMA001(corpID);
			c001po.setMA002(c006po.getMa002());// 企业名称
			c001po.setMA003(c006po.getMa003());// 机构代码
			c001po.setMA004(c006po.getZgdwid());//主管单位ID，
			c001po.setMA005(c006po.getMa013());// 负责人电话号码
			c001po.setMA006(c006po.getMa014());// 负责人手机
			c001po.setMA007(c006po.getMa015());// 邮箱
			c001po.setMA008(C001PO.MA008_VALID);//注册添加后，默认状态为无效
			c001po.setMA009(C006PO.USER_NUM);//同时在线人数默认为5
			c001po.setMA011("system");// 注册的时候没有创建人，暂定为系统：system
			c001po.setMA012(DateTimeUtils.getCurrentDate());// 创建日期
			c001po.setMA017(C001PO.MA017_VALID);//删除标识为有效
			c001Dao.insertC001(c001po);
			

			// 插入一条企业基本信息表B001
			B001PO b001po = new B001PO();
			b001po.setMA001(corpID);// 企业ID就是C001表中的ID
			b001po.setMA002(c006po.getMa009());// 企业编号
			b001po.setMA003(c006po.getMa003());// 组织机构代码
			b001po.setMA004(c006po.getMa002());// 企业名称
			b001po.setMA007("001");// 省地市默认，
			b001po.setMA008("00102");
			b001po.setMA009("0010201");
			b001po.setMA010(c006po.getMa007());// 经济类型
			b001po.setMA011(hyStr);// 行业类型:拼接后的行业类型字符串赋给B001中的Ma011，行业类型
			b001po.setMA048(c006po.getMa012());// 安全负责人
			// b001po.setMA049(c006po.getMa014());//安全负责人电话
			b001po.setMA028("system");// 填报人ＩＤ,默认为系统录入
			b001po.setMA051(c006po.getQylx());
			b001po.setMA053(c006po.getLsgx());
			// b001po.setMA020(c006po.getMa015());//企业Ｅｍａｉｌ
			b001po.setMA031(DateTimeUtils.getCurrentDate());
			b001po.setMA039("system");
			b001po.setMA080("0");//未上报
			b001po.setMA069(StringUtil.toJP(c006po.getMa002(), 1));// 拼音代码
			b001Dao.insertB001(b001po);

			// 插入一条用户记录
			UserPO userpo = new UserPO();
			String userIDString = UUIDGenerater.getUUID();
			userpo.setUUID(userIDString);
			userpo.setLoginName(c006po.getMa010());// 登陆用户名
			userpo.setPassword(c006po.getMa011());// 登陆密码
			userpo.setMOBILE_NUMBER(c006po.getMa014());// 联系方式
			userpo.setEMAIL(c006po.getMa015());// 邮箱
			userpo.setUSER_TYPE(UserPO.USER_TYPE_ENT);// 默认是企业端用户
			userpo.setIS_ADMIN(UserPO.IS_ADMIN_Y);// 默认注册的用户名即为管理人员
			userpo.setUSER_STATUS(UserPO.USER_STATUS_KT);// 默认注册添加后是禁用状态
			userpo.setCREATOR("system");// 审核人ID，就当是创建人
			userpo.setCREATE_DATE(DateTimeUtils.getCurrentDate());// 创建日期
			userpo.setORGAN_UUID(corpID);// 注册的企业ＩＤ，即管理人员的组织ID
			userpo.setMOBILE_DEVICE_ACCESS("0");// 数量默认为0
			userpo.setOUTER_NET_ACCESS("0");
			userpo.setREAL_NAME(c006po.getMa012());// 负责人姓名,真实姓名
			userDao.insertSysUser(userpo);

			// ////////////////////////////////////////////////////////////////李玉梅修改与2013年9月13日。注册后不需审核，即可登录
			//即把企业的角色赋给所有注册的企业管理人员。因为郑州市安监局的业务是所有企业具有的角色都是一样的，所有可以赋一个固定值。
			
			SysUserRolePO userRolePO = new SysUserRolePO();
			String userRoleID = UUIDGenerater.getUUID();
			userRolePO.setUUID(userRoleID);
			userRolePO.setUSER_UUID(userIDString);
			userRolePO.setROLE_UUID("c909c4e6-1245-41c5-9b8a-42729fcda0e9");
			// userRolePO.setCREATOR(creator);
			// userRolePO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
			userRoleDao.insertSysUserRole(userRolePO);
			
	/**		
			
			// 插入一条角色记录
			SysRolePO rolePO = new SysRolePO();
			String roleID = "c909c4e6-1245-41c5-9b8a-42729fcda0e9";
			String roleName = c006po.getMa002() + "管理员";// 角色名称为企业名称—+管理员
			rolePO.setUUID(roleID);
			rolePO.setROLE_NAME(roleName);
			rolePO.setUSER_TYPE(SysRolePO.USER_TYPE_ENT);
			rolePO.setREMARK("企业管理员");
			String organ_uuid = userDao.getSysAdminOrgId();
			rolePO.setORGAN_UUID(organ_uuid);
		//	rolePO.setCREATOR(creator);
		//	rolePO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
			roleDao.insertSysRole(rolePO);
			// 插入用户角色关系表
			SysUserRolePO userRolePO = new SysUserRolePO();
			String userRoleID = UUIDGenerater.getUUID();
			userRolePO.setUUID(userRoleID);
			userRolePO.setUSER_UUID(userIDString);
			userRolePO.setROLE_UUID(roleID);
			// userRolePO.setCREATOR(creator);
			// userRolePO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
			userRoleDao.insertSysUserRole(userRolePO);

			List<SysModulePO> moduleList = new ArrayList<SysModulePO>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("USER_TYPE", "ENT");
			map.put("IS_VALID", "10");
			
			moduleList = moduleDao.retrieveByCondition(map);

			for (int i = 0; i < moduleList.size(); i++) {
				SysModulePO sysModulePO = moduleList.get(i);
				String modeleID = sysModulePO.getUUID();

				// 插入角色模块关系映射表:角色和模块
				SysRolePermPO rolePermPO = new SysRolePermPO();
				rolePermPO.setUUID(UUIDGenerater.getUUID());
				rolePermPO.setROLE_UUID(roleID);
				rolePermPO.setORGAN_UUID(organ_uuid);
				// rolePermPO.setCREATOR(creator);
				// rolePermPO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
				rolePermPO.setMODULE_UUID(modeleID);
				rolePermDao.insertSysRolePerm(rolePermPO);

				List<SysOperationPO> optList = new ArrayList<SysOperationPO>();
				Map<String, String> optmap = new HashMap<String, String>();
				optmap.put("MENU_UUID", modeleID);
				optmap.put("IS_VALID", "10");
				optList = operationDao.retrieveByCondition(optmap);

				for (int j = 0; j < optList.size(); j++) {
					SysOperationPO operationPO = optList.get(j);
					String optID = operationPO.getUUID();

					// 插入角色模块关系映射表：角色和操作
					SysRolePermPO rolePermPO2 = new SysRolePermPO();
					rolePermPO2.setUUID(UUIDGenerater.getUUID());
					rolePermPO2.setROLE_UUID(roleID);
					rolePermPO2.setORGAN_UUID(organ_uuid);
					// rolePermPO2.setCREATOR(creator);
					// rolePermPO2.setCREATE_DATE(DateTimeUtils
					// .getCurrentDate());
					rolePermPO2.setOPERA_UUID(optID);
					rolePermDao.insertSysRolePerm(rolePermPO2);

				}
			}
			**/
			message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		} else {
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return message;

	}

	@Override
	public UserMessageData updateC006(C006PO c006po) throws Exception {
		UserMessageData message = new UserMessageData();
		String ma003OldStr = c006po.getOldMa003();// 记录修改之前的组织机构代码

		String strCode = C006PO.CORPCODE_PRE;// 编码规则为ZK+行业类型编号+
		C007PO c007po = new C007PO();
		String strCodeStr = "";
		// 拼接行业类型字符串
		String hyStr = "";
		if (c006po.getHymlID() != "" && !c006po.getHymlID().isEmpty()) {// 如果行业门类不为空，则添加行业门类，根据ＩＤ，获得对应的值
			c007po = c007Dao.retrieveC007ById(c006po.getHymlID());
			strCode = strCode + c007po.getValue();
			hyStr += c007po.getValue();
		} else {
			strCodeStr = strCode;
		}

		if (c006po.getHydlID() != null && !c006po.getHydlID().isEmpty()) {// 如果行业大类不为空，则添加行业大类
			c007po = c007Dao.retrieveC007ById(c006po.getHydlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}

		if (c006po.getHyzlID() != null && !c006po.getHyzlID().isEmpty()) {// 如果行业中类不为空，则添加行业中类
			c007po = c007Dao.retrieveC007ById(c006po.getHyzlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}

		if (c006po.getHyxlID() != "" && !c006po.getHyxlID().isEmpty()) {// 如果行业小类不为空，则添加行业小类
			c007po = c007Dao.retrieveC007ById(c006po.getHyxlID());
			strCodeStr = strCode + c007po.getValue();
			hyStr = hyStr + "-" + c007po.getValue();
		}
		// 对企业编号进行编码
		String corpCode = strCodeStr
				+ codeService.retrieveSequence(strCodeStr, 4);
		c006po.setMa009(corpCode);
		// 如果是因为审核未通过而进行的修改，则编辑后应该修改状态为注册状态
		if (c006po.getMa020().equals(C006PO.STATUS_NO)) {
			c006po.setMa020(C006PO.STATUS_REG);// 编辑的时候把为通过审核的状态改为注册状态
		}
		int optRecNum = c006Dao.updateC006(c006po);
		if (optRecNum == 1) {
			// 审核通过后修改C001表中的记录
			C001PO c001po = new C001PO();
			String c001IDString = null;
			c001po = c001Dao.queryC001POByMa003(ma003OldStr);// //根据组织机构代码，查询C001PO：根据老的组织机构代码进行查询PO
			if (c001po != null) {
				c001IDString = c001po.getMA001();// 获得c001表中的ＩＤ
				c001po.setMA002(c006po.getMa002());// 企业名称
				c001po.setMA003(c006po.getMa003());// 机构代码：把新的组织机构代码赋值
				c001po.setMA007(c006po.getMa015());// 邮箱
				c001po.setMA008(C001PO.MA008_VALID);// 审核通过设置状态为有效
				c001po.setMA017(C001PO.MA017_VALID);// 删除状态为有效
				c001Dao.updateC001(c001po);// 更新C001表记录
			}
			// 更新s_user表
			UserPO userPO = new UserPO();
			userPO = userDao.retrieveUserByLoginName(c006po.getMa010());// 根据用户名进行查询
			if (userPO != null) {
				userPO.setUSER_STATUS(UserPO.USER_STATUS_KT);// 审核通过后，设置该用户的状态为“开通”状态
				// 同时更新用户UserPO的其他字段
				userPO.setLoginName(c006po.getMa010());// 登陆用户名
				userPO.setPassword(c006po.getMa011());// 登陆密码
				userPO.setMOBILE_NUMBER(c006po.getMa014());// 联系方式
				userPO.setEMAIL(c006po.getMa015());// 邮箱
				userPO.setREAL_NAME(c006po.getMa012());// 负责人姓名,真实姓名
				userDao.updateSysUser(userPO);// 更新userPO
			}
			// 更新一条企业基本信息表
			B001PO b001po = b001Dao.retrieveB001ByID(c001IDString);
			if (b001po != null) {
				b001po.setMA002(c006po.getMa009());// 企业编号
				b001po.setMA003(c006po.getMa003());// 组织机构代码
				b001po.setMA004(c006po.getMa002());// 企业名称
				b001po.setMA010(c006po.getMa007());// 经济类型
				b001po.setMA011(hyStr);// 行业类型：把拼接后的行业类型赋给B001表中的ma011，
				b001po.setMA048(c006po.getMa012());// 安全负责人
				b001po.setMA049(c006po.getMa014());// 安全负责人电话
				b001po.setMA020(c006po.getMa015());// 企业Ｅｍａｉｌ
				b001po.setMA069(StringUtil.toJP(c006po.getMa002(), 1));// 拼音代码
				b001Dao.updateB001(b001po);
			}
			message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		} else {
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return message;

	}

	/**
	 * 核查更新的操作；填写核查信息，如果核查通过，则更新C001＼ｓ＿ｕｓｅｒ＼ｂ００１表中相关记录，而B001表中的记录不需要修改
	 * 同时赋予企业管理员所共有的权限操作
	 */
	@Override
	public UserMessageData updateHCC006(C006PO c006po) throws Exception {

		UserMessageData message = new UserMessageData();
		c006po.setMa017(SessionUtil.getUserId());// 审核人ID
		c006po.setMa018(SessionUtil.getUserPO().getREAL_NAME());// 审核人姓名
		c006po.setMa019(DateTimeUtils.getCurrentDate());
		// 如果核查不通过，则将ma021的状态置为无效
		if (c006po.getMa020() == C006PO.STATUS_NO) {
			c006po.setMa021(C006PO.MA021_DEL);
		}
		int optRecNum = c006Dao.updateHCC006(c006po);
		if (optRecNum == 1) {
			// 如果审核通过，则修改C001＼ｓ＿ｕｓｅｒ＼ｂ００１表中相关记录
			if (c006po.getMa020() == C006PO.STATUS_YES) {
				String creator = c006po.getMa017();// 审核人ID，就当是创建人
				String ma003 = c006po.getMa003();
				// 审核通过后修改C001表中的记录：修改状态为有效
				C001PO c001po = new C001PO();

				c001po = c001Dao.queryC001POByMa003(ma003);// //根据组织机构代码，查询C001PO
				c001po.setMA008(C001PO.MA008_VALID);// 审核通过设置状态为有效
				c001po.setMA017(C001PO.MA017_VALID);// 删除状态为有效
				c001Dao.updateC001(c001po);// 更新C001表记录

				// 更新s_user表
				UserPO userPO = userDao.retrieveUserByLoginName(c006po
						.getMa010());// 根据用户名进行查询
				if (userPO != null) {
					String userIDString = userPO.getId();// 获得用户ID，为了给该用户添加权限
					userPO.setUSER_STATUS(UserPO.USER_STATUS_KT);// 审核通过后，设置该用户的状态为“开通”状态
					userDao.updateSysUser(userPO);// 更新userPO

					// 插入一条角色记录
					SysRolePO rolePO = new SysRolePO();
					String roleID = UUIDGenerater.getUUID();
					String roleName = c006po.getMa002() + "管理员";// 角色名称为企业名称—+管理员
					rolePO.setUUID(roleID);
					rolePO.setROLE_NAME(roleName);
					rolePO.setUSER_TYPE(SysRolePO.USER_TYPE_ENT);
					rolePO.setREMARK("企业管理员");
					String organ_uuid = userDao.getSysAdminOrgId();
					rolePO.setORGAN_UUID(organ_uuid);
					rolePO.setCREATOR(creator);
					rolePO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
					roleDao.insertSysRole(rolePO);
					// 插入用户角色关系表
					SysUserRolePO userRolePO = new SysUserRolePO();
					String userRoleID = UUIDGenerater.getUUID();
					userRolePO.setUUID(userRoleID);
					userRolePO.setUSER_UUID(userIDString);
					userRolePO.setROLE_UUID(roleID);
					userRolePO.setCREATOR(creator);
					userRolePO.setCREATE_DATE(DateTimeUtils.getCurrentDate());
					userRoleDao.insertSysUserRole(userRolePO);

					List<SysModulePO> moduleList = new ArrayList<SysModulePO>();
					Map<String, String> map = new HashMap<String, String>();
					map.put("USER_TYPE", "ENT");
					map.put("IS_VALID", "10");

					moduleList = moduleDao.retrieveByCondition(map);

					for (int i = 0; i < moduleList.size(); i++) {
						SysModulePO sysModulePO = moduleList.get(i);
						String modeleID = sysModulePO.getUUID();

						// 插入角色模块关系映射表:角色和模块
						SysRolePermPO rolePermPO = new SysRolePermPO();
						rolePermPO.setUUID(UUIDGenerater.getUUID());
						rolePermPO.setROLE_UUID(roleID);
						rolePermPO.setORGAN_UUID(organ_uuid);
						rolePermPO.setCREATOR(creator);
						rolePermPO.setCREATE_DATE(DateTimeUtils
								.getCurrentDate());
						rolePermPO.setMODULE_UUID(modeleID);
						rolePermDao.insertSysRolePerm(rolePermPO);

						List<SysOperationPO> optList = new ArrayList<SysOperationPO>();
						Map<String, String> optmap = new HashMap<String, String>();
						optmap.put("MENU_UUID", modeleID);
						optmap.put("IS_VALID", "10");
						optList = operationDao.retrieveByCondition(optmap);

						for (int j = 0; j < optList.size(); j++) {
							SysOperationPO operationPO = optList.get(j);
							String optID = operationPO.getUUID();

							// 插入角色模块关系映射表：角色和操作
							SysRolePermPO rolePermPO2 = new SysRolePermPO();
							rolePermPO2.setUUID(UUIDGenerater.getUUID());
							rolePermPO2.setROLE_UUID(roleID);
							rolePermPO2.setORGAN_UUID(organ_uuid);
							rolePermPO2.setCREATOR(creator);
							rolePermPO2.setCREATE_DATE(DateTimeUtils
									.getCurrentDate());
							rolePermPO2.setOPERA_UUID(optID);
							rolePermDao.insertSysRolePerm(rolePermPO2);
						}
					}
				}
				sendSMSNotice(c006po, 3);// 短信发送
				message.set(Constants.MSG_OK, "审核通过！");
			}// 核查未通过，则驳回
			else {
				sendSMSNotice(c006po, 4);// 短信发送
				message.set(Constants.MSG_OK, "审核未通过！");
			}

		} else {
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return message;

	}

	@Override
	public UserMessageData deleteC006ByIds(String ids) throws Exception {
		UserMessageData message = new UserMessageData();
		c006Dao.deleteC006ByIds(ids);
		message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		return message;
	}

	/**
	 * 判断组织机构代码是否已经存在：既判断C006表又判断C001表的组织机构代码,是否已经存在
	 */
	@Override
	public boolean isUniqueMa003(C006PO c006po) throws Exception {
		Integer c006Count = c006Dao.countC006ByMa003(c006po);
		C001PO c001po = c001Dao.queryC001POByMa003(c006po.getMa003());// 根据用户名查询C001PO
		Integer c001Count = 0;
		if (c001po != null) {
			c001Count = c001Dao.countC001ByMa003(c001po);// 根据PO，判断用户名是否唯一
			// 如果不唯一，则返回
			if (c001Count > 0 || c006Count > 0) {
				return false;
			}
		} else {
			if (c001po != null || c006Count > 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isUniqueMa002(C006PO c006po) throws Exception {
		if (c006Dao.countC006ByMa002(c006po) > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isUniqueMa015(C006PO c006po) throws Exception {
		if (c006Dao.countC006ByMa015(c006po) > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断用户名是否唯一：C006表中是否唯一，s_user是否唯一，
	 * 由于在编辑的时候不让修改用户名，所以不存在在修改的时候再判断用户名唯一并id不等于本身
	 */
	@Override
	public boolean isUniqueMa010(C006PO c006po) throws Exception {
		UserPO userPO = userDao.retrieveUserByLoginName(c006po.getMa010());// 查询用户表中的此用户是否已经存在
		Integer countC006 = c006Dao.countC006ByMa010(c006po);
		// 如果不唯一，则返回
		if (userPO != null || countC006 > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isUniqueMa014(C006PO c006po) throws Exception {
		if (c006Dao.countC006ByMa014(c006po) > 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return c006Dao.retrieveByPage(map);
	}

	@Override
	public int delBatch(Map map) throws Exception {
		return 0;
	}

	/**
	 * 
	 * 
	 * sendSMSNotice(短信发送接口
	 * 
	 * @param C006PO
	 *            ,sendType:表示发送类型，其中1：注册成功；2：注册不成功；3：审核通过。4：审核不通过
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean sendSMSNotice(C006PO c006po, int sendType)
			throws SystemException {
		boolean flag = false;
		try {
			String regName = c006po.getMa012();// 安全人姓名
			String regPhone = c006po.getMa014();// 手机号
			String regAccount = c006po.getMa010();// 账号
			String corpname = c006po.getMa002();// 企业名称
			String opinions = (c006po.getMa024() != null && c006po.getMa024()
					.equals("")) ? "无" : c006po.getMa024();// 审核意见
			String regPassword = c006po.getOldPassword();// MD5加密前的密码

			// 发送短信和内容，不能为空
			if (StringUtil.string2DefVal(regName).isEmpty()
					|| StringUtil.string2DefVal(regPhone).isEmpty()
					|| StringUtil.string2DefVal(regAccount).isEmpty()) {
				return flag;
			}

			// //拼接短信内容
			// String header = "这是郑州高新区安监局安全生产信息监管平台,";
			// String footer ="。";
			// String content = "";
			// if(sendType == 1){
			// content = header + "您的用户名是："+regAccount
			// +",密码是："+regPassword+",请等待审核，谢谢！联系电话：0371-67169073，联系人：韩女士"+
			// footer;
			// }
			// if(sendType == 2 ){
			// content = header + "您注册的信息不成功，请重新注册，谢谢！"+ footer;
			// }
			// if(sendType == 3){
			// content = header +
			// "您注册的信息已经通过审核，请及时登录(http://218.28.134.91/hwsafe)完善企业信息！联系人：韩女士"+
			// footer;
			// }
			// if(sendType == 4){
			// content = header + "您注册的信息未通过审核！请重新注册！"+ footer;
			// }

			StringBuffer sb = new StringBuffer();
			if (sendType == 1) {
				// content = header + "您的用户名是："+regAccount
				// +",密码是："+regPassword+",请等待审核，谢谢！联系电话：0371-67169073，联系人：韩女士"+
				// footer;
				sb.append(RegisterConstants.REGISTER_SUCCESS_ONE)
						.append(corpname)
						.append(RegisterConstants.REGISTER_SUCCESS_TWO)
						.append(regAccount)
						.append(RegisterConstants.REGISTER_SUCCESS_THREE)
						.append(regPassword)
						.append(RegisterConstants.REGISTER_SUCCESS_FOUR);
			}
			if (sendType == 2) {
				// content = header + "您注册的信息不成功，请重新注册，谢谢！"+ footer;
				sb.append(RegisterConstants.REGISTER_FAILURE);
			}
			if (sendType == 3) {
				// content = header +
				// "您注册的信息已经通过审核，请及时登录(http://218.28.134.91/hwsafe)完善企业信息！联系人：韩女士"+
				// footer;
				sb.append(RegisterConstants.CHECK_SUCCESS_ONE).append(corpname)
						.append(RegisterConstants.CHECK_SUCCESS_TWO)
						.append(opinions);
			}
			if (sendType == 4) {
				// content = header + "您注册的信息未通过审核！请重新注册！"+ footer;
				sb.append(RegisterConstants.CHECK_FAILURE_ONE).append(corpname)
						.append(RegisterConstants.CHECK_FAILURE_TWO)
						.append(opinions);
			}

			// SMSPO smsPO =new SMSPO();
			// smsPO.setTelNum(regPhone);
			// smsPO.setTelContent(content);
			//
			// SmsHandleLintener.getSmsHandle().addAlarmInfo(smsPO);
			// if(!SmsHandleLintener.getSmsHandle().isRun()){
			// SmsHandleLintener.getSmsHandle().awaken();
			// }
			MessagePO message = new MessagePO();
			message.setContent(sb.toString());
			message.setMode("high");
			List<String> recievePeopleList = new ArrayList<String>();
			recievePeopleList.add(regPhone);
			message.setRecievePeopleList(recievePeopleList);
			ISendMessage sendMessage = new SendMessageImpl();
			sendMessage.sendOuterMessage(message);

		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		} finally {
			// smsService.stopService();//关闭短信发送服务
		}
		return flag;
	}

	@Override
	public C006PO retrieveC006ByNamePassword(C006PO c006po) throws Exception {
		C006PO c006poNew = new C006PO();
		c006poNew = c006Dao.retrieveC006ByNamePassword(c006po);
		return c006poNew;
	}

	public List getZgdwList() throws Exception {
		return c006Dao.getZgdwList();
	}

	public List getZgdwData(String pId) throws Exception {
		return c006Dao.getZgdwData(pId);
	}
}
