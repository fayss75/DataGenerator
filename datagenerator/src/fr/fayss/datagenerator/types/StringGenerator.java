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
	private String mPrefix="";
	private String mSuffix="";

	@Override
	public Object generate() {
		StringBuilder sb = new StringBuilder();
		return sb.append(getPrefix()).
				append(RandomStringUtils.random(getStringLength(),true,false)).
				append(getSuffix()).toString();
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		DataConfigurationTools.configure(this, pDataconfig);
	}
}
