package es.bsc.inb.limtox.retrieval.daos;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;
@Transactional(readOnly=true)
@Repository
public class GenericRetrievalDaoImpl implements GenericRetrievalDao {
	
	@PersistenceContext(unitName="retrievalPersistenceUnit")
    protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PubMedArticle> findAllPubMedArticleToProcess() {
		//Query query =  em.createQuery("from PubMedArticle d where d.plainTextGenerated=1 and d.filePath in ('hepatotoxicity_corpus_pubmed/1','random_pubmed/1') order by d.id");
		Query query =  em.createQuery("from PubMedArticle d where d.plainTextGenerated=1 and d.jsonFindingGenerated=0 order by d.id");
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=false)
    public PubMedArticle save(PubMedArticle t) {
    	if(t.getId()==null || t.getId()==0) {
    		this.em.persist(t);
    		return t;
    	}else {
    		return this.em.merge(t);    
    	}
    }
	
}
