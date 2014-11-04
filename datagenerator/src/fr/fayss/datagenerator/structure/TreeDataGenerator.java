package fr.fayss.datagenerator.structure;

import java.util.Collection;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

public class TreeDataGenerator extends CollectionDataGenerator{

	public TreeDataGenerator(Collection<DataGenerator> pChild,DataGenerator pNode,
			String pSeparator) {
		super(pChild);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object generate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}

}
