package es.bsc.inb.limtox.daos;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.model.HepatotoxicityTerm;
@Repository
public class HepatotoxicityTermDaoImpl extends GenericDaoImpl<HepatotoxicityTerm> implements HepatotoxicityTermDao{

	
    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String,HepatotoxicityTerm> findAllAsHash() {
    	HashMap<String,HepatotoxicityTerm> hash = new HashMap<String,HepatotoxicityTerm>();
    	Collection<HepatotoxicityTerm> collection = em.createQuery("from  HepatotoxicityTerm x").getResultList();
    	for (HepatotoxicityTerm hepatotoxicityTerm : collection) {
    		hash.put(hepatotoxicityTerm.getTerm(), hepatotoxicityTerm);
		}
    	return hash;
    }
}
