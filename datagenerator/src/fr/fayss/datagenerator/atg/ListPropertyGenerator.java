/**
 * 
 */
package fr.fayss.datagenerator.atg;

import fr.fayss.datagenerator.structure.MultiTypeCollectionGenerator;

/**
 * @author fayss
 *
 */
public class ListPropertyGenerator extends PropertyGenerator {

	/**
	 * @param pPropertyName
	 */
	public ListPropertyGenerator(String pPropertyName) {
		super(pPropertyName);
	}
	
	public ListPropertyGenerator (String pPropertyName, MultiTypeCollectionGenerator pDataGenerator){
		super(pPropertyName,pDataGenerator);
	}

}
