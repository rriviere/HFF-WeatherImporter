package au.com.hff.dao;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import au.com.hff.constant.DAOConstants;
import au.com.hff.dao.mapper.WeatherStagerMapper;
import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;
import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class WeatherImportStageDaoImpl extends AbstractDao implements WeatherImportStageDao{
		
	@Override
	public void saveXMLFile(String productId, String xml) throws HFFImportException {
		long startTime = System.currentTimeMillis();
		try{
			this.jdbcTemplate.update(
					DAOConstants.WEATHER_IMPORT_STAGE_INSERT_SQL, 
					new Object[] {productId, xml, DatabaseUtils.dateToSQLTimestamp(new Date()), null}, 
					new int[]{Types.VARCHAR, Types.CLOB, Types.TIMESTAMP, Types.TIMESTAMP});
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}
		writeToSQLLog(DAOConstants.WEATHER_IMPORT_STAGE_INSERT_SQL, startTime);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<WeatherStager> getStagedXML() throws HFFImportException { 
		long startTime = System.currentTimeMillis();
		List<WeatherStager> results = null;	
		try {
			results = (List<WeatherStager>) this.jdbcTemplate.query(
					DAOConstants.WEATHER_IMPORT_STAGE_SELECT_SQL, 
					new WeatherStagerMapper());
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}		
		writeToSQLLog(DAOConstants.WEATHER_IMPORT_STAGE_SELECT_SQL, startTime);
		return results;
	}

	@Override
	public void updateStaging(List<Long> ids) throws HFFImportException {
		long startTime = System.currentTimeMillis();
		try{
			SqlParameterSource params = (new MapSqlParameterSource())
					.addValue(DAOConstants.PROCESSED_DATETIME_PARAM, 
							DatabaseUtils.dateToSQLTimestamp(new Date()), 
							Types.TIMESTAMP)
					.addValue(DAOConstants.WEATHER_STAGE_ID_PARAM, 
							ids, 
							Types.NUMERIC);
			
			this.namedParameterJdbcTemplate.update(
					DAOConstants.WEATHER_IMPORT_STAGE_UPDATE_SQL, 
					params);	
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}		
		writeToSQLLog(DAOConstants.WEATHER_IMPORT_STAGE_UPDATE_SQL, startTime);
	}	
}
