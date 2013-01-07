package au.com.hff.domain;

import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class WeatherArea extends AbstractBulkInsertDomainObject{
	String aac; 			// a code for the area
	String description;		// Victoria
	String type;			// region, metropolitan
	String parentAAC;		// a code for the parent area	
	
	/**
	 * @return the aac
	 */
	public String getAac() {
		return aac;
	}
	/**
	 * @param aac the aac to set
	 */
	public void setAac(String aac) {
		this.aac =  aac;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the parentAAC
	 */
	public String getParentAAC() {
		return parentAAC;
	}
	/**
	 * @param parentAAC the parentAAC to set
	 */
	public void setParentAAC(String parentAAC) {
		this.parentAAC = parentAAC;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		StringBuffer strb = new StringBuffer();
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,aac);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,description);
		DatabaseUtils.addStrWithBulkUpdateFieldTerminator(strb,type);
		DatabaseUtils.addStrWithBulkUpdateRowTerminator(strb,parentAAC);
		return strb.toString();
	}

}
