package es.bsc.inb.limtox.services;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.daos.SectionDao;
import es.bsc.inb.limtox.model.Section;


@Service
public class SectionServiceImpl implements SectionService{
	
	@Autowired
	@Qualifier("sectionDaoJPAImpl")
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

	public HashMap<String, Section> getSections() {
		return sections;
	}

	public void setSections(HashMap<String, Section> sections) {
		this.sections = sections;
	}
	
	

}
