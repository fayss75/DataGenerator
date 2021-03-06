package fr.fayss.datagenerator.structure;

import fr.fayss.datagenerator.DataGenerator;

/**
 * This interface is used by any collection generator 
 * A Collection generator use a separator between generated data
 * The getter and the setter of the separator must be defined.
 * The value must be saved on a way that we can get it using the method getSeparator
 * 
 * @author fayss
 */
public interface CollectionGenerator  extends DataGenerator {
	
	/**
	 * @return the separator value
	 */
	public String getSeparator ();
	
	
	/**
	 * Set the separator value
	 * @param pSeparator the value of the separator
	 */
	public void setSeparator (String pSeparator) ;

}
