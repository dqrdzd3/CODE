package com.hw.hwsafe.platform.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hw.hwsafe.platform.service.IOperLogService;

//@SuppressWarnings("unused")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//		locations={
//			"classpath*:test-applicationContext.xml", 
//			"classpath:applicationContext-mybatis-platform.xml", 
//			"classpath:applicationContext-service-platform.xml"
//		}
//)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class OperLogServiceImplTest {

	@Autowired
	private IOperLogService operLogService;
	
//	@Test
	public void logTest(){
		operLogService.log("testClassName", "testMethodName", "testContent");
	}
	
}
