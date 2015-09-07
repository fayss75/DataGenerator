package fr.fayss.datagenerator.atg;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.InternalException;
import fr.fayss.datagenerator.PropertyConfigurationException;


/**
 * Generate an add item script for a repository item
 * 
 * example : 
 * <add-item item-descriptor="ITEM_DESCRIPTOR_NAME" id="ITEM_ID">
 *  	<set-property name="PROP_NAME1">PROP_VALUE1</set-property>
 *		<set-property name="PROP_NAME2">PROP_VALUE2</set-property>
 *		<set-property name="PROP_NAME3">PROP_VALUE3</set-property>
 *		<set-property name="PROP_NAME4">PROP_VALUE4</set-property>
 * 	</add-item>
 * Repository id can be generated by an ItemDescriptorIdGenerator or can be defined using the generate(Object RepositoryId) method.
 * Each property of the repository item (name/value)  is generated by its own PropertyDataGenerator
 *  
 * @author fayss
 *
 */
public @Getter @Setter class RepositoryItemGenerator implements DataFormatter{
	
	
	/** Define the default Class to use for id generation if idgenerator and value properties are not defined */
	private static final Class<? extends ItemDescriptorIdGenerator>  DEFAULT_ID_GENERATOR =  ItemDescriptorIdGenerator.class;
	
	
	/** 
	 * Define the item descriptor name
	 * Default value is ITEM_NAME 
	 */
	private String mItemName = "ITEM_NAME";

	/** Define the list of properties of the repository item to generate */
	private List<PropertyGenerator> mPropertyList ;

	/**
	 * Define the data generator for repository id of the item 
	 * This generator will be ignored if mValue property is defined
	 */
	private ItemDescriptorIdGenerator mIdGenerator ;

	/**
	 * Define the value of the repository id. 
	 * If mValue is not null , ignore the idGenerator property
	 */
	private Object mValue = null;


	/**
	 * Constructor
	 * @param pItemName the name of the repository item
	 * @param propertyList the list of properties of the item descriptor to generate
	 * @param pIdGenerator the repository id generator
	 */
	public RepositoryItemGenerator (String pItemName,List<PropertyGenerator> propertyList ,ItemDescriptorIdGenerator pIdGenerator ) {
		mItemName = pItemName;
		mPropertyList = propertyList;
		mIdGenerator = pIdGenerator;
	}

	
	public RepositoryItemGenerator  (){
		try {
			mIdGenerator = DEFAULT_ID_GENERATOR.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException(ex);
		}
	}

	/**
	 * Constructor
	 * @param pItemName the name of the repository item
	 * @param pPropertyList the list of properties of the item descriptor to generate
	 */
	public RepositoryItemGenerator (String pItemName,List<PropertyGenerator> pPropertyList ) {
		mItemName = pItemName;
		mPropertyList = pPropertyList;
		try {
			mIdGenerator = DEFAULT_ID_GENERATOR.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException(ex);
		}
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
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}

	/**
	 * Generate the Start tag for and add item
	 * example : 
	 * 		<add-item item-descriptor="ITEM_NAME" id="ITEM_ID">
	 * if mValue property is defined we use it as the repositoryId
	 * if not, we call the idGenerator
	 * 
	 * @param pStringBuilder container that we used to append generated data
	 */
	public void generateStartAddItemTag (StringBuilder pStringBuilder) {

		pStringBuilder.append("<add-item item-descriptor=\"").append(getItemName());
		
		if (getValue() == null){
			pStringBuilder.append("\" id=\"").append(getIdGenerator().generate());
		} else {
			pStringBuilder.append("\" id=\"").append(getValue());
		}
		pStringBuilder.append("\">\n");
	}


	/**
	 * Generate the property value for each property of the repository item
	 * example :
	 * 		<set-property name="PROP_NAME1">PROP_VALUE1</set-property>
	 *		<set-property name="PROP_NAME2">PROP_VALUE2</set-property>
	 *		<set-property name="PROP_NAME3">PROP_VALUE3</set-property>
	 *
	 * @param pStringBuilder container that we used to append generated data
	 */
	public void generateProperty (StringBuilder pStringBuilder) {
		for (DataGenerator property : getPropertyList()) {
			pStringBuilder.append(property.generate());
		}
	}

	/**
	 * Generate the end tag for and add item
	 * example : 
	 * 		</add-item>
	 * 
	 * @param pStringBuilder container that we used to append generated data
	 */
	public void generateEndAddItemTag (StringBuilder pStringBuilder) {
		pStringBuilder.append("</add-item>\n");
	}


	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return !StringUtils.isBlank(getItemName()) &&
				(getValue() != null || getIdGenerator() != null) &&
				getPropertyList() != null  &&
				!getPropertyList().isEmpty() ;
	}
}
