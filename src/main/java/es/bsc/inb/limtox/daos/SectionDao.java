package es.bsc.inb.limtox.daos;

import java.util.HashMap;

import es.bsc.inb.limtox.model.Section;


public interface SectionDao extends GenericDao<Section>{

	public HashMap<String,Section> findAllAsHash();
	
}
