package es.bsc.inb.limtox.services;

import java.util.List;

import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundHepatotoxicityTermPattern;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInductionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInhibitionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.Marker;

public interface DictionaryService {

	public void execute();
	
	public List<ChemicalCompound> getChemicalCompounds();
	
	public List<HepatotoxicityTerm> getHepatotoxicityTerms();
	
	public List<Cytochrome> getCytochromes();
	
	public List<Marker> getMarkers();
	
	public List<CytochromeChemicalCompoundPattern> getCytochromeChemicalCompoundPatterns();
	
	public List<CytochromeChemicalCompoundInductionPattern> getCytochromeChemicalCompoundInductionPatterns();
	
	public List<CytochromeChemicalCompoundInhibitionPattern> getCytochromeChemicalCompoundInhibitionPatterns();
	
	public List<ChemicalCompoundHepatotoxicityTermPattern> getChemicalCompoundHepatotoxicityTermPatterns();
	
}
