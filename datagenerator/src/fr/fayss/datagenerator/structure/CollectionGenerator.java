/**
 * 
 */
package fr.fayss.datagenerator.structure;

import fr.fayss.datagenerator.DataGenerator;

/**
 * @author fayss
 *
 */
public interface CollectionGenerator extends DataGenerator {
	
	
	public String getSeparator ();
	public void setSeparator (String pSeparator) ;

}
