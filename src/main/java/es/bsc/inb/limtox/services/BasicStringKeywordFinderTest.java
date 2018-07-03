package es.bsc.inb.limtox.services;

import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class BasicStringKeywordFinderTest {
	
	public static String text_2 = "acid induced cholestatic liverdddd injury after acid-induced cholestatic liver*, (acid-induced cholestatic liver) pediatric acid-induced cholestatic liver transplantation acid-induced cholestatic liver.";
	public static String text_3 = "acid induced cholestatic liverdddd gfdgfdgfd ver.";
	public static void main(String[] args) {

		long start=0;
	    long stop=0;
	    int cant=10000;
	    String keyword_to_search = "acid-induced cholestatic liver";
	    
	    start = System.nanoTime();
	    String patternString = "\\b(" + keyword_to_search + ")\\b";
	    Pattern pattern = Pattern.compile(patternString);
	    for (int i = 0; i < cant; i++) {
	    	BasicStringKeywordFinderTest.test_pattern(text_2, pattern);
		}
	    stop = System.nanoTime();
	    System.out.println("Contains: " + (stop-start));
	    
	    start = System.nanoTime();
	    for (int i = 0; i < cant; i++) {
	    	BasicStringKeywordFinderTest.test_pattern_bad(text_2, keyword_to_search);
		}
	    stop = System.nanoTime();
	    System.out.println("Contains: " + (stop-start));
	    
	    start = System.nanoTime();
	    String patternString2 = "\\b" + keyword_to_search + "\\b";
	    Pattern pattern2 = Pattern.compile(patternString2);
	    for (int i = 0; i < cant; i++) {
	    	BasicStringKeywordFinderTest.test_pattern(text_2, pattern2);
		}
	    stop = System.nanoTime();
	    System.out.println("Contains: " + (stop-start));
	    
	    
	    
	    start = System.nanoTime();
	    String chemicalCompoundValue = "(\\W|\\s|^)"+keyword_to_search+"(\\W|\\s|$)";
    	Pattern p = Pattern.compile(chemicalCompoundValue);
    	for (int i = 0; i < cant; i++) {
	    	BasicStringKeywordFinderTest.test_pattern(text_2, p);
		}
    	stop = System.nanoTime();
 	    System.out.println("Contains: " + (stop-start));
 	    
 	   
 	   start = System.nanoTime(); 
 	   BoyerMoore boyermoore1 = new BoyerMoore(keyword_to_search);
 	   for (int i = 0; i < cant; i++) {
 		  int offset1 = boyermoore1.search(text_2);
 	   }
 	   stop = System.nanoTime();
	   System.out.println("Contains: " + (stop-start));
 	    
 	   
// 	   start = System.nanoTime(); 
// 	   Scanner scanner = new Scanner(text_2);
// 	   for (int i = 0; i < 1000; i++) {
//	    	BasicStringKeywordFinderTest.test_scanner(scanner, keyword_to_search);
//	    }
// 	   
//       stop = System.nanoTime();
//	   System.out.println("Contains: " + (stop-start));
 	    
	}

//	private static void test_scanner(Scanner scanner, String keyword_to_search) {
//		scanner.findInLine(keyword_to_search);
//		MatchResult rexp = scanner.match();
//		int count = 0;
//		while (rexp)
//			count++;
//	}

	private static void test_pattern(String sentence, Pattern pattern) {
		Matcher m = pattern.matcher(sentence);
		int count = 0;
		while (m.find())
		    count++;
	}

	
	private static void test_pattern_bad(String sentence, String keyword_to_search) {
		String patternString = "\\b(" + keyword_to_search + ")\\b";
	    Pattern pattern = Pattern.compile(patternString);
		Matcher m = pattern.matcher(sentence);
		Integer count = 0;
		while (m.find())
		    count++;
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
			System.out.println(word + "\t" + pos + "\t" + ner + "\t" + lemma + "\t\n");
		}
		String lemma = sentence.get(LemmaAnnotation.class);
		// this is the parse tree of the current sentence
		 Tree tree = sentence.get(TreeAnnotation.class);
		 System.out.println(tree);
		 
		// this is the Stanford dependency graph of the current sentence
		SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
		System.out.println(dependencies); 
	}
	
	
}
