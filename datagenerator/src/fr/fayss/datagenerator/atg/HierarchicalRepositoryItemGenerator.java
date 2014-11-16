package fr.fayss.datagenerator.atg;

import java.util.ArrayList;
import java.util.List;

import fr.fayss.datagenerator.InternalException;
import fr.fayss.datagenerator.PropertyValueException;
import fr.fayss.datagenerator.types.DoubleGenerator;
import fr.fayss.datagenerator.types.IntegerGenerator;
import fr.fayss.datagenerator.types.StringGenerator;
import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class HierarchicalRepositoryItemGenerator extends RepositoryItemGenerator {
	

	private PropertyGenerator mChildProperyGenerator ;

	private static final Class<? extends PropertyGenerator> DEFAULT_CHILD_PROP_GENERATOR = PropertyGenerator.class;
	
	private Integer mNbChild = 3;
	private Integer mDepth = 1 ;
	
	
	/**
	 * Generate hierarchical repositoryItems from the same type.
	 * A repository item contains a list of child repository items, and its child could contain also child repository Items
	 *
	 * @param pItemName is the item descriptor name
	 * @param pChildPropertyName is the name of the child property
	 * @param propertyList contains other property
	 * @param pIdGenerator an @ItemDescriptorIdGenerator for the id 
	 */
	public HierarchicalRepositoryItemGenerator(String pItemName,String pChildPropertyName,
			List<PropertyGenerator> propertyList,
			ItemDescriptorIdGenerator pIdGenerator) {
		
		super(pItemName, propertyList, pIdGenerator);
		
		try {
			mChildProperyGenerator = DEFAULT_CHILD_PROP_GENERATOR.newInstance();
			mChildProperyGenerator.setPropertyName(pChildPropertyName);
			
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException (ex);
		}
	}
	
	public HierarchicalRepositoryItemGenerator(String pItemName,String pChildPropertyName,
			List<PropertyGenerator> propertyList) {
		
		super(pItemName, propertyList);
		
		try {
			mChildProperyGenerator = DEFAULT_CHILD_PROP_GENERATOR.newInstance();
			mChildProperyGenerator.setPropertyName(pChildPropertyName);
			
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException (ex);
		}
	}
	
	
	private String formatIds (List <String> ids) {
		StringBuilder sb = new StringBuilder();
		if (ids.size() > 0) ;
		sb.append(ids.get(0));
		
		for (int i = 1; i < ids.size(); i++) {
			sb.append(",");
			sb.append(ids.get(i));
		}
		
		return sb.toString();
		
	}
	
	@Override
	public Object generate() {
		
		StringBuilder sb = new StringBuilder();
		
		if (getDepth() == 0){
			return super.generate();
		} else {
			
			List <String> ids = generateIds(getNbChild());
			getChildProperyGenerator().setValue(formatIds(ids));
			
			super.generateStartAddItemTag(sb);
			super.generateProperty(sb);
			sb.append(getChildProperyGenerator().generate());
			super.generateEndAddItemTag(sb);
			
			
//			HierarchicalRepposiotryItemGenerator subItem = new HierarchicalRepposiotryItemGenerator(
//					getItemName(),getChildProperyGenerator().getPropertyName(),
//					getPropertyList(),getIdGenerator());
//			
//			subItem.setDepth(getDepth() - 1);
			this.setDepth(getDepth() - 1);
			
			
			for (String childId : ids) {
				
				//subItem.setValue(childId);
				this.setValue(childId);
				
				//sb.append(subItem.generate());
				sb.append(this.generate());
			}
			
		}
		
		return sb.toString();
	}
	
	
	/**
	 * Generates a list of ids. 
	 * CAUTION : this method do not garranty that all ids are different.
	 * Depending of the {@link ItemDescriptorIdGenerator} implemehtation, the same id could be generated several times
	 * @param  pQuantity of id generated, must be a positive number
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
	
	
	
	public static void main(String[] args) {
		
		ItemDescriptorIdGenerator idgenerator = new ItemDescriptorIdGenerator();
		idgenerator.setPrefix("cat");
		
		HierarchicalRepositoryItemGenerator item = new HierarchicalRepositoryItemGenerator("category", "childCategory", propertyBuilder (), idgenerator);
		
		System.out.println(item.generate());
		
	}
	
	public static List<PropertyGenerator> propertyBuilder () {
		
		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator sdg = new StringGenerator();
		dgList.add(new PropertyGenerator("prop1", sdg));
		dgList.add(new PropertyGenerator("prop2", sdg));
		dgList.add(new PropertyGenerator("default"));
		dgList.add(new PropertyGenerator("doubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("intProperty", new IntegerGenerator()));
		
		return dgList ;
		
	}
	
}
