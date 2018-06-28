package es.bsc.inb.limtox.daos;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.model.ChemicalCompound;
@Repository
public class ChemicalCompoundDaoJSONImpl extends GenericDaoImpl<ChemicalCompound> implements ChemicalCompoundDao{

	
    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String,ChemicalCompound> findAllAsHash() {
    	HashMap<String,ChemicalCompound> hash = new HashMap<String,ChemicalCompound>();
    	Collection<ChemicalCompound> collection = em.createQuery("from  ChemicalCompound x").getResultList();
    	for (ChemicalCompound chemicalCompound : collection) {
    		hash.put(chemicalCompound.getName(), chemicalCompound);
		}
    	return hash;
    }
}
