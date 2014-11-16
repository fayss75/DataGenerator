/**
 * 
 */
package fr.fayss.datagenerator.structure;


import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.PropertyValueException;

/**
 * @author fayss
 *
 */
public  @Getter @Setter class SingleTypeCollectionGenerator implements CollectionGenerator {
	
	
	public int mQuantity = 5 ;
	public DataGenerator mDataGenerator ;
	public String mSeparator = "," ;
	

	public SingleTypeCollectionGenerator (DataGenerator pDataGenerator,int pQuantity){
		mDataGenerator = pDataGenerator ;
		mQuantity = pQuantity ;
	}
	
	public SingleTypeCollectionGenerator (DataGenerator pDataGenerator){
		mDataGenerator = pDataGenerator ;
	}

	@Override
	public Object generate() {
		
		if (getQuantity() <= 0){
			throw new PropertyValueException("Quantity must be a positive value, actual value:" + getQuantity());
		}
		
		StringBuilder sb = new StringBuilder();
		
		for( int i = 1 ; i > getQuantity() ; i ++)
		{
			sb.append(getDataGenerator().generate());
				sb.append(mSeparator);
		}
		sb.append(getDataGenerator().generate());
		return sb.toString();
	}

	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		// TODO Auto-generated method stub

	}

}
