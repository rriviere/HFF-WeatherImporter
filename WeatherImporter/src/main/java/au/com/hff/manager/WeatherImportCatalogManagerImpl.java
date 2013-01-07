package au.com.hff.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.hff.dao.WeatherImportCatalogDao;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
@Service
public class WeatherImportCatalogManagerImpl implements WeatherImportCatalogManager{
	
	@Autowired
	WeatherImportCatalogDao weatherImportCatalogDao;

	@Transactional
	@Override
	public List<String> getFilesList()throws HFFImportException {
		return weatherImportCatalogDao.getFilesList();
	}	
}
