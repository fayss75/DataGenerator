package fr.fayss.datagenerator.atg;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.atg.gsa.Dataconfig;
import fr.fayss.datagenerator.atg.gsa.PropertyConfig;

public class AtgTemplateGenerator {

	public static Map<String, DataGenerator> dataGenMap  = new HashMap<String, DataGenerator> ();
	public static final String File_Path ="C:\\Users\\fayss\\Documents\\GitHub\\DataGenerator\\datagenerator\\configurationsV2\\Config2.xml" ;  
	public static void main(String[] args) {
		
		
		try {
			JAXBContext jc = JAXBContext.newInstance("fr.fayss.datagenerator.atg.gsa");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			Dataconfig dataconfig = (Dataconfig) unmarshaller.unmarshal(new File(File_Path));
			
			
			
			System.out.println(dataconfig.getName());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void registerDataGenerator (String pId , DataGenerator pDataGenerator){
		dataGenMap.put(pId, pDataGenerator) ;
	}
	
	public DataGenerator getDataGeneratorById (String pId ){
		return dataGenMap.get(pId) ;
	}
	
	public void generateTemplatefromXml (File pXmlFile) {

		DataGenerator dg = null;
		
	
		
		try {
		JAXBContext jc = JAXBContext.newInstance("fr.fayss.datagenerator.atg.gsa");
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		
		Dataconfig dataconfig = (Dataconfig) unmarshaller.unmarshal(new File(File_Path));
		
		
		
		for (fr.fayss.datagenerator.atg.gsa.Dataconfig.DataGenerator dataGenXml : dataconfig.getDataGenerator()){
			
			
			String className = dataGenXml.getClazz() ;
			
			try {
				@SuppressWarnings("unchecked")
				Class  <DataGenerator> dataGenClass =  (Class  <DataGenerator>) Class.forName(className);
				
				
				DataGenerator DataGenIns = dataGenClass.newInstance();
				if (!StringUtils.isBlank(dataGenXml.getId())) {
					registerDataGenerator(dataGenXml.getId(), DataGenIns);
				}
				
				
				for (PropertyConfig propertyConfig : dataGenXml.getPropertyConfig()) {
					
					
					String configName = propertyConfig.getName() ;
					String configType = propertyConfig.getType() ;
					
					
					
					
					
				}
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
