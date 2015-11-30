/**
 * 文件名：TaskTest.java
 *
 * 版本信息：1.0
 * 日期：2012-10-18
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.workflow.test;

//import java.util.HashMap;
//import java.util.Map;
//
//import org.activiti.engine.*;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hw.hwsafe.workflow.test.info.Bpmn20XmlInfo;
//
//import static org.junit.Assert.*;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：TaskTest
 * 类描述：任务测试类
 * 创建人：马宁
 * 创建时间：2012-10-18 下午4:33:22
 * 修改人：马宁
 * 修改时间：2012-10-18 下午4:33:22
 * 修改备注：
 * @version 
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/com/hw/hwsafe/workflow/test/applicationContext-activiti-test.xml")
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class TaskTest {
//
//	@Autowired
//	private IdentityService identityService;
//
//	@Autowired
//	private RepositoryService repositoryService;
//	
//	@Autowired
//	private RuntimeService runtimeService;
//	
//	@Autowired
//	private TaskService taskService;
//	
//	@Autowired
//	private HistoryService historyService;
//	
//	private User user1;
//	
//	private User user2;
//
//	private Group group1;
//	
//	private Group group2;
//	
//	private ProcessInstance processInstance;
//
//	/**
//	 * 
//	 * 函 数 名：flowTest
//	 * 功能描述：测试工作流的流程运转,具体流程见bpmn20.xml中的定义
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午3:08:22
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void flowTest(){
//		initData();
//		
//		assertEquals(1, getTaskHistoricCountOfCurrentProcessInstance());
//		assertFalse(isHistoricProcessInstanceEnd());
//		
//		assertEquals(1, taskService.createTaskQuery().taskCandidateGroup(group1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group2.getId()).count());
//		assertEquals(1, taskService.createTaskQuery().taskCandidateUser(user1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user2.getId()).count());
//		
//		Task taskStep1 = taskService.createTaskQuery().taskCandidateUser(user1.getId()).singleResult();
//		assertNotNull(taskStep1);
////		taskService.setAssignee(taskStep1.getId(), user1.getId());  // 可指定多次
//		taskService.claim(taskStep1.getId(), user1.getId());    // 只能指定一次
//		
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group2.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user2.getId()).count());
//		assertEquals(1, taskService.createTaskQuery().taskAssignee(user1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskAssignee(user2.getId()).count());
//		
//		Map<String, Object> argStep1 = new HashMap<String, Object>();
//		argStep1.put("step1Key", "step1Value");
//		
//		taskService.complete(taskStep1.getId(), argStep1);
//		
//		assertEquals(0, taskService.createTaskQuery().taskId(taskStep1.getId()).count());
//		assertEquals(2, getTaskHistoricCountOfCurrentProcessInstance());
//		HistoricTaskInstance historicTaskStep1 = historyService
//				.createHistoricTaskInstanceQuery()
//				.taskId(taskStep1.getId())
//				.singleResult();
//		assertNotNull(historicTaskStep1);
//		assertEquals("completed", historicTaskStep1.getDeleteReason());
//		
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group1.getId()).count());
//		assertEquals(1, taskService.createTaskQuery().taskCandidateGroup(group2.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user1.getId()).count());
//		assertEquals(1, taskService.createTaskQuery().taskCandidateUser(user2.getId()).count());
//		
//		Task taskStep2 = taskService.createTaskQuery().taskCandidateUser(user2.getId()).singleResult();
//		assertNotNull(taskStep2);
//		taskService.claim(taskStep2.getId(), user2.getId());
//		
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateGroup(group2.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user1.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskCandidateUser(user2.getId()).count());
//		assertEquals(0, taskService.createTaskQuery().taskAssignee(user1.getId()).count());
//		assertEquals(1, taskService.createTaskQuery().taskAssignee(user2.getId()).count());
//		
//		Map<String, Object> argStep2 = new HashMap<String, Object>();
//		argStep2.put("step2Key", "step2Value");
//		
//		assertFalse(isHistoricProcessInstanceEnd());
//		
//		taskService.complete(taskStep2.getId(), argStep2);
//		
//		assertEquals(0, taskService.createTaskQuery().taskId(taskStep1.getId()).count());
//		assertEquals(2, getTaskHistoricCountOfCurrentProcessInstance());
//		HistoricTaskInstance historicTaskStep2 = historyService
//				.createHistoricTaskInstanceQuery()
//				.taskId(taskStep2.getId())
//				.singleResult();
//		assertNotNull(historicTaskStep2);
//		assertEquals("completed", historicTaskStep2.getDeleteReason());
//		
//		assertTrue(isHistoricProcessInstanceEnd());
//	}
//	
//	// ---------- private methods -----------
//
//	private void initData(){
//		initIdentity();
//		deployDeployment();
//		startProcess();
//	}
//
//	private void initIdentity() {
//		initUser();
//		initGroup();
//		initMenberShip();
//	}
//
//	private void initUser() {
//		user1 = identityService.newUser("o0o0o_user1");
//		user2 = identityService.newUser("o0o0o_user2");
//		
//		user1.setFirstName("o0o0o_firstName1");
//		user1.setLastName("o0o0o_lastName1");
//		user1.setPassword("o0o0o_password1");
//		user1.setEmail("o0o0o_email1");
//		user2.setFirstName("o0o0o_firstName2");
//		user2.setLastName("o0o0o_lastName2");
//		user2.setPassword("o0o0o_password2");
//		user2.setEmail("o0o0o_email2");
//		
//		identityService.saveUser(user1);
//		identityService.saveUser(user2);
//	}
//
//	private void initGroup() {
//		group1 = identityService.newGroup("o0o0o_group1");
//		group2 = identityService.newGroup("o0o0o_group2");
//		
//		group1.setName("o0o0o_groupName1");
//		group1.setType("o0o0o_groupType1");
//		group2.setName("o0o0o_groupName2");
//		group2.setType("o0o0o_groupType2");
//		
//		identityService.saveGroup(group1);
//		identityService.saveGroup(group2);
//	}
//
//	private void initMenberShip() {
//		identityService.createMembership(user1.getId(), group1.getId());
//		identityService.createMembership(user2.getId(), group2.getId());
//	}
//	
//	private void deployDeployment() {
//		repositoryService.createDeployment()
//				.addClasspathResource(Bpmn20XmlInfo.BPMN_XML_PATH)
//				.name(Bpmn20XmlInfo.DEPLOYMENT_NAME)
//				.deploy();
//	}
//
//	private void startProcess() {
//		processInstance = runtimeService.startProcessInstanceByKey(Bpmn20XmlInfo.PROCESS_ID);
//	}
//	
//	private long getTaskHistoricCountOfCurrentProcessInstance(){
//		return historyService.createHistoricTaskInstanceQuery()
//				.processInstanceId(processInstance.getId())
//				.count();
//	}
//	
//	private boolean isHistoricProcessInstanceEnd(){
//		HistoricProcessInstance hisProcess = historyService
//				.createHistoricProcessInstanceQuery()
//				.processInstanceId(processInstance.getId())
//				.singleResult();
//		return hisProcess.getEndTime() != null;
//	}
}
