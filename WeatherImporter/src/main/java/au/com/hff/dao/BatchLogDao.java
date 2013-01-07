package au.com.hff.dao;

import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface BatchLogDao {
	public Long logStart(String batchName)throws HFFImportException;
	public void logEnd(Long logId, String errorMessage)throws HFFImportException;
}
