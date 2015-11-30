/**
 * 文件名：DeploymentTest.java
 *
 * 版本信息：1.0
 * 日期：2012-10-18
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.workflow.test;
//
//import java.util.List;
//
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.repository.Deployment;
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
 * 类名称：DeploymentTest
 * 类描述：部署测试类
 * 创建人：马宁
 * 创建时间：2012-10-18 下午1:35:33
 * 修改人：马宁
 * 修改时间：2012-10-18 下午1:35:33
 * 修改备注：
 * @version 
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration("/com/hw/hwsafe/workflow/test/applicationContext-activiti-test.xml")
//@ContextConfiguration(locations={"classpath*:applicationContext-all.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class DeploymentTest {
//
//	@Autowired
//	private RepositoryService repositoryService;
//
//	private Deployment initDeployment;
//
//	/**
//	 * 
//	 * 函 数 名：deployDeploymentTest
//	 * 功能描述：测试部署
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午1:42:33
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void deployDeploymentTest() {
//
//		long deploymentCountBeforeDeploy = getDeploymentCount();
//		initDeployment();
//		long deploymentCountAfterDeploy = getDeploymentCount();
//
//		assertEquals(deploymentCountBeforeDeploy + 1,
//				deploymentCountAfterDeploy);
//	}
//
//	/**
//	 * 
//	 * 函 数 名：queryDeploymentTest
//	 * 功能描述：测试查询部署
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午2:05:33
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void queryDeploymentTest() {
//		initDeployment();
//
//		Deployment queryDeployment = repositoryService.createDeploymentQuery()
//				.deploymentId(initDeployment.getId()).singleResult();
//
//		assertNotNull(queryDeployment);
//		assertEquals(queryDeployment.getName(), initDeployment.getName());
//		assertEquals(queryDeployment.getDeploymentTime(),
//				initDeployment.getDeploymentTime());
//	}
//
//	/**
//	 * 
//	 * 函 数 名：deleteDeploymentTest
//	 * 功能描述：测试删除部署
//	 * 创建人：马宁
//	 * 创建时间：2012-10-18 下午2:15:33
//	 * 修改人：
//	 * 修改时间：
//	 * 修改原因描述：
//	 */
//	@Test
//	public void deleteDeploymentTest() {
//		initDeployment();
//
//		long deploymentCountBeforeDelete = getDeploymentCount();
//		deleteInitDeployment();
//		long deploymentCountAfterDelete = getDeploymentCount();
//
//		assertEquals(deploymentCountBeforeDelete - 1,
//				deploymentCountAfterDelete);
//	}
//
//	// -------------------private methods----------------------
//
//	private void initDeployment() {
//		initDeployment = repositoryService.createDeployment()
//				.addClasspathResource(Bpmn20XmlInfo.BPMN_XML_PATH)
//				.name(Bpmn20XmlInfo.DEPLOYMENT_NAME)
//				.deploy();
//	}
//
//	private void deleteInitDeployment() {
//		repositoryService.deleteDeployment(initDeployment.getId());
//	}
//
//	private long getDeploymentCount() {
//		return repositoryService.createDeploymentQuery().count();
//	}
//
//	@SuppressWarnings("unused")
//	private void deleteAllDeployment() {
//		List<org.activiti.engine.repository.Deployment> deps = repositoryService
//				.createDeploymentQuery().list();
//		for (org.activiti.engine.repository.Deployment dep : deps) {
//			repositoryService.deleteDeployment(dep.getId());
//		}
//	}
}
