package fr.fayss.datagenerator;

import java.lang.reflect.Field;

public class DataConfigurationTools {

	
	public void configure(DataGenerator pDataGen, String PropertyName, DataConfiguration pDataconfig ) 
			throws PropertyConfigurationException {
		
		Object propertyconfig = pDataconfig.getPropertyConfiguration().get(PropertyName);

		
		try {
			Field property = pDataGen.getClass().getField(PropertyName);
			if (propertyconfig != null && propertyconfig.getClass().isInstance(property.getClass())){
				property.set(pDataGen, propertyconfig);
			}
		} catch (SecurityException e) {
			throw new PropertyConfigurationException(e);
		} catch (NoSuchFieldException e) {
			throw new PropertyConfigurationException(e);
		} catch (IllegalArgumentException e) {
			throw new PropertyConfigurationException(e);
		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		}
		


	}
}
