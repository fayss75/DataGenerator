/**
 * 
 */
package fr.fayss.datagenerator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author fayss
 * Singleton class that contains a Queue of datas to generate
 *
 */
public class GenerationBuffer {

	
	private Queue<DataConfiguration> mDataConfiguration;
	private static GenerationBuffer mGenerationBuffer ;
	
	
	
	public static GenerationBuffer getInstance () {
		
		if (mGenerationBuffer == null) {
			mGenerationBuffer = new GenerationBuffer () ;	
		}
		
		return mGenerationBuffer;
	}	
	
	
	private GenerationBuffer() {
		mDataConfiguration = new LinkedList<DataConfiguration>();
	}
	
	public void pushItem (DataConfiguration item) {
		mDataConfiguration.add(item);
		}
	
	public DataConfiguration popItem () {
		return mDataConfiguration.peek();
	}
}
