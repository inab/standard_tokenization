package es.bsc.inb.limtox.services;

import java.util.HashMap;

import es.bsc.inb.limtox.model.Section;

public interface SectionService {

	public void execute();

	public HashMap<String, Section> getSections();
}
