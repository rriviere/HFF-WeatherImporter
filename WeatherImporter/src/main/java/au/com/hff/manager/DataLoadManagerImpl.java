package au.com.hff.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.loader.DataLoader;

/**
 * @author richard.riviere
 *
 */
@Service
public class DataLoadManagerImpl implements DataLoadManager {
	private static String LOADER_NOT_FOUND = "No Data Loader Found";
	
	//map to store intialised data loaders
	private Map<String,DataLoader> cache 
		= new HashMap<String,DataLoader>();
	
	//map injected in with data loader names
	public Map<String,DataLoader> weatherDataLoaderMap 
		= new HashMap<String,DataLoader>();

	@Transactional
	@SuppressWarnings("deprecation")
	public DataLoader getDataLoader(String loaderCode)throws HFFImportException{
		DataLoader loader = (DataLoader) cache.get(loaderCode);
		if (loader == null ) {
			throw new HFFImportException(LOADER_NOT_FOUND);			
		}
		return loader;
	}
	
	public void afterPropertiesSet() {
		for (Map.Entry<String,DataLoader> entry : weatherDataLoaderMap.entrySet()) {
		    String loaderCode = entry.getKey();
		    DataLoader dataLoader = entry.getValue();		    
			cache.put(loaderCode, dataLoader);
		}	    
	}

	public Map<String,DataLoader> getWeatherDataLoaderMap() {
		return weatherDataLoaderMap;
	}

	public void setWeatherDataLoaderMap(Map<String,DataLoader> weatherDataLoaderMap) {
		this.weatherDataLoaderMap = weatherDataLoaderMap;
	}
}
