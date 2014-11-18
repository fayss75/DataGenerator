package fr.fayss.datagenerator;



/**
 * Interface of all data generators
 * 
 * @author fayss
 *
 */
public interface DataGenerator {

	/** Returns the generated data */
	public Object generate ();
	
	/**
	 * Configure properties of the data generator
	 * Not sure to keep this method....
	 * @param pDataconfig
	 * @throws PropertyConfigurationException
	 */
	public void configure ( DataConfiguration pDataconfig) throws PropertyConfigurationException ;
}
