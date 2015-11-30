/**
 * 文件名：IdentityTest.java
 *
 * 版本信息：1.0
 * 日期：2012-10-18
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.workflow.test;

//import java.util.List;
//
//import org.activiti.engine.IdentityService;
//import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hw.hwsafe.workflow.test.info.IdentityInfo;
//
//import static org.junit.Assert.*;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IdentityTest
 * 类描述：身份测试类
 * 创建人：马宁
 * 创建时间：2012-10-18 上午8:35:12
 * 修改人：马宁
 * 修改时间：2012-10-18 上午8:35:12
 * 修改备注：
 * @version 
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/com/hw/hwsafe/workflow/test/applicationContext-activiti-test.xml")
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class IdentityTest {
//
//	@Autowired
//	private IdentityService identityService;
//
//	private User initUser;
//
//	private Group initGroup;
//
//	/**
//	 * 
//	 * 函 数 名：addUserTest
//	 * 功能描述：测试添加用户
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 上午8:50:52
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void addUserTest() {
//		long userCountBeforeAdd = getUserCount();
//		createUser();
//		long userCountAfterAdd = getUserCount();
//
//		assertEquals(userCountBeforeAdd + 1, userCountAfterAdd);
//	}
//
//	/**
//	 * 
//	 * 函 数 名：addGroupTest
//	 * 功能描述：测试添加组
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 上午9:35:52
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void addGroupTest() {
//		long groupCountBeforeAdd = getGroupCount();
//		createGroup();
//		long groupCountAfterAdd = getGroupCount();
//
//		assertEquals(groupCountBeforeAdd + 1, groupCountAfterAdd);
//	}
//
//	/**
//	 * 
//	 * 函 数 名：queryUserTest
//	 * 功能描述：测试查询用户
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 上午9:59:52
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void queryUserTest() {
//		createUser();
//
//		User queryUserById = identityService.createUserQuery()
//				.userId(initUser.getId())
//				.singleResult();
//		assertNotNull(queryUserById);
//		assertEquals(initUser.getFirstName(), queryUserById.getFirstName());
//		assertEquals(initUser.getLastName(), queryUserById.getLastName());
//		assertEquals(initUser.getPassword(), queryUserById.getPassword());
//		assertEquals(initUser.getEmail(), queryUserById.getEmail());
//
//		User queryUserByFirstName = identityService.createUserQuery()
//				.userFirstName(initUser.getFirstName())
//				.singleResult();
//		assertNotNull(queryUserByFirstName);
//
//		User queryUserByLastName = identityService.createUserQuery()
//				.userLastName(initUser.getLastName())
//				.singleResult();
//		assertNotNull(queryUserByLastName);
//
//		User queryUserByEmail = identityService.createUserQuery()
//				.userEmail(initUser.getEmail())
//				.singleResult();
//		assertNotNull(queryUserByEmail);
//	}
//
//	/**
//	 * 
//	 * 函 数 名：queryGroupTest
//	 * 功能描述：测试查询组
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 上午10:26:52
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void queryGroupTest(){
//		createGroup();
//		
//		Group queryGroupById = identityService.createGroupQuery()
//				.groupId(initGroup.getId())
//				.singleResult();
//		assertNotNull(queryGroupById);
//		assertEquals(initGroup.getName(), queryGroupById.getName());
//		assertEquals(initGroup.getType(), queryGroupById.getType());
//		
//		Group queryGroupByName = identityService.createGroupQuery()
//				.groupName(initGroup.getName())
//				.singleResult();
//		assertNotNull(queryGroupByName);
//		
//		Group queryGroupByType = identityService.createGroupQuery()
//				.groupType(initGroup.getType())
//				.singleResult();
//		assertNotNull(queryGroupByType);
//	}
//	
//	/**
//	 * 
//	 * 函 数 名：createMenberShipTest
//	 * 功能描述：测试创建用户与组的对应关系
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 上午11:02:52
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void createMenberShipTest(){
//		createUser();
//		createGroup();
//		
//		long userCountBeforeCreate = getUserCountOfInitGroup();
//		createMemberShip();
//		long userCountAfterCreate = getUserCountOfInitGroup();
//		
//		assertEquals(userCountBeforeCreate + 1, userCountAfterCreate);
//	}
//	
//	/**
//	 * 
//	 * 函 数 名：queryUserOfGroupTest
//	 * 功能描述：测试查询组里的用户
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午1:05:33
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void queryUserOfGroupTest(){
//		createUser();
//		createGroup();
//		createMemberShip();
//		
//		List<User> users = identityService.createUserQuery().memberOfGroup(initGroup.getId()).list();
//		boolean flag = false;
//		for(User user : users){
//			if(user.getId().equals(initUser.getId())){
//				flag = true;
//				break;
//			}
//		}
//		assertTrue(flag);
//	}
//	
//	// ------------ private methods ------------
//
//	private void createUser() {
//		initUser = identityService.newUser(IdentityInfo.USER_ID);
//		initUser.setFirstName(IdentityInfo.FIRST_NAME);
//		initUser.setLastName(IdentityInfo.LAST_NAME);
//		initUser.setPassword(IdentityInfo.PASSWORD);
//		initUser.setEmail(IdentityInfo.EMAIL);
//		identityService.saveUser(initUser);
//	}
//
//	private void createGroup() {
//		initGroup = identityService.newGroup(IdentityInfo.GROUP_ID);
//		initGroup.setName(IdentityInfo.GROUP_NAME);
//		initGroup.setType(IdentityInfo.GROUP_TEPE);
//		identityService.saveGroup(initGroup);
//	}
//	
//	private void createMemberShip(){
//		identityService.createMembership(initUser.getId(), initGroup.getId());
//	}
//
//	private long getUserCount() {
//		return identityService.createUserQuery().count();
//	}
//
//	private long getGroupCount() {
//		return identityService.createGroupQuery().count();
//	}
//
//	private long getUserCountOfInitGroup(){
//		return identityService.createUserQuery()
//				.memberOfGroup(initGroup.getId())
//				.count();
//	}
}
