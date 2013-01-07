package au.com.hff.dao.bulkInsert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.hff.constant.DAOConstants;
import au.com.hff.dao.AbstractDao;
import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public abstract class AbstractBulkInsertDao extends AbstractDao{
	
	private static final Log logger = LogFactory.getLog(AbstractBulkInsertDao.class);

	protected String tableName = "";
	protected List<String> columnNames = null;
	protected List<AbstractBulkInsertDomainObject> data = new ArrayList<AbstractBulkInsertDomainObject>();

	protected void excuteBulkInsert(String tableName, String[] columnNames, List<AbstractBulkInsertDomainObject> data) 
			throws HFFImportException {
		
		com.mysql.jdbc.Connection conn = null;
		long startTime = System.currentTimeMillis();		
		try{
			conn = (com.mysql.jdbc.Connection)dataSource.getConnection();
		}catch(SQLException e){
			writeToErrorLog(e.getMessage());
			throw new HFFImportException(e);
		}
		BulkInsertOperation op = new BulkInsertOperation();
		op.bulkInsert(conn, 
			getPreStatements(tableName), 
			getPostStatements(tableName), 
			getMainStatement(tableName, columnNames), 
			data);	
		
		writeToSQLLog(tableName, startTime);	
		writeToBulkInsertDebugLog(tableName, data.size());
	}
	
	@Override
	protected void writeToSQLLog(String tableName, long startTime) {
		long endTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()){
			logger.info(tableName + "," + (endTime - startTime));			
		}
	}
	
	private void writeToBulkInsertDebugLog(String tableName, int rowCount) {
		if (logger.isInfoEnabled()){
			logger.debug(tableName + "," + rowCount);			
		}
	}	
	
	@Override
	protected void writeToErrorLog(String msg) {
		if (logger.isInfoEnabled()){
			logger.error(msg);			
		}
	}	
	
	protected List<String> getPreStatements(String tableName) {
		List<String> preStatement = new ArrayList<String>();	
		preStatement.add(DAOConstants.DISABLE_UNIQUE_CHECKS);
		preStatement.add(DAOConstants.DISABLE_FK_CHECKS);
		preStatement.add(DAOConstants.ALTER_TABLE + tableName + DAOConstants.DISABLE_KEYS);
		return preStatement;
	}
	
	protected List<String> getPostStatements(String tableName) {
		List<String> postStatement = new ArrayList<String>();			
		postStatement.add(DAOConstants.ALTER_TABLE + tableName + DAOConstants.ENABLE_KEYS);	
		postStatement.add(DAOConstants.ENABLE_FK_CHECKS);
		postStatement.add(DAOConstants.ENABLE_UNIQUE_CHECKS);		
		return postStatement;
	}	
	
	protected String getMainStatement(String tableName, String[] columnNames) {
		StringBuilder statementText = new StringBuilder(); 
		statementText.append(DAOConstants.LOAD_DATA_LOCAL_INFILE);
		statementText.append(DAOConstants.INTO_TABLE + tableName +  " ( ");
		for (int i = 0; i< columnNames.length; i++){
			statementText.append((String) columnNames[i]);
			if (i!= (columnNames.length-1)){
				statementText.append(",");
			}
		}
		statementText.append(" ) ");
		statementText.append(DAOConstants.SET_LAST_MODIFIED + DAOConstants.DETAULT_APP_USER);
		statementText.append(DAOConstants.SET_LAST_UPDATE);
		return statementText.toString();
	}		
}
