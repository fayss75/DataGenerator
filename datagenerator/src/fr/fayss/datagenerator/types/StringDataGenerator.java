package fr.fayss.datagenerator.types;


import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomStringUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataGenerator;

public  @Getter @Setter  class StringDataGenerator implements DataGenerator{

	// define the size of the generated string 	
	private  Integer mStringLength = 5 ;



	@Override
	public Object generate() {

		return RandomStringUtils.random(getStringLength(),true,true);
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) {
		
		Object stringLenght = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.STRING_LENGTH);

		if (stringLenght != null && stringLenght instanceof Integer)
			setStringLength((Integer)stringLenght);

	}
}
