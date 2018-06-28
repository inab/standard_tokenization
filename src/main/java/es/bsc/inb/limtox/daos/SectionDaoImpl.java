package es.bsc.inb.limtox.daos;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.model.Section;
@Repository
public class SectionDaoImpl extends GenericDaoImpl<Section> implements SectionDao{

	@Override
    @SuppressWarnings("unchecked")
    public HashMap<String,Section> findAllAsHash() {
    	HashMap<String,Section> hash = new HashMap<String,Section>();
    	Collection<Section> collection = em.createQuery("from  Section x").setFirstResult(0).setMaxResults(1000).getResultList();
    	for (Section section : collection) {
    		hash.put(section.getInternalName(), section);
		}
    	return hash;
    }
}
