/**
 * 
 */
package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationBuffer;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.ReferenceDataGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class ReferencePropertyGenerator implements ReferenceDataGenerator {



	private RepositoryItemGenerator mRepositoryItemGenerator ;
	private DataGenerator  mReferenceIdGenerator;
	private String mReferenceKey;
	
	/**
	 * Constructor
	 */
	public ReferencePropertyGenerator (RepositoryItemGenerator pRepositoryItemGenerator ,
			DataGenerator  pDataTypeGenerator,String pRefKey){
		mRepositoryItemGenerator = pRepositoryItemGenerator; 
		mReferenceIdGenerator = pDataTypeGenerator ;
		mReferenceKey = pRefKey;
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#generate()
	 */
	@Override
	public Object generate() {

		Object repositoryId = getReferenceIdGenerator().generate();
		
		GenerationBuffer genBuffer = GenerationBuffer.getInstance();
		
		DataConfiguration dataconfigutation = new DataConfiguration() ;
		dataconfigutation.setPropertyConfiguration(DataConfigurationTools.DATA_GENERATOR_INSTANCE,getRepositoryItemGenerator());
		dataconfigutation.setPropertyConfiguration(DataFormatter.VALUE_PROP,repositoryId);
		genBuffer.pushItem(dataconfigutation);
		
		return repositoryId;
	}





	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#configure(fr.fayss.datagenerator.DataConfiguration)
	 */
	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return getReferenceIdGenerator() != null &&
				getRepositoryItemGenerator() != null &&
				getReferenceKey() != null;
	}


}
