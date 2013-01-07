package au.com.hff.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import au.com.hff.constant.DAOConstants;
import au.com.hff.domain.WeatherStager;

/**
 * @author richard.riviere
 *
 */
public final class WeatherStagerMapper implements RowMapper{
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		WeatherStager ws = new WeatherStager();
		ws.setWeatherStageId(rs.getLong(DAOConstants.WEATHER_STAGE_ID_COL));
		ws.setProductId(rs.getString(DAOConstants.PRODUCT_ID_COL));
		ws.setDocument(rs.getString(DAOConstants.DOCUMENT_COL));
		ws.setCreateDate(rs.getDate(DAOConstants.CREATE_DATE_COL));
        return ws;
	}
}
