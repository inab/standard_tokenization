package es.bsc.inb.limtox.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.daos.ChemicalCompoundHepatotoxicityTermPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInductionPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInhibitionPatternDao;
import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundPatternDao;
import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.daos.MarkerChemicalCompoundPatternDao;
import es.bsc.inb.limtox.daos.MarkerDao;
import es.bsc.inb.limtox.daos.MeshChemicalCompoundDao;
import es.bsc.inb.limtox.daos.TaxonomyDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundHepatotoxicityTermPattern;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInductionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInhibitionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundMetabolismPattern;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.Marker;
import es.bsc.inb.limtox.model.MarkerChemicalCompoundPattern;
import es.bsc.inb.limtox.model.MeshChemicalCompound;
import es.bsc.inb.limtox.model.Taxonomy;

/**
 * Service for the Dictionaries operations 
 * @author jcorvi
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private ChemicalCompoundDao chemicalCompoundDao;
	
	@Autowired
	private MeshChemicalCompoundDao meshChemicalCompoundDao;
	
	@Autowired
	private HepatotoxicityTermDao hepatotoxicityTermDao;
	
	@Autowired
	private CytochromeDao cytochromeDao;
	
	@Autowired
	private MarkerDao markerDao;
	
	@Autowired
	private CytochromeChemicalCompoundPatternDao cytochromeChemicalCompoundPatternDao;
	
	@Autowired
	private CytochromeChemicalCompoundInductionPatternDao cytochromeChemicalCompoundInductionPatternDao;
	
	@Autowired
	private CytochromeChemicalCompoundInhibitionPatternDao cytochromeChemicalCompoundInhibitionPatternDao;
	
	@Autowired
	private ChemicalCompoundHepatotoxicityTermPatternDao chemicalCompoundHepatotoxicityTermPatternDao;
	
	@Autowired
	private TaxonomyDao taxonomyDao;
	
	@Autowired
	private MarkerChemicalCompoundPatternDao markerChemicalCompoundPatternDao;
	
	/**
	 * Chemical compounds Dictionary
	 */
	private List<ChemicalCompound> chemicalCompounds = null;
	/**
	 * Mesh Chemical compounds Dictionary
	 */
	private List<MeshChemicalCompound> meshChemicalCompounds = null;
	/**
	 * Hepatotoxicity Term Dictionary
	 */
	private List<HepatotoxicityTerm> hepatotoxicityTerms = null;
	/**
	 * Cytochromes Dictionary
	 */
	private List<Cytochrome> cytochromes = null;
	/**
	 * Markers Dictionary
	 */
	private List<Marker> markers = null;
	/**
	 * Metabolism Relation between Chemical compounds and Cytochromes
	 */
	private List<CytochromeChemicalCompoundMetabolismPattern> cytochromeChemicalCompoundPatterns = null;
	/**
	 * Induction Relation between Chemical compounds and Cytochromes
	 */
	private List<CytochromeChemicalCompoundInductionPattern> cytochromeChemicalCompoundInductionPatterns = null;
	/**
	 * Inhibition Relation between Chemical compounds and Cytochromes
	 */
	private List<CytochromeChemicalCompoundInhibitionPattern> cytochromeChemicalCompoundInhibitionPatterns = null;
	/**
	 * Relation between Chemical compounds and Hepatotoxicity Term
	 */
	private List<ChemicalCompoundHepatotoxicityTermPattern> chemicalCompoundHepatotoxicityTermPatterns = null;
	
	/**
	 * Relation between Chemical compounds and Hepatotoxicity Term
	 */
	private List<MarkerChemicalCompoundPattern> markerChemicalCompoundPatterns = null;
	

	
	/**
	 * Taxonomies Entity. Species
	 */
	private List<Taxonomy> taxonomies = null;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	public void execute() {
		try {
			log.debug("DictionaryServiceImpl :: execute :: loading chemical compounds");
			chemicalCompounds = chemicalCompoundDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading chemical compounds");
			
			log.debug("DictionaryServiceImpl :: execute :: loading mesh chemical compounds");
			meshChemicalCompounds = meshChemicalCompoundDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading mesh chemical compounds");
			
			log.debug("DictionaryServiceImpl :: execute :: loading hepatotoxicity terms");
			hepatotoxicityTerms = hepatotoxicityTermDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: loading cytochromes");
			cytochromes = cytochromeDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading cytochromes");
			log.debug("DictionaryServiceImpl :: execute :: loading markers");
			markers = markerDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading markers");
			
			log.debug("DictionaryServiceImpl :: execute :: loading taxonomies");
			taxonomies = taxonomyDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: end loading taxonomies");
			
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
			
			log.debug("DictionaryServiceImpl :: execute :: loading cytochrome compound inhibition relations");
			markerChemicalCompoundPatterns = markerChemicalCompoundPatternDao.findAll();
			log.debug("DictionaryServiceImpl :: execute :: cytochrome compound inhibition relations");
			
			
			
		} catch (Exception e) {
			log.error("DictionaryServiceImpl :: execute :: loading dictionaries ", e );
			System.out.println(e);
			e.printStackTrace();
		}
	
	}
	
	public List<Taxonomy> getTaxonomies() {
		return taxonomies;
	}

	public void setTaxonomies(List<Taxonomy> taxonomies) {
		this.taxonomies = taxonomies;
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

	public List<CytochromeChemicalCompoundMetabolismPattern> getCytochromeChemicalCompoundPatterns() {
		return cytochromeChemicalCompoundPatterns;
	}

	public void setCytochromeChemicalCompoundPatterns(
			List<CytochromeChemicalCompoundMetabolismPattern> cytochromeChemicalCompoundPatterns) {
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

	public List<MeshChemicalCompound> getMeshChemicalCompounds() {
		return meshChemicalCompounds;
	}

	public void setMeshChemicalCompounds(List<MeshChemicalCompound> meshChemicalCompounds) {
		this.meshChemicalCompounds = meshChemicalCompounds;
	}

	public List<MarkerChemicalCompoundPattern> getMarkerChemicalCompoundPatterns() {
		return markerChemicalCompoundPatterns;
	}

	public void setMarkerChemicalCompoundPatterns(List<MarkerChemicalCompoundPattern> markerChemicalCompoundPatterns) {
		this.markerChemicalCompoundPatterns = markerChemicalCompoundPatterns;
	}
	
}
