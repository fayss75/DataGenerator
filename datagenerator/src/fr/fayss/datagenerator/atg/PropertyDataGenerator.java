package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.types.StringDataGenerator;

/**
 * Generate a property of an itemDescriptor
 * Example : <set-property name="PROPERTY_NAME">PROPER_VALUE</set-property>
 * @author fayss
 *
 */
public @Getter @Setter class PropertyDataGenerator implements DataGenerator {


	private  DataGenerator  mDataTypeGenerator ;
	private  String  mPropertyName;

	
	public PropertyDataGenerator (String pPropertyName, DataGenerator pDataGenerator){

		mDataTypeGenerator = pDataGenerator ;
		mPropertyName = pPropertyName;
		if (mDataTypeGenerator == null){
			mDataTypeGenerator = new StringDataGenerator();
		}
	}


	@Override
	public Object generate() {

		Object result = getDataTypeGenerator().generate();

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
