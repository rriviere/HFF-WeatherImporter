package au.com.hff.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import au.com.hff.constant.DAOConstants;
import au.com.hff.exception.HFFImportException;
import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class BatchLogDaoImpl extends AbstractDao implements BatchLogDao{
	
	@Override
	public Long logStart(final String batchName) throws HFFImportException{	
		long startTime = System.currentTimeMillis();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try{
			jdbcTemplate.update(
				new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(
						java.sql.Connection con) throws SQLException {
					PreparedStatement ps =
							con.prepareStatement(DAOConstants.BATCH_LOG_START_INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
							ps.setString(1, batchName);
							ps.setTimestamp(2, DatabaseUtils.dateToSQLTimestamp(new Date()));
							return ps;
				}
				},
				keyHolder);
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}			
		writeToSQLLog(DAOConstants.BATCH_LOG_START_INSERT_SQL, startTime);
		return new Long(keyHolder.getKey().longValue());		
	}

	@Override
	public void logEnd(Long logId, String errorMessage) throws HFFImportException {
		long startTime = System.currentTimeMillis();
		try{
			this.jdbcTemplate.update(
					DAOConstants.BATCH_LOG_END_UPDATE_SQL,
					new Object[] {DatabaseUtils.dateToSQLTimestamp(new Date()),errorMessage,logId}, 
					new int[]{Types.TIMESTAMP, Types.VARCHAR, Types.NUMERIC});
		}catch(DataAccessException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}				
		writeToSQLLog(DAOConstants.BATCH_LOG_END_UPDATE_SQL, startTime);
	}
}
