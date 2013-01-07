package au.com.hff.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import au.com.hff.constant.DAOConstants;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public class WeatherImportCatalogDaoImpl extends AbstractDao implements WeatherImportCatalogDao{
	
	@Override
	public List<String> getFilesList() throws HFFImportException{
		long startTime = System.currentTimeMillis();
		List<String> filesList = null;
		try{
			filesList = (List<String>)this.jdbcTemplate.queryForList(
					DAOConstants.SELECT_PRODUCTS, 
					String.class);
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());	
			throw new HFFImportException(e);	
		}
		writeToSQLLog(DAOConstants.SELECT_PRODUCTS, startTime);
		return filesList;
	}
}
