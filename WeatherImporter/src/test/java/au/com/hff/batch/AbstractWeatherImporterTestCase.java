package au.com.hff.batch;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class  AbstractWeatherImporterTestCase extends TestCase {
	
	protected static final String PACKAGE_ROOT = "src/main/";
	protected static final String SPRING_CONFIG_ROOT = "WebContent/WEB-INF/";
	protected static final String APPLICATION_CONTEXT_TEST = 
			PACKAGE_ROOT +
			SPRING_CONFIG_ROOT + 
			"spring-servlet.xml";
		
	protected static ApplicationContext ctx;
	
	public AbstractWeatherImporterTestCase() {
		super();
		init();
	}
	
	public AbstractWeatherImporterTestCase(String testCase) {
		super(testCase);
		init();
	}
	
	private void init()	{
		if (ctx == null)	{
			try {
				ctx = 
					new FileSystemXmlApplicationContext(getConfigLocations());
				
			} catch(Exception e)	{
				e.printStackTrace();
			}
		}
	}
	
	protected String[] getConfigLocations() {
		return new String[] { APPLICATION_CONTEXT_TEST };
	}

}
