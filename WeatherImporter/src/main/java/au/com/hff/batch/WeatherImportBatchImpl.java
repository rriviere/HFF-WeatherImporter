package au.com.hff.batch;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.constant.BatchConstants;
import au.com.hff.exception.HFFImportException;
import au.com.hff.xml.XMLReader;

/**
 * @author richard.riviere
 *
 */
public class WeatherImportBatchImpl extends AbstractBatchJob {
	@Autowired
	XMLReader xmlReader;
	
	public void execute() throws HFFImportException{
		xmlReader.readStaging();
	}

	@Override
	protected String getBatchName() throws HFFImportException {
		return BatchConstants.WEATHER_IMPORT_BATCH_NAME;
	}
}
