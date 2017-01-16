package com.echeng.resumeparser.resumeInput;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.echeng.resumeparser.BaseTestCase;

public class ResumeFileReaderTest extends BaseTestCase {
	
	//private static IResumeReader reader;
	
	private IResumeReader reader;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BaseTestCase.setUpBeforeClass();
		//reader = (IResumeReader) ctx_static.getBean("fileReader");
	}
	
	@Before
	public void setUp() throws Exception {
		reader = (IResumeReader) ctx.getBean("fileReader");
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1(){
		byte[] ans = reader.readResume("testResume/test.txt", "local");
		assertNotNull(ans);
		if (null != ans)
			assertEquals(3608, ans.length);
	}
}
