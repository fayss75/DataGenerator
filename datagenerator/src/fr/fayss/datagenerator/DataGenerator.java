package fr.fayss.datagenerator;



/**
 * Interface of all data generators
 * 
 * @author fayss
 *
 */
public interface DataGenerator {

	/** Returns the generated data 
	 * this method should call the method isGeneratorConfigured () before 
	 * generating any data.
	 * */
	public Object generate ();
	
	/**
	 * Configure properties of the data generator
	 * Not sure to keep this method....
	 * @param pDataconfig
	 * @throws PropertyConfigurationException
	 */
	public void configure ( DataConfiguration pDataconfig) throws PropertyConfigurationException ;
	
	
	/**
	 * Verify if the dataGenerator has the minimum configuration to work.
	 * if we used the Contructors of the class , if shoud be ok , but if we use
	 * the class.newInstance () method, we have to verify that all the configuration is done.  
	 * Is means that if we run the generate method we will not have exceptions related
	 * to not defined mandatory property for example.
	 * 
	 * @return true if the generator is configured, false if not
	 */
	//public boolean isGeneratorConfigured ();
}
