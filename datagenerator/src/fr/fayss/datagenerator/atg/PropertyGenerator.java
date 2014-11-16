package fr.fayss.datagenerator.atg;


import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.InternalException;
import fr.fayss.datagenerator.PropertyValueException;
import fr.fayss.datagenerator.types.StringGenerator;

/**
 * Generate a property of an itemDescriptor
 * Example : <set-property name="PROPERTY_NAME">PROPER_VALUE</set-property>
 * @author fayss
 *
 */
public @Getter @Setter class PropertyGenerator implements DataFormatter{


	private DataGenerator  mDataTypeGenerator ;
	private String  mPropertyName;
	private Object mValue = null;

	private static final Class<? extends DataGenerator> DEFAULT_DATAGENERATOR = StringGenerator.class;

	
	public PropertyGenerator (){
		try {
			mPropertyName = "PROPERTY_NAME";
			mDataTypeGenerator =  DEFAULT_DATAGENERATOR.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException(ex);
		}
	}
	
	public PropertyGenerator (String pPropertyName){
			try {
				mPropertyName = pPropertyName;
				mDataTypeGenerator =  DEFAULT_DATAGENERATOR.newInstance();
			} catch (InstantiationException | IllegalAccessException ex) {
				throw new InternalException(ex);
			}
	}
	
	public PropertyGenerator (String pPropertyName, DataGenerator pDataGenerator){
		mDataTypeGenerator = pDataGenerator ;
		mPropertyName = pPropertyName;
	}


	@Override
	public Object generate() {
		
		Object result = null;
		
		if(getValue() != null) {
			result = getValue();
		} else if (getDataTypeGenerator() != null){
			result = getDataTypeGenerator().generate();
		} else {
			throw new PropertyValueException("Value and DataTypeGenerator properties can not be both null");
		}

		
		StringBuilder sb = new StringBuilder();

		sb.append("\t<set-property name=\"").append(getPropertyName()).append("\">")
		.append(result).append("</set-property>\n");

		return sb.toString();
	}




	@Override
	public void configure(DataConfiguration pDataconfig) {
		// TODO Auto-generated method stub

	}




}
