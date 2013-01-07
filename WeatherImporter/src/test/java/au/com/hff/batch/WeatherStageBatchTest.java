package au.com.hff.batch;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author richard.riviere
 *
 */
public class WeatherStageBatchTest extends AbstractWeatherImporterTestCase{
	
	AbstractBatchJob weatherStageBatch;

	public WeatherStageBatchTest() {
		super();
		try {
			weatherStageBatch = (WeatherStageBatchImpl) ctx.getBean("weatherStageBatch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	@Transactional
	public void testExecuteWeatherStageBatch()	{
		try {
			weatherStageBatch.run();
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}
}
