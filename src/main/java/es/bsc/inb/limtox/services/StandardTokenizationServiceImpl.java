package es.bsc.inb.limtox.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundCytochromeSentence;
import es.bsc.inb.limtox.model.ChemicalCompoundSentence;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;
import es.bsc.inb.limtox.model.CytochromeSentence;
import es.bsc.inb.limtox.model.Document;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.HepatotoxicityTermSentence;
import es.bsc.inb.limtox.model.PubMedDocument;
import es.bsc.inb.limtox.model.Section;
import es.bsc.inb.limtox.model.Sentence;
import es.bsc.inb.limtox.util.CoreNLP;
import es.bsc.inb.limtox.util.StringUtil;


@Service
public class StandardTokenizationServiceImpl {

	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private SectionService sectionService;
	protected Log log = LogFactory.getLog(this.getClass());
	@Transactional()
	public void execute(String sourceId, String file_path) {
		try {
			StanfordCoreNLP pipeline = CoreNLP.getStandfordCoreNLP();
			String text = StringUtil.readFile(file_path, Charset.forName("UTF-8"));
			Annotation document= new Annotation(text);
		    pipeline.annotate(document);
			List<CoreMap> sentences = document.get(SentencesAnnotation.class);
			PubMedDocument document_model = (PubMedDocument)documentDao.findBySourceId(sourceId);
			if(document_model!=null){
				//Then delete old relations
				document_model.getSentences().clear();
			}else{
				document_model = new PubMedDocument(sourceId);
			}
			Section section = null;
			for (CoreMap sentence: sentences) {
				String sentence_text_original = sentence.get(TextAnnotation.class);
				String sentence_text=sentence_text_original;
				Sentence sentence_model = new Sentence(document_model, sentence_text, section);
				sentence_model.setDocument(document_model);
				
				//List<CoreLabel> tokens= sentence.get(TokensAnnotation.class);
				
				if(sectionService.getSections().get(sentence_text_original)!=null) {
					section=sectionService.getSections().get(sentence_text_original);
				}else {
					
					//TODO agregar los flags para solamente indexar lo deseado
					
					findChemicalCompounds(document_model, sentence_model, sentence_text);
					
					findHepatotoxicityTerms(document_model, sentence_model, sentence_text);
					
					findCytochromes(document_model, sentence_model, sentence_text);
					
					findChemicalCompoundCytochromeRelations(document_model, sentence_model, sentence_text);
				}
				if(sentence_model!=null && (sentence_model.getChemicalCompoundSentences().size()>0 || 
						sentence_model.getHepatotoxicityTermSentences().size()>0 ||
						sentence_model.getCytochromeSentences().size()>0 ||
						sentence_model.getChemicalCompoundCytochromeSentences().size()>0)) {
						document_model.getSentences().add(sentence_model);
				}
				
			}
			if (document_model!=null && document_model.getSentences().size()>0) {
				documentDao.save(document_model);
			}
		} catch (IOException e) {
			log.error("StandardTokenizerServiceImpl :: execute :: Processiong the document " + file_path, e );
			System.out.println(e);
			e.printStackTrace();
		} catch (MoreThanOneEntityException e) {
			log.error("StandardTokenizerServiceImpl :: execute :: Processiong the document " + file_path, e );
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("StandardTokenizerServiceImpl :: execute :: Processiong the document " + file_path, e );
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param key_to_search
	 * @param sentence
	 * @return
	 */
	private int sentenceContains(String key_to_search, String sentence) {
		if(key_to_search!=null && !key_to_search.trim().equals("")) {
			String to_search = Pattern.quote(key_to_search);
	    	String chemicalCompoundValue = "(\\W|\\s|^)"+to_search+"(\\W|\\s|$)";
	    	Pattern p = Pattern.compile(chemicalCompoundValue);
			Matcher m = p.matcher(sentence);
			Integer count = 0;
			while (m.find())
			    count++;
			return count;
		}
		return 0;
	}
	
	
	/**
	 * 
	 * @param document_model
	 * @param sentence_model
	 * @param section
	 * @param sentence_text
	 * @param tokens
	 */
	private void findChemicalCompoundCytochromeRelations(PubMedDocument document, Sentence sentence, String sentence_text) {
		for (ChemicalCompound chemicalCompound : dictionaryService.getChemicalCompounds()) {
			int quantity_compound = sentenceContains(chemicalCompound.getName(), sentence_text);
			if(quantity_compound!=0) {
				//compound found, now look for Cysp
				for (Cytochrome cytochrome : dictionaryService.getCytochromes()) {
					int quantity_cys = sentenceContains(cytochrome.getName(), sentence_text);
					if(quantity_cys!=0) {
						for (CytochromeChemicalCompoundPattern pattern : dictionaryService.getCytochromeChemicalCompoundPatterns()) {
							String pattern_text = pattern.getSubstrate_pattern();
							pattern_text.replace("[SUBSTANCE]", chemicalCompound.getName());
							pattern_text.replace("[P450_CYPS]", cytochrome.getName());
							int quantity_pattern = sentenceContains(pattern_text, sentence_text);
							if(quantity_pattern!=0) {
								System.out.println(pattern_text);
							}
						}
						//Save Comention Compound and Cysp 
						ChemicalCompoundCytochromeSentence chemicalCompoundCytochromeSentence = new ChemicalCompoundCytochromeSentence(chemicalCompound, cytochrome,1f, quantity_cys, sentence);
						sentence.getChemicalCompoundCytochromeSentences().add(chemicalCompoundCytochromeSentence);
					}
				}	
			}
		}	
	}

	/**
	 * 
	 * @param document
	 * @param section
	 * @param sentence_text
	 * @param tokens
	 */
	private void findHepatotoxicityTerms(PubMedDocument document, Sentence sentence, String sentence_text) {
		for (HepatotoxicityTerm hepatotoxicityTerm : dictionaryService.getHepatotoxicityTerms()) {
			int quantity = sentenceContains(hepatotoxicityTerm.getTerm(), sentence_text);
			if(quantity!=0) {
				HepatotoxicityTermSentence hepatotoxicityTermSentence = new HepatotoxicityTermSentence(hepatotoxicityTerm,1f, quantity, sentence);
				sentence.getHepatotoxicityTermSentences().add(hepatotoxicityTermSentence);
			}
		}
	}

	/**
	 * 
	 * @param document
	 * @param section
	 * @param sentence_text
	 * @param tokens
	 */
	private void findCytochromes(Document document, Sentence sentence, String sentence_text) {
		for (Cytochrome cytochrome : dictionaryService.getCytochromes()) {
			this.findCytochromeByFieldType(cytochrome.getName(), sentence_text, sentence, cytochrome);
			this.findCytochromeByFieldType(cytochrome.getEntityId(), sentence_text, sentence, cytochrome);
			this.findCytochromeByFieldType(cytochrome.getCanonical(), sentence_text, sentence, cytochrome);
		}
	}

	
	/**
	 * Findings of ChemicalCompunds with synonyms
	 * @param sourceId
	 * @param document_model
	 * @param first_finding_on_document
	 * @param section
	 * @param sentence_text
	 * @return
	 * @throws MoreThanOneEntityException
	 */
	private void findChemicalCompounds(PubMedDocument document, Sentence sentence, String sentence_text) {
		for (ChemicalCompound chemicalCompound : dictionaryService.getChemicalCompounds()) {
			//Find and store differentes chemicalCompounds Key Words
			this.findChemicalCompoundByFieldType(chemicalCompound.getName(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getSmile(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getInchi(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getChemPlusId(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getChebi(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getDrugBankId(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getKeggCompound(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getMesh(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getCasRegistryNumber(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getHumanMetabolome(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getPubChemCompundId(), sentence_text, sentence, chemicalCompound);
			this.findChemicalCompoundByFieldType(chemicalCompound.getPubChemSubstance(), sentence_text, sentence, chemicalCompound);
		}
	}
	
	/**
	 * 
	 * @param chemicalCompoundValue
	 * @param sentence_text
	 * @param document
	 * @param section
	 * @param chemicalCompound
	 */
	private void findCytochromeByFieldType(String cytochromeValue,String sentence_text,Sentence sentence, Cytochrome cytochrome) {
		int quantity = sentenceContains(cytochromeValue, sentence_text);
		if(quantity!=0) {
			CytochromeSentence cytochromeSentence = new CytochromeSentence(cytochrome, 1f, quantity, sentence);
			sentence.getCytochromeSentences().add(cytochromeSentence);
		}
		
	}
	
	/**
	 * 
	 * @param chemicalCompoundValue
	 * @param sentence_text
	 * @param document
	 * @param section
	 * @param chemicalCompound
	 */
	private void findChemicalCompoundByFieldType(String chemicalCompoundValue,String sentence_text, 
			Sentence sentence, ChemicalCompound chemicalCompound) {
		int quantity = sentenceContains(chemicalCompoundValue, sentence_text);
		if(quantity!=0) {
			ChemicalCompoundSentence chemicalCompoundSentence = new ChemicalCompoundSentence(chemicalCompound, 1f, quantity, sentence);
			sentence.getChemicalCompoundSentences().add(chemicalCompoundSentence);
		}
	}
}
