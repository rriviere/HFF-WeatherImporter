package au.com.hff.dao;

import java.util.List;

import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface WeatherImportStageDao {
	public void saveXMLFile(String productId, String xml) throws HFFImportException;
	public List<WeatherStager> getStagedXML() throws HFFImportException;
	public void updateStaging(List<Long> ids) throws HFFImportException;
}
