package fr.fayss.datagenerator.atg;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.InternalException;


/**
 * Generate an add item script
 * 
 * example : 
 * <add-item item-descriptor="ITEM_DESCRIPTOR_NAME" id="ITEM_ID">
 *  	<set-property name="PROP_NAME1">PROP_VALUE1</set-property>
 *		<set-property name="PROP_NAME2">PROP_VALUE2</set-property>
 *		<set-property name="PROP_NAME3">PROP_VALUE3</set-property>
 *		<set-property name="PROP_NAME4">PROP_VALUE4</set-property>
 * 	</add-item>
 * Item id can be generated buy an ItemDescriptorIdGenerator or can be defined using the generate(Object RepositoryId) method.
 * Each property name/value is generated by its own @PropertyDataGenerator
 *  
 * @author fayss
 *
 */
public @Getter @Setter class RepositoryItemGenerator implements DataFormatter{


	private String mItemName;
	private List<PropertyGenerator> mPropertyList ;

	private ItemDescriptorIdGenerator mIdGenerator ;
	private Object mValue = null;

	private static final Class<? extends ItemDescriptorIdGenerator>  DEFAULT_ID_GENERATOR =  ItemDescriptorIdGenerator.class;


	public RepositoryItemGenerator (String pItemName,List<PropertyGenerator> propertyList ,ItemDescriptorIdGenerator pIdGenerator ) {
		mItemName = pItemName;
		mPropertyList = propertyList;
		mIdGenerator = pIdGenerator;

	}


	public RepositoryItemGenerator (String pItemName,List<PropertyGenerator> pPropertyList ) {
		mItemName = pItemName;
		mPropertyList = pPropertyList;
			try {
				mIdGenerator = DEFAULT_ID_GENERATOR.newInstance();
			} catch (InstantiationException | IllegalAccessException ex) {
				throw new InternalException(ex);
			}


	}

	public void generateStartAddItemTag (StringBuilder pStringBuilder) {

		pStringBuilder.append("<add-item item-descriptor=\"").append(mItemName);

		if (getValue() == null){
			pStringBuilder.append("\" id=\"").append(getIdGenerator().generate());
		} else {
			pStringBuilder.append("\" id=\"").append(getValue());
		}

		pStringBuilder.append("\">\n");
	}

	
	public void generateProperty (StringBuilder pStringBuilder) {
		for (DataGenerator property : getPropertyList()) {
			pStringBuilder.append(property.generate());
		}
	}
	
	public void generateEndAddItemTag (StringBuilder pStringBuilder) {
		pStringBuilder.append("</add-item>\n");
	}




	@Override
	public Object generate() {
		
		StringBuilder sb = new StringBuilder();

		generateStartAddItemTag (sb);

		generateProperty (sb);

		generateEndAddItemTag (sb);
		
		return sb.toString();
	}



	@Override
	public void configure(DataConfiguration pDataconfig) {
		// TODO Auto-generated method stub

	}

}
