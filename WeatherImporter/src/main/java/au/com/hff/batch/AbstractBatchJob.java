package au.com.hff.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.constant.BatchConstants;
import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.BatchLogManager;

/**
 * @author richard.riviere
 *
 */
public abstract class AbstractBatchJob {
	
	private static final Log logger = LogFactory.getLog(AbstractBatchJob.class);
	
	@Autowired
	BatchLogManager batchLogManager;
	
	public abstract void execute()throws HFFImportException;
	protected abstract String getBatchName()throws HFFImportException;
	
	protected void run() throws HFFImportException{
		// log4j timing variables
		long startTime = System.currentTimeMillis();		
		// do batch work
		String errorMessage = null;
		Long logID = startBatchJob();
		try {	
			execute();
		} catch(HFFImportException e){
			writeToErrorLog(e.getMessage());
			errorMessage = e.getMessage();
		}
		endBatchJob(startTime, logID, null, null, errorMessage);
	}
	
	@SuppressWarnings("deprecation")
	private Long startBatchJob() throws HFFImportException {
		Long logID = new Long(BatchConstants.BATCH_LOG_ID_INIT);
		logID = start(getBatchName());
		if (logID==BatchConstants.BATCH_LOG_ID_INIT){
			throw new HFFImportException("HFF Batch log ID could not be initialised.");
		}
		return logID;
	}
	
	private void endBatchJob(
			long startTime, 
			Long logID, 
			String info, 
			Long returnCode, 
			String errorMessage) throws HFFImportException {
		finish(logID, errorMessage);
		
		long endTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()){
			logger.info(this.getBatchName() + "," + (endTime - startTime));	
		}	
	}	
	
	protected void writeToErrorLog(String msg) {
		if (logger.isInfoEnabled()){
			logger.error(msg);			
		}
	}		
	
	private Long start(String batchName) throws HFFImportException{
		return batchLogManager.logStart(batchName);
	}
	
	private void finish(Long logId, String errorMessage) throws HFFImportException{
		batchLogManager.logEnd(logId, errorMessage);
	}	
}
