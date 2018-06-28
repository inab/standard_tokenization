package es.bsc.inb.limtox.annotators;


import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.simple.Token;
import edu.stanford.nlp.util.CoreMap;


public class ChemicalCompoundAnnotator /*implements Annotator */{

 /* HashMap<String,String> wordToLemma = new HashMap<String,String>();

  public ChemicalCompoundAnnotator(String name, Properties props) {
    // load the lemma file
    // format should be tsv with word and lemma
    
    
  }

  public void annotate(Annotation annotation) {
	List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
	
	for (CoreMap sentence : sentences) {
		String text_sentence = sentence.get(TextAnnotation.class);
    	List<CoreLabel> tokens= sentence.get(TokensAnnotation.class);
    	for (CoreLabel coreLabel : tokens) {
    		String text = coreLabel.get(TextAnnotation.class);
		}
    }
  }

@Override
public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Set<Class<? extends CoreAnnotation>> requires() {
	// TODO Auto-generated method stub
	return null;
}
*/
  


  
  
}
