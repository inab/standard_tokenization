package es.bsc.inb.limtox.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import es.bsc.inb.limtox.config.ChemSpotConfig;
import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundCytochromeSentence;
import es.bsc.inb.limtox.model.ChemicalCompoundHepatotoxicityTermPattern;
import es.bsc.inb.limtox.model.ChemicalCompoundSentence;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInductionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInhibitionPattern;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundMetabolismPattern;
import es.bsc.inb.limtox.model.CytochromeSentence;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.HepatotoxicityTermChemicalCompoundSentence;
import es.bsc.inb.limtox.model.HepatotoxicityTermSentence;
import es.bsc.inb.limtox.model.Marker;
import es.bsc.inb.limtox.model.MarkerChemicalCompoundPattern;
import es.bsc.inb.limtox.model.MarkerChemicalCompoundSentence;
import es.bsc.inb.limtox.model.MarkerSentence;
import es.bsc.inb.limtox.model.MeshChemicalCompound;
import es.bsc.inb.limtox.model.PubMedDocument;
import es.bsc.inb.limtox.model.RelationRule;
import es.bsc.inb.limtox.model.Section;
import es.bsc.inb.limtox.model.Sentence;
import es.bsc.inb.limtox.model.Taxonomy;
import es.bsc.inb.limtox.model.TaxonomySentence;
import es.bsc.inb.limtox.util.CoreNLP;
import es.bsc.inb.limtox.util.XMLUtil;


