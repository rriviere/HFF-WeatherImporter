package au.com.hff.dao.bulkInsert;

import java.util.List;

import au.com.hff.constant.DAOConstants;
import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public class AreaDaoImpl extends AbstractBulkInsertDao implements AreaDao {
	
	@Override
	public String getTableName(){
		return DAOConstants.AREA_TABLE_NAME;
	}
	@Override
	public String[] getColumnNames(){
		return DAOConstants.AREA_COLUMN_NAMES;
	}		
	@Override
	public void upsertData(List<AbstractBulkInsertDomainObject> areas) throws HFFImportException {
		excuteBulkInsert(this.getTableName(), this.getColumnNames(), areas);
	}	
}
