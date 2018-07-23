package es.bsc.inb.limtox.services;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.MentionsAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.tokensregex.CoreMapExpressionExtractor;
import edu.stanford.nlp.ling.tokensregex.Env;
import edu.stanford.nlp.ling.tokensregex.MatchedExpression;
import edu.stanford.nlp.ling.tokensregex.NodePattern;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.ErasureUtils;

public class BasicPipelineExampleCoreNLP {
	
	public static String text = "Joe Smith was born in California. " +
		      "In 2017, he went to Paris, France in the summer. " +
		      "His flight left at 3:00pm on July 10th, 2017. " +
		      "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
		      "He sent a postcard to his sister Jane Smith. " +
		      "After hearing about Joe's trip, Jane decided she might go to France one day.";
	
	public static String text_2 = "I pepotp1234 -14c)-dab like i.e. pizza with xenobiotic, liver toxicity. "
			+ "The xxxx and the liver transplantation 123 basic and because induced on metastases, perihilar extrahepatic bile duct carcinoma. " 
			+ "The xxxx and the liver transplantation basic and because induced metastases. "
			+ "The xxxx and the liver transplantation induced metastases. "
			+ "The protein and the liver transplantation induced by, liver-transplantation chronic cholestatic liver injury after liver pediatric. The interaction between line 1 carcinomas growing s.c. and their spontaneous (or artificial) metastases has been studied in "
			+ "syngeneic BALB/c mice. In contrast to typical murine tumor systems, concomitant immunity, or the ability of primary tumors to suppress the growth "
			+ "of metastases, develops very slowly in this system, such that the metastases that are shed within the first week of tumor growth survive and ultimately prove "
			+ "lethal to the host. The rate of development of concomitant immunity can be accelerated by increasing the hosts' tumor burden or decelerated by exposure of "
			+ "the hosts to X-rays prior to tumor transplant. "
			+ "This cancer system offers, therefore, an excellent model for the therapy of metastases that cannot be obtained "
			+ "with typical murine tumors.";
	
	// My custom tokens
	  public static class MyTokensAnnotation implements CoreAnnotation<List<? extends CoreMap>> {
	    @Override
	    public Class<List<? extends CoreMap>> getType() {
	      return ErasureUtils.<Class<List<? extends CoreMap>>> uncheckedCast(List.class);
	    }
	  }

	  // My custom type
	  public static class MyTypeAnnotation implements CoreAnnotation<String> {
	    @Override
	    public Class<String> getType() {
	      return ErasureUtils.<Class<String>> uncheckedCast(String.class);
	    }
	  }

	  // My custom value
	  public static class MyValueAnnotation implements CoreAnnotation<String> {
	    @Override
	    public Class<String> getType() {
	      return ErasureUtils.<Class<String>> uncheckedCast(String.class);
	    }
	  }
	
	
	public static void main(String[] args) {
	    // set up pipeline properties
	    Properties props = new Properties();
	    // set the list of annotators to run
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, regexner, entitymentions, parse");
	    //props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
	    
	    props.setProperty("tokenize.class", "true");
	    //props.setProperty("tokenize.whitespace", "true");
	    
	    //props.setProperty("tokenize.whitespace", "true");
	    //props.setProperty("tokenize.options", "ptb3Escaping=false");
	    
	    props.put("regexner.mapping", "/home/jcorvi/eclipse-workspace/etox/lexicon/hepatotoxicity.ner.txt");
	    props.put("regexner.posmatchtype", "MATCH_ALL_TOKENS");
	    props.put("rulesFiles", "/home/jcorvi/eclipse-workspace/etox/lexicon/rules_example.rules");
	    // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
	    // build pipeline
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    // create a document object
	    Annotation document= new Annotation(text_2);
	    // annnotate the document
	    pipeline.annotate(document);
	    
	    
	    // set up the TokensRegex pipeline
	    // get the rules files
	    String[] rulesFiles = props.getProperty("rulesFiles").split(",");

	    // set up an environment with reasonable defaults
	    Env env = TokenSequencePattern.getNewEnv();
	    // set to case insensitive
	    env.setDefaultStringMatchFlags(NodePattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	    env.setDefaultStringPatternFlags(Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

	    // build the CoreMapExpressionExtractor
	    CoreMapExpressionExtractor extractor = CoreMapExpressionExtractor.createExtractorFromFiles(env, rulesFiles);
	    
	    
	    
	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	    for (CoreMap sentence: sentences) {
	    	BasicPipelineExampleCoreNLP.text_mining_example(sentence);
	    	List<MatchedExpression> matchedExpressions = extractor.extractExpressions(sentence);
	    	// print out the matched expressions
	        for (MatchedExpression me : matchedExpressions) {
	        	System.out.println("matched expression: "+me.getText());
	        	System.out.println("matched expression value: "+me.getValue());
	        	System.out.println("matched expression char offsets: "+me.getCharOffsets());
	        	System.out.println("matched expression tokens:" + me.getAnnotation().get(CoreAnnotations.TokensAnnotation.class));
	        }
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
