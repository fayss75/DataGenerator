/**
 * 
 */
package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationBuffer;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * @author fayss
 *
 */
public @Getter @Setter class ReferencePropertyGenerator implements DataGenerator{



	private RepositoryItemGenerator mRepositoryItemGenerator ;
	private ItemDescriptorIdGenerator  mIdGenerator;



	/**
	 * Constructor
	 */
	public ReferencePropertyGenerator (RepositoryItemGenerator pRepositoryItemGenerator ){
		mRepositoryItemGenerator = pRepositoryItemGenerator; 
		mIdGenerator = getRepositoryItemGenerator().getIdGenerator() ;

	}


	/**
	 * Constructor
	 */
	public ReferencePropertyGenerator (RepositoryItemGenerator pRepositoryItemGenerator ,
			ItemDescriptorIdGenerator  pDataTypeGenerator ){
		mRepositoryItemGenerator = pRepositoryItemGenerator; 
		mIdGenerator = pDataTypeGenerator ;

	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#generate()
	 */
	@Override
	public Object generate() {

		Object repositoryId = getIdGenerator().generate();
		
		GenerationBuffer genBuffer = GenerationBuffer.getInstance();
		
		DataConfiguration dataconfigutation = new DataConfiguration() ;
		dataconfigutation.setPropertyConfiguration(DataConfigurationTools.DATA_GENERATOR_INSTANCE,getRepositoryItemGenerator());
		dataconfigutation.setPropertyConfiguration(RepositoryItemGenerator.VALUE_PROP,repositoryId);
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


}
