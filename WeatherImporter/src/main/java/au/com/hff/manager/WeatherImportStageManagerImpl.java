package au.com.hff.manager;

import java.util.List;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.hff.constant.BeanConstants;
import au.com.hff.dao.WeatherImportStageDao;
import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
@Service
public class WeatherImportStageManagerImpl implements WeatherImportStageManager{
	
	@Autowired
	WeatherImportStageDao weatherImportStageDao;
	
	@Transactional
	@Override
	public void saveXMLFile(String productId, String xml) throws HFFImportException {
		weatherImportStageDao.saveXMLFile(productId, xml);
	}

	@Transactional
	@Override
	public void updateStaging(List<WeatherStager> stagers)
			throws HFFImportException {
		@SuppressWarnings("unchecked")
		List<Long> stagersIds = 
				(List<Long>)CollectionUtils.collect(stagers, 
						new BeanToPropertyValueTransformer(BeanConstants.WEATHER_STAGE_ID));
		weatherImportStageDao.updateStaging(stagersIds);
	}
	
	@Override
	public List<WeatherStager> getStagedXML() throws HFFImportException {
		return weatherImportStageDao.getStagedXML();
	}
}
