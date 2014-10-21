package fr.fayss.datagenerator.structure;

import java.util.Collection;
import java.util.Iterator;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;

public class CollectionDataGenerator implements DataGenerator{

	public Collection<DataGenerator> mDataList ;
	public String mSeparator ;

	public CollectionDataGenerator ( Collection<DataGenerator> pDataList,String pSeparator){
		mDataList = pDataList ;

		mSeparator = pSeparator ;
		if (mSeparator == null){
			mSeparator ="";
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
	public void configure(DataConfiguration pDataconfig) {
		// TODO Auto-generated method stub
		
	}
}
