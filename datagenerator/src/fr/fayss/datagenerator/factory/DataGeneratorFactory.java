package fr.fayss.datagenerator.factory;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.fayss.datagenerator.atg.xml.DataConfig;

/**
 * @author fayss
 *
 */
public class DataGeneratorFactory {

	public static final String XML_BINDING_PACKAGE = "fr.fayss.datagenerator.atg.xml" ;


	public static DataConfig  parseXmlFile (File pXmlFile) throws DataConfigException{

		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			return (DataConfig) unmarshaller.unmarshal(pXmlFile);

		} catch (JAXBException e) {
			throw new DataConfigException (e);
		}
	}

	public static DataConfig  parseXmlFile (String pXmlFilePath) throws DataConfigException{
		return parseXmlFile (new File( pXmlFilePath));
	}

}
