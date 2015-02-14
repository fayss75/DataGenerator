package fr.fayss.datagenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
















import fr.fayss.datagenerator.atg.PropertyGenerator;
import fr.fayss.datagenerator.atg.RepositoryItemGenerator;
import fr.fayss.datagenerator.atg.ReferencePropertyGenerator;
import fr.fayss.datagenerator.structure.CollectionGenerator;
import fr.fayss.datagenerator.structure.SimpleCollectionGenerator;
import fr.fayss.datagenerator.structure.TreeGenerator;
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

		BufferedWriter bw = null;
		try {
			File file = new File("F:/filename2.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			DataGenerator productGen = createProductGen ();

			bw.write(productGen.generate().toString());

			GenerationBuffer generationBuffer = 
					GenerationBuffer.getInstance();

			while (generationBuffer.hasNext()){

				DataConfiguration config = generationBuffer.popItem();

				try {
					DataConfigurationTools.configure(config);
				} catch (PropertyConfigurationException e) {
					e.printStackTrace();
				}

				bw.write(((DataGenerator)config.getPropertyConfiguration(DataConfigurationTools.DATA_GENERATOR_INSTANCE)).generate().toString());
			}



		}catch (IOException ioe){
			ioe.printStackTrace();
		}
		finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public static List<PropertyGenerator> productpropertyBuilder () {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator stringGenerator = new StringGenerator();

		dgList.add(new PropertyGenerator("prdString1", stringGenerator));
		dgList.add(new PropertyGenerator("prdString2", stringGenerator));
		dgList.add(new PropertyGenerator("prddefault"));
		dgList.add(new PropertyGenerator("prddoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("prdintProperty", new IntegerGenerator()));

		return dgList ;

	}


	public static List<PropertyGenerator> catpropertyBuilder () {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator stringGenerator = new StringGenerator();

		dgList.add(new PropertyGenerator("catString1", stringGenerator));
		dgList.add(new PropertyGenerator("catString2", stringGenerator));
		dgList.add(new PropertyGenerator("catdefault"));
		dgList.add(new PropertyGenerator("catdoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("catintProperty", new IntegerGenerator()));

		return dgList ;

	}	


	public static List<PropertyGenerator> skupropertyBuilder () {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator stringGenerator = new StringGenerator();
		dgList.add(new PropertyGenerator("skuString1", stringGenerator));
		dgList.add(new PropertyGenerator("skuString2", stringGenerator));
		dgList.add(new PropertyGenerator("skudefault"));
		dgList.add(new PropertyGenerator("skudoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("skuintProperty", new IntegerGenerator()));

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

		return  new RepositoryItemGenerator("sku", skupropertyBuilder()) ;
	}

	public static RepositoryItemGenerator createProductGen (){


		RepositoryItemGenerator skuGen =  new RepositoryItemGenerator("sku", skupropertyBuilder()) ;
		skuGen.getIdGenerator().setPrefix("sku");

		RepositoryItemGenerator prdGen =  new RepositoryItemGenerator("product", productpropertyBuilder()) ;
		prdGen.getIdGenerator().setPrefix("prd");
		ReferenceDataGenerator refDataGenerator = new ReferencePropertyGenerator(skuGen,skuGen.getIdGenerator(),"childSkus");
		CollectionGenerator treeGen = new SimpleCollectionGenerator(refDataGenerator);
		prdGen.getPropertyList().add(new PropertyGenerator("childSkus", treeGen));


		RepositoryItemGenerator catGen =  new RepositoryItemGenerator("category", catpropertyBuilder()) ;
		catGen.getIdGenerator().setPrefix("cat");
		ReferenceDataGenerator refCatDataGenerator = new ReferencePropertyGenerator(catGen,catGen.getIdGenerator(),"childCategories");
		TreeGenerator treeCatGen = new TreeGenerator(refCatDataGenerator);
		catGen.getPropertyList().add(new PropertyGenerator("childCategories", treeCatGen));

		ReferenceDataGenerator refPrdDataGenerator = new ReferencePropertyGenerator(prdGen,prdGen.getIdGenerator(),"childProducts");
		CollectionGenerator treePrdGen = new SimpleCollectionGenerator(refPrdDataGenerator);
		catGen.getPropertyList().add(new PropertyGenerator("childProducts", treePrdGen));		

		return catGen;
	}

}
