package fr.fayss.datagenerator.structure;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationBuffer;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.atg.ItemDescriptorIdGenerator;

public @Getter @Setter class TreeGenerator implements DataGenerator {

	public static final String DATA_KEY_PREFIX = "TreeGenerator" ;
	public static final String CURRENT_DEPTH ="TreeGenerator.curenDepth";
	public static final String POSITION ="TreeGenerator.position";


	private DataGenerator mDataGenerator ;


	/** 
	 * Define the number of direct child items 
	 * Default vale is 3 
	 */
	private Integer mNbChild = 3;

	/** 
	 * Define the depth of the tree 
	 * Default value is 3*/
	private Integer mDepth = 3 ;


	public TreeGenerator(DataGenerator pDataGenerator, Integer pNbchild, Integer pDepth ){
		mDataGenerator = pDataGenerator; 
		mNbChild = pNbchild ;
		mDepth = pDepth ;

	}

	public TreeGenerator(DataGenerator pDataGenerator ){
		mDataGenerator = pDataGenerator; 

	}


	@Override
	public Object generate() {
		Object result =getgenerationBuffer().getData(CURRENT_DEPTH);



		if (result != null){

			Integer currentDepth = (Integer)getgenerationBuffer().getData(CURRENT_DEPTH);
			Integer currentChild =(Integer)getgenerationBuffer().getData(POSITION);


			if (currentChild ==  Math.pow(mNbChild, mDepth - currentDepth 	) ){
				getgenerationBuffer().setData(CURRENT_DEPTH, currentDepth - 1);
				getgenerationBuffer().setData(POSITION, 1);
			} else {
				getgenerationBuffer().setData(POSITION,currentChild+ 1);
			}

			if (currentDepth > 1 ){
				SingleTypeCollectionGenerator collectionGen = new SingleTypeCollectionGenerator (mDataGenerator,mNbChild) ;
				return collectionGen.generate();
			} 




		} else {
			getgenerationBuffer().setData(CURRENT_DEPTH, mDepth - 1);
			getgenerationBuffer().setData(POSITION, 1);
			SingleTypeCollectionGenerator collectionGen = new SingleTypeCollectionGenerator (mDataGenerator,mNbChild) ;
			return collectionGen.generate();
		}
		return "";

	}

	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		// TODO Auto-generated method stub

	}

	private GenerationBuffer getgenerationBuffer () {
		return GenerationBuffer.getInstance();
	}

}
