package com.echeng.resumeparser.convert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.echeng.resumeparser.BaseTestCase;
import com.echeng.resumeparser.convert.convertors.PdfConvertor;
import com.echeng.resumeparser.resumeInput.IResumeReader;

public class PdfConvertorTest extends BaseTestCase {
	
	private static IResumeReader reader;
	private static IFileConvertor convertor;

	@BeforeClass
	public static void setUpBeforeClass() {
		BaseTestCase.setUpBeforeClass();
		convertor = new PdfConvertor();
		reader = (IResumeReader) ctx_static.getBean("fileReader");
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test1(){
		byte[] ans = reader.readResume("testResume/test.pdf", "local");
		if (null != ans){
			convertor.convert(ans);
			String ret = convertor.getFileContent();
			assertNotNull(ret);
			if (null != ret){
				assertEquals(1051, ret.length());
				String[] aaa = ret.split("\n");
				assertEquals(44, aaa.length);
			}
		}
	}
}
