package es.bsc.inb.limtox.util;

import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class CoreNLP {
	
	static StanfordCoreNLP pipeline = null;
	
	public static StanfordCoreNLP getStandfordCoreNLP() {
		if (pipeline == null) {
			Properties props = new Properties();
		    // set the list of annotators to run
		    props.setProperty("annotators", "tokenize,ssplit");
		    //props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		    //props.setProperty("customAnnotatorClass.chemicalEntityCompound", "es.bsc.inb.limtox.annotators.ChemicalCompoundAnnotator");
		    
		    // build pipeline
		    //props.setProperty("tokenize.whitespace", "true");
		    pipeline = new StanfordCoreNLP(props);
		    return pipeline;
		}else {
			return pipeline;
		}
	}
}
