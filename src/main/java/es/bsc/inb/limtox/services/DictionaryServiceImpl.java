package es.bsc.inb.limtox.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.daos.ChemicalCompoundHepatotoxicityTermPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInductionPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInhibitionPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundPatternDao;
import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.daos.MarkerDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundHepatotoxicityTermPattern;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInductionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInhibitionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.Marker;


@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	@Autowired
	@Qualifier("chemicalCompoundDaoJPAImpl")
	private ChemicalCompoundDao chemicalCompoundDao;
	@Autowired
	@Qualifier("hepatotoxicityTermDaoJPAImpl")
	private HepatotoxicityTermDao hepatotoxicityTermDao;
	@Autowired
	@Qualifier("cytochromeDaoJPAImpl")
	private CytochromeDao cytochromeDao;
	
	@Autowired
	@Qualifier("markerDaoJSONImpl")
	private MarkerDao markerDao;
	
	@Autowired
	@Qualifier("cytochromeChemicalCompoundPatternDaoJSONImpl")
	private CytochromeChemicalCompoundPatternDao cytochromeChemicalCompoundPatternDao;
	
	@Autowired
	@Qualifier("cytochromeChemicalCompoundInductionPatternDaoJSONImpl")
	private CytochromeChemicalCompoundInductionPatternDao cytochromeChemicalCompoundInductionPatternDao;
	
	@Autowired
	@Qualifier("cytochromeChemicalCompoundInhibitionPatternDaoJSONImpl")
	private CytochromeChemicalCompoundInhibitionPatternDao cytochromeChemicalCompoundInhibitionPatternDao;
	
	@Autowired
	@Qualifier("chemicalCompoundHepatotoxicityTermPatternDaoJSONImpl")
	private ChemicalCompoundHepatotoxicityTermPatternDao chemicalCompoundHepatotoxicityTermPatternDao;
	
	private List<ChemicalCompound> chemicalCompounds = null;
	
	private List<HepatotoxicityTerm> hepatotoxicityTerms = null;

	private List<Cytochrome> cytochromes = null;
	
	private List<Marker> markers = null;
	
	private List<CytochromeChemicalCompoundPattern> cytochromeChemicalCompoundPatterns = null;
	
	private List<CytochromeChemicalCompoundInductionPattern> cytochromeChemicalCompoundInductionPatterns = null;
	
	private List<CytochromeChemicalCompoundInhibitionPattern> cytochromeChemicalCompoundInhibitionPatterns = null;
	
	private List<ChemicalCompoundHepatotoxicityTermPattern> chemicalCompoundHepatotoxicityTermPatterns = null;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	public void execute() {
		try {
			log.debug("DictionaryServiceImpl :: execute :: loading chemical compounds");
			chemicalCompounds = chemicalCompoundDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading chemical compounds");
			log.debug("DictionaryServiceImpl :: execute :: loading hepatotoxicity terms");
			hepatotoxicityTerms = hepatotoxicityTermDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: loading cytochromes");
			cytochromes = cytochromeDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading cytochromes");
			log.debug("DictionaryServiceImpl :: execute :: loading markers");
			markers = markerDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading markers");
			
			log.debug("DictionaryServiceImpl :: execute :: loading compound compound hepatotoxicity term");
			chemicalCompoundHepatotoxicityTermPatterns = chemicalCompoundHepatotoxicityTermPatternDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading compound compound hepatotoxicity term");
			
			log.debug("DictionaryServiceImpl :: execute :: loading cytochrome compound relations");
			cytochromeChemicalCompoundPatterns = cytochromeChemicalCompoundPatternDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: cytochrome compound relations");
			log.debug("DictionaryServiceImpl :: execute :: loading cytochrome compound induction relations");
			cytochromeChemicalCompoundInductionPatterns = cytochromeChemicalCompoundInductionPatternDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: cytochrome compound induction relations");
			log.debug("DictionaryServiceImpl :: execute :: loading cytochrome compound inhibition relations");
			cytochromeChemicalCompoundInhibitionPatterns = cytochromeChemicalCompoundInhibitionPatternDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: cytochrome compound inhibition relations");
			
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

	

	public List<Marker> getMarkers() {
		return markers;
	}

	
	


	public List<ChemicalCompoundHepatotoxicityTermPattern> getChemicalCompoundHepatotoxicityTermPatterns() {
		return chemicalCompoundHepatotoxicityTermPatterns;
	}

	public void setChemicalCompoundHepatotoxicityTermPatterns(
			List<ChemicalCompoundHepatotoxicityTermPattern> chemicalCompoundHepatotoxicityTermPatterns) {
		this.chemicalCompoundHepatotoxicityTermPatterns = chemicalCompoundHepatotoxicityTermPatterns;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}



	public List<CytochromeChemicalCompoundPattern> getCytochromeChemicalCompoundPatterns() {
		return cytochromeChemicalCompoundPatterns;
	}

	public void setCytochromeChemicalCompoundPatterns(
			List<CytochromeChemicalCompoundPattern> cytochromeChemicalCompoundPatterns) {
		this.cytochromeChemicalCompoundPatterns = cytochromeChemicalCompoundPatterns;
	}

	public List<CytochromeChemicalCompoundInductionPattern> getCytochromeChemicalCompoundInductionPatterns() {
		return cytochromeChemicalCompoundInductionPatterns;
	}

	public void setCytochromeChemicalCompoundInductionPatterns(
			List<CytochromeChemicalCompoundInductionPattern> cytochromeChemicalCompoundInductionPatterns) {
		this.cytochromeChemicalCompoundInductionPatterns = cytochromeChemicalCompoundInductionPatterns;
	}

	public List<CytochromeChemicalCompoundInhibitionPattern> getCytochromeChemicalCompoundInhibitionPatterns() {
		return cytochromeChemicalCompoundInhibitionPatterns;
	}

	public void setCytochromeChemicalCompoundInhibitionPatterns(
			List<CytochromeChemicalCompoundInhibitionPattern> cytochromeChemicalCompoundInhibitionPatterns) {
		this.cytochromeChemicalCompoundInhibitionPatterns = cytochromeChemicalCompoundInhibitionPatterns;
	}
	
	
}
