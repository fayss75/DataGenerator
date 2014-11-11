package fr.fayss.datagenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DataConfigurationTools {


	public static void configure ( DataGenerator pDataGen,DataConfiguration pDataconfig)
			throws PropertyConfigurationException {

		for (String property : pDataconfig.getAllConfiguredPropertyName()) {
			Object propertyconfig = pDataconfig.getPropertyConfiguration(property);
			configure( pDataGen,  property,  propertyconfig ) ;
		}
	}

	/**
	 * 
	 * @param pDataGen
	 * @param PropertyName
	 * @param propertyValue
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

	/**
	 * 
	 * @param pDataGen
	 * @param pProperties
	 * @throws PropertyConfigurationException
	 */
	public static void configure(DataGenerator pDataGen, Map<String, Object> pProperties ) 
			throws PropertyConfigurationException {

			for (String key: pProperties.keySet()) {
				configure(pDataGen, key,pProperties.get(key));
			}
	}




}
