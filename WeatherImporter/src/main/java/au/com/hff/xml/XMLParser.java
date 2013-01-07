/**
 * 
 */
package au.com.hff.xml;

import au.com.hff.exception.HFFImportException;

/**
 * @author richard.riviere
 *
 */
public interface XMLParser {
	public void parse(String xml) throws HFFImportException;
}
