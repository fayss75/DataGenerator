package fr.fayss.datagenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class DataConfigurationTools {


	public void confugre ( DataGenerator pDataGen,DataConfiguration pDataconfig)
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
	public void configure(DataGenerator pDataGen, String PropertyName, Object propertyValue ) 
			throws PropertyConfigurationException {

		try {
			BeanUtils.setProperty(pDataGen, PropertyName, propertyValue);

		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		}


	}

	/**
	 * 
	 * @param pDataGen
	 * @param pProperties
	 * @throws PropertyConfigurationException
	 */
	public void configure(DataGenerator pDataGen, Map<String, Object> pProperties ) 
			throws PropertyConfigurationException {

		try {
			BeanUtils.populate(pDataGen, pProperties);

		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		}


	}


	/**
	 * 
	 * @param pDataGen
	 * @param pProperties
	 * @throws PropertyConfigurationException
	 */
	public void configure(DataGenerator pDataGen, DataConfiguration pDataconfig) 
			throws PropertyConfigurationException {

		for (String key : pDataconfig.getAllConfiguredPropertyName()) {
			configure( pDataGen,  key,  pDataconfig.getPropertyConfiguration(key) ); 
		}

	}



}
