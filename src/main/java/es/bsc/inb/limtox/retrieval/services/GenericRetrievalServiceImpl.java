package es.bsc.inb.limtox.retrieval.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.retrieval.daos.GenericRetrievalDao;
import es.bsc.inb.limtox.retrieval.model.PubMedArticle;
@Service
public class GenericRetrievalServiceImpl implements GenericRetrievalService{
	@Autowired
	private GenericRetrievalDao genericRetrievalDao;
	
	@Override
	public List<PubMedArticle> findAllPubMedArticleToProcess() {
		return genericRetrievalDao.findAllPubMedArticleToProcess();
		
	}

	@Override
	public PubMedArticle save(PubMedArticle pubMedArticle) {
		return genericRetrievalDao.save(pubMedArticle);
		
	}
	
	
	
}
