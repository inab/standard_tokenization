package es.bsc.inb.limtox.daos;

import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Document;


public interface DocumentDao extends GenericDao<Document>{

	public Document findBySourceId(String string) throws MoreThanOneEntityException;

}
