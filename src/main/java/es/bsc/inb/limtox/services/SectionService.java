package es.bsc.inb.limtox.services;

import es.bsc.inb.limtox.model.Section;

public interface SectionService {

	public void execute();

	public Section getSection(String key);
}
