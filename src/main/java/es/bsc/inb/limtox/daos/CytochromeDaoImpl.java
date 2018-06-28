package es.bsc.inb.limtox.daos;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.model.Cytochrome;
@Repository
public class CytochromeDaoImpl extends GenericDaoImpl<Cytochrome> implements CytochromeDao{

	
    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String,Cytochrome> findAllAsHash() {
    	HashMap<String,Cytochrome> hash = new HashMap<String,Cytochrome>();
    	Collection<Cytochrome> collection = em.createQuery("from  Cytochrome x").getResultList();
    	for (Cytochrome cytochrome : collection) {
    		hash.put(cytochrome.getName(), cytochrome);
		}
    	return hash;
    }
}
