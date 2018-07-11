package es.bsc.inb.limtox.services;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.daos.SectionDao;
import es.bsc.inb.limtox.model.Section;


@Service
public class SectionServiceImpl implements SectionService{
	
	private SectionDao sectionsDao;
	
	private HashMap<String,Section> sections = null;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	public void execute() {
		try {
			log.debug("DictionaryServiceImpl :: execute :: loading chemical compounds");
			sections = sectionsDao.findAllAsHash();
			log.debug("DictionaryServiceImpl :: execute :: end loading chemical compounds");
		} catch (Exception e) {
			log.error("DictionaryServiceImpl :: execute :: loading dictionaries ", e );
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public Section getSection(String key) {
		if(key.contains("_LIMTOX>.")) {
			String name = key.substring(1, key.indexOf("_LIMTOX>."));
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			return new Section(name, key);
		}else {
			return null;
		}
	}
}
