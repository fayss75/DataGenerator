package fr.fayss.datagenerator.atg;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.atg.xml.DataConfig;
import fr.fayss.datagenerator.atg.xml.PropertyConfig;

public class AtgTemplateGenerator {


	public static final String XML_BINDING_PACKAGE = "fr.fayss.datagenerator.atg.xml" ;

	public static Map<String, DataGenerator> dataGenMap  = new HashMap<String, DataGenerator> ();
	public static final String File_Path ="C:\\Users\\fayss\\Documents\\GitHub\\DataGenerator\\datagenerator\\configurationsV2\\Config2.xml" ;  
	public static void main(String[] args) {


		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			DataConfig dataconfig = (DataConfig) unmarshaller.unmarshal(new File(File_Path));



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


	/**
	 * Bind the xml File to java objects
	 * @param pXmlFile
	 * @return a DataConfig that contains the mapping from the xml file 
	 * @throws JAXBException
	 */
	public DataConfig createDataConfig (File pXmlFile) throws JAXBException {
		JAXBContext	jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return (DataConfig) unmarshaller.unmarshal(new File(File_Path));
	}




	public void generateTemplatefromXml (File pXmlFile) {


		try {
			DataConfig dataConfig = createDataConfig(pXmlFile);



			for (fr.fayss.datagenerator.atg.xml.DataConfig.DataGenerator dagaGenConfig : dataConfig.getDataGenerator()){


				String dataGenClassName = dagaGenConfig.getClazz() ;

				@SuppressWarnings("unchecked")
				Class  <DataGenerator> dataGenClass =  (Class  <DataGenerator>) Class.forName(dataGenClassName);


				DataGenerator DataGenIns = dataGenClass.newInstance();
				if (!StringUtils.isBlank(dagaGenConfig.getId())) {
					registerDataGenerator(dagaGenConfig.getId(), DataGenIns);
				}


				for (PropertyConfig propertyConfig : dagaGenConfig.getPropertyConfig()) {
					String configName = propertyConfig.getName() ;
					String configType = propertyConfig.getType() ;
				}

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

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
