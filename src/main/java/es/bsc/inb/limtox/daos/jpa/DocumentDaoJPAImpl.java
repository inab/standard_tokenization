package es.bsc.inb.limtox.daos.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Document;
@Repository(value="documentDaoJPAImpl")
public class DocumentDaoJPAImpl extends GenericDaoJPAImpl<Document> implements DocumentDao{

	@Override
	@SuppressWarnings("unchecked")
	public Document findBySourceId(String sourceId) throws MoreThanOneEntityException {
		Query query =  em.createQuery("from Document d where d.sourceId=:sourceId");
		query.setParameter("sourceId", sourceId);
		List<Document> documents = query.getResultList();
		if(documents!=null && documents.size()==1) {
			return documents.get(0);
		}else if(documents!=null && documents.size()>1) {
			throw new MoreThanOneEntityException("More than one entity with sourceId " + sourceId + " exist in database ");
		}
		return null;
	}
	
	
	
}
