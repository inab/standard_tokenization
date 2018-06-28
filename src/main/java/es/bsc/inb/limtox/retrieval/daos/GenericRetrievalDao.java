package es.bsc.inb.limtox.retrieval.daos;

import java.util.List;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;

public interface GenericRetrievalDao {

	public List<PubMedArticle> findAllPubMedArticleToProcess();
	
}
