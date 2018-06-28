package es.bsc.inb.limtox.retrieval.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;
@Repository
public class GenericRetrievalDaoImpl implements GenericRetrievalDao {
	
	//@PersistenceContext(unitName="retrievalPersistenceUnit")
    protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PubMedArticle> findAllPubMedArticleToProcess() {
		Query query =  em.createQuery("from PubMedArticle d where d.plainTextGenerated=1 order by d.id");
		return query.getResultList();
	}

}
