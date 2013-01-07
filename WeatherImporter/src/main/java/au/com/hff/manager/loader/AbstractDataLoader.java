package au.com.hff.manager.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.hff.domain.AbstractBulkInsertDomainObject;
import au.com.hff.domain.WeatherStager;
import au.com.hff.exception.HFFImportException;
import au.com.hff.manager.WeatherImportStageManager;

/**
 * @author richard.riviere
 *
 */
public abstract class AbstractDataLoader {
	
	@Autowired
	WeatherImportStageManager weatherImportStageManager;
	
	protected List<AbstractBulkInsertDomainObject> data 
		= new ArrayList<AbstractBulkInsertDomainObject>();

	public void queue(AbstractBulkInsertDomainObject c) {
		this.data.add(c);
	}
	
	public void queue(List<AbstractBulkInsertDomainObject> c) {
		this.data.addAll(c);
	}	
	
	public List<AbstractBulkInsertDomainObject> getDataList() {
		return data;
	}	
	
	protected void updateStaging(List<WeatherStager> stagers) throws HFFImportException {
		weatherImportStageManager.updateStaging(stagers);
	}
	
	public String toString(){
		return this.data.toString();
	}
}
