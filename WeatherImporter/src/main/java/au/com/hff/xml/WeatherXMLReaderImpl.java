package au.com.hff.xml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.constant.BatchConstants;
import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.DataLoadManager;
import au.com.hff.manager.WeatherImportStageManager;
import au.com.hff.manager.loader.DataLoader;
import au.com.hff.manager.loader.DataLoaderConstants;

public class WeatherXMLReaderImpl implements XMLReader {
	
	@Autowired
	DataLoadManager dataLoadManager;
	
	@Autowired
	WeatherImportStageManager weatherImportStageManager;
	
	@Autowired
	SaxWeatherParserImpl saxWeatherParser;
	
	public void readStaging() throws HFFImportException {	
		List<WeatherStager> stagers = getStagedXML();
		
		int batchCheckPoint=0, i=0;
		while (i < stagers.size()) {
			WeatherStager stager = (WeatherStager)stagers.get(i++);
			String xml = stager.getDocument();
			if (xml!=null){
				parseFile(xml);
			}
			// j is an exact multiple of batch size
			if ( (i%BatchConstants.batchSize==0)||(i==stagers.size()) ) {
				batchCheckPoint = (i<BatchConstants.batchSize ? 0 : i);
				
				List<WeatherStager> processedStagers = stagers.subList(batchCheckPoint, i);
				flushQueues(processedStagers);
				updateStaging(processedStagers);
			}
		}		
	}	
	
	private List<WeatherStager> getStagedXML() throws HFFImportException {	
		return weatherImportStageManager.getStagedXML();		
	}
	
	private void parseFile(String xml) throws HFFImportException {
		saxWeatherParser.parse(xml);
	}		

	private void flushQueues(List<WeatherStager> stagers) throws HFFImportException {
		if (dataLoadManager!= null){
			DataLoader areaLoader = dataLoadManager.getDataLoader(DataLoaderConstants.AREA_DATA_LOADER);
			if (areaLoader != null){
				areaLoader.flush();
			}
			DataLoader forecastHeadLoader = dataLoadManager.getDataLoader(DataLoaderConstants.FORECAST_HEAD_DATA_LOADER);
			if (forecastHeadLoader != null){
				forecastHeadLoader.flush();
			}
			DataLoader forecastDetailLoader = dataLoadManager.getDataLoader(DataLoaderConstants.FORECAST_DETAIL_DATA_LOADER);
			if (forecastDetailLoader != null){
				forecastDetailLoader.flush();
			}
		}
	}
	
	private void updateStaging(List<WeatherStager> processedStagers) throws HFFImportException {
		if (weatherImportStageManager!= null){
			weatherImportStageManager.updateStaging(processedStagers);
		}
	}		
}
