/**
 * 
 */
package fr.fayss.datagenerator.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * @author fayss
 *
 */
public @Getter @Setter class FloatDataGenerator implements DataGenerator {
	
	
	// the min value that can be generated
	private Float mStartInclusive = 1000f;
	
	// the max value that can be generated
	private Float mEndInclusive = 9000f;
	
	// number of decimals
	private int mScale = 2 ;

	@Override
	public Object generate() {
		Float result =  RandomUtils.nextFloat(getStartInclusive(), getEndInclusive());
		return new BigDecimal(result).setScale(getScale(), RoundingMode.HALF_UP).floatValue();
	}

	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}

}
