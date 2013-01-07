package au.com.hff.manager;

import java.util.List;

import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface WeatherImportStageManager {
	public void saveXMLFile(String productId, String xml) throws HFFImportException;
	public List<WeatherStager> getStagedXML() throws HFFImportException;
	public void updateStaging(List<WeatherStager> stager) throws HFFImportException;
}
