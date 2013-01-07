package au.com.hff.dao.bulkInsert;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.exception.HFFImportException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author richard.riviere
 * 
 */
public class BulkInsertOperation {
	
	
	public void executeStatements(Statement statement, List<String> statements) throws SQLException {
		for(String statementStr:statements){
			statement.execute(statementStr);			
		}	
	}
	
	public void bulkInsert(Connection connection, 
			List<String> preStatements,
			List<String> postStatements,
			String mainStatement,
			List<AbstractBulkInsertDomainObject> data) throws HFFImportException {
		try {
			// First create a statement off the connection and turn off unique
			// checks and key creation
			Statement statement = (com.mysql.jdbc.Statement) connection.createStatement();
			executeStatements(statement, preStatements);
			
			//Create StringBuilder to String that will become stream
			StringBuilder builder = new StringBuilder();
	
			// Iterate over data map and create tab-text string
			for (Iterator<AbstractBulkInsertDomainObject> i = data.iterator(); i.hasNext();) {
				AbstractBulkInsertDomainObject obj = (AbstractBulkInsertDomainObject) i.next();
				builder.append(obj.toString());
			}
			//System.err.println(mainStatement);
			//System.err.println(builder);
			// Create stream from String Builder
			InputStream is = IOUtils.toInputStream(builder.toString());
	
			// Setup our input stream as the source for the local infile
			statement.setLocalInfileInputStream(is);
			
			// Execute the load infile
			statement.execute(mainStatement);
	
			// Turn the checks back on
			executeStatements(statement, postStatements);
		}catch(SQLException e){
			throw new HFFImportException(e);
		}
	}
}
