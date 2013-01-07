package au.com.hff.manager;

import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.loader.DataLoader;

public interface DataLoadManager {
	public DataLoader getDataLoader(String loaderCode) throws HFFImportException;
}
