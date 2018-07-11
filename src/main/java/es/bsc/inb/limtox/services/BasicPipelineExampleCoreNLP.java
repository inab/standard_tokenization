package es.bsc.inb.limtox.services;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.MentionsAnnotation;
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

public class BasicPipelineExampleCoreNLP {
	
	public static String text = "Joe Smith was born in California. " +
		      "In 2017, he went to Paris, France in the summer. " +
		      "His flight left at 3:00pm on July 10th, 2017. " +
		      "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
		      "He sent a postcard to his sister Jane Smith. " +
		      "After hearing about Joe's trip, Jane decided she might go to France one day.";
	
	public static String text_2 = "Julia Corvi -liver-transplantation, chronic cholestatic liver injury after liver pediatric liver transplantation. The interaction between line 1 carcinomas growing s.c. and their spontaneous (or artificial) metastases has been studied in "
			+ "syngeneic BALB/c mice. In contrast to typical murine tumor systems, concomitant immunity, or the ability of primary tumors to suppress the growth "
			+ "of metastases, develops very slowly in this system, such that the metastases that are shed within the first week of tumor growth survive and ultimately prove "
			+ "lethal to the host. The rate of development of concomitant immunity can be accelerated by increasing the hosts' tumor burden or decelerated by exposure of "
			+ "the hosts to X-rays prior to tumor transplant. "
			+ "This cancer system offers, therefore, an excellent model for the therapy of metastases that cannot be obtained "
			+ "with typical murine tumors.";
	
	public static void main(String[] args) {
	    // set up pipeline properties
	    Properties props = new Properties();
	    // set the list of annotators to run
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, regexner, entitymentions, parse");
	    props.put("regexner.mapping", "/home/jcorvi/eclipse-workspace/etox/lexicon/limtox2.0-regexner.txt");
	    props.put("regexner.posmatchtype", "MATCH_ALL_TOKENS");
	    // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
	    // build pipeline
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    // create a document object
	    Annotation document= new Annotation(text_2);
	    // annnotate the document
	    pipeline.annotate(document);
	    
	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	    for (CoreMap sentence: sentences) {
	    	BasicPipelineExampleCoreNLP.text_mining_example(sentence);
	    }
	    Map<Integer, CorefChain> graph =  document.get(CorefChainAnnotation.class);
	    System.out.println(graph); 
	    
	    
//	    Map<Integer, CorefChain> graph_2 = 
//	    		  document.get(CorefChainAnnotation.class);
//	    System.out.println(graph); 
	}

	/**
	 * 
	 * @param sentence
	 */
	private static void text_mining_example(CoreMap sentence) {
		System.out.println(sentence.get(TextAnnotation.class));
		List<CoreLabel> tokens= sentence.get(TokensAnnotation.class);
		for (CoreLabel token: tokens){
			String word = token.get(TextAnnotation.class);
			String pos = token.get(PartOfSpeechAnnotation.class);
			String ner = token.get(NamedEntityTagAnnotation.class);
			String lemma = token.get(LemmaAnnotation.class);
			System.out.println(word + "\t" + token.beginPosition() + "\t" + token.endPosition() + "\t" + pos + "\t" + ner + "\t" + lemma + "\t\n");
		}
		String lemma = sentence.get(LemmaAnnotation.class);
		// this is the parse tree of the current sentence
		Tree tree = sentence.get(TreeAnnotation.class);
		System.out.println(tree);
		// this is the Stanford dependency graph of the current sentence
		SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
		System.out.println(dependencies); 
		List<CoreMap> entityMentions = sentence.get(MentionsAnnotation.class);
		for (CoreMap coreMap : entityMentions) {
			String keyword = coreMap.get(TextAnnotation.class);
			String entityType = coreMap.get(CoreAnnotations.EntityTypeAnnotation.class);
			System.out.println(keyword + "\t" + entityType);
		}
	}
	
	
}
