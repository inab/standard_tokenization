package es.bsc.inb.limtox.daos.jpa;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
@Repository(value="hepatotoxicityTermDaoJPAImpl")
public class HepatotoxicityTermDaoJPAImpl extends GenericDaoJPAImpl<HepatotoxicityTerm> implements HepatotoxicityTermDao{

}
