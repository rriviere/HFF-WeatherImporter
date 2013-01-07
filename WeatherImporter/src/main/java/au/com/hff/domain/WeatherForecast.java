package au.com.hff.domain;

import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class WeatherForecast extends AbstractBulkInsertDomainObject {
	private String index;
	private String startTimeLocal;			// 2012-11-15T00:00:00+11:00
	private String endTimeLocal;			// 2012-11-16T00:00:00+11:00
	private String timeZone;				// +11:00	
	private WeatherArea area;


	/**
	 * @return the endTimeLocal
	 */
	public String getEndTimeLocal() {
		return endTimeLocal;
	}
	/**
	 * @param endTimeLocal the endTimeLocal to set
	 */
	public void setEndTimeLocal(String endTimeLocal) {
		this.endTimeLocal = endTimeLocal;
	}
	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * @return the startTimeLocal
	 */
	public String getStartTimeLocal() {
		return startTimeLocal;
	}
	/**
	 * @param startTimeLocal the startTimeLocal to set
	 */
	public void setStartTimeLocal(String startTimeLocal) {
		this.startTimeLocal = startTimeLocal;
	}
	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}	
	
	public WeatherArea getArea() {
		return area;
	}
	public void setArea(WeatherArea area) {
		this.area = area;
	}
	
	public String toString(){
		StringBuffer strb = new StringBuffer();
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,startTimeLocal);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,endTimeLocal);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,timeZone);
		DatabaseUtils.addStrWithBulkUpdateRowTerminator(strb,area.getAac());
		return strb.toString();
	}
	
}
