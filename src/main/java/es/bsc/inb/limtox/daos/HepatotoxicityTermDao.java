package es.bsc.inb.limtox.daos;

import java.util.List;

import es.bsc.inb.limtox.model.HepatotoxicityTerm;


public interface HepatotoxicityTermDao extends GenericDao<HepatotoxicityTerm>{

	public void saveAll(List<HepatotoxicityTerm> hepatotoxicityTerms);
	
}
