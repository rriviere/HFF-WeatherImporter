package au.com.hff.domain;

import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class WeatherForecastElement extends AbstractBulkInsertDomainObject{
	String elementName;
	String elementUnit;
	String elementValue;
	WeatherForecast weatherForecast;
	
	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}
	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	/**
	 * @return the elementUnit
	 */
	public String getElementUnit() {
		return elementUnit;
	}
	/**
	 * @param elementUnit the elementUnit to set
	 */
	public void setElementUnit(String elementUnit) {
		this.elementUnit = elementUnit;
	}
	/**
	 * @return the elementValue
	 */
	public String getElementValue() {
		return elementValue;
	}
	/**
	 * @param elementValue the elementValue to set
	 */
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	public WeatherForecast getWeatherForecast() {
		return weatherForecast;
	}
	public void setWeatherForecast(WeatherForecast weatherForecast) {
		this.weatherForecast = weatherForecast;
	}	
	
	public String toString(){		
		StringBuffer strb = new StringBuffer();
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,elementName);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,elementUnit);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,elementValue);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,weatherForecast.getStartTimeLocal());
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,weatherForecast.getEndTimeLocal());
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,weatherForecast.getTimeZone());
		DatabaseUtils.addStrWithBulkUpdateRowTerminator(strb,weatherForecast.getArea().getAac());	
		return strb.toString();		
	}
}
