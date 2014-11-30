/**
 * 
 */
package fr.fayss.datagenerator.sql;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.types.IntegerGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class IntegerColumnGenerator extends IntegerGenerator implements ColumnGenerator {
	
	private Object mValue;
	private String mColumnName  ;

	public IntegerColumnGenerator () {
		mColumnName = DEFAULT_COLUMN_NAME;
	}
	
	public IntegerColumnGenerator (String pColumnName) {
		mColumnName = pColumnName;
	}

	@Override
	public Object generate() {
		if (mValue  == null) {
			return super.generate();
		} else {
			return mValue;
		}
	}

	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		DataConfigurationTools.configure(this, pDataconfig);
	}


}
