package au.com.hff.dao.bulkInsert;

import java.util.List;

import au.com.hff.constant.DAOConstants;
import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public class ForecastHeadDaoImpl extends AbstractBulkInsertDao implements ForecastHeadDao {

	@Override
	public String getTableName(){
		return DAOConstants.FORECAST_HEAD_TABLE_NAME;
	}
	@Override
	public String[] getColumnNames(){
		return DAOConstants.FORECAST_HEAD_COLUMN_NAMES;
	}				
	
	@Override
	public void upsertData(List<AbstractBulkInsertDomainObject> forecastHeads) throws HFFImportException {
		excuteBulkInsert(this.getTableName(), this.getColumnNames(), forecastHeads);
	}
}
