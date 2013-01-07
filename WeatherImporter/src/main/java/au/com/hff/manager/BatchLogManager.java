package au.com.hff.manager;

import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface BatchLogManager {	
	public Long logStart(String batchName)throws HFFImportException;
	public void logEnd(Long logId, String errorMessage)throws HFFImportException;
}
