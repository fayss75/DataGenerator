/**
 * 
 */
package fr.fayss.datagenerator;

/**
 * @author fayss
 *
 */
public interface DataFormatter extends DataGenerator {
	
	/**
	 * Generally used when the generator encapsulates the generated data in specific format
	 * this method should gave the possibility to use this format without calling the generator
	 * Example:
	 * let's say that the data format takes and IntegerGenerator and return the format: 
	 * "The Generated Value is [GenerateValue]." where [GenerateValue] is replace buy the integer generated.
	 * if we call the method generate () without parameter we could have this kind of result: 
	 *  "The Generated Value is 5."
	 *  "The Generated Value is 17."
	 *  
	 * Calling the method generate (19)  should garranty that  the result is :
	 *  "The Generated Value is 19."
	 * @param pDataValue
	 * @return
	 */
	public Object generate (Object pDataValue);

}
