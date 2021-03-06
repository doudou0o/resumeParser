package com.echeng.resumeparser;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;

import junit.framework.TestCase;

/**
 * 测试基类
 * 可用两种方法获得 spring bean 对象
 *     1. 在 setUpBeforeClass() 中使用 ctx_static 获得. (推荐)
 *     2. 在 setUp() 中使用 ctx 获得.
 * @author doudou0o
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:properties/spring/mainContext.xml")
public class BaseTestCase extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger(BaseTestCase.class);
	
	protected static ApplicationContext ctx_static;
	
	@Autowired
	protected ApplicationContext ctx;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		ctx_static = new ClassPathXmlApplicationContext("properties/spring/mainContext.xml");
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1(){
		assertNotNull(logger);
		logger.info("logger works");
		logger.info("BaseTestCase loaded");
	}
	
}
