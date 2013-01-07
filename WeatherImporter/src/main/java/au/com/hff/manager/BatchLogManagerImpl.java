package au.com.hff.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.hff.dao.BatchLogDao;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
@Service
public class BatchLogManagerImpl implements BatchLogManager{
	
	@Autowired
	BatchLogDao batchLogDao;

	@Transactional
	@Override
	public Long logStart(String batchName) throws HFFImportException{
		return batchLogDao.logStart(batchName);
	}

	@Transactional
	@Override
	public void logEnd(Long logId, String errorMessage) throws HFFImportException{
		batchLogDao.logEnd(logId, errorMessage);
	}
}
