/**
 * 文件名：ProcessTest.java
 *
 * 版本信息：1.0
 * 日期：2012-10-18
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.workflow.test;

//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.runtime.ProcessInstance;
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
 * 类名称：ProcessTest
 * 类描述：进程测试类
 * 创建人：马宁
 * 创建时间：2012-10-18 下午2:29:33
 * 修改人：马宁
 * 修改时间：2012-10-18 下午2:29:33
 * 修改备注：
 * @version 
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/com/hw/hwsafe/workflow/test/applicationContext-activiti-test.xml")
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class ProcessTest {
//
//	@Autowired
//	private RepositoryService repositoryService;
//	
//	@Autowired
//	private RuntimeService runtimeService;
//	
//	@Autowired
//	private HistoryService historyService;
//	
//	/**
//	 * 
//	 * 函 数 名：startProcessInstanceTest
//	 * 功能描述：测试开始进程实例
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午2:33:33
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void startProcessInstanceTest(){
//		initDeployment();
//		
//		long processCountBeforeStart = getProcessInstanceCount();
//		long historicProcessCountBeforeStart = getHistoricProcessCount();
//		
//		ProcessInstance processInstance = startProcessInstance();
//		assertNotNull(processInstance);
//		assertNotNull(processInstance.getId());
//		assertTrue(processInstance.getProcessDefinitionId()
//				.startsWith(Bpmn20XmlInfo.PROCESS_ID));
//		
//		long processCountAfterStart = getProcessInstanceCount();
//		long historicProcessCountAfterStart = getHistoricProcessCount();
//		
//		assertEquals(processCountBeforeStart + 1, processCountAfterStart);
//		assertEquals(historicProcessCountBeforeStart + 1, historicProcessCountAfterStart);
//	}
//	
//
//	// -------- private methods ---------
//	
//	private void initDeployment() {
//		repositoryService.createDeployment()
//				.addClasspathResource(Bpmn20XmlInfo.BPMN_XML_PATH)
//				.name(Bpmn20XmlInfo.DEPLOYMENT_NAME)
//				.deploy();
//	}
//	
//	private ProcessInstance startProcessInstance(){
//		return runtimeService.startProcessInstanceByKey(Bpmn20XmlInfo.PROCESS_ID);
//	}
//	
//	private long getProcessInstanceCount(){
//		return runtimeService.createProcessInstanceQuery().count();
//	}
//	
//	private long getHistoricProcessCount() {
//		return historyService.createHistoricProcessInstanceQuery().count();
//	}
}
