package es.bsc.inb.limtox.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundPatternDao;
import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;


@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	@Autowired
	private ChemicalCompoundDao chemicalCompoundDao;
	@Autowired
	private HepatotoxicityTermDao hepatotoxicityTermDao;
	@Autowired
	private CytochromeDao cytochromeDao;
	@Autowired
	private CytochromeChemicalCompoundPatternDao cytochromeChemicalCompoundPatterDao;
	
	private List<ChemicalCompound> chemicalCompounds = null;
	
	private List<HepatotoxicityTerm> hepatotoxicityTerms = null;

	private List<Cytochrome> cytochromes = null;

	private List<CytochromeChemicalCompoundPattern> cytochromeChemicalCompoundPatterns = null;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	public void execute() {
		try {
			log.debug("DictionaryServiceImpl :: execute :: loading chemical compounds");
			chemicalCompounds = chemicalCompoundDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading chemical compounds");
			log.debug("DictionaryServiceImpl :: execute :: loading hepatotoxicity terms");
			hepatotoxicityTerms = hepatotoxicityTermDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading cytochromes");
			cytochromes = cytochromeDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading cytochromes");
			cytochromeChemicalCompoundPatterns = cytochromeChemicalCompoundPatterDao.findAll();
		} catch (Exception e) {
			log.error("DictionaryServiceImpl :: execute :: loading dictionaries ", e );
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	

	public List<ChemicalCompound> getChemicalCompounds() {
		return chemicalCompounds;
	}



	public void setChemicalCompounds(List<ChemicalCompound> chemicalCompounds) {
		this.chemicalCompounds = chemicalCompounds;
	}



	public List<HepatotoxicityTerm> getHepatotoxicityTerms() {
		return hepatotoxicityTerms;
	}



	public void setHepatotoxicityTerms(List<HepatotoxicityTerm> hepatotoxicityTerms) {
		this.hepatotoxicityTerms = hepatotoxicityTerms;
	}



	public List<Cytochrome> getCytochromes() {
		return cytochromes;
	}



	public void setCytochromes(List<Cytochrome> cytochromes) {
		this.cytochromes = cytochromes;
	}



	public List<CytochromeChemicalCompoundPattern> getCytochromeChemicalCompoundPatterns() {
		return cytochromeChemicalCompoundPatterns;
	}

	public void setCytochromeChemicalCompoundPatterns(
			List<CytochromeChemicalCompoundPattern> cytochromeChemicalCompoundPatterns) {
		this.cytochromeChemicalCompoundPatterns = cytochromeChemicalCompoundPatterns;
	}

	
}
