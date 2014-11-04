package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataGenerator;

/**
 * Class that handle Double data generation
 * 
 * 
 * @author fayss
 *
 */
public @Getter @Setter class DoubleDataGenerator implements DataGenerator {

	// the min value that can be generated
	private Double mStartInclusive = 1000d;
	
	// the max value that can be generated
	private Double mEndInclusive = 9000d;
	
	@Override
	public Object generate() {
		
		return RandomUtils.nextDouble(getStartInclusive(), getEndInclusive());
	}
	@Override
	public void configure(DataConfiguration pDataconfig) {
		Object startInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.START_INCLUSIVE);

		if (startInclusive != null && startInclusive instanceof Double)
			setStartInclusive((Double)startInclusive);
		
		
		Object endInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.END_INCLUSIVE);

		if (endInclusive != null && endInclusive instanceof Double)
			setEndInclusive((Double)endInclusive);	
		
	}
}
