package fr.fayss.datagenerator;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Data configuration Tools
 * @author fayss
 *
 */
public class DataConfigurationTools {


	/**
	 * Change the propertie's value of the data generator by using the Data config property
	 * @param pDataGen the data generator
	 * @param pDataconfig the data configuration to use
	 * @throws PropertyConfigurationException
	 */
	public static void configure ( DataGenerator pDataGen,DataConfiguration pDataconfig)
			throws PropertyConfigurationException {

		for (String property : pDataconfig.getAllConfiguredPropertyName()) {
			Object propertyconfig = pDataconfig.getPropertyConfiguration(property);
			configure( pDataGen,  property,  propertyconfig ) ;
		}
	}

	/**
	 * Change the value of a specific property of the data generator
	 * @param pDataGen the data generator 
	 * @param PropertyName the property name ,must be defined in the data generator) 
	 * @param propertyValue the value to set
	 * @throws PropertyConfigurationException
	 */
	public static void configure(DataGenerator pDataGen, String PropertyName, Object propertyValue ) 
			throws PropertyConfigurationException {

		try {
			
			// add the getProperty method because setProperty doesn't throw  exceptions if the property doesn't exist ..
			BeanUtils.getProperty(pDataGen, PropertyName);
			BeanUtils.setProperty(pDataGen, PropertyName, propertyValue);

		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		} catch (NoSuchMethodException e) {
			throw new PropertyConfigurationException("Property " + PropertyName + " not found in data generator " + pDataGen.getClass());
		}
	}
	
	

}
