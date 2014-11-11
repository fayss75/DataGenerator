package fr.fayss.datagenerator.structure;

import java.util.Collection;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

public class MultiTypeCollectionGenerator implements DataGenerator{

	public Collection<DataGenerator> mDataList ;
	public @Getter @Setter String mSeparator = "," ;

	public MultiTypeCollectionGenerator (Collection<DataGenerator> pDatalist){
		mDataList = pDatalist ;
	}
	
	public MultiTypeCollectionGenerator ( Collection<DataGenerator> pDataList,String pSeparator){
		mDataList = pDataList ;
		if (mSeparator != null){
			mSeparator = pSeparator ;
		}
	}

	@Override
	public Object generate() {

		StringBuilder sb = new StringBuilder();
		
		Iterator<DataGenerator> dataIter = mDataList.iterator();
		
		
		while (dataIter.hasNext()){
			sb.append(dataIter.next().generate());
			
			if (dataIter.hasNext()){
				sb.append(mSeparator);
			}
		}

		return sb.toString();
	}

	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}

}
