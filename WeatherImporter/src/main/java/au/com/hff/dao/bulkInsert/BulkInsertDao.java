package au.com.hff.dao.bulkInsert;

import java.util.List;

import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;


/**
 * @author richard.riviere
 *
 */
public interface BulkInsertDao {
	public String getTableName();
	public String[] getColumnNames();
	public void upsertData(List<AbstractBulkInsertDomainObject> data) throws HFFImportException;
}
