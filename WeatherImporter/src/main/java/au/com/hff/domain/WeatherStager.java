package au.com.hff.domain;

import java.util.Date;

import au.com.hff.util.DatabaseUtils;

/**
 * @author richard.riviere
 *
 */
public class WeatherStager {
	Long weatherStageId;
	String productId;
	String document;
	Date createDate;
	Date processedDatetime;

	public Long getWeatherStageId() {
		return weatherStageId;
	}
	public void setWeatherStageId(Long weatherStageId) {
		this.weatherStageId = weatherStageId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getProcessedDatetime() {
		return processedDatetime;
	}
	public void setProcessedDatetime(Date processedDatetime) {
		this.processedDatetime = processedDatetime;
	}
	
	public String toString(){
		StringBuffer strb = new StringBuffer();
		strb.append(" weatherStageId:" + weatherStageId);
		strb.append(" productId:" + productId);
		strb.append(" createDate:" + createDate);
		strb.append(" processedDatetime:" + processedDatetime);
		return strb.toString();
	}	
}
