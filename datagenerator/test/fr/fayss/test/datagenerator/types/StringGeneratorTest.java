package fr.fayss.test.datagenerator.types;

import org.junit.Test;

import fr.fayss.datagenerator.types.StringGenerator;

import static org.junit.Assert.*;

public class StringGeneratorTest {
	
	@Test
	public void testIsconfigure () {
		
		StringGenerator stringGen = new StringGenerator() ;
		
		assertEquals(stringGen.getStringLength() , new Integer(5));
		assertEquals(stringGen.getPrefix() , "");
		assertEquals(stringGen.getSuffix() , "");
		
	}
	
	@Test
	public void testIsConfigured (){
		
		StringGenerator stringGen = new StringGenerator() ;
		assertTrue(stringGen.isConfigured());
		
		stringGen.setStringLength(0);
		assertFalse(stringGen.isConfigured());
		
		stringGen.setStringLength(null);
		assertFalse(stringGen.isConfigured());
		
		stringGen.setStringLength(1);
		assertTrue(stringGen.isConfigured());	
	}
	
	@Test
	public void testGenerate () {
	
		StringGenerator stringGen = new StringGenerator() ;
		assertTrue(stringGen.generate() instanceof String) ;

		String result = (String) stringGen.generate();
		assertTrue(result.length() == stringGen.getStringLength());

		stringGen.setStringLength(10);
		result = (String) stringGen.generate();
		assertTrue(result.length() == 10);
		
	}
	
	
	@Test
	public void testGeneratePrefix () {
	
		StringGenerator stringGen = new StringGenerator() ;
		stringGen.setStringLength(10);
		String result = (String) stringGen.generate();
		
		assertTrue(result.length() == 10);
		
		stringGen.setPrefix("PREFIX");
		result = (String) stringGen.generate();
		
		assertTrue(result.length() == 16);	
		assertTrue(result.startsWith("PREFIX"));
		
	}
	
	@Test
	public void testGenerateSuffix () {
	
		StringGenerator stringGen = new StringGenerator() ;

		stringGen.setStringLength(20);
		stringGen.setSuffix("SUFFIX");
		String result = (String) stringGen.generate();
		
		assertTrue(result.length() == 26);	
		assertTrue(result.endsWith("SUFFIX"));
		
	}

}
