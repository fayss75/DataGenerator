package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
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
	private double mStartInclusive = 1000;
	
	// the max value that can be generated
	private double mEndInclusive = 9000;
	
	@Override
	public Object generate() {
		
		return RandomUtils.nextDouble(getStartInclusive(), getEndInclusive());
	}
	@Override
	public void configure(DataConfiguration pDataconfig) {
		// TODO use configuration
		
	}
}
