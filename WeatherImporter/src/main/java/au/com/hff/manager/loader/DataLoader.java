package au.com.hff.manager.loader;

import au.com.hff.exception.HFFImportException;


/**
 * @author richard.riviere
 *
 */
public interface DataLoader {
	public void flush()throws HFFImportException;
}
