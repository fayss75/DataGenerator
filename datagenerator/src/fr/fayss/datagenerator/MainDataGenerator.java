package fr.fayss.datagenerator;

import java.util.ArrayList;
import java.util.List;



import fr.fayss.datagenerator.atg.ItemDescriptorIdGenerator;
import fr.fayss.datagenerator.atg.PropertyGenerator;
import fr.fayss.datagenerator.atg.RepositoryItemGenerator;
import fr.fayss.datagenerator.types.DoubleGenerator;
import fr.fayss.datagenerator.types.IntegerGenerator;
import fr.fayss.datagenerator.types.StringGenerator;


/**
 * Sample class
 * 
 * @author fayss
 *
 */
public class MainDataGenerator {
	
	
	public static void main(String[] args) {
		DataGenerator skuGen = createSkuGen ();
			System.out.println(skuGen.generate());
			System.out.println(skuGen.generate());
		//testException();
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
	
	public static void testException(){
		
		
IntegerGenerator integerDataGenerator = new IntegerGenerator ();


System.out.println("avant :" + integerDataGenerator.getStartInclusive());
			try {
				DataConfigurationTools.configure(integerDataGenerator, "ee", 5);
			} catch (PropertyConfigurationException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("apres :" + integerDataGenerator.getStartInclusive());
		
		
	}
	public static RepositoryItemGenerator createSkuGen (){
		
		ItemDescriptorIdGenerator idgenerator = new ItemDescriptorIdGenerator();
		try {
			DataConfigurationTools.configure(idgenerator, "prefix", "sku");
		} catch (PropertyConfigurationException e) {
			e.printStackTrace();
		}
		
		
		//return  new RepositoryItemGenerator("sku", propertyBuilder(), idgenerator) ;
		return  new RepositoryItemGenerator("sku", propertyBuilder()) ;
	}

}
