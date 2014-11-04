/**
 * 
 */
package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class FloatDataGenerator implements DataGenerator {
	
	
	// the min value that can be generated
	private Float mStartInclusive = 1000f;
	
	// the max value that can be generated
	private Float mEndInclusive = 9000f;

	@Override
	public Object generate() {
		return RandomUtils.nextFloat(getStartInclusive(), getEndInclusive());
	}

	@Override
	public void configure(DataConfiguration pDataconfig) {
		Object startInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.START_INCLUSIVE);

		if (startInclusive != null && startInclusive instanceof Float)
			setStartInclusive((Float)startInclusive);
		
		
		Object endInclusive = pDataconfig.getPropertyConfiguration(DataConfigurationConstant.END_INCLUSIVE);

		if (endInclusive != null && endInclusive instanceof Float)
			setEndInclusive((Float)endInclusive);	

	}

}
