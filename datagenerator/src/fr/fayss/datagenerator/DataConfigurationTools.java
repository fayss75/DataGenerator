package fr.fayss.datagenerator;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Data configuration Tools
 * @author fayss
 *
 */
public class DataConfigurationTools {
	
	public static final String DATA_GENERATOR_INSTANCE = "dataGeneratorInstance";


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
	 * Change the propertie's value of the data generator by using the Data config property
	 * The data config must contains the data generator in this method and it must store using 
	 * the static property DataConfigurationTools.DATA_GENERATOR_CLASS
	 * @param pDataconfig the data configuration to use
	 * @throws PropertyConfigurationException
	 */
	public static void configure (DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		DataGenerator dataGenerator = (DataGenerator) pDataconfig.getPropertyConfiguration(DATA_GENERATOR_INSTANCE);
		
		if (dataGenerator != null){
			for (String property : pDataconfig.getAllConfiguredPropertyName()) {
				Object propertyconfig = pDataconfig.getPropertyConfiguration(property);
				configure( dataGenerator,  property,  propertyconfig ,false) ;
			}
		} else {
			throw new PropertyConfigurationException ("data generator not found in the dataConfig. use the value of the static property DataConfigurationTools.DATA_GENERATOR_CLASS") ;
		}
	}
	
	
	/**
	 * Change the value of a specific property of the data generator
	 * @param pDataGen the data generator 
	 * @param PropertyName the property name ,must be defined in the data generator) 
	 * @param propertyValue the value to set
	 * @param testPropetyExist true if we want to throw an exception if a property doesn't exist. false to ignore
	 * @throws PropertyConfigurationException
	 */
	public static void configure(DataGenerator pDataGen, String PropertyName, Object propertyValue ,boolean testPropetyExist) 
			throws PropertyConfigurationException {
		
		try {
			if (testPropetyExist){
				// add the getProperty method because setProperty doesn't throw  exceptions if the property doesn't exist ..
				BeanUtils.getProperty(pDataGen, PropertyName);
			}
			BeanUtils.setProperty(pDataGen, PropertyName, propertyValue);

		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		} catch (NoSuchMethodException e) {
			throw new PropertyConfigurationException("Property " + PropertyName + " not found in data generator " + pDataGen.getClass());
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
		 configure( pDataGen,  PropertyName,  propertyValue , false); 
	}
	
	

}
