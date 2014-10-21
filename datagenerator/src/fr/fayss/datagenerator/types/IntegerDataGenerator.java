package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataGenerator;

public @Getter @Setter  class IntegerDataGenerator implements DataGenerator {

	// the min value that can be generated
	private int mStartInclusive = 1000;
	
	// the max value that can be generated
	private int mEndInclusive = 9000;
	
	@Override
	public Object generate() {
		return RandomUtils.nextInt(getStartInclusive(), getEndInclusive());
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) {
		Object startInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.START_INCLUSIVE);

		if (startInclusive != null && startInclusive instanceof Integer)
			setStartInclusive((Integer)startInclusive);
		
		
		Object endInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.END_INCLUSIVE);

		if (endInclusive != null && endInclusive instanceof Integer)
			setEndInclusive((Integer)endInclusive);	
		
	}
}
