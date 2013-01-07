package au.com.hff.batch;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.constant.BatchConstants;
import au.com.hff.exception.HFFImportException;
import au.com.hff.ftp.BOMFileRetriever;

/**
 * @author richard.riviere
 *
 */
public class WeatherStageBatchImpl extends AbstractBatchJob{
	
	@Autowired
	BOMFileRetriever bomFileRetriever;

	@Override
	public void execute() throws HFFImportException {
		bomFileRetriever.retrieveFiles();
	}

	@Override
	protected String getBatchName() throws HFFImportException {
		return BatchConstants.WEATHER_STAGE_BATCH_NAME;
	}
}
