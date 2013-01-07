package au.com.hff.dao;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author richard.riviere
 *
 */
public abstract class AbstractDao {

	private Log logger = LogFactory.getLog(AbstractDao.class);
	
	protected DataSource dataSource;
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Give the Spring JDBC template a data source
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	protected void writeToSQLLog(String sql, long startTime) {
		long endTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()){
			logger.info(sql + "," + (endTime - startTime));			
		}
	}	
	protected void writeToErrorLog(String msg) {
		if (logger.isInfoEnabled()){
			logger.error(msg);			
		}
	}	
}
