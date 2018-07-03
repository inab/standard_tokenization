package es.bsc.inb.limtox.daos.jpa;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.SentenceDao;
import es.bsc.inb.limtox.model.Sentence;
@Repository(value="sentenceDaoJPAImpl")
public class SentenceDaoJPAImpl extends GenericDaoJPAImpl<Sentence> implements SentenceDao{

	
}
