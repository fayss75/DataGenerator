package fr.fayss.datagenerator;

import java.util.ArrayList;
import java.util.List;



import fr.fayss.datagenerator.atg.ItemDescriptorIdGenerator;
import fr.fayss.datagenerator.atg.PropertyDataGenerator;
import fr.fayss.datagenerator.atg.RepositoryItemDataGenerator;
import fr.fayss.datagenerator.types.DoubleDataGenerator;
import fr.fayss.datagenerator.types.IntegerDataGenerator;
import fr.fayss.datagenerator.types.StringDataGenerator;


/**
 * this is a sample class
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
	
	public static List<DataGenerator> propertyBuilder () {
		
		ArrayList<DataGenerator> dgList = new ArrayList<DataGenerator>();
		StringDataGenerator sdg = new StringDataGenerator();
		dgList.add(new PropertyDataGenerator("prop1", sdg));
		dgList.add(new PropertyDataGenerator("prop2", sdg));
		dgList.add(new PropertyDataGenerator("doubleProperty", new DoubleDataGenerator()));
		dgList.add(new PropertyDataGenerator("intProperty", new IntegerDataGenerator()));
		
		return dgList ;
		
	}
	
	public static void testException(){
		
		
IntegerDataGenerator integerDataGenerator = new IntegerDataGenerator ();


System.out.println("avant :" + integerDataGenerator.getStartInclusive());
			try {
				DataConfigurationTools.configure(integerDataGenerator, "ee", 5);
			} catch (PropertyConfigurationException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("apres :" + integerDataGenerator.getStartInclusive());
		
		
	}
	public static RepositoryItemDataGenerator createSkuGen (){
		
		DataGenerator idgenerator = new ItemDescriptorIdGenerator();
		try {
			DataConfigurationTools.configure(idgenerator, "prefix", "sku");
		} catch (PropertyConfigurationException e) {
			e.printStackTrace();
		}
		
		
		return  new RepositoryItemDataGenerator("sku", propertyBuilder(), idgenerator) ;
	}

}
