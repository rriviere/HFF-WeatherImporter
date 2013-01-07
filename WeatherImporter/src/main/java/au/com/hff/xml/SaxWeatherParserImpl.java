package au.com.hff.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.com.hff.constant.XMLConstants;
import au.com.hff.domain.WeatherArea;
import au.com.hff.domain.WeatherForecast;
import au.com.hff.domain.WeatherForecastElement;
import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.DataLoadManager;
import au.com.hff.manager.loader.AbstractDataLoader;
import au.com.hff.manager.loader.AreaDataLoaderImpl;
import au.com.hff.manager.loader.DataLoaderConstants;
import au.com.hff.manager.loader.ForecastDetailDataLoaderImpl;
import au.com.hff.manager.loader.ForecastHeadDataLoaderImpl;
import au.com.hff.util.XMLUtility;


/**
 * @author richard.riviere
 *
 */
public class SaxWeatherParserImpl implements SaxWeatherParser{
	
	@Autowired
	DataLoadManager dataLoadManager;	
	
	public void parse(String xml) throws HFFImportException {
		this.parse(xml, XMLConstants.XPATH_REGION);
		this.parse(xml, XMLConstants.XPATH_METRO);
		this.parse(xml, XMLConstants.XPATH_LOCATION);
	}
	
	public void parse(String xml, String xPath) throws HFFImportException {
		Document weather = XMLUtility.parseXML(xml);
		NodeList areaNodes = XMLUtility.findNodeListByXpath(weather, xPath);
		if (areaNodes != null){
			parseAreas(areaNodes);
		}
	}	
	
	private void parseAreas(NodeList areaNodes) throws HFFImportException {
		for (int i = 0; i < areaNodes.getLength(); i++) {
			Node areaNode = areaNodes.item(i);
			if (areaNode != null){
				parseArea(areaNode);
			}
		}
	}
	
	private void parseArea(Node areaNode) throws HFFImportException {
		String areaAAC = null, areaDesc= null, areaType= null, parentAreaAac= null;
		WeatherArea area = new WeatherArea();
		
		NamedNodeMap attribs = areaNode.getAttributes();	
		if (attribs != null){
			Node areaAACNode = attribs.getNamedItem("aac");
			if (areaAACNode != null){
				areaAAC = areaAACNode.getNodeValue();
				area.setAac(areaAAC);
			}
			
			Node areaDescNode = attribs.getNamedItem("description");
			if (areaDescNode != null){
				areaDesc = areaDescNode.getNodeValue();
				area.setDescription(areaDesc);
			}	
			
			Node areaTypeNode = attribs.getNamedItem("type");
			if (areaTypeNode != null){
				areaType = areaTypeNode.getNodeValue();
				area.setType(areaType);
			}				
			
			Node parentAreaAacNode = attribs.getNamedItem("parent-aac");
			if (parentAreaAacNode != null){
				parentAreaAac = parentAreaAacNode.getNodeValue();
				area.setParentAAC(parentAreaAac);
			}					
		}
		queueAreaData(area);
		//areaPrintDebug(area);	
		parseForcasts(area, areaNode);
	}

	private void parseForcasts(WeatherArea area, Node areaNode) throws HFFImportException {
		NodeList forecastNodes = areaNode.getChildNodes();	
		for (int i = 0; i < forecastNodes.getLength(); i++) {
			Node forecastNode = forecastNodes.item(i);
			if (forecastNode.getNodeName().equals("forecast-period")){
				NamedNodeMap attribs = forecastNode.getAttributes();
				if (attribs != null) {
					WeatherForecast forecast = new WeatherForecast();
					
					Node indexNode = attribs.getNamedItem("index");
					if (indexNode != null){
						String index = indexNode.getNodeValue();
						forecast.setIndex(index);		
					}
					
					Node startTimeNode = attribs.getNamedItem("start-time-local");
					if (startTimeNode != null){
						String startTimeLocal = startTimeNode.getNodeValue();
						String timezone = null;
						if (startTimeLocal != null){
							timezone = startTimeLocal.substring(19);
							startTimeLocal = startTimeLocal.substring(0,19).replace("T", " ");
						}
						forecast.setStartTimeLocal(startTimeLocal);		
						forecast.setTimeZone(timezone);
					}
					
					Node endTimeNode = attribs.getNamedItem("end-time-local");
					if (endTimeNode != null){
						String endTimeLocal = endTimeNode.getNodeValue();
						if (endTimeLocal != null){
							endTimeLocal = endTimeLocal.substring(0,19).replace("T", " ");
						}
						forecast.setEndTimeLocal(endTimeLocal);				
					}
					
					forecast.setArea(area);
					
					queueForecastHeadData(forecast);
					parseElements(forecast, forecastNode);
				}	
			}
		}
	}
	
	private void parseElements(WeatherForecast forecast, Node forecastNode) throws HFFImportException {		
		NodeList forecastTexts = forecastNode.getChildNodes();
		
		if (forecastTexts != null){
			for (int i = 0; i < forecastTexts.getLength(); i++) {
				Node forecastText = forecastTexts.item(i);
				if (forecastText != null){
					if (forecastText.getNodeName().equals("element") 
							|| forecastText.getNodeName().equals("text")) {
						
						WeatherForecastElement element = new WeatherForecastElement();
						NamedNodeMap attribs = forecastText.getAttributes();
						if (attribs != null){
							for (int a = 0; a < attribs.getLength(); a++) {
								Node elementNode = attribs.item(a);
								if (elementNode!=null){								
									String nodeName = elementNode.getNodeName();
									String nodeValue = elementNode.getNodeValue();
									if (nodeName != null && nodeName.equals("type")){
										element.setElementName(nodeValue);
									}
									if (nodeName != null && nodeName.equals("units")){
										element.setElementUnit(nodeValue);
									}								
								}
							}							
						}
						element.setElementValue(forecastText.getTextContent());
						element.setWeatherForecast(forecast);
						
						queueForecastDetailData(element);
					}					
				}
			}			
		}
	}	

	private void queueAreaData(WeatherArea area) throws HFFImportException {
		if (dataLoadManager!= null){	
			AbstractDataLoader loader = (AreaDataLoaderImpl)dataLoadManager.getDataLoader(DataLoaderConstants.AREA_DATA_LOADER);
			if (loader != null){
				loader.queue(area);
			}
		}
	}
	
	private void queueForecastHeadData(WeatherForecast forecast) throws HFFImportException {
		if (dataLoadManager!= null){			
			AbstractDataLoader loader = (ForecastHeadDataLoaderImpl)dataLoadManager.getDataLoader(DataLoaderConstants.FORECAST_HEAD_DATA_LOADER);
			if (loader != null){
				loader.queue(forecast);
			}	
		}
	}	
	
	private void queueForecastDetailData(WeatherForecastElement element) throws HFFImportException {
		if (dataLoadManager!= null){			
			AbstractDataLoader loader = (ForecastDetailDataLoaderImpl)dataLoadManager.getDataLoader(DataLoaderConstants.FORECAST_DETAIL_DATA_LOADER);
			if (loader != null){
				loader.queue(element);
			}		
		}
	}
}
