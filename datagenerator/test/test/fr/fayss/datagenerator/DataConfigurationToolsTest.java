package test.fr.fayss.datagenerator;


import org.junit.Assert;
import org.junit.Test;

import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.types.IntegerDataGenerator;



/**
 * this is a test class
 * 
 * @author fayss
 *
 */
public class DataConfigurationToolsTest {
	
	
	@Test
	public void testConfigure (){
		
		IntegerDataGenerator integerDataGenerator = new IntegerDataGenerator ();
		
		DataConfigurationTools dct =new DataConfigurationTools() ;
		
		Assert.assertEquals(new Integer(1000), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(new Integer(9000), integerDataGenerator.getEndInclusive());
		
		try {
			dct.configure(integerDataGenerator, DataConfigurationConstant.START_INCLUSIVE, 5);
		} catch (PropertyConfigurationException e) {
			Assert.fail("PropertyConfigurationException not exptected");
		}
	
		
		Assert.assertEquals(new Integer(5), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(new Integer(9000), integerDataGenerator.getEndInclusive());
		
		
	}
	

}
