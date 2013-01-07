/**
 * 
 */
package au.com.hff.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import au.com.hff.exception.HFFImportException;

/**
 * @author ibm_robzimm
 * 
 * Utility class for manipulating XML.
 */
public class XMLUtility {

	
	/**
	 * Parse an XML document and creates a DOM document.
	 * @param xmlStr The XML as a <code>String</code>
	 * @return A DOM document.
	 * @throws CustomerException 
	 */
	public static Document parseXML(String xmlStr) throws HFFImportException {
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			
		} catch (SAXException e) {
			System.err.println("SAXException parsing XML: " + e.toString());
			throw new HFFImportException(e);
		} catch (IOException e) {
			System.err.println("IOException parsing XML: " + e.toString());
			throw new HFFImportException(e);
		} catch (ParserConfigurationException e) {
			System.err.println("ParserConfigurationException parsing XML: " + e.toString());
			throw new HFFImportException(e);
		}
		return doc;
	}
	
	/**
	 * Method to search a DOM document using XPath searching.
	 * 
	 * @param doc The DOM document.
	 * @param xPath The xpath expression.
	 * @return The value determined by the xpath expression.
	 * @throws CustomerException
	 */
	public static String findByXpath(Document doc, String xPath) throws HFFImportException {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			String result = xpath.evaluate(xPath, doc);
			return result;
		} catch (XPathExpressionException e) {
			System.err.println("Error evaluating XPath: " + xPath + e.toString());
			throw new HFFImportException(e);
		}
	}
	
	/**
	 * Method to search a DOM document using XPath searching.
	 * 
	 * @param doc The DOM document.
	 * @param xPath The xpath expression.
	 * @return The value determined by the xpath expression.
	 * @throws CustomerException
	 */
	public static String findByXpath(Element element, String xPath) throws HFFImportException {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			return xpath.evaluate(xPath, element);
		} catch (XPathExpressionException e) {
			System.err.println("Error evaluating XPath: " + xPath + e.toString());
			throw new HFFImportException(e);
		}
	}
	
	/**
	 * Method to search a DOM document using XPath searching.
	 * 
	 * @param doc The DOM document.
	 * @param xPath The xpath expression.
	 * @return The value determined by the xpath expression.
	 * @throws CustomerException
	 */
	public static Node findNodeByXpath(Document doc, String xPath) throws HFFImportException {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			Node node = (Node) xpath.evaluate(xPath, doc, XPathConstants.NODE);
			return node;
		} catch (XPathExpressionException e) {
			System.err.println("Error evaluating XPath: " + xPath + e.toString());
			throw new HFFImportException(e);
		}
	}
	
	/**
	 * Method to search a DOM document using XPath searching.
	 * 
	 * @param doc The DOM document.
	 * @param xPath The xpath expression.
	 * @return The value determined by the xpath expression.
	 * @throws CustomerException
	 */
	public static String findNodeXMLByXpath(Document doc, String xPath) throws HFFImportException {
		String xmlString = null;
		try {
			Node node = findNodeByXpath(doc, xPath);
			
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "no");
			   
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(node);
			trans.transform(source, result);
			xmlString = sw.toString();
		} catch (Exception e) {
			System.err.println("Error evaluating XPath: " + xPath + e.toString());
			throw new HFFImportException(e);
		}
		return xmlString;
	}
	
	/**
	 * Method to search a DOM document using XPath searching.
	 * 
	 * @param doc The DOM document.
	 * @param xPath The xpath expression.
	 * @return The value determined by the xpath expression.
	 * @throws CustomerException
	 */
	public static NodeList findNodeListByXpath(Document doc, String xPath) throws HFFImportException {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			return (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			System.err.println("Error evaluating XPath: " + xPath + e.toString());
			throw new HFFImportException(e);
		}
	}
}
