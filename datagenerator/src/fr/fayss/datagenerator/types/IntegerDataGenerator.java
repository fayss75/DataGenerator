package fr.fayss.datagenerator.types;


import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

public @Getter @Setter  class IntegerDataGenerator implements DataGenerator {

	// the min value that can be generated
	private Integer mStartInclusive = 1000;
	
	// the max value that can be generated
	private Integer mEndInclusive = 9000;
	
	@Override
	public Object generate() {
		return RandomUtils.nextInt(getStartInclusive(), getEndInclusive());
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}
}
