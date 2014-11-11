package fr.fayss.datagenerator.atg;

import java.util.ArrayList;
import java.util.List;

import fr.fayss.datagenerator.PropertyValueException;
import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class HierarchicalRepposiotryItemGenerator extends RepositoryItemGenerator {
	

	private String mChildProperyName ;

	private Integer mNbChild = 3;
	private Integer mDepth = 3 ;
	
	
	/**
	 * Generate hierarchical repositoryItems from the same type.
	 * A repository item contains a list of child repository items, and its child could contain also child repository Items
	 *
	 * @param pItemName is the item descriptor name
	 * @param pChildPropertyName is the name of the child property
	 * @param propertyList contains other property
	 * @param pIdGenerator an @ItemDescriptorIdGenerator for the id 
	 */
	public HierarchicalRepposiotryItemGenerator(String pItemName,String pChildPropertyName,
			List<PropertyGenerator> propertyList,
			ItemDescriptorIdGenerator pIdGenerator) {
		super(pItemName, propertyList, pIdGenerator);
		mChildProperyName = pChildPropertyName ;
	}
	
	
	@Override
	public Object generate() {
		
		List <String> ids = generateIds(getNbChild());
		return null;
	}
	
	
	/**
	 * Generates a list of ids. 
	 * CAUTION : this method do not garranty that all ids are different.
	 * Depending of the {@link ItemDescriptorIdGenerator} configuration, the same id could be generated several times
	 * @param  pQuantity
	 * @return A list a ids
	 * @throws PropertyValueException if pQuantity is not greater than zero 
	 */
	private List <String> generateIds (int pQuantity) {
		if (pQuantity <= 0){
			throw new PropertyValueException("Quantity must be a positive value, actual value:" + pQuantity);
		}
		
		List <String> ids = new ArrayList <String>(pQuantity);
		
		for (int i = 0 ; i < pQuantity ; i++ ){
			ids.add((String)getIdGenerator().generate());
		}
		
		return ids;
	}
	
}
