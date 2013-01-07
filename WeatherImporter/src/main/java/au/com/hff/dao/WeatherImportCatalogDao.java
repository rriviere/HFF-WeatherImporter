package au.com.hff.dao;

import java.util.List;

import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface WeatherImportCatalogDao {
	public List<String> getFilesList() throws HFFImportException;
}
