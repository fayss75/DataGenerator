package fr.fayss.datagenerator;

import java.util.HashMap;
import java.util.Map;



public  class DataConfiguration {
	
	
	private Map<String, Object> mPropertyConfigMap;

	public DataConfiguration () {
		mPropertyConfigMap = new HashMap<String, Object>() ;
	}

	public Object getPropertyConfiguration (String pPropertyName){
		return mPropertyConfigMap.get(pPropertyName);
	}
	
	public Object setPropertyConfiguration (String pPropertyName, Object pValue){
		return mPropertyConfigMap.put(pPropertyName, pValue);
	}
	

}
