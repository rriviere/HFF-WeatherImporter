package au.com.hff.dao.bulkInsert;

import java.util.List;

import au.com.hff.constant.DAOConstants;
import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public class ForecastDetailDaoImpl extends AbstractBulkInsertDao implements ForecastDetailDao {
	@Override
	public String getTableName(){
		return DAOConstants.FORECAST_DETAIL_TABLE_NAME;
	}
	@Override
	public String[] getColumnNames(){
		return DAOConstants.FORECAST_DETAIL_COLUMN_NAMES;
	}		
	@Override
	public void upsertData(List<AbstractBulkInsertDomainObject> forecastDetails) throws HFFImportException {
		excuteBulkInsert(this.getTableName(), this.getColumnNames(), forecastDetails);
	}

}
