package au.com.hff.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.constant.FTPConstants;
import au.com.hff.constant.XMLConstants;
import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.WeatherImportCatalogManager;
import au.com.hff.manager.WeatherImportStageManager;

/**
 * @author richard.riviere
 *
 */
public class BOMFileRetrieverImpl implements BOMFileRetriever{
	
	private Log logger = LogFactory.getLog(BOMFileRetrieverImpl.class);
	
	@Autowired
	WeatherImportCatalogManager weatherImportCatalogManager;
	
	@Autowired
	WeatherImportStageManager weatherImportStageManager;

	public void retrieveFiles() throws HFFImportException{
		List<String> fileNames = getFilesList();
		ftpDownload(fileNames);
	}
	
	public List<String> getFilesList() throws HFFImportException{
		return weatherImportCatalogManager.getFilesList();
	}	
	
    public String ftpDownload(List<String> filesNames) throws HFFImportException{
        FTPClient client = new FTPClient();
        InputStream is = null;
        String xml = null;
        
        try {
        	// init connection
            client.connect(FTPConstants.BOM_WEATHER_FTP_ADDRESS);
            client.login(FTPConstants.BOM_WEATHER_FTP_USER, FTPConstants.BOM_WEATHER_FTP_PASSWORD); 
            // download files
            for (String fileName : filesNames){
            	long startTime = System.currentTimeMillis();
            	is = client.retrieveFileStream(
            			FTPConstants.BOM_WEATHER_FTP_PATH+
            			fileName+XMLConstants.INPUT_XML_EXTENSION);  
            	writeToFTPLog(fileName, startTime);
            	
                xml = IOUtils.toString(is);
                persistFile(fileName, xml);
            }
        } catch (IOException e) {
            throw new HFFImportException(e);
        } finally {
            try {
                if (is != null) {
                	is.close();
                }
                client.disconnect();
            } catch (IOException e) {
            	throw new HFFImportException(e);
            }
        }
        return xml;
    }
    
	public void persistFile(String productId,String xml)throws HFFImportException {
		weatherImportStageManager.saveXMLFile(productId,xml);
	}  
	
	protected void writeToFTPLog(String fileName, long startTime) {
		long endTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()){
			logger.info("FTP FILE: " + fileName + ", Timing: " + (endTime - startTime));			
		}
	}		
}
