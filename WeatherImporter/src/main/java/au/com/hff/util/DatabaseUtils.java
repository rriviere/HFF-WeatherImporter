
package au.com.hff.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import au.com.hff.constant.DAOConstants;

/**
 * @author richard.riviere
 *
 */
public class DatabaseUtils {
	
	public static void addStrWithBulkUpdateFieldTerminator(StringBuffer strb, String str){
		if (strb != null){
			strb.append((str == null ? DAOConstants.BULK_UPSERT_NULL_VALUE : str));
			strb.append(DAOConstants.BULK_UPSERT_FIELD_TERMINATOR);
		}
	}
	
	public static void addStrWithBulkUpdateRowTerminator(StringBuffer strb, String str){
		if (strb != null){
			strb.append((str == null ? DAOConstants.BULK_UPSERT_NULL_VALUE : str));
			strb.append(DAOConstants.BULK_UPSERT_ROW_TERMINATOR);
		}
	}	
	public static java.sql.Date dateToSQLDate(String dateStr) throws Exception{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date convertedDate = dateFormat.parse(dateStr); 		
		return dateToSQLDate(convertedDate);
		
	}	
	
	public static java.sql.Date dateToSQLDate(Date date) {
		 java.sql.Date sqlDate = null;             
        if (date != null){
       	 sqlDate = new java.sql.Date(date.getTime());
        }
        return sqlDate;
	}
	
	public static java.sql.Timestamp dateToSQLTimestamp(Date date) {
		java.sql.Timestamp sqlTimestamp = null;             
        if (date != null){
        	sqlTimestamp = new java.sql.Timestamp(date.getTime());
        }
        return sqlTimestamp;
	}	
}
