package au.com.hff.batch;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author richard.riviere
 *
 */
public class WeatherImportBatchTest extends AbstractWeatherImporterTestCase{
	AbstractBatchJob weatherImportBatch;
	
	public WeatherImportBatchTest() {
		super();
		try {
			weatherImportBatch = (WeatherImportBatchImpl) ctx.getBean("weatherImportBatch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	@Transactional
	public void testExecuteWeatherImportBatch()	{
		try {
			weatherImportBatch.run();
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}	
}
