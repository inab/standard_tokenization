package es.bsc.inb.limtox.retrieval.services;

import java.util.List;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;

public interface GenericRetrievalService {

	public List<PubMedArticle> findAllPubMedArticleToProcess();
	
	public PubMedArticle save(PubMedArticle pubMedArticle);
	
}