@Service
public class StandardTokenizationVersion2ServiceImpl {
//	@Autowired
//	private DocumentDao documentDao;
//	@Autowired
//	private DictionaryService dictionaryService;
//	@Autowired
//	private ChemSpotConfig chemSpotConfig;
//	
//	
//	protected Log log = LogFactory.getLog(this.getClass());
//	
//	private HashMap<String, Pattern> patterns = new HashMap<String,Pattern>();
//	
//
//	
//	public void execute(String sourceId, String file_path, String outputPath) {
//		try {
//			File xmlFile = new File(file_path);
//	        DocumentBuilder dBuilder = XMLUtil.getDocumentBuilder();
//	        Document doc = dBuilder.parse(xmlFile);
//	        doc.getDocumentElement().normalize();
//	        PubMedDocument document_model = new PubMedDocument(sourceId, outputPath);
//	        this.findingsInTextSections(doc, document_model);
//		    this.findingsKeywords(doc, document_model);
//		    if (document_model!=null && (document_model.getSentences().size()>0 || document_model.getMeshChemicalCompounds().size()>0)) {
//		    	documentDao.save(document_model);
//		    }
//	    }catch (IOException e) {
//			log.error("StandardTokenizerServiceImpl :: execute :: Processiong the document " + file_path, e );
//			System.out.println(e);
//			e.printStackTrace();
//		} catch (Exception e) {
//			log.error("StandardTokenizerServiceImpl :: execute :: Processiong the document " + file_path, e );
//			System.out.println(e);
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * Find Keywords Indexed in original Source, like Chemical Mesh
//	 * @param doc
//	 * @param document_model
//	 */
//	private void findingsKeywords(Document doc, PubMedDocument document_model) {
//		List<MeshChemicalCompound> chemical_mesh = new ArrayList<MeshChemicalCompound>();
//		NodeList chemicalMeshs = doc.getElementsByTagName("CHEMICAL");  
//		for (int count = 0; count < chemicalMeshs.getLength(); count++) {
//			Node chemical = chemicalMeshs.item(count);
//			NodeList nodes = chemical.getChildNodes();
//			MeshChemicalCompound meshChemicalCompound = new MeshChemicalCompound();
//			for (int count2 = 0; count2 < nodes.getLength(); count2++) {
//				Node node = nodes.item(count2);
//				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("NAME")) {
//					meshChemicalCompound.setName(node.getTextContent());
//				}else if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("UI")) {
//					meshChemicalCompound.setUi(node.getTextContent());
//				}
//			}
//			chemical_mesh.add(meshChemicalCompound); 
//		}
//		if(chemical_mesh.size()>0) {
//			document_model.getMeshChemicalCompounds().addAll(chemical_mesh);
//		}
//	}
//	/**
//	 * Find Dictionaries Keywords, findings in text.
//	 * @param doc
//	 * @param document_model
//	 */
//	private void findingsInTextSections(Document doc, PubMedDocument document_model) {
//		StanfordCoreNLP pipeline = CoreNLP.getStandfordCoreNLP();
//		NodeList sections = doc.getElementsByTagName("SECTIONS_LIMTOX");
//        NodeList nodeList = sections.item(0).getChildNodes();
//        for (int count = 0; count < nodeList.getLength(); count++) {
//            Node section = nodeList.item(count);
//            if (section.getNodeType() == Node.ELEMENT_NODE) {
//            	String text = section.getTextContent();
//            	String section_name = section.getNodeName();
//            	Annotation document= new Annotation(text);
//        		pipeline.annotate(document);
//        		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//        		Section section_model= new Section(section_name.replace("_LIMTOX", ""), section_name);
//        		int i_s = 0;
//        		for (CoreMap sentence: sentences) {
//        			i_s ++;
//        			String sentence_text_original = sentence.get(TextAnnotation.class);
//	    			String sentence_text=sentence_text_original.toLowerCase();
//	    			Sentence sentence_model = new Sentence(document_model, sentence_text_original, section_model,i_s);
//	    			sentence_model.setDocument(document_model);
//	    			//document text sentence to index.
//					
//	    			//Set the hepatotoxicity terms present into the sentence
//					findHepatotoxicityTerms(sentence_model, sentence_text);
//					
//					//The limtox definition says that only if only is a hepatotoxicity sentence then it will be indexed.
//					//This "only if only is a hepatotoxicity sentence" in the original limtox where done for the SVM classifier that was trained with hepatotoxicity abstracts and with 
//					//random abstract from pub med. Now we dont have the SVM Classifier used so, we use the dictionary in this first basic version and later we are going to use 
//					//the limtox 2.0 classifier to achieve this classification to know if a sentence is hepatotoxicity related or not.
//					if(sentence_model.getHepatotoxicityTermSentences().size()>0 ) {
//						//Set the chemical compounds present into the sentence
//						findChemicalCompounds(sentence_model, sentence_text);
//						//Set the markers present into the sentence
//						findMarkers(sentence_model, sentence_text);
//						//Set the Cytochromes present into the sentence
//						findCytochromes(sentence_model, sentence_text);
//						//Set the species 
//						findSpecies(sentence_model, sentence_text);
//						//Find relations between chemical compounds and hepatotoxicity terms in the sentence
//						findChemicalCompoundHepatotoxicityRelations(sentence_model, sentence_text);
//						//Find relations between chemical compounds and Cytochromes  in the sentence
//						findChemicalCompoundCytochromeRelations(sentence_model, sentence_text);
//						//Find relations between chemical compounds and Markers  in the sentence
//						findMarkerChemicalCompoundRelations(sentence_model, sentence_text);
//		    			
//						if(sentence_model!=null && (sentence_model.getChemicalCompoundSentences().size()>0 || 
//							sentence_model.getHepatotoxicityTermSentences().size()>0 ||
//							sentence_model.getCytochromeSentences().size()>0 ||
//							sentence_model.getMarkerSentences().size()>0 ||
//							sentence_model.getChemicalCompoundCytochromeSentences().size()>0 ||
//							sentence_model.getMarkerChemicalCompoundSentences().size()>0 ||
//							sentence_model.getHepatotoxicityTermChemicalCompoundSentences().size()>0 ||
//							sentence_model.getTaxonomySentences().size()>0) ) {
//								document_model.getSentences().add(sentence_model);
//						}
//					}
//				}
//        	}	
//      }
//		
//	}
//
//	/**
//	 * 
//	 * @param sentence
//	 */
//	private static void text_mining_example(CoreMap sentence) {
//		System.out.println(sentence.get(TextAnnotation.class));
//		List<CoreLabel> tokens= sentence.get(TokensAnnotation.class);
//		for (CoreLabel token: tokens){
//			String word = token.get(TextAnnotation.class);
//			String pos = token.get(PartOfSpeechAnnotation.class);
//			String ner = token.get(NamedEntityTagAnnotation.class);
//			String lemma = token.get(LemmaAnnotation.class);
//			System.out.println(word + "\t" + pos + "\t" + ner + "\t" + lemma + "\t\n");
//		}
//		String lemma = sentence.get(LemmaAnnotation.class);
//		// this is the parse tree of the current sentence
//		 Tree tree = sentence.get(TreeAnnotation.class);
//		 System.out.println(tree);
//		 
//		// this is the Stanford dependency graph of the current sentence
//		SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
//		System.out.println(dependencies); 
//	}
//	
//	/**
//	 * 
//	 * @param key_to_search
//	 * @param sentence
//	 * @return
//	 */
//	private int sentenceContains(String key_to_search, String sentence) {
//		if(key_to_search!=null && !key_to_search.trim().equals("")) {
//			Pattern pattern = patterns.get(key_to_search);
//			if(pattern==null) {
//				String to_search = Pattern.quote(key_to_search);
//		    	String patternString = "\\b(" + to_search + ")\\b";
//			    pattern = Pattern.compile(patternString);
//			    patterns.put(key_to_search, pattern);
//			}
//			Matcher m = pattern.matcher(sentence);
//			Integer count = 0;
//			while (m.find()) {
//				count++;
//				m.start();
//				m.end();
//				System.out.println("\t" + m.start() + "\t" + m.end() + "\t" +  sentence.substring(m.start(), m.end()));
//			}
//			return count;
//		}
//		return 0;
//	}
//	/**
//	 * 
//	 * @param document_model
//	 * @param sentence_model
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findChemicalCompoundCytochromeRelations(Sentence sentence, String sentence_text) {
//		for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
//			for (CytochromeSentence cytochromeSentence : sentence.getCytochromeSentences()) {
//				ChemicalCompoundCytochromeSentence chemicalCompoundCytochromeSentence = new ChemicalCompoundCytochromeSentence(chemicalCompoundSentence.getChemicalCompound(), cytochromeSentence.getCytochrome(),1f, 1, sentence);
//				sentence.getChemicalCompoundCytochromeSentences().add(chemicalCompoundCytochromeSentence);
//				chemicalCompoundCytochromeSentence.setRelationRule(RelationRule.COMENTION);
//				Boolean cut = false;
//				//Aca si leemos los patterns de induccion.
//				for (CytochromeChemicalCompoundInductionPattern pattern : dictionaryService.getCytochromeChemicalCompoundInductionPatterns()) {
//					String pattern_text = pattern.getCyp_induction_pattern();
//					pattern_text = pattern_text.replace("[substance]", chemicalCompoundSentence.getChemicalCompound().getName());
//					pattern_text = pattern_text.replace("[p450_cyps]", cytochromeSentence.getCytochrome().getName());
//					int quantity_pattern = sentenceContains(pattern_text, sentence_text);
//					if(quantity_pattern!=0) {
//						chemicalCompoundCytochromeSentence.setRelationRule(RelationRule.INDUCTION);
//						cut=true;
//						break;
//					}
//				}
//				//Aca si leemos los patterns de inhibicion.
//				if(!cut) {
//					for (CytochromeChemicalCompoundInhibitionPattern pattern : dictionaryService.getCytochromeChemicalCompoundInhibitionPatterns()) {
//						String pattern_text = pattern.getCyp_inhibition_pattern();
//						pattern_text = pattern_text.replace("[substance]", chemicalCompoundSentence.getChemicalCompound().getName());
//						pattern_text = pattern_text.replace("[p450_cyps]", cytochromeSentence.getCytochrome().getName());
//						int quantity_pattern = sentenceContains(pattern_text, sentence_text);
//						if(quantity_pattern!=0) {
//							chemicalCompoundCytochromeSentence.setRelationRule(RelationRule.INHIBITION);
//							cut=true;
//							break;
//						}
//					}
//				}
//				//Aca si leemos los patterns de metabolismo.
//				if(!cut) {
//					for (CytochromeChemicalCompoundMetabolismPattern pattern : dictionaryService.getCytochromeChemicalCompoundPatterns()) {
//						String pattern_text = pattern.getSubstrate_pattern();
//						pattern_text = pattern_text.replace("[substance]", chemicalCompoundSentence.getChemicalCompound().getName());
//						pattern_text = pattern_text.replace("[p450_cyps]", cytochromeSentence.getCytochrome().getName());
//						int quantity_pattern = sentenceContains(pattern_text, sentence_text);
//						if(quantity_pattern!=0) {
//							chemicalCompoundCytochromeSentence.setRelationRule(RelationRule.METABOLISM);
//							cut=true;
//							break;
//						}
//					}
//				}	
//				//Otra opcion seria verificar si entre medio hay palabras claves ... induction induce etc
//				//Save Comention Compound and Cysp
//				System.out.println("relation compound-cys: " + chemicalCompoundSentence.getChemicalCompound().getName() + " and " + cytochromeSentence.getCytochrome().getName());
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @param document_model
//	 * @param sentence_model
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findChemicalCompoundHepatotoxicityRelations(Sentence sentence, String sentence_text) {
//		for (HepatotoxicityTermSentence hepatotoxicityTermSentence : sentence.getHepatotoxicityTermSentences()) {
//			for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
//				HepatotoxicityTermChemicalCompoundSentence hepatotoxicityTermChemicalCompoundSentence = 
//						new HepatotoxicityTermChemicalCompoundSentence(chemicalCompoundSentence.getChemicalCompound(), hepatotoxicityTermSentence.getHepatotoxicityTerm(),1f, 1, sentence);
//				sentence.getHepatotoxicityTermChemicalCompoundSentences().add(hepatotoxicityTermChemicalCompoundSentence);
//				hepatotoxicityTermChemicalCompoundSentence.setRelationRule(RelationRule.COMENTION);
//				for (ChemicalCompoundHepatotoxicityTermPattern pattern : dictionaryService.getChemicalCompoundHepatotoxicityTermPatterns()) {
//					String pattern_text = pattern.getAdverse_pattern();
//					pattern_text = pattern_text.replace("[substance]", chemicalCompoundSentence.getChemicalCompound().getName());
//					pattern_text = pattern_text.replace("[adverse_effect]", hepatotoxicityTermSentence.getHepatotoxicityTerm().getOriginal_entry());
//					int quantity_pattern = sentenceContains(pattern_text, sentence_text);
//					if(quantity_pattern!=0) {
//						hepatotoxicityTermChemicalCompoundSentence.setRelationRule(RelationRule.ADVERSE_EFFECT);
//						break;
//					}
//				}
//				System.out.println("relation compound hepatotoxicityterm: " + chemicalCompoundSentence.getChemicalCompound().getName() + " and " + hepatotoxicityTermSentence.getHepatotoxicityTerm().getOriginal_entry());
//			}	
//			
//		}	
//	}
//	
//	/**
//	 * 
//	 * @param document_model
//	 * @param sentence_model
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findMarkerChemicalCompoundRelations(Sentence sentence, String sentence_text) {
//		for (MarkerSentence markerSentence : sentence.getMarkerSentences()) {
//			for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
//				MarkerChemicalCompoundSentence markerChemicalCompoundSentence = 
//							new MarkerChemicalCompoundSentence(chemicalCompoundSentence.getChemicalCompound(), markerSentence.getMarker(),1f, 1, sentence, RelationRule.COMENTION);
//				sentence.getMarkerChemicalCompoundSentences().add(markerChemicalCompoundSentence);
//				Boolean cut = false;
//				String type = "";
//				for (MarkerChemicalCompoundPattern pattern : dictionaryService.getMarkerChemicalCompoundPatterns()) {
//					String pattern_text = pattern.getMarker_pattern();
//					type = pattern.getPattern_type();
//					pattern_text = pattern_text.replace("[chemical]", chemicalCompoundSentence.getChemicalCompound().getName());
//					pattern_text = pattern_text.replace("[marker]", markerSentence.getMarker().getMarker_full_name());
//					//pattern_text = pattern_text.replace("number", markerSentence.getMarker().getMarker_full_name());
//					int quantity_pattern = sentenceContains(pattern_text, sentence_text);
//					if(quantity_pattern!=0) {
//						if(type!=null && type.equals("Increase")) {
//							markerChemicalCompoundSentence.setRelationRule(RelationRule.INCREASE);
//							cut=true;
//							System.out.println("pattern marker compound increase: " + pattern_text);
//							break;
//						}else if(type!=null && type.equals("Decrease")) {
//							markerChemicalCompoundSentence.setRelationRule(RelationRule.DECREASE);
//							cut=true;
//							System.out.println("pattern marker compound decrease: " + pattern_text);
//							break;
//						}
//					}
//				}
//				//Save Comention Compound and Cysp
//				System.out.println("compound marker: " + chemicalCompoundSentence.getChemicalCompound().getName() + " and " + markerSentence.getMarker().getMarker_full_name());
//			}	
//		}
//	}
//	
//	/**
//	 * 
//	 * @param document
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findMarkers(Sentence sentence, String sentence_text) {
//		for (Marker marker : dictionaryService.getMarkers()) {
//			int quantity = sentenceContains(marker.getMarker_full_name(), sentence_text);
//			if(quantity!=0) {
//				MarkerSentence markerSentence = new MarkerSentence(marker,1f, quantity, sentence);
//				sentence.getMarkerSentences().add(markerSentence);
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * @param document
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findHepatotoxicityTerms(Sentence sentence, String sentence_text) {
//		for (HepatotoxicityTerm hepatotoxicityTerm : dictionaryService.getHepatotoxicityTerms()) {
//			int quantity = sentenceContains(hepatotoxicityTerm.getOriginal_entry(), sentence_text);
//			if(quantity!=0) {
//				HepatotoxicityTermSentence hepatotoxicityTermSentence = new HepatotoxicityTermSentence(hepatotoxicityTerm,1f, quantity, null,sentence);
//				sentence.getHepatotoxicityTermSentences().add(hepatotoxicityTermSentence);
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @param document
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findSpecies(Sentence sentence, String sentence_text) {
//		for (Taxonomy taxonomy : dictionaryService.getTaxonomies()) {
//			int quantity = sentenceContains(taxonomy.getName(), sentence_text);
//			if(quantity!=0) {
//				TaxonomySentence taxonomySentence = new TaxonomySentence(taxonomy,1f, quantity, sentence);
//				sentence.getTaxonomySentences().add(taxonomySentence);
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * @param document
//	 * @param section
//	 * @param sentence_text
//	 * @param tokens
//	 */
//	private void findCytochromes(Sentence sentence, String sentence_text) {
//		for (Cytochrome cytochrome : dictionaryService.getCytochromes()) {
//			this.findCytochromeByFieldType(cytochrome.getName(), sentence_text, sentence, cytochrome);
//		}
//	}
//
//	
//	/**
//	 * Findings of ChemicalCompunds with synonyms
//	 * @param sourceId
//	 * @param document_model
//	 * @param first_finding_on_document
//	 * @param section
//	 * @param sentence_text
//	 * @return
//	 * @throws MoreThanOneEntityException
//	 */
//	private void findChemicalCompounds(Sentence sentence, String sentence_text) {
//		for (ChemicalCompound chemicalCompound : dictionaryService.getChemicalCompounds()) {
//			//Find and store differentes chemicalCompounds Key Words
//			this.findChemicalCompoundByFieldType(chemicalCompound.getName(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getSmile(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getInchi(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getChemPlusId(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getChebi(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getDrugBankId(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getKeggCompoundId(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getMeshSubstanceId(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getCasRegistryNumber(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getHumanMetabolome(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getPubChemCompundId(), sentence_text, sentence, chemicalCompound);
//			this.findChemicalCompoundByFieldType(chemicalCompound.getPubChemSubstance(), sentence_text, sentence, chemicalCompound);
//		}
//	}
//	
////	/**
////	 * Findings of ChemicalCompunds with synonyms
////	 * @param sourceId
////	 * @param document_model
////	 * @param first_finding_on_document
////	 * @param section
////	 * @param sentence_text
////	 * @return
////	 * @throws MoreThanOneEntityException
////	 */
////	private void findChemicalCompounds(Sentence sentence, String sentence_text) {
////		ChemSpot tagger = chemSpotConfig.getChemSpotTagger();
////		for (Mention mention : tagger.tag(sentence_text)) {
////			System.out.printf("%d\t%d\t%s\t%s\t%s,\t%s%n", 
////					mention.getStart(), mention.getEnd(), mention.getText(), 
////					mention.getCHID(), mention.getSource(), mention.getType().toString(), mention.getCAS(),mention.getCHEB(),mention.getDRUG(),mention.getFDA(),
////					mention.getINCH());
////		}
////	}
//	
//	/**
//	 * 
//	 * @param chemicalCompoundValue
//	 * @param sentence_text
//	 * @param document
//	 * @param section
//	 * @param chemicalCompound
//	 */
//	private void findCytochromeByFieldType(String cytochromeValue,String sentence_text,Sentence sentence, Cytochrome cytochrome) {
//		int quantity = sentenceContains(cytochromeValue, sentence_text);
//		if(quantity!=0) {
//			CytochromeSentence cytochromeSentence = new CytochromeSentence(cytochrome, 1f, quantity, sentence);
//			sentence.getCytochromeSentences().add(cytochromeSentence);
//		}
//	}
//	
//	/**
//	 * 
//	 * @param chemicalCompoundValue
//	 * @param sentence_text
//	 * @param document
//	 * @param section
//	 * @param chemicalCompound
//	 */
//	private void findChemicalCompoundByFieldType(String chemicalCompoundValue,String sentence_text, 
//			Sentence sentence, ChemicalCompound chemicalCompound) {
//		int quantity = sentenceContains(chemicalCompoundValue, sentence_text);
//		if(quantity!=0) {
//			ChemicalCompoundSentence chemicalCompoundSentence = new ChemicalCompoundSentence(chemicalCompound, 1f, quantity, sentence);
//			sentence.getChemicalCompoundSentences().add(chemicalCompoundSentence);
//		}
//	}
}
