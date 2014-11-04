/**
 * 
 */
package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.types.IntegerDataGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class ItemDescriptorIdGenerator extends IntegerDataGenerator {
	
	
	private String mPrefix="";
	private String mSuffix="";

	@Override
	public Object generate() {
		
		StringBuilder builder = new StringBuilder() ;
		
		return builder.append(getPrefix()).append(super.generate()).append(getSuffix()).toString();
	}


}
