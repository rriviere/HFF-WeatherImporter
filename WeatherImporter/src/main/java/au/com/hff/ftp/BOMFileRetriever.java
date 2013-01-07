package au.com.hff.ftp;

import au.com.hff.exception.HFFImportException;


/**
 * @author richard.riviere
 *
 */
public interface BOMFileRetriever {
	
	public void retrieveFiles() throws HFFImportException;
}
