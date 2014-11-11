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
 * Class that handle Double data generation
 * 
 * 
 * @author fayss
 *
 */
public @Getter @Setter class DoubleGenerator implements DataGenerator {

	// the min value that can be generated
	private Double mStartInclusive = 1000d;
	
	// the max value that can be generated
	private Double mEndInclusive = 9000d;
	
	// number of decimals
	private Integer mScale = 2 ;
	
	@Override
	public Object generate() {
		
		Double result = RandomUtils.nextDouble(getStartInclusive(), getEndInclusive());
		return new BigDecimal(result).setScale(getScale(), RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}
	
}
