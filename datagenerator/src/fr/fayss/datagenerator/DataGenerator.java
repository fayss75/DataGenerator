package fr.fayss.datagenerator;


/**
 * interface of all data generators
 * 
 * @author fayss
 *
 */
public interface DataGenerator {

	
	public Object generate ();
	
	
	public void configure (DataConfiguration pDataconfig) throws PropertyConfigurationException ;
}
