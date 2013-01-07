package au.com.hff.xml;

import au.com.hff.exception.HFFImportException;


/**
 * @author richard.riviere
 *
 */
public interface SaxWeatherParser extends XMLParser{
	public void parse(String xml) throws HFFImportException;
	public void parse(String xml, String xPath) throws HFFImportException;
}
