package fr.fayss.datagenerator.atg;


import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.InternalException;

/**
 * Generate a property of an itemDescriptor
 * Example : <set-property name="PROPERTY_NAME">PROPER_VALUE</set-property>
 * @author fayss
 *
 */
public @Getter @Setter class PropertyGenerator implements DataFormatter{


	private  DataGenerator  mDataTypeGenerator ;
	private  String  mPropertyName;

	private static final String DEFAULT_DATAGENERATOR = "fr.fayss.datagenerator.types.StringGenerator";

	public PropertyGenerator (String pPropertyName){
		mPropertyName = pPropertyName;
		try {
			mDataTypeGenerator = (DataGenerator) Class.forName(DEFAULT_DATAGENERATOR).newInstance();

		} catch (ClassNotFoundException cnfe) {
			throw new InternalException(cnfe);
		} catch (InstantiationException ie) {
			throw new InternalException(ie);
		} catch (IllegalAccessException iae) {
			throw new InternalException(iae);
		}

	}

	public PropertyGenerator (String pPropertyName, DataGenerator pDataGenerator){
		mDataTypeGenerator = pDataGenerator ;
		mPropertyName = pPropertyName;
	}


	@Override
	public Object generate() {
		return  generate(null) ;
	}

	@Override
	public Object generate(Object pvalues) {

		Object result = pvalues ;
		if(pvalues == null) {
			result = getDataTypeGenerator().generate();
		}

		StringBuilder sb = new StringBuilder();

		sb.append("\t<set-property name=\"").append(mPropertyName).append("\">")
		.append(result).append("</set-property>\n");

		return sb.toString();
	}



	@Override
	public void configure(DataConfiguration pDataconfig) {
		// TODO Auto-generated method stub

	}




}
