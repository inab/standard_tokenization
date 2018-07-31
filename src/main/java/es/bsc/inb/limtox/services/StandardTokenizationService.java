package es.bsc.inb.limtox.services;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;

public interface StandardTokenizationService {

	public void execute(String sourceId, String file_path, String outputPath, PubMedArticle pubMedArticle) ;
	
}
