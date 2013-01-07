package au.com.hff.manager.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.hff.dao.bulkInsert.BulkInsertDao;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
@Service
public class AreaDataLoaderImpl extends AbstractDataLoader implements DataLoader {
	
	@Autowired
	public BulkInsertDao areaDao;
	
	@Transactional
	@Override
	public void flush()throws HFFImportException {
		areaDao.upsertData(this.data);	
		data.clear();
	}	
}
