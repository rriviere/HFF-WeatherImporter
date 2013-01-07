package au.com.hff.constant;

/**
 * @author richard.riviere
 *
 */
public class DAOConstants {
	
	// general
	public static String DETAULT_APP_USER = "HFF_IMPORT_USER";
	public static String BULK_UPSERT_FIELD_TERMINATOR = "\t";
	public static String BULK_UPSERT_ROW_TERMINATOR = "\n";
	public static String BULK_UPSERT_NULL_VALUE = "\\N";
	
	// table names
	public static String AREA_TABLE_NAME = "HFF_WEATHER_AREA";
	public static String FORECAST_HEAD_TABLE_NAME = "HFF_WEATHER_FORECAST_HEAD";
	public static String FORECAST_DETAIL_TABLE_NAME = "HFF_WEATHER_FORECAST_DETAIL";
	
	// column names
	public static String[] AREA_COLUMN_NAMES = new String[]{"AREA","DESCRIPTION","TYPE","PARENT_AREA"};
	public static String[] FORECAST_HEAD_COLUMN_NAMES = new String[]{"START_TIME_LOCAL","END_TIME_LOCAL","TIME_ZONE,AREA"};
	public static String[] FORECAST_HEAD_DATE_COLUMN_NAMES = new String[]{"START_TIME_LOCAL","END_TIME_LOCAL"};
	public static String[] FORECAST_DETAIL_COLUMN_NAMES = new String[]{"ELEMENT_NAME","ELEMENT_UNIT","ELEMENT_VALUE","START_TIME_LOCAL","END_TIME_LOCAL","TIME_ZONE,AREA"};

	// dao sql named parameters 
	public static final String PROCESSED_DATETIME_PARAM = "processedDatetime"; 
	public static final String WEATHER_STAGE_ID_PARAM = "weatherStageIds";
	
	// column names used in mappers
	public static final String WEATHER_STAGE_ID_COL = "WEATHER_STAGE_ID";
	public static final String PRODUCT_ID_COL = "PRODUCT_ID";
	public static final String DOCUMENT_COL = "DOCUMENT";
	public static final String CREATE_DATE_COL = "CREATE_DATE";
	
	// sql queries
	public static final String SELECT_PRODUCTS = 
			"SELECT product_id" +
			" FROM hff_weather_import_catalog";

	public static final String BATCH_LOG_START_INSERT_SQL = 
			"INSERT INTO hff_batch_log" + 
		    " (job_name,start_datetime)" +
			" VALUES (?,?)";
	
	public static final String BATCH_LOG_END_UPDATE_SQL = 
			"UPDATE hff_batch_log" + 
		    " SET end_datetime=?,error=?"  +
			" WHERE log_id=? ";	
	
	public static final String WEATHER_IMPORT_STAGE_INSERT_SQL = 
			"INSERT INTO hff_weather_import_stg" + 
		    " (product_id,document,create_date,processed_datetime)" +
			" VALUES (?,?,?,?)";
	
	public static final String WEATHER_IMPORT_STAGE_SELECT_SQL = 
			"SELECT weather_stage_id,product_id,document,create_date" + 
			" FROM hff_weather_import_stg" +
			" WHERE processed_datetime IS NULL";	
	
	public static final String WEATHER_IMPORT_STAGE_UPDATE_SQL = 
			"UPDATE hff_weather_import_stg" +
			" SET processed_datetime=:" + PROCESSED_DATETIME_PARAM + 
			" WHERE weather_stage_id in (:" + WEATHER_STAGE_ID_PARAM + ")";
	
	// Data loader sql
	public static final String SET_UNIQUE_CHECKS= "SET UNIQUE_CHECKS=";
	public static final String SET_FK_CHECKS= "SET FOREIGN_KEY_CHECKS=";
	
	public static final int ENABLED = 1;
	public static final int DISABLED = 0;
	
	public static final String DISABLE_UNIQUE_CHECKS = SET_UNIQUE_CHECKS + DISABLED + ";";
	public static final String DISABLE_FK_CHECKS = SET_FK_CHECKS + DISABLED + ";";
	
	public static final String ENABLE_UNIQUE_CHECKS = SET_UNIQUE_CHECKS + ENABLED + ";";
	public static final String ENABLE_FK_CHECKS = SET_FK_CHECKS + ENABLED + ";";
	
	public static final String ALTER_TABLE= "ALTER TABLE ";
	public static final String DISABLE_KEYS = " DISABLE KEYS";
	public static final String ENABLE_KEYS = "  ENABLE KEYS";
	
	public static final String LOAD_DATA_LOCAL_INFILE = "LOAD DATA LOCAL INFILE 'file.txt' REPLACE ";
	public static final String INTO_TABLE = "INTO TABLE  ";
	
	public static final String SET_LAST_MODIFIED = "SET LAST_MODIFIED_USER='";
	public static final String SET_LAST_UPDATE = "', LAST_UPDATE_DATETIME=CURRENT_TIMESTAMP";

}
