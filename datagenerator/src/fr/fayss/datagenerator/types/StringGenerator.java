package fr.fayss.datagenerator.types;



import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomStringUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

public  @Getter @Setter  class StringGenerator implements DataGenerator{

	// define the size of the generated string 	
	private  Integer mStringLength = 5 ;


	@Override
	public Object generate() {

		return RandomStringUtils.random(getStringLength(),true,true);
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}
}
