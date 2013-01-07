package au.com.hff.manager;

import java.util.List;

import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface WeatherImportCatalogManager {
	public List<String> getFilesList() throws HFFImportException;
}
