package es.bsc.inb.limtox.services;

import java.util.List;

import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;

public interface DictionaryService {

	public void execute();
	
	public List<ChemicalCompound> getChemicalCompounds();
	
	public List<HepatotoxicityTerm> getHepatotoxicityTerms();
	
	public List<Cytochrome> getCytochromes();
	
	public List<CytochromeChemicalCompoundPattern> getCytochromeChemicalCompoundPatterns();
	
}
